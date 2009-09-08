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
<%-- member of awardPaymentAndInvoices.tag --%>

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<div align="center">
<html:image property="methodToCall.generatePaymentSchedules.anchor${tabKey}"
	src='${ConfigProperties.kra.externalizable.images.url}generate-schedules.gif' styleClass="tinybutton"/>
</div>

<c:set var="awardPaymentScheduleAttributes" value="${DataDictionary.AwardPaymentSchedule.attributes}" />
<c:set var="action" value="awardPaymentSchedule" />
<kul:innerTab parentTab="Payment & Invoices" defaultOpen="true" tabTitle="Award Payment Schedule" tabErrorKey="paymentScheduleBean.newAwardPaymentSchedule.*,document.awardList[0].paymentScheduleItems*" noShowHideButton="true" >
    	<table cellpadding="0" cellspacing="0" summary="">
          	<%-- Header --%>
          	<tr>
          		<kul:htmlAttributeHeaderCell literalLabel="&nbsp;" scope="col" />
          		<kul:htmlAttributeHeaderCell attributeEntry="${awardPaymentScheduleAttributes.invoiceNumber}" scope="col" /></div></th>
          		<kul:htmlAttributeHeaderCell attributeEntry="${awardPaymentScheduleAttributes.dueDate}" scope="col" /></div></th>
          		<kul:htmlAttributeHeaderCell attributeEntry="${awardPaymentScheduleAttributes.submittedBy}" scope="col" /></div></th>
          		<kul:htmlAttributeHeaderCell attributeEntry="${awardPaymentScheduleAttributes.status}" scope="col" /></div></th>
          		<kul:htmlAttributeHeaderCell attributeEntry="${awardPaymentScheduleAttributes.submitDate}" scope="col" /></div></th>          		
          		<kul:htmlAttributeHeaderCell attributeEntry="${awardPaymentScheduleAttributes.statusDescription}" scope="col" /></div></th>
          		<kul:htmlAttributeHeaderCell attributeEntry="${awardPaymentScheduleAttributes.amount}" scope="col" /></div></th>
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
                	<kul:htmlControlAttribute property="paymentScheduleBean.newAwardPaymentSchedule.invoiceNumber" attributeEntry="${awardPaymentScheduleAttributes.invoiceNumber}" />
                	</div>
				</td>				                
                <td align="left" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="paymentScheduleBean.newAwardPaymentSchedule.dueDate" attributeEntry="${awardPaymentScheduleAttributes.dueDate}" datePicker="true" />
                	</div>
				</td>
				<td align="left" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="paymentScheduleBean.newAwardPaymentSchedule.submittedBy" attributeEntry="${awardPaymentScheduleAttributes.submittedBy}" />
                	</div>
				</td>
				<td align="left" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="paymentScheduleBean.newAwardPaymentSchedule.status" attributeEntry="${awardPaymentScheduleAttributes.status}" />
                	</div>
				</td>
				<td align="left" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="paymentScheduleBean.newAwardPaymentSchedule.submitDate" attributeEntry="${awardPaymentScheduleAttributes.submitDate}" datePicker="true" />
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
				<td class="infoline">
					<div align="center">
						<html:image property="methodToCall.addPaymentScheduleItem.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
					</div>
                </td>
            </tr>
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
                		<kul:htmlControlAttribute property="document.awardList[0].paymentScheduleItems[${status.index}].submittedBy" attributeEntry="${awardPaymentScheduleAttributes.submittedBy}" />
					</div>
				  </td>
				  <td align="left" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.awardList[0].paymentScheduleItems[${status.index}].status" attributeEntry="${awardPaymentScheduleAttributes.status}" />
					</div>
				  </td>
				  <td align="left" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.awardList[0].paymentScheduleItems[${status.index}].submitDate" attributeEntry="${awardPaymentScheduleAttributes.submitDate}" datePicker="true" />
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
				  
				  <td class="infoline">
					<div align="center">
						<html:image property="methodToCall.deletePaymentScheduleItem.line${status.index}.anchor${currentTabIndex}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
					</div>
                  </td>

	            </tr>
        	</c:forEach> 
            <%-- Existing data --%>
        </table>

</kul:innerTab>
