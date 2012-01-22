/*
 * Copyright 2005-2010 The Kuali Foundation.
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

import gov.grants.apply.forms.phs398ResearchPlanV10.PHS398ResearchPlanDocument;
import gov.grants.apply.forms.phs398ResearchPlanV10.PHS398ResearchPlanDocument.PHS398ResearchPlan;
import gov.grants.apply.forms.phs398ResearchPlanV10.PHS398ResearchPlanDocument.PHS398ResearchPlan.ApplicationType;
import gov.grants.apply.forms.phs398ResearchPlanV10.PHS398ResearchPlanDocument.PHS398ResearchPlan.ResearchPlanAttachments;
import gov.grants.apply.forms.phs398ResearchPlanV10.PHS398ResearchPlanDocument.PHS398ResearchPlan.ApplicationType.TypeOfApplication;
import gov.grants.apply.forms.phs398ResearchPlanV10.PHS398ResearchPlanDocument.PHS398ResearchPlan.ResearchPlanAttachments.BackgroundSignificance;
import gov.grants.apply.forms.phs398ResearchPlanV10.PHS398ResearchPlanDocument.PHS398ResearchPlan.ResearchPlanAttachments.HumanSubjectSection;
import gov.grants.apply.forms.phs398ResearchPlanV10.PHS398ResearchPlanDocument.PHS398ResearchPlan.ResearchPlanAttachments.IntroductionToApplication;
import gov.grants.apply.forms.phs398ResearchPlanV10.PHS398ResearchPlanDocument.PHS398ResearchPlan.ResearchPlanAttachments.ProgressReport;
import gov.grants.apply.forms.phs398ResearchPlanV10.PHS398ResearchPlanDocument.PHS398ResearchPlan.ResearchPlanAttachments.ResearchDesignMethods;
import gov.grants.apply.forms.phs398ResearchPlanV10.PHS398ResearchPlanDocument.PHS398ResearchPlan.ResearchPlanAttachments.SpecificAims;
import gov.grants.apply.forms.phs398ResearchPlanV10.PHS398ResearchPlanDocument.PHS398ResearchPlan.ResearchPlanAttachments.HumanSubjectSection.ConsortiumContractualArrangements;
import gov.grants.apply.forms.phs398ResearchPlanV10.PHS398ResearchPlanDocument.PHS398ResearchPlan.ResearchPlanAttachments.HumanSubjectSection.DataAndSafetyMonitoringPlan;
import gov.grants.apply.forms.phs398ResearchPlanV10.PHS398ResearchPlanDocument.PHS398ResearchPlan.ResearchPlanAttachments.HumanSubjectSection.InclusionOfChildren;
import gov.grants.apply.forms.phs398ResearchPlanV10.PHS398ResearchPlanDocument.PHS398ResearchPlan.ResearchPlanAttachments.HumanSubjectSection.InclusionOfWomenAndMinorities;
import gov.grants.apply.forms.phs398ResearchPlanV10.PHS398ResearchPlanDocument.PHS398ResearchPlan.ResearchPlanAttachments.HumanSubjectSection.LettersOfSupport;
import gov.grants.apply.forms.phs398ResearchPlanV10.PHS398ResearchPlanDocument.PHS398ResearchPlan.ResearchPlanAttachments.HumanSubjectSection.ProtectionOfHumanSubjects;
import gov.grants.apply.forms.phs398ResearchPlanV10.PHS398ResearchPlanDocument.PHS398ResearchPlan.ResearchPlanAttachments.HumanSubjectSection.ResourceSharingPlans;
import gov.grants.apply.forms.phs398ResearchPlanV10.PHS398ResearchPlanDocument.PHS398ResearchPlan.ResearchPlanAttachments.HumanSubjectSection.TargetedPlannedEnrollmentTable;
import gov.grants.apply.forms.phs398ResearchPlanV10.PHS398ResearchPlanDocument.PHS398ResearchPlan.ResearchPlanAttachments.HumanSubjectSection.VertebrateAnimals;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.attachmentsV10.AttachmentGroupMin0Max100DataType;

import java.util.ArrayList;
import java.util.List;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.util.S2SConstants;

/**
 * Class for generating the XML object for grants.gov PHS398ResearchPlanV1_0. Form is generated using XMLBean classes and is based
 * on PHS398ResearchPlanV1_0 schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class PHS398ResearchPlanV1_0Generator extends PHS398ResearchPlanBaseGenerator {

    /**
     * 
     * This method gives the list of attachments for PHS398ResearchPlan form
     * 
     * @return phsResearchPlanDocument {@link XmlObject} of type PHS398ResearchPlanDocument.
     */
    private PHS398ResearchPlanDocument getPHS398ResearchPlan() {
        PHS398ResearchPlanDocument phsResearchPlanDocument = PHS398ResearchPlanDocument.Factory.newInstance();
        PHS398ResearchPlan phsResearchPlan = PHS398ResearchPlan.Factory.newInstance();

        phsResearchPlan.setFormVersion(S2SConstants.FORMVERSION_1_0);
        phsResearchPlan.setApplicationType(getApplicationType());
        ResearchPlanAttachments researchPlanAttachments = ResearchPlanAttachments.Factory.newInstance();
        HumanSubjectSection humanSubjectSection = HumanSubjectSection.Factory.newInstance();
        AttachedFileDataType attachedFileDataType = null;
        for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeTypeCode() != null) {
                if (Integer.parseInt(narrative.getNarrativeTypeCode()) == INTRODUCTION_TO_APPLICATION) {
                    attachedFileDataType = getAttachedFileType(narrative);
                    if(attachedFileDataType == null){
                        continue;
                    }
                    IntroductionToApplication introductionToApplication = IntroductionToApplication.Factory.newInstance();
                    introductionToApplication.setAttFile(attachedFileDataType);
                    researchPlanAttachments.setIntroductionToApplication(introductionToApplication);
                }
                if (Integer.parseInt(narrative.getNarrativeTypeCode()) == SPECIFIC_AIMS) {
                    attachedFileDataType = getAttachedFileType(narrative);
                    if(attachedFileDataType == null){
                        continue;
                    }
                    SpecificAims specificAims = SpecificAims.Factory.newInstance();
                    specificAims.setAttFile(attachedFileDataType);
                    researchPlanAttachments.setSpecificAims(specificAims);
                }
                if (Integer.parseInt(narrative.getNarrativeTypeCode()) == BACKGROUND_SIGNIFICANCE) {
                    attachedFileDataType = getAttachedFileType(narrative);
                    if(attachedFileDataType == null){
                        continue;
                    }
                    BackgroundSignificance backgroundSignificance = BackgroundSignificance.Factory.newInstance();
                    backgroundSignificance.setAttFile(attachedFileDataType);
                    researchPlanAttachments.setBackgroundSignificance(backgroundSignificance);
                }
                if (Integer.parseInt(narrative.getNarrativeTypeCode()) == PROGRESS_REPORT) {
                    attachedFileDataType = getAttachedFileType(narrative);
                    if(attachedFileDataType == null){
                        continue;
                    }
                    ProgressReport progressReport = ProgressReport.Factory.newInstance();
                    progressReport.setAttFile(attachedFileDataType);
                    researchPlanAttachments.setProgressReport(progressReport);
                }
                if (Integer.parseInt(narrative.getNarrativeTypeCode()) == RESEARCH_DESIGN_METHODS) {
                    attachedFileDataType = getAttachedFileType(narrative);
                    if(attachedFileDataType == null){
                        continue;
                    }
                    ResearchDesignMethods researchDesignMethods = ResearchDesignMethods.Factory.newInstance();
                    researchDesignMethods.setAttFile(attachedFileDataType);
                    researchPlanAttachments.setResearchDesignMethods(researchDesignMethods);
                }
                if (Integer.parseInt(narrative.getNarrativeTypeCode()) == PROTECTION_OF_HUMAN_SUBJECTS) {
                    attachedFileDataType = getAttachedFileType(narrative);
                    if(attachedFileDataType == null){
                        continue;
                    }
                    ProtectionOfHumanSubjects protectionOfHumanSubjects = ProtectionOfHumanSubjects.Factory.newInstance();
                    protectionOfHumanSubjects.setAttFile(attachedFileDataType);
                    humanSubjectSection.setProtectionOfHumanSubjects(protectionOfHumanSubjects);
                }
                if (Integer.parseInt(narrative.getNarrativeTypeCode()) == INCLUSION_OF_WOMEN_AND_MINORITIES) {
                    attachedFileDataType = getAttachedFileType(narrative);
                    if(attachedFileDataType == null){
                        continue;
                    }
                    InclusionOfWomenAndMinorities inclusionOfWomenAndMinorities = InclusionOfWomenAndMinorities.Factory
                            .newInstance();
                    inclusionOfWomenAndMinorities.setAttFile(attachedFileDataType);
                    humanSubjectSection.setInclusionOfWomenAndMinorities(inclusionOfWomenAndMinorities);
                }
                if (Integer.parseInt(narrative.getNarrativeTypeCode()) == TARGETED_PLANNED_ENROLLMENT_TABLE) {
                    attachedFileDataType = getAttachedFileType(narrative);
                    if(attachedFileDataType == null){
                        continue;
                    }
                    TargetedPlannedEnrollmentTable targetedPlannedEnrollmentTable = TargetedPlannedEnrollmentTable.Factory
                            .newInstance();
                    targetedPlannedEnrollmentTable.setAttFile(attachedFileDataType);
                    humanSubjectSection.setTargetedPlannedEnrollmentTable(targetedPlannedEnrollmentTable);
                }
                if (Integer.parseInt(narrative.getNarrativeTypeCode()) == INCLUSION_OF_CHILDREN) {
                    attachedFileDataType = getAttachedFileType(narrative);
                    if(attachedFileDataType == null){
                        continue;
                    }
                    InclusionOfChildren inclusionOfChildren = InclusionOfChildren.Factory.newInstance();
                    inclusionOfChildren.setAttFile(attachedFileDataType);
                    humanSubjectSection.setInclusionOfChildren(inclusionOfChildren);
                }
                if (Integer.parseInt(narrative.getNarrativeTypeCode()) == DATA_AND_SAFETY_MONITORING_PLAN) {
                    attachedFileDataType = getAttachedFileType(narrative);
                    if(attachedFileDataType == null){
                        continue;
                    }
                    DataAndSafetyMonitoringPlan dataAndSafetyMonitoringPlan = DataAndSafetyMonitoringPlan.Factory.newInstance();
                    dataAndSafetyMonitoringPlan.setAttFile(attachedFileDataType);
                    humanSubjectSection.setDataAndSafetyMonitoringPlan(dataAndSafetyMonitoringPlan);
                }
                if (Integer.parseInt(narrative.getNarrativeTypeCode()) == VERTEBRATE_ANIMALS) {
                    attachedFileDataType = getAttachedFileType(narrative);
                    if(attachedFileDataType == null){
                        continue;
                    }
                    VertebrateAnimals vertebrateAnimals = VertebrateAnimals.Factory.newInstance();
                    vertebrateAnimals.setAttFile(attachedFileDataType);
                    humanSubjectSection.setVertebrateAnimals(vertebrateAnimals);
                }
                if (Integer.parseInt(narrative.getNarrativeTypeCode()) == CONSORTIUM_CONTRACTUAL_ARRANGEMENTS) {
                    attachedFileDataType = getAttachedFileType(narrative);
                    if(attachedFileDataType == null){
                        continue;
                    }
                    ConsortiumContractualArrangements consortiumContractualArrangements = ConsortiumContractualArrangements.Factory
                            .newInstance();
                    consortiumContractualArrangements.setAttFile(attachedFileDataType);
                    humanSubjectSection.setConsortiumContractualArrangements(consortiumContractualArrangements);
                }
                if (Integer.parseInt(narrative.getNarrativeTypeCode()) == LETTERS_OF_SUPPORT) {
                    attachedFileDataType = getAttachedFileType(narrative);
                    if(attachedFileDataType == null){
                        continue;
                    }
                    LettersOfSupport lettersOfSupport = LettersOfSupport.Factory.newInstance();
                    lettersOfSupport.setAttFile(attachedFileDataType);
                    humanSubjectSection.setLettersOfSupport(lettersOfSupport);
                }
                if (Integer.parseInt(narrative.getNarrativeTypeCode()) == RESOURCE_SHARING_PLANS) {
                    attachedFileDataType = getAttachedFileType(narrative);
                    if(attachedFileDataType == null){
                        continue;
                    }
                    ResourceSharingPlans resourceSharingPlans = ResourceSharingPlans.Factory.newInstance();
                    resourceSharingPlans.setAttFile(attachedFileDataType);
                    humanSubjectSection.setResourceSharingPlans(resourceSharingPlans);
                }
            }
        }
        researchPlanAttachments.setHumanSubjectSection(humanSubjectSection);

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
            // Check < 6 to ensure that if proposalType='TASK ORDER", it must not set. THis is because enum ApplicationType has no
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
     * @return AttachedFileDataType[]  array of attachments for the corresponding narrative type code APPENDIX.
     */
    private AttachedFileDataType[] getAttachedFileDataTypes() {
        List<AttachedFileDataType> attachedFileDataTypeList = new ArrayList<AttachedFileDataType>();
        AttachedFileDataType attachedFileDataType = null;
        for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeTypeCode() != null && Integer.parseInt(narrative.getNarrativeTypeCode()) == APPENDIX) {
            	attachedFileDataType = getAttachedFileType(narrative);
            	if(attachedFileDataType != null){
            		attachedFileDataTypeList.add(attachedFileDataType);
            	}
            }
        }
        return attachedFileDataTypeList.toArray(new AttachedFileDataType[0]);
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
