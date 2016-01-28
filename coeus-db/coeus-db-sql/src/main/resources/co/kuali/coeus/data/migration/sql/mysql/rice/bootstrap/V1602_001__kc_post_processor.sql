--
-- Kuali Coeus, a comprehensive research administration system for higher education.
--
-- Copyright 2005-2015 Kuali, Inc.
--
-- This program is free software: you can redistribute it and/or modify
-- it under the terms of the GNU Affero General Public License as
-- published by the Free Software Foundation, either version 3 of the
-- License, or (at your option) any later version.
--
-- This program is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
-- GNU Affero General Public License for more details.
--
-- You should have received a copy of the GNU Affero General Public License
-- along with this program.  If not, see <http://www.gnu.org/licenses/>.
--

update krew_doc_typ_t set post_prcsr = 'org.kuali.coeus.sys.framework.workflow.KcPostProcessor'
where DOC_TYP_NM in ('KC', 'AwardBudgetDocument', 'AwardDocument', 'CoiDisclosureDocument', 'CommitteeDocument', 'CommonCommitteeDocument',
                           'IacucProtocolDocument', 'IacucProtocolOnlineReviewDocument', 'InstitutionalProposalDocument', 'KcMaintenanceDocument',
                           'NegotiationDocument', 'PersonMassChangeDocument', 'ProposalDevelopmentDocument', 'ProtocolDocument',
                           'ProtocolOnlineReviewDocument', 'SubAwardDocument', 'TimeAndMoneyDocument', 'Award Transaction Type Maintenance Document',
                           'BudgetColumnsToAlterMaintenanceDocument', 'CarrierTypeMaintenanceDocument', 'ExemptionTypeMaintenanceDocument',
                           'KcAwardsMaintenanceDocument', 'KcComplianceMaintenanceDocument', 'KcMiscellaneousMaintenanceDocument',
                           'KcNegotiationsMaintenanceDocument', 'KcPersonExtendedAttributesMaintenanceDocument', 'KcProposalsMaintenanceDocument',
                           'KcSharedMaintenanceDocument', 'PersonAppointmentMaintenanceDocument', 'PersonDegreeMaintenanceDocument',
                           'PersonEditableFieldMaintenanceDocument', 'PropLocationMaintenanceDocument', 'ProposalColumnsToAlterMaintenanceDocument',
                           'ProposalLogMaintenanceDocument', 'ProposalRoleTemplateMaintenanceDocument', 'PropPerDocTypeMaintenanceDocument',
                           'RiskLevelMaintenanceDocument', 'SponsorTermMaintenanceDocument', 'SponsorTermTypeMaintenanceDocument', 'TbnPersonMaintenanceDocument',
                           'UnitMaintenanceDocument', 'AbstractTypeMaintenanceDocument', 'AccountTypeMaintenanceDocument', 'ActiveMqMessageMaintenanceDocument',
                           'ActivityTypeMaintenanceDocument', 'KcAffiliationTypeMaintenanceDocument', 'AppointmentTypeMaintenanceDocument',
                           'ArgValueLookupMaintenanceDocument', 'AttachmentsEntryTypeMaintenanceDocument', 'AwardAttachmentTypeMaintenanceDocument',
                           'AwardBasisOfPaymentMaintenanceDocument', 'AwardMethodOfPaymentMaintenanceDocument', 'AwardStatusMaintenanceDocument',
                           'AwardTemplateCommentMaintenanceDocument', 'AwardTemplateContactMaintenanceDocument', 'AwardTemplateMaintenanceDocument',
                           'AwardTemplateReportTermMaintenanceDocument', 'AwardTemplateReportTermRecipientMaintenanceDocument', 'AwardTemplateTermMaintenanceDocument',
                           'AwardTypeMaintenanceDocument', 'BatchCorrespondenceDetailMaintenanceDocument', 'BatchCorrespondenceMaintenanceDocument',
                           'BudgetCategoryMaintenanceDocument', 'BudgetCategoryMappingMaintenanceDocument', 'BudgetCategoryMapMaintenanceDocument',
                           'BudgetCategoryTypeMaintenanceDocument', 'BudgetStatusMaintenanceDocument', 'CFDAMaintenanceDocument', 'CitizenshipTypeMaintenanceDocument',
                           'CloseoutReportTypeMaintenanceDocument', 'CloseoutTypeMaintenanceDocument', 'CoeusModuleMaintenanceDocument', 'Coeus Sub Module Maintenance Document',
                           'CoiAttachmentTypeMaintenanceDocument', 'CoiCommitteeRoleTypeMaintenanceDocument', 'CoiDisclosureEventTypeMaintenanceDocument',
                           'CoiDisclosureStatusMaintenanceDocument', 'CoiDispositionStatusMaintenanceDocument', 'CoiNoteTypeMaintenanceDocument',
                           'CoiRecomendedActionTypeMaintenanceDocument', 'CoiReviewerMaintenanceDocument', 'CoiReviewStatusMaintenanceDocument',
                           'CoiReviewTypeMaintenanceDocument', 'CoiStatusMaintenanceDocument', 'CommentTypeMaintenanceDocument', 'CommitteeDecisionMotionTypeMaintenanceDocument',
                           'CommitteeMembershipTypeMaintenanceDocument', 'CommitteeTypeMaintenanceDocument', 'ContactTypeMaintenanceDocument', 'ContactUsageMaintenanceDocument',
                           'CorrespondentTypeMaintenanceDocument', 'CostElementMaintenanceDocument', 'CostShareTypeMaintenanceDocument', 'CustomAttributeDocumentMaintenanceDocument',
                           'CustomAttributeMaintenanceDocument', 'CustReportDetailsMaintenanceDocument', 'CustReportTypeMaintenanceDocument', 'CustRptDefaultParmsMaintenanceDocument',
                           'CustRptTypeDocumentMaintenanceDocument', 'DeadlineTypeMaintenanceDocument', 'DegreeTypeMaintenanceDocument', 'DistributionMaintenanceDocument',
                           'DocumentAccessMaintenanceDocument', 'ExemptStudiesCheckListMaintenanceDocument', 'ExpeditedReviewCheckListMaintenanceDocument',
                           'FandaRateTypeMaintenanceDocument', 'FinancialIndirectCostRecoveryTypeCodeMaintenanceDocument', 'FinancialObjectCodeMappingMaintenanceDocument',
                           'FinEntitiesDataGroupMaintenanceDocument', 'FinEntitiesDataMatrixMaintenanceDocument', 'FinIntEntityRelTypeMaintenanceDocument',
                           'FinIntEntityStatusMaintenanceDocument', 'FormulatedTypeMaintenanceDocument', 'FrequencyBaseMaintenanceDocument', 'FrequencyMaintenanceDocument',
                           'FundingSourceTypeMaintenanceDocument', 'IacucAlternateSearchDatabaseMaintenanceDocument', 'IacucCorrespondentTypeMaintenanceDocument',
                           'IacucExceptionCategoryMaintenanceDocument', 'IacucLocationNameMaintenanceDocument', 'IacucLocationTypeMaintenanceDocument',
                           'IacucOrganizationCorrespondentMaintenanceDocument', 'IacucPainCategoryMaintenanceDocument', 'IacucPersonTrainingMaintenanceDocument',
                           'IacucProcedureCategoryCustomDataMaintenanceDocument', 'IacucProcedureCategoryMaintenanceDocument', 'IacucProcedureMaintenanceDocument',
                           'IacucProtocolActionTypeMaintenanceDocument', 'IacucProtocolAffiliationTypeMaintenanceDocument', 'IacucProtocolAttachmentGroupMaintenanceDocument',
                           'IacucProtocolAttachmentStatusMaintenanceDocument', 'IacucProtocolAttachmentTypeGroupMaintenanceDocument', 'IacucProtocolAttachmentTypeMaintenanceDocument',
                           'IacucProtocolContingencyMaintenanceDocument', 'IacucProtocolCorrespondenceTemplateMaintenanceDocument', 'IacucProtocolCorrespondenceTypeMaintenanceDocument',
                           'IacucProtocolOnlineReviewDeterminRecommendMaintenanceDocument', 'IacucProtocolOnlineReviewDeterminTypeRecommendMaintDocument',
                           'IacucProtocolOnlineReviewStatusMaintenanceDocument', 'IacucProtocolOrganizationTypeMaintenanceDocument', 'IacucProtocolPersonRoleMaintenanceDocument',
                           'IacucProtocolProjectTypeMaintenanceDocument', 'IacucProtocolReferenceTypeMaintenanceDocument', 'IacucProtocolReviewerTypeMaintenanceDocument',
                           'IacucProtocolReviewTypeMaintenanceDocument', 'IacucProtocolStatusMaintenanceDocument', 'IacucProtocolSubmissionQualifierTypeMaintenanceDocument',
                           'IacucProtocolSubmissionStatusMaintenanceDocument', 'IacucProtocolSubmissionTypeMaintenanceDocument', 'IacucProtocolTypeMaintenanceDocument',
                           'IacucResearchAreasMaintenanceDocument', 'IacucSpeciesCountTypeMaintenanceDocument', 'IacucSpeciesMaintenanceDocument', 'IacucUnitCorrespondentMaintenanceDocument',
                           'IacucValidProtocolActionActionMaintenanceDocument', 'IacucValidProtoSubRevTypeMaintenanceDocument', 'IacucValidProtoSubTypeQualMaintenanceDocument',
                           'InstituteLaRateMaintenanceDocument', 'InstituteRateMaintenanceDocument', 'InstitutionalProposalAttachmentTypeMaintenanceDocument',
                           'IntellectualPropertyReviewActivityTypeMaintenanceDocument', 'IntellectualPropertyReviewMaintenanceDocument', 'IntellectualPropertyReviewRequirementTypeMaintenanceDocument',
                           'IntellectualPropertyReviewResultTypeMaintenanceDocument', 'InvestigatorCreditTypeMaintenanceDocument', 'JobCodeMaintenanceDocument',
                           'KcKrmsTermFunctionMaintenanceDocument', 'KcKrmsTermFunParamSpecMaintenanceDocument', 'MailByMaintenanceDocument', 'MailTypeMaintenanceDocument',
                           'MembershipRoleMaintenanceDocument', 'MessageOfTheDayMaintenanceDocument', 'MinuteEntryTypeMaintenanceDocument', 'NarrativeStatusMaintenanceDocument',
                           'NarrativeTypeMaintenanceDocument', 'NegotiationActivityTypeMaintenanceDocument', 'NegotiationAgreementTypeMaintenanceDocument',
                           'NegotiationAssociationTypeMaintenanceDocument', 'NegotiationLocationMaintenanceDocument', 'NegotiationStatusMaintenanceDocument',
                           'NoticeOfOpportunityMaintenanceDocument', 'NotificationModuleRoleMaintenanceDocument', 'NotificationTypeMaintenanceDocument', 'NsfCodeMaintenanceDocument',
                           'OrganizationAuditAcceptedTypeMaintenanceDocument', 'OrganizationCorrespondentMaintenanceDocument', 'OrganizationMaintenanceDocument',
                           'OrganizationTypeListMaintenanceDocument', 'OrganizationTypeMaintenanceDocument', 'ParticipantTypeMaintenanceDocument', 'PersonMaintenanceDocument',
                           'PersonSignatureMaintenanceDocument', 'PersonSignatureModuleMaintenanceDocument', 'PersonTrainingMaintenanceDocument', 'PostSubmissionStatusMaintenanceDocument',
                           'PropAwardPersonRoleMaintenanceDocument', 'ProposalLocationTypeMaintenanceDocument', 'Proposal Log Status Maintenance Document', 'Proposal Log Type Maintenance Document',
                           'Proposal Status Maintenance Document', 'ProposalTypeMaintenanceDocument', 'ProtocolActionTypeMaintenanceDocument', 'ProtocolAttachmentGroupMaintenanceDocument',
                           'ProtocolAttachmentStatusMaintenanceDocument', 'ProtocolAttachmentTypeGroupMaintenanceDocument', 'ProtocolAttachmentTypeMaintenanceDocument',
                           'ProtocolContingencyMaintenanceDocument', 'ProtocolCorrespondenceTemplateMaintenanceDocument', 'ProtocolCorrespondenceTypeMaintenanceDocument',
                           'ProtocolNotificationTemplateMaintenanceDocument', 'ProtocolOnlineReviewDeterminRecommendMaintenanceDocument', 'ProtocolOnlineReviewStatusMaintenanceDocument',
                           'Protocol Organization Type Maintenance Document', 'ProtocolPersonRoleMaintenanceDocument', 'ProtocolReferenceTypeMaintenanceDocument', 'ProtocolReviewerTypeMaintenanceDocument',
                           'ProtocolReviewTypeMaintenanceDocument', 'ProtocolStatusMaintenanceDocument', 'ProtocolSubmissionQualifierTypeMaintenanceDocument', 'ProtocolSubmissionStatusMaintenanceDocument',
                           'ProtocolSubmissionTypeMaintenanceDocument', 'ProtocolTypeMaintenanceDocument', 'QuestionCategoryMaintenanceDocument', 'QuestionMaintenanceDocument',
                           'QuestionnaireMaintenanceDocument', 'Questionnaire Questions Maintenance Document', 'Questionnaire Usage Maintenance Document', 'QuestionTypeMaintenanceDocument',
                           'RateClassBaseExclusionMaintenanceDocument', 'RateClassBaseInclusionMaintenanceDocument', 'RateClassMaintenanceDocument', 'RateClassTypeMaintenanceDocument',
                           'RateTypeMaintenanceDocument', 'ReportClassMaintenanceDocument', 'ReportMaintenanceDocument', 'ReportStatusMaintenanceDocument',
                           'ResearchAreasMaintenanceDocument', 'RolodexMaintenanceDocument', 'S2sErrorMaintenanceDocument', 'S2sOppFormQuestionnaireMaintenanceDocument',
                           'S2sProviderMaintenanceDocument', 'S2sRevisionTypeMaintenanceDocument', 'S2sSubmissionTypeMaintenanceDocument', 'ScheduleActItemTypeMaintenanceDocument',
                           'ScheduleStatusMaintenanceDocument', 'SchoolCodeMaintenanceDocument', 'ScienceKeywordMaintenanceDocument', 'SpecialReviewApprovalTypeMaintenanceDocument',
                           'SpecialReviewTypeMaintenanceDocument', 'SpecialReviewUsageMaintenanceDocument', 'SponsorFormsMaintenanceDocument', 'SponsorFormTemplateMaintenanceDocument',
                           'SponsorHierarchyMaintenanceDocument', 'SponsorMaintenanceDocument', 'SponsorTypeMaintenanceDocument', 'SubAwardApprovalTypeMaintenanceDocument',
                           'SubAwardAttachmentTypeMaintenanceDocument', 'SubAwardCopyRightsTypeMaintenanceDocument', 'SubAwardCostTypeMaintenanceDocument', 'SubAwardFormsMaintenanceDocument',
                           'SubAwardInvoiceMaintenanceDocument', 'SubAwardReportTypeMaintenanceDocument', 'SubAwardStatusMaintenanceDocument', 'SubAwardTemplateTypeMaintenanceDocument',
                           'TrainingMaintenanceDocument', 'TrainingStipendRateMaintenanceDocument', 'UnitAdministratorMaintenanceDocument', 'UnitAdministratorTypeMaintenanceDocument',
                           'UnitCorrespondentMaintenanceDocument', 'UnitFormulatedCostMaintenanceDocument', 'ValidAwardBasisPaymentMaintenanceDocument', 'ValidBasisMethodPaymentMaintenanceDocument',
                           'ValidCalcTypeMaintenanceDocument', 'ValidCeJobCodeMaintenanceDocument', 'ValidCeRateTypeMaintenanceDocument', 'Valid Class Report Freq Maintenance Document',
                           'Valid Frequency Base Maintenance Document', 'ValidIacucProtocolActionCorrespondenceMaintenanceDocument', 'ValidNarrFormsMaintenanceDocument',
                           'ValidProtocolActionCorrespondenceMaintenanceDocument', 'ValidProtocolActionActionMaintenanceDocument', 'ValidProtoSubRevTypeMaintenanceDocument',
                           'ValidProtoSubTypeQualMaintenanceDocument', 'ValidRatesMaintenanceDocument', 'ValidSpecialReviewApprovalMaintenanceDocument', 'WatermarkMaintenanceDocument',
                           'YnqMaintenanceDocument'
)
 and post_prcsr = 'org.kuali.rice.krad.workflow.postprocessor.KualiPostProcessor';

INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-SYS', 'All', 'KC_POST_PROCESSOR_LEGACY_SAVE', UUID(), 1, 'CONFG', 'Y', 'Determines whether the KcPostProcessor class should use the legacy save behavior which does a full document save.', 'A', 'KC');
