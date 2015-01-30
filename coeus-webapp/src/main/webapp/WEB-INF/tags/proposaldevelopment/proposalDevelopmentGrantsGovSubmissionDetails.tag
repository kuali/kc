<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2015 Kuali, Inc.
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

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.DevelopmentProposal.attributes}" />
<c:set var="s2sAppSubmissionAttributes" value="${DataDictionary.S2sAppSubmission.attributes}" />
<c:set var="s2sAppAttachmentsAttributes" value="${DataDictionary.S2sAppAttachments.attributes}" />

<c:set var="action" value="proposalDevelopmentProposal" />

<kul:innerTab parentTab="Opportunity Search" defaultOpen="${KualiForm.showSubmissionDetails}" tabTitle="Submission Details">
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
							<a href="javascript:showS2SAppSubmissionStatusDetails('${KualiForm.document.developmentProposal.proposalNumber}', '${KualiForm.document.developmentProposalList[0].s2sAppSubmission[submissionToBeDisplayed-1].ggTrackingId}')">
								<img src="${ConfigProperties.kr.externalizable.images.url}openreadonly_greenarrow01.png" alt="s2s status" styleClass="globalbuttons"/>
							</a>                                                      
							<div   id='s2s_status_popup' name='s2s_status_popup' style='display:none; position: absolute;width: 500px; height: 250px;'>
								<table  cellspacing="0" cellpadding="0" border="0"><th align="left">Grants.Gov Submission Status</th>
									<tr><td>
										<div id="s2s_status_detail"></div>
									</td></tr>
									<tr><td align=center>
										<div align="center"><img  align="middle" src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_close.gif" alt="s2s status" styleClass="globalbuttons"
												onclick="hideStatusDetails()" /></div>
									</td></tr>
								</table>
							</div>                                                      
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
                               items="${KualiForm.document.developmentProposalList[0].s2sAppSubmission[submissionToBeDisplayed-1].s2sAppAttachmentList}" 
                               varStatus="status">		                
						<tr>
							<td align="left" valign="middle" colspan="4">
	    						<kul:htmlControlAttribute property="document.developmentProposalList[0].s2sAppSubmission[${submissionToBeDisplayed-1}].s2sAppAttachmentList[${status.index}].contentId" 
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

