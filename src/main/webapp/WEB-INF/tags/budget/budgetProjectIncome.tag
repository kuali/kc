<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="budgetProjectIncomeAttributes" value="${DataDictionary.BudgetProjectIncome.attributes}" />

<kul:tab tabTitle="Project Income (#)" defaultOpen="true" tabErrorKey="${Constants.DOCUMENT_ERRORS}">
	<div class="tab-container" align="center">
		<div class="h2-container">
	    	<span class="subhead-left"><h2>Income Details</h2></span>
	    	<span class="subhead-right"><kul:help businessObjectClassName="fillMeIn" altText="help"/></span>
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
				<tr>
	            	<th width="50" align="right"><div align="right">Add:</div></th>
					<td class="infoline"><div align="center">
						<html:select property="newBudgetProjectIncome.budgetPeriodNumber">
	                    	<html:option value="0">Select</html:option>  		                    	
	                    	<c:set var="budgetPeriods" value="${KualiForm.document.budgetPeriods}"/>
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
	            			<html:image property="methodToCall.addProjectIncome" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' />
						</div>
					</td>
	          	</tr>
			          	
	  			<c:forEach var="budgetProjectIncome" items="${KualiForm.document.budgetProjectIncomes}" varStatus="status">
	          		<tr>
	          			<th><div align="right">${status.index + 1}</div></th>
	            		<td><div align="center">
	            			${KualiForm.document.budgetPeriods[budgetProjectIncome.budgetPeriodNumber - 1].label}
	    				</div></td>
	            		<td><div align="center">
							<kul:htmlControlAttribute property="document.budgetProjectIncomes[${status.index}].projectIncome" attributeEntry="${budgetProjectIncomeAttributes.projectIncome}" styleClass="amount" />            				
	        			</div></td>
	            		<td><div align="center">
	        				<kul:htmlControlAttribute property="document.budgetProjectIncomes[${status.index}].description" attributeEntry="${budgetProjectIncomeAttributes.description}" />
	        			</div></td>
	            		<td>
	            			<div align=center>
	            				<c:if test="${!viewOnly and fn:length(KualiForm.document.budgetProjectIncomes) > 0}">
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
	    	<table id="budget-proj-income-table" cellpadding="0" cellspacing="0" summary="Budget Period Income Totals">
	    		<c:forEach var="periodIncomeTotal" items="${KualiForm.document.projectIncomePeriodTotals}" varStatus="status">
		    		<tr>
		    			<th width="75%"><div align="right">Period ${status.index + 1} Income:</div></th>
		    			<td width="25%"><div align="right">${periodIncomeTotal}</div></td>
		    		</tr>
		    	</c:forEach>
	    		<tr>
		    		<th width="75%"><div align="right">Total Income:</div></th>
		    		<td width="25%"><div align="right">${KualiForm.document.projectIncomeTotal}</div></td>
		    	</tr>
	    	</table>
		</div>					
	</div>
	<kul:panelFooter />
</kul:tab>