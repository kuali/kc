<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<c:set var="methodCall" value="${RouteQueueForm.routeQueueFromDatabase.methodCall}" />

<html>
  <head>
    <title>Route Queue Payload</title>
    <link href="css/screen.css" rel="stylesheet" type="text/css">
    <script language="JavaScript" src="scripts/en-common.js"></script>
    <script language="JavaScript" src="scripts/routequeue-common.js"></script>
  </head>
  
  <body>
  
    <table width="100%" border=0 cellpadding=0 cellspacing=0 class="headercell1">
      <tr>
        <td><img src="images/wf-logo.gif" alt="OneStart Workflow" width=150 height=21 hspace=5 vspace=5></td>
        <td width="90%">
          <a href="RouteQueue.do?methodToCall=start">Route Queue</a>
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
	                <td height=30><strong>Route Queue Payload</strong></td>
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
		  <jsp:include page="../WorkflowMessages.jsp" flush="true" />
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
										<td class="thnormal" align="right" width="20%">RouteQueueId:</td>
						  		  <td class="datacell"><c:out value="${RouteQueueForm.routeQueueFromForm.routeQueueId}" />&nbsp;</td>
					  		  </tr>
							    <tr>
									  <td class="thnormal" align="right" width="20%">Payload Class:</td>
					  		    <td class="datacell"><c:out value="${methodCall.class}" /></td>
				  		    </tr>
					  		  <tr>
										<td class="thnormal" align="right" width="20%">Document Id:</td>
						  		  <td class="datacell"><c:out value="${methodCall.documentId}" />&nbsp;</td>
					  		  </tr>
					  		  <tr>
										<td class="thnormal" align="right" width="20%">Method Name</td>
						  		  <td class="datacell"><c:out value="${methodCall.methodName}" />&nbsp;</td>
					  		  </tr>
					  		  <tr>
										<td class="thnormal" align="right" width="20%">repeatCallTimeIncrement</td>
						  		  <td class="datacell"><c:out value="${methodCall.repeatCallTimeIncrement}" />&nbsp;</td>
					  		  </tr>
					  		  <tr>
										<td class="thnormal" align="right" width="20%">repeatCallTimeIncrement</td>
						  		  <td class="datacell"><c:out value="${methodCall.repeatCallTimeIncrement}" />&nbsp;</td>
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
										<td class="thnormal" align="right" width="20%">ServiceInfo.queue:</td>
										<c:choose>
											<c:when test="${methodCall.serviceInfo == null}">
								  		  <td class="datacell">ServiceInfo is null&nbsp;</td>
											</c:when>
											<c:otherwise>
								  		  <td class="datacell"><c:out value="${methodCall.serviceInfo.queue}" />&nbsp;</td>
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
								  		  <td class="datacell"><c:out value="${methodCall.serviceInfo.priority}" />&nbsp;</td>
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
								  		  <td class="datacell"><c:out value="${methodCall.serviceInfo.retryAttempts}" />&nbsp;</td>
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
								  		  <td class="datacell"><c:out value="${methodCall.serviceInfo.millisToLive}" />&nbsp;</td>
											</c:otherwise>
										</c:choose>
					  		  </tr>
					  		  <tr>
										<td class="thnormal" align="right" width="20%">ServiceInfo.messageExceptionHandlerClassName:</td>
										<c:choose>
											<c:when test="${methodCall.serviceInfo == null}">
								  		  <td class="datacell">ServiceInfo is null&nbsp;</td>
											</c:when>
											<c:otherwise>
								  		  <td class="datacell"><c:out value="${methodCall.serviceInfo.messageExceptionHandlerClassName}" />&nbsp;</td>
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
								  		  <td class="datacell"><c:out value="${methodCall.serviceInfo.service.class}" />&nbsp;</td>
											</c:otherwise>
										</c:choose>
					  		  </tr>
					  		  <tr>
										<td class="thnormal" align="right" width="20%">ServiceInfo.messageEntity:</td>
										<c:choose>
											<c:when test="${methodCall.serviceInfo == null}">
								  		  <td class="datacell">ServiceInfo is null&nbsp;</td>
											</c:when>
											<c:otherwise>
								  		  <td class="datacell"><c:out value="${methodCall.serviceInfo.messageEntity}" />&nbsp;</td>
											</c:otherwise>
										</c:choose>
					  		  </tr>
					  		  <tr>
										<td class="thnormal" align="right" width="20%">ServiceInfo.serviceInterfaces:</td>
										<c:choose>
											<c:when test="${methodCall.serviceInfo == null}">
								  		  <td class="datacell">ServiceInfo is null&nbsp;</td>
											</c:when>
											<c:otherwise>
												<td class="datacell">
													<c:forEach items="${methodCall.serviceInfo.serviceInterfaces}" var="serviceInterfaceItem" varStatus="i">
														<c:out value="${serviceInterfaceItem}" /><br />
													</c:forEach>
												</td>
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

    <jsp:include page="../BackdoorMessage.jsp" flush="true"/>  
  
  </body>
</html>
