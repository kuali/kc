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
<c:set var="viewOnly" value="${KualiForm.editingMode['viewOnly']}" scope="request" />
<c:set var="awardAttributes" value="${DataDictionary.Award.attributes}" />
<c:set var="budgetAttributes" value="${DataDictionary.Budget.attributes}" />
<c:set var="awardBudgetAttributes" value="${DataDictionary.AwardBudgetExt.attributes}" />

<c:if test="${KualiForm.editingMode['modifyCompletedBudgets']}">
	<c:set target="${KualiForm.documentActions}" property="canSave" value="true"/>
</c:if>

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="awardBudgets"
	documentTypeName="AwardDocument"
	showTabButtons="true"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="budgets"
  	extraTopButtons="${KualiForm.extraTopButtons}" >
  	
  	<div align="right"><kul:help documentTypeName="AwardDocument" pageName="Budget Versions" /></div>

	<c:set var="awardBudgetPage" value="true" scope = "request"/>
  	<kul:tabTop tabTitle="Budget Overview (${KualiForm.document.award.awardIdAccount})" defaultOpen="true" tabErrorKey="budgetParameters*,document.budgetVersionOverview.totalCostLimit" auditCluster="budgetParametersOverviewWarnings,awardBudgetTypeAuditErrors" tabAuditKey="document.budgetVersionOverview.totalCostLimit,document.budget.comments">
	<div class="tab-container" align="center">
      <h3>
    	Budget Overview
    	<span class="subhead-right"><kul:help parameterNamespace="KC-AWARD" parameterDetailType="Document" parameterName="awardBudgetOverviewHelpUrl" altText="help"/></span>
      </h3>
        <table cellpadding=0 cellspacing=0 summary="">
        	<tr>
                <th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.accountNumber}" /></div></th>
                <td width="25%" align="left" valign="middle">
                	<bean:write name="KualiForm" property="document.award.accountNumber"/>
                </td>
                <th width="25%"><div align="right">Budget Start Date</div></th>
                <td width="25%" align="left" valign="middle">
                	<fmt:formatDate value="${KualiForm.document.budgetVersionOverview.startDate}" pattern="MM/dd/yyyy" />
                </td>
            </tr>
        	<tr>
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.awardNumber}" /></div></th>
           		<td>
           			<bean:write name="KualiForm" property="document.award.awardNumber"/> 
           		</td>
				<th><div align="right">Budget End Date</div></th>
                <td>
                	<fmt:formatDate value="${KualiForm.document.budgetVersionOverview.endDate}" pattern="MM/dd/yyyy" />
                </td>
        	</tr>
			<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.budgetVersionNumber}" /></div></th>
                <td >
                	<bean:write name="KualiForm" property="document.budgetVersionOverview.budgetVersionNumber"/> 
                </td>
       			<th>
           			<div align="right">Budget Total Cost Limit:</div>
           		<td>
           			<bean:write name="KualiForm" property="document.award.budgetTotalCostLimit"/> 
           		</td>
        	</tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${awardBudgetAttributes.awardBudgetTypeCode}" /></div></th>
			    <td>
			    	<bean:write name="KualiForm" property="document.budgetVersionOverview.awardBudgetType.description"/> 
                </td>
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${awardBudgetAttributes.totalCostLimit}" /></div></th>
           		<td>
           			<bean:write name="KualiForm" property="document.budgetVersionOverview.totalCostLimit"/>
           		</td>
     		</tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${awardBudgetAttributes.awardBudgetStatusCode}" /></div></th>
			    <td>
			    	<bean:write name="KualiForm" property="document.budgetVersionOverview.awardBudgetStatus.description"/>
                </td>
		        <input type="hidden" name="prevOnOffCampusFlag" value="${KualiForm.document.budgetVersionOverview.onOffCampusFlag}">
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.urRateClassCode}" /></div></th>
                <td>
                	<bean:write name="KualiForm" property="document.budgetVersionOverview.urRateClass.description"/>
                	<input type="hidden" name="urRateClassCodePrevValue" value="${KualiForm.document.budgetVersionOverview.urRateClassCode}">
                </td>
        	</tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.onOffCampusFlag}" /></div></th>
                <td >
                	<bean:write name="KualiForm" property="document.budgetVersionOverview.onOffCampusFlagDescription"/>
                </td>
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.ohRateClassCode}" /></div></th>
           		<td>
                	<c:out value="${KualiForm.document.budgetVersionOverview.rateClass.description}" />
           			<input type="hidden" name="ohRateClassCodePrevValue" value="${KualiForm.document.budgetVersionOverview.ohRateClassCode}">
           		</td>
        	</tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${awardBudgetAttributes.description}" /></div></th>
                <td colspan="3">
                	<bean:write name="KualiForm" property="document.budgetVersionOverview.description"/>
                </td>
        	</tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.comments}" /></div></th>
                <td colspan="3">
                	<bean:write name="KualiForm" property="document.budgetVersionOverview.comments"/>
                </td>
        	</tr>
        </table>
    </div>
</kul:tabTop>
  	
  	<kra-b:budgetVersions 
  		budgetDocumentVersions="${KualiForm.document.award.allAwardBudgets}"
  		pathToVersions="document.award.allAwardBudgets"
  		errorKey="document.budgetDocumentVersion*,
  					document.award.budget*"
  		requestedStartDateInitial="${KualiForm.document.award.beginDate}"
		requestedEndDateInitial="${KualiForm.document.award.projectEndDate}"
		/>
  	  	
  	 <kra-a:awardBudgetLimits /> 
  	
  	<kul:documentControls 
		transactionalDocument="false"
		suppressRoutingControls="true"
		extraButtonSource="${extraButtonSource}"
		extraButtonProperty="${extraButtonProperty}"
		extraButtonAlt="${extraButtonAlt}"
		viewOnly="${viewOnly}"
		suppressCancelButton="true" />
		
</kul:documentPage>
