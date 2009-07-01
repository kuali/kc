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

import gov.grants.apply.forms.rrOtherProjectInfoV11.RROtherProjectInfoDocument;
import gov.grants.apply.forms.rrOtherProjectInfoV11.RROtherProjectInfoDocument.RROtherProjectInfo;
import gov.grants.apply.forms.rrOtherProjectInfoV11.RROtherProjectInfoDocument.RROtherProjectInfo.AbstractAttachments;
import gov.grants.apply.forms.rrOtherProjectInfoV11.RROtherProjectInfoDocument.RROtherProjectInfo.BibliographyAttachments;
import gov.grants.apply.forms.rrOtherProjectInfoV11.RROtherProjectInfoDocument.RROtherProjectInfo.EnvironmentalImpact;
import gov.grants.apply.forms.rrOtherProjectInfoV11.RROtherProjectInfoDocument.RROtherProjectInfo.EquipmentAttachments;
import gov.grants.apply.forms.rrOtherProjectInfoV11.RROtherProjectInfoDocument.RROtherProjectInfo.FacilitiesAttachments;
import gov.grants.apply.forms.rrOtherProjectInfoV11.RROtherProjectInfoDocument.RROtherProjectInfo.HumanSubjectsSupplement;
import gov.grants.apply.forms.rrOtherProjectInfoV11.RROtherProjectInfoDocument.RROtherProjectInfo.InternationalActivities;
import gov.grants.apply.forms.rrOtherProjectInfoV11.RROtherProjectInfoDocument.RROtherProjectInfo.OtherAttachments;
import gov.grants.apply.forms.rrOtherProjectInfoV11.RROtherProjectInfoDocument.RROtherProjectInfo.ProjectNarrativeAttachments;
import gov.grants.apply.forms.rrOtherProjectInfoV11.RROtherProjectInfoDocument.RROtherProjectInfo.VertebrateAnimalsSupplement;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType.Enum;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.ProposalSpecialReview;
import org.kuali.kra.proposaldevelopment.bo.ProposalYnq;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.util.S2SConstants;

