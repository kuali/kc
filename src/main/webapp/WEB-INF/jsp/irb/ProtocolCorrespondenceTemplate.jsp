<%--
 Copyright 2006-2009 The Kuali Foundation

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

<%-- <c:set var="readOnly" value="${KualiForm.readOnly}"  scope="request"/> --%>
<c:set var="readOnly" value="false"  scope="request"/>

<kul:page lookup="true" 
          docTitle="Correspondence Template" 
          transactionalDocument="false"
          renderMultipart="true" 
          htmlFormAction="protocolCorrespondenceTemplate">
          
    <script language="javascript" src="scripts/kuali_application.js"></script>
    
    <div id="workarea">

<kul:tab tabTitle="Correspondence Templates" 
         defaultOpen="true"
         alwaysOpen="true"
         transparentBackground="true" 
         useCurrentTabIndexAsKey="true"
         tabErrorKey="">
         
    <div class="tab-container" align="center" id="G100">
        <h3>
            <span class="subhead-left">Correspondence Templates</span>
            <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.irb.correspondence.ProtocolCorrespondenceTemplate" altText="help" /></span>
        </h3>
        
        <c:forEach items="${KualiForm.correspondenceTypes}" var="correspondenceType" varStatus="status">
            <kra:innerTab tabTitle="${correspondenceType.description}" 
                          parentTab="Correspondence Templates" 
                          defaultOpen="false"
                          useCurrentTabIndexAsKey="true" 
                          tabErrorKey="newCorrespondenceTemplates[${status.index}].*,correspondenceTypes[${status.index}].*">
                <p style="text-align:left; font-weight:bold;">Default</p>
                <table style="border-top-width:1px; border-top-style:solid; border-top-color:#999999;" cellpAdding="0" cellspacing="0" width="50%" align="center" >
                    <tr>
                        <th width="75%">File</th>
                        <th width="25%">Actions</th>
                    </tr>

                    <%-- Default Template --%>
                    <tr>
                        <td>
                            <div align="center">
                                <c:set var="property" value="defaultCorrespondenceTemplates[${status.index}].templateFile" />
                        
                                <%-- attachment file error handling logic start--%>
                                    <kul:checkErrors keyMatch="${property}" auditMatch="${property}"/>
                                    <%-- highlighting does not work in firefox but does in ie... --%>
                                    <c:set var="textStyle" value="${hasErrors == true ? 'background-color:#FFD5D5' : ''}"/>
                                <%-- attachment file error handling logic end--%>
                        
                                <html:file property="${property}" style="${textStyle}" />
                            </div>
                        </td>
                        <td>
                            <div align="center">
                                <html:image property="methodToCall.viewCorrespondenceTemplate" 
                                    src="${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif" 
                                    title="View Correspondence Template" 
                                    alt="View Correspondence Template" 
                                    styleClass="tinybutton" />
                                <html:image property="methodToCall.replaceCorrespondenceTemplate" 
                                    src="${ConfigProperties.kra.externalizable.images.url}tinybutton-replace.gif" 
                                    title="Replace Correspondence Template" 
                                    alt="Replace Correspondence Template" 
                                    styleClass="tinybutton" />
                                <html:image property="methodToCall.deleteCorrespondenceTemplate" 
                                    src="${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif" 
                                    title="Delete Correspondence Template" 
                                    alt="Delete Correspondence Template" 
                                    styleClass="tinybutton" />
                             </div>
                         </td>
                     </tr>
                    <%-- Default Template --%>
                     
                </table>
                
                <p style="text-align:left; font-weight:bold;">Customized per Committee</p>
                <table style="border-top-width:1px; border-top-style:solid; border-top-color:#999999;" cellpAdding="0" cellspacing="0" width="50%" align="center" >
                    <tr>
                        <kul:htmlAttributeHeaderCell literalLabel="&nbsp;" scope="col" align="center" />
                        <kul:htmlAttributeHeaderCell attributeEntry="${DataDictionary.ProtocolCorrespondenceTemplate.attributes.committeeId}" scope="col" align="center" />
                        <kul:htmlAttributeHeaderCell attributeEntry="${DataDictionary.ProtocolCorrespondenceTemplate.attributes.fileName}" scope="col" align="center" />
                        <c:if test="${!readOnly}">
                            <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" align="center" />
                        </c:if>
                    </tr>
                    
                    <%-- New Template --%>
                    <tr>
                        <td>
                            <div align="center" style="font-weight:bold;">
                                add
                            </div>
                        </td>
                        <td>
                            <div align="left">
                                <kul:htmlControlAttribute property="newCorrespondenceTemplates[${status.index}].committeeId"
                                                          attributeEntry="${DataDictionary.ProtocolCorrespondenceTemplate.attributes.committeeId}" />
                            </div>
                        </td>
                        <td>
                            <div align="center">
                                <c:set var="property" value="newCorrespondenceTemplates[${status.index}].templateFile" />
                        
                                <%-- attachment file error handling logic start--%>
                                    <kul:checkErrors keyMatch="${property}" auditMatch="${property}"/>
                                    <%-- highlighting does not work in firefox but does in ie... --%>
                                    <c:set var="textStyle" value="${hasErrors == true ? 'background-color:#FFD5D5' : ''}"/>
                                <%-- attachment file error handling logic end--%>
                        
                                <html:file property="${property}" style="${textStyle}" />
                            </div>
                        </td>
                        <td>
                            <div align="center">
                            <html:image property="methodToCall.addCorrespondenceTemplate.correspondenceType[${status.index}]" 
                                src="${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif" 
                                title="Add Correspondence Template" 
                                alt="Add Correspondence Template" 
                                styleClass="tinybutton" />
                            </div>
                        </td>
                    </tr>
                    <%-- New Template --%>
                    
                    <%-- Existing Templates --%>
                    <c:forEach items="${correspondenceType.protocolCorrespondenceTemplates}" var="protocolCorrespondenceTemplate" varStatus="status2">
                        <tr>
                            <td>
                                <div align="center" style="font-weight:bold;">
                                   ${status2.index + 1}
                                </div>
                            </td>
                            <td>
                                <div align="left">
                                    ${protocolCorrespondenceTemplate.committee.committeeName}
                                    <c:forEach items="${ErrorPropertyList}" var="key">
                                        <c:set var="propertyName" value="correspondenceTypes[${status.index}].protocolCorrespondenceTemplates[${status2.index}].committeeId" />
                                        <c:if test="${key eq propertyName}">
                                            <kul:fieldShowErrorIcon />
                                        </c:if>
                                    </c:forEach>            
                                </div>
                            </td>
                            <td>
                                <div align="center">
                                    ${protocolCorrespondenceTemplate.fileName}
                                </div>
                            </td>
                            <c:if test="${!readOnly}">
                                <td>
                                    <div align="center">
                                        <html:image property="methodToCall.viewCorrespondenceTemplate" 
                                            src="${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif" 
                                            title="View Correspondence Template" 
                                            alt="View Correspondence Template" 
                                            styleClass="tinybutton" />
                                        <html:image property="methodToCall.replaceCorrespondenceTemplate" 
                                            src="${ConfigProperties.kra.externalizable.images.url}tinybutton-replace.gif" 
                                            title="Replace Correspondence Template" 
                                            alt="Replace Correspondence Template" 
                                            styleClass="tinybutton" />
                                        <html:image property="methodToCall.deleteCorrespondenceTemplate.correspondenceType[${status.index}].correspondenceTemplate[${status2.index}]}" 
                                            src="${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif" 
                                            title="Delete Correspondence Template" 
                                            alt="Delete Correspondence Template" 
                                            styleClass="tinybutton" />
                                    </div>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                    <%-- Existing Templates --%>

                </table>
                <p></p>
            </kra:innerTab>
        </c:forEach>
    </div> 
</kul:tab>

<kul:panelFooter />

<div id="globalbuttons" class="globalbuttons">
    <c:if test="${!readOnly}">
        <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_save.gif" styleClass="globalbuttons" property="methodToCall.save" title="save" alt="save"/>
    </c:if>
    <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_reload.gif" styleClass="globalbuttons" property="methodToCall.reload" title="reload" alt="reload" onclick="excludeSubmitRestriction=true"/>
    <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_close.gif" styleClass="globalbuttons" property="methodToCall.close" title="close" alt="close"/>
    <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_cancel.gif" styleClass="globalbuttons" property="methodToCall.cancel" title="cancel" alt="cancel"/>
</div>

</kul:page>