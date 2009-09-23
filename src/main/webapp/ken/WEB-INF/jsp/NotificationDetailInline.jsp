<%--
 Copyright 2007-2009 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl2.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
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
