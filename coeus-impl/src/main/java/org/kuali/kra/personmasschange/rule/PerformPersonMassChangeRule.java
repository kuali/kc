/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.personmasschange.rule;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.person.masschange.ProposalDevelopmentPersonMassChange;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.personmasschange.bo.*;
import org.kuali.kra.personmasschange.rule.event.PerformPersonMassChangeEvent;

public class PerformPersonMassChangeRule extends KcTransactionalDocumentRuleBase implements KcBusinessRule<PerformPersonMassChangeEvent> {
    
    private static final String REPLACEE_FULL_NAME_FIELD = "document.personMassChange.replaceeFullName";
    private static final String REPLACER_FULL_NAME_FIELD = "document.personMassChange.replacerFullName";
    
    private static final String AWARD_FIELD = "document.personMassChange.awardPersonMassChange.";
    private static final String INSTITUTIONAL_PROPOSAL_FIELD = "document.personMassChange.institutionalProposalPersonMassChange.";
    private static final String PROPOSAL_DEVELOPMENT_FIELD = "document.personMassChange.proposalDevelopmentPersonMassChange.";
    private static final String SUBAWARD_FIELD = "document.personMassChange.subawardPersonMassChange.";
    private static final String NEGOTIATION_FIELD = "document.personMassChange.negotiationPersonMassChange.";
    private static final String UNIT_ADMINISTRATOR_FIELD = "document.personMassChange.unitAdministratorPersonMassChange.";

    private static final String UNIT_CONTACT_ID = "unitContact";
    private static final String SPONSOR_CONTACT_ID = "sponsorContact";
    private static final String MAILING_INFORMATION_ID = "mailingInformation";
    private static final String IP_REVIEWER_ID = "ipReviewer";
    private static final String REQUISITIONER_ID = "requisitioner";
    private static final String CONTACT_ID = "contact";
    private static final String NEGOTIATOR_ID = "negotiator";
    private static final String ADMINISTRATIVE_OFFICER_ID = "administrativeOfficer";
    private static final String OSP_ADMINISTRATOR_ID = "ospAdministrator";
    private static final String UNIT_HEAD_ID = "unitHead";
    private static final String DEAN_VP_ID = "deanVP";
    private static final String OTHER_INDIVIDUAL_TO_NOTIFY_ID = "otherIndividualToNotify";
    private static final String ADMINISTRATIVE_CONTACT_ID = "administrativeContact";
    private static final String FINANCIAL_CONTACT_ID = "financialContact";
    
    private static final String AWARD_UNIT_CONTACT_FIELD = AWARD_FIELD + UNIT_CONTACT_ID;
    private static final String AWARD_SPONSOR_CONTACT_FIELD = AWARD_FIELD + SPONSOR_CONTACT_ID;
    private static final String INSTITUTIONAL_PROPOSAL_UNIT_CONTACT_FIELD = INSTITUTIONAL_PROPOSAL_FIELD + UNIT_CONTACT_ID;
    private static final String INSTITUTIONAL_PROPOSAL_MAILING_INFORMATION_FIELD = INSTITUTIONAL_PROPOSAL_FIELD + MAILING_INFORMATION_ID;
    private static final String INSTITUTIONAL_PROPOSAL_IP_REVIEWER_FIELD = INSTITUTIONAL_PROPOSAL_FIELD + IP_REVIEWER_ID;
    private static final String PROPOSAL_DEVELOPMENT_MAILING_INFORMATION_FIELD = PROPOSAL_DEVELOPMENT_FIELD + MAILING_INFORMATION_ID;
    private static final String SUBAWARD_REQUISITIONER_FIELD = SUBAWARD_FIELD + REQUISITIONER_ID;
    private static final String SUBAWARD_CONTACT_FIELD = SUBAWARD_FIELD + CONTACT_ID;
    private static final String NEGOTIATION_NEGOTIATOR_FIELD = NEGOTIATION_FIELD + NEGOTIATOR_ID;
    private static final String UNIT_ADMINISTRATOR_ADMINISTRATIVE_OFFICER_FIELD = UNIT_ADMINISTRATOR_FIELD + ADMINISTRATIVE_OFFICER_ID;
    private static final String UNIT_ADMINISTRATOR_OSP_ADMINISTRATOR_FIELD = UNIT_ADMINISTRATOR_FIELD + OSP_ADMINISTRATOR_ID;
    private static final String UNIT_ADMINISTRATOR_UNIT_HEAD_FIELD = UNIT_ADMINISTRATOR_FIELD + UNIT_HEAD_ID;
    private static final String UNIT_ADMINISTRATOR_DEAN_VP_FIELD = UNIT_ADMINISTRATOR_FIELD + DEAN_VP_ID;
    private static final String UNIT_ADMINISTRATOR_OTHER_INDIVIDUAL_TO_NOTIFY_FIELD = UNIT_ADMINISTRATOR_FIELD + OTHER_INDIVIDUAL_TO_NOTIFY_ID;
    private static final String UNIT_ADMINISTRATOR_ADMINISTRATIVE_CONTACT_FIELD = UNIT_ADMINISTRATOR_FIELD + ADMINISTRATIVE_CONTACT_ID;
    private static final String UNIT_ADMINISTRATOR_FINANCIAL_CONTACT_FIELD = UNIT_ADMINISTRATOR_FIELD + FINANCIAL_CONTACT_ID;
    
    private static final String EMPLOYEE = "Employee";
    private static final String NON_EMPLOYEE = "Non-Employee";
    
