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

<%@ attribute name="index" required="true" %>

<jsp:useBean id="paramMap" class="java.util.HashMap"/>

<p style="text-align:left; font-weight:bold;">Customized per Committee</p>

<table style="border-top-width:1px; border-top-style:solid; border-top-color:#999999;" cellpAdding="0" cellspacing="0" width="50%" align="center" >
    <tr>
        <kul:htmlAttributeHeaderCell literalLabel="&nbsp;" scope="col" align="center" width="5%" />
        <kul:htmlAttributeHeaderCell attributeEntry="${DataDictionary.ProtocolCorrespondenceTemplate.attributes.committeeId}" scope="col" align="center" width="25%" />
        <kul:htmlAttributeHeaderCell attributeEntry="${DataDictionary.ProtocolCorrespondenceTemplate.attributes.fileName}" scope="col" align="center" />
        <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" align="center" width="25%" />
    </tr>
    
    <%-- New Template --%>
    <c:if test="${!readOnly}">
        <tr>
            <td class="infoline nobord">
                <div align="center" style="font-weight:bold;">
                    add
                </div>
            </td>
            <td class="infoline nobord">
                <div align="left">
                    <c:set var="hasCommitteeError" value="false"/>
                    <c:set var="fieldName" value="newCorrespondenceTemplates[${index}].committeeId" />
                    <c:forEach items="${ErrorPropertyList}" var="key">
                        <c:if test="${key eq fieldName }">
                            <c:set var="hasCommitteeError" value="true"/>
                        </c:if>
                    </c:forEach>
                    <c:set target="${paramMap}" property="correspondenceTemplates" value="${KualiForm.correspondenceTypes[index].committeeProtocolCorrespondenceTemplates}" />
                    <html:select property="newCorrespondenceTemplates[${index}].committeeId" tabindex="0"  >                               
                        <c:forEach items="${krafn:getOptionList('org.kuali.kra.committee.lookup.keyvalue.CommitteeIdValuesFinder', paramMap)}" var="option">
                            <c:choose>                      
                                <c:when test="${KualiForm.newCorrespondenceTemplates[index].committeeId == option.key}">
                                    <option value="${option.key}" selected>${option.value}</option>
                                </c:when>
                                <c:otherwise>                               
                                    <c:out value="${option.value}"/>
                                    <option value="${option.key}">${option.value}</option>
                                </c:otherwise>
                            </c:choose>                                                
                        </c:forEach>
                    </html:select>
                    <c:if test="${hasCommitteeError}">
                        <kul:fieldShowErrorIcon />
                    </c:if>
                </div>
            </td>
            <td class="infoline nobord">
                <div align="center">
                    <kra:file property="newCorrespondenceTemplates[${index}].templateFile" />
                </div>
            </td>
            <td class="infoline nobord">
                <div align="center">
                <html:image property="methodToCall.addCorrespondenceTemplate.correspondenceType[${index}]" 
                    src="${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif" 
                    title="Add Correspondence Template" 
                    alt="Add Correspondence Template" 
                    styleClass="tinybutton" />
                </div>
            </td>
        </tr>
    </c:if>
    <%-- New Template --%>
    
    <%-- Existing Templates --%>
    <c:forEach items="${KualiForm.correspondenceTypes[index].committeeProtocolCorrespondenceTemplates}" var="protocolCorrespondenceTemplate" varStatus="status">
        <c:set var="filenameStyle" value="display:block;" />
        <c:set var="browseStyle" value="display:none;" />
        <c:forEach items="${ErrorPropertyList}" var="key">
            <c:set var="propertyName" value="replaceCorrespondenceTemplates[${index}].list[${status.index}].templateFile" />
            <c:if test="${key eq propertyName}">
                <c:set var="filenameStyle" value="display:none;" />
                <c:set var="browseStyle" value="display:block;" />
            </c:if>
        </c:forEach>            
        
        <tr>
            <td>
                <div align="center" style="font-weight:bold;">
                   ${status.index + 1}
                </div>
            </td>
            <td>
                <div align="left">
                    ${protocolCorrespondenceTemplate.committee.committeeName}
                    <c:forEach items="${ErrorPropertyList}" var="key">
                        <c:set var="propertyName" value="correspondenceTypes[${index}].protocolCorrespondenceTemplates[${status.index}].committeeId" />
                        <c:if test="${key eq propertyName}">
                            <kul:fieldShowErrorIcon />
                        </c:if>
                    </c:forEach>            
                </div>
            </td>
            <td>
                <div id="filename1-${index}-${status.index}" style="${filenameStyle}" align="center">
                    ${protocolCorrespondenceTemplate.fileName}
                    <c:forEach items="${ErrorPropertyList}" var="key">
                        <c:set var="propertyName" value="correspondenceTypes[${index}].protocolCorrespondenceTemplates[${status.index}].templateFile" />
                        <c:if test="${key eq propertyName}">
                            <kul:fieldShowErrorIcon />
                        </c:if>
                    </c:forEach>            
                </div>
                <div id="browse1-${index}-${status.index}" style="${browseStyle}" align="center">
                    <kra:file property="replaceCorrespondenceTemplates[${index}].list[${status.index}].templateFile" />
                </div>                      
            </td>

           <c:choose>
            <c:when test="${!readOnly}">
                <td>
                    <div id="filename2-${index}-${status.index}" style="${filenameStyle}" align="center">
                        <html:image property="methodToCall.viewCorrespondenceTemplate.correspondenceType[${index}].correspondenceTemplate[${status.index}]}" 
                            src="${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif" 
                            title="View Correspondence Template" 
                            alt="View Correspondence Template" 
                            styleClass="tinybutton" 
                            onclick="excludeSubmitRestriction=true" />
                        <html:image property="methodToCall.replaceCorrespondenceTemplate.correspondenceType[${index}].correspondenceTemplate[${status.index}]}"
                            src="${ConfigProperties.kra.externalizable.images.url}tinybutton-replace.gif" 
                            title="Replace Correspondence Template" 
                            alt="Replace Correspondence Template" 
                            styleClass="tinybutton" 
                            onclick="javascript: showHide('browse1-${index}-${status.index}','filename1-${index}-${status.index}'); javascript: showHide('browse2-${index}-${status.index}','filename2-${index}-${status.index}'); return false" />
                        <html:image property="methodToCall.deleteCorrespondenceTemplate.correspondenceType[${index}].correspondenceTemplate[${status.index}]}" 
                            src="${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif" 
                            title="Delete Correspondence Template" 
                            alt="Delete Correspondence Template" 
                            styleClass="tinybutton" />
                    </div>
                    <div id="browse2-${index}-${status.index}" style="${browseStyle}" align="center">
                        <html:image property="methodToCall.replaceCorrespondenceTemplate.correspondenceType[${index}].correspondenceTemplate[${status.index}]" 
                            src="${ConfigProperties.kra.externalizable.images.url}tinybutton-upload.gif" 
                            title="Upload Correspondence Template" 
                            alt="Upload Correspondence Template" 
                            styleClass="tinybutton" />
                        <html:image onclick="javascript: showHide('filename1-${index}-${status.index}','browse1-${index}-${status.index}'); showHide('filename2-${index}-${status.index}','browse2-${index}-${status.index}'); return false"
                            src="${ConfigProperties.kra.externalizable.images.url}tinybutton-cancel.gif" 
                            title="Cancel Correspondence Template Upload" 
                            alt="Cancel Correspondence Template Upload" 
                            styleClass="tinybutton" />
                    </div>
                </td>
            </c:when>
            <c:otherwise>
                <td>
                    <div align="center">
                        <html:image property="methodToCall.viewCorrespondenceTemplate.correspondenceType[${index}].correspondenceTemplate[${status.index}]}" 
                            src="${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif" 
                            title="View Correspondence Template" 
                            alt="View Correspondence Template" 
                            styleClass="tinybutton" 
                            onclick="excludeSubmitRestriction=true" />
                    </div>
                </td>
            </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
    <%-- Existing Templates --%>

</table>

<p></p>
