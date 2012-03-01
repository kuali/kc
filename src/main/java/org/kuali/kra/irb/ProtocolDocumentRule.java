/*
 * Copyright 2005-2010 The Kuali Foundation
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

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.common.permissions.bo.PermissionsUser;
import org.kuali.kra.common.permissions.bo.PermissionsUserEditRoles;
import org.kuali.kra.common.permissions.rule.PermissionsRule;
import org.kuali.kra.common.permissions.web.bean.User;
import org.kuali.kra.common.specialreview.rule.event.SaveSpecialReviewEvent;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
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
import org.kuali.kra.irb.actions.decision.ExecuteCommitteeDecisionAbstainerRule;
import org.kuali.kra.irb.actions.decision.ExecuteCommitteeDecisionRecuserRule;
import org.kuali.kra.irb.actions.decision.ExecuteCommitteeDecisionRule;
import org.kuali.kra.irb.actions.modifysubmission.ExecuteProtocolModifySubmissionRule;
import org.kuali.kra.irb.actions.modifysubmission.ProtocolModifySubmissionBean;
import org.kuali.kra.irb.actions.modifysubmission.ProtocolModifySubmissionRule;
import org.kuali.kra.irb.actions.noreview.ExecuteProtocolReviewNotRequiredRule;
import org.kuali.kra.irb.actions.noreview.ProtocolReviewNotRequiredBean;
import org.kuali.kra.irb.actions.noreview.ProtocolReviewNotRequiredRule;
import org.kuali.kra.irb.actions.submit.ExecuteProtocolSubmitActionRule;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitAction;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitActionRule;
import org.kuali.kra.irb.noteattachment.SubmitProtocolAttachmentProtocolRuleImpl;
import org.kuali.kra.irb.permission.ProtocolPermissionsRule;
import org.kuali.kra.irb.personnel.AddProtocolAttachmentPersonnelEvent;
import org.kuali.kra.irb.personnel.AddProtocolAttachmentPersonnelRule;
import org.kuali.kra.irb.personnel.AddProtocolUnitEvent;
import org.kuali.kra.irb.personnel.AddProtocolUnitRule;
import org.kuali.kra.irb.personnel.ProtocolAttachmentPersonnelRule;
import org.kuali.kra.irb.personnel.ProtocolPersonnelAuditRule;
import org.kuali.kra.irb.personnel.ProtocolUnitRule;
import org.kuali.kra.irb.personnel.SaveProtocolPersonnelEvent;
import org.kuali.kra.irb.protocol.funding.AddProtocolFundingSourceEvent;
import org.kuali.kra.irb.protocol.funding.ProtocolFundingSourceAuditRule;
import org.kuali.kra.irb.protocol.funding.ProtocolFundingSourceRule;
import org.kuali.kra.irb.protocol.location.AddProtocolLocationEvent;
import org.kuali.kra.irb.protocol.location.AddProtocolLocationRule;
import org.kuali.kra.irb.protocol.location.ProtocolLocationRule;
import org.kuali.kra.irb.protocol.reference.AddProtocolReferenceEvent;
import org.kuali.kra.irb.protocol.reference.AddProtocolReferenceRule;
import org.kuali.kra.irb.protocol.reference.ProtocolReferenceRule;
import org.kuali.kra.irb.protocol.research.ProtocolResearchArea;
import org.kuali.kra.irb.protocol.research.ProtocolResearchAreaAuditRule;
import org.kuali.kra.irb.questionnaire.ProtocolQuestionnaireAuditRule;
import org.kuali.kra.irb.specialreview.ProtocolSpecialReview;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rule.event.KraDocumentEventBaseExtension;
import org.kuali.kra.rule.event.SaveCustomAttributeEvent;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.kra.service.UnitService;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

/**
 * Main Business Rule class for <code>{@link ProtocolDocument}</code>. Responsible for delegating rules to independent rule classes.
 *
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class ProtocolDocumentRule extends ResearchDocumentRuleBase  implements AddProtocolReferenceRule, AddProtocolLocationRule, PermissionsRule, AddProtocolAttachmentPersonnelRule, AddProtocolUnitRule, BusinessRuleInterface, ExecuteProtocolSubmitActionRule, ExecuteProtocolAssignCmtSchedRule, ExecuteProtocolAssignReviewersRule, ExecuteProtocolAdminCorrectionRule, ExecuteCommitteeDecisionRule, ExecuteCommitteeDecisionAbstainerRule, ExecuteCommitteeDecisionRecuserRule, ExecuteProtocolModifySubmissionRule, ExecuteProtocolReviewNotRequiredRule {

    private static final String PROTOCOL_LUN_FORM_ELEMENT="protocolHelper.leadUnitNumber";
    private static final String ERROR_PROPERTY_ORGANIZATION_ID = "protocolHelper.newProtocolLocation.organizationId";
    private static final String PROTOCOL_DOC_LUN_FORM_ELEMENT = "document.protocolList[0].leadUnitNumber";
    private static final String SAVE_SPECIAL_REVIEW_FIELD = "document.protocolList[0].specialReviews";
    private static final String SEPERATOR = ".";
    private static final String INACTIVE_RESEARCH_AREAS_PREFIX = "document.protocolList[0].protocolResearchAreas.inactive";
    
// TODO : move these static constant up to parent 
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(Document document) {
        boolean retval = true;
        retval &= super.processCustomRouteDocumentBusinessRules(document);
        
        return retval;
    }

    @Override
    protected boolean processCustomSaveDocumentBusinessRules(Document document) {
        if (!(document instanceof ProtocolDocument)) {
            return false;
        }

        MessageMap errorMap = GlobalVariables.getMessageMap();
        errorMap.addToErrorPath(DOCUMENT_ERROR_PATH);
        getKnsDictionaryValidationService().validateDocumentAndUpdatableReferencesRecursively(
               document, getMaxDictionaryValidationDepth(),
               VALIDATION_REQUIRED, CHOMP_LAST_LETTER_S_FROM_COLLECTION_NAME);
        errorMap.removeFromErrorPath(DOCUMENT_ERROR_PATH);

        boolean valid = true;
        ProtocolDocument protocolDocument = (ProtocolDocument) document;
        if ((protocolDocument.getDocumentHeader().getWorkflowDocument().isInitiated() || protocolDocument.getDocumentHeader().getWorkflowDocument().isSaved()) && ProtocolStatus.IN_PROGRESS.equals(protocolDocument.getProtocol().getProtocolStatusCode())
                && StringUtils.isBlank(protocolDocument.getDocumentHeader().getDocumentTemplateNumber())) {
            valid &= processProtocolResearchAreaBusinessRules((ProtocolDocument) document);
        } 
        valid &= processLeadUnitBusinessRules((ProtocolDocument) document);
        valid &= processProtocolLocationBusinessRules((ProtocolDocument) document);
        valid &= processProtocolPersonnelBusinessRules((ProtocolDocument) document);
        valid &= processProtocolCustomDataBusinessRules((ProtocolDocument) document);
        valid &= processProtocolSpecialReviewBusinessRules((ProtocolDocument) document);
        return valid;
    }

    /**
     * This method will check if all the research areas that have been added to the protocol are indeed active.
     * @param document
     * @return
     */
    public boolean processProtocolResearchAreaBusinessRules(ProtocolDocument document) {
        boolean inactiveFound = false;
        StringBuffer inactiveResearchAreaIndices = new StringBuffer();
        
        List<ProtocolResearchArea> pras = document.getProtocol().getProtocolResearchAreas();
        // iterate over all the research areas for this protocol looking for inactive research areas
        if(CollectionUtils.isNotEmpty(pras)) {
            int raIndex = 0;
            for (ProtocolResearchArea protocolResearchArea : pras) {
                if(!(protocolResearchArea.getResearchAreas().isActive())) {
                    inactiveFound = true;
                    inactiveResearchAreaIndices.append(raIndex).append(SEPERATOR);
                }
                raIndex++;
            }
        }
        // if we found any inactive research areas in the above loop, report as a single error key suffixed by the list of indices of the inactive areas
        if(inactiveFound) { 
            String protocolResearchAreaInactiveErrorPropertyKey = INACTIVE_RESEARCH_AREAS_PREFIX + SEPERATOR + inactiveResearchAreaIndices.toString();
            reportError(protocolResearchAreaInactiveErrorPropertyKey, KeyConstants.ERROR_PROTOCOL_RESEARCH_AREA_INACTIVE);
        }
        
        return !inactiveFound;
    }

    /**
     * @see org.kuali.core.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.rice.krad.document.Document)
     */
    @Override
    public boolean processRunAuditBusinessRules(Document document){
        boolean retval = true;
        
        retval &= super.processRunAuditBusinessRules(document);
        retval &= new ProtocolFundingSourceAuditRule().processRunAuditBusinessRules((ProtocolDocument) document);
        retval &= new ProtocolResearchAreaAuditRule().processRunAuditBusinessRules((ProtocolDocument) document);
        retval &= new ProtocolPersonnelAuditRule().processRunAuditBusinessRules(document);
        retval &= this.processNoteAndAttachmentAuditRules((ProtocolDocument) document);
        retval &= new ProtocolQuestionnaireAuditRule().processRunAuditBusinessRules((ProtocolDocument) document);
        return retval;
    }

    
    /**
     * Validates lead unit rules for a {@link ProtocolDocument ProtocolDocument}.
     * @param document the document
     * @return true if validation passes false if not
     * @throws NullPointerException if the document is null
     */
    public boolean processLeadUnitBusinessRules(ProtocolDocument document) {
        boolean isValid = true;
        
        if (document == null) {
            throw new NullPointerException("the document was null");
        }
        Protocol protocol = document.getProtocol();
        
        if (StringUtils.isNotBlank(protocol.getLeadUnitNumber())) {
            Unit unit = getUnitService().getUnit(protocol.getLeadUnitNumber());
            if (unit == null) {
                if (getErrorReporter().propertyHasErrorReported(PROTOCOL_DOC_LUN_FORM_ELEMENT)) {
                    getErrorReporter().removeErrors(PROTOCOL_DOC_LUN_FORM_ELEMENT);
                }
                reportError(PROTOCOL_LUN_FORM_ELEMENT, KeyConstants.ERROR_PROTOCOL_LEAD_UNIT_NUM_INVALID);
                isValid = false;
            }
        }
        
        return isValid;
    }
    
    /**
     * At least one organization must be entered.  
     * If the default value is removed, another organization must be added before user 
     * can save
     * @see org.kuali.kra.irb.rule.SaveProtocolLocationRule#processSaveProtocolLocationBusinessRules(org.kuali.kra.irb.rule.event.SaveProtocolLocationEvent)
     */
    public boolean processProtocolLocationBusinessRules(ProtocolDocument document) {
        boolean isValid = true;
        if(CollectionUtils.isEmpty(document.getProtocol().getProtocolLocations())) {
            reportError(ERROR_PROPERTY_ORGANIZATION_ID, KeyConstants.ERROR_PROTOCOL_LOCATION_SHOULD_EXIST);
            isValid = false;
        }
        return isValid;
    }
    
    private boolean processProtocolPersonnelBusinessRules(ProtocolDocument document) {
        return processRules(new SaveProtocolPersonnelEvent(Constants.EMPTY_STRING, document));
    }
    
    private boolean processProtocolCustomDataBusinessRules(ProtocolDocument document) {
        return processRules(new SaveCustomAttributeEvent(Constants.EMPTY_STRING, document));
    }
    
    private boolean processProtocolSpecialReviewBusinessRules(ProtocolDocument document) {
        List<ProtocolSpecialReview> specialReviews = document.getProtocol().getSpecialReviews();
        return processRules(new SaveSpecialReviewEvent<ProtocolSpecialReview>(SAVE_SPECIAL_REVIEW_FIELD, document, specialReviews, false));
    }

    public boolean processAddProtocolReferenceBusinessRules(AddProtocolReferenceEvent addProtocolReferenceEvent) {

        return new ProtocolReferenceRule().processAddProtocolReferenceBusinessRules(addProtocolReferenceEvent);
        
    }

    /**
     * @see org.kuali.kra.irb.protocol.location.AddProtocolLocationRule#processAddProtocolLocationBusinessRules(org.kuali.kra.irb.protocol.location.AddProtocolLocationEvent)
     */
    public boolean processAddProtocolLocationBusinessRules(AddProtocolLocationEvent addProtocolLocationEvent) {

        return new ProtocolLocationRule().processAddProtocolLocationBusinessRules(addProtocolLocationEvent);
        
    }
    
    /**
     * @see org.kuali.kra.irb.protocol.AddProtocolFundingSourceRule#processAddProtocolFundingSourceBusinessRules(org.org.kuali.kra.irb.protocol.funding.AddProtocolFundingSourceEvent)
     */
    public boolean processAddProtocolFundingSourceBusinessRules(AddProtocolFundingSourceEvent addProtocolFundingSourceEvent) {

        return new ProtocolFundingSourceRule().processAddProtocolFundingSourceBusinessRules(addProtocolFundingSourceEvent);
        
    }
    
    /**
     * @see org.kuali.kra.common.permissions.rule.PermissionsRule#processAddPermissionsUserBusinessRules(org.kuali.core.document.Document, java.util.List, org.kuali.kra.common.permissions.bo.PermissionsUser)
     */
    public boolean processAddPermissionsUserBusinessRules(Document document, List<User> users, PermissionsUser newUser) {
        return new ProtocolPermissionsRule().processAddPermissionsUserBusinessRules(document, users, newUser);
    }

    /**
     * @see org.kuali.kra.common.permissions.rule.PermissionsRule#processDeletePermissionsUserBusinessRules(org.kuali.core.document.Document, java.util.List, int)
     */
    public boolean processDeletePermissionsUserBusinessRules(Document document, List<User> users, int index) {
        return new ProtocolPermissionsRule().processDeletePermissionsUserBusinessRules(document, users, index);     
    }

    /**
     * @see org.kuali.kra.common.permissions.rule.PermissionsRule#processEditPermissionsUserRolesBusinessRules(org.kuali.core.document.Document, java.util.List, org.kuali.kra.common.permissions.bo.PermissionsUserEditRoles)
     */
    public boolean processEditPermissionsUserRolesBusinessRules(Document document, List<User> users,
            PermissionsUserEditRoles editRoles) {
        return new ProtocolPermissionsRule().processEditPermissionsUserRolesBusinessRules(document, users, editRoles);
    }

    public boolean processAddProtocolAttachmentPersonnelRules(AddProtocolAttachmentPersonnelEvent addProtocolAttachmentPersonnelEvent) {
        return new ProtocolAttachmentPersonnelRule().processAddProtocolAttachmentPersonnelRules(addProtocolAttachmentPersonnelEvent);
    }
    /**
     * @see org.kuali.kra.irb.personnel.AddProtocolUnitRule#processAddProtocolUnitBusinessRules(org.kuali.kra.irb.personnel.AddProtocolUnitEvent)
     */
    public boolean processAddProtocolUnitBusinessRules(AddProtocolUnitEvent addProtocolUnitEvent) {

        return new ProtocolUnitRule().processAddProtocolUnitBusinessRules(addProtocolUnitEvent);
        
    }

    /**
     * 
     * @see org.kuali.kra.rule.BusinessRuleInterface#processRules(org.kuali.kra.rule.event.KraDocumentEventBaseExtension)
     */
    public boolean processRules(KraDocumentEventBaseExtension event) {
        boolean retVal = false;
        retVal = event.getRule().processRules(event);
        return retVal;
    }
    
    /**
     * Executes the notes and attachment related audit rules.
     * @param document the document.
     * @return true if valid.
     */
    private boolean processNoteAndAttachmentAuditRules(ProtocolDocument document) {
        assert document != null : "the document was null";
        
        boolean valid = true;
        valid &= new SubmitProtocolAttachmentProtocolRuleImpl().processSubmitProtocolAttachmentProtocolRules(document);
     
        return valid;
    } 

    /**
     * @see org.kuali.kra.irb.actions.submit.ExecuteProtocolSubmitActionRule#processSubmitAction(org.kuali.kra.irb.ProtocolDocument, org.kuali.kra.irb.actions.submit.ProtocolSubmitActionBean)
     */
    public boolean processSubmitAction(ProtocolDocument document, ProtocolSubmitAction submitAction) {
        return new ProtocolSubmitActionRule().processSubmitAction(document, submitAction);
    }

    /**
     * @see org.kuali.kra.irb.actions.assigncmtsched.ExecuteProtocolAssignCmtSchedRule#processAssignToCommitteeSchedule(org.kuali.kra.irb.ProtocolDocument, org.kuali.kra.irb.actions.assigncmtsched.ProtocolAssignCmtSchedBean)
     */
    public boolean processAssignToCommitteeSchedule(ProtocolDocument document, ProtocolAssignCmtSchedBean actionBean) {
        return new ProtocolAssignCmtSchedRule().processAssignToCommitteeSchedule(document, actionBean);
    }

    /**
     * @see org.kuali.kra.irb.actions.assignreviewers.ExecuteProtocolAssignReviewersRule#processAssignReviewers(org.kuali.kra.irb.ProtocolDocument, org.kuali.kra.irb.actions.assignreviewers.ProtocolAssignReviewersBean)
     */
    public boolean processAssignReviewers(ProtocolDocument document, ProtocolAssignReviewersBean actionBean) {
        return new ProtocolAssignReviewersRule().processAssignReviewers(document, actionBean);
    }
    
    public boolean processAdminCorrectionRule(ProtocolDocument document, AdminCorrectionBean actionBean) {
        return new ProtocolAdminCorrectionRule().processAdminCorrectionRule(document, actionBean);
    }
    
    /**
     * @see org.kuali.kra.irb.actions.decision.ExecuteCommitteeDecisionRule#proccessCommitteeDecisionRule(org.kuali.kra.irb.ProtocolDocument, org.kuali.kra.irb.actions.decision.CommitteeDecision)
     */
    public boolean proccessCommitteeDecisionRule(ProtocolDocument document, CommitteeDecision actionBean) {
        return new CommitteeDecisionRule().proccessCommitteeDecisionRule(document, actionBean);
    }
    
    public boolean processModifySubmissionRule(ProtocolDocument document, ProtocolModifySubmissionBean actionBean) {
        return new ProtocolModifySubmissionRule().processModifySubmissionRule(document, actionBean);
    }
    
    /**
     * 
     * @see org.kuali.kra.irb.actions.decision.ExecuteCommitteeDecisionAbstainerRule#proccessCommitteeDecisionAbstainerRule(org.kuali.kra.irb.ProtocolDocument, org.kuali.kra.irb.actions.decision.CommitteeDecision)
     */
    public boolean proccessCommitteeDecisionAbstainerRule(ProtocolDocument document, CommitteeDecision actionBean) {
        return new CommitteeDecisionAbstainerRule().proccessCommitteeDecisionAbstainerRule(document, actionBean);
    }
    
    /**
     * 
     * @see org.kuali.kra.irb.actions.decision.ExecuteCommitteeDecisionRecuserRule#proccessCommitteeDecisionRecuserRule(org.kuali.kra.irb.ProtocolDocument, org.kuali.kra.irb.actions.decision.CommitteeDecision)
     */
    public boolean proccessCommitteeDecisionRecuserRule(ProtocolDocument document, CommitteeDecision actionBean) {
        return new CommitteeDecisionRecuserRule().proccessCommitteeDecisionRecuserRule(document, actionBean);
    }
    
    private UnitService getUnitService() {
        return KraServiceLocator.getService(UnitService.class);
    }
    
    public boolean processReviewNotRequiredRule(ProtocolDocument document, ProtocolReviewNotRequiredBean actionBean) {
        return new ProtocolReviewNotRequiredRule().processReviewNotRequiredRule(document, actionBean);
    }
}
