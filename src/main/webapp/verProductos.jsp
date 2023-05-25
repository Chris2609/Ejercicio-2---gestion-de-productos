<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>

<a class="btn btn-primary" href="/WareHouse/InsertarProducto">Insertar</a>

<h3>Buscador</h3>
<form method="POST" action="VerProductos">
	<input type="text" name="busqueda" placeholder="Codigo o nombre">
	
	<button type="submit" name="boton" value="buscar">Buscar</button>
</form>

<form method="POST" action="VerProductos">

<h3>Rango de precios</h3>
<input type="number" name="minimo" placeholder="minimo">
<input type="number" name="maximo" placeholder="maximo">

	<button type="submit" name="boton" value="filtrar">Filtrar</button>
</form>

<h3>Eliminar productos</h3>
<form method="POST" action="VerProductos">
<input type="text" name="codigosElim" placeholder="Eliminar productos">

	<button type="submit" name="boton" value="eliminarProductos">Eliminar</button>
</form>

	<table class="table">
  <thead>
    <tr>
      <th scope="col">ID</th>
      <th scope="col">Codigo <a href="./VerProductos?orden=asc">Ascendente</a> <a href="./VerProductos?orden=desc">Descendente</a></th>
      <th scope="col">Nombre</th>
      <th scope="col">Cantidad</th>
      <th scope="col">Precio</th>
      <th scope="col">Caducidad</th>
      <th scope="col">ID_Seccion</th>
      <th scope="col">Modificar</th>
      <th scope="col">Eliminar</th>
      
    </tr>
  </thead>
  <tbody>
  
  <c:forEach var="productos" items="${productos}">
      <tr>
      <td><c:out value="${productos.id}"></c:out></td>
      <td><c:out value="${productos.codigo}"></c:out></td>
      <td><c:out value="${productos.nombre}"></c:out></td>
      <td><c:out value="${productos.cantidad}"></c:out></td>
      <td><c:out value="${productos.precio}"></c:out></td>
      <td><c:out value="${productos.caducidad}"></c:out></td>
      <td><c:out value="${productos.seccion.nombre}"></c:out></td>
      <td><a class="btn btn-warning" href="./ModificarProducto?id=<c:out value ="${productos.id}"></c:out>&codigo=<c:out value ="${productos.codigo}"></c:out>&nombre=<c:out value ="${productos.nombre}"></c:out>&cantidad=<c:out value ="${productos.cantidad}"></c:out>&precio=<c:out value ="${productos.precio}"></c:out>&caducidad=<c:out value ="${productos.caducidad}"></c:out>&seccion=<c:out value ="${productos.seccion.id}"></c:out>">Modificar</a></td>
      <td><a class="btn btn-danger" href="./EliminarProducto?id=<c:out value="${productos.id}"></c:out>">Eliminar</a></td>
    </tr> 
  </c:forEach>

  </tbody>
</table>

</body>
</html>