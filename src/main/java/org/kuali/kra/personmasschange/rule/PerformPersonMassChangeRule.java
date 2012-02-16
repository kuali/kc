/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.personmasschange.rule;

import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.personmasschange.bo.AwardPersonMassChange;
import org.kuali.kra.personmasschange.bo.InstitutionalProposalPersonMassChange;
import org.kuali.kra.personmasschange.bo.NegotiationPersonMassChange;
import org.kuali.kra.personmasschange.bo.PersonMassChange;
import org.kuali.kra.personmasschange.bo.ProposalDevelopmentPersonMassChange;
import org.kuali.kra.personmasschange.bo.SubawardPersonMassChange;
import org.kuali.kra.personmasschange.bo.UnitAdministratorPersonMassChange;
import org.kuali.kra.personmasschange.rule.event.PerformPersonMassChangeEvent;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

public class PerformPersonMassChangeRule extends ResearchDocumentRuleBase implements BusinessRuleInterface<PerformPersonMassChangeEvent> {
    
    private static final String REPLACEE_FULL_NAME_FIELD = "document.personMassChange.replaceeFullName";
    private static final String REPLACER_FULL_NAME_FIELD = "document.personMassChange.replacerFullName";
    
    private static final String AWARD_FIELD = "document.personMassChange.awardPersonMassChange.";
    private static final String INSTITUTIONAL_PROPOSAL_FIELD = "document.personMassChange.institutionalProposalPersonMassChange.";
    private static final String PROPOSAL_DEVELOPMENT_FIELD = "document.personMassChange.proposalDevelopmentPersonMassChange.";
    private static final String SUBAWARD_FIELD = "document.personMassChange.subawardPersonMassChange.";
    private static final String NEGOTIATION_FIELD = "document.personMassChange.negotiationPersonMassChange.";
    private static final String UNIT_ADMINISTRATOR_FIELD = "document.personMassChange.unitAdministratorPersonMassChange.";

    private static final String CONTACT_PERSON_ID = "contactPerson";
    private static final String UNIT_ADMINISTRATOR_ID = "unitAdministrator";
    private static final String MAILING_INFORMATION_ID = "mailingInformation";
    private static final String IP_REVIEWER_ID = "ipReviewer";
    private static final String REQUISITIONER_ID = "requisitioner";
    private static final String NEGOTIATOR_ID = "negotiator";
    private static final String ADMINISTRATIVE_OFFICER_ID = "administrativeOfficer";
    private static final String OSP_ADMINISTRATOR_ID = "ospAdministrator";
    private static final String UNIT_HEAD_ID = "unitHead";
    private static final String DEAN_VP_ID = "deanVP";
    private static final String OTHER_INDIVIDUAL_TO_NOTIFY_ID = "otherIndividualToNotify";
    private static final String ADMINISTRATOR_ID = "administrator";
    
    private static final String AWARD_CONTACT_PERSON_FIELD = AWARD_FIELD + CONTACT_PERSON_ID;
    private static final String AWARD_UNIT_ADMINISTRATOR_FIELD = AWARD_FIELD + UNIT_ADMINISTRATOR_ID;
    private static final String INSTITUTIONAL_PROPOSAL_UNIT_ADMINISTRATOR_FIELD = INSTITUTIONAL_PROPOSAL_FIELD + UNIT_ADMINISTRATOR_ID;
    private static final String INSTITUTIONAL_PROPOSAL_MAILING_INFORMATION_FIELD = INSTITUTIONAL_PROPOSAL_FIELD + MAILING_INFORMATION_ID;
    private static final String INSTITUTIONAL_PROPOSAL_IP_REVIEWER_FIELD = INSTITUTIONAL_PROPOSAL_FIELD + IP_REVIEWER_ID;
    private static final String PROPOSAL_DEVELOPMENT_MAILING_INFORMATION_FIELD = PROPOSAL_DEVELOPMENT_FIELD + MAILING_INFORMATION_ID;
    private static final String SUBAWARD_CONTACT_PERSON_FIELD = SUBAWARD_FIELD + CONTACT_PERSON_ID;
    private static final String SUBAWARD_REQUISITIONER_FIELD = SUBAWARD_FIELD + REQUISITIONER_ID;
    private static final String NEGOTIATION_NEGOTIATOR_FIELD = NEGOTIATION_FIELD + NEGOTIATOR_ID;
    private static final String UNIT_ADMINISTRATOR_ADMINISTRATIVE_OFFICER_FIELD = UNIT_ADMINISTRATOR_FIELD + ADMINISTRATIVE_OFFICER_ID;
    private static final String UNIT_ADMINISTRATOR_OSP_ADMINISTRATOR_FIELD = UNIT_ADMINISTRATOR_FIELD + OSP_ADMINISTRATOR_ID;
    private static final String UNIT_ADMINISTRATOR_UNIT_HEAD_FIELD = UNIT_ADMINISTRATOR_FIELD + UNIT_HEAD_ID;
    private static final String UNIT_ADMINISTRATOR_DEAN_VP_FIELD = UNIT_ADMINISTRATOR_FIELD + DEAN_VP_ID;
    private static final String UNIT_ADMINISTRATOR_OTHER_INDIVIDUAL_TO_NOTIFY_FIELD = UNIT_ADMINISTRATOR_FIELD + OTHER_INDIVIDUAL_TO_NOTIFY_ID;
    private static final String UNIT_ADMINISTRATOR_ADMINISTRATOR_FIELD = UNIT_ADMINISTRATOR_FIELD + ADMINISTRATOR_ID;
    
