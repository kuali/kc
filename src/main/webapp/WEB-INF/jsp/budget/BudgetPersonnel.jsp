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
<c:set var="readOnly" value="${not KualiForm.editingMode['modifyBudgets']}" scope="request" />

<c:if test="${KualiForm.editingMode['modifyBudgets']}">
	<c:set var="extraButtons" value="${KualiForm.extraPersonnelButtons}" scope="request"/>
</c:if>

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="budgetPersonnel"
	documentTypeName="BudgetDocument"
  	headerDispatch="${KualiForm.headerDispatch}"
  	showTabButtons="true" 
  	headerTabActive="personnel"
  	extraTopButtons="${KualiForm.extraTopButtons}">
  	
  	<div align="right"><kul:help documentTypeName="BudgetDocument" pageName="Personnel" /></div>
  	
	<kra-b:budgetExpensesSelectBudgetPeriod />
	<br><br>
	
	<kra-b:projectPersonnel/>
	
	<c:set var="action" value="budgetExpensesAction" />
	<c:set var="budgetCategoryTypeCodeKey" value="${KualiForm.document.budget.budgetCategoryTypeCodes[0].key}" />
	<c:set var="budgetCategoryTypeCodeLabel" value="${KualiForm.document.budget.budgetCategoryTypeCodes[0].label}" />
	<c:set var="catCodes" value="0" />
	
	<kra-b:budgetExpenseBudgetOverview transparentBackground="false" defaultOpen="false" /> 
	<kra-b:budgetPersonnelDetail budgetCategoryTypeCodeKey="${budgetCategoryTypeCodeKey}" budgetCategoryTypeCodeLabel="${budgetCategoryTypeCodeLabel}" catCodes="${catCodes}"/>
	
	<kul:panelFooter />
	
	<kul:documentControls transactionalDocument="true" suppressRoutingControls="true" extraButtons="${extraButtons}"  viewOnly="${KualiForm.editingMode['viewOnly']}" />	
	
	<SCRIPT type="text/javascript">
	var kualiForm = document.forms['KualiForm'];
	var kualiElements = kualiForm.elements;
	</SCRIPT>
	
<script language="javascript" src="scripts/kuali_application.js"></script>	
<script language="javascript" src="dwr/interface/JobCodeService.js"></script>
	
</kul:documentPage>
