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
			<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.irb.actions.ProtocolAction" altText="help"/></span>
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
            <kra-irb-action:modifyAmendmentSectionsAction />
            <kra-irb-action:createRenewalWithAmendmentAction />
            <kra-irb-action:createRenewalAction />
            <kra-irb-action:requestAction bean="${KualiForm.actionHelper.protocolCloseRequestBean}"
                                          permission="${KualiForm.actionHelper.canRequestClose}"
                                          beanName="protocolCloseRequestBean"
                                          taskName="protocolRequestClose"
                                          actionTypeCode="105" 
                                          tabTitle="Request To Close"/>
            <kra-irb-action:requestAction bean="${KualiForm.actionHelper.protocolSuspendRequestBean}"
                                          permission="${KualiForm.actionHelper.canRequestSuspension}"
                                          beanName="protocolSuspendRequestBean"
                                          taskName="protocolRequestSuspension"
                                          actionTypeCode="106"
                                          tabTitle="Request for Suspension"/>
            <kra-irb-action:requestAction bean="${KualiForm.actionHelper.protocolCloseEnrollmentRequestBean}"
                                          permission="${KualiForm.actionHelper.canRequestCloseEnrollment}"
                                          taskName="protocolRequestCloseEnrollment"
                                          beanName="protocolCloseEnrollmentRequestBean"
                                          actionTypeCode="108"
                                          tabTitle="Request to Close Enrollment"/>
            <kra-irb-action:requestAction bean="${KualiForm.actionHelper.protocolReOpenEnrollmentRequestBean}"
                                          permission="${KualiForm.actionHelper.canRequestReOpenEnrollment}"
                                          beanName="protocolReOpenEnrollmentRequestBean"
                                          taskName="protocolRequestReOpenEnrollment"
                                          actionTypeCode="115"
                                          tabTitle="Request to Re-open Enrollment"/>
            <kra-irb-action:requestAction bean="${KualiForm.actionHelper.protocolDataAnalysisRequestBean}"
                                          permission="${KualiForm.actionHelper.canRequestDataAnalysis}"
                                          beanName="protocolDataAnalysisRequestBean"
                                          taskName="protocolRequestDataAnalysis"
                                          actionTypeCode="114"
                                          tabTitle="Request for Data Analysis Only"/>
            <kra-irb-action:requestAction bean="${KualiForm.actionHelper.protocolTerminateRequestBean}"
                                          permission="${KualiForm.actionHelper.canRequestTerminate}"
                                          beanName="protocolTerminateRequestBean"
                                          taskName="protocolRequestTerminate"
                                          actionTypeCode="104"
                                          tabTitle="Request for Termination"/>
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
            <kra-irb-action:manageNotes />
		</c:if>
	</div>
</kul:tabTop>
