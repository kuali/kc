<!--    /auth/loginRedirect.jsp  -->
<%@ page errorPage="/error.jsp" %>
<html>
<head>
<script language="JavaScript1.1">
function redirectLogin(){
    parent.location='/index.jsp';
}
</script>
</head>
<body onLoad="redirectLogin()">
<!--Reload login page in top frame-->
</body>
</html>