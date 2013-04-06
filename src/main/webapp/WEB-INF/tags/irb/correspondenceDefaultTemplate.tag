<%--
 Copyright 2005-2013 The Kuali Foundation

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

<%@ attribute name="index" required="true" %>

<p style="text-align:left; font-weight:bold;">Default</p>

<table style="border-top-width:1px; border-top-style:solid; border-top-color:#999999;" cellpAdding="0" cellspacing="0" width="50%" align="center" >

    <tr>
        <th width="75%">File</th>
        <th width="25%">Actions</th>
    </tr>

    <c:if test="${!readOnly || !empty KualiForm.correspondenceTypes[index].defaultProtocolCorrespondenceTemplate}">
        <tr>
            <td>
                <div align="center">
                    <c:choose>
                        <c:when test="${empty KualiForm.correspondenceTypes[index].defaultProtocolCorrespondenceTemplate}">
                            <kra:file property="newDefaultCorrespondenceTemplates[${index}].templateFile" />
                        </c:when>
                        <c:otherwise>
                            <div id="filename1-${index}" style="display:block;" align="center">
                                ${KualiForm.correspondenceTypes[index].defaultProtocolCorrespondenceTemplate.fileName}
                                <c:forEach items="${ErrorPropertyList}" var="key">
                                    <c:set var="propertyName" 
                                           value="correspondenceTypes[${index}].defaultProtocolCorrespondenceTemplate.templateFile" />
                                    <c:if test="${key eq propertyName}">
                                        <kul:fieldShowErrorIcon />
                                    </c:if>
                                </c:forEach>
                            </div>
                            <div id="browse1-${index}" style="display:none;" align="center">
                                <kra:file property="newDefaultCorrespondenceTemplates[${index}].templateFile" />
                            </div>            
                        </c:otherwise>
                    </c:choose>
                </div>
            </td>
            <td>
                <div align="center">
                    <c:choose>
                        <c:when test="${readOnly}">
                            <div align="center">
                                <html:image property="methodToCall.viewDefaultCorrespondenceTemplate.correspondenceType[${index}]" 
                                    src="${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif" 
                                    title="View Default Correspondence Template" 
                                    alt="View Default Correspondence Template" 
                                    styleClass="tinybutton"
                                    onclick="excludeSubmitRestriction=true" />
                            </div>
                        </c:when>
                        <c:when test="${empty KualiForm.correspondenceTypes[index].defaultProtocolCorrespondenceTemplate}">
                            <html:image property="methodToCall.addDefaultCorrespondenceTemplate.correspondenceType[${index}]" 
                                src="${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif" 
                                title="Add Default Correspondence Template" 
                                alt="Add Default Correspondence Template" 
                                styleClass="tinybutton" />
                        </c:when>
                        <c:otherwise>
                            <div id="filename2-${index}" style="display:block;" align="center">
                                <html:image property="methodToCall.viewDefaultCorrespondenceTemplate.correspondenceType[${index}]" 
                                    src="${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif" 
                                    title="View Default Correspondence Template" 
                                    alt="View Default Correspondence Template" 
                                    styleClass="tinybutton"
                                    onclick="excludeSubmitRestriction=true" />
                                <html:image property="methodToCall.replaceDefaultCorrespondenceTemplate.correspondenceType[${index}]" 
                                    src="${ConfigProperties.kra.externalizable.images.url}tinybutton-replace.gif" 
                                    title="Replace Default Correspondence Template" 
                                    alt="Replace Default Correspondence Template" 
                                    styleClass="tinybutton"
                                    onclick="javascript: showHide('browse1-${index}','filename1-${index}'); showHide('browse2-${index}','filename2-${index}'); return false" />
                                <html:image property="methodToCall.deleteDefaultCorrespondenceTemplate.correspondenceType[${index}]" 
                                    src="${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif" 
                                    title="Delete Default Correspondence Template" 
                                    alt="Delete Default Correspondence Template" 
                                    styleClass="tinybutton" />
                            </div>
                            <div id="browse2-${index}" style="display:none;" align="center">
                                <html:image property="methodToCall.replaceDefaultCorrespondenceTemplate.correspondenceType[${index}]" 
                                    src="${ConfigProperties.kra.externalizable.images.url}tinybutton-upload.gif" 
                                    title="Upload Default Correspondence Template" 
                                    alt="Upload Default Correspondence Template" 
                                    styleClass="tinybutton" />
                                <html:image onclick="javascript: showHide('filename1-${index}','browse1-${index}'); showHide('filename2-${index}','browse2-${index}'); return false"
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
    </c:if>
    
</table>

<p></p>
