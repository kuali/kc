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

<c:set var="protocolAttachmentProtocolAttributes" value="${DataDictionary.ProtocolAttachmentProtocol.attributes}" />
<c:set var="attachmentFileAttributes" value="${DataDictionary.AttachmentFile.attributes}" />
<c:set var="protocolAttachmentFilterAttributes" value="${DataDictionary.ProtocolAttachmentFilter.attributes}" />
<c:set var="notesAttachmentsHelper" value="${KualiForm.notesAttachmentsHelper}" />
<c:set var="modify" value="${KualiForm.notesAttachmentsHelper.modifyAttachments}" />
<c:set var="action" value="protocolNoteAndAttachment" />
<c:set var="attachmentProtocols" value="${KualiForm.document.protocolList[0].attachmentProtocols}"/>
<c:set var="filteredAttachmentProtocols" value="${KualiForm.document.protocolList[0].filteredAttachmentProtocols}"/>

<kul:tab tabTitle="Protocol Attachments" tabItemCount="${fn:length(KualiForm.document.protocolList[0].activeAttachmentProtocolsNoDelete)}" defaultOpen="false" tabErrorKey="notesAttachmentsHelper.newAttachmentProtocol.*" transparentBackground="true" tabAuditKey="document.protocolList[0].attachmentProtocols*">
	<div class="tab-container" align="center">
   		<kra:permission value="${modify}">
	   		<h3>
	   			<span class="subhead-left">Add Protocol Attachment</span>
	   			<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.irb.noteattachment.ProtocolAttachmentProtocol" altText="help"/></span>
	       </h3>
	       <table cellpadding="4" cellspacing="0" summary="">
	         	<tr>
	         		<th>
	         			<div align="right">
	         				<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes['typeCode']}" noColon="false"/>
	         			</div>
	         		</th>
	         		<td align="left" valign="middle" colspan="3">
	                	<div align="left">
	                		<c:set var="property" value="notesAttachmentsHelper.newAttachmentProtocol.typeCode" />
	                	
	               			<%-- attachment type finder logic start--%>
								<jsp:useBean id="typeParamsType" class="java.util.HashMap"/>
								<c:set target="${typeParamsType}" property="groupCode" value="${notesAttachmentsHelper.newAttachmentProtocol.groupCode}" />
								<c:set var="options" value="${krafn:getOptionList('org.kuali.kra.irb.noteattachment.ProtocolAttachmentTypeByGroupValuesFinder', typeParamsType)}" />
							<%-- attachment type finder logic end --%>
							
	               			<%-- attachment type error handling logic start--%>
	               				<kul:checkErrors keyMatch="${property}" auditMatch="${property}"/>
	               			<%-- 	<c:set var="textStyle" value="${hasErrors == true ? 'background-color:#FFD5D5' : ''}"/>--%>
	               			<%-- attachment type error handling logic start--%>
	               			<html:select property="${property}">
	               				<html:options collection="options" labelProperty="label" property="key" />
	               			</html:select>
	               			<c:if test="${hasErrors}">
                    	 		<kul:fieldShowErrorIcon />
                            </c:if>
 
		            	</div>
					</td>
	         	</tr>
	         	<tr>
	         		<th>
	         			<div align="right">
	         				<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes['statusCode']}" noColon="false"/>
	         			</div>
	         		</th>
	         		<td align="left" valign="middle">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="notesAttachmentsHelper.newAttachmentProtocol.statusCode" attributeEntry="${protocolAttachmentProtocolAttributes['statusCode']}"/>
		            	</div>
					</td>
					<th>
						<div align="right">
							<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes.contactName}" noColon="false" />
						</div>
					</th>
	         		<td align="left" valign="middle">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="notesAttachmentsHelper.newAttachmentProtocol.contactName" attributeEntry="${protocolAttachmentProtocolAttributes.contactName}"/>
		            	</div>
					</td>
	         	</tr>
	         	<tr>
	         		<th>
	         			<div align="right">
	         				<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes.updateUser}" noColon="false" />
	         			</div>
	         		</th>
	         		<td align="left" valign="middle">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="notesAttachmentsHelper.newAttachmentProtocol.updateUser" attributeEntry="${protocolAttachmentProtocolAttributes.updateUser}" readOnly="true"/>
		            	</div>
					</td>
					<th>
						<div align="right">
							<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes.contactEmailAddress}" noColon="false" />
						</div>
					</th>
	         			<td align="left" valign="middle">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="notesAttachmentsHelper.newAttachmentProtocol.contactEmailAddress" attributeEntry="${protocolAttachmentProtocolAttributes.contactEmailAddress}"/>
		            	</div>
					</td>
	         	</tr>
	         	<tr>
	         		<th>
	         			<div align="right">
	         				<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes.updateTimestamp}" noColon="false" />
	         			</div>
	         		</th>
	         		<td align="left" valign="middle">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="notesAttachmentsHelper.newAttachmentProtocol.updateTimestamp" attributeEntry="${protocolAttachmentProtocolAttributes.updateTimestamp}" readOnly="true"/>
		            	</div>
					</td>
					<th>
						<div align="right">
							<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes.contactPhoneNumber}" noColon="false" />
						</div>
					</th>
	         		<td align="left" valign="middle">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="notesAttachmentsHelper.newAttachmentProtocol.contactPhoneNumber" attributeEntry="${protocolAttachmentProtocolAttributes.contactPhoneNumber}"/>
		            	</div>
					</td>
	         	</tr>
	         	<tr>
	         		<th>
	         			<div align="right">
	         				<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes.comments}" noColon="false" />
	         			</div>
	         		</th>
	         		<td align="left" valign="middle">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="notesAttachmentsHelper.newAttachmentProtocol.comments" attributeEntry="${protocolAttachmentProtocolAttributes.comments}"/>
		            	</div>
					</td>
					<th>
						<div align="right">
							<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes.description}" noColon="false"/>
						</div>
					</th>
	         		<td align="left" valign="middle">
                        <div align="left">
                            <c:set var="property" value="notesAttachmentsHelper.newAttachmentProtocol.description" />
                                   
                            <%-- attachment description error handling logic start--%>
                            <kul:checkErrors keyMatch="${property}" auditMatch="${property}"/>
                            <%-- attachment type description handling logic start--%>
                            <kul:htmlControlAttribute property="notesAttachmentsHelper.newAttachmentProtocol.description" attributeEntry="${protocolAttachmentProtocolAttributes.description}"/>
                            
                        </div>
					</td>
	         	</tr>
	         	<tr>
	         		<th>
						<div align="right">
							<kul:htmlAttributeLabel attributeEntry="${attachmentFileAttributes['name']}" noColon="false" />
						</div>
					</th>
	       			<td align="left" valign="middle" colspan="3">
	              		<div align="left">
	              			<c:set var="property" value="notesAttachmentsHelper.newAttachmentProtocol.newFile" />
	              		
	              		    <%-- attachment file error handling logic start--%>
	               				<kul:checkErrors keyMatch="${property}" auditMatch="${property}"/>
	               				<%-- highlighting does not work in firefox but does in ie... --%>
	               				<%-- <c:set var="textStyle" value="${hasErrors == true ? 'background-color:#FFD5D5' : ''}"/>--%>
	               			<%-- attachment file error handling logic start--%>
	              			<html:file property="${property}" size="50"/>
	               			<c:if test="${hasErrors}">
                    	 		<kul:fieldShowErrorIcon />
                            </c:if>
	           			</div>
					</td>
	         	</tr>
	         	<tr>
	         		<td colspan="4" class="infoline">
						<div align="center">
							<html:image property="methodToCall.addAttachmentProtocol.anchor${tabKey}"
							src="${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif" styleClass="tinybutton"/>
						</div>
					</td>
	         	</tr>
			</table>
		</kra:permission>
		
		<c:if test="${not empty attachmentProtocols}">


		<!--  Attached Items sub-panel -->
		<br/>
        <h3>
	        <span class="subhead-left">Attached Items</span>
	        <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.irb.noteattachment.ProtocolAttachmentProtocol" altText="help"/></span>        
        </h3>
        
            <table cellpadding="4" cellspacing="0" summary="">
                <tr>
                    <td style="border: none; width: 30%;">
                        <div align="right">
                            Show:
                        </div>
                    </td style="border: none; width: 20%;">
                    <td align="left" valign="middle" style="border: none;">
                        <div align="left">
                            <kul:htmlControlAttribute property="notesAttachmentsHelper.newAttachmentFilter.filterBy" attributeEntry="${protocolAttachmentFilterAttributes.filterBy}" readOnly="false"/>
                        </div>
                    </td>
                    <td style="border: none; width: 10%;">
                        <div align="right">
                            Sort By:
                        </div>
                    </td>
                    <td align="left" valign="middle" style="border: none; width: 40%;">
                        <div align="left">
                            <kul:htmlControlAttribute property="notesAttachmentsHelper.newAttachmentFilter.sortBy" attributeEntry="${protocolAttachmentFilterAttributes.sortBy}" readOnly="false"/>
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
        <table cellpadding="4" cellspacing="0" summary="">
        
        
        <c:if test="${not empty filteredAttachmentProtocols}">
		<c:forEach var="attachmentProtocol" items="${filteredAttachmentProtocols}" varStatus="itrStatus">
        
          <!--  Display logic to show the correct attribute being sorted on in the attachment header -->
          <c:choose>
            <c:when test="${KualiForm.document.protocolList[0].protocolAttachmentFilter.sortBy eq 'UPBY'}">
                <c:set var="sortDisplay" value="- ${attachmentProtocol.updateUserFullName}"/>
            </c:when>
            <c:when test="${KualiForm.document.protocolList[0].protocolAttachmentFilter.sortBy eq 'LAUP'}">
                <c:set var="sortDisplay">
                    <fmt:formatDate value="${attachmentProtocol.updateTimestamp}" pattern="- MM/dd/yyyy KK:mm a" />                      
                </c:set>
            </c:when>
            <c:when test="${KualiForm.document.protocolList[0].protocolAttachmentFilter.sortBy eq 'DESC'}">
                <c:set var="sortDisplay" >
                    <c:choose>
                        <c:when test="${fn:length(attachmentProtocol.description) > 29}">
                            <c:out value="- ${fn:substring(attachmentProtocol.description, 0, 29)}..." />
                        </c:when>
                        <c:otherwise>
                            <c:out value="- ${attachmentProtocol.description}" />
                        </c:otherwise>
                    </c:choose>
                </c:set>
            </c:when>                        
            <c:otherwise>
                <c:set var="sortDisplay" value="&nbsp;"/>
            </c:otherwise>
          </c:choose>	
          	
		  <c:choose>
		    <c:when test="${attachmentProtocol.active}">
		      <tr>
		        <td>
		             <c:set var="modify" value="${KualiForm.notesAttachmentsHelper.modifyAttachments and attachmentProtocol.documentStatusCode != '3' and (not KualiForm.document.protocolList[0].renewalWithoutAmendment or attachmentProtocol.documentStatusCode != '2')}" />
		    			<kul:innerTab tabTitle="${attachmentProtocol.type.description} ${sortDisplay}" parentTab="Protocol Attachments(${size})" defaultOpen="false" tabErrorKey="document.protocolList[0].attachmentProtocols[${itrStatus.index}]*,document.protocolList[0].attachmentProtocols[${itrStatus.index}]*" useCurrentTabIndexAsKey="true" tabAuditKey="document.protocolList[0].attachmentProtocols[${itrStatus.index}]*" auditCluster="NoteAndAttachmentAuditErrors">
				<div class="innerTab-container" align="left">
            		<table class=tab cellpadding=0 cellspacing="0" summary="">
						<tr>
			         		<th>
			         			<div align="right">
			         				<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes['typeCode']}" noColon="false" />
			         			</div>
			         		</th>
			         		<td align="left" valign="middle" colspan="3">
			                	<div align="left">
			                		<kul:htmlControlAttribute property="document.protocolList[0].filteredAttachmentProtocols[${itrStatus.index}].typeCode" attributeEntry="${protocolAttachmentProtocolAttributes['typeCode']}" readOnly="true" readOnlyAlternateDisplay ="${attachmentProtocol.type.description}" />
				            	</div>
							</td>
			         	</tr>
			         	<tr>
			         		<th>
			         			<div align="right">
			         				<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes['statusCode']}" noColon="false"/>
			         			</div>
			         		</th>
			         		<td align="left" valign="middle">
			                	<div align="left">
			                		<kul:htmlControlAttribute property="document.protocolList[0].filteredAttachmentProtocols[${itrStatus.index}].statusCode" attributeEntry="${protocolAttachmentProtocolAttributes['statusCode']}" readOnly="${!modify}"/>
				            	</div>
							</td>
							<th>
								<div align="right">
									<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes.contactName}" noColon="false" />
								</div>
							</th>
			         		<td align="left" valign="middle">
			                	<div align="left">
			                		<kul:htmlControlAttribute property="document.protocolList[0].filteredAttachmentProtocols[${itrStatus.index}].contactName" attributeEntry="${protocolAttachmentProtocolAttributes.contactName}" readOnly="${!modify}"/>
				            	</div>
							</td>
			         	</tr>
			         	<tr>
			         		<th>
			         			<div align="right">
			         				<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes.updateUser}" noColon="false" />
			         			</div>
			         		</th>
			         		<td align="left" valign="middle">
			                	<div align="left">
			                		<kul:htmlControlAttribute property="document.protocolList[0].filteredAttachmentProtocols[${itrStatus.index}].updateUserFullName" attributeEntry="${protocolAttachmentProtocolAttributes.updateUser}" readOnly="true"/>
				            	</div>
							</td>
							<th>
								<div align="right">
									<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes.contactEmailAddress}" noColon="false" />
								</div>
							</th>
			         			<td align="left" valign="middle">
			                	<div align="left">
			                		<kul:htmlControlAttribute property="document.protocolList[0].filteredAttachmentProtocols[${itrStatus.index}].contactEmailAddress" attributeEntry="${protocolAttachmentProtocolAttributes.contactEmailAddress}" readOnly="${!modify}"/>
				            	</div>
							</td>
			         	</tr>
			         	<tr>
			         		<th>
			         			<div align="right">
			         				<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes.updateTimestamp}" noColon="false" />
			         			</div>
			         		</th>
			         		<td align="left" valign="middle">
			                	<div align="left">
			                	 	     <kul:htmlControlAttribute property="document.protocolList[0].filteredAttachmentProtocols[${itrStatus.index}].updateTimestamp" attributeEntry="${protocolAttachmentProtocolAttributes.updateTimestamp}" readOnly="true"/>  
				            	</div>
							</td>
							<th>
								<div align="right">
									<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes.contactPhoneNumber}" noColon="false" />
								</div>
							</th>
			         		<td align="left" valign="middle">
			                	<div align="left">
			                		<kul:htmlControlAttribute property="document.protocolList[0].filteredAttachmentProtocols[${itrStatus.index}].contactPhoneNumber" attributeEntry="${protocolAttachmentProtocolAttributes.contactPhoneNumber}" readOnly="${!modify}"/>
				            	</div>
							</td>
			         	</tr>
			         	<tr>
			         		<th>
			         			<div align="right">
			         				<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes.comments}" noColon="false" />
			         			</div>
			         		</th>
			         		<td align="left" valign="middle">
			                	<div align="left">
			                		<kul:htmlControlAttribute property="document.protocolList[0].filteredAttachmentProtocols[${itrStatus.index}].comments" attributeEntry="${protocolAttachmentProtocolAttributes.comments}" readOnly="${!modify}"/>
				            	</div>
							</td>
							<th>
								<div align="right">
									<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes.description}" noColon="false"/>
								</div>
							</th>
			         		<td align="left" valign="middle">
			                	<div align="left">
			                		<kul:htmlControlAttribute property="document.protocolList[0].filteredAttachmentProtocols[${itrStatus.index}].description" attributeEntry="${protocolAttachmentProtocolAttributes.description}" readOnly="${!modify}"/>
				            	</div>
							</td>
			         	</tr>
			         	<tr>
			         	<th>
								<div align="right">
									<kul:htmlAttributeLabel attributeEntry="${attachmentFileAttributes['name']}" noColon="false" />
								</div>
							</th>
			       			<td align="left" valign="middle" colspan="3">
			              		<div align="left" style="display: none;" id="attachmentProtocolFile${itrStatus.index}">
			              			<html:file property="document.protocolList[0].filteredAttachmentProtocols[${itrStatus.index}].newFile" size="50" />
			           			</div>
			           			<div align="left" id="attachmentProtocolFileName${itrStatus.index}">
			           			   <c:if test="${attachmentProtocol.documentStatusCode == '3'}">
			           			      <font color="red">Deleted -&nbsp;</font>
			           			   </c:if>
			              			<kra:fileicon attachment="${attachmentProtocol.file}"/>${attachmentProtocol.file.name}
			           			</div>
			           			
			           			<%-- this assumes that the versions collection is sorted descending by sequence number --%>
			           			<c:set var="doVersionsExist" value="${fn:length(attachmentProtocol.versions) > 0}" />
			           			<c:if test="${doVersionsExist}">
				           			<kul:innerTab tabTitle="File Versions" parentTab="${attachmentProtocol.type.description} - ${attachmentProtocol.status.description} - ${itrStatus.index}" defaultOpen="false">
										<div class="innerTab-container" align="left">
				         					<table class=tab cellpadding=0 cellspacing="0" summary="" width="100%">
			         							<tr>
					         						<th style="width: 20%">
					         							Last Modifier
					         						</th>
					         						<th style="width: 20%">
					         							Created Date
					         						</th>
					         						<th style="width: 20%">
					         							Last Modified Date
					         						</th>
					         						<th style="width: 60%">
					         							Description
					         						</th>
					         					</tr>
							         			<c:forEach var="attachmentProtocolVersion" items="${attachmentProtocol.versions}" varStatus="innerItrStatus">
						         					<tr>
						         						<td style="width: 20%">
						         							${attachmentProtocolVersion.authorPersonName}
						         						</td>
						         						<td style="width: 20%">
	                                                       <fmt:formatDate value="${attachmentProtocolVersion.createTimestamp}" pattern="MM/dd/yyyy KK:mm a" />
						         							
						         						</td>
						         						<td style="width: 20%">
	                                                       <fmt:formatDate value="${attachmentProtocolVersion.updateTimestamp}" pattern="MM/dd/yyyy KK:mm a" />
						         						</td>
														<td style="width: 60%">
														   <div align="left">
						         							${attachmentProtocolVersion.description}
						         							</div>
						         						</td>
						         					</tr>
							         			</c:forEach>
						         			</table>
					         			</div>
				         			</kul:innerTab>
				         		</c:if>
							</td>
			         	</tr>
						<tr>
			         		<td colspan="4" class="infoline">
								<div align="center">
									<input type="hidden" id="protocolRefreshButtonClicked${itrStatus.index}" name="protocolRefreshButtonClicked${itrStatus.index}" value="F"/>
									<html:image property="methodToCall.viewAttachmentProtocol.line${itrStatus.index}.anchor${currentTabIndex}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton"
										alt="View Protocol Attachment" onclick="excludeSubmitRestriction = true;"/>
									<kra:permission value="${KualiForm.notesAttachmentsHelper.modifyAttachments and (not KualiForm.document.protocolList[0].renewalWithoutAmendment or attachmentProtocol.documentStatusCode != '2')}">
										<input class="tinybutton" type="image"
											src='${ConfigProperties.kra.externalizable.images.url}tinybutton-replace.gif'
											id="replaceButton${itrStatus.index}"
											alt="Replace Protocol Attachment"
											onclick="document.getElementById('attachmentProtocolFile${itrStatus.index}').style.display = 'block';
											document.getElementById('attachmentProtocolFileName${itrStatus.index}').style.display = 'none';
											document.getElementById('replaceButton${itrStatus.index}').style.display = 'none';
											document.getElementById('protocolRefreshButtonClicked${itrStatus.index}').value = 'T';
											return false;"/>
									    <c:if test="${modify}">
										    <html:image property="methodToCall.deleteAttachmentProtocol.line${itrStatus.index}.anchor${currentTabIndex}"
											    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"
											    alt="Delete Protocol Attachment"/>
			           			        </c:if>											
									</kra:permission>
								</div>
							</td>
			         	</tr>
         			</table>
         		</div>
         	</kul:innerTab>
		    
		        </td>
		      </tr>
		    </c:when>
		    <c:otherwise>
                <tr><td>&nbsp;</td></tr>
		    </c:otherwise>
		  </c:choose>
		</c:forEach>
		</c:if>
		</table>
		</c:if>
     </div>		
</kul:tab>
