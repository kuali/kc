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

<%@ attribute name="budgetCategoryTypeCodeKey" description="Budget Category Type Code Key" required="true" %>
<%@ attribute name="budgetCategoryTypeCodeLabel" description="Budget Category Type Code Label" required="true" %>
<%@ attribute name="catCodes" description="Category Type Index" required="true" %>

<c:set var="readOnly" value="${not KualiForm.editingMode['modifyBudgets']}" scope="request" />

<c:set var="budgetPeriodAttributes" value="${DataDictionary.BudgetPeriod.attributes}" />
<c:set var="budgetLineItemAttributes" value="${DataDictionary.BudgetLineItem.attributes}" />
<c:set var="awardBudgetLineItemAttributes" value="${DataDictionary.AwardBudgetLineItemExt.attributes}" />
<c:set var="action" value="budgetExpensesAction" />
<c:set var="textAreaFieldName" value="newBudgetLineItems[${catCodes}].lineItemDescription" />
<c:set var="lineItemCostAttribute" value="${awardBudgetLineItemAttributes}" />
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
	<c:if test="${budgetCategoryTypeCodeLabel eq 'Participant Support'}">
		<c:set var="tabErrorKeyString4" value="document.budget.budgetPeriods[${budgetPeriod - 1}].numberOfParticipants"/>
	</c:if>

	<c:if test="${! empty budgetLineItemSize}">
  		<c:set var="tabItemCount" value="${budgetLineItemSize} line item" />
  		<c:if test="${budgetLineItemSize > 1}">
  			<c:set var="tabItemCount" value="${tabItemCount}s" />
  		</c:if>		
	</c:if>
 	<c:if test="${! empty budgetLineItemSize or budgetCategoryTypeCodeKey ne 'H'}">
	<kul:tab tabTitle="${budgetCategoryTypeCodeLabel}" tabItemCount="${tabItemCount}" defaultOpen="false" tabErrorKey="*costElement*,newBudgetLineItems[${catCodes}].*,${tabErrorKeyString},${tabErrorKeyString2},${tabErrorKeyString3},${tabErrorKeyString4}"  auditCluster="budgetNonPersonnelAuditWarnings${budgetPeriod}${budgetCategoryTypeCodeLabel}" tabAuditKey="${tabErrorKeyString},${tabErrorKeyString2},${tabErrorKeyString3},${tabErrorKeyString4}" useRiceAuditMode="true">
		<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">${budgetCategoryTypeCodeLabel}</span>
				<c:if test="${budgetCategoryTypeCodeLabel eq 'Equipment'}">
					<span class="subhead-right"><kul:help
							parameterNamespace="KC-AB" parameterDetailType="Document"
							parameterName="awardBudgetNonPersonnelEquipmentHelpUrl"
							altText="help" /></span>
				</c:if>
				<c:if test="${budgetCategoryTypeCodeLabel eq 'Travel'}">
					<span class="subhead-right"><kul:help
							parameterNamespace="KC-AB" parameterDetailType="Document"
							parameterName="awardBudgetNonPersonnelTravelHelpUrl"
							altText="help" /></span>
				</c:if>
				<c:if test="${budgetCategoryTypeCodeLabel eq 'Participant Support'}">
					<span class="subhead-right"><kul:help
							parameterNamespace="KC-AB" parameterDetailType="Document"
							parameterName="awardBudgetNonPersonnelParticipantSupportHelpUrl"
							altText="help" /></span>
				</c:if>
				<c:if test="${budgetCategoryTypeCodeLabel eq 'Other Direct'}">
					<span class="subhead-right"><kul:help
							parameterNamespace="KC-AB" parameterDetailType="Document"
							parameterName="awardBudgetNonPersonnelOtherDirectHelpUrl"
							altText="help" /></span>
				</c:if>
			</h3>
        <jsp:useBean id="paramMap" class="java.util.HashMap"/>
		<c:set target="${paramMap}" property="budgetCategoryTypeCode" value="${budgetCategoryTypeCodeKey}" />
		<c:if test="${budgetCategoryTypeCodeLabel eq 'Participant Support'}">
			<table border="0" cellpadding=0 cellspacing=0 summary="">
        		<tr>
        			<th style="width : 15em;"><kul:htmlAttributeLabel attributeEntry="${budgetPeriodAttributes.numberOfParticipants}"/></th>
        			<td><kul:htmlControlAttribute property="document.budget.budgetPeriods[${budgetPeriod - 1}].numberOfParticipants" attributeEntry="${budgetPeriodAttributes.numberOfParticipants}" /></td>
        		</tr>
        	</table>
        </c:if>
        <table border="0" cellpadding=0 cellspacing=0 summary="">
          	<tr>
          		<th width="6%" class="darkInfoline"><div align="center">&nbsp;</div></th> 
          		<th width="33%" class="darkInfoline"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.costElement}" noColon="true" /></div></th>
          		<th width="20%" class="darkInfoline"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.lineItemDescription}" noColon="true" /></div></th>
          		<th width="6%" class="darkInfoline"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.quantity}" noColon="true" /></div></th>
          		<th width="16%" class="darkInfoline"><div align="center"><kul:htmlAttributeLabel attributeEntry="${lineItemCostAttribute.lineItemCost}" noColon="true" /></div></th>
       			<th width="16%" class="darkInfoline"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardBudgetLineItemAttributes.obligatedAmount}" noColon="true" /></div></th>
          		<th width="9%" class="darkInfoline"><div align="center">Action</div></th>
          	</tr>    
          	
          	<kra:section permission="modifyBudgets">    
	            <tr class="addline">
					<th class="darkInfoline">
						<c:out value="Add:" />
					</th>
					<td valign="middle" class="darkInfoline" nowrap="true">
	                	<div align="center">
	                	<html:select property="newBudgetLineItems[${catCodes}].costElement" tabindex="0" >
	                    <c:forEach items="${krafn:getOptionList('org.kuali.coeus.common.budget.impl.core.CostElementValuesFinder', paramMap)}" var="option">
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
	                	<kul:lookup boClassName="org.kuali.coeus.common.budget.framework.core.CostElement" fieldConversions="costElement:newBudgetLineItems[${catCodes}].costElement" anchor="${tabKey}" lookupParameters="newBudgetLineItems[${catCodes}].costElement:costElement,document.budget.budgetCategoryType[${catCodes}]:budgetCategoryTypeCode" autoSearch="yes"/>
	                	<kul:directInquiry boClassName="org.kuali.coeus.common.budget.framework.core.CostElement" inquiryParameters="newBudgetLineItems[${catCodes}].costElement:costElement" anchor="${tabKey}"/>
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
                   <td valign="middle" class="darkInfoline">&nbsp;</td>
					<td class="darkInfoline">
						<c:if test="${!readOnly}" >
						<div align="center">
							<html:image property="methodToCall.addBudgetLineItem.budgetCategoryTypeCode${budgetCategoryTypeCodeKey}.catTypeIndex${catCodes}.anchor${tabKey}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton addButton" />
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
