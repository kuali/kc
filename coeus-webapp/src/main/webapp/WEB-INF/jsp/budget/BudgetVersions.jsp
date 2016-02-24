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
<c:set var="viewOnly" value="${KualiForm.editingMode['viewOnly']}" scope="request" />
<c:if test="${KualiForm.editingMode['modifyCompletedBudgets']}">
	<c:set target="${KualiForm.documentActions}" property="canSave" value="true"/>
</c:if> 
<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="${KualiForm.actionPrefix}Versions"
	documentTypeName="${KualiForm.docTypeName}"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="versions"
  	extraTopButtons="${KualiForm.extraTopButtons}"
  	>
 
        	<div align="right"><kul:help parameterNamespace="KC-AB" parameterDetailType="Document" parameterName="awardBudgetVersionsHelpUrl" altText="help"/></div>

	<kra-b:budgetVersions 
		budgetDocumentVersions="${KualiForm.document.budget.budgetParent.budgets}"
		pathToVersions="document.budget.budgetParent.budgets"
		errorKey="document.parentDocument.budgetDocumentVersion*,document.parentDocument.budgetParent.finalVersionFlag,document.parentDocument.documentDescription"
		requestedStartDateInitial="${KualiForm.document.budget.budgetParent.requestedStartDateInitial}"
		requestedEndDateInitial="${KualiForm.document.budget.budgetParent.requestedEndDateInitial}"
		/>

	<kul:documentControls 
		transactionalDocument="false"
		suppressRoutingControls="true"
		extraButtonSource="${extraButtonSource}"
		extraButtonProperty="${extraButtonProperty}"
		extraButtonAlt="${extraButtonAlt}"
		viewOnly="${viewOnly}"
		suppressCancelButton="true"
		/>

</kul:documentPage>
