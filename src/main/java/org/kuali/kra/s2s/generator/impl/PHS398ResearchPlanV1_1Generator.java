/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.s2s.generator.impl;


import gov.grants.apply.forms.phs398ResearchPlanV11.PHS398ResearchPlanDocument;
import gov.grants.apply.forms.phs398ResearchPlanV11.PHS398ResearchPlanDocument.PHS398ResearchPlan;
import gov.grants.apply.forms.phs398ResearchPlanV11.PHS398ResearchPlanDocument.PHS398ResearchPlan.ApplicationType;
import gov.grants.apply.forms.phs398ResearchPlanV11.PHS398ResearchPlanDocument.PHS398ResearchPlan.ResearchPlanAttachments;
import gov.grants.apply.forms.phs398ResearchPlanV11.PHS398ResearchPlanDocument.PHS398ResearchPlan.ApplicationType.TypeOfApplication;
import gov.grants.apply.forms.phs398ResearchPlanV11.PHS398ResearchPlanDocument.PHS398ResearchPlan.ResearchPlanAttachments.BackgroundSignificance;
import gov.grants.apply.forms.phs398ResearchPlanV11.PHS398ResearchPlanDocument.PHS398ResearchPlan.ResearchPlanAttachments.HumanSubjectSection;
import gov.grants.apply.forms.phs398ResearchPlanV11.PHS398ResearchPlanDocument.PHS398ResearchPlan.ResearchPlanAttachments.InclusionEnrollmentReport;
import gov.grants.apply.forms.phs398ResearchPlanV11.PHS398ResearchPlanDocument.PHS398ResearchPlan.ResearchPlanAttachments.IntroductionToApplication;
import gov.grants.apply.forms.phs398ResearchPlanV11.PHS398ResearchPlanDocument.PHS398ResearchPlan.ResearchPlanAttachments.OtherResearchPlanSections;
import gov.grants.apply.forms.phs398ResearchPlanV11.PHS398ResearchPlanDocument.PHS398ResearchPlan.ResearchPlanAttachments.ProgressReport;
import gov.grants.apply.forms.phs398ResearchPlanV11.PHS398ResearchPlanDocument.PHS398ResearchPlan.ResearchPlanAttachments.ProgressReportPublicationList;
import gov.grants.apply.forms.phs398ResearchPlanV11.PHS398ResearchPlanDocument.PHS398ResearchPlan.ResearchPlanAttachments.ResearchDesignMethods;
import gov.grants.apply.forms.phs398ResearchPlanV11.PHS398ResearchPlanDocument.PHS398ResearchPlan.ResearchPlanAttachments.SpecificAims;
import gov.grants.apply.forms.phs398ResearchPlanV11.PHS398ResearchPlanDocument.PHS398ResearchPlan.ResearchPlanAttachments.HumanSubjectSection.InclusionOfChildren;
import gov.grants.apply.forms.phs398ResearchPlanV11.PHS398ResearchPlanDocument.PHS398ResearchPlan.ResearchPlanAttachments.HumanSubjectSection.InclusionOfWomenAndMinorities;
import gov.grants.apply.forms.phs398ResearchPlanV11.PHS398ResearchPlanDocument.PHS398ResearchPlan.ResearchPlanAttachments.HumanSubjectSection.ProtectionOfHumanSubjects;
import gov.grants.apply.forms.phs398ResearchPlanV11.PHS398ResearchPlanDocument.PHS398ResearchPlan.ResearchPlanAttachments.HumanSubjectSection.TargetedPlannedEnrollmentTable;
import gov.grants.apply.forms.phs398ResearchPlanV11.PHS398ResearchPlanDocument.PHS398ResearchPlan.ResearchPlanAttachments.OtherResearchPlanSections.ConsortiumContractualArrangements;
import gov.grants.apply.forms.phs398ResearchPlanV11.PHS398ResearchPlanDocument.PHS398ResearchPlan.ResearchPlanAttachments.OtherResearchPlanSections.LettersOfSupport;
import gov.grants.apply.forms.phs398ResearchPlanV11.PHS398ResearchPlanDocument.PHS398ResearchPlan.ResearchPlanAttachments.OtherResearchPlanSections.MultiplePILeadershipPlan;
import gov.grants.apply.forms.phs398ResearchPlanV11.PHS398ResearchPlanDocument.PHS398ResearchPlan.ResearchPlanAttachments.OtherResearchPlanSections.ResourceSharingPlans;
import gov.grants.apply.forms.phs398ResearchPlanV11.PHS398ResearchPlanDocument.PHS398ResearchPlan.ResearchPlanAttachments.OtherResearchPlanSections.SelectAgentResearch;
import gov.grants.apply.forms.phs398ResearchPlanV11.PHS398ResearchPlanDocument.PHS398ResearchPlan.ResearchPlanAttachments.OtherResearchPlanSections.VertebrateAnimals;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.attachmentsV10.AttachmentGroupMin0Max100DataType;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.util.S2SConstants;

