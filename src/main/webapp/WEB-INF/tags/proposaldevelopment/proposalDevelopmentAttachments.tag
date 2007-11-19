<%--
 Copyright 2007 The Kuali Foundation.
 
 Licensed under the Educational Community License, Version 1.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl1.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%@ attribute name="htmlFormAction" required="false" %>
<%@ attribute name="renderMultipart" required="false" %>

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.ProposalDevelopmentDocument.attributes}" />
<c:set var="narrativeAttributes" value="${DataDictionary.Narrative.attributes}" />
<c:set var="narrativeAttachmentAttributes" value="${DataDictionary.NarrativeAttachment.attributes}" />

<c:set var="action" value="proposalDevelopmentAbstractsAttachments" />
<kul:tabTop tabTitle="Proposal Attachments (${fn:length(KualiForm.document.narratives)})" defaultOpen="true" tabErrorKey="document.narrative*">
	<div class="tab-container" align="center">
    	<div class="h2-container">
    		<span class="subhead-left"><h2>Add Proposal Attachments</h2></span>
    		<span class="subhead-right"><kul:help businessObjectClassName="fillMeIn" altText="help"/></span>
        </div>
        <table cellpadding=0 cellspacing=0 summary="">
           	<tr>
         		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.narrativeTypeCode}"/></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="newNarrative.narrativeTypeCode" attributeEntry="${narrativeAttributes.narrativeTypeCode}" />
				</td>
          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttachmentAttributes.fileName}"/></div></th>
                <td align="left" valign="middle">
                	<html:file property="newNarrative.narrativeFile" />
				</td>
          	</tr>
          	<tr>
          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.moduleStatusCode}"/></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="newNarrative.moduleStatusCode" attributeEntry="${narrativeAttributes.moduleStatusCode}" />
				</td>
          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.contactName}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="newNarrative.contactName" attributeEntry="${narrativeAttributes.contactName}" />
				</td>
          	</tr>
          	<tr>
          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.updateUser}" /></div></th>
                <td align="left" valign="middle">&nbsp;</td>
          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.emailAddress}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="newNarrative.emailAddress" attributeEntry="${narrativeAttributes.emailAddress}" />
				</td>
          	</tr>
          	<tr>
          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.updateTimestamp}" /></div></th>
                <td align="left" valign="middle">&nbsp;</td>
          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.phoneNumber}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="newNarrative.phoneNumber" attributeEntry="${narrativeAttributes.phoneNumber}" />
				</td>
          	</tr>
          	<tr>
          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.comments}" /></div></th>
                <td align="left" valign="middle">
                	<c:set var="textAreaFieldName" value="newNarrative.comments" />
                	<kul:htmlControlAttribute property="newNarrative.comments" attributeEntry="${narrativeAttributes.comments}" />
                    <kra:expandedTextArea textAreaFieldName="${textAreaFieldName}" action="${action}" textAreaLabel="${narrativeAttributes.comments.label}" />
				</td>
          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.moduleTitle}" /></div></th>
                <td align="left" valign="middle">
                	<c:set var="textAreaFieldName" value="newNarrative.moduleTitle" />
                	<kul:htmlControlAttribute property="newNarrative.moduleTitle" attributeEntry="${narrativeAttributes.moduleTitle}" />
                    <kra:expandedTextArea textAreaFieldName="${textAreaFieldName}" action="${action}" textAreaLabel="${narrativeAttributes.moduleTitle.label}" />
				</td>
          	</tr>
          	<tr>
				<td colspan=4>
					<div align="center">
						<html:image property="methodToCall.addProposalAttachment"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' />
					</div>
                </td>
            </tr>
            <tr>
            	<td colspan="4">
            	<div  align="left">
        	<c:forEach var="narrative" items="${KualiForm.document.narratives}" varStatus="status">
        	<c:if test="${narrative.narrativeType.narrativeTypeGroup eq ProposalDevelopmentParameters.proposalNarrativeTypeGroup.parameterValue}">
			<c:set var="narrType" value="${narrative.narrativeType.description}"/>
			<c:set var="narrStatus" value="${narrative.narrativeStatus.description}"/>
			<kul:innerTab parentTab="Proposal Attachments" defaultOpen="false" tabDescription="${narrType} - ${narrStatus}" tabTitle="${status.index+1}. ${narrType} - ${narrStatus}">
				<div class="innerTab-container" align="left">
					<table class=tab cellpadding=0 cellspacing=0 summary="">
			          	<tr>
			          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.narrativeTypeCode}"/></div></th>
			                <td align="left" valign="middle">
			                	<kul:htmlControlAttribute property="document.narratives[${status.index}].narrativeTypeCode" attributeEntry="${narrativeAttributes.narrativeTypeCode}" />
							</td>
			          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttachmentAttributes.fileName}"/></div></th>
			                <td align="left" valign="middle">
			                <%-- %><c:if test="${(!empty narrative.fileName)}">--%>
				                <div id="replaceDiv${status.index}" style="display:block;">
					                <kul:htmlControlAttribute property="document.narratives[${status.index}].fileName" readOnly="true" attributeEntry="${narrativeAttributes.fileName}" />
					                <c:if test="${(narrative.viewAttachment || narrative.modifyAttachment) }">
						                (
						                <c:if test="${narrative.viewAttachment && (!empty narrative.fileName)}">
						                <html:link linkName="downloadProposalAttachment.line${status.index}" onclick="javascript: openNewWindow('${action}','downloadProposalAttachment','${status.index}'); return true" href="" anchor="${currentTabIndex}" property="methodToCall.downloadProposalAttachment.line${status.index}">download</html:link>
							                <c:if test="${narrative.modifyAttachment}">
							                &nbsp;|&nbsp;
							                </c:if>
						                </c:if>
						                <c:if test="${narrative.modifyAttachment}">
						                <html:link linkName="replaceProposalAttachment.line${status.index}" onclick="javascript: showHide('fileDiv${status.index}','replaceDiv${status.index}')" href="" anchor="${currentTabIndex}" property="methodToCall.replaceProposalAttachment.line${status.index}">replace</html:link>
						                </c:if>
						                )
					                </c:if>
				                </div>
				                <div id="fileDiv${status.index}" valign="middle" style="display:none;">
				                	<html:file property="document.narratives[${status.index}].narrativeFile" />
									<html:image property="methodToCall.replaceProposalAttachment.line${status.index}.anchor${currentTabIndex}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' />
								</div>
				            <%-- </c:if> --%>
							</td>
			          	</tr>
			          	<tr>
			          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.moduleStatusCode}"/></div></th>
			                <td align="left" valign="middle">
			                	<kul:htmlControlAttribute property="document.narratives[${status.index}].moduleStatusCode" attributeEntry="${narrativeAttributes.moduleStatusCode}" />
							</td>
			          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.contactName}" /></div></th>
			                <td align="left" valign="middle">
			                	<kul:htmlControlAttribute property="document.narratives[${status.index}].contactName" attributeEntry="${narrativeAttributes.contactName}" />
							</td>
			          	</tr>
			          	<tr>
			          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.updateUser}" /></div></th>
			                <td align="left" valign="middle">
			                	<kul:htmlControlAttribute property="document.narratives[${status.index}].updateUser" readOnly="true" attributeEntry="${narrativeAttributes.updateUser}" />
							</td>
			          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.emailAddress}" /></div></th>
			                <td align="left" valign="middle">
			                	<kul:htmlControlAttribute property="document.narratives[${status.index}].emailAddress" attributeEntry="${narrativeAttributes.emailAddress}" />
							</td>
			          	</tr>
			          	<tr>
			          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.updateTimestamp}" /></div></th>
			                <td align="left" valign="middle">
			                	<kul:htmlControlAttribute property="document.narratives[${status.index}].updateTimestamp" readOnly="true" attributeEntry="${narrativeAttributes.updateTimestamp}" />
							</td>
			          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.phoneNumber}" /></div></th>
			                <td align="left" valign="middle">
			                	<kul:htmlControlAttribute property="document.narratives[${status.index}].phoneNumber" attributeEntry="${narrativeAttributes.phoneNumber}" />
							</td>
			          	</tr>
			          	<tr>
			          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.comments}" /></div></th>
			                <td align="left" valign="middle">
			                	<kul:htmlControlAttribute property="document.narratives[${status.index}].comments" attributeEntry="${narrativeAttributes.comments}" />
			                	<c:set var="textAreaFieldName" value="document.narratives[${status.index}].comments" />
			                	<kra:expandedTextArea textAreaFieldName="${textAreaFieldName}" action="${action}" textAreaLabel="${narrativeAttributes.moduleTitle.label}" />
							</td>
			          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.moduleTitle}" /></div></th>
			                <td align="left" valign="middle">
			                	<kul:htmlControlAttribute property="document.narratives[${status.index}].moduleTitle" attributeEntry="${narrativeAttributes.moduleTitle}" />
			                	<c:set var="textAreaFieldName" value="document.narratives[${status.index}].moduleTitle" />
			                	<kra:expandedTextArea textAreaFieldName="${textAreaFieldName}" action="${action}" textAreaLabel="${narrativeAttributes.moduleTitle.label}" />
							</td>
			          	</tr>
			          	<tr>
							<td colspan=4>
								<div align="center">
									<html:image styleId="deleteProposalAttachment.line${status.index}" property="methodToCall.deleteProposalAttachment.line${status.index}.anchor${currentTabIndex}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' />
										<html:image styleId="getProposalAttachmentRights.line${status.index}" property="methodToCall.getProposalAttachmentRights.line${status.index}.anchor${currentTabIndex}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-vieweditrights.gif' 
										onclick="javascript: proposalAttachmentRightsPop('${status.index}');return false"/>
								</div>
			                </td>
			            </tr>
			          </table>
			       </div>
			     </kul:innerTab>
			   </c:if>
        	</c:forEach> 
        	</div>
        	</td>
        	</tr>
        </table>
    </div>
</kul:tabTop>
