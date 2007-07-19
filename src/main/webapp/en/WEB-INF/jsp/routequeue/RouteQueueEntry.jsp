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
    <html-el:hidden name="RouteQueueForm" property="routeQueueFromForm.routeQueueId" />
    <html-el:hidden name="RouteQueueForm" property="routeQueueFromForm.lockVerNbr" />
		<html-el:hidden name="RouteQueueForm" property="routeQueueFromForm.payload" />
				    
    <c:set var="showOld" value="false" />
    <c:if test="${not empty RouteQueueForm.routeQueueFromDatabase}">
    	<c:set var="showOld" value="true" />
    </c:if>
    <c:set var="inEditMode" value="false" />
    <c:if test="${RouteQueueForm.showEdit == 'yes'}">
    	<c:set var="inEditMode" value="true" />
    </c:if>
    
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
		              <tr>
						     		<td class="thnormal" width="20%">&nbsp;</td>
						     		<c:if test="${showOld}">
								    	<td class="thnormal">Existing Route Queue Values</td>
								    </c:if>
								    <td class="thnormal">New Route Queue Values</td>
			      		  </tr>
							    <tr>
									  <td class="thnormal" align="right" width="20%">Route Queue Id:&nbsp;</td>
									  <c:if test="${showOld}">
							  			<td class="datacell"><c:out value="${RouteQueueForm.routeQueueFromDatabase.routeQueueId}" /></td>
									  </c:if>
									  <td class="datacell"><c:out value="${RouteQueueForm.routeQueueFromForm.routeQueueId}" />
									  &nbsp;
									  <bean-el:message key="routequeue.help.routeQueueId"/></td>	
				  		    </tr>
				  		    
				  		    <tr>
				  		    	<td class="thnormal" align="right" width="20%">Queue Priority:&nbsp;</td>
				  		    	<c:if test="${showOld}">
					  		    	<td class="datacell">
					  		    		<c:out value="${RouteQueueForm.routeQueueFromDatabase.queuePriority}" />
					  		    	</td>
				  		    	</c:if>
				  		    	<td class="datacell">
				  		    		<c:choose>
				  		    			<c:when test="${inEditMode}">
		                      <html-el:select property="routeQueueFromForm.queuePriority">
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
								          </html-el:select>
								          &nbsp;
								          <bean-el:message key="routequeue.help.queuePriority"/>
				  		    			</c:when>
				  		    			<c:otherwise>
				  		    				<c:out value="${RouteQueueForm.routeQueueFromForm.queuePriority}" />
				  		    			</c:otherwise>
				  		    		</c:choose>
				  		    	</td>
				  		    </tr>
				  		    
				  		    <tr>
				  		    	<td class="thnormal" align="right" width="20%">Queue Status:&nbsp;</td>
				  		    	<c:if test="${showOld}">
					  		    	<td class="datacell">
					  		    		<c:out value="${RouteQueueForm.routeQueueFromDatabase.queueStatus}" />
					  		    	</td>
				  		    	</c:if>
				  		    	<td class="datacell">
				  		    		<c:choose>
				  		    			<c:when test="${inEditMode}">
													<html-el:select property="routeQueueFromForm.queueStatus">
														<html-el:option value=""></html-el:option>
														<html-el:option value="${Constants.ROUTE_QUEUE_QUEUED}"><c:out value="${Constants.ROUTE_QUEUE_QUEUED_LABEL}" /></html-el:option>
														<html-el:option value="${Constants.ROUTE_QUEUE_ROUTING}"><c:out value="${Constants.ROUTE_QUEUE_ROUTING_LABEL}" /></html-el:option>
														<html-el:option value="${Constants.ROUTE_QUEUE_EXCEPTION}"><c:out value="${Constants.ROUTE_QUEUE_EXCEPTION_LABEL}" /></html-el:option>
													</html-el:select>
													&nbsp;
													<bean-el:message key="routequeue.help.queueStatus"/></td>
				  		    			</c:when>
				  		    			<c:otherwise>
				  		    				<c:out value="${RouteQueueForm.routeQueueFromForm.queueStatus}" />
				  		    			</c:otherwise>
				  		    		</c:choose>
				  		    	</td>
				  		    </tr>
				  		    
				  		    <tr>
				  		    	<td class="thnormal" align="right" width="20%">Queue Date:&nbsp;</td>
				  		    	<c:if test="${showOld}">
					  		    	<td class="datacell">
					  		    		<c:out value="${RouteQueueForm.routeQueueFromDatabase.queueDate}" />
					  		    	</td>
				  		    	</c:if>
				  		    	<td class="datacell">
				  		    		<c:choose>
				  		    			<c:when test="${inEditMode}">
													<html-el:text property="routeQueueFromForm.queueDate" size="25" maxlength="25"/>
													&nbsp;
													<bean-el:message key="routequeue.help.queueDate"/>
												</c:when>
				  		    			<c:otherwise>
				  		    				<c:out value="${RouteQueueForm.routeQueueFromForm.queueDate}" />
				  		    			</c:otherwise>
				  		    		</c:choose>
				  		    	</td>
				  		    </tr>
				  		    
				  		    <tr>
				  		    	<td class="thnormal" align="right" width="20%">Expiration Date:&nbsp;</td>
				  		    	<c:if test="${showOld}">
					  		    	<td class="datacell">
					  		    		<c:out value="${(RouteQueueForm.routeQueueFromDatabase.expirationDate == null ? '&nbsp;' : RouteQueueForm.routeQueueFromDatabase.expirationDate)}" />
					  		    	</td>
				  		    	</c:if>
				  		    	<td class="datacell">
				  		    		<c:choose>
				  		    			<c:when test="${inEditMode}">
													<html-el:text property="routeQueueFromForm.expirationDate" size="25" maxlength="25" />
													&nbsp;
													<bean-el:message key="routequeue.help.expirationDate"/>
												</c:when>
				  		    			<c:otherwise>
				  		    				<c:out value="${(RouteQueueForm.routeQueueFromDatabase.expirationDate == null ? '&nbsp;' : RouteQueueForm.routeQueueFromDatabase.expirationDate)}" />
				  		    			</c:otherwise>
				  		    		</c:choose>
				  		    	</td>
				  		    </tr>
				  		    
				  		    <tr>
				  		    	<td class="thnormal" align="right" width="20%">Retry Count:&nbsp;</td>
				  		    	<c:if test="${showOld}">
					  		    	<td class="datacell">
					  		    		<c:out value="${RouteQueueForm.routeQueueFromDatabase.retryCount}" />
					  		    	</td>
				  		    	</c:if>
				  		    	<td class="datacell">
				  		    		<c:choose>
				  		    			<c:when test="${inEditMode}">
													<html-el:text property="routeQueueFromForm.retryCount" size="5" maxlength="2" />
													&nbsp;
													<bean-el:message key="routequeue.help.retryCount"/>
				  		    			</c:when>
				  		    			<c:otherwise>
				  		    				<c:out value="${RouteQueueForm.routeQueueFromForm.retryCount}" />
				  		    			</c:otherwise>
				  		    		</c:choose>
				  		    	</td>
				  		    </tr>
				  		    
				  		    <tr>
				  		    	<td class="thnormal" align="right" width="20%">IP Number:&nbsp;</td>
				  		    	<c:if test="${showOld}">
					  		    	<td class="datacell">
					  		    		<c:out value="${RouteQueueForm.routeQueueFromDatabase.ipNumber}" />
					  		    	</td>
				  		    	</c:if>
				  		    	<td class="datacell">
				  		    		<c:choose>
				  		    			<c:when test="${inEditMode}">
													<html-el:text property="routeQueueFromForm.ipNumber" size="20" maxlength="15"/>
													&nbsp;
													<bean-el:message key="routequeue.help.ipNumber"/>
				  		    			</c:when>
				  		    			<c:otherwise>
				  		    				<c:out value="${RouteQueueForm.routeQueueFromForm.ipNumber}" />
				  		    			</c:otherwise>
				  		    		</c:choose>
				  		    	</td>
				  		    </tr>
				  		    
				  		    <tr>
				  		    	<td class="thnormal" align="right" width="20%">Service Name:&nbsp;</td>
				  		    	<c:if test="${showOld}">
					  		    	<td class="datacell">
					  		    		<c:out value="${RouteQueueForm.routeQueueFromDatabase.serviceName}" />
					  		    	</td>
				  		    	</c:if>
				  		    	<td class="datacell">
				  		    		<c:choose>
				  		    			<c:when test="${inEditMode}">
													<html-el:text property="routeQueueFromForm.serviceName" size="35" maxlength="50" />
													&nbsp;
													<bean-el:message key="routequeue.help.serviceName"/>
				  		    			</c:when>
				  		    			<c:otherwise>
				  		    				<c:out value="${RouteQueueForm.routeQueueFromForm.serviceName}" />
				  		    			</c:otherwise>
				  		    		</c:choose>
				  		    	</td>
				  		    </tr>
				  		    
				  		    <tr>
				  		    	<td class="thnormal" align="right" width="20%">Message Entity:&nbsp;</td>
				  		    	<c:if test="${showOld}">
					  		    	<td class="datacell">
					  		    		<c:out value="${RouteQueueForm.routeQueueFromDatabase.messageEntity}" />
					  		    	</td>
				  		    	</c:if>
				  		    	<td class="datacell">
				  		    		<c:choose>
				  		    			<c:when test="${inEditMode}">
													<html-el:text property="routeQueueFromForm.messageEntity" size="35" maxlength="50" />
													&nbsp;
													<bean-el:message key="routequeue.help.messageEntity"/>
				  		    			</c:when>
				  		    			<c:otherwise>
				  		    				<c:out value="${RouteQueueForm.routeQueueFromForm.messageEntity}" />
				  		    			</c:otherwise>
				  		    		</c:choose>
				  		    	</td>
				  		    </tr>
				  		    
				  		    <tr>
				  		    	<td class="thnormal" align="right" width="20%">Method Name:&nbsp;</td>
				  		    	<c:if test="${showOld}">
					  		    	<td class="datacell">
					  		    		<c:out value="${RouteQueueForm.routeQueueFromDatabase.methodName}" />
					  		    	</td>
				  		    	</c:if>
				  		    	<td class="datacell">
				  		    		<c:choose>
				  		    			<c:when test="${inEditMode}">
													<html-el:text property="routeQueueFromForm.methodName" size="35" maxlength="50" />
													&nbsp;
													<bean-el:message key="routequeue.help.methodName"/>
				  		    			</c:when>
				  		    			<c:otherwise>
				  		    				<c:out value="${RouteQueueForm.routeQueueFromForm.methodName}" />
				  		    			</c:otherwise>
				  		    		</c:choose>
				  		    	</td>
				  		    </tr>
				  		    
		              <tr>
				            <td class="thnormal" colspan="3" align="center">
  	                  <c:choose>
		  	                <c:when test="${inEditMode}">
		                      <a href="javascript: submitQueueId('saveAndRequeue', '<c:out value="${RouteQueueForm.routeQueueFromForm.routeQueueId}" />')"><img src="images/buttonsmall_quedoc.gif" alt="Save Changes and Requeue Message"/></a>&nbsp;&nbsp;&nbsp;&nbsp;
		                      <a href="javascript: submitQueueId('delete', '<c:out value="${RouteQueueForm.routeQueueFromForm.routeQueueId}" />')"><img src="images/buttonsmall_delete.gif" alt="Delete Message"/></a>&nbsp;&nbsp;&nbsp;&nbsp;
		                      <a href="javascript: submitQueueId('reset', '<c:out value="${RouteQueueForm.routeQueueFromForm.routeQueueId}" />')"><img src="images/buttonsmall_reset.gif" alt="Reset Message"/></a>&nbsp;&nbsp;&nbsp;&nbsp;
				                </c:when>
				                <c:otherwise>
		                      <a href="javascript: setMethod('queueNewMessage');document.forms[0].submit();"><img src="images/buttonsmall_quedoc.gif" alt="Queue This New Message"/></a>&nbsp;&nbsp;&nbsp;&nbsp;
				                </c:otherwise>
		                  </c:choose>
	 	                  <a href="javascript: submitQueueId('clear', '<c:out value="${RouteQueueForm.routeQueueFromForm.routeQueueId}" />')"><img src="images/buttonsmall_clear.gif" alt="Clear Message"/></a>
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
