<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
   - 
   - This program is free software: you can redistribute it and/or modify
   - it under the terms of the GNU Affero General Public License as
   - published by the Free Software Foundation, either version 3 of the
   - License, or (at your option) any later version.
   - 
   - This program is distributed in the hope that it will be useful,
   - but WITHOUT ANY WARRANTY; without even the implied warranty of
   - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   - GNU Affero General Public License for more details.
   - 
   - You should have received a copy of the GNU Affero General Public License
   - along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%@ attribute name="protocolAttachmentProtocolAttributes" required="true" type="java.util.Map" %>
<%@ attribute name="attachmentFileAttributes" required="true" type="java.util.Map" %>
<%@ attribute name="protocolAttachmentFilterAttributes" required="true" type="java.util.Map" %>
<%@ attribute name="action" required="true" %>
<%@ attribute name="protocolAttachmentTypeByGroupValuesFinder" required="true" %>



<c:set var="notesAttachmentsHelper" value="${KualiForm.notesAttachmentsHelper}" />
<c:set var="modify" value="${KualiForm.notesAttachmentsHelper.modifyAttachments}" />
<c:set var="attachmentProtocols" value="${KualiForm.document.protocolList[0].attachmentProtocols}"/>
<c:set var="filteredAttachmentProtocols" value="${KualiForm.document.protocolList[0].filteredAttachmentProtocols}"/>

