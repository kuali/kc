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
<c:set var="readOnly" value="${not KualiForm.editingMode['modifyBudgets']}" scope="request" />
<c:set var="extraButtons" value="${KualiForm.extraActionsButtons}" scope="request"/>
<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="${KualiForm.actionPrefix}Actions"
	documentTypeName="${KualiForm.docTypeName}"
  	headerDispatch="${KualiForm.headerDispatch}"
  	renderMultipart="true"
  	headerTabActive="budgetActions"
  	extraTopButtons="${KualiForm.extraTopButtons}"
  	auditCount="0"
  	showTabButtons="true">
  	
	<div align="right"><kul:help parameterNamespace="KC-AB" parameterDetailType="Document" parameterName="awardBudgetActionsHelpUrl" altText="help"/></div>
	<div align="center">

		<kra-b:budgetPrintForms/>
		<kra-b:budgetJustification top="false"/>
		<kra:dataValidation auditActivated="${KualiForm.auditActivated}" topTab="false" helpParameterNamespace="KC-AB"
							helpParameterName="awardBudgetDataValidationHelpUrl" helpParameterDetailType="Document"/>
		<kul:adHocRecipients />
        <kul:routeLog /> 
		<kul:panelFooter />
	</div>
	
	<kul:documentControls 
		transactionalDocument="false"
		extraButtonSource="${extraButtonSource}"
		extraButtonProperty="${extraButtonProperty}"
		extraButtonAlt="${extraButtonAlt}"
		extraButtons="${extraButtons}"
		viewOnly="${KualiForm.editingMode['viewOnly']}"
		/>

</kul:documentPage>
