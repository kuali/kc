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

<c:set var="awardAttributes" value="${DataDictionary.Award.attributes}" />
<h3>
	<span class="subhead-left">Payment & Invoices</span>
	<span class="subhead-right">
		<kul:help businessObjectClassName="org.kuali.kra.award.bo.Award" altText="help"/>
	</span>	
</h3>
<table border="0" cellpadding="0" cellspacing="0" summary="">
	<tr>
		<th width="25%">
			<div align="right">
				<kul:htmlAttributeLabel attributeEntry="${awardAttributes.basisOfPaymentCode}"  />
			</div>
		</th>
		<td width="25%" valign="middle">
			<div align="left">
                <kul:htmlControlAttribute property="document.awardList[0].basisOfPaymentCode" attributeEntry="${awardAttributes.basisOfPaymentCode}" />
			</div>
		</td>
		<th width="25%">
			<div align="right">
				<kul:htmlAttributeLabel attributeEntry="${awardAttributes.methodOfPaymentCode}"  />
			</div>
		</th>
		<td width="25%" valign="middle">
			<div align="left">
                <kul:htmlControlAttribute property="document.awardList[0].methodOfPaymentCode" attributeEntry="${awardAttributes.methodOfPaymentCode}" />
			</div>
		</td>
	</tr>
	<tr>
		<th width="25%">
			<div align="right">
				<kul:htmlAttributeLabel attributeEntry="${awardAttributes.finalInvoiceDue}"  />
			</div>
		</th>
		<td width="25%" valign="middle">
			<div align="left">
				<c:out value="due within" />
                <kul:htmlControlAttribute property="document.awardList[0].finalInvoiceDue" attributeEntry="${awardAttributes.finalInvoiceDue}" />
                <c:out value="days" />
			</div>
		</td>
		<th width="25%">
			<div align="right">
				<kul:htmlAttributeLabel attributeEntry="${awardAttributes.documentFundingId}"  />
			</div>
		</th>
		<td width="25%" valign="middle">
			<div align="left">
                <kul:htmlControlAttribute property="document.awardList[0].documentFundingId" attributeEntry="${awardAttributes.documentFundingId}" />
			</div>
		</td>
	</tr>
</table>
