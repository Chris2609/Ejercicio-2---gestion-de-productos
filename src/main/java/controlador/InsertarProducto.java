package controlador;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.DAO.ModeloProducto;
import modelo.DTO.Producto;

/**
 * Servlet implementation class InsertarProducto
 */
@WebServlet("/InsertarProducto")
public class InsertarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertarProducto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.getRequestDispatcher("InsertarProducto.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModeloProducto insertarP = new ModeloProducto();
		Producto producto = new Producto();
		
		Date fecha_login = null;
		try {
			fecha_login = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("nuevoCadProduc"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		producto.setId(Integer.parseInt(request.getParameter("nuevoIdProduc")));
		producto.setCodigo(request.getParameter("nuevoCodProduc"));
		producto.setNombre(request.getParameter("nuevoNomProduc"));
		producto.setCantidad(Integer.parseInt(request.getParameter("nuevoCantProduc")));
		producto.setPrecio(Double.parseDouble(request.getParameter("nuevoPrecProduc")));
		producto.setCaducidad((fecha_login));
		
		insertarP.insertarProducto(producto);
		
		response.sendRedirect(request.getContextPath() + "/VerProductos");

	}

}
