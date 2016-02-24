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
