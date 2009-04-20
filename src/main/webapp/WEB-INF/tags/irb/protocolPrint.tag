<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="protocolDocumentAttributes" value="${DataDictionary.ProtocolDocument.attributes}" />
<c:set var="action" value="protocolProtocolActions" />
<c:set var="protocolAttachmentBaseAttributes" value="${DataDictionary.ProtocolAttachmentBase.attributes}" />
<c:set var="protocolAttachmentFileAttributes" value="${DataDictionary.ProtocolAttachmentFile.attributes}" />
<c:set var="protocolAttachmentTypeAttributes" value="${DataDictionary.ProtocolAttachmentType.attributes}" />
<c:set var="protocolAttachmentStatusAttributes" value="${DataDictionary.ProtocolAttachmentStatus.attributes}" />

<kul:innerTab parentTab="Summary, History, & Print" defaultOpen="true" tabTitle="Print">

            <table cellpadding=0 cellspacing="0" summary="print forms">
                

                    <tr>
                        <td>Summary View</td>
                        <td style="text-align:center;"><input name="d" type="radio" class="nobord"></td>
                    </tr>
                    <tr>
                        <td>Full Protocol</td>
                        <td style="text-align:center;"><input name="d" type="radio" class="nobord"></td>
                    </tr>

                    <tr>
                        <td>Protocol History</td>
                        <td style="text-align:center;"><input name="d" type="radio" class="nobord"></td>
                    </tr>
                    <tr>
                        <td>Review Comments</td>
                        <td style="text-align:center;"><input name="d" type="radio" class="nobord"></td>
                    </tr>

                    <tr>
                    	<td class="tab-subhead" colspan="2">Attachments</td>
                    </tr>
			        	<c:forEach var="protocolAttachement" items="${protocol.attachmentProtocols}" varStatus="status">			        	
				             <tr>
			                     <td>
			                     	<kul:htmlControlAttribute property="document.protocolList[0].attachmentProtocol[${status.index}].file.description" 
				                									readOnly="true"	attributeEntry="${protocolAttachmentBaseAttributes.description}"  />
				                 </td>
				                 <td style="text-align:center;"><input name="d" type="radio" class="nobord"></td>
				             </tr>     
			        	</c:forEach>                    
                    
                    <tr>
                        <td>Informed Consent Document - complete (Document1.doc)</td>
                        <td style="text-align:center;"><input name="d" type="radio" class="nobord"></td>
                    </tr>
                    <tr>

                        <td>Recruitment Brochure- incomplete (Document2.doc)</td>
                        <td style="text-align:center;"><input name="d" type="radio" class="nobord"></td>
                    </tr>
                    <tr>
                        <td class="infoline">&nbsp;</td>
                        <td class="infoline" style="text-align:center;">
                        	<html:image property="methodToCall.printProtocol.line${ctr}.anchor${currentTabIndex}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-printsel.gif' styleClass="tinybutton"/>                         
                        </td>
                    </tr>
                

            </table>

    			
</kul:innerTab>

