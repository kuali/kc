<%--
 Copyright 2007 The Kuali Foundation.
 
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

<c:set var="budgetAttributes" value="${DataDictionary.BudgetDocument.attributes}" />
<c:set var="budgetPeriodAttributes" value="${DataDictionary.BudgetPeriod.attributes}" />
<c:set var="action" value="budgetSummary" />
<kul:tab tabTitle="Budget Periods &amp; Totals" defaultOpen="true" tabErrorKey="newBudgetPeriod*,document.budgetPeriod*">
	<div class="tab-container" align="center">
    	<div class="h2-container">
    		<span class="subhead-left"><h2>Budget Periods</h2></span>
    		<span class="subhead-right"><kul:help businessObjectClassName="fillMeIn" altText="help"/></span>
        </div>
        
        <table cellpadding=0 cellspacing=0 summary="">
          	<tr>
          		<th width="5%"><div align="center">&nbsp</div></th> 
          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetPeriodAttributes.startDate}" noColon="true" /></div></th>
          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetPeriodAttributes.endDate}" noColon="true" /></div></th>
          		<th width="15%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetPeriodAttributes.totalCost}" noColon="true" /></div></th>
          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetPeriodAttributes.totalDirectCost}" noColon="true" /></div></th>
          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetPeriodAttributes.totalIndirectCost}"noColon="true" /></div></th>
          		<th width="15%"> <div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetPeriodAttributes.underrecoveryAmount}" noColon="true" /></div></th>
          		<th width="15%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetPeriodAttributes.costSharingAmount}" noColon="true" /></div></th>
              	<kul:htmlAttributeHeaderCell literalLabel="Action" scope="col"/>
          	
          	</tr>        
             <tr>
				<th class="infoline">
					<c:out value="Add:" />
				</th>

                <td valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newBudgetPeriod.startDate" attributeEntry="${budgetPeriodAttributes.startDate}" datePicker="true"/>
                	</div>
				</td>
                <td valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newBudgetPeriod.endDate" attributeEntry="${budgetPeriodAttributes.endDate}" datePicker="true"/>
                	</div>
                </td>
                <td valign="middle" class="infoline">                	
                	<div align="center">
                  	<kul:htmlControlAttribute property="newBudgetPeriod.totalCost" attributeEntry="${budgetPeriodAttributes.totalCost}" styleClass="amount" /> 
                	</div>
				</td>
                <td valign="middle" class="infoline">
                	<div align="center">
	                <kul:htmlControlAttribute property="newBudgetPeriod.totalDirectCost" attributeEntry="${budgetPeriodAttributes.totalDirectCost}" styleClass="amount"/> 
                	</div>
                </td>
                <td valign="middle" class="infoline">
                 	<div align="center">
             	    <kul:htmlControlAttribute property="newBudgetPeriod.totalIndirectCost" attributeEntry="${budgetPeriodAttributes.totalIndirectCost}" styleClass="amount"/> 
                	</div>
                </td>
                <td valign="middle" class="infoline">
                 	<div align="center">
	                <kul:htmlControlAttribute property="newBudgetPeriod.underrecoveryAmount" attributeEntry="${budgetPeriodAttributes.underrecoveryAmount}" styleClass="amount"/> 
                	</div>
                </td>
                <td valign="middle" class="infoline">
                	<div align="center">
 	                <kul:htmlControlAttribute property="newBudgetPeriod.costSharingAmount" attributeEntry="${budgetPeriodAttributes.costSharingAmount}" styleClass="amount"/> 
                	</div>
                </td>
				<td class="infoline">
					<div align=center>
						<html:image property="methodToCall.addBudgetPeriod.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' />
					</div>
                </td>
            </tr>
            
        	<c:forEach var="budgetPeriods" items="${KualiForm.document.budgetPeriods}" varStatus="status">
				  <bean:define id="readOnly" name="KualiForm" property="document.budgetPeriods[${status.index}].budgetLineItemStatus" />
				  <c:if test="${readOnly == 'Yes'}">
				  		<c:set var="readOnly" value="true"/>
				  </c:if>
	             <tr>
					<th class="infoline">
						<c:out value="${status.index+1}" />
					</th>
	                <td valign="middle">
					<div align=center>
                		<kul:htmlControlAttribute property="document.budgetPeriods[${status.index}].startDate" attributeEntry="${budgetPeriodAttributes.startDate}" datePicker="true"/>
					</div>
					</td>
	                <td valign="middle">
					<div align=center>
                		<kul:htmlControlAttribute property="document.budgetPeriods[${status.index}].endDate" attributeEntry="${budgetPeriodAttributes.endDate}" datePicker="true"/>
					</div>
	                </td>
	                <td valign="middle">                	
					<div align=center>
                  		<kul:htmlControlAttribute property="document.budgetPeriods[${status.index}].totalCost" attributeEntry="${budgetPeriodAttributes.totalCost}" styleClass="amount" readOnly="${readOnly}"/> 
					</div>
					</td>
	                <td valign="middle">                	
					<div align=center>
                  		<kul:htmlControlAttribute property="document.budgetPeriods[${status.index}].totalDirectCost" attributeEntry="${budgetPeriodAttributes.totalDirectCost}" styleClass="amount" readOnly="${readOnly}"/> 
					</div>
					</td>
	                <td valign="middle">
					<div align=center>
	                	<kul:htmlControlAttribute property="document.budgetPeriods[${status.index}].totalIndirectCost" attributeEntry="${budgetPeriodAttributes.totalIndirectCost}" styleClass="amount"  readOnly="${readOnly}"/>
					</div>
	                </td>
	                <td valign="middle">
					<div align=center>
	                	<kul:htmlControlAttribute property="document.budgetPeriods[${status.index}].underrecoveryAmount" attributeEntry="${budgetPeriodAttributes.underrecoveryAmount}" styleClass="amount" readOnly="${readOnly}"/>
					</div>
	                </td>
	                <td valign="middle">
					<div align=center>
	                	<kul:htmlControlAttribute property="document.budgetPeriods[${status.index}].costSharingAmount" attributeEntry="${budgetPeriodAttributes.costSharingAmount}" styleClass="amount" readOnly="${readOnly}"/>
					</div>
	                </td>
					<td>
					<div align=center>
						<html:image property="methodToCall.deleteBudgetPeriod.line${status.index}.anchor${currentTabIndex}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' />
					</div>
	                </td>
	            </tr>
        	</c:forEach>        
        </table>
    	<div class="h2-container">
    		<span class="subhead-left"><h2>Totals</h2></span>
        </div>
        <table cellpadding=0 cellspacing=0 summary="">
            <tr>
          		<td width="5%" class="infoline"> 
          			<div align="center">
          				&nbsp
          			</div> 
          		</td> 
                <td width="10%" valign="middle" class="infoline">
                	<div align="center">
                		<strong> <fmt:formatDate value="${KualiForm.document.summaryPeriodStartDate}" pattern="MM/dd/yyyy" /> </strong>
                	</div>
				</td>
                <td width="10%" valign="middle" class="infoline">
                	<div align="center">
                		<strong> <fmt:formatDate value="${KualiForm.document.summaryPeriodEndDate}" pattern="MM/dd/yyyy" /> </strong>
                	</div>
                </td>
                <td width="15%" valign="middle" class="infoline">                	
                	<div align="center">
                		<strong> ${KualiForm.document.totalCost} </strong>
                	</div>
				</td>
                <td width="10%" valign="middle" class="infoline">
                	<div align="center">
                		<strong> ${KualiForm.document.totalDirectCost} </strong>
                	</div>
                </td>
                <td width="10%" valign="middle" class="infoline">
                 	<div align="center">
                		<strong> ${KualiForm.document.totalIndirectCost} </strong>
                	</div>
                </td>
                <td width="15%" valign="middle" class="infoline">
                 	<div align="center">
                		<strong> ${KualiForm.document.underrecoveryAmount} </strong>
                	</div>
                </td>
                <td width="15%" valign="middle" class="infoline">
                	<div align="center">
                		<strong> ${KualiForm.document.costSharingAmount} </strong>
                	</div>
                </td>
				<td width="10%" class="infoline">
					<div align=center>
						<html:image property="methodToCall.recalculateBudgetPeriod.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-recalculate.gif' />
					</div>
                </td>
            </tr>
        </table>
    </div> 
</kul:tab>
<kul:panelFooter />
