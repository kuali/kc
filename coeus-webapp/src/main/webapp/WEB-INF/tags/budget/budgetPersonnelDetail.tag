<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2015 Kuali, Inc.
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
<c:set var="budgetLineItemAttributes" value="${DataDictionary.BudgetLineItem.attributes}" />
<c:set var="budgetPersonnelDetailsAttributes" value="${DataDictionary.BudgetPersonnelDetails.attributes}" />
<c:set var="action" value="budgetExpensesAction" />

<c:choose>
	<c:when test="${!empty KualiForm.viewBudgetPeriod}" >
		<c:set var="budgetPeriod" value="${KualiForm.viewBudgetPeriod}" />
	</c:when>
	<c:otherwise>
		<c:set var="budgetPeriod" value="1" />
	</c:otherwise>
</c:choose>

	<c:set var="tabErrorKeyString" value=""/>
    <c:forEach var="budgetCategoryTypeIndex" items="${KualiForm.document.budget.budgetCategoryTypeCodes}" varStatus="status1">
    	<c:if test="${budgetCategoryTypeIndex.key ==  budgetCategoryTypeCodeKey}">
    		<c:set var="index" value="0"/>
    		<c:forEach var="budgetLineItems" items="${KualiForm.document.budget.budgetPeriods[budgetPeriod - 1].budgetLineItems}" varStatus="status">			    		
    		<c:if test="${budgetLineItems.budgetCategory.budgetCategoryTypeCode == budgetCategoryTypeIndex.key}" >    						
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
	    				<c:set var="tabErrorKeyString3" value="document.budget.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${status.index}].*,document.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${status.index}].*"/>
	    			</c:when>
	    			<c:otherwise>
	    				<c:set var="tabErrorKeyString3" value="${tabErrorKeyString3},document.budget.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${status.index}].*,document.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${status.index}].*"/>
	    			</c:otherwise>
	    		</c:choose>			    		
    		</c:if>    		
    		<c:if test="${index!=0}">    					    		
    			<c:set var="budgetLineItemSize" value="${index}"/>
    		</c:if>
    		</c:forEach>
    	</c:if>
    </c:forEach>
    
