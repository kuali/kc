<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

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
            	<c:if test="${! (actionRequest.workflowId == null && actionRequest.workgroupId == null)}">
					<table border="0" cellpadding="0" cellspacing="0" class="bord-r-t" width="100%">
						<tr>
							<td height="30" colspan="2" class="headercell1" align="center"><b><c:out value="${actionRequest.actionRequestedLabel}" /></b> Requested of
								<c:choose>
									<c:when test="${actionRequest.userRequest}">
										<c:out value="${actionRequest.workflowUser.displayName}" />
									</c:when>
									<c:otherwise>
										<c:out value="${actionRequest.workgroup.workgroupName}"/>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td class="thnormal" align="right">Request Date</td>
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
	                        <td height="30" colspan="2" class="headercell1" align="center">
	                          <c:choose>
	                            <c:when test="${actionRequest.recipientTypeCd == Constants.ACTION_REQUEST_USER_RECIPIENT_CD}" >
	                              <c:set var="username" value="${actionRequest.workflowUser.authenticationUserId.authenticationId}" />
	                            </c:when>
	                            <c:otherwise>
	                              <c:set var="username" value="" />
	                            </c:otherwise>
	                          </c:choose>
	                          <c:choose>
	                          	<c:when test="${actionRequest.actionRequested == Constants.ACTION_REQUEST_ACKNOWLEDGE_REQ}">
	                          	  <img src="images/buttonsmall_acknowledge.gif" onclick="processActionRequest('SuperUserForm', '<c:out value="${actionRequest.recipientTypeCd}"/>',
	                               '<c:out value="${username}"/>', '<c:out value="${actionRequest.workgroupId}"/>', '<c:out value="${actionRequest.actionRequestId}"/>','acknowlege');" />
	                          	</c:when>
	                          	<c:when test="${actionRequest.actionRequested == Constants.ACTION_REQUEST_FYI_REQ}">
	                          	  <img src="images/buttonsmall_fyi.gif" onclick="processActionRequest('SuperUserForm', '<c:out value="${actionRequest.recipientTypeCd}"/>',
	                               '<c:out value="${username}"/>', '<c:out value="${actionRequest.workgroupId}"/>', '<c:out value="${actionRequest.actionRequestId}"/>','FYI');" />
	                          		 
	                          	</c:when>
	                          	<c:when test="${actionRequest.actionRequested == Constants.ACTION_REQUEST_COMPLETE_REQ}">
	                          	  <img src="images/buttonsmall_complete.gif" onclick="processActionRequest('SuperUserForm', '<c:out value="${actionRequest.recipientTypeCd}"/>',
	                               '<c:out value="${username}"/>', '<c:out value="${actionRequest.workgroupId}"/>', '<c:out value="${actionRequest.actionRequestId}"/>','complete');" />
	                          		 
	                          	</c:when>
	                          	<c:when test="${actionRequest.actionRequested == Constants.ACTION_REQUEST_APPROVE_REQ}">
	                          	  <img src="images/buttonsmall_approve.gif" onclick="processActionRequest('SuperUserForm', '<c:out value="${actionRequest.recipientTypeCd}"/>',
	                               '<c:out value="${username}"/>', '<c:out value="${actionRequest.workgroupId}"/>', '<c:out value="${actionRequest.actionRequestId}"/>','approved');" />
	                          		
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


