<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<html>
<head>
<title>Route Log</title>
<link href="css/screen.css" rel="stylesheet" type="text/css">
<script language=javascript1.2 src="scripts/en-common.js"></script>
</head>
<body>

<c:if test="${!RouteLogForm.removeHeader}">
	<table width="100%" border=0 cellpadding=0 cellspacing=0 class="headercell1">
	  <tr>
	    <td width="10%"><img src="images/wf-logo.gif" alt="OneStart Workflow" width=150 height=21 hspace=5 vspace=5></td>
		<td>
		  <a href="<c:url value="RouteLog.do">
					<c:param name="showFuture" value="${RouteLogForm.showFuture}" />
					<c:param name="showNotes" value="${RouteLogForm.showNotes}" />
					<c:param name="routeHeaderId" value="${RouteLogForm.routeHeaderId}" />
					<c:param name="docId" value="${RouteLogForm.docId}" />
					<c:param name="backUrl" value="${RouteLogForm.returnUrlLocation}" />
				  </c:url>">Refresh</a>
		  <c:if test="${not empty RouteLogForm.returnUrlLocation}">
		    &nbsp;&nbsp;&nbsp;<a href="<c:out value="${RouteLogForm.returnUrlLocation}"/>">Back to Previous Page</a>
		  </c:if>
		</td>
	  </tr>
	</table>
</c:if>
<br>
<jsp:include page="../WorkflowMessages.jsp" flush="true" />
<br>

