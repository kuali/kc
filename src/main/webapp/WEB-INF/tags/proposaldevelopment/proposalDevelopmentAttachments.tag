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
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<%@ attribute name="htmlFormAction" required="false" %>
<%@ attribute name="renderMultipart" required="false" %>

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.ProposalDevelopmentDocument.attributes}" />
<c:set var="narrativeAttributes" value="${DataDictionary.Narrative.attributes}" />
<c:set var="narrativeAttachmentAttributes" value="${DataDictionary.NarrativeAttachment.attributes}" />

<c:set var="action" value="proposalAttachments" />
<kul:tabTop tabTitle="Proposal Attachments" defaultOpen="true" tabErrorKey="document.narratives*">
	<div class="tab-container" align="center">
    	<div class="h2-container">
    		<span class="subhead-left"><h2>Proposal Attachments</h2></span>
    		<span class="subhead-right"><kul:help businessObjectClassName="fillMeIn" altText="help"/></span>
        </div>
        <table cellpadding=0 cellspacing=0 summary="">
          	<tr>
          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.narrativeTypeCode}" skipHelpUrl="true" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.newNarrative.narrativeTypeCode" attributeEntry="${narrativeAttributes.narrativeTypeCode}" />
				</td>
          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttachmentAttributes.fileName}" skipHelpUrl="true" /></div></th>
                <td align="left" valign="middle">
                	<%--<html:file property="narrativeFile"/>--%>
                	<html:file property="document.newNarrative.narrativeFile" />
                	<%--<input type="file" name="${narrativeAttachmentAttributes.fileName.name}"/> --%>
                	<%--<kul:htmlControlAttribute property="document.newNarrative.narrativeAttachment.fileName" readOnly="true" attributeEntry="${narrativeAttachmentAttributes.fileName}" />--%>
				</td>
          	</tr>
          	<tr>
          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.moduleStatusCode}" skipHelpUrl="true" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.newNarrative.moduleStatusCode" attributeEntry="${narrativeAttributes.moduleStatusCode}" />
				</td>
          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.contactName}" skipHelpUrl="true"  /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.newNarrative.contactName" attributeEntry="${narrativeAttributes.contactName}" />
				</td>
          	</tr>
          	<tr>
          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.updateUser}" skipHelpUrl="true"  /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.newNarrative.updateUser" readOnly="true" attributeEntry="${narrativeAttributes.updateUser}" />
				</td>
          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.emailAddress}" skipHelpUrl="true"  /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.newNarrative.emailAddress" attributeEntry="${narrativeAttributes.emailAddress}" />
				</td>
          	</tr>
          	<tr>
          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.updateTimestamp}" skipHelpUrl="true"  /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.newNarrative.updateTimestamp" readOnly="true" attributeEntry="${narrativeAttributes.updateTimestamp}" />
				</td>
          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.phoneNumber}" skipHelpUrl="true"  /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.newNarrative.phoneNumber" attributeEntry="${narrativeAttributes.phoneNumber}" />
				</td>
          	</tr>
          	<tr>
          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.comments}" skipHelpUrl="true"  /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.newNarrative.comments" attributeEntry="${narrativeAttributes.comments}" />
				</td>
          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.moduleTitle}" skipHelpUrl="true"  /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.newNarrative.moduleTitle" attributeEntry="${narrativeAttributes.moduleTitle}" />
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
            
        	<c:forEach var="proposalAttachment" items="${KualiForm.document.narratives}" varStatus="status">





          	<tr>
          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.narrativeTypeCode}" skipHelpUrl="true" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.narratives[${status.index}].narrativeTypeCode" attributeEntry="${narrativeAttributes.narrativeTypeCode}" />
				</td>
          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttachmentAttributes.fileName}" skipHelpUrl="true" /></div></th>
                <td align="left" valign="middle">
                
                <c:set var="narrPdfList" value="${proposalAttachment.narrativeAttachmentList}"></c:set>
                <c:if test="${fn:length(narrPdfList) >0}">
	                <kul:htmlControlAttribute property="document.narratives[${status.index}].narrativeAttachmentList[0].fileName" readOnly="true" attributeEntry="${narrativeAttachmentAttributes.fileName}" />
	            </c:if>
				</td>
          	</tr>
          	<tr>
          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.moduleStatusCode}" skipHelpUrl="true" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.narratives[${status.index}].moduleStatusCode" attributeEntry="${narrativeAttributes.moduleStatusCode}" />
				</td>
          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.contactName}" skipHelpUrl="true"  /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.narratives[${status.index}].contactName" attributeEntry="${narrativeAttributes.contactName}" />
				</td>
          	</tr>
          	<tr>
          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.updateUser}" skipHelpUrl="true"  /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.narratives[${status.index}].updateUser" readOnly="true" attributeEntry="${narrativeAttributes.updateUser}" />
				</td>
          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.emailAddress}" skipHelpUrl="true"  /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.narratives[${status.index}].emailAddress" attributeEntry="${narrativeAttributes.emailAddress}" />
				</td>
          	</tr>
          	<tr>
          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.updateTimestamp}" skipHelpUrl="true"  /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.narratives[${status.index}].updateTimestamp" readOnly="true" attributeEntry="${narrativeAttributes.updateTimestamp}" />
				</td>
          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.phoneNumber}" skipHelpUrl="true"  /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.narratives[${status.index}].phoneNumber" attributeEntry="${narrativeAttributes.phoneNumber}" />
				</td>
          	</tr>
          	<tr>
          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.comments}" skipHelpUrl="true"  /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.narratives[${status.index}].comments" attributeEntry="${narrativeAttributes.comments}" />
				</td>
          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.moduleTitle}" skipHelpUrl="true"  /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.narratives[${status.index}].moduleTitle" attributeEntry="${narrativeAttributes.moduleTitle}" />
                	<%--<html:image property="methodToCall.updateTextArea.((#${textAreaFieldName}:${action}:${narrativeAttributes.moduleTitle.label}#)).anchor${tabKey}" src='${ConfigProperties.kra.externalizable.images.url}pencil_add.png' onclick="javascript: textAreaPop(document.getElementById('${textAreaFieldName}').value,'${textAreaFieldName}','proposalDevelopment','${narrativeAttributes.moduleTitle.label}');return false"/>--%>
				</td>
          	</tr>
          	<tr>
				<td colspan=4>
					<div align="center">
						<html:image property="methodToCall.deleteProposalAttachment.line${status.index}.anchor${currentTabIndex}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' />
					</div>
                </td>
            </tr>
        	</c:forEach> 
        </table>
    </div>
</kul:tabTop>
