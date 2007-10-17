<%@ include file="Include.jsp" %>
<html>
<head>
<title>Kuali Enterprise Notification - Search</title>
<meta name="Author" content="John Fereira">
<meta name="Author" content="Aaron Godert">
<meta name="Author" content="Aaron Hamid">
<link href="css/notification.css" rel="stylesheet" type="text/css" />
</head>
<body>
 
<%@ include file="Header.jsp" %>

<div id="pagebody">
<table width="100%" border="0" align="center" cellpadding="3" cellspacing="0"  summary="">
<tr>
	<td colspan="2">

		<%@ include file="LogoutForm.jsp" %>
		
	</td>
</tr>
<tr>

<!-- Include the Menu -->
<%@ include file="Menu.jsp" %>

<!-- Include the top half of the Work Area -->
<%@ include file="WorkAreaTop.jsp" %>

<div class="error-div">
<h3>An error occurred</h3>

<div class="exception">
${exception}
</div>
</div>

<!-- Include the bottom half of the Work Area -->
<%@ include file="WorkAreaBottom.jsp" %>

</tr>
</table>
</div> <!-- end pagebody -->

<%@ include file="Footer.jsp" %>

</body>
</html>
