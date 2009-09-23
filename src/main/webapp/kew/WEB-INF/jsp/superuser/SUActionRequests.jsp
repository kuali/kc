<%--
 Copyright 2008-2009 The Kuali Foundation
 
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
<%@ page import="org.apache.commons.beanutils.BeanUtils" %>
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<script language="javascript" src="scripts/superuser-common.js"></script>

<c:if test="${! empty SuperUserForm.actionRequests}">
<table width="60%" border="0" cellpadding="0" cellspacing="0">
    <html-el:hidden name="SuperUserForm" property="actionTakenActionRequestId" value=""/>
    <html-el:hidden name="SuperUserForm" property="actionTakenRecipientCode" value=""/>
    <html-el:hidden name="SuperUserForm" property="actionTakenNetworkId" value=""/>
    <html-el:hidden name="SuperUserForm" property="actionTakenWorkGroupId" value=""/>
    <html-el:hidden name="SuperUserForm" property="buttonClick" value=""/>
	<tr>
		<td>
            <c:forEach var="actionRequest" items="${SuperUserForm.actionRequests}">
            	<c:if test="${! (actionRequest.principalId == null && actionRequest.groupId == null)}">
					<table border="0" cellpadding="0" cellspacing="0" class="bord-r-t" width="100%">
						<tr>
							<td height="30" colspan="2" class="headercell1" align="center"><b><c:out value="${actionRequest.actionRequestedLabel}" /></b> Requested of
								<c:choose>
									<c:when test="${actionRequest.userRequest}">
										<c:out value="${actionRequest.person.name}" />
									</c:when>
									<c:otherwise>
										<c:out value="${actionRequest.groupName}"/>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td class="thnormal" align="right" width="25%">Request Date</td>
							<td class="datacell" align="left"><fmt:formatDate value="${actionRequest.createDate}" pattern="${Constants.DEFAULT_DATE_FORMAT_PATTERN}"/>&nbsp;</td>
						</tr>
						<tr>
							<td class="thnormal" align="right">Request Status</td>
							<td class="datacell" align="left"><c:out value="${actionRequest.statusLabel}" />&nbsp;</td>
						</tr>
						<tr>
							<td class="thnormal" align="right">Responsibility</td>
							<td class="datacell" align="left"><c:out value="${actionRequest.responsibilityDesc}" />&nbsp;</td>
						</tr>
						<tr>
							<td class="thnormal" align="right">Annotation</td>
							<td class="datacell" align="left"><c:out value="${actionRequest.annotation}" />&nbsp;</td>
						</tr>
						<tr>
							<td class="thnormal" align="right">Route Level</td>
							<td class="datacell" align="left"><c:out value="${actionRequest.routeLevelName}" />&nbsp;</td>
						</tr>
						<tr>
							<td class="thnormal" align="right">Routing Priority</td>
							<td class="datacell" align="left"><c:out value="${actionRequest.priority}" />&nbsp;</td>
						</tr>
						<tr>
							<td class="thnormal" align="right">Responsibility Id</td>
							<td class="datacell" align="left"><c:out value="${actionRequest.responsibilityId}" />&nbsp;</td>
						</tr>
						<tr>
							<td class="thnormal" align="right">Action Request Id</td>
							<td class="datacell" align="left"><c:out value="${actionRequest.actionRequestId}" />&nbsp;</td>
						</tr>
						<tr>
							<td class="thnormal" align="right">Perform Post Processor Logic</td>
							<td class="datacell" align="left">
							  <c:set var="foundMatchingValue" value="false"/>
							  <c:choose>
							    <c:when test="${empty SuperUserForm.actionRequestPostProcessorCheck}">
							      <c:set var="foundMatchingValue" value="true"/>
							    </c:when>
							    <c:otherwise>
							      <c:forEach var="actionRequestId" items="${SuperUserForm.actionRequestPostProcessorCheck}">
							        <c:if test="${actionRequestId eq actionRequest.actionRequestId}">
							          <c:set var="foundMatchingValue" value="true"/>
							        </c:if>
							      </c:forEach>
							    </c:otherwise>
							  </c:choose>
						      <input type="checkbox" name="actionRequestRunPostProcessorCheck" value="${actionRequest.actionRequestId}" <c:if test="${foundMatchingValue}">checked="checked"</c:if> />
							</td>
						</tr>
						<tr>
	                        <td height="30" colspan="2" class="headercell1" align="center">
	                          <c:choose>
	                            <c:when test="${actionRequest.recipientTypeCd == Constants.ACTION_REQUEST_USER_RECIPIENT_CD}" >
	                              <c:set var="username" value="${actionRequest.principal.principalName}" />
	                            </c:when>
	                            <c:otherwise>
	                              <c:set var="username" value="" />
	                            </c:otherwise>
	                          </c:choose>
	                          <c:choose>
	                          	<c:when test="${actionRequest.actionRequested == Constants.ACTION_REQUEST_ACKNOWLEDGE_REQ}">
	                          		<html-el:image src="${resourcePath}images/buttonsmall_acknowledge.gif" styleClass="tinybutton"
	                          			property="methodToCall.actionRequestApprove.(((${actionRequest.recipientTypeCd}))).((#${username}#)).(([${actionRequest.groupId}])).((*${actionRequest.actionRequestId}*)).((%acknowledge%))" />
                                </c:when>
	                          	<c:when test="${actionRequest.actionRequested == Constants.ACTION_REQUEST_FYI_REQ}">
	                          		<html-el:image src="${resourcePath}images/buttonsmall_fyi.gif" styleClass="tinybutton"
	                          			property="methodToCall.actionRequestApprove.(((${actionRequest.recipientTypeCd}))).((#${username}#)).(([${actionRequest.groupId}])).((*${actionRequest.actionRequestId}*)).((%FYI%))" />
	                          	</c:when>
	                          	<c:when test="${actionRequest.actionRequested == Constants.ACTION_REQUEST_COMPLETE_REQ}">
	                          		<html-el:image src="${resourcePath}images/buttonsmall_complete.gif" styleClass="tinybutton"
	                          			property="methodToCall.actionRequestApprove.(((${actionRequest.recipientTypeCd}))).((#${username}#)).(([${actionRequest.groupId}])).((*${actionRequest.actionRequestId}*)).((%complete%))" />
	                          	</c:when>
	                          	<c:when test="${actionRequest.actionRequested == Constants.ACTION_REQUEST_APPROVE_REQ}">
	                          		<html-el:image src="${resourcePath}images/buttonsmall_approve.gif" styleClass="tinybutton"
	                          			property="methodToCall.actionRequestApprove.(((${actionRequest.recipientTypeCd}))).((#${username}#)).(([${actionRequest.groupId}])).((*${actionRequest.actionRequestId}*)).((%approved%))" />
	                          	</c:when>
	                         </c:choose>
	                        </td>
						</tr>
					</table><br>
				</c:if>
			</c:forEach>
		</td>
	</tr>
</table>

</c:if>


