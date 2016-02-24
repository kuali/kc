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
<%-- member of AwardPaymentReportsAndTerms.jsp --%>

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="awardPaymentAndInvoiceRequirementsCommentAttributes" value="${DataDictionary.AwardComment.attributes}" />
<c:set var="action" value="awardPaymentReportsAndTerms" />
																			
<kul:tabTop tabTitle="Payment & Invoices" defaultOpen="false" tabErrorKey="document.awardList[0].awardPaymentAndInvoiceRequirementsComments.comments*,document.awardList[0].paymentsAndInvoices.*,document.award.awardTemplate.PAYMENTS_AND_INVOICES_TAB" auditCluster="paymentAndInvoicesAuditErrors" tabAuditKey="document.paymentsAuditRules*">
	<div class="tab-container" align="center">
		<kra-a:awardPaymentAndInvoicesTopPanel />	
		<kra-a:awardReportClasses index="2" reportClassKey="${KualiForm.reportClassForPaymentsAndInvoices.reportClassCode}" reportCodeLabel="* Payment Type"
			reportClassLabel="Payment & Invoice Requirements" defaultOpenForTab="true" noShowHideButton="true" />
		<table border="0" cellpadding="0" cellspacing="0" summary="">
		<tr>
       	    <th width="100" align="right" scope="row"><div align="center">Invoice Instructions:</div></th>
       			<td class="infoline" colspan="10">
           	 		<div align="left">
           	  	 		<kul:htmlControlAttribute property="document.awardList[0].awardPaymentAndInvoiceRequirementsComments.comments" attributeEntry="${awardPaymentAndInvoiceRequirementsCommentAttributes.comments}"/>
           	 		</div>
           		</td>            
       		</tr>
       	</table>
       	
       	
       
		<div align="center">
		</br>
		<c:if test = "${(!readOnly)}">
			<kra-a:awardSyncButton  scopeNames="PAYMENTS_AND_INVOICES_TAB" tabKey="${tabKey}"/>
		</c:if>
		</div>	
       	
       	<br/>
       	
       	<kra-a:awardPaymentSchedule />
	</div>
</kul:tabTop>
