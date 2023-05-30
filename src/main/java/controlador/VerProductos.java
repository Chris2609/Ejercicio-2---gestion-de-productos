package controlador;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		HttpSession session = request.getSession();
		ArrayList<Producto> productosCar = new ArrayList<Producto>();
		productosCar = (ArrayList<Producto>) session.getAttribute("productosCarrito");
		if (productosCar != null)
		request.setAttribute("productosCarTam", productosCar.size());
		
		if (request.getParameter("orden") != null) {
			if (request.getParameter("orden").equals("asc")) {
				 Collections.sort(productos, new Comparator<Producto>() {
			            @Override
			            public int compare(Producto p1, Producto p2) {
			                return p1.getCodigo().compareTo(p2.getCodigo());
			            }
			        });
			} else if (request.getParameter("orden").equals("desc")) {
			     Collections.sort(productos, new Comparator<Producto>() {
			            @Override
			            public int compare(Producto p1, Producto p2) {
			                return p2.getCodigo().compareTo(p1.getCodigo());
			            }
			        });
			}
		}
		request.setAttribute("productos", productos);
		
		request.getRequestDispatcher("verProductos.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String botonPulsado = request.getParameter("boton");
		
		ArrayList<Producto> productos = new ArrayList<Producto>(); 
		ModeloProducto mproducto = new ModeloProducto();
		
		//aqui empieza el buscador
		if (botonPulsado.equals("buscar")) {
			String busqueda = "";
			
			busqueda = request.getParameter("busqueda");
			
			productos = mproducto.verProductos();
			
			ListIterator<Producto> filtroP = productos.listIterator();
			while(filtroP.hasNext()) {
				Producto siguienteP = new Producto();
				siguienteP = filtroP.next();
				if(!siguienteP.getNombre().contains(busqueda) && !siguienteP.getCodigo().contains(busqueda)) {
					filtroP.remove();
				}
			}
			
			request.setAttribute("productos", productos);
			request.getRequestDispatcher("verProductos.jsp").forward(request, response);
			
			
		} else if (botonPulsado.equals("filtrar")) {
			int minimo = 0; 
			int maximo = 9999;
			
			if (request.getParameter("minimo") != "") {
				minimo = Integer.parseInt(request.getParameter("minimo"));
			}
			if (request.getParameter("maximo") != "") {
				maximo = Integer.parseInt(request.getParameter("maximo"));
			}

			productos = mproducto.verProductos();
			
			ListIterator<Producto> precio = productos.listIterator();
			while(precio.hasNext()) {
				Producto siguienteP = new Producto();
				siguienteP = precio.next();
				if(siguienteP.getPrecio() < minimo || siguienteP.getPrecio() > maximo) {
					precio.remove();
				}
			}
			request.setAttribute("productos", productos);
			request.getRequestDispatcher("verProductos.jsp").forward(request, response);
		
		} else if (botonPulsado.equals("eliminarProductos")){
						
			String[] codigosElim = request.getParameter("codigosElim").split(",");
			
			
			boolean existe = true;

				for (String codigo : codigosElim) {
					if (existe == true) {
						existe = mproducto.comprobarCodigo(codigo);
					}
				}
				
				if (existe == true) {
					for (String codigo : codigosElim) {
						mproducto.eliminarProducCod(codigo);
					}
				}
				
			productos = mproducto.verProductos();
			request.setAttribute("productos", productos);

			
			request.getRequestDispatcher("verProductos.jsp").forward(request, response);

		}

		
		

	}

}
