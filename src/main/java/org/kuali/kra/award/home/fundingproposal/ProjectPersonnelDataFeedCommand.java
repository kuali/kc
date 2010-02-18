/*
 * Copyright 2006-2008 The Kuali Foundation
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
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPerson;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPersonCreditSplit;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPersonUnit;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPersonUnitCreditSplit;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;

class ProjectPersonnelDataFeedCommand extends ProposalDataFeedCommandBase {
    
    public ProjectPersonnelDataFeedCommand(Award award, InstitutionalProposal proposal) {
        super(award, proposal);
    }

    /**
     * @see org.kuali.kra.award.home.fundingproposal.ProposalDataFeedCommandBase#performDataFeed()
     */
    @Override
    void performDataFeed() {
        for (InstitutionalProposalPerson proposalPerson : proposal.getProjectPersons()) {
            AwardPerson awardPerson = new AwardPerson();
            if (!StringUtils.isBlank(proposalPerson.getPersonId())) {
                awardPerson.setPersonId(proposalPerson.getPersonId());
            } else if (proposalPerson.getRolodexId() != null) {
                awardPerson.setRolodexId(proposalPerson.getRolodexId());
            }
            awardPerson.setAcademicYearEffort(proposalPerson.getAcademicYearEffort());
            awardPerson.setCalendarYearEffort(proposalPerson.getCalendarYearEffort());
            awardPerson.setContactRoleCode(proposalPerson.getContactRoleCode());
            awardPerson.setEmailAddress(proposalPerson.getEmailAddress());
            awardPerson.setFaculty(proposalPerson.isFaculty());
            awardPerson.setFullName(proposalPerson.getFullName());
            awardPerson.setKeyPersonRole(proposalPerson.getKeyPersonRole());
            awardPerson.setPhoneNumber(proposalPerson.getPhoneNumber());
            awardPerson.setRoleCode(proposalPerson.getRoleCode());
            awardPerson.setSummerEffort(proposalPerson.getSummerEffort());
            awardPerson.setTotalEffort(proposalPerson.getTotalEffort());
            for (InstitutionalProposalPersonCreditSplit ipPersonCreditSplit : proposalPerson.getCreditSplits()) {
                AwardPersonCreditSplit awardPersonCreditSplit = new AwardPersonCreditSplit();
                awardPersonCreditSplit.setAutoIncrementSet(ipPersonCreditSplit.isAutoIncrementSet());
                awardPersonCreditSplit.setCredit(ipPersonCreditSplit.getCredit());
                awardPersonCreditSplit.setInvCreditTypeCode(ipPersonCreditSplit.getInvCreditTypeCode());
                awardPerson.add(awardPersonCreditSplit);
            }
            for (InstitutionalProposalPersonUnit ipPersonUnit : proposalPerson.getUnits()) {
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
                awardPerson.add(awardPersonUnit);
            }
            award.add(awardPerson);
        }
    }

}
