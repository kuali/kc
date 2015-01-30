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

<c:set var="readOnly" value="${(not KualiForm.editingMode['modifyBudgets']) && ( not parentReadOnlyFlag )}" scope="request" />
<c:set var="viewOnly" value="${readOnly}" scope="request" />

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="${KualiForm.actionPrefix}DistributionAndIncome"
	documentTypeName="${KualiForm.docTypeName}"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="distributionAndIncome"
  	extraTopButtons="${KualiForm.extraTopButtons}"
  	showTabButtons="true">
  	
   	<div align="right"><kul:help parameterNamespace="KC-AB" parameterDetailType="Document" parameterName="awardBudgetDistributionAndIncomeHelpUrl" altText="help"/></div>
	
	<div align="center">
		<kra-b:budgetCostSharing />
		<kra-b:budgetUnrecoveredFandA />
		<kra-b:budgetProjectIncome />
		<kul:panelFooter />
	</div>

	<kul:documentControls 
		transactionalDocument="false"
		suppressRoutingControls="true"
		extraButtonSource="${extraButtonSource}"
		extraButtonProperty="${extraButtonProperty}"
		extraButtonAlt="${extraButtonAlt}"
		viewOnly="${readOnly}"
		suppressCancelButton="true"
		/>

</kul:documentPage>
