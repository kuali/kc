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

<html-el:hidden property="routeHeader.routeHeaderId" />
<html-el:hidden property="routeHeader.versionNumber" />
<html-el:hidden property="lookupableImplServiceName" />
<tr>
  <td><img src="images/pixel_clear.gif" alt="" width="20" height="20"></td>
  <td> 
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t" align="center">
	  <tr>
		<td class="thnormal" colspan="2" align="center" height="30"><strong>Document</strong></td>
	  </tr>
	 	  <tr>
		    <td width="33%" class="headercell3-b-l" align="right"><b> Document ID: </b><c:out value="${DocumentOperationForm.routeHeader.routeHeaderId}" /> </td>
		    <td width="66%" class="headercell3-b-l"><html-el:radio property="routeHeaderOp" value="update"/>Update &nbsp;&nbsp;<html-el:radio property="routeHeaderOp" value="noop"/>No Operation&nbsp;&nbsp;</td>
		  </tr>
		  <tr>
	  	    <td width="33%" align="right" class="thnormal">* Document Version:</td>
	  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.docVersion"/></td>
	  	  </tr>
		  <tr>
	  	    <td width="33%" align="right" class="thnormal">* Initiator ID:</td>
	  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.initiatorWorkflowId"/>
	  	      <kul:lookup boClassName="org.kuali.rice.kim.bo.Person" fieldConversions="principalId:routeHeader.initiatorWorkflowId" lookupParameters="routeHeader.initiatorWorkflowId:principalId" />
	  	      <%-- document.forms[0].elements['lookupableImplServiceName'].value = 'UserLookupableImplService';"/> --%>
	  	    </td>
	  	  </tr>
	  	  <tr>
	  	    <td width="33%" align="right" class="thnormal">* Initial Route Node Instances:</td>
	  	    <td width="66%" class="datacell">
	  	      	<html-el:text property="initialNodeInstances"/>
	  	    </td>
	  	  </tr>
		  <tr>
	  	    <td width="33%" align="right" class="thnormal">* Route Status:</td>
	  	    <td width="66%" class="datacell">
  	    	  <html-el:select property="routeHeader.docRouteStatus" value="${DocumentOperationForm.routeHeader.docRouteStatus}"> 
    		  <c:set var="docStatuses" value="${DocumentOperationForm.docStatuses}"/>
    		  <html-el:options collection="docStatuses" property="key" labelProperty="value"/>
  			  </html-el:select>
	  	    </td>
	  	  </tr>
	  	 <tr>
	  	    <td width="33%" align="right" class="thnormal">* Route Level:</td>
	  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.docRouteLevel"/></td>
	  	  </tr>
	  	  <tr>
	  	    <td width="33%" align="right" class="thnormal">* Create Date:</td>
	  	    <td width="66%" class="datacell"><html-el:text property="createDate" />&nbsp;<a href="javascript:showCal('createDate');"><img src="images/cal.gif" width="16" height="16" align="absmiddle" alt="Click Here to select a date"></a></td>
	  	  </tr>	 
	  	  <tr>
	  	    <td width="33%" align="right" class="thnormal">* Doc Status Modification Date:</td>
	  	    <td width="66%" class="datacell"><html-el:text property="statusModDate" />&nbsp;<a href="javascript:showCal('statusModDate');"><img src="images/cal.gif" width="16" height="16" align="absmiddle" alt="Click Here to select a date"></a></td>
	  	  </tr>	 		 
	  	  <tr>
	  	    <td width="33%" align="right" class="thnormal">Approved Date:</td>
	  	    <td width="66%" class="datacell"><html-el:text  property="approvedDate" />&nbsp;<a href="javascript:showCal('approvedDate');"><img src="images/cal.gif" width="16" height="16" align="absmiddle" alt="Click Here to select a date"></a></td>
	  	  </tr>
	  	  <tr>
	  	    <td width="33%" align="right" class="thnormal">Finalized Date:</td>
	  	    <td width="66%" class="datacell"><html-el:text property="finalizedDate" />&nbsp;<a href="javascript:showCal('finalizedDate');"><img src="images/cal.gif" width="16" height="16" align="absmiddle" alt="Click Here to select a date"></a></td>
	  	  </tr>
	  	  <tr>
	  	    <td width="33%" align="right" class="thnormal">Route Status Modification Date:</td>
	  	    <td width="66%" class="datacell"><html-el:text property="routeStatusDate" />&nbsp;<a href="javascript:showCal('routeStatusDate');"><img src="images/cal.gif" width="16" height="16" align="absmiddle" alt="Click Here to select a date"></a></td>
	  	  </tr>
	  	  <tr>
	  	    <td width="33%" align="right" class="thnormal">Route Level Modification Date:</td>
	  	    <td width="66%" class="datacell"><html-el:text property="routeLevelDate" />&nbsp;<a href="javascript:showCal('routeLevelDate');"><img src="images/cal.gif" width="16" height="16" align="absmiddle" alt="Click Here to select a date"></a></td>
	  	  </tr>
	  	  <tr>
	  	    <td width="33%" align="right" class="thnormal">Doc Type ID:</td>
	  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.documentTypeId" />
	  	    	<kul:lookup boClassName="org.kuali.rice.kew.doctype.bo.DocumentType" fieldConversions="documentTypeId:routeHeader.documentTypeId" lookupParameters="routeHeader.documentTypeId:documentTypeId" />
	  	    </td>
		  </tr>
	  	  <tr>
	  	    <td width="33%" align="right" class="thnormal">Doc Title:</td>
	  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.docTitle"/></td>
	  	  </tr>
	  	  <tr>
	  	    <td width="33%" align="right" class="thnormal">Application Doc ID:</td>
	  	    <td width="66%" class="datacell"><html-el:text property="routeHeader.appDocId"/></td>
	  	  </tr> 	  
	  	  <tr>
	  	    <td width="33%" align="right" class="thnormal">Doc Content:</td>
	  	    <td width="66%" class="datacell"><html-el:textarea cols="120" rows="5" property="routeHeader.docContent"/></td>
	  	  </tr>
	  </table>
    </td>
    <td width="20" height="30">&nbsp;</td>
  </tr>
