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

<%@ attribute name="attachmentViewAction" required="false" %>
<c:if test="${attachmentViewAction == null}">
	<c:set var="attachmentViewAction" value="meetingManagement" />
</c:if>

<%@ attribute name="htmlFormAction" required="false" %>
<%@ attribute name="renderMultipart" required="false" %>
<c:set var="commScheduleAttachmentAttributes" value="${DataDictionary.CommitteeScheduleAttachments.attributes}" />
<kul:tab defaultOpen="false" tabTitle="Attachments" 
		tabErrorKey="meetingHelper.newCommitteeScheduleAttachments.*"  tabAuditKey="meetingHelper.newCommitteeScheduleAttachments.attachmentsTypeCode*,meetingHelper.newCommitteeScheduleAttachments.newFile*" useRiceAuditMode="true">

	<div class="tab-container" align="center">
    	<h3>
        	<span class="subhead-left"> Attachments </span>
        	<span class="subhead-right">  <kul:help businessObjectClassName="org.kuali.kra.meeting.CommitteeScheduleAttachments" altText="help"/>  </span>
    	</h3>       
        <table id="Atachments-table" cellpadding=0 cellspacing=0 class="datatable" summary="View/edit meeting other actions">
        
        	<%-- Header --%>

			<tr height="30" >
				<kul:htmlAttributeHeaderCell literalLabel="&nbsp"  scope="col" />
				<!-- to display the drop down -->
				<kul:htmlAttributeHeaderCell attributeEntry="${commScheduleAttachmentAttributes.attachmentsTypeCode}" scope="col" />
				<!-- display description -->
				<kul:htmlAttributeHeaderCell attributeEntry="${commScheduleAttachmentAttributes.description}" scope="col" />
				<!-- display filename -->
				<kul:htmlAttributeHeaderCell attributeEntry="${commScheduleAttachmentAttributes.fileName}" scope="col"  colspan="2"/>
				<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" />
			</tr>
			<%-- Header ends --%>
			
			
			<%-- New Data --%>
			<c:if test="${!readOnly}">
			<tr>
				<th class="infoline" >
				    <c:out value="Add:" />
				</th>
				<td>
					    <div align="center">
	               		    <kul:htmlControlAttribute property="meetingHelper.newCommitteeScheduleAttachments.attachmentsTypeCode" attributeEntry="${commScheduleAttachmentAttributes.attachmentsTypeCode}" readOnly="false" />
	            	    </div>
	            </td>
				<td align="left" valign="middle" class="infoline" >
	                    <div align="middle">
	               	         <kul:htmlControlAttribute property="meetingHelper.newCommitteeScheduleAttachments.description" attributeEntry="${commScheduleAttachmentAttributes.description}" readOnly="false" />
	               	    </div>
	            </td>
				<td align="left" valign="middle" colspan="2">
						  <c:if test="${readOnly!='true'}">
                			<html:file property="meetingHelper.newCommitteeScheduleAttachments.newFile" />
                			</c:if>
				</td>
				<td align="left" valign="middle" class="infoline"  >
					    <div align=center>
					        <html:image property="methodToCall.addCommitteeScheduleAttachment.anchor${tabKey}"
						    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
						</div>
				</td>
		  </tr>
		  </c:if> 
				<%--New data --%>
				
				<%-- Existing data --%>
				
		  <c:forEach var="newCommitteeScheduleAttachments" items="${KualiForm.meetingHelper.committeeSchedule.committeeScheduleAttachments}" varStatus="status">
		  <tr>
				<th width="5%" class="infoline" rowspan="2">
					<c:out value="${status.index+1}" />
					&nbsp;
				</th>
				<td width="9%" valign="middle">
	            	<div align="center">
	               		${newCommitteeScheduleAttachments.attachmentsEntryType.description}               	       
	            	</div>
				</td>
				<td width="9%" valign="middle">
	                <div align="middle">
	               		<kul:htmlControlAttribute property="meetingHelper.committeeSchedule.committeeScheduleAttachments[${status.index}].description" attributeEntry="${commScheduleAttachmentAttributes.description}" readOnly="false" />
	               	</div>
	           	</td>
			    <td width="9%" valign="middle" colspan="2">
					<div align="center"></div>
					<div id="replaceDiv${status.index}" style="display: block;">
							<kra:fileicon attachment="${newCommitteeScheduleAttachments}" />
					<kul:htmlControlAttribute 
 						property="meetingHelper.committeeSchedule.committeeScheduleAttachments[${status.index}].fileName"
						readOnly="true" 
						attributeEntry="${commScheduleAttachmentAttributes.fileName}" />
				   </div>
				   <div id="fileDiv${status.index}" valign="middle" style="display:none;">
				   		<html:file
								property="meetingHelper.committeeSchedule.committeeScheduleAttachments[${status.index}].newFile" />
						<html:image
								property="methodToCall.replaceCommitteeScheduleAttachmentsAttachment.line${status.index}.anchor${currentTabIndex}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif'
								styleClass="tinybutton" />
					</div>
				</td>
				<td width="10%" valign="middle"  rowspan="2" >
				<div align="center">
					Attachment Actions :
				</div>					
				<c:out value="${KualiForm.formKey}" />
				<div align="center">
					<c:if test="${newCommitteeScheduleAttachments.fileName!=null}">
					<html:image
					styleId="downloadCommitteScheduleAttachmentsAttachment.line${status.index}"
					property="methodToCall.downloadCommitteScheduleAttachmentsAttachment.line${status.index}.anchor${currentTabIndex}"
					src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif'
					styleClass="tinybutton"
					onclick="javascript: openNewWindow('${attachmentViewAction}','downloadCommitteScheduleAttachmentsAttachment','${status.index}',0,'true'); return false" />
					</c:if>
						<c:if test="${!readOnly}">	
						<html:image
						styleId="replaceCommitteScheduleAttachmentsAttachment.line${status.index}"
						onclick="javascript: showHide('fileDiv${status.index}','replaceDiv${status.index}') ; return false"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-replace.gif'
						styleClass="tinybutton"
						property="methodToCall.replaceCommitteScheduleAttachmentsAttachment.line${status.index}.anchor${currentTabIndex};return false" />
							<c:if test="${newCommitteeScheduleAttachments.fileName!=null}">
								<html:image
								property="methodToCall.deleteCommitteScheduleAttachmentsAttachment.line${status.index}.anchor${currentTabIndex}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif'
									styleClass="tinybutton" />
							</c:if>
						</c:if>
					<c:if test="${readOnly}">&nbsp;</c:if>
				</div>
				</td>
		  </tr>
		  <tr>
		  	 <th valign="middle"><div align="center">Update User :</div></th>
             <td>
             	<c:out value="${newCommitteeScheduleAttachments.newUpdateUser}"/>
             </td>
		     <th  valign="middle"><div align="center">Update Timestamp :</div></th>
             <td>
             	<fmt:formatDate value="${newCommitteeScheduleAttachments.newUpdateTimestamp}" pattern=" MM/dd/yyyy KK:mm a" />
			 </td>
		  </tr>
		  </c:forEach>
         </table>
    </div>
</kul:tab>
