<%--
 Copyright 2006-2009 The Kuali Foundation
 
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

<c:set var="budgetProjectIncomeAttributes" value="${DataDictionary.BudgetProjectIncome.attributes}" />

<kul:tab tabTitle="Project Income" tabItemCount="${KualiForm.document.budget.budgetProjectIncomeCount}" defaultOpen="false" tabErrorKey="newBudgetProjectIncome.*,document.budgetProjectIncome*">
	<div class="tab-container" align="center">
		<div class="h2-container">
	    	<span class="subhead-left"><h2>Income Details</h2></span>
	    	<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.budget.distributionincome.BudgetProjectIncome" altText="help"/></span>
		</div>
		<div align="center">
			<table id="budget-proj-income-table" cellpadding="0" cellspacing="0" summary="Budget Period Incomes">
				<tr>
					<th><div align="left">&nbsp</div></th>
					<th><div align="center">*Period</div></th>
					<th><div align="center">*Income</div></th>
					<th><div align="center">*Description</div></th>
					<th><div align="center">Actions</div></th>	
				</tr>
				
				<kra:section permission="modifyBudgets">
					<tr>
		            	<th width="50" align="right"><div align="right">Add:</div></th>
						<td class="infoline"><div align="center">
							<html:select property="newBudgetProjectIncome.budgetPeriodNumber">
		                    	<html:option value="0">Select</html:option>  		                    	
		                    	<c:set var="budgetPeriods" value="${KualiForm.document.budget.budgetPeriods}"/>
	    		            	<html:options collection="budgetPeriods" property="budgetPeriod" labelProperty="label"/>
	  			        	</html:select>						
		    			</div></td>
						<td class="infoline"><div align="center">
		        			<kul:htmlControlAttribute property="newBudgetProjectIncome.projectIncome" attributeEntry="${budgetProjectIncomeAttributes.projectIncome}" styleClass="amount" />
		        		</div></td>
		        		<td class="infoline"><div align="center">
		        			<kul:htmlControlAttribute property="newBudgetProjectIncome.description" attributeEntry="${budgetProjectIncomeAttributes.description}" />
		        		</div></td>
		                <td class="infoline">
		            		<div align=center>
		            			<html:image property="methodToCall.addProjectIncome" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
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
							<kul:htmlControlAttribute property="document.budget.budgetProjectIncome[${status.index}].projectIncome" attributeEntry="${budgetProjectIncomeAttributes.projectIncome}" styleClass="amount" />            				
	        			</div></td>
	            		<td><div align="center">
	        				<kul:htmlControlAttribute property="document.budget.budgetProjectIncome[${status.index}].description" attributeEntry="${budgetProjectIncomeAttributes.description}" />
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
    
    	<div class="h2-container">
    		<span class="subhead-left"><h2>Income Summary</h2></span>		    		
    	</div>
    
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
