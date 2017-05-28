package py.pol.una.ii.pw.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.apache.ibatis.session.SqlSession;

import py.pol.una.ii.pw.mapper.CompraCabeceraMapper;
import py.pol.una.ii.pw.mapper.CompraDetalleMapper;
import py.pol.una.ii.pw.model.CompraCabecera;
import py.pol.una.ii.pw.model.CompraDetalle;
import py.pol.una.ii.pw.model.Producto;
import py.pol.una.ii.pw.model.Proveedor;
import py.pol.una.ii.pw.util.CSVFileReadingException;
import py.pol.una.ii.pw.util.Compra;
import py.pol.una.ii.pw.util.CompraDet;
import py.pol.una.ii.pw.util.IncorrectFieldException;
import py.pol.una.ii.pw.util.MyBatisUtil;
import py.pol.una.ii.pw.util.ProductNotFoundException;
import py.pol.una.ii.pw.util.ProviderNotFoundException;

@Stateless
public class CompraMasivaService {
	
	@Inject
	private ProductoService productoService;
	
	@Inject
	private ProveedorService proveedorService;
	
	@Inject
    private Validator validator;
	
	
	 @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	    public void massiveRegistration( FileReader fr) throws CSVFileReadingException, IOException{

	        boolean rollback = false;
	        int i = 1;
	        BufferedReader br = new BufferedReader(fr);
	        HashMap<String,String> exceptions = new HashMap<String,String>();
	        exceptions.clear();
	        String l;

	        try {
	            while ( ( l = br.readLine() ) != null ){
	                System.out.println(l);
	                try {
	                    CompraCabecera c = parseCompra(l);
	                    registerCompraCabecera(c);

	                } catch (IncorrectFieldException e) {
	                    exceptions.put( "line" + i,  e.getMessage() );
	                    rollback = true;

	                } catch (ProviderNotFoundException e) {
	                    exceptions.put( "line" + i,  e.getMessage() );
	                    rollback = true;
	                } catch (ConstraintViolationException e) {
	                    exceptions.put( "line" + i,  e.getConstraintViolations().toString() );
	                    rollback = true;
	                } catch (Exception e) {
	                    exceptions.put( "line" + i,  e.getMessage() );
	                    rollback = true;
	                }
	                i++;
	            }
	        } catch (IOException e) {
	            throw new IOException();
	        }

	        if( rollback ){
	      
	            throw new CSVFileReadingException( exceptions.toString() );
	        }

	    }

	    /*private void validateCompra(CompraCabecera compra) throws ConstraintViolationException, ValidationException {
	        // Create a bean validator and check for issues.
	        Set<ConstraintViolation<CompraCabecera>> violations = validator.validate(compra);

	        if (!violations.isEmpty()) {
	            throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
	        }
	    }

	    private void validateCompraDetalle(CompraDetalle detalle) throws ConstraintViolationException, ValidationException {
	        // Create a bean validator and check for issues.
	        Set<ConstraintViolation<CompraDetalle>> violations = validator.validate(detalle);

	        if (!violations.isEmpty()) {
	            throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
	        }
	    }*/

	    private CompraCabecera parseCompra(String s) throws Exception,IncorrectFieldException, ProductNotFoundException, ConstraintViolationException{

	        String[] line =  s.split(",", 3) ;
	        CompraCabecera compra = new CompraCabecera();

	        try {
	            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	            compra.setFecha( formatter.parse(line[0]) );

	        } catch (Exception e) {
	            throw new IncorrectFieldException("Fecha mal especificada");
	        }

	        try {
	            Proveedor provider = proveedorService.selectProveedorById( Integer.parseInt(line[1]) );
	            compra.setProveedor(Integer.parseInt(line[1]) );//verificar
	        } catch (Exception e) {
	            throw new ProviderNotFoundException("Proveedor no encontrado");
	        }

	        ArrayList<CompraDetalle> detallesList = null;
	        try {
	            detallesList = parseDetalles( line[2] );
	        } catch (ProductNotFoundException e) {
	            throw e;
	        } catch (IncorrectFieldException e) {
	            throw e;
	        }
	        compra.setMonto(getMontoCompra(detallesList));
	        
	        registerCompraDetalle(detallesList,compra);

	        return compra;
	    }
	    
	    private Float getMontoCompra(List<CompraDetalle> detalles){
	    	Float monto = new Float(0);
	    	for (CompraDetalle detalle : detalles){
	    		Producto producto  = productoService.selectProductoById(detalle.getProducto());
	    		monto = monto + producto.getPrecio_compra();
	    	}
	    	return monto;
	    	
	    }
	    private ArrayList<CompraDetalle> parseDetalles( String s) throws ProductNotFoundException,IncorrectFieldException{

	        ArrayList<CompraDetalle> lista = new ArrayList<CompraDetalle>();

	        String[] line = s.split(",",-1);

	        for( String detalle : line){
	            String[] prop = detalle.split("-",-1);

	            Integer productId = null;
	            Float cantidad;
	            Producto product;
	            try {
	                productId = Integer.parseInt( prop[0] );
	                cantidad = Float.parseFloat( prop[1] );
	            }catch ( Exception e){
	                throw new IncorrectFieldException("Detalle de compra mal especificado");
	            }

	            try {
	                product = productoService.selectProductoById(productId);
	            }catch( Exception e){
	                throw new ProductNotFoundException("Producto con id: " + productId + "no encontrado");
	            }


	            CompraDetalle det = new CompraDetalle();
	            det.setCantidad( cantidad );
	            det.setProducto( product.getId_producto() );

	            lista.add( det );

	        }

	        if( lista.isEmpty() ){
	            throw  new IncorrectFieldException("La lista de detalles no puede estar vacia");
	        }
	        else {
	            return lista;
	        }
	    }
	
    
    public void registerCompraCabecera(CompraCabecera compraCabecera)  {
    	
    	System.out.println("Registering COMPRA Cabecera --- " + compraCabecera.getProveedor());
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
    	try{
    			CompraCabeceraMapper compracabeceraMapper = session.getMapper(CompraCabeceraMapper.class);
    			compracabeceraMapper.insertCompraCabecera(compraCabecera);	
    		}finally{
    			session.close();
    	}
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void registerCompraDetalle(List<CompraDetalle> detallesList,CompraCabecera compra) throws ConstraintViolationException,Exception{
    	
    	
    	
    	for( CompraDetalle detalle : detallesList ){
    		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
 	           try {
 	               //validateCompraDetalle( detalle );
 	               detalle.setCompraCabecera(compra.getId_compraCabecera());
 	               CompraDetalleMapper compradetalleMapper = session.getMapper(CompraDetalleMapper.class);
 	               compradetalleMapper.insertCompraDetalle(detalle);
 	                	              

 	               Producto producto = productoService.selectProductoById(detalle.getProducto());
 	               producto.setCantidad( producto.getCantidad() + detalle.getCantidad() );
 	               productoService.updateProducto(producto);

 	           }catch ( ConstraintViolationException e ){
 	                throw e;
 	           }catch (Exception e){
 	               throw new Exception(e);
 	           }finally{
 	        	   session.close();
 	           }

 	        }
           

    }

	

}
