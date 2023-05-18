<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>

<a class="btn btn-primary" href="/WareHouse/InsertarProducto">Insertar</a>
	<table class="table">
  <thead>
    <tr>
      <th scope="col">ID</th>
      <th scope="col">Codigo</th>
      <th scope="col">Nombre</th>
      <th scope="col">Cantidad</th>
      <th scope="col">Precio</th>
      <th scope="col">Caducidad</th>
      <th scope="col">ID_Seccion</th>
      <th scope="col">Modificar</th>
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
    </tr>
  </c:forEach>

  </tbody>
</table>

</body>
</html>