<table border=0 cellpadding=0 cellspacing=0 width="100%">
    <tr>
      <td>

   <%-- Document Routing Information --%>
        <table border=0 cellpadding=0 cellspacing=0 width="100%">
            <tr>
              <td width=20><img src="images/pixel_clear.gif" alt="" height=20 width=20></td>
              <td>
                <table width="100%" border=0 cellspacing=0 cellpadding=0>
                  <tr>
                    <td width=12><img src="images/tab-topleft.gif" alt="" width=12 height=29></td>
                    <td width=200 nowrap background="images/tab-back.gif">
                      <table width="100%" border=0 cellspacing=0 cellpadding=0>
                        <tr>
                          <td nowrap class="tabtitle"><b><bean-el:message key="routeLog.RouteLog.header.label.documentId"/>: <c:out value="${routeHeader.routeHeaderId}" /></b></td>
                          <td width=100 align=right nowrap>&nbsp;</td>
                        </tr>
                      </table>
                    </td>
                    <td width=15><img src="images/tab-bevel.gif" alt="" width=15 height=29></td>
                    <td width="95%" align=right valign=top background="images/tab-rightback.gif"><img src="images/tab-topright.gif" alt="" width=12 height=29 align=top></td>
                  </tr>
                </table>
                <table width="100%" border=0 cellspacing=0 cellpadding=0>
                  <tr>
                    <td width=8 class="bordercell-left"><img src="images/pixel_clear.gif" alt="" width=8 height=8></td>
                    <td>
                      <table width="100%" border=0 cellspacing=0 cellpadding=0>
                        <tr>
                          <td align=right class="spacercell">&nbsp;</td>
                        </tr>
                      </table>
                      <table width="100%" border=0 cellpadding=0 cellspacing=0 class="bord-r-t">
                         <tr>
		                    <td width="20%" align=right class="thnormal"><bean-el:message key="routeLog.RouteLog.header.label.documentTitle"/></td>
		                    <td class="datacell" colspan="3" ><c:out value="${routeHeader.docTitle}" />&nbsp;</td>

	                     </tr>
	                     <tr>
		                   <td width="20%" align=right class="thnormal"><bean-el:message key="routeLog.RouteLog.header.label.documentType"/></td>
		                   <td class="datacell" width="25%">
			                   <a href="
               						<c:url value="DocumentType.do">
										<c:param name="docTypeId" value="${routeHeader.documentTypeId}" />
										<c:param name="methodToCall" value="report"/>
									</c:url>"><c:out value="${routeHeader.documentType.label}" />
								</a>&nbsp;
		                   &nbsp;</td>
		                   <td width="20%" align=right class="thnormal"><bean-el:message key="routeLog.RouteLog.header.label.created"/></td>
		                    <td class="datacell" width="25%"><fmt:formatDate value="${routeHeader.createDate}" pattern="${Constants.DEFAULT_DATE_FORMAT_PATTERN}" />&nbsp;</td>
		                 </tr>
	                     <tr>
		                   <td width="20%" align=right class="thnormal"><bean-el:message key="routeLog.RouteLog.header.label.initiator"/></td>
		                   <td class="datacell" width="25%">
		                   <c:set var="initiatorDisplayName" value="${routeHeader.initiatorUser.displayName}"/>
							  <c:if test="${kewUserSession.workflowUser.workflowId != routeHeader.initiatorUser.workflowId}">
  							    <c:set var="initiatorDisplayName" value="${routeHeader.initiatorUser.displayNameSafe}"/>
							  </c:if>
			                   <a href="
               						<c:url value="${UrlResolver.userReportUrl}">
										<c:param name="workflowId" value="${routeHeader.initiatorWorkflowId}" />
										<c:param name="methodToCall" value="report" />
										<c:param name="showEdit" value="no" />
									</c:url>"><c:out value="${initiatorDisplayName}" />
								</a>&nbsp;
		                   	</td>
		                   	<td width="20%" align=right class="thnormal"><bean-el:message key="routeLog.RouteLog.header.label.lastModified"/></td>
		                   <td class="datacell" width="25%"><fmt:formatDate value="${routeHeader.statusModDate}" pattern="${Constants.DEFAULT_DATE_FORMAT_PATTERN}" />&nbsp;</td>

		                  </tr>
	                     <tr>
		                   <td width="20%" align=right class="thnormal"><bean-el:message key="routeLog.RouteLog.header.label.routeStatus"/></td>
		                   <td class="datacell" width="25%"><b><c:out value="${routeHeader.routeStatusLabel}" /></b>&nbsp;</td>
		                    <td width="20%" align=right class="thnormal"><bean-el:message key="routeLog.RouteLog.header.label.lastApproved"/></td>
		                   <td class="datacell" width="25%"><fmt:formatDate value="${routeHeader.approvedDate}" pattern="${Constants.DEFAULT_DATE_FORMAT_PATTERN}" />&nbsp;</td>

		                   </tr>
	                     <tr>
		                   <td width="20%" align=right class="thnormal"><bean-el:message key="routeLog.RouteLog.header.label.routeNodes"/></td>
		                   <td class="datacell"><c:out value="${routeHeader.currentRouteLevelName}" />&nbsp;</td>
		                   <td width="20%" align=right class="thnormal"><bean-el:message key="routeLog.RouteLog.header.label.finalized"/></td>
		                   <td class="datacell" width="25%"><fmt:formatDate value="${routeHeader.finalizedDate}" pattern="${Constants.DEFAULT_DATE_FORMAT_PATTERN}" />&nbsp;</td>

	                     </tr>
                      </table>
                      <table width="100%" border=0 cellspacing=0 cellpadding=0>
                        <tr>
                          <td class="spacercell">&nbsp;</td>
                        </tr>
                      </table>
                    </td>
                    <td width=8 class="bordercell-right"><img src="images/pixel_clear.gif" alt="" width=8 height=8></td>
                  </tr>
                </table>

   <%-- Actions Taken --%>
              <c:if test="${! empty routeHeader.actionsTaken}">
                <table width="100%" border=0 cellspacing=0 cellpadding=0>
                  <tr>
                    <td width=12><img src="images/tab-topleft1.gif" alt="" width=12 height=27></td>
                    <td width=200 nowrap background="images/tab-back.gif">
                      <table width="100%" border=0 cellspacing=0 cellpadding=0>
                        <tr>
                          <td nowrap class="tabtitle"><b><bean-el:message key="routeLog.RouteLog.actionsTaken.label.actionsTaken"/></b></td>
                        </tr>
                      </table>
                    </td>
                    <td width=15><img src="images/tab-bevel1.gif" alt="" width=15 height=27></td>
                    <td width="95%" align=right background="images/tab-rightback1.gif"><img src="images/tab-topright1.gif" alt="" width=12 height=27></td>
                  </tr>
                </table>

                <table width="100%" border=0 cellspacing=0 cellpadding=0>
                  <tr>
                    <td width=8 class="bordercell-left"><img src="images/pixel_clear.gif" alt="" width=8 height=8></td>
                    <td class="headercell4" width="5%">&nbsp;</td>
                    <td align="center" class="headercell4"><bean-el:message key="routeLog.RouteLog.actionsTaken.label.action"/>
                    </td>
                    <td align="center" class="headercell4"><bean-el:message key="routeLog.RouteLog.actionsTaken.label.takenBy"/>
                    </td>
                    <td align="center" class="headercell4"><bean-el:message key="routeLog.RouteLog.actionsTaken.label.forDelegator"/>
                    </td>
                    <td align="center" class="headercell4"><bean-el:message key="routeLog.RouteLog.actionsTaken.label.timeDate"/>
                    </td>
                    <td align="center" class="headercell4" width="40%"><bean-el:message key="routeLog.RouteLog.actionsTaken.label.annotation"/>
                    </td>
                    <td width=8 class="bordercell-right"><img src="images/pixel_clear.gif" alt="" width=8 height=8></td>
                  </tr>

					<c:forEach var="actionTaken" items="${routeHeader.actionsTaken}" varStatus="atStatus">
                            <tr class="bord-r-t">
	                            <td width=8 class="bordercell-left"><img src="images/pixel_clear.gif" alt="" width=8 height=8></td>
	                          	<td class="headercell4">
	                          		<c:if test="${! empty actionTaken.actionRequests}">
	                            	<a id="A<c:out value="${atStatus.count}" />" onclick="rend(this, false)">
		                              <img src="images/tinybutton-show.gif" alt="show" width=45 height=15 border=0
		                              align=absmiddle id="F<c:out value="${atStatus.count}" />"></a>
		                            </c:if>&nbsp;
		                        </td>

		                        <td align="center" class="headercell4">
	                               <b><c:out value="${actionTaken.actionTakenLabel}" /></b>
	                            </td>
	                            <td align="left" class="headercell4">
	                               <a style="color:white" href="
										<c:url value="${UrlResolver.userReportUrl}">
											<c:param name="workflowId" value="${actionTaken.workflowId}" />
											<c:param name="methodToCall" value="report" />
											<c:param name="showEdit" value="no" />
										</c:url>"><c:out value="${actionTaken.workflowUser.displayName}" />
									</a>
	                       		</td>
	                       		<td align="left" class="headercell4">
							        <c:if test="${actionTaken.forDelegator}">
							        <c:set var="actionDisplayName" value="${actionTaken.workflowUser.displayName}"/>
							      <c:if test="${kewUserSession.workflowUser.workflowId != actionTaken.workflowUser.workflowId}">
  							        <c:set var="actionDisplayName" value="${actionTaken.workflowUser.displayNameSafe}"/>
							      </c:if>
											<a style="color:white" href="
									  			<c:url value="${UrlResolver.userReportUrl}">
													<c:param name="workflowId" value="${actionTaken.delegatorWorkflowId}" />
													<c:param name="methodToCall" value="report" />
													<c:param name="showEdit" value="no" />
												</c:url>"><c:out value="${actionDisplayName}" />
											</a>
							         </c:if>&nbsp;
						         </td>
						         <td align="center" class="headercell4">
						             <b><fmt:formatDate value="${actionTaken.actionDate}" pattern="${Constants.DEFAULT_DATE_FORMAT_PATTERN}" /></b>
						         </td>
						         <td align="left" class="headercell4">
						             <c:out value="${actionTaken.annotation}" />&nbsp;
						             <!--ActionTakenId:<c:out value="${actionTaken.actionTakenId}" />-->
						         </td>
						         <td width=8 class="bordercell-right"><img src="images/pixel_clear.gif" alt="" width=8 height=8></td>
                            </tr>

                            <tr id="G<c:out value="${atStatus.count}" />" style="display: none;" >
                            	<td width=8 class="bordercell-left"><img src="images/pixel_clear.gif" alt="" width=8 height=8></td>
                            	<td>&nbsp;</td>
								<td colspan="5">
									<table width="100%" border=0 cellspacing=0 cellpadding=0>
					                  <tr>
					                    <td align="center" class="headercell3-b-l" width="5%">&nbsp;</td>
					  		            <td align="center" width="15%" class="headercell3-b-l"><bean-el:message key="routeLog.ActionRequests.actionRequests.label.action"/></td>
					  		            <td align="center" width="15%" class="headercell3-b-l"><bean-el:message key="routeLog.ActionRequests.actionRequests.label.requestedOf"/></td>
					  		            <td align="center" width="22%" class="headercell3-b-l"><bean-el:message key="routeLog.ActionRequests.actionRequests.label.timeDate"/></td>
					  		            <td align="center" width="40%" class="headercell3-b-l"><bean-el:message key="routeLog.ActionRequests.actionRequests.label.annotation"/></td>
					                  </tr>
	                              	<c:forEach var="actionRequest" items="${actionTaken.actionRequests}" varStatus="arStatus">
	                              		<c:if test="${actionRequest.parentActionRequest == null}">
											<c:set var="level" value="1" scope="request"/>
											<c:set var="index" value="${atStatus.count}z${arStatus.index}" scope="request" />
											<c:set var="actionRequest" value="${actionRequest}" scope="request"/>
											<c:set var="RouteLogForm" value="${RouteLogForm}"/>
											<c:set var="hasChildren" value="${! empty actionRequest.childrenRequests}" scope="request"/>
											<jsp:include page="ActionRequest.jsp" flush="true" />
										</c:if>
									</c:forEach>
									</table>
                              	</td>
                              	<td width=8 class="bordercell-right"><img src="images/pixel_clear.gif" alt="" width=8 height=8></td>
                            </tr>
	                </c:forEach>
                </table>
              </c:if>

              <table width="100%" border=0 cellspacing=0 cellpadding=0>
                  <tr>
                    <td width=8 class="bordercell-left"><img src="images/pixel_clear.gif" alt="" width=8 height=8></td>
                    <td class="spacercell">
                      <div align=center>&nbsp;</div>
                    </td>
                    <td width=8 class="bordercell-right"><img src="images/pixel_clear.gif" alt="" width=8 height=8></td>
                  </tr>
                </table>
   <%-- Pending Action Requests --%>

              <c:if test="${RouteLogForm.pendingActionRequestCount > 0}">
                <table width="100%" border=0 cellspacing=0 cellpadding=0>
                  <tr>
                    <td width=12><img src="images/tab-topleft1.gif" alt="" width=12 height=27></td>
                    <td width=200 nowrap background="images/tab-back.gif">
                      <table width="100%" border=0 cellspacing=0 cellpadding=0>
                        <tr>
                          <td nowrap class="tabtitle"><b>Pending Action Requests<b></td>
                        </tr>
                      </table>
                    </td>
                    <td width=15><img src="images/tab-bevel1.gif" alt="" width=15 height=27></td>
                    <td width="95%" align=right background="images/tab-rightback1.gif"><img src="images/tab-topright1.gif" alt="" width=12 height=27></td>
                  </tr>
                </table>
                <table width="100%" border=0 cellspacing=0 cellpadding=0>
                	<tr>
                		<td width=8 class="bordercell-left"><img src="images/pixel_clear.gif" alt="" width=8 height=8></td>
                		<td>
			                <table width="100%" border=0 cellspacing=0 cellpadding=0>
			                  <tr>
			                    <td align="center" class="headercell4" width="5%">&nbsp;</td>
			  		            <td align="center" width="15%" class="headercell4"><bean-el:message key="routeLog.ActionRequests.actionRequests.label.action"/></td>
			  		            <td align="center" width="15%" class="headercell4"><bean-el:message key="routeLog.ActionRequests.actionRequests.label.requestedOf"/></td>
			  		            <td align="center" width="22%" class="headercell4"><bean-el:message key="routeLog.ActionRequests.actionRequests.label.timeDate"/></td>
			  		            <td align="center" width="40%" class="headercell4"><bean-el:message key="routeLog.ActionRequests.actionRequests.label.annotation"/></td>
			                  </tr>
			                  <tr>
			                  	<td>
			                        <c:set var="shiftIndex" value="5000" scope="request"/>
			                        <c:forEach var="actionRequest" items="${RouteLogForm.rootRequests}" varStatus="arStatus">
			                        	<c:if test="${actionRequest.pending}">
			                        	     <c:set var="level" value="0" scope="request"/>
					                         <c:set var="index" value="${arStatus.index + shiftIndex}" scope="request" />
					                         <c:set var="actionRequest" value="${actionRequest}" scope="request"/>
					                         <jsp:include page="ActionRequest.jsp" flush="true" />
					                      </c:if>
			                        </c:forEach>
			                    </td>
			                  </tr>
			                </table>
                		</td>
						<td width=8 class="bordercell-right"><img src="images/pixel_clear.gif" alt="" width=8 height=8></td>
                	</tr>
                </table>
              </c:if>

              <table width="100%" border=0 cellspacing=0 cellpadding=0>
                  <tr>
                    <td width=8 class="bordercell-left"><img src="images/pixel_clear.gif" alt="" width=8 height=8></td>
                    <td class="spacercell">
                      <div align=center>&nbsp;</div>
                    </td>
                    <td width=8 class="bordercell-right"><img src="images/pixel_clear.gif" alt="" width=8 height=8></td>
                  </tr>
                </table>