/**
 * Class for generating the XML object for grants.gov PHS398ResearchPlanV1_1. Form is generated using XMLBean classes and is based
 * on PHS398ResearchPlanV1_1 schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class PHS398ResearchPlanV1_1Generator extends PHS398ResearchPlanBaseGenerator {


    /**
     * 
     * This method gives the list of attachments for PHS398ResearchPlan form
     * 
     * @return phsResearchPlanDocument {@link XmlObject} of type PHS398ResearchPlanDocument.
     */
    private PHS398ResearchPlanDocument getPHS398ResearchPlan() {

        PHS398ResearchPlanDocument phsResearchPlanDocument = PHS398ResearchPlanDocument.Factory.newInstance();
        PHS398ResearchPlan phsResearchPlan = PHS398ResearchPlan.Factory.newInstance();
        phsResearchPlan.setFormVersion(S2SConstants.FORMVERSION_1_1);

        phsResearchPlan.setApplicationType(getApplicationType());
        ResearchPlanAttachments researchPlanAttachments = ResearchPlanAttachments.Factory.newInstance();
        HumanSubjectSection humanSubjectSection = HumanSubjectSection.Factory.newInstance();
        OtherResearchPlanSections otherResearchPlanSections = OtherResearchPlanSections.Factory.newInstance();
        for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeTypeCode() != null) {
                if (Integer.parseInt(narrative.getNarrativeTypeCode()) == INTRODUCTION_TO_APPLICATION) {
                    IntroductionToApplication introductionToApplication = IntroductionToApplication.Factory.newInstance();
                    AttachedFileDataType attachedFileDataType = AttachedFileDataType.Factory.newInstance();
                    attachedFileDataType = getAttachedFileType(narrative);
                    introductionToApplication.setAttFile(attachedFileDataType);
                    researchPlanAttachments.setIntroductionToApplication(introductionToApplication);
                }
                if (Integer.parseInt(narrative.getNarrativeTypeCode()) == SPECIFIC_AIMS) {
                    SpecificAims specificAims = SpecificAims.Factory.newInstance();
                    AttachedFileDataType attachedFileDataType = AttachedFileDataType.Factory.newInstance();
                    attachedFileDataType = getAttachedFileType(narrative);
                    specificAims.setAttFile(attachedFileDataType);
                    researchPlanAttachments.setSpecificAims(specificAims);
                }
                if (Integer.parseInt(narrative.getNarrativeTypeCode()) == BACKGROUND_SIGNIFICANCE) {
                    BackgroundSignificance backgroundSignificance = BackgroundSignificance.Factory.newInstance();
                    AttachedFileDataType attachedFileDataType = AttachedFileDataType.Factory.newInstance();
                    attachedFileDataType = getAttachedFileType(narrative);
                    backgroundSignificance.setAttFile(attachedFileDataType);
                    researchPlanAttachments.setBackgroundSignificance(backgroundSignificance);
                }
                if (Integer.parseInt(narrative.getNarrativeTypeCode()) == PROGRESS_REPORT) {
                    ProgressReport progressReport = ProgressReport.Factory.newInstance();
                    AttachedFileDataType attachedFileDataType = AttachedFileDataType.Factory.newInstance();
                    attachedFileDataType = getAttachedFileType(narrative);
                    progressReport.setAttFile(attachedFileDataType);
                    researchPlanAttachments.setProgressReport(progressReport);
                }
                if (Integer.parseInt(narrative.getNarrativeTypeCode()) == RESEARCH_DESIGN_METHODS) {
                    ResearchDesignMethods researchDesignMethods = ResearchDesignMethods.Factory.newInstance();
                    AttachedFileDataType attachedFileDataType = AttachedFileDataType.Factory.newInstance();
                    attachedFileDataType = getAttachedFileType(narrative);
                    researchDesignMethods.setAttFile(attachedFileDataType);
                    researchPlanAttachments.setResearchDesignMethods(researchDesignMethods);
                }
                if (Integer.parseInt(narrative.getNarrativeTypeCode()) == INCLUSION_ENROLLMENT_REPORT) {
                    InclusionEnrollmentReport inclusionEnrollmentReport = InclusionEnrollmentReport.Factory.newInstance();
                    AttachedFileDataType attachedFileDataType = AttachedFileDataType.Factory.newInstance();
                    attachedFileDataType = getAttachedFileType(narrative);
                    inclusionEnrollmentReport.setAttFile(attachedFileDataType);
                    researchPlanAttachments.setInclusionEnrollmentReport(inclusionEnrollmentReport);
                }
                if (Integer.parseInt(narrative.getNarrativeTypeCode()) == PROGRESS_REPORT_PUBLICATION_LIST) {
                    ProgressReportPublicationList progressReportPublicationList = ProgressReportPublicationList.Factory
                            .newInstance();
                    AttachedFileDataType attachedFileDataType = AttachedFileDataType.Factory.newInstance();
                    attachedFileDataType = getAttachedFileType(narrative);
                    progressReportPublicationList.setAttFile(attachedFileDataType);
                    researchPlanAttachments.setProgressReportPublicationList(progressReportPublicationList);
                }
                if (Integer.parseInt(narrative.getNarrativeTypeCode()) == PROTECTION_OF_HUMAN_SUBJECTS) {
                    ProtectionOfHumanSubjects protectionOfHumanSubjects = ProtectionOfHumanSubjects.Factory.newInstance();
                    AttachedFileDataType attachedFileDataType = AttachedFileDataType.Factory.newInstance();
                    attachedFileDataType = getAttachedFileType(narrative);
                    protectionOfHumanSubjects.setAttFile(attachedFileDataType);
                    humanSubjectSection.setProtectionOfHumanSubjects(protectionOfHumanSubjects);
                }
                if (Integer.parseInt(narrative.getNarrativeTypeCode()) == INCLUSION_OF_WOMEN_AND_MINORITIES) {
                    InclusionOfWomenAndMinorities inclusionOfWomenAndMinorities = InclusionOfWomenAndMinorities.Factory
                            .newInstance();
                    AttachedFileDataType attachedFileDataType = AttachedFileDataType.Factory.newInstance();
                    attachedFileDataType = getAttachedFileType(narrative);
                    inclusionOfWomenAndMinorities.setAttFile(attachedFileDataType);
                    humanSubjectSection.setInclusionOfWomenAndMinorities(inclusionOfWomenAndMinorities);
                }
                if (Integer.parseInt(narrative.getNarrativeTypeCode()) == TARGETED_PLANNED_ENROLLMENT_TABLE) {
                    TargetedPlannedEnrollmentTable tarPlannedEnrollmentTable = TargetedPlannedEnrollmentTable.Factory.newInstance();
                    AttachedFileDataType attachedFileDataType = AttachedFileDataType.Factory.newInstance();
                    attachedFileDataType = getAttachedFileType(narrative);
                    tarPlannedEnrollmentTable.setAttFile(attachedFileDataType);
                    humanSubjectSection.setTargetedPlannedEnrollmentTable(tarPlannedEnrollmentTable);
                }
                if (Integer.parseInt(narrative.getNarrativeTypeCode()) == INCLUSION_OF_CHILDREN) {
                    InclusionOfChildren inclusionOfChildren = InclusionOfChildren.Factory.newInstance();
                    AttachedFileDataType attachedFileDataType = AttachedFileDataType.Factory.newInstance();
                    attachedFileDataType = getAttachedFileType(narrative);
                    inclusionOfChildren.setAttFile(attachedFileDataType);
                    humanSubjectSection.setInclusionOfChildren(inclusionOfChildren);
                }
                if (Integer.parseInt(narrative.getNarrativeTypeCode()) == VERTEBRATE_ANIMALS) {
                    VertebrateAnimals vertebrateAnimals = VertebrateAnimals.Factory.newInstance();
                    AttachedFileDataType attachedFileDataType = AttachedFileDataType.Factory.newInstance();
                    attachedFileDataType = getAttachedFileType(narrative);
                    vertebrateAnimals.setAttFile(attachedFileDataType);
                    otherResearchPlanSections.setVertebrateAnimals(vertebrateAnimals);
                }
                if (Integer.parseInt(narrative.getNarrativeTypeCode()) == SELECT_AGENT_RESEARCH) {
                    SelectAgentResearch selectAgentResearch = SelectAgentResearch.Factory.newInstance();
                    AttachedFileDataType attachedFileDataType = AttachedFileDataType.Factory.newInstance();
                    attachedFileDataType = getAttachedFileType(narrative);
                    selectAgentResearch.setAttFile(attachedFileDataType);
                    otherResearchPlanSections.setSelectAgentResearch(selectAgentResearch);
                }
                if (Integer.parseInt(narrative.getNarrativeTypeCode()) == MULTIPLE_PI_LEADERSHIP_PLAN) {
                    MultiplePILeadershipPlan multiplePILeadershipPlan = MultiplePILeadershipPlan.Factory.newInstance();
                    AttachedFileDataType attachedFileDataType = AttachedFileDataType.Factory.newInstance();
                    attachedFileDataType = getAttachedFileType(narrative);
                    multiplePILeadershipPlan.setAttFile(attachedFileDataType);
                    otherResearchPlanSections.setMultiplePILeadershipPlan(multiplePILeadershipPlan);
                }
                if (Integer.parseInt(narrative.getNarrativeTypeCode()) == CONSORTIUM_CONTRACTUAL_ARRANGEMENTS) {
                    ConsortiumContractualArrangements contractualArrangements = ConsortiumContractualArrangements.Factory
                            .newInstance();
                    AttachedFileDataType attachedFileDataType = AttachedFileDataType.Factory.newInstance();
                    attachedFileDataType = getAttachedFileType(narrative);
                    contractualArrangements.setAttFile(attachedFileDataType);
                    otherResearchPlanSections.setConsortiumContractualArrangements(contractualArrangements);
                }
                if (Integer.parseInt(narrative.getNarrativeTypeCode()) == LETTERS_OF_SUPPORT) {
                    LettersOfSupport lettersOfSupport = LettersOfSupport.Factory.newInstance();
                    AttachedFileDataType attachedFileDataType = AttachedFileDataType.Factory.newInstance();
                    attachedFileDataType = getAttachedFileType(narrative);
                    lettersOfSupport.setAttFile(attachedFileDataType);
                    otherResearchPlanSections.setLettersOfSupport(lettersOfSupport);
                }
                if (Integer.parseInt(narrative.getNarrativeTypeCode()) == RESOURCE_SHARING_PLANS) {
                    ResourceSharingPlans resourceSharingPlans = ResourceSharingPlans.Factory.newInstance();
                    AttachedFileDataType attachedFileDataType = AttachedFileDataType.Factory.newInstance();
                    attachedFileDataType = getAttachedFileType(narrative);
                    resourceSharingPlans.setAttFile(attachedFileDataType);
                    otherResearchPlanSections.setResourceSharingPlans(resourceSharingPlans);
                }
            }
        }

        researchPlanAttachments.setHumanSubjectSection(humanSubjectSection);
        researchPlanAttachments.setOtherResearchPlanSections(otherResearchPlanSections);

        AttachmentGroupMin0Max100DataType attachmentGroupMin0Max100DataType = AttachmentGroupMin0Max100DataType.Factory
                .newInstance();
        attachmentGroupMin0Max100DataType.setAttachedFileArray(getAttachedFileDataTypes());
        researchPlanAttachments.setAppendix(attachmentGroupMin0Max100DataType);
        phsResearchPlan.setResearchPlanAttachments(researchPlanAttachments);
        phsResearchPlanDocument.setPHS398ResearchPlan(phsResearchPlan);
        return phsResearchPlanDocument;
    }

    /**
     * 
     * This method is used to get ApplicationType from ProposalDevelopmentDocument
     * 
     * @return ApplicationType corresponding to the proposal type code.
     */
    private ApplicationType getApplicationType() {

        ApplicationType applicationType = ApplicationType.Factory.newInstance();
        if (pdDoc.getDevelopmentProposal().getProposalTypeCode() != null && Integer.parseInt(pdDoc.getDevelopmentProposal().getProposalTypeCode()) < PROPOSAL_TYPE_CODE_6) {
            // Check <6 to ensure that if proposalType='TASk ORDER", it must not set. THis is because enum ApplicationType has no
            // entry for TASK ORDER
            TypeOfApplication.Enum typeOfApplication = TypeOfApplication.Enum.forInt(Integer.parseInt(pdDoc.getDevelopmentProposal().getProposalTypeCode()));
            applicationType.setTypeOfApplication(typeOfApplication);
        }
        return applicationType;
    }

    /**
     * 
     * This method is used to get List of appendix attachments from NarrativeAttachmentList
     * 
     * @return AttachedFileDataType[] array of attachments for the corresponding narrative type code APPENDIX.
     */
    private AttachedFileDataType[] getAttachedFileDataTypes() {
        int size = 0;
        for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeTypeCode() != null && Integer.parseInt(narrative.getNarrativeTypeCode()) == APPENDIX) {
                size++;
            }
        }
        AttachedFileDataType[] attachedFileDataTypes = new AttachedFileDataType[size];
        int attachments = 0;
        for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeTypeCode() != null && Integer.parseInt(narrative.getNarrativeTypeCode()) == APPENDIX) {
                attachedFileDataTypes[attachments] = getAttachedFileType(narrative);
                attachments++;
            }
        }
        return attachedFileDataTypes;
    }

    /**
     * This method creates {@link XmlObject} of type {@link PHS398ResearchPlanDocument} by populating data from the given
     * {@link ProposalDevelopmentDocument}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocument}
     * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(ProposalDevelopmentDocument)
     */
    public XmlObject getFormObject(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getPHS398ResearchPlan();
    }

    /**
     * This method typecasts the given {@link XmlObject} to the required generator type and returns back the document of that
     * generator type.
     * 
     * @param xmlObject which needs to be converted to the document type of the required generator
     * @return {@link XmlObject} document of the required generator type
     * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(XmlObject)
     */
    public XmlObject getFormObject(XmlObject xmlObject) {

        PHS398ResearchPlan phsResearchPlan = (PHS398ResearchPlan) xmlObject;
        PHS398ResearchPlanDocument phsResearchPlanDocument = PHS398ResearchPlanDocument.Factory.newInstance();
        phsResearchPlanDocument.setPHS398ResearchPlan(phsResearchPlan);
        return phsResearchPlanDocument;
    }
}
