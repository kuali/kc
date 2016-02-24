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
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<c:set var="budgetLineItemAttributes" value="${DataDictionary.BudgetLineItem.attributes}" />
<c:set var="budgetPersonnelDetailsAttributes" value="${DataDictionary.BudgetPersonnelDetails.attributes}" />
<c:set var="budgetPersonAttributes" value="${DataDictionary.BudgetPerson.attributes}" />
<c:set var="budgetLineItemCalculatedAmountAttributes" value="${DataDictionary.BudgetLineItemCalculatedAmount.attributes}" />

<c:set var="budgetPeriodNumber" value='<%=request.getAttribute("budgetPeriod")%>' />
<c:set var="budgetLineItemNumber" value='<%=request.getAttribute("lineNumber")%>' />
<c:set var="rateClassCode" value='<%=request.getAttribute("rateClassCode")%>' />
<c:set var="rateTypeCode" value='<%=request.getAttribute("rateTypeCode")%>' />
<c:set var="fieldName" value='<%=request.getAttribute("fieldName")%>' />

<c:set var="docTitle" value="Rate Cost Details" />
<c:set var="rateAttributeEntry" value="${budgetLineItemCalculatedAmountAttributes.calculatedCost}" />

<c:if test="${not empty fieldName && fieldName == 'rateCostSharing' }">
	<c:set var="docTitle" value="Rate Cost Sharing Details" />
	<c:set var="rateAttributeEntry" value="${budgetLineItemCalculatedAmountAttributes.calculatedCostSharing}" />
</c:if>

<c:set var="budgetLineItem" value="${KualiForm.document.budget.budgetPeriods[budgetPeriodNumber - 1].budgetLineItems[budgetLineItemNumber]}"/>

<kul:tabTop defaultOpen="true" tabTitle="${docTitle}" tabErrorKey="*">
	<div class="tab-container" align="center">
	   <input type="hidden" name="line" value="${line}" />
	   <div class="h2-container">
       		<span class="subhead-left"><h2>${docTitle}</h2></span>
       </div>	
       <table id="rates-table" cellpadding=0 cellspacing=0 summary="">
		<tr>
		  	<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetPersonAttributes.personName}" noColon="true" /></div></th>
  			<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemCalculatedAmountAttributes.rateClassCode}" noColon="true" /></div></th>          		
  			<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemCalculatedAmountAttributes.rateTypeCode}" noColon="true" /></div></th>
  			<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemCalculatedAmountAttributes.applyRateFlag}" noColon="true" /></div></th>
  			<th width="10%">
  				<div align="center">
  					<kul:htmlAttributeLabel attributeEntry="${rateAttributeEntry}" noColon="true" />
  				</div>
  			</th>
		</tr>
       
       <c:forEach var="budgetPersonnelDetails" items="${budgetLineItem.budgetPersonnelDetailsList}" varStatus="status">
	       <c:forEach var="budgetPersonnelRate" items="${budgetPersonnelDetails.budgetPersonnelCalculatedAmounts}" varStatus="rateStatus">
			  <c:if test="${budgetPersonnelRate.rateClassCode == rateClassCode and budgetPersonnelRate.rateTypeCode == rateTypeCode}" >
			  
			  <c:set var="rateValue" value="${budgetPersonnelRate.calculatedCost}" />
			  <c:if test="${not empty fieldName && fieldName == 'rateCostSharing' }">
			  		<c:set var="rateValue" value="${budgetPersonnelRate.calculatedCostSharing}" />
			  </c:if>
			  		       	
	    	  	<tr>	
	    	  		<td><div align="center">
						<c:out value="${budgetPersonnelDetails.budgetPerson.personName}" />
					</div></td>
					<td><div align="center">
						<c:out value="${budgetPersonnelRate.rateType.rateClass.description}" />
					</div></td>
					<td><div align="center">
						<c:out value="${budgetPersonnelRate.rateType.description}" />
					</div></td>
					<td><div align="center">
						<c:choose>
							<c:when test="${not empty budgetPersonnelRate.applyRateFlag && budgetPersonnelRate.applyRateFlag == true}">
								<c:out value="Yes" />
							</c:when>
							<c:otherwise><c:out value="No" /></c:otherwise>
						</c:choose>
					</div></td>									
					<td><div align="center">
						<c:out value="${rateValue}" />
					</div>
					</td>
				</tr>
	    	   </c:if>
    		</c:forEach>
       </c:forEach>
        </table>
    </div>
</kul:tabTop>

     
