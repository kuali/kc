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
package org.kuali.kra.award.home.fundingproposal;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.contacts.AwardPersonCreditSplit;
import org.kuali.kra.award.contacts.AwardPersonUnit;
import org.kuali.kra.award.contacts.AwardPersonUnitCreditSplit;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPerson;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPersonCreditSplit;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPersonUnit;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPersonUnitCreditSplit;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;

/**
 * Handles key personnel data feed from Institutional Proposal to Award.
 */
class ProjectPersonnelDataFeedCommand extends ProposalDataFeedCommandBase {
    
    /**
     * Constructs a ProjectPersonnelDataFeedCommand.java.
     * @param award Award
     * @param proposal InstitutionalProposal
     */
    public ProjectPersonnelDataFeedCommand(Award award, InstitutionalProposal proposal) {
        super(award, proposal);
    }

    /**
     * @see org.kuali.kra.award.home.fundingproposal.ProposalDataFeedCommandBase#performDataFeed()
     */
    @Override
    void performDataFeed() {
        for (InstitutionalProposalPerson proposalPerson : proposal.getProjectPersons()) {
            AwardPerson existingAwardPerson = findExistingAwardPerson(proposalPerson);
            if (existingAwardPerson != null) {
                reconcileUnits(proposalPerson, existingAwardPerson);
            } else {
                award.add(createAwardPerson(proposalPerson));
            }
        }
    }
    
    private AwardPerson findExistingAwardPerson(InstitutionalProposalPerson proposalPerson) {
        AwardPerson existingAwardPerson = null;
        if (!StringUtils.isBlank(proposalPerson.getPersonId())) {
            existingAwardPerson = award.getProjectPerson(proposalPerson.getPersonId());
        } else if (proposalPerson.getRolodexId() != null) {
            existingAwardPerson = award.getProjectPerson(proposalPerson.getRolodexId());
        }
        return existingAwardPerson;
    }
    
    private void reconcileUnits(InstitutionalProposalPerson proposalPerson, AwardPerson awardPerson) {
        for (InstitutionalProposalPersonUnit ipPersonUnit : proposalPerson.getUnits()) {
            if (!awardPerson.hasUnit(ipPersonUnit.getUnitNumber())) {
                awardPerson.add(createAwardPersonUnit(ipPersonUnit));
            }
        }
    }
    
    private AwardPerson createAwardPerson(InstitutionalProposalPerson proposalPerson) {
        AwardPerson awardPerson = new AwardPerson();
        if (!StringUtils.isBlank(proposalPerson.getPersonId())) {
            awardPerson.setPersonId(proposalPerson.getPersonId());
        } else {
            awardPerson.setRolodexId(proposalPerson.getRolodexId());
        }
        
        populateRoleCodes(awardPerson, proposalPerson);
        awardPerson.setAcademicYearEffort(proposalPerson.getAcademicYearEffort());
        awardPerson.setCalendarYearEffort(proposalPerson.getCalendarYearEffort());
        awardPerson.setEmailAddress(proposalPerson.getEmailAddress());
        awardPerson.setFaculty(proposalPerson.isFaculty());
        awardPerson.setFullName(proposalPerson.getFullName());
        awardPerson.setPhoneNumber(proposalPerson.getPhoneNumber());
        awardPerson.setSummerEffort(proposalPerson.getSummerEffort());
        awardPerson.setTotalEffort(proposalPerson.getTotalEffort());
        
        for (InstitutionalProposalPersonCreditSplit ipPersonCreditSplit : proposalPerson.getCreditSplits()) {
            awardPerson.add(createAwardPersonCreditSplit(ipPersonCreditSplit));
        }
        
        for (InstitutionalProposalPersonUnit ipPersonUnit : proposalPerson.getUnits()) {
            awardPerson.add(createAwardPersonUnit(ipPersonUnit));
        }
        
        return awardPerson;
    }
    
    private void populateRoleCodes(AwardPerson awardPerson, InstitutionalProposalPerson proposalPerson) {
        if (ContactRole.PI_CODE.equals(proposalPerson.getRoleCode()) && award.getPrincipalInvestigator() != null) {
            awardPerson.setRoleCode(ContactRole.COI_CODE);
            awardPerson.setKeyPersonRole(ContactRole.COI_CODE);
            awardPerson.setContactRoleCode(ContactRole.COI_CODE);
        } else {
            awardPerson.setRoleCode(proposalPerson.getRoleCode());
            awardPerson.setKeyPersonRole(proposalPerson.getKeyPersonRole());
            awardPerson.setContactRoleCode(proposalPerson.getContactRoleCode());
        }
    }
    
    private AwardPersonCreditSplit createAwardPersonCreditSplit(InstitutionalProposalPersonCreditSplit ipPersonCreditSplit) {
        AwardPersonCreditSplit awardPersonCreditSplit = new AwardPersonCreditSplit();
        awardPersonCreditSplit.setAutoIncrementSet(ipPersonCreditSplit.isAutoIncrementSet());
        awardPersonCreditSplit.setCredit(ipPersonCreditSplit.getCredit());
        awardPersonCreditSplit.setInvCreditTypeCode(ipPersonCreditSplit.getInvCreditTypeCode());
        return awardPersonCreditSplit;
    }
    
    private AwardPersonUnit createAwardPersonUnit(InstitutionalProposalPersonUnit ipPersonUnit) {
        AwardPersonUnit awardPersonUnit = new AwardPersonUnit();
        awardPersonUnit.setUnitNumber(ipPersonUnit.getUnitNumber());
        awardPersonUnit.setAutoIncrementSet(ipPersonUnit.isAutoIncrementSet());
        awardPersonUnit.setLeadUnit(ipPersonUnit.isLeadUnit());
        for (InstitutionalProposalPersonUnitCreditSplit ipPersonUnitCreditSplit : ipPersonUnit.getCreditSplits()) {
            AwardPersonUnitCreditSplit awardPersonUnitCreditSplit = new AwardPersonUnitCreditSplit();
            awardPersonUnitCreditSplit.setAutoIncrementSet(ipPersonUnitCreditSplit.isAutoIncrementSet());
            awardPersonUnitCreditSplit.setCredit(ipPersonUnitCreditSplit.getCredit());
            awardPersonUnitCreditSplit.setInvCreditTypeCode(ipPersonUnitCreditSplit.getInvCreditTypeCode());
            awardPersonUnit.add(awardPersonUnitCreditSplit);
        }
        return awardPersonUnit;
    }

}
