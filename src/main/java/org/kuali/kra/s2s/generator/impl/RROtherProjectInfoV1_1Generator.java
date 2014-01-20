/*
 * Copyright 2005-2014 The Kuali Foundation.
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
import gov.grants.apply.forms.rrOtherProjectInfoV11.RROtherProjectInfoDocument.RROtherProjectInfo.*;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType.Enum;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.common.specialreview.bo.SpecialReviewExemption;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.specialreview.ProposalSpecialReview;
import org.kuali.kra.questionnaire.answer.Answer;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.s2s.util.S2SConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for generating the XML object for grants.gov RROtherProjectInfoV1.1. Form is generated using XMLBean classes and is based
 * on RROtherProjectInfo schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class RROtherProjectInfoV1_1Generator extends RROtherProjectInfoBaseGenerator {


    private static final Log LOG = LogFactory.getLog(RROtherProjectInfoV1_1Generator.class);
    List<AnswerHeader> answerHeaders;

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
        rrOtherProjectInfo.setFormVersion(S2SConstants.FORMVERSION_1_1);
        answerHeaders = getQuestionnaireAnswers(pdDoc.getDevelopmentProposal(), true);
        setHumanSubjAndVertebrateAnimals(rrOtherProjectInfo);
        Enum answer = YesNoDataType.N_NO;
        String answerExplanation = " ";

        /**
         * ProprietaryInformationIndicator is of YnQ type
         */
        EnvironmentalImpact environmentalImpact = EnvironmentalImpact.Factory.newInstance();
        InternationalActivities internationalActivities = InternationalActivities.Factory.newInstance();
        String propertyInformationAnswer = getAnswers(PROPRIETARY_INFORMATION_INDICATOR);
        if(propertyInformationAnswer != null && !propertyInformationAnswer.equals(NOT_ANSWERED)){
            answer = (S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(propertyInformationAnswer) ? YesNoDataType.Y_YES : YesNoDataType.N_NO);
            rrOtherProjectInfo.setProprietaryInformationIndicator(answer);
        } else {
            rrOtherProjectInfo.setProprietaryInformationIndicator(null);
        }
        /**
         * EnvironmentalImpact is of YnQ type
         */
        String environmentalImpactAnswer = getAnswers(ENVIRONMENTAL_IMPACT_YNQ);
        answer = (S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(environmentalImpactAnswer) ? YesNoDataType.Y_YES : YesNoDataType.N_NO);
        answerExplanation = getChildQuestionAnswer(ENVIRONMENTAL_IMPACT_YNQ, EXPLANATION);
        environmentalImpact.setEnvironmentalImpactIndicator(answer);
        if (environmentalImpactAnswer != null && !environmentalImpactAnswer.equals(NOT_ANSWERED)){
            if (answerExplanation != null) {
                environmentalImpact.setEnvironmentalImpactExplanation(answerExplanation);
            } else {
                environmentalImpact.setEnvironmentalImpactIndicator(YesNoDataType.N_NO);
            }
        } else {
            environmentalImpact.setEnvironmentalImpactIndicator(null);
        }
        if (S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(environmentalImpactAnswer) &&  answerExplanation == null) {
            environmentalImpact.setEnvironmentalImpactExplanation(answerExplanation);
        }
        if (answer.equals(YesNoDataType.Y_YES)) {
            answerExplanation = getChildQuestionAnswer(ENVIRONMENTAL_EXEMPTION_YNQ, EXPLANATION);
            String ynqAnswer = getAnswers(ENVIRONMENTAL_EXEMPTION_YNQ);
            if (S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(ynqAnswer)) {
                answer = YesNoDataType.Y_YES;
            } else {
                answer = YesNoDataType.N_NO;
            }
            EnvironmentalImpact.EnvironmentalExemption environmentalExemption = EnvironmentalImpact.EnvironmentalExemption.Factory
                .newInstance();
            if (ynqAnswer != null && !ynqAnswer.equals(NOT_ANSWERED)) {
                if (!S2SConstants.PROPOSAL_YNQ_ANSWER_NA.equals(ynqAnswer)) {
                    // Answer not equal to X (not-applicable)
                    environmentalExemption.setEnvironmentalExemptionIndicator(answer);
                    if (S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(ynqAnswer)) {                       
                        environmentalExemption.setEnvironmentalExemptionExplanation(answerExplanation);
                    }
                }
            } else {
                environmentalExemption.setEnvironmentalExemptionIndicator(null);
            }
            environmentalImpact.setEnvironmentalExemption(environmentalExemption);
        }
        rrOtherProjectInfo.setEnvironmentalImpact(environmentalImpact);

        /**
         * InternationalActivities is of YnQ type
         */
        String internationalActivitiesAnswer = getAnswers(INTERNATIONAL_ACTIVITIES_YNQ);
        if (internationalActivitiesAnswer != null && !internationalActivitiesAnswer.equals(NOT_ANSWERED)) {
            answer = S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(internationalActivitiesAnswer) ? YesNoDataType.Y_YES : YesNoDataType.N_NO;
            answerExplanation = getAnswers(INTERNATIONAL_ACTIVITIES_EXPL);
            internationalActivities.setInternationalActivitiesIndicator(answer);
            if (answerExplanation != null && !answerExplanation.equals(NOT_ANSWERED)) {
                internationalActivities.setActivitiesPartnershipsCountries(answerExplanation);
                if (getChildQuestionAnswer(INTERNATIONAL_ACTIVITIES_YNQ, EXPLANATION) != null) {
                    internationalActivities.setInternationalActivitiesExplanation(getChildQuestionAnswer(INTERNATIONAL_ACTIVITIES_YNQ, EXPLANATION));
                }
            }
            if (S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(internationalActivitiesAnswer) && answerExplanation == null) {
                internationalActivities.setActivitiesPartnershipsCountries(answerExplanation);
            }
        } else {
            internationalActivities.setInternationalActivitiesIndicator(null);
        }
        rrOtherProjectInfo.setInternationalActivities(internationalActivities);
        /**
         * Attachments
         */
        setAttachments(rrOtherProjectInfo);
        rrOtherProjectInfoDocument.setRROtherProjectInfo(rrOtherProjectInfo);
        return rrOtherProjectInfoDocument;
    }

    /*
	 * This method will set the attachments to RR Other Project info document
	 * based on the type of attachment
	 * @param rrOtherProjectInfo
	 */
	private void setAttachments(
			RROtherProjectInfoDocument.RROtherProjectInfo rrOtherProjectInfo) {
        Boolean isOtherAttachmentsExists = false;
        AttachedFileDataType attachedFileDataType;
        ProjectNarrativeAttachments projectNarrativeAttachments = ProjectNarrativeAttachments.Factory.newInstance();
        AbstractAttachments abstractAttachments = AbstractAttachments.Factory.newInstance();
       for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeTypeCode() != null) {               
                switch(Integer.parseInt(narrative.getNarrativeTypeCode())){
                    case(EQUIPMENT_ATTACHMENT):
                        attachedFileDataType = getAttachedFileType(narrative);
                        if(attachedFileDataType != null){                            
                            EquipmentAttachments equipmentAttachments = EquipmentAttachments.Factory.newInstance();
                            equipmentAttachments.setEquipmentAttachment(attachedFileDataType);
                            rrOtherProjectInfo.setEquipmentAttachments(equipmentAttachments);
                          }
                    break;
                    case(FACILITIES_ATTACHMENT):
                        attachedFileDataType = getAttachedFileType(narrative);
                        if(attachedFileDataType != null){                            
                            FacilitiesAttachments facilitiesAttachments = FacilitiesAttachments.Factory.newInstance();
                            facilitiesAttachments.setFacilitiesAttachment(attachedFileDataType);
                            rrOtherProjectInfo.setFacilitiesAttachments(facilitiesAttachments);
                          }
                    break;
                    case(NARRATIVE_ATTACHMENT):
                        attachedFileDataType = getAttachedFileType(narrative);
                        if(attachedFileDataType != null){
                            projectNarrativeAttachments.setProjectNarrativeAttachment(attachedFileDataType);
                        }
                    break;
                    case(BIBLIOGRAPHY_ATTACHMENT):
                        attachedFileDataType = getAttachedFileType(narrative);
                        if(attachedFileDataType != null){                            
                            BibliographyAttachments bibliographyAttachments = BibliographyAttachments.Factory.newInstance();
                            bibliographyAttachments.setBibliographyAttachment(attachedFileDataType);
                            rrOtherProjectInfo.setBibliographyAttachments(bibliographyAttachments);
                         }
                    break;
                    case(ABSTRACT_PROJECT_SUMMARY_ATTACHMENT):
                        attachedFileDataType = getAttachedFileType(narrative);
                        if(attachedFileDataType != null){
                            abstractAttachments.setAbstractAttachment(attachedFileDataType);
                        }
                    break;
                    case(OTHER_ATTACHMENT):
                    case(SUPPLIMENTARY_ATTACHMENT):
                        isOtherAttachmentsExists = true;
                    break;
                }
            }
        }
        rrOtherProjectInfo.setProjectNarrativeAttachments(projectNarrativeAttachments);        
        rrOtherProjectInfo.setAbstractAttachments(abstractAttachments);
        if (isOtherAttachmentsExists) {
            setOtherAttachments(rrOtherProjectInfo);
        }

	}
    /*
     * This method will set the other attachments to RR other Project info
     * document
     */
    private void setOtherAttachments(RROtherProjectInfo rrOtherProjectInfo) {
        OtherAttachments otherAttachments = OtherAttachments.Factory
                .newInstance();
        otherAttachments.setOtherAttachmentArray(getAttachedFileDataTypes());
        rrOtherProjectInfo.setOtherAttachments(otherAttachments);
    }

	/*
	 * This method will set the values to human subject supplement and
	 * vertebrate animals supplement
	 * @param rrOtherProjectInfo
	 */
	private void setHumanSubjAndVertebrateAnimals(
			RROtherProjectInfoDocument.RROtherProjectInfo rrOtherProjectInfo) {
		Organization organization = pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization();
        rrOtherProjectInfo.setHumanSubjectsIndicator(YesNoDataType.N_NO);
        rrOtherProjectInfo.setVertebrateAnimalsIndicator(YesNoDataType.N_NO);
        for (ProposalSpecialReview proposalSpecialReview : pdDoc.getDevelopmentProposal().getPropSpecialReviews()) {
            if (proposalSpecialReview.getSpecialReviewTypeCode() != null) {
                switch (Integer.parseInt(proposalSpecialReview.getSpecialReviewTypeCode())) {
                    case HUMAN_SUBJECT_SUPPLEMENT:
                        HumanSubjectsSupplement humanSubjectsSupplement = getHumanSubjectsIndicator(
								rrOtherProjectInfo, proposalSpecialReview);
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
                        if (SPECIAL_REVIEW_ANIMAL_USAGE.equals(proposalSpecialReview.getApprovalTypeCode())) {
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
                            vertebrateAnimalsSupplement.setAssuranceNumber(organization.getAnimalWelfareAssurance());
                        }

                        rrOtherProjectInfo.setVertebrateAnimalsSupplement(vertebrateAnimalsSupplement);
                        break;
                    default:
                        break;
                }// switch
            }// if
        }// for
	}

	/*
	 * This method will set the values to human subject supplement details
	 * 
	 * @param rrOtherProjectInfo
	 * @param proposalSpecialReview
	 * @return HumanSubjectsSupplement
	 */
	private HumanSubjectsSupplement getHumanSubjectsIndicator(
			RROtherProjectInfoDocument.RROtherProjectInfo rrOtherProjectInfo,
			ProposalSpecialReview proposalSpecialReview) {
		rrOtherProjectInfo.setHumanSubjectsIndicator(YesNoDataType.Y_YES);
		HumanSubjectsSupplement humanSubjectsSupplement = HumanSubjectsSupplement.Factory.newInstance();
		HumanSubjectsSupplement.ExemptionNumbers exemptionNumbers = HumanSubjectsSupplement.ExemptionNumbers.Factory
		        .newInstance();
		if (proposalSpecialReview.getApprovalTypeCode() != null) {
		    if (Integer.parseInt(proposalSpecialReview.getApprovalTypeCode()) == APPROVAL_TYPE_EXCEMPT) {
		        if (proposalSpecialReview.getSpecialReviewExemptions() != null) {
		        	List<HumanSubjectsSupplement.ExemptionNumbers.ExemptionNumber.Enum> exemptionNumberList = new ArrayList<HumanSubjectsSupplement.ExemptionNumbers.ExemptionNumber.Enum>();
					HumanSubjectsSupplement.ExemptionNumbers.ExemptionNumber.Enum exemptionNumberEnum = null;
		        	for (SpecialReviewExemption exemption : proposalSpecialReview.getSpecialReviewExemptions()) {
						exemptionNumberEnum = HumanSubjectsSupplement.ExemptionNumbers.ExemptionNumber.Enum
								.forInt(Integer.parseInt(exemption.getExemptionTypeCode()));
						exemptionNumberList.add(exemptionNumberEnum);
					}
					exemptionNumbers
							.setExemptionNumberArray(exemptionNumberList
									.toArray(new HumanSubjectsSupplement.ExemptionNumbers.ExemptionNumber.Enum[1]));
		            humanSubjectsSupplement.setExemptionNumbers(exemptionNumbers);
		        }
		    }
		    if (SPECIAL_REVIEW_HUMAN_SUBJECTS.equals(proposalSpecialReview.getApprovalTypeCode())) {
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
		return humanSubjectsSupplement;
	}
    
    /**
     * 
     * This method is used to get the answer for a particular Questionnaire question
     * question based on the question id.
     * 
     * @param questionId
     *            the question id to be passed.
     * @return returns the answer for a particular
     *         question based on the question id passed.
     */
    private String getAnswers(String questionId) {
        String answer = null;
        if (answerHeaders != null && !answerHeaders.isEmpty()) {
            for (AnswerHeader answerHeader : answerHeaders) {
                List<Answer> answerDetails = answerHeader.getAnswers();
                for (Answer answers : answerDetails) {
                    if (questionId.equals(answers.getQuestion().getQuestionId())) {
                        answer = answers.getAnswer();
                        return answer;
                    }
                }
            }
        }
        return answer;        
    }
    
    /**
     * 
     * This method is used to get the answer for a particular Questionnaire question
     * question based on the question id and parentQuestionId.
     * 
     * @param questionId
     *            the question id to be passed.
     *            
     * @param parentQuestionId
     *            the parentQuestionId to be passed.
     * @return returns the answer for a particular
     *         question based on the question id passed.
     */
    private String getChildQuestionAnswer(String parentQuestionId,String questionId) {
        String answer = null;
        for (AnswerHeader answerHeader:answerHeaders) {            
            List<Answer> answerDetails = answerHeader.getAnswers();
            for (Answer answers:answerDetails) {
                if (answers.getParentAnswer() != null) {
                    Answer parentAnswer =  answers.getParentAnswer().get(0);
                    if (questionId.equals(answers.getQuestion().getQuestionId()) && parentAnswer.getQuestion().getQuestionId().equals(parentQuestionId)) {
                        answer = answers.getAnswer();
                        return answer;
                    }
                }
            }
        }
        
        return answer;
        
    }


    /**
     * 
     * This method is used to get List of attachments from NarrativeAttachmentList
     * 
     * @return attachedFileDataTypes(AttachedFileDataType[])
     */
    private AttachedFileDataType[] getAttachedFileDataTypes() {
        LOG.info("Getting AttachedFileDataType ");
        List<AttachedFileDataType> attachedFileDataTypeList = new ArrayList<AttachedFileDataType>();
        AttachedFileDataType attachedFileDataType = null;
        for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeTypeCode() != null
                    && (Integer.parseInt(narrative.getNarrativeTypeCode()) == OTHER_ATTACHMENT || Integer.parseInt(narrative
                            .getNarrativeTypeCode()) == SUPPLIMENTARY_ATTACHMENT)) {
            	attachedFileDataType= getAttachedFileType(narrative);
            	if(attachedFileDataType != null){
            		attachedFileDataTypeList.add(attachedFileDataType);
            	}
                LOG.info("Attachmentcount" + attachedFileDataTypeList.size());
            }
        }
        return attachedFileDataTypeList.toArray(new AttachedFileDataType[0]);
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