    private static final String EMPLOYEE = "Employee";
    private static final String NON_EMPLOYEE = "Non-Employee";
    
    private static final String AWARD_CONTACT_PERSON = "Award Contact Person";
    private static final String AWARD_UNIT_ADMINISTRATOR = "Award Unit Administrator";
    private static final String INSTITUTIONAL_PROPOSAL_UNIT_ADMINISTRATOR = "Institutional Proposal Unit Administrator";
    private static final String INSTITUTIONAL_PROPOSAL_MAILING_INFORMATION = "Institutional Proposal Mailing Information";
    private static final String INSTITUTIONAL_PROPOSAL_IP_REVIEWER = "Institutional Proposal IP Reviewer";
    private static final String PROPOSAL_DEVELOPMENT_MAILING_INFORMATION = "Proposal Development Mailing Information";
    private static final String SUBAWARD_CONTACT_PERSON = "SubAward Contact Person";
    private static final String SUBAWARD_REQUISITIONER = "SubAward Requisitioner";
    private static final String NEGOTIATION_NEGOTIATOR = "Negotiation Negotiator";
    private static final String UNIT_ADMINISTRATOR_ADMINISTRATIVE_OFFICER = "Unit Administrator Administrative Officer";
    private static final String UNIT_ADMINISTRATOR_OSP_ADMINISTRATOR = "Unit Administrator OSP Administrator";
    private static final String UNIT_ADMINISTRATOR_UNIT_HEAD = "Unit Administrator Unit Head";
    private static final String UNIT_ADMINISTRATOR_DEAN_VP = "Unit Administrator Dean VP";
    private static final String UNIT_ADMINISTRATOR_OTHER_INDIVIDUAL_TO_NOTIFY = "Unit Administrator Other Individual to Notify";
    private static final String UNIT_ADMINISTRATOR_ADMINISTRATOR = "Unit Administrator Administrator";
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.rule.BusinessRuleInterface#processRules(org.kuali.kra.rule.event.KraDocumentEventBaseExtension)
     */
    public boolean processRules(PerformPersonMassChangeEvent event) {
        return processPerformMassChangeEvent(event);
    }
    
    private boolean processPerformMassChangeEvent(PerformPersonMassChangeEvent event) {
        boolean rulePassed = true;
        
        PersonMassChange personMassChange = event.getPersonMassChange();
        
        rulePassed &= validateReplaceeReplacer(personMassChange);
        
        if (rulePassed) {
            rulePassed &= validateAwardMassChange(personMassChange);
            rulePassed &= validateInstitutionalProposalMassChange(personMassChange);
            rulePassed &= validateProposalDevelopmentMassChange(personMassChange);
            rulePassed &= validateSubawardMassChange(personMassChange);
            rulePassed &= validateNegotiationMassChange(personMassChange);
            rulePassed &= validateUnitAdministratorMassChange(personMassChange);
        }
        return rulePassed;
    }
    
