package py.pol.una.ii.pw.model;

import java.io.Serializable;
import java.util.Date;

public class CompraCabecera implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3051916410557101964L;

	private Integer id_compraCabecera;

	private Integer id_proveedor;	
	private Date fecha;
	private Float monto;
	public Integer getId_compraCabecera() {
		return id_compraCabecera;
	}
	public void setId_compraCabecera(Integer id_compraCabecera) {
		this.id_compraCabecera = id_compraCabecera;
	}
	public Integer getProveedor() {
		return id_proveedor;
	}
	public void setProveedor(Integer id_proveedor) {
		this.id_proveedor = id_proveedor;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Float getMonto() {
		return monto;
	}
	public void setMonto(Float monto) {
		this.monto = monto;
	}

	
	
}
