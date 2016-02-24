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

<%@ attribute name="researchAreaReference" required="true" %>
<%@ attribute name="membershipRoleValuesFinderClassName" required="false" %>
<c:if test="${membershipRoleValuesFinderClassName == null}">
	<c:set var="membershipRoleValuesFinderClassName" value="org.kuali.kra.committee.keyvalue.MembershipRoleValuesFinder" />
</c:if>


<c:set var="committeeMembershipAttributes" value="${DataDictionary.CommitteeMembership.attributes}" />

<c:if test="${not empty KualiForm.document.committee.committeeMemberships}">
  <p align="center">
    <c:choose>
        <c:when test="${KualiForm.committeeHelper.showActiveMembersOnly}">
              <html:image property="methodToCall.showAllMembers" 
                          src="${ConfigProperties.kra.externalizable.images.url}tinybutton-showallmembers.gif" 
                          title="Show All Members" 
                          alt="Show All Members" 
                          styleClass="tinybutton"/>
        </c:when>
        <c:otherwise>
              <html:image property="methodToCall.showActiveMembersOnly" 
                          src="${ConfigProperties.kra.externalizable.images.url}tinybutton-showactivemembers.gif" 
                          title="Show Active Members" 
                          alt="Show Active Members" 
                          styleClass="tinybutton"/>
        </c:otherwise>
    </c:choose>
  </p>
</c:if>

<div id="workarea">
<c:forEach items="${KualiForm.document.committee.committeeMemberships}" var="membership" varStatus="status">
	<c:set var="committeeMembershipInactiveExpertiseProperty" value="document.committeeList[0].committeeMemberships[${status.index}].areasOfExpertise.inactive" />
    <c:set var="committeeMembershipProperty" value="document.committeeList[0].committeeMemberships[${status.index}]" />
    <c:set var="committeeMembershipRoleProperty" value="committeeHelper.newCommitteeMembershipRoles[${status.index}]" />
    <c:set var="committeeMembershipExpertiseProperty" value="committeeHelper.newCommitteeMembershipExpertise[${status.index}]" />

    <kul:checkErrors keyMatch="${committeeMembershipInactiveExpertiseProperty}.*,${committeeMembershipProperty}.*,${committeeMembershipRoleProperty}.*,${committeeMembershipExpertiseProperty}.*" />

    <c:if test="${!KualiForm.committeeHelper.showActiveMembersOnly || !membership.wasInactiveAtLastSave || hasErrors || KualiForm.committeeHelper.memberIndex == status.index}">
        
        <c:choose>
            <c:when test="${empty transparent}">
                <c:set var="transparent" value="true" />
            </c:when>
            <c:otherwise>
                <c:set var="transparent" value="false" />
            </c:otherwise>
        </c:choose>
    	
    	<%-- Create Tab Title --%>
        <c:choose>
            <c:when test="${membership.active}">
                <c:set var="tabTitleValue" value="${fn:substring(membership.personName, 0, 22)} (active)" />
            </c:when>
            <c:otherwise>
                <c:set var="tabTitleValue" value="${fn:substring(membership.personName, 0, 22)} (inactive)" />
            </c:otherwise>
        </c:choose>
    
        <%-- Create Tab Description --%>
        <c:choose>
            <c:when test="${empty membership.termStartDate && empty membership.termEndDate}">
                <c:set var="tabDescriptionValue" value=" " />
            </c:when>
            <c:otherwise>
                <c:set var="tabDescriptionValue" value="Term ${membership.formattedTermStartDate} - ${membership.formattedTermEndDate}" />
            </c:otherwise>
        </c:choose>
        
        <%-- Create Delete Checkbox --%>
        <c:choose>
            <c:when test="${!readOnly}">
                <c:set var="deleteProperty" value="${committeeMembershipProperty}.delete" />
            </c:when>
            <c:otherwise>
                <c:set var="deleteProperty" value="" />
            </c:otherwise>    
        </c:choose>
            
        <kul:tab tabTitle="${tabTitleValue}"
                 tabErrorKey="${committeeMembershipProperty}.delete"
                 innerTabErrorKey="${committeeMembershipInactiveExpertiseProperty}.*,${committeeMembershipProperty}.*,${committeeMembershipRoleProperty}.*,${committeeMembershipExpertiseProperty}.*"
                 auditCluster="requiredFieldsAuditErrors" 
                 tabAuditKey="" 
                 useRiceAuditMode="true"
                 tabDescription="${tabDescriptionValue}"
                 leftSideHtmlProperty="${deleteProperty}" 
                 leftSideHtmlAttribute="${committeeMembershipAttributes.delete}" 
                 leftSideHtmlDisabled="${readOnly}" 
                 defaultOpen="false"
                 useCurrentTabIndexAsKey="true" 
                 transparentBackground="${transparent}">
            <div class="tab-container" align="center" id="G100">
              <h3>
                <span class="subhead-left"><bean:write name="KualiForm" property="${committeeMembershipProperty}.personName" /></span>
                <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.committee.bo.CommitteeMembership" altText="help" /></span>
              </h3>
              <kra-committee:committeeMembershipDetailsSection committeeMembership="${committeeMembershipProperty}" memberIndex="${status.index}" parentTabValue="${tabTitleValue}"  readOnly="${readOnly}" />
              <kra-committee:committeeMembershipContactInformationSection committeeMembership="${committeeMembershipProperty}" memberIndex="${status.index}" parentTabValue="${tabTitleValue}"  readOnly="${readOnly}" />
              <kra-committee:committeeMembershipRolesSection committeeMembership="${committeeMembershipProperty}" memberIndex="${status.index}" parentTabValue="${tabTitleValue}"  readOnly="${readOnly}"  finderClassName="${membershipRoleValuesFinderClassName}" />
              <kra-committee:committeeMembershipExpertiseSection researchAreaReference = "${researchAreaReference}" committeeMembership="${committeeMembershipProperty}" memberIndex="${status.index}" parentTabValue="${tabTitleValue}" readOnly="${readOnly}" />
            </div>
        </kul:tab>
    </c:if>
 </c:forEach>

<c:if test="${not empty transparent}">
    <kul:panelFooter />
</c:if>

</div>
