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


<html>
  <head>
    <title>Message Entry</title>
    <link href="css/screen.css" rel="stylesheet" type="text/css">
    <script language="JavaScript" src="scripts/en-common.js"></script>
    <script language="JavaScript" src="scripts/messagequeue-common.js"></script>
  </head>

  <body>

    <html-el:form action="/MessageQueue.do">
    <html-el:hidden name="MessageQueueForm" property="methodToCall" />
    <html-el:hidden name="MessageQueueForm" property="showEdit" />
    <html-el:hidden name="MessageQueueForm" property="messageId" />
    <html-el:hidden name="MessageQueueForm" property="messageQueueFromForm.routeQueueId" />
    <html-el:hidden name="MessageQueueForm" property="messageQueueFromForm.lockVerNbr" />

    <c:set var="showOld" value="false" />
    <c:if test="${not empty MessageQueueForm.messageQueueFromDatabase}">
    	<c:set var="showOld" value="true" />
    </c:if>
    <c:set var="inEditMode" value="false" />
    <c:if test="${MessageQueueForm.showEdit == 'yes'}">
    	<c:set var="inEditMode" value="true" />
    </c:if>

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
			                <td height=30><strong>Message Queue Entry</strong></td>
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
						     		<td class="thnormal" width="20%">&nbsp;</td>
						     		<c:if test="${showOld}">
								    	<td class="thnormal">Existing Message Queue Values</td>
								    </c:if>
								    <td class="thnormal">New Message Queue Values</td>
			      		  </tr>
							    <tr>
									  <td class="thnormal" align="right" width="20%">Message Id:&nbsp;</td>
									  <c:if test="${showOld}">
							  			<td class="datacell"><c:out value="${MessageQueueForm.messageQueueFromDatabase.routeQueueId}" /></td>
									  </c:if>
									  <td class="datacell"><c:out value="${MessageQueueForm.messageQueueFromForm.routeQueueId}" />
									  &nbsp;
									  <bean-el:message key="messagequeue.help.routeQueueId"/></td>
				  		    </tr>

				  		    <tr>
				  		    	<td class="thnormal" align="right" width="20%">Queue Date:&nbsp;</td>
				  		    	<c:if test="${showOld}">
					  		    	<td class="datacell">
					  		    		<c:out value="${MessageQueueForm.messageQueueFromDatabase.queueDate}" />
					  		    	</td>
				  		    	</c:if>
				  		    	<td class="datacell">
				  		    		<c:out value="${MessageQueueForm.messageQueueFromForm.queueDate}" />
				  		    	</td>
				  		    </tr>

				  		    <tr>
				  		    	<td class="thnormal" align="right" width="20%">Expiration Date:&nbsp;</td>
				  		    	<c:if test="${showOld}">
					  		    	<td class="datacell">
					  		    		<c:out value="${MessageQueueForm.messageQueueFromDatabase.expirationDate}" default="&nbsp;" escapeXml="false"/>
					  		    	</td>
				  		    	</c:if>
				  		    	<td class="datacell">
		  		    				<c:out value="${MessageQueueForm.messageQueueFromDatabase.expirationDate}" default="&nbsp;" escapeXml="false"/>
				  		    	</td>
				  		    </tr>

				  		    <tr>
				  		    	<td class="thnormal" align="right" width="20%">Queue Priority:&nbsp;</td>
				  		    	<c:if test="${showOld}">
					  		    	<td class="datacell">
					  		    		<c:out value="${MessageQueueForm.messageQueueFromDatabase.queuePriority}" />
					  		    	</td>
				  		    	</c:if>
				  		    	<td class="datacell">
				  		    		<c:choose>
				  		    			<c:when test="${inEditMode}">
		                      <html-el:text property="messageQueueFromForm.queuePriority"/>
								          &nbsp;
								          <bean-el:message key="messagequeue.help.queuePriority"/>
				  		    			</c:when>
				  		    			<c:otherwise>
				  		    				<c:out value="${MessageQueueForm.messageQueueFromForm.queuePriority}" />
				  		    			</c:otherwise>
				  		    		</c:choose>
				  		    	</td>
				  		    </tr>

				  		    <tr>
				  		    	<td class="thnormal" align="right" width="20%">Queue Status:&nbsp;</td>
				  		    	<c:if test="${showOld}">
					  		    	<td class="datacell">
					  		    		<c:out value="${MessageQueueForm.messageQueueFromDatabase.queueStatus}" />
					  		    	</td>
				  		    	</c:if>
				  		    	<td class="datacell">
				  		    		<c:choose>
				  		    			<c:when test="${inEditMode}">
													<html-el:select property="messageQueueFromForm.queueStatus">
														<html-el:option value=""></html-el:option>
														<html-el:option value="${ksb_constant.ROUTE_QUEUE_QUEUED}"><c:out value="${ksb_constant.ROUTE_QUEUE_QUEUED_LABEL}" /></html-el:option>
														<html-el:option value="${ksb_constant.ROUTE_QUEUE_ROUTING}"><c:out value="${ksb_constant.ROUTE_QUEUE_ROUTING_LABEL}" /></html-el:option>
														<html-el:option value="${ksb_constant.ROUTE_QUEUE_EXCEPTION}"><c:out value="${ksb_constant.ROUTE_QUEUE_EXCEPTION_LABEL}" /></html-el:option>
													</html-el:select>
													&nbsp;
													<bean-el:message key="messagequeue.help.queueStatus"/></td>
				  		    			</c:when>
				  		    			<c:otherwise>
				  		    				<c:out value="${MessageQueueForm.messageQueueFromForm.queueStatus}" />
				  		    			</c:otherwise>
				  		    		</c:choose>
				  		    	</td>
				  		    </tr>



				  		    <tr>
				  		    	<td class="thnormal" align="right" width="20%">Retry Count:&nbsp;</td>
				  		    	<c:if test="${showOld}">
					  		    	<td class="datacell">
					  		    		<c:out value="${MessageQueueForm.messageQueueFromDatabase.retryCount}" />
					  		    	</td>
				  		    	</c:if>
				  		    	<td class="datacell">
				  		    		<c:choose>
				  		    			<c:when test="${inEditMode}">
													<html-el:text property="messageQueueFromForm.retryCount" size="5" maxlength="2" />
													&nbsp;
													<bean-el:message key="messagequeue.help.retryCount"/>
				  		    			</c:when>
				  		    			<c:otherwise>
				  		    				<c:out value="${MessageQueueForm.messageQueueFromForm.retryCount}" />
				  		    			</c:otherwise>
				  		    		</c:choose>
				  		    	</td>
				  		    </tr>

				  		    <tr>
				  		    	<td class="thnormal" align="right" width="20%">IP Number:&nbsp;</td>
				  		    	<c:if test="${showOld}">
					  		    	<td class="datacell">
					  		    		<c:out value="${MessageQueueForm.messageQueueFromDatabase.ipNumber}" />
					  		    	</td>
				  		    	</c:if>
				  		    	<td class="datacell">
				  		    		<c:choose>
				  		    			<c:when test="${inEditMode}">
													<html-el:text property="messageQueueFromForm.ipNumber" size="20" maxlength="15"/>
													&nbsp;
													<bean-el:message key="messagequeue.help.ipNumber"/>
				  		    			</c:when>
				  		    			<c:otherwise>
				  		    				<c:out value="${MessageQueueForm.messageQueueFromForm.ipNumber}" />
				  		    			</c:otherwise>
				  		    		</c:choose>
				  		    	</td>
				  		    </tr>

				  		    <tr>
				  		    	<td class="thnormal" align="right" width="20%">Service Name:&nbsp;</td>
				  		    	<c:if test="${showOld}">
					  		    	<td class="datacell">
					  		    		<c:out value="${MessageQueueForm.messageQueueFromDatabase.serviceName}" />
					  		    	</td>
				  		    	</c:if>
				  		    	<td class="datacell">
				  		    		<c:choose>
				  		    			<c:when test="${inEditMode}">
													<html-el:text property="messageQueueFromForm.serviceName" size="35" maxlength="50" />
													&nbsp;
													<bean-el:message key="messagequeue.help.serviceName"/>
				  		    			</c:when>
				  		    			<c:otherwise>
				  		    				<c:out value="${MessageQueueForm.messageQueueFromForm.serviceName}" />
				  		    			</c:otherwise>
				  		    		</c:choose>
				  		    	</td>
				  		    </tr>

				  		    <tr>
				  		    	<td class="thnormal" align="right" width="20%">Service Namespace:&nbsp;</td>
				  		    	<c:if test="${showOld}">
					  		    	<td class="datacell">
					  		    		<c:out value="${MessageQueueForm.messageQueueFromDatabase.serviceNamespace}" />
					  		    	</td>
				  		    	</c:if>
				  		    	<td class="datacell">
				  		    		<c:choose>
				  		    			<c:when test="${inEditMode}">
													<html-el:text property="messageQueueFromForm.serviceNamespace" size="35" maxlength="50" />
													&nbsp;
													<bean-el:message key="messagequeue.help.serviceNamespace"/>
				  		    			</c:when>
				  		    			<c:otherwise>
				  		    				<c:out value="${MessageQueueForm.messageQueueFromForm.serviceNamespace}" />
				  		    			</c:otherwise>
				  		    		</c:choose>
				  		    	</td>
				  		    </tr>

				  		    <tr>
				  		    	<td class="thnormal" align="right" width="20%">Method Name:&nbsp;</td>
				  		    	<c:if test="${showOld}">
					  		    	<td class="datacell">
					  		    		<c:out value="${MessageQueueForm.messageQueueFromDatabase.methodName}" />
					  		    	</td>
				  		    	</c:if>
				  		    	<td class="datacell">
				  		    		<c:choose>
				  		    			<c:when test="${inEditMode}">
													<html-el:text property="messageQueueFromForm.methodName" size="35" maxlength="50" />
													&nbsp;
													<bean-el:message key="messagequeue.help.methodName"/>
				  		    			</c:when>
				  		    			<c:otherwise>
				  		    				<c:out value="${MessageQueueForm.messageQueueFromForm.methodName}" />
				  		    			</c:otherwise>
				  		    		</c:choose>
				  		    	</td>
				  		    </tr>

				  		     <tr>
				  		    	<td class="thnormal" align="right" width="20%">App Specific Value 1:&nbsp;</td>
				  		    	<c:if test="${showOld}">
					  		    	<td class="datacell">
					  		    		<c:out value="${MessageQueueForm.messageQueueFromDatabase.value1}" default="&nbsp;" />
					  		    	</td>
				  		    	</c:if>
				  		    	<td class="datacell">
				  		    		<c:choose>
				  		    			<c:when test="${inEditMode}">
													<html-el:text property="messageQueueFromForm.value1" size="40" maxlength="15"/>
													&nbsp;
				  		    			</c:when>
				  		    			<c:otherwise>
				  		    				<c:out value="${MessageQueueForm.messageQueueFromForm.value1}" default="&nbsp;" />
				  		    			</c:otherwise>
				  		    		</c:choose>
				  		    	</td>
				  		    </tr>
				  		     <tr>
				  		    	<td class="thnormal" align="right" width="20%">App Specific Value 2:&nbsp;</td>
				  		    	<c:if test="${showOld}">
					  		    	<td class="datacell">
					  		    		<c:out value="${MessageQueueForm.messageQueueFromDatabase.value2}" default="&nbsp;" />
					  		    	</td>
				  		    	</c:if>
				  		    	<td class="datacell">
				  		    		<c:choose>
				  		    			<c:when test="${inEditMode}">
													<html-el:text property="messageQueueFromForm.value2" size="40" maxlength="15"/>
													&nbsp;
				  		    			</c:when>
				  		    			<c:otherwise>
				  		    				<c:out value="${MessageQueueForm.messageQueueFromForm.value2}" default="&nbsp;" />
				  		    			</c:otherwise>
				  		    		</c:choose>
				  		    	</td>
				  		    </tr>


		              <tr>
				            <td class="thnormal" colspan="3" align="center">
  	                  <c:choose>
		  	                <c:when test="${inEditMode}">
		                      <a href="javascript: submitQueueId('save', '<c:out value="${MessageQueueForm.messageQueueFromForm.routeQueueId}" />')">Save Changes</a><br>
		                      <a href="javascript: submitQueueId('saveAndResubmit', '<c:out value="${MessageQueueForm.messageQueueFromForm.routeQueueId}" />')">Save Changes and Resubmit</a><br>
		                      <a href="javascript: submitQueueId('saveAndForward', '<c:out value="${MessageQueueForm.messageQueueFromForm.routeQueueId}" />')">Save and Forward</a>&nbsp;&nbsp;
		                        <c:set var="ipAddresses" value="${MessageQueueForm.ipAddresses}" scope="request" />
		                        <html-el:select property="ipAddress">
								  <html-el:options collection="ipAddresses" property="value" labelProperty="label"/>
								</html-el:select>
								<br>
		                      <a href="javascript: submitQueueId('delete', '<c:out value="${MessageQueueForm.messageQueueFromForm.routeQueueId}" />')">Delete</a><br>
		                      <a href="javascript: submitQueueId('reset', '<c:out value="${MessageQueueForm.messageQueueFromForm.routeQueueId}" />')">Reset</a><br>
				                </c:when>
				                <c:otherwise>
		                      <a href="javascript: setMethod('queueNewMessage');document.forms[0].submit();">Queue new message</a><br>
				                </c:otherwise>
		                  </c:choose>
		                  <%-- 
	 	                  <a href="javascript: submitQueueId('clear', '<c:out value="${MessageQueueForm.messageQueueFromForm.routeQueueId}" />')">Clear Message</a>
	 	                  --%>
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

    <jsp:include page="../Footer.jsp"/>

  </body>
</html>
