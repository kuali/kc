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
<%@ include file="/WEB-INF/jsp/irb/ProtocolPerson.jsp"%>
<c:set var="narrativeAttributes" value="${DataDictionary.Narrative.attributes}" />

<c:choose>
	<c:when test="${empty KualiForm.document.protocolList[0].protocolPersons[personIndex].personName}">
		<c:set var="parentTabName" value="" />
	</c:when>
	<c:otherwise>
		<bean:define id="parentTabName" name="KualiForm" property="${protocolPerson}.personName"/>
	</c:otherwise>
</c:choose>

<c:set var="attachmentPersonnels" value="${KualiForm.document.protocolList[0].protocolPersons[personIndex].attachmentPersonnels}"/>

<c:set var="readOnly" value="${!KualiForm.personnelHelper.modifyPersonnel}" />

<table cellpadding=0 cellspacing=0 summary="">
 	<tr>
		<td>
			<kul:innerTab tabTitle="Attachments" parentTab="${parentTabName}" defaultOpen="false" tabErrorKey="personnelHelper.newProtocolAttachmentPersonnels[${personIndex}]*" useCurrentTabIndexAsKey="true">
				<div class="innerTab-container" align="left">
			        <table class=tab cellpadding="0" cellspacing="0" summary="">
              			<tbody id="G3">
			          	<%-- Header --%>
			          	<tr>
			          		<kul:htmlAttributeHeaderCell literalLabel="&nbsp;" scope="col" /> 
			          		<kul:htmlAttributeHeaderCell attributeEntry="${protocolAttachmentPersonnelAttributes.updateTimestamp}" scope="col" align="center"/>
			          		<kul:htmlAttributeHeaderCell attributeEntry="${protocolAttachmentPersonnelAttributes.updateUser}" scope="col" align="center"/>
			          		<kul:htmlAttributeHeaderCell attributeEntry="${protocolAttachmentPersonnelAttributes.typeCode}" scope="col" align="center"/>
			          		<kul:htmlAttributeHeaderCell attributeEntry="${protocolAttachmentPersonnelAttributes.description}" scope="col" align="center"/>
			          		<kul:htmlAttributeHeaderCell attributeEntry="${attachmentFileAttributes.name}" scope="col" align="center"/>
                			<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" align="center"/>
			          	</tr> 
			          	<%-- Header --%>
			          	
			            <%-- New data --%>
        				<kra:permission value="${KualiForm.personnelHelper.modifyPersonnel}">
				             <tr>
								<th class="infoline">
									<c:out value="Add:" />
								</th>
				                <td align="left" valign="middle" class="infoline">
				                	<div align="left">
				                		<kul:htmlControlAttribute property="personnelHelper.newProtocolAttachmentPersonnels[${personIndex}].updateTimestamp" 
				                		                          attributeEntry="${protocolAttachmentPersonnelAttributes.updateTimestamp}" 
				                		                          readOnly="true" />
					            	</div>
								</td>
				                <td align="left" valign="middle" class="infoline">
				                	<div align="left">
				                		<kul:htmlControlAttribute property="personnelHelper.newProtocolAttachmentPersonnels[${personIndex}].updateUser" 
				                		                          attributeEntry="${protocolAttachmentPersonnelAttributes.updateUser}" 
				                		                          readOnly="true" />
					            	</div>
								</td>
								<td align="left" valign="middle" class="infoline">
				                	<div align="left">
				                		<c:set var="property" value="personnelHelper.newProtocolAttachmentPersonnels[${personIndex}].typeCode" />
				                		
				                		<%-- attachment type finder logic start--%>
											<jsp:useBean id="typeParamsType" class="java.util.HashMap"/>
											<c:set target="${typeParamsType}" property="groupCode" value="${KualiForm.personnelHelper.newProtocolAttachmentPersonnels[personIndex].groupCode}" />
											<c:set var="options" value="${krafn:getOptionList('org.kuali.kra.irb.noteattachment.ProtocolAttachmentTypeByGroupValuesFinder', typeParamsType)}" />
										<%-- attachment type finder logic end --%>
				               			
				               			<%-- attachment type error handling logic start--%>
				               				<kul:checkErrors keyMatch="${property}" auditMatch="${property}"/>
				               			<%-- attachment type error handling logic start--%>
				               			
            	               			<html:select property="${property}">
            	               				<html:options collection="options" labelProperty="value" property="key" />
	                           			</html:select>
	               		            	<c:if test="${hasErrors}">
                                	 		<kul:fieldShowErrorIcon />
                                        </c:if>
					            	</div>
								</td>
								<td align="left" valign="middle" class="infoline">
				                	<div align="left">
				                	    <c:set var="property" value="personnelHelper.newProtocolAttachmentPersonnels[${personIndex}].description" />
                                        <%-- attachment description error handling logic start--%>
                                            <kul:checkErrors keyMatch="${property}" auditMatch="${property}"/>
                                        <%-- attachment description error handling logic start--%>				                	
				                		<kul:htmlControlAttribute property="personnelHelper.newProtocolAttachmentPersonnels[${personIndex}].description" 
				                		                          attributeEntry="${protocolAttachmentPersonnelAttributes.description}" />
				                		                          				                		                          
					            	</div>
								</td>
								<td align="left" valign="middle" class="infoline">
				              		<div align="left">
				              		    <c:set var="property" value="personnelHelper.newProtocolAttachmentPersonnels[${personIndex}].newFile" />
				              		
				              		    <%-- attachment file error handling logic start--%>
				               				<kul:checkErrors keyMatch="${property}" auditMatch="${property}"/>
				               				<%-- highlighting does not work in firefox but does in ie... --%>
				               			<%-- attachment file error handling logic start--%>
				              		
				              			<html:file property="${property}"/>
				               			<c:if test="${hasErrors}">
			                    	 		<kul:fieldShowErrorIcon />
			                            </c:if>
				           			</div>
								</td>
								<td align="center" valign="middle" class="infoline">
									<div align="center">
										<html:image property="methodToCall.addPersonnelAttachment.${protocolPerson}.line${status.index}"
										            src="${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif" 
										            title="Add Attachment" alt="Add Attachment" styleClass="tinybutton" />
									</div>
								</td>
				            </tr>
						</kra:permission>
			            <%-- New data --%>
			            
			            <%-- Existing data --%>
		  				<c:forEach var="attachment" items="${attachmentPersonnels}" varStatus="status">
			                <tr>
			                    <th scope="row"  align="center">
			                        <c:out value="${status.index + 1}" />
			                    </th>
			                    <td> 
			                        <kul:htmlControlAttribute property="document.protocolList[0].protocolPersons[${personIndex}].attachmentPersonnels[${status.index}].updateTimestamp"
			                                                  attributeEntry="${protocolAttachmentPersonnelAttributes.updateTimestamp}" 
			                                                  readOnly="true" />
			                                                  ${attachment.updateTimestamp }
			                    </td>
			                    <td> 
			                        <kul:htmlControlAttribute property="document.protocolList[0].protocolPersons[${personIndex}].attachmentPersonnels[${status.index}].updateUser"
			                                                  attributeEntry="${protocolAttachmentPersonnelAttributes.updateUser}" 
			                                                  readOnly="true" />
			                    </td>
			                    <td> 
			                        <kul:htmlControlAttribute property="document.protocolList[0].protocolPersons[${personIndex}].attachmentPersonnels[${status.index}].typeCode"
			                                                  attributeEntry="${protocolAttachmentPersonnelAttributes.typeCode}" 
			                                                  readOnly="true" />
								</td>
			                    <td> 
			                        <kul:htmlControlAttribute property="document.protocolList[0].protocolPersons[${personIndex}].attachmentPersonnels[${status.index}].description"
			                                                  attributeEntry="${protocolAttachmentPersonnelAttributes.description}" 
			                                                  readOnly="true" /> 
								</td>
			                    <td>
			                        <kra:fileicon attachment="${attachment.file}"/>
			                        <kul:htmlControlAttribute property="document.protocolList[0].protocolPersons[${personIndex}].attachmentPersonnels[${status.index}].file.name"
			                                                  attributeEntry="${narrativeAttributes.fileName}" 
			                                                  readOnly="true" />
								</td>
			                    <td>
			                    	<div align=center>
				                    	<html:image property="methodToCall.viewPersonnelAttachment.${protocolPerson}.line${status.index}"
								                    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton"
								                    alt="View Attachment" onclick="excludeSubmitRestriction = true;"/>
				                    	<kra:permission value="${KualiForm.personnelHelper.modifyPersonnel}">
					                        <html:image property="methodToCall.deletePersonnelAttachment.${protocolPerson}.line${status.index}" 
					                                    src="${ConfigProperties.kr.externalizable.images.url}tinybutton-delete1.gif" 
					                                    title="Remove Attachment" alt="Remove Attachment" styleClass="tinybutton" />
				                    	</kra:permission>
			                    	</div>
			                    </td>
			                  </tr>
		  				</c:forEach>
			            <%-- Existing data --%>
     					</tbody>
			        </table>
				</div>
			</kul:innerTab>
		</td>
	</tr>
</table>


