<%--
 Copyright 2006-2009 The Kuali Foundation

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
<c:set var="extraButtons" value="${KualiForm.ratesExtraButtons}" scope="request"/>
<c:set var="readOnly" value="${not KualiForm.editingMode['modifyBudgets']}" scope="request" />
<c:if test="${readOnly}">
	<c:set var="extraButtons" value="" scope="request"/>
</c:if>

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="budgetRates"
	documentTypeName="BudgetDocument"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="rates"
  	extraTopButtons="${KualiForm.extraTopButtons}"
  	showTabButtons="true">
  	
  	<div align="right"><kul:help documentTypeName="BudgetDocument" pageName="Rates" /></div>

<kra-b:budgetRates /> 

<script language="javascript" src="scripts/kuali_application.js"></script>
<kul:documentControls 
		transactionalDocument="true" 
		suppressRoutingControls="true" 
		extraButtons="${extraButtons}"
		viewOnly="${KualiForm.editingMode['viewOnly']}"
/>
</kul:documentPage>
