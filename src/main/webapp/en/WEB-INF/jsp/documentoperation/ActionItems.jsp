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
		<td class="thnormal" colspan=2 align=center height=30><strong>Action Items</strong></td>
	  </tr>
	  <c:choose> 	
	  <c:when test="${empty DocumentOperationForm.routeHeader.actionItems}">
	    <tr><td class="datacell" colspan=2 align=center height=15>None</td></tr>
	  </c:when>	  		 
	  <c:otherwise>
      <logic-el:iterate id="actionItem" name="DocumentOperationForm" property="routeHeader.actionItems" indexId="ctr">
      <html-el:hidden property="routeHeader.docActionItem[${ctr}].actionItemId" />  
      <html-el:hidden property="routeHeader.docActionItem[${ctr}].lockVerNbr" />  
 	  <tr>
	    <td width="33%" class="headercell3-b-l" align=right><b> Action Item ID: </b><c:out value="${actionItem.actionItemId}" /> </td>
	    <td width="66%" class="headercell3-b-l">
	      <html-el:radio property="actionItemOp[${ctr}].value" value="update"/>Update &nbsp;&nbsp;<html-el:radio property="actionItemOp[${ctr}].value" value="delete"/>Delete&nbsp;&nbsp;<html-el:radio property="actionItemOp[${ctr}].value" value="noop"/>No Operation&nbsp;&nbsp;
	      <html-el:hidden property="actionItemOp[${ctr}].index" />
	    </td>
	  </tr>	 
	  <tr>
  	    <td width="33%" align=right class="thnormal">* Document ID:</td>
  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionItem[${ctr}].routeHeaderId" /></td>
  	  </tr> 
  	  <tr>
  	    <td width="33%" align=right class="thnormal">* Doc Type Name:</td>
  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionItem[${ctr}].docName" />
  	      <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" onclick="javascript:configureLookup('DocumentTypeLookupableImplService', 'ActionItem', 'docName', '${ctr}')"/>
  	    </td>
  	  </tr> 
  	   <tr>
  	    <td width="33%" align=right class="thnormal">* Doc Type Label:</td>
  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionItem[${ctr}].docLabel" /></td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">* Doc Handler URL:</td>
  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionItem[${ctr}].docHandlerURL" /></td>
  	  </tr>
   	  <tr>
  	    <td width="33%" align=right class="thnormal">* Date Assigned:</td>
  	    <td width="66%" class="datacell"><%-- <html-el:text property="routeHeader.docActionItem[${ctr}].dateAssignedString" />&nbsp;--%>
  	      <input type='text' name='actionItemDateAssigned<c:out value="${ctr}"/>' value='<c:out value="${actionItem.dateAssignedString}"/>'>
	  	  <a href="javascript:addCalendar('actionItemDateAssigned<c:out value="${ctr}"/>', 'Select Date', 'actionItemDateAssigned<c:out value="${ctr}"/>', 'DocumentOperationForm'); showCal('actionItemDateAssigned<c:out value="${ctr}"/>');"><img src="images/cal.gif" width="16" height="16" align="absmiddle" alt="Click Here to select a date"></a>
  	      <html-el:hidden property="routeHeader.docActionItem[${ctr}].dateAssignedString" />
  	    </td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">* Action Request ID:</td>
  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionItem[${ctr}].actionRequestId" /></td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">* Action Requested:</td>
  	    <td width="66%" class="datacell"><%-- <html-el:text property="routeHeader.docActionItem[${ctr}].actionRequestCd" />--%>
  	      <html-el:select property="routeHeader.docActionItem[${ctr}].actionRequestCd" value="${actionItem.actionRequestCd}"> 
		    <c:set var="actionRequestCds" value="${DocumentOperationForm.actionRequestCds}"/>
    		<html-el:options collection="actionRequestCds" property="key" labelProperty="value"/>
  		  </html-el:select>   
  	    </td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">* Responsibility ID:</td>
  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionItem[${ctr}].responsibilityId" /></td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">* Person ID:</td>
  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionItem[${ctr}].workflowId" />
  	      <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="javascript:configureLookup('UserLookupableImplService', 'ActionItem', 'workflowId', '${ctr}');"/>
  	    </td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Workgroup ID:</td>
  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionItem[${ctr}].workgroupId" />
  	      <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="javascript:configureLookup('WorkGroupLookupableImplService', 'ActionItem', 'workgroupId', '${ctr}');"/>
  	    </td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Role Name:</td>
  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionItem[${ctr}].roleName" />
  	       <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="javascript:configureLookup('RoleLookupableImplService', 'ActionItem', 'roleName', '${ctr}');"/>	  	    
  	    </td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Delegator Person ID: </td>
  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionItem[${ctr}].delegatorWorkflowId" />
  	      <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="javascript:configureLookup('UserLookupableImplService', 'ActionItem', 'delegatorWorkflowId', '${ctr}');"/>
  	    </td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Delegator Workgroup ID:</td>
  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionItem[${ctr}].delegatorWorkgroupId" />
  	      <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="javascript:configureLookup('WorkGroupLookupableImplService', 'ActionItem', 'delegatorWorkgroupId', '${ctr}');"/>
  	    </td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Document Title:</td>
  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionItem[${ctr}].docTitle" /></td>
  	  </tr>
  	  </logic-el:iterate>
	  </c:otherwise>
      </c:choose>
    </table>
  </td>
  <td width="20" height="30">&nbsp;</td>
</tr>

