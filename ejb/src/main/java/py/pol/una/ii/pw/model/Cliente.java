package py.pol.una.ii.pw.model;

import java.io.Serializable;

public class Cliente implements Serializable {
    
	private static final long serialVersionUID = -5721193536209413888L;
	private Integer id_cliente;
    private String ruc;
    private String nombre;
    private String telefono;
    private Float saldo;
//    [java.lang.Integer, java.lang.String, java.lang.String, java.lang.Float, java.lang.String]
    public Cliente(Integer id_cliente, String ruc, String nombre,Float saldo, String telefono) {
		super();
		this.id_cliente = id_cliente;
		this.ruc = ruc;
		this.nombre = nombre;
		this.telefono = telefono;
		this.saldo = saldo;
	}
	/**
	 * 
	 */
    
    
	public Cliente(int id, String nombre) {
		this.id_cliente = id;
		this.nombre = nombre;
		this.ruc = "123456";
		this.telefono = "4321234";
		this.saldo = new Float(2.0);
		// TODO Auto-generated constructor stub
	}
	
	
	public Integer getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Float getSaldo() {
		return saldo;
	}
	public void setSaldo(Float saldo) {
		this.saldo = saldo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    

}
