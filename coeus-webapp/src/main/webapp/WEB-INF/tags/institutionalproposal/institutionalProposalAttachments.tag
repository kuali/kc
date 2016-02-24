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
td.infoline select {
    width:100%;
}
td select {
    width:100%;
}
.voidShadeEnable td{
opacity:.6;
}

.voidShadeDisable td{
opacity:1;
}

</style>

<c:set var="attachments" value="${KualiForm.document.institutionalProposalList[0].instProposalAttachments}"/>
<c:set var="instproposalAttachmentAttributes" value="${DataDictionary.InstitutionalProposalAttachment.attributes}" />
<c:set var="institutionalProposalAttachmentBean" value="${KualiForm.institutionalProposalAttachmentBean}" />
<c:set var="action" value="institutionalProposalAttachments"/>
<c:set var="readOnly" value="${not KualiForm.editingMode['fullEntry']}" scope="request" />
<c:set var="disableAttachmentRemovalIndicator" value="${institutionalProposalAttachmentBean.disableAttachmentRemovalIndicator}"/>

<kul:tabTop tabTitle="Attachments" defaultOpen="true" tabErrorKey="document.*,institutionalProposalAttachmentBean.*">
	<div class="tab-container" align="center">
   		<h3>
   			<span class="subhead-left">Add Attachment</span>
   		</h3>
       <table id="attachments-table" cellpadding="4" cellspacing="0" summary="">
         	<tr>
         	    <th>
         	    	&nbsp;
         	    </th>
         	    <th>
         			<div align="center">
         				<kul:htmlAttributeLabel attributeEntry="${instproposalAttachmentAttributes.attachmentTypeCode}" noColon="false"/>
         			</div>
         		</th>
         		<th>
         			<div align="center">
         				<kul:htmlAttributeLabel attributeEntry="${instproposalAttachmentAttributes.attachmentTitle}" noColon="false"/>
         			</div>
         		</th>
         		
         		<th>
         			<div align="center">
         				<kul:htmlAttributeLabel attributeEntry="${instproposalAttachmentAttributes.comments}" noColon="false"/>
         			</div>
         		</th>
         		
         		<th>
					<div align="center">
						<kul:htmlAttributeLabel attributeEntry="${instproposalAttachmentAttributes['fileName']}" noColon="false"/>
					</div>
				</th>
				<th>
         			<div align="center">
         				<kul:htmlAttributeLabel attributeEntry="${instproposalAttachmentAttributes.lastUpdateTimestamp}" noColon="false" />
         			</div>
         		</th>
         		<th>
         			<div align="center">
         				<kul:htmlAttributeLabel attributeEntry="${instproposalAttachmentAttributes.lastUpdateUser}" noColon="false" />
         			</div>
         		</th>
         	
         		<th>
					<div align="center">
						Actions
					</div>
				</th>
				
             </tr>
             
                 <tbody class="addline">
	             <tr>
	             <c:if test="${!empty KualiForm.documentActions['CAN_MAINTAIN_IP_ATTACHMENTS']}">
	                <td align="center" valign="middle" class="infoline">
	                	<div align="center">
	                		Add:
		            	</div>
					</td>
					<td align="left" valign="middle" class="infoline">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="institutionalProposalAttachmentBean.newAttachment.attachmentTypeCode" attributeEntry="${instproposalAttachmentAttributes.attachmentTypeCode}" readOnly="false"/> 
		            	</div>
					</td>
					<td align="left" valign="middle" class="infoline">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="institutionalProposalAttachmentBean.newAttachment.attachmentTitle" attributeEntry="${instproposalAttachmentAttributes.attachmentTitle}" readOnly="false"/> 
		            	</div>
					</td>
	                <td class="infoline">
	              		<div align="center">
	            			<kul:htmlControlAttribute property="institutionalProposalAttachmentBean.newAttachment.comments" attributeEntry="${instproposalAttachmentAttributes.comments}" readOnly="false"/> 
	              		</div>
	            	</td>
	                <td align="left" valign="middle" class="infoline">
	              		<div align="left">
	              		    <c:set var="property" value="institutionalProposalAttachmentBean.newAttachment.newFile" />
	              		    <html:file property="${property}"/>
	           			</div>
					</td> 
					<td align="left" valign="middle" class="infoline">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="institutionalProposalAttachmentBean.newAttachment.lastUpdateTimestamp" attributeEntry="${instproposalAttachmentAttributes.lastUpdateTimestamp}" readOnly="true"/>
		            	</div>
					</td>
	                <td align="left" valign="middle" class="infoline">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="institutionalProposalAttachmentBean.newAttachment.lastUpdateUser" attributeEntry="${instproposalAttachmentAttributes.lastUpdateUser}" readOnly="true"/>
		            	</div>
					</td> 
					 
					<td align="center" valign="middle" class="infoline">
						<div align="center">
							<html:image property="methodToCall.addAttachment.anchor${tabKey}"
							src="${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif" styleClass="tinybutton addButton"/>
						</div>
					</td>
			   </c:if>
			   </tr>
			<tr>
			
			<c:forEach var="attachment" items="${attachments}" varStatus="itrStatus">
			
			<c:set var="count" value="${itrStatus.index}"/>
			<c:set var="modify" value="${KualiForm.document.institutionalProposalList[0].instProposalAttachments[count].modifyAttachment}"/>
		    <c:set var="voidShade" value="voidShadeDisable"/>
		    <c:if test="${KualiForm.document.institutionalProposalList[0].instProposalAttachments[itrStatus.index].documentStatusCode == 'V'  && !modify}">
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
	                		<kul:htmlControlAttribute property="document.institutionalProposalList[0].instProposalAttachments[${itrStatus.index}].attachmentTypeCode" attributeEntry="${instproposalAttachmentAttributes.attachmentTypeCode}" readOnly="${activeModify}"/>
		            	</div>
					</td>
					<td align="left" valign="middle">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="document.institutionalProposalList[0].instProposalAttachments[${itrStatus.index}].attachmentTitle" attributeEntry="${instproposalAttachmentAttributes.attachmentTitle}" readOnly="${!modify}"/>
		            	</div>
					</td>
					<td align="left" valign="middle">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="document.institutionalProposalList[0].instProposalAttachments[${itrStatus.index}].comments" attributeEntry="${instproposalAttachmentAttributes.comments}" readOnly="${!modify}"/>
		            	</div>
					</td>
					 <td align="left" valign="middle">
	           			<div align="left" id="attachmentFileName${itrStatus.index}">
	           			<c:if test="${!readOnly || attachment.fileName!=null}">
							<img src="${krafn:getIconPath(attachment.type)}" height="16" width="16" alt="${attachment.type}" title="${attachment.type}"/>${attachment.fileName}
	              	   </c:if>
	           			</div>
					</td> 
					<td align="left" valign="middle">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="document.institutionalProposalList[0].instProposalAttachments[${itrStatus.index}].lastUpdateTimestamp" attributeEntry="${instproposalAttachmentAttributes.lastUpdateTimestamp}" readOnly="true"/>
		            	</div>
					</td>
	         		<td align="left" valign="middle">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="document.institutionalProposalList[0].instProposalAttachments[${itrStatus.index}].lastUpdateUserName" attributeEntry="${instproposalAttachmentAttributes.lastUpdateUser}" readOnly="true"/>
		            	</div>
					</td>
					 <td align="center" valign="middle">
						<div align="center">
						<c:if test="${KualiForm.document.institutionalProposalList[0].instProposalAttachments[itrStatus.index].documentStatusCode != 'V'}">
						<c:choose>
						<c:when test="${readOnly}">
						<c:if test="${!empty KualiForm.documentActions['CAN_VIEW_IP_ATTACHMENTS']}">
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
						<c:if test="${!empty KualiForm.documentActions['CAN_MAINTAIN_IP_ATTACHMENTS']}">
						<c:choose>
							<c:when test="${institutionalProposalAttachmentBean.disableAttachmentRemovalIndicator == true }">
								<html:image property="methodToCall.voidAttachment.line${itrStatus.index}.anchor${currentTabIndex}"
									   src='${ConfigProperties.kra.externalizable.images.url}tinybutton-void.gif' styleClass="tinybutton"
									   alt="Void Attachment"/>
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
						   </c:when>
						   <c:otherwise>
								<html:image property="methodToCall.deleteAttachment.line${itrStatus.index}.anchor${currentTabIndex}"
									   src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"
									   alt="Delete Attachment"/>
						   </c:otherwise>
						   </c:choose>
						   </c:if>
						  </div>
						 </c:if>
					</td>  
				</tr>
			</c:forEach>
			</tr>
			</tbody> 
		</table>
     </div>		
</kul:tabTop>
