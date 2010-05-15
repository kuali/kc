<%--
 Copyright 2005-2010 The Kuali Foundation

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
    <c:set var="numOfCols" value="5" />
<c:set var="awardBudgetAttributes" value="${DataDictionary.AwardBudgetExt.attributes}" />

  	<kul:tab tabTitle="Budget Limits (${KualiForm.document.award.awardIdAccount})" defaultOpen="false" >
	<div class="tab-container" align="center">
        <table cellpadding=0 cellspacing=0 summary="" width="100%">
            <tr>
                  <td colspan="${numOfCols}" class="subhead"><span class="subhead-left"> Personnel&nbsp;</span> </td>
            </tr>
            <tr>
               <%-- <th><div align="center">*Cost Element</div></th> --%>
                <th><div align="center">Description</div></th>
                <th><div align="center">Limits</div></th>
                <th><div align="center">Budget Change</div></th>
                <th><div align="center">Previous Budget</div></th>
                <th><div align="center">Obligated Total</div></th>
             </tr>
             
             <c:forEach var="personnelBudgetLimit" items="${KualiForm.personnelBudgetLimits}" varStatus="limitStatus">
		         <tr>
		             <c:choose>
		            <c:when test="${limitStatus.index < 3}">
		             <td>
		               ${KualiForm.personnelLabel[limitStatus.index]}
		             </td>
		            </c:when>
		            <c:otherwise>
		             <td><div align="right">
		               <b>${KualiForm.personnelLabel[limitStatus.index]}</b>
		               </div>
		             </td>
		            </c:otherwise>
		             </c:choose>
		             <td >
		               &nbsp;
		             </td>
		                <c:forEach var="budgetLimit" items="${personnelBudgetLimit}" varStatus="status">
					                <td>
					                	<div align="right">  	
					                		<fmt:formatNumber value="${budgetLimit}" type="currency" currencySymbol="" maxFractionDigits="2" />
					                	</div>
					                </td>
		        	    </c:forEach> 
		           </tr> 	    
		      </c:forEach>
            
            
            
            <tr>
                  <td colspan="${numOfCols}" class="subhead"><span class="subhead-left"> NonPersonnel&nbsp;</span> </td>
            </tr>
            <tr>
                <th><div align="center">Description</div></th>
                <th><div align="center">Limits</div></th>
                <th><div align="center">Budget Change</div></th>
                <th><div align="center">Previous Budget</div></th>
                <th><div align="center">Obligated Total</div></th>
             </tr>

             <c:forEach var="nonPersonnelBudgetLimit" items="${KualiForm.nonPersonnelBudgetLimits}" varStatus="limitStatus">
		         <tr>
		             <c:choose>
		            <c:when test="${limitStatus.index < 5}">
		             <td>
		               ${KualiForm.nonPersonnelLabel[limitStatus.index]}
		             </td>
		            </c:when>
		            <c:otherwise>
		             <td><div align="right">
		               <b>${KualiForm.nonPersonnelLabel[limitStatus.index]}</b>
		               </div>
		             </td>
		            </c:otherwise>
		             </c:choose>
		             <td >
		               &nbsp;
		             </td>
		                <c:forEach var="budgetLimit" items="${nonPersonnelBudgetLimit}" varStatus="status">
					                <td>
					                	<div align="right">  	
					                		<fmt:formatNumber value="${budgetLimit}" type="currency" currencySymbol="" maxFractionDigits="2" />
					                	</div>
					                </td>
		        	    </c:forEach> 
		           </tr> 	    
		      </c:forEach>


            <tr>
                  <td colspan="${numOfCols}" class="subhead"><span class="subhead-left"> Totals&nbsp;</span> </td>
            </tr>
                         <c:forEach var="totalBudgetLimit" items="${KualiForm.totalBudgetLimits}" varStatus="limitStatus">
		         <tr>
		             <td><div align="right">
		               <b>${KualiForm.totalLabel[limitStatus.index]}</b>
		               </div>
		             </td>
		             <c:choose>
		            <c:when test="${limitStatus.index == 2}">
		             <td>
		                 <div align="right">
		                  <kul:htmlControlAttribute property="document.budgetVersionOverview.totalCostLimit" attributeEntry="${awardBudgetAttributes.totalCostLimit}"/>
		                  </div>
		             </td>
		            </c:when>
		            <c:otherwise>
		             <td>&nbsp;
		             </td>
		            </c:otherwise>
		             </c:choose>
		                <c:forEach var="budgetLimit" items="${totalBudgetLimit}" varStatus="status">
					                <td>
					                	<div align="right">  	
					                		<fmt:formatNumber value="${budgetLimit}" type="currency" currencySymbol="" maxFractionDigits="2" />
					                	</div>
					                </td>
		        	    </c:forEach> 
		           </tr> 	    
		      </c:forEach>
            
        </table>
    </div>
</kul:tab>
<kul:panelFooter />