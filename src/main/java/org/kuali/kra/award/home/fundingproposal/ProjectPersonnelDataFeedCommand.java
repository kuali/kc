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
    
    private boolean identicalCreditSplit;
    
    /**
     * Constructs a ProjectPersonnelDataFeedCommand.java.
     * @param award Award
     * @param proposal InstitutionalProposal
     */
    public ProjectPersonnelDataFeedCommand(Award award, InstitutionalProposal proposal, FundingProposalMergeType mergeType) {
        super(award, proposal, mergeType);
    }

    /**
     * @see org.kuali.kra.award.home.fundingproposal.ProposalDataFeedCommandBase#performDataFeed()
     */
    @Override
    void performDataFeed() {
        if (mergeType != FundingProposalMergeType.NOCHANGE) {
            identicalCreditSplit = isCreditSplitIdentical();
            if (mergeType == FundingProposalMergeType.REPLACE
                    && doesProposalHaveCreditSplitData()
                    && !proposal.getProjectPersons().isEmpty()) {
                award.getProjectPersons().clear();
            }
            for (InstitutionalProposalPerson proposalPerson : proposal.getProjectPersons()) {
                AwardPerson existingAwardPerson = findExistingAwardPerson(proposalPerson);
                if (existingAwardPerson != null) {
                    reconcileUnits(proposalPerson, existingAwardPerson);
                    if (mergeType == FundingProposalMergeType.MERGE && !identicalCreditSplit) {
                        mergeCreditSplit(existingAwardPerson, proposalPerson);
                    }
                } else {
                    award.add(createAwardPerson(proposalPerson));
                }
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
            AwardPersonUnit awardUnit = awardPerson.getUnit(ipPersonUnit.getUnitNumber());
            if (awardUnit == null) {
                awardPerson.add(createAwardPersonUnit(awardPerson, ipPersonUnit));
            } else {
                if (mergeType == FundingProposalMergeType.MERGE && !identicalCreditSplit) {
                    mergeUnitCreditSplit(awardUnit, ipPersonUnit);
                }
            }
        }
    }
    
    private boolean hasLeadUnit(AwardPerson awardPerson) {
        for (AwardPersonUnit unit : awardPerson.getUnits()) {
            if (unit.isLeadUnit()) {
                return true;
            }
        }
        return false;
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
        awardPerson.setMultiplePi(proposalPerson.isMultiplePi());
        
        for (InstitutionalProposalPersonCreditSplit ipPersonCreditSplit : proposalPerson.getCreditSplits()) {
            awardPerson.add(createAwardPersonCreditSplit(ipPersonCreditSplit));
        }
        
        for (InstitutionalProposalPersonUnit ipPersonUnit : proposalPerson.getUnits()) {
            awardPerson.add(createAwardPersonUnit(awardPerson, ipPersonUnit));
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
        awardPersonCreditSplit.setCredit(ipPersonCreditSplit.getCredit());
        awardPersonCreditSplit.setInvCreditTypeCode(ipPersonCreditSplit.getInvCreditTypeCode());
        return awardPersonCreditSplit;
    }
    
    private AwardPersonUnit createAwardPersonUnit(AwardPerson awardPerson, InstitutionalProposalPersonUnit ipPersonUnit) {
        AwardPersonUnit awardPersonUnit = new AwardPersonUnit();
        awardPersonUnit.setUnitNumber(ipPersonUnit.getUnitNumber());
        if (awardPerson.isPrincipalInvestigator() && !hasLeadUnit(awardPerson) && ipPersonUnit.isLeadUnit()) {
            awardPersonUnit.setLeadUnit(true);
            award.setLeadUnit(ipPersonUnit.getUnit());
        } else {
            awardPersonUnit.setLeadUnit(false);
        }
        for (InstitutionalProposalPersonUnitCreditSplit ipPersonUnitCreditSplit : ipPersonUnit.getCreditSplits()) {
            AwardPersonUnitCreditSplit awardPersonUnitCreditSplit = new AwardPersonUnitCreditSplit();
            awardPersonUnitCreditSplit.setCredit(ipPersonUnitCreditSplit.getCredit());
            awardPersonUnitCreditSplit.setInvCreditTypeCode(ipPersonUnitCreditSplit.getInvCreditTypeCode());
            awardPersonUnit.add(awardPersonUnitCreditSplit);
        }
        return awardPersonUnit;
    }
    
    protected void mergeCreditSplit(AwardPerson awardPerson, InstitutionalProposalPerson ipPerson) {
        for (InstitutionalProposalPersonCreditSplit ipPersonCreditSplit : ipPerson.getCreditSplits()) {
            for (AwardPersonCreditSplit awardPersonCreditSplit : awardPerson.getCreditSplits()) {
                if (StringUtils.equals(awardPersonCreditSplit.getInvCreditTypeCode(),
                        ipPersonCreditSplit.getInvCreditTypeCode())) {
                    awardPersonCreditSplit.setCredit(awardPersonCreditSplit.getCredit().add(ipPersonCreditSplit.getCredit()));
                }
            }
            
        }
    }
    
    private void mergeUnitCreditSplit(AwardPersonUnit awardUnit, InstitutionalProposalPersonUnit ipPersonUnit) {
        for (InstitutionalProposalPersonUnitCreditSplit ipPersonUnitCreditSplit : ipPersonUnit.getCreditSplits()) {
            for (AwardPersonUnitCreditSplit awardPersonUnitCreditSplit : awardUnit.getCreditSplits()) {
                if (StringUtils.equals(awardPersonUnitCreditSplit.getInvCreditTypeCode(), 
                        ipPersonUnitCreditSplit.getInvCreditTypeCode())) {
                    awardPersonUnitCreditSplit.setCredit(awardPersonUnitCreditSplit.getCredit().add(ipPersonUnitCreditSplit.getCredit()));
                }
            }
        }
    }

    protected boolean doesProposalHaveCreditSplitData() {
        for (InstitutionalProposalPerson person : proposal.getProjectPersons()) {
            if (person.getCreditSplits().size() > 0) {
                return true;
            }
            for (InstitutionalProposalPersonUnit unit : person.getUnits()) {
                if (unit.getCreditSplits().size() > 0) {
                    return true;
                }
            }
        }
        return false;
    }
    
    protected boolean isCreditSplitIdentical() {
        boolean identical = true;
        for (InstitutionalProposalPerson person : proposal.getProjectPersons()) {
            AwardPerson awardPerson = findExistingAwardPerson(person);
            if (awardPerson == null) {
                identical = false;
            } else {
                for (InstitutionalProposalPersonCreditSplit propCreditSplit : person.getCreditSplits()) {
                    AwardPersonCreditSplit awardCreditSplit = findCreditSplit(awardPerson, propCreditSplit);
                    if (awardCreditSplit == null
                            || !awardCreditSplit.getCredit().equals(propCreditSplit.getCredit())) {
                        identical = false;
                    }
                }
                for (InstitutionalProposalPersonUnit propUnit : person.getUnits()) {
                    AwardPersonUnit awardUnit = awardPerson.getUnit(propUnit.getUnitNumber());
                    if (awardUnit == null) {
                        identical = false;
                    } else {
                        for (InstitutionalProposalPersonUnitCreditSplit propCreditSplit : propUnit.getCreditSplits()) {
                            AwardPersonUnitCreditSplit awardCreditSplit = findCreditSplit(awardUnit, propCreditSplit);
                            if (awardCreditSplit == null
                                    || !awardCreditSplit.getCredit().equals(propCreditSplit.getCredit())) {
                                identical = false;
                            }
                        }
                    }
                }
            }
        }
        return identical;
    }
    
    protected AwardPersonCreditSplit findCreditSplit(AwardPerson person, InstitutionalProposalPersonCreditSplit creditSplit) {
        for (AwardPersonCreditSplit awardCreditSplit : person.getCreditSplits()) {
            if (StringUtils.equals(awardCreditSplit.getInvCreditTypeCode(), creditSplit.getInvCreditTypeCode())) {
                return awardCreditSplit;
            }
        }
        return null;
    }

    protected AwardPersonUnitCreditSplit findCreditSplit(AwardPersonUnit unit, InstitutionalProposalPersonUnitCreditSplit creditSplit) {
        for (AwardPersonUnitCreditSplit awardCreditSplit : unit.getCreditSplits()) {
            if (StringUtils.equals(awardCreditSplit.getInvCreditTypeCode(), creditSplit.getInvCreditTypeCode())) {
                return awardCreditSplit;
            }
        }
        return null;
    }


    protected boolean isIdenticalCreditSplit() {
        return identicalCreditSplit;
    }

    protected void setIdenticalCreditSplit(boolean identicalCreditSplit) {
        this.identicalCreditSplit = identicalCreditSplit;
    }
    
}
