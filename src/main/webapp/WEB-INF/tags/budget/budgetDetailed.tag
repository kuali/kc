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

<%@ attribute name="budgetCategoryTypeCodeKey" description="Budget Category Type Code Key" required="true" %>
<%@ attribute name="budgetCategoryTypeCodeLabel" description="Budget Category Type Code Label" required="true" %>
<%@ attribute name="catCodes" description="Category Type Index" required="true" %>

<c:set var="readOnly" value="${not KualiForm.editingMode['modifyBudgets']}" scope="request" />
<bean:define id="proposalBudgetFlag" name="KualiForm" property="document.proposalBudgetFlag"/>

<c:set var="budgetLineItemAttributes" value="${DataDictionary.BudgetLineItem.attributes}" />
<c:set var="awardBudgetLineItemAttributes" value="${DataDictionary.AwardBudgetLineItemExt.attributes}" />
<c:set var="action" value="budgetExpensesAction" />
<c:set var="textAreaFieldName" value="newBudgetLineItems[${catCodes}].lineItemDescription" />
<c:choose>
	<c:when test="${proposalBudgetFlag}" >
		<c:set var="lineItemCostAttribute" value="${budgetLineItemAttributes}" />
	</c:when>
	<c:otherwise>
		<c:set var="lineItemCostAttribute" value="${awardBudgetLineItemAttributes}" />
	</c:otherwise>
</c:choose>
<c:choose>
	<c:when test="${!empty KualiForm.viewBudgetPeriod}" >
		<c:set var="budgetPeriod" value="${KualiForm.viewBudgetPeriod}" />
	</c:when>
	<c:otherwise>
		<c:set var="budgetPeriod" value="1" />
	</c:otherwise>
