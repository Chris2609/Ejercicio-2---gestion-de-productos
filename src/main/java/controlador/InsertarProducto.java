package controlador;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.DAO.ModeloProducto;
import modelo.DAO.ModeloSeccion;
import modelo.DTO.Producto;
import modelo.DTO.Seccion;

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

		ModeloSeccion secciones = new ModeloSeccion();
		ArrayList<Seccion> listaSecciones = new ArrayList<Seccion>();
		listaSecciones = secciones.obtenerSecciones();
		request.setAttribute("listaSecciones", listaSecciones);
		
		boolean existe = false;
		existe = request.getParameter("existe") != null;
		request.setAttribute("existe", existe);

		request.getRequestDispatcher("InsertarProducto.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModeloProducto insertarP = new ModeloProducto();
		Producto producto = new Producto();
		
		Date fecha_cad = null;
		try {
			fecha_cad = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("nuevoCadProduc"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		producto.setId(Integer.parseInt(request.getParameter("nuevoIdProduc")));
		producto.setCodigo(request.getParameter("nuevoCodProduc"));
		producto.setNombre(request.getParameter("nuevoNomProduc"));
		producto.setCantidad(Integer.parseInt(request.getParameter("nuevoCantProduc")));
		producto.setPrecio(Double.parseDouble(request.getParameter("nuevoPrecProduc")));
		producto.setCaducidad((fecha_cad));
		
		Seccion seccion = new Seccion();
		seccion.setId(Integer.parseInt(request.getParameter("nuevaSeccion")));
		producto.setSeccion(seccion);
		
		boolean existe = insertarP.comprobarCodigo(producto.getCodigo());
		Date hoy = new Date();
		if(existe == false && producto.getPrecio() > 0 && producto.getCantidad() > 0 && producto.getCaducidad().after(hoy) && producto.getSeccion() != null) {
			insertarP.insertarProducto(producto);
			response.sendRedirect(request.getContextPath() + "/VerProductos");
		}else {
			existe = true;
			response.sendRedirect(request.getContextPath() + "/InsertarProducto?existe="+existe);
		}
	}

}
