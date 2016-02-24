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

<c:set var="awardAttributes" value="${DataDictionary.Award.attributes}" />
<c:set var="awardDirectFandADistributionAttributes" value="${DataDictionary.AwardDirectFandADistribution.attributes}" />
<c:set var="awardAmountInfoAttributes" value="${DataDictionary.AwardAmountInfo.attributes}" />

<c:set var="action" value="timeAndMoney" />


<kul:tab tabItemCount="${KualiForm.document.awardNumber}" tabTitle="Direct/F&A Funds Distribution" defaultOpen="false" tabErrorKey="newAwardDirectFandADistribution.*,document.award.awardDirectFandADistribution*,awardDirectFandADistributionBean.newAwardDirectFandADistribution*">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Direct/F&A Funds Distribution</span>
    		<div align="right"><kul:help parameterNamespace="KC-T" parameterDetailType="Document" parameterName="awardFundDistributionHelpUrl" altText="help"/></div>
        </h3>
        <table id="Direct-FandA-Funds-Distribution-table" cellpadding="0" cellspacing="0" summary="Direct F and A Distribution">
			<tr>
				<th width="30%" align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.awardEffectiveDate}" useShortLabel="true" noColon="false" /></th>
				<td  width="20%" align="left">          	  
        			<kul:htmlControlAttribute property="document.award.awardEffectiveDate" attributeEntry="${awardAttributes.awardEffectiveDate}" readOnly="true" />   	 
            	</td>
				<th width="30%" align="right"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.finalExpirationDate}" useShortLabel="true" noColon="false" /></th>
				<td  width="20%" align="left">          	  
        			<kul:htmlControlAttribute property="document.award.awardAmountInfos[${KualiForm.document.award.indexOfLastAwardAmountInfo}].finalExpirationDate" attributeEntry="${awardAmountInfoAttributes.finalExpirationDate}" readOnly="true" />   	 
            	</td>
			</tr>
			<tr>
				<th width="30%" align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.obligatedTotal}" useShortLabel="true" noColon="false" /></th>
				<td width="20%">            	  	
            	  	 $<fmt:formatNumber value="${KualiForm.document.award.awardAmountInfos[KualiForm.document.award.indexOfLastAwardAmountInfo].amountObligatedToDate}" type="currency" currencySymbol="" maxFractionDigits="2" />           	 
            	</td>
				<th width="30%" align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.anticipatedTotal}" useShortLabel="true" noColon="false" /></th>
				<td width="20%">
            	  	 $<fmt:formatNumber value="${KualiForm.document.award.awardAmountInfos[KualiForm.document.award.indexOfLastAwardAmountInfo].anticipatedTotalAmount}" type="currency" currencySymbol="" maxFractionDigits="2" />
            	</td>
			</tr>
			</table>
			<table cellpadding="0" cellspacing="0">
			<tr>
				<th scope="row" width="6%">&nbsp;</th>
				<th width="17%"><kul:htmlAttributeLabel attributeEntry="${awardDirectFandADistributionAttributes.startDate}" useShortLabel="true" noColon="true" /></th>
				<th width="17%"><kul:htmlAttributeLabel attributeEntry="${awardDirectFandADistributionAttributes.endDate}" useShortLabel="true" noColon="true" /></th>
				<th width="20%"><kul:htmlAttributeLabel attributeEntry="${awardDirectFandADistributionAttributes.directCost}" useShortLabel="true" noColon="true"/></th>
				<th width="20%"><kul:htmlAttributeLabel attributeEntry="${awardDirectFandADistributionAttributes.indirectCost}" useShortLabel="true" noColon="true"/></th>
				<th width="20%"><div align="center">Actions</div></th>
			</tr>
			<c:if test="${!readOnly}">
			<tbody class="addline">
			<tr>
            	<th align="center" scope="row"><div align="center">Add:</div></th>
            	<td class="infoline">
            	  	<div align="center">
            	  	 	<kul:htmlControlAttribute property="awardDirectFandADistributionBean.newAwardDirectFandADistribution.startDate" attributeEntry="${awardDirectFandADistributionAttributes.startDate}" />
            	 	</div>
            	</td>
	            <td class="infoline">
	              	<div align="center">
	            		<kul:htmlControlAttribute property="awardDirectFandADistributionBean.newAwardDirectFandADistribution.endDate" attributeEntry="${awardDirectFandADistributionAttributes.endDate}" />
	              	</div>
	            </td>
	            <td class="infoline">
	            	<div align="right">
            	    	<kul:htmlControlAttribute property="awardDirectFandADistributionBean.newAwardDirectFandADistribution.directCost" attributeEntry="${awardDirectFandADistributionAttributes.directCost}" styleClass="amount"/>
            	  	</div>
	            </td>
	            <td class="infoline">
	            	<div align="right">
            	    	<kul:htmlControlAttribute property="awardDirectFandADistributionBean.newAwardDirectFandADistribution.indirectCost" attributeEntry="${awardDirectFandADistributionAttributes.indirectCost}" styleClass="amount"/>
            	  	</div>
	            </td>
	           <td class="infoline">
	            	<div align=center>
						<html:image property="methodToCall.addAwardDirectFandADistribution.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton addButton"/>
					</div>
	            </td>
	         </tr>
	         </tbody>
	         </c:if>
	          <c:forEach var="awardDirectFandADistribution" items="${KualiForm.document.award.awardDirectFandADistributions}" varStatus="status">
	             <tr>
					<th width="6%" class="infoline">
						<c:out value="${awardDirectFandADistribution.budgetPeriod}" />
					</th>
					<td width="17%" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.award.awardDirectFandADistributions[${status.index}].startDate" attributeEntry="${awardDirectFandADistributionAttributes.startDate}" />
					</div>
					</td>
	                <td width="17%" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.award.awardDirectFandADistributions[${status.index}].endDate" attributeEntry="${awardDirectFandADistributionAttributes.endDate}" />
					</div>
	                </td>
	                <td width="20%" valign="right">                	
					<div align="right">
                  		<kul:htmlControlAttribute property="document.award.awardDirectFandADistributions[${status.index}].directCost" attributeEntry="${awardDirectFandADistributionAttributes.directCost}" styleClass="amount"/> 
					</div>
					</td>
	                <td width="20%" valign="right">                	
					<div align="right">
                  		<kul:htmlControlAttribute property="document.award.awardDirectFandADistributions[${status.index}].indirectCost" attributeEntry="${awardDirectFandADistributionAttributes.indirectCost}" styleClass="amount"/> 
					</div>
					</td>
					<td width="10%">
					<div align="center">&nbsp;
					   <c:if test="${!readOnly}">
						<html:image property="methodToCall.deleteAwardDirectFandADistribution.line${status.index}.anchor${currentTabIndex}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
					   </c:if>
					</div>
	                </td>
	             </tr>
	           </c:forEach> 
	           <tr>
          		<th align="right" colspan="3" scope="row"><div>Total:</div></th>
          		<th align="right">
          			<div align="right">  		                		
	                	$<fmt:formatNumber value="${KualiForm.document.award.totalDirectFandADistributionDirectCostAmount}" type="currency" currencySymbol="" maxFractionDigits="2" />
	                </div>
	         	</th>
	         	<th align="right">
          			<div align="right">  		                		
	                	$<fmt:formatNumber value="${KualiForm.document.award.totalDirectFandADistributionIndirectCostAmount}" type="currency" currencySymbol="" maxFractionDigits="2" />
	                </div>
	         	</th>
	         	<th align="center" rowspan="2">
	         	 <c:if test="${!readOnly}">
					<html:image property="methodToCall.recalculateDirectFandADistributionTotals.anchor${tabKey}"
					src='${ConfigProperties.kra.externalizable.images.url}tinybutton-recalculate.gif' styleClass="tinybutton"/> 
				 </c:if>
				 <c:if test="${readOnly}">
				    &nbsp;
				 </c:if>
   				</th>  
          	   </tr>
          	   <tr>
          		<th align="right" colspan="3" scope="row"><div>Total Anticipated(Direct + F&A):</div></th>
          		<th align="right" colspan="2">
          			<div align="right">  		                		
	                	$<fmt:formatNumber value="${KualiForm.document.award.totalDirectFandADistributionAnticipatedCostAmount}" type="currency" currencySymbol="" maxFractionDigits="2" />
	                </div>
	         	</th>
		</table>
	</div>
</kul:tab>
