package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.DTO.Seccion;

public class ModeloSeccion {

	Conector conexion = new Conector();
	
		public ArrayList<Seccion> obtenerSecciones(){
			ArrayList<Seccion> secciones = new ArrayList<Seccion>();
			conexion.conectar();
			
			try {
				PreparedStatement obtSecciones = conexion.con.prepareStatement("SELECT * FROM secciones");
				
				ResultSet resultado = obtSecciones.executeQuery();
				
				while(resultado.next()) {
					Seccion seccion = new Seccion();
					seccion.setId(resultado.getInt("id"));
					seccion.setNombre(resultado.getString("nombre"));
					
					secciones.add(seccion);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conexion.cerrar();
			return secciones;
		}
		
		public Seccion seccionProduc(int id_seccion) {
			Seccion seccion = new Seccion();
			conexion.conectar();
			
			try {
				PreparedStatement rolPro = conexion.con.prepareStatement("SELECT * FROM secciones WHERE id = ?");
				rolPro.setInt(1, id_seccion);
				
				ResultSet resultado = rolPro.executeQuery();
					
				while(resultado.next()) {
					seccion.setId(resultado.getInt("id"));
					seccion.setNombre(resultado.getString("nombre"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			conexion.cerrar();
			return seccion;
			
		}
	
	
}
