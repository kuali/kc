<%--
 Copyright 2005-2010 The Kuali Foundation

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

<jsp:useBean id="paramMap1" class="java.util.HashMap"/>

<c:set var="numberOfAttachments" value="0" />
<c:set var="modify" value="${KualiForm.coiNotesAndAttachmentsHelper.modifyAttachments}" />
<c:set var="attributes" value="${DataDictionary.CoiDisclosureAttachment.attributes}" />
<c:set var="attachmentFileAttributes" value="${DataDictionary.AttachmentFile.attributes}" />
<c:set var="attachmentFilterAttributes" value="${DataDictionary.CoiDisclosureAttachmentFilter.attributes}" />
<c:set var="attachmentHelper" value="${KualiForm.coiNotesAndAttachmentsHelper}" />
<c:set var="coiDisclosureAttachments" value="${KualiForm.document.coiDisclosureList[0].coiDisclosureAttachments}"/>
<c:set var="filteredAttachments" value="${KualiForm.document.coiDisclosureList[0].filteredAttachments}"/>
<c:set target="${paramMap1}" property = "projectId" value = "${coiNotesAndAttachmentsHelper.newCoiDisclosureAttachment.projectId}"/>
<c:set var="tabItemCount" value="0" />

<c:forEach var="coiDisclosureAttachment" items="${KualiForm.document.coiDisclosure.coiDisclosureAttachments}" varStatus="status">
        <c:set var="tabItemCount" value="${tabItemCount+1}" />
</c:forEach>

