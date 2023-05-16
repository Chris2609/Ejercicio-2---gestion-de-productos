package modelo.DAO;

import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.DTO.Producto;
import modelo.DTO.Seccion;

public class ModeloProducto {

	Conector conexion = new Conector();
	ModeloSeccion seccionProduc = new ModeloSeccion();
	
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
				producto.setSeccion(seccionProduc.seccionProduc(resultado.getInt("id_seccion")));
				
				productos.add(producto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return productos;
	}
	
	/**
	 * @param producto
	 */
	public void insertarProducto(Producto producto) {
		
		conexion.conectar();
		
		try {
			PreparedStatement insertarP = conexion.con.prepareStatement("INSERT INTO productos VALUES (?,?,?,?,?,?,?)");
			insertarP.setInt(1, producto.getId());
			insertarP.setString(2, producto.getCodigo());
			insertarP.setString(3, producto.getNombre());
			insertarP.setInt(4, producto.getCantidad());
			insertarP.setDouble(5, producto.getPrecio());
			insertarP.setDate(6, new Date(producto.getCaducidad().getTime()));
			insertarP.setInt(7, producto.getSeccion().getId());
			insertarP.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
