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

<c:set var="budgetProjectIncomeAttributes" value="${DataDictionary.BudgetProjectIncome.attributes}" />

<kul:tab tabTitle="Project Income" tabItemCount="${KualiForm.document.budget.budgetProjectIncomeCount}" defaultOpen="false" tabErrorKey="newBudgetProjectIncome.*,document.budget.budgetProjectIncome*">
	<div class="tab-container" align="center">
		<h3>
            <span class="subhead-left">Income Details</span>
   			<span class="subhead-right"><kul:help parameterNamespace="KC-AB" parameterDetailType="Document" parameterName="awardBudgetDistributionAndIncomeDetailsHelpUrl" altText="help"/></span>
    	</h3>
		<div align="center">
			<table id="budget-proj-income-table" cellpadding="0" cellspacing="0" summary="Budget Period Incomes">
				<tr>
					<th><div align="left">&nbsp;</div></th>
					<th><div align="center">*Period</div></th>
					<th><div align="center">*Income</div></th>
					<th><div align="center">*Description</div></th>
					<th><div align="center">Actions</div></th>	
				</tr>
				
				<kra:section permission="modifyBudgets">
					<tr class="addline">
		            	<th width="50" align="right"><div align="right">Add:</div></th>
						<td class="infoline"><div align="center">
							<kul:htmlControlAttribute property="newBudgetProjectIncome.budgetPeriodNumber" attributeEntry="${budgetProjectIncomeAttributes.budgetPeriodNumber}"/>					
		    			</div></td>
						<td class="infoline"><div align="center">
		        			<kul:htmlControlAttribute property="newBudgetProjectIncome.projectIncome" attributeEntry="${budgetProjectIncomeAttributes.projectIncome}" styleClass="amount" />
		        		</div></td>
		        		<td class="infoline"><div align="center">
		        			<kul:htmlControlAttribute property="newBudgetProjectIncome.description" attributeEntry="${budgetProjectIncomeAttributes.description}" />
		        		</div></td>
		                <td class="infoline">
		            		<div align=center>
		            			<html:image property="methodToCall.addProjectIncome" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton addButton"/>
							</div>
						</td>
		          	</tr>
	          	</kra:section>
			          	
	  			<c:forEach var="budgetProjectIncome" items="${KualiForm.document.budget.budgetProjectIncomes}" varStatus="status">
	          		<tr>
	          			<th><div align="right">${status.index + 1}</div></th>
	            		<td><div align="center">
	            			${KualiForm.document.budget.budgetPeriods[budgetProjectIncome.budgetPeriodNumber - 1].label}
	    				</div></td>
	            		<td><div align="center">
							<kul:htmlControlAttribute property="document.budget.budgetProjectIncomes[${status.index}].projectIncome" attributeEntry="${budgetProjectIncomeAttributes.projectIncome}" styleClass="amount" />
	        			</div></td>
	            		<td><div align="center">
	        				<kul:htmlControlAttribute property="document.budget.budgetProjectIncomes[${status.index}].description" attributeEntry="${budgetProjectIncomeAttributes.description}" />
	        			</div></td>
	            		<td>
	            			<div align=center>
	            				<c:if test="${!viewOnly and fn:length(KualiForm.document.budget.budgetProjectIncomes) > 0}">
								  	<html:image property="methodToCall.deleteProjectIncome.line${status.index}" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' title="Delete a Project Income" alt="Delete a Project Income" styleClass="tinybutton" />
								</c:if>
							</div>
	            		</td>
	         		</tr>
	         		</tbody>
	          	</c:forEach>
	        </table>
		</div>			
    
    	<h3>
            <span class="subhead-left">Income Summary</span>
  			<span class="subhead-right"><kul:help parameterNamespace="KC-AB" parameterDetailType="Document" parameterName="awardBudgetDistributionAndIncomeSummaryHelpUrl" altText="help"/></span>
    	</h3>
    	<div align="center">
	    	<table id="budget-proj-income-summary-table" cellpadding="0" cellspacing="0" summary="Budget Period Income Totals">
	    		<c:forEach var="periodIncomeTotal" items="${KualiForm.document.budget.projectIncomePeriodTotalsForEachBudgetPeriod}" varStatus="status">
		    		<tr>
		    			<th width="75%"><div align="right">Period ${status.index + 1} Income:</div></th>
		    			<td width="25%"><div align="right">${periodIncomeTotal}</div></td>
		    		</tr>
		    	</c:forEach>
	    		<tr>
		    		<th width="75%"><div align="right">Total Income:</div></th>
		    		<td width="25%"><div align="right">${KualiForm.document.budget.projectIncomeTotal}</div></td>
		    	</tr>
	    	</table>
	    	
	    	<div align="center" style="padding-top: 2em;">&nbsp;
		    	<kra:section permission="modifyBudgets">
					<html:image property="methodToCall.refreshTotals" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-recalculate.gif' styleClass="tinybutton"/>
				</kra:section>
			</div>
		</div>					
	</div>
</kul:tab>
