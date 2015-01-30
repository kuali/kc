<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2015 Kuali, Inc.
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

<link rel="stylesheet" href="css/jquery/new_kuali.css" type="text/css" />

<c:set var="viewOnly" value="${KualiForm.editingMode['viewOnly']}" scope="request" />

<c:if test="${KualiForm.editingMode['modifyCompletedBudgets']}">
	<c:set target="${KualiForm.documentActions}" property="canSave" value="true"/>
</c:if>

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="proposalDevelopmentBudgetVersions"
	documentTypeName="ProposalDevelopmentDocument"
	showTabButtons="true"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="budgetVersions">
  	
  	<div align="right"><kul:help documentTypeName="ProposalDevelopmentDocument" pageName="Budget Versions" /></div>
  	
  	<kra-b:budgetVersions 
  		budgetDocumentVersions="${KualiForm.document.budgetDocumentVersions}" 
  		pathToVersions="document"
  		errorKey="document.developmentProposalList[0].budgetVersion*,document.developmentProposalList[0].finalVersionFlag"
  		requestedStartDateInitial="${KualiForm.document.developmentProposal.requestedStartDateInitial}"
		requestedEndDateInitial="${KualiForm.document.developmentProposal.requestedEndDateInitial}"
		hierarchyParentBudgetIsComplete="${KualiForm.document.developmentProposal.parentProposalComplete}"
		/>
  	
  	<kul:documentControls 
		transactionalDocument="false"
		suppressRoutingControls="true"
		suppressCancelButton="true"
		extraButtonSource="${extraButtonSource}"
		extraButtonProperty="${extraButtonProperty}"
		extraButtonAlt="${extraButtonAlt}"
		viewOnly="${viewOnly}"
		/>
		
</kul:documentPage>
