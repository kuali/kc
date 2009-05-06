<%--
 Copyright 2006-2008 The Kuali Foundation
 
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


<kul:tabTop tabTitle="Cost Share" defaultOpen="false" tabErrorKey="newAwardCostShare.*,document.awardList[0].awardCostShare*">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Cost Share</span>
        </h3>
        <table id="cost-share-table" cellpadding="0" cellspacing="0" summary="Cost Share">
			<tr>
				<th scope="row">&nbsp;</th>
				<th><kul:htmlAttributeLabel attributeEntry="${awardCostShareAttributes.costSharePercentage}" useShortLabel="true" noColon="true" /></th>
				<th><kul:htmlAttributeLabel attributeEntry="${awardCostShareAttributes.costShareTypeCode}" useShortLabel="true" noColon="true" /></th>
				<th><kul:htmlAttributeLabel attributeEntry="${awardCostShareAttributes.fiscalYear}" useShortLabel="true" noColon="true"/></th>
				<th><kul:htmlAttributeLabel attributeEntry="${awardCostShareAttributes.source}" useShortLabel="true" noColon="true"/></th>
				<th><kul:htmlAttributeLabel attributeEntry="${awardCostShareAttributes.destination}" useShortLabel="true" noColon="true"/></th>
				<th><kul:htmlAttributeLabel attributeEntry="${awardCostShareAttributes.verificationDate}" useShortLabel="true" noColon="true"/></th>
				<th><kul:htmlAttributeLabel attributeEntry="${awardCostShareAttributes.commitmentAmount}" useShortLabel="true" noColon="true"/></th>
				<th><kul:htmlAttributeLabel attributeEntry="${awardCostShareAttributes.costShareMet}" useShortLabel="true" noColon="true"/></th>
				<th><div align="center">Actions</div></th>
			</tr>
			
			<tr>
            	<th width="50" align="center" scope="row"><div align="right">Add:</div></th>
            	<td class="infoline">
            	  	<div align="center">
            	  	 	<kul:htmlControlAttribute property="costShareFormHelper.newAwardCostShare.costSharePercentage" attributeEntry="${awardCostShareAttributes.costSharePercentage}"/>
            	 	</div>
            	</td>
	            <td class="infoline">
	              	<div align="center">
	            		<kul:htmlControlAttribute property="costShareFormHelper.newAwardCostShare.costShareTypeCode" attributeEntry="${awardCostShareAttributes.costShareTypeCode}" />
	              	</div>
	            </td>
	            <td class="infoline">
	            	<div align="center">
            	    	<kul:htmlControlAttribute property="costShareFormHelper.newAwardCostShare.fiscalYear" attributeEntry="${awardCostShareAttributes.fiscalYear}"/>
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
	            	<div align="center">
            	   	 	<kul:htmlControlAttribute property="costShareFormHelper.newAwardCostShare.verificationDate" attributeEntry="${awardCostShareAttributes.verificationDate}" datePicker="true"/>
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
	            	<div align=center>
						<html:image property="methodToCall.addCostShare.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
					</div>
	            </td>
          	</tr>
          	
         <c:forEach var="awardCostShares" items="${KualiForm.document.awardList[0].awardCostShares}" varStatus="status">
	             <tr>
					<th width="5%" class="infoline">
						<c:out value="${status.index+1}" />
					</th>
	                <td width="10%" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.awardList[0].awardCostShares[${status.index}].costSharePercentage" attributeEntry="${awardCostShareAttributes.costSharePercentage}"/>
					</div>
					</td>
	                <td width="20%" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.awardList[0].awardCostShares[${status.index}].costShareTypeCode" attributeEntry="${awardCostShareAttributes.costShareTypeCode}"/>
					</div>
	                </td>
	                <td width="15%" valign="middle">                	
					<div align="center">
                  		<kul:htmlControlAttribute property="document.awardList[0].awardCostShares[${status.index}].fiscalYear" attributeEntry="${awardCostShareAttributes.fiscalYear}"/> 
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
	                <td width="15%" valign="middle">
					<div align="center">
	                	<kul:htmlControlAttribute property="document.awardList[0].awardCostShares[${status.index}].verificationDate" attributeEntry="${awardCostShareAttributes.verificationDate}" datePicker="true"/>
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
					<td width="10%">
					<div align="center">&nbsp;
						<html:image property="methodToCall.deleteCostShare.line${status.index}.anchor${currentTabIndex}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
					</div>
	                </td>
	            </tr>
        	</c:forEach> 
          	<tr>
          		<th align="center" scope="row"><div>Total:</div></th>
          		<th colspan="6" scope="row">&nbsp;</th>
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
	         	<th scope="row">&nbsp;</th>
          	</tr>
          	
        </table>
   </div>
   <div class="tab-container" align="center">
		<html:image property="methodToCall.recalculateCostShareTotal.anchor${tabKey}"
		src='${ConfigProperties.kra.externalizable.images.url}tinybutton-recalculate.gif' styleClass="tinybutton"/>
   </div>           
   <div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Comments</span>
        </h3>
        <table>
        	<th width="100" align="right" scope="row"><div align="center">Add:</div></th>
        	<td class="infoline">
            	 <div align="left">
            	  	 <kul:htmlControlAttribute property="document.awardList[0].awardCostShareComment.comments" attributeEntry="${awardCostShareCommentAttributes.comments}"/>
            	  	 <kra:expandedTextArea textAreaFieldName="document.awardList[0].awardCostShareComment.comments" action="${action}" textAreaLabel="${awardCostShareCommentAttributes.comments.label}" />
            	 </div>
            </td>
        </table>
    </div>
</kul:tabTop>
