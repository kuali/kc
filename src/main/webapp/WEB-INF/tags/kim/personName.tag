<%--
 Copyright 2008-2009 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl2.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<c:set var="docNameAttributes" value="${DataDictionary.PersonDocumentName.attributes}" />

<c:set var="canModify" scope="request" value="${!KualiForm.document.privacy.suppressName || KualiForm.canOverrideEntityPrivacyPreferences}" />
<c:set var="maskData" value="${KualiForm.document.privacy.suppressName && !KualiForm.canOverrideEntityPrivacyPreferences}" />

<kul:subtab lookedUpCollectionName="name" width="${tableWidth}" subTabTitle="Names" noShowHideButton="true">      
    <table cellpadding="0" cellspacing="0" summary="">
        <tr>
            <th>&nbsp;</th> 
            <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docNameAttributes.nameTypeCode}" noColon="true" /></div></th>
            <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docNameAttributes.title}" noColon="true" /></div></th>
            <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docNameAttributes.firstName}" noColon="true" /></div></th>
            <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docNameAttributes.lastName}" noColon="true" /></div></th>
            <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docNameAttributes.suffix}" noColon="true" /></div></th>
            <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docNameAttributes.dflt}" noColon="true" /></div></th>
            <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docNameAttributes.active}" noColon="true" /></div></th>
            <c:if test="${not inquiry and canModify}">    
                <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
            </c:if>
        </tr>     
        <c:if test="${not inquiry and not readOnlyEntity and canModify}">              
            <tr>
                <th class="infoline">
                    <c:out value="Add:" />
                </th>
                <td align="left" valign="middle" class="infoline">
                    <div align="center">
                        <kul:htmlControlAttribute property="newName.nameTypeCode" attributeEntry="${docNameAttributes.nameTypeCode}" readOnly="${readOnlyEntity}" />
                    </div>
                </td>
                <td class="infoline">
                    <div align="center">
                        <kul:htmlControlAttribute property="newName.title" attributeEntry="${docNameAttributes.title}" readOnly="${readOnlyEntity}" />
                    </div>
                </td>
                <td class="infoline">   
                    <div align="center">                
                      <kul:htmlControlAttribute property="newName.firstName" attributeEntry="${docNameAttributes.firstName}" readOnly="${readOnlyEntity}" />
                    </div>
                </td>                
                <td align="left" valign="middle" class="infoline">
                    <div align="center"><kul:htmlControlAttribute property="newName.lastName" attributeEntry="${docNameAttributes.lastName}" readOnly="${readOnlyEntity}" />
                    </div>
                </td>
                <td align="left" valign="middle" class="infoline">
                    <div align="center"><kul:htmlControlAttribute property="newName.suffix" attributeEntry="${docNameAttributes.suffix}" readOnly="${readOnlyEntity}" />
                    </div>
                </td>
                <td align="left" valign="middle" class="infoline">
                    <div align="center"><kul:htmlControlAttribute property="newName.dflt" attributeEntry="${docNameAttributes.dflt}" readOnly="${readOnlyEntity}" />
                    </div>
                </td>
                <td align="left" valign="middle" class="infoline">
                    <div align="center"><kul:htmlControlAttribute property="newName.active" attributeEntry="${docNameAttributes.active}" readOnly="${readOnlyEntity}" />
                    </div>
                </td>                               
                <td class="infoline">
                    <div align=center>
                        <html:image property="methodToCall.addName.anchor${tabKey}"
                        src='${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
                    </div>
                </td>
            </tr>         
        </c:if>      
        <c:forEach var="name" items="${KualiForm.document.names}" varStatus="status">
            <tr>
                <th class="infoline">
                    <c:out value="${status.index+1}" />
                </th>
                <td align="left" valign="middle">
                    <div align="center"> <kul:htmlControlAttribute property="document.names[${status.index}].nameTypeCode"  attributeEntry="${docNameAttributes.nameTypeCode}"  readOnlyAlternateDisplay="${fn:escapeXml(name.entityNameType.entityNameTypeName)}" readOnly="${readOnlyEntity or !canModify}" />
                    </div>
                </td>
                <td>
                    <div align="center"> <kul:htmlControlAttribute property="document.names[${status.index}].title" attributeEntry="${docNameAttributes.title}" readOnly="${readOnlyEntity or !canModify}"  displayMask="${maskData}" displayMaskValue="Xxxxxxx" />
                    </div>
                </td>
                <td>     
                    <div align="center">            
                      <kul:htmlControlAttribute property="document.names[${status.index}].firstName" attributeEntry="${docNameAttributes.firstName}" readOnly="${readOnlyEntity or !canModify}"  displayMask="${maskData}" displayMaskValue="Xxxxxxx" />
                    </div>
                </td>
                <td align="left" valign="middle" class="infoline">
                    <div align="center"><kul:htmlControlAttribute property="document.names[${status.index}].lastName" attributeEntry="${docNameAttributes.lastName}" readOnly="${readOnlyEntity or !canModify}" displayMask="${maskData}" displayMaskValue="Xxxxxxx" />
                </div>
                </td>
                <td align="left" valign="middle" class="infoline">
                    <div align="center"><kul:htmlControlAttribute property="document.names[${status.index}].suffix" attributeEntry="${docNameAttributes.suffix}" readOnly="${readOnlyEntity or !canModify}"  displayMask="${maskData}" displayMaskValue="Xxxxxxx" />
                </div>
                </td>
                <td align="left" valign="middle" class="infoline">
                    <div align="center"><kul:htmlControlAttribute property="document.names[${status.index}].dflt" attributeEntry="${docNameAttributes.dflt}" readOnly="${readOnlyEntity or !canModify}" />
                </div>
                </td>
                <td align="left" valign="middle" class="infoline">
                    <div align="center"><kul:htmlControlAttribute property="document.names[${status.index}].active" attributeEntry="${docNameAttributes.active}" readOnly="${readOnlyEntity or !canModify}" />
                </div>
                </td>
                <c:if test="${not inquiry and canModify}">                        
                    <td>
                        <div align="center">&nbsp;
                         <c:choose>
                           <c:when test="${name.edit or readOnlyEntity}">
                              <img class='nobord' src='${ConfigProperties.kr.externalizable.images.url}tinybutton-delete2.gif' styleClass='tinybutton'/>
                           </c:when>
                           <c:otherwise>
                              <html:image property='methodToCall.deleteName.line${status.index}.anchor${currentTabIndex}'
                                    src='${ConfigProperties.kr.externalizable.images.url}tinybutton-delete1.gif' styleClass='tinybutton'/>
                           </c:otherwise>
                         </c:choose>  
                        </div>
                    </td>
                </c:if>    
            </tr>
        </c:forEach> 
    </table>
</kul:subtab>
