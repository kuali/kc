<%--
 Copyright 2009 The Kuali Foundation
 
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

<c:set var="roleMemberAttributes" value="${DataDictionary.KimDocumentRoleMember.attributes}" />
<c:set var="roleQualifierAttributes" value="${DataDictionary.KimDocumentRoleQualifier.attributes}" />
<c:set var="kimAttributes" value="${DataDictionary.KimAttributeImpl.attributes}" />

<c:if test="${!(empty KualiForm.document.members) || canModifyAssignees}">
<kul:tab tabTitle="Assignees" defaultOpen="true" tabErrorKey="document.member*,member.*">
    <div class="tab-container" align="center">
    <kul:tableRenderPagingBanner pageNumber="${KualiForm.memberTableMetadata.viewedPageNumber}"
                                totalPages="${KualiForm.memberTableMetadata.totalNumberOfPages}"
                                firstDisplayedRow="${KualiForm.memberTableMetadata.firstRowIndex}" 
                                lastDisplayedRow="${KualiForm.memberTableMetadata.lastRowIndex}"
                                resultsActualSize="${KualiForm.memberTableMetadata.resultsActualSize}" 
                                resultsLimitedSize="${KualiForm.memberTableMetadata.resultsLimitedSize}"
                                buttonExtraParams=".anchor${currentTabIndex}"/>
    <input type="hidden" name="memberTableMetadata.${Constants.TableRenderConstants.PREVIOUSLY_SORTED_COLUMN_INDEX_PARAM}" value="${KualiForm.memberTableMetadata.columnToSortIndex}"/>
    <input type="hidden" name="memberTableMetadata.sortDescending" value="${KualiForm.memberTableMetadata.sortDescending}"/>
    <input type="hidden" name="memberTableMetadata.viewedPageNumber" value="${KualiForm.memberTableMetadata.viewedPageNumber}"/>
    
    <table cellpadding="0" cellspacing="0" summary="">
            <tr>
                <th>&nbsp;</th> 
                <kul:htmlAttributeHeaderCell attributeEntry="${roleMemberAttributes.memberTypeCode}" horizontal="false" />
                <kul:htmlAttributeHeaderCell attributeEntry="${roleMemberAttributes.memberId}" horizontal="false" />
                <kul:htmlAttributeHeaderCell attributeEntry="${roleMemberAttributes.memberNamespaceCode}" horizontal="false" />
                <kul:htmlAttributeHeaderCell attributeEntry="${roleMemberAttributes.memberName}" horizontal="false" />
                <kul:htmlAttributeHeaderCell attributeEntry="${roleMemberAttributes.activeFromDate}" horizontal="false" />
                <kul:htmlAttributeHeaderCell attributeEntry="${roleMemberAttributes.activeToDate}" horizontal="false" />
                <c:forEach var="attrDefn" items="${KualiForm.document.kimType.attributeDefinitions}" varStatus="status">
                    <c:set var="fieldName" value="${attrDefn.attributeName}" />
                    <c:set var="attrEntry" value="${KualiForm.document.attributeEntry[fieldName]}" />
                    <kul:htmlAttributeHeaderCell attributeEntry="${attrEntry}" useShortLabel="false" />
                </c:forEach>
                <c:if test="${canModifyAssignees}"> 
                    <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
                </c:if> 
            </tr>     
            <c:if test="${canModifyAssignees}"> 
             <tr>
                <th class="infoline">Add:</th>
                <td align="left" valign="middle" class="infoline">
                <div align="center">
                    <kul:htmlControlAttribute property="member.memberTypeCode" 
                    attributeEntry="${roleMemberAttributes.memberTypeCode}" 
                    onchange="changeMemberTypeCode(this.form)" disabled="${!canModifyAssignees}" />
                    <NOSCRIPT>
                        <input type="image" tabindex="32768" name="methodToCall.changeMemberTypeCode" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-refresh.gif" class="tinybutton" title="Click to refresh the page after changing the member type." alt="Click to refresh the page after changing the member type." />
                    </NOSCRIPT>              
                </div>
                <c:set var="bo" value="${KualiForm.memberBusinessObjectName}"/>
                <c:set var="fc" value="${KualiForm.memberFieldConversions}"/>
                </td>
                <td class="infoline">   
                <div align="center">                
                    <kul:htmlControlAttribute kimTypeId="${KualiForm.document.kimType.kimTypeId}" property="member.memberId" attributeEntry="${roleMemberAttributes.memberId}" readOnly="${!canModifyAssignees}"/>
                    <c:if test="${canModifyAssignees}">
                        <kul:lookup boClassName="${bo}" fieldConversions="${fc}" anchor="${tabKey}" />
                    </c:if>
                </div>
                </td>
                <td class="infoline">   
                <div align="center">                
                    <kul:htmlControlAttribute property="member.memberNamespaceCode" attributeEntry="${roleMemberAttributes.memberNamespaceCode}" readOnly="true" />
                </div>
                </td>
                <td class="infoline">   
                <div align="center">                
                    <kul:htmlControlAttribute property="member.memberName" attributeEntry="${roleMemberAttributes.memberName}" readOnly="true" />
                </div>
                </td>
                <td align="left" valign="middle" class="infoline">
                <div align="center">
                    <kul:htmlControlAttribute property="member.activeFromDate" attributeEntry="${roleMemberAttributes.activeFromDate}" datePicker="true" readOnly="${!canModifyAssignees}" />
                </div>
                </td>
                <td align="left" valign="middle" class="infoline">
                <div align="center">
                    <kul:htmlControlAttribute property="member.activeToDate" attributeEntry="${roleMemberAttributes.activeToDate}" datePicker="true" readOnly="${!canModifyAssignees}" />
                </div>
                </td>
                <c:forEach var="qualifier" items="${KualiForm.document.kimType.attributeDefinitions}" varStatus="statusQualifier">
                    <c:set var="fieldName" value="${qualifier.attributeName}" />
                    <c:set var="attrEntry" value="${KualiForm.document.attributeEntry[fieldName]}" />
                    <c:set var="attrDefinition" value="${KualiForm.document.definitionsKeyedByAttributeName[fieldName]}"/>
                    <td align="left" valign="middle">
                        <div align="center"> <kul:htmlControlAttribute kimTypeId="${KualiForm.document.kimType.kimTypeId}" property="member.qualifier(${qualifier.kimAttributeId}).attrVal"  attributeEntry="${attrEntry}" readOnly="${!canModifyAssignees}" />
 	               	    <c:if test="${attrDefinition.hasLookupBoDefinition}"> 
                            <c:if test="${!empty attrDefinition.lookupBoClass and not readOnlyAssignees}">
                              <kim:attributeLookup attributeDefinitions="${KualiForm.document.definitions}" pathPrefix="member" attr="${attrDefinition}" />
                            </c:if>
                        </c:if>
                        </div>
                    </td>
                </c:forEach>
                <td class="infoline">
                    <div align="center">
                        <c:choose>
                        <c:when test="${canModifyAssignees}">
                            <html:image property="methodToCall.addMember.anchor${tabKey}"
                            src='${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
                        </c:when>
                        <c:otherwise>
                            <html:image property="methodToCall.addMember.anchor${tabKey}"
                            src='${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton" disabled="true"/>
                        </c:otherwise>
                        </c:choose>
                    </div>
                </td>
           </tr>         
          </c:if>
    <c:if test="${KualiForm.memberTableMetadata.firstRowIndex >= 0}">
        <c:forEach var="member" items="${KualiForm.document.members}" varStatus="statusMember"
                 begin="${KualiForm.memberTableMetadata.firstRowIndex}" 
                 end="${KualiForm.memberTableMetadata.lastRowIndex}">
            <c:set var="rows" value="2"/>
            <c:if test="${fn:length(member.roleRspActions) == 0}">  
                   <c:set var="rows" value="1"/>            
            </c:if>         
            <tr>
                <th rowspan="${rows}" class="infoline" valign="top">
                    <c:out value="${statusMember.index+1}" />
                </th>
                <td align="left" valign="middle">
                    <div align="center"> 
                        <html:link linkName="${KualiForm.document.members[statusMember.index].roleMemberId}" />
                        <kul:htmlControlAttribute property="document.members[${statusMember.index}].memberTypeCode"  attributeEntry="${roleMemberAttributes.memberTypeCode}" disabled="true" readOnly="false" />
                    </div>
                </td>
                <td align="left" valign="middle">
                    <div align="center"> <kul:htmlControlAttribute property="document.members[${statusMember.index}].memberId"  attributeEntry="${roleMemberAttributes.memberId}" readOnly="true" />
                    </div>
                </td>
                <td align="left" valign="middle">
                    <div align="center"> <kul:htmlControlAttribute property="document.members[${statusMember.index}].memberNamespaceCode"  attributeEntry="${roleMemberAttributes.memberNamespaceCode}" readOnly="true"  />
                    </div>
                </td>
                <td align="left" valign="middle">
                    <div align="center"> <kul:htmlControlAttribute property="document.members[${statusMember.index}].memberName"  attributeEntry="${roleMemberAttributes.memberName}" readOnly="true"  />
                    </div>
                </td>
                <td align="left" valign="middle">
                    <div align="center"> <kul:htmlControlAttribute property="document.members[${statusMember.index}].activeFromDate"  attributeEntry="${roleMemberAttributes.activeFromDate}" readOnly="${!canModifyAssignees}" datePicker="true" />
                    </div>
                </td>
                <td align="left" valign="middle">
                    <div align="center"> <kul:htmlControlAttribute property="document.members[${statusMember.index}].activeToDate"  attributeEntry="${roleMemberAttributes.activeToDate}" readOnly="${!canModifyAssignees}" datePicker="true" />
                    </div>
                </td>
                <c:set var="numberOfColumns" value="${KualiForm.member.numberOfQualifiers+6}"/>
                <c:forEach var="qualifier" items="${KualiForm.document.kimType.attributeDefinitions}" varStatus="statusQualifier">
                    <c:set var="fieldName" value="${qualifier.attributeName}" />
                    <c:set var="attrEntry" value="${KualiForm.document.attributeEntry[fieldName]}" />
                    <c:set var="attrDefinition" value="${KualiForm.document.definitionsKeyedByAttributeName[fieldName]}"/>
                    <c:set var="attrReadOnly" value="${(!canModifyAssignees || (attrDefinition.unique && member.edit))}"/>
                    <td align="left" valign="middle">
                        <div align="center"> <kul:htmlControlAttribute kimTypeId="${KualiForm.document.kimType.kimTypeId}" property="document.members[${statusMember.index}].qualifier(${qualifier.kimAttributeId}).attrVal"  attributeEntry="${attrEntry}" readOnly="${attrReadOnly}" />
                        <c:if test="${attrDefinition.hasLookupBoDefinition}"> 
                            <c:if test="${!empty attrDefinition.lookupBoClass and not attrReadOnly}">
                              <kim:attributeLookup attributeDefinitions="${KualiForm.document.definitions}" pathPrefix="document.members[${statusMember.index}]" attr="${attrDefinition}" />
                            </c:if>
                        </c:if>
                        </div>
                    </td>
                </c:forEach>
            <c:if test="${canModifyAssignees}"> 
                <td>
                    <div align="center">&nbsp;
                        <c:choose>
							<c:when test="${member.edit or readOnlyAssignees}">
                                <img class='nobord' src='${ConfigProperties.kr.externalizable.images.url}tinybutton-delete2.gif' styleClass='tinybutton'/>
                            </c:when>
                            <c:otherwise>
                                <html:image property='methodToCall.deleteMember.line${statusMember.index}.anchor${currentTabIndex}'
                                src='${ConfigProperties.kr.externalizable.images.url}tinybutton-delete1.gif' styleClass='tinybutton'/>
                            </c:otherwise>
                        </c:choose>  
                    &nbsp;</div>
                </td>
            </c:if>    
            </tr>
            <c:if test="${fn:length(member.roleRspActions) != 0}">  
                    <tr>
                  <td colspan="${numberOfColumns}" style="padding:0px;">
                    <kim:responsibilityActions mbrIdx="${statusMember.index}" />
                  </td>
                </tr>
            </c:if>  
        </c:forEach>        
    </c:if> 
    </table>
    </div>
</kul:tab>
</c:if>
