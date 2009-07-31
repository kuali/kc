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
<c:set var="transactionDetailAttributes" value="${DataDictionary.TransactionDetail.attributes}" />

<kul:tab tabTitle="History" defaultOpen="false" tabErrorKey="">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left"> History</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.timeandmoney.transactions.PendingTransaction" altText="help"/></span>
        </h3>
        
        <table cellpadding="0" cellspacing="0" summary="">
          	<%-- Header --%>
          	<tr>
          		<kul:htmlAttributeHeaderCell literalLabel="Document" scope="col" />
          		<kul:htmlAttributeHeaderCell literalLabel="Description" scope="col" colspan="9"/>
          	</tr>
          	
          	<c:forEach var="timeAndMoneyHistory" items="${KualiForm.document.timeAndMoneyHistory}" varStatus="status">			          		
				<c:choose>
					<c:when test="${timeAndMoneyHistory.key.class.name == 'java.lang.String'}">
						<tr>
						<td align="left" valign="middle" class="infoline" rowspan="1">
		                	<div align="center">
		                		<c:out value="${timeAndMoneyHistory.key}" />
		                	</div>                	
						</td>
						<td align="left" valign="middle" class="infoline" colspan="9" >
	                	<div align="center">
	                		<c:out value="${timeAndMoneyHistory.value}" />
	                	</div>
						</td>
						</tr>
					</c:when>
					<c:otherwise>						
						<c:choose>							
							<c:when test="${timeAndMoneyHistory.value.class.name == 'org.kuali.kra.award.home.AwardAmountInfo'}">								
							<tr>
							<td align="left" valign="middle" class="infoline" rowspan="4">
			                	<div align="center">
			                		<c:out value="${timeAndMoneyHistory.key}" />
			                	</div>                	
							</td>
								<th>
									<div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.currentFundEffectiveDate}" readOnly="true" noColon="true" /></div>
			        			</th>
			        			<th>
									<div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.obligationExpirationDate}" readOnly="true" noColon="true" /></div>
			        			</th>
			        			<th>
									<div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.finalExpirationDate}" readOnly="true" noColon="true" /></div>
			        			</th>
			        			<th>
									<div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.obligatedChange}" readOnly="true" noColon="true" /></div>
			        			</th>
			        			<th>
									<div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.amountObligatedToDate}" readOnly="true" noColon="true" /></div>
			        			</th>
			        			<th>
									<div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.obliDistributableAmount}" readOnly="true" noColon="true" /></div>
			        			</th>
			        			<th>
									<div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.anticipatedChange}" readOnly="true" noColon="true" /></div>
			        			</th>
			        			<th>
									<div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.anticipatedTotalAmount}" readOnly="true" noColon="true" /></div>
			        			</th>
			        			<th>
									<div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.antDistributableAmount}" readOnly="true" noColon="true" /></div>
			        			</th>
			        		</tr>
			        		<tr>
			        			<td align="center" valign="middle">				        		
				        			<c:out value="${timeAndMoneyHistory.value.currentFundEffectiveDate}" />
				        		</td>
				        		<td align="center" valign="middle">				        		
				        			<c:out value="${timeAndMoneyHistory.value.obligationExpirationDate}" />
				        		</td>
				        		<td align="center" valign="middle">				        		
				        			<c:out value="${timeAndMoneyHistory.value.finalExpirationDate}" />
				        		</td>
				        		<td align="center" valign="middle">				        		
				        			<c:out value="${timeAndMoneyHistory.value.obligatedChange}" />
				        		</td>
				        		<td align="center" valign="middle">				        		
				        			<c:out value="${timeAndMoneyHistory.value.amountObligatedToDate}" />
				        		</td>
				        		<td align="center" valign="middle">				        		
				        			<c:out value="${timeAndMoneyHistory.value.obliDistributableAmount}" />
				        		</td>
				        		<td align="center" valign="middle">				        		
				        			<c:out value="${timeAndMoneyHistory.value.anticipatedChange}" />
				        		</td>
				        		<td align="center" valign="middle">				        		
				        			<c:out value="${timeAndMoneyHistory.value.anticipatedTotalAmount}" />
				        		</td>
				        		<td align="center" valign="middle">				        		
				        			<c:out value="${timeAndMoneyHistory.value.antDistributableAmount}" />
				        		</td>				        		
			        		</tr>	
							</c:when>
							<c:otherwise>
							<tr>
								<th colspan="5">									
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
			        			<td align="center" valign="middle" colspan="5">				        		
				        			<c:out value="${timeAndMoneyHistory.value.comments}" />
				        		</td>
				        		<td align="center" valign="middle">
				        			<c:choose>
				        				<c:when test="${timeAndMoneyHistory.value.sourceAwardNumber == '000000-00000'}">
				        					<c:out value="External" />
				        				</c:when>
				        				<c:otherwise>
				        					<c:out value="${timeAndMoneyHistory.value.sourceAwardNumber}" />
				        				</c:otherwise>
				        			</c:choose>
				        		</td>
				        		<td align="center" valign="middle">				        		
				        			<c:out value="${timeAndMoneyHistory.value.destinationAwardNumber}" />
				        		</td>
				        		<td align="center" valign="middle">				        		
				        			<c:out value="${timeAndMoneyHistory.value.obligatedAmount}" />
				        		</td>
				        		<td align="center" valign="middle">				        		
				        			<c:out value="${timeAndMoneyHistory.value.anticipatedAmount}" />
				        		</td>				        						        		
			        		</tr>
							</c:otherwise>
						</c:choose>						
					</c:otherwise>					
				</c:choose>          	
          	</c:forEach>
          	
        </table>  	
     </div>   
</kul:tab>
