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

<c:set var="awardAmountInfoAttributes" value="${DataDictionary.AwardAmountInfo.attributes}" />
<c:set var="transactionDetailAttributes" value="${DataDictionary.TransactionDetail.attributes}" />

<kul:tab tabTitle="History (${KualiForm.document.awardNumber})" defaultOpen="false" tabErrorKey="">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left"> History</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.timeandmoney.history.TransactionDetail" altText="help"/></span>
        </h3>
        
        <table cellpadding="0" cellspacing="0" summary="">
          	<%-- Header --%>
          	<tr>
          		<kul:htmlAttributeHeaderCell literalLabel="Document" scope="col" />
          		<kul:htmlAttributeHeaderCell literalLabel="Description" scope="col" colspan="9"/>
          	</tr>
          	
          	<c:set var="displayedOnce" value="false" />
          	<c:set var="rowSpanIndex" value="0" />
          	
          	<c:set var="moneyTransaction" value="true" />
          	
          	<c:forEach var="timeAndMoneyHistory" items="${KualiForm.document.timeAndMoneyHistory}" varStatus="status">         		
          			<c:choose>
					<c:when test="${timeAndMoneyHistory.key.class.name == 'java.lang.String'}">
						<tr>
						<td align="left" valign="middle" class="infoline" rowspan="1">
		                	<div align="center">
		                		${timeAndMoneyHistory.key}
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
							<%--<c:set var="displayedOnce" value="false" />
							<c:set var="displayedOnceForThis" value="false" />	--%>							
							<tr>
							<td align="left" valign="middle" class="infoline" rowspan="4">
								<c:set var="rowSpanForOther" value="1" />
								<c:set var="rowSpanIndex" value="${rowSpanIndex+1}" />							
			                	<div align="center">
			                	<c:choose>
			                		<c:when test="${timeAndMoneyHistory.key > 0}" >	
			                			<c:set var="moneyTransaction" value="true" />		                	
			                			<c:out value="${timeAndMoneyHistory.key}" />
			                		</c:when>
			                		<c:when test="${timeAndMoneyHistory.key == 0}" >	
			                			<c:set var="moneyTransaction" value="false" />		                	
			                			<c:out value="Initial Transaction" />
			                		</c:when>
			                		<c:otherwise>
			                			<c:set var="moneyTransaction" value="false" />
			                			<c:out value="Date Change:" />
			                		</c:otherwise>
			                	</c:choose>
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
			        			<div align="center" >
				        			<fmt:formatDate value="${timeAndMoneyHistory.value.currentFundEffectiveDate}" pattern="MM/dd/yyyy" />
				        		</div>
				        		</td>				        		
				        		<td align="center" valign="middle">
				        		<div align="center" >
				        			<fmt:formatDate value="${timeAndMoneyHistory.value.obligationExpirationDate}" pattern="MM/dd/yyyy" />
				        		</div>	
				        		</td>
				        		</div>				        		
				        		<td align="center" valign="middle">
				        		<div align="center" >
				        			<fmt:formatDate value="${timeAndMoneyHistory.value.finalExpirationDate}" pattern="MM/dd/yyyy" />
				        		</div>
				        		</td>
				        		<td align="center" valign="middle">
				        		<div align="center" >				        		
				        			${timeAndMoneyHistory.value.obligatedChange}
				        		</div>	
				        		</td>
				        		<td align="center" valign="middle">
				        		<div align="center" >				        		
				        			${timeAndMoneyHistory.value.amountObligatedToDate}
				        		</div>	
				        		</td>
				        		<td align="center" valign="middle">
				        		<div align="center" >				        		
				        			${timeAndMoneyHistory.value.obliDistributableAmount}
				        		</div>	
				        		</td>
				        		<td align="center" valign="middle">
				        		<div align="center" >				        		
				        			${timeAndMoneyHistory.value.anticipatedChange}
				        		</div>	
				        		</td>
				        		<td align="center" valign="middle">
				        		<div align="center" >				        		
				        			${timeAndMoneyHistory.value.anticipatedTotalAmount}
				        		</div>	
				        		</td>
				        		<td align="center" valign="middle">
				        		<div align="center" >				        		
				        			${timeAndMoneyHistory.value.antDistributableAmount}
				        		</div>	
				        		</td>				        		
			        		</tr>	
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${moneyTransaction}">
										<%--<c:if test="${displayedOnce!=true}" >
											<c:set var="displayedOnce" value="true" />--%>
									<tr>
										<th colspan="5" >									
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
					        		<%--</c:if>--%>
					        		<tr>
					        			<%--<c:if test="${displayedOnceForThis!=true}" >
										<c:set var="displayedOnceForThis" value="true" />--%>
					        			<td align="center" valign="middle" colspan="5" rowspan="${rowSpanForOther}" >
					        				<c:set var="displayedOnceForThis" value="true" />				        		
						        			<c:out value="${timeAndMoneyHistory.value.comments}" />
						        		</td>
						        		<%--</c:if>--%>
						        		<td align="center" valign="middle">
						        		<div align="center" >
						        			<c:choose>
						        				<c:when test="${timeAndMoneyHistory.value.sourceAwardNumber == '000000-00000'}">
						        					External
						        				</c:when>
						        				<c:otherwise>
						        					${timeAndMoneyHistory.value.sourceAwardNumber}
						        				</c:otherwise>
						        			</c:choose>
						        		</div>	
						        		</td>
						        		<td align="center" valign="middle">
						        		<div align="center" >				        		
						        			${timeAndMoneyHistory.value.destinationAwardNumber}
						        		</div>
						        		</td>
						        		<td align="center" valign="middle">
						        		<div align="center" >				        		
						        			${timeAndMoneyHistory.value.obligatedAmount}
						        		</div>
						        		</td>
						        		<td align="center" valign="middle">				        		
						        		<div align="center" >
						        			${timeAndMoneyHistory.value.anticipatedAmount}
						        		</div>
						        		</td>				        						        		
						        		</tr>
						        		
						        		</c:when>
						        		<c:otherwise>
						        			<%--<c:if test="${displayedOnce!=true}" >
											<c:set var="displayedOnce" value="true" />--%>
												<tr>
													<th colspan="5" >									
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
					        				<%--</c:if>--%>
					        					<tr>
								        			<%--<c:if test="${displayedOnceForThis!=true}" >
													<c:set var="displayedOnceForThis" value="true" />--%>
								        			<td align="center" valign="middle" colspan="5" rowspan="${rowSpanForOther}" >
								        				<c:set var="displayedOnceForThis" value="true" />				        		
									        			<c:out value="${timeAndMoneyHistory.value.comments}" />
									        		</td>
									        		<%--</c:if>--%>
									        		<td align="center" valign="middle">
									        		<div align="center" >
									        			<c:out value="N/A" />
									        		</div>	
									        		</td>
									        		<td align="center" valign="middle">
									        		<div align="center" >				        		
									        			<c:out value="N/A" />
									        		</div>
									        		</td>
									        		<td align="center" valign="middle">
									        		<div align="center" >				        		
									        			<c:out value="N/A" />
									        		</div>
									        		</td>
									        		<td align="center" valign="middle">				        		
									        		<div align="center" >
									        			<c:out value="N/A" />
									        		</div>
									        		</td>				        						        		
						        				</tr>
						        		</c:otherwise>
						        		</c:choose>
							</c:otherwise>
						</c:choose>												
					</c:otherwise>
					</c:choose>	 	
          	</c:forEach>
          	
        </table>  	
     </div>   
</kul:tab>
