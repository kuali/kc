<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<c:set var="numOfCols" value="${fn:length(KualiForm.document.budgetPeriods)+3}" /> 
<kul:tabTop tabTitle="Totals" defaultOpen="true" >
	<div class="tab-container" align="center">
        <table cellpadding=0 cellspacing=0 summary="">
          <tr>
                  <td colspan="${numOfCols}" class="subhead" ><span class="subhead-left"> Expenses&nbsp;</span> </td>
            </tr>
        
        	<tr>
                <th><div align="center">Object Code</div></th>
                <th><div align="center">Description</div></th>
                <c:forEach var="period" items="${KualiForm.document.budgetPeriods}" varStatus="status">
                	<th><div align="center">Period ${period.budgetPeriod}</div></th>
        	    </c:forEach>        
                <th><div align="center">Total</div></th>
            </tr>
           <c:forEach var="objectCodeTotal" items="${KualiForm.document.objectCodeTotals}" varStatus="objStatus" > 
        	<tr>
                <td>
                	${objectCodeTotal.key.costElement}
                </td>
                <td>
                	${objectCodeTotal.key.description}
                </td>
                <c:set var="cumTotal" value="0.00" />
                
                <c:forEach var="periodTotal" items="${objectCodeTotal.value}" varStatus="objPeriodStatus">
	                <td>
	                	<div align="right">  	
	                		<fmt:formatNumber value="${periodTotal}" type="currency" currencySymbol="" maxFractionDigits="2" />
	                	</div>
	                	<c:set var="cumTotal" value = "${cumTotal + periodTotal }" />
	                </td>
        	    </c:forEach>        
	                <td>
	                	<div align="right">  	
	                		<fmt:formatNumber value="${cumTotal}" type="currency" currencySymbol="" maxFractionDigits="2" />
	                	</div>
	                </td>
                
        	 </tr>
			</c:forEach>


        
           <tr> 
             <td colspan="${numOfCols}" class="subhead" >
            	Calculated Expenses
             </td>
           </tr>
           <c:forEach var="calculatedExpenseTotal" items="${KualiForm.document.calculatedExpenseTotals}" > 
        	<tr>
                <td>
                	
                </td>
                <td>
                	${calculatedExpenseTotal.key.rateClassPrefix} - ${calculatedExpenseTotal.key.description}
                </td>
                <c:set var="cumTotal" value="0.00" />
                <c:forEach var="periodTotal" items="${calculatedExpenseTotal.value}" varStatus="status">
	                <td>
	                	<div align="right">  	
	                		<fmt:formatNumber value="${periodTotal}" type="currency" currencySymbol="" maxFractionDigits="2" />
	                	</div>
	                	<c:set var="cumTotal" value = "${cumTotal + periodTotal }" />
	                </td>
        	    </c:forEach>        
	                <td>
	                	<div align="right">  	
	                		<fmt:formatNumber value="${cumTotal}" type="currency" currencySymbol="" maxFractionDigits="2" />
	                	</div>
	                </td>
                
        	 </tr>
			</c:forEach>

            <tr> 
              <td colspan="${numOfCols}" class="subhead" >
            	 Totals
              </td>
            </tr>
            <tr>
              <td>
              </td>
              <td>
              </td>
              <c:set var="cumTotal" value="0.00" />
              <c:forEach var="period" items="${KualiForm.document.budgetPeriods}" varStatus="status">
			     <td>
	                	<div align="right">  		                		
	                		<fmt:formatNumber value="${period.expenseTotal}" type="currency" currencySymbol="" maxFractionDigits="2" />
	                	</div>
	                	<c:set var="cumTotal" value = "${cumTotal + period.expenseTotal }" />
			     </td>
		      </c:forEach>
	                <td>
	                	<div align="right">  	
	                		<fmt:formatNumber value="${cumTotal}" type="currency" currencySymbol="" maxFractionDigits="2" />
	                	</div>
	                </td>
               </tr>

        </table>
        
    </div>
</kul:tabTop>
