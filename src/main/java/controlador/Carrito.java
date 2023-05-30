package controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.DAO.ModeloProducto;
import modelo.DTO.Producto;

/**
 * Servlet implementation class Carrito
 */
@WebServlet("/Carrito")
public class Carrito extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Carrito() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idProduc = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		
		ArrayList<Producto> productosCarrito = new ArrayList<Producto>();
		if((ArrayList<Producto>) session.getAttribute("productosCarrito") !=null) {
			productosCarrito = (ArrayList<Producto>) session.getAttribute("productosCarrito");
		}
		ModeloProducto mproducto = new ModeloProducto();
		
		productosCarrito.add(mproducto.obtenerProductoID(idProduc));
		
		
		session.setAttribute("productosCarrito", productosCarrito);
		
		response.sendRedirect(request.getContextPath() + "/VerProductos");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
