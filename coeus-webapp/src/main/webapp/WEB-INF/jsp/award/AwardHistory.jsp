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

<kul:documentPage
    showDocumentInfo="true"
    htmlFormAction="awardActions"
    documentTypeName="AwardDocument"
    renderMultipart="false"
    showTabButtons="false"
    auditCount="0"
    headerTabActive="actions">

<kul:tabTop tabTitle="Award History" defaultOpen="true">

<c:set var="awardAmountInfoAttributes" value="${DataDictionary.AwardAmountInfo.attributes}" />
<c:set var="transactionDetailAttributes" value="${DataDictionary.TransactionDetail.attributes}" />
<c:set var="awardCurrentActionCommentAttributes" value="${DataDictionary.AwardComment.attributes}" />
<style>
	td.datacell div{
		text-align: right;
	}
</style>

	<div class="tab-container" align="center">
   		<h3>
   			<span class="subhead-left"> History</span>
        </h3>
		<c:forEach var="awardVersion" items="${KualiForm.awardsForHistoryDisplay}" varStatus="status"> 
		    <c:set var="awardVersionInfo" value="${awardVersion.lastAwardAmountInfo}" />
    	   	<table cellpadding="0" cellspacing="0" summary="">
        		<tr>
					<c:choose>
		        		<c:when test="${KualiForm.directIndirectViewEnabled == '1'}">
        	  				<td style="text-align: left; background-color: rgb(195, 195, 195); font-weight: bold; colspan: 11" colspan="15" />
          				</c:when>
						<c:otherwise>
							<td style="text-align: left; background-color: rgb(195, 195, 195); font-weight: bold; colspan: 7" colspan="11" />
						</c:otherwise>
					 </c:choose>
        	        	<div align="left">
            	    		<c:out value="${awardVersion.awardDescriptionLine}" />
                		</div>
					</td>
				</tr>
				<tr>
         			<kul:htmlAttributeHeaderCell literalLabel="Link" scope="col" />
   					<kul:htmlAttributeHeaderCell literalLabel="Project Start Date" scope="col" />
	   				<kul:htmlAttributeHeaderCell literalLabel="Project End Date" scope="col" />
    	     		<kul:htmlAttributeHeaderCell literalLabel="Obligated Start Date" scope="col" />
   					<kul:htmlAttributeHeaderCell literalLabel="Obligated End Date" scope="col" />
   					<kul:htmlAttributeHeaderCell literalLabel="Anticipated Total" scope="col" />
   					<kul:htmlAttributeHeaderCell literalLabel="Obligated Total" scope="col" />
					<c:if  test="${KualiForm.directIndirectViewEnabled == '1'}">
    	      			<kul:htmlAttributeHeaderCell literalLabel="Anticipated Direct" scope="col" />
   	    	  			<kul:htmlAttributeHeaderCell literalLabel="Anticipated Indirect" scope="col" />
       	  				<kul:htmlAttributeHeaderCell literalLabel="Obligated Direct" scope="col" />
       					<kul:htmlAttributeHeaderCell literalLabel="Obligated Indirect" scope="col" />
	          		</c:if>
       		   		<kul:htmlAttributeHeaderCell literalLabel="" scope="col" colspan="4" />
    	  		</tr>	
    	    	<tr>
					<td align="left" valign="left" class="infoline" rowspan="1">
		    	      	<div align="center">
		    	      		${awardVersion.awardDocumentUrl}
		            	</div>                	
					</td>
					<td align="left" valign="left" class="infoline" rowspan="1">
		    	      	<div align="center">
	        				<fmt:formatDate value="${awardVersion.awardEffectiveDate}" pattern="MM/dd/yyyy" />
		            	</div>                	
					</td>
					<td align="left" valign="left" class="infoline" rowspan="1">
		    	      	<div align="center">
   		     				<fmt:formatDate value="${awardVersionInfo.finalExpirationDate}" pattern="MM/dd/yyyy" />
		            	</div>                	
					</td>
					<td align="left" valign="left" class="infoline" rowspan="1">
		    	      	<div align="center">
        					<fmt:formatDate value="${awardVersionInfo.currentFundEffectiveDate}" pattern="MM/dd/yyyy" />
		            	</div>                	
					</td>
					<td align="left" valign="left" class="infoline" rowspan="1">
		    	      	<div align="center">
			            	<fmt:formatDate value="${awardVersionInfo.obligationExpirationDate}" pattern="MM/dd/yyyy" />
		            	</div>                	
					</td>
					<td align="left" valign="left" class="infoline" rowspan="1">
		    	      	<div align="center">
						    <fmt:formatNumber currencySymbol="$" type="currency" value="${awardVersion.anticipatedTotal}"/>
		            	</div>                	
					</td>
					<td align="left" valign="left" class="infoline" rowspan="1">
		    	      	<div align="center">
							<fmt:formatNumber currencySymbol="$" type="currency" value="${awardVersion.obligatedTotal}"/>
		            	</div>                	
					</td>
    	    	
    	    		<c:if test="${KualiForm.directIndirectViewEnabled == '1'}">
						<td align="left" valign="left" class="infoline" rowspan="1">
			    	      	<div align="center">
								<fmt:formatNumber currencySymbol="$" type="currency" value="${awardVersionInfo.anticipatedTotalDirect}"/>
	    	    	    	</div>                	
						</td>
						<td align="left" valign="left" class="infoline" rowspan="1">
		    	    	  	<div align="center">
								<fmt:formatNumber currencySymbol="$" type="currency" value="${awardVersionInfo.anticipatedTotalIndirect}"/>
		        	    	</div>                	
						</td>
						<td align="left" valign="left" class="infoline" rowspan="1">
			    	      	<div align="center">
					        	<fmt:formatNumber currencySymbol="$" type="currency" value="${awardVersionInfo.obligatedTotalDirect}"/>
		        	    	</div>                	
						</td>
						<td align="left" valign="left" class="infoline" rowspan="1">
			    	      	<div align="center">
					        	<fmt:formatNumber currencySymbol="$" type="currency" value="${awardVersionInfo.obligatedTotalIndirect}"/>
			            	</div>                	
						</td>
       				</c:if>
					<td align="left" valign="left" class="infoline" rowspan="1" colspan="4">
		    	      	&nbsp;                	
					</td>    
				</tr>
				<c:forEach var="timeAndMoneyDocumentHistory" items="${awardVersion.timeAndMoneyDocumentHistoryList}" varStatus="status"> 
					<tr>
					<td  align="left" valign="left" class="infoline" rowspan="1">
		    	      	<div align="center">
	            	    	${timeAndMoneyDocumentHistory.documentUrl}
		            	</div>                	
					</td>
					<c:choose>
	    	    		<c:when test="${KualiForm.directIndirectViewEnabled == '1'}">
          					<td align="left" valign="left" class="infoline" colspan="14" >
          				</c:when>
						<c:otherwise>
							<td align="left" valign="left" class="infoline" colspan="10" >
						</c:otherwise>
					 </c:choose>
        	        	<div align="left">
            	    		<c:out value="${timeAndMoneyDocumentHistory.timeAndMoneyDocumentDescriptionLine}" />
                		</div>
					</td>
					</tr>
					
					<c:forEach var="awardAmountInfoHistory" items="${timeAndMoneyDocumentHistory.validAwardAmountInfoHistoryList}" varStatus="status"> 
						<tr>
							<td rowspan="2">&nbsp;</td>
							<td align="center" valign="middle" rowspan="2" >
								<div align="center" >
							        <c:choose>
						    	    	<c:when test="${awardAmountInfoHistory.transactionType == 'MONEY'}">
						      				<c:out value="Transaction ID: " />
						        			<c:out value="${awardAmountInfoHistory.primaryDetail.transactionId}" />
							        	</c:when>
							        	<c:when test="${awardAmountInfoHistory.transactionType == 'DATE'}">
											<c:out value="Transaction ID: " />
											<c:out value="${awardAmountInfoHistory.dateDetail.transactionId}" />
							        	</c:when>
							        	<c:when test="${awardAmountInfoHistory.transactionType == 'SINGLENODEMONEYTRANSACTION'}">
											<c:out value="Transaction ID: " />
											<c:out value="${awardAmountInfoHistory.primaryDetail.transactionId}" />
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
					</c:forEach>
				</c:forEach>
        	</c:forEach> 
        </table>       			
	</div>   
</kul:tabTop>
<kul:panelFooter />
<div id="globalbuttons" class="globalbuttons">
   	<a href="javascript:self.close()">
       	<img src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_close.gif" border="0" alt="close" />
    </a>
</div>

</kul:documentPage>
