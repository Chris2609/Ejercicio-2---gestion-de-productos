package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.DTO.Producto;

public class ModeloProducto {

	Conector conexion = new Conector();
	
	public ArrayList<Producto> verProductos() {
		ArrayList<Producto> productos = new ArrayList<Producto>();
		conexion.conectar();
		try {
			PreparedStatement ver = conexion.con.prepareStatement("SELECT * FROM productos");
			
			ResultSet resultado = ver.executeQuery();
			
			while(resultado.next()){
				Producto producto = new Producto();
				producto.setId(resultado.getInt("id"));
				producto.setCodigo(resultado.getString("codigo"));
				producto.setNombre(resultado.getString("nombre"));
				producto.setCantidad(resultado.getInt("cantidad"));
				producto.setPrecio(resultado.getDouble("precio"));
				producto.setCaducidad(resultado.getDate("caducidad"));
				producto.setId_seccion(resultado.getInt("id_seccion"));
				
				productos.add(producto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return productos;
	}
	
}
