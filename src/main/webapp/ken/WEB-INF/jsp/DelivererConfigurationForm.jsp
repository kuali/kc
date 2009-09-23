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
<title>Kuali Enterprise Notification - Delivery Type Configuration</title>
<meta name="Author" content="John Fereira">
<link href="css/notification.css" rel="stylesheet" type="text/css" />
</head>
<body>

        <div style="padding: 5px">
            <div class="title">Configure Delivery Types</div>
			<p>Enter the appropriate information for each delivery type
            then select which channels for which you want the delivery
            type enabled.  Select "None" in channel list to remove a delivery
            type for all channels. 
            </p>
            <div style="color: red; padding: 2px;" id="message">
            <c:if test="${not empty errorList}">
	            <strong>Error(s):</strong><br />
	            <c:forEach var="error" items="${errorList}">
		            <c:out value="${error}" /><br/>
	            </c:forEach>
            </c:if>
            <c:if test="${empty errorList}">
            	<c:if test="${not empty message}" ><c:out value="${message}" /></c:if>
            </c:if>
            </div>
            <form name="DelivererConfiguration" action="SaveDelivererConfiguration.form">
			<table  border="0" cellpadding="0" cellspacing="0" class="bord-all" width="80%">
                     <tr>
                       <td class="thnormal"><strong>Delivery Type</strong></td>
                       <td class="thnormal" width="15%"/>
                       <td class="thnormal" width="25%"><strong>Channels</strong></td>
                     </tr>
				<c:forEach var="deliveryType" items="${deliveryTypes}">
                     <c:if test="${deliveryType.name != 'KEWActionList'}" >
					 <tr>
						<td class="thnormal">
                             <p><strong><c:out value="${deliveryType.title}" /></strong></p>
                             <p>
                             <c:out value="${deliveryType.description}" />
                             </p>
                             <c:if test="${! empty deliveryType.preferenceKeys}" >
                             
                             <c:forEach var="entry" items="${deliveryType.preferenceKeys}">
                             <c:set var="keyname"><c:out value="${deliveryType.name}" />.<c:out value="${entry.key}" /></c:set>
                             
                             <div style="margin-left: 2px; padding: 2px">
                             <c:out value="${entry.value}" />:<br /> <input type="text" size="20" name="<c:out value="${keyname}"  />" value="<c:out value="${preferences[keyname]}" />"/>
                             </div>
                             </c:forEach>                                             
                             </c:if>
                             
                             </td>
                             <td class="thnormal" valign="center"><center><strong><i>Enabled For &gt;&gt;</i></strong></center></td>
						     <td class="thnormal" align="center" valign="center" width="25%">
                             <c:set var="channellist"><c:out value="${deliveryType.name}" />.channels</c:set>
                             <select multiple="true" name="<c:out value="${channellist}" />" id="<c:out value="${channellist}" />">
                             <option value="0" >None</option> 
                             <c:forEach var="channel" items="${channels}">
                             <c:set var="chid"><c:out value="${deliveryType.name}" />.<c:out value="${channel.id}" /></c:set>
                             <option value="<c:out value="${channel.id}" />"  <c:if test="${not empty currentDeliverersMap[chid]}" > selected="true" </c:if> ><c:out value="${channel.name}" /></option>
                             </c:forEach>
                             </select>
                             </td>
					</tr>
                    </c:if>
				    </c:forEach>
                     <tr>
                     <td class="thnormal" colspan="3" align="center">
                     <input type="image" src="images/buttonsmall_save.gif" value="save" alt="Save" name="save"/>
                     <img src="images/transparent_002.gif" height="1" width="1">
                     <a href="${ConfigProperties.application.url}/portal.do">
                       <img src="${ConfigProperties.ken.url}/images/buttonsmall_cancel.gif" alt="Cancel" border="0" />
                     </a>
                     </td>
                     </tr>
			</table>
            </form> 								
		</div>

</body>
</html>
