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
<%@ attribute name="headerDispatch" required="false" %>
<c:set var="budgetPeriodGroupMax" value="<%=org.kuali.kra.infrastructure.Constants.BUDGET_SUMMARY_PERIOD_GROUP_SIZE%>" />

<c:set var="periodStartIndex" value='<%=request.getAttribute("startIndex")%>' />
<c:set var="periodEndIndex" value='<%=request.getAttribute("endIndex")%>' />

<c:if test="${(empty periodStartIndex or periodStartIndex == '') && (empty periodEndIndex or periodEndIndex == '') }">
	<c:set var="periodStartIndex" value="0" />
	<c:if test="${fn:length(KualiForm.document.budget.budgetPeriods) >= budgetPeriodGroupMax}" >
		<c:set var="periodEndIndex" value="${budgetPeriodGroupMax-1}" />
	</c:if> 
	<c:if test="${fn:length(KualiForm.document.budget.budgetPeriods) < budgetPeriodGroupMax}" >
		<c:set var="periodEndIndex" value="${fn:length(KualiForm.document.budget.budgetPeriods)-1}" />
	</c:if> 
</c:if>

<input type="hidden" name="periodStartIndex" value="${periodStartIndex}" />
<input type="hidden" name="periodEndIndex" value="${periodEndIndex}" />
<html:hidden name="KualiForm" property="viewBudgetPeriod" value="1" />

<c:set var="numOfCols" value="${(periodEndIndex-periodStartIndex)+1+4 	}" /> 
<jsp:useBean id="indirectCostMap" class="java.util.HashMap" scope="request" />
<jsp:useBean id="mtdcCostMap" class="java.util.HashMap" scope="request" />
<jsp:useBean id="nonPersonnelSubTotalsMap" class="java.util.HashMap" scope="request" />
<jsp:useBean id="personnelSubTotalsMap" class="java.util.HashMap" scope="request" />

<c:set var="anchorIndex" value="1" />

<c:forEach var="period" items="${KualiForm.document.budget.budgetPeriods}" varStatus="status">
	<c:set var="periodTotalVar" value="period${status.index}" />
	<c:set target="${nonPersonnelSubTotalsMap}" property="${periodTotalVar}" value="0.00" />
	<c:set target="${personnelSubTotalsMap}" property="${periodTotalVar}" value="0.00" />
</c:forEach>

