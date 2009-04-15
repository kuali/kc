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
<c:set var="readOnly" value="${not KualiForm.editingMode['addNarratives']}" scope="request" />

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.ProposalDevelopmentDocument.attributes}" />
<c:set var="narrativeAttributes" value="${DataDictionary.Narrative.attributes}" />
<c:set var="textAreaFieldName" value="newInstituteAttachment.moduleTitle" />
<c:set var="action" value="proposalDevelopmentAbstractsAttachments" />
<c:set var="label" value="Internal Attachments" />

<kul:tab tabTitle="${label} (${fn:length(KualiForm.document.instituteAttachments)})" defaultOpen="false" tabErrorKey="document.instituteAttachment*,newInstituteAttachment*">
	<div class="tab-container" align="center">
        <c:set var="sectionLabel" value="Internal Attachments" />
	   
	    <kra:section permission="addNarratives">
	    	<c:set var="sectionLabel" value="Add ${sectionLabel}" />
        </kra:section>
        
        <div class="h2-container">
	    	<span class="subhead-left"><h2>${sectionLabel}</h2></span>
	    	<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.proposaldevelopment.bo.Narrative" altText="help"/></span>
	    </div>
        
        <table cellpadding=0 cellspacing=0 summary="">
            <c:if test="${fn:length(KualiForm.document.instituteAttachments) > 0  || KualiForm.editingMode['addNarratives']}" >
	        <tr>
	          	<th><div align="left">&nbsp</div></th> 
	            <th><div align="left"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.updateTimestamp}" noColon="true" /></div></th>
	          	<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.updateUser}" noColon="true" /></div></th>
	          	<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.institutionalAttachmentTypeCode}" noColon="true" /></div></th>
	          	<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.moduleTitle}" noColon="true" /></div></th>
	          	<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.fileName}" noColon="true" /></div></th>
	            <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/> 		
	        </tr>  
	        </c:if>
	          
	        <kra:section permission="addNarratives">    
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
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
						</div>
	                </td>
	            </tr>
            </kra:section>

			<c:if test="${fn:length(KualiForm.document.instituteAttachments) > 0}" >
        	<c:forEach var="instituteAttachment" items="${KualiForm.document.instituteAttachments}" varStatus="status">
        	    <c:set var="downloadKey" value="instituteAttachment.${instituteAttachment.moduleNumber}.download" />
                <c:set var="downloadAttachment" value="${KualiForm.editingMode[downloadKey]}" />
                <c:set var="replaceKey" value="instituteAttachment.${instituteAttachment.moduleNumber}.replace" />
                <c:set var="replaceAttachment" value="${KualiForm.editingMode[replaceKey]}" />
                <c:set var="deleteKey" value="instituteAttachment.${instituteAttachment.moduleNumber}.delete" />
                <c:set var="deleteAttachment" value="${KualiForm.editingMode[deleteKey]}" />
                
	             <tr>
					<th class="infoline" align="right">
						${status.index + 1}:
					</th>
	                 <td class=>                
                	<kul:htmlControlAttribute property="document.instituteAttachments[${status.index}].timestampDisplay" attributeEntry="${narrativeAttributes.updateTimestamp}" readOnly="true" />	            
				</td>
                <td >
                	<kul:htmlControlAttribute property="document.instituteAttachments[${status.index}].uploadUserDisplay" attributeEntry="${narrativeAttributes.updateUser}" readOnly="true" />
                </td>
                <td class=>      
                    ${instituteAttachment.narrativeType.description} 
               	</td>
                <td class=>
                     ${instituteAttachment.moduleTitle}
                </td>
	                <td>
	                    <div id="replaceInstDiv${status.index}" style="display:block;">
					       <kul:htmlControlAttribute property="document.instituteAttachments[${status.index}].fileName" 
					       		readOnly="true" attributeEntry="${narrativeAttributes.fileName}" />     
				        </div>
				        <div id="instFileDiv${status.index}" valign="middle" style="display:none;">
				           	<html:file property="document.instituteAttachments[${status.index}].narrativeFile" />
							<html:image property="methodToCall.replaceInstituteAttachment.line${status.index}.anchor${currentTabIndex}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
						</div>
	                </td>
	                <td>
					<div align=center>
					<c:if test="${!empty instituteAttachment.fileName}" >
							<c:if test="${(downloadAttachment) }">							
								<html:image styleId="viewInstituteAttachment.line${status.index}"  property="methodToCall.downloadInstituteAttachment.line${status.index}.anchor${currentTabIndex}"
												src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton"
												onclick="javascript: openNewWindow('${action}','downloadInstituteAttachment','${status.index}',${KualiForm.formKey},'${KualiForm.document.sessionDocument}'); return false" />
							</c:if>
							<c:if test="${(replaceAttachment) }">							
								<html:image styleId="replaceInstituteAttachment.line${status.index}" 
												onclick="javascript: showHide('instFileDiv${status.index}','replaceInstDiv${status.index}') ; return false"  
												src='${ConfigProperties.kra.externalizable.images.url}tinybutton-replace.gif' styleClass="tinybutton"
												property="methodToCall.replaceInstituteAttachment.line${status.index}.anchor${currentTabIndex};return false" />
	
						    </c:if>	
						    <c:if test="${deleteAttachment}">
								<html:image property="methodToCall.deleteInstitutionalAttachment.line${status.index}.anchor${currentTabIndex}"
									        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
						    </c:if>
						
						<html:image styleId="getInstituteAttachmentRights.line${status.index}" property="methodToCall.getInstituteAttachmentRights.line${status.index}.anchor${currentTabIndex}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-vieweditrights.gif' styleClass="tinybutton"
										onclick="javascript: proposalInstituteAttachmentRightsPop('${status.index}',${KualiForm.formKey},'${KualiForm.document.sessionDocument}');return false"/>
							 

						</c:if>	 
					</div>
	                </td>
	            </tr>
        	</c:forEach>        
			</c:if>
          	
        </table>
		<c:set var="sectionLabel" value="" />
    </div>

</kul:tab>
