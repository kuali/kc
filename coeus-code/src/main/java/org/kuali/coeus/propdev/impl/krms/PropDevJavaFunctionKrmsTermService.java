/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.krms;

import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;

/**
 * This interface is to declare all methods which are used as KRMS Terms in KC
 */
public interface PropDevJavaFunctionKrmsTermService {
    public String multiplePI(DevelopmentProposal developmentProposal);
    public String s2sBudgetRule(DevelopmentProposal developmentProposal, String formNames);
    public String monitoredSponsorRule(DevelopmentProposal developmentProposal, String monitoredSponsorHirearchies);
    public String s2sResplanRule(DevelopmentProposal developmentProposal, String narativeTypes, String maxNumber);
    public String grantsFormRule(DevelopmentProposal developmentProposal, String formName);
    public String biosketchFileNameRule(DevelopmentProposal developmentProposal);
    public String ospAdminPropPersonRule(DevelopmentProposal developmentProposal);
    public String costElementVersionLimit(DevelopmentProposal developmentProposal, String versionNumber, String costElementName, String limit);
    public String divisionCodeRule(DevelopmentProposal developmentProposal);
    public String divisionCodeIsFellowship(DevelopmentProposal developmentProposal, String fellowshipCodes);
    public String budgetSubawardOrganizationnameRule(DevelopmentProposal developmentProposal);
    public String checkProposalPerson(DevelopmentProposal developmentProposal, String personId);
    public String agencyProgramCodeNullRule(DevelopmentProposal developmentProposal);
    public String allProposalsRule(DevelopmentProposal developmentProposal);
    public String proposalLeadUnitInHierarchy(DevelopmentProposal developmentProposal, String unitNumberToCheck);
    public String s2sSubawardRule(DevelopmentProposal developmentProposal, String rrFormNames, String phsFromNames);
    public String proposalGrantsRule(DevelopmentProposal developmentProposal);
    public String narrativeTypeRule(DevelopmentProposal developmentProposal,String narrativeTypeCode);
    public String s2s398CoverRule(DevelopmentProposal developmentProposal, String PHSCoverLetters, String narrativeTypeCode);
    public String narrativeFileName(DevelopmentProposal developmentProposal);
    public String costElementInVersion(DevelopmentProposal developmentProposal, String versionNumber, String costElement);
    public String investigatorKeyPersonCertificationRule(DevelopmentProposal developmentProposal);
    
    public Boolean specifiedGGForm(DevelopmentProposal developmentProposal,String formName);
    public String leadUnitRule(DevelopmentProposal developmentProposal, String unitNumber);
    public String sponsorGroupRule(DevelopmentProposal developmentProposal, String sponsorGroup);
    public String proposalAwardTypeRule(DevelopmentProposal developmentProposal, Integer awardTypeCode);
    public String s2sLeadershipRule(DevelopmentProposal developmentProposal);
    public String checkProposalPiRule(DevelopmentProposal developmentProposal, String principalId);
    public String checkProposalCoiRule(DevelopmentProposal developmentProposal, String principalId);
    public String leadUnitBelowRule(DevelopmentProposal developmentProposal, String unitNumber);
    public String specialReviewRule(DevelopmentProposal developmentProposal, String specialReviewTypeCode);
    public String proposalUnitRule(DevelopmentProposal developmentProposal, String unitNumber);
    public String sponsorTypeRule(DevelopmentProposal developmentProposal, String sponsorTypeCode);
    public String s2sAttachmentNarrativeRule(DevelopmentProposal developmentProposal);
    public String s2sModularBudgetRule(DevelopmentProposal developmentProposal);
    public String s2sFederalIdRule(DevelopmentProposal developmentProposal);
    public String mtdcDeviation(DevelopmentProposal developmentProposal);
    public String s2sExemptionRule(DevelopmentProposal developmentProposal);
    public String costElement(DevelopmentProposal developmentProposal, String costElement);
    public String activityTypeRule(DevelopmentProposal developmentProposal, String activityTypeCode);
    public String sponsor(DevelopmentProposal developmentProposal, String sponsorCode);
    public String nonFacultyPi(DevelopmentProposal developmentProposal);
    public String attachmentFileNameRule(DevelopmentProposal developmentProposal);
    public String mtdcDeviationInVersion(DevelopmentProposal developmentProposal, String versionNumber);
    public String proposalTypeRule(DevelopmentProposal developmentProposal, String proposalTypeCode);
    public String incompleteNarrativeRule(DevelopmentProposal developmentProposal);
    
    public String investigatorCitizenshipTypeRule(DevelopmentProposal developmentProposal, String citizenshipTypeToCheck);
    public String piAppointmentTypeRule(DevelopmentProposal developmentProposal);
    public String proposalCampusRule(DevelopmentProposal developmentProposal, String a2SCampusCode);
    public String routedToOSPRule(DevelopmentProposal developmentProposal);
    public String isUserProposalPI(DevelopmentProposal developmentProposal);
    public String proposalUnitBelow(DevelopmentProposal developmentProposal, String unitNumberToCheck);
    public String usesRolodex(DevelopmentProposal developmentProposal, Integer rolodexId);
    public String competitionIdRule(DevelopmentProposal developmentProposal, String competitionId);
    public String specialReviewDateRule(DevelopmentProposal developmentProposal);
    public String deadlineDateRule(DevelopmentProposal developmentProposal, String deadlineDate);
    public String routingSequenceRule(DevelopmentProposal developmentProposal);
    
    public String checkProposalPersonIsKeyPerson(DevelopmentProposal developmentProposal);
    public String checkProposalPersonIsCoi(DevelopmentProposal developmentProposal);
    public String checkProposalPersonIsPi(DevelopmentProposal developmentProposal);
    
}
