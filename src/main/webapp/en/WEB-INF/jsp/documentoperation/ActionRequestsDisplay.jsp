<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

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
	  	    <td width="66%" class="datacell"><c:out value="${actionRequest.workflowId}" />&nbsp;</td>
	  	  </tr>
	  	  <tr>
	  	    <td width="33%" align=right class="thnormal">Workgroup ID:</td>
	  	    <td width="66%" class="datacell"><c:out value="${actionRequest.workgroupId}" />&nbsp;</td>
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
		    <td width="33%" align=right class="thnormal">Ignore Previous Action: </td>
		    <td width="66%" class="datacell"><c:out value="${actionRequest.ignorePrevAction}" />&nbsp;</td>
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




