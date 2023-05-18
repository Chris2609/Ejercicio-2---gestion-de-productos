package controlador;

import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.DTO.Producto;

/**
 * Servlet implementation class ModificarProducto
 */
@WebServlet("/ModificarProducto")
public class ModificarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarProducto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Producto producto = new Producto();
		
		int id = Integer.parseInt(request.getParameter("id"));
		String codigo = (request.getParameter("codigo"));
		String nombre = (request.getParameter("nombre"));
		int cantidad = Integer.parseInt(request.getParameter("cantidad"));
		double precio = Double.parseDouble(request.getParameter("precio"));
		
		Date caducidad = null;
		try {
			caducidad = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("caducidad"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		int idSeccion = Integer.parseInt(request.getParameter("seccion"));
		
		request.setAttribute("id", id);
		request.setAttribute("codigo", codigo);
		request.setAttribute("nombre", nombre);
		request.setAttribute("cantidad", cantidad);
		request.setAttribute("precio", precio);
		request.setAttribute("caducidad", caducidad);
		request.setAttribute("idSeccion", idSeccion);

		
		request.getRequestDispatcher("ModificarProducto.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
	
	}

}
