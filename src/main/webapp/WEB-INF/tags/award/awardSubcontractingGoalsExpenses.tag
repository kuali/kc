<%--
 Copyright 2005-2010 The Kuali Foundation

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

	<c:set var="goalsAndExpendituresAttributes" value="${DataDictionary.AwardSubcontractingGoalsExpenditures.attributes}" />
	
	<html:hidden property="awardSubcontractingGoalsExpenditures.awardNumber" />
	<html:hidden property="awardSubcontractingGoalsExpenditures.fresh" />
	
	<h3>
		<span class="subhead-left">
			Budgeted Amounts for Award <c:out value="${KualiForm.awardSubcontractingGoalsExpenditures.awardNumber}" />
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
			<td> <div align="center"> <kul:htmlControlAttribute property="awardSubcontractingGoalsExpenditures.largeBusinessGoalAmount" attributeEntry="${goalsAndExpendituresAttributes.largeBusinessGoalAmount}" styleClass="amount" /></div></td>
			<td> <div align="center"> <kul:htmlControlAttribute property="awardSubcontractingGoalsExpenditures.largeBusinessExpenditureAmount" attributeEntry="${goalsAndExpendituresAttributes.largeBusinessExpenditureAmount}" styleClass="amount" /></div></td>
		</tr>
		
		<tr>
			<td> <div align="right"><label>Small Business</label></div></td>
			<td> <div align="center"> <kul:htmlControlAttribute property="awardSubcontractingGoalsExpenditures.smallBusinessGoalAmount" attributeEntry="${goalsAndExpendituresAttributes.smallBusinessGoalAmount}" styleClass="amount" /></div></td>
			<td> <div align="center"> <kul:htmlControlAttribute property="awardSubcontractingGoalsExpenditures.smallBusinessExpenditureAmount" attributeEntry="${goalsAndExpendituresAttributes.smallBusinessExpenditureAmount}" styleClass="amount" /></div></td>
		</tr>		
		
		<tr>
			<td> <div align="right"><label>Total</label>
				<c:if test="${!readOnly}"> 
					<br/> <html:image property="methodToCall.recalculateBusinessTotals" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-recalculate.gif' styleClass="tinybutton" styleId="recalculateBusinessTotals"/></div></td>
				</c:if>
			<td> <div align="center"> <fmt:formatNumber value="${KualiForm.awardSubcontractingGoalsExpenditures.totalBusinessGoalAmount}" type="currency" currencySymbol="$" maxFractionDigits="2" /> </div></td>
			<td> <div align="center"> <fmt:formatNumber value="${KualiForm.awardSubcontractingGoalsExpenditures.totalBusinessExpenditureAmount}" type="currency" currencySymbol="$" maxFractionDigits="2" /></div></td>
		</tr>
		
		<tr>
			<td> <div align="right"><label>A8 Disadvantage</label></div></td>
			<td> <div align="center"> <kul:htmlControlAttribute property="awardSubcontractingGoalsExpenditures.a8DisadvantageGoalAmount" attributeEntry="${goalsAndExpendituresAttributes.a8DisadvantageGoalAmount}" styleClass="amount" /></div></td>
			<td> <div align="center"> <kul:htmlControlAttribute property="awardSubcontractingGoalsExpenditures.a8DisadvantageExpenditureAmount" attributeEntry="${goalsAndExpendituresAttributes.a8DisadvantageExpenditureAmount}" styleClass="amount" /></div></td>
		</tr>
		
		<tr>
			<td> <div align="right"><label>Hub Zone</label></div></td>
			<td> <div align="center"> <kul:htmlControlAttribute property="awardSubcontractingGoalsExpenditures.hubZoneGoalAmount" attributeEntry="${goalsAndExpendituresAttributes.hubZoneGoalAmount}" styleClass="amount" /></div></td>
			<td> <div align="center"> <kul:htmlControlAttribute property="awardSubcontractingGoalsExpenditures.hubZoneExpenditureAmount" attributeEntry="${goalsAndExpendituresAttributes.hubZoneExpenditureAmount}" styleClass="amount" /></div></td>
		</tr>
		
		<tr>
			<td> <div align="right"><label>Veteran Owned</label></div></td>
			<td> <div align="center"> <kul:htmlControlAttribute property="awardSubcontractingGoalsExpenditures.veteranOwnedGoalAmount" attributeEntry="${goalsAndExpendituresAttributes.veteranOwnedGoalAmount}" styleClass="amount" /></div></td>
			<td> <div align="center"> <kul:htmlControlAttribute property="awardSubcontractingGoalsExpenditures.veteranOwnedExpenditureAmount" attributeEntry="${goalsAndExpendituresAttributes.veteranOwnedExpenditureAmount}" styleClass="amount" /></div></td>
		</tr>
		
		<tr>
			<td> <div align="right"><label>Service Disabled Veteran Owned</label></div></td>
			<td> <div align="center"> <kul:htmlControlAttribute property="awardSubcontractingGoalsExpenditures.serviceDisabledVeteranOwnedGoalAmount" attributeEntry="${goalsAndExpendituresAttributes.serviceDisabledVeteranOwnedGoalAmount}" styleClass="amount" /></div></td>
			<td> <div align="center"> <kul:htmlControlAttribute property="awardSubcontractingGoalsExpenditures.serviceDisabledVeteranOwnedExpenditureAmount" attributeEntry="${goalsAndExpendituresAttributes.serviceDisabledVeteranOwnedExpenditureAmount}" styleClass="amount" /></div></td>
		</tr>
		
		<tr>
			<td> <div align="right"><label>Historical Black College</label></div></td>
			<td> <div align="center"> <kul:htmlControlAttribute property="awardSubcontractingGoalsExpenditures.historicalBlackCollegeGoalAmount" attributeEntry="${goalsAndExpendituresAttributes.historicalBlackCollegeGoalAmount}" styleClass="amount" /></div></td>
			<td> <div align="center"> <kul:htmlControlAttribute property="awardSubcontractingGoalsExpenditures.historicalBlackCollegeExpenditureAmount" attributeEntry="${goalsAndExpendituresAttributes.historicalBlackCollegeExpenditureAmount}" styleClass="amount" /></div></td>
		</tr>	
		
		<tr>
			<td>
				<div align="right">
                	<kul:htmlAttributeLabel attributeEntry="${goalsAndExpendituresAttributes.comments}" noColon="true"/>
                </div>
            </td>
            <td colspan="2"> 
            	<div align="center">
                	<kul:htmlControlAttribute property="awardSubcontractingGoalsExpenditures.comments" attributeEntry="${goalsAndExpendituresAttributes.comments}" />
                </div> 
            </td>
       </tr>
	
	</table>
</div>

