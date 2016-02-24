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
<c:set var="readOnly" value="${not KualiForm.editingMode['fullEntry']}"/>
<c:set var="numOfCols" value="6" />
<c:set var="budgetLimitAttributes" value="${DataDictionary.AwardBudgetLimit.attributes}" />
<c:set var="budgetSummary" value="${KualiForm.budgetLimitSummary}"/>
<c:set var="anchorIndex" value="100" />
<style>
.disabledImage {
    opacity : 0.4;
    filter: alpha(opacity=40); // msie
    background-color: #000;
}
</style>
<script type="text/javascript">
$jq = jQuery.noConflict();
$jq(document).ready(function() {
	$jq(".expandableLink").click(function() {
		rend(this, false);
	});
});
function expandAll() {
  $jq(".expandableLink").each(function() {
    $jq(this).find('img').attr('src', open_file);
  });
  $jq(".expandableArea").each(function() {
	$jq(this).show();
  });
}
function collapseAll() {
  $jq(".expandableLink").each(function() {
    $jq(this).find('img').attr('src', closed_file);
  });
  $jq(".expandableArea").each(function() {
	$jq(this).hide();
  });
}

</script>

  	<kul:tab tabTitle="Budget Limits (${KualiForm.document.award.awardIdAccount})" defaultOpen="false" tabErrorKey="document.award.totalCostBudgetLimit.limit,document.award.directCostBudgetLimit.limit,document.award.indirectCostBudgetLimit.limit" auditCluster="awardBudgetLimitAuditErrors" tabAuditKey="document.award.totalCostBudgetLimit.limit,document.award.directCostBudgetLimit.limit,document.award.indirectCostBudgetLimit.limit" useRiceAuditMode="true">
	<div class="tab-container" align="center">
	    <a href="#" onclick="expandAll()">
	      <img src="static/images/jquery/plus.gif"/>Expand All</a>
	    <a href="#" onclick="collapseAll()">
	      <img src="static/images/jquery/minus.gif"/>Collapse All</a>  
        <table cellpadding=0 cellspacing=0 summary="" width="100%">
            <tr>
                  <td colspan="${numOfCols}" class="subhead">
                  <span class="subhead-left">Personnel</span>
                  <span class="subhead-right"><kul:help parameterNamespace="KC-AWARD" parameterDetailType="Document" parameterName="awardBudgetLimitsHelpUrl" altText="help"/></span>
                 </td>         
            </tr>
            <tr>
                <th width="10%"><div align="center">Cost Element</div></th>
                <th width="34%"><div align="center">Description</div></th>
                <th width="14%"><div align="center">Limits</div></th>
                <th width="14%"><div align="center">Budget Change</div></th>
                <th width="14%"><div align="center">Previous Budget</div></th>
                <th width="14%"><div align="center">Budget Total</div></th>
             </tr>
             <c:set var="curTotal" value="${budgetSummary.currentSummaryTotals['personnelSalaryTotals'] == null ? 0.00 : krafn:getBigDecimal(budgetSummary.currentSummaryTotals['personnelSalaryTotals'])}" />
             <c:set var="prevTotal" value="${budgetSummary.previousSummaryTotals['personnelSalaryTotals'] == null ? 0.00 : krafn:getBigDecimal(budgetSummary.previousSummaryTotals['personnelSalaryTotals'])}" />
             <c:set var="total" value="${curTotal + prevTotal}" />
             <c:set var="personnelObjectCodes" value="${budgetSummary.combinedPersonnelObjectCodes}"/>
             <c:set var="hasItems" value="${not empty personnelObjectCodes}"/>
			 <tr>
			   <td colspan="2" class="tab-subhead">
	               <a id="A${anchorIndex}" class="${hasItems ? 'expandableLink' : 'disabledImage'}">
              	    <img src="${ConfigProperties.kr.externalizable.images.url}tinybutton-show.gif" alt="show" width=45 height=15 border=0 align=absmiddle id="F${anchorIndex}">
              	   </a>          	   
			     Salary</td>
			   <td class="tab-subhead">&nbsp;</td>
			   <td class="tab-subhead"><div align="right"><fmt:formatNumber value="${curTotal}" type="currency" currencySymbol="" maxFractionDigits="2" />&nbsp;</div></td>
			   <td class="tab-subhead"><div align="right"><fmt:formatNumber value="${prevTotal}" type="currency" currencySymbol="" maxFractionDigits="2" />&nbsp;</div></td>
			   <td class="tab-subhead"><div align="right"><fmt:formatNumber value="${total}" type="currency" currencySymbol="" maxFractionDigits="2" />&nbsp;</div></td>
			 </tr>
			 
			 
			 
			 <tbody id="G${anchorIndex}" class="expandableArea" style="display: none;">
			 <c:forEach var="objectCode" items="${personnelObjectCodes}">
			    <c:set var="currentTotal" value="${budgetSummary.currentObjectCodeTotals[objectCode] == null ? 0.00 : krafn:getBigDecimal(budgetSummary.currentObjectCodeTotals[objectCode])}"/>
			    <c:set var="previousTotal" value="${budgetSummary.previousObjectCodeTotals[objectCode] == null ? 0.00 : krafn:getBigDecimal(budgetSummary.previousObjectCodeTotals[objectCode])}"/>
			    <c:set var="total" value="${currentTotal + previousTotal}"/>
			 	<tr>
			 		<td>&nbsp;</td>
			 		<td><div align="left">${objectCode.description}</div></td>
			 		<td>&nbsp;</td>
			 		<td><div align="right"><fmt:formatNumber value="${currentTotal}" type="currency" currencySymbol=""  minIntegerDigits="1" maxFractionDigits="2" minFractionDigits="2"/>&nbsp;</div></td>
			 		<td><div align="right"><fmt:formatNumber value="${previousTotal}" type="currency" currencySymbol="" minIntegerDigits="1" maxFractionDigits="2" minFractionDigits="2"/>&nbsp;</div></td>
			 		<td><div align="right"><fmt:formatNumber value="${total}" type="currency" currencySymbol=""  minIntegerDigits="1" maxFractionDigits="2" minFractionDigits="2"/>&nbsp;</div></td>
				</tr>
			</c:forEach>
			</tbody>
			<c:set var="anchorIndex" value="${anchorIndex+1}"/>
			
			<!-- Fringe -->
             <c:set var="curTotal" value="${budgetSummary.currentSummaryTotals['personnelFringeTotals'] == null ? 0.00 : krafn:getBigDecimal(budgetSummary.currentSummaryTotals['personnelFringeTotals'])}" />
             <c:set var="prevTotal" value="${budgetSummary.previousSummaryTotals['personnelFringeTotals'] == null ? 0.00 : krafn:getBigDecimal(budgetSummary.previousSummaryTotals['personnelFringeTotals'])}" />
             <c:set var="total" value="${curTotal + prevTotal}" />
			 <tr>
			   <td colspan="2" class="tab-subhead">
	               <a id="A${anchorIndex}" class="${hasItems ? 'expandableLink' : 'disabledImage'}">
              	    <img src="${ConfigProperties.kr.externalizable.images.url}tinybutton-show.gif" alt="show" width=45 height=15 border=0 align=absmiddle id="F${anchorIndex}">
              	   </a>
			       Fringe</td>
			   <td class="tab-subhead">&nbsp;</td>
			   <td class="tab-subhead"><div align="right"><fmt:formatNumber value="${curTotal}" type="currency" currencySymbol="" maxFractionDigits="2" />&nbsp;</div></td>
			   <td class="tab-subhead"><div align="right"><fmt:formatNumber value="${prevTotal}" type="currency" currencySymbol="" maxFractionDigits="2" />&nbsp;</div></td>
			   <td class="tab-subhead"><div align="right"><fmt:formatNumber value="${total}" type="currency" currencySymbol="" maxFractionDigits="2" />&nbsp;</div></td>
			 </tr>
			 
			 <tbody id="G${anchorIndex}" class="expandableArea" style="display: none;">
			 <c:forEach var="objectCode" items="${personnelObjectCodes}">
			    <c:set var="currentTotal" value="${budgetSummary.currentObjectCodePersonnelFringeTotals[objectCode.costElement] == null ? 0.00 : krafn:getBigDecimal(budgetSummary.currentObjectCodePersonnelFringeTotals[objectCode.costElement])}"/>
			    <c:set var="previousTotal" value="${budgetSummary.previousObjectCodePersonnelFringeTotals[objectCode.costElement] == null ? 0.00 : krafn:getBigDecimal(budgetSummary.previousObjectCodePersonnelFringeTotals[objectCode.costElement])}"/>
			    <c:set var="total" value="${currentTotal + previousTotal}"/>
			 	<tr>
			 		<td>&nbsp;</td>
			 		<td><div align="left">${objectCode.description}</div></td>
			 		<td>&nbsp;</td>
			 		<td><div align="right"><fmt:formatNumber value="${currentTotal}" type="currency" currencySymbol=""  minIntegerDigits="1" maxFractionDigits="2" minFractionDigits="2"/>&nbsp;</div></td>
			 		<td><div align="right"><fmt:formatNumber value="${previousTotal}" type="currency" currencySymbol="" minIntegerDigits="1" maxFractionDigits="2" minFractionDigits="2"/>&nbsp;</div></td>
			 		<td><div align="right"><fmt:formatNumber value="${total}" type="currency" currencySymbol=""  minIntegerDigits="1" maxFractionDigits="2" minFractionDigits="2"/>&nbsp;</div></td>
				</tr>
			</c:forEach>
			</tbody>
			<c:set var="anchorIndex" value="${anchorIndex+1}"/>		
			
			<!-- Calc Direct Costs for Personnel -->
			 <c:set var="curTotal" value="${budgetSummary.currentSummaryTotals['personnelCalculatedExpenseSummaryTotals'] == null ? 0.00 : krafn:getBigDecimal(budgetSummary.currentSummaryTotals['personnelCalculatedExpenseSummaryTotals'])}" />
             <c:set var="prevTotal" value="${budgetSummary.previousSummaryTotals['personnelCalculatedExpenseSummaryTotals'] == null ? 0.00 : krafn:getBigDecimal(budgetSummary.previousSummaryTotals['personnelCalculatedExpenseSummaryTotals'])}" />
             <c:set var="total" value="${curTotal + prevTotal}" />
             <c:set var="calcRates" value="${budgetSummary.combinedPersonnelCalculatedExpenseRates}"/>
             <c:set var="hasItems" value="${not empty calcRates}"/>
			 <tr>
			   <td colspan="2" class="tab-subhead">
	               <a id="A${anchorIndex}" class="${hasItems ? 'expandableLink' : 'disabledImage'}">
              	    <img src="${ConfigProperties.kr.externalizable.images.url}tinybutton-show.gif" alt="show" width=45 height=15 border=0 align=absmiddle id="F${anchorIndex}">
              	   </a>
			       Calculated Direct Costs</td>
			   <td class="tab-subhead">&nbsp;</td>
			   <td class="tab-subhead"><div align="right"><fmt:formatNumber value="${curTotal}" type="currency" currencySymbol="" maxFractionDigits="2" />&nbsp;</div></td>
			   <td class="tab-subhead"><div align="right"><fmt:formatNumber value="${prevTotal}" type="currency" currencySymbol="" maxFractionDigits="2" />&nbsp;</div></td>
			   <td class="tab-subhead"><div align="right"><fmt:formatNumber value="${total}" type="currency" currencySymbol="" maxFractionDigits="2" />&nbsp;</div></td>
			 </tr>
			 
			 <tbody id="G${anchorIndex}" class="expandableArea" style="display: none;">
			 <c:forEach var="rate" items="${calcRates}">
			    <c:set var="currentTotal" value="${budgetSummary.currentPersonnelCalculatedExpenseTotals[rate] == null ? 0.00 : krafn:getBigDecimal(budgetSummary.currentPersonnelCalculatedExpenseTotals[rate])}"/>
			    <c:set var="previousTotal" value="${budgetSummary.previousPersonnelCalculatedExpenseTotals[rate] == null ? 0.00 : krafn:getBigDecimal(budgetSummary.previousPersonnelCalculatedExpenseTotals[rate])}"/>
			    <c:set var="total" value="${currentTotal + previousTotal}"/>
			 	<tr>
			 		<td>&nbsp;</td>
			 		<td><div align="left">${rate.rateClassPrefix} - ${rate.description}</div></td>
			 		<td>&nbsp;</td>
			 		<td><div align="right"><fmt:formatNumber value="${currentTotal}" type="currency" currencySymbol=""  minIntegerDigits="1" maxFractionDigits="2" minFractionDigits="2"/>&nbsp;</div></td>
			 		<td><div align="right"><fmt:formatNumber value="${previousTotal}" type="currency" currencySymbol="" minIntegerDigits="1" maxFractionDigits="2" minFractionDigits="2"/>&nbsp;</div></td>
			 		<td><div align="right"><fmt:formatNumber value="${total}" type="currency" currencySymbol=""  minIntegerDigits="1" maxFractionDigits="2" minFractionDigits="2"/>&nbsp;</div></td>
				</tr>
			</c:forEach>
			</tbody>
			<c:set var="anchorIndex" value="${anchorIndex+1}"/>		
              
            <c:set var="currentTotal" value="${krafn:getBigDecimal(budgetSummary.currentPersonnelTotal)}"/>
            <c:set var="previousTotal" value="${krafn:getBigDecimal(budgetSummary.previousPersonnelTotal)}"/>
            <c:set var="total" value="${currentTotal + previousTotal}"/>
            <!-- Personnel Subtotals -->
            <tr>
              <td class="infoline" colspan="2" style="text-align:right;"><strong>Personnel Subtotal</strong></td>
              <td class="infoline">&nbsp;</td>
              <td class="infoline" style="text-align:right;"><strong><fmt:formatNumber value="${currentTotal}" type="currency" currencySymbol="$"  minIntegerDigits="1" maxFractionDigits="2" minFractionDigits="2"/>&nbsp;</strong></td>
              <td class="infoline" style="text-align:right;"><strong><fmt:formatNumber value="${previousTotal}" type="currency" currencySymbol="$"  minIntegerDigits="1" maxFractionDigits="2" minFractionDigits="2"/>&nbsp;</strong></td>
              <td class="infoline" style="text-align:right;"><strong><fmt:formatNumber value="${total}" type="currency" currencySymbol="$"  minIntegerDigits="1" maxFractionDigits="2" minFractionDigits="2"/>&nbsp;</strong></td>
            </tr>
            
            <c:set var="currentNonPersonnelTotal" value="0.00"/>
            <c:set var="previousNonPersonnelTotal" value="0.00"/>
            <tr>
                  <td colspan="${numOfCols}" class="subhead"><span class="subhead-left"> NonPersonnel</span> </td>
            </tr>
            <tr>
                <th><div align="center">Cost Element</div></th>
                <th><div align="center">Description</div></th>
                <th><div align="center">Limits</div></th>
                <th><div align="center">Budget Change</div></th>
                <th><div align="center">Previous Budget</div></th>
                <th><div align="center">Budget Total</div></th>
             </tr>
			<c:forEach var="objectCodeByTypeEntry" items="${budgetSummary.combinedObjectCodeListByCategory}">
			 <c:set var="catType" value="${objectCodeByTypeEntry.key}"/>
			 <c:set var="objCodes" value="${objectCodeByTypeEntry.value}"/>
			 <c:if test="${catType.code ne 'P'}">
			 <c:set var="curTotal" value="${budgetSummary.currentSummaryTotals[catType.code] == null ? 0.00 : krafn:getBigDecimal(budgetSummary.currentSummaryTotals[catType.code])}" />
             <c:set var="prevTotal" value="${budgetSummary.previousSummaryTotals[catType.code] == null ? 0.00 : krafn:getBigDecimal(budgetSummary.previousSummaryTotals[catType.code])}" />
             <c:set var="total" value="${curTotal + prevTotal}" />
             <c:set var="currentNonPersonnelTotal" value="${currentNonPersonnelTotal + curTotal}"/>
             <c:set var="previousNonPersonnelTotal" value="${previousNonPersonnelTotal + prevTotal}"/>
             <c:set var="hasItems" value="${not empty objCodes}"/>
			 <tr>
			   <td colspan="2" class="tab-subhead">
	               <a id="A${anchorIndex}" class="${hasItems ? 'expandableLink' : 'disabledImage'}">
              	    <img src="${ConfigProperties.kr.externalizable.images.url}tinybutton-show.gif" alt="show" width=45 height=15 border=0 align=absmiddle id="F${anchorIndex}">
              	   </a>
			       ${catType.description}</td>
			   <td class="tab-subhead">&nbsp;</td>
			   <td class="tab-subhead"><div align="right"><fmt:formatNumber value="${curTotal}" type="currency" currencySymbol="" maxFractionDigits="2" />&nbsp;</div></td>
			   <td class="tab-subhead"><div align="right"><fmt:formatNumber value="${prevTotal}" type="currency" currencySymbol="" maxFractionDigits="2" />&nbsp;</div></td>
			   <td class="tab-subhead"><div align="right"><fmt:formatNumber value="${total}" type="currency" currencySymbol="" maxFractionDigits="2" />&nbsp;</div></td>
			 </tr>
			 
			 <tbody id="G${anchorIndex}" class="expandableArea" style="display: none;">
			 <c:forEach var="objectCode" items="${objCodes}">
			   <c:if test="${objectCode.costElement ne KualiForm.proposalHierarchyIndirectObjectCode}">
			    <c:set var="currentTotal" value="${budgetSummary.currentObjectCodeTotals[objectCode] == null ? 0.00 : krafn:getBigDecimal(budgetSummary.currentObjectCodeTotals[objectCode])}"/>
			    <c:set var="previousTotal" value="${budgetSummary.previousObjectCodeTotals[objectCode] == null ? 0.00 : krafn:getBigDecimal(budgetSummary.previousObjectCodeTotals[objectCode])}"/>
			    <c:set var="total" value="${currentTotal + previousTotal}"/>
			 	<tr>
			 		<td>${objectCode.costElement}</td>
			 		<td><div align="left">${objectCode.description}</div></td>
			 		<td>&nbsp;</td>
			 		<td><div align="right"><fmt:formatNumber value="${currentTotal}" type="currency" currencySymbol=""  minIntegerDigits="1" maxFractionDigits="2" minFractionDigits="2"/>&nbsp;</div></td>
			 		<td><div align="right"><fmt:formatNumber value="${previousTotal}" type="currency" currencySymbol="" minIntegerDigits="1" maxFractionDigits="2" minFractionDigits="2"/>&nbsp;</div></td>
			 		<td><div align="right"><fmt:formatNumber value="${total}" type="currency" currencySymbol=""  minIntegerDigits="1" maxFractionDigits="2" minFractionDigits="2"/>&nbsp;</div></td>
			  	</tr>
			   </c:if> 
			  </c:forEach>
			  </tbody>
			<c:set var="anchorIndex" value="${anchorIndex+1}"/>
			</c:if>			
		  </c:forEach>
		  
			<!-- Calc Direct Costs for Non-Personnel -->
			 <c:set var="curTotal" value="${budgetSummary.currentSummaryTotals['nonPersonnelCalculatedExpenseSummaryTotals'] == null ? 0.00 : krafn:getBigDecimal(budgetSummary.currentSummaryTotals['nonPersonnelCalculatedExpenseSummaryTotals'])}" />
             <c:set var="prevTotal" value="${budgetSummary.previousSummaryTotals['nonPersonnelCalculatedExpenseSummaryTotals'] == null ? 0.00 : krafn:getBigDecimal(budgetSummary.previousSummaryTotals['nonPersonnelCalculatedExpenseSummaryTotals'])}" />
             <c:set var="total" value="${curTotal + prevTotal}" />
             <c:set var="currentNonPersonnelTotal" value="${currentNonPersonnelTotal + curTotal}"/>
             <c:set var="previousNonPersonnelTotal" value="${previousNonPersonnelTotal + prevTotal}"/>
             <c:set var="calcRates" value="${budgetSummary.combinedNonPersonnelCalculatedExpenseRates}"/>
             <c:set var="hasItems" value="${not empty calcRates}"/>             
			 <tr>
			   <td colspan="2" class="tab-subhead">
	               <a id="A${anchorIndex}" class="${hasItems ? 'expandableLink' : 'disabledImage'}">
              	    <img src="${ConfigProperties.kr.externalizable.images.url}tinybutton-show.gif" alt="show" width=45 height=15 border=0 align=absmiddle id="F${anchorIndex}">
              	   </a>
			       Calculated Direct Costs</td>
			   <td class="tab-subhead">&nbsp;</td>
			   <td class="tab-subhead"><div align="right"><fmt:formatNumber value="${curTotal}" type="currency" currencySymbol="" maxFractionDigits="2" />&nbsp;</div></td>
			   <td class="tab-subhead"><div align="right"><fmt:formatNumber value="${prevTotal}" type="currency" currencySymbol="" maxFractionDigits="2" />&nbsp;</div></td>
			   <td class="tab-subhead"><div align="right"><fmt:formatNumber value="${total}" type="currency" currencySymbol="" maxFractionDigits="2" />&nbsp;</div></td>
			 </tr>
			 
			 <tbody id="G${anchorIndex}" class="expandableArea" style="display: none;">
			 <c:forEach var="rate" items="${calcRates}">
			    <c:set var="currentTotal" value="${budgetSummary.currentNonPersonnelCalculatedExpenseTotals[rate] == null ? 0.00 : krafn:getBigDecimal(budgetSummary.currentNonPersonnelCalculatedExpenseTotals[rate])}"/>
			    <c:set var="previousTotal" value="${budgetSummary.previousNonPersonnelCalculatedExpenseTotals[rate] == null ? 0.00 : krafn:getBigDecimal(budgetSummary.previousNonPersonnelCalculatedExpenseTotals[rate])}"/>
			    <c:set var="total" value="${currentTotal + previousTotal}"/>
			 	<tr>
			 		<td>&nbsp;</td>
			 		<td><div align="left">${rate.rateClassPrefix} - ${rate.description}</div></td>
			 		<td>&nbsp;</td>
			 		<td><div align="right"><fmt:formatNumber value="${currentTotal}" type="currency" currencySymbol=""  minIntegerDigits="1" maxFractionDigits="2" minFractionDigits="2"/>&nbsp;</div></td>
			 		<td><div align="right"><fmt:formatNumber value="${previousTotal}" type="currency" currencySymbol="" minIntegerDigits="1" maxFractionDigits="2" minFractionDigits="2"/>&nbsp;</div></td>
			 		<td><div align="right"><fmt:formatNumber value="${total}" type="currency" currencySymbol=""  minIntegerDigits="1" maxFractionDigits="2" minFractionDigits="2"/>&nbsp;</div></td>
				</tr>
			</c:forEach>
			</tbody>
			<c:set var="anchorIndex" value="${anchorIndex+1}"/>	
				
		    <!-- Non-Personnel Subtotals -->
            <tr>
              <td class="infoline" colspan="2" style="text-align:right;"><strong>Non-Personnel Subtotal</strong></td>
              <td class="infoline">&nbsp;</td>
              <td class="infoline" style="text-align:right;"><strong><fmt:formatNumber value="${currentNonPersonnelTotal}" type="currency" currencySymbol="$"  minIntegerDigits="1" maxFractionDigits="2" minFractionDigits="2"/>&nbsp;</strong></td>
              <td class="infoline" style="text-align:right;"><strong><fmt:formatNumber value="${previousNonPersonnelTotal}" type="currency" currencySymbol="$"  minIntegerDigits="1" maxFractionDigits="2" minFractionDigits="2"/>&nbsp;</strong></td>
              <td class="infoline" style="text-align:right;"><strong><fmt:formatNumber value="${currentNonPersonnelTotal + previousNonPersonnelTotal}" type="currency" currencySymbol="$"  minIntegerDigits="1" maxFractionDigits="2" minFractionDigits="2"/>&nbsp;</strong></td>
            </tr>
		 
			
            <tr>
                  <td colspan="${numOfCols}" class="subhead"><span class="subhead-left"> Totals&nbsp;</span> </td>
            </tr>
            <tr>
              <c:set var="curDirect" value="${krafn:getBigDecimal(budgetSummary.currentBudget.totalDirectCost)}"/>
              <c:set var="prevDirect" value="${krafn:getBigDecimal(budgetSummary.previousBudget.totalDirectCostInclPrev)}"/>
              <td colspan="2" class="infoline" style="text-align:right;"><strong>TOTAL DIRECT COSTS</strong></td>
              <td class="infoline" style="text-align:right;"><strong><kul:htmlControlAttribute property="awardBudgetLimitsBean.directCostBudgetLimit" attributeEntry="${budgetLimitAttributes.limit}" styleClass="amount" readOnly="${readOnly}"/></strong></td>
              <td class="infoline" style="text-align:right;"><strong><fmt:formatNumber value="${curDirect}" type="currency" currencySymbol="$"  minIntegerDigits="1" maxFractionDigits="2" minFractionDigits="2"/></strong></td>
              <td class="infoline" style="text-align:right;"><strong><fmt:formatNumber value="${prevDirect}" type="currency" currencySymbol="$"  minIntegerDigits="1" maxFractionDigits="2" minFractionDigits="2"/></strong></td>
              <td class="infoline" style="text-align:right;"><strong><fmt:formatNumber value="${curDirect + prevDirect}" type="currency" currencySymbol="$"  minIntegerDigits="1" maxFractionDigits="2" minFractionDigits="2"/></strong></td>
            </tr>
            <tr>
              <c:set var="curDirect" value="${krafn:getBigDecimal(budgetSummary.currentBudget.totalIndirectCost)}"/>
              <c:set var="prevDirect" value="${krafn:getBigDecimal(budgetSummary.previousBudget.totalIndirectCostInclPrev)}"/>
              <td colspan="2" class="infoline" style="text-align:right;"><strong>TOTAL F&A COSTS</strong></td>
              <td class="infoline" style="text-align:right;"><strong><kul:htmlControlAttribute property="awardBudgetLimitsBean.indirectCostBudgetLimit" attributeEntry="${budgetLimitAttributes.limit}" styleClass="amount" readOnly="${readOnly}"/></strong></td>
              <td class="infoline" style="text-align:right;"><strong><fmt:formatNumber value="${curDirect}" type="currency" currencySymbol="$"  minIntegerDigits="1" maxFractionDigits="2" minFractionDigits="2"/></strong></td>
              <td class="infoline" style="text-align:right;"><strong><fmt:formatNumber value="${prevDirect}" type="currency" currencySymbol="$"  minIntegerDigits="1" maxFractionDigits="2" minFractionDigits="2"/></strong></td>
              <td class="infoline" style="text-align:right;"><strong><fmt:formatNumber value="${curDirect + prevDirect}" type="currency" currencySymbol="$"  minIntegerDigits="1" maxFractionDigits="2" minFractionDigits="2"/></strong></td>
            </tr> 
            <tr>
              <c:set var="curDirect" value="${krafn:getBigDecimal(budgetSummary.currentBudget.totalCost)}"/>
              <c:set var="prevDirect" value="${krafn:getBigDecimal(budgetSummary.previousBudget.totalCostInclPrev)}"/>
              <td colspan="2" class="infoline" style="text-align:right;"><strong>TOTAL COSTS</strong></td>
              <td class="infoline" style="text-align:right;"><strong><kul:htmlControlAttribute property="awardBudgetLimitsBean.totalCostBudgetLimit" attributeEntry="${budgetLimitAttributes.limit}" styleClass="amount" readOnly="${readOnly}"/></strong></td>
              <td class="infoline" style="text-align:right;"><strong><fmt:formatNumber value="${curDirect}" type="currency" currencySymbol="$"  minIntegerDigits="1" maxFractionDigits="2" minFractionDigits="2"/></strong></td>
              <td class="infoline" style="text-align:right;"><strong><fmt:formatNumber value="${prevDirect}" type="currency" currencySymbol="$"  minIntegerDigits="1" maxFractionDigits="2" minFractionDigits="2"/></strong></td>
              <td class="infoline" style="text-align:right;"><strong><fmt:formatNumber value="${curDirect + prevDirect}" type="currency" currencySymbol="$"  minIntegerDigits="1" maxFractionDigits="2" minFractionDigits="2"/></strong></td>
            </tr>
            
        </table>
    </div>
</kul:tab>
<kul:panelFooter />