<kul:tab tabTitle="Protocol Attachments" tabItemCount="${fn:length(KualiForm.document.protocolList[0].activeAttachmentProtocolsNoDelete)}" defaultOpen="false" tabErrorKey="notesAttachmentsHelper.newAttachmentProtocol.*" transparentBackground="true" tabAuditKey="document.protocolList[0].attachmentProtocols*">
	<div class="tab-container" align="center">
   		<kra:permission value="${modify}">
	   		<h3>
	   			<span class="subhead-left">Add Protocol Attachment</span>
	   			<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.iacuc.noteattachment.IacucProtocolAttachmentProtocol" altText="help"/></span>
	       </h3>
	       <table cellpadding="4" cellspacing="0" summary="">
	       		<tbody class="addline">
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
								<c:set var="options" value="${krafn:getOptionList(protocolAttachmentTypeByGroupValuesFinder, typeParamsType)}" />
							<%-- attachment type finder logic end --%>
							
	               			<%-- attachment type error handling logic start--%>
	               				<kul:checkErrors keyMatch="${property}" auditMatch="${property}"/>
	               			<%-- 	<c:set var="textStyle" value="${hasErrors == true ? 'background-color:#FFD5D5' : ''}"/>--%>
	               			<%-- attachment type error handling logic start--%>
	               			<html:select property="${property}">
	               				<html:options collection="options" labelProperty="value" property="key" />
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
							src="${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif" styleClass="tinybutton addButton"/>
						</div>
					</td>
	         	</tr>
	         	</tbody>
			</table>
		</kra:permission>
		
		<c:if test="${not empty attachmentProtocols}">


		<!--  Attached Items sub-panel -->
		<br/>
        <h3>
	        <span class="subhead-left">Attached Items</span>
	        <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.iacuc.noteattachment.IacucProtocolAttachmentProtocol" altText="help"/></span>        
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
                            <kul:htmlControlAttribute property="notesAttachmentsHelper.newAttachmentFilter.filterBy" attributeEntry="${protocolAttachmentFilterAttributes.filterBy}" readOnly="false" onchange="filterTableByCriteria(this)"/>
                        </div>
                    </td>
                    <td style="border: none; width: 10%;">
                        <div align="right">
                            Sort By:
                        </div>
                    </td>
                    <td align="left" valign="middle" style="border: none; width: 40%;">
                        <div align="left">
                            <kul:htmlControlAttribute property="notesAttachmentsHelper.newAttachmentFilter.sortBy" attributeEntry="${protocolAttachmentFilterAttributes.sortBy}" readOnly="false" onchange="sortTableByCriteria(this)"/>
                        </div>
                    </td>                    
                </tr>
            </table>
        <table cellpadding="4" cellspacing="0" summary="" id="protocol-attachment-table">
        <tbody>
		<c:forEach var="attachmentProtocol" items="${filteredAttachmentProtocols}" varStatus="itrStatus">
        
          <!--  Display logic to show the correct attribute being sorted on in the attachment header -->
          <c:set var="descDisplay" >
              <c:choose>
                  <c:when test="${fn:length(attachmentProtocol.description) > 29}">
                      <c:out value="${fn:substring(attachmentProtocol.description, 0, 29)}..." />
                  </c:when>
                  <c:otherwise>
                      <c:out value="${attachmentProtocol.description}" />
                  </c:otherwise>
              </c:choose>
          </c:set>
          <c:set var="updateUserDisplay" value="${attachmentProtocol.updateUserFullName}"/>
          <c:set var="lastUpdateDisplay">
              <fmt:formatDate value="${attachmentProtocol.updateTimestamp}" pattern="MM/dd/yyyy KK:mm a" />                      
          </c:set>	
          	
		  <c:choose>
		    <c:when test="${attachmentProtocol.active}">
		      <tr id="protocol-attachment-row-${itrStatus.index}" class="fake-class-level-1">
		        <td>
		             <c:set var="modify" value="${KualiForm.notesAttachmentsHelper.modifyAttachments and attachmentProtocol.documentStatusCode != '3' and (not KualiForm.document.protocolList[0].renewalWithoutAmendment or attachmentProtocol.documentStatusCode != '2')}" />
		    			<kul:innerTab tabTitle="${attachmentProtocol.type.description} - ${descDisplay} - ${updateUserDisplay} (${lastUpdateDisplay})" parentTab="Protocol Attachments(${size})" defaultOpen="false" tabErrorKey="document.protocolList[0].attachmentProtocols[${itrStatus.index}]*,document.protocolList[0].attachmentProtocols[${itrStatus.index}]*" useCurrentTabIndexAsKey="true" tabAuditKey="document.protocolList[0].attachmentProtocols[${itrStatus.index}]*" auditCluster="NoteAndAttachmentAuditErrors">
				<div class="innerTab-container" align="left">
            		<table class=tab cellpadding=0 cellspacing="0" summary="">
						<tr>
			         		<th>
			         			<div align="right">
			         				<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes['typeCode']}" noColon="false" />
			         			</div>
			         		</th>
			         		<td align="left" valign="middle" colspan="3">
			                	<div align="left" id="attachment-type-${itrStatus.index}">
			                		<kul:htmlControlAttribute property="document.protocolList[0].attachmentProtocols[${itrStatus.index}].typeCode" attributeEntry="${protocolAttachmentProtocolAttributes['typeCode']}" readOnly="true" readOnlyAlternateDisplay ="${attachmentProtocol.type.description}" />
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
			                		<kul:htmlControlAttribute property="document.protocolList[0].attachmentProtocols[${itrStatus.index}].statusCode" attributeEntry="${protocolAttachmentProtocolAttributes['statusCode']}" readOnly="${!modify}"/>
				            	</div>
							</td>
							<th>
								<div align="right">
									<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes.contactName}" noColon="false" />
								</div>
							</th>
			         		<td align="left" valign="middle">
			                	<div align="left">
			                		<kul:htmlControlAttribute property="document.protocolList[0].attachmentProtocols[${itrStatus.index}].contactName" attributeEntry="${protocolAttachmentProtocolAttributes.contactName}" readOnly="${!modify}"/>
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
			                	<div align="left" id="updated-by-${itrStatus.index}">
			                		<kul:htmlControlAttribute property="document.protocolList[0].attachmentProtocols[${itrStatus.index}].updateUserFullName" attributeEntry="${protocolAttachmentProtocolAttributes.updateUser}" readOnly="true"/>
				            	</div>
							</td>
							<th>
								<div align="right">
									<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes.contactEmailAddress}" noColon="false" />
								</div>
							</th>
			         			<td align="left" valign="middle">
			                	<div align="left">
			                		<kul:htmlControlAttribute property="document.protocolList[0].attachmentProtocols[${itrStatus.index}].contactEmailAddress" attributeEntry="${protocolAttachmentProtocolAttributes.contactEmailAddress}" readOnly="${!modify}"/>
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
			                	<div align="left" id="last-updated-${itrStatus.index}">
			                	 	     <kul:htmlControlAttribute property="document.protocolList[0].attachmentProtocols[${itrStatus.index}].updateTimestamp" attributeEntry="${protocolAttachmentProtocolAttributes.updateTimestamp}" readOnly="true"/>  
				            	</div>
							</td>
							<th>
								<div align="right">
									<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes.contactPhoneNumber}" noColon="false" />
								</div>
							</th>
			         		<td align="left" valign="middle">
			                	<div align="left">
			                		<kul:htmlControlAttribute property="document.protocolList[0].attachmentProtocols[${itrStatus.index}].contactPhoneNumber" attributeEntry="${protocolAttachmentProtocolAttributes.contactPhoneNumber}" readOnly="${!modify}"/>
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
			                		<kul:htmlControlAttribute property="document.protocolList[0].attachmentProtocols[${itrStatus.index}].comments" attributeEntry="${protocolAttachmentProtocolAttributes.comments}" readOnly="${!modify}"/>
				            	</div>
							</td>
							<th>
								<div align="right">
									<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes.description}" noColon="false"/>
								</div>
							</th>
			         		<td align="left" valign="middle">
			                	<div align="left" id="row-description-${itrStatus.index}">
			                		<kul:htmlControlAttribute property="document.protocolList[0].attachmentProtocols[${itrStatus.index}].description" attributeEntry="${protocolAttachmentProtocolAttributes.description}" readOnly="${!modify}"/>
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
			              			<html:file property="document.protocolList[0].attachmentProtocols[${itrStatus.index}].newFile" size="50" />
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
		      <html:hidden property="document.protocolList[0].attachmentProtocols[${itrStatus.index}].typeCode" value="${KualiForm.document.protocolList[0].attachmentProtocols[itrStatus.index].typeCode}" />
		      <html:hidden property="document.protocolList[0].attachmentProtocols[${itrStatus.index}].statusCode" value="${KualiForm.document.protocolList[0].attachmentProtocols[itrStatus.index].statusCode}" />
		      <html:hidden property="document.protocolList[0].attachmentProtocols[${itrStatus.index}].contactName" value="${KualiForm.document.protocolList[0].attachmentProtocols[itrStatus.index].contactName}" />
		      <html:hidden property="document.protocolList[0].attachmentProtocols[${itrStatus.index}].contactEmailAddress" value="${KualiForm.document.protocolList[0].attachmentProtocols[itrStatus.index].contactEmailAddress}" />
		      <html:hidden property="document.protocolList[0].attachmentProtocols[${itrStatus.index}].contactPhoneNumber" value="${KualiForm.document.protocolList[0].attachmentProtocols[itrStatus.index].contactPhoneNumber}" />
		      <html:hidden property="document.protocolList[0].attachmentProtocols[${itrStatus.index}].comments" value="${KualiForm.document.protocolList[0].attachmentProtocols[itrStatus.index].comments}" />
		      <html:hidden property="document.protocolList[0].attachmentProtocols[${itrStatus.index}].description" value="${KualiForm.document.protocolList[0].attachmentProtocols[itrStatus.index].description}" />
		      <html:hidden property="document.protocolList[0].attachmentProtocols[${itrStatus.index}].file.name" value="${KualiForm.document.protocolList[0].attachmentProtocols[itrStatus.index].file.name}" />
		    </c:otherwise>
		  </c:choose>
		</c:forEach>
		</tbody>
		</table>
		</c:if>
     </div>		
</kul:tab>
