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
			PreparedStatement insertarP = conexion.con.prepareStatement("INSERT INTO productos (codigo, nombre, cantidad, precio, caducidad, id_seccion) VALUES (?,?,?,?,?,?)");
			insertarP.setInt(1, producto.getId());
			insertarP.setString(1, producto.getCodigo());
			insertarP.setString(2, producto.getNombre());
			insertarP.setInt(3, producto.getCantidad());
			insertarP.setDouble(4, producto.getPrecio());
			insertarP.setDate(5, new Date(producto.getCaducidad().getTime()));
			insertarP.setInt(6, producto.getSeccion().getId());
			insertarP.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean comprobarCodigo(String codigo) {
		boolean existe = false;
		
		ArrayList<Producto> codigosp = new ArrayList<Producto>();
		conexion.conectar();
		
		try {
			PreparedStatement compCod = conexion.con.prepareStatement("SELECT codigo FROM productos WHERE codigo = ?");
			compCod.setString(1, codigo);
			
			ResultSet resultado = compCod.executeQuery();
			
			while(resultado.next()) {
				existe = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return existe;
	}
	
	public void actualizarProduc(Producto producto) {
		conexion.conectar();
		
		try {
			PreparedStatement actualizarP = conexion.con.prepareStatement("UPDATE productos SET codigo = ?, nombre = ?, cantidad = ?, precio = ?, caducidad = ?, id_seccion = ? WHERE id = ?");
			
			actualizarP.setString(1, producto.getCodigo());
			actualizarP.setString(2, producto.getNombre());
			actualizarP.setInt(3, producto.getCantidad());
			actualizarP.setDouble(4, producto.getPrecio());
			actualizarP.setDate(5, new Date (producto.getCaducidad().getTime()));
			actualizarP.setInt(6, producto.getSeccion().getId());
			actualizarP.setInt(7, producto.getId());
			
			actualizarP.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void eliminarProduc(int id) {
		conexion.conectar();
		
		try {
			PreparedStatement eliminarP = conexion.con.prepareStatement("DELETE FROM productos WHERE id = ?");
			eliminarP.setInt(1, id);
			eliminarP.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int idProducInsertado(String codPro) {
		conexion.conectar();
		int idPro = 0;
		try {
			PreparedStatement idProduc = conexion.con.prepareStatement("SELECT id FROM productos WHERE codigo = ?");
			idProduc.setString(1, codPro);
			
			ResultSet resultado = idProduc.executeQuery();
			
			while(resultado.next()) {
				idPro = resultado.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return idPro;
	}
}
