package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.DAO.ModeloProducto;
import modelo.DTO.Producto;

/**
 * Servlet implementation class VerProductos
 */
@WebServlet("/VerProductos")
public class VerProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerProductos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<Producto> productos = new ArrayList<Producto>(); 
		ModeloProducto mproducto = new ModeloProducto();
		
		productos = mproducto.verProductos();
		request.setAttribute("productos", productos);
		
		request.getRequestDispatcher("verProductos.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String busqueda = "";
		
		busqueda = request.getParameter("busqueda");
		
		ArrayList<Producto> productos = new ArrayList<Producto>(); 
		ModeloProducto mproducto = new ModeloProducto();
		
		productos = mproducto.verProductos();
		
		ListIterator<Producto> filtroP = productos.listIterator();
		while(filtroP.hasNext()) {
			Producto siguienteP = new Producto();
			siguienteP = filtroP.next();
			if(!siguienteP.getNombre().contains(busqueda) && !siguienteP.getCodigo().contains(busqueda) ) {
				filtroP.remove();
			}
		
		}
		
		request.setAttribute("productos", productos);
		request.getRequestDispatcher("verProductos.jsp").forward(request, response);
		
	}

}
