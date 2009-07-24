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

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.DevelopmentProposal.attributes}" />
<c:set var="s2sAppSubmissionAttributes" value="${DataDictionary.S2sAppSubmission.attributes}" />
<c:set var="s2sAppAttachmentsAttributes" value="${DataDictionary.S2sAppAttachments.attributes}" />

<c:set var="action" value="proposalDevelopmentProposal" />

<kul:innerTab parentTab="Opportunity Search" defaultOpen="false" tabTitle="Submission Details">
    <div class="innerTab-container" align="left">
        <table class=tab cellpadding=0 cellspacing="0" summary=""> 
        <tbody id="G1">
            <c:set var="submissionToBeDisplayed" value="${fn:length(KualiForm.document.developmentProposalList[0].s2sAppSubmission)}" />
            <c:set var="textAreaFieldName" value="document.developmentProposalList[0].s2sAppSubmission[${submissionToBeDisplayed-1}].comments" />
            <c:choose>	
 	            <c:when test ="${empty KualiForm.document.developmentProposalList[0].s2sAppSubmission[submissionToBeDisplayed-1].submissionNumber}">
 		            <tr>
						<td>
 			            Submission details will be available after the proposal is submitted.
 		            	</td>
					</tr> 	
 	            </c:when>
 	            <c:otherwise>
	                <tr>
		                <th width="25%">
                            <div align="left">
                                <kul:htmlAttributeLabel attributeEntry="${s2sAppSubmissionAttributes.receivedDate}" />
                            </div>
						</th>
						<td>
							<fmt:formatDate value="${KualiForm.document.developmentProposalList[0].s2sAppSubmission[submissionToBeDisplayed-1].receivedDate}" type="both" dateStyle="short" timeStyle="short"/>							
						</td>
						<th width="25%">
							<div align="left">
								<kul:htmlAttributeLabel attributeEntry="${s2sAppSubmissionAttributes.lastModifiedDate}" />
							</div>
						</th>
						<td>
							<fmt:formatDate value="${KualiForm.document.developmentProposalList[0].s2sAppSubmission[submissionToBeDisplayed-1].lastModifiedDate}" type="both" dateStyle="short" timeStyle="short"/>							
						</td>
					</tr>
					<tr>
						<th width="25%">
							<div align="left">
								<kul:htmlAttributeLabel attributeEntry="${s2sAppSubmissionAttributes.status}" />
							</div>
						</th>
						<td>
							<kul:htmlControlAttribute property="document.developmentProposalList[0].s2sAppSubmission[${submissionToBeDisplayed-1}].status" 
                                                      attributeEntry="${s2sAppSubmissionAttributes.status}" readOnly="true" />
						</td>
					</tr>
	
					<tr>
						<th width="25%">
							<div align="left">
								<kul:htmlAttributeLabel attributeEntry="${s2sAppSubmissionAttributes.ggTrackingId}" />
							</div>
						</th>
						<td>
							<kul:htmlControlAttribute property="document.developmentProposalList[0].s2sAppSubmission[${submissionToBeDisplayed-1}].ggTrackingId" 
                                                      attributeEntry="${s2sAppSubmissionAttributes.ggTrackingId}" readOnly="true" />
                        </td>
					</tr>
	
					<tr>
						<th width="25%">
							<div align="left">
								<kul:htmlAttributeLabel attributeEntry="${s2sAppSubmissionAttributes.agencyTrackingId}" />
							</div>
						</th>
						<td>
							<kul:htmlControlAttribute property="document.developmentProposalList[0].s2sAppSubmission[${submissionToBeDisplayed-1}].agencyTrackingId" 
                                                      attributeEntry="${s2sAppSubmissionAttributes.agencyTrackingId}" readOnly="true" />
						</td>
					</tr>
	
					<tr>
						<th width="25%">
							<div align="left">
								<kul:htmlAttributeLabel attributeEntry="${s2sAppSubmissionAttributes.comments}" />
							</div>
						</th>		
						<td>
							<kul:htmlControlAttribute property="document.developmentProposalList[0].s2sAppSubmission[${submissionToBeDisplayed-1}].comments" 
                                                      attributeEntry="${s2sAppSubmissionAttributes.comments}" readOnly="true"/>
							<kra:expandedTextArea textAreaFieldName="${textAreaFieldName}" action="${action}" 
                                                  textAreaLabel="${s2sAppSubmissionAttributes.comments.description}" />
						</td>
					</tr>

					<tr>
						<th colspan="4">
							<div align="left">
								<kul:htmlAttributeLabel attributeEntry="${s2sAppAttachmentsAttributes.contentId}" />
							</div>
						</th>
					</tr>

					<c:forEach var="form" 
                               items="${KualiForm.document.developmentProposalList[0].s2sAppSubmission[submissionToBeDisplayed-1].s2sApplication[0].s2sAppAttachmentList}" 
                               varStatus="status">		                
						<tr>
							<td align="left" valign="middle" colspan="4">
	    						<kul:htmlControlAttribute property="document.developmentProposalList[0].s2sAppSubmission[${submissionToBeDisplayed-1}].s2sApplication[0].s2sAppAttachmentList[${status.index}].contentId" 
                                                          attributeEntry="${s2sAppAttachmentsAttributes.contentId}" readOnly="true" />
							</td>
						</tr>	
					</c:forEach>

					<tr>
    					<td colspan="5">
    						<div align="right">
    							<html:image src="${ConfigProperties.kra.externalizable.images.url}tinybutton-refresh.gif" 
                                            styleClass="globalbuttons" 
                                            property="methodToCall.refreshSubmissionDetails" 
                                            alt="Refresh Submission Details"/>
    				            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    		                </div>
    					</td>
    				</tr>	
 				</c:otherwise>
 			</c:choose>
		</tbody>
		</table>
	</div>
</kul:innerTab>

