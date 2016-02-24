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
package org.kuali.kra.test.fixtures;

import org.kuali.coeus.common.framework.person.editable.PersonEditableService;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.ProposalPersonUnit;
import org.kuali.coeus.propdev.impl.person.creditsplit.ProposalPersonCreditSplit;
import org.kuali.coeus.propdev.impl.person.creditsplit.ProposalUnitCreditSplit;
import org.kuali.coeus.propdev.impl.person.KeyPersonnelService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.person.ProposalPersonYnq;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;
import static org.kuali.kra.infrastructure.Constants.*;

/**
 * Fixtures used as <code>{@link ProposalPerson}</code> instances for tests. This is NOT test data. Test data is loaded by scripts. These are 
 * test fixtures.
 */
public enum ProposalPersonFixture {
    PRINCIPAL_INVESTIGATOR("10000000003", PRINCIPAL_INVESTIGATOR_ROLE),
    
    /**
     * Fully valid Principal Investigator with all the right valid stuff except no certifications.
     */
    INCOMPLETE_CERTIFICATIONS("10000000003", PRINCIPAL_INVESTIGATOR_ROLE) {
        /**
         * Clear out the Yes/No Questions
         * 
         * @see org.kuali.kra.test.fixtures.ProposalPersonFixture#getPerson()
         */
        public void populatePerson(ProposalDevelopmentDocument document, ProposalPerson person) {
            super.populatePerson(document, person);
            
            person.getProposalPersonYnq(0).setAnswer(null);
        }
    },

    /**
     * Fully valid Principal Investigator with valid credit splits adding to a hundred and valid lead unit with valid credit splits
     */
    INVESTIGATOR_SPLIT_ADDS_TO_ONE_HUNDRED("10000000003", PRINCIPAL_INVESTIGATOR_ROLE) {
        
        /**
         * Use a normally valid <code>{@link ProposalPerson}</code> instance. Set all credit splits up to be valid. 
         * 
         * @see org.kuali.kra.test.fixtures.ProposalPersonFixture#getPerson()
         */
        public void populatePerson(ProposalDevelopmentDocument document, ProposalPerson person) {       
            super.populatePerson(document, person);
        }        
    },
    /**
     * Fully valid Principal Investigator with valid credit splits adding to a hundred and invalid lead unit
     */
    INVESTIGATOR_UNIT_NOT_TO_ONE_HUNDRED("admin", PRINCIPAL_INVESTIGATOR_ROLE) {
        
        /**
         * Use a normally valid <code>{@link ProposalPerson}</code> instance. Set all credit splits up to be valid except for units. 
         * 
         * @see org.kuali.kra.test.fixtures.ProposalPersonFixture#getPerson()
         */
        public void populatePerson(ProposalDevelopmentDocument document, ProposalPerson person) {
            super.populatePerson(document, person);

            for (ProposalUnitCreditSplit creditSplit : person.getUnit(0).getCreditSplits()) {
                if (creditSplit.getInvestigatorCreditType().addsToHundred()){
                    creditSplit.setCredit(new ScaleTwoDecimal(50.00));
                }
            }
        }        
    },
    /**
     * Fully valid Principal Investigator with valid credit splits adding to a hundred and invalid lead unit
     */
    INVESTIGATOR_OVER_ONE_HUNDRED("10000000003", PRINCIPAL_INVESTIGATOR_ROLE) {
        
        /**
         * Use a normally valid <code>{@link ProposalPerson}</code> instance. Set all credit splits up to be valid except for units. 
         * 
         * @see org.kuali.kra.test.fixtures.ProposalPersonFixture#getPerson()
         */
        public void populatePerson(ProposalDevelopmentDocument document, ProposalPerson person) {
            super.populatePerson(document, person);
            
            person.getCreditSplit(0).setCredit(new ScaleTwoDecimal(200.00));
            
        }        
    },
    /**
     * Fully valid Principal Investigator with valid credit splits adding to a hundred except for one is negative. No 
     * <code>{@link ProposalUnitCreditSplit}</code> instances.
     */
    INVESTIGATOR_UNDER_ZERO("10000000003", PRINCIPAL_INVESTIGATOR_ROLE) {
        
        /**
         * Use a normally valid <code>{@link ProposalPerson}</code> instance. Set all credit splits up to be valid except for units. 
         * 
         * @see org.kuali.kra.test.fixtures.ProposalPersonFixture#getPerson()
         */
        public void populatePerson(ProposalDevelopmentDocument document, ProposalPerson person) {
            super.populatePerson(document, person);
            
            person.getCreditSplit(0).setCredit(new ScaleTwoDecimal(-10.00));
        }        
    },
    /**
     * Fully valid Principal Investigator with valid credit splits adding to a hundred and invalid lead unit that goes over 100%
     */
    INVESTIGATOR_UNIT_OVER_ONE_HUNDRED("10000000003", PRINCIPAL_INVESTIGATOR_ROLE) {
        
        /**
         * Use a normally valid <code>{@link ProposalPerson}</code> instance. Set all credit splits up to be valid except unit is over 100% 
         * 
         * @see org.kuali.kra.test.fixtures.ProposalPersonFixture#getPerson()
         */
        public void populatePerson(ProposalDevelopmentDocument document, ProposalPerson person) {
            super.populatePerson(document, person);
            
            person.getUnit(0).getCreditSplit(0).setCredit(new ScaleTwoDecimal(200.00));
        }        
    },
    /**
     * Fully valid Principal Investigator with valid credit splits adding to a hundred and invalid lead unit
     */
    INVESTIGATOR_UNIT_UNDER_ZERO("10000000003", PRINCIPAL_INVESTIGATOR_ROLE) {
        
        /**
         * Use a normally valid <code>{@link ProposalPerson}</code> instance. Set all credit splits up to be valid except for units. Units are under 0%
         * 
         * @see org.kuali.kra.test.fixtures.ProposalPersonFixture#getPerson()
         */
        public void populatePerson(ProposalDevelopmentDocument document, ProposalPerson person) {
            super.populatePerson(document, person);
            
            person.getUnit(0).getCreditSplit(0).setCredit(new ScaleTwoDecimal(-10.00));
        }        
    },
    JTESTER_CO_INVESTIGATOR("10000000001", CO_INVESTIGATOR_ROLE),
    WOODS_CO_INVESTIGATOR("10000000005", CO_INVESTIGATOR_ROLE),
    OBLOOD_KEY_PERSON("10000000006", KEY_PERSON_ROLE);
    
