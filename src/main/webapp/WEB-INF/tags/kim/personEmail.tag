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

<c:set var="docEmailAttributes" value="${DataDictionary.PersonDocumentEmail.attributes}" />

<c:set var="canModify" scope="request" value="${!KualiForm.document.privacy.suppressEmail || KualiForm.canOverrideEntityPrivacyPreferences}" />
<c:set var="maskData" value="${KualiForm.document.privacy.suppressPhone && !KualiForm.canOverrideEntityPrivacyPreferences}" />

<kul:subtab lookedUpCollectionName="email" width="${tableWidth}" subTabTitle="Email Addresses" noShowHideButton="true">      
    <table cellpadding="0" cellspacing="0" summary="">
        <tr>
            <th>&nbsp;</th> 
            <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docEmailAttributes.emailAddress}" noColon="true" /></div></th>
            <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docEmailAttributes.emailTypeCode}" noColon="true" /></div></th>
            <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docEmailAttributes.dflt}" noColon="true" /></div></th>
            <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docEmailAttributes.active}" noColon="true" /></div></th>
            <c:if test="${not inquiry and canModify}">  
                <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
            </c:if>
        </tr>     
        <c:if test="${not inquiry and not readOnlyEntity and canModify}">
            <tr>
                <th class="infoline">Add:</th>
                <td class="infoline">   
                <div align="center">                
                  <kul:htmlControlAttribute property="newEmail.emailAddress" attributeEntry="${docEmailAttributes.emailAddress}" readOnly="${readOnlyEntity}" />
                </div>
                </td>
                <td align="left" valign="middle" class="infoline">
                <div align="center">
                    <kul:htmlControlAttribute property="newEmail.emailTypeCode" attributeEntry="${docEmailAttributes.emailTypeCode}" readOnly="${readOnlyEntity}" />
                </div>
                </td>
                <td class="infoline">   
                <div align="center">                
                  <kul:htmlControlAttribute property="newEmail.dflt" attributeEntry="${docEmailAttributes.dflt}" readOnly="${readOnlyEntity}" />
                </div>
                </td>
                <td class="infoline">   
                <div align="center">                
                  <kul:htmlControlAttribute property="newEmail.active" attributeEntry="${docEmailAttributes.active}" readOnly="${readOnlyEntity}" />
                </div>
                </td>                                
                <td class="infoline">
                    <div align=center>
                        <html:image property="methodToCall.addEmail.anchor${tabKey}"
                        src='${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
                    </div>
                </td>
            </tr>         
        </c:if>        
        <c:forEach var="email" items="${KualiForm.document.emails}" varStatus="status">
            <tr>
                <th class="infoline">
                    <c:out value="${status.index+1}" />
                </th>
                <td>     
                    <div align="center">            
                      <kul:htmlControlAttribute property="document.emails[${status.index}].emailAddress" attributeEntry="${docEmailAttributes.emailAddress}" readOnly="${readOnlyEntity or !canModify}" displayMask="${maskData}" displayMaskValue="xxxxx@xxx.xxx" />
                    </div>
                </td>
                <td align="left" valign="middle">
                    <div align="center"> <kul:htmlControlAttribute property="document.emails[${status.index}].emailTypeCode"  attributeEntry="${docEmailAttributes.emailTypeCode}"  readOnlyAlternateDisplay="${fn:escapeXml(email.emailType.name)}" readOnly="${readOnlyEntity or !canModify}" />
                    </div>
                </td>
                <td>
                    <div align="center">            
                      <kul:htmlControlAttribute property="document.emails[${status.index}].dflt" attributeEntry="${docEmailAttributes.dflt}" readOnly="${readOnlyEntity or !canModify}" />
                    </div>
                </td>
                <td>
                    <div align="center">            
                      <kul:htmlControlAttribute property="document.emails[${status.index}].active" attributeEntry="${docEmailAttributes.active}" readOnly="${readOnlyEntity or !canModify}" />
                    </div>
                </td>
                <c:if test="${not inquiry and canModify}">                      
                    <td>
                        <div align="center">&nbsp;
                             <c:choose>
                               <c:when test="${email.edit  or readOnlyEntity}">
                                  <img class='nobord' src='${ConfigProperties.kr.externalizable.images.url}tinybutton-delete2.gif' styleClass='tinybutton'/>
                               </c:when>
                               <c:otherwise>
                                  <html:image property='methodToCall.deleteEmail.line${status.index}.anchor${currentTabIndex}'
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