    private boolean validateReplaceeReplacer(PersonMassChange personMassChange) {
        boolean isValid = true;
        
        if (personMassChange.getReplaceePersonId() == null && personMassChange.getReplaceeRolodexId() == null) {
            isValid = false;
            reportError(REPLACEE_FULL_NAME_FIELD, KeyConstants.ERROR_PERSON_MASS_CHANGE_REPLACEE_EMPTY);
        }
        
        if (personMassChange.getReplacerPersonId() == null && personMassChange.getReplacerRolodexId() == null) {
            isValid = false;
            reportError(REPLACER_FULL_NAME_FIELD, KeyConstants.ERROR_PERSON_MASS_CHANGE_REPLACER_EMPTY);
        }
        
        return isValid;
    }
    
    private boolean validateAwardMassChange(PersonMassChange personMassChange) {
        boolean isValid = true;
        
        AwardPersonMassChange awardPersonMassChange = personMassChange.getAwardPersonMassChange();
        
        if (awardPersonMassChange.isContactPerson()) {
            isValid &= validateRolodex(personMassChange, AWARD_CONTACT_PERSON_FIELD, AWARD_CONTACT_PERSON);
        }
        
        if (awardPersonMassChange.isUnitAdministrator()) {
            isValid &= validatePerson(personMassChange, AWARD_UNIT_ADMINISTRATOR_FIELD, AWARD_UNIT_ADMINISTRATOR);
        }
        
        return isValid;
    }
    
    private boolean validateInstitutionalProposalMassChange(PersonMassChange personMassChange) {
        boolean isValid = true;
        
        InstitutionalProposalPersonMassChange institutionalProposalPersonMassChange = personMassChange.getInstitutionalProposalPersonMassChange();
        
        if (institutionalProposalPersonMassChange.isUnitAdministrator()) {
            isValid &= validatePerson(personMassChange, INSTITUTIONAL_PROPOSAL_UNIT_ADMINISTRATOR_FIELD, INSTITUTIONAL_PROPOSAL_UNIT_ADMINISTRATOR);
        }
        
        if (institutionalProposalPersonMassChange.isMailingInformation()) {
            isValid &= validateRolodex(personMassChange, INSTITUTIONAL_PROPOSAL_MAILING_INFORMATION_FIELD, INSTITUTIONAL_PROPOSAL_MAILING_INFORMATION);
        }
        
        if (institutionalProposalPersonMassChange.isIpReviewer()) {
            isValid &= validatePerson(personMassChange, INSTITUTIONAL_PROPOSAL_IP_REVIEWER_FIELD, INSTITUTIONAL_PROPOSAL_IP_REVIEWER);
        }
        
        return isValid;
    }
    
    private boolean validateProposalDevelopmentMassChange(PersonMassChange personMassChange) {
        boolean isValid = true;
        
        ProposalDevelopmentPersonMassChange proposalDevelopmentPersonMassChange = personMassChange.getProposalDevelopmentPersonMassChange();
        
        if (proposalDevelopmentPersonMassChange.isMailingInformation()) {
            isValid &= validateRolodex(personMassChange, PROPOSAL_DEVELOPMENT_MAILING_INFORMATION_FIELD, PROPOSAL_DEVELOPMENT_MAILING_INFORMATION);
        }
        
        return isValid;
    }
    
    private boolean validateSubawardMassChange(PersonMassChange personMassChange) {
        boolean isValid = true;
        
        SubawardPersonMassChange subawardPersonMassChange = personMassChange.getSubawardPersonMassChange();
        
        if (subawardPersonMassChange.isContactPerson()) {
            isValid &= validateRolodex(personMassChange, SUBAWARD_CONTACT_PERSON_FIELD, SUBAWARD_CONTACT_PERSON);
        }
        
        if (subawardPersonMassChange.isRequisitioner()) {
            isValid &= validatePerson(personMassChange, SUBAWARD_REQUISITIONER_FIELD, SUBAWARD_REQUISITIONER);
        }
        
        return isValid;
    }
    
    private boolean validateNegotiationMassChange(PersonMassChange personMassChange) {
        boolean isValid = true;
        
        NegotiationPersonMassChange negotiationPersonMassChange = personMassChange.getNegotiationPersonMassChange();
        
        if (negotiationPersonMassChange.isNegotiator()) {
            isValid &= validatePerson(personMassChange, NEGOTIATION_NEGOTIATOR_FIELD, NEGOTIATION_NEGOTIATOR);
        }
        
        return isValid;
    }
    
