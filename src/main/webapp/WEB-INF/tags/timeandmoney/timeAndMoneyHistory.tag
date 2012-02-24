<%--
 Copyright 2005-2010 The Kuali Foundation
 
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
<c:set var="awardCurrentActionCommentAttributes" value="${DataDictionary.AwardComment.attributes}" />


<kul:tab tabTitle="History (${KualiForm.document.awardNumber})" defaultOpen="false" tabErrorKey="">
	<div class="tab-container" align="center">
   		<h3>
   			<span class="subhead-left"> History</span>
       	    <span class="subhead-right"><kul:help parameterNamespace="KC-T" parameterDetailType="Document" parameterName="tmHistoryHelpUrl" altText="help"/></span>
        </h3>
       	<table cellpadding="0" cellspacing="0" summary="">
        	<tr>
         		<kul:htmlAttributeHeaderCell literalLabel="Document" scope="col" />
         		<c:choose>
	        		<c:when test="${KualiForm.directIndirectViewEnabled == '1'}">
          				<kul:htmlAttributeHeaderCell literalLabel="Description" scope="col" colspan="13"/>
          			</c:when>
					<c:otherwise>
						<kul:htmlAttributeHeaderCell literalLabel="Description" scope="col" colspan="9"/>
					</c:otherwise>
				 </c:choose>
      		</tr>	
        <c:forEach var="awardVersionHistory" items="${KualiForm.document.awardVersionHistoryList}" varStatus="status"> 
        	<tr>
				<td align="left" valign="left" class="infoline" rowspan="1">
		          	<div align="center">
	                	${awardVersionHistory.documentUrl}"
		            </div>                	
				</td>
				<c:choose>
	        		<c:when test="${KualiForm.directIndirectViewEnabled == '1'}">
          				<td style="text-align: left; background-color: rgb(195, 195, 195); font-weight: bold; colspan: 13" colspan="13" />
          			</c:when>
					<c:otherwise>
						<td style="text-align: left; background-color: rgb(195, 195, 195); font-weight: bold; colspan: 9" colspan="9" />
					</c:otherwise>
				 </c:choose>
                	<div align="left">
                		<c:out value="${awardVersionHistory.awardDescriptionLine}" />
                	</div>
				</td>
			</tr>
			<c:forEach var="timeAndMoneyDocumentHistory" items="${awardVersionHistory.timeAndMoneyDocumentHistoryList}" varStatus="status"> 
				<tr>
				<td  align="left" valign="left" class="infoline" rowspan="1">
		          	<div align="center">
	                	${timeAndMoneyDocumentHistory.documentUrl}"
		            </div>                	
				</td>
				<c:choose>
	        		<c:when test="${KualiForm.directIndirectViewEnabled == '1'}">
          				<td align="left" valign="left" class="infoline" colspan="13" >
          			</c:when>
					<c:otherwise>
						<td align="left" valign="left" class="infoline" colspan="9" >
					</c:otherwise>
				 </c:choose>
                	<div align="left">
                		<c:out value="${timeAndMoneyDocumentHistory.timeAndMoneyDocumentDescriptionLine}" />
                	</div>
				</td>
				</tr>
				<c:forEach var="awardAmountInfoHistory" items="${timeAndMoneyDocumentHistory.validAwardAmountInfoHistoryList}" varStatus="status"> 
					<tr>
						<td align="center" valign="middle" rowspan="2" >
							<div align="center" >
						        <c:choose>
						        	<c:when test="${awardAmountInfoHistory.transactionType == 'MONEY'}">
						      			<c:out value="Transaction ID: " />
						        		<c:out value="${awardAmountInfoHistory.primaryDetail.transactionId}" />
						        	</c:when>
						        	<c:when test="${awardAmountInfoHistory.transactionType == 'DATE'}">
						        		<c:out value="No Cost Extension" />
						        	</c:when>
						        	<c:when test="${awardAmountInfoHistory.transactionType == 'SINGLENODEMONEYTRANSACTION'}">
						        		<c:out value="Single Node Money Transaction" />
						        	</c:when>
						        	<c:otherwise>
						        		<c:out value="Initial Transaction" />
						        	</c:otherwise>
						        </c:choose>
						     </div>	
						</td>
						
				<c:choose>
	        		<c:when test="${KualiForm.directIndirectViewEnabled == '1'}">
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
							<div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.obligatedChangeDirect}" readOnly="true" noColon="true" /></div>
	        			</th>
	        			<th>
							<div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.obligatedChangeIndirect}" readOnly="true" noColon="true" /></div>
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
							<div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.anticipatedChangeDirect}" readOnly="true" noColon="true" /></div>
	        			</th>
	        			<th>
							<div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.anticipatedChangeIndirect}" readOnly="true" noColon="true" /></div>
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
	        			<div align="center" >
		        			<fmt:formatDate value="${awardAmountInfoHistory.awardAmountInfo.currentFundEffectiveDate}" pattern="MM/dd/yyyy" />
		        		</div>
		        		</td>				        		
		        		<td align="center" valign="middle">
		        		<div align="center" >
		        			<fmt:formatDate value="${awardAmountInfoHistory.awardAmountInfo.obligationExpirationDate}" pattern="MM/dd/yyyy" />
		        		</div>	
		        		</td>
		        		</div>				        		
		        		<td align="center" valign="middle">
		        		<div align="center" >
		        			<fmt:formatDate value="${awardAmountInfoHistory.awardAmountInfo.finalExpirationDate}" pattern="MM/dd/yyyy" />
		        		</div>
		        		</td>
		        		<td align="center" valign="middle">
		        		<div align="center" >				        		
		        			${awardAmountInfoHistory.awardAmountInfo.obligatedChange}
		        		</div>	
		        		</td>
		        		<td align="center" valign="middle">
		        		<div align="center" >				        		
		        			${awardAmountInfoHistory.awardAmountInfo.obligatedChangeDirect}
		        		</div>	
		        		</td>
		        		<td align="center" valign="middle">
		        		<div align="center" >				        		
		        			${awardAmountInfoHistory.awardAmountInfo.obligatedChangeIndirect}
		        		</div>	
		        		</td>
		        		<td align="center" valign="middle">
		        		<div align="center" >				        		
		        			${awardAmountInfoHistory.awardAmountInfo.amountObligatedToDate}
		        		</div>	
		        		</td>
		        		<td align="center" valign="middle">
		        		<div align="center" >				        		
		        			${awardAmountInfoHistory.awardAmountInfo.obliDistributableAmount}
		        		</div>	
		        		</td>
		        		<td align="center" valign="middle">
		        		<div align="center" >				        		
		        			${awardAmountInfoHistory.awardAmountInfo.anticipatedChange}
		        		</div>	
		        		</td>
		        		<td align="center" valign="middle">
		        		<div align="center" >				        		
		        			${awardAmountInfoHistory.awardAmountInfo.anticipatedChangeDirect}
		        		</div>	
		        		</td>
		        		<td align="center" valign="middle">
		        		<div align="center" >				        		
		        			${awardAmountInfoHistory.awardAmountInfo.anticipatedChangeIndirect}
		        		</div>	
		        		</td>
		        		<td align="center" valign="middle">
		        		<div align="center" >				        		
		        			${awardAmountInfoHistory.awardAmountInfo.anticipatedTotalAmount}
		        		</div>	
		        		</td>
		        		<td align="center" valign="middle">
		        		<div align="center" >				        		
		        			${awardAmountInfoHistory.awardAmountInfo.antDistributableAmount}
		        		</div>	
		        		</td>		        		
	        		</tr>
	        		</c:when>
        			<c:otherwise>
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
	        			<div align="center" >
		        			<fmt:formatDate value="${awardAmountInfoHistory.awardAmountInfo.currentFundEffectiveDate}" pattern="MM/dd/yyyy" />
		        		</div>
		        		</td>				        		
		        		<td align="center" valign="middle">
		        		<div align="center" >
		        			<fmt:formatDate value="${awardAmountInfoHistory.awardAmountInfo.obligationExpirationDate}" pattern="MM/dd/yyyy" />
		        		</div>	
		        		</td>
		        		</div>				        		
		        		<td align="center" valign="middle">
		        		<div align="center" >
		        			<fmt:formatDate value="${awardAmountInfoHistory.awardAmountInfo.finalExpirationDate}" pattern="MM/dd/yyyy" />
		        		</div>
		        		</td>
		        		<td align="center" valign="middle">
		        		<div align="center" >				        		
		        			${awardAmountInfoHistory.awardAmountInfo.obligatedChange}
		        		</div>	
		        		</td>
		        		<td align="center" valign="middle">
		        		<div align="center" >				        		
		        			${awardAmountInfoHistory.awardAmountInfo.amountObligatedToDate}
		        		</div>	
		        		</td>
		        		<td align="center" valign="middle">
		        		<div align="center" >				        		
		        			${awardAmountInfoHistory.awardAmountInfo.obliDistributableAmount}
		        		</div>	
		        		</td>
		        		<td align="center" valign="middle">
		        		<div align="center" >				        		
		        			${awardAmountInfoHistory.awardAmountInfo.anticipatedChange}
		        		</div>	
		        		</td>
		        		<td align="center" valign="middle">
		        		<div align="center" >				        		
		        			${awardAmountInfoHistory.awardAmountInfo.anticipatedTotalAmount}
		        		</div>	
		        		</td>
		        		<td align="center" valign="middle">
		        		<div align="center" >				        		
		        			${awardAmountInfoHistory.awardAmountInfo.antDistributableAmount}
		        		</div>	
		        		</td>		        		
	        		</tr>
        			</c:otherwise>
	        	</c:choose>
	        			<c:if test="${awardAmountInfoHistory.transactionType == 'MONEY'}">
		        			<tr>
		        			<c:choose>
				        		<c:when test="${KualiForm.directIndirectViewEnabled == '1'}">
			          				<td colspan="14">
			          			</c:when>
								<c:otherwise>
									<td colspan="10">
								</c:otherwise>
							 </c:choose>
			        			<div>
			        				<kra-timeandmoney:transactionDetails awardAmountInfoHistory="${awardAmountInfoHistory}" />
			        			</div>
			        			</td>		
		        			</tr>
		        		</c:if>	
		        		<c:if test="${awardAmountInfoHistory.transactionType == 'SINGLENODEMONEYTRANSACTION'}">
		        			<tr>
		        			<c:choose>
				        		<c:when test="${KualiForm.directIndirectViewEnabled == '1'}">
			          				<td colspan="14">
			          			</c:when>
								<c:otherwise>
									<td colspan="10">
								</c:otherwise>
							 </c:choose>
			        			<div>
			        				<kra-timeandmoney:transactionDetails awardAmountInfoHistory="${awardAmountInfoHistory}" />
			        			</div>
			        			</td>		
		        			</tr>
		        		</c:if>	
				</c:forEach>
			</c:forEach>
        </c:forEach> 
        </table>       			
	</div>   
</kul:tab>