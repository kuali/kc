<%@ include file="Include.jsp"%>

<html>
<head>
<title>Kuali Enterprise Notification - Notification Details</title>
<meta name="Author" content="John Fereira">
<link href="css/notification.css" rel="stylesheet" type="text/css" />
<c:if test="${! empty message}">
	<script type="text/javascript">
        // the intent of this block is to reload the notification "portal" page
        // when action has been taken on a notification rendered via this view
        // (as indicated by a non-empty 'ackmessage')
        // if this page has been opened from the notification portal, then
        // the parent of THIS page will be the actionlist frame, and the parent
        // of THAT page, is the notification portal, which needs to be refreshed
        // for now we are just hardcoding this relationship
        
        // super duper NPE avoidance for the case that this view was launched
        // from somewhere other than the notification portal (e.g. search results)
        if (parent != null) {
        // &&
         //   parent.parent != null &&
          //  parent.parent.frames[0] != null &&
           // parent.parent.frames[0].location != null) {

            //parent.parent.frames[0].location.reload();
            
            parent.location.reload();
        }
	</script>
</c:if>
</head>
<body>


<div id="pagebody">
	<%@ include file="NotificationDetailInclude.jsp" %>	
</div>
<!-- end pagebody -->


</body>
</html>