<kul:tab tabTitle="Attachments" tabItemCount="${tabItemCount}" defaultOpen="false" >
	<div class="tab-container" align="center">
	<kra:permission value="${modify}">
	<h3>
	<span class="subhead-left">Attachments</span> 
	<spanclass="subhead-right"> </span>
	</h3>
	<table cellpadding="4" cellspacing="0" summary="">	
	         	<tr>
					<th>
						<div align="right">
	         				<kul:htmlAttributeLabel attributeEntry="${attributes.contactName}" noColon="false"/>
						</div>
					</th>
	         		<td align="left" valign="middle">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="coiNotesAndAttachmentsHelper.newCoiDisclosureAttachment.contactName" attributeEntry="${attributes.contactName}" />
	                	
		            	</div>
					</td>
					
					<th>
							<div align="right">
	         				<kul:htmlAttributeLabel attributeEntry="${attributes.projectId}" noColon="false"/>
	         				</div>
					</th>
					<td>
	                <html:select property="coiNotesAndAttachmentsHelper.newCoiDisclosureAttachment.projectId" tabindex="0" disabled="${readOnly}" >                                              
					
					<c:forEach items="${krafn:getOptionList('org.kuali.kra.coi.lookup.keyvalue.CoiDisclosureProjectValuesFinder', paramMap1)}" var="option">
	                <c:choose>                    	
	                	<c:when test="${coiNotesAndAttachmentsHelper.newCoiDisclosureAttachment.projectId == option.key}">
	                        <option value="${option.key}" selected>${option.label}</option>
	                    </c:when>
	                    <c:otherwise>
	                        <c:out value="${option.label}"/>
	                        <option value="${option.key}">${option.label}</option>
	                    </c:otherwise>
	                </c:choose>         
	            </c:forEach>
	            </html:select>
					</td>
					
					
					<th>
							<div align="right">
	         				<kul:htmlAttributeLabel attributeEntry="${attributes.entityNumber}" noColon="false"/>
	         				</div>
					</th>
					<td>        					
						<kul:htmlControlAttribute property="coiNotesAndAttachmentsHelper.newCoiDisclosureAttachment.entityNumber" attributeEntry="${attributes.entityNumber}"/>                                              
					</td>
					
					
	         	</tr>
	         	<tr>
	         		<th>
	         			<div align="right">
			         		<kul:htmlAttributeLabel attributeEntry="${attributes.updateUser}" noColon="false" />
	         			</div>
	         		</th>
	         		<td align="left" valign="middle">
	                	<div align="left">
	             			<kul:htmlControlAttribute property="coiNotesAndAttachmentsHelper.newCoiDisclosureAttachment.updateUser" attributeEntry="${attributes.updateUser}" readOnly="true"/>
	                	
		            	</div>
					</td>
					<th>
						<div align="right">
	         				<kul:htmlAttributeLabel attributeEntry="${attributes.contactEmailAddress}" noColon="false"/>
						</div>
					</th>
	         			<td align="left" valign="middle" colspan="3">
	         		    	<kul:htmlControlAttribute property="coiNotesAndAttachmentsHelper.newCoiDisclosureAttachment.contactEmailAddress" attributeEntry="${attributes.contactEmailAddress}" />
	         			
	                	<div align="left">
		            	</div>
					</td>
	         	</tr>
	         	<tr>
	         		<th>
	         			<div align="right">
			         		<kul:htmlAttributeLabel attributeEntry="${attributes.updateTimestamp}" noColon="false" />
	         			</div>
	         		</th>
	         		<td align="left" valign="middle">
	                	<div align="left">
	            			<kul:htmlControlAttribute property="coiNotesAndAttachmentsHelper.newCoiDisclosureAttachment.updateTimestamp" attributeEntry="${attributes.updateTimestamp}" readOnly="true"/>  
	                	
		            	</div>
					</td>
					<th>
						<div align="right">
	         				<kul:htmlAttributeLabel attributeEntry="${attributes.contactPhoneNumber}" noColon="false"/>
						</div>
					</th>
	         		<td align="left" valign="middle" colspan="3">
	                	<div align="left">
	                    	<kul:htmlControlAttribute property="coiNotesAndAttachmentsHelper.newCoiDisclosureAttachment.contactPhoneNumber" attributeEntry="${attributes.contactPhoneNumber}" />
	                	
		            	</div>
					</td>
	         	</tr>
	         	<tr>
	         		<th>
	         			<div align="right">
	         				<kul:htmlAttributeLabel attributeEntry="${attributes.comments}" noColon="false"/>
	         			</div>
	         		</th>
	         		<td align="left" valign="middle">
	                	<div align="left">
	                    	<kul:htmlControlAttribute property="coiNotesAndAttachmentsHelper.newCoiDisclosureAttachment.comments" attributeEntry="${attributes.comments}" />
		            	</div>
					</td>
					<th>
						<div align="right">
							<kul:htmlAttributeLabel attributeEntry="${attributes.description}" noColon="false" />
						</div>
					</th>
	         		<td align="left" valign="middle" colspan="3">
	                    	<kul:htmlControlAttribute property="coiNotesAndAttachmentsHelper.newCoiDisclosureAttachment.description" attributeEntry="${attributes.description}" />                       
					</td>
	         	</tr>
	         	<tr>
	         		<th>
						<div align="right">
							<kul:htmlAttributeLabel attributeEntry="${attachmentFileAttributes['name']}" noColon="false" />
						</div>
					</th>
	       			<td align="left" valign="middle" colspan="5">
	              		<div align="left">
						<c:set var="property" value="coiNotesAndAttachmentsHelper.newCoiDisclosureAttachment.newFile" />
	              		
	              		    <%-- attachment file error handling logic start--%>
	               				<kul:checkErrors keyMatch="${property}" auditMatch="${property}"/>
	               				<%-- highlighting does not work in firefox but does in ie... --%>
	               			<%-- attachment file error handling logic start--%>
	              			<html:file property="${property}" size="50"/>
	               			<c:if test="${hasErrors}">
                    	 		<kul:fieldShowErrorIcon />
                            </c:if>	           			
                            </div>
					</td>
	         	</tr>
	         	<tr>
	         		<td colspan="6" class="infoline">
						<div align="center">
							<html:image property="methodToCall.addAttachmentCoi.anchor${tabKey}"
							src="${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif" styleClass="tinybutton"/>
						</div>
					</td>
	         	</tr>
			</table>
		</kra:permission>
		<c:if test="${not empty coiDisclosureAttachments}">


		<!--  Attached Items sub-panel -->
		<br/>
        <h3>
	        <span class="subhead-left">Attached Items</span>
	        <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.coi.notesandattachments.attachment.CoiDisclosureAttachment" altText="help"/></span>        
        </h3>
        
            <table cellpadding="4" cellspacing="0" summary="">
                <tr>
                   
                    <td style="border: none; width: 10%;">
                        <div align="right">
                            Sort By:
                        </div>
                    </td>
                    <td align="left" valign="middle" style="border: none; width: 40%;">
                        <div align="left">
                            <kul:htmlControlAttribute property="coiNotesAndAttachmentsHelper.newAttachmentFilter.sortBy" attributeEntry="${attachmentFilterAttributes.sortBy}" readOnly="false"/>
                        </div>
                    </td>                    
                </tr>
                <tr>
                    <td colspan="4" class="infoline" style="border: none;">
                        <div align="center">
                            <html:image property="methodToCall.updateAttachmentFilter.anchor${tabKey}"
                            src="${ConfigProperties.kra.externalizable.images.url}tinybutton-filter.gif" styleClass="tinybutton"/>
                        </div>
                    </td>
                </tr>                
            </table>
            
            <!-- show attachments -->
        
        <table cellpadding="4" cellspacing="0" summary="">
        
		<c:forEach var="attachment" items="${filteredAttachments}" varStatus="itrStatus">
        
          <!--  Display logic to show the correct attribute being sorted on in the attachment header -->
          <c:choose>
            <c:when test="${KualiForm.document.coiDisclosureList[0].coiDisclosureAttachmentFilter.sortBy eq 'UPBY'}">
                <c:set var="sortDisplay" value="- ${attachment.updateUserFullName}"/>
            </c:when>
            <c:when test="${KualiForm.document.coiDisclosureList[0].coiDisclosureAttachmentFilter.sortBy eq 'LAUP'}">
                <c:set var="sortDisplay">
                    <fmt:formatDate value="${attachment.updateTimestamp}" pattern="- MM/dd/yyyy KK:mm a" />                      
                </c:set>
            </c:when>
            <c:when test="${KualiForm.document.coiDisclosureList[0].coiDisclosureAttachmentFilter.sortBy eq 'DESC'}">
                <c:set var="sortDisplay" >
                    <c:choose>
                        <c:when test="${fn:length(attachment.description) > 29}">
                            <c:out value="- ${fn:substring(attachment.description, 0, 29)}..." />
                        </c:when>
                        <c:otherwise>
                            <c:out value="- ${attachment.description}" />
                        </c:otherwise>
                    </c:choose>
                </c:set>
            </c:when>                        
            <c:otherwise>
                <c:set var="sortDisplay" value="&nbsp;"/>
            </c:otherwise>
          </c:choose>	
         
		 
		      <tr>
		        <td>
		    			<kul:innerTab tabTitle="attachment" parentTab="Coi Disclosure Attachments" defaultOpen="false" tabErrorKey="document.coiDisclosureList[0].coiDisclosureAttachments[${itrStatus.index}]*,document.coiDisclosureList[0].coiDisclosureAttachments[${itrStatus.index}]*" useCurrentTabIndexAsKey="true" tabAuditKey="document.coiDisclosureList[0].coiDisclosureAttachments[${itrStatus.index}]*" auditCluster="NoteAndAttachmentAuditErrors">
						<div class="innerTab-container" align="left">
            		<table class=tab cellpadding=0 cellspacing="0" summary="">
			         	<tr>
							<th>
								<div align="right">
									<kul:htmlAttributeLabel attributeEntry="${attributes.contactName}" noColon="false" />
								</div>
							</th>
			         		<td align="left" valign="middle">
			                	<div align="left">
			                		
			                		<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclosureAttachments[${itrStatus.index}].contactName" attributeEntry="${attributes.contactName}" readOnly="${!modify}"/>
				            	</div>
							</td>
							
							<th>
							<div align="right">
	         				<kul:htmlAttributeLabel attributeEntry="${attributes.projectId}" noColon="false"/>
	         				</div>
							</th>
							<td>
								<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclosureAttachments[${itrStatus.index}].projectId" attributeEntry="${attributes.contactName}" readOnly="true"/>							
							</td>
							<th>
							<div align="right">
	         					<kul:htmlAttributeLabel attributeEntry="${attributes.entityNumber}" noColon="false"/>
	         				</div>
							</th>
							<td>        					
								<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclosureAttachments[${itrStatus.index}].entityNumber" attributeEntry="${attributes.entityNumber}" readOnly="true"/>                                              
							</td>
			         	</tr>
			         	<tr>
			         		<th>
			         			<div align="right">
			         				<kul:htmlAttributeLabel attributeEntry="${attributes.updateUser}" noColon="false" />
			         			</div>
			         		</th>
			         		<td align="left" valign="middle">
			                	<div align="left">
			                		<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclosureAttachments[${itrStatus.index}].updateUser" attributeEntry="${attributes.updateUser}" readOnly="true"/>
				            	</div>
							</td>
							<th>
								<div align="right">
									<kul:htmlAttributeLabel attributeEntry="${attributes.contactEmailAddress}" noColon="false" />
								</div>
							</th>
			         			<td align="left" valign="middle" colspan="3">
			         			
			                	<div align="left">
			                		<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclosureAttachments[${itrStatus.index}].contactEmailAddress" attributeEntry="${attributes.contactEmailAddress}" readOnly="${!modify}"/>
				            	</div>
							</td>
			         	</tr>
			         	<tr>
			         		<th>
			         			<div align="right">
			         				<kul:htmlAttributeLabel attributeEntry="${attributes.updateTimestamp}" noColon="false" />
			         			</div>
			         		</th>
			         		<td align="left" valign="middle">
			                	<div align="left">
			                	 	     <kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclosureAttachments[${itrStatus.index}].updateTimestamp" attributeEntry="${attributes.updateTimestamp}" readOnly="true"/>  
				            	</div>
							</td>
							<th>
								<div align="right">
									<kul:htmlAttributeLabel attributeEntry="${attributes.contactPhoneNumber}" noColon="false" />
								</div>
							</th>
			         		<td align="left" valign="middle" colspan="3">
			                	<div align="left">
			                		<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclosureAttachments[${itrStatus.index}].contactPhoneNumber" attributeEntry="${attributes.contactPhoneNumber}" readOnly="${!modify}"/>
				            	</div>
							</td>
			         	</tr>
			         	<tr>
			         		<th>
			         			<div align="right">
			         				<kul:htmlAttributeLabel attributeEntry="${attributes.comments}" noColon="false" />
			         			</div>
			         		</th>
			         		<td align="left" valign="middle">
			                	<div align="left">
			                		<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclosureAttachments[${itrStatus.index}].comments" attributeEntry="${attributes.comments}" readOnly="${!modify}"/>
				            	</div>
							</td>
							<th>
								<div align="right">
									<kul:htmlAttributeLabel attributeEntry="${attributes.description}" noColon="false"/>
								</div>
							</th>
			         		<td align="left" valign="middle" colspan="3">
			                	<div align="left">
			                		<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclosureAttachments[${itrStatus.index}].description" attributeEntry="${attributes.description}" readOnly="${!modify}"/>
				            	</div>
							</td>
			         	</tr>
			         	<tr>
			         	<th>
						<div align="right">
									<kul:htmlAttributeLabel attributeEntry="${attachmentFileAttributes['name']}" noColon="false" />
								</div>
							</th>
			       			<td align="left" valign="middle" colspan="5">
			              		<div align="left" style="display: none;" id="attachmentCoiDisclosureFile${itrStatus.index}">
			              			<html:file property="document.coiDisclosureList[0].coiDisclosureAttachments[${itrStatus.index}].newFile" size="50" />
			           			</div>
			           			<div align="left" id="attachmentCoiDisclosureFileName${itrStatus.index}">
			              			<kra:fileicon attachment="${attachment.file}"/>${attachment.file.name}
			           			</div>
			           			
			         	<tr>
			         		<td colspan="6" class="infoline">
								<div align="center">
									<input type="hidden" id="coiDisclosureRefreshButtonClicked${itrStatus.index}" name="coiDisclosureRefreshButtonClicked${itrStatus.index}" value="F"/>
									<html:image property="methodToCall.viewAttachmentCoi.line${itrStatus.index}.anchor${currentTabIndex}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton"
										alt="View Coi Disclosure Attachment" onclick="excludeSubmitRestriction = true;"/>
										<input class="tinybutton" type="image"
											src='${ConfigProperties.kra.externalizable.images.url}tinybutton-replace.gif'
											id="replaceButton${itrStatus.index}"
											alt="Replace Coi Disclosure Attachment"
											onclick="document.getElementById('attachmentCoiDisclosureFile${itrStatus.index}').style.display = 'block';
											document.getElementById('attachmentCoiDisclosureFileName${itrStatus.index}').style.display = 'none';
											document.getElementById('replaceButton${itrStatus.index}').style.display = 'none';
											document.getElementById('coiDisclosureRefreshButtonClicked${itrStatus.index}').value = 'T';
											return false;"/>
										    <html:image property="methodToCall.deleteCoiDisclosureAttachment.line${itrStatus.index}.anchor${currentTabIndex}"
											    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"
											    alt="Delete Coi Disclosure Attachment"/>
								</div>
							</td>
			         	</tr>
         			</table>
         		</div>
         	</kul:innerTab>
		    
		        </td>
		      </tr>
		    
		</c:forEach>
		</table>
		</c:if>
	</div>
</kul:tab>
