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
package org.kuali.kra.committee.rules;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.document.Document;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.rule.AddCommitteeMembershipRule;
import org.kuali.kra.committee.rule.AddCommitteeScheduleRule;
import org.kuali.kra.committee.rule.event.AddCommitteeMembershipEvent;
import org.kuali.kra.committee.rule.event.AddCommitteeScheduleEvent;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.kra.service.UnitService;

/**
 * This is the main business rule class for the Committee Document.  It
 * is responsible for customized workflow related business rule checking such
 * saving, routing, etc.  All committee specific actions, e.g. adding members,
 * this class will act as a controller and forward the rules checking to 
 * another class within this package.
 */
public class CommitteeDocumentRule extends ResearchDocumentRuleBase
                                   implements AddCommitteeScheduleRule, AddCommitteeMembershipRule {
    
    static private final boolean VALIDATION_REQUIRED = true;
    
    // KRACOEUS-641: Changed CHOMP_LAST_LETTER_S_FROM_COLLECTION_NAME to false to prevent duplicate error messages
    static private final boolean CHOMP_LAST_LETTER_S_FROM_COLLECTION_NAME = false;
    
    /**
     * @see org.kuali.core.rules.DocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.core.document.Document)
     */
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(Document document) {
        boolean retval = true;
        
        retval &= super.processCustomRouteDocumentBusinessRules(document);
        
        return retval;
    }

    /**
     * @see org.kuali.core.rules.DocumentRuleBase#processCustomSaveDocumentBusinessRules(org.kuali.core.document.Document)
     */
    @Override
    protected boolean processCustomSaveDocumentBusinessRules(Document document) {
       
        boolean valid = true;
        
        GlobalVariables.getErrorMap().addToErrorPath("document");
        
        /* 
         * The Kuali Core business rules don't check to see if the required fields are
         * set in order to save the document.  Thus, that check must be performed here.
         * Note that the method validateDocumentAndUpdatableReferencesRecursively() does
         * not return whether validation failed or succeeded.  Therefore, we check the
         * the global error map.  If it isn't empty, we assume that the errors were put
         * there by this method.
         */
        getDictionaryValidationService().validateDocumentAndUpdatableReferencesRecursively(document, getMaxDictionaryValidationDepth(), VALIDATION_REQUIRED, CHOMP_LAST_LETTER_S_FROM_COLLECTION_NAME);
        valid &= GlobalVariables.getErrorMap().isEmpty();
        
        valid &= validateUniqueCommitteeId((CommitteeDocument) document);
        valid &= validateHomeUnit((CommitteeDocument) document);
        
        GlobalVariables.getErrorMap().removeFromErrorPath("document");
        
        return valid;
    }
    
    /**
     * Verify that we are not saving a committee with a duplicate Committee ID.
     * In other words, each committee must have a unique Committee ID.
     * @param document Committee Document
     * @return true if valid; otherwise false
     */
    private boolean validateUniqueCommitteeId(CommitteeDocument document) {
        
        boolean valid = true;
        
        CommitteeService committeeService = KraServiceLocator.getService(CommitteeService.class);
        Committee committee = committeeService.getCommitteeById(document.getCommittee().getCommitteeId());
        
        // The committee is null if we are creating a committee with a new committee ID or
        // we are changing the committee ID.
        
        if (committee != null) {
            
            // There is no conflict if we are only modifying the same committee.
            
            if (!committee.getId().equals(document.getCommittee().getId())) {
                
                // We can have a conflict if we find a different committee in the database
                // and it has the same ID as the committee we are trying to save.
                
                if (StringUtils.equals(committee.getCommitteeId(), document.getCommittee().getCommitteeId())) {
                    valid = false;
                    reportError(Constants.COMMITTEE_PROPERTY_KEY + "List[0].committeeId", 
                                KeyConstants.ERROR_COMMITTEE_DUPLICATE_ID);
                }
            }
        }
        return valid;
    }
    
    /**
     * Verify that the unit number if is valid.  We can ignore a blank
     * home unit number since it is a required field and that business logic
     * will flag a blank value as invalid.
     * @param document the Committee document
     * @return true if valid; otherwise false
     */
    private boolean validateHomeUnit(CommitteeDocument document) {
        
        boolean valid = true;
        
        String homeUnitNumber = document.getCommittee().getHomeUnitNumber();
        if (!StringUtils.isBlank(homeUnitNumber)) {
            UnitService unitService = KraServiceLocator.getService(UnitService.class);
            Unit homeUnit = unitService.getUnit(homeUnitNumber);
            if (homeUnit == null) {
                valid = false;
                reportError(Constants.COMMITTEE_PROPERTY_KEY + "List[0].homeUnitNumber", 
                            KeyConstants.ERROR_INVALID_UNIT, homeUnitNumber);
            }
        }
        
        return valid;
    }
    
    /**
     * @see org.kuali.core.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.core.document.Document)
     */
    public boolean processRunAuditBusinessRules(Document document) {
        boolean retval = true;
        
        retval &= super.processRunAuditBusinessRules(document);
        
        return retval;
    }
    
    /**
     * @see org.kuali.kra.committee.rule.AddCommitteeMembershipRule#processAddCommitteeMembershipRules(org.kuali.kra.committee.rule.event.AddCommitteeMembershipEvent)
     */
    public boolean processAddCommitteeMembershipRules(AddCommitteeMembershipEvent addCommitteeMembershipEvent) {
        return new CommitteeMembershipRule().processAddCommitteeMembershipBusinessRules(addCommitteeMembershipEvent);
    }
    
    /**
     * @see org.kuali.kra.committee.rule.AddCommitteeScheduleRule#processAddCommitteeScheduleRuleBusinessRules(org.kuali.kra.committee.rule.event.AddCommitteeScheduleEvent)
     */
    public boolean processAddCommitteeScheduleRuleBusinessRules(AddCommitteeScheduleEvent addCommitteeScheduleEvent) {
        return new CommitteeScheduleRule().processAddCommitteeScheduleRuleBusinessRules(addCommitteeScheduleEvent);
    }
}
