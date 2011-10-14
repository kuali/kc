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
package org.kuali.kra.triage.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.kuali.kra.SeleniumUnitTest;
import org.kuali.kra.award.web.AwardAttachmentsPanelSeleniumTest;
import org.kuali.kra.award.web.AwardBudgetBasicSeleniumTest;
import org.kuali.kra.award.web.AwardCommentsPanelSeleniumTest;
import org.kuali.kra.award.web.AwardCompleteSeleniumTest;
import org.kuali.kra.award.web.AwardDataValidationPanelSeleniumTest;
import org.kuali.kra.award.web.AwardDetailsAndDatesPanelSeleniumTest;
import org.kuali.kra.award.web.AwardDirectFandADistributionPanelSeleniumTest;
import org.kuali.kra.award.web.AwardReportsPanelSeleniumTest;
import org.kuali.kra.award.web.AwardSpecialReviewSeleniumTest;
import org.kuali.kra.bo.web.CustomAttributeMaintenanceDocumentSeleniumTest;
import org.kuali.kra.bo.web.InstituteLaRateMaintenanceDocumentSeleniumTest;
import org.kuali.kra.bo.web.InstituteRateMaintenanceDocumentSeleniumTest;
import org.kuali.kra.bo.web.OrganizationMaintenanceDocumentSeleniumTest;
import org.kuali.kra.bo.web.SponsorFormMaintenanceDocumentSeleniumTest;
import org.kuali.kra.budget.web.BudgetCategoryMaintenanceDocumentSeleniumTest;
import org.kuali.kra.budget.web.CostElementMaintenanceDocumentSeleniumTest;
import org.kuali.kra.budget.web.ValidCalcTypeMaintenanceDocumentSeleniumTest;
import org.kuali.kra.coi.personfinancialentity.FinancialEntitySeleniumWebTest;
import org.kuali.kra.committee.web.CommitteeActionsSeleniumTest;
import org.kuali.kra.committee.web.CommitteeAreasOfResearchPanelSeleniumTest;
import org.kuali.kra.committee.web.CommitteeAuthorizationSeleniumTest;
import org.kuali.kra.committee.web.CommitteeCommitteeSeleniumTest;
import org.kuali.kra.committee.web.CommitteeCompleteSeleniumTest;
import org.kuali.kra.committee.web.CommitteeMembershipSeleniumTest;
import org.kuali.kra.committee.web.CommitteeScheduleAddSeleniumTest;
import org.kuali.kra.committee.web.CommitteeScheduleFilterSeleniumTest;
import org.kuali.kra.committee.web.CommitteeSchedulePanelSeleniumTest;
import org.kuali.kra.committee.web.CommitteeScheduleRuleSeleniumTest;
import org.kuali.kra.institutionalproposal.web.InstitutionalProposalCompleteSeleniumTest;
import org.kuali.kra.institutionalproposal.web.InstitutionalProposalContactsSeleniumTest;
import org.kuali.kra.institutionalproposal.web.InstitutionalProposalDistributionSeleniumTest;
import org.kuali.kra.institutionalproposal.web.InstitutionalProposalHomeSeleniumTest;
import org.kuali.kra.institutionalproposal.web.InstitutionalProposalSpecialReviewSeleniumTest;
import org.kuali.kra.irb.web.ProtocolCompleteSeleniumTest;
import org.kuali.kra.proposaldevelopment.web.ActivityTypeMaintenanceDocumentSeleniumTest;
import org.kuali.kra.proposaldevelopment.web.ProposalDevelopmentAbstractsPanelSeleniumTest;
import org.kuali.kra.proposaldevelopment.web.ProposalDevelopmentCompleteSeleniumTest;
import org.kuali.kra.proposaldevelopment.web.ProposalDevelopmentCustomDataSeleniumTest;
import org.kuali.kra.proposaldevelopment.web.ProposalDevelopmentDeleteProposalSeleniumTest;
import org.kuali.kra.proposaldevelopment.web.ProposalDevelopmentKeyPersonnelSeleniumTest;
import org.kuali.kra.proposaldevelopment.web.ProposalDevelopmentKeywordPanelSeleniumTest;
import org.kuali.kra.proposaldevelopment.web.ProposalDevelopmentSpecialReviewSeleniumTest;
import org.kuali.kra.proposaldevelopment.web.ProposalDevelopmentSponsorProgramInformationPanelSeleniumTest;
import org.kuali.kra.proposaldevelopment.web.ProposalDevelopmentYnqSeleniumTest;

@RunWith(Suite.class)
@SuiteClasses({
    SeleniumUnitTest.class,
    ActivityTypeMaintenanceDocumentSeleniumTest.class,
    AwardAttachmentsPanelSeleniumTest.class,
    AwardBudgetBasicSeleniumTest.class,
    AwardCommentsPanelSeleniumTest.class,
    AwardCompleteSeleniumTest.class,
    AwardDataValidationPanelSeleniumTest.class,
    AwardDetailsAndDatesPanelSeleniumTest.class,
    AwardDirectFandADistributionPanelSeleniumTest.class,
    AwardReportsPanelSeleniumTest.class,
    AwardSpecialReviewSeleniumTest.class,
    BudgetCategoryMaintenanceDocumentSeleniumTest.class,
    CommitteeActionsSeleniumTest.class,
    CommitteeAreasOfResearchPanelSeleniumTest.class,
    CommitteeAuthorizationSeleniumTest.class,
    CommitteeCommitteeSeleniumTest.class,
    CommitteeCompleteSeleniumTest.class,
    CommitteeMembershipSeleniumTest.class,
    CommitteeScheduleAddSeleniumTest.class,
    CommitteeScheduleFilterSeleniumTest.class,
    CommitteeSchedulePanelSeleniumTest.class,
    CommitteeScheduleRuleSeleniumTest.class,
    CostElementMaintenanceDocumentSeleniumTest.class,
    CustomAttributeMaintenanceDocumentSeleniumTest.class,
    InstituteLaRateMaintenanceDocumentSeleniumTest.class,
    InstituteRateMaintenanceDocumentSeleniumTest.class,
    InstitutionalProposalCompleteSeleniumTest.class,
    InstitutionalProposalContactsSeleniumTest.class,
    InstitutionalProposalDistributionSeleniumTest.class,
    InstitutionalProposalHomeSeleniumTest.class,
    InstitutionalProposalSpecialReviewSeleniumTest.class,
    OrganizationMaintenanceDocumentSeleniumTest.class,
    ProposalDevelopmentCompleteSeleniumTest.class,
    ProtocolCompleteSeleniumTest.class,
    ProposalDevelopmentAbstractsPanelSeleniumTest.class,
    ProposalDevelopmentCustomDataSeleniumTest.class,
    ProposalDevelopmentDeleteProposalSeleniumTest.class,
    ProposalDevelopmentKeyPersonnelSeleniumTest.class,
    ProposalDevelopmentKeywordPanelSeleniumTest.class,
    ProposalDevelopmentSpecialReviewSeleniumTest.class,
    ProposalDevelopmentSponsorProgramInformationPanelSeleniumTest.class,
    ProposalDevelopmentYnqSeleniumTest.class,
    SponsorFormMaintenanceDocumentSeleniumTest.class,
    ValidCalcTypeMaintenanceDocumentSeleniumTest.class,
    FinancialEntitySeleniumWebTest.class
})
public class IntegrationSuite {}