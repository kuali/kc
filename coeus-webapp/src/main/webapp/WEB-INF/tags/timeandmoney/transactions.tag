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

<c:set var="pendingTransactionAttributes" value="${DataDictionary.PendingTransaction.attributes}" />

<kul:tab tabTitle="Transactions (${KualiForm.document.awardNumber})" defaultOpen="false" tabErrorKey="document.newAwardAmountTransaction*,transactionBean.newPendingTransaction.obligatedAmount,transactionBean.newPendingTransaction.anticipatedAmount,transactionBean.newPendingTransaction.timeAndMoneyTransaction,transactionBean.newPendingTransaction.sourceAwardNumber,transactionBean.newPendingTransaction.destinationAwardNumber,document.pendingTransactions*" auditCluster="requiredFieldsAuditErrors" tabAuditKey="" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">  Transactions</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.timeandmoney.transactions.PendingTransaction" altText="help"/></span>
        </h3>
        
        <table cellpadding="0" cellspacing="0" summary="">
        <c:set var="isRouted" value="${KualiForm.document.documentRouteStatus}" />
          	<%-- Header --%>
          	<tr>
          		<kul:htmlAttributeHeaderCell literalLabel="&nbsp;" scope="col" />
          		<kul:htmlAttributeHeaderCell attributeEntry="${pendingTransactionAttributes.transactionId}" scope="col" />
          		<kul:htmlAttributeHeaderCell attributeEntry="${pendingTransactionAttributes.comments}" scope="col" />
          		<kul:htmlAttributeHeaderCell attributeEntry="${pendingTransactionAttributes.sourceAwardNumber}" scope="col" />          		
          		<kul:htmlAttributeHeaderCell attributeEntry="${pendingTransactionAttributes.destinationAwardNumber}" scope="col" />
          		<kul:htmlAttributeHeaderCell attributeEntry="${pendingTransactionAttributes.obligatedAmount}" scope="col" />
          		<kul:htmlAttributeHeaderCell attributeEntry="${pendingTransactionAttributes.anticipatedAmount}" scope="col" />        		
	            <c:if test="${!readOnly}">
    	      	    <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" />
    	      	</c:if>
          	</tr> 
          	
          	<%-- Header --%>
          	
             <%-- New data --%>
             <c:if test="${!readOnly}">
             	<tr>
					<th class="infoline">
						<c:out value="Add:" />
					</th>
				                
	                <td align="left" valign="middle" class="infoline">
                		<div align="center">
            		    	<kul:htmlControlAttribute property="transactionBean.newPendingTransaction.transactionId" attributeEntry="${pendingTransactionAttributes.transactionId}" readOnly="true"/>
        	        	</div>
					</td>
					<td align="left" valign="middle" class="infoline">
                		<div align="center">
            		    	<kul:htmlControlAttribute property="transactionBean.newPendingTransaction.comments" attributeEntry="${pendingTransactionAttributes.comments}"/>
        	        	</div>
					</td>
	                <td align="left" valign="middle" class="infoline">
                		<div align="center">                	
            		    	<kul:htmlControlAttribute property="transactionBean.newPendingTransaction.sourceAwardNumber" attributeEntry="${pendingTransactionAttributes.sourceAwardNumber}" />     	
        	        	</div>
					</td>                
	                <td align="left" valign="middle" class="infoline">
                		<div align="center">
        		        	<kul:htmlControlAttribute property="transactionBean.newPendingTransaction.destinationAwardNumber" attributeEntry="${pendingTransactionAttributes.destinationAwardNumber}" />
    	            	</div>
					</td>
                	<td align="left" valign="middle" class="infoline">
                		<div align="center">
            		    	<kul:htmlControlAttribute property="transactionBean.newPendingTransaction.obligatedAmount" attributeEntry="${pendingTransactionAttributes.obligatedAmount}" styleClass="amount" />
        	        	</div>
					</td>
					<td align="left" valign="middle" class="infoline">
                		<div align="center">
        		        	<kul:htmlControlAttribute property="transactionBean.newPendingTransaction.anticipatedAmount" attributeEntry="${pendingTransactionAttributes.anticipatedAmount}" styleClass="amount" />
    	            	</div>
					</td>                
					<td class="infoline">
						<div align="center">
							<html:image property="methodToCall.addTransaction.anchor${tabKey}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
						</div>
                	</td>
            	</tr>
            </c:if>
            <%-- New data --%>
            
            <%-- Existing data --%>
        	<c:forEach var="pendingTransaction" items="${KualiForm.document.pendingTransactions}" varStatus="status">
	        	<tr>
					<th class="infoline">
						<c:out value="${status.index+1}" />
					</th>
                    <td align="left" valign="middle">
					    <div align="center">
                		   <kul:htmlControlAttribute property="document.pendingTransactions[${status.index}].transactionId" attributeEntry="${pendingTransactionAttributes.transactionId}"  readOnly="true" />
					    </div>
				    </td>
				    <td align="left" valign="middle">
					    <div align="center">
                		    <kul:htmlControlAttribute property="document.pendingTransactions[${status.index}].comments" attributeEntry="${pendingTransactionAttributes.comments}"  readOnly="${isRouted}" />
					    </div>
				    </td>
                    <td align="left" valign="middle">
					    <div align="center">                		
                	        <c:choose>
                		        <c:when test="${KualiForm.document.pendingTransactions[status.index].sourceAwardNumber == '000000-00000'}" >
                			        <c:out value = "External" />
                		        </c:when>
                		        <c:otherwise>
                			        <kul:htmlControlAttribute property="document.pendingTransactions[${status.index}].sourceAwardNumber" attributeEntry="${pendingTransactionAttributes.sourceAwardNumber}"  readOnly="true" />
                		        </c:otherwise>
                	        </c:choose>	                		
					    </div>
				    </td>
                    <td align="left" valign="middle">
					    <div align="center">
                		    <kul:htmlControlAttribute property="document.pendingTransactions[${status.index}].destinationAwardNumber" attributeEntry="${pendingTransactionAttributes.destinationAwardNumber}"  readOnly="true" />
					    </div>
				    </td>
                    <td align="left" valign="middle">
					    <div align="center">
                		    <kul:htmlControlAttribute property="document.pendingTransactions[${status.index}].obligatedAmount" attributeEntry="${pendingTransactionAttributes.obligatedAmount}"  readOnly="true" />
					    </div>
				    </td>
                    <td align="left" valign="middle">
					    <div align="center">
                		    <kul:htmlControlAttribute property="document.pendingTransactions[${status.index}].anticipatedAmount" attributeEntry="${pendingTransactionAttributes.anticipatedAmount}" readOnly="true" />
					    </div>
				    </td>
                  
	            	<c:if test="${!readOnly}">
				  		<td class="infoline">
				  			<c:if test="${status.index == fn:length(KualiForm.document.pendingTransactions)-1}" >
								<div align="center">
							        <c:if test="${!readOnly}">
										<html:image property="methodToCall.deleteTransaction.line${status.index}.anchor${currentTabIndex}"
											src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
							  		</c:if>
							  		<c:if test="${readOnly}">
						      			&nbsp;
						  			</c:if>
								</div>
							</c:if>
                  		</td>
	            	</c:if>
	            </tr>
        	</c:forEach>
        	<%-- Existing data --%>
    	</table>
	</div>
    
</kul:tab>

