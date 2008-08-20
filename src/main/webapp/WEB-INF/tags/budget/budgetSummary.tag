<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.ProposalDevelopmentDocument.attributes}" />
<c:set var="budgetAttributes" value="${DataDictionary.BudgetDocument.attributes}" />
<c:set var="textAreaFieldName" value="document.comments" />
<c:set var="action" value="budgetAction" />
<c:set var="KRAConst" value="${org.kuali.kra.infrastructure.Constants}"/>

<input type="hidden" id="updateFinalVersion" name="updateFinalVersion" value='<bean:write name="KualiForm" property="updateFinalVersion"/>' />

<c:forEach var="budgetVersionOverviews" items="${KualiForm.document.proposal.budgetVersionOverviews}" varStatus="status">
	<c:if test="${status.index + 1 != KualiForm.document.budgetVersionNumber}">
		<input type="hidden" id="finalVersionFlag${status.index}" name="KualiForm" property="document.proposal.budgetVersionOverview[${status.index}].finalVersionFlag" value='<bean:write name="KualiForm" property="document.proposal.budgetVersionOverview[${status.index}].finalVersionFlag"/>' />
		<input type="hidden" id="budgetStatus${status.index}" name="KualiForm" property="document.proposal.budgetVersionOverview[${status.index}].budgetStatus" value='<bean:write name="KualiForm" property="document.proposal.budgetVersionOverview[${status.index}].budgetStatus"/>' />
	</c:if>
</c:forEach>
 
<kul:tabTop tabTitle="Budget Overview" defaultOpen="true" tabErrorKey="budgetSummary*,document.residualFunds,document.totalCostLimit"  >
	<div class="tab-container" align="center">
    	<div class="h2-container">
    		<span class="subhead-left"><h2>Budget Overview</h2></span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.proposaldevelopment.bo.ProposalBudgetStatus" altText="help"/></span>
        </div>
        <table cellpadding=0 cellspacing=0 summary="">
        	<tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.startDate}" /></div></th>
                <td align="left" valign="middle">
                	<fmt:formatDate value="${KualiForm.document.startDate}" pattern="MM/dd/yyyy" />
                </td>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.modularBudgetFlag}" noColon="true" /></div></th>
                <td>
                	<kul:htmlControlAttribute property="document.modularBudgetFlag" attributeEntry="${budgetAttributes.modularBudgetFlag}" />
                </td>
            </tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.endDate}" /></div></th>
                <td>
                	<fmt:formatDate value="${KualiForm.document.endDate}" pattern="MM/dd/yyyy" />
                </td>
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.totalCostLimit}" /></div></th>
           		<td>
           			<kul:htmlControlAttribute property="document.totalCostLimit" attributeEntry="${budgetAttributes.totalCostLimit}" styleClass="amount"/>
           		</td>
        	</tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.budgetStatus}" /></div></th>
                <td>
                	<kra:kraControlAttribute property="document.budgetStatus" readOnly="${readOnly}" attributeEntry="${proposalDevelopmentAttributes.budgetStatus}"  styleClass="fixed-size-200-select"/>
                </td>
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.residualFunds}" /></div></th>
           		<td>
           			<kul:htmlControlAttribute property="document.residualFunds" attributeEntry="${budgetAttributes.residualFunds}" styleClass="amount"/>
           		</td>
        	</tr>
     		<tr>
     		    <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.finalVersionFlag}" noColon="true" /></div></th>
           		<td>
           			<kul:htmlControlAttribute property="document.finalVersionFlag" attributeEntry="${budgetAttributes.finalVersionFlag}" onclick="javascript: confirmFinalizeVersion(document, ${KualiForm.document.proposal.numberOfVersions})" />
           		</td>
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.urRateClassCode}" /></div></th>
                <td>
                	<kra:kraControlAttribute property="document.urRateClassCode" readOnly="${readOnly}" attributeEntry="${budgetAttributes.urRateClassCode}"  styleClass="fixed-size-200-select"/>
                	<input type="hidden" name="urRateClassCodePrevValue" value="${KualiForm.document.urRateClassCode}">
                </td>
     		</tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.comments}" /></div></th>
                <td>
                	<kul:htmlControlAttribute property="document.comments" attributeEntry="${budgetAttributes.comments}"/>
                    <kra:expandedTextArea textAreaFieldName="${textAreaFieldName}" action="${action}" textAreaLabel="${budgetAttributes.comments.label}" />
                </td>
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.ohRateClassCode}" /></div></th>
           		<td>
           			<kra:kraControlAttribute property="document.ohRateClassCode" readOnly="${readOnly}" attributeEntry="${budgetAttributes.ohRateClassCode}"  styleClass="fixed-size-200-select"/>
           			<input type="hidden" name="ohRateClassCodePrevValue" value="${KualiForm.document.ohRateClassCode}">
           		</td>
     		</tr>
        	<tr>
		        <input type="hidden" name="prevOnOffCampusFlag" value="${KualiForm.document.onOffCampusFlag}">
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.onOffCampusFlag}" /></div></th>
                <td colspan="3">
                	<kul:htmlControlAttribute property="document.onOffCampusFlag" attributeEntry="${budgetAttributes.onOffCampusFlag}" readOnlyAlternateDisplay="${KualiForm.document.onOffCampusFlagDescription}"/>
                </td>           		
     		</tr>

        </table>
    </div>
</kul:tabTop>
