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

<%@ attribute name="readOnly" description="All fields are displayed as read-only elements." required="true" %>

<c:set var="committeeMembershipAttributes" value="${DataDictionary.CommitteeMembership.attributes}" />

<c:if test="${!readOnly}">
    <kra:permission value="${KualiForm.committeeHelper.modifyCommittee}">
        <kra:uncollapsable tabTitle="Add ${KualiForm.document.committee.committeeType.description} Member"
            tabErrorKey="committeeHelper.newCommitteeMembership.*" 
            auditCluster="committeeMembershipAuditErrors" 
            tabAuditKey="newCommitteeMembership*">
          <div align="center">
            <table  cellpadding="0" cellspacing="0" class="grid" summary="">
              <tr>
                <th class="grid"><div align="right">*Person:</div></th>
                <td nowrap class="grid">
                    <c:choose>                  
                        <c:when test="${empty KualiForm.committeeHelper.newCommitteeMembership.personId && empty KualiForm.committeeHelper.newCommitteeMembership.rolodexId}">
                            <label>Employee Search</label>
                            <label>
                                <kul:lookup boClassName="org.kuali.coeus.common.framework.person.KcPerson" 
                                    fieldConversions="personId:committeeHelper.newCommitteeMembership.personId,fullName:committeeHelper.newCommitteeMembership.personName" />
                            </label>
                            <br>
                            <label>Non-employee Search</label> 
                            <label>
                                <kul:lookup boClassName="org.kuali.coeus.common.framework.rolodex.NonOrganizationalRolodex"
                                    fieldConversions="rolodexId:committeeHelper.newCommitteeMembership.rolodexId,fullName:committeeHelper.newCommitteeMembership.personName" />
                            </label>
                        </c:when>
                        <c:otherwise>
                            <label>
                                <kul:htmlControlAttribute property="committeeHelper.newCommitteeMembership.personName" attributeEntry="${committeeMembershipAttributes.personName}" readOnly="true"/> 
                            </label>
                            <br/>
                        </c:otherwise>
                    </c:choose>
                </td>
              </tr>
            </table>
            <br>
            <html:image property="methodToCall.clearCommitteeMembership" 
                 src="${ConfigProperties.kr.externalizable.images.url}tinybutton-clear1.gif" 
                 title="Clear Fields" alt="Clear Fields" 
                 styleClass="tinybutton"/>
            <html:image property="methodToCall.addCommitteeMembership" 
                 src="${ConfigProperties.kra.externalizable.images.url}tinybutton-addmember.gif" 
                 title="Add Committee Member" 
                 alt="Add Committee Member" 
                 styleClass="tinybutton"/>
          </div>
        </kra:uncollapsable>
    </kra:permission>
    <br/>
</c:if>
