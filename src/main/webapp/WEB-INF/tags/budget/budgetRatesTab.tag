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

<%@ attribute name="budgetProposalRate" description="Budget proposal and la rates" required="true" %>
<%@ attribute name="rateClassType" description="rate class type code" required="true" %>
<c:set var="budgetProposalRatesAttributes" value="${DataDictionary.BudgetProposalRate.attributes}" />
<c:set var="action" value="budgetRates" />

				  <bean:define id="irateClassType" name="KualiForm" property="${budgetProposalRate}.rateClass.rateClassType"/>
				  <bean:define id="displayRow" name="KualiForm" property="${budgetProposalRate}.displayLocation"/>
    			  <c:if test="${irateClassType == rateClassType && displayRow == 'Yes'}">
                  <tr>
                    <td width="10%" class="${tdClass}">
                    	<div align=left>
                    	<span class="copy">
                    		<bean:write name="KualiForm" property="${budgetProposalRate}.rateType.description"/>
                      	</span>
                      	</div>
                    </td>
                    <td width="10%" class="${tdClass}">
                    	<div align=left>
                    	<span class="copy">
                    		<bean:write name="KualiForm" property="${budgetProposalRate}.onOffCampusFlag"/>
                      	</span>
                      	</div>
                    </td>
                    <td width="10%" class="${tdClass}">
                    	<div align=left>
                    	<span class="copy">
                    		<bean:write name="KualiForm" property="${budgetProposalRate}.fiscalYear"/>
                      	</span>
                      	</div>
                    </td>
                    <td width="15%" class="${tdClass}">
                    	<div align=left>
                    	<span class="copy">
					        &nbsp;
		                    <bean:write name="KualiForm" property="${budgetProposalRate}.affectedBudgetPeriod"/>
                      	</span>
                      	</div>
                    </td>
                    <td width="10%" class="${tdClass}">
                    	<div align=center>
                    	<span class="copy">
	                    	<bean:write name="KualiForm" property="${budgetProposalRate}.startDate"/>
                      	</span>
                      	</div>
                    </td>
                    <td width="10%" class="${tdClass}">
                    	<div align=center>
                    	<span class="copy">
	                    	<bean:write name="KualiForm" property="${budgetProposalRate}.instituteRate"/>
                      	</span>
                      	</div>
                    </td>
                    <td width="10%" class="${tdClass}">
                    	<div align=center>
                    	<span class="copy">
                			<kul:htmlControlAttribute property="${budgetProposalRate}.applicableRate" attributeEntry="${budgetProposalRatesAttributes.applicableRate}" />
                      	</span>
                      	</div>
                    </td>
                  </tr>
				  <c:set var="rowIndex" value="${rowIndex+1}" />
                  </c:if>
