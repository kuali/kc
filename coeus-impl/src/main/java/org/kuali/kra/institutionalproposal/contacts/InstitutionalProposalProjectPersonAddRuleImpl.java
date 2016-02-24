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
package org.kuali.kra.institutionalproposal.contacts;

import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.rice.krad.util.GlobalVariables;


public class InstitutionalProposalProjectPersonAddRuleImpl extends BaseInstitutionalProposalContactAddRule implements
        InstitutionalProposalProjectPersonAddRule {

    @Override
    public boolean processAddInstitutionalProposalProjectPersonBusinessRules(InstitutionalProposalProjectPersonRuleAddEvent event) {
        InstitutionalProposalPerson newProjectPerson = event.getNewProjectPerson();
        InstitutionalProposal institutionalProposal = ((InstitutionalProposalDocument) event.getDocument()).getInstitutionalProposal();
        
        return checkForSelectedContactAndRole(newProjectPerson) && (checkForExistingPrincipalInvestigators(institutionalProposal, newProjectPerson) 
                                                                    & checkForDuplicatePerson(institutionalProposal, newProjectPerson));
    }
    
    boolean checkForSelectedContactAndRole(InstitutionalProposalContact newContact) {
        return super.checkForSelectedContactAndRole(newContact, PROPOSAL_PROJECT_PERSON_LIST_ERROR_KEY);
    }
    
    /**
     * Verify a PI exists
     * @param institutionalProposal
     * @param newProjectPerson
     * @return
     */
    boolean checkForExistingPrincipalInvestigators(InstitutionalProposal institutionalProposal, InstitutionalProposalPerson newProjectPerson) {
        boolean valid = true;
        if(newProjectPerson.isPrincipalInvestigator()) {
            for(InstitutionalProposalPerson p: institutionalProposal.getProjectPersons()) {
                if(p.isPrincipalInvestigator()) {
                    valid = false;
                    break;
                }
            }
        }
        
        if(!valid) {
            GlobalVariables.getMessageMap().putError(PROPOSAL_PROJECT_PERSON_LIST_ERROR_KEY, ERROR_PROPOSAL_PROJECT_PERSON_PI_EXISTS);
        }
        
        return valid;
    }

    /**
     * Verify no duplicate person
     * @param institutionalProposal
     * @param newProjectPerson
     * @return
     */
    boolean checkForDuplicatePerson(InstitutionalProposal institutionalProposal, InstitutionalProposalPerson newProjectPerson) {
        boolean valid = true;
        for(InstitutionalProposalPerson p: institutionalProposal.getProjectPersons()) {
            if (p.getClass().equals(newProjectPerson.getClass()) 
                    && p.getContact().getIdentifier().equals(newProjectPerson.getContact().getIdentifier())
                    && isImportantPerson(p) && isImportantPerson(newProjectPerson)) {
                valid = false;
                break;
            }
        }
        
        if(!valid) {
            GlobalVariables.getMessageMap().putError(PROPOSAL_PROJECT_PERSON_LIST_ERROR_KEY, ERROR_PROPOSAL_PROJECT_PERSON_EXISTS, 
                                                                                newProjectPerson.getContact().getFullName());
        }

        return valid;
    }
    
    private boolean isProjectPersonInvestigator(InstitutionalProposalPerson projectPerson) {
        return projectPerson.isCoInvestigator() || projectPerson.isPrincipalInvestigator();
    }

    private boolean isImportantPerson(InstitutionalProposalPerson person) {
        return isProjectPersonInvestigator(person) || person.isKeyPerson() || person.isMultiplePi();
    }
}
