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

<c:set var="budgetAttributes" value="${DataDictionary.Budget.attributes}" />
<c:set var="budgetPeriodAttributes" value="${DataDictionary.BudgetPeriod.attributes}" />
<c:set var="action" value="budgetParameters" />
<kul:tab tabTitle="Budget Periods & Totals" defaultOpen="true" 
	tabErrorKey="newBudgetPeriod*,document.budget.budgetPeriod*" 
	auditCluster="budgetPeriodProjectDateAuditErrors,budgetPeriodProjectDateAuditWarnings,awardBudgetTotalCostAuditErrors,awardBudgetCostLimitAuditErrors"  
	tabAuditKey="document.budget.budgetPeriod*,document.budget.totalCost,document.budget.totalDirectCost,document.budget.totalIndirectCost" 
	useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<h3>
            <span class="subhead-left">Budget Periods</span>
            <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.budget.parameters.BudgetPeriod" altText="help"/></span>
        </h3>
        
        <table cellpadding="0" cellspacing="0" summary="">
          	<tr>
          		<th width="5%"><div align="center">&nbsp;</div></th> 
          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetPeriodAttributes.startDate}" noColon="true" /></div></th>
          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetPeriodAttributes.endDate}" noColon="true" /></div></th>
          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetPeriodAttributes.totalCost}" noColon="true" /></div></th>
          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetPeriodAttributes.totalDirectCost}" noColon="true" /></div></th>
          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetPeriodAttributes.totalIndirectCost}"noColon="true" /></div></th>
          		<th width="10%"> <div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetPeriodAttributes.underrecoveryAmount}" noColon="true" /></div></th>
          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetPeriodAttributes.costSharingAmount}" noColon="true" /></div></th>
          		<th width="9%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetPeriodAttributes.totalCostLimit}" noColon="true" /></div></th>
          		<th width="9%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetPeriodAttributes.directCostLimit}" noColon="true" /></div></th>
          		<th width="7%"><div align="center">Actions</div></th>
          	
          	</tr> 
          	
          	<kra:section permission="modifyBudgets">       
             <tr>
				<th width="5%" class="infoline">
					<c:out value="Add:" />
				</th>

                <td width="10%" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newBudgetPeriod.startDate" attributeEntry="${budgetPeriodAttributes.startDate}" />
                	</div>
				</td>
                <td width="10%" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newBudgetPeriod.endDate" attributeEntry="${budgetPeriodAttributes.endDate}" />
                	</div>
                </td>
                <td width="10%" valign="middle" class="infoline">                	
                	<div align="center">
                  	<kul:htmlControlAttribute property="newBudgetPeriod.totalCost" attributeEntry="${budgetPeriodAttributes.totalCost}" styleClass="amount" /> 
                	</div>
				</td>
                <td width="10%" valign="middle" class="infoline">
                	<div align="center">
	                <kul:htmlControlAttribute property="newBudgetPeriod.totalDirectCost" attributeEntry="${budgetPeriodAttributes.totalDirectCost}" styleClass="amount"/> 
                	</div>
                </td>
                <td width="10%" valign="middle" class="infoline">
                 	<div align="center">
             	    <kul:htmlControlAttribute property="newBudgetPeriod.totalIndirectCost" attributeEntry="${budgetPeriodAttributes.totalIndirectCost}" styleClass="amount"/> 
                	</div>
                </td>
                <td width="10%" valign="middle" class="infoline">
                 	<div align="center">
	                <kul:htmlControlAttribute property="newBudgetPeriod.underrecoveryAmount" attributeEntry="${budgetPeriodAttributes.underrecoveryAmount}" styleClass="amount"/> 
                	</div>
                </td>
                <td width="10%" valign="middle" class="infoline">
                	<div align="center">
 	                <kul:htmlControlAttribute property="newBudgetPeriod.costSharingAmount" attributeEntry="${budgetPeriodAttributes.costSharingAmount}" styleClass="amount"/> 
                	</div>
                </td>
                <td width="9%" valign="middle" class="infoline">
                	<div align="center">
 	                <kul:htmlControlAttribute property="newBudgetPeriod.totalCostLimit" attributeEntry="${budgetPeriodAttributes.totalCostLimit}" styleClass="amount"/> 
                	</div>
                </td>
                <td width="9%" valign="middle" class="infoline">
                	<div align="center">
 	                <kul:htmlControlAttribute property="newBudgetPeriod.directCostLimit" attributeEntry="${budgetPeriodAttributes.directCostLimit}" styleClass="amount"/> 
                	</div>
                </td>
				<td class="infoline">
					<div width="7%" align="center">
						<html:image property="methodToCall.addBudgetPeriod.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton" />
					</div>
                </td>
            </tr>
            </kra:section>
            
        	<c:forEach var="budgetPeriods" items="${KualiForm.document.budget.budgetPeriods}" varStatus="status">
                 <c:set var="numberPeriods" value="${status.index+1}" />
            </c:forEach>
            
        	<c:forEach var="budgetPeriods" items="${KualiForm.document.budget.budgetPeriods}" varStatus="status">
				  <c:set var="periodReadOnly" value="false"/>
				  <bean:define id="readOnlyFlag" name="KualiForm" property="document.budget.budgetPeriods[${status.index}].readOnly" />
				  <c:if test="${readOnlyFlag == 'Yes' || readOnly}">
				  		<c:set var="periodReadOnly" value="true"/>
				  </c:if>
	             <tr>
					<th width="5%" class="infoline">
						<c:out value="${status.index+1}" />
					</th>
	                <td width="10%" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.budget.budgetPeriods[${status.index}].startDate" attributeEntry="${budgetPeriodAttributes.startDate}" />
					</div>
					</td>
	                <td width="10%" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.budget.budgetPeriods[${status.index}].endDate" attributeEntry="${budgetPeriodAttributes.endDate}" />
					</div>
	                </td>
	                <td width="10%" valign="middle">                	
					<div align="center">
                  		<kul:htmlControlAttribute property="document.budget.budgetPeriods[${status.index}].totalCost" attributeEntry="${budgetPeriodAttributes.totalCost}" styleClass="amount" readOnly="${periodReadOnly}"/> 
					</div>
					</td>
	                <td width="10%" valign="middle">                	
					<div align="center">
                  		<kul:htmlControlAttribute property="document.budget.budgetPeriods[${status.index}].totalDirectCost" attributeEntry="${budgetPeriodAttributes.totalDirectCost}" styleClass="amount" readOnly="${periodReadOnly}"/> 
					</div>
					</td>
	                <td width="10%" valign="middle">
					<div align="center">
	                	<kul:htmlControlAttribute property="document.budget.budgetPeriods[${status.index}].totalIndirectCost" attributeEntry="${budgetPeriodAttributes.totalIndirectCost}" styleClass="amount"  readOnly="${periodReadOnly}"/>
					</div>
	                </td>
	                <td width="10%" valign="middle">
					<div align="center">
	                	<kul:htmlControlAttribute property="document.budget.budgetPeriods[${status.index}].underrecoveryAmount" attributeEntry="${budgetPeriodAttributes.underrecoveryAmount}" styleClass="amount" readOnly="${periodReadOnly}"/>
					</div>
	                </td>
	                <td width="10%" valign="middle">
					<div align="center">
	                	<kul:htmlControlAttribute property="document.budget.budgetPeriods[${status.index}].costSharingAmount" attributeEntry="${budgetPeriodAttributes.costSharingAmount}" styleClass="amount" readOnly="${periodReadOnly}"/>
					</div>
	                </td> 
	                <td width="9%" valign="middle">
                	<div align="center">
 	                	<kul:htmlControlAttribute property="document.budget.budgetPeriods[${status.index}].totalCostLimit" attributeEntry="${budgetPeriodAttributes.totalCostLimit}" styleClass="amount" readOnly="${readOnly}"/>
                	</div>
                	</td>
	                <td width="9%" valign="middle">
                	<div align="center">
 	                	<kul:htmlControlAttribute property="document.budget.budgetPeriods[${status.index}].directCostLimit" attributeEntry="${budgetPeriodAttributes.directCostLimit}" styleClass="amount" readOnly="${readOnly}"/>
                	</div>
                	</td>
					<td width="7%">
					<div align="center">&nbsp;
						<kra:section permission="modifyBudgets"> 
		          		<c:choose>
		    				<c:when test="${numberPeriods > 1}">
								<html:image property="methodToCall.deleteBudgetPeriod.line${status.index}.anchor${currentTabIndex}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton" />
		    				</c:when>
		    				<c:otherwise >
								<img class="nobord" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete2.gif' styleClass="tinybutton" />
		    				</c:otherwise>
						</c:choose>
						<c:if test="${!proposalBudgetFlag}">
    				    	<kul:multipleValueLookup boClassName="org.kuali.kra.budget.parameters.BudgetPeriod" 
    				    							anchor="${tabKey}" 
    				    							lookupParameters="document.parentDocument.award.awardNumber:budgetParentId"
    				    							lookedUpCollectionName="${status.index}" autoSearch="yes" />						
						</c:if>
						</kra:section>
					</div>
	                </td>
	            </tr>
        	</c:forEach> 
        	<tr>
        		<td colspan="10" class="subhead">Totals</td>
    	    </tr>
          	<tr>
          		<td width="5%" class="infoline"> 
          			<div align="center">
          			  <c:if test="${not KualiForm.document.proposalBudgetFlag}" >  
          				<strong>Budget Change:</strong>
          				</c:if>
          				&nbsp;
          			</div> 
          		</td> 
                <td width="10%" valign="middle" class="infoline">
                	<div align="center">
                		<strong> <fmt:formatDate value="${KualiForm.document.budget.summaryPeriodStartDate}" pattern="MM/dd/yyyy" /> </strong>
                	</div>
				</td>
                <td width="10%" valign="middle" class="infoline">
                	<div align="center">
                		<strong> <fmt:formatDate value="${KualiForm.document.budget.summaryPeriodEndDate}" pattern="MM/dd/yyyy" /> </strong>
                	</div>
                </td>
                <td width="10%" valign="middle" class="infoline">                	
                	<div align="center">
                	    <strong> <kul:htmlControlAttribute property="document.budget.totalCost" attributeEntry="${budgetPeriodAttributes.totalCost}" styleClass="amount" readOnly="true"/> </strong>
                	</div>
				</td>
                <td width="10%" valign="middle" class="infoline">
                	<div align="center">
                	    <strong> <kul:htmlControlAttribute property="document.budget.totalDirectCost" attributeEntry="${budgetPeriodAttributes.totalDirectCost}" styleClass="amount" readOnly="true"/> </strong>
                		
                	</div>
                </td>
                <td width="10%" valign="middle" class="infoline">
                 	<div align="center">
                	    <strong> <kul:htmlControlAttribute property="document.budget.totalIndirectCost" attributeEntry="${budgetPeriodAttributes.totalIndirectCost}" styleClass="amount" readOnly="true"/> </strong>
                	</div>
                </td>
                <td width="10%" valign="middle" class="infoline">
                 	<div align="center">
                	    <strong> <kul:htmlControlAttribute property="document.budget.underrecoveryAmount" attributeEntry="${budgetPeriodAttributes.underrecoveryAmount}" styleClass="amount" readOnly="true"/> </strong>
                	</div>
                </td>
                <td width="10%" valign="middle" class="infoline">
                	<div align="center">
                	    <strong> <kul:htmlControlAttribute property="document.budget.costSharingAmount" attributeEntry="${budgetPeriodAttributes.costSharingAmount}" styleClass="amount" readOnly="true"/> </strong>
                	</div>
                </td>
                <td width="9%" valign="middle" class="infoline">
                	<div align="center">
                	    <strong> &nbsp; </strong>
                	</div>
                </td>
                <td width="9%" valign="middle" class="infoline">
                	<div align="center">
                	    <strong> &nbsp; </strong>
                	</div>
                </td>
				<td width="10%" class="infoline" rowspan="3" valigh="middle">
					<div align=center>&nbsp;
						<kra:section permission="modifyBudgets">
							<html:image property="methodToCall.recalculateBudgetPeriod.anchor${tabKey}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-recalculate.gif' styleClass="tinybutton" />
						</kra:section>
					</div>
                </td>
          	</tr>        
          	
          	
        <c:if test="${not KualiForm.document.proposalBudgetFlag}" >  	
          	<tr>
          		<td width="5%" class="infoline"> 
          			<div align="center">
          				Previous Budget:
          			</div> 
          		</td> 
                <td width="10%" valign="middle" class="infoline">
                	<div align="center">
                		 <fmt:formatDate value="${KualiForm.document.budget.prevBudget.startDate}" pattern="MM/dd/yyyy" /> 
                	</div>
				</td>
                <td width="10%" valign="middle" class="infoline">
                	<div align="center">
                		 <fmt:formatDate value="${KualiForm.document.budget.prevBudget.endDate}" pattern="MM/dd/yyyy" /> 
                	</div>
                </td>
                <td width="10%" valign="middle" class="infoline">                	
                	<div align="center">
                	     <kul:htmlControlAttribute property="document.budget.prevBudget.totalCost" attributeEntry="${budgetPeriodAttributes.totalCost}" styleClass="amount" readOnly="true"/> 
                	</div>
				</td>
                <td width="10%" valign="middle" class="infoline">
                	<div align="center">
                	     <kul:htmlControlAttribute property="document.budget.prevBudget.totalDirectCost" attributeEntry="${budgetPeriodAttributes.totalDirectCost}" styleClass="amount" readOnly="true"/>
                		
                	</div>
                </td>
                <td width="10%" valign="middle" class="infoline">
                 	<div align="center">
                	     <kul:htmlControlAttribute property="document.budget.prevBudget.totalIndirectCost" attributeEntry="${budgetPeriodAttributes.totalIndirectCost}" styleClass="amount" readOnly="true"/> 
                	</div>
                </td>
                <td width="10%" valign="middle" class="infoline">
                 	<div align="center">
                	     <kul:htmlControlAttribute property="document.budget.prevBudget.underrecoveryAmount" attributeEntry="${budgetPeriodAttributes.underrecoveryAmount}" styleClass="amount" readOnly="true"/> 
                	</div>
                </td>
                <td width="10%" valign="middle" class="infoline">
                	<div align="center">
                	     <kul:htmlControlAttribute property="document.budget.prevBudget.costSharingAmount" attributeEntry="${budgetPeriodAttributes.costSharingAmount}" styleClass="amount" readOnly="true"/> 
                	</div>
                </td>
                <td width="9%" valign="middle" class="infoline">
                	<div align="center">
                	     &nbsp; 
                	</div>
                </td>
                <td width="9%" valign="middle" class="infoline">
                	<div align="center">
                	     &nbsp; 
                	</div>
                </td>
          	</tr>    
         	<tr>
          		<td width="5%" class="infoline"> 
          			<div align="center">
          				<strong>Budget Total:</strong>
          			</div> 
          		</td> 
                <td width="10%" valign="middle" class="infoline">
                	<div align="center">
                		<strong> <fmt:formatDate value="${KualiForm.document.budget.firstBudget.startDate}" pattern="MM/dd/yyyy" />  </strong>
                	</div>
				</td>
                <td width="10%" valign="middle" class="infoline">
                	<div align="center">
                		<strong> <fmt:formatDate value="${KualiForm.document.budget.summaryPeriodEndDate}" pattern="MM/dd/yyyy" /> </strong>
                	</div>
                </td>
                <c:forEach var="budgetTotal" items="${KualiForm.document.budget.budgetsTotals}" varStatus="status">
                  <td valign="middle" class="infoline">
                	<div align="center">
                	    <strong> <kul:htmlControlAttribute property="document.budget.budgetsTotals[${status.index}]" attributeEntry="${budgetPeriodAttributes.costSharingAmount}" styleClass="amount" readOnly="true"/> </strong>
                	</div>
                  </td>
                
                </c:forEach>
          	    <td  class="infoline">
          	    &nbsp;
          	    </td>
          	    <td  class="infoline">
          	    &nbsp;
          	    </td>
          	
          	</tr>
          	    
          </c:if>  	
        </table>
    </div> 
</kul:tab>
<kul:panelFooter />
