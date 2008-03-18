<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="budgetCostShareAttributes" value="${DataDictionary.BudgetCostShare.attributes}" />

<kul:tabTop tabTitle="Cost Sharing (${KualiForm.document.budgetVersionNumber})" defaultOpen="true" tabErrorKey="newCostShare*">
	<div class="tab-container" align="center">
		<div class="h2-container">
	    	<div class="h2-container">
	    		<span class="subhead-left"><h2>Cost Sharing Distribution List</h2></span>
	    		<span class="subhead-right"><kul:help businessObjectClassName="fillMeIn" altText="help"/></span>
	        </div>
	    </div>
		<div align="center">
			<table id="budget-cost-sharing-table" cellpadding="0" cellspacing="0" summary="Budget Period Incomes">
				<tr>
					<th><div align="left">&nbsp</div></th>
					<th><div align="center">Fiscal Year</div></th>
					<th><div align="center">Percentage</div></th>
					<th><div align="center">Source Account</div></th>
					<th><div align="center">Amount</div></th>					
					<th><div align="center">Actions</div></th>	
				</tr>
				<tr>
	            	<th width="50" align="right"><div align="right">Add:</div></th>
					<td class="infoline"><div align="center">
	        			<kul:htmlControlAttribute property="newBudgetCostShare.fiscalYear" attributeEntry="${budgetCostShareAttributes.fiscalYear}" />
	        		</div></td>
	        		<td class="infoline"><div align="center">
						<kul:htmlControlAttribute property="newBudgetCostShare.sharePercentage" attributeEntry="${budgetCostShareAttributes.sharePercentage}" />						
	    			</div></td>
	    			<td class="infoline"><div align="center">
	        			<kul:htmlControlAttribute property="newBudgetCostShare.sourceAccount" attributeEntry="${budgetCostShareAttributes.sourceAccount}" />
	        		</div></td>
	        		<td class="infoline"><div align="center">
	        			<kul:htmlControlAttribute property="newBudgetCostShare.shareAmount" attributeEntry="${budgetCostShareAttributes.shareAmount}" styleClass="amount" />
	        		</div></td>	        		
	                <td class="infoline">
	            		<div align=center>
	            			<html:image property="methodToCall.addCostShare" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' />
						</div>
					</td>
	          	</tr>
			          	
	  			<c:forEach var="budgetCostShare" items="${KualiForm.document.budgetCostShares}" varStatus="status">
	          		<tr>
	          			<th><div align="right">${status.index + 1}</div></th>
	            		
	            		<td><div align="center">
							<kul:htmlControlAttribute property="document.budgetCostShares[${status.index}].fiscalYear" attributeEntry="${budgetCostShareAttributes.fiscalYear}" />            				
	        			</div></td>
	        			
	            		<td><div align="center">
	            			<kul:htmlControlAttribute property="document.budgetCostShares[${status.index}].sharePercentage" attributeEntry="${budgetCostShareAttributes.sharePercentage}" styleClass="amount" />
	    				</div></td>
	            		
	            		<td><div align="center">
	        				<kul:htmlControlAttribute property="document.budgetCostShares[${status.index}].sourceAccount" attributeEntry="${budgetCostShareAttributes.sourceAccount}" />
	        			</div></td>
	            		
	            		<td><div align="center">
	        				<kul:htmlControlAttribute property="document.budgetCostShares[${status.index}].shareAmount" attributeEntry="${budgetCostShareAttributes.shareAmount}" styleClass="amount" />
	        			</div></td>
	        				        			
	            		<td>
	            			<div align=center>
	            				<c:if test="${!viewOnly and fn:length(KualiForm.document.budgetCostShares) > 0}">
								  	<html:image property="methodToCall.deleteCostShare.line${status.index}" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' title="Delete a Cost Share" alt="Delete a Cost Share" styleClass="tinybutton" />
								</c:if>
							</div>
	            		</td>
	         		</tr>
	         		</tbody>
	          	</c:forEach>
	        </table>
		</div>			
	    
		<div class="h2-container">
			<span class="subhead-left"><h2>Cost Sharing Summary</h2></span>		    		
		</div>
	
		<div align="center">
	    	<table id="budget-cost-sharing-summary-table" cellpadding="0" cellspacing="0" summary="Cost Sharing Amounts to be Allocated">
	    		<tr>
	    			<td width="50%"><table cellpadding="0" cellspacing="0">
		    			<c:forEach var="fiscalYearCostShare" items="${KualiForm.document.fiscalYearCostShareTotals}" varStatus="status">
				    		<tr>
				    			<th width="75%" class="infoline"><div align="right">Period ${status.index + 1}: ${fiscalYearCostShare.assignedBudgetPeriod.dateRangeLabel}:</div></th>
				    			<td width="25%"><div align="right"><span class="amount">${fiscalYearCostShare.costShare}</span></div></td>
				    		</tr>
			    		</c:forEach>
			    	</table></td>
			    	<td width="50%"><table cellpadding="0" cellspacing="0" class="workarea">
	    				<tr>
				    		<th width="75%" class="infoline"><div align="right">Total Allocated:</div></th>
				    		<td width="25%"><div align="right"><span class="amount">${KualiForm.document.availableCostSharing}</span></div></td>
				    	</tr>
				    	<tr>
				    		<th width="75%" class="infoline"><div align="right">Unallocated:</div></th>
				    		<td width="25%"><div align="right"><span class="amount">${KualiForm.document.unallocatedCostSharing}</span></div></td>
				    	</tr>
			    	</table></td>
			    </tr>
			</table>
			<div align="center" style="padding-top: 2em;">
				<html:image property="methodToCall.resetCostSharingToDefault" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-resettodefault.gif' />
				<html:image property="methodToCall.recalculateCostSharing" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-recalculate.gif' />
			</div>
		</div>					
	</div>
</kul:tabTop>