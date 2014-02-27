<%--
 Copyright 2005-2014 The Kuali Foundation
 
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
<%@ attribute name="awardAmountInfoHistory" description="awardAmountInfoHistory" required="true" type="org.kuali.kra.timeandmoney.AwardAmountInfoHistory"%>

<c:set var="transactionDetailAttributes" value="${DataDictionary.TransactionDetail.attributes}" />

 <c:choose>
       	<c:when test="${awardAmountInfoHistory.transactionType == 'SINGLENODEMONEYTRANSACTION'}">
     		<c:set var="tabTitleAttribute" value="Single Node Transaction ID:" />
     		<c:set var="idValue" value="${awardAmountInfoHistory.primaryDetail.transactionDetailId}" />
       	</c:when>
       	<c:otherwise>
       		<c:set var="tabTitleAttribute" value="Transaction Details ID:" />
     		<c:set var="idValue" value="${awardAmountInfoHistory.primaryDetail.transactionDetailId}" />
       	</c:otherwise>
 </c:choose>

<kul:innerTab parentTab="History" defaultOpen="false" tabTitle="Transaction Details/${tabTitleAttribute} ${idValue}" tabErrorKey="" >
	<table border="0" cellpadding="0" cellspacing="0" summary="">
        <tr>
        	<th width="65%">
				<div align="center"><kul:htmlAttributeLabel attributeEntry="${transactionDetailAttributes.comments}" readOnly="true" noColon="true" /></div>
   			</th>
   			<th>
				<div align="center"><kul:htmlAttributeLabel attributeEntry="${transactionDetailAttributes.sourceAwardNumber}" readOnly="true" noColon="true" /></div>
   			</th>
   			<th>
				<div align="center"><kul:htmlAttributeLabel attributeEntry="${transactionDetailAttributes.destinationAwardNumber}" readOnly="true" noColon="true" /></div>
   			</th>
   			<th>
				<div align="center"><kul:htmlAttributeLabel attributeEntry="${transactionDetailAttributes.obligatedAmount}" readOnly="true" noColon="true" /></div>
   			</th>
   			<th>
				<div align="center"><kul:htmlAttributeLabel attributeEntry="${transactionDetailAttributes.anticipatedAmount}" readOnly="true" noColon="true" /></div>
   			</th>
   		</tr>
   		<tr>
   			<td align="left" class="infoline" rowspan="${awardAmountInfoHistory.transactionDetailTableSize}">
	          	<div align="center">
                	${awardAmountInfoHistory.primaryDetail.comments}
	            </div>                	
			</td>
			<td style="font-weight:bold">
				<div align="center">
					${awardAmountInfoHistory.primaryDetail.sourceAwardNumber}
				</div>
			</td>
			<td style="font-weight:bold">
				<div align="center">
					${awardAmountInfoHistory.primaryDetail.destinationAwardNumber}
				</div>
			</td>
			<td style="font-weight:bold">
				<div align="center">
					${awardAmountInfoHistory.primaryDetail.obligatedAmount}
				</div>
			</td>
			<td style="font-weight:bold">
				<div align="center">
					${awardAmountInfoHistory.primaryDetail.anticipatedAmount}
				</div>
			</td>
   		</tr>
   		<c:forEach var="transactionDetail" items="${awardAmountInfoHistory.intermediateDetails}" varStatus="status"> 
   			<tr>
				<td>
					<div align="center">
						${transactionDetail.sourceAwardNumber}
					</div>
				</td>
				<td>
					<div align="center">
						${transactionDetail.destinationAwardNumber}
					</div>
				</td>
				<td>
					<div align="center">
						${transactionDetail.obligatedAmount}
					</div>
				</td>
				<td>
					<div align="center">
						${transactionDetail.anticipatedAmount}
					</div>
				</td>
   			</tr>
   		</c:forEach>
	</table>
</kul:innerTab>	
   		
   		
   		
   		
   		
   		
   		