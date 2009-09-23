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
<title>Kuali Enterprise Notification - Send ${command.notification.contentType.name} Notification Message</title>
<meta name="Author" content="Aaron Godert (ag266 at cornell dot edu)">
<meta name="Author" content="Aaron Hamid (arh14 at cornell dot edu)">
<link href="css/notification.css" rel="stylesheet" type="text/css" />
</head>
<body>

<div id="pagebody">
        <div class="title">View a(n) ${command.notification.contentType.name} Notification Message (${command.docId})</div>

        <c:if test="${! empty command.message}">
            <div class="note">${command.message}</div>
        </c:if>

		<form name="AdministerNotificationRequest" action="AdministerNotificationRequest.form">
            <input type="hidden" name="docId" value="${command.docId}"/>
            <input type="hidden" name="standaloneWindow" value="${standaloneWindow}" />
			<table  border="0" cellpadding="0" cellspacing="0"
				class="bord-all" width="60%">
				<tr>
					<td class="thnormal"><strong>Sent By:</strong></td>
					<td class="thnormal">
						<i> ${command.document.routeHeader.initiatorPrincipalId} on ${document.dateCreated} </i>
					</td>
				</tr>
				<tr>
					<td class="thnormal"><strong>Channel:</strong></td>
					<td class="thnormal">
						${command.notification.channel.name}
					</td>
				</tr>
				<tr>
					<td class="thnormal"><strong>Priority:</strong></td>
					<td class="thnormal">
						${command.notification.priority.name}
					</td>
				</tr>
				<tr>
					<td class="thnormal"><strong>Senders:</strong></td>
					<td class="thnormal">
						<c:forEach var="sender" items="${command.notification.senders}">
							${sender.senderName} <br/>					
						</c:forEach>
						<img src="images/transparent_002.gif" height="1" width="5">
					</td>
				</tr>
				<tr>
					<td class="thnormal"><strong>Type:</strong></td>
					<td class="thnormal">
						${command.notification.deliveryType}
					</td>
				</tr>
				<tr>
					<td class="thnormal"><strong>Send Date/Time:</strong></td>
					<td class="thnormal">
						${command.notification.sendDateTime}
					</td>
				</tr>
				<tr>
					<td class="thnormal"><strong>Auto-Remove Date/Time:</strong></td>
					<td class="thnormal">
						${command.notification.autoRemoveDateTime}
					</td>
				</tr>
				<tr>
					<td class="thnormal"><strong>User Recipients:</strong></td>
					<td class="thnormal">
						<c:forEach var="recipient" items="${command.notification.recipients}">
							<c:if test="${recipient.recipientType eq 'USER'}">
								${recipient.recipientId} <br/>
							</c:if>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td class="thnormal"><strong>Workgroup Recipients:</strong></td>
					<td class="thnormal">
						<c:forEach var="recipient" items="${command.notification.recipients}">
							<c:if test="${recipient.recipientType eq 'GROUP'}">
								${recipient.recipientId} <br/>
							</c:if>
						</c:forEach>
					</td>
				</tr>
                <tr>
                    <td class="thnormal"><strong>Title:</strong></td>
                    <td class="thnormal">
                        ${command.notification.title}
                    </td>
                </tr>
                <tr>
                    <td class="thnormal"><strong>${command.notification.contentType.name} content:</strong></td>
                    <td class="thnormal">
                        ${command.renderedContent}
                    </td>
                </tr>
                <tr>
                    <td class="thnormal" colspan="2" align="center">
                        <c:if test="${command.valid}">
                            <c:choose>
                                <c:when test="${command.document.approvalRequested}">
                                    <input type="image" src="images/buttonsmall_approve.gif" name="approve" value="Approve Notification" alt="Approve Notification"/>
                                    <input type="image" src="images/buttonsmall_disapprove.gif" name="disapprove" value="Disapprove Notification" alt="Disapprove Notification"/>
                                </c:when>
                                <c:when test="${command.document.acknowledgeRequested}">
                                    <input type="image" src="images/buttonsmall_acknowledge.gif" name="acknowledge" value="Acknowledge Notification" alt="Acknowledge Notification"/>
                                </c:when>
                            </c:choose>
                        </c:if>
                        <c:if test="${standaloneWindow eq 'true'}">
                    		<a href="javascript:self.close()">
          						<img src="images/buttonsmall_close.gif" border="0" alt="close" />
        					</a>
                    	</c:if>
                    </td>
                </tr>
			</table>
		</form> 								
</div>
</body>
</html>
