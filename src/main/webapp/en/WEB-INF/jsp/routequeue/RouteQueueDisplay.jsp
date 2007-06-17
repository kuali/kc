<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

	            <table width="100%" border="0" cellpadding="0" cellspacing="0">
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
					  <td class="datacell"><c:out value="${RouteQueueForm.routeQueue.routeQueueId}" />&nbsp;</td>	
		  		    </tr>
			      </c:if>
		  		  <tr>
					<td class="thnormal" align="right" width="20%">Document Id:</td>
					<c:if test="${RouteQueueForm.existingRouteQueue != null}">
			  		  <td class="datacell"><c:out value="${RouteQueueForm.existingRouteQueue.routeHeaderId}" />&nbsp;</td>
				 	</c:if>
					<td class="datacell"><c:out value="${RouteQueueForm.routeQueue.routeHeaderId}" />&nbsp;</td>
		  		  </tr>
		  		  <tr>
					<td class="thnormal" align="right" width="20%">Queue Priority:</td>
					<c:if test="${RouteQueueForm.existingRouteQueue != null}">
			  		  <td class="datacell"><c:out value="${RouteQueueForm.existingRouteQueue.queuePriority}" />&nbsp;</td>
					</c:if>
					<td class="datacell"><c:out value="${RouteQueueForm.routeQueue.queuePriority}" />&nbsp;</td>
		  		  </tr>
		  		  <tr>
					<td class="thnormal" align="right" width="20%">Queue Status:</td>
					<c:if test="${RouteQueueForm.existingRouteQueue != null}">
			  		  <td class="datacell"><c:out value="${RouteQueueForm.existingRouteQueue.routeQueueStatusLabel}" />&nbsp;</td>
					</c:if>
					<td class="datacell"><c:out value="${RouteQueueForm.routeQueue.routeQueueStatusLabel}" />&nbsp;</td>
		  	      </tr>
		  		  <tr>
					<td class="thnormal" align="right" width="20%">Queue Date:</td>
					<c:if test="${RouteQueueForm.existingRouteQueue != null}">
			  		  <td class="datacell"><c:out value="${RouteQueueForm.existingQueueDate}" />&nbsp;</td>
					</c:if>
					<td class="datacell"><c:out value="${RouteQueueForm.newQueueDate}" />&nbsp;</td>
		  		  </tr>
		  		  <tr>
					<td class="thnormal" align="right" width="20%">Retry Count:</td>
				    <c:if test="${RouteQueueForm.existingRouteQueue != null}">
			  	      <td class="datacell"><c:out value="${RouteQueueForm.existingRouteQueue.retryCount}" />&nbsp;</td>
				    </c:if>
				    <td class="datacell"><c:out value="${RouteQueueForm.routeQueue.retryCount}" />&nbsp;</td>
		  		  </tr>
		  		  <tr>
					<td class="thnormal" align="right" width="20%">IP Number:</td>
					<c:if test="${RouteQueueForm.existingRouteQueue != null}">
			  		<td class="datacell"><c:out value="${RouteQueueForm.existingRouteQueue.ipNumber}" />&nbsp;</td>
					</c:if>
					<td class="datacell"><c:out value="${RouteQueueForm.routeQueue.ipNumber}" />&nbsp;</td>
		  		  </tr>
		  		  <tr>
					<td class="thnormal" align="right" width="20%">Processor Class Name:</td>
					<c:if test="${RouteQueueForm.existingRouteQueue != null}">
			  		<td class="datacell"><c:out value="${RouteQueueForm.existingRouteQueue.processorClassName}" />&nbsp;</td>
					</c:if>
					<td class="datacell"><c:out value="${RouteQueueForm.routeQueue.processorClassName}" />&nbsp;</td>
		  		  </tr>
		  		  <tr>
					<td class="thnormal" align="right" width="20%">Processor Value:</td>
					<c:if test="${RouteQueueForm.existingRouteQueue != null}">
			  		<td class="datacell"><c:out value="${RouteQueueForm.existingRouteQueue.processorValue}" />&nbsp;</td>
					</c:if>
					<td class="datacell"><c:out value="${RouteQueueForm.routeQueue.processorValue}" />&nbsp;</td>
		  		  </tr>
		 		</table>  
