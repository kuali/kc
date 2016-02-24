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

<c:set var="cgbAttributes" value="${DataDictionary.AwardCgb.attributes}" />
<c:set var="awardCommentAttributes" value="${DataDictionary.AwardComment.attributes}" />

<kul:tab tabTitle="Contract And Grants Billing" defaultOpen="false" tabErrorKey="document.awardList[0].awardCgb*">	
	<div class="tab-container" align="center">
	<h3><span class="subhead-left">Accounts</span></h3>
	<table border="0" cellpadding="0" cellspacing="0" summary="">	
		<tr>
			<th>
				<div style="text-align:right;">
					<kul:htmlAttributeLabel attributeEntry="${cgbAttributes.lastBilledDate}"  />
				</div>
			</th>
			<td>
				<div style="text-align:left;">
	                <kul:htmlControlAttribute property="document.awardList[0].awardCgb.lastBilledDate" attributeEntry="${cgbAttributes.lastBilledDate}" readOnly="true"/>
				</div>
			</td>
			<th>
				<div style="text-align:right;">
					<kul:htmlAttributeLabel attributeEntry="${cgbAttributes.finalBill}"  />
				</div>
			</th>
			<td>
				<div style="text-align:left;">
	                <kul:htmlControlAttribute property="document.awardList[0].awardCgb.finalBill" attributeEntry="${cgbAttributes.finalBill}" readOnly="true"/>
				</div>
			</td>
		</tr>
		<tr>
			<th>
				<div style="text-align:right;">
					<kul:htmlAttributeLabel attributeEntry="${cgbAttributes.amountToDraw}"  />
				</div>
			</th>
			<td>
				<div style="text-align:left;">
	                <kul:htmlControlAttribute property="document.awardList[0].awardCgb.amountToDraw" attributeEntry="${cgbAttributes.amountToDraw}" readOnly="true"/>
				</div>
			</td>
			<th>
				<div style="text-align:right;">
					<kul:htmlAttributeLabel attributeEntry="${cgbAttributes.letterOfCreditReviewIndicator}"  />
				</div>
			</th>
			<td>
				<div style="text-align:left;">
	                <kul:htmlControlAttribute property="document.awardList[0].awardCgb.letterOfCreditReviewIndicator" attributeEntry="${cgbAttributes.letterOfCreditReviewIndicator}" readOnly="true"/>
				</div>
			</td>
		</tr>
		<tr>
			<th>
				<div style="text-align:right;">
					<kul:htmlAttributeLabel attributeEntry="${cgbAttributes.invoiceDocumentStatus}"  />
				</div>
			</th>
			<td colspan="3">
				<div style="text-align:left;">
	                <kul:htmlControlAttribute property="document.awardList[0].awardCgb.invoiceDocumentStatus" attributeEntry="${cgbAttributes.invoiceDocumentStatus}" readOnly="true"/>
				</div>
			</td>
		</tr>
	</table>	
	<c:set var="isRoot" value="${fn:endsWith(KualiForm.document.award.awardNumber, '00001')}"/>
	<c:choose>
		<c:when test="${isRoot}"><c:set var="cgbPath" value="document.awardList[0]"/></c:when>
		<c:otherwise><c:set var="cgbPath" value="awardHierarchyBean.rootNode.award"/></c:otherwise>
	</c:choose>
	<c:set var="cgbReadOnly" value="${!isRoot || readOnly}"/>
	<h3><span class="subhead-left">Contract and Grants Billing</span></h3>
	<table border="0" cellpadding="0" cellspacing="0" summary="">	
		<tr>
			<th>
				<div style="text-align:right;">
					<kul:htmlAttributeLabel attributeEntry="${cgbAttributes.additionalFormsRequired}"  />
				</div>
			</th>
			<td>
				<div style="text-align:left;">
	                <kul:htmlControlAttribute property="${cgbPath}.awardCgb.additionalFormsRequired" attributeEntry="${cgbAttributes.additionalFormsRequired}" readOnly="${cgbReadOnly}"/>
				</div>
			</td>
			<th>
				<div style="text-align:right;">
					<kul:htmlAttributeLabel attributeEntry="${cgbAttributes.autoApproveInvoice}"  />
				</div>
			</th>
			<td>
				<div style="text-align:left;">
	                <kul:htmlControlAttribute property="${cgbPath}.awardCgb.autoApproveInvoice" attributeEntry="${cgbAttributes.autoApproveInvoice}" readOnly="${cgbReadOnly}"/>
				</div>
			</td>
		</tr>
		<tr>
			<th>
				<div style="text-align:right;">
					<c:out value="${document.awardList[0].additionalFormsDescriptionComment.commentType.description}"/>
				</div>
			</th>
			<td colspan="3">
				<div style="text-align:left;">
	                <kul:htmlControlAttribute property="${cgbPath}.additionalFormsDescriptionComment.comments" attributeEntry="${awardCommentAttributes.comments}" readOnly="${cgbReadOnly}"/>
				</div>
			</td>
		</tr>
		<tr>
			<th>
				<div style="text-align:right;">
					<kul:htmlAttributeLabel attributeEntry="${cgbAttributes.minInvoiceAmount}"  />
				</div>
			</th>
			<td>
				<div style="text-align:left;">
	                <kul:htmlControlAttribute property="${cgbPath}.awardCgb.minInvoiceAmount" attributeEntry="${cgbAttributes.minInvoiceAmount}" readOnly="${cgbReadOnly}"/>
				</div>
			</td>
			<th>
				<div style="text-align:right;">
					<kul:htmlAttributeLabel attributeEntry="${cgbAttributes.invoicingOption}"  />
				</div>
			</th>
			<td>
				<div style="text-align:left;">
					<kul:htmlControlAttribute property="${cgbPath}.awardCgb.invoicingOption" attributeEntry="${cgbAttributes.invoicingOption}" readOnly="${cgbReadOnly}"/>
				</div>
			</td>
		</tr>
		<tr>
			<th>
				<div style="text-align:right;">
					<kul:htmlAttributeLabel attributeEntry="${cgbAttributes.stopWork}"  />
				</div>
			</th>
			<td>
				<div style="text-align:left;">
	                <kul:htmlControlAttribute property="${cgbPath}.awardCgb.stopWork" attributeEntry="${cgbAttributes.stopWork}" readOnly="${cgbReadOnly}"/>
				</div>
			</td>
			<th>
				<div style="text-align:right;">
					<kul:htmlAttributeLabel attributeEntry="${cgbAttributes.dunningCampaignId}"  />
				</div>
			</th>
			<td>
				<div style="text-align:left;">
					<kul:htmlControlAttribute property="${cgbPath}.awardCgb.dunningCampaignId" attributeEntry="${cgbAttributes.dunningCampaignId}" readOnly="${cgbReadOnly}"/>
					<c:if test="${cgbReadOnly}">
						<kul:lookup boClassName="org.kuali.kra.external.dunningcampaign.DunningCampaign"
                                fieldConversions="campaignID:${cgbPath}.awardCgb.dunningCampaignId"
                                lookupParameters="${cgbPath}.awardCgb.dunningCampaignId:campaignID"
                                anchor="${tabKey}" />
                    </c:if>
				</div>
			</td>
		</tr>
		<tr>
			<th>
				<div style="text-align:right;">
					<c:out value="${document.awardList[0].stopWorkReasonComment.commentType.description}"/>
				</div>
			</th>
			<td colspan="3">
				<div style="text-align:left;">
	                <kul:htmlControlAttribute property="${cgbPath}.stopWorkReasonComment.comments" attributeEntry="${awardCommentAttributes.comments}" readOnly="${cgbReadOnly}"/>
				</div>
			</td>
		</tr>
		<tr>
			<th>
				<div style="text-align:right;">
					<kul:htmlAttributeLabel attributeEntry="${cgbAttributes.suspendInvoicing }"/>
				</div>
			</th>
			<td>
				<div style="text-align:left;" colspace="3">
					<kul:htmlControlAttribute property="${cgbPath}.awardCgb.suspendInvoicing" attributeEntry="${cgbAttributes.suspendInvoicing}" readOnly="${cgbReadOnly}"/>
				</div>
			</td>
		</tr>
		<tr>
			<th>
				<div style="text-align:right;">
					<c:out value="${document.awardList[0].suspendInvoicingComment.commentType.description}"/>
				</div>
			</th>
			<td colspan="3">
				<div style="text-align:left;">
	                <kul:htmlControlAttribute property="${cgbPath}.suspendInvoicingComment.comments" attributeEntry="${awardCommentAttributes.comments}" readOnly="${cgbReadOnly}"/>
				</div>
			</td>
		</tr>					
	</table>
	</div>
</kul:tab>
