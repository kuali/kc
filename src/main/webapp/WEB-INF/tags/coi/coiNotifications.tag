<%--
 Copyright 2005-2014 The Kuali Foundation

 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.osedu.org/licenses/ECL-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<kul:tab tabTitle="Notifications" tabItemCount="" defaultOpen="false" tabErrorKey="">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left"> Disclosure Notifications </span>
        </h3>
        <table id="coiDisclosure-notifications-table" cellpadding="0" cellspacing="0" summary="Coi Disclosure Notifications">
			<tr>
				<th scope="row">Type</th>
				<th align="left">Recipient(s)</th>
				<th align="left">Subject</th>
				<th align="left">Date Sent</th>
			</tr>
			<c:forEach var="disclosureNotification" items="${KualiForm.document.coiDisclosure.filteredNotificationsByDocId}" varStatus="status">
    			<tr>
    				<td>
	                    <a class="viewNotification" id="viewNotification${status.index}" title="${disclosureNotification.notificationType.description}" href="${pageContext.request.contextPath}/coiDisclosure.do?methodToCall=viewDisclosureNotification&notificationId=${disclosureNotification.notificationId}" scrolling="no" noresize>
    				        <c:out value="${disclosureNotification.notificationType.description}" />
						</a>
    				</td>
    				<td>
    				    <c:out value="${disclosureNotification.recipients}" />
    				</td>
    				<td>
    				    <c:out value="${disclosureNotification.subject}" />
    				</td>
    				<td>
    				    <c:out value="${disclosureNotification.updateTimestampString}" />
    				</td>
				</tr>
			</c:forEach>
        </table>
	</div>
</kul:tab>
