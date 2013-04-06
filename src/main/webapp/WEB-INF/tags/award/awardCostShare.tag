<%--
 Copyright 2005-2013 The Kuali Foundation
 
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

<c:set var="awardAttributes" value="${DataDictionary.AwardDocument.attributes}" />
<c:set var="awardCostShareAttributes" value="${DataDictionary.AwardCostShare.attributes}" />
<c:set var="awardCostShareCommentAttributes" value="${DataDictionary.AwardComment.attributes}" />
<c:set var="action" value="awardTimeAndMoney" />


<kul:tabTop tabTitle="Cost Sharing" defaultOpen="false" tabErrorKey="newAwardCostShare.*,costShareFormHelper.newAwardCostShare.*,document.awardList[0].awardCostShare*"
			auditCluster="costShareAuditErrors" tabAuditKey="document.awardList[0].awardCostShares*">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Cost Sharing</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.award.commitments.AwardCostShare" altText="help"/></span>
        </h3>
        <table id="cost-share-table" cellpadding="0" cellspacing="0" summary="Cost Share">
			<tr>
				<th scope="row">&nbsp;</th>
				<th><kul:htmlAttributeLabel attributeEntry="${awardCostShareAttributes.costSharePercentage}" useShortLabel="true" noColon="true" /></th>
				<th><kul:htmlAttributeLabel attributeEntry="${awardCostShareAttributes.costShareTypeCode}" useShortLabel="true" noColon="true" /></th>
				
				<th>
					* ${KualiForm.costShareFormHelper.projectPeriodLabel }
					<%--<kul:htmlAttributeLabel attributeEntry="${awardCostShareAttributes.projectPeriod}" useShortLabel="true" noColon="true"/>--%>
				</th>
				
				<th><kul:htmlAttributeLabel attributeEntry="${awardCostShareAttributes.source}" useShortLabel="true" noColon="true"/></th>
				<th><kul:htmlAttributeLabel attributeEntry="${awardCostShareAttributes.destination}" useShortLabel="true" noColon="true"/></th>
				<th><kul:htmlAttributeLabel attributeEntry="${awardCostShareAttributes.commitmentAmount}" useShortLabel="true" noColon="true"/></th>
				<th><kul:htmlAttributeLabel attributeEntry="${awardCostShareAttributes.costShareMet}" useShortLabel="true" noColon="true"/></th>
                <th><kul:htmlAttributeLabel attributeEntry="${awardCostShareAttributes.verificationDate}" useShortLabel="true" noColon="true"/></th>
				<th><div align="center">Actions</div></th>
			</tr>
			
			<c:if test="${!readOnly}">
			<tr>
            	<th width="50" align="center" scope="row"><div align="right">Add:</div></th>
            	<td class="infoline" style="white-space: nowrap;">
            	  	<div align="center">
            	  	 	<kul:htmlControlAttribute property="costShareFormHelper.newAwardCostShare.costSharePercentage" attributeEntry="${awardCostShareAttributes.costSharePercentage}" styleClass="amount"/>%
            	 	</div>
            	</td>
	            <td class="infoline">
	              	<div align="center">
	            		<kul:htmlControlAttribute property="costShareFormHelper.newAwardCostShare.costShareTypeCode" attributeEntry="${awardCostShareAttributes.costShareTypeCode}" />
	              	</div>
	            </td>
	            <td class="infoline">
	            	<div align="center">
            	    	<kul:htmlControlAttribute property="costShareFormHelper.newAwardCostShare.projectPeriod" attributeEntry="${awardCostShareAttributes.projectPeriod}"/>
            	  	</div>
	            </td>
	            <td class="infoline">
	            	<div align="center">
            	    	<kul:htmlControlAttribute property="costShareFormHelper.newAwardCostShare.source" attributeEntry="${awardCostShareAttributes.source}"/>
            	  	</div>
	            </td>
	            <td class="infoline">
	            	<div align="center">
            	   	 	<kul:htmlControlAttribute property="costShareFormHelper.newAwardCostShare.destination" attributeEntry="${awardCostShareAttributes.destination}"/>
            	  	</div>
	            </td>
	            <td class="infoline">
	            	<div align="right">
            	    	<kul:htmlControlAttribute property="costShareFormHelper.newAwardCostShare.commitmentAmount" attributeEntry="${awardCostShareAttributes.commitmentAmount}" styleClass="amount"/>
            	  	</div>
	            </td>
	             <td class="infoline">
	            	<div align="right">
            	    	<kul:htmlControlAttribute property="costShareFormHelper.newAwardCostShare.costShareMet" attributeEntry="${awardCostShareAttributes.costShareMet}" styleClass="amount"/>
            	  	</div>
	            </td>
                <td class="infoline">
                    <div align="center">
                        <kul:htmlControlAttribute property="costShareFormHelper.newAwardCostShare.verificationDate" attributeEntry="${awardCostShareAttributes.verificationDate}" />
                    </div>
                </td>
	            <td class="infoline">
	            	<div align=center>
						<html:image property="methodToCall.addCostShare.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
					</div>
	            </td>
          	</tr>
          	</c:if>
          	
         <c:forEach var="awardCostShares" items="${KualiForm.document.awardList[0].awardCostShares}" varStatus="status">
	             <tr>
					<th width="5%" class="infoline">
						<c:out value="${status.index+1}" />
					</th>
	                <td width="10%" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.awardList[0].awardCostShares[${status.index}].costSharePercentage" attributeEntry="${awardCostShareAttributes.costSharePercentage}" styleClass="amount"/>
					</div>
					</td>
	                <td width="20%" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.awardList[0].awardCostShares[${status.index}].costShareTypeCode" attributeEntry="${awardCostShareAttributes.costShareTypeCode}"/>
					</div>
	                </td>
	                <td width="15%" valign="middle">                	
					<div align="center">
                  		<kul:htmlControlAttribute property="document.awardList[0].awardCostShares[${status.index}].projectPeriod" attributeEntry="${awardCostShareAttributes.projectPeriod}"/> 
					</div>
					</td>
	                <td width="15%" valign="middle">                	
					<div align="center">
                  		<kul:htmlControlAttribute property="document.awardList[0].awardCostShares[${status.index}].source" attributeEntry="${awardCostShareAttributes.source}"/> 
					</div>
					</td>
	                <td width="15%" valign="middle">
					<div align="center">
	                	<kul:htmlControlAttribute property="document.awardList[0].awardCostShares[${status.index}].destination" attributeEntry="${awardCostShareAttributes.destination}"/>
					</div>
	                </td>
	                <td width="15%" valign="right">
					<div align="right">
	                	<kul:htmlControlAttribute property="document.awardList[0].awardCostShares[${status.index}].commitmentAmount" attributeEntry="${awardCostShareAttributes.commitmentAmount}" styleClass="amount"/>
					</div>
	                </td>
	                <td width="15%" valign="right">
					<div align="right">
	                	<kul:htmlControlAttribute property="document.awardList[0].awardCostShares[${status.index}].costShareMet" attributeEntry="${awardCostShareAttributes.costShareMet}" styleClass="amount"/>
					</div>
	                </td>
                    <td width="15%" valign="middle">
                    <div align="center">
                        <kul:htmlControlAttribute property="document.awardList[0].awardCostShares[${status.index}].verificationDate" attributeEntry="${awardCostShareAttributes.verificationDate}" />
                    </div>
                    </td>
					<td width="10%">
					<div align="center">&nbsp;
					   <c:if test="${!readOnly}">
						<html:image property="methodToCall.deleteCostShare.line${status.index}.anchor${currentTabIndex}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
					   </c:if>
					</div>
	                </td>
	            </tr>
        	</c:forEach> 
          	<tr>
          		<th align="center" scope="row"><div>Total:</div></th>
          		<th colspan="5" scope="row">&nbsp;</th>
          		<th align="right">
          			<div align="right">  		                		
	                	$<fmt:formatNumber value="${KualiForm.document.awardList[0].totalCostShareCommitmentAmount}" type="currency" currencySymbol="" maxFractionDigits="2" />
	                </div>
	         	</th>
	         	<th align="right">
          			<div align="right">  		                		
	                	$<fmt:formatNumber value="${KualiForm.document.awardList[0].totalCostShareMetAmount}" type="currency" currencySymbol="" maxFractionDigits="2" />
	                </div>
	         	</th>
	         	<th colspan="2" align="center">
                    <c:if test="${!readOnly}">
    	         	    <html:image property="methodToCall.recalculateCostShareTotal.anchor${tabKey}"
                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-recalculate.gif' styleClass="tinybutton"/>
                   </c:if>
	         	</th>
          	</tr>
          	
        	<th width="100" align="right" scope="row"><div align="center">Comments:</div></th>
        	<td colspan="9" class="infoline">
            	 <div align="left">
            	  	 <kul:htmlControlAttribute property="document.awardList[0].awardCostShareComment.comments" attributeEntry="${awardCostShareCommentAttributes.comments}"/>
            	 </div>
            </td>
        </table>
		<div align="center">
		</br>
		<c:if test = "${(!readOnly)}">
			<kra-a:awardSyncButton  scopeNames="COST_SHARE" tabKey="${tabKey}"/>
		</c:if>
		</div>	        
    </div>
</kul:tabTop>