    private boolean validateUnitAdministratorMassChange(PersonMassChange personMassChange) {
        boolean isValid = true;
        
        UnitAdministratorPersonMassChange unitPersonMassChange = personMassChange.getUnitAdministratorPersonMassChange();
        
        if (unitPersonMassChange.isAdministrativeOfficer()) {
            isValid &= validatePerson(personMassChange, UNIT_ADMINISTRATOR_ADMINISTRATIVE_OFFICER_FIELD, UNIT_ADMINISTRATOR_ADMINISTRATIVE_OFFICER);
        }
        
        if (unitPersonMassChange.isOspAdministrator()) {
            isValid &= validatePerson(personMassChange, UNIT_ADMINISTRATOR_OSP_ADMINISTRATOR_FIELD, UNIT_ADMINISTRATOR_OSP_ADMINISTRATOR);
        }
        
        if (unitPersonMassChange.isUnitHead()) {
            isValid &= validatePerson(personMassChange, UNIT_ADMINISTRATOR_UNIT_HEAD_FIELD, UNIT_ADMINISTRATOR_UNIT_HEAD);
        }
        
        if (unitPersonMassChange.isDeanVP()) {
            isValid &= validatePerson(personMassChange, UNIT_ADMINISTRATOR_DEAN_VP_FIELD, UNIT_ADMINISTRATOR_DEAN_VP);
        }
        
        if (unitPersonMassChange.isOtherIndividualToNotify()) {
            isValid &= validatePerson(personMassChange, UNIT_ADMINISTRATOR_OTHER_INDIVIDUAL_TO_NOTIFY_FIELD, UNIT_ADMINISTRATOR_OTHER_INDIVIDUAL_TO_NOTIFY);
        }
        
        if (unitPersonMassChange.isAdministrator()) {
            isValid &= validatePerson(personMassChange, UNIT_ADMINISTRATOR_ADMINISTRATOR_FIELD, UNIT_ADMINISTRATOR_ADMINISTRATOR);
        }
        
        return isValid;
    }
    
    private boolean validatePerson(PersonMassChange personMassChange, String propertyName, String typeText) {
        boolean isValid = true;
        
        if (!isReplaceePerson(personMassChange.getReplaceePersonId(), personMassChange.getReplaceeRolodexId())) {
            isValid = false;
            reportError(propertyName, KeyConstants.ERROR_PERSON_MASS_CHANGE_REPLACEE_SELECTION, NON_EMPLOYEE, typeText);
        }
        
        if (!isReplacerPerson(personMassChange.getReplacerPersonId(), personMassChange.getReplacerRolodexId())) {
            isValid = false;
            reportError(propertyName, KeyConstants.ERROR_PERSON_MASS_CHANGE_REPLACER_SELECTION, typeText, NON_EMPLOYEE);
        }
        
        return isValid;
    }
    
    private boolean validateRolodex(PersonMassChange personMassChange, String propertyName, String typeText) {
        boolean isValid = true;
        
        if (!isReplaceeRolodex(personMassChange.getReplaceePersonId(), personMassChange.getReplaceeRolodexId())) {
            isValid = false;
            reportError(propertyName, KeyConstants.ERROR_PERSON_MASS_CHANGE_REPLACEE_SELECTION, EMPLOYEE, typeText);
        }
        
        if (!isReplacerRolodex(personMassChange.getReplacerPersonId(), personMassChange.getReplacerRolodexId())) {
            isValid = false;
            reportError(propertyName, KeyConstants.ERROR_PERSON_MASS_CHANGE_REPLACER_SELECTION, typeText, EMPLOYEE);
        }
        
        return isValid;
    }
    
    private boolean isReplaceePerson(String replaceePersonId, String replaceeRolodexId) {
        return replaceePersonId != null && replaceeRolodexId == null;
    }
    
    private boolean isReplaceeRolodex(String replaceePersonId, String replaceeRolodexId) {
        return replaceePersonId == null && replaceeRolodexId != null;
    }
    
    private boolean isReplacerPerson(String replacerPersonId, String replacerRolodexId) {
        return replacerPersonId != null && replacerRolodexId == null;
    }
    
    private boolean isReplacerRolodex(String replacerPersonId, String replacerRolodexId) {
        return replacerPersonId == null && replacerRolodexId != null;
    }

}