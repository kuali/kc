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

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.ProposalDevelopmentDocument.attributes}" />
<c:set var="narrativeAttributes" value="${DataDictionary.Narrative.attributes}" />
<c:set var="narrativeAttachmentAttributes" value="${DataDictionary.NarrativeAttachment.attributes}" />
<c:set var="instituteAttachmentAttributes" value="${DataDictionary.InstituteAttachmentType.attributes}" />
<c:set var="textAreaFieldName" value="newInstitute.comments" />
<c:set var="action" value="proposalDevelopmentAbstractsAttachments" />
<kul:tab tabTitle="Institutional Attachments" defaultOpen="true" tabErrorKey="">
	<div class="tab-container" align="center">
    	<div class="h2-container">
    		<span class="subhead-left"><h2>Institutional Attachments</h2></span>
    		<span class="subhead-right"><kul:help businessObjectClassName="fillMeIn" altText="help"/></span>
        </div>
        
        <table cellpadding=0 cellspacing=0 summary="">
          	<tr>
          	    <th><div align="left">&nbsp</div></th> 
          		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.updateTimestamp}" skipHelpUrl="true" noColon="true" /></div></th>
          		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.updateUser}" skipHelpUrl="true" noColon="true" /></div></th>
          		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.moduleStatusCode}" skipHelpUrl="true" noColon="true" /></div></th>
          		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.narrativeTypeCode}" skipHelpUrl="true" noColon="true" /></div></th>
          		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.comments}" skipHelpUrl="true" noColon="true" /></div></th>
          		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${narrativeAttachmentAttributes.fileName}" skipHelpUrl="true" noColon="true" /></div></th>
              	<kul:htmlAttributeHeaderCell literalLabel="Action" scope="col"/>
	  			             		
          	</tr>        
          	<tr>
				<th class="infoline">
					<c:out value="Add:" />
				</th>

                <td align="left" valign="middle">                
                	<kul:htmlControlAttribute property="newInstitute.updateTimestamp" attributeEntry="${narrativeAttributes.updateTimestamp}" readOnly="true" />	            
				</td>
                <td>
                	<kul:htmlControlAttribute property="newInstitute.updateUser" attributeEntry="${narrativeAttributes.updateUser}" readOnly="true" />
                </td>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="newInstitute.moduleStatusCode" attributeEntry="${narrativeAttributes.moduleStatusCode}" />
				</td>
                <td>                	
                	<kul:htmlControlAttribute property="newInstitute.institutionalAttachmentTypeCode" attributeEntry="${narrativeAttributes.institutionalAttachmentTypeCode}" />
				</td>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="newInstitute.comments" attributeEntry="${narrativeAttributes.comments}" />
                    <html:image property="methodToCall.updateTextArea.((#${textAreaFieldName}:${action}:${narrativeAttributes.comments.label}#)).anchor${currentTabIndex}" src='${ConfigProperties.kra.externalizable.images.url}pencil_add.png' onclick="javascript: textAreaPop(document.getElementById('${textAreaFieldName}').value,'${textAreaFieldName}','proposalDevelopment','${narrativeAttributes.comments.label}');return false" styleClass="tinybutton" /> 
				</td>
                
                <td align="left" valign="middle">
                	<html:file property="newInstitute.narrativeFile" />
                </td>
				<td>
					<div align=center>
						<html:image property="methodToCall.addInstitutionalAttachment.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' />
					</div>
                </td>
            </tr>

        	<c:forEach var="institute" items="${KualiForm.document.institutes}" varStatus="status">
	             <tr>
					<th class="infoline">
						<c:out value="${status.index+1}" />
					</th>
	                <td align="left" valign="middle">
                	    <kul:htmlControlAttribute property="document.institutes[${status.index}].updateTimestamp" readOnly="true" attributeEntry="${narrativeAttributes.updateTimestamp}" />
					</td>
	                <td>
                	    <kul:htmlControlAttribute property="document.institutes[${status.index}].updateUser" readOnly="true" attributeEntry="${narrativeAttributes.updateUser}" />
	                </td>
	                <td>
                	    <kul:htmlControlAttribute property="document.institutes[${status.index}].moduleStatusCode" readOnly="true" attributeEntry="${narrativeAttributes.moduleStatusCode}" />
	                </td>
	                <td>                	
                	    <input type="hidden" name="document.institutes[${status.index}].institutionalAttachmentTypeCode" value="${institute.institutionalAttachmentTypeCode}" />
                	    <kul:htmlControlAttribute property="document.institutes[${status.index}].narrativeType.description" readOnly="true" attributeEntry="${narrativeAttributes.narrativeType.description}" />
					</td>
	                <td align="left" valign="middle">
	                	<kul:htmlControlAttribute property="document.institutes[${status.index}].comments" readOnly="true" attributeEntry="${narrativeAttributes.comments}" />
					</td>
	                <td align="left" valign="middle">
	                    <kul:htmlControlAttribute property="document.institutes[${status.index}].narrativeAttachmentList[0].fileName" readOnly="true" attributeEntry="${instituteAttachmentAttributes.fileName}" />
	                </td>
	                <td align="left" valign="middle">
					<div align=center>
						<html:image property="methodToCall.viewInstitutionalAttachment.line${status.index}.anchor${currentTabIndex}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' 
									onclick="javascript: openNewWindow('proposalDevelopmentAbstractsAttachments','viewInstitutionalAttachment',${status.index}); return false" />
						<html:image property="methodToCall.deleteInstitutionalAttachment.line${status.index}.anchor${currentTabIndex}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' />
					</div>
	                </td>
	            </tr>
        	</c:forEach>        

          	
        </table>

    </div>

</kul:tab>
