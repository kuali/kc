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
            <kra-irb-action:approveAction tabTitle="Approve Action"
                                          bean="${KualiForm.actionHelper.protocolFullApprovalBean}"
                                          property="actionHelper.protocolFullApprovalBean"
                                          taskName="protocolApprove"
                                          methodToCall="grantFullApproval"
                                          canPerformAction="${KualiForm.actionHelper.canApproveFull}"
                                          defaultOpen="${KualiForm.actionHelper.isApproveOpenForFollowup}" />
            <kra-irb-action:approveAction tabTitle="Expedited Approval"
                                          bean="${KualiForm.actionHelper.protocolExpeditedApprovalBean}"
                                          property="actionHelper.protocolExpeditedApprovalBean"
                                          taskName="protocolExpediteApproval"
                                          methodToCall="grantExpeditedApproval"
                                          canPerformAction="${KualiForm.actionHelper.canApproveExpedited}" />
            <kra-irb-action:approveAction tabTitle="Response Approval"
                                          bean="${KualiForm.actionHelper.protocolResponseApprovalBean}"
                                          property="actionHelper.protocolResponseApprovalBean"
                                          taskName="protocolResponseApproval"
                                          methodToCall="grantResponseApproval"
                                          canPerformAction="${KualiForm.actionHelper.canApproveResponse}" />
            <kra-irb-action:recordCommitteeDecisionAction />
            <kra-irb-action:genericAction tabTitle="Defer Action"
			                              bean="${KualiForm.actionHelper.protocolDeferBean}"
			                              property="actionHelper.protocolDeferBean"
			                              taskName="protocolDefer"
			                              methodToCall="defer"
			                              canPerformAction="${KualiForm.actionHelper.canDefer}" />
            <kra-irb-action:genericAction tabTitle="Disapprove"
                                          bean="${KualiForm.actionHelper.protocolDisapproveBean}"
                                          property="actionHelper.protocolDisapproveBean"
                                          taskName="protocolDisapprove"
                                          methodToCall="disapproveProtocol"
                                          canPerformAction="${KualiForm.actionHelper.canDisapprove}"
                                          defaultOpen="${KualiForm.actionHelper.isDisapproveOpenForFollowup}"/>
            <kra-irb-action:genericAction tabTitle="Return for Specific Minor Revisions"
                                          bean="${KualiForm.actionHelper.protocolSMRBean}"
                                          property="actionHelper.protocolSMRBean"
                                          taskName="protocolReturnForSMR"
                                          methodToCall="returnForSMR"
                                          canPerformAction="${KualiForm.actionHelper.canReturnForSMR}"
                                          defaultOpen="${KualiForm.actionHelper.isReturnForSMROpenForFollowup}" />
            <kra-irb-action:genericAction tabTitle="Return for Substantive Revisions Required"
                                          bean="${KualiForm.actionHelper.protocolSRRBean}"
                                          property="actionHelper.protocolSRRBean"
                                          taskName="protocolReturnForSRR"
                                          methodToCall="returnForSRR"
                                          canPerformAction="${KualiForm.actionHelper.canReturnForSRR}"
                                          defaultOpen="${KualiForm.actionHelper.isReturnForSRROpenForFollowup}" />
            <kra-irb-action:notifyIrbAction />
            <kra-irb-action:genericAction tabTitle="IRB Acknowledgement"
                                          bean="${KualiForm.actionHelper.protocolIrbAcknowledgementBean}"
                                          property="actionHelper.protocolIrbAcknowledgementBean"
                                          taskName="irbAcknowledgement"
                                          methodToCall="irbAcknowledgement"
                                          canPerformAction="${KualiForm.actionHelper.canIrbAcknowledgement}" />
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
            <kra-irb-action:genericAction tabTitle="Close Enrollment"
                                          bean="${KualiForm.actionHelper.protocolCloseEnrollmentBean}"
                                          property="actionHelper.protocolCloseEnrollmentBean"
                                          taskName="protocolCloseEnrollment"
                                          methodToCall="closeEnrollment"
                                          canPerformAction="${KualiForm.actionHelper.canCloseEnrollment}"
                                          canAddReviewComments="${KualiForm.actionHelper.canAddCloseEnrollmentReviewerComments}" />
            <kra-irb-action:genericAction tabTitle="Re-open Enrollment"
                                          bean="${KualiForm.actionHelper.protocolReopenEnrollmentBean}"
                                          property="actionHelper.protocolReopenEnrollmentBean"
                                          taskName="protocolReopen"
                                          methodToCall="reopenEnrollment"
                                          canPerformAction="${KualiForm.actionHelper.canReopenEnrollment}"
                                          canAddReviewComments="${KualiForm.actionHelper.canAddReopenEnrollmentReviewerComments}" />
            <kra-irb-action:genericAction tabTitle="Data Analysis Only"
                                          bean="${KualiForm.actionHelper.protocolPermitDataAnalysisBean}"
                                          property="actionHelper.protocolPermitDataAnalysisBean"
                                          taskName="protocolPermitDataAnalysis"
                                          methodToCall="permitDataAnalysis"
                                          canPerformAction="${KualiForm.actionHelper.canPermitDataAnalysis}"
                                          canAddReviewComments="${KualiForm.actionHelper.canAddDataAnalysisReviewerComments}" />
            <kra-irb-action:genericAction tabTitle="Suspend"
                                          bean="${KualiForm.actionHelper.protocolSuspendBean}"
                                          property="actionHelper.protocolSuspendBean"
                                          taskName="protocolSuspend"
                                          methodToCall="suspend"
                                          canPerformAction="${KualiForm.actionHelper.canSuspend}"
                                          canAddReviewComments="${KualiForm.actionHelper.canAddSuspendReviewerComments}" />
            <kra-irb-action:genericAction tabTitle="Suspend By DSMB"
                                          bean="${KualiForm.actionHelper.protocolSuspendByDsmbBean}"
                                          property="actionHelper.protocolSuspendByDsmbBean"
                                          taskName="protocolSuspendByDsmb"
                                          methodToCall="suspendByDsmb"
                                          canPerformAction="${KualiForm.actionHelper.canSuspendByDsmb}"
                                          canAddReviewComments="false" />
            <kra-irb-action:genericAction tabTitle="Close"
                                          bean="${KualiForm.actionHelper.protocolCloseBean}"
                                          property="actionHelper.protocolCloseBean"
                                          taskName="protocolClose"
                                          methodToCall="closeProtocol"
                                          canPerformAction="${KualiForm.actionHelper.canClose}"
                                          canAddReviewComments="${KualiForm.actionHelper.canAddCloseReviewerComments}" />
            <kra-irb-action:genericAction tabTitle="Expire"
                                          bean="${KualiForm.actionHelper.protocolExpireBean}"
                                          property="actionHelper.protocolExpireBean"
                                          taskName="protocolExpire"
                                          methodToCall="expire"
                                          canPerformAction="${KualiForm.actionHelper.canExpire}" />
            <kra-irb-action:genericAction tabTitle="Terminate"
                                          bean="${KualiForm.actionHelper.protocolTerminateBean}"
                                          property="actionHelper.protocolTerminateBean"
                                          taskName="protocolTerminate"
                                          methodToCall="terminate"
                                          canPerformAction="${KualiForm.actionHelper.canTerminate}"
                                          canAddReviewComments="${KualiForm.actionHelper.canAddTerminateReviewerComments}" />
            <kra-irb-action:reviewNotRequiredAction />
            <kra-irb-action:undoLastAction />
            <kra-irb-action:manageReviewComments />
            <kra-irb-action:manageNotes />
		</c:if>
	</div>
</kul:tabTop>
