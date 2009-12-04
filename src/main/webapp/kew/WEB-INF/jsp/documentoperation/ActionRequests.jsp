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
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t" align="center">
	  <tr>
		<td class="thnormal" colspan="2" align="center" height="30"><strong>Action Requests</strong></td>
	  </tr>
	  
	  <c:choose> 
	   <c:when test="${empty DocumentOperationForm.routeHeader.actionRequests}">
	     <tr><td class="datacell" colspan="2" align="center" height="15">None</td></tr>
	   </c:when>
	   <c:otherwise>
	 	<logic-el:iterate id="actionRequest" name="DocumentOperationForm" property="routeHeader.actionRequests" indexId="ctr">
	      <html-el:hidden property="routeHeader.docActionRequest[${ctr}].actionRequestId" />  
	      <html-el:hidden property="routeHeader.docActionRequest[${ctr}].jrfVerNbr" />  
	 	  <tr>
		    <td width="33%" class="headercell3-b-l" align="right"><b> Action Request ID: </b><c:out value="${actionRequest.actionRequestId}" /> </td>
		    <td width="66%" class="headercell3-b-l">
		      <html-el:radio property="actionRequestOp[${ctr}].value" value="update" />Update &nbsp;&nbsp;<html-el:radio property="actionRequestOp[${ctr}].value" value="delete"/>Delete&nbsp;&nbsp;<html-el:radio property="actionRequestOp[${ctr}].value" value="noop"/>No Operation&nbsp;&nbsp;
		      <html-el:hidden property="actionRequestOp[${ctr}].index" />
		    </td>
		  </tr>
		  <tr>
		    <td width="33%" align="right" class="thnormal">* Document Version:</td>
		    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionRequest[${ctr}].docVersion" /></td>
		  </tr>
		  <tr>
	  	    <td width="33%" align="right" class="thnormal">* Document ID:</td>
	  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionRequest[${ctr}].routeHeaderId" /></td>
		  </tr>	    
		  <tr>
		  	<td width="33%" align="right" class="thnormal">* Route Node Instance ID:</td>
	  	    <td width="66%" class="datacell">
	  	    <c:choose>
	  	      <c:when test="${actionRequest.nodeInstance==null}">
	  	        None
	  	      </c:when>
	  	      <c:otherwise>
	  	      	<html-el:text property="routeHeader.docActionRequest[${ctr}].nodeInstance.routeNodeInstanceId" />
	  	      </c:otherwise>
	  	    </c:choose>  	      
	  	    </td>
		  </tr>
		  <tr>
	  	    <td width="33%" align="right" class="thnormal">* Action Requested:</td>
	  	    <td width="66%" class="datacell">
	  	      <html-el:select property="routeHeader.docActionRequest[${ctr}].actionRequested" value="${actionRequest.actionRequested}"> 
    		    <c:set var="actionRequestCds" value="${DocumentOperationForm.actionRequestCds}"/>
    		    <html-el:options collection="actionRequestCds" property="key" labelProperty="value"/>
  			  </html-el:select>    
	  	    </td>
	  	  </tr>  
	  	  <tr>
	  	    <td width="33%" align="right" class="thnormal">* Create Date: </td>
	  	    <td width="66%" class="datacell"><%-- <html-el:text property="routeHeader.docActionRequest[${ctr}].createDateString" />&nbsp; --%>
	  	     <input type='text' name='actionRequestCreateDate<c:out value="${ctr}"/>' value='<c:out value="${actionRequest.createDateString}"/>'>
	  	      <a href="javascript:addCalendar('actionRequestCreateDate<c:out value="${ctr}"/>', 'Select Date', 'actionRequestCreateDate<c:out value="${ctr}"/>', 'DocumentOperationForm'); showCal('actionRequestCreateDate<c:out value="${ctr}"/>');"><img src="images/cal.gif" width="16" height="16" align="absmiddle" alt="Click Here to select a date"></a>
	  	      <html-el:hidden property="routeHeader.docActionRequest[${ctr}].createDateString" />
	  	   </td>
	  	  </tr>   	  
	  	  <tr>
	  	    <td width="33%" align="right" class="thnormal">* Status:</td>
	  	    <td width="66%" class="datacell">
	  	      <html-el:select property="routeHeader.docActionRequest[${ctr}].status" value="${actionRequest.status}"> 
    		    <c:set var="actionRequestStatuses" value="${DocumentOperationForm.actionRequestStatuses}"/>
    		    <html-el:options collection="actionRequestStatuses" property="key" labelProperty="value"/>
  			  </html-el:select>    
	  	    </td>
	  	  </tr>
	  	  <tr>
	  	    <td width="33%" align="right" class="thnormal">* Priority:</td>
	  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionRequest[${ctr}].priority" size="60" /></td>
	  	  </tr>
	  	  <%--
	     <tr>
	  	    <td width="33%" align="right" class="thnormal">* Route Method Name:</td>
	  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionRequest[${ctr}].routeMethodName" /><br>
		       &nbsp;&nbsp;&nbsp;&nbsp;Route Module&nbsp; 
		       <c:set var="routeModules" value="${DocumentOperationForm.routeModules}" scope="request" />
               <html-el:select property="routeModuleName" onchange="document.forms[0].elements['routeHeader.docActionRequest[${ctr}].routeMethodName'].value=this.value" >
                 <html-el:options collection="routeModules" labelProperty="value" property="key" filter="false"/>
               </html-el:select><br>	                
               &nbsp;&nbsp;&nbsp;&nbsp;Rule Template&nbsp;<html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" onclick="javascript:configureLookup('RuleTemplateLookupableImplService', 'ActionRequest', 'routeMethodName', '${ctr}');" />		                
  	        </td>
		  </tr>--%>
		  <tr>
		    <td width="33%" align="right" class="thnormal">* Route Level:</td>
		    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionRequest[${ctr}].routeLevel" /></td>
	      </tr>
	      <tr>
	  	    <td width="33%" align="right" class="thnormal">* Responsibility ID:</td>
	  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionRequest[${ctr}].responsibilityId" /></td>
	  	  </tr>
	  	  <tr>
	  	    <td width="33%" align="right" class="thnormal">Responsibility Description: </td>
	  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionRequest[${ctr}].responsibilityDesc" /></td>
	  	  </tr>
	  	  <tr>
	  	    <td width="33%" align="right" class="thnormal">Action Request Parent ID:</td>
	  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionRequest[${ctr}].parentActionRequestId" /></td>
		  </tr>
	  	  <tr>
	  	    <td width="33%" align="right" class="thnormal">Recipient Type:</td>
	  	    <td width="66%" class="datacell">
	  	      <html-el:select property="routeHeader.docActionRequest[${ctr}].recipientTypeCd" value="${actionRequest.recipientTypeCd}"> 
    		    <c:set var="actionRequestRecipientTypes" value="${DocumentOperationForm.actionRequestRecipientTypes}"/>
    		    <html-el:options collection="actionRequestRecipientTypes" property="key" labelProperty="value"/>
  			  </html-el:select> 
	  	    <%-- <html-el:text property="routeHeader.docActionRequest[${ctr}].recipientTypeCd" />--%>
	  	    
	  	    </td>
	  	  </tr>
	  	  <tr>
	  	    <td width="33%" align="right" class="thnormal">Person ID:</td>
	  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionRequest[${ctr}].principalId" />
	  	      <kul:lookup boClassName="org.kuali.rice.kim.bo.Person" fieldConversions="principalId:routeHeader.docActionRequest[${ctr}].principalId" lookupParameters="routeHeader.docActionRequest[${ctr}].principalId:principalId" />
	  	    </td>
	  	  </tr>
	  	  <tr>
	  	    <td width="33%" align="right" class="thnormal">Workgroup ID:</td>
	  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionRequest[${ctr}].groupId" />
	  	      <kul:lookup boClassName="org.kuali.rice.kim.bo.Group" fieldConversions="groupId:routeHeader.docActionRequest[${ctr}].groupId" lookupParameters="routeHeader.docActionRequest[${ctr}].groupId:groupId" />
	  	    </td>
		  </tr>
	  	  <tr>
	  	    <td width="33%" align="right" class="thnormal">Role Name:</td>
 	  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionRequest[${ctr}].roleName" />
			  <kul:lookup boClassName="org.kuali.rice.kim.bo.Role" fieldConversions="roleName:routeHeader.docActionRequest[${ctr}].roleName" lookupParameters="routeHeader.docActionRequest[${ctr}].roleName:roleName" />	  	    
	  	    </td>
	  	  </tr>
	  	  <tr>
	  	    <td width="33%" align="right" class="thnormal">Qualified Role Name:</td>
	  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionRequest[${ctr}].qualifiedRoleName" /></td>
	  	  </tr>
	  	  <tr>
	  	    <td width="33%" align="right" class="thnormal">Qualified Role Label:</td>
	  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionRequest[${ctr}].qualifiedRoleNameLabel" /></td>
	  	  </tr>	  
		  <tr>
		    <td width="33%" align="right" class="thnormal">Action Taken ID:</td>
		    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionRequest[${ctr}].actionTakenId" /></td>
		  </tr>
		  <tr>
		    <td width="33%" align="right" class="thnormal">Force Action: </td>
		    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionRequest[${ctr}].forceAction" /></td>
		  </tr>
		  <tr>
		    <td width="33%" align="right" class="thnormal">Approve Policy: </td>
		    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionRequest[${ctr}].approvePolicy" /></td>
		  </tr>
		  <tr>
		    <td width="33%" align="right" class="thnormal">Delegation Type: </td>
		    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionRequest[${ctr}].delegationType" /></td>
		  </tr>     
		  <tr>
		    <td width="33%" align="right" class="thnormal">Current Indicator: </td>
		    <td width="66%" class="datacell"><html-el:text property="routeHeader.docActionRequest[${ctr}].currentIndicator" /></td>
		  </tr>
		  <tr>
		    <td width="33%" align="right" class="thnormal">Annotation: </td>
		    <td width="66%" class="datacell"><html-el:textarea cols="120" rows="1" property="routeHeader.docActionRequest[${ctr}].annotation" /></td>
		  </tr> 
		  
		</logic-el:iterate>
	
		  </c:otherwise>
		 </c:choose> 
	  </table>
    </td>
    <td width="20" height="30">&nbsp;</td>
  </tr>
