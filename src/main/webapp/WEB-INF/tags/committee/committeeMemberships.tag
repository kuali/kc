<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<c:set var="committeeMembershipAttributes" value="${DataDictionary.CommitteeMembership.attributes}" />

<div id="workarea">
<c:forEach items="${KualiForm.document.committee.committeeMemberships}" var="membership" varStatus="status">
    <c:set var="committeeMembershipProperty" value="document.committee.committeeMemberships[${status.index}]" />
    <c:set var="transparent" value="false" />

    <c:if test="${status.first}">
      <c:set var="transparent" value="true" />
    </c:if>
    <c:set var="description" value="Term ${committeeMembershipProperty}.termStartDate - ${committeeMembershipProperty}.termEndDate" /> 
    <c:set var="membershipIndex" value="${status.index}" />
    <kul:tab tabTitle="${fn:substring(membership.personName, 0, 22)} (${membership.membershipType.description})"
             tabErrorKey="document.committee.committeeMemberships[${membershipIndex}]*"
             auditCluster="requiredFieldsAuditErrors" 
             tabAuditKey="" 
             useRiceAuditMode="true"
             tabDescription="Term ${membership.termStartDate} - ${membership.termEndDate}"
             leftSideHtmlProperty="${committeeMembershipProperty}.delete" 
             leftSideHtmlAttribute="${committeeMembershipAttributes.delete}" 
             leftSideHtmlDisabled="false" 
             defaultOpen="${hasErrors}" 
             transparentBackground="${transparent}">
             <div class="tab-container" align="center">
                 <div id="workarea">
                     <div class="tab-container" align="center" id="G100">
                         <h3>
                             <span class="subhead-left"><bean:write name="KualiForm" property="${committeeMembershipProperty}.personName" /></span>
                             <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.bo.Committee" altText="help" /></span>
                         </h3>
                         <kra-committee:committeeMembershipDetailsSection committeeMembership="${committeeMembershipProperty}" memberIndex="${status.index}" />
                         <kra-committee:committeeMembershipContactInformationSection committeeMembership="${committeeMembershipProperty}" memberIndex="${status.index}" />
                         <kra-committee:committeeMembershipRolesSection committeeMembership="${committeeMembershipProperty}" memberIndex="${status.index}" />
                         <kra-committee:committeeMembershipExpertiseSection committeeMembership="${committeeMembershipProperty}" memberIndex="${status.index}" />
                     </div>
                 </div>
             </div>
    </kul:tab>
 </c:forEach>

<c:if test="${fn:length(KualiForm.document.committee.committeeMemberships) > 0}">
    <kul:panelFooter />
</c:if>

</div>