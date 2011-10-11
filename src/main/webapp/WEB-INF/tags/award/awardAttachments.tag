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

<c:set var="awardAttachmentAttributes" value="${DataDictionary.AwardAttachment.attributes}" />
<c:set var="awardAttachmentFormBean" value="${KualiForm.awardAttachmentFormBean}" />
<c:set var="action" value="awardNotesAndAttachments" />
<c:set var="attachments" value="${KualiForm.document.awardList[0].awardAttachments}"/>

<kul:tab tabTitle="Attachments" tabItemCount="${fn:length(attachments)}" defaultOpen="false" tabErrorKey="awardAttachmentFormBean.newAttachment*,document.awardList[0].awardAttachments*" transparentBackground="false">
	<div class="tab-container" align="center">
   		<h3>
   			<span class="subhead-left">Add Attachment</span>
   			<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.award.notesandattachments.attachments.AwardAttachment" altText="help"/></span>
       </h3>
       <table id="attachments-table" cellpadding="4" cellspacing="0" summary="">
         	<tr>
         	    <th>
         	    	&nbsp;
         	    </th>
         		<th>
         			<div align="center">
         				<kul:htmlAttributeLabel attributeEntry="${awardAttachmentAttributes.updateTimestamp}" noColon="false" />
         			</div>
         		</th>
         		<th>
         			<div align="center">
         				<kul:htmlAttributeLabel attributeEntry="${awardAttachmentAttributes.updateUser}" noColon="false" />
         			</div>
         		</th>
         		<th>
         			<div align="center">
         				<kul:htmlAttributeLabel attributeEntry="${awardAttachmentAttributes['typeCode']}" noColon="false"/>
         			</div>
         		</th>
         		<th>
					<div align="center">
						<kul:htmlAttributeLabel attributeEntry="${awardAttachmentAttributes.description}" noColon="false"/>
					</div>
				</th>
         		<th>
					<div align="center">
						<kul:htmlAttributeLabel attributeEntry="${awardAttachmentAttributes['fileId']}" noColon="false"/>
					</div>
				</th>
         		<th>
					<div align="center">
						Actions
					</div>
				</th>
             </tr>
             
                <c:if test="${!readOnly}">
	             <tr>
	                <td align="center" valign="middle" class="infoline">
	                	<div align="center">
	                		Add:
		            	</div>
					</td>
	                <td align="left" valign="middle" class="infoline">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="awardAttachmentFormBean.newAttachment.updateTimestamp" attributeEntry="${awardAttachmentAttributes.updateTimestamp}" readOnly="true"/>
		            	</div>
					</td>
	                <td align="left" valign="middle" class="infoline">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="awardAttachmentFormBean.newAttachment.updateUser" attributeEntry="${awardAttachmentAttributes.updateUser}" readOnly="true"/>
		            	</div>
					</td>
	         		<td class="infoline">
	              		<div align="center">
	            			<kul:htmlControlAttribute property="awardAttachmentFormBean.newAttachment.typeCode" attributeEntry="${awardAttachmentAttributes.typeCode}" />
	              		</div>
	            	</td>
					<td align="left" valign="middle" class="infoline">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="awardAttachmentFormBean.newAttachment.description" attributeEntry="${awardAttachmentAttributes.description}"/>
		            	</div>
					</td>
					<td align="left" valign="middle" class="infoline">
	              		<div align="left">
	              		    <c:set var="property" value="awardAttachmentFormBean.newAttachment.newFile" />
	              		
	              		    <%-- attachment file error handling logic start--%>
	               				<kul:checkErrors keyMatch="${property}" auditMatch="${property}"/>
	               				<%-- highlighting does not work in firefox but does in ie... --%>
	               				<c:set var="textStyle" value="${hasErrors == true ? 'background-color:#FFD5D5' : ''}"/>
	               			<%-- attachment file error handling logic start--%>
	              		
	              			<html:file property="${property}" style="${textStyle}"/>
	           			</div>
					</td>
					<td align="center" valign="middle" class="infoline">
						<div align="center">
							<html:image property="methodToCall.addAttachment.anchor${tabKey}"
							src="${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif" styleClass="tinybutton"/>
						</div>
					</td>
				</tr>
			 </c:if>
				
			<c:forEach var="attachment" items="${attachments}" varStatus="itrStatus">
				<tr>
	         		<td>
	         			<div align="center">
	                		${itrStatus.index + 1}
		            	</div>
	         		</td>
	         		<td align="left" valign="middle">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="document.awardList[0].awardAttachments[${itrStatus.index}].updateTimestamp" attributeEntry="${awardAttachmentAttributes.updateTimestamp}" readOnly="true"/>
		            	</div>
					</td>
	         		<td align="left" valign="middle">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="document.awardList[0].awardAttachments[${itrStatus.index}].updateUserName" attributeEntry="${awardAttachmentAttributes.updateUser}" readOnly="true"/>
		            	</div>
					</td>
	         		<td align="left" valign="middle">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="document.awardList[0].awardAttachments[${itrStatus.index}].type.description" attributeEntry="${awardAttachmentAttributes['typeCode']}" readOnly="true" readOnlyAlternateDisplay ="${awardAttachment.type.description}"/>
		            	</div>
					</td>
					<td align="left" valign="middle">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="document.awardList[0].awardAttachments[${itrStatus.index}].description" attributeEntry="${awardAttachmentAttributes.description}" readOnly="true"/>
		            	</div>
					</td>
	       			<td align="left" valign="middle">
	           			<div align="left" id="attachmentFileName${itrStatus.index}">
	              			<kra:fileicon attachment="${attachment.file}"/>${attachment.file.name}
	           			</div>
					</td>
					<td align="center" valign="middle">
						<div align="center">
							<html:image property="methodToCall.viewAttachment.line${itrStatus.index}.anchor${currentTabIndex}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton"
								alt="View Attachment" onclick="excludeSubmitRestriction = true;"/>
								<c:if test="${!readOnly}">
								    <html:image property="methodToCall.deleteAttachment.line${itrStatus.index}.anchor${currentTabIndex}"
									   src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"
									   alt="Delete Attachment"/>
							    </c:if>
						</div>
					</td>
	         	</tr>
			</c:forEach>
		</table>
     </div>		
</kul:tab>
