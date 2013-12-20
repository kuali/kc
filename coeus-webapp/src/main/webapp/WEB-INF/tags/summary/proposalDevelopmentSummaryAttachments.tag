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
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%@ attribute name="transparentBackground" required="false" %>
<c:set var="action" value="proposalDevelopmentApproverView" />
<c:set var="proposalDevelopmentAttributes"
	value="${DataDictionary.DevelopmentProposal.attributes}" />
<c:set var="narrativeAttributes"
	value="${DataDictionary.Narrative.attributes}" />
<c:set var="propPersonBioAttributes"
	value="${DataDictionary.ProposalPersonBiography.attributes}" />
<c:set scope="page" var="proposalAttachementCount" value="0" />
<c:forEach var="narrative"
	items="${KualiForm.document.developmentProposalList[0].narratives}"
	varStatus="status">
	<c:if
		test="${narrative.narrativeType.narrativeTypeGroup eq KualiForm.proposalDevelopmentParameters['proposalNarrativeTypeGroup'].value}">
		<c:set scope="page" var="proposalAttachementCount"
			value="${proposalAttachementCount + 1}" />
	</c:if>
</c:forEach>

<kul:tab tabTitle="Attachments" defaultOpen="false" tabErrorKey="" transparentBackground="${transparentBackground }">

	<kul:innerTab
		tabTitle="Proposal Attachments(${proposalAttachementCount})"
		parentTab="" defaultOpen="false">
		
		<c:if test="${proposalAttachementCount > 0 }" >
		
		<div class="tab-container" align="center">
			<table cellpadding="0" cellspacing="0" summary="">
				<tr>
					<th><div align="center">Attachment Type</div>
					</th>
					<th>
						<div align="center">
							<kul:htmlAttributeLabel
								attributeEntry="${narrativeAttributes.moduleTitle}"
								noColon="true" />
						</div>
					</th>
					<th><div align="center">
							<kul:htmlAttributeLabel
								attributeEntry="${narrativeAttributes.moduleStatusCode}"
								noColon="true" />
						</div>
					</th>
					<th><div align="center">&nbsp;</div>
					</th>
				</tr>
				<c:if
					test="${fn:length(KualiForm.document.developmentProposalList[0].narratives) > 0}">
					<c:forEach var="narrative"
						items="${KualiForm.document.developmentProposalList[0].narratives}"
						varStatus="status">
						<c:if
							test="${narrative.narrativeType.narrativeTypeGroup eq KualiForm.proposalDevelopmentParameters['proposalNarrativeTypeGroup'].value}">
							<tr>
							
								<td>
									<div align=center>${KualiForm.document.developmentProposalList[0].narratives[status.index].narrativeType.description}&nbsp;</div>
								</td>
								<td>
									<div align=center>${KualiForm.document.developmentProposalList[0].narratives[status.index].moduleTitle}&nbsp;</div>
								</td>
								<td>
									<div align=center>
										${KualiForm.document.developmentProposalList[0].narratives[status.index].narrativeStatus.description}&nbsp;</div>
								</td>
								<td>
									<div align=center>

										<html:image
											styleId="downloadProposalAttachment.line${status.index}"
											property="methodToCall.downloadProposalAttachment.line${status.index}.anchor${currentTabIndex}"
											src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif'
											styleClass="tinybutton"
											onclick="javascript: openNewWindow('${action}','downloadProposalAttachment','${status.index}',${KualiForm.formKey},'${KualiForm.document.sessionDocument}'); return false" />

									</div>
								</td>
							</tr>
						</c:if>
					</c:forEach>
				</c:if>
			</table>
		</div>
		</c:if>	
	</kul:innerTab>

	<kul:innerTab
		tabTitle="Personnel Attachments (${fn:length(KualiForm.document.developmentProposalList[0].propPersonBios)})"
		parentTab="" defaultOpen="false">
			<c:if test="${fn:length(KualiForm.document.developmentProposalList[0].propPersonBios) > 0 }" >

		<div class="tab-container" align="center">
			<table cellpadding="0" cellspacing="0" summary="">
				<tr>
					<th><div align="center">Person</div>
					</th>
					<th><div align="center">Attachment Type</div>
					</th>
					<th><div align="center">Description</div>
					</th>
					<th><div align="center">&nbsp;</div>
					</th>
				</tr>
				<c:if
					test="${fn:length(KualiForm.document.developmentProposalList[0].propPersonBios) > 0}">
					<c:forEach var="personalAttachment"
						items="${KualiForm.document.developmentProposalList[0].propPersonBios}"
						varStatus="status">
						<tr>
							<c:if
								test="${fn:length(KualiForm.document.developmentProposalList[0].proposalPersons) > 0}">
								<td>
									<div align=center>${KualiForm.document.developmentProposalList[0].proposalPersons[status.index].fullName}&nbsp;</div>
								</td>

							</c:if>
							<td>
								<div align=center>${KualiForm.document.developmentProposalList[0].propPersonBios[status.index].propPerDocType.description}&nbsp;</div>
							</td>
							<td>
								<div align=center>
									${KualiForm.document.developmentProposalList[0].propPersonBios[status.index].description}&nbsp;</div>
							</td>
							<td><div align=center>
									<html:image
										property="methodToCall.viewPersonnelAttachment.line${status.index}.anchor${currentTabIndex}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif'
										styleClass="tinybutton"
										onclick="javascript: openNewWindow('proposalDevelopmentAbstractsAttachments','viewPersonnelAttachment',${status.index},${KualiForm.formKey},'${KualiForm.document.sessionDocument}'); return false" />
								</div>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</div>
		</c:if>
	</kul:innerTab>

	<kul:innerTab
		tabTitle="Institutional Attachments (${fn:length(KualiForm.document.developmentProposalList[0].instituteAttachments)})"
		parentTab="" defaultOpen="false">
		<c:if test="${fn:length(KualiForm.document.developmentProposalList[0].instituteAttachments) > 0 }" >
		<div class="tab-container" align="center">
			<table cellpadding="0" cellspacing="0" summary="">
				<tr>
					<th><div align="center">Attachment Type</div>
					</th>
					<th><div align="center">
							<kul:htmlAttributeLabel
								attributeEntry="${narrativeAttributes.moduleTitle}"
								noColon="true" />
						</div>
					</th>
					<th><div align="center">&nbsp;</div>
					</th>
				</tr>
				<c:if
					test="${fn:length(KualiForm.document.developmentProposalList[0].instituteAttachments) > 0}">
					<c:forEach var="instituteAttachment"
						items="${KualiForm.document.developmentProposalList[0].instituteAttachments}"
						varStatus="status">
						<tr>
							<td>
								<div align=center>${KualiForm.document.developmentProposalList[0].instituteAttachments[status.index].narrativeType.description}&nbsp;</div>
							</td>
							<td>
								<div align=center>${KualiForm.document.developmentProposalList[0].instituteAttachments[status.index].moduleTitle}&nbsp;</div>
							</td>
							<td>
								<div align=center>
									<html:image
										styleId="viewInstituteAttachment.line${status.index}"
										property="methodToCall.downloadInstituteAttachment.line${status.index}.anchor${currentTabIndex}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif'
										styleClass="tinybutton"
										onclick="javascript: openNewWindow('${action}','downloadInstituteAttachment','${status.index}',${KualiForm.formKey},'${KualiForm.document.sessionDocument}'); return false" />
								</div>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</div>
		</c:if>
	</kul:innerTab>

</kul:tab>