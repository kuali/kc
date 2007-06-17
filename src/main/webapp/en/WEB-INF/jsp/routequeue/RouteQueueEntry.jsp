<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<html>
  <head>
    <title>Route Queue Entry</title>
    <link href="css/screen.css" rel="stylesheet" type="text/css">
    <script language="JavaScript" src="scripts/en-common.js"></script>
    <script language="JavaScript" src="scripts/routequeue-common.js"></script>
  </head>
  
  <body>
  
    <html-el:form action="/RouteQueue.do">
    <html-el:hidden name="RouteQueueForm" property="methodToCall" />
    <html-el:hidden name="RouteQueueForm" property="showEdit" />
    <html-el:hidden name="RouteQueueForm" property="routeQueueId" />
    <html-el:hidden name="RouteQueueForm" property="routeQueue.routeQueueId" />
    <html-el:hidden name="RouteQueueForm" property="routeQueue.lockVerNbr" />
    
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
	                <td height=30><strong>Route Queue Entry</strong></td>
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
			      <c:if test="${RouteQueueForm.existingRouteQueue != null}">
	              <tr>
		     		<td class="thnormal" width="20%">&nbsp;</td>
				    <td class="thnormal">Existing Route Queue Values</td>
				    <td class="thnormal">New Route Queue Values</td>
	      		  </tr>
			      </c:if>
                  <c:if test="${RouteQueueForm.routeQueue.routeQueueId != null && RouteQueueForm.routeQueue.routeQueueId != ''}">
				    <tr>
					  <td class="thnormal" align="right" width="20%">Route Queue Id:</td>
					  <c:if test="${RouteQueueForm.existingRouteQueue != null}">
			  		    <td class="datacell"><c:out value="${RouteQueueForm.existingRouteQueue.routeQueueId}" /></td>
					  </c:if>
					  <td class="datacell"><c:out value="${RouteQueueForm.routeQueue.routeQueueId}" />&nbsp;<bean-el:message key="routequeue.help.routeQueueId"/></td>	
		  		    </tr>
			      </c:if>
		  		  <tr>
					<td class="thnormal" align="right" width="20%">Document Id:</td>
					<c:if test="${RouteQueueForm.existingRouteQueue != null}">
			  		  <td class="datacell"><c:out value="${RouteQueueForm.existingRouteQueue.routeHeaderId}" />&nbsp;</td>
				 	</c:if>
					<td class="datacell"><html-el:text property="routeQueue.routeHeaderId" />&nbsp;<bean-el:message key="general.help.routeHeaderId"/></td>
		  		  </tr>
		  		  <tr>
					<td class="thnormal" align="right" width="20%">Queue Priority:</td>
					<c:if test="${RouteQueueForm.existingRouteQueue != null}">
			  		  <td class="datacell"><c:out value="${RouteQueueForm.existingRouteQueue.queuePriority}" />&nbsp;</td>
					</c:if>
					<td class="datacell">
                      <html-el:select property="routeQueue.queuePriority">
                        <html-el:option value="0"><c:out value="0" /></html-el:option>
			            <html-el:option value="1"><c:out value="1" /></html-el:option>
			            <html-el:option value="2"><c:out value="2" /></html-el:option>
			            <html-el:option value="3"><c:out value="3" /></html-el:option>
			            <html-el:option value="4"><c:out value="4" /></html-el:option>
			            <html-el:option value="5"><c:out value="5" /></html-el:option>
			            <html-el:option value="6"><c:out value="6" /></html-el:option>
			            <html-el:option value="7"><c:out value="7" /></html-el:option>
			            <html-el:option value="8"><c:out value="8" /></html-el:option>
			            <html-el:option value="9"><c:out value="9" /></html-el:option>
			          </html-el:select>&nbsp;<bean-el:message key="routequeue.help.queuePriority"/>
			        </td>
		  		  </tr>
                  <c:if test="${RouteQueueForm.showEdit == 'yes'}">
		  		    <tr>
					  <td class="thnormal" align="right" width="20%">Queue Status:</td>
					  <c:if test="${RouteQueueForm.existingRouteQueue != null}">
			  		  <td class="datacell"><c:out value="${RouteQueueForm.existingRouteQueue.queueStatus}" />&nbsp;</td>
					  </c:if>
					  <td class="datacell">
					    <html-el:select property="routeQueue.queueStatus">
			            <html-el:option value=""></html-el:option>
                        <html-el:option value="${Constants.ROUTE_QUEUE_QUEUED}"><c:out value="${Constants.ROUTE_QUEUE_QUEUED_LABEL}" /></html-el:option>
			            <html-el:option value="${Constants.ROUTE_QUEUE_ROUTING}"><c:out value="${Constants.ROUTE_QUEUE_ROUTING_LABEL}" /></html-el:option>
			            <html-el:option value="${Constants.ROUTE_QUEUE_EXCEPTION}"><c:out value="${Constants.ROUTE_QUEUE_EXCEPTION_LABEL}" /></html-el:option>
			          </html-el:select>&nbsp;<bean-el:message key="routequeue.help.queueStatus"/></td>
		  		    </tr>
		  		    <tr>
					  <td class="thnormal" align="right" width="20%">Queue Date:</td>
					  <c:if test="${RouteQueueForm.existingRouteQueue != null}">
			  		    <td class="datacell"><c:out value="${RouteQueueForm.existingQueueDate}" />&nbsp;</td>
					  </c:if>
					  <td class="datacell"><html-el:text property="newQueueDate" />&nbsp;<bean-el:message key="routequeue.help.queueDate"/></td>
		  		    </tr>
		  		    <tr>
					  <td class="thnormal" align="right" width="20%">Retry Count:</td>
					  <c:if test="${RouteQueueForm.existingRouteQueue != null}">
			  		  <td class="datacell"><c:out value="${RouteQueueForm.existingRouteQueue.retryCount}" />&nbsp;</td>
					  </c:if>
					  <td class="datacell"><html-el:text property="routeQueue.retryCount" />&nbsp;<bean-el:message key="routequeue.help.retryCount"/></td>
		  		    </tr>
                  </c:if>
		  		  <tr>
					<td class="thnormal" align="right" width="20%">IP Number:</td>
					<c:if test="${RouteQueueForm.existingRouteQueue != null}">
			  		<td class="datacell"><c:out value="${RouteQueueForm.existingRouteQueue.ipNumber}" />&nbsp;</td>
					</c:if>
					<td class="datacell"><html-el:text property="routeQueue.ipNumber" />&nbsp;<bean-el:message key="routequeue.help.ipNumber"/></td>
		  		  </tr>
		  		  <tr>
					<td class="thnormal" align="right" width="20%">Processor Class Name:</td>
					<c:if test="${RouteQueueForm.existingRouteQueue != null}">
			  		<td class="datacell"><c:out value="${RouteQueueForm.existingRouteQueue.processorClassName}" />&nbsp;</td>
					</c:if>
					<td class="datacell"><html-el:text property="routeQueue.processorClassName" />&nbsp;<bean-el:message key="routequeue.help.processorClassName"/></td>
		  		  </tr>
		  		  <tr>
					<td class="thnormal" align="right" width="20%">Processor Value:</td>
					<c:if test="${RouteQueueForm.existingRouteQueue != null}">
			  		<td class="datacell"><c:out value="${RouteQueueForm.existingRouteQueue.processorValue}" />&nbsp;</td>
					</c:if>
					<td class="datacell"><html-el:text property="routeQueue.processorValue" />&nbsp;<bean-el:message key="routequeue.help.processorValue"/></td>
		  		  </tr>
	              <tr>
		            <td class="thnormal" colspan="3" align="center">
  	                  <c:choose>
	  	                <c:when test="${RouteQueueForm.showEdit != 'yes'}">
                          <a href="javascript: setMethod('queueMessage');document.forms[0].submit();"><img src="images/buttonsmall_quedoc.gif" /></a>&nbsp;&nbsp;&nbsp;&nbsp;
		                </c:when>
		                <c:otherwise>
                          <a href="javascript: submitQueueId('requeueMessage', '<c:out value="${RouteQueueForm.existingRouteQueue.routeQueueId}" />')"><img src="images/buttonsmall_quedoc.gif" /></a>&nbsp;&nbsp;&nbsp;&nbsp;
                          <a href="javascript: submitQueueId('delete', '<c:out value="${RouteQueueForm.routeQueue.routeQueueId}" />')"><img src="images/buttonsmall_delete.gif" /></a>&nbsp;&nbsp;&nbsp;&nbsp;
                          <a href="javascript: submitQueueId('reset', '<c:out value="${RouteQueueForm.routeQueue.routeQueueId}" />')"><img src="images/buttonsmall_reset.gif" /></a>&nbsp;&nbsp;&nbsp;&nbsp;
		                </c:otherwise>
	                  </c:choose>
 	                  <a href="javascript: submitQueueId('clear', '<c:out value="${RouteQueueForm.routeQueue.routeQueueId}" />')"><img src="images/buttonsmall_clear.gif" /></a>
		            </td>
	              </tr>
		 		</table>  
      		  </td>
      		  <td width=20><img src="images/pixel_clear.gif" alt="" width=20 height=20></td>
    		</tr>
    	  </table>
    	</td>
      </tr>  
    </table>
    </html-el:form>

    <jsp:include page="../BackdoorMessage.jsp" flush="true"/>  
  
  </body>
</html>
