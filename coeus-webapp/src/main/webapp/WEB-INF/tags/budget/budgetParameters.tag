<%--
 Copyright 2005-2013 The Kuali Foundation
 
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
<c:set var="textAreaFieldName" value="document.budget.comments" />
<c:set var="action" value="budgetAction" />
<c:set var="KRAConst" value="${org.kuali.kra.infrastructure.Constants}"/>
<bean:define id="proposalBudgetFlag" name="KualiForm" property="document.proposalBudgetFlag"/>

<input type="hidden" id="updateFinalVersion" name="updateFinalVersion" value='<bean:write name="KualiForm" property="updateFinalVersion"/>' />

<c:forEach var="budgetDocumentVersions" items="${KualiForm.document.parentDocument.budgetDocumentVersions}" varStatus="status">
	<c:if test="${status.index + 1 != KualiForm.document.budget.budgetVersionNumber}">
		<input type="hidden" id="finalVersionFlag${status.index}" name="KualiForm" property="document.parentDocument.budgetDocumentVersion[${status.index}].budgetVersionOverview.finalVersionFlag" value='<bean:write name="KualiForm" property="document.parentDocument.budgetDocumentVersion[${status.index}].budgetVersionOverview.finalVersionFlag"/>' />
		<input type="hidden" id="budgetStatus${status.index}" name="KualiForm" property="document.parentDocument.budgetDocumentVersion[${status.index}].budgetVersionOverview.budgetStatus" value='<bean:write name="KualiForm" property="document.parentDocument.budgetDocumentVersion[${status.index}].budgetVersionOverview.budgetStatus"/>' />
	</c:if>
</c:forEach>
 
