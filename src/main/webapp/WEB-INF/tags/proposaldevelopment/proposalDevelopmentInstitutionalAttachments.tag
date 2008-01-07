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

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.ProposalDevelopmentDocument.attributes}" />
<c:set var="narrativeAttributes" value="${DataDictionary.Narrative.attributes}" />
<c:set var="textAreaFieldName" value="newInstituteAttachment.moduleTitle" />
<c:set var="action" value="proposalDevelopmentAbstractsAttachments" />


<kul:tab tabTitle="Internal Attachments (${fn:length(KualiForm.document.instituteAttachments)})" defaultOpen="true" tabErrorKey="document.instituteAttachment*,newInstituteAttachment*">
	<div class="tab-container" align="center">
    	<div class="h2-container">
    		<span class="subhead-left"><h2>Add Internal Attachments</h2></span>
    		<span class="subhead-right"><kul:help businessObjectClassName="fillMeIn" altText="help"/></span>
        </div>
        
        <table cellpadding=0 cellspacing=0 summary="">
          	<tr>
          	    <th><div align="left">&nbsp</div></th> 
          		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.updateTimestamp}" noColon="true" /></div></th>
          		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.updateUser}" noColon="true" /></div></th>
          		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.institutionalAttachmentTypeCode}" noColon="true" /></div></th>
          		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.moduleTitle}" noColon="true" /></div></th>
          		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.fileName}" noColon="true" /></div></th>
              	<kul:htmlAttributeHeaderCell literalLabel="Action" scope="col"/>
	  			             		
          	</tr>        
          	<tr>
				<th class="infoline">
					<c:out value="Add:" />
				</th>

                <td class="infoline">                
                	<kul:htmlControlAttribute property="newInstituteAttachment.updateTimestamp" attributeEntry="${narrativeAttributes.updateTimestamp}" readOnly="true" />	            
				</td>
                <td class="infoline">
                	<kul:htmlControlAttribute property="newInstituteAttachment.updateUser" attributeEntry="${narrativeAttributes.updateUser}" readOnly="true" />
                </td>
                <td class="infoline">                	
                	<kul:htmlControlAttribute property="newInstituteAttachment.institutionalAttachmentTypeCode" attributeEntry="${narrativeAttributes.institutionalAttachmentTypeCode}"  styleClass="fixed-size-select"/>
				</td>
                <td class="infoline">
                	<kul:htmlControlAttribute property="newInstituteAttachment.moduleTitle" attributeEntry="${narrativeAttributes.moduleTitle}" />
                    <kra:expandedTextArea textAreaFieldName="${textAreaFieldName}" action="${action}" textAreaLabel="${narrativeAttributes.moduleTitle.label}" />
				</td>
                
                <td class="infoline">
                	<html:file property="newInstituteAttachment.narrativeFile" />
                </td>
				<td class="infoline">
					<div align=center>
						<html:image property="methodToCall.addInstitutionalAttachment.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' />
					</div>
                </td>
            </tr>

        	<c:forEach var="instituteAttachment" items="${KualiForm.document.instituteAttachments}" varStatus="status">
	             <tr>
					<th class="infoline" align="right">
						${status.index + 1}:
					</th>
	                <td>
                	    <kul:htmlControlAttribute property="document.instituteAttachment[${status.index}].updateTimestamp" readOnly="true" attributeEntry="${narrativeAttributes.updateTimestamp}" />
					</td>
	                <td>
                	    ${instituteAttachment.authorPersonName}
	                </td>
	                <td>                	
                         ${instituteAttachment.narrativeType.description}	                
					</td>
	                <td>
	                    ${instituteAttachment.moduleTitle}
					</td>
	                <td>
	                    ${instituteAttachment.fileName}
	                </td>
	                <td>
					<div align=center>
					    <c:if test="${!empty instituteAttachment.fileName}" >
						   <html:image property="methodToCall.viewInstitutionalAttachment.line${status.index}.anchor${currentTabIndex}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' 
									onclick="javascript: openNewWindow('proposalDevelopmentAbstractsAttachments','viewInstitutionalAttachment',${status.index},${KualiForm.formKey},'${KualiForm.document.sessionDocument}'); return false" />
						</c:if>			
						<html:image property="methodToCall.deleteInstitutionalAttachment.line${status.index}.anchor${currentTabIndex}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' />
					</div>
	                </td>
	            </tr>
        	</c:forEach>        

          	
        </table>

    </div>

</kul:tab>
