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

<c:set var="isOpen" value="${KualiForm.auditActivated}" />
<c:forEach items="${param}" var="par">
    <c:if test="${fn:startsWith(par.key, 'lookupActionAmendRenewProtocol') and fn:startsWith(par.value, 'true')}">
        <c:set var="isOpen" value="true" />
    </c:if>
    <c:if test="${fn:startsWith(par.key, 'lookupActionNotifyIRBProtocol') and fn:startsWith(par.value, 'true')}">
        <c:set var="isOpen" value="true" />
    </c:if>
    <c:if test="${fn:startsWith(par.key, 'lookupActionRequestProtocol') and fn:startsWith(par.value, 'true')}">
        <c:set var="isOpen" value="true" />
    </c:if>
</c:forEach>

<kul:tabTop tabTitle="Request an Action" defaultOpen="${isOpen}" tabErrorKey="">
	<div class="tab-container"  align="center">
		<h3> 
			<span class="subhead-left">Available Actions</span>
		</h3>
		<c:if test="${KualiForm.document.protocol.active and showActions}">
            <kra-irb-action:submitAction />
            <kra-irb-action:withdrawAction />
            <kra-irb-action:modifySubmissionAction />
            <kra-irb-action:assignCmtSchedAction />
            <kra-irb-action:assignReviewersAction />
            <kra-irb-action:assignToAgendaAction />
            <kra-irb-action:grantExemptionAction />
            <kra-irb-action:approveAction />
            <kra-irb-action:recordCommitteeDecisionAction />
            <kra-irb-action:deferAction />
            <kra-irb-action:expediteApprovalAction />
            <kra-irb-action:responseApprovalAction />
            <kra-irb-action:disapproveAction />
            <kra-irb-action:smrAction />
            <kra-irb-action:srrAction />
            <kra-irb-action:notifyIrbAction />
            <kra-irb-action:irbAcknowledgement />
            <kra-irb-action:createAmendmentAction />
            <kra-irb-action:createRenewalWithAmendmentAction />
            <kra-irb-action:createRenewalAction />
            <kra-irb-action:closeRequestAction />
            <kra-irb-action:suspendRequestAction />
            <kra-irb-action:closeEnrollmentRequestAction />
            <kra-irb-action:reopenEnrollmentRequestAction />
            <kra-irb-action:dataAnalysisRequestAction />
            <kra-irb-action:terminateRequestAction />
            <kra-irb-action:deleteAction />
            <kra-irb-action:makeAdminCorrectionAction />
            <kra-irb-action:closeEnrollmentAction />
            <kra-irb-action:reopenAction />
            <kra-irb-action:permitDataAnalysisAction />
            <kra-irb-action:suspendAction />
            <kra-irb-action:suspendByDsmbAction />
            <kra-irb-action:closeAction />
            <kra-irb-action:expireAction />
            <kra-irb-action:terminateAction />
            <kra-irb-action:reviewNotRequiredAction />
            <kra-irb-action:undoLastAction />
            <kra-irb-action:manageReviewComments />
		</c:if>
	</div>
</kul:tabTop>
