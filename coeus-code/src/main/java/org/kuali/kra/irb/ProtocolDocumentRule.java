/*
 * Copyright 2005-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.irb;

import org.kuali.coeus.common.permissions.impl.bo.PermissionsUser;
import org.kuali.coeus.common.permissions.impl.bo.PermissionsUserEditRoles;
import org.kuali.coeus.common.permissions.impl.rule.PermissionsRule;
import org.kuali.coeus.common.permissions.impl.web.bean.User;
import org.kuali.coeus.common.specialreview.impl.rule.event.SaveSpecialReviewEvent;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.assigncmtsched.ExecuteProtocolAssignCmtSchedRule;
import org.kuali.kra.irb.actions.assigncmtsched.ProtocolAssignCmtSchedBean;
import org.kuali.kra.irb.actions.assigncmtsched.ProtocolAssignCmtSchedRule;
import org.kuali.kra.irb.actions.assignreviewers.ExecuteProtocolAssignReviewersRule;
import org.kuali.kra.irb.actions.assignreviewers.ProtocolAssignReviewersBean;
import org.kuali.kra.irb.actions.assignreviewers.ProtocolAssignReviewersRule;
import org.kuali.kra.irb.actions.correction.AdminCorrectionBean;
import org.kuali.kra.irb.actions.correction.ExecuteProtocolAdminCorrectionRule;
import org.kuali.kra.irb.actions.correction.ProtocolAdminCorrectionRule;
import org.kuali.kra.irb.actions.decision.CommitteeDecision;
import org.kuali.kra.irb.actions.decision.CommitteeDecisionAbstainerRule;
import org.kuali.kra.irb.actions.decision.CommitteeDecisionRecuserRule;
import org.kuali.kra.irb.actions.decision.CommitteeDecisionRule;
import org.kuali.kra.irb.actions.modifysubmission.ExecuteProtocolModifySubmissionRule;
import org.kuali.kra.irb.actions.modifysubmission.ProtocolModifySubmissionBean;
import org.kuali.kra.irb.actions.modifysubmission.ProtocolModifySubmissionRule;
import org.kuali.kra.irb.actions.noreview.ExecuteProtocolReviewNotRequiredRule;
import org.kuali.kra.irb.actions.noreview.ProtocolReviewNotRequiredBean;
import org.kuali.kra.irb.actions.noreview.ProtocolReviewNotRequiredRule;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitActionRule;
import org.kuali.kra.irb.noteattachment.SubmitProtocolAttachmentProtocolRuleImpl;
import org.kuali.kra.irb.permission.ProtocolPermissionsRule;
import org.kuali.kra.irb.personnel.ProtocolAttachmentPersonnelRule;
import org.kuali.kra.irb.personnel.ProtocolPersonnelAuditRule;
import org.kuali.kra.irb.personnel.ProtocolUnitRule;
import org.kuali.kra.irb.personnel.SaveProtocolPersonnelEvent;
import org.kuali.kra.irb.protocol.funding.ProtocolFundingSourceAuditRule;
import org.kuali.kra.irb.protocol.funding.ProtocolFundingSourceRule;
import org.kuali.kra.irb.protocol.location.ProtocolLocationRule;
import org.kuali.kra.irb.protocol.reference.ProtocolReferenceRule;
import org.kuali.kra.irb.protocol.research.ProtocolResearchAreaAuditRule;
import org.kuali.kra.irb.questionnaire.ProtocolQuestionnaireAuditRule;
import org.kuali.kra.irb.specialreview.ProtocolSpecialReview;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.ProtocolDocumentRuleBase;
import org.kuali.kra.protocol.actions.decision.CommitteeDecisionRuleBase;
import org.kuali.kra.protocol.noteattachment.SubmitProtocolAttachmentProtocolRuleImplBase;
import org.kuali.kra.protocol.personnel.SaveProtocolPersonnelEventBase;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceAuditRuleBase;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceRuleBase;
import org.kuali.kra.protocol.specialreview.ProtocolSpecialReviewBase;
import org.kuali.rice.krad.document.Document;

import java.util.List;

/**
 * Main Business Rule class for <code>{@link ProtocolDocument}</code>. Responsible for delegating rules to independent rule classes.
 *
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class ProtocolDocumentRule extends ProtocolDocumentRuleBase<CommitteeDecision>  implements 
                                                                               ExecuteProtocolAssignCmtSchedRule, 
                                                                               ExecuteProtocolAssignReviewersRule, 
                                                                               ExecuteProtocolAdminCorrectionRule,
                                                                               ExecuteProtocolModifySubmissionRule, 
                                                                               ExecuteProtocolReviewNotRequiredRule,
                                                                               PermissionsRule {

    
    
    
    
    private static final String SAVE_SPECIAL_REVIEW_FIELD = "document.protocolList[0].specialReviews";

    @Override
    protected boolean processCustomSaveDocumentBusinessRules(Document document) {
        boolean valid = super.processCustomSaveDocumentBusinessRules(document);
        valid &= processProtocolSpecialReviewBusinessRules((ProtocolDocument) document);
        return valid;
    }
    
    @Override
    public boolean processRunAuditBusinessRules(Document document){
        boolean retval = super.processRunAuditBusinessRules(document);
        retval &= new ProtocolQuestionnaireAuditRule().processRunAuditBusinessRules((ProtocolDocument) document);
        return retval;
    }
        
    private boolean processProtocolSpecialReviewBusinessRules(ProtocolDocument document) {
        List<ProtocolSpecialReviewBase> specialReviews = document.getProtocol().getSpecialReviews();
        return processRules(new SaveSpecialReviewEvent<ProtocolSpecialReview>(SAVE_SPECIAL_REVIEW_FIELD, document, (List)specialReviews, false, false));
    }

    @Override
    public boolean processAddPermissionsUserBusinessRules(Document document, List<User> users, PermissionsUser newUser) {
        return new ProtocolPermissionsRule().processAddPermissionsUserBusinessRules(document, users, newUser);
    }

    @Override
    public boolean processDeletePermissionsUserBusinessRules(Document document, List<User> users, int index) {
        return new ProtocolPermissionsRule().processDeletePermissionsUserBusinessRules(document, users, index);     
    }

    @Override
    public boolean processEditPermissionsUserRolesBusinessRules(Document document, List<User> users,
            PermissionsUserEditRoles editRoles) {
        return new ProtocolPermissionsRule().processEditPermissionsUserRolesBusinessRules(document, users, editRoles);
    }

    @Override
    public boolean processAssignToCommitteeSchedule(ProtocolDocument document, ProtocolAssignCmtSchedBean actionBean) {
        return new ProtocolAssignCmtSchedRule().processAssignToCommitteeSchedule(document, actionBean);
    }

    @Override
    public boolean processAssignReviewers(ProtocolDocument document, ProtocolAssignReviewersBean actionBean) {
        return new ProtocolAssignReviewersRule().processAssignReviewers(document, actionBean);
    }
    
    public boolean processAdminCorrectionRule(ProtocolDocument document, AdminCorrectionBean actionBean) {
        return new ProtocolAdminCorrectionRule().processAdminCorrectionRule(document, actionBean);
    }
    
    public boolean processModifySubmissionRule(ProtocolDocument document, ProtocolModifySubmissionBean actionBean) {
        return new ProtocolModifySubmissionRule().processModifySubmissionRule(document, actionBean);
    }
    
    public boolean processReviewNotRequiredRule(ProtocolDocument document, ProtocolReviewNotRequiredBean actionBean) {
        return new ProtocolReviewNotRequiredRule().processReviewNotRequiredRule(document, actionBean);
    }

    @Override
    protected String getInProgressProtocolStatusCodeHook() {
        return ProtocolStatus.IN_PROGRESS;
    }

    @Override
    protected ProtocolFundingSourceAuditRuleBase getNewProtocolFundingSourceAuditRuleInstanceHook() {
        return new ProtocolFundingSourceAuditRule();
    }

    @Override
    protected ProtocolPersonnelAuditRule getNewProtocolPersonnelAuditRuleInstanceHook() {
        return new ProtocolPersonnelAuditRule();
    }

    @Override
    protected ProtocolResearchAreaAuditRule getNewProtocolResearchAreaAuditRuleInstanceHook() {
        return new ProtocolResearchAreaAuditRule();
    }

    @Override
    protected SaveProtocolPersonnelEventBase getSaveProtocolPersonnelEventHook(ProtocolDocumentBase document) {
        return new SaveProtocolPersonnelEvent(Constants.EMPTY_STRING, (ProtocolDocument) document);
    }

    @Override
    protected ProtocolReferenceRule getNewProtocolReferenceRuleInstanceHook() {
        return new ProtocolReferenceRule();
    }

    @Override
    protected ProtocolLocationRule getNewProtocolLocationRuleInstanceHook() {
        return new ProtocolLocationRule();
    }

    @Override
    protected ProtocolFundingSourceRuleBase getNewProtocolFundingSourceRuleInstanceHook() {
        return new ProtocolFundingSourceRule();
    }

    @Override
    public ProtocolAttachmentPersonnelRule getProtocolAttachmentPersonnelRuleInstanceHook() {
        return new ProtocolAttachmentPersonnelRule();
    }

    @Override
    protected ProtocolUnitRule getNewProtocolUnitRuleInstanceHook() {
        return new ProtocolUnitRule();
    }

    @Override
    protected SubmitProtocolAttachmentProtocolRuleImplBase newSubmitProtocolAttachmentProtocolRuleImplInstanceHook() {
        return new SubmitProtocolAttachmentProtocolRuleImpl();
    }

    @Override
    protected ProtocolSubmitActionRule newProtocolSubmitActionRuleInstanceHook() {
        return new ProtocolSubmitActionRule();
    }

    @Override
    protected CommitteeDecisionRuleBase<CommitteeDecision> newCommitteeDecisionRuleInstanceHook() {
        return new CommitteeDecisionRule();
    }

    @Override
    protected org.kuali.kra.protocol.actions.decision.ExecuteCommitteeDecisionAbstainerRule<CommitteeDecision> newCommitteeDecisionAbstainerRuleInstanceHook() {
        return new CommitteeDecisionAbstainerRule();
    }

    @Override
    protected org.kuali.kra.protocol.actions.decision.ExecuteCommitteeDecisionRecuserRule<CommitteeDecision> newCommitteeDecisionRecuserRuleInstanceHook() {
        return new CommitteeDecisionRecuserRule();
    }
}
