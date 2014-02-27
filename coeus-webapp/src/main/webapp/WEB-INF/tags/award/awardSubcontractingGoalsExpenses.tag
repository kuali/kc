<%--
 Copyright 2005-2014 The Kuali Foundation

 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License."C:/Users/neerajsk/AppData/Local/Temp/cy0ryhmf.bmp"
 You may obtain a copy of the License at

 http://www.osedu.org/licenses/ECL-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>


<div class="tab-container" align="center" id="goalsExpensesDetailsPanel">

	<c:set var="budgetedGoalsAttributes" value="${DataDictionary.AwardSubcontractingBudgetedGoals.attributes}" />
	<c:set var="expenditureDataNotFound" value="${KualiForm.subcontractingExpenditureCategoryAmounts.fresh}" />
	
	<html:hidden property="awardSubcontractingBudgetedGoals.awardNumber" />
	<html:hidden property="awardSubcontractingBudgetedGoals.fresh" />
	
	<h3>
		<span class="subhead-left">
			Budgeted Amounts for Award <c:out value="${KualiForm.awardSubcontractingBudgetedGoals.awardNumber}" />
		</span> 
	</h3>
	
	<table id="goalsExpenses-table" cellpadding="0" cellspacing="0" summary="Goals Expenses Details" style="border-top: 1px solid rgb(153, 153, 153);">
	
		<tr>
			<th><div align="right">Category</div></th>
			<th>Goals</th>
			<th>Expenditures</th>
		</tr>
		
		<tr>
			<td> <div align="right"><label>Large Business</label></div></td>
			<td> <div align="center"> <kul:htmlControlAttribute property="awardSubcontractingBudgetedGoals.largeBusinessGoalAmount" attributeEntry="${budgetedGoalsAttributes.largeBusinessGoalAmount}" styleClass="amount" /></div></td>
			<td> <div align="center">
					<c:if test="${!expenditureDataNotFound}">  
						<fmt:formatNumber value="${KualiForm.subcontractingExpenditureCategoryAmounts.largeBusinessExpenditureAmount}" type="currency" currencySymbol="$" maxFractionDigits="2" />
					</c:if>
				</div>
			</td>
		</tr>
		
		<tr>
			<td> <div align="right"><label>Small Business</label></div></td>
			<td> <div align="center"> <kul:htmlControlAttribute property="awardSubcontractingBudgetedGoals.smallBusinessGoalAmount" attributeEntry="${budgetedGoalsAttributes.smallBusinessGoalAmount}" styleClass="amount" /></div></td>
			<td> <div align="center"> 
					<c:if test="${!expenditureDataNotFound}">
						<fmt:formatNumber value="${KualiForm.subcontractingExpenditureCategoryAmounts.smallBusinessExpenditureAmount}" type="currency" currencySymbol="$" maxFractionDigits="2" />
				    </c:if>
				</div>
			</td>
		</tr>		
		
		<tr>
			<td> <div align="right"><label>Total</label>
				<c:if test="${!readOnly}"> 
					<br/> <html:image property="methodToCall.recalculateBusinessTotals" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-recalculate.gif' styleClass="tinybutton" styleId="recalculateBusinessTotals"/></div></td>
				</c:if>
			<td> <div align="center"> <fmt:formatNumber value="${KualiForm.awardSubcontractingBudgetedGoals.totalBusinessGoalAmount}" type="currency" currencySymbol="$" maxFractionDigits="2" /> </div></td>
			<td> <div align="center">
					<c:if test="${!expenditureDataNotFound}"> 
						<fmt:formatNumber value="${KualiForm.subcontractingExpenditureCategoryAmounts.totalBusinessExpenditureAmount}" type="currency" currencySymbol="$" maxFractionDigits="2" />
				    </c:if>
			     </div>
			</td>
		</tr>
		
		<tr>
			<td> <div align="right"><label>Woman Owned</label></div></td>
			<td> <div align="center"> <kul:htmlControlAttribute property="awardSubcontractingBudgetedGoals.womanOwnedGoalAmount" attributeEntry="${budgetedGoalsAttributes.womanOwnedGoalAmount}" styleClass="amount" /></div></td>
			<td> 
				<div align="center">
					<c:if test="${!expenditureDataNotFound}">
						<fmt:formatNumber value="${KualiForm.subcontractingExpenditureCategoryAmounts.womanOwnedExpenditureAmount}" type="currency" currencySymbol="$" maxFractionDigits="2" />
					</c:if>
				</div>
			</td>
		</tr>
		
		<tr>
			<td> <div align="right"><label>8A Disadvantage</label></div></td>
			<td> <div align="center"> <kul:htmlControlAttribute property="awardSubcontractingBudgetedGoals.eightADisadvantageGoalAmount" attributeEntry="${budgetedGoalsAttributes.eightADisadvantageGoalAmount}" styleClass="amount" /></div></td>
			<td> 
				<div align="center">
					<c:if test="${!expenditureDataNotFound}">
						<fmt:formatNumber value="${KualiForm.subcontractingExpenditureCategoryAmounts.eightADisadvantageExpenditureAmount}" type="currency" currencySymbol="$" maxFractionDigits="2" />
					</c:if>
				</div>
			</td>		
		</tr>
		
		<tr>
			<td> <div align="right"><label>Hub Zone</label></div></td>
			<td> <div align="center"> <kul:htmlControlAttribute property="awardSubcontractingBudgetedGoals.hubZoneGoalAmount" attributeEntry="${budgetedGoalsAttributes.hubZoneGoalAmount}" styleClass="amount" /></div></td>
			<td> 
				<div align="center">
					<c:if test="${!expenditureDataNotFound}">
						<fmt:formatNumber value="${KualiForm.subcontractingExpenditureCategoryAmounts.hubZoneExpenditureAmount}" type="currency" currencySymbol="$" maxFractionDigits="2" />
					</c:if>
				</div>
			</td>		
		</tr>
		
		<tr>
			<td> <div align="right"><label>Veteran Owned</label></div></td>
			<td> <div align="center"> <kul:htmlControlAttribute property="awardSubcontractingBudgetedGoals.veteranOwnedGoalAmount" attributeEntry="${budgetedGoalsAttributes.veteranOwnedGoalAmount}" styleClass="amount" /></div></td>
			<td> 
				<div align="center">
					<c:if test="${!expenditureDataNotFound}">
						<fmt:formatNumber value="${KualiForm.subcontractingExpenditureCategoryAmounts.veteranOwnedExpenditureAmount}" type="currency" currencySymbol="$" maxFractionDigits="2" />
					</c:if>
				</div>
			</td>		
		</tr>
		
		<tr>
			<td> <div align="right"><label>Service Disabled Veteran Owned</label></div></td>
			<td> <div align="center"> <kul:htmlControlAttribute property="awardSubcontractingBudgetedGoals.serviceDisabledVeteranOwnedGoalAmount" attributeEntry="${budgetedGoalsAttributes.serviceDisabledVeteranOwnedGoalAmount}" styleClass="amount" /></div></td>
			<td> 
				<div align="center">
					<c:if test="${!expenditureDataNotFound}">
						<fmt:formatNumber value="${KualiForm.subcontractingExpenditureCategoryAmounts.serviceDisabledVeteranOwnedExpenditureAmount}" type="currency" currencySymbol="$" maxFractionDigits="2" />
					</c:if>
				</div>
			</td>		
		</tr>
		
		<tr>
			<td> <div align="right"><label>Historical Black College</label></div></td>
			<td> <div align="center"> <kul:htmlControlAttribute property="awardSubcontractingBudgetedGoals.historicalBlackCollegeGoalAmount" attributeEntry="${budgetedGoalsAttributes.historicalBlackCollegeGoalAmount}" styleClass="amount" /></div></td>
			<td> 
				<div align="center">
					<c:if test="${!expenditureDataNotFound}">
						<fmt:formatNumber value="${KualiForm.subcontractingExpenditureCategoryAmounts.historicalBlackCollegeExpenditureAmount}" type="currency" currencySymbol="$" maxFractionDigits="2" />
					</c:if>
				</div>
			</td>		
		</tr>	
		
		<tr>
			<td>
				<div align="right">
                	<kul:htmlAttributeLabel attributeEntry="${budgetedGoalsAttributes.comments}" noColon="true"/>
                </div>
            </td>
            <td colspan="2"> 
            	<div align="center">
                	<kul:htmlControlAttribute property="awardSubcontractingBudgetedGoals.comments" attributeEntry="${budgetedGoalsAttributes.comments}" />
                </div> 
            </td>
       </tr>
	
	</table>
</div>

