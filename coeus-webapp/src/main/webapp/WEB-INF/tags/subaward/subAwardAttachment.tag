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

<style type="text/css">
.voidShadeEnable td{
opacity:.6;
}

.voidShadeDisable td{
opacity:1;
}

</style>
<c:set var="subAwardAttachmentAttributes" value="${DataDictionary.SubAwardAttachments.attributes}" />
<c:set var="subAwardAttachmentFormBean" value="${KualiForm.subAwardAttachmentFormBean}" />
<c:set var="action" value="subAwardTemplateInformation" />
<c:set var="readOnly" value="${not KualiForm.editingMode['fullEntry']}" scope="request" />
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
         				<kul:htmlAttributeLabel attributeEntry="${subAwardAttachmentAttributes.subAwardAttachmentTypeCode}" noColon="false"/>
         			</div>
         		</th>
         		<th>
					<div align="center">
						<kul:htmlAttributeLabel attributeEntry="${subAwardAttachmentAttributes.description}" noColon="false"/>
					</div>
				</th>
         		<th>
					<div align="center">
						<kul:htmlAttributeLabel attributeEntry="${subAwardAttachmentAttributes.fileName}" noColon="false"/>
					</div>
				</th>
				<th>
         			<div align="center">
         				<kul:htmlAttributeLabel attributeEntry="${subAwardAttachmentAttributes.lastUpdateTimestamp}" noColon="false" />
         			</div>
         		</th>
         		<th>
         			<div align="center">
         				<kul:htmlAttributeLabel attributeEntry="${subAwardAttachmentAttributes.lastUpdateUser}" noColon="false" />
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
	             <c:if test="${!empty KualiForm.editingMode['fullEntry']}">
	                <td align="center" valign="middle" class="infoline">
	                	<div align="center">
	                		Add:
		            	</div>
					</td>
	         		<td class="infoline">
	              		<div align="center">
	            			<kul:htmlControlAttribute property="subAwardAttachmentFormBean.newAttachment.subAwardAttachmentTypeCode" attributeEntry="${subAwardAttachmentAttributes.subAwardAttachmentTypeCode}" readOnly="false" /> 
	              		</div>
	            	</td>
					 <td align="left" valign="middle" class="infoline">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="subAwardAttachmentFormBean.newAttachment.description" attributeEntry="${subAwardAttachmentAttributes.description}" readOnly="false"/>
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
	                		<kul:htmlControlAttribute property="subAwardAttachmentFormBean.newAttachment.lastUpdateTimestamp" attributeEntry="${subAwardAttachmentAttributes.lastUpdateTimestamp}" readOnly="true"/>
		            	</div>
					</td>
	                <td align="left" valign="middle" class="infoline">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="subAwardAttachmentFormBean.newAttachment.lastUpdateUser" attributeEntry="${subAwardAttachmentAttributes.lastUpdateUser}" readOnly="true"/>
		            	</div>
					</td> 
					<td align="center" valign="middle" class="infoline">
						<div align="center">
						<c:if test="${!readOnly}">
							<html:image property="methodToCall.addAttachment.anchor${tabKey}"
							src="${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif" styleClass="tinybutton addButton"/>
						</c:if>
						</div>
					</td>
				</c:if>
				</tr>
				</tbody>
				</c:if>

			<c:forEach var="attachment" items="${KualiForm.document.subAwardList[0].subAwardAttachments}" varStatus="itrStatus">
				<c:set var="count" value="${itrStatus.index}"/>
			<c:set var="modify" value="${KualiForm.document.subAwardList[0].subAwardAttachments[count].modifyAttachment}"/>
				<c:set var="voidShade" value="voidShadeDisable"/>
		    <c:if test="${KualiForm.document.subAwardList[0].subAwardAttachments[itrStatus.index].documentStatusCode == 'V' && !modify}">
		    <c:set var="voidShade" value="voidShadeEnable"/>
		    </c:if>
				<tr class="${voidShade}">
	         		<td>
	         			<div align="center">
	                		${itrStatus.index + 1}
		            	</div>
	         		</td>
	         		<td align="left" valign="middle">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAttachments[${itrStatus.index}].subAwardAttachmentTypeCode" attributeEntry="${subAwardAttachmentAttributes['subAwardAttachmentTypeCode']}" readOnly="${!modify}" readOnlyAlternateDisplay ="${subAwardAttachments.typeAttachment.description}"/>
		            	</div>
					</td>
					<td align="left" valign="middle">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAttachments[${itrStatus.index}].description" attributeEntry="${subAwardAttachmentAttributes.description}" readOnly="${!modify}"/>
		            	</div>
					</td>
	       			<td align="left" valign="middle">
	           			<div id="replaceInstDiv${itrStatus.index}" style="display:block;">
	           			<c:if test="${!readOnly || attachment.fileName!=null}"> 
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
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAttachments[${itrStatus.index}].lastUpdateTimestamp" attributeEntry="${subAwardAttachmentAttributes.lastUpdateTimestamp}" readOnly="true"/>
		            	</div>
					</td>
	         		<td align="left" valign="middle">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAttachments[${itrStatus.index}].lastUpdateUserName" attributeEntry="${subAwardAttachmentAttributes.lastUpdateUser}" readOnly="true"/>
		            	</div>
					</td>
					<td align="center" valign="middle">
						<div align="center">
					   <c:if test="${KualiForm.document.subAwardList[0].subAwardAttachments[itrStatus.index].documentStatusCode != 'V'}">
						<c:choose>
						<c:when test="${readOnly}">
						<c:if test="${!empty KualiForm.editingMode['fullEntry'] || !empty KualiForm.editingMode['viewOnly']}">
						<html:image property="methodToCall.viewAttachment.line${itrStatus.index}.anchor${currentTabIndex}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton"
								alt="View Attachment" onclick="excludeSubmitRestriction = true;"/>
						</c:if>
						</c:when>
						<c:otherwise>
						<html:image property="methodToCall.viewAttachment.line${itrStatus.index}.anchor${currentTabIndex}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton"
								alt="View Attachment" onclick="excludeSubmitRestriction = true;"/>
						</c:otherwise>
						</c:choose>
						   <c:choose>
						   <c:when test="${subAwardAttachmentFormBean.disableAttachmentRemovalIndicator == true}">
								<c:if test="${!empty KualiForm.editingMode['fullEntry'] && !readOnly}">
								<c:if test="${KualiForm.document.subAwardList[0].subAwardAttachments[itrStatus.index].documentStatusCode != 'V'}">
								<html:image property="methodToCall.voidAttachment.line${itrStatus.index}.anchor${currentTabIndex}"
									   src='${ConfigProperties.kra.externalizable.images.url}tinybutton-void.gif' styleClass="tinybutton"
									   alt="Void Attachment"/>
								</c:if>
							    <c:choose>
							      <c:when test="${!modify}">
							        <html:image property="methodToCall.modifyAttachment.line${itrStatus.index}.anchor${currentTabIndex}"
									   src='${ConfigProperties.kra.externalizable.images.url}tinybutton-modify.gif' styleClass="tinybutton"
									   alt="Modify Attachment"/>
							        </c:when>
							       <c:otherwise>
								   <html:image property="methodToCall.applyModifyAttachment.line${itrStatus.index}.anchor${currentTabIndex}"
						           src="${ConfigProperties.kra.externalizable.images.url}tinybutton-apply.gif" styleClass="tinybutton"/>
            	                  </c:otherwise>
            	                 </c:choose>
            	                 </c:if>
            	              </c:when>
						   <c:otherwise>
								    <html:image property="methodToCall.deleteAttachment.line${itrStatus.index}.anchor${currentTabIndex}"
									   src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"
									   alt="Delete Attachment"/>
									   <html:image styleId="replaceHistoryOfChangesAttachment.line${itrStatus.index}" 
												onclick="javascript: showHide('instFileDiv${itrStatus.index}','replaceInstDiv${itrStatus.index}') ; return false"  
												src='${ConfigProperties.kra.externalizable.images.url}tinybutton-replace.gif' styleClass="tinybutton"
												property="methodToCall.replaceNarrativeAttachment.line${itrStatus.index}.anchor${currentTabIndex};return false" />
							</c:otherwise>    
						   </c:choose>
					   </c:if>
					</div>
					</td>
	         	</tr>
			</c:forEach> 
		</table> 
     </div>	
</kul:tab>
