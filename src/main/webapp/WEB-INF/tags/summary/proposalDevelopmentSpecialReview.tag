<%--
 Copyright 2005-2014 The Kuali Foundation
 
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
<%@ attribute name="businessObjectClassName" required="true"
	description="The specific per-module business class to use for the help pages"%>
<%@ attribute name="attributes" required="true" type="java.util.Map"
	description="The Data Dictionary reference to the Special Review attributes"%>
<%@ attribute name="exemptionAttributes" required="true"
	type="java.util.Map"
	description="The Data Dictionary reference to the Special Review Exemption attributes"%>
<%@ attribute name="collectionReference" required="true"
	type="java.util.List"
	description="The object reference to the collection that holds all the current Special Reviews"%>
<%@ attribute name="collectionProperty" required="true"
	description="The property name of the collection that holds all the current Special Reviews"%>
<%@ attribute name="action" required="true" 
	description="The name of the action class"%>
<%@ attribute name="transparentBackground" required="false" %>
<c:set var="canModify"
	value="${KualiForm.specialReviewHelper.canModifySpecialReview}" />
<c:set var="enableIrbProtocolLinking"
	value="${KualiForm.specialReviewHelper.isIrbProtocolLinkingEnabled}" />
<c:set var="commentDisplayLength"
	value="<%=org.kuali.kra.infrastructure.Constants.SPECIAL_REVIEW_COMMENT_LENGTH%>" />
<c:set var="count" value="${fn:length(collectionReference)}" />
<kul:tab
	tabTitle="Special Review (${count})" transparentBackground="${transparentBackground }"
	defaultOpen="false">
	<div class="tab-container" align="center">
		<h3>
			<span class="subhead-left">Special Review Information</span> <span
				class="subhead-right">
		</h3>

		<table id="specialReviewTableId" cellpadding="0" cellspacing="0"
			summary="">
			<tr>
				<th><div align="center">Special Review</div>
				</th>
				<th><div align="center">Approval Status</div>
				</th>
				<th><div align="center">
						Protocol Number
						</nobr>
					</div>
				</th>
				<th><div align="center">Application Date</div>
				</th>
				<th><div align="center">Comments</div>
				</th>
			</tr>
                	
			<c:forEach var="specialReview" items="${collectionReference}" varStatus="status">
				<tr>
				
					<td align="left" valign="middle">
						<div align="center">
							<kul:htmlControlAttribute
								property="${collectionProperty}[${status.index}].specialReviewTypeCode"
								attributeEntry="${attributes.specialReviewTypeCode}"
								readOnly="true" styleClass="fixed-size-200-select"
								readOnlyAlternateDisplay="${specialReview.specialReviewType.description}" />
						</div>
					</td>
					<td><div align="center">
							<kul:htmlControlAttribute
								property="${collectionProperty}[${status.index}].approvalTypeCode"
								attributeEntry="${attributes.approvalTypeCode}" readOnly="true"
								readOnlyAlternateDisplay="${specialReview.approvalType.description}" />
						</div>
					</td>
					<td><div align="center">
							<kul:htmlControlAttribute
								property="${collectionProperty}[${status.index}].protocolNumber"
								attributeEntry="${attributes.protocolNumber}" readOnly="true" />

						</div>
					</td>
					<td align="left" valign="middle"><div align="center">
							<kra:dynamicHtmlControlAttribute
								property="${collectionProperty}[${status.index}].applicationDate"
								attributeEntry="${attributes.applicationDate}"
								initialReadOnly="${protocolLinkingReadOnly}" readOnly="true"
								staticOnly="${!enableIrbProtocolLinking}" />
						</div>
					</td>
					<td align="left" valign="middle"><div align="center">

							<html:image
								property="methodToCall.getProposalComment.personIndex${status.index}.anchor${currentTabIndex}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif'
								styleClass="tinybutton"
								onclick="javascript: proposalDevelopmentCommentPop('${status.index}',${KualiForm.formKey},'${KualiForm.document.sessionDocument}','${KualiForm.document.developmentProposalList[0].propSpecialReviews[status.index].comments}');return false" />

						</div></td>

				</tr>
			</c:forEach>

		</table>
	</div>
</kul:tab>