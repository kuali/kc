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

<c:set var="protocolDocumentAttributes" value="${DataDictionary.ProtocolDocument.attributes}" />
<c:set var="action" value="protocolProtocolActions" />
<c:set var="protocolAttachmentBaseAttributes" value="${DataDictionary.ProtocolAttachmentBase.attributes}" />
<c:set var="protocolAttachmentFileAttributes" value="${DataDictionary.ProtocolAttachmentFile.attributes}" />
<c:set var="protocolAttachmentTypeAttributes" value="${DataDictionary.ProtocolAttachmentType.attributes}" />
<c:set var="protocolAttachmentStatusAttributes" value="${DataDictionary.ProtocolAttachmentStatus.attributes}" />
${kfunc:registerEditableProperty(KualiForm, "actionHelper.printTag")}

<kul:tab tabTitle="Print" defaultOpen="false" tabErrorKey="">
    <div class="tab-container" align="left">
        <h3>
            <span class="subhead-left">Print</span>
            <span class="subhead-right"><kul:help businessObjectClassName="" altText="help"/></span>
        </h3>

        <table cellpadding="0" cellspacing="0" summary="print forms">
            <tr>
                <td>Summary View</td>
                <td style="text-align:center;"><input name="actionHelper.printTag" type="radio" class="nobord" value="summary"></td>
            </tr>
            <tr>
                <td>Full Protocol</td>
                <td style="text-align:center;"><input name="actionHelper.printTag" type="radio" class="nobord" value="full"></td>
            </tr>
            <tr>
                <td>Protocol History</td>
                <td style="text-align:center;"><input name="actionHelper.printTag" type="radio" class="nobord" value="history"></td>
            </tr>
            <tr>
                <td>Review Comments</td>
                <td style="text-align:center;"><input name="actionHelper.printTag" type="radio" class="nobord" value="comments"></td>
            </tr>

            <tr>
                <td class="tab-subhead" colspan="2">Attachments</td>
            </tr>

            <c:forEach var="protocolAttachment" items="${KualiForm.document.protocolList[0].activeAttachmentProtocols}" varStatus="status">
                <tr>
                    <td>
                        ${protocolAttachment.description} - ${protocolAttachment.status.description} (${protocolAttachment.file.name})
                        <%--<kul:htmlControlAttribute property="document.protocolList[0].attachmentProtocols[${status.index}].file.fileName"
                                                  attributeEntry="${protocolAttachmentBaseAttributes.description}"
                                                  readOnly="true" /> --%>
                    </td>
                    <td style="text-align:center;"><input name="actionHelper.printTag" type="radio" class="nobord" value="attachment:${status.index}"></td>
                </tr>     
            </c:forEach>
    
            <tr>
                <td class="infoline">&nbsp;</td>
                <td class="infoline" style="text-align:center;">
                    <html:image property="methodToCall.printProtocolDocument.line${ctr}.anchor${currentTabIndex}"
                                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-printsel.gif' 
                                styleClass="tinybutton" onclick="excludeSubmitRestriction = true;"/>                         
                </td>
            </tr>
        </table>
    </div>
</kul:tab>

