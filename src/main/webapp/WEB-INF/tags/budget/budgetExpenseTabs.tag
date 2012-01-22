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

<c:set var="action" value="budgetExpensesAction" />
			
<kra-b:budgetExpenseBudgetOverview isTop="true" /> 
    	   			   	
<c:forEach var="budgetCategoryTypeCode" items="${KualiForm.document.budget.budgetCategoryTypeCodes}" varStatus="catCodes">
	<!-- c:if test="${budgetCategoryTypeCode.key != 'H' || (KualiForm.document.proposalBudgetFlag && KualiForm.document.parentDocument.developmentProposal.parent) }" -->
		<kra-b:budgetDetailed budgetCategoryTypeCodeKey="${budgetCategoryTypeCode.key}" budgetCategoryTypeCodeLabel="${budgetCategoryTypeCode.value}" catCodes="${catCodes.index}"/>
	<!--/c:if-->
</c:forEach>
