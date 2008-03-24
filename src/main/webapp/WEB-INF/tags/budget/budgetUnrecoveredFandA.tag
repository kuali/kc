<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="ufaAttributes" value="${DataDictionary.BudgetUnrecoveredFandA.attributes}" />

<kul:tab tabTitle="Unrecovered F&A (${KualiForm.document.budgetVersionNumber})" defaultOpen="false" tabErrorKey="newUnrecoveredFandA*">
	<div class="tab-container" align="center">
		<div class="h2-container">
			<span class="subhead-left"><h2>Unrecovered F&A Distribution List</h2></span>
			<span class="subhead-right"><kul:help businessObjectClassName="fillMeIn" altText="help"/></span>
		</div>
	
		<div align="center">
			<table id="budget-unrecovered-fna-table" cellpadding="0" cellspacing="0" summary="Budget Unrecovered F & A">
				<tr>
					<th><div align="left">&nbsp</div></th>
					<th><div align="center">Fiscal Year</div></th>
					<th><div align="center">Applicable Rate</div></th>
					<th><div align="center">Campus</div></th>
					<th><div align="center">Source Account</div></th>
					<th><div align="center">Amount</div></th>					
					<th><div align="center">Actions</div></th>	
				</tr>
				<tr>
	            	<th width="50" align="right"><div align="right">Add:</div></th>
					<td class="infoline"><div align="center">
	        			<kul:htmlControlAttribute property="newBudgetUnrecoveredFandA.fiscalYear" attributeEntry="${ufaAttributes.fiscalYear}" />
	        		</div></td>
	        		<td class="infoline"><div align="center">
						<kul:htmlControlAttribute property="newBudgetUnrecoveredFandA.applicableRate" attributeEntry="${ufaAttributes.applicableRate}" styleClass="amount"/>						
	    			</div></td>
	    			<td class="infoline"><div align="center">
	    				<html:select property="newBudgetUnrecoveredFandA.onCampusFlag">
	        				<html:option value="">Select</html:option>
	        				<html:option value="Y">Yes</html:option>
	        				<html:option value="N">No</html:option>
	        			</html:select>
	        		</div></td>
	        		<td class="infoline"><div align="center">	        			
	        			<kul:htmlControlAttribute property="newBudgetUnrecoveredFandA.sourceAccount" attributeEntry="${ufaAttributes.sourceAccount}" />
	        		</div></td>	        		
	        		<td class="infoline"><div align="center">
	        			<kul:htmlControlAttribute property="newBudgetUnrecoveredFandA.amount" attributeEntry="${ufaAttributes.amount}" styleClass="amount" />
	        		</div></td>
	                <td class="infoline">
	            		<div align=center>
	            			<html:image property="methodToCall.addUnrecoveredFandA" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' />
						</div>
					</td>
	          	</tr>
				          	
	  			<c:forEach var="unrecoveredFandA" items="${KualiForm.document.budgetUnrecoveredFandAs}" varStatus="status">
	          		<tr>
	          			<th><div align="right">${status.index + 1}</div></th>
	            		
	            		<td><div align="center">
							<kul:htmlControlAttribute property="document.budgetUnrecoveredFandAs[${status.index}].fiscalYear" attributeEntry="${ufaAttributes.fiscalYear}" />            				
	        			</div></td>
	        			
	            		<td><div align="center">
	            			<fmt:formatNumber value="${unrecoveredFandA.applicableRate}" type="percent" pattern="##0.000" />% 
	    				</div></td>
	            		
	            		<td><div align="center">
	            			<html:select property="document.budgetUnrecoveredFandAs[${status.index}].onCampusFlag">
	        					<html:option value="">Select</html:option>
	        					<html:option value="Y">Yes</html:option>
	        					<html:option value="N">No</html:option>
	        				</html:select>	        				
	        			</div></td>
	            		
	            		<td><div align="center">
	        				<kul:htmlControlAttribute property="document.budgetUnrecoveredFandAs[${status.index}].sourceAccount" attributeEntry="${ufaAttributes.sourceAccount}" />
	        			</div></td>
	            		
	            		<td><div align="center">
	        				<kul:htmlControlAttribute property="document.budgetUnrecoveredFandAs[${status.index}].amount" attributeEntry="${ufaAttributes.amount}" styleClass="amount" />
	        			</div></td>
	        				        			
	            		<td>
	            			<div align=center>
	            				<c:if test="${!viewOnly and fn:length(KualiForm.document.budgetUnrecoveredFandAs) > 0}">
								  	<html:image property="methodToCall.deleteUnrecoveredFandA.line${status.index}" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' title="Delete an Unrecovered F&A" alt="Delete an Unrecovered F&A" styleClass="tinybutton" />
								</c:if>
							</div>
	            		</td>
	         		</tr>
	         		</tbody>
	          	</c:forEach>
	        </table>
		</div>			
		    
		<div class="h2-container">
			<span class="subhead-left"><h2>Unrecovered F & A Summary</h2></span>		    		
		</div>
		
		<div align="center">
	    	<table id="budget-unrecovered-fna-summary-table" cellpadding="0" cellspacing="0" summary="Unrecovered F & A Amounts to be Allocated">
	    		<tr>
	    			<td width="50%"><table cellpadding="0" cellspacing="0">
		    			<c:forEach var="fiscalYearShare" items="${KualiForm.document.fiscalYearUnrecoveredFandATotals}" varStatus="status">
				    		<tr>
				    			<th width="75%" class="infoline"><div align="right">Period ${status.index + 1}: ${fiscalYearShare.assignedBudgetPeriod.dateRangeLabel}:</div></th>
				    			<td width="25%"><div align="right"><span class="amount">${fiscalYearShare.unrecoveredFandA}</span></div></td>
				    		</tr>
			    		</c:forEach>
			    	</table></td>
			    	<td width="50%"><table cellpadding="0" cellspacing="0" class="workarea">
	    				<tr>
				    		<th width="75%" class="infoline"><div align="right">Total Allocated:</div></th>
				    		<td width="25%"><div align="right"><span class="amount">${KualiForm.document.allocatedUnrecoveredFandA}</span></div></td>
				    	</tr>
				    	<tr>
				    		<th width="75%" class="infoline"><div align="right">Unallocated:</div></th>
				    		<td width="25%"><div align="right"><span class="amount">${KualiForm.document.unallocatedUnrecoveredFandA}</span></div></td>
				    	</tr>
			    	</table></td>
			    </tr>
			</table>
			<div align="center" style="padding-top: 2em;">
				<html:image property="methodToCall.resetUnrecoveredFandAToDefault" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-resettodefault.gif' />
				<html:image property="methodToCall.refreshTotals" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-recalculate.gif' />
			</div>
		</div>
	</div>
</kul:tab>