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
<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<c:set var="methodCall" value="${MessageQueueForm.messageQueueFromDatabase.methodCall}" />

<html>
  <head>
    <title>Message</title>
    <link href="css/screen.css" rel="stylesheet" type="text/css">
    <script language="JavaScript" src="scripts/en-common.js"></script>
    <script language="JavaScript" src="scripts/messagequeue-common.js"></script>
  </head>

  <body>

    <table width="100%" border=0 cellpadding=0 cellspacing=0 class="headercell1">
      <tr>
        <td><img src="images/wf-logo.gif" alt="Workflow" width=150 height=21 hspace=5 vspace=5></td>
        <td width="90%">
          <a href="MessageQueue.do?methodToCall=start">Message Queue</a>
        </td>
      </tr>
    </table>

    <table width="100%" border=0 cellpadding=0 cellspacing=0>
      <tr>
        <td>
		  <table width="100%" border=0 cellpadding=0 cellspacing=0 bgcolor="#FFFFFF">
            <tr>
              <td width="20"><img src="images-channelglobal/pixel_clear.gif" alt="" width="1" height="1"><img src="images/pixel_clear.gif" alt="" width=20 height=20></td>
	          <td>
	            <table width="100%" border=0 cellspacing=0 cellpadding=0>
	              <tr>
	                <td height=30><strong>Message</strong></td>
	              </tr>
	            </table>
	          </td>
	          <td width="20"><img src="images/pixel_clear.gif" alt="" width=20 height=20></td>
	        </tr>
	      </table>
	    </td>
	  </tr>

	  <tr>
	    <td>
		  <jsp:include page="../Messages.jsp" flush="true" />
	    </td>
	  </tr>

	  <tr>
	    <td>
          <table width="100%" border=0 cellspacing=0 cellpadding=0>
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td width=20><img src="images/pixel_clear.gif" alt="" width=20 height=20></td>
              <td>
		            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t">
		            		  <tr>
		            		  	<th colspan="2"><b>Message</b></th>
		            		  </tr>
					  		  <tr>
										<td class="thnormal" align="right" width="20%">Message Queue Id:</td>
						  		  <td class="datacell"><c:out value="${MessageQueueForm.messageQueueFromForm.routeQueueId}" />&nbsp;</td>
					  		  </tr>
					  		  <tr>
								<td class="thnormal" align="right" width="20%">Status:</td>
						  		  <td class="datacell"><c:out value="${MessageQueueForm.messageQueueFromForm.queueStatus}" />&nbsp;</td>
					  		  </tr>
					  		  <tr>
								<td class="thnormal" align="right" width="20%">Date:</td>
						  		  <td class="datacell"><fmt:formatDate value="${MessageQueueForm.messageQueueFromForm.queueDate}" pattern="${rice_constant.DEFAULT_DATE_FORMAT_PATTERN}" />&nbsp;</td>
					  		  </tr>
					  		  <tr>
								<td class="thnormal" align="right" width="20%">Expiration Date:</td>
						  		  <td class="datacell"><fmt:formatDate value="${MessageQueueForm.messageQueueFromForm.expirationDate}" pattern="${rice_constant.DEFAULT_DATE_FORMAT_PATTERN}" />&nbsp;</td>
					  		  </tr>
					  		  <tr>
								<td class="thnormal" align="right" width="20%">Priority:</td>
						  		  <td class="datacell"><c:out value="${MessageQueueForm.messageQueueFromForm.queuePriority}" />&nbsp;</td>
					  		  </tr>
					  		  <tr>
								<td class="thnormal" align="right" width="20%">Retry Count:</td>
						  		  <td class="datacell"><c:out value="${MessageQueueForm.messageQueueFromForm.retryCount}" />&nbsp;</td>
					  		  </tr>
					  		  <tr>
								<td class="thnormal" align="right" width="20%">IP Number:</td>
						  		  <td class="datacell"><c:out value="${MessageQueueForm.messageQueueFromForm.ipNumber}" />&nbsp;</td>
					  		  </tr>
					  		  <tr>
								<td class="thnormal" align="right" width="20%">Service Name:</td>
						  		  <td class="datacell"><c:out value="${MessageQueueForm.messageQueueFromForm.serviceName}" />&nbsp;</td>
					  		  </tr>
					  		  <tr>
								<td class="thnormal" align="right" width="20%">Service Namespace:</td>
						  		  <td class="datacell"><c:out value="${MessageQueueForm.messageQueueFromForm.serviceNamespace}" />&nbsp;</td>
					  		  </tr>
					  		  <tr>
								<td class="thnormal" align="right" width="20%">Method Name:</td>
						  		  <td class="datacell"><c:out value="${MessageQueueForm.messageQueueFromForm.methodName}" />&nbsp;</td>
					  		  </tr>
					  		  <tr>
								<td class="thnormal" align="right" width="20%">App Specific Value 1:</td>
						  		  <td class="datacell"><c:out value="${MessageQueueForm.messageQueueFromForm.value1}" />&nbsp;</td>
					  		  </tr>
					  		  <tr>
								<td class="thnormal" align="right" width="20%">App Specific Value 2:</td>
						  		  <td class="datacell"><c:out value="${MessageQueueForm.messageQueueFromForm.value2}" />&nbsp;</td>
					  		  </tr>

		            		  <tr>
		            		  	<th colspan="2"><b>Payload</b></td>
		            		  </tr>

							    <tr>
									  <td class="thnormal" align="right" width="20%">Payload Class:</td>
					  		    <td class="datacell"><c:out value="${methodCall.class}" /></td>
				  		    </tr>
					  		  <tr>
										<td class="thnormal" align="right" width="20%">Method Name</td>
						  		  <td class="datacell"><c:out value="${methodCall.methodName}" />&nbsp;</td>
					  		  </tr>
					  		  <tr>
										<td class="thnormal" align="right" width="20%">ignoreStoreAndForward</td>
						  		  <td class="datacell"><c:out value="${methodCall.ignoreStoreAndForward}" />&nbsp;</td>
					  		  </tr>
					  		  <tr>
										<td class="thnormal" align="right" width="20%">ServiceInfo.messageEntryId:</td>
										<c:choose>
											<c:when test="${methodCall.serviceInfo == null}">
								  		  <td class="datacell">ServiceInfo is null&nbsp;</td>
											</c:when>
											<c:otherwise>
								  		  <td class="datacell"><c:out value="${methodCall.serviceInfo.messageEntryId}" />&nbsp;</td>
											</c:otherwise>
										</c:choose>
					  		  </tr>
					  		  <tr>
										<td class="thnormal" align="right" width="20%">ServiceInfo.serviceNamespace:</td>
										<c:choose>
											<c:when test="${methodCall.serviceInfo == null}">
								  		  <td class="datacell">ServiceInfo is null&nbsp;</td>
											</c:when>
											<c:otherwise>
								  		  <td class="datacell"><c:out value="${methodCall.serviceInfo.serviceNamespace}" />&nbsp;</td>
											</c:otherwise>
										</c:choose>
					  		  </tr>
					  		  <tr>
										<td class="thnormal" align="right" width="20%">ServiceInfo.serverIp:</td>
										<c:choose>
											<c:when test="${methodCall.serviceInfo == null}">
								  		  <td class="datacell">ServiceInfo is null&nbsp;</td>
											</c:when>
											<c:otherwise>
								  		  <td class="datacell"><c:out value="${methodCall.serviceInfo.serverIp}" />&nbsp;</td>
											</c:otherwise>
										</c:choose>
					  		  </tr>
					  		  <tr>
										<td class="thnormal" align="right" width="20%">ServiceInfo.ServiceName:</td>
										<c:choose>
											<c:when test="${methodCall.serviceInfo == null}">
								  		  <td class="datacell">ServiceInfo is null&nbsp;</td>
											</c:when>
											<c:otherwise>
								  		  <td class="datacell"><c:out value="${methodCall.serviceInfo.serviceName}" />&nbsp;</td>
											</c:otherwise>
										</c:choose>
					  		  </tr>
					  		  <tr>
										<td class="thnormal" align="right" width="20%">ServiceInfo.endpointUrl:</td>
										<c:choose>
											<c:when test="${methodCall.serviceInfo == null}">
								  		  <td class="datacell">ServiceInfo is null&nbsp;</td>
											</c:when>
											<c:otherwise>
								  		  <td class="datacell"><c:out value="${methodCall.serviceInfo.endpointUrl}" />&nbsp;</td>
											</c:otherwise>
										</c:choose>
					  		  </tr>

					  		  <tr>
										<td class="thnormal" align="right" width="20%">ServiceInfo.queue:</td>
										<c:choose>
											<c:when test="${methodCall.serviceInfo == null}">
								  		  <td class="datacell">ServiceInfo is null&nbsp;</td>
											</c:when>
											<c:otherwise>
												<c:choose>
													<c:when test="${methodCall.serviceInfo.serviceDefinition == null}">
														<td class="datacell">ServiceInfo.ServiceDefinition is null&nbsp;</td>
													</c:when>
													<c:otherwise>
										  		  <td class="datacell"><c:out value="${methodCall.serviceInfo.serviceDefinition.queue}" />&nbsp;</td>
													</c:otherwise>
												</c:choose>
											</c:otherwise>
										</c:choose>
					  		  </tr>
					  		  <tr>
										<td class="thnormal" align="right" width="20%">ServiceInfo.alive:</td>
										<c:choose>
											<c:when test="${methodCall.serviceInfo == null}">
								  		  <td class="datacell">ServiceInfo is null&nbsp;</td>
											</c:when>
											<c:otherwise>
								  		  <td class="datacell"><c:out value="${methodCall.serviceInfo.alive}" />&nbsp;</td>
											</c:otherwise>
										</c:choose>
					  		  </tr>
					  		  <tr>
										<td class="thnormal" align="right" width="20%">ServiceInfo.priority:</td>
										<c:choose>
											<c:when test="${methodCall.serviceInfo == null}">
								  		  <td class="datacell">ServiceInfo is null&nbsp;</td>
											</c:when>
											<c:otherwise>
												<c:choose>
													<c:when test="${methodCall.serviceInfo.serviceDefinition == null}">
														<td class="datacell">ServiceInfo.ServiceDefinition is null&nbsp;</td>
													</c:when>
													<c:otherwise>
										  		  <td class="datacell"><c:out value="${methodCall.serviceInfo.serviceDefinition.priority}" />&nbsp;</td>
													</c:otherwise>
												</c:choose>
											</c:otherwise>
										</c:choose>
					  		  </tr>
					  		  <tr>
										<td class="thnormal" align="right" width="20%">ServiceInfo.retryAttempts:</td>
										<c:choose>
											<c:when test="${methodCall.serviceInfo == null}">
								  		  <td class="datacell">ServiceInfo is null&nbsp;</td>
											</c:when>
											<c:otherwise>
												<c:choose>
													<c:when test="${methodCall.serviceInfo.serviceDefinition == null}">
														<td class="datacell">ServiceInfo.ServiceDefinition is null&nbsp;</td>
													</c:when>
													<c:otherwise>
										  		  <td class="datacell"><c:out value="${methodCall.serviceInfo.serviceDefinition.retryAttempts}" />&nbsp;</td>
													</c:otherwise>
												</c:choose>
											</c:otherwise>
										</c:choose>
					  		  </tr>
					  		  <tr>
										<td class="thnormal" align="right" width="20%">ServiceInfo.millisToLive:</td>
										<c:choose>
											<c:when test="${methodCall.serviceInfo == null}">
								  		  <td class="datacell">ServiceInfo is null&nbsp;</td>
											</c:when>
											<c:otherwise>
												<c:choose>
													<c:when test="${methodCall.serviceInfo.serviceDefinition == null}">
														<td class="datacell">ServiceInfo.ServiceDefinition is null&nbsp;</td>
													</c:when>
													<c:otherwise>
										  		  <td class="datacell"><c:out value="${methodCall.serviceInfo.serviceDefinition.millisToLive}" />&nbsp;</td>
													</c:otherwise>
												</c:choose>
											</c:otherwise>
										</c:choose>
					  		  </tr>
					  		  <tr>
										<td class="thnormal" align="right" width="20%">ServiceInfo.messageExceptionHandler:</td>
										<c:choose>
											<c:when test="${methodCall.serviceInfo == null}">
								  		  <td class="datacell">ServiceInfo is null&nbsp;</td>
											</c:when>
											<c:otherwise>
												<c:choose>
													<c:when test="${methodCall.serviceInfo.serviceDefinition == null}">
														<td class="datacell">ServiceInfo.ServiceDefinition is null&nbsp;</td>
													</c:when>
													<c:otherwise>
										  		  <td class="datacell"><c:out value="${methodCall.serviceInfo.serviceDefinition.messageExceptionHandler}" />&nbsp;</td>
													</c:otherwise>
												</c:choose>
											</c:otherwise>
										</c:choose>
					  		  </tr>
					  		  <tr>
										<td class="thnormal" align="right" width="20%">ServiceInfo.service.class:</td>
										<c:choose>
											<c:when test="${methodCall.serviceInfo == null}">
								  		  <td class="datacell">ServiceInfo is null&nbsp;</td>
											</c:when>
											<c:otherwise>
												<c:choose>
													<c:when test="${methodCall.serviceInfo.serviceDefinition == null}">
														<td class="datacell">ServiceInfo.ServiceDefinition is null&nbsp;</td>
													</c:when>
													<c:otherwise>
										  		  <td class="datacell"><c:out value="${methodCall.serviceInfo.serviceDefinition.service.class}" />&nbsp;</td>
													</c:otherwise>
												</c:choose>
											</c:otherwise>
										</c:choose>
					  		  </tr>
					  		  <tr>
										<td class="thnormal" align="right" width="20%">ServiceInfo.busSecurity:</td>
										<c:choose>
											<c:when test="${methodCall.serviceInfo.serviceDefinition == null}">
														<td class="datacell">ServiceInfo.ServiceDefinition is null&nbsp;</td>
													</c:when>
											<c:otherwise>
								  		  <td class="datacell"><c:out value="${methodCall.serviceInfo.serviceDefinition.busSecurity}" />&nbsp;</td>
											</c:otherwise>
										</c:choose>
					  		  </tr>
								<tr>
										<td class="thnormal" align="right" width="20%">ServiceInfo.credentialsType:</td>
										<c:choose>
											<c:when test="${methodCall.serviceInfo.serviceDefinition == null}">
														<td class="datacell">ServiceInfo.ServiceDefinition is null&nbsp;</td>
													</c:when>
											<c:otherwise>
								  		  <td class="datacell"><c:out value="${methodCall.serviceInfo.serviceDefinition.credentialsType}" />&nbsp;</td>
											</c:otherwise>
										</c:choose>
					  		  </tr>
					  		  <tr>
										<td class="thnormal" align="right" width="20%">Arguments:</td>
										<c:choose>
											<c:when test="${(empty methodCall.arguments) || (empty methodCall.paramTypes)}">
								  		  <td class="datacell">No Arguments&nbsp;</td>
											</c:when>
											<c:otherwise>
												<td class="datacell">
												<c:forEach items="methodCall.arguments" var="serviceInterface" varStatus="x">
									  		  [<c:out value="${methodCall.paramTypes[x.index].name}" />] <c:out value="${methodCall.arguments[x.index]}" />&nbsp;<br />
												</c:forEach>
												</td>
											</c:otherwise>
										</c:choose>
					  		  </tr>
						 		</table>
      		  </td>
      		  <td width=20><img src="images/pixel_clear.gif" alt="" width=20 height=20></td>
    			</tr>
    	  </table>
    	</td>
      </tr>
    </table>

    <jsp:include page="../Footer.jsp"/>

  </body>
</html>
