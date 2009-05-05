/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.rules;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.common.permissions.bo.PermissionsUser;
import org.kuali.kra.common.permissions.bo.PermissionsUserEditRoles;
import org.kuali.kra.common.permissions.rule.PermissionsRule;
import org.kuali.kra.common.permissions.web.bean.User;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.irb.bo.ProtocolParticipant;
import org.kuali.kra.irb.bo.ProtocolSpecialReview;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.irb.noteattachment.SaveProtocolAttachmentPersonnelRuleImpl;
import org.kuali.kra.irb.noteattachment.SaveProtocolAttachmentProtocolRuleImpl;
import org.kuali.kra.irb.noteattachment.SubmitProtocolAttachmentProtocolRuleImpl;
import org.kuali.kra.irb.permission.ProtocolPermissionsRule;
import org.kuali.kra.irb.personnel.AddProtocolPersonnelEvent;
import org.kuali.kra.irb.personnel.AddProtocolPersonnelRule;
import org.kuali.kra.irb.personnel.ProtocolPersonnelAuditRule;
import org.kuali.kra.irb.personnel.ProtocolPersonnelRule;
import org.kuali.kra.irb.personnel.SaveProtocolPersonnelEvent;
import org.kuali.kra.irb.personnel.SaveProtocolPersonnelRule;
import org.kuali.kra.irb.rule.AddProtocolFundingSourceRule;
import org.kuali.kra.irb.rule.AddProtocolLocationRule;
import org.kuali.kra.irb.rule.AddProtocolParticipantRule;
import org.kuali.kra.irb.rule.AddProtocolReferenceRule;
import org.kuali.kra.irb.rule.AddProtocolUnitRule;
import org.kuali.kra.irb.rule.ExecuteProtocolActionRule;
import org.kuali.kra.irb.rule.event.AddProtocolFundingSourceEvent;
import org.kuali.kra.irb.rule.event.AddProtocolLocationEvent;
import org.kuali.kra.irb.rule.event.AddProtocolParticipantEvent;
import org.kuali.kra.irb.rule.event.AddProtocolReferenceEvent;
import org.kuali.kra.irb.rule.event.AddProtocolUnitEvent;
import org.kuali.kra.irb.web.struts.bean.ProtocolSubmitAction;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rule.CustomAttributeRule;
import org.kuali.kra.rule.SpecialReviewRule;
import org.kuali.kra.rule.event.AddSpecialReviewEvent;
import org.kuali.kra.rule.event.KraDocumentEventBaseExtension;
import org.kuali.kra.rule.event.SaveCustomAttributeEvent;
import org.kuali.kra.rules.KraCustomAttributeRule;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.kra.rules.SpecialReviewRulesImpl;
import org.kuali.kra.service.UnitService;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * Main Business Rule class for <code>{@link ProtocolDocument}</code>. Responsible for delegating rules to independent rule classes.
 *
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class ProtocolDocumentRule extends ResearchDocumentRuleBase  implements AddProtocolReferenceRule, AddProtocolParticipantRule, AddProtocolLocationRule, AddProtocolPersonnelRule, SaveProtocolPersonnelRule, AddProtocolFundingSourceRule, PermissionsRule, AddProtocolUnitRule, CustomAttributeRule, SpecialReviewRule<ProtocolSpecialReview>, BusinessRuleInterface, ExecuteProtocolActionRule {
    private static final String PROTOCOL_PIID_FORM_ELEMENT="protocolHelper.personId";
    private static final String PROTOCOL_LUN_FORM_ELEMENT="protocolHelper.leadUnitNumber";
    private static final String ERROR_PROPERTY_ORGANIZATION_ID = "protocolHelper.newProtocolLocation.organizationId";
    private static final String SPECIAL_REVIEW_ERROR_PATH = "specialReviewHelper.newSpecialReview";
    private static final String SPECIAL_REVIEW_ERROR_PATH2 = "document.protocolList[0].specialReview";

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

        ErrorMap errorMap = GlobalVariables.getErrorMap();
        errorMap.addToErrorPath(DOCUMENT_ERROR_PATH);
        getDictionaryValidationService().validateDocumentAndUpdatableReferencesRecursively(
                document, getMaxDictionaryValidationDepth(),
                VALIDATION_REQUIRED, CHOMP_LAST_LETTER_S_FROM_COLLECTION_NAME);
        errorMap.removeFromErrorPath(DOCUMENT_ERROR_PATH);

        boolean valid = true;
        valid &= processRequiredFieldsBusinessRules((ProtocolDocument) document);
        valid &= processProtocolLocationBusinessRules((ProtocolDocument) document);
        valid &= processProtocolParticipantBusinessRules((ProtocolDocument) document);
        valid &= processNoteAndAttachmentSaveRules((ProtocolDocument) document);
        valid &= processSpecialReviewSaveRules((ProtocolDocument) document);
        
        return valid;
    }


    private boolean processSpecialReviewSaveRules(ProtocolDocument document) {
        SpecialReviewRulesImpl specialReviewRules = new SpecialReviewRulesImpl(SPECIAL_REVIEW_ERROR_PATH2);
        return specialReviewRules.processSpecialReviewSaveRules(document.getProtocol().getSpecialReviews());
    }

    /**
     * @see org.kuali.core.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.rice.kns.document.Document)
     */
    @Override
    public boolean processRunAuditBusinessRules(Document document){
        boolean retval = true;
        
        retval &= super.processRunAuditBusinessRules(document);
        retval &= new ProtocolPersonnelAuditRule().processRunAuditBusinessRules(document);
        retval &= this.processNoteAndAttachmentAuditRules((ProtocolDocument) document);
        return retval;
    }

    
    /**
     * Executes validation rule for the required fields on a {@link ProtocolDocument ProtocolDocument}.
     * @param document the document
     * @return true if validation passes false if not
     * @throws NullPointerException if the document is null
     */
    public boolean processRequiredFieldsBusinessRules(ProtocolDocument document) {

        if (document == null) {
            throw new NullPointerException("the document was null");
        }

        return isValidUnit(document.getProtocol().getLeadUnitNumber()) && isValidUnitProperties(document.getProtocol());
        
    }

    /*
     * Checks if the unit properties contained in a {@link Protocol Protocol} are valid.
     * 
     * @param protocol the {@link Protocol Protocol} @return true is valid false if not.
     */
    private boolean isValidUnitProperties(Protocol protocol) {

        boolean isValid = true;
         if (protocol.getLeadUnitForValidation() == null ||  protocol.getLeadUnit() == null) {
               isValid = false;
               reportError(PROTOCOL_LUN_FORM_ELEMENT, KeyConstants.ERROR_PROTOCOL_LEAD_UNIT_NAME_NOT_FOUND);
         }
         return isValid;

    }


    /*
     * Checks if a {@link Unit Unit} is available for a given unit number.
     *
     * <p>
     * This method will return false if passed in a blank unitNUmber
     * </p>
     *
     * @param unitNumber the unitNumber
     * @return true if unit exists in Unit table false if not.
     */
    private boolean isValidUnit(String unitNumber) {
        boolean isValid = true;
        if (StringUtils.isBlank(unitNumber)) {
            isValid = false;
        } else {
            UnitService unitService = KraServiceLocator.getService(UnitService.class);
            Unit unit = unitService.getUnit(unitNumber);
            isValid = unit != null;
        }
        if (!isValid) {
            reportError(PROTOCOL_LUN_FORM_ELEMENT, KeyConstants.ERROR_PROTOCOL_LEAD_UNIT_NUM_INVALID);
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

    /**
     * At least one organization must be entered.  
     * If the default value is removed, another organization must be added before user 
     * can save
     * @param document
     * @return <code>true</code> if document passed the protocol participant validation, 
     *         <code>false</code> otherwise.
     */
    public boolean processProtocolParticipantBusinessRules(ProtocolDocument document) {
        final String ERROR_PROPERTY_PREFIX = "document.protocolList[0].protocolParticipants[";
        final String ERROR_PROPERTY_PARTICIPANT_COUNT = "].participantCount";
        boolean isValid = true;
        List<ProtocolParticipant> protocolParticipants = document.getProtocol().getProtocolParticipants();
        for(ProtocolParticipant protocolParticipant: protocolParticipants) {
            if ((protocolParticipant.getParticipantCount() != null) && (protocolParticipant.getParticipantCount() == 0)) {
                int index = protocolParticipants.indexOf(protocolParticipant);
                String errorPath = ERROR_PROPERTY_PREFIX + index + ERROR_PROPERTY_PARTICIPANT_COUNT;
                reportError(errorPath, KeyConstants.ERROR_PROTOCOL_PARTICIPANT_COUNT_INVALID);
                isValid = false;
            }
        }
        return isValid;
    }

    /**
     * @see org.kuali.kra.irb.rule.AddProtocolParticipantRule#processAddParticipantBusinessRules(org.kuali.kra.irb.document.ProtocolDocument, org.kuali.kra.irb.bo.ProtocolParticipant)
     */
    public boolean processAddProtocolParticipantBusinessRules(AddProtocolParticipantEvent addProtocolParticipantEvent) {
        return new ProtocolParticipantRule().processAddProtocolParticipantBusinessRules(addProtocolParticipantEvent);
    }

    public boolean processAddProtocolReferenceBusinessRules(AddProtocolReferenceEvent addProtocolReferenceEvent) {

        return new ProtocolReferenceRule().processAddProtocolReferenceBusinessRules(addProtocolReferenceEvent);
        
    }

    /**
     * @see org.kuali.kra.irb.rule.AddProtocolLocationRule#processAddProtocolLocationBusinessRules(org.kuali.kra.irb.rule.event.AddProtocolLocationEvent)
     */
    public boolean processAddProtocolLocationBusinessRules(AddProtocolLocationEvent addProtocolLocationEvent) {

        return new ProtocolLocationRule().processAddProtocolLocationBusinessRules(addProtocolLocationEvent);
        
    }
    
    /**
     * @see org.kuali.kra.irb.personnel.AddProtocolPersonnelRule#processAddProtocolPersonnelBusinessRules(org.kuali.kra.irb.personnel.AddProtocolPersonnelEvent)
     */
    public boolean processAddProtocolPersonnelBusinessRules(AddProtocolPersonnelEvent addProtocolPersonnelEvent) {

        return new ProtocolPersonnelRule().processAddProtocolPersonnelBusinessRules(addProtocolPersonnelEvent);
        
    }

    /**
     * @see org.kuali.kra.irb.personnel.SaveProtocolPersonnelRule#processSaveProtocolPersonnelBusinessRules(org.kuali.kra.irb.personnel.SaveProtocolPersonnelEvent)
     */
    public boolean processSaveProtocolPersonnelBusinessRules(SaveProtocolPersonnelEvent saveProtocolPersonnelEvent) {

        return new ProtocolPersonnelRule().processSaveProtocolPersonnelBusinessRules(saveProtocolPersonnelEvent);
        
    }
    
    /**
     * @see org.kuali.kra.irb.rule.AddProtocolFundingSourceRule#processAddProtocolFundingSourceBusinessRules(org.kuali.kra.irb.rule.event.AddProtocolFundingSourceEvent)
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

    /**
     * @see org.kuali.kra.irb.rule.AddProtocolUnitRule#processAddProtocolUnitBusinessRules(org.kuali.kra.irb.rule.event.AddProtocolUnitEvent)
     */
    public boolean processAddProtocolUnitBusinessRules(AddProtocolUnitEvent addProtocolUnitEvent) {

        return new ProtocolUnitRule().processAddProtocolUnitBusinessRules(addProtocolUnitEvent);
        
    }

    /**
     * @see org.kuali.kra.rule.CustomAttributeRule#processCustomAttributeRules(org.kuali.kra.rule.event.SaveCustomAttributeEvent)
     */
    public boolean processCustomAttributeRules(SaveCustomAttributeEvent saveCustomAttributeEvent) {
        return new KraCustomAttributeRule().processCustomAttributeRules(saveCustomAttributeEvent);
    }

    /**
     * @see org.kuali.kra.rule.SpecialReviewRule#processAddSpecialReviewEvent(org.kuali.kra.rule.event.AddSpecialReviewEvent)
     */
    public boolean processAddSpecialReviewEvent(AddSpecialReviewEvent<ProtocolSpecialReview> addSpecialReviewEvent) {
        SpecialReviewRulesImpl ruleImpl = new SpecialReviewRulesImpl(SPECIAL_REVIEW_ERROR_PATH);
        return ruleImpl.processAddSpecialReviewEvent(addSpecialReviewEvent);
    }
    
    /**
     * Executes the notes and attachment related save rules.
     * @param document the document.
     * @return true if valid.
     */
    private boolean processNoteAndAttachmentSaveRules(ProtocolDocument document) {
        assert document != null : "the document was null";
        
        boolean valid = true;
        valid &= new SaveProtocolAttachmentPersonnelRuleImpl().processSaveProtocolAttachmentPersonnelRules(document);
        valid &= new SaveProtocolAttachmentProtocolRuleImpl().processSaveProtocolAttachmentProtocolRules(document);
     
        return valid;
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
     * @see org.kuali.kra.irb.rule.ExecuteProtocolActionRule#processSubmitAction(org.kuali.kra.irb.document.ProtocolDocument, org.kuali.kra.irb.web.struts.bean.ProtocolSubmitAction)
     */
    public boolean processSubmitAction(ProtocolDocument document, ProtocolSubmitAction submitAction) {
        return new ProtocolActionRule().processSubmitAction(document, submitAction);
    }
}
