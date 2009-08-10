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

<c:set var="ufaAttributes" value="${DataDictionary.BudgetUnrecoveredFandA.attributes}" />

<kul:tab tabTitle="Unrecovered F&A" 
		tabItemCount="${KualiForm.document.budget.budgetUnrecoveredFandACount}" 
		defaultOpen="false" 
		tabErrorKey="newUnrecoveredFandA*,document.unrecoveredFandA*,document.budgetUnrecoveredFandA*" 
		auditCluster="budgetUnrecoveredFandAAuditErrors,budgetUnrecoveredFandAAuditWarnings" 
		tabAuditKey="document.budget.budgetUnrecoveredFandA*"
		useRiceAuditMode="true">
	<div class="tab-container" align="center">
		<c:choose>
			<c:when test="${KualiForm.unrecoveredFandAEditFormVisible}">
				<div class="h2-container">
					<span class="subhead-left"><h2>Unrecovered F&A Distribution List</h2></span>
					<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.budget.distributionincome.BudgetUnrecoveredFandA" altText="help"/></span>
				</div>
			
				<div align="center">
					<table id="budget-unrecovered-fna-table" cellpadding="0" cellspacing="0" summary="Budget Unrecovered F & A">
						<tr>
							<th width="5%">&nbsp</th>
							<th width="15%"><div align="center">Fiscal Year</div></th>
							<th width="15%"><div align="center">Applicable Rate</div></th>
							<th width="15%"><div align="center">Campus</div></th>
							<th width="20%"><div align="center">Source Account</div></th>
							<th width="15%"><div align="center">Amount</div></th>					
							<th width="15%"><div align="center">Actions</div></th>	
						</tr>
						
						<kra:section permission="modifyBudgets">
							<tr>
				            	<th align="right"><div align="right">Add:</div></th>
								<td class="infoline">
									<div align="center">
				        				<kul:htmlControlAttribute property="newBudgetUnrecoveredFandA.fiscalYear" attributeEntry="${ufaAttributes.fiscalYear}" />
				        			</div>
				        		</td>
				        		<td class="infoline">
				        			<div align="center">
										<kul:htmlControlAttribute property="newBudgetUnrecoveredFandA.applicableRate" attributeEntry="${ufaAttributes.applicableRate}" styleClass="amount"/>						
				    				</div>
				    			</td>
				    			<td class="infoline">
				    				<div align="center">
					    				<html:select property="newBudgetUnrecoveredFandA.onCampusFlag">
					        				<html:option value="">Select</html:option>
					        				<html:option value="Y">Yes</html:option>
					        				<html:option value="N">No</html:option>
					        			</html:select>
				        			</div>
				        		</td>
				        		<td class="infoline">
				        			<div align="center">	        			
				        				<kul:htmlControlAttribute property="newBudgetUnrecoveredFandA.sourceAccount" attributeEntry="${ufaAttributes.sourceAccount}" />
				        			</div>
				        		</td>	        		
				        		<td class="infoline">
				        			<div align="center">
				        				<kul:htmlControlAttribute property="newBudgetUnrecoveredFandA.amount" attributeEntry="${ufaAttributes.amount}" styleClass="amount" />
				        			</div>
				        		</td>
				                <td class="infoline">
				            		<div align=center>
				            			<html:image property="methodToCall.addUnrecoveredFandA" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
									</div>
								</td>
				          	</tr>
				        </kra:section>
						          	
			  			<c:forEach var="unrecoveredFandA" items="${KualiForm.document.budget.budgetUnrecoveredFandAs}" varStatus="status">
			          		<tr>
			          			<th><div align="right">${status.index + 1}</div></th>
			            		
			            		<td><div align="center">
									<kul:htmlControlAttribute property="document.budget.budgetUnrecoveredFandA[${status.index}].fiscalYear" attributeEntry="${ufaAttributes.fiscalYear}" />            				
			        			</div></td>
			        			
			            		<td><div align="center">
			            			<fmt:formatNumber value="${unrecoveredFandA.applicableRate}" type="percent" pattern="##0.000" />% 
			    				</div></td>
			            		
			            		<td><div align="center">
				            		 <c:choose>
					                    <c:when test="${readOnly}">
					                    	<c:set var="campusFlagText" value="${unrecoveredFandA.onCampusFlag}" /> 
					                    	<c:if test="${campusFlagText == 'Y'}" >
					                    		<c:set var="campusFlagText" value="Yes" />
					                    	</c:if>
					                    	<c:if test="${campusFlagText == 'N'}" >
					                    		<c:set var="campusFlagText" value="No" />
					                    	</c:if>
					                    	<c:out value="${campusFlagText}" />  
					                     </c:when>
				                     	<c:otherwise>
					                     	<html:select property="document.budget.budgetUnrecoveredFandA[${status.index}].onCampusFlag">
					        					<html:option value="">Select</html:option>
					        					<html:option value="Y">Yes</html:option>
					        					<html:option value="N">No</html:option>
					        				</html:select>	
				                    	</c:otherwise>  
				                    </c:choose>   
			        			</div></td>
			            		
			            		<td><div align="center">
			        				<kul:htmlControlAttribute property="document.budget.budgetUnrecoveredFandA[${status.index}].sourceAccount" attributeEntry="${ufaAttributes.sourceAccount}" />
			        			</div></td>
			            		
			            		<td><div align="center">
			        				<kul:htmlControlAttribute property="document.budget.budgetUnrecoveredFandA[${status.index}].amount" attributeEntry="${ufaAttributes.amount}" styleClass="amount" />
			        			</div></td>
			        				        			
			            		<td>
			            			<div align=center>&nbsp;
			            				<c:if test="${!viewOnly and fn:length(KualiForm.document.budget.budgetUnrecoveredFandAs) > 0}">
										  	<html:image property="methodToCall.deleteUnrecoveredFandA.line${status.index}" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' title="Delete an Unrecovered F&A" alt="Delete an Unrecovered F&A" styleClass="tinybutton" />
										</c:if>
									</div>
			            		</td>
			         		</tr>	         		
			          	</c:forEach>
		          		<tr>
				    		<th colspan="5" class="infoline"><div align="right">Total Allocated:</div></th>
				    		<td><div align="right"><span class="amount"><fmt:formatNumber value="${KualiForm.document.budget.allocatedUnrecoveredFandA}" type="currency" currencySymbol="$" maxFractionDigits="2" /></span></div></td>
				    		<td>&nbsp;</td>
				    	</tr>
				    	<tr>
				    		<th colspan="5" class="infoline"><div align="right">Unallocated:</div></th>
				    		<td><div align="right"><span class="amount"><fmt:formatNumber value="${KualiForm.document.budget.unallocatedUnrecoveredFandA}" type="currency" currencySymbol="$" maxFractionDigits="2" /></span></div></td>
				    		<td>&nbsp;</td>
				    	</tr>
			        </table>
				</div>			
				    
				<div class="h2-container">
					<span class="subhead-left"><h2>Unrecovered F & A Summary</h2></span>		    		
				</div>
				
				<div align="center">
			    	<table id="budget-unrecovered-fna-summary-table" cellpadding="0" cellspacing="0" summary="Unrecovered F & A Amounts to be Allocated">
			    		<c:forEach var="budgetPeriod" items="${KualiForm.document.budget.budgetPeriods}" varStatus="status">
							<tr>
						    	<th width="70.5%" class="infoline"><div align="right">Period ${status.index + 1}: ${budgetPeriod.dateRangeLabel}:</div></th>
						    	<td width="15%"><div align="right"><span class="amount"><fmt:formatNumber value="${budgetPeriod.underrecoveryAmount}" type="currency" currencySymbol="$" maxFractionDigits="2" /></span></div></td>
						    	<th width="14.5%" class="infoline">&nbsp;</th>
						    </tr>
					    </c:forEach>
					</table>
					
					<div align="center" style="padding-top: 2em;">&nbsp; 
						<kra:section permission="modifyBudgets">
							<html:image property="methodToCall.resetUnrecoveredFandAToDefault" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-resettodefault.gif' />
							<html:image property="methodToCall.refreshTotals" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-recalculate.gif' />
						</kra:section>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div align="center">Unrecovered F &amp; A doesn't apply or is not available</div>
			</c:otherwise>
		</c:choose>
	</div>
</kul:tab>
