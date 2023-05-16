<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form method="POST" action="InsertarProducto">
	<input type="number" name="nuevoIdProduc" placeholder="ID producto">
	<br>
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
	<input type="text" name="nuevoSeccProduc" placeholder="Seccion producto">
	
	<button type="submit">Enviar</button>
	</form>
</body>
</html>