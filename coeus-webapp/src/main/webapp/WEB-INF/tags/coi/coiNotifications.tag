<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
   - 
   - This program is free software: you can redistribute it and/or modify
   - it under the terms of the GNU Affero General Public License as
   - published by the Free Software Foundation, either version 3 of the
   - License, or (at your option) any later version.
   - 
   - This program is distributed in the hope that it will be useful,
   - but WITHOUT ANY WARRANTY; without even the implied warranty of
   - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   - GNU Affero General Public License for more details.
   - 
   - You should have received a copy of the GNU Affero General Public License
   - along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
