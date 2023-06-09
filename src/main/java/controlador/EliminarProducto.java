package controlador;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.DAO.ModeloProducto;

/**
 * Servlet implementation class EliminarProducto
 */
@WebServlet("/EliminarProducto")
public class EliminarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarProducto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int id;
		
		id = Integer.parseInt(request.getParameter("id"));
		
		ModeloProducto mProducto = new ModeloProducto();
		
		int cantidad = mProducto.obtenerCantidad(id);
		boolean existeEnSupers = mProducto.comprobarProducSuper(id);
		if(cantidad > 0) {
			mProducto.restarUnProduc(id);
		}else if(cantidad == 0 && existeEnSupers == true) {
			mProducto.eliminarProducSuper(id);
		}
		if(cantidad == 0 && existeEnSupers == false) {
			mProducto.eliminarProduc(id);
		}
		
		response.sendRedirect(request.getContextPath() + "/VerProductos");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String botonPulsado = request.getParameter("boton");
		
		if(botonPulsado.equals("eliminarSeleccionados")) {
			
			String[] idProductos = request.getParameterValues("productosEliminar");
			int[] idProductosInt = Arrays.stream(idProductos).mapToInt(Integer::parseInt).toArray();
			
			ModeloProducto mProducto = new ModeloProducto();
			for (int idProducto : idProductosInt) {
				mProducto.eliminarProduc(idProducto);
			}
			
			response.sendRedirect(request.getContextPath() + "/VerProductos");
		}
		
	}

}