    private String personId;
    private String roleId;

    private ProposalPersonFixture() {
    }
 
    private ProposalPersonFixture(String personId, String roleId) {
        this.roleId = roleId;
        this.personId = personId;
    }

    public ProposalPerson getPerson() {
       // ProposalPerson retval = getService(KeyPersonnelService.class).createProposalPersonFromPersonId(personId);
        ProposalPerson retval = new ProposalPerson();
        retval.setPersonId(personId);
        getService(PersonEditableService.class).populateContactFieldsFromPersonId(retval);
        retval.setProposalPersonRoleId(roleId);
        retval.setPercentageEffort(new ScaleTwoDecimal(100.0));
        return retval;
    }
    
    public void populatePerson(ProposalDevelopmentDocument document, ProposalPerson person) { 
        getService(KeyPersonnelService.class).populateProposalPerson(person, document);
        if (person.isPrincipalInvestigator()) {
            getService(KeyPersonnelService.class).assignLeadUnit(person, document.getDevelopmentProposal().getOwnedByUnitNumber());
        }else {
            // Lead Unit information needs to be removed in case the person used to be a PI
            ProposalPersonUnit unit = person.getUnit(document.getDevelopmentProposal().getOwnedByUnitNumber());
            if (unit != null) {
                unit.setLeadUnit(false);
            }                
        }
        
        for (ProposalPersonCreditSplit creditSplit : person.getCreditSplits()) {
            creditSplit.refreshReferenceObject("investigatorCreditType");
            if (creditSplit.getInvestigatorCreditType().addsToHundred()){
                creditSplit.setCredit(new ScaleTwoDecimal(100.00));
            }
        }
        
        for (ProposalPersonUnit unit : person.getUnits()) {
            for (ProposalUnitCreditSplit creditSplit : unit.getCreditSplits()) {
                creditSplit.refreshReferenceObject("investigatorCreditType");
                if (creditSplit.getInvestigatorCreditType().addsToHundred()){
                    creditSplit.setCredit(new ScaleTwoDecimal(100.00));
                }
            }
        }
        
        if (person.isPrincipalInvestigator()) {
            for (ProposalPersonYnq ynq : person.getProposalPersonYnqs()) {  
                ynq.setAnswer("Y");
            }
        }
    }
}