<kul:tabTop tabTitle="Budget Overview" defaultOpen="true" tabErrorKey="budgetParameters*,document.budget.residualFunds,document.budget.totalCostLimit,document.budget.totalDirectCostLimit,budgetVersionOverview[*" auditCluster="budgetParametersOverviewWarnings,awardBudgetCostLimitAuditErrors" tabAuditKey="document.budget.totalCostLimit,document.budget.awardBudgetLimits">
	<div class="tab-container" align="center">
	
		<kra:softError softErrorKey="projectDatesChanged" />
		
    	<h3>Budget Overview
     	<span class="subhead-right"><kul:help parameterNamespace="KC-B" parameterDetailType="Document" parameterName="budgetOverviewHelpUrl" altText="help"/></span>
       </h3>
        <table cellpadding=0 cellspacing=0 summary="">
        	<tr>
                <th><div align="right">
                
                   <c:choose>
    				<c:when test="${proposalBudgetFlag}">
						<kul:htmlAttributeLabel attributeEntry="${budgetAttributes.startDate}" />
					</c:when>
					<c:otherwise>
						Budget Start Date
					</c:otherwise>
				</c:choose>
                
                </div></th>
                <td align="left" valign="middle">
                	<fmt:formatDate value="${KualiForm.budgetStartDate}" pattern="MM/dd/yyyy" />
                </td>
				<kra-b:swapProposalDevelopmentEditModes/>
       		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.modularBudgetFlag}" noColon="true" /></div></th>
                <td>
                	<kul:htmlControlAttribute property="document.budget.modularBudgetFlag" attributeEntry="${budgetAttributes.modularBudgetFlag}" />
                </td>
                <kra-b:swapProposalDevelopmentEditModes/>
        	
            </tr>
        	<tr>
			
				<th><div align="right">
				   <c:choose>
    				<c:when test="${proposalBudgetFlag}">
						<kul:htmlAttributeLabel attributeEntry="${budgetAttributes.endDate}" />
					</c:when>
					<c:otherwise>
						Budget End Date
					</c:otherwise>
				</c:choose>
				
				</div></th>
                <td>
                	<fmt:formatDate value="${KualiForm.budgetEndDate}" pattern="MM/dd/yyyy" />
                </td>
           		<kra-b:swapProposalDevelopmentEditModes/>
        		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.residualFunds}" /></div></th>
           		<td>
       				<kul:htmlControlAttribute property="document.budget.residualFunds" attributeEntry="${budgetAttributes.residualFunds}" styleClass="amount"/>
           		</td>
	     		<kra-b:swapProposalDevelopmentEditModes/>
        	</tr>
        	<tr>
           		<kra-b:swapProposalDevelopmentEditModes/>
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.totalDirectCostLimit}" /></div></th>
           		<td>
           			<kul:htmlControlAttribute property="document.budget.totalDirectCostLimit" attributeEntry="${budgetAttributes.totalDirectCostLimit}" styleClass="amount"/>
           		</td>
           		<kra-b:swapProposalDevelopmentEditModes/>
           		<kra-b:swapProposalDevelopmentEditModes/>
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.totalCostLimit}" /></div></th>
           		<td>
           			<kul:htmlControlAttribute property="document.budget.totalCostLimit" attributeEntry="${budgetAttributes.totalCostLimit}" styleClass="amount"/>
           		</td>
           		<kra-b:swapProposalDevelopmentEditModes/>
        	</tr>
			<tr>
				<kra-b:swapProposalDevelopmentEditModes/>
     			<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.budgetStatus}" /></div></th>
			    <td>
			    	 <input type=hidden name="KualiForm" property="hack" id="hack" disabled="true" />
			         <html:hidden name="KualiForm" property="document.budget.budgetStatus"  disabled="true" />
			         <kul:htmlControlAttribute property="document.budget.budgetStatus" readOnly="${readOnly}" readOnlyAlternateDisplay="Complete" attributeEntry="${proposalDevelopmentAttributes.budgetStatus}"  styleClass="fixed-size-200-select" onchange="javascript: toggleFinalCheckboxSummary(document)" disabled="${viewOnly}"/>
                </td>
    			<kra-b:swapProposalDevelopmentEditModes/>
				<kra-b:swapProposalDevelopmentEditModes/>
        		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.urRateClassCode}" /></div></th>
                <td>
           			<c:set var="prevUrRateClassCode" value="${KualiForm.document.budget.ohRateClassCode}"/>
                	<input type="hidden" name="urRateClassCodePrevValue" value="${prevUrRateClassCode}">
                	<kul:htmlControlAttribute property="document.budget.urRateClassCode" readOnly="${readOnly}" attributeEntry="${budgetAttributes.urRateClassCode}"  styleClass="fixed-size-200-select"/>
                </td>
                <kra-b:swapProposalDevelopmentEditModes/>
        	
        	</tr>
     		<tr>
	     		<kra-b:swapProposalDevelopmentEditModes/>
     		    <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.finalVersionFlag}" noColon="true" /></div></th>
           		<td>
           			<kul:htmlControlAttribute property="document.budget.finalVersionFlag" attributeEntry="${budgetAttributes.finalVersionFlag}" onclick="javascript: setupBudgetStatusSummary(document);" disabled="false" />
           			<html:hidden name="KualiForm" property="document.budget.finalVersionFlag" disabled="true" />
           		</td>
	    		<kra-b:swapProposalDevelopmentEditModes/>
           		<kra-b:swapProposalDevelopmentEditModes/>
        		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.ohRateClassCode}" /></div></th>
           		<td>
           			<c:set var="prevOhRateClassCode" value="${KualiForm.document.budget.ohRateClassCode}"/>
           			<input type="hidden" name="ohRateClassCodePrevValue" value="${prevOhRateClassCode}">
           			<kul:htmlControlAttribute property="document.budget.ohRateClassCode" readOnly="${readOnly}" attributeEntry="${budgetAttributes.ohRateClassCode}"  styleClass="fixed-size-200-select"/>
           		</td>
           		<kra-b:swapProposalDevelopmentEditModes/>
     		</tr>     	
    		<tr>
		        <input type="hidden" name="prevOnOffCampusFlag" value="${KualiForm.document.budget.onOffCampusFlag}">
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.onOffCampusFlag}" /></div></th>
                <td>
                	<kul:htmlControlAttribute 
                		property="document.budget.onOffCampusFlag" 
                		attributeEntry="${budgetAttributes.onOffCampusFlag}" 
                		readOnlyAlternateDisplay="${KualiForm.document.budget.onOffCampusFlagDescription}"                		
                		onchange="confirmOnOffCampusFlagChange(this);"/>
                </td> 
                 <script type="text/javascript">
                 	var selIdx;
                 	function confirmOnOffCampusFlagChange(formItem) {
                		if(confirm("Changing On/Off Campus Flag, will result in recalculation of the budget. If you changed On/Off Campus Flag, you have to redistribute the Under recovery amount. Do you want to change the On/Off Campus Flag?")){
                			selIdx=formItem.selectedIndex} 
                		else {formItem.selectedIndex = selIdx}
                	}
                	function assignOnOffCampusIndex(document) {
                    	if (document.getElementById('document.budget.onOffCampusFlag') != null) {
                			selIdx = document.getElementById('document.budget.onOffCampusFlag').selectedIndex;
                    	}
                	}
                </script>          		
       		    <c:choose>				        		
	        		<c:when test="${KualiForm.document.budget.costSharingSubmissionEnabled}">
						<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.submitCostSharingFlag}" noColon="true" /></div></th>
		                <td>
		                	<kul:htmlControlAttribute property="document.budget.submitCostSharingFlag" attributeEntry="${budgetAttributes.submitCostSharingFlag}"/>
		                </td>
	                </c:when>
	                <c:otherwise>
	        			<td colspan="2">&nbsp;</td>
	                </c:otherwise>
                </c:choose>
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
<%-- initialize status of final checkbox and budget status. --%>
<img src="${ConfigProperties.kr.externalizable.images.url}pixel_clear.gif" onLoad="assignOnOffCampusIndex(document); setupBudgetStatusSummary(document)" />
