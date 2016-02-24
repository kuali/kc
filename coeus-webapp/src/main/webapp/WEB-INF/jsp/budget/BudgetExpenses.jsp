<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
   - 
   - This program is free software: you can redistribute it and/or modify
   - it under the terms of the GNU Affero General Public License as
   - published by the Free Software Foundation, either version 3 of the
   - License, or (at your option) any later version.
   - 
   - This program is distributed in the hope that it will be useful,
   - but WITHOUT ANY WARRANTY; without even the implied warranty of
   - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   - GNU Affero General Public License for more details.
   - 
   - You should have received a copy of the GNU Affero General Public License
   - along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<c:set var="extraButtons" value="${KualiForm.extraExpensesButtons}" scope="request"/>

<c:if test="${readOnly}">
	<c:set var="extraButtons" value="" scope="request"/>
</c:if>
<c:set var="readOnly" value="${(not KualiForm.editingMode['modifyBudgets'])}" scope="request" />
<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="${KualiForm.actionPrefix}Expenses"
	documentTypeName="${KualiForm.docTypeName}"
  	headerDispatch="${KualiForm.headerDispatch}"
  	showTabButtons="true"
  	headerTabActive="expenses"
  	extraTopButtons="${KualiForm.extraTopButtons}">
  	
	<div align="right"><kul:help parameterNamespace="KC-AB" parameterDetailType="Document" parameterName="awardBudgetNonpersonnelHelpUrl" altText="help"/></div>

		
	<kra-b:budgetExpenses /> 
	<kul:panelFooter />
		
<kul:documentControls 
		transactionalDocument="true" 
		suppressRoutingControls="true" 
		extraButtons="${extraButtons}"
		viewOnly="${KualiForm.editingMode['viewOnly']}"
		suppressCancelButton="true"
/>	

<SCRIPT type="text/javascript">
var kualiForm = document.forms['KualiForm'];
var kualiElements = kualiForm.elements;
</SCRIPT>
<script language="javascript" src="scripts/kuali_application.js"></script>
<script language="javascript" src="dwr/interface/ObjectCodeToBudgetCategoryCodeService.js"></script>
<script language="javascript" src="dwr/interface/BudgetRatesService.js"></script>

</kul:documentPage>
