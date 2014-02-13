 
 <%--
 Copyright 2005-2013 The Kuali Foundation

 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.osedu.org/licenses/ECL-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="subAwardFinancial"
	documentTypeName="SubAwardDocument"
	renderMultipart="true"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="financial"> 	  	
<c:set var="readOnly" value="${not KualiForm.editingMode['fullEntry']}" scope="request" />
	<div align="right"><kul:help parameterNamespace="KC-SUBAWARD" parameterDetailType="Document" parameterName="subAwardFinancialHelpUrl" altText="help"/></div>

<div id="workarea">
<script language="javascript" src="scripts/kuali_application.js"></script>
<script>
jq(document).ready(function() {
	jq('input[name*="addAmountReleased"]').click(function() { openNewWindow('subAwardFinancial','addAmountReleased','','${KualiForm.formKey}','${KualiForm.document.sessionDocument}'); return false; });
});
</script>
	<kra-sub:subAwardHistoryOfChanges/>	
	<kra-sub:subAwardInvoices/>
	<kul:panelFooter />
</div>
<kul:documentControls transactionalDocument="true" suppressRoutingControls="true" 
	suppressCancelButton="true" viewOnly="${KualiForm.editingMode['viewOnly']}"
	extraButtons="${KualiForm.extraFinancialButtons}"/>

</kul:documentPage>