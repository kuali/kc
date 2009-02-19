<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<c:set var="committeeMembershipAttributes" value="${DataDictionary.CommitteeMembership.attributes}" />

<div id="workarea">
<c:forEach items="${KualiForm.document.committee.committeeMemberships}" var="membership" varStatus="status">
    <c:set var="committeeMembershipProperty" value="document.committee.committeeMemberships${status.index}]" />
    <c:set var="transparent" value="false" />

    <c:if test="${status.first}">
      <c:set var="transparent" value="true" />
    </c:if> 
    <c:set var="membershipIndex" value="${status.index}" />
    <kul:tab tabTitle="${fn:substring(membership.personName, 0, 22)}"
             tabErrorKey="document.committee.committeeMemberships[${personIndex}]*"
             auditCluster="requiredFieldsAuditErrors" 
             tabAuditKey="" 
             useRiceAuditMode="true"
             tabDescription="Term <start date> - <end date>"
             leftSideHtmlProperty="${committeeMembershipProperty}.delete" 
             leftSideHtmlAttribute="${committeeMembershipAttributes.delete}" 
             leftSideHtmlDisabled="false" 
             defaultOpen="${hasErrors}" 
             transparentBackground="${transparent}">
             <div class="tab-container" align="center">
                 <div id="workarea">
                     <div class="tab-container" align="center" id="G100">
                         <h3>
                             <span class="subhead-left"> <%-- <bean:write name="KualiForm" property="${committeeMembershipProperty}.getPersonName()"/> --%> blah blah </span>
                             <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.bo.Committee" altText="help"/></span>
                         </h3>
                         <%-- <kra-irb:personDetailsSection personIndex="${status.index}" protocolPerson="${protocolPersonProperty}"/> --%>
                     </div>
                 </div>
             </div>
    </kul:tab>
 </c:forEach>

<c:if test="${fn:length(KualiForm.document.committee.committeeMemberships) > 0}">
    <kul:panelFooter />
</c:if>

</div>