<%@ include file="Include.jsp"%>

<html>
<head>
<title>Kuali Enterprise Notification - Notification Action Taken</title>
<meta name="Author" content="Aaron Godert (ag266 at cornell dot edu)">
<link href="css/notification.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	self.opener.location.reload();
	self.close();
</script>
</head>
<body>

<div id="pagebody">
		<!-- actually render page if javascript is turned off -->
       	<center>
	       	<br/>
	       	<br/>
	       	<br/>
	       	<br/>
	       	<br/>
	       	<b>The action taken on this notification was successful!</b>
	       	<br/>
	       	<br/>
	       	<b>Please close this window and refresh your action list if it was not refreshed automatically.</b>
	       	<br/>
	       	<br/>
	       	<br/>
	       	<br/>
	       	<br/>
	       	<br/>
	       	<br/>
	       	<br/>
	       	<a href="javascript:self.close()">
	         	<img src="images/buttonsmall_close.gif" border="0" alt="close" />
            </a>  
       	</center>
</div>

</body>
</html>