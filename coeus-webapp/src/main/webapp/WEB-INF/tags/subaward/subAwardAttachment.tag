<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2015 Kuali, Inc.
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
<c:set var="subAwardAttachmentAttributes" value="${DataDictionary.SubAwardAttachments.attributes}" />
<c:set var="subAwardAttachmentFormBean" value="${KualiForm.subAwardAttachmentFormBean}" />
<c:set var="action" value="subAwardTemplateInformation" />
<c:set var="attachments" value="${KualiForm.document.subAwardList[0].subAwardAttachments}"/>

<kul:tab tabTitle="Attachments" tabItemCount="${fn:length(attachments)}" defaultOpen="false" tabErrorKey="subAwardAttachmentFormBean.newAttachment*,document.subAwardList[0].subAwardAttachments*" transparentBackground="false" useRiceAuditMode="true">
	
	<div class="tab-container" align="center">
   		<h3>
   			<span class="subhead-left">Add Attachment</span>
   			<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.subaward.bo.SubAwardAttachments" altText="help"/></span>
       </h3>
        <table cellpadding="0" cellspacing="0" summary="">
         	<tr>
         	    <th>
         	    	&nbsp;
         	    </th>
         		 <th>
         			<div align="center">
         				<kul:htmlAttributeLabel attributeEntry="${subAwardAttachmentAttributes['subAwardAttachmentTypeCode']}" noColon="false"/>
         			</div>
         		</th>
         		<th>
					<div align="center">
						<kul:htmlAttributeLabel attributeEntry="${subAwardAttachmentAttributes.description}" noColon="false"/>
					</div>
				</th>
         		<th>
					<div align="center">
						<kul:htmlAttributeLabel attributeEntry="${subAwardAttachmentAttributes['fileId']}" noColon="false"/>
					</div>
				</th>
				<th>
         			<div align="center">
         				<kul:htmlAttributeLabel attributeEntry="${subAwardAttachmentAttributes.updateTimestamp}" noColon="false" />
         			</div>
         		</th>
         		<th>
         			<div align="center">
         				<kul:htmlAttributeLabel attributeEntry="${subAwardAttachmentAttributes.updateUser}" noColon="false" />
         			</div>
         		</th>
         		<th>
					<div align="center">
						Actions
					</div>
				</th> 
             </tr>
                <c:if test="${!readOnly}">
                <tbody class="addline">
	             <tr>
	                <td align="center" valign="middle" class="infoline">
	                	<div align="center">
	                		Add:
		            	</div>
					</td>
	         		<td class="infoline">
	              		<div align="center">
	            			<kul:htmlControlAttribute property="subAwardAttachmentFormBean.newAttachment.subAwardAttachmentTypeCode" attributeEntry="${subAwardAttachmentAttributes.subAwardAttachmentTypeCode}" /> 
	              		</div>
	            	</td>
					 <td align="left" valign="middle" class="infoline">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="subAwardAttachmentFormBean.newAttachment.description" attributeEntry="${subAwardAttachmentAttributes.description}"/>
		            	</div>
					</td>
					<td align="left" valign="middle" class="infoline">
	              		<div align="left">
	              		    <c:set var="property" value="subAwardAttachmentFormBean.newAttachment.newFile" />
	              		
	              		    <!-- attachment file error handling logic start -->
	               				<kul:checkErrors keyMatch="${property}" auditMatch="${property}"/>
	               				<!-- highlighting does not work in firefox but does in ie... -->
	               				<c:set var="textStyle" value="${hasErrors == true ? 'background-color:#FFD5D5' : ''}"/>
	               			<!-- attachment file error handling logic start -->
	              		
	              			<html:file property="${property}" style="${textStyle}"/>
	           			</div>
					</td>
					<td align="left" valign="middle" class="infoline">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="subAwardAttachmentFormBean.newAttachment.updateTimestamp" attributeEntry="${subAwardAttachmentAttributes.updateTimestamp}" readOnly="true"/>
		            	</div>
					</td>
	                <td align="left" valign="middle" class="infoline">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="subAwardAttachmentFormBean.newAttachment.updateUser" attributeEntry="${subAwardAttachmentAttributes.updateUser}" readOnly="true"/>
		            	</div>
					</td> 
					<td align="center" valign="middle" class="infoline">
						<div align="center">
							<html:image property="methodToCall.addAttachment.anchor${tabKey}"
							src="${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif" styleClass="tinybutton addButton"/>
						</div>
					</td>
				</tr>
				</tbody>
			 </c:if> 
			 
			<c:forEach var="attachment" items="${KualiForm.document.subAwardList[0].subAwardAttachments}" varStatus="itrStatus">
				<tr>
	         		<td>
	         			<div align="center">
	                		${itrStatus.index + 1}
		            	</div>
	         		</td>
	         		<td align="left" valign="middle">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAttachments[${itrStatus.index}].subAwardAttachmentTypeCode" attributeEntry="${subAwardAttachmentAttributes['subAwardAttachmentTypeCode']}" readOnly="true" readOnlyAlternateDisplay ="${subAwardAttachments.typeAttachment.description}"/>
		            	</div>
					</td>
					<td align="left" valign="middle">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAttachments[${itrStatus.index}].description" attributeEntry="${subAwardAttachmentAttributes.description}" readOnly="true"/>
		            	</div>
					</td>
	       			<td align="left" valign="middle">
	           			<div id="replaceInstDiv${itrStatus.index}" style="display:block;">
	           			<c:if test="${attachment.fileName!=null}"> 
							<kra:fileicon attachment="${attachment}" />
							 </c:if> 
					       <kul:htmlControlAttribute property="document.subAwardList[0].subAwardAttachments[${itrStatus.index}].fileName" 
					       		readOnly="true" attributeEntry="${subAwardAttachmentAttributes.fileName}" />  
				        </div>
				        <div id="instFileDiv${itrStatus.index}" valign="middle" style="display:none;">
				           	<html:file property="document.subAwardList[0].subAwardAttachments[${itrStatus.index}].newFile" />
							<html:image property="methodToCall.replaceHistoryOfChangesAttachment.line${itrStatus.index}.anchor${currentTabIndex}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
						</div>
					</td>
					<td align="left" valign="middle">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAttachments[${itrStatus.index}].updateTimestamp" attributeEntry="${subAwardAttachmentAttributes.updateTimestamp}" readOnly="true"/>
		            	</div>
					</td>
	         		<td align="left" valign="middle">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAttachments[${itrStatus.index}].updateUserName" attributeEntry="${subAwardAttachmentAttributes.updateUser}" readOnly="true"/>
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
									   <html:image styleId="replaceHistoryOfChangesAttachment.line${itrStatus.index}" 
												onclick="javascript: showHide('instFileDiv${itrStatus.index}','replaceInstDiv${itrStatus.index}') ; return false"  
												src='${ConfigProperties.kra.externalizable.images.url}tinybutton-replace.gif' styleClass="tinybutton"
												property="methodToCall.replaceNarrativeAttachment.line${itrStatus.index}.anchor${currentTabIndex};return false" />
							    </c:if>
						</div>
					</td>
	         	</tr>
			</c:forEach> 
		</table> 
     </div>	
</kul:tab>
