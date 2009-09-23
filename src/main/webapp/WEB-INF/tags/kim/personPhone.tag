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

<c:set var="docPhoneAttributes" value="${DataDictionary.PersonDocumentPhone.attributes}" />

<c:set var="canModify" scope="request" value="${!KualiForm.document.privacy.suppressPhone || KualiForm.canOverrideEntityPrivacyPreferences}" />
<c:set var="maskData" value="${KualiForm.document.privacy.suppressPhone && !KualiForm.canOverrideEntityPrivacyPreferences}" />

<kul:subtab lookedUpCollectionName="phone" width="${tableWidth}" subTabTitle="Phone Numbers" noShowHideButton="true">      
    <table cellpadding="0" cellspacing="0" summary="">
        <tr>
            <th>&nbsp;</th> 
            <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docPhoneAttributes.phoneTypeCode}" noColon="true" /></div></th>
            <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docPhoneAttributes.phoneNumber}" noColon="true" /></div></th>
            <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docPhoneAttributes.extensionNumber}" noColon="true" /></div></th>
            <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docPhoneAttributes.countryCode}" noColon="true" /></div></th>
            <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docPhoneAttributes.dflt}" noColon="true" /></div></th>
            <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docPhoneAttributes.active}" noColon="true" /></div></th>
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
                        <kul:htmlControlAttribute property="newPhone.phoneTypeCode" attributeEntry="${docPhoneAttributes.phoneTypeCode}" readOnly="${readOnlyEntity}" />
                    </div>
                </td>
                <td class="infoline">
                    <div align="center">
                        <kul:htmlControlAttribute property="newPhone.phoneNumber" attributeEntry="${docPhoneAttributes.phoneNumber}" readOnly="${readOnlyEntity}" />
                    </div>
                </td>
                <td class="infoline">   
                    <div align="center">                
                      <kul:htmlControlAttribute property="newPhone.extensionNumber" attributeEntry="${docPhoneAttributes.extensionNumber}" readOnly="${readOnlyEntity}" />
                    </div>
                </td>
                <td align="left" valign="middle" class="infoline">
                    <div align="center"><kul:htmlControlAttribute property="newPhone.countryCode" attributeEntry="${docPhoneAttributes.countryCode}" readOnly="${readOnlyEntity}" />
                    </div>
                </td>
                <td class="infoline">   
                    <div align="center">                
                      <kul:htmlControlAttribute property="newPhone.dflt" attributeEntry="${docPhoneAttributes.dflt}" readOnly="${readOnlyEntity}" />
                    </div>
                </td>
                <td class="infoline">   
                    <div align="center">                
                      <kul:htmlControlAttribute property="newPhone.active" attributeEntry="${docPhoneAttributes.active}" readOnly="${readOnlyEntity}" />
                    </div>
                </td>                                
                <td class="infoline">
                    <div align=center>
                        <html:image property="methodToCall.addPhone.anchor${tabKey}"
                        src='${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
                    </div>
                </td>
            </tr>         
        </c:if>        
        <c:forEach var="phone" items="${KualiForm.document.phones}" varStatus="status">
            <tr>
                <th class="infoline">
                    <c:out value="${status.index+1}" />
                </th>
                <td align="left" valign="middle">
                    <div align="center"> <kul:htmlControlAttribute property="document.phones[${status.index}].phoneTypeCode"  attributeEntry="${docPhoneAttributes.phoneTypeCode}"  readOnlyAlternateDisplay="${fn:escapeXml(phone.phoneType.phoneTypeName)}" readOnly="${readOnlyEntity or !canModify}" />
                    </div>
                </td>
                <td>
                    <div align="center"> <kul:htmlControlAttribute property="document.phones[${status.index}].phoneNumber" attributeEntry="${docPhoneAttributes.phoneNumber}" readOnly="${readOnlyEntity or !canModify}"   displayMask="${maskData}" displayMaskValue="(XXX) XXX-XXXX" />
                    </div>
                </td>
                <td>     
                    <div align="center">            
                      <kul:htmlControlAttribute property="document.phones[${status.index}].extensionNumber" attributeEntry="${docPhoneAttributes.extensionNumber}" readOnly="${readOnlyEntity or !canModify}" displayMask="${maskData}" displayMaskValue="XXXX" />
                    </div>
                </td>
                <td align="left" valign="middle" class="infoline">
                    <div align="center"><kul:htmlControlAttribute property="document.phones[${status.index}].countryCode" attributeEntry="${docPhoneAttributes.countryCode}" readOnly="${readOnlyEntity or !canModify}" displayMask="${maskData}" displayMaskValue="XX" />
                    </div>
                </td>
                <td align="left" valign="middle" class="infoline">
                    <div align="center"><kul:htmlControlAttribute property="document.phones[${status.index}].dflt" attributeEntry="${docPhoneAttributes.dflt}" readOnly="${readOnlyEntity or !canModify}" />
                    </div>
                </td>
                <td align="left" valign="middle" class="infoline">
                    <div align="center"><kul:htmlControlAttribute property="document.phones[${status.index}].active" attributeEntry="${docPhoneAttributes.active}" readOnly="${readOnlyEntity or !canModify}" />
                    </div>
                </td>
                <c:if test="${not inquiry and canModify}">                      
                    <td>
                        <div align="center">&nbsp;
                             <c:choose>
                               <c:when test="${phone.edit or readOnlyEntity}">
                                  <img class='nobord' src='${ConfigProperties.kr.externalizable.images.url}tinybutton-delete2.gif' styleClass='tinybutton'/>
                               </c:when>
                               <c:otherwise>
                                  <html:image property='methodToCall.deletePhone.line${status.index}.anchor${currentTabIndex}'
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