<kul:tabTop tabTitle="Summary" defaultOpen="true" >
	<div class="tab-container" align="center">
        <table cellpadding=0 cellspacing=0 summary="" width="100%">
            <tr>
                  <td colspan="${numOfCols}" class="subhead"><span class="subhead-left"> Summary&nbsp;</span> </td>
            </tr>
             <tr>
                  <td colspan="3" rowspan="2" width="30%" class="infoline">&nbsp; </td>
                  <td colspan="${(periodEndIndex-periodStartIndex)+1}" width="60%" class="infoline" >
                  	<div align="center">
                  		&nbsp;
                  		<c:if test="${periodStartIndex == 0 or periodStartIndex == '0' }" >
							<html:image property="methodToCall.previousPeriodSet" disabled="true"  
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-back1.gif' />
						</c:if> 
						<c:if test="${periodStartIndex > 0 or periodStartIndex != '0' }" >
                   			<html:image property="methodToCall.previousPeriodSet" onclick="javascript: previousPeriodSet();"  
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-back.gif' />
						</c:if>
						&nbsp;
						<c:if test="${periodEndIndex == (fn:length(KualiForm.document.budget.budgetPeriods)-1) }" >
							<html:image property="methodToCall.nextPeriodSet"  disabled="true"  
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-next1.gif' />
						</c:if>
						<c:if test="${periodEndIndex < (fn:length(KualiForm.document.budget.budgetPeriods)-1) }" >
							<html:image property="methodToCall.nextPeriodSet"  onclick="javascript: nextPeriodSet();"  
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-next.gif' />
						</c:if>
						&nbsp;
					</div>
                  </td>
                  <th colspan="1" rowspan="2" width="10%" class="infoline" >Total</th>
            </tr>
            <tr>
            	<c:forEach var="period" items="${KualiForm.document.budget.budgetPeriods}" varStatus="status" begin="${periodStartIndex}" end="${periodEndIndex}">
                	<th nowrap>
                		<div>
                			Period ${period.budgetPeriod}<br/>
                			<span class="fineprint">
                				<fmt:formatDate value="${period.startDate}" pattern="MM/dd/yyyy" /> - <fmt:formatDate value="${period.endDate}" pattern="MM/dd/yyyy" />
                			</span>
                		</div>
                	</th>
        	    </c:forEach>
            </tr>
            <tr>
                  <td colspan="${numOfCols}" class="subhead" >
                  	<span class="subhead-left"> Personnel&nbsp;
                  		<html:image property="methodToCall.headerTab.headerDispatch.${headerDispatch}.navigateTo.personnel.x"
									src='${ConfigProperties.kra.externalizable.images.url}edit.gif' />
                  	</span> 
                  </td>
            </tr>
           
           <tr>
           	  <td class="tab-subhead" width="5%">
           	  		<a id="A${anchorIndex}" onclick="rend(this, false)">
              			<img src="${ConfigProperties.kr.externalizable.images.url}tinybutton-show.gif" alt="show" width=45 height=15 border=0 align=absmiddle id="F${anchorIndex}">
              		</a>
           	  </td>
              <td colspan="2" class="tab-subhead" >Salary</td>
              <c:set var="personnelSalaryTotals" value="${KualiForm.document.budget.budgetSummaryTotals['personnelSalaryTotals']}" />
              <c:set var="personnelSalaryCumulativeTotals" value="0.00" />
              <c:forEach var="period" items="${KualiForm.document.budget.budgetPeriods}" varStatus="status">
                	<c:set var="periodTotalVar" value="period${status.index}" />
               		<c:set target="${personnelSubTotalsMap}" property="${periodTotalVar}" value="${personnelSubTotalsMap[periodTotalVar] + personnelSalaryTotals[period.budgetPeriod-1]}" />

              		<c:if test="${status.index ge periodStartIndex and status.index le periodEndIndex }" >
		           		<td class="tab-subhead" >
		           			<div align="right">
		           				<fmt:formatNumber value="${personnelSalaryTotals[period.budgetPeriod-1]}" type="currency" currencySymbol="" maxFractionDigits="2" />&nbsp;
		           			</div>
		           		</td>
		           	</c:if>
	           		<c:set var="personnelSalaryCumulativeTotals" value = "${personnelSalaryCumulativeTotals + personnelSalaryTotals[period.budgetPeriod-1] }" />
	          </c:forEach>
              <td  align="right" class="tab-subhead">
				<div align="right">
					<strong><fmt:formatNumber value="${personnelSalaryCumulativeTotals}" type="currency" currencySymbol="" maxFractionDigits="2" />&nbsp;</strong>
				</div>
			 </td>
            </tr>
            
            <c:set var="objectCodeListByBudgetCategoryType" value="${KualiForm.document.budget.objectCodeListByBudgetCategoryType}" />
            <c:set var="personnelObjectCodes" />
            <c:forEach var="objectCodeMapEntry" items="${objectCodeListByBudgetCategoryType}" varStatus="mapIndex">
            	<c:set var="categoryType" value="${objectCodeMapEntry.key}" /> 
            	<c:if test="${categoryType.budgetCategoryTypeCode eq 'P'}">
            		<c:set var="personnelObjectCodes" value="${objectCodeMapEntry.value}" />
            	</c:if>
			</c:forEach>
			
			<c:set var="firstCellRowSpan" value="0" />
			<c:forEach var="personnelObjectCode" items="${personnelObjectCodes}" varStatus="objStatus" >
				<c:set var="firstCellRowSpan" value="${firstCellRowSpan+1}" />
				<c:set var="personnelList" value="${KualiForm.document.budget.objectCodePersonnelList[personnelObjectCode]}" />
	            <c:forEach var="person" items="${personnelList}" varStatus="personStatus" >
	            	<c:set var="firstCellRowSpan" value="${firstCellRowSpan+1}" />
				</c:forEach>	                 
			</c:forEach>
			            
            <tbody id="G${anchorIndex}" style="display: none;">
	           	<c:forEach var="personnelObjectCode" items="${personnelObjectCodes}" varStatus="objStatus" >
			        <c:set var="summarySalaryTotals" value="${KualiForm.document.budget.objectCodePersonnelSalaryTotals[personnelObjectCode.costElement]}" />
			        <c:if test="${summarySalaryTotals != null}">
			         	<c:set var="firstCellRowSpan" value="${firstCellRowSpan+1}" />
			        </c:if>
		        	 <tr>
		        	 	<c:if test="${objStatus.index == 0}">
		        	    	<td width="5%" rowspan="${firstCellRowSpan}">&nbsp;</td>
		        	    </c:if>
		                <th colspan="2" width="20%"><div align="left"><strong>${personnelObjectCode.description}</strong></div></td>
		                
		        	    <c:set var="cumPersonnelObjCodeTotal" value="0.00" />
						<c:forEach var="periodPersonnelObjCodeTotal" items="${KualiForm.document.budget.objectCodeTotals[personnelObjectCode]}" varStatus="objPeriodStatus" >
							<c:set var="periodTotalVar" value="period${objPeriodStatus.index}" />
							<c:if test="${objPeriodStatus.index ge periodStartIndex and objPeriodStatus.index le periodEndIndex }" >
								<th>
									<div align="right">	
										<fmt:formatNumber value="${periodPersonnelObjCodeTotal}" type="currency" currencySymbol="" maxFractionDigits="2" />
									</div>
								</th>
							</c:if>
							<c:set var="cumPersonnelObjCodeTotal" value = "${cumPersonnelObjCodeTotal + periodPersonnelObjCodeTotal }" />
						</c:forEach>   
						
						<th width="10%">
							<div align="right">  	
								<fmt:formatNumber value="${cumPersonnelObjCodeTotal}" type="currency" currencySymbol="" maxFractionDigits="2" />
							</div>
						</th>
		        	    
		        	 </tr>
	        	 
	                 <c:set var="personnelList" value="${KualiForm.document.budget.objectCodePersonnelList[personnelObjectCode]}" />
	                 <c:forEach var="person" items="${personnelList}" varStatus="personStatus" >
	                 		<c:set var="personSalaryTotalsMapKey" value="${personnelObjectCode.costElement},${person.personId}" />
	                  		<c:set var="personSalaryTotals" value="${KualiForm.document.budget.objectCodePersonnelSalaryTotals[personSalaryTotalsMapKey]}" />
	                  		<tr>
	                  			
				                <td width="12%">
				                	<div align="left">&nbsp;&nbsp;${person.budgetPerson.personName}</div>
				                </td>
				                <td width="13%" align="left">
				                	<div align="left">&nbsp;&nbsp;${person.budgetPerson.role}&nbsp;</div>
				                </td>
				                <c:set var="personSalaryCumulativeTotals" value="0.00" />
				                <c:forEach var="period" items="${KualiForm.document.budget.budgetPeriods}" varStatus="status" >
				                	<c:set var="periodTotalVar" value="period${status.index}" />
			                		<c:if test="${status.index ge periodStartIndex and status.index le periodEndIndex }" >
					                  	<td>
						                  	<div align="right">&nbsp;
							                  	<fmt:formatNumber value="${personSalaryTotals[period.budgetPeriod-1]}" type="currency" currencySymbol="" maxFractionDigits="2" />
						                  	</div>
					                  	</td>
					                </c:if>
				                  	<c:set var="personSalaryCumulativeTotals" value = "${personSalaryCumulativeTotals + personSalaryTotals[period.budgetPeriod-1] }" />
				        	    </c:forEach>
				        	    
				        	    <td width="10%">
									<div align="right">
										<fmt:formatNumber value="${personSalaryCumulativeTotals}" type="currency" currencySymbol="" maxFractionDigits="2" />&nbsp;
									</div>
								</td>
				        	 </tr>
	                 </c:forEach>

	                 <c:if test="${summarySalaryTotals != null}">
	                 	<tr>
	                 		<td><div align="left">&nbsp;&nbsp;Summary Line Item</div></td>
	                 		<td><div align="left">&nbsp;&nbsp;</div></td>   
			                <c:set var="summarySalaryCumulativeTotals" value = "0.00" />
			                <c:forEach var="period" items="${KualiForm.document.budget.budgetPeriods}" varStatus="status" >
			                	<c:set var="periodTotalVar" value="period${status.index}" />
		                		<c:if test="${status.index ge periodStartIndex and status.index le periodEndIndex }" >
			                 		<td><div align="right">&nbsp;
			                 			<fmt:formatNumber value="${summarySalaryTotals[period.budgetPeriod-1]}" type="currency" currencySymbol="" maxFractionDigits="2" />
			                 		</div></td>
				                </c:if>
			                  	<c:set var="summarySalaryCumulativeTotals" value = "${summarySalaryCumulativeTotals + summarySalaryTotals[period.budgetPeriod-1] }" />
			                </c:forEach>
			        	    <td width="10%">
								<div align="right">
									<fmt:formatNumber value="${summarySalaryCumulativeTotals}" type="currency" currencySymbol="" maxFractionDigits="2" />&nbsp;
								</div>
							</td>
			        	 </tr>
				     </c:if>   
				</c:forEach>
			</tbody>
			
			<c:set var="anchorIndex" value="${anchorIndex+1}" />
			
			<tr>
			  <td class="tab-subhead" width="5%">
			   <a id="A${anchorIndex}" onclick="rend(this, false)">
              	<img src="${ConfigProperties.kr.externalizable.images.url}tinybutton-show.gif" alt="show" width=45 height=15 border=0 align=absmiddle id="F${anchorIndex}">
              	</a>
			  </td>
              <td colspan="2" width="25%" class="tab-subhead" >Fringe</td>
              <c:set var="personnelFringeTotals" value="${KualiForm.document.budget.budgetSummaryTotals['personnelFringeTotals']}" />
              <c:set var="personnelFringeCumulativeTotals" value="0.00" />
              <c:forEach var="period" items="${KualiForm.document.budget.budgetPeriods}" varStatus="status" >
                	<c:set var="periodTotalVar" value="period${status.index}" />
               		<c:set target="${personnelSubTotalsMap}" property="${periodTotalVar}" value="${personnelSubTotalsMap[periodTotalVar] + personnelFringeTotals[period.budgetPeriod-1]}" />
	              <c:if test="${status.index ge periodStartIndex and status.index le periodEndIndex }" >
		           		<td class="tab-subhead" >
		           			<div align="right">
		           				<fmt:formatNumber value="${personnelFringeTotals[period.budgetPeriod-1]}" type="currency" currencySymbol="" maxFractionDigits="2" />&nbsp;
		           			</div>
		           		</td>
		          </c:if>
		          <c:set var="personnelFringeCumulativeTotals" value = "${personnelFringeCumulativeTotals + personnelFringeTotals[period.budgetPeriod-1] }" />
	          </c:forEach>
	          
              <td  align="right" class="tab-subhead">
					<div align="right">
						<strong><fmt:formatNumber value="${personnelFringeCumulativeTotals}" type="currency" currencySymbol="" maxFractionDigits="2" />&nbsp;</strong>
					</div>
				</td>
            </tr>
            
            <tbody id="G${anchorIndex}" style="display: none;">
	           	<c:forEach var="personnelObjectCode" items="${personnelObjectCodes}" varStatus="objStatus" >
			        <c:set var="summaryFringeTotals" value="${KualiForm.document.budget.objectCodePersonnelFringeTotals[personnelObjectCode.costElement]}" />
			        <c:if test="${summaryFringeTotals != null}">
			         	<c:set var="firstCellRowSpan" value="${firstCellRowSpan+1}" />
			        </c:if>
		        	 <tr>
		        	 	<c:if test="${objStatus.index == 0}">
		        	 		<td width="5%" rowspan="${firstCellRowSpan}">&nbsp;</td>
		        	 	</c:if>
		                <th colspan="2" width="25%"><div align="left"><strong>${personnelObjectCode.description}</strong></div></td>
		        	    
		        	     <c:set var="personnelList" value="${KualiForm.document.budget.objectCodePersonnelList[personnelObjectCode]}" />
						 <c:set var="personFringeCumulativeTotals" value="0.00" />
 						 <c:forEach var="period" items="${KualiForm.document.budget.budgetPeriods}" varStatus="status" >
			        	     <c:set var="periodFringeCumulativeTotals" value="0.00" />
							 <c:forEach var="person" items="${personnelList}" varStatus="personStatus" >
									<c:set var="personFringeTotalsMapKey" value="${personnelObjectCode.costElement},${person.personId}" />
									<c:set var="personFringeTotals" value="${KualiForm.document.budget.objectCodePersonnelFringeTotals[personFringeTotalsMapKey]}" />
									<c:set var="periodFringeCumulativeTotals" value = "${periodFringeCumulativeTotals + personFringeTotals[period.budgetPeriod-1] }" />
							 </c:forEach>
							 
							 <c:if test="${fn:length(personnelList) == 0}">
									<c:set var="personFringeTotalsMapKey" value="${personnelObjectCode.costElement}" />
									<c:set var="personFringeTotals" value="${KualiForm.document.budget.objectCodePersonnelFringeTotals[personFringeTotalsMapKey]}" />
									<c:set var="periodFringeCumulativeTotals" value = "${periodFringeCumulativeTotals + personFringeTotals[period.budgetPeriod-1] }" />
							 </c:if>
							 
							 <c:if test="${status.index ge periodStartIndex and status.index le periodEndIndex }" >
							 	<th><div align="right"><fmt:formatNumber value="${periodFringeCumulativeTotals}" type="currency" currencySymbol="" maxFractionDigits="2" />&nbsp;</div></th>
							 </c:if>
							 <c:set var="personFringeCumulativeTotals" value = "${personFringeCumulativeTotals + periodFringeCumulativeTotals }" />
						 </c:forEach> 
						 
						 <th width="10%"><div align="right"><fmt:formatNumber value="${personFringeCumulativeTotals}" type="currency" currencySymbol="" maxFractionDigits="2" />&nbsp;</div></th>
		        	 </tr>
	        	 
	                 <c:set var="personnelList" value="${KualiForm.document.budget.objectCodePersonnelList[personnelObjectCode]}" />
	                 <c:forEach var="person" items="${personnelList}" varStatus="personStatus" >
	                 		<c:set var="personFringeTotalsMapKey" value="${personnelObjectCode.costElement},${person.personId}" />
	                  		<c:set var="personFringeTotals" value="${KualiForm.document.budget.objectCodePersonnelFringeTotals[personFringeTotalsMapKey]}" />
	                  		<tr>
	                  			
				                <td width="12%">
				                	<div align="left">&nbsp;&nbsp;${person.budgetPerson.personName}</div>
				                </td>
				                <td width="13%" align="left">
				                	<div align="left">&nbsp;&nbsp;${person.budgetPerson.role}&nbsp;</div>
				                </td>
				                
				                <c:set var="personFringeCumulativeTotals" value="0.00" />
				                <c:forEach var="period" items="${KualiForm.document.budget.budgetPeriods}" varStatus="status" >
				                	<c:set var="periodTotalVar" value="period${status.index}" />
				                	<c:if test="${status.index ge periodStartIndex and status.index le periodEndIndex }" >
					                  	<td>
					                  		<div align="right">&nbsp;
						                  		<fmt:formatNumber value="${personFringeTotals[period.budgetPeriod-1]}" type="currency" currencySymbol="" maxFractionDigits="2" />
					                  		</div>
					                  	</td>
					                </c:if>
				                  	<c:set var="personFringeCumulativeTotals" value = "${personFringeCumulativeTotals + personFringeTotals[period.budgetPeriod-1] }" />
				        	    </c:forEach>
				        	    
				        	    <td width="10%">
				        	    	<div align="right">
										<fmt:formatNumber value="${personFringeCumulativeTotals}" type="currency" currencySymbol="" maxFractionDigits="2" />&nbsp;
									</div>
				        	    </td>
				        	 </tr>
	                 </c:forEach> 

	                 <c:if test="${summaryFringeTotals != null}">
	                 	<tr>
	                 		<td><div align="left">&nbsp;&nbsp;Summary Line Item</div></td>
	                 		<td><div align="left">&nbsp;&nbsp;</div></td>   
			                <c:set var="summaryFringeCumulativeTotals" value = "0.00" />
			                <c:forEach var="period" items="${KualiForm.document.budget.budgetPeriods}" varStatus="status" >
			                	<c:set var="periodTotalVar" value="period${status.index}" />
		                		<c:if test="${status.index ge periodStartIndex and status.index le periodEndIndex }" >
			                 		<td><div align="right">&nbsp;
			                 			<fmt:formatNumber value="${summaryFringeTotals[period.budgetPeriod-1]}" type="currency" currencySymbol="" maxFractionDigits="2" />
			                 		</div></td>
				                </c:if>
			                  	<c:set var="summaryFringeCumulativeTotals" value = "${summaryFringeCumulativeTotals + summaryFringeTotals[period.budgetPeriod-1] }" />
			                </c:forEach>
			        	    <td width="10%">
								<div align="right">
									<fmt:formatNumber value="${summaryFringeCumulativeTotals}" type="currency" currencySymbol="" maxFractionDigits="2" />&nbsp;
								</div>
							</td>
			        	 </tr>
				     </c:if>   

				</c:forEach>
			</tbody>
			
			<c:set var="anchorIndex" value="${anchorIndex+1}" />
			<tr>
				<td class="tab-subhead" width="5%">
				<a id="A${anchorIndex}" onclick="rend(this, false)">
				<img src="${ConfigProperties.kr.externalizable.images.url}tinybutton-show.gif" alt="show" width=45 height=15 border=0 align=absmiddle id="F${anchorIndex}">
				</a>
				</td>
				<td colspan="2" class="tab-subhead" >Calculated Direct Costs</td>
				<c:set var="personnelCalculatedExpenseSummaryTotals" value="${KualiForm.document.budget.budgetSummaryTotals['personnelCalculatedExpenseSummaryTotals']}" />
				<c:set var="personnelCalculatedExpenseSummaryCumulativeTotals" value="0.00" />
              	<c:forEach var="period" items="${KualiForm.document.budget.budgetPeriods}" varStatus="status" >
                	<c:set var="periodTotalVar" value="period${status.index}" />
               		<c:set target="${personnelSubTotalsMap}" property="${periodTotalVar}" value="${personnelSubTotalsMap[periodTotalVar] + personnelCalculatedExpenseSummaryTotals[period.budgetPeriod-1]}" />
              		<c:if test="${status.index ge periodStartIndex and status.index le periodEndIndex }" >
		           		<td class="tab-subhead" >
		           			<div align="right">
		           				<fmt:formatNumber value="${personnelCalculatedExpenseSummaryTotals[period.budgetPeriod-1]}" type="currency" currencySymbol="" maxFractionDigits="2" />&nbsp;
		           			</div>
		           		</td>
		           	</c:if>
	           		<c:set var="personnelCalculatedExpenseSummaryCumulativeTotals" value = "${personnelCalculatedExpenseSummaryCumulativeTotals + personnelCalculatedExpenseSummaryTotals[period.budgetPeriod-1] }" />
	          	</c:forEach>
				<td  align="right" class="tab-subhead">
					<div align="right">
						<strong><fmt:formatNumber value="${personnelCalculatedExpenseSummaryCumulativeTotals}" type="currency" currencySymbol="" maxFractionDigits="2" />&nbsp;</strong>
					</div>
				</td>
		   </tr>

		   <c:set var="firstCellRowSpan" value="0" />
		   <c:set var="directExpenseCounter" value="0" /> 
		   <c:forEach var="calculatedExpenseTotal" items="${KualiForm.document.budget.personnelCalculatedExpenseTotals}" >
		   		<c:if test="${not empty calculatedExpenseTotal.key.rateClass.rateClassType && calculatedExpenseTotal.key.rateClass.rateClassType ne 'O'}">
		   			<c:set var="firstCellRowSpan" value="${firstCellRowSpan + 1}" />
		   		</c:if>
		   </c:forEach>
			
		   <tbody id="G${anchorIndex}" style="display: none;">			            
	           <c:forEach var="calculatedExpenseTotal" items="${KualiForm.document.budget.personnelCalculatedExpenseTotals}" >
	           		<c:if test="${not empty calculatedExpenseTotal.key.rateClass.rateClassType && calculatedExpenseTotal.key.rateClass.rateClassType eq 'O'}">
		                <c:forEach var="periodTotal" items="${calculatedExpenseTotal.value}" varStatus="status">
		                	<c:set var="calculatedIndirectExpenseVar" value="calculatedIndirectExpense${status.index}" />
		                	<c:set target="${indirectCostMap}" property="calculatedIndirectExpense${status.index}" value="0.00" />
			                <c:set target="${indirectCostMap}" property="calculatedIndirectExpense${status.index}" value="${indirectCostMap[calculatedIndirectExpenseVar] + periodTotal}" />
		        	    </c:forEach> 
	           			<c:if test="${calculatedExpenseTotal.key.rateClass.description eq 'MTDC'}" >
			               	<c:forEach var="periodTotal" items="${calculatedExpenseTotal.value}" varStatus="status">
				               	<c:set var="mtdcVar" value="mtdc${status.index}" />
				               	<c:set target="${mtdcCostMap}" property="mtdc${status.index}" value="0.00" />
				               	<c:set target="${mtdcCostMap}" property="mtdc${status.index}" value="${mtdcCostMap[mtdcVar] + periodTotal}" />
				             </c:forEach> 
			             </c:if>
	           		</c:if>
	           		<c:if test="${not empty calculatedExpenseTotal.key.rateClass.rateClassType && calculatedExpenseTotal.key.rateClass.rateClassType ne 'O'}">
			        	<tr>
				        	<c:if test="${directExpenseCounter == 0}">
			        	 		<td width="5%" rowspan="${firstCellRowSpan}">&nbsp;</td>
			        	 	</c:if>
			                <td colspan="2" width="20%">
			                	${calculatedExpenseTotal.key.rateClassPrefix} - ${calculatedExpenseTotal.key.description}
			                </td>
			                <c:set var="cumTotal" value="0.00" />
			                <c:forEach var="periodTotal" items="${calculatedExpenseTotal.value}" varStatus="status" >
			                    <c:set var="periodTotalVar" value="period${status.index}" />
			                	<c:if test="${status.index ge periodStartIndex and status.index le periodEndIndex }" >
					                <td>
					                	<div align="right">  	
					                		<fmt:formatNumber value="${periodTotal}" type="currency" currencySymbol="" maxFractionDigits="2" />
					                	</div>
					                </td>
					            </c:if>
				                <c:set var="cumTotal" value = "${cumTotal + periodTotal }" />
			        	    </c:forEach>        
			                <td>
			                	<div align="right">  	
			                		<fmt:formatNumber value="${cumTotal}" type="currency" currencySymbol="" maxFractionDigits="2" />
			                	</div>
			                </td>
			        	 </tr>
			        	 <c:set var="directExpenseCounter" value="${directExpenseCounter+1}" /> 
		        	 </c:if>
				</c:forEach>
			</tbody>
			
			<tr>
        	 	<th width="5%" class="infoline">&nbsp;</th>
                <td colspan="2" width="20%"><strong>Personnel Subtotal</strong></td>
                <c:set var="cumPersonnelTotal" value="0.00" />
        	    <c:forEach var="period" items="${KualiForm.document.budget.budgetPeriods}" varStatus="status" >
        	    	<c:set var="periodTotalVar" value="period${status.index}" />
        	    	<c:if test="${status.index ge periodStartIndex and status.index le periodEndIndex }" >
						<td><div align="right"><i><fmt:formatNumber value="${personnelSubTotalsMap[periodTotalVar]}" type="currency" currencySymbol="" maxFractionDigits="2" />&nbsp;</i></div></td>
					</c:if>
					<c:set var="cumPersonnelTotal" value = "${cumPersonnelTotal + personnelSubTotalsMap[periodTotalVar] }" />
				</c:forEach>    
                <td>
                	<div align="right">  	
                      <i>
                		<fmt:formatNumber value="${cumPersonnelTotal}" type="currency" currencySymbol="" maxFractionDigits="2" />
                      </i>
                	</div>
                </td>
        	 </tr>
			
			<tr>
                  <td colspan="${numOfCols}" class="subhead" >
                  	   <span class="subhead-left"> Non-Personnel&nbsp;
                  	   		<html:image property="methodToCall.headerTab.headerDispatch.${headerDispatch}.navigateTo.expenses.x"
									src='${ConfigProperties.kra.externalizable.images.url}edit.gif' />
                  	   </span> 
                  </td>
            </tr>
            
            <c:forEach var="objectCodeMapEntry" items="${objectCodeListByBudgetCategoryType}" varStatus="mapIndex">
            <c:set var="categoryType" value="${objectCodeMapEntry.key}" /> 
	            <c:if test="${categoryType.budgetCategoryTypeCode ne 'P'}" >
						<c:set var="anchorIndex" value="${anchorIndex+1}" />
			            <tr>
			           	  <td class="tab-subhead" width="5%">
			           	  		<a id="A${anchorIndex}" onclick="rend(this, false)">
			              			<img src="${ConfigProperties.kr.externalizable.images.url}tinybutton-show.gif" alt="show" width=45 height=15 border=0 align=absmiddle id="F${anchorIndex}">
			              		</a>
			           	  </td>
			              <td colspan="2" class="tab-subhead" >${categoryType.description}</td>
			              <c:set var="nonPersonnelSummaryTotals" value="${KualiForm.document.budget.budgetSummaryTotals[categoryType.budgetCategoryTypeCode]}" />
			              <c:set var="nonPersonnelCumulativeTotals" value="0.00" />
			              <c:forEach var="period" items="${KualiForm.document.budget.budgetPeriods}" varStatus="status" >
				              	<c:if test="${status.index ge periodStartIndex and status.index le periodEndIndex }" >
						           	<td class="tab-subhead" >
										<div align="right">
											<fmt:formatNumber value="${nonPersonnelSummaryTotals[period.budgetPeriod-1]}" type="currency" currencySymbol="" maxFractionDigits="2" />&nbsp;
										</div>
									</td>
								</c:if>
								<c:set var="nonPersonnelCumulativeTotals" value = "${nonPersonnelCumulativeTotals + nonPersonnelSummaryTotals[period.budgetPeriod-1] }" />
				          </c:forEach>
			              <td  align="right" class="tab-subhead">
							  <div align="right">
									<strong><fmt:formatNumber value="${nonPersonnelCumulativeTotals}" type="currency" currencySymbol="" maxFractionDigits="2" />&nbsp;</strong>
							  </div>
						  </td>
			            </tr>
			            <c:set var="nonPersonnelObjectCodes" value="${objectCodeListByBudgetCategoryType[categoryType]}" />
			            
			            <tbody id="G${anchorIndex}" style="display: none;">
				           	<c:forEach var="nonPersonnelObjectCode" items="${nonPersonnelObjectCodes}" varStatus="objStatus" >
					        	 <tr>
									<c:if test="${objStatus.index == 0}">
					        	 		<td width="5%" rowspan="${fn:length(nonPersonnelObjectCodes)}">&nbsp;</td>
					        	 	</c:if>
					                <td colspan="2" width="20%"><strong>${nonPersonnelObjectCode.description}</strong></td>
					                
					                <c:set var="cumTotal" value="0.00" />
					                <c:forEach var="periodTotal" items="${KualiForm.document.budget.objectCodeTotals[nonPersonnelObjectCode]}" varStatus="objPeriodStatus" >
					                	<c:set var="periodTotalVar" value="period${objPeriodStatus.index}" />
					                	<c:if test="${objPeriodStatus.index ge periodStartIndex and objPeriodStatus.index le periodEndIndex }" >
							                <td>
							                	<div align="right">  	
							                		<fmt:formatNumber value="${periodTotal}" type="currency" currencySymbol="" maxFractionDigits="2" />
							                	</div>
							                </td>
							            </c:if>
						                <c:set var="cumTotal" value = "${cumTotal + periodTotal }" />
						                <c:set target="${nonPersonnelSubTotalsMap}" property="${periodTotalVar}" value="${nonPersonnelSubTotalsMap[periodTotalVar] + periodTotal}" />
					        	    </c:forEach>        
					                <td>
					                	<div align="right">  	
					                		<fmt:formatNumber value="${cumTotal}" type="currency" currencySymbol="" maxFractionDigits="2" />
					                	</div>
					                </td>
					                
					        	 </tr>
							</c:forEach>
						</tbody>
					</c:if>
			</c:forEach>	

		   <c:set var="anchorIndex" value="${anchorIndex+1}" />
		   <tr>
				<td class="tab-subhead" width="5%">
				<a id="A${anchorIndex}" onclick="rend(this, false)">
				<img src="${ConfigProperties.kr.externalizable.images.url}tinybutton-show.gif" alt="show" width=45 height=15 border=0 align=absmiddle id="F${anchorIndex}">
				</a>
				</td>
				<td colspan="2" class="tab-subhead" >Calculated Direct Costs</td>
				<c:set var="nonPersonnelCalculatedExpenseSummaryTotals" value="${KualiForm.document.budget.budgetSummaryTotals['nonPersonnelCalculatedExpenseSummaryTotals']}" />
				<c:set var="nonPersonnelCalculatedExpenseSummaryCumulativeTotals" value="0.00" />
              	<c:forEach var="period" items="${KualiForm.document.budget.budgetPeriods}" varStatus="status" >
              		<c:if test="${status.index ge periodStartIndex and status.index le periodEndIndex }" >
		           		<td class="tab-subhead" >
		           			<div align="right">
		           				<fmt:formatNumber value="${nonPersonnelCalculatedExpenseSummaryTotals[period.budgetPeriod-1]}" type="currency" currencySymbol="" maxFractionDigits="2" />&nbsp;
		           			</div>
		           		</td>
		           	</c:if>
	           		<c:set var="nonPersonnelCalculatedExpenseSummaryCumulativeTotals" value = "${nonPersonnelCalculatedExpenseSummaryCumulativeTotals + nonPersonnelCalculatedExpenseSummaryTotals[period.budgetPeriod-1] }" />
	          	</c:forEach>
				<td  align="right" class="tab-subhead">
					<div align="right">
						<strong><fmt:formatNumber value="${nonPersonnelCalculatedExpenseSummaryCumulativeTotals}" type="currency" currencySymbol="" maxFractionDigits="2" />&nbsp;</strong>
					</div>
				</td>
		   </tr>
		   
		   <c:set var="firstCellRowSpan" value="0" />
		   <c:set var="directExpenseCounter" value="0" /> 
		   <c:forEach var="calculatedExpenseTotal" items="${KualiForm.document.budget.nonPersonnelCalculatedExpenseTotals}" >
		   		<c:if test="${not empty calculatedExpenseTotal.key.rateClass.rateClassType && calculatedExpenseTotal.key.rateClass.rateClassType ne 'O'}">
		   			<c:set var="firstCellRowSpan" value="${firstCellRowSpan + 1}" />
		   		</c:if>
		   </c:forEach>
		   
		   <tbody id="G${anchorIndex}" style="display: none;">	
		   				            
	           <c:forEach var="calculatedExpenseTotal" items="${KualiForm.document.budget.nonPersonnelCalculatedExpenseTotals}" >
	           		<c:if test="${not empty calculatedExpenseTotal.key.rateClass.rateClassType && calculatedExpenseTotal.key.rateClass.rateClassType eq 'O'}">
		               <c:forEach var="periodTotal" items="${calculatedExpenseTotal.value}" varStatus="status">
		               		<c:set var="calculatedIndirectExpenseVar" value="calculatedIndirectExpense${status.index}" />
		               		<c:set target="${indirectCostMap}" property="calculatedIndirectExpense${status.index}" value="${0.00 + indirectCostMap[calculatedIndirectExpenseVar] + periodTotal}" />
						</c:forEach> 
						<c:if test="${calculatedExpenseTotal.key.rateClass.description eq 'MTDC'}" >
			               	<c:forEach var="periodTotal" items="${calculatedExpenseTotal.value}" varStatus="status">
				               	<c:set var="mtdcVar" value="mtdc${status.index}" />
				               	<c:set target="${mtdcCostMap}" property="mtdc${status.index}" value="${0.00 + mtdcCostMap[mtdcVar] + periodTotal}" />
				             </c:forEach> 
			             </c:if> 
	           		</c:if>
		           <c:if test="${not empty calculatedExpenseTotal.key.rateClass.rateClassType && calculatedExpenseTotal.key.rateClass.rateClassType ne 'O'}"> 
			        	<tr>
			                <c:if test="${directExpenseCounter == 0}">
			        	 		<td width="5%" rowspan="${firstCellRowSpan}">&nbsp;</td>
			        	 	</c:if>
			                <td colspan="2" width="20%">
			                	${calculatedExpenseTotal.key.rateClassPrefix} - ${calculatedExpenseTotal.key.description}
			                </td>
			                <c:set var="cumTotal" value="0.00" />
			                <c:forEach var="periodTotal" items="${calculatedExpenseTotal.value}" varStatus="status" >
			                	<c:if test="${status.index ge periodStartIndex and status.index le periodEndIndex }" >
					                <td>
					                	<div align="right">  	
					                		<fmt:formatNumber value="${periodTotal}" type="currency" currencySymbol="" maxFractionDigits="2" />
					                	</div>
					                </td>
					            </c:if>
			                	<c:set var="cumTotal" value = "${cumTotal + periodTotal }" />
				                <c:set var="periodTotalVar" value="period${status.index}" />
				                <c:set target="${nonPersonnelSubTotalsMap}" property="${periodTotalVar}" value="${nonPersonnelSubTotalsMap[periodTotalVar] + periodTotal}" />
			        	    </c:forEach>        
			                <td>
			                	<div align="right">  	
			                		<fmt:formatNumber value="${cumTotal}" type="currency" currencySymbol="" maxFractionDigits="2" />
			                	</div>
			                </td>
			        	 </tr>
			        	 <c:set var="directExpenseCounter" value="${directExpenseCounter+1}" /> 
			         </c:if>
				</c:forEach>
			</tbody>

			<tr>
        	 	<th width="5%" class="infoline">&nbsp;</th>
                <td colspan="2" width="20%"><strong>Non-Personnel Subtotal</strong></td>
                <c:set var="cumNonPersonnelTotal" value="0.00" />
        	    <c:forEach var="period" items="${KualiForm.document.budget.budgetPeriods}" varStatus="status" >
        	    	<c:set var="periodTotalVar" value="period${status.index}" />
        	    	<c:if test="${status.index ge periodStartIndex and status.index le periodEndIndex }" >
						<td><div align="right"><i><fmt:formatNumber value="${nonPersonnelSubTotalsMap[periodTotalVar]}" type="currency" currencySymbol="" maxFractionDigits="2" />&nbsp;</i></div></td>
					</c:if>
					<c:set var="cumNonPersonnelTotal" value = "${cumNonPersonnelTotal + nonPersonnelSubTotalsMap[periodTotalVar] }" />
				</c:forEach>    
                <td>
                	<div align="right">  	
                      <i>
                		<fmt:formatNumber value="${cumNonPersonnelTotal}" type="currency" currencySymbol="" maxFractionDigits="2" />
                      </i>
                	</div>
                </td>
        	 </tr>
			
			<tr>
                  <td colspan="${numOfCols}" class="subhead" ><span class="subhead-left"> Totals&nbsp;</span> </td>
            </tr>
            
        	<tr>
                <td width="5%" rowspan="3" class="infoline">&nbsp;</td>
                <td colspan="2" width="20%" class="infoline"><strong>TOTAL DIRECT COSTS</strong></td>
                <c:set var="cumTotal" value="0.00" />
        	    <c:forEach var="period" items="${KualiForm.document.budget.budgetPeriods}" varStatus="status" >
        	    	<c:set var="periodTotalVar" value="period${status.index}" />
        	    	<c:if test="${status.index ge periodStartIndex and status.index le periodEndIndex }" >
						<td class="infoline"><div align="right"><strong><fmt:formatNumber value="${personnelSubTotalsMap[periodTotalVar] + nonPersonnelSubTotalsMap[periodTotalVar]}" type="currency" currencySymbol="" maxFractionDigits="2" />&nbsp;</strong></div></td>
					</c:if>
					<c:set var="cumTotal" value = "${cumTotal + personnelSubTotalsMap[periodTotalVar] + nonPersonnelSubTotalsMap[periodTotalVar]}" />
				</c:forEach>    
                <td class="infoline">
                	<div align="right">  	
                      <strong>
                		<fmt:formatNumber value="${cumTotal}" type="currency" currencySymbol="" maxFractionDigits="2" />
                      </strong>
                	</div>
                </td>
        	 </tr>
        	 
        	 <tr>
                <td colspan="2" width="20%" class="infoline">
                	<strong>TOTAL F&amp;A COSTS</strong>
                </td>
                <c:set var="cumTotal" value="0.00" />
        	    <c:forEach var="period" items="${KualiForm.document.budget.budgetPeriods}" varStatus="status" >
        	    	<c:set var="calculatedIndirectExpenseVar" value="calculatedIndirectExpense${status.index}" />
        	    	<c:if test="${status.index ge periodStartIndex and status.index le periodEndIndex }" >
						<td class="infoline">
						<div align="right">
							<strong><fmt:formatNumber value="${indirectCostMap[calculatedIndirectExpenseVar]}" type="currency" currencySymbol="" maxFractionDigits="2" />&nbsp;</strong>
						</div>
						</td>
					</c:if>
					<c:set var="cumTotal" value = "${cumTotal + indirectCostMap[calculatedIndirectExpenseVar]}" />
				</c:forEach>    
                <td class="infoline">
                	<div align="right">  	
                      <strong>
                		<fmt:formatNumber value="${cumTotal}" type="currency" currencySymbol="" maxFractionDigits="2" />
                      </strong>
                	</div>
                </td>
        	 </tr>
        	 
        	 <tr>
                <td colspan="2" width="20%" class="infoline"><strong>TOTAL COSTS</strong></td>
                <c:set var="cumTotal" value="0.00" />
        	    <c:forEach var="period" items="${KualiForm.document.budget.budgetPeriods}" varStatus="status" >
        	    	<c:set var="periodTotalVar" value="period${status.index}" />
        	    	<c:set var="calculatedIndirectExpenseVar" value="calculatedIndirectExpense${status.index}" />
        	    	<c:if test="${status.index ge periodStartIndex and status.index le periodEndIndex }" >
						<td class="infoline"><div align="right"><strong><fmt:formatNumber value="${personnelSubTotalsMap[periodTotalVar] + nonPersonnelSubTotalsMap[periodTotalVar] + indirectCostMap[calculatedIndirectExpenseVar]}" type="currency" currencySymbol="" maxFractionDigits="2" />&nbsp;</strong></div></td>
					</c:if>
					<c:set var="cumTotal" value = "${cumTotal + personnelSubTotalsMap[periodTotalVar] + nonPersonnelSubTotalsMap[periodTotalVar] + indirectCostMap[calculatedIndirectExpenseVar]}" />
				</c:forEach>    
                <td class="infoline">
                	<div align="right">  	
                      <strong>
                		<fmt:formatNumber value="${cumTotal}" type="currency" currencySymbol="" maxFractionDigits="2" />
                      </strong>
                	</div>
                </td>
        	 </tr>
        	 
        	 
		</table>
    </div>
</kul:tabTop>
<kul:panelFooter />
