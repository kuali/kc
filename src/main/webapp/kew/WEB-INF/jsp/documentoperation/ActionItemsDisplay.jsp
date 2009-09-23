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
		<td class="thnormal" colspan=2 align=center height=30><strong>Action Items</strong></td>
	  </tr>
	  <c:choose> 	
	  <c:when test="${empty DocumentOperationForm.routeHeader.actionItems}">
	    <tr><td class="datacell" colspan=2 align=center height=15>None</td></tr>
	  </c:when>	  		 
	  <c:otherwise> 
      <c:forEach var="actionItem" items="${DocumentOperationForm.routeHeader.actionItems}">
 	  <tr>
	    <td width="33%" class="headercell3-b-l" align=right><b> Action Item ID: </b></td>
	    <td width="66%" class="headercell3-b-l"><c:out value="${actionItem.actionItemId}" /> &nbsp;</td>
	  </tr> 
	  <tr>
	    <td width="33%" class="thnormal" align=right> Document ID: </td>
	    <td width="66%" class="datacell"><c:out value="${actionItem.routeHeaderId}" />&nbsp;</td>
	  </tr>
	  <tr>
  	    <td width="33%" align=right class="thnormal">Doc Type Name:</td>
  	    <td width="66%" class="datacell"><c:out value="${actionItem.docName}" />&nbsp;</td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Doc Type Label:</td>
  	    <td width="66%" class="datacell"><c:out value="${actionItem.docLabel}" />&nbsp;</td>
  	  </tr>
  	   <tr>
  	    <td width="33%" align=right class="thnormal">Doc Handler URL:</td>
  	    <td width="66%" class="datacell"><c:out value="${actionItem.docHandlerURL}" />&nbsp;</td>
  	  </tr>
   	  <tr>
  	    <td width="33%" align=right class="thnormal">Date Assigned:</td>
  	    <td width="66%" class="datacell"><c:out value="${actionItem.dateAssignedString}" />&nbsp;</td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Action Request ID:</td>
  	    <td width="66%" class="datacell"><c:out value="${actionItem.actionRequestId}" />&nbsp;</td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Action Requested:</td>
  	    <td width="66%" class="datacell"><c:out value="${actionItem.actionRequestCd}" />&nbsp;</td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Responsibility ID:</td>
  	    <td width="66%" class="datacell"><c:out value="${actionItem.responsibilityId}" />&nbsp;</td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Person ID:</td>
  	    <td width="66%" class="datacell"><c:out value="${actionItem.principalId}" />&nbsp;</td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Workgroup ID:</td>
  	    <td width="66%" class="datacell"><c:out value="${actionItem.workgroupId}" />&nbsp;</td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Role Name:</td>
  	    <td width="66%" class="datacell"><c:out value="${actionItem.roleName}" />&nbsp;</td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Delegator Person ID: </td>
  	    <td width="66%" class="datacell"><c:out value="${actionItem.delegatorWorkflowId}" />&nbsp;</td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Delegator Workgroup ID:</td>
  	    <td width="66%" class="datacell"><c:out value="${actionItem.delegatorGroupId}" />&nbsp;</td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Document Title:</td>
  	    <td width="66%" class="datacell"><c:out value="${actionItem.docTitle}" />&nbsp;</td>
  	  </tr>
      </c:forEach>
	  </c:otherwise>
      </c:choose>
    </table>
  </td>
  <td width="20" height="30">&nbsp;</td>
</tr>