    private static final String AWARD_UNIT_CONTACT = "Award Unit Contact";
    private static final String AWARD_SPONSOR_CONTACT = "Award Sponsor Contact";
    private static final String INSTITUTIONAL_PROPOSAL_MAILING_INFORMATION = "Institutional Proposal Mailing Information";
    private static final String INSTITUTIONAL_PROPOSAL_UNIT_CONTACT = "Institutional Proposal Unit Contact";
    private static final String INSTITUTIONAL_PROPOSAL_IP_REVIEWER = "Institutional Proposal IP Reviewer";
    private static final String PROPOSAL_DEVELOPMENT_MAILING_INFORMATION = "Proposal Development Mailing Information";
    private static final String SUBAWARD_REQUISITIONER = "SubAward Requisitioner";
    private static final String SUBAWARD_CONTACT = "SubAward Contact";
    private static final String NEGOTIATION_NEGOTIATOR = "Negotiation Negotiator";
    private static final String UNIT_ADMINISTRATOR_ADMINISTRATIVE_OFFICER = "Unit Administrator Administrative Officer";
    private static final String UNIT_ADMINISTRATOR_OSP_ADMINISTRATOR = "Unit Administrator OSP Administrator";
    private static final String UNIT_ADMINISTRATOR_UNIT_HEAD = "Unit Administrator Unit Head";
    private static final String UNIT_ADMINISTRATOR_DEAN_VP = "Unit Administrator Dean VP";
    private static final String UNIT_ADMINISTRATOR_OTHER_INDIVIDUAL_TO_NOTIFY = "Unit Administrator Other Individual to Notify";
    private static final String UNIT_ADMINISTRATOR_ADMINISTRATIVE_CONTACT = "Unit Administrator Administrative Contact";
    private static final String UNIT_ADMINISTRATOR_FINANCIAL_CONTACT = "Unit Administrator Financial Contact";
    
    @Override
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
            return isValid;
        }
        
        if (personMassChange.getReplacerPersonId() == null && personMassChange.getReplacerRolodexId() == null) {
            isValid = false;
            reportError(REPLACER_FULL_NAME_FIELD, KeyConstants.ERROR_PERSON_MASS_CHANGE_REPLACER_EMPTY);
            return isValid;
        }
        
        if ((personMassChange.getReplacerPersonId() != null 
                && StringUtils.equalsIgnoreCase(personMassChange.getReplacerPersonId(), personMassChange.getReplaceePersonId()))
           || (personMassChange.getReplacerRolodexId() != null 
                && StringUtils.equalsIgnoreCase(String.valueOf(personMassChange.getReplacerRolodexId()), 
                        String.valueOf(personMassChange.getReplaceeRolodexId())))) {
            isValid = false;
            reportError(REPLACER_FULL_NAME_FIELD, KeyConstants.ERROR_PERSON_MASS_CHANGE_SAME_PERSONS);
            
        }
        
        return isValid;
    }
    
    private boolean validateAwardMassChange(PersonMassChange personMassChange) {
        boolean isValid = true;
        
        AwardPersonMassChange awardPersonMassChange = personMassChange.getAwardPersonMassChange();
        
        if (awardPersonMassChange.isUnitContact()) {
            isValid &= validatePerson(personMassChange, AWARD_UNIT_CONTACT_FIELD, AWARD_UNIT_CONTACT);
        }
        
        if (awardPersonMassChange.isSponsorContact()) {
            isValid &= validateRolodex(personMassChange, AWARD_SPONSOR_CONTACT_FIELD, AWARD_SPONSOR_CONTACT);
        }
        

        
        return isValid;
    }
    
    private boolean validateInstitutionalProposalMassChange(PersonMassChange personMassChange) {
        boolean isValid = true;
        
        InstitutionalProposalPersonMassChange institutionalProposalPersonMassChange = personMassChange.getInstitutionalProposalPersonMassChange();
        
        if (institutionalProposalPersonMassChange.isMailingInformation()) {
            isValid &= validateRolodex(personMassChange, INSTITUTIONAL_PROPOSAL_MAILING_INFORMATION_FIELD, INSTITUTIONAL_PROPOSAL_MAILING_INFORMATION);
        }
        
        if (institutionalProposalPersonMassChange.isUnitContact()) {
            isValid &= validatePerson(personMassChange, INSTITUTIONAL_PROPOSAL_UNIT_CONTACT_FIELD, INSTITUTIONAL_PROPOSAL_UNIT_CONTACT);
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
        
        if (subawardPersonMassChange.isContact()) {
            isValid &= validateRolodex(personMassChange, SUBAWARD_CONTACT_FIELD, SUBAWARD_CONTACT);
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
        
        if (unitPersonMassChange.isAdministrativeContact()) {
            isValid &= validatePerson(personMassChange, UNIT_ADMINISTRATOR_ADMINISTRATIVE_CONTACT_FIELD, UNIT_ADMINISTRATOR_ADMINISTRATIVE_CONTACT);
        }
        
        if (unitPersonMassChange.isFinancialContact()) {
            isValid &= validatePerson(personMassChange, UNIT_ADMINISTRATOR_FINANCIAL_CONTACT_FIELD, UNIT_ADMINISTRATOR_FINANCIAL_CONTACT);
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
    
    private boolean isReplaceePerson(String replaceePersonId, Integer replaceeRolodexId) {
        return replaceePersonId != null && replaceeRolodexId == null;
    }
    
    private boolean isReplaceeRolodex(String replaceePersonId, Integer replaceeRolodexId) {
        return replaceePersonId == null && replaceeRolodexId != null;
    }
    
    private boolean isReplacerPerson(String replacerPersonId, Integer replacerRolodexId) {
        return replacerPersonId != null && replacerRolodexId == null;
    }
    
    private boolean isReplacerRolodex(String replacerPersonId, Integer replacerRolodexId) {
        return replacerPersonId == null && replacerRolodexId != null;
    }

}
