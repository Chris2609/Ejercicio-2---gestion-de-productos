package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.DTO.Supermercado;

public class ModeloSupermercado {

	Conector conexion = new Conector();
	
	public ArrayList<Supermercado> obtenerSupers(){
		
		ArrayList<Supermercado> supermercados = new ArrayList<Supermercado>();
		conexion.conectar();
		
		try {
			PreparedStatement obtSupers = conexion.con.prepareStatement("SELECT * FROM supermercados");
			obtSupers.execute();
			
			ResultSet resultado = obtSupers.executeQuery();
			
			while(resultado.next()) {
				Supermercado supermercado = new Supermercado();
				supermercado.setId(resultado.getInt("id"));
				supermercado.setNombre(resultado.getString("nombre"));
				
				supermercados.add(supermercado);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return supermercados;
	}
	
	public void productoSupermercado(int idPro, int idSuper) {
		conexion.conectar();
		
		try {
			PreparedStatement insertarPS = conexion.con.prepareStatement("INSERT INTO productos_supermercados (id_producto, id_supermercado) VALUES (?,?)");
			insertarPS.setInt(1, idPro);
			insertarPS.setInt(2, idSuper);
			
			insertarPS.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
