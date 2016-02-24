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
<c:set var="extraButtons" value="${KualiForm.ratesExtraButtons}" scope="request"/>
<c:set var="readOnly" value="${not KualiForm.editingMode['modifyBudgets']}" scope="request" />

<c:if test="${readOnly}">
	<c:set var="extraButtons" value="" scope="request"/>
</c:if>

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="${KualiForm.actionPrefix}Rates"
	documentTypeName="${KualiForm.docTypeName}"
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
		suppressCancelButton="true"
/>
</kul:documentPage>
