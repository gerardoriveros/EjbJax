package py.pol.una.ii.pw.mapper;

import java.util.List;

import py.pol.una.ii.pw.model.CompraCabecera;

public interface CompraCabeceraMapper {
	
	public List<CompraCabecera> getAllCompraCabecera();
	
	public CompraCabecera selectCompraCabeceraById(int id);
	
	public void insertCompraCabecera(CompraCabecera compraCabecera);
	
	public Integer selectNextCompraCabecera();

}
