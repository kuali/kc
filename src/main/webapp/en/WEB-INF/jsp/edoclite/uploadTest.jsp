<html>
	<body>
		<b><%=request.getAttribute("message")%></b><br>
		<form action="/en-dev/uploadTest" enctype="multipart/form-data" method="post">
		
		    <input type="text" name="name"/><br>
			<input type="file" name="example"/><br>
			<input type="submit"/>
		</form>
	</body>
</html>