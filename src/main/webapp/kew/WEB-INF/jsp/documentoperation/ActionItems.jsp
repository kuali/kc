<%--
 Copyright 2008-2009 The Kuali Foundation
 
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
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t" align="center">
	  <tr>
		<td class="thnormal" colspan="2" align="center" height="30"><strong>Action Items</strong></td>
	  </tr>
	  <c:choose> 	
	  <c:when test="${empty DocumentOperationForm.routeHeader.actionItems}">
	    <tr><td class="datacell" colspan="2" align="center" height="15">None</td></tr>
	  </c:when>	  		 
	  <c:otherwise>
      <logic-el:iterate id="actionItem" name="DocumentOperationForm" property="routeHeader.actionItems" indexId="ctr">
      <html-el:hidden property="routeHeader.docActionItem[${ctr}].actionItemId" />  
      <html-el:hidden property="routeHeader.docActionItem[${ctr}].lockVerNbr" />  
 	  <tr>
	    <td width="33%" class="headercell3-b-l" align="right"><b> Action Item ID: </b><c:out value="${actionItem.actionItemId}" /> </td>
	    <td width="66%" class="headercell3-b-l">
	      <html-el:radio property="actionItemOp[${ctr}].value" value="update"/>Update &nbsp;&nbsp;<html-el:radio property="actionItemOp[${ctr}].value" value="delete"/>Delete&nbsp;&nbsp;<html-el:radio property="actionItemOp[${ctr}].value" value="noop"/>No Operation&nbsp;&nbsp;
	      <html-el:hidden property="actionItemOp[${ctr}].index" />
	    </td>
	  </tr>	 
	  <tr>
  	    <td width="33%" align="right" class="thnormal">* Document ID:</td>
  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionItem[${ctr}].routeHeaderId" /></td>
  	  </tr> 
  	  <tr>
  	    <td width="33%" align="right" class="thnormal">* Doc Type Name:</td>
  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionItem[${ctr}].docName" />
			<kul:lookup boClassName="org.kuali.rice.kew.doctype.bo.DocumentType" fieldConversions="name:routeHeader.docActionItem[${ctr}].docName" lookupParameters="routeHeader.docActionItem[${ctr}].docName:name" />
  	    </td>
  	  </tr> 
  	   <tr>
  	    <td width="33%" align="right" class="thnormal">* Doc Type Label:</td>
  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionItem[${ctr}].docLabel" /></td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align="right" class="thnormal">* Doc Handler URL:</td>
  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionItem[${ctr}].docHandlerURL" /></td>
  	  </tr>
   	  <tr>
  	    <td width="33%" align="right" class="thnormal">* Date Assigned:</td>
  	    <td width="66%" class="datacell"><%-- <html-el:text property="routeHeader.docActionItem[${ctr}].dateAssignedString" />&nbsp;--%>
  	      <input type='text' name='actionItemDateAssigned<c:out value="${ctr}"/>' value='<c:out value="${actionItem.dateAssignedString}"/>'>
	  	  <a href="javascript:addCalendar('actionItemDateAssigned<c:out value="${ctr}"/>', 'Select Date', 'actionItemDateAssigned<c:out value="${ctr}"/>', 'DocumentOperationForm'); showCal('actionItemDateAssigned<c:out value="${ctr}"/>');"><img src="images/cal.gif" width="16" height="16" align="absmiddle" alt="Click Here to select a date"></a>
  	      <html-el:hidden property="routeHeader.docActionItem[${ctr}].dateAssignedString" />
  	    </td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align="right" class="thnormal">* Action Request ID:</td>
  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionItem[${ctr}].actionRequestId" /></td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align="right" class="thnormal">* Action Requested:</td>
  	    <td width="66%" class="datacell"><%-- <html-el:text property="routeHeader.docActionItem[${ctr}].actionRequestCd" />--%>
  	      <html-el:select property="routeHeader.docActionItem[${ctr}].actionRequestCd" value="${actionItem.actionRequestCd}"> 
		    <c:set var="actionRequestCds" value="${DocumentOperationForm.actionRequestCds}"/>
    		<html-el:options collection="actionRequestCds" property="key" labelProperty="value"/>
  		  </html-el:select>   
  	    </td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align="right" class="thnormal">* Responsibility ID:</td>
  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionItem[${ctr}].responsibilityId" /></td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align="right" class="thnormal">* Person ID:</td>
  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionItem[${ctr}].principalId" />
  	      <kul:lookup boClassName="org.kuali.rice.kim.bo.Person" fieldConversions="principalId:routeHeader.docActionItem[${ctr}].principalId" lookupParameters="routeHeader.docActionItem[${ctr}].principalId:principalId" />
  	    </td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align="right" class="thnormal">Workgroup ID:</td>
  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionItem[${ctr}].groupId" />
  	      <kul:lookup boClassName="org.kuali.rice.kim.bo.Group" fieldConversions="groupId:routeHeader.docActionItem[${ctr}].groupId" lookupParameters="routeHeader.docActionItem[${ctr}].groupId:groupId" />
  	    </td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align="right" class="thnormal">Role Name:</td>
  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionItem[${ctr}].roleName" />
  	      <kul:lookup boClassName="org.kuali.rice.kim.bo.Role" fieldConversions="roleName:routeHeader.docActionItem[${ctr}].roleName" lookupParameters="routeHeader.docActionItem[${ctr}].roleName:roleName" />
  	    </td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align="right" class="thnormal">Delegator Person ID: </td>
  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionItem[${ctr}].delegatorWorkflowId" />
  	      <kul:lookup boClassName="org.kuali.rice.kim.bo.Person" fieldConversions="principalId:routeHeader.docActionItem[${ctr}].delegatorWorkflowId" lookupParameters="routeHeader.docActionItem[${ctr}].delegatorWorkflowId:principalId" />
  	    </td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align="right" class="thnormal">Delegator Workgroup ID:</td>
  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionItem[${ctr}].delegatorGroupId" />
  	      <kul:lookup boClassName="org.kuali.rice.kim.bo.Group" fieldConversions="groupId:routeHeader.docActionItem[${ctr}].delegatorGroupId" lookupParameters="routeHeader.docActionItem[${ctr}].delegatorGroupId:groupId" />
  	    </td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align="right" class="thnormal">Document Title:</td>
  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionItem[${ctr}].docTitle" /></td>
  	  </tr>
  	  </logic-el:iterate>
	  </c:otherwise>
      </c:choose>
    </table>
  </td>
  <td width="20" height="30">&nbsp;</td>
</tr>
