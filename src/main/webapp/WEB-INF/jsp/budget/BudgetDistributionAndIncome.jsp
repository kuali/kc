<%--
 Copyright 2005-2006 The Kuali Foundation.

 Licensed under the Educational Community License, Version 1.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.opensource.org/licenses/ecl1.php

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="budgetDistributionAndIncome"
	documentTypeName="BudgetDocument"
  	headerDispatch="save"
  	headerTabActive="distributionAndIncome"
  	extraTopButtons="${KualiForm.extraTopButtons}"
  	showTabButtons="true">

	<center>Under Construction Still</center>
	
	<div align="center">
		<kul:uncollapsable tabTitle="Select Budget Period:" tabErrorKey="projectIncome*">
	          <div align="center">
	            <table  cellpadding="0" cellspacing="0" class="grid" summary="">
	              <tr>
	                <th class="grid"><div align="right">Budget Period:</div></th>                
	                <td class="grid" >
	                	<html:select property="newBudgetPeriodNumber">
	                    	<html:option value="0">Select</html:option>  		                    	
	                    	<c:set var="budgetPeriods" value="${KualiForm.document.budgetPeriods}"/>
    		            	<html:options collection="budgetPeriods" property="budgetPeriod" labelProperty="label"/>
  			        	</html:select>
	                </td>
	              </tr>
	            </table>
	            <br>
	            <html:image property="methodToCall.updateBudgetPeriodView" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-updateview.gif" title="Update View" alt="Update View" styleClass="tinybutton"/>
			</div>
		</kul:uncollapsable>
		
		<div style="padding-top: 3em;">
			<kul:tabTop tabTitle="Cost Sharing (#)" defaultOpen="true" tabErrorKey="budget.projectIncome*">
				<div class="tab-container" align="center">
			    	<div class="h2-container">
			    		<span class="subhead-left"><h2>Cost Sharing Distrbution List</h2></span>
			    		<span class="subhead-right"><kul:help businessObjectClassName="fillMeIn" altText="help"/></span>
			        </div>
			    </div>
			</kul:tabTop>
			
			<kul:tab tabTitle="Unrecovered F&A (#)" defaultOpen="true" tabErrorKey="budget.projectIncome*">
				<div class="tab-container" align="center">
			    	<div class="h2-container">
			    		<span class="subhead-left"><h2>Unrecovered F&A Distrbution List</h2></span>
			    		<span class="subhead-right"><kul:help businessObjectClassName="fillMeIn" altText="help"/></span>
			        </div>
			    </div>
			</kul:tab>
			
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
									<kul:htmlControlAttribute property="newBudgetProjectIncome.budgetPeriodNumber" attributeEntry="${budgetProjectIncomeAttributes.budgetPeriodNumber}" />
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
				
		</div>
	</div>

	<kul:documentControls 
		transactionalDocument="false"
		suppressRoutingControls="true"
		extraButtonSource="${extraButtonSource}"
		extraButtonProperty="${extraButtonProperty}"
		extraButtonAlt="${extraButtonAlt}"
		viewOnly="${KualiForm.editingMode['viewOnly']}"
		/>

</kul:documentPage>