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

<c:set var="budgetPersonAttributes" value="${DataDictionary.BudgetPerson.attributes}" />

<c:set var="hierarchyStatus" value="${KualiForm.document.parentDocument.budgetParent.hierarchyStatus}" />
<c:set var="hierarchyParentStatus" value="${KualiForm.hierarchyParentStatus}"/>

<div id="workarea">
<kul:tab tabTitle="Project Personnel (All Periods)" defaultOpen="true" transparentBackground="true" tabErrorKey="document.budget.budgetPerson*" auditCluster="budgetPersonnelAuditWarnings" tabAuditKey="document.budget.budgetPerson*" useRiceAuditMode="true">

	<c:if test="${hierarchyStatus != hierarchyParentStatus}">
	    <%-- handle job code error start--%>
	    <c:set var="firstErrorFound" value="false" />
	    <c:forEach var="property" items="${ErrorContainer.errorPropertyList}">
	        <c:if test="${fn:startsWith(property,'document.budget.budgetPerson')}">
	            <c:set var="firstErrorFound" value="true" />
	        </c:if>
	    </c:forEach>
			
	    <div class="tab-container-error">
			<div class="left-errmsg-tab">
				<div class="error">
					<c:forEach var="budgetPersons" items="${KualiForm.document.budget.budgetPersons}" varStatus="status">			
						
						<c:set var="foundJobCodeError" value="false" />
						<c:forEach var="property" items="${ErrorContainer.errorPropertyList}">
							<c:set var="propertyName" value="document.budget.budgetPersons[${status.index}].jobCode"/>
							<c:if test="${property eq propertyName}">
								<c:set var="foundJobCodeError" value="true" />
							</c:if>
						</c:forEach>
						
						<c:if test="${(KualiForm.document.budget.budgetPersons[status.index].jobCode == '' or empty KualiForm.document.budget.budgetPersons[status.index].jobCode) && foundJobCodeError == false}">
							<c:if test="${not empty firstErrorFound && firstErrorFound == false}">
								<strong>Errors found in this Section:</strong>
								<c:set var="firstErrorFound" value="true" />
							</c:if>	
							<li>The Job Code for <c:out value="${KualiForm.document.budget.budgetPersons[status.index].personName}" /> is not complete. You must enter a job code value before budgeting this individual.</li>
						</c:if>		
					
					</c:forEach>
				</div>
			</div>
		</div>
		
		<div class="tab-container" align="center">
			<div align="left">
	        &nbsp;&nbsp;&nbsp;Changes made in the Project Personnel panel must be saved before the corresponding results are reflected in the Personnel Details panel.<br/><br/>
	        </div> 
	    </div>
	    <%-- handle job code error end --%>
	</c:if>
		
    <div class="tab-container" align="center">
		<h3>
		    <span class="subhead-left">Project Personnel (All Periods)</span>
	       <span class="subhead-right"><kul:help parameterNamespace="KC-B" parameterDetailType="Document" parameterName="budgetPersonHelpUrl" altText="help"/></span>
 		</h3>
		
		<c:choose>
	        <c:when test="${hierarchyStatus != hierarchyParentStatus}">
	            <kra-b:budgetProjectPersonnelDetails budgetPersonList="${KualiForm.document.budget.budgetPersons}" 
	                                                 budgetPersonProperty="document.budget.budgetPersons" />
	        </c:when>
	        <c:otherwise>
			    <c:forEach items="${KualiForm.hierarchyPersonnelSummaries}" varStatus="summaryStatus">
			        <c:forEach items="${KualiForm.hierarchyPersonnelSummaries[summaryStatus.index].hierarchyBudgets}" varStatus="budgetStatus">
			            <kul:innerTab tabTitle="Proposal # ${KualiForm.hierarchyPersonnelSummaries[summaryStatus.index].proposalNumber} - Budget Version ${KualiForm.hierarchyPersonnelSummaries[summaryStatus.index].hierarchyBudgets[budgetStatus.index].budgetVersionNumber}" parentTab="" defaultOpen="false" tabErrorKey="">
			                <kra-b:budgetProjectPersonnelDetails budgetPersonList="${KualiForm.hierarchyPersonnelSummaries[summaryStatus.index].hierarchyBudgets[budgetStatus.index].budgetPersons}" 
                                                                 budgetPersonProperty="hierarchyPersonnelSummaries[${summaryStatus.index}].hierarchyBudgets[${budgetStatus.index}].budgetPersons" />
					    </kul:innerTab>
					</c:forEach>
	            </c:forEach>
	        </c:otherwise>
		</c:choose>
    </div>
	
</kul:tab>