</c:choose>
	<c:set var="tabErrorKeyString" value=""/>
	<c:set var="tabErrorKeyString2" value=""/>
	<c:set var="tabErrorKeyString3" value=""/>
	
    <c:forEach var="budgetCategoryTypeIndex" items="${KualiForm.document.budget.budgetCategoryTypeCodes}" varStatus="status1">
    	<c:if test="${budgetCategoryTypeIndex.key ==  budgetCategoryTypeCodeKey}">
    		<c:set var="index" value="0"/>
    		<c:forEach var="budgetLineItems" items="${KualiForm.document.budget.budgetPeriods[budgetPeriod - 1].budgetLineItems}" varStatus="status">			    		
				<c:if test="${budgetLineItems.costElementBO.budgetCategory.budgetCategoryTypeCode == budgetCategoryTypeIndex.key}" >    						
					<c:set var="index" value="${index+1}"/>
					<c:choose>
		    			<c:when test="${empty tabErrorKeyString}">
		    				<c:set var="tabErrorKeyString" value="document.budget.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${status.index}].lineItemDescription"/>
		    			</c:when>
		    			<c:otherwise>
		    				<c:set var="tabErrorKeyString" value="${tabErrorKeyString},document.budget.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${status.index}].lineItemDescription"/>
		    			</c:otherwise>
		    		</c:choose>
		    		<c:choose>
		    			<c:when test="${empty tabErrorKeyString2}">
		    				<c:set var="tabErrorKeyString2" value="document.budget.budgetCategoryTypes[${budgetCategoryTypeIndex.key}].budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${status.index}].*"/>
		    			</c:when>
		    			<c:otherwise>
		    				<c:set var="tabErrorKeyString2" value="${tabErrorKeyString2},document.budget.budgetCategoryTypes[${budgetCategoryTypeIndex.key}].budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${status.index}].*"/>
		    			</c:otherwise>
		    		</c:choose>
		    		<c:choose>
		    			<c:when test="${empty tabErrorKeyString3}">
		    				<c:set var="tabErrorKeyString3" value="document.budget.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${status.index}].lineItemCost,document.budget.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${status.index}].quantity,document.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${status.index}].*"/>
		    			</c:when>
		    			<c:otherwise>
		    				<c:set var="tabErrorKeyString3" value="${tabErrorKeyString3},document.budget.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${status.index}].lineItemCost,document.budget.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${status.index}].quantity,document.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${status.index}].*"/>
		    			</c:otherwise>
		    		</c:choose>			    		
	    		</c:if>    		
	    		<c:if test="${index!=0}">    					    		
	    			<c:set var="budgetLineItemSize" value="${index}"/>
	    		</c:if>
    		</c:forEach>
    	</c:if>
    </c:forEach>

	<%--<c:set var="budgetExpensePanelReadOnly" value="${KualiForm.document.parentDocument.developmentProposalList[0].budgetVersionOverviews[KualiForm.document.budget.budgetVersionNumber-1].finalVersionFlag}" /> --%>
	<c:if test="${! empty budgetLineItemSize}">
  		<c:set var="tabItemCount" value="${budgetLineItemSize} line item" />
  		<c:if test="${budgetLineItemSize > 1}">
  			<c:set var="tabItemCount" value="${tabItemCount}s" />
  		</c:if>		
	</c:if>
 	<c:if test="${! empty budgetLineItemSize or budgetCategoryTypeCodeKey ne 'H'}">
	<kul:tab tabTitle="${budgetCategoryTypeCodeLabel}" tabItemCount="${tabItemCount}" defaultOpen="false" tabErrorKey="*costElement*,newBudgetLineItems[${catCodes}].*,${tabErrorKeyString},${tabErrorKeyString2},${tabErrorKeyString3}"  auditCluster="budgetNonPersonnelAuditWarnings${budgetPeriod}${budgetCategoryTypeCodeLabel}" tabAuditKey="${tabErrorKeyString},${tabErrorKeyString2},${tabErrorKeyString3}" useRiceAuditMode="true">
		<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">${budgetCategoryTypeCodeLabel}</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.budget.nonpersonnel.BudgetLineItem" altText="help"/></span>
        </h3>
        <jsp:useBean id="paramMap" class="java.util.HashMap"/>
		<c:set target="${paramMap}" property="budgetCategoryTypeCode" value="${budgetCategoryTypeCodeKey}" />
        <table border="0" cellpadding=0 cellspacing=0 summary="">
          	<tr>
          		<th width="6%" class="darkInfoline"><div align="center">&nbsp;</div></th> 
          		<th width="33%" class="darkInfoline"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.costElement}" noColon="true" /></div></th>
          		<th width="20%" class="darkInfoline"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.lineItemDescription}" noColon="true" /></div></th>
          		<th width="6%" class="darkInfoline"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.quantity}" noColon="true" /></div></th>
          		<th width="16%" class="darkInfoline"><div align="center"><kul:htmlAttributeLabel attributeEntry="${lineItemCostAttribute.lineItemCost}" noColon="true" /></div></th>
          		<c:if test="${!proposalBudgetFlag}">
          			<th width="16%" class="darkInfoline"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardBudgetLineItemAttributes.obligatedAmount}" noColon="true" /></div></th>
          		</c:if>
          		<th width="9%" class="darkInfoline"><div align="center">Action</div></th>
          	</tr>    
          	
          	<kra:section permission="modifyBudgets">    
	            <tr>
					<th class="darkInfoline">
						<c:out value="Add:" />
					</th>
					<td valign="middle" class="darkInfoline" nowrap="true">
	                	<div align="center">
	                	<html:select property="newBudgetLineItems[${catCodes}].costElement" tabindex="0" >
	                    <c:forEach items="${krafn:getOptionList('org.kuali.kra.budget.lookup.keyvalue.CostElementValuesFinder', paramMap)}" var="option">
	                    <c:choose>                    	
	                    	<c:when test="${KualiForm.newBudgetLineItems[catCodes].costElement == option.key}">
	                        <option value="${option.key}" selected="true">${option.value}</option>
	                        </c:when>
	                        <c:otherwise>
	                        <c:out value="${option.value}"/>
	                        <option value="${option.key}">${option.value}</option>
	                        </c:otherwise>
	                    </c:choose>                    
	                    </c:forEach>
	                    </html:select>
	                    <input type="hidden" name="document.budget.budgetCategoryType[${catCodes}]" value="${budgetCategoryTypeCodeKey}">                    
	                	<kul:lookup boClassName="org.kuali.kra.budget.core.CostElement" fieldConversions="costElement:newBudgetLineItems[${catCodes}].costElement" anchor="${tabKey}" lookupParameters="newBudgetLineItems[${catCodes}].costElement:costElement,document.budget.budgetCategoryType[${catCodes}]:budgetCategoryTypeCode" autoSearch="yes"/>                	
	                	<kul:directInquiry boClassName="org.kuali.kra.budget.core.CostElement" inquiryParameters="newBudgetLineItems[${catCodes}].costElement:costElement" anchor="${tabKey}"/>	                	
	                	</div>
					</td>
					<td valign="middle" class="darkInfoline">
	                	<div align="center">
	                	<kul:htmlControlAttribute property="newBudgetLineItems[${catCodes}].lineItemDescription" attributeEntry="${budgetLineItemAttributes.lineItemDescription}" />
	                	</div>
					</td>
	                <td valign="middle" class="darkInfoline">
	                	<div align="center">
	                	<kul:htmlControlAttribute property="newBudgetLineItems[${catCodes}].quantity" attributeEntry="${budgetLineItemAttributes.quantity}" styleClass="amount" />
	                	</div>
	                </td>
	                <td valign="middle" class="darkInfoline">                	
	                	<div align="center">
	                  	<kul:htmlControlAttribute property="newBudgetLineItems[${catCodes}].lineItemCost" attributeEntry="${lineItemCostAttribute.lineItemCost}" styleClass="amount" /> 
	                	</div>
					</td>
					<c:if test="${!proposalBudgetFlag}">		
	                   <td valign="middle" class="darkInfoline">&nbsp;</td>
                    </c:if>               	
					<td class="darkInfoline">
						<c:if test="${!readOnly}" >
						<div align="center">
							<html:image property="methodToCall.addBudgetLineItem.budgetCategoryTypeCode${budgetCategoryTypeCodeKey}.catTypeIndex${catCodes}.anchor${tabKey}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton" />
						</div>
						</c:if>	
	                </td>			
	            </tr>
            </kra:section>
             
			    <c:forEach var="budgetCategoryTypeIndex" items="${KualiForm.document.budget.budgetCategoryTypeCodes}" varStatus="status1">
			    	<c:if test="${budgetCategoryTypeIndex.key ==  budgetCategoryTypeCodeKey}">
			    		<c:set var="index" value="0"/>
			    		<c:forEach var="budgetLineItems" items="${KualiForm.document.budget.budgetPeriods[budgetPeriod - 1].budgetLineItems}" varStatus="status">
			    		<c:if test="${budgetLineItems.costElementBO.budgetCategory.budgetCategoryTypeCode == budgetCategoryTypeIndex.key}" >
							<kra-b:budgetLineItems budgetPeriod = "${budgetPeriod}" budgetCategoryTypeCode = "${budgetCategoryTypeCodeKey}" budgetLineItemNumber="${status.index}" budgetLineItemSequenceNumber="${index}" innerTabParent="${budgetCategoryTypeCodeLabel}" budgetExpensePanelReadOnly="${readOnly}"/>
							<c:set var="index" value="${index+1}"/>			    		
			    		</c:if> 		
			    		</c:forEach>
			    	</c:if>
			    </c:forEach>
			    			    			    
        </table>  
	    </div>
	</kul:tab>
	</c:if>