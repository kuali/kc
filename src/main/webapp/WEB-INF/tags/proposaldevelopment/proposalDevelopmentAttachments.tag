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

<c:set var="action" value="proposalDevelopmentAbstractsAttachments" />
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
                	<html:file property="document.newNarrative.narrativeFile" />
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
                	<c:set var="textAreaFieldName" value="document.newNarrative.comments" />
                	<kul:htmlControlAttribute property="document.newNarrative.comments" attributeEntry="${narrativeAttributes.comments}" />
                    <html:image property="methodToCall.updateTextArea.((#${textAreaFieldName}:${action}:${narrativeAttributes.comments.label}#)).anchor${currentTabIndex}" src='${ConfigProperties.kra.externalizable.images.url}pencil_add.png' onclick="javascript: textAreaPop(document.getElementById('${textAreaFieldName}').value,'${textAreaFieldName}','proposalDevelopment','${narrativeAttributes.comments.label}');return false"  styleClass="tinybutton"/>
				</td>
          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.moduleTitle}" skipHelpUrl="true"  /></div></th>
                <td align="left" valign="middle">
                	<c:set var="textAreaFieldName" value="document.newNarrative.moduleTitle" />
                	<kul:htmlControlAttribute property="document.newNarrative.moduleTitle" attributeEntry="${narrativeAttributes.moduleTitle}" />
                    <html:image property="methodToCall.updateTextArea.((#${textAreaFieldName}:${action}:${narrativeAttributes.comments.label}#)).anchor${currentTabIndex}" src='${ConfigProperties.kra.externalizable.images.url}pencil_add.png' onclick="javascript: textAreaPop(document.getElementById('${textAreaFieldName}').value,'${textAreaFieldName}','proposalDevelopment','${narrativeAttributes.comments.label}');return false"  styleClass="tinybutton"/>
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
        	<c:forEach var="narrativeAttachment" items="${KualiForm.document.narratives}" varStatus="status">
        	
			<c:set var="narrType" value="${narrativeAttachment.narrativeType.description}"/>
			<c:set var="narrStatus" value="${narrativeAttachment.narrativeStatus.description}"/>
			<kul:innerTab parentTab="Proposal Attachments" defaultOpen="false" tabDescription="${narrType} - ${narrStatus}" tabTitle="${status.index+1}. ${narrType} - ${narrStatus}">
				<div class="tab-container" align="center">
					<table cellpadding=0 cellspacing=0 summary="">
			          	<tr>
			          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.narrativeTypeCode}" skipHelpUrl="true" /></div></th>
			                <td align="left" valign="middle">
			                	<kul:htmlControlAttribute property="document.narratives[${status.index}].narrativeTypeCode" attributeEntry="${narrativeAttributes.narrativeTypeCode}" />
							</td>
			          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttachmentAttributes.fileName}" skipHelpUrl="true" /></div></th>
			                <td align="left" valign="middle">
			                
			                <c:set var="narrAttList" value="${narrativeAttachment.narrativeAttachmentList}"></c:set>
			                <c:if test="${fn:length(narrAttList) >0}">
			                	
				                <div id="replaceDiv${status.index}" style="display:block;">
				                <kul:htmlControlAttribute property="document.narratives[${status.index}].fileName" readOnly="true" attributeEntry="${narrativeAttributes.fileName}" />
				                <%--<html:link onclick="javascript: window.open(extractUrl()+'/${action}.do?methodToCall=downloadProposalAttachment&line=${status.index}'); return true" href="" anchor="${currentTabIndex}" property="methodToCall.downloadProposalAttachment.line${status.index}">download</html:link>--%>
				                <html:link onclick="javascript: openNewWindow('${action}','downloadProposalAttachment','${status.index}'); return true" href="" anchor="${currentTabIndex}" property="methodToCall.downloadProposalAttachment.line${status.index}">download</html:link>
				                <html:link onclick="javascript: showHide('fileDiv${status.index}','replaceDiv${status.index}')" href="" anchor="${currentTabIndex}" property="methodToCall.downloadProposalAttachment.line${status.index}">replace</html:link>
				                </div>
				                <div id="fileDiv${status.index}" style="display:none;">
				                	<html:file property="document.narratives[${status.index}].narrativeFile" />
									<html:image property="methodToCall.replaceProposalAttachment.line${status.index}.anchor${currentTabIndex}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' />
								</div>
				               
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
							</td>
			          	</tr>
			          	<tr>
							<td colspan=4>
								<div align="center">
									<html:image property="methodToCall.deleteProposalAttachment.line${status.index}.anchor${currentTabIndex}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' />
									<html:image property="methodToCall.getProposalAttachmentRights.line${status.index}.anchor${currentTabIndex}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-vieweditrights.gif' 
									onclick="javascript: proposalAttachmentRightsPop('${status.index}');return false"/>
								</div>
			                </td>
			            </tr>
			            </table>
			            </div>
			            </kul:innerTab>
        	</c:forEach> 
        	</div>
        	</td>
        	</tr>
        </table>
    </div>
</kul:tabTop>
