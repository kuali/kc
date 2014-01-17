
<%--
 Copyright 2005-2014 The Kuali Foundation
 
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
<c:if test = "${empty parentReadOnlyFlag}">
	<c:set var = "parentReadOnlyFlag" scope = "request" value = "false"/>
</c:if>

<bean:define id="proposalBudgetFlag" name="KualiForm" property="document.proposalBudgetFlag" toScope = "request"/>

	<c:choose>
		<c:when test = "${proposalBudgetFlag}">			
			<c:set var = "isParentBudget" scope = "request" value= "${KualiForm.document.parentDocument.developmentProposal.parent}"/>
		</c:when>
		<c:otherwise>
			<c:set var = "isParentBudget" scope = "request" value= "false"/>
		</c:otherwise>
	</c:choose>

<c:if test = "${isParentBudget==true}">
	
	<c:choose>
		<c:when test = "${parentReadOnlyFlag==true}">
			<c:set target = "${KualiForm.editingMode}" property="modifyBudgets" value = "true"/>
			<c:set target = "${KualiForm.editingMode}" property="viewOnly" value = "false"/>
			<c:set target = "${KualiForm.editingMode}" property="readOnly" value = "false"/>
			<c:set var = "parentReadOnlyFlag" value = "false" scope = "request"/>
		</c:when>
		
		
		<c:otherwise>
			<c:set target = "${KualiForm.editingMode}" property="modifyBudgets" value = "false"/>
			<c:set target = "${KualiForm.editingMode}" property="viewOnly" value = "true"/>
			<c:set target = "${KualiForm.editingMode}" property="readOnly" value = "true"/>
			<c:set var = "parentReadOnlyFlag" value = "true" scope = "request"/>
		</c:otherwise>
	
	</c:choose>
</c:if>

<c:set var = "readOnly" scope = "request" value = "${not (KualiForm.editingMode['modifyBudgets'] && !parentReadOnlyFlag )}"/>
