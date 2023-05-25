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
	
	<c:if test="${existe eq true}">
		<h1>Ha ocurrido un error con alguno de los campos, revisa los datos antes de enviar</h1>
	</c:if>

	<form method="POST" action="InsertarProducto">

	<input type="text" name="nuevoCodProduc" placeholder="Codigo producto">
	<br>
	<input type="text" name="nuevoNomProduc" placeholder="Nombre producto">
		<br>
	<input type="text" name="nuevoCantProduc" placeholder="Cantidad producto">
			<br>
	<input type="text" name="nuevoPrecProduc" placeholder="Precio producto">
		<br>
	<input type="date" pattern="yyyy-MM-dd" name="nuevoCadProduc">
		<br>
		
		<select name="nuevaSeccion">
		
	  <c:forEach var="seccion" items="${listaSecciones}">
		<option value="${seccion.id}">${seccion.nombre}</option>
		</c:forEach>
		</select>
	
	<c:forEach var="supermercado" items="${listaSupers}">
		<br>
	<input type="checkbox" name="supermercados" value="${supermercado.id}">
	<label>${supermercado.nombre}</label>
	</c:forEach>
		
	
	<button type="submit">Enviar</button>
	</form>
</body>
</html>