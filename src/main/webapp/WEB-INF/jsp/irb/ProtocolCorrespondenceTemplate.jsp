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

<kul:page lookup="false" 
          docTitle="Correspondence Template" 
          transactionalDocument="false"
          renderMultipart="true" 
          htmlFormAction="protocolCorrespondenceTemplate">
          
    <script language="javascript" src="scripts/kuali_application.js"></script>
    
    <div id="workarea">

<c:set var="parentTab" value = "Correspondence Templates" />

<kul:tab tabTitle="${parentTab}"
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
            <c:set var="tabKey" value="${kfunc:generateTabKey(parentTab)}:${kfunc:generateTabKey(tabTitle)}" />
            <c:set var="isOpen" value="false"/>
            <c:if test="${KualiForm.tabStates[tabKey] == 'OPEN'}">
                <c:set var="isOpen" value="true"/>
            </c:if>

            <kra:innerTab tabTitle="${correspondenceType.description}" 
                          parentTab="${parentTab}" 
                          defaultOpen="${isOpen}"
                          tabErrorKey="newDefaultCorrespondenceTemplates[${status.index}].*,newCorrespondenceTemplates[${status.index}].*,correspondenceTypes[${status.index}].*,replaceCorrespondenceTemplates[${status.index}].*">
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
                                <c:choose>
                                    <c:when test="${empty correspondenceType.defaultProtocolCorrespondenceTemplate}">
                                        <c:set var="property" value="newDefaultCorrespondenceTemplates[${status.index}].templateFile" />
                        
                                        <%-- attachment file error handling logic start--%>
                                            <kul:checkErrors keyMatch="${property}" auditMatch="${property}"/>
                                            <%-- highlighting does not work in firefox but does in ie... --%>
                                            <c:set var="textStyle" value="${hasErrors == true ? 'background-color:#FFD5D5' : ''}"/>
                                        <%-- attachment file error handling logic end--%>
                        
                                        <html:file property="${property}" style="${textStyle}" />
                                    </c:when>
                                    <c:otherwise>
                                        <div id="filename1-${status.index}" style="display:block;" align="center">
                                            ${correspondenceType.defaultProtocolCorrespondenceTemplate.fileName}
                                            <c:forEach items="${ErrorPropertyList}" var="key">
                                                <c:set var="propertyName" value="correspondenceTypes[${status.index}].defaultProtocolCorrespondenceTemplate.templateFile" />
                                                <c:if test="${key eq propertyName}">
                                                    <kul:fieldShowErrorIcon />
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                        <div id="browse1-${status.index}" style="display:none;" align="center">
                                            <c:set var="property" value="newDefaultCorrespondenceTemplates[${status.index}].templateFile" />
                        
                                            <%-- attachment file error handling logic start--%>
                                                <kul:checkErrors keyMatch="${property}" auditMatch="${property}"/>
                                                <%-- highlighting does not work in firefox but does in ie... --%>
                                                <c:set var="textStyle" value="${hasErrors == true ? 'background-color:#FFD5D5' : ''}"/>
                                            <%-- attachment file error handling logic end--%>
                        
                                            <html:file property="${property}" style="${textStyle}" />
                                        </div>            
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </td>
                        <td>
                            <div align="center">
                                <c:choose>
                                    <c:when test="${empty correspondenceType.defaultProtocolCorrespondenceTemplate}">
                                        <html:image property="methodToCall.addDefaultCorrespondenceTemplate.correspondenceType[${status.index}]" 
                                            src="${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif" 
                                            title="Add Default Correspondence Template" 
                                            alt="Add Default Correspondence Template" 
                                            styleClass="tinybutton" />
                                    </c:when>
                                    <c:otherwise>
                                        <div id="filename2-${status.index}" style="display:block;" align="center">
                                            <html:image property="methodToCall.viewDefaultCorrespondenceTemplate.correspondenceType[${status.index}]" 
                                                src="${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif" 
                                                title="View Default Correspondence Template" 
                                                alt="View Default Correspondence Template" 
                                                styleClass="tinybutton"
                                                onclick="excludeSubmitRestriction=true" />
                                            <html:image onclick="javascript: showHide('browse1-${status.index}','filename1-${status.index}'); showHide('browse2-${status.index}','filename2-${status.index}'); return false"
                                                src="${ConfigProperties.kra.externalizable.images.url}tinybutton-replace.gif" 
                                                title="Replace Default Correspondence Template" 
                                                alt="Replace Default Correspondence Template" 
                                                styleClass="tinybutton" />
                                            <html:image property="methodToCall.deleteDefaultCorrespondenceTemplate.correspondenceType[${status.index}]" 
                                                src="${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif" 
                                                title="Delete Default Correspondence Template" 
                                                alt="Delete Default Correspondence Template" 
                                                styleClass="tinybutton" />
                                        </div>
                                        <div id="browse2-${status.index}" style="display:none;" align="center">
                                            <html:image property="methodToCall.replaceDefaultCorrespondenceTemplate.correspondenceType[${status.index}]" 
                                                src="${ConfigProperties.kra.externalizable.images.url}tinybutton-upload.gif" 
                                                title="Upload Default Correspondence Template" 
                                                alt="Upload Default Correspondence Template" 
                                                styleClass="tinybutton" />
                                            <html:image onclick="javascript: showHide('filename1-${status.index}','browse1-${status.index}'); showHide('filename2-${status.index}','browse2-${status.index}'); return false"
                                                src="${ConfigProperties.kra.externalizable.images.url}tinybutton-cancel.gif" 
                                                title="Cancel Default Correspondence Template Upload" 
                                                alt="Cancel Default Correspondence Template Upload" 
                                                styleClass="tinybutton" />
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </td>
                    </tr>
                    <%-- Default Template --%>
                     
                </table>
                
                <p style="text-align:left; font-weight:bold;">Customized per Committee</p>
                <table style="border-top-width:1px; border-top-style:solid; border-top-color:#999999;" cellpAdding="0" cellspacing="0" width="50%" align="center" >
                    <tr>
                        <kul:htmlAttributeHeaderCell literalLabel="&nbsp;" scope="col" align="center" width="5%" />
                        <kul:htmlAttributeHeaderCell attributeEntry="${DataDictionary.ProtocolCorrespondenceTemplate.attributes.committeeId}" scope="col" align="center" width="25%" />
                        <kul:htmlAttributeHeaderCell attributeEntry="${DataDictionary.ProtocolCorrespondenceTemplate.attributes.fileName}" scope="col" align="center" />
                        <c:if test="${!readOnly}">
                            <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" align="center" width="25%" />
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
                    <c:forEach items="${correspondenceType.committeeProtocolCorrespondenceTemplates}" var="protocolCorrespondenceTemplate" varStatus="status2">
                        <c:set var="filenameStyle" value="display:block;" />
                        <c:set var="browseStyle" value="display:none;" />
                        <c:forEach items="${ErrorPropertyList}" var="key">
                            <c:set var="propertyName" value="replaceCorrespondenceTemplates[${status.index}].list[${status2.index}].templateFile" />
                            <c:if test="${key eq propertyName}">
                                <c:set var="filenameStyle" value="display:none;" />
                                <c:set var="browseStyle" value="display:block;" />
                            </c:if>
                        </c:forEach>            
                        
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
                                <div id="filename1-${status.index}-${status2.index}" style="${filenameStyle}" align="center">
                                    ${protocolCorrespondenceTemplate.fileName}
                                    <c:forEach items="${ErrorPropertyList}" var="key">
                                        <c:set var="propertyName" value="correspondenceTypes[${status.index}].protocolCorrespondenceTemplates[${status2.index}].templateFile" />
                                        <c:if test="${key eq propertyName}">
                                            <kul:fieldShowErrorIcon />
                                        </c:if>
                                    </c:forEach>            
                                </div>
                                <div id="browse1-${status.index}-${status2.index}" style="${browseStyle}" align="center">
                                    <c:set var="property" value="replaceCorrespondenceTemplates[${status.index}].list[${status2.index}].templateFile" />

                                    <%-- attachment file error handling logic start--%>
                                        <kul:checkErrors keyMatch="${property}" auditMatch="${property}"/>
                                        <%-- highlighting does not work in firefox but does in ie... --%>
                                        <c:set var="textStyle" value="${hasErrors == true ? 'background-color:#FFD5D5' : ''}"/>
                                    <%-- attachment file error handling logic end--%>
                        
                                    <html:file property="${property}" style="${textStyle}" />
                                </div>                      
                            </td>
                            <c:if test="${!readOnly}">
                                <td>
                                    <div id="filename2-${status.index}-${status2.index}" style="${filenameStyle}" align="center">
                                        <html:image property="methodToCall.viewCorrespondenceTemplate.correspondenceType[${status.index}].correspondenceTemplate[${status2.index}]}" 
                                            src="${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif" 
                                            title="View Correspondence Template" 
                                            alt="View Correspondence Template" 
                                            styleClass="tinybutton" 
                                            onclick="excludeSubmitRestriction=true" />
                                        <html:image onclick="javascript: showHide('browse1-${status.index}-${status2.index}','filename1-${status.index}-${status2.index}'); javascript: showHide('browse2-${status.index}-${status2.index}','filename2-${status.index}-${status2.index}'); return false"
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
                                    <div id="browse2-${status.index}-${status2.index}" style="${browseStyle}" align="center">
                                        <html:image property="methodToCall.replaceCorrespondenceTemplate.correspondenceType[${status.index}].correspondenceTemplate[${status2.index}]" 
                                            src="${ConfigProperties.kra.externalizable.images.url}tinybutton-upload.gif" 
                                            title="Upload Correspondence Template" 
                                            alt="Upload Correspondence Template" 
                                            styleClass="tinybutton" />
                                        <html:image onclick="javascript: showHide('filename1-${status.index}-${status2.index}','browse1-${status.index}-${status2.index}'); showHide('filename2-${status.index}-${status2.index}','browse2-${status.index}-${status2.index}'); return false"
                                            src="${ConfigProperties.kra.externalizable.images.url}tinybutton-cancel.gif" 
                                            title="Cancel Correspondence Template Upload" 
                                            alt="Cancel Correspondence Template Upload" 
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