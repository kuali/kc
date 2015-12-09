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

<c:set var="awardAttributes" value="${DataDictionary.AwardDocument.attributes}" />
<c:set var="awardApprovedSubawardAttributes" value="${DataDictionary.AwardApprovedSubaward.attributes}" />
<c:set var="action" value="awardTimeAndMoney" />


<kul:tab tabTitle="Subawards" defaultOpen="false" tabErrorKey="newAwardApprovedSubaward.*,document.awardList[0].awardApprovedSubawards*,approvedSubawardFormHelper.*" auditCluster="subawardAuditErrors,subawardAuditWarnings" useRiceAuditMode="true" tabAuditKey="document.subawardAuditRules*,document.awardList[0].awardApprovedSubawards*">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Approved Subawards</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.award.home.approvedsubawards.AwardApprovedSubaward" altText="help"/></span>
        </h3>
        <table id="approved-subaward-table" cellpadding="0" cellspacing="0" summary="Approved Subaward">
			<tr>
				<th scope="row">&nbsp;</th>
				<th><kul:htmlAttributeLabel attributeEntry="${awardApprovedSubawardAttributes.organizationName}" useShortLabel="true" noColon="true" /></th>
				<th><kul:htmlAttributeLabel attributeEntry="${awardApprovedSubawardAttributes.amount}" useShortLabel="true" noColon="true" /></th>
				<th><div align="center">Actions</div></th>
			</tr>
			<c:if test="${!readOnly}">
			<tbody class="addline">
			<tr>
            	<th width="50" align="center" scope="row"><div align="center">Add:</div></th>
            	<td class="infoline">
            	  	<div align="center">
            	  	 	<kul:htmlControlAttribute property="approvedSubawardFormHelper.newAwardApprovedSubaward.organizationName" attributeEntry="${awardApprovedSubawardAttributes.organizationName}"/>
            	  	 	<kul:lookup boClassName="org.kuali.coeus.common.framework.org.Organization" fieldConversions="organizationName:approvedSubawardFormHelper.newAwardApprovedSubaward.organizationName,organizationId:approvedSubawardFormHelper.newAwardApprovedSubaward.organizationId" anchor="${tabKey}" />
            	  	 	<html:hidden property="approvedSubawardFormHelper.newAwardApprovedSubaward.organizationId"/>    	  	 	
            	  	    <kul:directInquiry boClassName="org.kuali.coeus.common.framework.org.Organization" inquiryParameters="approvedSubawardFormHelper.newAwardApprovedSubaward.organizationId:organizationId" anchor="${tabKey}" /> 
            	 	</div>
            	</td>
	            <td class="infoline">
	              	<div align="right">
	            		<kul:htmlControlAttribute property="approvedSubawardFormHelper.newAwardApprovedSubaward.amount" attributeEntry="${awardApprovedSubawardAttributes.amount}" styleClass="text-align: right" />
	              	</div>
	            </td>
	            <td class="infoline">
	            	<div align=center>
						<html:image property="methodToCall.addApprovedSubaward.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton addButton"/>
					</div>
	            </td>
          	</tr>
          	</tbody>
          	</c:if>
          	<c:forEach var="awardApprovedSubawards" items="${KualiForm.document.awardList[0].awardApprovedSubawards}" varStatus="status">
	             <tr>
					<th width="5%" class="infoline">
						${status.index+1}
					</th>
	                <td width="10%" valign="middle">
						${awardApprovedSubawards.organizationName}
						<input type="hidden" name="award_subaward.identifier_${status.index}" value="${awardApprovedSubawards.organizationId}"/>
						<kul:directInquiry boClassName="org.kuali.coeus.common.framework.org.Organization" inquiryParameters="award_subaward.identifier_${status.index}:organizationId" anchor="${tabKey}" />      	  	 		
					</td>
	                <td width="20%" valign="middle">
					<div align="right">
                		<kul:htmlControlAttribute property="document.awardList[0].awardApprovedSubawards[${status.index}].amount" attributeEntry="${awardApprovedSubawardAttributes.amount}" styleClass="text-align: right" />
					</div>
	                </td>
					<td width="10%">
					<div align="center">&nbsp;
					   <c:if test="${!readOnly}">
						<html:image property="methodToCall.deleteApprovedSubaward.line${status.index}.anchor${currentTabIndex}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
					   </c:if>
					</div>
	                </td>
	            </tr>
        	</c:forEach> 
        	<tr>
          		<th colspan="2" align="right" scope="row"><div>Total:</div></th>
          		<th align="right"> 		                		
	                	$<fmt:formatNumber value="${KualiForm.document.awardList[0].totalApprovedSubawardAmount}" type="currency" currencySymbol="" maxFractionDigits="2" />
	         	</th>
	         	<th align="center" scope="row">
	         			<html:image property="methodToCall.recalculateSubawardTotal.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-recalculate.gif' styleClass="tinybutton"/>
	         	</th>
          	</tr>
        </table>
        <h3>
    		<span class="subhead-left">Subawards where this award is a Funding source</span>
        </h3>
        <table id="subaward-table" cellpadding="0" cellspacing="0" summary="Subawards">
        			<tr>
        			<th>
        			</th>
        			<th>
					<div align="center">Subrecipient Name:</div></th>
					
       				<th>
						<div align="center">Subaward Id:</div></th>
					
					<th>
					<div align="center">Obligated Amount:</div></th>
					
					<th>
					<div align="center">Subaward Status:</div></th>
					
					</tr>
                  	<c:forEach var="awardLinkedSubawards" items="${KualiForm.document.awardList[0].subAwardList}" varStatus="status">
                  	<tr>
                  					 <td width="8%" valign="middle"> 
						    
						    <a
						href="${ConfigProperties.application.url}/subAwardHome.do?methodToCall=docHandler&command=displayDocSearchView&docId=${awardLinkedSubawards.subAwardDocument.documentNumber}&medusaOpenedDoc=true"
						target="_blank" class="medusaOpenLink">Open Subaward</a>
						</td>
                 					<td>${awardLinkedSubawards.organizationName}&nbsp; </td>
                 					<td>${awardLinkedSubawards.subAwardCode}&nbsp; </td>
									<c:choose>
										<c:when test="${awardLinkedSubawards.latestSubAwardAmountInfo != null}">
											<td>${awardLinkedSubawards.latestSubAwardAmountInfo.obligatedAmount}&nbsp;</td>
										</c:when>
										<c:otherwise>
											<td>0.00&nbsp;</td>
										</c:otherwise>
									</c:choose>
                 					<td>${awardLinkedSubawards.subAwardStatus.description}</td>
                  	</tr>
        			</c:forEach>
        </table>
     </div>
</kul:tab>
