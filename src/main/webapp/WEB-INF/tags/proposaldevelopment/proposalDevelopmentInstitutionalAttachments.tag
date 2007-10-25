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
<c:set var="textAreaFieldName" value="newInstitute.moduleTitle" />
<c:set var="action" value="proposalDevelopmentAbstractsAttachments" />
<c:set var="lineNum" value="1"/>	
<kul:tab tabTitle="Institutional Attachments" defaultOpen="true" tabErrorKey="document.institutes*,newInstitute*">
	<div class="tab-container" align="center">
    	<div class="h2-container">
    		<span class="subhead-left"><h2>Add Institutional Attachments</h2></span>
    		<span class="subhead-right"><kul:help businessObjectClassName="fillMeIn" altText="help"/></span>
        </div>
        
        <table cellpadding=0 cellspacing=0 summary="">
          	<tr>
          	    <th><div align="left">&nbsp</div></th> 
          		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.updateTimestamp}" skipHelpUrl="true" noColon="true" /></div></th>
          		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.updateUser}" skipHelpUrl="true" noColon="true" /></div></th>
          		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.narrativeTypeCode}" skipHelpUrl="true" noColon="true" /></div></th>
          		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.moduleTitle}" skipHelpUrl="true" noColon="true" /></div></th>
          		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.fileName}" skipHelpUrl="true" noColon="true" /></div></th>
              	<kul:htmlAttributeHeaderCell literalLabel="Action" scope="col"/>
	  			             		
          	</tr>        
          	<tr>
				<th class="infoline">
					<c:out value="Add:" />
				</th>

                <td class="infoline">                
                	<kul:htmlControlAttribute property="newInstitute.updateTimestamp" attributeEntry="${narrativeAttributes.updateTimestamp}" readOnly="true" />	            
				</td>
                <td class="infoline">
                	<kul:htmlControlAttribute property="newInstitute.updateUser" attributeEntry="${narrativeAttributes.updateUser}" readOnly="true" />
                </td>
                <td class="infoline">                	
                	<kul:htmlControlAttribute property="newInstitute.institutionalAttachmentTypeCode" attributeEntry="${narrativeAttributes.institutionalAttachmentTypeCode}"  styleClass="fixed-size-select"/>
				</td>
                <td class="infoline">
                	<kul:htmlControlAttribute property="newInstitute.moduleTitle" attributeEntry="${narrativeAttributes.moduleTitle}" />
                    <kra:expandedTextArea textAreaFieldName="${textAreaFieldName}" action="${action}" textAreaLabel="${narrativeAttributes.moduleTitle.label}" />
				</td>
                
                <td class="infoline">
                	<html:file property="newInstitute.narrativeFile" />
                </td>
				<td class="infoline">
					<div align=center>
						<html:image property="methodToCall.addInstitutionalAttachment.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' />
					</div>
                </td>
            </tr>

        	<c:forEach var="instituteAttachment" items="${KualiForm.document.narratives}" varStatus="status">
        	  <c:if test="${instituteAttachment.narrativeType.narrativeTypeGroup eq ProposalDevelopmentParameters.instituteNarrativeTypeGroup.parameterValue}">
	             <tr>
					<th class="infoline" align="right">
						${lineNum}:
						<c:set var="lineNum" value="${lineNum+1}" />
					</th>
	                <td>
                	    <kul:htmlControlAttribute property="document.narratives[${status.index}].updateTimestamp" readOnly="true" attributeEntry="${narrativeAttributes.updateTimestamp}" />
					</td>
	                <td>
                	    <kul:htmlControlAttribute property="document.narratives[${status.index}].updateUser" readOnly="true" attributeEntry="${narrativeAttributes.updateUser}" />
	                </td>
	                <td>                	
                	    <input type="hidden" name="document.narratives[${status.index}].institutionalAttachmentTypeCode" value="${instituteAttachment.institutionalAttachmentTypeCode}" />
                         ${instituteAttachment.narrativeType.description}	                </td>
                	    <!-- <kul:htmlControlAttribute property="document.narratives[${status.index}].narrativeType.description" readOnly="true" attributeEntry="${narrativeAttributes.narrativeType.description}" /> -->
					</td>
	                <td>
	                	<kul:htmlControlAttribute property="document.narratives[${status.index}].moduleTitle" readOnly="true" attributeEntry="${narrativeAttributes.moduleTitle}" />
					</td>
	                <td>
	                    <kul:htmlControlAttribute property="document.narratives[${status.index}].fileName" readOnly="true" attributeEntry="${narrativeAttributes.fileName}" />
	                </td>
	                <td>
					<div align=center>
						<html:image property="methodToCall.viewInstitutionalAttachment.line${status.index}.anchor${currentTabIndex}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' 
									onclick="javascript: openNewWindow('proposalDevelopmentAbstractsAttachments','viewInstitutionalAttachment',${status.index}); return false" />
						<html:image property="methodToCall.deleteInstitutionalAttachment.line${status.index}.anchor${currentTabIndex}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' />
					</div>
	                </td>
	            </tr>
			  </c:if>
        	</c:forEach>        

          	
        </table>

    </div>

</kul:tab>
