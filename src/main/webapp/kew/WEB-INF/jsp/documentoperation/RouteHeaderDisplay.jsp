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
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t" align=center>
     <tr>
		<td class="thnormal" colspan=2 align=center height=30><strong>Document</strong></td>
	  </tr>
	  <tr>
	    <td width="33%" class="headercell3-b-l" align=right><b> Document ID: </b></td>
	    <td width="66%" class="headercell3-b-l"><c:out value="${DocumentOperationForm.routeHeader.routeHeaderId}" /> &nbsp;</td>
	  </tr>
	  <tr>
  	    <td width="33%" align=right class="thnormal">Document Version:</td>
  	    <td width="66%" class="datacell"><c:out value="${DocumentOperationForm.routeHeader.docVersion}" />&nbsp;</td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Initiator ID:</td>
  	    <td width="66%" class="datacell"><c:out value="${DocumentOperationForm.routeHeader.initiatorWorkflowId}" />&nbsp;</td>
  	  </tr>
  	   <tr>
  	    <td width="33%" align=right class="thnormal">Route Status:</td>
  	    <td width="66%" class="datacell"><c:out value="${DocumentOperationForm.routeHeader.docRouteStatusLabel}" />&nbsp;</td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Route Level:</td>
  	    <td width="66%" class="datacell"><c:out value="${DocumentOperationForm.routeHeader.docRouteLevel}" />&nbsp;</td>
  	  </tr>
  	   <tr>
  	    <td width="33%" align=right class="thnormal">Create Date:</td>
  	    <td width="66%" class="datacell"><c:out value="${DocumentOperationForm.createDate}" />&nbsp;</td>
  	  </tr>	  		 
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Document Status Modification Date:</td>
  	    <td width="66%" class="datacell"><c:out value="${DocumentOperationForm.statusModDate}" /></td>
  	  </tr>	
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Approved Date:</td>
  	    <td width="66%" class="datacell"><c:out value="${DocumentOperationForm.approvedDate}" />&nbsp;</td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Finalized Date:</td>
  	    <td width="66%" class="datacell"><c:out value="${DocumentOperationForm.finalizedDate}" />&nbsp;</td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Route Status Modification Date:</td>
  	    <td width="66%" class="datacell"><c:out value="${DocumentOperationForm.routeStatusDate}" />&nbsp;</td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Route Level Modification Date:</td>
  	    <td width="66%" class="datacell"><c:out value="${DocumentOperationForm.routeLevelDate}" />&nbsp;</td>
  	  </tr>
	  <tr>
  	    <td width="33%" align=right class="thnormal">Doc Type ID:</td>
  	    <td width="66%" class="datacell"><c:out value="${DocumentOperationForm.routeHeader.documentTypeId}" />&nbsp;</td>
	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Doc Title:</td>
  	    <td width="66%" class="datacell"><c:out value="${DocumentOperationForm.routeHeader.docTitle}"/>&nbsp;</td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Application Doc ID:</td>
  	    <td width="66%" class="datacell"><c:out value="${DocumentOperationForm.routeHeader.appDocId}" />&nbsp;</td>
  	  </tr>
  	  <!--tr>
  	    <td width="33%" align=right class="thnormal">Override Indicator:</td>
  	    <td width="66%" class="datacell"><c:out value="${DocumentOperationForm.routeHeader.overrideInd}" />&nbsp;</td>
  	  </tr>
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Lock Code:</td>
  	    <td width="66%" class="datacell"><c:out value="${DocumentOperationForm.routeHeader.lockCode}" />&nbsp;</td>
  	  </tr-->
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Doc Content:</td>
  	    <td width="66%" class="datacell"><c:out value="${DocumentOperationForm.routeHeader.docContent}" />&nbsp;</td>
  	  </tr>
	</table>
  </td>
  <td width="20" height="30">&nbsp;</td>
</tr>
