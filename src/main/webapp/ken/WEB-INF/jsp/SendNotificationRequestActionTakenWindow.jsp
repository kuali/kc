<%@ include file="Include.jsp"%>

<html>
<head>
<title>Kuali Enterprise Notification - Request to Send Notification ${workflowActionTaken}</title>
<meta name="Author" content="Aaron Godert (ag266 at cornell dot edu)">
<link href="css/notification.css" rel="stylesheet" type="text/css" />
<c:if test="${(! empty workflowActionTaken) && (standaloneWindow eq 'false')}">
	<script type="text/javascript">
		parent.parent.frames[0].location.reload();
	</script>
</c:if>
<c:if test="${(! empty workflowActionTaken) && (standaloneWindow eq 'true')}">
	<script type="text/javascript">
		self.opener.location.reload();
		self.close();
	</script>
</c:if>
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
	       	<b>The request to send the notification was successfully ${workflowActionTaken}!</b>
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