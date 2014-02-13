<%--
 Copyright 2005-2013 The Kuali Foundation

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
<%-- member of awardPaymentAndInvoices.tag --%>

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<script type='text/javascript' src='dwr/interface/KraPersonService.js'></script>

<div align="center">
<html:image property="methodToCall.generatePaymentSchedules.anchor${tabKey}"
	src='${ConfigProperties.kra.externalizable.images.url}tinybutton-generate-schedule.gif' styleClass="tinybutton"/>
</div>

<c:set var="awardPaymentScheduleAttributes" value="${DataDictionary.AwardPaymentSchedule.attributes}" />
<c:set var="action" value="awardPaymentSchedule" />
<%--<c:set var="readOnly" value="${false }"/> --%>
<kul:innerTab parentTab="Payment & Invoices" defaultOpen="true" tabTitle="Award Payment Schedule" tabErrorKey="paymentScheduleBean.newAwardPaymentSchedule.*,document.awardList[0].paymentScheduleItems*" noShowHideButton="true" >
    	<table cellpadding="0" cellspacing="0" summary="">
          	<%-- Header --%>
          	<tr>
          		<kul:htmlAttributeHeaderCell literalLabel="&nbsp;" scope="col" />
          		<kul:htmlAttributeHeaderCell attributeEntry="${awardPaymentScheduleAttributes.invoiceNumber}" scope="col" /></div></th>
          		<kul:htmlAttributeHeaderCell attributeEntry="${awardPaymentScheduleAttributes.dueDate}" scope="col" /></div></th>
          		<kul:htmlAttributeHeaderCell attributeEntry="${awardPaymentScheduleAttributes.overdue}" scope="col" /></div></th>
          		<kul:htmlAttributeHeaderCell attributeEntry="${awardPaymentScheduleAttributes.submittedBy}" scope="col" /></div></th>
          		<kul:htmlAttributeHeaderCell attributeEntry="${awardPaymentScheduleAttributes.status}" scope="col" /></div></th>
          		<kul:htmlAttributeHeaderCell attributeEntry="${awardPaymentScheduleAttributes.submitDate}" scope="col" /></div></th>          		
          		<kul:htmlAttributeHeaderCell attributeEntry="${awardPaymentScheduleAttributes.statusDescription}" scope="col" /></div></th>
          		<kul:htmlAttributeHeaderCell attributeEntry="${awardPaymentScheduleAttributes.amount}" scope="col" /></div></th>
          		<th>Last Update</th>
          		<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" />
          	</tr> 
          	<%-- Header --%>
          	
             <%-- New data --%>
             <c:if test="${!readOnly}">
             <tbody class="addline">
             <tr>
				<th class="infoline">
					<c:out value="Add:" />
				</th>
				<td align="left" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="paymentScheduleBean.newAwardPaymentSchedule.invoiceNumber" 
                		attributeEntry="${awardPaymentScheduleAttributes.invoiceNumber}" />
                	</div>
				</td>				                
                <td align="left" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="paymentScheduleBean.newAwardPaymentSchedule.dueDate" 
                		attributeEntry="${awardPaymentScheduleAttributes.dueDate}" />
                	</div>
				</td>
				<td align="left" valign="middle" class="infoline">
                	<div align="center">
	                	<kul:htmlControlAttribute property="paymentScheduleBean.newAwardPaymentSchedule.overdue" 
	                		attributeEntry="${awardPaymentScheduleAttributes.overdue}" />
                	</div>
				</td>
				<td align="left" valign="middle" class="infoline">
                	<div align="center">
                		<c:choose>
							<c:when test="${KualiForm.displayAwardPaymentScheduleActiveLinkFields }">
								<kul:htmlControlAttribute
									property="paymentScheduleBean.newAwardPaymentSchedule.submittedByPersonUsername"
									attributeEntry="${awardPaymentScheduleAttributes.submittedByPersonId}"
									onblur="loadContactPersonName('paymentScheduleBean.newAwardPaymentSchedule.submittedByPersonUsername',
					                               				 			'',
					                	        				     		'',
					                	        				  			'',
				           	        							  			'',
				           	        							  			'submitterId[NEW]');"
									readOnly="${readOnly}" /> 
									
								<c:if test="${!readOnly}">
									<kul:lookup boClassName="org.kuali.kra.bo.KcPerson"
										fieldConversions="personId:paymentScheduleBean.newAwardPaymentSchedule.submittedByPersonId,fullName:submitterFullNameNEW" />
								</c:if>	
								
								${kfunc:registerEditableProperty(KualiForm, "paymentScheduleBean.newAwardPaymentSchedule.submittedByPersonId")}
								<html:hidden styleId ="submitterId[NEW]" 
									property="paymentScheduleBean.newAwardPaymentSchedule.submittedByPersonId" />
								
								<Br/>
								<div id="submitterFullNameNEW">
									<c:out value="${awardPaymentSchedule.submittedByPersonFullname}"/>
								</div>
							</c:when>
							<c:otherwise>
								<kul:htmlControlAttribute property="paymentScheduleBean.newAwardPaymentSchedule.submittedBy" 
                					attributeEntry="${awardPaymentScheduleAttributes.submittedBy}" />
							</c:otherwise>
						</c:choose> 
                	</div>
				</td>
				<td align="left" valign="middle" class="infoline">
                	<div align="center">
                		<c:choose>
					      <c:when test="${KualiForm.displayAwardPaymentScheduleActiveLinkFields }">
					        <kul:htmlControlAttribute property="paymentScheduleBean.newAwardPaymentSchedule.reportStatusCode" 
                				attributeEntry="${awardPaymentScheduleAttributes.reportStatusCode}" />
					      </c:when>
					      <c:otherwise>
					        <kul:htmlControlAttribute property="paymentScheduleBean.newAwardPaymentSchedule.status" 
                				attributeEntry="${awardPaymentScheduleAttributes.status}" />
					      </c:otherwise>
					    </c:choose>                		
                	</div>
				</td>
				<td align="left" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="paymentScheduleBean.newAwardPaymentSchedule.submitDate" attributeEntry="${awardPaymentScheduleAttributes.submitDate}" />
                	</div>
				</td>                
                <td align="left" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="paymentScheduleBean.newAwardPaymentSchedule.statusDescription" attributeEntry="${awardPaymentScheduleAttributes.statusDescription}" />
                	</div>
				</td>				
                <td align="left" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="paymentScheduleBean.newAwardPaymentSchedule.amount" attributeEntry="${awardPaymentScheduleAttributes.amount}" styleClass="amount"/>
                	</div>
				</td>
				<td>
					<div align="center">
					</div>
				</td>
				<td class="infoline">
					<div align="center">
						<html:image property="methodToCall.addPaymentScheduleItem.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton addButton"/>
					</div>
                </td>
            </tr>
            </tbody>
            </c:if>
            <%-- New data --%>
            
            <%-- Existing data --%>
        	<c:forEach var="awardPaymentSchedule" items="${KualiForm.document.award.paymentScheduleItems}" varStatus="status">
	             <tr>
					<th class="infoline">
						<c:out value="${status.index+1}" />
					</th>                  
                  <td align="left" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.awardList[0].paymentScheduleItems[${status.index}].invoiceNumber" attributeEntry="${awardPaymentScheduleAttributes.invoiceNumber}" />
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.awardList[0].paymentScheduleItems[${status.index}].dueDate" attributeEntry="${awardPaymentScheduleAttributes.dueDate}" />
					</div>
				  </td>
				  <td align="left" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.awardList[0].paymentScheduleItems[${status.index}].overdue" 
                			attributeEntry="${awardPaymentScheduleAttributes.overdue}" />
					</div>
				  </td>
				  <td align="left" valign="middle">
					<div align="center">
						
						<c:choose>
							<c:when test="${KualiForm.displayAwardPaymentScheduleActiveLinkFields }">
								<kul:htmlControlAttribute
								property="document.awardList[0].paymentScheduleItems[${status.index}].submittedByPersonUsername"
									attributeEntry="${awardPaymentScheduleAttributes.submittedByPersonId}"
									onblur="loadContactPersonName('document.awardList[0].paymentScheduleItems[${status.index}].submittedByPersonUsername',
					                               				 			'',
					                	        				     		'',
					                	        				  			'',
				           	        							  			'',
				           	        							  			'submitterId[${status.index}]');"
									readOnly="${readOnly}" /> 
								
								<c:if test="${!readOnly}">
									<kul:lookup boClassName="org.kuali.kra.bo.KcPerson"
										fieldConversions="personId:document.awardList[0].paymentScheduleItems[${status.index}].submittedByPersonId" />
								</c:if>	
								
								${kfunc:registerEditableProperty(KualiForm, "document.awardList[0].paymentScheduleItems[${status.index}].submittedByPersonId")}
								<html:hidden styleId ="submitterId[${status.index}]" property="document.awardList[0].paymentScheduleItems[${status.index}].submittedByPersonId" />
								
								<Br/>
								<div id="submitterFullName${status.index}">
									<c:out value="${awardPaymentSchedule.submittedByPersonFullname}"/>
								</div>
							</c:when>
							<c:otherwise>
								<kul:htmlControlAttribute property="document.awardList[0].paymentScheduleItems[${status.index}].submittedBy" 
                					attributeEntry="${awardPaymentScheduleAttributes.submittedBy}" />
							</c:otherwise>
						</c:choose> 
					</div>
				  </td>
				  <td align="left" valign="middle">
					<div align="center">
						<c:choose>
							<c:when test="${KualiForm.displayAwardPaymentScheduleActiveLinkFields }">
								<kul:htmlControlAttribute property="document.awardList[0].paymentScheduleItems[${status.index}].reportStatusCode" 
                				attributeEntry="${awardPaymentScheduleAttributes.reportStatusCode}" />
							</c:when>
							<c:otherwise>
								<kul:htmlControlAttribute property="document.awardList[0].paymentScheduleItems[${status.index}].status" 
                					attributeEntry="${awardPaymentScheduleAttributes.status}" />
							</c:otherwise>
						</c:choose> 
					</div>
				  </td>
				  <td align="left" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.awardList[0].paymentScheduleItems[${status.index}].submitDate" attributeEntry="${awardPaymentScheduleAttributes.submitDate}" />
					</div>
				  </td>
				  <td align="left" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.awardList[0].paymentScheduleItems[${status.index}].statusDescription" attributeEntry="${awardPaymentScheduleAttributes.statusDescription}" />
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.awardList[0].paymentScheduleItems[${status.index}].amount" attributeEntry="${awardPaymentScheduleAttributes.amount}" styleClass="amount"/>
					</div>
				  </td>
				  <td align="left" valign="middle">
				  	<div align="center">
				  		<c:out value="${awardPaymentSchedule.lastUpdateUser }" /> <Br/> 
				  		<c:out value="${awardPaymentSchedule.lastUpdateTimestamp }" /> 
				  		  
				  	</div>
				  </td>
				  <td class="infoline">
					<div align="center">
					   <c:if test="${!readOnly}">
						  <html:image property="methodToCall.deletePaymentScheduleItem.line${status.index}.anchor${currentTabIndex}"
						  src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
					   </c:if>
					   <c:if test="${readOnly}">&nbsp;</c:if>
					</div>
                  </td>

	            </tr>
        	</c:forEach> 
            <%-- Existing data --%>
            
            <tr>
                <th colspan="8" align="right" scope="row"><div>Total:</div></th>
                <th>
                    <div align="right">
                        $<fmt:formatNumber value="${KualiForm.document.awardList[0].totalPaymentScheduleAmount}" type="currency" currencySymbol="" maxFractionDigits="2" />
                    </div>
                </th>
                <th colspan="2" align="center">
                    <c:if test="${!readOnly}">
                        <html:image property="methodToCall.recalculateCostShareTotal.anchor${tabKey}"
                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-recalculate.gif' styleClass="tinybutton"/>
                   </c:if>
                </th>
            </tr>
        </table>

</kul:innerTab>
