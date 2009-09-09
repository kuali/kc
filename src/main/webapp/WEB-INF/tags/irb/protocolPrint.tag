<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="protocolDocumentAttributes" value="${DataDictionary.ProtocolDocument.attributes}" />
<c:set var="action" value="protocolProtocolActions" />
<c:set var="protocolAttachmentBaseAttributes" value="${DataDictionary.ProtocolAttachmentBase.attributes}" />
<c:set var="protocolAttachmentFileAttributes" value="${DataDictionary.ProtocolAttachmentFile.attributes}" />
<c:set var="protocolAttachmentTypeAttributes" value="${DataDictionary.ProtocolAttachmentType.attributes}" />
<c:set var="protocolAttachmentStatusAttributes" value="${DataDictionary.ProtocolAttachmentStatus.attributes}" />

<kul:tab tabTitle="Print" defaultOpen="true" tabErrorKey="">
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
            
            <c:forEach var="protocolAttachment" items="${protocol.attachmentProtocols}" varStatus="status">
                <tr>
                    <td>
                        <kul:htmlControlAttribute property="document.protocolList[0].attachmentProtocol[${status.index}].file.description"
                                                  attributeEntry="${protocolAttachmentBaseAttributes.description}"
                                                  readOnly="true" />
                    </td>
                    <td style="text-align:center;"><input name="actionHelper.printTag" type="radio" class="nobord" value="attachment:${status.index}"></td>
                </tr>     
            </c:forEach>
    
            <tr>
                <td class="infoline">&nbsp;</td>
                <td class="infoline" style="text-align:center;">
                    <html:image property="methodToCall.printProtocolDocument.line${ctr}.anchor${currentTabIndex}"
                                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-printsel.gif' 
                                styleClass="tinybutton"/>                         
                </td>
            </tr>
        </table>
    </div>
</kul:tab>