<kul:tab tabTitle="Personnel Detail (Period ${budgetPeriod})" defaultOpen="false" tabErrorKey="*costElement*,newGroupName,newBudgetLineItems*,newBudgetPersonnelDetails.*,${tabErrorKeyString},${tabErrorKeyString2}" auditCluster="budgetNonPersonnelAuditWarnings${budgetPeriod}Personnel" tabAuditKey="${tabErrorKeyString},${tabErrorKeyString2},${tabErrorKeyString3}" useRiceAuditMode="true">
		<div class="tab-container" align="center">
		
		<div style="text-align:left;width: 98%" >
		   	<c:forEach var="budgetLineItem" items="${KualiForm.document.budget.budgetPeriods[budgetPeriod-1].budgetLineItems}" varStatus="status">
			   	<c:forEach var="budgetPersonnelDetails" items="${budgetLineItem.budgetPersonnelDetailsList}" varStatus="personStatus">
					<c:set var="msg" value="${budgetPersonnelDetails.effdtAfterStartdtMsg}" /> 
	     			<c:if test="${!empty  msg}" >
	     			    <strong><c:out value="${msg}" /> </strong><br/>
	     			</c:if>
				</c:forEach>
			</c:forEach>
        </div><br/>
		
   		<h3>
            <span class="subhead-left">Add Details</span>
	       	<div align="right"><kul:help parameterNamespace="KC-AB" parameterDetailType="Document" parameterName="awardBudgetPersonnelDetailsHelpUrl" altText="help"/></div>
		</h3>
        <jsp:useBean id="paramMap" class="java.util.HashMap"/>
		<c:set target="${paramMap}" property="budgetCategoryTypeCode" value="${budgetCategoryTypeCodeKey}" />
        <table border="0" cellpadding=0 cellspacing=0 summary="">
          	<tr>
          		<th width="20%" ><div align="center">${Constants.REQUIRED_FIELD_SYMBOL}&nbsp;<kul:htmlAttributeLabel attributeEntry="${budgetPersonnelDetailsAttributes.personSequenceNumber}" noColon="true" /></div></th>
          		<th width="40%" ><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.costElement}" noColon="true" /></div></th>
          		<th width="25%" ><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.groupName}" noColon="true" /></div></th>
          		<th width="15%" ><div align="center">Action</div></th>
          	</tr>    
          	
          	<kra:section permission="modifyBudgets">    
	            <tr class="addline">
					<td valign="middle" nowrap="true">
	                	<div align="center">
	                	<kul:htmlControlAttribute property="newBudgetPersonnelDetails.personSequenceNumber" attributeEntry="${budgetPersonnelDetailsAttributes.personSequenceNumber}"  onchange="updateCostElement('${KualiForm.document.budget.budgetId}', 'newBudgetLineItems[${catCodes}].costElement', this, '${budgetCategoryTypeCodeKey}', updateCostElement_Callback);" />
	                	<input type="hidden" name="costElementLookup">
	                	</div>
					</td>
					<td valign="middle" nowrap="true">
	                	<div align="center">
		                	<kul:checkErrors keyMatch="newBudgetLineItems[${catCodes}].costElement" auditMatch="newBudgetLineItems[${catCodes}].costElement" />
							<c:choose>
							  <%-- border color not supported for select controls, so make background highlighted instead --%>
							  <c:when test="${hasErrors==true}">
							    <c:set var="textStyle" value="background-color:#FFD5D5"/>
							  </c:when>
							  <c:when test="${readOnly && !hasErrors}">
							    <c:set var="textStyle" value="border-color: black"/>
							  </c:when>
							</c:choose>
	                	
		                	<html:select property="newBudgetLineItems[${catCodes}].costElement" tabindex="0" style="${textStyle}"  >
		                    <c:forEach items="${krafn:getOptionList('org.kuali.coeus.common.budget.impl.core.CostElementValuesFinder', paramMap)}" var="option">
		                    <c:choose>                    	
		                    	<c:when test="${KualiForm.newBudgetLineItems[catCodes].costElement == option.key}">
		                        <option value="${option.key}" selected>${option.value}</option>
		                        </c:when>
		                        <c:otherwise>
		                        <c:out value="${option.value}"/>
		                        <option value="${option.key}">${option.value}</option>
		                        </c:otherwise>
		                    </c:choose>                    
		                    </c:forEach>
		                    </html:select>
		                    <input type="hidden" name="document.budget.budgetCategoryType[${catCodes}]" value="${budgetCategoryTypeCodeKey}">
		
		                    <span id="ceLookupDiv">                 
		                		<kul:lookup boClassName="org.kuali.coeus.common.budget.framework.core.CostElement" fieldConversions="costElement:newBudgetLineItems[${catCodes}].costElement" anchor="${tabKey}" lookupParameters="newBudgetLineItems[${catCodes}].costElement:costElement,document.budget.budgetCategoryType[${catCodes}]:budgetCategoryTypeCode" autoSearch="yes"/>
		                	</span>
		                	
		                	<kul:directInquiry boClassName="org.kuali.coeus.common.budget.framework.core.CostElement" inquiryParameters="newBudgetLineItems[${catCodes}].costElement:costElement" anchor="${tabKey}"/>
		                	<c:set var="hasErrors" value="" scope="request"/>                	
	                	</div>
					</td>
					<td valign="middle" >
						<div align="center">
							<kul:htmlControlAttribute onchange="javascript:disableGrpNameTextbox(this);return;" property="newBudgetLineItems[${catCodes}].groupName" attributeEntry="${budgetLineItemAttributes.groupName}" />
							--or--
							<c:set var="textStyle" value=""/>
							<html:text property="newGroupName" size="10" maxlength="25" tabindex="0" styleId="newGroupName" style="${textStyle}"  disabled="false" value="(new group)"  onfocus="javascript:resetGrpNameTextbox();return;"  />
						</div>
					</td>
					<td >
						<c:if test="${!readOnly}" >
						<div align=center>
							<html:image property="methodToCall.addPersonnelLineItem.budgetCategoryTypeCode${budgetCategoryTypeCodeKey}.catTypeIndex${catCodes}.anchor${tabKey}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' onclick="javascript:resetGrpNameTextbox();return;" styleClass="addButton"/>
						</div>
						</c:if>	
	                </td>			
	            </tr>
            </kra:section>
             
                    
    		<c:set var="index" value="0"/>
    		<c:forEach var="budgetLineItems" items="${KualiForm.document.budget.budgetPeriods[budgetPeriod - 1].budgetLineItems}" varStatus="status">
    		<c:if test="${budgetLineItems.costElementBO.budgetCategory.budgetCategoryTypeCode == budgetCategoryTypeCodeKey}" >
    			<tr>
    				<td colspan="4" width="100%">
						<kra-b:budgetPersonnelLineItems budgetPeriod = "${budgetPeriod}" budgetCategoryTypeCode = "${budgetCategoryTypeCodeKey}" budgetLineItemNumber="${status.index}" budgetLineItemSequenceNumber="${index}" innerTabParent="Personnel Detail" budgetExpensePanelReadOnly="${readOnly}"/>
						<c:set var="index" value="${index+1}"/>
					</td>
				</tr>
    		</c:if>		
    		</c:forEach>
 
 			</table>
	    </div>
	</kul:tab>
