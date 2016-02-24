<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
   - 
   - This program is free software: you can redistribute it and/or modify
   - it under the terms of the GNU Affero General Public License as
   - published by the Free Software Foundation, either version 3 of the
   - License, or (at your option) any later version.
   - 
   - This program is distributed in the hope that it will be useful,
   - but WITHOUT ANY WARRANTY; without even the implied warranty of
   - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   - GNU Affero General Public License for more details.
   - 
   - You should have received a copy of the GNU Affero General Public License
   - along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%@ attribute name="committeeMembership" description="The current committee membership which is being processed" required="true" %>
<%@ attribute name="memberIndex" description="The index of the current committee membership which is being processed" required="true" %>
<%@ attribute name="parentTabValue" description="The tabTitle of the parent tab" required="true" %>
<%@ attribute name="readOnly" description="All fields are displayed as read-only elements." required="true" %>

<c:set var="committeeMembershipAttributes" value="${DataDictionary.CommitteeMembership.attributes}" />
<c:set var="personAttributes" value="${DataDictionary.KcPerson.attributes}" />

<%@ attribute name="finderClassName" required="false" %>
<c:if test="${finderClassName == null}">
	<c:set var="finderClassName" value="org.kuali.kra.committee.keyvalue.MembershipRoleValuesFinder" />
</c:if>


<c:set var="membershipRoleAttributes" value="${DataDictionary.CommitteeMembershipRole.attributes}" />

<table border="0" cellpadding="0" cellspacing="0" summary="">
    <tr>
        <td>
            <kul:innerTab tabTitle="Roles" 
                          parentTab="${parentTabValue}" 
                          defaultOpen="false"
                          useCurrentTabIndexAsKey="true" 
                          tabErrorKey="committeeHelper.newCommitteeMembershipRoles[${memberIndex}].*,document.committeeList[0].committeeMemberships[${memberIndex}].membershipRoles*">
                <div class="innerTab-container" align="left">
                    <table border="0" id="membership-role-table-${memberIndex}" cellpadding="0" cellspacing="0" class="datatable" summary="View/edit committee membership roles">
                    
                        <%-- Header --%>
                        <tr>
                            <kul:htmlAttributeHeaderCell literalLabel="&nbsp;" scope="col" align="center" />
                            <kul:htmlAttributeHeaderCell attributeEntry="${membershipRoleAttributes.membershipRoleCode}" scope="col" align="center" />
                            <kul:htmlAttributeHeaderCell attributeEntry="${membershipRoleAttributes.startDate}" scope="col" align="center" />
                            <kul:htmlAttributeHeaderCell attributeEntry="${membershipRoleAttributes.endDate}" scope="col" align="center" />
                            <c:if test="${!readOnly}">
                                <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" align="center" />
                            </c:if>
                        </tr>
                        <%-- Header --%>
                        
                        <%-- New data --%>
                        <c:if test="${!readOnly}">
                            <tr>
                                <th class="infoline" align="center">
                                    <c:out value="Add:" />
                                </th>
                                <!-- this is where I would add different roles -->
                                <td align="left" valign="middle" class="infoline">
                                    <div align="left">
                                       <html:select property="committeeHelper.newCommitteeMembershipRoles[${memberIndex}].membershipRoleCode" style="width:180px" tabindex="0" disabled="${readOnly}">
										<c:forEach items="${krafn:getOptionList(finderClassName, paramMap1)}" var="option">											
											<option value="${option.key}">${option.value}</option>
										</c:forEach>
										</html:select>
                                    </div>
                                </td>
                                
                                <td align="left" valign="middle" class="infoline">
                                    <div align="center">
                                        <kul:htmlControlAttribute property="committeeHelper.newCommitteeMembershipRoles[${memberIndex}].startDate" 
                                                                  attributeEntry="${membershipRoleAttributes.startDate}" 
                                                                   />
                                    </div>
                                </td>
                                
                                <td align="left" valign="middle" class="infoline">
                                    <div align="center">
                                        <kul:htmlControlAttribute property="committeeHelper.newCommitteeMembershipRoles[${memberIndex}].endDate" 
                                                                  attributeEntry="${membershipRoleAttributes.endDate}" 
                                                                   />
                                    </div>
                                </td>

                               <td align="left" valign="middle" class="infoline">
                                   <div align=center>
                                       <html:image property="methodToCall.addCommitteeMembershipRole.${committeeMembership}.line${memberIndex}"
                                                   src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' 
                                                   styleClass="tinybutton"/>
                                    </div>
                                </td>
                            </tr>
                        </c:if>
                        <%-- New data --%>
                        <%-- Existing data --%>
                        <c:forEach var="membershipRole" items="${KualiForm.document.committeeList[0].committeeMemberships[memberIndex].membershipRoles}" varStatus="status">
                            <c:set var="roleStyle" value="" scope="request"/>
                            <c:set var="tmpErrProp" value="document.committeeList[0].committeeMemberships[${memberIndex}].membershipRoles[${status.index}].membershipRoleCode"/>
                            <c:forEach items="${ErrorPropertyList}" var="key">
                                <c:if test="${key eq tmpErrProp}">
                                    <c:set var="roleStyle" value="background-color:#FFD5D5" scope="request"/>
                                </c:if>
                            </c:forEach>
                            
                            <tr>
                                <th class="infoline" align="center">
                                    <c:out value="${status.index+1}" />
                                </th>
                                <td align="left" valign="middle">
                                    <div align="left" style="${roleStyle}">
                                        <%--
                                        <kul:htmlControlAttribute property="${committeeMembership}.membershipRoles[${status.index}].membershipRoleCode" 
                                                                  attributeEntry="${membershipRoleAttributes.membershipRoleCode}" 
                                                                  readOnlyAlternateDisplay="${membershipRole.membershipRole.description}" 
                                                                  readOnly="true" />
                                        --%>
                                        <c:out value="${membershipRole.membershipRole.description}" />
                                    </div>
                                </td>
                                <td align="left" valign="middle">
                                    <div align="center">
                                        <kul:htmlControlAttribute property="${committeeMembership}.membershipRoles[${status.index}].startDate" 
                                                                  attributeEntry="${membershipRoleAttributes.startDate}" 
                                                                  readOnly="${readOnly}"
                                                                   />
                                    </div>
                                </td>
                                <td align="left" valign="middle">
                                    <div align="center">
                                        <kul:htmlControlAttribute property="${committeeMembership}.membershipRoles[${status.index}].endDate" 
                                                                  attributeEntry="${membershipRoleAttributes.endDate}" 
                                                                  readOnly="${readOnly}"
                                                                   />
                                    </div>
                                </td>
 
                                <c:if test="${!readOnly}">
                                    <td align="left" valign="middle">
                                        <div align="center">                    
                                            <html:image property="methodToCall.deleteCommitteeMembershipRole.${committeeMembership}.line${status.index}"
                                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' 
                                                        styleClass="tinybutton"/>
                                        </div>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                        <%-- Existing data --%>
                        
                    </table>
                </div>
            </kul:innerTab>
        </td>
    </tr>
</table>
