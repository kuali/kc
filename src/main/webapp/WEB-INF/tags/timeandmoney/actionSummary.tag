<%--
 Copyright 2006-2009 The Kuali Foundation
 
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

<c:set var="awardAmountInfoAttributes" value="${DataDictionary.AwardAmountInfo.attributes}" />
<c:set var="awardAmountTransactionAttributes" value="${DataDictionary.AwardAmountTransaction.attributes}" />

<kul:tab tabTitle="Action Summary" defaultOpen="false" tabErrorKey="">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left"> History</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.timeandmoney.transactions.PendingTransaction" altText="help"/></span>
        </h3>
        
        <table cellpadding="0" cellspacing="0" summary="">
        	<tr>
        		<th>
					<div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAmountTransactionAttributes.noticeDate}" readOnly="true" noColon="true" /></div>
       			</th>
       			<th>
					<div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAmountTransactionAttributes.transactionTypeCode}" readOnly="true" noColon="true" /></div>
       			</th>
       			
        		<th>
					<div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.currentFundEffectiveDate}" readOnly="true" noColon="true" /></div>
       			</th>
       			<th>
					<div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.obligationExpirationDate}" readOnly="true" noColon="true" /></div>
       			</th>
       			<th>
					<div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.amountObligatedToDate}" readOnly="true" noColon="true" /></div>
       			</th>
       			<th>
					<div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.obligatedChange}" readOnly="true" noColon="true" /></div>
       			</th>        	
        	</tr>
        	<c:forEach var="timeAndMoneyActionSummaryItems" items="${KualiForm.document.timeAndMoneyActionSummaryItems}" varStatus="status">
        	<tr>
        		<td align="center" valign="middle" >				        		
					<c:out value="${timeAndMoneyActionSummaryItems.noticeDate}" />
				</td>
				<td align="center" valign="middle" >				        		
					<c:out value="${timeAndMoneyActionSummaryItems.transactionType}" />
				</td>
				<td align="center" valign="middle" >				        		
					<c:out value="${timeAndMoneyActionSummaryItems.obligationStartDate}" />
				</td>
				<td align="center" valign="middle" >				        		
					<c:out value="${timeAndMoneyActionSummaryItems.obligationEndDate}" />
				</td>
				<td align="center" valign="middle" >				        		
					<c:out value="${timeAndMoneyActionSummaryItems.obligationCumulative}" />
				</td>
				<td align="center" valign="middle" >				        		
					<c:out value="${timeAndMoneyActionSummaryItems.changeAmount}" />
				</td>
        	</tr>
        	</c:forEach>
        
        </table>
    </div>    
</kul:tab>
