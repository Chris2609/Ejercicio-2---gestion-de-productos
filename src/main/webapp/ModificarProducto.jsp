<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form method="POST" action="ModificarProducto">
	
	<input type="hidden" name="actuId" placeholder="Id producto" value="<c:out value="${id}"></c:out>">
	<br>
	<input type="text" name="actuCodigo" placeholder="Codigo producto" value="<c:out value="${codigo}"></c:out>">
	<br>
	<input type="text" name="actuNombre" placeholder="Nombre producto" value="<c:out value="${nombre}"></c:out>">
	<br>
	<input type="number" name="actuCantidad" placeholder="Cantidad producto" value="<c:out value="${cantidad}"></c:out>">
	<br>
	<input type="number" name="actuPrecio" placeholder="Precio producto" value="<c:out value="${precio}"></c:out>">	
	<br>
	<input type="date" name="actuCaducidad" value="<c:out value="${caducidad}"></c:out>">	
	<br>
	<input type="number" name="actuSeccion" placeholder="Seccion producto" value="<c:out value="${idSeccion}"></c:out>">
	<br><br>
	<button type="submit">Modificar</button>	
	</form>
</body>
</html>