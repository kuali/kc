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
<c:set var="kraAttributeReferenceDummyAttributes" value="${DataDictionary.KraAttributeReferenceDummy.attributes}" />

<c:set var="protocolDocumentAttributes" value="${DataDictionary.ProtocolDocument.attributes}" />
<c:set var="action" value="protocolProtocolActions" />
<c:set var="protocolAttachmentBaseAttributes" value="${DataDictionary.ProtocolAttachmentBase.attributes}" />
<c:set var="protocolAttachmentFileAttributes" value="${DataDictionary.ProtocolAttachmentFile.attributes}" />
<c:set var="protocolAttachmentTypeAttributes" value="${DataDictionary.ProtocolAttachmentType.attributes}" />
<c:set var="protocolAttachmentStatusAttributes" value="${DataDictionary.ProtocolAttachmentStatus.attributes}" />
${kfunc:registerEditableProperty(KualiForm, "actionHelper.printTag")}

<kul:tab tabTitle="Print" defaultOpen="false" tabErrorKey="actionHelper.reportType">
    <div class="tab-container" align="left">
        <h3>
            <span class="subhead-left">Print</span>
        </h3>

        <table cellpadding="0" cellspacing="0" summary="print forms">
            <tr>
                <td>Summary View</td>
                <td style="text-align:center;">
                       <div align="center">
                            <kul:htmlControlAttribute property="actionHelper.summaryReport" 
                                                      attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                      readOnly="false" />
                        </div>
                </td>
            </tr>
            <tr>
                <td>Full Protocol</td>
                <td style="text-align:center;">
                       <div align="center">
                            <kul:htmlControlAttribute property="actionHelper.fullReport" 
                                                      attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                      readOnly="false" />
                        </div>
                </td>
            </tr>
            <tr>
                <td>Protocol History</td>
                <td style="text-align:center;">
                       <div align="center">
                            <kul:htmlControlAttribute property="actionHelper.historyReport" 
                                                      attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                      readOnly="false" />
                        </div>
                </td>
            </tr>
            <tr>
                <td>Review Comments</td>
                <td style="text-align:center;">
                       <div align="center">
                            <kul:htmlControlAttribute property="actionHelper.reviewCommentsReport" 
                                                      attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                      readOnly="false" />
                        </div>
                </td>
            </tr>

            <tr>
                <td class="infoline">&nbsp;</td>
                <td class="infoline" style="text-align:center;">
                    <html:image property="methodToCall.printProtocolDocument.line${ctr}.anchor${currentTabIndex}"
                                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-printsel.gif' 
                                styleClass="tinybutton" onclick="excludeSubmitRestriction = true;"/>                         
                </td>
            </tr>
	        <c:if test="${fn:length(KualiForm.document.protocolList[0].activeAttachmentProtocols) > 0}">
	            <tr>
	                <td class="tab-subhead" colspan="2">Protocol Attachments</td>
	            </tr>
	
	            <c:forEach var="protocolAttachment" items="${KualiForm.document.protocolList[0].activeAttachmentProtocols}" varStatus="status">
	                <tr>
	                    <td>
	                        ${protocolAttachment.description} - ${protocolAttachment.status.description} (${protocolAttachment.file.name})
	                        <%--<kul:htmlControlAttribute property="document.protocolList[0].attachmentProtocols[${status.index}].file.fileName"
	                                                  attributeEntry="${protocolAttachmentBaseAttributes.description}"
	                                                  readOnly="true" /> --%>
	                    </td>
			            <td align="center" valign="middle">
	                        <div align="center">
	                             <html:image property="methodToCall.viewProtocolAttachment.line${status.index}.anchor${currentTabIndex}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' 
										alt="View Attachment" onclick="excludeSubmitRestriction = true;" styleClass="tinybutton"/>
						     </div>
					     </td>
	                    
	                </tr>     
	            </c:forEach>
           </c:if>
           <c:if test="${fn:length(KualiForm.document.protocolList[0].attachmentPersonnels) > 0}">
           	<tr>
                <td class="tab-subhead" colspan="2">Personnel Attachments</td>
            </tr>
           	<c:forEach var="attachmentPersonnel" items="${KualiForm.document.protocolList[0].attachmentPersonnels}" varStatus="status">
           		<tr>
	                    <td>
	                        ${attachmentPersonnel.description} - ${attachmentPersonnel.type.description} (${attachmentPersonnel.file.name})
	                    </td>
			            <td align="center" valign="middle">
	                        <div align="center">
	                             <html:image property="methodToCall.viewProtocolPersonnelAttachment.line${status.index}.anchor${currentTabIndex}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' 
										alt="View Attachment" onclick="excludeSubmitRestriction = true;" styleClass="tinybutton"/>
						     </div>
					     </td>
	                    
	                </tr>
	          </c:forEach>
           </c:if>
        </table>
    </div>
</kul:tab>

