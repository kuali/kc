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
<c:set var="kraAttributeReferenceDummyAttributes" value="${DataDictionary.KraAttributeReferenceDummy.attributes}" />
<c:set var="h3BkColor" value="background-color: #C3C3C3;border-bottom: 1px solid #CCCCCC;"/>
<c:set var="protocolStatus" value="${KualiForm.protocolDocument.protocol.protocolStatusCode}" />
<c:set var="canViewReviewComments" value="<%=org.kuali.kra.infrastructure.Constants.CAN_VIEW_REVIEW_COMMENTS%>" /> 
<kul:tab tabTitle="Print" defaultOpen="false" tabErrorKey="actionHelper.reportType">
    <div class="tab-container" align="left" >

        <kul:innerTab tabTitle="Protocol Details" parentTab="" defaultOpen="false">
            <div class="innerTab-container">
                <table cellpadding="0" cellspacing="0" summary="print forms">
                    <tr>
                        <th colspan="3" class="infoline">
                            <div align="left">
                                Protocol Summary
                            </div>
                        </th>
                    </tr>
                    <tr>
                        <td>
                           <div align="left">
                                <kul:htmlControlAttribute property="actionHelper.protocolPrintOption.protocolDetails" 
                                                          attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                          readOnly="false" styleClass="printOptions"/>Protocol Details
                            </div>
                        </td>
                        <td>
                           <div align="left">
                                <kul:htmlControlAttribute property="actionHelper.protocolPrintOption.fundingSource" 
                                                          attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                          readOnly="false" styleClass="printOptions"/>Funding Source
                            </div>
                        </td>
                        <td>
                           <div align="left">
                                <kul:htmlControlAttribute property="actionHelper.protocolPrintOption.ammendmentRenewalSummary" 
                                                          attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                          readOnly="false" styleClass="printOptions"/>Amendment & Renewal
                            </div>
                        </td>
                    </tr>
                
                    <tr>
                        <td>
                           <div align="left">
                                <kul:htmlControlAttribute property="actionHelper.protocolPrintOption.organizaition" 
                                                          attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                          readOnly="false" styleClass="printOptions"/>Organization
                            </div>
                        </td>
                        <td>
                           <div align="left">
                                <kul:htmlControlAttribute property="actionHelper.protocolPrintOption.actions" 
                                                          attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                          readOnly="false" styleClass="printOptions"/>Action History
                            </div>
                        </td>
                        <td>
                           <div align="left">
                                <kul:htmlControlAttribute property="actionHelper.protocolPrintOption.otherData" 
                                                          attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                          readOnly="false" styleClass="printOptions"/>Other Data
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                           <div align="left">
                                <kul:htmlControlAttribute property="actionHelper.protocolPrintOption.investigator" 
                                                          attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                          readOnly="false" styleClass="printOptions"/>Investigator
                            </div>
                        </td>
                        <td>
                           <div align="left">
                                <kul:htmlControlAttribute property="actionHelper.protocolPrintOption.subjects" 
                                                          attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                          readOnly="false" styleClass="printOptions"/>Subjects
                            </div>
                        </td>
                        <td>
                           <div align="left">
                                <kul:htmlControlAttribute property="actionHelper.protocolPrintOption.roles" 
                                                          attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                          readOnly="false" styleClass="printOptions"/>Protocol Roles
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                          <div align="left">
                                <kul:htmlControlAttribute property="actionHelper.protocolPrintOption.studyPersonnels" 
                                                          attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                          readOnly="false" styleClass="printOptions"/>Study Personnel
                            </div>
                        </td>
                        <td>
                           <div align="left">
                                <kul:htmlControlAttribute property="actionHelper.protocolPrintOption.specialReview" 
                                                          attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                          readOnly="false" styleClass="printOptions"/>Special Review
                            </div>
                        </td>
                        <td>
                           <div align="left">
                                <kul:htmlControlAttribute property="actionHelper.protocolPrintOption.references" 
                                                          attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                          readOnly="false" styleClass="printOptions"/>References
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                          <div align="left">
                                <kul:htmlControlAttribute property="actionHelper.protocolPrintOption.correspondents" 
                                                          attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                          readOnly="false" styleClass="printOptions"/>Correspondents
                            </div>
                        </td>
                        <td>
                           <div align="left">
                                <kul:htmlControlAttribute property="actionHelper.protocolPrintOption.riskLevel" 
                                                          attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                          readOnly="false" styleClass="printOptions"/>Risk Levels
                            </div>
                        </td>
                        <td>
                           <div align="left">
                                &nbsp;
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                          <div align="left">
                                <kul:htmlControlAttribute property="actionHelper.protocolPrintOption.areaOfResearch" 
                                                          attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                          readOnly="false" styleClass="printOptions"/>Area of Research
                            </div>
                        </td>
                        <td>
                           <div align="left">
                                <kul:htmlControlAttribute property="actionHelper.protocolPrintOption.notes" 
                                                          attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                          readOnly="false" styleClass="printOptions"/>Notes
                            </div>
                        </td>
                        <td>
                           <div align="left">
                                &nbsp;
                            </div>
                        </td>
                    </tr>
                    <kra:section permission="${canViewReviewComments}">
	                    <tr>
    	                    <th class="infoline">
        	                    <div align="left">
            	                    Review Comments
                	            </div>
                    	    </th>
                        	<td colspan="2">
                           		<div align="center">
                                	<kul:htmlControlAttribute property="actionHelper.protocolPrintOption.reviewComments" 
                                    	                      attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                        	                  readOnly="false" styleClass="printOptions"/>
                            	</div>
                        	</td>
                    	</tr>
                    </kra:section>
             		<%-- kcirb-1159 is closed (not fix, so comment out this       
                    <tr>
                        <th class="infoline">
                            <div align="left">
                                Protocol History
                            </div>
                        </th>
                        <td colspan="2" >
                           <div align="center">
                                <kul:htmlControlAttribute property="actionHelper.protocolPrintOption.protocolHistory" 
                                                          attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                          readOnly="false" styleClass="printOptions"/>
                            </div>
                        </td>
                    </tr>
            		--%>         
                    <tr>
                        <td colspan="2" class="infoline">
                            <div align="center">
                                <html:image property="methodToCall.printProtocolSelectedItems.anchor${currentTabIndex}"
                                    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-printsel.gif' 
                                    styleClass="tinybutton" onclick="excludeSubmitRestriction = true;"/>                         
                            </div>
                        </td>
                        <td>
                            <div align="center">
                                  <html:image property="methodToCall.selectAllProtocolPrint.anchor${tabKey}" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-selectall.gif" title="Select All" alt="Select All" styleClass="tinybutton" onclick="$j('.printOptions').attr('checked', true);return false;" />
                                  <html:image property="methodToCall.deselectAllProtocolPrint.anchor${tabKey}" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-selectnone.gif" title="Select None" alt="Select None" styleClass="tinybutton" onclick="$j('.printOptions').attr('checked', false);return false;" />
                            </div>                        
                        </td>
                    </tr>
                 </table>
            </div>
        </kul:innerTab>
          
        <c:if test="${fn:length(KualiForm.document.protocolList[0].activeAttachmentProtocolsNoDelete) > 0 or fn:length(KualiForm.document.protocolList[0].attachmentPersonnels) > 0}">
            <kul:innerTab tabTitle="Attachments" parentTab="" defaultOpen="false">
                <div class="innerTab-container">
                    <c:if test="${fn:length(KualiForm.document.protocolList[0].activeAttachmentProtocolsNoDelete) > 0}">
                        <table cellpadding="0" cellspacing="0" summary="protocol attachments print form" style="padding-top: 10px;">
                            <tr>
                                <td class="tab-subhead">Protocol Attachments</td>
                            </tr>
                            <tr>
                                <td>
                                    <table id="protocolActionPrint-protocolAttachment-table" cellpadding="0" cellspacing="0" summary="print forms" class="tablesorter" style="border-collapse:collapse;">
                                        <thead>
                                            <tr>
                                                <th class="tab-subhead" style="width: 24%;">Description<img src="${ConfigProperties.kra.externalizable.images.url}sort-arrows.gif" width="15" height="16" alt="sort"></th>
                                                <th class="tab-subhead" style="width: 22%;">Status/File name<img src="${ConfigProperties.kra.externalizable.images.url}sort-arrows.gif" width="15" height="16" alt="sort"></th>
                                                <th class="tab-subhead" style="width: 13%;">Last Updated<img src="${ConfigProperties.kra.externalizable.images.url}sort-arrows.gif" width="15" height="16" alt="sort"></th>
                                                <th class="tab-subhead" style="width: 13%;">Last Updated By<img src="${ConfigProperties.kra.externalizable.images.url}sort-arrows.gif" width="15" height="16" alt="sort"></th>
                                                <th class="tab-subhead" style="width: 20%;">Attachment Type<img src="${ConfigProperties.kra.externalizable.images.url}sort-arrows.gif" width="15" height="16" alt="sort"></th>
                                                <th class="tab-subhead" style="width: 8%;">&nbsp;</th>
                                            </tr>
                                        </thead>
                                        <tbody>    
                                            <c:forEach var="protocolAttachment" items="${KualiForm.document.protocolList[0].activeAttachmentProtocolsNoDelete}" varStatus="status">
                                                <tr>
                                                    <td>
                                                        ${protocolAttachment.description}
                                                        <%--<kul:htmlControlAttribute property="document.protocolList[0].attachmentProtocols[${status.index}].file.fileName"
                                                                                  attributeEntry="${protocolAttachmentBaseAttributes.description}"
                                                                                  readOnly="true" /> --%>
                                                    </td>
                                                    <td>
                                                       ${protocolAttachment.status.description} (${protocolAttachment.file.name})
                                                    </td>
                                                    <td>
                                                        <fmt:formatDate value="${protocolAttachment.updateTimestamp}" pattern="MM/dd/yyyy KK:mm a" />                      
                                                    </td>
                                                    <td>
                                                       ${protocolAttachment.updateUser}
                                                    </td>
                                                    <td>
                                                       ${protocolAttachment.type.description}
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
                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                        </table>    
                    </c:if>
        
                   <c:if test="${fn:length(KualiForm.document.protocolList[0].attachmentPersonnels) > 0}">
                        <table cellpadding="0" cellspacing="0" summary="personnel attachments print form" style="padding-top: 10px;">
                            <tr>
                                <td class="tab-subhead">Personnel Attachments</td>
                            </tr>
                            <tr>
                                <td>           
                                    <table id="protocolActionPrint-personnelAttachment-table" cellpadding="0" cellspacing="0" summary="print forms" class="tablesorter" style="border-collapse:collapse;">
                                        <thead>
                                            <tr>
                                                <th class="tab-subhead" style="width: 24%;">Description<img src="${ConfigProperties.kra.externalizable.images.url}sort-arrows.gif" width="15" height="16" alt="sort"></th>
                                                <th class="tab-subhead" style="width: 22%;">File name<img src="${ConfigProperties.kra.externalizable.images.url}sort-arrows.gif" width="15" height="16" alt="sort"></th>
                                                <th class="tab-subhead" style="width: 13%;">Last Updated<img src="${ConfigProperties.kra.externalizable.images.url}sort-arrows.gif" width="15" height="16" alt="sort"></th>
                                                <th class="tab-subhead" style="width: 13%;">Last Updated By<img src="${ConfigProperties.kra.externalizable.images.url}sort-arrows.gif" width="15" height="16" alt="sort"></th>
                                                <th class="tab-subhead" style="width: 20%;">Attachment Type<img src="${ConfigProperties.kra.externalizable.images.url}sort-arrows.gif" width="15" height="16" alt="sort"></th>
                                                <th class="tab-subhead" style="width: 8%;">&nbsp;</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                               <c:forEach var="attachmentPersonnel" items="${KualiForm.document.protocolList[0].attachmentPersonnels}" varStatus="status">
                                                   <tr>
                                                    <td>
                                                        ${attachmentPersonnel.description}
                                                    </td>
                                                    <td>
                                                        ${attachmentPersonnel.file.name}
                                                    </td>
                                                    <td>
                                                        <fmt:formatDate value="${attachmentPersonnel.updateTimestamp}" pattern="MM/dd/yyyy KK:mm a" />  
                                                    </td>
                                                    <td>
                                                        ${attachmentPersonnel.updateUser}
                                                    </td>
                                                    <td>
                                                        ${attachmentPersonnel.type.description} 
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
                                       </tbody>
                                    </table>
                                </td>    
                            </tr>           
                       </table>
                   </c:if>
               </div>
            </kul:innerTab>
        </c:if>
          
        <c:if test="${fn:length(KualiForm.actionHelper.questionnairesToPrints) > 0}">
            <kul:innerTab tabTitle="Questionnaires" parentTab="" defaultOpen="false">
                <div class="innerTab-container">
                    <c:set var="qid" value="0"/>
                    <table cellpadding="0" cellspacing="0"  border="0" summary="print forms">
                        <c:set var = "qnLength" value = "${fn:length(KualiForm.actionHelper.questionnairesToPrints)}" />
                        <c:forEach var="qnPrintOption" items="${KualiForm.actionHelper.questionnairesToPrints}" varStatus="status">
                            <tr>
                                <td>
                                    <div align="left">
                                        ${KualiForm.actionHelper.questionnairesToPrints[status.index].label}
                                        <c:if test="${not KualiForm.actionHelper.questionnairesToPrints[status.index].questionnaireActive}">
                                            -- This Questionnaire has been deactivated.
                                        </c:if>
                                    </div>
                                </td>
                                <td>
                                    <div align="center">
                                        <kul:htmlControlAttribute property="actionHelper.questionnairesToPrints[${status.index}].selected" 
                                                          attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                          readOnly="false" styleClass="printQnOptions"/>
                                    </div>
                                </td>
                             </tr>     
                        </c:forEach>
                        <tr>
                            <td class="infoline">
                                <div align="center">
                                    <html:image property="methodToCall.printProtocolQuestionnaires.anchor${currentTabIndex}"
                                            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-printsel.gif' 
                                            styleClass="tinybutton" onclick="excludeSubmitRestriction = true;"/>                         
                                </div>
                            </td>
                            <td>
                                <div align="center">
                                      <html:image property="methodToCall.selectAllProtocolPrint.anchor${tabKey}" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-selectall.gif" title="Select All" alt="Select All" styleClass="tinybutton" onclick="$j('.printQnOptions').attr('checked', true);return false;" />
                                      <html:image property="methodToCall.deselectAllProtocolPrint.anchor${tabKey}" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-selectnone.gif" title="Select None" alt="Select None" styleClass="tinybutton" onclick="$j('.printQnOptions').attr('checked', false);return false;" />
                                </div>                        
                            </td>
                        </tr>
                       </table>
                   </div>
            </kul:innerTab>
          </c:if>
    </div>
</kul:tab>
