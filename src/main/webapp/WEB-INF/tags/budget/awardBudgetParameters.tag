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

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.DevelopmentProposal.attributes}" />
<c:set var="budgetAttributes" value="${DataDictionary.Budget.attributes}" />
<c:set var="awardBudgetAttributes" value="${DataDictionary.AwardBudgetExt.attributes}" />
<c:set var="awardAttributes" value="${DataDictionary.Award.attributes}" />
<c:set var="textAreaFieldName" value="document.budget.comments" />
<c:set var="action" value="budgetAction" />
<c:set var="KRAConst" value="${org.kuali.kra.infrastructure.Constants}"/>

<input type="hidden" id="updateFinalVersion" name="updateFinalVersion" value='<bean:write name="KualiForm" property="updateFinalVersion"/>' />

<c:forEach var="budgetDocumentVersions" items="${KualiForm.document.parentDocument.budgetDocumentVersions}" varStatus="status">
	<c:if test="${status.index + 1 != KualiForm.document.budget.budgetVersionNumber}">
		<input type="hidden" id="budgetStatus${status.index}" name="KualiForm" property="document.budget.awardBudgetStatusCode" value='<bean:write name="KualiForm" property="document.budget.awardBudgetStatusCode"/>' />
	</c:if> 
</c:forEach>
 <c:set var="useRiceAuditMode" value="true" scope="request" />
<kul:tabTop tabTitle="Budget Overview" defaultOpen="true" tabErrorKey="budgetParameters*,document.budget.totalCostLimit,document.budget.comments" auditCluster="budgetParametersOverviewWarnings,awardBudgetTypeAuditErrors" tabAuditKey="document.budget.totalCostLimit,document.budget.comments">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Buddddget Overview</span>
    		<span class="subhead-right">
    			<kul:help parameterNamespace="KC-AWARD" parameterDetailType="Document" parameterName="awardBudgetOverviewHelp" altText="help"/>
			</span>
        </h3>
        <table cellpadding=0 cellspacing=0 summary="">
        	<tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.accountNumber}" /></div></th>
                <td align="left" valign="middle">
                	<bean:write name="KualiForm" property="document.parentDocument.budgetParent.accountNumber"/>
                </td>
                <th><div align="right">Budget Start Date</div></th>
                <td align="left" valign="middle">
                	<fmt:formatDate value="${KualiForm.document.budget.startDate}" pattern="MM/dd/yyyy" />
                </td>
            </tr>
        	<tr>
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.awardNumber}" /></div></th>
           		<td>
           			<bean:write name="KualiForm" property="document.parentDocument.budgetParent.awardNumber"/> 
           		</td>
				<th><div align="right">Budget End Date</div></th>
                <td>
                	<fmt:formatDate value="${KualiForm.document.budget.endDate}" pattern="MM/dd/yyyy" />
                </td>
        	</tr>
			<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.budgetVersionNumber}" /></div></th>
                <td >
                	<kul:htmlControlAttribute property="document.budget.budgetVersionNumber" attributeEntry="${budgetAttributes.budgetVersionNumber}" readOnly="true"/>
                </td>
       			<th rowspan="2">
           			 <div align="right">Budget Totals:</div>
       			</th>
           		<td rowspan="2">
           			 <table id="budgetTotalsTable">
           			 	<tr>
           			 		<th width="45%">
           			 			<div align="right">Obligated Previous:</div>
           			 		</th>
           			 		<td width="30%">
           			 			<div align="right">
           			 				<bean:write name="KualiForm" property="document.budget.prevBudget.totalCost"/>
           			 			</div>
           			 		</td>
           			 		<td rowspan="3" width="25%">
           			 			&nbsp;
           			 		</td>
           			 	</tr>
           			 	<tr>
           			 		<th>
           			 			<div align="right">Obligated Change:</div>
           			 		</th>
           			 		<td>
           			 			<div align="right">
           			 				<bean:write name="KualiForm" property="document.budget.totalCost"/>
           			 			</div>
           			 		</td>
           			 	</tr>
           			 	<tr>
           			 		<th>
           			 			<div align="right">Obligated Total:</div>
           			 		</th>
           			 		<td>
           			 			<div align="right">
           			 				<bean:write name="KualiForm" property="document.budget.budgetsTotals[0]"/>
           			 			</div>
           			 		</td>
           			 	</tr>
           			 </table>
           		</td>
        	</tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${awardBudgetAttributes.awardBudgetTypeCode}" /></div></th>
			    <td>
			         <kul:htmlControlAttribute property="document.budget.awardBudgetTypeCode" attributeEntry="${awardBudgetAttributes.awardBudgetTypeCode}" readOnly="true"/>
                </td>
                <%--
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${awardBudgetAttributes.totalCostLimit}" /></div></th>
           		<td>
           			<kul:htmlControlAttribute property="document.budget.totalCostLimit" attributeEntry="${awardBudgetAttributes.totalCostLimit}" readOnly="true"/>
           		</td>
           		 --%>
     		</tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${awardBudgetAttributes.awardBudgetStatusCode}" /></div></th>
			    <td>
			         <kul:htmlControlAttribute property="document.budget.awardBudgetStatusCode" attributeEntry="${awardBudgetAttributes.awardBudgetStatusCode}" readOnly="true"/>
                </td>
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.urRateClassCode}" /></div></th>
                <td>
           			<c:set var="prevUrRateClassCode" value="${KualiForm.document.budget.ohRateClassCode}"/>
                	<input type="hidden" name="urRateClassCodePrevValue" value="${prevUrRateClassCode}">
                	<kul:htmlControlAttribute property="document.budget.urRateClassCode" readOnly="${readOnly}" attributeEntry="${budgetAttributes.urRateClassCode}"  styleClass="fixed-size-200-select"/>
                </td>
        	</tr>
        	<tr>
		        <input type="hidden" name="prevOnOffCampusFlag" value="${KualiForm.document.budget.onOffCampusFlag}">
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.onOffCampusFlag}" /></div></th>
                <td >
                	<kul:htmlControlAttribute property="document.budget.onOffCampusFlag" attributeEntry="${budgetAttributes.onOffCampusFlag}" readOnlyAlternateDisplay="${KualiForm.document.budget.onOffCampusFlagDescription}"/>
                </td>
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.ohRateClassCode}" /></div></th>
           		<td>
           			<c:set var="prevOhRateClassCode" value="${KualiForm.document.budget.ohRateClassCode}"/>
           			<input type="hidden" name="ohRateClassCodePrevValue" value="${prevOhRateClassCode}">
           			<kul:htmlControlAttribute property="document.budget.ohRateClassCode" readOnly="${readOnly or !KualiForm.fnARateFlagEditable}" attributeEntry="${budgetAttributes.ohRateClassCode}"  styleClass="fixed-size-200-select"/>
           		</td>
           	</tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${awardBudgetAttributes.description}" /></div></th>
                <td colspan="3">
                	<kul:htmlControlAttribute property="document.budget.description" attributeEntry="${awardBudgetAttributes.description}" readOnly="${readOnly or KualiForm.document.budget.rebudgetFlag}"/>
                </td>
        	</tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.comments}" /></div></th>
                <td colspan="3">
                	<kul:htmlControlAttribute property="document.budget.comments" attributeEntry="${budgetAttributes.comments}"/>
                </td>
        	</tr>
        </table>
    </div>
</kul:tabTop>
