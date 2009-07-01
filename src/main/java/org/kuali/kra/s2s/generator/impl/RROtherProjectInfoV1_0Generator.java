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

import gov.grants.apply.forms.rrOtherProjectInfoV10.RROtherProjectInfoDocument;
import gov.grants.apply.forms.rrOtherProjectInfoV10.RROtherProjectInfoDocument.RROtherProjectInfo;
import gov.grants.apply.forms.rrOtherProjectInfoV10.RROtherProjectInfoDocument.RROtherProjectInfo.AbstractAttachments;
import gov.grants.apply.forms.rrOtherProjectInfoV10.RROtherProjectInfoDocument.RROtherProjectInfo.BibliographyAttachments;
import gov.grants.apply.forms.rrOtherProjectInfoV10.RROtherProjectInfoDocument.RROtherProjectInfo.EnvironmentalImpact;
import gov.grants.apply.forms.rrOtherProjectInfoV10.RROtherProjectInfoDocument.RROtherProjectInfo.EquipmentAttachments;
import gov.grants.apply.forms.rrOtherProjectInfoV10.RROtherProjectInfoDocument.RROtherProjectInfo.FacilitiesAttachments;
import gov.grants.apply.forms.rrOtherProjectInfoV10.RROtherProjectInfoDocument.RROtherProjectInfo.HumanSubjectsSupplement;
import gov.grants.apply.forms.rrOtherProjectInfoV10.RROtherProjectInfoDocument.RROtherProjectInfo.InternationalActivities;
import gov.grants.apply.forms.rrOtherProjectInfoV10.RROtherProjectInfoDocument.RROtherProjectInfo.OtherAttachments;
import gov.grants.apply.forms.rrOtherProjectInfoV10.RROtherProjectInfoDocument.RROtherProjectInfo.ProjectNarrativeAttachments;
import gov.grants.apply.forms.rrOtherProjectInfoV10.RROtherProjectInfoDocument.RROtherProjectInfo.VertebrateAnimalsSupplement;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.globalLibraryV10.YesNoDataType;
import gov.grants.apply.system.globalLibraryV10.YesNoDataType.Enum;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.ProposalSpecialReview;
import org.kuali.kra.proposaldevelopment.bo.ProposalYnq;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.util.S2SConstants;

