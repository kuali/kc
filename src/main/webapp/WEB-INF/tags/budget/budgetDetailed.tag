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

<%@ attribute name="budgetCategoryTypeCodesKey" description="Budget Category Type Code Key" required="true" %>
<%@ attribute name="budgetCategoryTypeCodesLabel" description="Budget Category Type Code Label" required="true" %>
<%@ attribute name="catCodes" description="Category Type Index" required="true" %>

<c:set var="budgetLineItemAttributes" value="${DataDictionary.BudgetLineItem.attributes}" />

<c:set var="action" value="budgetExpensesAction" />
<c:set var="textAreaFieldName" value="newBudgetLineItems[${catCodes}].lineItemDescription" />

	<%--
	<c:forEach var="budgetPeriodLineItemNumbersMapping" items="${KualiForm.budgetPeriodLineItemNumbersMapping}" varStatus="number1">
		<c:if test="${budgetPeriodLineItemNumbersMapping.key == KualiForm.viewBudgetPeriod}">
			<c:set var="budgetPeriodLineItemNumbersMappingValue" value="${budgetPeriodLineItemNumbersMapping.value}"/>
			<c:forEach var="budgetCategoryTypeCodeNumber" items="${budgetPeriodLineItemNumbersMappingValue}" varStatus="number">
				<c:if test="${budgetCategoryTypeCodeNumber.key == budgetCategoryTypeCodesKey}">
					<c:set var="budgetLineItemSize" value=" (${budgetCategoryTypeCodeNumber.value+1})"/>			
				</c:if>
			</c:forEach>
		</c:if>
	</c:forEach>
	 --%>
    <c:forEach var="budgetCategoryTypeIndex" items="${KualiForm.document.budgetCategoryTypeCodes}" varStatus="status1">
    	<c:if test="${budgetCategoryTypeIndex.key ==  budgetCategoryTypeCodesKey}">
    		<c:set var="index" value="0"/>
    		<c:forEach var="budgetLineItems" items="${KualiForm.document.budgetPeriods[KualiForm.viewBudgetPeriod - 1].budgetLineItems}" varStatus="status">			    		
    		<c:if test="${budgetLineItems.budgetCategory.budgetCategoryTypeCode == budgetCategoryTypeIndex.key}" >				
				<c:set var="index" value="${index+1}"/>			    		
    		</c:if>
    		<c:if test="${index!=0}">    					    		
    			<c:set var="budgetLineItemSize" value=" (${index})"/>
    		</c:if>
    		</c:forEach>
    	</c:if>
    </c:forEach>	
		
	<kul:tab tabTitle="${budgetCategoryTypeCodesLabel}${budgetLineItemSize}" defaultOpen="true" tabErrorKey="budget.projectIncome*">
		<div class="tab-container" align="center">
    	<div class="h2-container">
    		<span class="subhead-left"><h2>${budgetCategoryTypeCodesLabel}</h2></span>
    		<span class="subhead-right"><kul:help businessObjectClassName="fillMeIn" altText="help"/></span>
        </div>
        <jsp:useBean id="paramMap" class="java.util.HashMap"/>
		<c:set target="${paramMap}" property="budgetCategoryTypeCode" value="${budgetCategoryTypeCodesKey}" />
        <table border="0" cellpadding=0 cellspacing=0 summary="">
          	<tr>
          		<th width="5%"><div align="center">&nbsp</div></th> 
          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.costElement}" noColon="true" /></div></th>
          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.lineItemDescription}" noColon="true" /></div></th>
          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.underrecoveryAmount}" noColon="true" /></div></th>
          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.costSharingAmount}" noColon="true" /></div></th>
          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.quantity}" noColon="true" /></div></th>
          		<th width="15%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.lineItemCost}" noColon="true" /></div></th>
              	<kul:htmlAttributeHeaderCell literalLabel="Action" scope="col"/>
          	</tr>        
            <tr>
				<th class="infoline">
					<c:out value="Add:" />
				</th>
				<td valign="middle" class="infoline">
                	<div align="center">
                	<html:select property="newBudgetLineItems[${catCodes}].costElement" tabindex="0" >
                    <c:forEach items="${krafn:getOptionList('org.kuali.kra.budget.lookup.keyvalue.CostElementValuesFinder', paramMap)}" var="option">                    
                    <c:choose>                    	
                        <c:when test="${newBudgetLineItems}[${catCodes}].costElement == option.key}">						
                        <option value="${option.key}" selected>${option.label}</option>
                        </c:when>
                        <c:otherwise>
                        <option value="${option.key}">${option.label}</option>
                        </c:otherwise>
                    </c:choose>
                    </c:forEach>
                    </html:select>
                	<kul:lookup boClassName="org.kuali.kra.budget.bo.CostElement" fieldConversions="costElement:newBudgetLineItems[${catCodes}].costElement" anchor="${tabKey}" lookupParameters="newBudgetLineItems[${catCodes}].costElement:newBudgetLineItems[${catCodes}].costElement" />
                	<kul:directInquiry boClassName="org.kuali.kra.budget.bo.CostElement" inquiryParameters="costElement:newBudgetLineItems[${catCodes}].costElement" anchor="${tabKey}"/>
                	</div>
				</td>
				<td valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newBudgetLineItems[${catCodes}].lineItemDescription" attributeEntry="${budgetLineItemAttributes.lineItemDescription}"/>
                	<kra:expandedTextArea textAreaFieldName="${textAreaFieldName}" action="${action}" textAreaLabel="${budgetLineItemAttributes.lineItemDescription.label}" />                	
                	</div>
				</td>
				<td valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newBudgetLineItems[${catCodes}].underrecoveryAmount" attributeEntry="${budgetLineItemAttributes.underrecoveryAmount}"/>
                	</div>
				</td>
                <td valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newBudgetLineItems[${catCodes}].costSharingAmount" attributeEntry="${budgetLineItemAttributes.costSharingAmount}"/>
                	</div>
				</td>
                <td valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newBudgetLineItems[${catCodes}].quantity" attributeEntry="${budgetLineItemAttributes.quantity}" />
                	</div>
                </td>
                <td valign="middle" class="infoline">                	
                	<div align="center">
                  	<kul:htmlControlAttribute property="newBudgetLineItems[${catCodes}].lineItemCost" attributeEntry="${budgetLineItemAttributes.lineItemCost}" styleClass="amount" /> 
                	</div>
				</td>
				<td class="infoline">
					<div align=center>
						<html:image property="methodToCall.addBudgetLineItem.budgetCategoryTypeCode${budgetCategoryTypeCodesKey}.catTypeIndex${catCodes}.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' />
					</div>
                </td>
            </tr>            
				<%--
				<c:forEach var="budgetLineItems" items="${KualiForm.document.budgetPeriods[KualiForm.viewBudgetPeriod - 1].budgetLineItems}" varStatus="status">        		
        		
        			<c:out value="Param!!2${budgetLineItems.budgetCategory.budgetCategoryTypeCode}"/>
        			<c:out value="Param!!${budgetCategoryTypeCodesKey}"/>
        			<c:if test="${budgetLineItems.budgetCategory.budgetCategoryTypeCode ==  budgetCategoryTypeCodesKey}">
        		<kra-b:budgetLineItems budgetCategoryTypeCode = "${budgetCategoryTypeCodesKey}" budgetLineItemNumber="${status.index}" innerTabParent="${budgetCategoryTypeCodesLabel}${budgetLineItemSize}" />
        		</c:if>
			   	 </c:forEach>
			   	 --%> 
			   	<%-- 
				<c:set var="index" value="0"/>	        		
        		<c:forEach var="budgetPeriodLineItemSequenceMapping" items="${KualiForm.budgetPeriodLineItemSequenceMapping}" varStatus="number1">
					<c:out value="Param!!${budgetPeriodLineItemSequenceMapping.key}"/>
					<c:out value="Param!!${KualiForm.viewBudgetPeriod}"/>
					<c:if test="${budgetPeriodLineItemSequenceMapping.key == KualiForm.viewBudgetPeriod}">
						<c:set var="budgetPeriodLineItemSequenceMappingValue" value="${budgetPeriodLineItemSequenceMapping.value}"/>
		        		<c:forEach var="budgetCategoryTypeCodeSequence" items="${budgetPeriodLineItemSequenceMappingValue}" varStatus="number">
		        			<c:out value="Param2!!${budgetCategoryTypeCodeSequence.key}"/>
							<c:out value="Param2!!${budgetCategoryTypeCodeSequence.key}"/>
		        			<c:if test="${budgetCategoryTypeCodeSequence.key == budgetCategoryTypeCodesKey}">		        									
								<c:set var="budgetLineItemSequence" value="${budgetCategoryTypeCodeSequence.value}"/>
								<c:forEach var="budgetCategoryTypeCodeSequenceNumber" items="${budgetLineItemSequence}" varStatus="number2">															
									<kra-b:budgetLineItems budgetCategoryTypeCode = "${budgetCategoryTypeCodesKey}" budgetLineItemNumber="${index}" budgetLineItemSequenceNumber="${budgetCategoryTypeCodeSequenceNumber}" innerTabParent="${budgetCategoryTypeCodesLabel}${budgetLineItemSize}" />
									<c:set var="index" value="${index+1}"/>													
								</c:forEach>								
							</c:if>	
				       	</c:forEach>
				    </c:if>
			    </c:forEach>
			    --%>			    
			    <c:forEach var="budgetCategoryTypeIndex" items="${KualiForm.document.budgetCategoryTypeCodes}" varStatus="status1">
			    	<c:if test="${budgetCategoryTypeIndex.key ==  budgetCategoryTypeCodesKey}">
			    		<c:set var="index" value="0"/>
			    		<c:forEach var="budgetLineItems" items="${KualiForm.document.budgetPeriods[KualiForm.viewBudgetPeriod - 1].budgetLineItems}" varStatus="status">			    		
			    		<c:if test="${budgetLineItems.budgetCategory.budgetCategoryTypeCode == budgetCategoryTypeIndex.key}" >
							<kra-b:budgetLineItems budgetCategoryTypeCode = "${budgetCategoryTypeCodesKey}" budgetLineItemNumber="${status.index}" budgetLineItemSequenceNumber="${index}" innerTabParent="${budgetCategoryTypeCodesLabel}${budgetLineItemSize}" />
							<c:set var="index" value="${index+1}"/>			    		
			    		</c:if>			    		
			    		</c:forEach>
			    	</c:if>
			    </c:forEach>
			    			    			    
        </table>  
	    </div>
	</kul:tab>