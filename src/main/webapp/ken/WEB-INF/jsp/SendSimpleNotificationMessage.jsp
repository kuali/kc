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
<title>Kuali Enterprise Notification - Send a Simple Notification</title>
<meta name="Author" content="Aaron Godert (ag266 at cornell dot edu)">
<link href="css/notification.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="scripts/jscalendar-1.0/calendar.js"></script>
<script language="JavaScript" type="text/javascript" src="scripts/jscalendar-1.0/lang/calendar-en.js"></script>
<script language="JavaScript" type="text/javascript" src="scripts/jscalendar-1.0/calendar-setup.js"></script>
<style type="text/css">@import url(scripts/jscalendar-1.0/calendar-win2k-1.css);</style>
</head>
<body>

		<div style="padding: 5px">						
        <div class="title">Send a Simple Notification</div>
       
       	<c:forEach var="error" items="${errors.errors}">
        	 <div><font color="red"><b><c:out value="${error}" /></b></font></div>
        </c:forEach>
		
		<form name="SendSimpleNotificationMessage" action="SubmitSimpleNotificationMessage.form">
			<input type="hidden" name="originalDateTime"  value="${originalDateTime}"/>
            <table  border="0" cellpadding="0" cellspacing="0"
				class="bord-all" width="60%">
				<tr>
					<td class="thnormal"><strong>Channel:</strong></td>
					<td class="thnormal">
						<%@ include file="util/channel_dropdown_fragment.jsp" %>
						<img src="images/transparent_002.gif" height="1" width="5">
						<i>(choose the channel that this message will be sent on behalf of)</i>
					</td>
				</tr>
				<tr>
					<td class="thnormal"><strong>Priority:</strong></td>
					<td class="thnormal">
						<select name="priorityName">
							<c:forEach var="priority" items="${priorities}">
								<c:choose>
									<c:when test="${priority.name eq priorityName}">
										<option value="${priority.name}" selected="true">${priority.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${priority.name}">${priority.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
						<img src="images/transparent_002.gif" height="1" width="5">
						<i>(select the system defined priority that matches the importance of this message)</i>
					</td>
				</tr>
				<tr>
					<td class="thnormal"><strong>Senders:</strong></td>
					<td class="thnormal">
						<c:choose>
							<c:when test="${! empty senderNames}">
								<input type="text" name="senderNames" value="${senderNames}" />
							</c:when>
							<c:otherwise>
								<input type="text" name="senderNames" value="${defaultSender}" />
							</c:otherwise>
						</c:choose>
						
						<img src="images/transparent_002.gif" height="1" width="5">
						<i>(separate names using a comma - i.e. John Doe, Joe Schmoe, ...)</i>
					</td>
				</tr>
				<tr>
					<td class="thnormal"><strong>Type:</strong></td>
					<td class="thnormal">
						<c:choose>
							<c:when test="${deliveryType eq 'ACK'}">
								<input type="radio" name="deliveryType" value="FYI" />FYI
								<img src="images/transparent_002.gif" height="1" width="4"/>
								<input type="radio" name="deliveryType" value="ACK" checked="true"/>Acknowledge
							</c:when>
							<c:otherwise>
								<input type="radio" name="deliveryType" value="FYI"  checked="true"/>FYI
								<img src="images/transparent_002.gif" height="1" width="4"/>
								<input type="radio" name="deliveryType" value="ACK" />Acknowledge
							</c:otherwise>
						</c:choose>
						<img src="images/transparent_002.gif" height="1" width="5"/>
						<i>(choose whether you want the recipients to have to view the details and acknowledge the message or not)</i>
					</td>
				</tr>
				<tr>
					<td class="thnormal"><strong>Send Date/Time:</strong></td>
					<td class="thnormal">
						<input type="text" name="sendDateTime" id="send_date_time" value="${sendDateTime}"/>
						<img src="images/cal.gif" id="send_date_time_trigger" title="Date/time selector" />
						<script type="text/javascript">
						    Calendar.setup({
						        inputField     :    "send_date_time",     // id of the input field
						        ifFormat       :    "%m/%d/%Y %I:%M %p",     // format of the input field (even if hidden, this format will be honored)
						        button         :    "send_date_time_trigger", // the button or image that triggers this
						        showsTime      :    true,            // will display a time selector
						        daFormat       :    "%A, %B %d, %Y",// format of the displayed date
						        singleClick    :    true,
						        step           :    1
						    });
						</script>
						<img src="images/transparent_002.gif" height="1" width="5">
						<i>(choose whether you want the message to be sent at a given point in time)</i>
					</td>
				</tr>
				<tr>
					<td class="thnormal"><strong>Auto-Remove Date/Time:</strong></td>
					<td class="thnormal">
						<input type="text" name="autoRemoveDateTime" id="auto_remove_date_time" value="${autoRemoveDateTime}"/>
						<img src="images/cal.gif" id="auto_remove_date_time_trigger" title="Date/time selector" />
						<script type="text/javascript">
						    Calendar.setup({
						        inputField     :    "auto_remove_date_time",     // id of the input field
						        ifFormat       :    "%m/%d/%Y %I:%M %p",     // format of the input field (even if hidden, this format will be honored)
						        button         :    "auto_remove_date_time_trigger", // the button or image that triggers this
						        showsTime      :    true,            // will display a time selector
						        daFormat       :    "%A, %B %d, %Y",// format of the displayed date
						        singleClick    :    true,
						        step           :    1
						    });
						</script>
						<img src="images/transparent_002.gif" height="1" width="5">
						<i>(choose whether you want the message to be auto-removed from peoples' notification lists at a given point in time)</i>
					</td>
				</tr>
				<tr>
					<td class="thnormal"><strong>User Recipients:</strong></td>
					<td class="thnormal">
						<input type="text" name="userRecipients" value="${userRecipients}" />
                        <img src="images/transparent_002.gif" height="1" width="5">
                        <i>(separate names using a comma - i.e. John Doe, Joe Schmoe, ...)</i>
					</td>
				</tr>
				<tr>
					<td class="thnormal"><strong>Group Recipients:</strong></td>
					<td class="thnormal">
						<input type="text" name="workgroupRecipients" value="${workgroupRecipients}"/>
                        <img src="images/transparent_002.gif" height="1" width="5">
                        <i>(separate names using a comma - i.e. GroupA, GroupB, ...)</i>
					</td>
				</tr>
				<tr>
                    <td class="thnormal"><strong>Group Namespace Codes:</strong></td>
                    <td class="thnormal">
						<input type="text" name="workgroupNamespaceCodes" value="${workgroupNamespaceCodes}"/>
                        <img src="images/transparent_002.gif" height="1" width="5">
                        <i>(separate namespace codes using a comma, corresponding them with the group names above - i.e. NamespaceA, NamespaceB, ...)</i>
                    </td>
                </tr>
                <tr>
                    <td class="thnormal"><strong>Title:</strong></td>
                    <td class="thnormal">
                        <input type="text" name="title" maxlength="255" size="50" value="${title}"/>
                        <img src="images/transparent_002.gif" height="1" width="5">
                        <i>(required)</i>
                    </td>
                </tr>
				<tr>
					<td class="thnormal"><strong>Message:</strong></td>
					<td class="thnormal">
						<textarea name="message" rows="5" cols="50">${message}</textarea>
						<img src="images/transparent_002.gif" height="1" width="5"> 
						<i>(required)</i>
					</td>
				</tr>
				<tr>
					<td class="thnormal" colspan="2">
						<input type="image" src="images/buttonsmall_submit.gif" value="submit" alt="Submit message." name="submit"/>  
					</td>
				</tr>
			</table>
		</form> 								
		</div>

</body>
</html>
