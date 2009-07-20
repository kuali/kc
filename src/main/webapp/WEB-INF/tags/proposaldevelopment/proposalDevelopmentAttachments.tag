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

<%@ attribute name="htmlFormAction" required="false" %>
<%@ attribute name="renderMultipart" required="false" %>

<%! static private final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(org.kuali.kra.proposaldevelopment.bo.Narrative.class); %>
<% long startTime = 0; %>
<% long endTime = 0; %>

    
           <% startTime = System.currentTimeMillis(); %>
<c:set var="readOnly" value="${not KualiForm.editingMode['addNarratives']}" scope="request" />

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.ProposalDevelopmentDocument.attributes}" />
<c:set var="narrativeAttributes" value="${DataDictionary.Narrative.attributes}" />
<c:set var="narrativeAttachmentAttributes" value="${DataDictionary.NarrativeAttachment.attributes}" />

<c:set var="action" value="proposalDevelopmentAbstractsAttachments" />
<kul:tabTop tabTitle="Proposal Attachments (${fn:length(KualiForm.document.narratives)})" defaultOpen="false" tabErrorKey="newNarrative*,document.narrative*">
	<div class="tab-container" align="center">
	    <kra:section permission="addNarratives">
	    	<h3>
	    		<span class="subhead-left">Add Proposal Attachments</span>
	    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.proposaldevelopment.bo.Narrative" altText="help"/></span>
	        </h3>
        </kra:section>
        <table cellpadding=0 cellspacing=0 summary="">
            <kra:section permission="addNarratives">
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
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
						</div>
	                </td>
	            </tr>
            </kra:section>
            
 
            <c:if test="${fn:length(KualiForm.document.narratives) > 0}">
            <tr>
            	<td colspan="4">
            	<div  align="left">
            	
            	
        	<c:forEach var="narrative" items="${KualiForm.document.narratives}" varStatus="status">
        	<c:if test="${narrative.narrativeType.narrativeTypeGroup eq KualiForm.proposalDevelopmentParameters['proposalNarrativeTypeGroup'].parameterValue}">
			<c:set var="narrType" value="${narrative.narrativeType.description}"/>
			<c:set var="narrStatus" value="${narrative.narrativeStatus.description}"/>
			<c:set var="downloadKey" value="proposalAttachment.${narrative.moduleNumber}.download" />
			<c:set var="downloadAttachment" value="${KualiForm.editingMode[downloadKey]}" />
			<c:set var="replaceKey" value="proposalAttachment.${narrative.moduleNumber}.replace" />
			<c:set var="replaceAttachment" value="${KualiForm.editingMode[replaceKey]}" />
			<c:set var="deleteKey" value="proposalAttachment.${narrative.moduleNumber}.delete" />
            <c:set var="deleteAttachment" value="${KualiForm.editingMode[deleteKey]}" />
			<kul:innerTab parentTab="Proposal Attachments" defaultOpen="false" tabDescription="${narrType} - ${narrStatus}" tabTitle="${status.index+1}. ${narrType} - ${narrStatus}" auditCluster="proposalAttachmentsAuditWarnings" tabAuditKey="document.narrative[${status.index}]*">
				<div class="innerTab-container" align="left">
					<table class=tab cellpadding=0 cellspacing=0 summary="">
			          	<tr>
			          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.narrativeTypeCode}" /></div></th>
			                <td align="left" valign="middle">
			                	<kul:htmlControlAttribute property="document.narrative[${status.index}].narrativeType.description" attributeEntry="${narrativeAttributes.narrativeType.description}" readOnly="true" />
							</td>
			          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttachmentAttributes.fileName}"/></div></th>
			                <td align="left" valign="middle">
			                <%-- %><c:if test="${(!empty narrative.fileName)}">--%>
				                <div id="replaceDiv${status.index}" style="display:block;">
					                <kul:htmlControlAttribute property="document.narrative[${status.index}].fileName" 
					                	 readOnly="true" attributeEntry="${narrativeAttributes.fileName}" />
				                </div>
				                <div id="fileDiv${status.index}" valign="middle" style="display:none;">
				                	<html:file property="document.narrative[${status.index}].narrativeFile" />
									<html:image property="methodToCall.replaceProposalAttachment.line${status.index}.anchor${currentTabIndex}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
								</div>
				            <%-- </c:if> --%>
							</td>
			          	</tr>
			          	<tr>
			          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.moduleStatusCode}"/></div></th>
			                <td align="left" valign="middle">
			                	<kul:htmlControlAttribute property="document.narrative[${status.index}].moduleStatusCode" attributeEntry="${narrativeAttributes.moduleStatusCode}" />
							</td>
			          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.contactName}" /></div></th>
			                <td align="left" valign="middle">
			                	<kul:htmlControlAttribute property="document.narrative[${status.index}].contactName" attributeEntry="${narrativeAttributes.contactName}" />
							</td>
			          	</tr>
			          	<tr>
			          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.updateUser}" /></div></th>
			                <td align="left" valign="middle">
			                	<kul:htmlControlAttribute property="document.narrative[${status.index}].uploadUserDisplay" readOnly="true" attributeEntry="${narrativeAttributes.updateUser}" />
							</td>
			          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.emailAddress}" /></div></th>
			                <td align="left" valign="middle">
			                	<kul:htmlControlAttribute property="document.narrative[${status.index}].emailAddress" attributeEntry="${narrativeAttributes.emailAddress}" />
							</td>
			          	</tr>
			          	<tr>
			          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.updateTimestamp}" /></div></th>
			                <td align="left" valign="middle">
			                	<kul:htmlControlAttribute property="document.narrative[${status.index}].timestampDisplay" readOnly="true" attributeEntry="${narrativeAttributes.updateTimestamp}" />
							</td>
			          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.phoneNumber}" /></div></th>
			                <td align="left" valign="middle">
			                	<kul:htmlControlAttribute property="document.narrative[${status.index}].phoneNumber" attributeEntry="${narrativeAttributes.phoneNumber}" />
							</td>
			          	</tr>
			          	<tr>
			          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.comments}" /></div></th>
			                <td align="left" valign="middle">
			                	<kul:htmlControlAttribute property="document.narrative[${status.index}].comments" attributeEntry="${narrativeAttributes.comments}" />
			                	<c:set var="textAreaFieldName" value="document.narrative[${status.index}].comments" />
			                	<kra:expandedTextArea textAreaFieldName="${textAreaFieldName}" action="${action}" textAreaLabel="${narrativeAttributes.moduleTitle.label}" />
							</td>
			          		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${narrativeAttributes.moduleTitle}" /></div></th>
			                <td align="left" valign="middle">
			                	<kul:htmlControlAttribute property="document.narrative[${status.index}].moduleTitle" attributeEntry="${narrativeAttributes.moduleTitle}" />
			                	<c:set var="textAreaFieldName" value="document.narrative[${status.index}].moduleTitle" />
			                	<kra:expandedTextArea textAreaFieldName="${textAreaFieldName}" action="${action}" textAreaLabel="${narrativeAttributes.moduleTitle.label}" />
							</td>
			          	</tr>
			          	<tr>
							<td colspan=4>
								<div align="center">
									<c:if test="${(downloadAttachment) }">							
										<html:image styleId="downloadProposalAttachment.line${status.index}"  property="methodToCall.downloadProposalAttachment.line${status.index}.anchor${currentTabIndex}"
														src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton"
														onclick="javascript: openNewWindow('${action}','downloadProposalAttachment','${status.index}',${KualiForm.formKey},'${KualiForm.document.sessionDocument}'); return false" />
									</c:if>
									<c:if test="${(replaceAttachment) }">							
										<html:image styleId="replaceProposalAttachment.line${status.index}" 
														onclick="javascript: showHide('fileDiv${status.index}','replaceDiv${status.index}') ; return false"  
														src='${ConfigProperties.kra.externalizable.images.url}tinybutton-replace.gif' styleClass="tinybutton"
														property="methodToCall.replaceNarrativeAttachment.line${status.index}.anchor${currentTabIndex};return false" />
			
								    </c:if>	
								    <c:if test="${deleteAttachment}">
										<html:image styleId="deleteProposalAttachment.line${status.index}" property="methodToCall.deleteProposalAttachment.line${status.index}.anchor${currentTabIndex}"
										            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
									</c:if>
									<html:image styleId="getProposalAttachmentRights.line${status.index}" property="methodToCall.getProposalAttachmentRights.line${status.index}.anchor${currentTabIndex}"
										        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-vieweditrights.gif' styleClass="tinybutton"
										        onclick="javascript: proposalAttachmentRightsPop('${status.index}',${KualiForm.formKey},'${KualiForm.document.sessionDocument}');return false"/>
										        
	        
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
        	</c:if>
        	
            
        </table>
    </div>
</kul:tabTop>
<% endTime = System.currentTimeMillis();
               LOG.info("JSP Narrative Time = " + (endTime - startTime)); %>
