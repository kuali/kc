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

<c:set var="pendingTransactionAttributes" value="${DataDictionary.PendingTransaction.attributes}" />

<kul:tab tabTitle="Transactions (${KualiForm.document.awardNumber})" defaultOpen="false" tabErrorKey="document.newAwardAmountTransaction*" auditCluster="requiredFieldsAuditErrors" tabAuditKey="" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left"> Transaction</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.timeandmoney.transactions.PendingTransaction" altText="help"/></span>
        </h3>
        
        <table cellpadding="0" cellspacing="0" summary="">
          	<%-- Header --%>
          	<tr>
          		<kul:htmlAttributeHeaderCell literalLabel="&nbsp;" scope="col" />
          		<kul:htmlAttributeHeaderCell attributeEntry="${pendingTransactionAttributes.transactionId}" scope="col" /></div></th>
          		<kul:htmlAttributeHeaderCell attributeEntry="${pendingTransactionAttributes.comments}" scope="col" /></div></th>
          		<kul:htmlAttributeHeaderCell attributeEntry="${pendingTransactionAttributes.sourceAwardNumber}" scope="col" /></div></th>          		
          		<kul:htmlAttributeHeaderCell attributeEntry="${pendingTransactionAttributes.destinationAwardNumber}" scope="col" /></div></th>
          		<kul:htmlAttributeHeaderCell attributeEntry="${pendingTransactionAttributes.obligatedAmount}" scope="col" /></div></th>
          		<kul:htmlAttributeHeaderCell attributeEntry="${pendingTransactionAttributes.anticipatedAmount}" scope="col" /></div></th>          		
          		<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" />
          	</tr> 
          	<%-- Header --%>
          	
             <%-- New data --%>
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
                	<kul:htmlControlAttribute property="transactionBean.newPendingTransaction.comments" attributeEntry="${pendingTransactionAttributes.comments}" />
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
                	<kul:htmlControlAttribute property="transactionBean.newPendingTransaction.obligatedAmount" attributeEntry="${pendingTransactionAttributes.obligatedAmount}" datePicker="true" />
                	</div>
				</td>
				<td align="left" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="transactionBean.newPendingTransaction.anticipatedAmount" attributeEntry="${pendingTransactionAttributes.anticipatedAmount}" datePicker="true" />
                	</div>
				</td>                
				<td class="infoline">
					<div align="center">
						<html:image property="methodToCall.addTransaction.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
					</div>
                </td>
            </tr>
            <%-- New data --%>
            
            <%-- Existing data --%>
        	<c:forEach var="pendingTransaction" items="${KualiForm.document.pendingTransactions}" varStatus="status">
	             <tr>
					<th class="infoline">
						<c:out value="${status.index+1}" />
					</th>
                  <td align="left" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.pendingTransactions[${status.index}].transactionId" attributeEntry="${pendingTransactionAttributes.awardAmountTransactionId}"  readOnly="true" />
					</div>
				  </td>
				  <td align="left" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.pendingTransactions[${status.index}].comments" attributeEntry="${pendingTransactionAttributes.comments}"  readOnly="true" />
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
                  
				  <td class="infoline">
				  	<c:if test="${status.index == fn:length(KualiForm.document.pendingTransactions)-1}" >
						<div align="center">
							<html:image property="methodToCall.deleteTransaction.line${status.index}.anchor${currentTabIndex}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
						</div>
					</c:if>
                  </td>
	            </tr>
        	</c:forEach>
        	<%-- Existing data --%>
        </table>
    </div>
</kul:tab>
