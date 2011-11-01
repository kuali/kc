<%--
 Copyright 2005-2010 The Kuali Foundation

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
<kra-b:swapProposalDevelopmentEditModes/>
<c:set var="readOnly" value="${not KualiForm.editingMode['modifyBudgets']}" scope="request" />
<c:set var="extraButtons" value="${KualiForm.extraActionsButtons}" scope="request"/>
<bean:define id="proposalBudgetFlag" name="KualiForm" property="document.proposalBudgetFlag"/>
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
  	
  	<div align="right"><kul:help documentTypeName="BudgetDocument" pageName="Budget Actions" /></div>

	<div align="center">
		<c:choose>
	       <c:when test="${KualiForm.editingMode['printProposal']}">
		       <kra-b:budgetPrintForms />
		       <kra-b:budgetJustification top="false" />
		   </c:when>
		   <c:otherwise>
		       <kra-b:budgetJustification top="true" />
		   </c:otherwise>
	    </c:choose>
	    <c:if test="${proposalBudgetFlag}">
			<kra-b:budgetSubAwardsBudget />
	    </c:if>
	    <c:if test="${not proposalBudgetFlag}">
             <kra:dataValidation auditActivated="${KualiForm.auditActivated}" topTab="false"/>
        </c:if>
        <kul:adHocRecipients />
	    <c:if test="${not proposalBudgetFlag}">
            <kul:routeLog /> 
        </c:if>
		<kul:panelFooter />
	</div>
	
	<kul:documentControls 
		transactionalDocument="false"
		suppressRoutingControls="${proposalBudgetFlag}"
		extraButtonSource="${extraButtonSource}"
		extraButtonProperty="${extraButtonProperty}"
		extraButtonAlt="${extraButtonAlt}"
		extraButtons="${extraButtons}"
		viewOnly="${KualiForm.editingMode['viewOnly']}"
		/>

</kul:documentPage>
