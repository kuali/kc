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
<title>Kuali Enterprise Notification - My Preferences</title>
<meta name="Author" content="John Fereira">
<link href="css/notification.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript">
function subscribe() {
   document.forms['UpdatePreference'].action = 'SubscribeToChannel.form';
   document.forms['UpdatePreference'].submit();
}

function unsubscribe() {
   document.forms['UpdatePreference'].action = 'UnsubscribeFromChannel.form';
   document.forms['UpdatePreference'].submit();
}
</script>
</head>
<body>

		<div style="padding: 5px">						
        <div class="title">Channel Subscriptions</div>
		
		<form name="UpdatePreference" action="#">
			<table  border="0" cellpadding="0" cellspacing="0"
				class="bord-all" width="60%">
				<tr>
					<td class="thnormal"><strong>Channel Name</strong></td>
					<td class="thnormal" width="20%">&nbsp;</td>
				</tr>
				<c:forEach var="channel" items="${channels}">
					<tr>
						<td class="thnormal"><c:out	value="${channel.name}" /></td>
						<td align="center" width="15%">
		                    <c:set var="chid"><c:out value="${channel.id}" /></c:set>
		                                                                
		                    <c:if test="${empty currentsubs[chid]}" >  
		                    <a href="<c:url value="SubscribeToChannel.form"><c:param name="channelid" value="${channel.id}"/></c:url>">
		                    <img src="images/buttonsmall_subscribe.gif" border="0" alt="Subscribe" /></a>                                                   
		                    </c:if>
		                    <c:if test="${not empty currentsubs[chid]}" > 
		                    <a href="<c:url value="UnsubscribeFromChannel.form"><c:param name="channelid" value="${channel.id}"/></c:url>">
		                    <img src="images/buttonsmall_unsubscribe.gif" border="0" alt="Unsubscribe" /></a>                                                   
		                    </c:if>
		                    </td>
					</tr>
				</c:forEach>
			</table>
		</form>        					
		</div>

</body>
</html>
