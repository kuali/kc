<%@ include file="Include.jsp" %>
<html>
<head>
<title>Kuali Enterprise Notification - Home</title>
<meta name="Author" content="John Fereira">
<meta name="Author" content="Aaron Godert">
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

               	<iframe name="iframe_51148" id="iframe_51148" src="../en/ActionList.do" frameborder="0" scrolling="auto" width="100%" height="100%">
               	</iframe>
<!-- Include the bottom half of the Work Area -->
<%@ include file="WorkAreaBottom.jsp" %>

</tr>
</table>
</div> <!-- end pagebody -->

<%@ include file="Footer.jsp" %>

</body>
</html>