<%-- Future Action Requests --%>

              <c:if test="${RouteLogForm.lookFuture}">
                <table width="100%" border=0 cellspacing=0 cellpadding=0>
                  <tr>
                    <td width=12><img src="images/tab-topleft1.gif" alt="" width=12 height=27></td>
                    <td width=200 nowrap background="images/tab-back.gif">
                      <table width="100%" border=0 cellspacing=0 cellpadding=0>
                        <tr>
                          <td nowrap class="tabtitle"><b>Future Action Requests<b>&nbsp;
							<a href="
								<c:url value="RouteLog.do">
									<c:param name="showFuture" value="${!RouteLogForm.showFuture}" />
									<c:param name="showNotes" value="${RouteLogForm.showNotes}" />
									<c:param name="routeHeaderId" value="${RouteLogForm.routeHeaderId}" />
								</c:url>">
									<c:if test="${RouteLogForm.showFuture}">
										<img src="images/tinybutton-hide1.gif">
									</c:if>
									<c:if test="${!RouteLogForm.showFuture}">
										<img src="images/tinybutton-show.gif">
									</c:if>
								</a>
                          </td>
                        </tr>
                      </table>
                    </td>
                    <td width=15><img src="images/tab-bevel1.gif" alt="" width=15 height=27></td>
                    <td width="95%" align=right background="images/tab-rightback1.gif"><img src="images/tab-topright1.gif" alt="" width=12 height=27></td>
                  </tr>
                </table>
                <table width="100%" border=0 cellspacing=0 cellpadding=0>

                	<tr>
                		<td width=8 class="bordercell-left"><img src="images/pixel_clear.gif" alt="" width=8 height=8></td>
                		<c:choose>
	                		<c:when test="${RouteLogForm.showFuture}">
		                		<td>
					                <table width="100%" border=0 cellspacing=0 cellpadding=0>
					                  <tr>
					                    <td align="center" class="headercell4" width="5%">&nbsp;</td>
					  		            <td align="center" width="15%" class="headercell4"><bean-el:message key="routeLog.ActionRequests.actionRequests.label.action"/></td>
					  		            <td align="center" width="15%" class="headercell4"><bean-el:message key="routeLog.ActionRequests.actionRequests.label.requestedOf"/></td>
					  		            <td align="center" width="22%" class="headercell4"><bean-el:message key="routeLog.ActionRequests.actionRequests.label.timeDate"/></td>
					  		            <td align="center" width="40%" class="headercell4"><bean-el:message key="routeLog.ActionRequests.actionRequests.label.annotation"/></td>
					                  </tr>
					                  <tr>
					                  	<td>
					                        <c:set var="shiftIndex" value="6000" scope="request"/>
					                        <c:forEach var="actionRequest" items="${RouteLogForm.futureRootRequests}" varStatus="arStatus">
					                        	<c:if test="${actionRequest.pending}">
					                        	     <c:set var="level" value="0" scope="request"/>
							                         <c:set var="index" value="${arStatus.index + shiftIndex}" scope="request" />
							                         <c:set var="actionRequest" value="${actionRequest}" scope="request"/>
							                         <jsp:include page="ActionRequest.jsp" flush="true" />
							                      </c:if>
					                        </c:forEach>
					                    </td>
					                  </tr>
					                </table>
		                		</td>
		                	</c:when>
		                	<c:otherwise>
		                		<td class="spacercell"><img src="images/pixel_clear.gif" alt="" width=8 height=8></td>
		                	</c:otherwise>
		                </c:choose>
						<td width=8 class="bordercell-right"><img src="images/pixel_clear.gif" alt="" width=8 height=8></td>
                	</tr>

                </table>
              </c:if>

              <table width="100%" border=0 cellspacing=0 cellpadding=0>
                  <tr>
                    <td width=8 class="bordercell-left"><img src="images/pixel_clear.gif" alt="" width=8 height=8></td>
                    <td class="spacercell">
                      <div align=center>&nbsp;</div>
                    </td>
                    <td width=8 class="bordercell-right"><img src="images/pixel_clear.gif" alt="" width=8 height=8></td>
                  </tr>
                </table>

			   <%-- Page Footer --%>
                <table width="100%" border=0 cellpadding=0 cellspacing=0 background="images/tabfoot-back.gif">
                  <tr>
                    <td><img src="images/tabfoot-left.gif" alt="" width=12 height=14></td>
                    <td>&nbsp;</td>
                    <td align=right><img src="images/tabfoot-right.gif" alt="" width=12 height=14></td>
                  </tr>
                </table>
              </td>
              <td width=20><img src="images/pixel_clear.gif" alt="" height=20 width=20></td>
            </tr>
        </table>
      </td>
    </tr>
    <c:if test="${RouteLogForm.showCloseButton}">
      <tr>
        <td align="center">
          <br><br><a href="#" onclick="javascript:window.close();"><img src="images/buttonsmall_close.gif" alt="Close This Window" /></a>
        </td>
      </tr>
    </c:if>
</table>

</body>
</html>