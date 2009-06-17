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
<%-- member of AwardPaymentReportsAndTerms.jsp --%>

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="awardPaymentAndInvoiceRequirementsCommentAttributes" value="${DataDictionary.AwardComment.attributes}" />
<c:set var="action" value="awardPaymentReportsAndTerms" />

<kul:tabTop tabTitle="Payment & Invoices" defaultOpen="false" tabErrorKey="" >
	<div class="tab-container" align="right">
		<kra-a:awardPaymentAndInvoicesTopPanel />	
		<kra-a:awardReportClasses index="5" reportClassKey="${KualiForm.reportClassForPaymentsAndInvoices.reportClassCode}" 
			reportClassLabel="Payment & Invoice Requirements" defaultOpenForTab="true" noShowHideButton="true" />
		<table border="0" cellpadding="0" cellspacing="0" summary="">
		<tr>
       	    <th width="100" align="right" scope="row"><div align="center">Invoice Instructions:</div></th>
       			<td class="infoline" colspan="10">
           	 		<div align="left">
           	  	 		<kul:htmlControlAttribute property="document.award.awardPaymentAndInvoiceRequirementsComments.comments" attributeEntry="${awardPaymentAndInvoiceRequirementsCommentAttributes.comments}"/>
           	  	 		<kra:expandedTextArea textAreaFieldName="document.award.awardPaymentAndInvoiceRequirementsComments.comments" action="${action}" textAreaLabel="${awardPaymentAndInvoiceRequirementsCommentAttributes.comments.label}" />
           	 		</div>
           		</td>            
       		</tr>
       	</table>
       	<kra-a:awardPaymentSchedule />
	</div>
</kul:tabTop>