/**
 * Class for generating the XML object for grants.gov RROtherProjectInfoV1.1. Form is generated using XMLBean classes and is based
 * on RROtherProjectInfo schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class RROtherProjectInfoV1_1Generator extends RROtherProjectInfoBaseGenerator {


    private static final Logger LOG = Logger.getLogger(RROtherProjectInfoV1_1Generator.class);

    /**
     * 
     * This method gives information about RROtherProjectInfo of proposal special reviews based on the data in the proposal
     * development document.
     * 
     * @return rrOtherProjectInfoDocument(RROtherProjectInfoDocument)
     */
    private RROtherProjectInfoDocument getRROtherProjectInfo() {

        LOG.info("Inside RROtherProjectInfo");
        RROtherProjectInfoDocument rrOtherProjectInfoDocument = RROtherProjectInfoDocument.Factory.newInstance();
        RROtherProjectInfoDocument.RROtherProjectInfo rrOtherProjectInfo = RROtherProjectInfoDocument.RROtherProjectInfo.Factory
                .newInstance();
        Organization organization = pdDoc.getOrganization();
        rrOtherProjectInfo.setFormVersion(S2SConstants.FORMVERSION_1_1);

        rrOtherProjectInfo.setHumanSubjectsIndicator(YesNoDataType.N_NO);
        rrOtherProjectInfo.setVertebrateAnimalsIndicator(YesNoDataType.N_NO);
        String newDescription;
        for (ProposalSpecialReview proposalSpecialReview : pdDoc.getPropSpecialReviews()) {
            if (proposalSpecialReview.getSpecialReviewCode() != null) {
                switch (Integer.parseInt(proposalSpecialReview.getSpecialReviewCode())) {
                    case HUMAN_SUBJECT_SUPPLEMENT:
                        rrOtherProjectInfo.setHumanSubjectsIndicator(YesNoDataType.Y_YES);
                        HumanSubjectsSupplement humanSubjectsSupplement = HumanSubjectsSupplement.Factory.newInstance();
                        HumanSubjectsSupplement.ExemptionNumbers exemptionNumbers = HumanSubjectsSupplement.ExemptionNumbers.Factory
                                .newInstance();
                        newDescription = proposalSpecialReview.getComments();
                        if (proposalSpecialReview.getApprovalTypeCode() != null) {
                            if (Integer.parseInt(proposalSpecialReview.getApprovalTypeCode()) == APPROVAL_TYPE_EXCEMPT) {
                                if (newDescription != null) {
                                    String[] exemptions = newDescription.split(",");
                                    LOG.info("Gets valid Exception numbers: " + newDescription);
                                    HumanSubjectsSupplement.ExemptionNumbers.ExemptionNumber.Enum[] exceptionNumberArray = new HumanSubjectsSupplement.ExemptionNumbers.ExemptionNumber.Enum[exemptions.length];
                                    for (int exceptionNumber = 0; exceptionNumber < exemptions.length; exceptionNumber++) {
                                        HumanSubjectsSupplement.ExemptionNumbers.ExemptionNumber.Enum exceptionNumberEnum = HumanSubjectsSupplement.ExemptionNumbers.ExemptionNumber.Enum
                                                .forString(exemptions[exceptionNumber]);
                                        exceptionNumberArray[exceptionNumber] = exceptionNumberEnum;
                                    }
                                    exemptionNumbers.setExemptionNumberArray(exceptionNumberArray);
                                    humanSubjectsSupplement.setExemptionNumbers(exemptionNumbers);
                                }
                            }
                            if (SPECIAL_REVIEW_HUMAN_SUBJECTS.equals(proposalSpecialReview.getSpecialReviewCode())) {
                                humanSubjectsSupplement.setHumanSubjectIRBReviewIndicator(YesNoDataType.Y_YES);
                            }
                            else {
                                humanSubjectsSupplement.setHumanSubjectIRBReviewIndicator(YesNoDataType.N_NO);
                                if (proposalSpecialReview.getApprovalDate() != null) {
                                    humanSubjectsSupplement.setHumanSubjectIRBReviewDate(s2sUtilService
                                            .convertDateToCalendar(proposalSpecialReview.getApprovalDate()));
                                }
                            }
                        }
                        if (organization != null && organization.getHumanSubAssurance() != null) {
                            humanSubjectsSupplement
                                    .setHumanSubjectAssuranceNumber(organization.getHumanSubAssurance().substring(3));
                        }
                        if (humanSubjectsSupplement != null) {
                            rrOtherProjectInfo.setHumanSubjectsSupplement(humanSubjectsSupplement);
                        }
                        break;

                    case VERTEBRATE_ANIMALS_SUPPLEMENT:
                        rrOtherProjectInfo.setVertebrateAnimalsIndicator(YesNoDataType.Y_YES);
                        VertebrateAnimalsSupplement vertebrateAnimalsSupplement = VertebrateAnimalsSupplement.Factory.newInstance();
                        if (SPECIAL_REVIEW_ANIMAL_USAGE.equals(proposalSpecialReview.getSpecialReviewCode())) {
                            vertebrateAnimalsSupplement.setVertebrateAnimalsIACUCReviewIndicator(YesNoDataType.Y_YES);
                        }
                        else {
                            vertebrateAnimalsSupplement.setVertebrateAnimalsIACUCReviewIndicator(YesNoDataType.N_NO);
                            if (proposalSpecialReview.getApprovalDate() != null) {
                                vertebrateAnimalsSupplement.setVertebrateAnimalsIACUCApprovalDateReviewDate(s2sUtilService
                                        .convertDateToCalendar(proposalSpecialReview.getApprovalDate()));
                            }
                        }
                        if (organization != null && organization.getHumanSubAssurance() != null) {
                            vertebrateAnimalsSupplement.setAssuranceNumber(organization.getHumanSubAssurance().substring(3));
                        }

                        rrOtherProjectInfo.setVertebrateAnimalsSupplement(vertebrateAnimalsSupplement);
                        break;
                    default:
                        break;
                }// switch
            }// if
        }// for

        Enum answer = YesNoDataType.N_NO;
        String answerExplanation = " ";

        /**
         * ProprietaryInformationIndicator is of YnQ type
         */
        ProposalYnq proposalYnq = getAnswer(PROPRIETARY_INFORMATION_INDICATOR, pdDoc);
        EnvironmentalImpact environmentalImpact = EnvironmentalImpact.Factory.newInstance();
        InternationalActivities internationalActivities = InternationalActivities.Factory.newInstance();
        // Set default values for mandatory fields
        rrOtherProjectInfo.setProprietaryInformationIndicator(YesNoDataType.N_NO);
        environmentalImpact.setEnvironmentalImpactIndicator(YesNoDataType.N_NO);
        internationalActivities.setInternationalActivitiesIndicator(YesNoDataType.N_NO);
        if (proposalYnq != null) {
            answer = (S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(proposalYnq.getAnswer()) ? YesNoDataType.Y_YES : YesNoDataType.N_NO);
            rrOtherProjectInfo.setProprietaryInformationIndicator(answer);
        }
        /**
         * EnvironmentalImpact is of YnQ type
         */
        proposalYnq = getAnswer(ENVIRONMENTAL_IMPACT_YNQ, pdDoc);

        if (proposalYnq != null) {
            answer = (S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(proposalYnq.getAnswer()) ? YesNoDataType.Y_YES : YesNoDataType.N_NO);
            answerExplanation = proposalYnq.getExplanation();
            environmentalImpact.setEnvironmentalImpactIndicator(answer);
            if (answerExplanation != null) {
                environmentalImpact.setEnvironmentalImpactExplanation(answerExplanation);
            }
        }
        proposalYnq = getAnswer(ENVIRONMENTAL_EXEMPTION_YNQ, pdDoc);
        if (proposalYnq != null) {
            answerExplanation = proposalYnq.getExplanation();
            String ynqAnswer = proposalYnq.getAnswer();
            if (S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(ynqAnswer)) {
                answer = YesNoDataType.Y_YES;
            }
            else {
                answer = YesNoDataType.N_NO;
            }

            if (!S2SConstants.PROPOSAL_YNQ_ANSWER_NA.equals(ynqAnswer)) {
                // Answer not equal to X (not-applicable)
                EnvironmentalImpact.EnvironmentalExemption environmentalExemption = EnvironmentalImpact.EnvironmentalExemption.Factory
                        .newInstance();
                environmentalExemption.setEnvironmentalExemptionIndicator(answer);

                environmentalExemption.setEnvironmentalExemptionExplanation(answerExplanation);
                environmentalImpact.setEnvironmentalExemption(environmentalExemption);

            }

        }
        rrOtherProjectInfo.setEnvironmentalImpact(environmentalImpact);

        /**
         * InternationalActivities is of YnQ type
         */

        proposalYnq = getAnswer(INTERNATIONAL_ACTIVITIES_YNQ, pdDoc);
        if (proposalYnq != null) {
            answer = (S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(proposalYnq.getAnswer()) ? YesNoDataType.Y_YES : YesNoDataType.N_NO);
            answerExplanation = proposalYnq.getExplanation();
            internationalActivities.setInternationalActivitiesIndicator(answer);
            if (answerExplanation != null) {
                internationalActivities.setInternationalActivitiesIndicator(answer);
                internationalActivities.setActivitiesPartnershipsCountries(answerExplanation);
            }
        }
        rrOtherProjectInfo.setInternationalActivities(internationalActivities);
        /**
         * Attachments
         */
        for (Narrative narrative : pdDoc.getNarratives()) {
            if (narrative.getNarrativeTypeCode() != null) {
                if (Integer.parseInt(narrative.getNarrativeTypeCode()) == EQUIPMENT_ATTACHMENT) {

                    EquipmentAttachments equipmentAttachments = EquipmentAttachments.Factory.newInstance();
                    equipmentAttachments.setEquipmentAttachment(getAttachedFileType(narrative));
                    rrOtherProjectInfo.setEquipmentAttachments(equipmentAttachments);
                }
                else if (Integer.parseInt(narrative.getNarrativeTypeCode()) == FACILITIES_ATTACHMENT) {

                    FacilitiesAttachments facilitiesAttachments = FacilitiesAttachments.Factory.newInstance();
                    facilitiesAttachments.setFacilitiesAttachment(getAttachedFileType(narrative));
                    rrOtherProjectInfo.setFacilitiesAttachments(facilitiesAttachments);
                }
                else if (Integer.parseInt(narrative.getNarrativeTypeCode()) == NARRATIVE_ATTACHMENT) {

                    ProjectNarrativeAttachments projectNarrativeAttachments = ProjectNarrativeAttachments.Factory.newInstance();
                    projectNarrativeAttachments.setProjectNarrativeAttachment(getAttachedFileType(narrative));
                    rrOtherProjectInfo.setProjectNarrativeAttachments(projectNarrativeAttachments);
                }
                else if (Integer.parseInt(narrative.getNarrativeTypeCode()) == BIBLIOGRAPHY_ATTACHMENT) {

                    BibliographyAttachments bibliographyAttachments = BibliographyAttachments.Factory.newInstance();
                    bibliographyAttachments.setBibliographyAttachment(getAttachedFileType(narrative));
                    rrOtherProjectInfo.setBibliographyAttachments(bibliographyAttachments);
                }
                else if (Integer.parseInt(narrative.getNarrativeTypeCode()) == ABSTRACT_PROJECT_SUMMARY_ATTACHMENT) {

                    AbstractAttachments abstractAttachments = AbstractAttachments.Factory.newInstance();
                    abstractAttachments.setAbstractAttachment(getAttachedFileType(narrative));
                    rrOtherProjectInfo.setAbstractAttachments(abstractAttachments);
                }
                else if (Integer.parseInt(narrative.getNarrativeTypeCode()) == OTHER_ATTACHMENT
                        || Integer.parseInt(narrative.getNarrativeTypeCode()) == SUPPLIMENTARY_ATTACHMENT) {
                    OtherAttachments otherAttachments = OtherAttachments.Factory.newInstance();
                    otherAttachments.setOtherAttachmentArray(getAttachedFileDataTypes());
                    rrOtherProjectInfo.setOtherAttachments(otherAttachments);
                }
            }
        }
        rrOtherProjectInfoDocument.setRROtherProjectInfo(rrOtherProjectInfo);
        return rrOtherProjectInfoDocument;
    }

    /**
     * 
     * This method is used to get the answer for ProposalYnq
     * 
     * @param questionId
     * @return proposalYnq
     */
    private ProposalYnq getAnswer(String questionId, ProposalDevelopmentDocument pdDoc) {
        String question;
        ProposalYnq ynQ = null;
        for (ProposalYnq proposalYnq : pdDoc.getProposalYnqs()) {
            question = proposalYnq.getQuestionId();
            if (question != null && question.equals(questionId)) {
                ynQ =  proposalYnq;
                break;
            }
        }
        return ynQ;
    }

    /**
     * 
     * This method is used to get List of attachments from NarrativeAttachmentList
     * 
     * @return attachedFileDataTypes(AttachedFileDataType[])
     */
    private AttachedFileDataType[] getAttachedFileDataTypes() {
        LOG.info("Getting AttachedFileDataType ");
        int size = 0;
        for (Narrative narrative : pdDoc.getNarratives()) {
            if (narrative.getNarrativeTypeCode() != null
                    && (Integer.parseInt(narrative.getNarrativeTypeCode()) == OTHER_ATTACHMENT || Integer.parseInt(narrative
                            .getNarrativeTypeCode()) == SUPPLIMENTARY_ATTACHMENT)) {
                size++;
            }
        }
        AttachedFileDataType[] attachedFileDataTypes = new AttachedFileDataType[size];
        int attachments = 0;
        for (Narrative narrative : pdDoc.getNarratives()) {
            if (narrative.getNarrativeTypeCode() != null
                    && (Integer.parseInt(narrative.getNarrativeTypeCode()) == OTHER_ATTACHMENT || Integer.parseInt(narrative
                            .getNarrativeTypeCode()) == SUPPLIMENTARY_ATTACHMENT)) {
                attachedFileDataTypes[attachments] = getAttachedFileType(narrative);
                attachments++;
                LOG.info("Attachmentcount" + attachments);
            }
        }
        return attachedFileDataTypes;
    }

    /**
     * This method creates {@link XmlObject} of type {@link RROtherProjectInfoDocument} by populating data from the given
     * {@link ProposalDevelopmentDocument}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocument}
     * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(ProposalDevelopmentDocument)
     */
    public XmlObject getFormObject(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getRROtherProjectInfo();
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

        RROtherProjectInfo rrOtherProjectInfo = (RROtherProjectInfo) xmlObject;
        RROtherProjectInfoDocument rrOtherProjectInfoDocument = RROtherProjectInfoDocument.Factory.newInstance();
        rrOtherProjectInfoDocument.setRROtherProjectInfo(rrOtherProjectInfo);
        return rrOtherProjectInfoDocument;
    }
}
