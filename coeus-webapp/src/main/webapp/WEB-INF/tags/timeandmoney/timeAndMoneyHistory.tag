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

<c:set var="awardAmountInfoAttributes" value="${DataDictionary.AwardAmountInfo.attributes}" />
<c:set var="transactionDetailAttributes" value="${DataDictionary.TransactionDetail.attributes}" />
<c:set var="awardCurrentActionCommentAttributes" value="${DataDictionary.AwardComment.attributes}" />
<style>
	td.datacell div{
		text-align: right;
	}
</style>

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
	                	${awardVersionHistory.documentUrl}
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
			<%-- if no T&M document, then use Award info --%>
			<c:if test="${fn:length(awardVersionHistory.timeAndMoneyDocumentHistoryList) == 0}">
			    <c:set var="awardVersion" value="${awardVersionHistory.awardParent}" />
		        <c:set var="awardAwardAmountInfo" value="${awardVersion.lastAwardAmountInfo}" />
    	    	<tr>
                    <td align="center" valign="middle" rowspan="2">
                        <div align="center" >
                            Award Transaction
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
					<c:if  test="${KualiForm.directIndirectViewEnabled == '1'}">
	        			<th>
							<div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.obligatedChangeDirect}" readOnly="true" noColon="true" /></div>
        				</th>
        				<th>
							<div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.obligatedChangeIndirect}" readOnly="true" noColon="true" /></div>
        				</th>
	          		</c:if>
        			<th>
						<div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.amountObligatedToDate}" readOnly="true" noColon="true" /></div>
        			</th>
        			<th>
						<div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.obliDistributableAmount}" readOnly="true" noColon="true" /></div>
        			</th>
        			<th>
						<div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.anticipatedChange}" readOnly="true" noColon="true" /></div>
        			</th>
					<c:if  test="${KualiForm.directIndirectViewEnabled == '1'}">
	        			<th>
							<div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.anticipatedChangeDirect}" readOnly="true" noColon="true" /></div>
        				</th>
        				<th>
							<div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.anticipatedChangeIndirect}" readOnly="true" noColon="true" /></div>
        				</th>
	          		</c:if>
        			<th>
						<div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.anticipatedTotalAmount}" readOnly="true" noColon="true" /></div>
        			</th>
        			<th>
						<div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.antDistributableAmount}" readOnly="true" noColon="true" /></div>
        			</th>
    	  		</tr>
    	    	<tr>
        			<td class="datacell"><div>
	        			<fmt:formatDate value="${awardAwardAmountInfo.currentFundEffectiveDate}" pattern="MM/dd/yyyy" />
	        		</div></td>
							<td class="datacell"><div>
	        			<fmt:formatDate value="${awardAwardAmountInfo.obligationExpirationDate}" pattern="MM/dd/yyyy" />
	        		</div></td>
	        		<td class="datacell"><div>
	        			<fmt:formatDate value="${awardAwardAmountInfo.finalExpirationDate}" pattern="MM/dd/yyyy" />
	        		</div></td>
	        		<td class="datacell"><div>
	        			<fmt:formatNumber type="currency" currencySymbol="" value="${awardAwardAmountInfo.obligatedChange}"/>
	        		</div></td>
					<c:if  test="${KualiForm.directIndirectViewEnabled == '1'}">
		        		<td class="datacell"><div>
		        			<fmt:formatNumber type="currency" currencySymbol="" value="${awardAwardAmountInfo.obligatedChangeDirect}"/>
		        		</div></td>
		        		<td class="datacell"><div>
		        			<fmt:formatNumber type="currency" currencySymbol="" value="${awardAwardAmountInfo.obligatedChangeIndirect}"/>
		        		</div></td>
	          		</c:if>
	        		<td class="datacell"><div>
	        			<fmt:formatNumber type="currency" currencySymbol="" value="${awardAwardAmountInfo.amountObligatedToDate}"/>
	        		</div></td>
	        		<td class="datacell"><div>
	        			<fmt:formatNumber type="currency" currencySymbol="" value="${awardAwardAmountInfo.obliDistributableAmount}"/>
	        		</div></td>
	        		<td class="datacell"><div>
	        			<fmt:formatNumber type="currency" currencySymbol="" value="${awardAwardAmountInfo.anticipatedChange}"/>
	        		</div></td>
					<c:if  test="${KualiForm.directIndirectViewEnabled == '1'}">
		        		<td class="datacell"><div>
		        			<fmt:formatNumber type="currency" currencySymbol="" value="${awardAwardAmountInfo.anticipatedChangeDirect}"/>
		        		</div></td>
		        		<td class="datacell"><div>
		        			<fmt:formatNumber type="currency" currencySymbol="" value="${awardAwardAmountInfo.anticipatedChangeIndirect}"/>
		        		</div></td>
	          		</c:if>
		        		<td class="datacell"><div>
		        			<fmt:formatNumber type="currency" currencySymbol="" value="${awardAwardAmountInfo.anticipatedTotalAmount}"/>
		        		</div></td>
		        		<td class="datacell"><div>
		        			<fmt:formatNumber type="currency" currencySymbol="" value="${awardAwardAmountInfo.antDistributableAmount}"/>
		        		</div></td>
					<td align="left" valign="left" class="infoline" rowspan="1" colspan="4">
		    	      	&nbsp;
					</td>
				</tr>
			</c:if>
			<c:forEach var="timeAndMoneyDocumentHistory" items="${awardVersionHistory.timeAndMoneyDocumentHistoryList}" varStatus="status">
				<tr>
				<td  align="left" valign="left" class="infoline" rowspan="1">
		          	<div align="center">
	                	${timeAndMoneyDocumentHistory.documentUrl}
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
						      			Transaction ID: ${awardAmountInfoHistory.primaryDetail.transactionId}
						        	</c:when>
						        	<c:when test="${awardAmountInfoHistory.transactionType == 'DATE'}">
										Transaction ID: ${awardAmountInfoHistory.dateDetail.transactionId}
						        	</c:when>
						        	<c:when test="${awardAmountInfoHistory.transactionType == 'SINGLENODEMONEYTRANSACTION'}">
										Transaction ID: ${awardAmountInfoHistory.primaryDetail.transactionId}
						        	</c:when>
						        	<c:otherwise>
						        		Initial T&M Transaction
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
	        			<td class="datacell"><div>
		        			<fmt:formatDate value="${awardAmountInfoHistory.awardAmountInfo.currentFundEffectiveDate}" pattern="MM/dd/yyyy" />
		        		</div></td>				        		
								<td class="datacell"><div>
		        			<fmt:formatDate value="${awardAmountInfoHistory.awardAmountInfo.obligationExpirationDate}" pattern="MM/dd/yyyy" />
		        		</div></td>				        		
		        		<td class="datacell"><div>
		        			<fmt:formatDate value="${awardAmountInfoHistory.awardAmountInfo.finalExpirationDate}" pattern="MM/dd/yyyy" />
		        		</div></td>
		        		<td class="datacell"><div>				        		
		        			<fmt:formatNumber type="currency" currencySymbol="" value="${awardAmountInfoHistory.awardAmountInfo.obligatedChange}"/>
		        		</div></td>
		        		<td class="datacell"><div>				        		
		        			<fmt:formatNumber type="currency" currencySymbol="" value="${awardAmountInfoHistory.awardAmountInfo.obligatedChangeDirect}"/>
		        		</div></td>
		        		<td class="datacell"><div>				        		
		        			<fmt:formatNumber type="currency" currencySymbol="" value="${awardAmountInfoHistory.awardAmountInfo.obligatedChangeIndirect}"/>
		        		</div></td>
		        		<td class="datacell"><div>				        		
		        			<fmt:formatNumber type="currency" currencySymbol="" value="${awardAmountInfoHistory.awardAmountInfo.amountObligatedToDate}"/>
		        		</div></td>
		        		<td class="datacell"><div>				        		
		        			<fmt:formatNumber type="currency" currencySymbol="" value="${awardAmountInfoHistory.awardAmountInfo.obliDistributableAmount}"/>
		        		</div></td>
		        		<td class="datacell"><div>				        		
		        			<fmt:formatNumber type="currency" currencySymbol="" value="${awardAmountInfoHistory.awardAmountInfo.anticipatedChange}"/>
		        		</div></td>
		        		<td class="datacell"><div>				        		
		        			<fmt:formatNumber type="currency" currencySymbol="" value="${awardAmountInfoHistory.awardAmountInfo.anticipatedChangeDirect}"/>
		        		</div></td>
		        		<td class="datacell"><div>				        		
		        			<fmt:formatNumber type="currency" currencySymbol="" value="${awardAmountInfoHistory.awardAmountInfo.anticipatedChangeIndirect}"/>
		        		</div></td>
		        		<td class="datacell"><div>				        		
		        			<fmt:formatNumber type="currency" currencySymbol="" value="${awardAmountInfoHistory.awardAmountInfo.anticipatedTotalAmount}"/>
		        		</div></td>
		        		<td class="datacell"><div>				        		
		        			<fmt:formatNumber type="currency" currencySymbol="" value="${awardAmountInfoHistory.awardAmountInfo.antDistributableAmount}"/>
		        		</div></td>		        		
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
	        			<td class="datacell"><div>
		        			<fmt:formatDate value="${awardAmountInfoHistory.awardAmountInfo.currentFundEffectiveDate}" pattern="MM/dd/yyyy" />
		        		</div></td>				        		
								<td class="datacell"><div>
		        			<fmt:formatDate value="${awardAmountInfoHistory.awardAmountInfo.obligationExpirationDate}" pattern="MM/dd/yyyy" />
		        		</div></td>				        		
		        		<td class="datacell"><div>
		        			<fmt:formatDate value="${awardAmountInfoHistory.awardAmountInfo.finalExpirationDate}" pattern="MM/dd/yyyy" />
		        		</div></td>
		        		<td class="datacell"><div>				        		
		        			<fmt:formatNumber type="currency" currencySymbol="" value="${awardAmountInfoHistory.awardAmountInfo.obligatedChange}"/>
		        		</div></td>
		        		<td class="datacell"><div>				        		
		        			<fmt:formatNumber type="currency" currencySymbol="" value="${awardAmountInfoHistory.awardAmountInfo.amountObligatedToDate}"/>
		        		</div></td>
		        		<td class="datacell"><div>				        		
		        			<fmt:formatNumber type="currency" currencySymbol="" value="${awardAmountInfoHistory.awardAmountInfo.obliDistributableAmount}"/>
		        		</div></td>
		        		<td class="datacell"><div>				        		
		        			<fmt:formatNumber type="currency" currencySymbol="" value="${awardAmountInfoHistory.awardAmountInfo.anticipatedChange}"/>
		        		</div></td>
		        		<td class="datacell"><div>				        		
		        			<fmt:formatNumber type="currency" currencySymbol="" value="${awardAmountInfoHistory.awardAmountInfo.anticipatedTotalAmount}"/>
		        		</div></td>
		        		<td class="datacell"><div>				        		
		        			<fmt:formatNumber type="currency" currencySymbol="" value="${awardAmountInfoHistory.awardAmountInfo.antDistributableAmount}"/>
		        		</div></td>		        		
	        		</tr>
	        		
        			</c:otherwise>
	        	</c:choose>
	        			<c:if test="${awardAmountInfoHistory.transactionType == 'MONEY' || awardAmountInfoHistory.transactionType == 'SINGLENODEMONEYTRANSACTION'}">
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
            <c:if test="${fn:length(awardVersionHistory.timeAndMoneyDocumentHistoryList) > 0}">
                <c:set var="awardVersion" value="${awardVersionHistory.awardParent}" />
                <c:set var="awardAwardAmountInfo" value="${awardVersion.awardAmountInfo}" />
                <c:if test="${awardAwardAmountInfo.amountObligatedToDate > 0 || awardAmountInfo.anticipatedTotalAmount > 0}">
                    <tr>
                        <td align="center" valign="middle" rowspan="2">
                            <div align="center" >
                                Award Transaction
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
                        <c:if  test="${KualiForm.directIndirectViewEnabled == '1'}">
                            <th>
                                <div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.obligatedChangeDirect}" readOnly="true" noColon="true" /></div>
                            </th>
                            <th>
                                <div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.obligatedChangeIndirect}" readOnly="true" noColon="true" /></div>
                            </th>
                        </c:if>
                        <th>
                            <div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.amountObligatedToDate}" readOnly="true" noColon="true" /></div>
                        </th>
                        <th>
                            <div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.obliDistributableAmount}" readOnly="true" noColon="true" /></div>
                        </th>
                        <th>
                            <div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.anticipatedChange}" readOnly="true" noColon="true" /></div>
                        </th>
                        <c:if  test="${KualiForm.directIndirectViewEnabled == '1'}">
                            <th>
                                <div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.anticipatedChangeDirect}" readOnly="true" noColon="true" /></div>
                            </th>
                            <th>
                                <div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.anticipatedChangeIndirect}" readOnly="true" noColon="true" /></div>
                            </th>
                        </c:if>
                        <th>
                            <div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.anticipatedTotalAmount}" readOnly="true" noColon="true" /></div>
                        </th>
                        <th>
                            <div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.antDistributableAmount}" readOnly="true" noColon="true" /></div>
                        </th>
                    </tr>
                    <tr>
                        <td class="datacell"><div>
                            <fmt:formatDate value="${awardAwardAmountInfo.currentFundEffectiveDate}" pattern="MM/dd/yyyy" />
                        </div></td>
                        <td class="datacell"><div>
                            <fmt:formatDate value="${awardAwardAmountInfo.obligationExpirationDate}" pattern="MM/dd/yyyy" />
                        </div></td>
                        <td class="datacell"><div>
                            <fmt:formatDate value="${awardAwardAmountInfo.finalExpirationDate}" pattern="MM/dd/yyyy" />
                        </div></td>
                        <td class="datacell"><div>
                            <fmt:formatNumber type="currency" currencySymbol="" value="${awardAwardAmountInfo.obligatedChange}"/>
                        </div></td>
                        <c:if  test="${KualiForm.directIndirectViewEnabled == '1'}">
                            <td class="datacell"><div>
                                <fmt:formatNumber type="currency" currencySymbol="" value="${awardAwardAmountInfo.obligatedChangeDirect}"/>
                            </div></td>
                            <td class="datacell"><div>
                                <fmt:formatNumber type="currency" currencySymbol="" value="${awardAwardAmountInfo.obligatedChangeIndirect}"/>
                            </div></td>
                        </c:if>
                        <td class="datacell"><div>
                            <fmt:formatNumber type="currency" currencySymbol="" value="${awardAwardAmountInfo.amountObligatedToDate}"/>
                        </div></td>
                        <td class="datacell"><div>
                            <fmt:formatNumber type="currency" currencySymbol="" value="${awardAwardAmountInfo.obliDistributableAmount}"/>
                        </div></td>
                        <td class="datacell"><div>
                            <fmt:formatNumber type="currency" currencySymbol="" value="${awardAwardAmountInfo.anticipatedChange}"/>
                        </div></td>
                        <c:if  test="${KualiForm.directIndirectViewEnabled == '1'}">
                            <td class="datacell"><div>
                                <fmt:formatNumber type="currency" currencySymbol="" value="${awardAwardAmountInfo.anticipatedChangeDirect}"/>
                            </div></td>
                            <td class="datacell"><div>
                                <fmt:formatNumber type="currency" currencySymbol="" value="${awardAwardAmountInfo.anticipatedChangeIndirect}"/>
                            </div></td>
                        </c:if>
                        <td class="datacell"><div>
                            <fmt:formatNumber type="currency" currencySymbol="" value="${awardAwardAmountInfo.anticipatedTotalAmount}"/>
                        </div></td>
                        <td class="datacell"><div>
                            <fmt:formatNumber type="currency" currencySymbol="" value="${awardAwardAmountInfo.antDistributableAmount}"/>
                        </div></td>
                        <td align="left" valign="left" class="infoline" rowspan="1" colspan="4">
                            &nbsp;
                        </td>
                    </tr>
                </c:if>
            </c:if>
        </c:forEach> 
        </table>       			
	</div>   
</kul:tab>
