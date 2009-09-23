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
		<td class="thnormal" colspan=2 align=center height=30><strong>Actions Taken</strong></td>
	  </tr>
	  	  
	  <c:choose> 	
	  <c:when test="${empty DocumentOperationForm.routeHeader.actionsTaken}">
	    <tr><td class="datacell" colspan=2 align=center height=15>None</td></tr>
	  </c:when>	  		 
	  <c:otherwise>
	  <c:forEach var="actionTaken" items="${DocumentOperationForm.routeHeader.actionsTaken}">
 	  <tr>
		<td width="33%" class="headercell3-b-l" align=right><b>Action Taken ID:</b> </td>
	    <td width="66%" class="headercell3-b-l"><c:out value="${actionTaken.actionTakenId}" /> &nbsp;</td>
	  </tr>	 
	  <tr>
  	    <td width="33%" align=right class="thnormal">Document ID:</td>
  	    <td width="66%" class="datacell"><c:out value="${actionTaken.routeHeaderId}" />&nbsp;</td>
  	  </tr> 
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Document Version:</td>
  	    <td width="66%" class="datacell"><c:out value="${actionTaken.docVersion}" />&nbsp;</td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Action Taken:</td>
  	    <td width="66%" class="datacell"><c:out value="${actionTaken.actionTakenLabel}" />&nbsp;</td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Action Date:</td>
  	    <td width="66%" class="datacell"><c:out value="${actionTaken.actionDateString}" />&nbsp;</td>
  	  </tr>
   	  <tr>
  	    <td width="33%" align=right class="thnormal">Action Taken Person ID:</td>
  	    <td width="66%" class="datacell"><c:out value="${actionTaken.principalId}" />&nbsp;</td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Delegator Person ID:</td>
  	    <td width="66%" class="datacell"><c:out value="${actionTaken.delegatorWorkflowId}" />&nbsp;</td>
  	  </tr>
	  <tr>
  	    <td width="33%" align=right class="thnormal">Delegator Workgroup ID:</td>
  	    <td width="66%" class="datacell"><c:out value="${actionTaken.delegatorGroupId}" />&nbsp;</td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Current Indicator:</td>
  	    <td width="66%" class="datacell"><c:out value="${actionTaken.currentIndicator}" />&nbsp;</td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Annotation:</td>
  	    <td width="66%" class="datacell"><c:out value="${actionTaken.annotation}" />&nbsp;</td>
  	  </tr>
	  </c:forEach>
	  </c:otherwise>
      </c:choose>
	</table>
  </td>
  <td width="20" height="30">&nbsp;</td>
</tr>