/**
 * Class for generating the XML object for grants.gov RROtherProjectInfoV1.0. Form is generated using XMLBean classes and is based
 * on RROtherProjectInfo schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class RROtherProjectInfoV1_0Generator extends RROtherProjectInfoBaseGenerator {

    private static final Logger LOG = Logger.getLogger(RROtherProjectInfoV1_0Generator.class);


    /**
     * 
     * This method gives information about RROtherProjectInfo of proposal special reviews based on the data in the proposal
     * development document.
     * 
     * @return rrOtherProjectInfoDocument {@link XmlObject} of type RRKeyPersonExpandedDocument.
     */
    private RROtherProjectInfoDocument getRROtherProjectInfo() {

        LOG.info("Inside RROtherProjectInfo");
        RROtherProjectInfoDocument rrOtherProjectInfoDocument = RROtherProjectInfoDocument.Factory.newInstance();
        RROtherProjectInfo rrOtherProjectInfo = RROtherProjectInfo.Factory.newInstance();
        Organization organization = pdDoc.getOrganization();
        rrOtherProjectInfo.setFormVersion(S2SConstants.FORMVERSION_1_0);

        rrOtherProjectInfo.setHumanSubjectsIndicator(YesNoDataType.NO);
        rrOtherProjectInfo.setVertebrateAnimalsIndicator(YesNoDataType.NO);
        String newDescription;
        for (ProposalSpecialReview proposalSpecialReview : pdDoc.getPropSpecialReviews()) {
            if (proposalSpecialReview.getSpecialReviewCode() != null) {
                switch (Integer.parseInt(proposalSpecialReview.getSpecialReviewCode())) {
                    case HUMAN_SUBJECT_SUPPLEMENT:

                        rrOtherProjectInfo.setHumanSubjectsIndicator(YesNoDataType.YES);
                        HumanSubjectsSupplement huSubjectsSupplement = HumanSubjectsSupplement.Factory.newInstance();
                        HumanSubjectsSupplement.ExemptionNumbers exemptionNumbers = HumanSubjectsSupplement.ExemptionNumbers.Factory
                                .newInstance();
                        newDescription = proposalSpecialReview.getComments();
                        HumanSubjectsSupplement.HumanSubjectIRBReviewDate huSubjectIRBReviewDate = HumanSubjectsSupplement.HumanSubjectIRBReviewDate.Factory
                                .newInstance();
                        if (proposalSpecialReview.getApprovalTypeCode() != null
                                && Integer.parseInt(proposalSpecialReview.getApprovalTypeCode()) == APPROVAL_TYPE_EXCEMPT) {
                            if (newDescription != null) {
                                String[] exemptions = newDescription.split(",");
                                HumanSubjectsSupplement.ExemptionNumbers.ExemptionNumber.Enum[] exemptionNumberArray = new HumanSubjectsSupplement.ExemptionNumbers.ExemptionNumber.Enum[exemptions.length];
                                for (int exemptionNumber = 0; exemptionNumber < exemptions.length; exemptionNumber++) {
                                    HumanSubjectsSupplement.ExemptionNumbers.ExemptionNumber.Enum exemptionNumberEnum = HumanSubjectsSupplement.ExemptionNumbers.ExemptionNumber.Enum
                                            .forString(exemptions[exemptionNumber]);
                                    exemptionNumberArray[exemptionNumber] = exemptionNumberEnum;
                                }
                                exemptionNumbers.setExemptionNumberArray(exemptionNumberArray);
                                huSubjectsSupplement.setExemptionNumbers(exemptionNumbers);
                            }
                        }
                        if (SPECIAL_REVIEW_HUMAN_SUBJECTS.equals(proposalSpecialReview.getSpecialReviewCode())) {
                            huSubjectsSupplement.setHumanSubjectIRBReviewIndicator(YesNoDataType.YES);
                        }
                        else {
                            huSubjectsSupplement.setHumanSubjectIRBReviewIndicator(YesNoDataType.NO);
                            if (proposalSpecialReview.getApprovalDate() != null) {
                                huSubjectIRBReviewDate.setCalendarValue(s2sUtilService.convertDateToCalendar(proposalSpecialReview
                                        .getApprovalDate()));
                                huSubjectIRBReviewDate.setHumanSubjectIRBReviewIndicator(YesNoDataType.NO);
                                huSubjectsSupplement.setHumanSubjectIRBReviewDate(huSubjectIRBReviewDate);
                            }
                        }
                        if (organization != null && organization.getHumanSubAssurance() != null) {
                            huSubjectsSupplement.setHumanSubjectAssuranceNumber(organization.getHumanSubAssurance().substring(3));
                        }

                        rrOtherProjectInfo.setHumanSubjectsSupplement(huSubjectsSupplement);
                        break;

                    case VERTEBRATE_ANIMALS_SUPPLEMENT:
                        rrOtherProjectInfo.setVertebrateAnimalsIndicator(YesNoDataType.YES);
                        VertebrateAnimalsSupplement vertebrateAnimalsSupplement = VertebrateAnimalsSupplement.Factory.newInstance();
                        VertebrateAnimalsSupplement.VertebrateAnimalsIACUCApprovalDateReviewDate veApprovalDateReviewDate = VertebrateAnimalsSupplement.VertebrateAnimalsIACUCApprovalDateReviewDate.Factory
                                .newInstance();

                        if (SPECIAL_REVIEW_ANIMAL_USAGE.equals(proposalSpecialReview.getSpecialReviewCode())) {
                            vertebrateAnimalsSupplement.setVertebrateAnimalsIACUCReviewIndicator(YesNoDataType.YES);
                        }
                        else {
                            vertebrateAnimalsSupplement.setVertebrateAnimalsIACUCReviewIndicator(YesNoDataType.NO);
                            if (proposalSpecialReview.getApprovalDate() != null) {
                                veApprovalDateReviewDate.setCalendarValue(s2sUtilService
                                        .convertDateToCalendar(proposalSpecialReview.getApprovalDate()));
                                veApprovalDateReviewDate.setVertebrateAnimalsIACUCReviewIndicator(YesNoDataType.NO);
                                vertebrateAnimalsSupplement
                                        .setVertebrateAnimalsIACUCApprovalDateReviewDate(veApprovalDateReviewDate);
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

        Enum answer = YesNoDataType.NO;
        String answerExplanation = "";
        /**
         * ProprietaryInformationIndicator is of YnQ type
         */
        ProposalYnq proposalYnq = getAnswer(PROPRIETARY_INFORMATION_INDICATOR, pdDoc);
        EnvironmentalImpact environmentalImpact = EnvironmentalImpact.Factory.newInstance();
        InternationalActivities internationalActivities = InternationalActivities.Factory.newInstance();
        // Set default values for mandatory fields
        rrOtherProjectInfo.setProprietaryInformationIndicator(YesNoDataType.NO);
        environmentalImpact.setEnvironmentalImpactIndicator(YesNoDataType.NO);
        internationalActivities.setInternationalActivitiesIndicator(YesNoDataType.NO);
        if (proposalYnq != null) {
            answer = (S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(proposalYnq.getAnswer()) ? YesNoDataType.YES : YesNoDataType.NO);
            rrOtherProjectInfo.setProprietaryInformationIndicator(answer);
        }
        /**
         * EnvironmentalImpact is of YnQ type
         */

        proposalYnq = getAnswer(ENVIRONMENTAL_IMPACT_YNQ, pdDoc);
        if (proposalYnq != null) {
            answer = (S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(proposalYnq.getAnswer()) ? YesNoDataType.YES : YesNoDataType.NO);
            answerExplanation = proposalYnq.getExplanation();
            environmentalImpact.setEnvironmentalImpactIndicator(answer);

            if (YesNoDataType.YES.equals(answer) && answerExplanation != null) {
                EnvironmentalImpact.EnvironmentalImpactExplanation environmentalImpactExplanation = EnvironmentalImpact.EnvironmentalImpactExplanation.Factory
                        .newInstance();
                environmentalImpactExplanation.setStringValue(answerExplanation);
                environmentalImpactExplanation.setEnvironmentalImpactIndicator(answer);
                environmentalImpact.setEnvironmentalImpactExplanation(environmentalImpactExplanation);
            }
        }
        proposalYnq = getAnswer(ENVIRONMENTAL_EXEMPTION_YNQ, pdDoc);
        if (proposalYnq != null) {
            String ynqAnswer = proposalYnq.getAnswer();
            answerExplanation = proposalYnq.getExplanation();
            if (S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(ynqAnswer)) {
                answer = YesNoDataType.YES;
            }
            else {
                answer = YesNoDataType.NO;
            }
            if (!S2SConstants.PROPOSAL_YNQ_ANSWER_NA.equals(ynqAnswer)) {
                // Answer not equal to X (not-applicable)
                EnvironmentalImpact.EnvironmentalExemption environmentalExemption = EnvironmentalImpact.EnvironmentalExemption.Factory
                        .newInstance();
                environmentalExemption.setEnvironmentalExemptionIndicator(answer);
                if (answerExplanation != null) {
                    EnvironmentalImpact.EnvironmentalExemption.EnvironmentalExemptionExplanation environmentalExemptionExplanation = EnvironmentalImpact.EnvironmentalExemption.EnvironmentalExemptionExplanation.Factory
                            .newInstance();
                    environmentalExemptionExplanation.setEnvironmentalExemptionIndicator(answer);
                    environmentalExemptionExplanation.setStringValue(answerExplanation);
                    environmentalExemption.setEnvironmentalExemptionExplanation(environmentalExemptionExplanation);
                }
                environmentalImpact.setEnvironmentalExemption(environmentalExemption);
            }
        }
        rrOtherProjectInfo.setEnvironmentalImpact(environmentalImpact);
        /**
         * InternationalActivities is of YnQ type
         */
        proposalYnq = getAnswer(INTERNATIONAL_ACTIVITIES_YNQ, pdDoc);

        if (proposalYnq != null) {
            answer = (S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(proposalYnq.getAnswer()) ? YesNoDataType.YES : YesNoDataType.NO);
            answerExplanation = proposalYnq.getExplanation();
            internationalActivities.setInternationalActivitiesIndicator(answer);
            if (answerExplanation != null) {
                InternationalActivities.ActivitiesPartnershipsCountries activitiesPartnershipsCountries = InternationalActivities.ActivitiesPartnershipsCountries.Factory
                        .newInstance();

                activitiesPartnershipsCountries.setInternationalActivitiesIndicator(answer);
                activitiesPartnershipsCountries.setStringValue(answerExplanation);
                internationalActivities.setActivitiesPartnershipsCountries(activitiesPartnershipsCountries);
            }
        }
        rrOtherProjectInfo.setInternationalActivities(internationalActivities);
        /**
         * Attachments
         */
        for (Narrative narrative : pdDoc.getNarratives()) {
            if (narrative.getNarrativeTypeCode() != null) {
                if (Integer.parseInt(narrative.getNarrativeTypeCode()) == EQUIPMENT_ATTACHMENT) {
                    // EQUIPMENT
                    EquipmentAttachments equipmentAttachments = EquipmentAttachments.Factory.newInstance();
                    equipmentAttachments.setEquipmentAttachment(getAttachedFileType(narrative));
                    rrOtherProjectInfo.setEquipmentAttachments(equipmentAttachments);
                }
                else if (Integer.parseInt(narrative.getNarrativeTypeCode()) == FACILITIES_ATTACHMENT) {
                    // facilities
                    FacilitiesAttachments facilitiesAttachments = FacilitiesAttachments.Factory.newInstance();
                    facilitiesAttachments.setFacilitiesAttachment(getAttachedFileType(narrative));
                    rrOtherProjectInfo.setFacilitiesAttachments(facilitiesAttachments);
                }
                else if (Integer.parseInt(narrative.getNarrativeTypeCode()) == NARRATIVE_ATTACHMENT) {
                    // NARRATIVE
                    ProjectNarrativeAttachments projectNarrativeAttachments = ProjectNarrativeAttachments.Factory.newInstance();
                    projectNarrativeAttachments.setProjectNarrativeAttachment(getAttachedFileType(narrative));
                    rrOtherProjectInfo.setProjectNarrativeAttachments(projectNarrativeAttachments);
                }
                else if (Integer.parseInt(narrative.getNarrativeTypeCode()) == BIBLIOGRAPHY_ATTACHMENT) {
                    // BIBLIOGRAPHY
                    BibliographyAttachments bibliographyAttachments = BibliographyAttachments.Factory.newInstance();
                    bibliographyAttachments.setBibliographyAttachment(getAttachedFileType(narrative));
                    rrOtherProjectInfo.setBibliographyAttachments(bibliographyAttachments);
                }
                else if (Integer.parseInt(narrative.getNarrativeTypeCode()) == ABSTRACT_PROJECT_SUMMARY_ATTACHMENT) {
                    // ABSTRACT - PROJECT SUMMARY
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
     * This method is used to get List of Other attachments from NarrativeAttachmentList
     * 
     * @return attachedFileDataTypes(AttachedFileDataType[]) Array of other attachments based on narrative type code.
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
     * 
     * This method is used to get the answer for ProposalYnq
     * 
     * @param questionId proposal ynq question id.
     * @return proposalYnq corresponding to the question id.
     */
    private ProposalYnq getAnswer(String questionId, ProposalDevelopmentDocument pdDoc) {
        String question;
        ProposalYnq ynQ = null;
        for (ProposalYnq proposalYnq : pdDoc.getProposalYnqs()) {
            question = proposalYnq.getQuestionId();
            if (question != null && question.equals(questionId)) {
                ynQ = proposalYnq;
                break;
            }
        }
        return ynQ;
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