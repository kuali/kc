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

<c:set var="isOpen" value="${KualiForm.auditActivated or KualiForm.actionHelper.openForFollowup}" />
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
            <kra-irb-action:genericAction tabTitle="Abandon"
                                          bean="${KualiForm.actionHelper.protocolAbandonBean}"
                                          property="actionHelper.protocolAbandonBean"
                                          taskName="protocolAbandon"
                                          methodToCall="abandon"
                                          canPerformAction="${KualiForm.actionHelper.canAbandon}" 
                                          canAddReviewComments="false"/>
            <kra-irb-action:reviewNotRequiredAction />
            <kra-irb-action:undoLastAction />
            <kra-irb-action:manageReviewComments />
            <kra-irb-action:manageNotes />
		</c:if>		
		 <c:if test="${!KualiForm.document.protocol.active and showActions}">		     	     
		     <table cellpadding="0" cellspacing="0" summary="">
				<tr>
					<td>	
						<div align="left">		     	
		   					<p>No actions available for a canceled protocol document. </p>		   					
						</div>
					</td>
				</tr>
			</table> 
		</c:if>    
	</div>


      
    <div class="tab-container"  align="center">
    <c:if test="${KualiForm.document.protocol.active and showActions}">
        <kul:innerTab tabTitle="Unavailable Actions" parentTab="" defaultOpen="false" useCurrentTabIndexAsKey="true" overrideDivClass="innerTab-h3head">
            <div class="innerTab-container" align="left">
            <c:if test="${KualiForm.document.protocol.active and showActions}">
	            <kra-irb-action:genericUnavailableAction tabTitle="Submit for Review"
                                                         canPerformAction="${KualiForm.actionHelper.canSubmitProtocolUnavailable}" 
                                                         reason="Protocol status must be In Progress, Specific Minor Revision Required,
                                                                 Substantive Revision Required, Amendment In Progress, Renewal In Progress,
                                                                 Deferred, or Withdrawn." />
	            <kra-irb-action:genericUnavailableAction tabTitle="Withdraw Protocol"
                                                         canPerformAction="${KualiForm.actionHelper.canWithdrawUnavailable}" 
                                                         reason="Protocol can not be an amendment or renewal.
                                                                 <p>
                                                                 Protocol status must not be Specific Minor Revisions Required or Substantive 
                                                                 Revisions Required.
                                                                 <p>
                                                                 Protocol submission status must be Submitted To Committee, In Agenda, or Pending.
                                                                 <p>
                                                                 Protocol must be enroute in workflow." />
	            <kra-irb-action:genericUnavailableAction tabTitle="Modify Submission Request"
                                                         canPerformAction="${KualiForm.actionHelper.canModifyProtocolSubmissionUnavailable}" 
                                                         reason="Protocol status must be Submitted To IRB." />
	            <kra-irb-action:genericUnavailableAction tabTitle="Assign to Committee and Schedule"
                                                         canPerformAction="${KualiForm.actionHelper.canAssignCmtSchedUnavailable}" 
                                                         reason="Protocol submission status must be Pending or Submitted To Committee.
                                                                 <p>
                                                                 Protocol must be enroute in workflow." />
	            <kra-irb-action:genericUnavailableAction tabTitle="Assign Reviewers"
                                                         canPerformAction="${KualiForm.actionHelper.canAssignReviewersUnavailable}" 
                                                         reason="Protocol must be assigned to a committee and committee schedule.
                                                                 <p>
                                                                 Protocol submission status must be Pending or Submitted To Committee.
                                                                 <p>
                                                                 Protocol must be enroute in workflow." />
	            <kra-irb-action:genericUnavailableAction tabTitle="Assign to Agenda"
                                                         canPerformAction="${KualiForm.actionHelper.canAssignToAgendaUnavailable}" 
                                                         reason="Protocol must be assigned to a committee and committee schedule.
                                                                 <p>
                                                                 Protocol submission status must be Pending or Submitted To Committee.
                                                                 <p>
                                                                 Protocol must be enroute in workflow." />
	            <kra-irb-action:genericUnavailableAction tabTitle="Grant Exemption"
                                                         canPerformAction="${KualiForm.actionHelper.canGrantExemptionUnavailable}" 
                                                         reason="Protocol review type must be Exempt.
                                                                 <p>
                                                                 Protocol is not renewal and not amendment
                                                                 <p>
                                                                 Protocol submission status must be Submitted To Committee or In Agenda." />
	            <kra-irb-action:genericUnavailableAction tabTitle="Approve Action"
                                                         canPerformAction="${KualiForm.actionHelper.canApproveFullUnavailable}" 
                                                         reason="The last protocol action must have been Record Committee Decision with the motion to approve. " />
	            <kra-irb-action:genericUnavailableAction tabTitle="Expedited Approval"
	                                                     canPerformAction="${KualiForm.actionHelper.canApproveExpeditedUnavailable}"
	                                                     reason="Protocol review type must be Expedited.
                                                                 <p>
                                                                 Protocol submission status must be Submitted To committee or In Agenda." />
	            <kra-irb-action:genericUnavailableAction tabTitle="Response Approval"
                                                         canPerformAction="${KualiForm.actionHelper.canApproveResponseUnavailable}"
                                                         reason="Protocol review type must be Response.
                                                                 <p>
                                                                 Protocol submission status must be Submitted To Committee or In Agenda." />
	            <kra-irb-action:genericUnavailableAction tabTitle="Record Committee Decision"
				                                         canPerformAction="${KualiForm.actionHelper.canRecordCommitteeDecisionUnavailable}"
				                                         reason="Protocol submission status must be In Agenda.
				                                                 <p>
				                                                 The last protocol action was not Record Committee Decision." />
	            <kra-irb-action:genericUnavailableAction tabTitle="Defer Action"
				                                         canPerformAction="${KualiForm.actionHelper.canDeferUnavailable}" 
				                                         reason="Protocol Sumission status must be Submitted To Committee or In Agenda.
				                                                 <p>
				                                                 Protocol document must not be Final." />
	            <kra-irb-action:genericUnavailableAction tabTitle="Disapprove"
				                                         canPerformAction="${KualiForm.actionHelper.canDisapproveUnavailable}"
                                                         reason="The last protocol action must have been Record Committee Decision with the motion to disapprove. " />
	            <kra-irb-action:genericUnavailableAction tabTitle="Return for Specific Minor Revisions"
				                                         canPerformAction="${KualiForm.actionHelper.canReturnForSMRUnavailable}"
                                                         reason="The last protocol action must have been Record Committee Decision with the motion to specific minor revisions. " />
	            <kra-irb-action:genericUnavailableAction tabTitle="Return for Substantive Revisions Required."
				                                         canPerformAction="${KualiForm.actionHelper.canReturnForSRRUnavailable}"
                                                         reason="The last protocol action must have been Record Committee Decision with the motion to substantive revisions required. " />
	            <kra-irb-action:genericUnavailableAction tabTitle="Notify IRB"
				                                         canPerformAction="${KualiForm.actionHelper.canNotifyIrbUnavailable}"
				                                         reason="Protocol can not be an amendment or renewal.
				                                                 <p>
				                                                 Protocol status is In Progress, Submitted To IRB, Specific Minor Revisions Required, Defered, Amendment In Progress or Renewal In Progress." />
	            <kra-irb-action:genericUnavailableAction tabTitle="IRB Acknowledgement"
				                                         canPerformAction="${KualiForm.actionHelper.canIrbAcknowledgementUnavailable}"
				                                         reason="Submission type must be FYI.
				                                                 <p>
				                                                 Submission status must be Submitted To Committee.
				                                                 <p>
				                                                 - or -
				                                                 <p>
				                                                 Submission type must be Continuing Review/Continuation with Amendment." />
	            <kra-irb-action:genericUnavailableAction tabTitle="Create Amendment"
				                                         canPerformAction="${KualiForm.actionHelper.canCreateAmendmentUnavailable}"
				                                         reason="Protocol can not be an amendment or renewal.
				                                                 <p>
				                                                 Protocol status must be Active - Open to Enrollment, Active - Closed to Enrollment, Active - Data Analysis Only, Exempt, Closed Administratively for lack of response, Closed by Investigator, Suspended by Investigator, Suspended by IRB, or Suspended by DSMB." />
	            <kra-irb-action:genericUnavailableAction tabTitle="Modify Amendment Sections"
				                                         canPerformAction="${KualiForm.actionHelper.canModifyAmendmentSectionsUnavailable}"
				                                         reason="Protocol must be be an amendment or renewal.
				                                                 <p>
				                                                 Protocol status must be Amendment in Progress or Renewal in Progress." />
	            <kra-irb-action:genericUnavailableAction tabTitle="Create Renewal with Amendment"
				                                         canPerformAction="${KualiForm.actionHelper.canCreateRenewalUnavailable && KualiForm.actionHelper.canCreateAmendmentUnavailable}"
				                                         reason="Protocol can not be an amendment or renewal.
				                                                 <p>
				                                                 Protocol status must be Active - Open to Enrollment, Active - Closed to Enrollment, Active - Data Analysis Only, Exempt, Closed Administratively for lack of response, Closed by Investigator, Suspended by Investigator, Suspended by IRB, or Suspended by DSMB." />
	            <kra-irb-action:genericUnavailableAction tabTitle="Create Renewal without Amendment"
				                                         canPerformAction="${KualiForm.actionHelper.canCreateRenewalUnavailable}"
				                                         reason="Protocol can not be an amendment or renewal.
				                                                 <p>
				                                                 Protocol status must be Active - Open to Enrollment, Active - Closed to Enrollment, Active - Data Analysis Only, Exempt, Closed Administratively for lack of response, Closed by Investigator, Suspended by Investigator, Suspended by IRB, Suspended by DSMB, or Expired." />
	            <kra-irb-action:genericUnavailableAction tabTitle="Request To Close"
	                                                     canPerformAction="${KualiForm.actionHelper.canRequestCloseUnavailable}"
				                                         reason="Protocol can not be an amendment or renewal.
				                                                 <p>
				                                                 Protocol status must be Active - Open to Enrollment, Active - Closed to Enrollment, Active - Data Analysis Only, Exempt, Suspended by Investigator, Suspended by IRB, or Suspended by DSMB.
				                                                 <p>
				                                                 Submission Type is Request to Close, Request for Suspension, Request to Close Enrollment, Request for Termination, Request for Data Analysis Only, or Request for Re-open Enrollment." />
	            <kra-irb-action:genericUnavailableAction tabTitle="Request for Suspension"
	                                                     canPerformAction="${KualiForm.actionHelper.canRequestSuspensionUnavailable}"
	                                                     reason="Protocol can not be an amendment or renewal.
				                                                 <p>
				                                                 Protocol status must be Active - Open to Enrollment, Active - Closed to Enrollment, Active - Data Analysis Only, or Exempt.
				                                                 <p>
				                                                 Submission Type is Request to Close, Request for Suspension, Request to Close Enrollment, Request for Termination, Request for Data Analysis Only, or Request for Re-open Enrollment." />
	            <kra-irb-action:genericUnavailableAction tabTitle="Request to Close Enrollment"
	                                                     canPerformAction="${KualiForm.actionHelper.canRequestCloseEnrollmentUnavailable}"
				                                         reason="Protocol can not be an amendment or renewal.
				                                                 <p>
				                                                 Protocol status must be Active - Open to Enrollment.
				                                                 <p>
				                                                 Submission Type is Request to Close, Request for Suspension, Request to Close Enrollment, Request for Termination, Request for Data Analysis Only, or Request for Re-open Enrollment." />
	            <kra-irb-action:genericUnavailableAction tabTitle="Request to Re-open Enrollment"
	                                                     canPerformAction="${KualiForm.actionHelper.canRequestReOpenEnrollmentUnavailable}"
				                                         reason="Protocol can not be an amendment or renewal.
				                                                 <p>
				                                                 Protocol status must be Active - Closed to Enrollment.
				                                                 <p>
				                                                 Submission Type is Request to Close, Request for Suspension, Request to Close Enrollment, Request for Termination, Request for Data Analysis Only, or Request for Re-open Enrollment." />
	            <kra-irb-action:genericUnavailableAction tabTitle="Request for Data Analysis Only"
	                                                     canPerformAction="${KualiForm.actionHelper.canRequestDataAnalysisUnavailable}"
				                                         reason="Protocol can not be an amendment or renewal.
				                                                 <p>
				                                                 Protocol status must be Active - Open to Enrollment or Active - Closed to Enrollment.
				                                                 <p>
				                                                 Submission Type is Request to Close, Request for Suspension, Request to Close Enrollment, Request for Termination, Request for Data Analysis Only, or Request for Re-open Enrollment." />
	            <kra-irb-action:genericUnavailableAction tabTitle="Request for Termination"
	                                                     canPerformAction="${KualiForm.actionHelper.canRequestTerminateUnavailable}"
				                                         reason="Protocol can not be an amendment or renewal.
				                                                 <p>
				                                                 Protocol status must be Active - Open to Enrollment, Active - Closed to Enrollment, Active - Data Analysis Only, Exempt, Closed Administratively for lack of response, Closed by Investigator, Suspended by Investigator, Suspended by IRB, or Suspended by DSMB.
				                                                 <p>
				                                                 Submission Type is Request to Close, Request for Suspension, Request to Close Enrollment, Request for Termination, Request for Data Analysis Only, or Request for Re-open Enrollment." />
	            <kra-irb-action:genericUnavailableAction tabTitle="Delete Protocol, Amendment, or Renewal"
	                                                     canPerformAction="${KualiForm.actionHelper.canDeleteProtocolAmendRenewUnavailable}"
	                                                     reason="Protocol must be editable.
	                                                             <p>
	                                                             Protocol status must be Pending/In Progress, Amendment in Progress, or Renewal in Progress." />
	            <kra-irb-action:genericUnavailableAction tabTitle="Make Administrative Correction"
	                                                     canPerformAction="${KualiForm.actionHelper.canMakeAdminCorrectionUnavailable}"
	                                                     reason="Protocol status must be Pending/In Progress, Withdrawn, or Disapproved." />
	            <kra-irb-action:genericUnavailableAction tabTitle="Close Enrollment"
	                                                     canPerformAction="${KualiForm.actionHelper.canCloseEnrollmentUnavailable}"
				                                         reason="Protocol can not be an amendment or renewal.
				                                                 <p>
				                                                 Protocol status must be Active - Open to Enrollment.
				                                                 <p>
				                                                 Submission Type is Request to Close, Request for Suspension, Request to Close Enrollment, Request for Termination, Request for Data Analysis Only, or Request for Re-open Enrollment." />
	            <kra-irb-action:genericUnavailableAction tabTitle="Re-open Enrollment"
	                                                     canPerformAction="${KualiForm.actionHelper.canReopenEnrollmentUnavailable}"
				                                         reason="Protocol can not be an amendment or renewal.
				                                                 <p>
				                                                 Protocol status must be Active - Closed to Enrollment.
				                                                 <p>
				                                                 Submission Type is Request to Close, Request for Suspension, Request to Close Enrollment, Request for Termination, Request for Data Analysis Only, or Request for Re-open Enrollment." />
	            <kra-irb-action:genericUnavailableAction tabTitle="Data Analysis Only"
	                                                     canPerformAction="${KualiForm.actionHelper.canPermitDataAnalysisUnavailable}"
				                                         reason="Protocol status must be Active - Open to Enrollment or Active - Closed to Enrollment.
				                                                 <p>
				                                                 Submission type must be Request for Data Analysis Only.
				                                                 <p>
				                                                 Submission status can not be Withdrawn." />
	            <kra-irb-action:genericUnavailableAction tabTitle="Suspend"
	                                                     canPerformAction="${KualiForm.actionHelper.canSuspendUnavailable}"
				                                         reason="Protocol status must be Active - Open to Enrollment, Active - Closed to Enrollment, Active - Data Analysis Only, or Exempt.
				                                                 <p>
				                                                 Submission type is Request for Suspension.
				                                                 <p>
				                                                 Submission status can not be Withdrawn.
				                                                 <p>
				                                                 - or -
				                                                 Protocol status must be Active - Open to Enrollment, Active - Closed to Enrollment, Active - Data Analysis Only, or Exempt.
				                                                 <p>
				                                                 Protocol must have a pending submission." />
	            <kra-irb-action:genericUnavailableAction tabTitle="Suspend By DSMB"
	                                                     canPerformAction="${KualiForm.actionHelper.canSuspendByDsmbUnavailable}"
				                                         reason="Protocol status must be Active - Open to Enrollment, Active - Closed to Enrollment, Active - Data Analysis Only, or Exempt.
				                                                 <p>
				                                                 Submission type is Request for Suspension.
				                                                 <p>
				                                                 Submission status can not be Withdrawn.
				                                                 <p>
				                                                 - or -
				                                                 Protocol status must be Active - Open to Enrollment, Active - Closed to Enrollment, Active - Data Analysis Only, or Exempt.
				                                                 <p>
				                                                 Protocol must have a pending submission." />
	            <kra-irb-action:genericUnavailableAction tabTitle="Close"
	                                                     canPerformAction="${KualiForm.actionHelper.canCloseUnavailable}"
				                                         reason="Protocol status must be Pending/In Progress, Specific Minor Revisions Required, Substantive Revisions Required, Amendment in Progress, Renewal in Progress, Active - Open to Enrollment, Active - Closed to Enrollment, Active - Data Analysis Only, Exempt, Suspended by Investigator, Suspended by IRB, Withdrawn, or Suspended by DSMB.
				                                                 <p>
				                                                 Protocol must have a pending submission.
				                                                 <p>
				                                                 - or - 
				                                                 <p>
				                                                 Protocol status must be Active - Open to Enrollment, Active - Closed to Enrollment, Active - Data Analysis Only, Exempt, Suspended by Investigator,Suspended by IRB, Suspended by DSMB, Specific Minor Revisions Required, Substantive Revisions Required, or Withdrawn.
				                                                 <p>
				                                                 Submission type must be Request to Close.
				                                                 <p>
				                                                 Submission status can not be Withdrawn." />
	            <kra-irb-action:genericUnavailableAction tabTitle="Expire"
	                                                     canPerformAction="${KualiForm.actionHelper.canExpireUnavailable}"
				                                         reason="Protocol status must be Active - Open to Enrollment, Active - Closed to Enrollment, Active - Data Analysis Only, Exempt, Closed Administratively for lack of response, Closed by Investigator, Suspended by Investigator, Withdrawn, Suspended by IRB, IRB review not required, or Suspended by DSMB.
				                                                 <p>
				                                                 Protocol must have a pending submission.
				                                                 <p>
				                                                 Protocol must have pending amendments or renewals." />
	            <kra-irb-action:genericUnavailableAction tabTitle="Terminate"
	                                                     canPerformAction="${KualiForm.actionHelper.canTerminateUnavailable}"
				                                         reason="Protocol status must be Active - Open to Enrollment, Active - Closed to Enrollment, Active - Data Analysis Only, Exempt, Closed Administratively for lack of response, Closed by Investigator, Suspended by Investigator, Expired, Suspended by IRB, or Suspended by DSMB.
				                                                 <p>
				                                                 Protocol must have a pending submission.
				                                                 <p>
				                                                 - or - 
				                                                 <p>
				                                                 Protocol status must be Active - Open to Enrollment, Active - Closed to Enrollment, Active - Data Analysis Only, Exempt, Closed Administratively for lack of response, Closed by Investigator, Suspended by Investigator, Expired, Suspended by IRB, or Suspended by DSMB.
				                                                 <p>
				                                                 Protocol submission type must be Request for Termination.
				                                                 <p>
				                                                 Protocol submission status can not be Withdrawn.
				                                                 " />
	            <kra-irb-action:genericUnavailableAction tabTitle="Review Not Required"
	                                                     canPerformAction="${KualiForm.actionHelper.canReviewNotRequiredUnavailable}"
	                                                     reason="Protocol Status is Submitted to IRB.
	                                                             <p>
	                                                             Protocol review type is IRB Review not required" />
	            <kra-irb-action:genericUnavailableAction tabTitle="Undo Last Action"
	                                                     canPerformAction="${KualiForm.actionHelper.canUndoLastActionUnavailable}"
	                                                     reason="No action to undo." />
	            <kra-irb-action:genericUnavailableAction tabTitle="Manage Review Comments"
	                                                     canPerformAction="${KualiForm.actionHelper.canManageReviewCommentsUnavailable}"
	                                                     reason="Protocol Status is Submitted to IRB, Specific Minor Revisions Required, Substantive Revisions Required, Amendment in Progress, Renewal in Progress, Withdrawn, or Deferred.
	                                                             <p>
	                                                             Protocol must be enroute in workflow." />
	            <kra-irb-action:genericUnavailableAction tabTitle="Manage Notes"
	                                                     canPerformAction="${KualiForm.actionHelper.canManageNotesUnavailable}"
	                                                     reason="" />
	            <kra-irb-action:genericUnavailableAction tabTitle="Abandon"
	                                                     canPerformAction="${!KualiForm.actionHelper.canAbandon}"
				                                         reason="Protocol status must be SMR or SRR.
				                                                 <p>
				                                                 Protocol must be the initial Protocol.
				                                                 <p>
				                                                 Only PI can perform this action." />
	                                                     
		    </c:if>		   	
		   	</div>
        </kul:innerTab>
        </c:if>       
    </div>
    <c:if test="${!KualiForm.document.protocol.active and showActions}">
         	<div class="tab-container"  align="center">
				<h3> 
					<span class="subhead-left">Unavailable Actions</span>					
				</h3>  	     
			     <table cellpadding="0" cellspacing="0" summary="">
					<tr>
						<td>	
							<div align="left">   			     	
			   					<p>No actions available for a canceled protocol document. </p>			   					
							</div>
						</td>
					</tr>
				</table> 
			</div>
		</c:if>
</kul:tabTop>