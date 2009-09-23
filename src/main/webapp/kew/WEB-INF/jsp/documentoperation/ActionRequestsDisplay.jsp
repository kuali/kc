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
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp" %>
<tr>
  <td><img src="images/pixel_clear.gif" alt="" width="20" height="20"></td>
  <td> 
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t" align=center>
	  <tr>
		<td class="thnormal" colspan=2 align=center height=30><strong>Action Requests</strong></td>
	  </tr>
	  <c:choose> 
	   <c:when test="${empty DocumentOperationForm.routeHeader.actionRequests}">
	     <tr><td class="datacell" colspan=2 align=center height=15>None</td></tr>
	   </c:when>
	   <c:otherwise>
		 <c:forEach var="actionRequest" items="${DocumentOperationForm.routeHeader.actionRequests}">
	 	  <tr>
		    <td width="33%" class="headercell3-b-l" align=right><b> Action Request ID: </b></td>
		    <td width="66%" class="headercell3-b-l"><c:out value="${actionRequest.actionRequestId}" /> &nbsp;</td>
		  </tr>
		  <tr>
		    <td width="33%" align=right class="thnormal">Document Version:</td>
		    <td width="66%" class="datacell"><c:out value="${actionRequest.docVersion}" />&nbsp;</td>
		  </tr>
		  <tr>
		    <td width="33%" class="thnormal" align=right> Document ID: </td>
		    <td width="66%" class="datacell"><c:out value="${actionRequest.routeHeaderId}" />&nbsp;
		  </tr>
		   <tr>
	  	    <td width="33%" align=right class="thnormal">Action Requested:</td>
	  	    <td width="66%" class="datacell"><c:out value="${actionRequest.actionRequestedLabel}" />&nbsp;</td>
	  	  </tr>	
	  	  <tr>
	  	    <td width="33%" align=right class="thnormal">Create Date: </td>
	  	    <td width="66%" class="datacell"><c:out value="${actionRequest.createDateString}" />&nbsp;</td>
	  	  </tr>   
	  	  <tr>
	  	    <td width="33%" align=right class="thnormal">Status:</td>
	  	    <td width="66%" class="datacell"><c:out value="${actionRequest.statusLabel}" />&nbsp;</td>
	  	  </tr>
	  	  <tr>
	  	    <td width="33%" align=right class="thnormal">Priority:</td>
	  	    <td width="66%" class="datacell"><c:out value="${actionRequest.priority}" />&nbsp;</td>
	  	  </tr>
	  	  <%--<tr>
	  	    <td width="33%" align=right class="thnormal">Route Method Name:</td>
	  	    <td width="66%" class="datacell"><c:out value="${actionRequest.routeMethodName}" />&nbsp;</td>
		  </tr>--%>
	      <tr>
		    <td width="33%" align=right class="thnormal">Route Level:</td>
		    <td width="66%" class="datacell"><c:out value="${actionRequest.routeLevel}" />&nbsp;</td>
	      </tr>
	      <tr>
	  	    <td width="33%" align=right class="thnormal">Responsibility ID:</td>
	  	    <td width="66%" class="datacell"><c:out value="${actionRequest.responsibilityId}" />&nbsp;</td>
	  	  </tr>
	  	  <tr>
	  	    <td width="33%" align=right class="thnormal">Responsibility Description: </td>
	  	    <td width="66%" class="datacell"><c:out value="${actionRequest.responsibilityDesc}" />&nbsp;</td>
	  	  </tr>
		  <tr>
	  	    <td width="33%" align=right class="thnormal">Action Request Parent ID:</td>
	  	    <td width="66%" class="datacell"><c:out value="${actionRequest.parentActionRequestId}" />&nbsp;</td>
		  </tr>
	  	  <tr>
	  	    <td width="33%" align=right class="thnormal">Recipient Type:</td>
	  	    <td width="66%" class="datacell"><c:out value="${actionRequest.recipientTypeLabel}" />&nbsp;</td>
	  	  </tr>
	   	  <tr>
	  	    <td width="33%" align=right class="thnormal">Person ID:</td>
	  	    <td width="66%" class="datacell"><c:out value="${actionRequest.principalId}" />&nbsp;</td>
	  	  </tr>
	  	  <tr>
	  	    <td width="33%" align=right class="thnormal">Workgroup ID:</td>
	  	    <td width="66%" class="datacell"><c:out value="${actionRequest.groupId}" />&nbsp;</td>
		  </tr>
	  	  <tr>
	  	    <td width="33%" align=right class="thnormal">Role Name:</td>
	  	    <td width="66%" class="datacell"><c:out value="${actionRequest.roleName}" />&nbsp;</td>
	  	  </tr>
	  	  <tr>
	  	    <td width="33%" align=right class="thnormal">Qualified Role Name:</td>
	  	    <td width="66%" class="datacell"><c:out value="${actionRequest.qualifiedRoleName}" />&nbsp;</td>
	  	  </tr>
	  	  <tr>
	  	    <td width="33%" align=right class="thnormal">Qualified Role Label:</td>
	  	    <td width="66%" class="datacell"><c:out value="${actionRequest.qualifiedRoleNameLabel}" />&nbsp;</td>
	  	  </tr>	
		  <tr>
		    <td width="33%" align=right class="thnormal">Action Taken ID:</td>
		    <td width="66%" class="datacell"><c:out value="${actionRequest.actionTakenId}" />&nbsp;</td>
		  </tr>
		  <tr>
		    <td width="33%" align=right class="thnormal">Force Action: </td>
		    <td width="66%" class="datacell"><c:out value="${actionRequest.forceAction}" />&nbsp;</td>
		  </tr>
		  <tr>
		    <td width="33%" align=right class="thnormal">Approve Policy: </td>
		    <td width="66%" class="datacell"><c:out value="${actionRequest.approvePolicy}" />&nbsp;</td>
		  </tr>
		  <tr>
		    <td width="33%" align=right class="thnormal">Delegation Type: </td>
		    <td width="66%" class="datacell"><c:out value="${actionRequest.delegationType}" />&nbsp;</td>
		  </tr>     
		  <tr>
		    <td width="33%" align=right class="thnormal">Current Indicator: </td>
		    <td width="66%" class="datacell"><c:out value="${actionRequest.currentIndicator}" />&nbsp;</td>
		  </tr>  
		  
		  <tr>
		    <td width="33%" align=right class="thnormal">Annotation: </td>
		    <td width="66%" class="datacell"><c:out value="${actionRequest.annotation}" />&nbsp;</td>
		  </tr> 
		 </c:forEach>
	   </c:otherwise>
	  </c:choose>
	  </table>
    </td>
    <td width="20" height="30">&nbsp;</td>
  </tr>




