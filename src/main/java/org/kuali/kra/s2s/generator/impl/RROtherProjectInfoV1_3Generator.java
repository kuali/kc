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

import gov.grants.apply.forms.rrOtherProjectInfo13V13.RROtherProjectInfo13Document;
import gov.grants.apply.forms.rrOtherProjectInfo13V13.RROtherProjectInfo13Document.RROtherProjectInfo13;
import gov.grants.apply.forms.rrOtherProjectInfo13V13.RROtherProjectInfo13Document.RROtherProjectInfo13.AbstractAttachments;
import gov.grants.apply.forms.rrOtherProjectInfo13V13.RROtherProjectInfo13Document.RROtherProjectInfo13.BibliographyAttachments;
import gov.grants.apply.forms.rrOtherProjectInfo13V13.RROtherProjectInfo13Document.RROtherProjectInfo13.EnvironmentalImpact;
import gov.grants.apply.forms.rrOtherProjectInfo13V13.RROtherProjectInfo13Document.RROtherProjectInfo13.EquipmentAttachments;
import gov.grants.apply.forms.rrOtherProjectInfo13V13.RROtherProjectInfo13Document.RROtherProjectInfo13.FacilitiesAttachments;
import gov.grants.apply.forms.rrOtherProjectInfo13V13.RROtherProjectInfo13Document.RROtherProjectInfo13.HumanSubjectsSupplement;
import gov.grants.apply.forms.rrOtherProjectInfo13V13.RROtherProjectInfo13Document.RROtherProjectInfo13.InternationalActivities;
import gov.grants.apply.forms.rrOtherProjectInfo13V13.RROtherProjectInfo13Document.RROtherProjectInfo13.OtherAttachments;
import gov.grants.apply.forms.rrOtherProjectInfo13V13.RROtherProjectInfo13Document.RROtherProjectInfo13.ProjectNarrativeAttachments;
import gov.grants.apply.forms.rrOtherProjectInfo13V13.RROtherProjectInfo13Document.RROtherProjectInfo13.VertebrateAnimalsSupplement;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.bo.CoeusSubModule;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.common.specialreview.bo.SpecialReviewExemption;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.ProposalYnq;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.specialreview.ProposalSpecialReview;
import org.kuali.kra.questionnaire.answer.Answer;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean;
import org.kuali.kra.questionnaire.answer.QuestionnaireAnswerService;
import org.kuali.kra.s2s.util.S2SConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

/**
 * <p>
 * Class for generating the XML object for grants.gov RROtherProjectInfoV1.2.
 * Form is generated using XMLBean classes and is based on RROtherProjectInfo
 * schema.
 * <p>
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class RROtherProjectInfoV1_3Generator extends
		RROtherProjectInfoBaseGenerator {
	private static final String HISTORIC_DESTIONATION_YNQ = "125";
	private static final String EMPTY_STRING = " ";
	private static final Log LOG = LogFactory
			.getLog(RROtherProjectInfoV1_3Generator.class);
	List<AnswerHeader> answerHeaders;

	/*
	 * This method gives information about RROtherProjectInfo of proposal
	 * special reviews based on the data in the proposal development document.
	 * 
	 */
	private RROtherProjectInfo13Document getRROtherProjectInfo() {
		RROtherProjectInfo13Document rrOtherProjectInfoDocument = RROtherProjectInfo13Document.Factory
				.newInstance();
		RROtherProjectInfo13Document.RROtherProjectInfo13 rrOtherProjectInfo = RROtherProjectInfo13Document.RROtherProjectInfo13.Factory
				.newInstance();
		rrOtherProjectInfo.setFormVersion(S2SConstants.FORMVERSION_1_3);
		ModuleQuestionnaireBean moduleQuestionnaireBean = new ModuleQuestionnaireBean(
                CoeusModule.PROPOSAL_DEVELOPMENT_MODULE_CODE, pdDoc.getDevelopmentProposal().getProposalNumber(), CoeusSubModule.ZERO_SUBMODULE ,
                    CoeusSubModule.ZERO_SUBMODULE, true);
        QuestionnaireAnswerService questionnaireAnswerService = KraServiceLocator.getService(QuestionnaireAnswerService.class);
        answerHeaders = questionnaireAnswerService.getQuestionnaireAnswer(moduleQuestionnaireBean);
		rrOtherProjectInfo.
		setHumanSubjectsIndicator(YesNoDataType.N_NO);
		rrOtherProjectInfo.setVertebrateAnimalsIndicator(YesNoDataType.N_NO);
		Organization organization = pdDoc.getDevelopmentProposal()
				.getApplicantOrganization().getOrganization();
		setHumanSubjAndVertebrateAnimals(rrOtherProjectInfo, organization);
		setProprietaryInformationIndicator(rrOtherProjectInfo);
		setEnvironmentalImpactDetails(rrOtherProjectInfo);
		setHistoricDestionation(rrOtherProjectInfo);
		setInternationalActivities(rrOtherProjectInfo);
		setAttachments(rrOtherProjectInfo);
		rrOtherProjectInfoDocument.setRROtherProjectInfo13(rrOtherProjectInfo);
		return rrOtherProjectInfoDocument;
	}

	/*
	 * This method will set the values to historic destination
	 */
	private void setHistoricDestionation(
			RROtherProjectInfo13Document.RROtherProjectInfo13 rrOtherProjectInfo) {
	    String historicDestinationAnswer = getAnswers(HISTORIC_DESTIONATION_YNQ);	   	    
	    if (historicDestinationAnswer != null && !historicDestinationAnswer.equals(NOT_ANSWERED)) {
	        YesNoDataType.Enum answer = S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(historicDestinationAnswer) ? YesNoDataType.Y_YES
	                : YesNoDataType.N_NO;
	        String answerExplanation = getChildQuestionAnswer(HISTORIC_DESTIONATION_YNQ, EXPLANATION);
	        rrOtherProjectInfo.setHistoricDesignation(answer);
	        if (answerExplanation != null) {
	            if (answerExplanation.trim().length() > EXPLANATION_MAX_LENGTH) {
	                rrOtherProjectInfo
						    .setHistoricDesignationExplanation(answerExplanation
						            .trim()
						            .substring(0, EXPLANATION_MAX_LENGTH));
	            } else {
	                rrOtherProjectInfo
						    .setHistoricDesignationExplanation(answerExplanation
						            .trim());
	            }
	        } else if (S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(historicDestinationAnswer)) {
	            rrOtherProjectInfo.setHistoricDesignationExplanation(answerExplanation);
	        }
	    } else {
	        rrOtherProjectInfo.setHistoricDesignation(null);
	    }
	}

	/*
	 * This method will set the values of Proprietary Information
	 */
	private void setProprietaryInformationIndicator(
			RROtherProjectInfo13Document.RROtherProjectInfo13 rrOtherProjectInfo) {
		YesNoDataType.Enum answer = null;
		String propertyInformationAnswer = getAnswers(PROPRIETARY_INFORMATION_INDICATOR);
		if (propertyInformationAnswer != null && !propertyInformationAnswer.equals(NOT_ANSWERED)) {
    		answer = S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(
    			       propertyInformationAnswer) ? YesNoDataType.Y_YES : YesNoDataType.N_NO;
    		rrOtherProjectInfo.setProprietaryInformationIndicator(answer);
		} else {
		    rrOtherProjectInfo.setProprietaryInformationIndicator(null);
		}
	}

	/*
	 * This method will set the values to environmental impact
	 */
	private void setEnvironmentalImpactDetails(
			RROtherProjectInfo13Document.RROtherProjectInfo13 rrOtherProjectInfo) {
		EnvironmentalImpact environmentalImpact = EnvironmentalImpact.Factory
				.newInstance();
		setEnvironmentalImpactIndicatorAndExplanation(environmentalImpact);
		setEnvironmentalExemption(environmentalImpact);
		rrOtherProjectInfo.setEnvironmentalImpact(environmentalImpact);
	}

	/*
	 * This method will set the values to environmental impact indicator and
	 * environmental impact explanation
	 */
	private void setEnvironmentalImpactIndicatorAndExplanation(
			EnvironmentalImpact environmentalImpact) {
		String answerExplanation = null;
		String environmentalImpactAnswer = getAnswers(ENVIRONMENTAL_IMPACT_YNQ);
		if (environmentalImpactAnswer != null && !environmentalImpactAnswer.equals(NOT_ANSWERED)) {
    		YesNoDataType.Enum answer = S2SConstants.PROPOSAL_YNQ_ANSWER_Y
    				.equals(environmentalImpactAnswer) ? YesNoDataType.Y_YES
    				: YesNoDataType.N_NO;
    		answerExplanation =  getChildQuestionAnswer(ENVIRONMENTAL_IMPACT_YNQ, EXPLANATION);
    		environmentalImpact.setEnvironmentalImpactIndicator(answer);
    		if (answerExplanation != null) {
    			if (answerExplanation.trim().length() > EXPLANATION_MAX_LENGTH) {
    				environmentalImpact
    						.setEnvironmentalImpactExplanation(answerExplanation
    								.trim()
    								.substring(0, EXPLANATION_MAX_LENGTH));
    			} else {
    				environmentalImpact
    						.setEnvironmentalImpactExplanation(answerExplanation
    								.trim());
    			}
    		} else if (S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(environmentalImpactAnswer)) {
    		    environmentalImpact.setEnvironmentalImpactExplanation(answerExplanation);
    		}
		} else {
		    environmentalImpact.setEnvironmentalImpactIndicator(null);
		}
	}

	/*
	 * This method will set the values to human subject supplement and
	 * vertebrate animals supplement
	 */
	private void setHumanSubjAndVertebrateAnimals(
			RROtherProjectInfo13Document.RROtherProjectInfo13 rrOtherProjectInfo,
			Organization organization) {
		rrOtherProjectInfo.
		setHumanSubjectsIndicator(YesNoDataType.N_NO); 
		rrOtherProjectInfo.setVertebrateAnimalsIndicator(YesNoDataType.N_NO); 
		for (ProposalSpecialReview proposalSpecialReview : pdDoc
				.getDevelopmentProposal().getPropSpecialReviews()) {
			if (proposalSpecialReview.getSpecialReviewTypeCode() != null) {
				if (Integer.valueOf(proposalSpecialReview
						.getSpecialReviewTypeCode()) == HUMAN_SUBJECT_SUPPLEMENT) {
					setHumaSubjectSupplementDetails(rrOtherProjectInfo,
							organization, proposalSpecialReview);
				} else if (Integer.valueOf(proposalSpecialReview
						.getSpecialReviewTypeCode()) == VERTEBRATE_ANIMALS_SUPPLEMENT) {
					setVertebrateAnimalsSupplementDetails(rrOtherProjectInfo,
							organization, proposalSpecialReview);
				}
			}
		}
	}

	/*
	 * This method will set the values to environmental exemption
	 */
	private void setEnvironmentalExemption(
			EnvironmentalImpact environmentalImpact) {
		YesNoDataType.Enum answer = null;
		String answerExplanation;		
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
    			if (answerExplanation != null) {
    				if (answerExplanation.trim().length() > EXPLANATION_MAX_LENGTH) {
    					environmentalExemption
    							.setEnvironmentalExemptionExplanation(answerExplanation
    									.trim().substring(0,EXPLANATION_MAX_LENGTH));
    				} else {
    					environmentalExemption.setEnvironmentalExemptionExplanation(answerExplanation.trim());
    				}
    			} else if (S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(ynqAnswer)) {
    			    environmentalExemption.setEnvironmentalExemptionExplanation(answerExplanation);
    			}
    		    environmentalImpact.setEnvironmentalExemption(environmentalExemption);
    
    		}
		} else {
		    environmentalExemption.setEnvironmentalExemptionIndicator(null);
        }    		
	}

	/*
	 * This method will set the International Activities are
	 * InternationalActivitiesIndicator ,ActivitiesPartnershipsCountries
	 */
	private void setInternationalActivities(
			RROtherProjectInfo13Document.RROtherProjectInfo13 rrOtherProjectInfo) {
		InternationalActivities internationalActivities = InternationalActivities.Factory
				.newInstance();
		YesNoDataType.Enum answer = null;
		String answerExplanation;
		String internationalActivitiesAnswer = getAnswers(INTERNATIONAL_ACTIVITIES_YNQ);
		if (internationalActivitiesAnswer != null && !internationalActivitiesAnswer.equals(NOT_ANSWERED)) {
    		answer = S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(
    				internationalActivitiesAnswer) ? YesNoDataType.Y_YES : YesNoDataType.N_NO;
    		answerExplanation = getAnswers(INTERNATIONAL_ACTIVITIES_EXPL);
    		internationalActivities.setInternationalActivitiesIndicator(answer);
    		if (answerExplanation != null && !answerExplanation.equals(NOT_ANSWERED)) {
    			if (answerExplanation.trim().length() > EXPLANATION_MAX_LENGTH) {
    				internationalActivities
    						.setActivitiesPartnershipsCountries(answerExplanation
    								.trim()
    								.substring(0, EXPLANATION_MAX_LENGTH));
    			} else {
    				internationalActivities
    						.setActivitiesPartnershipsCountries(answerExplanation
    								.trim());
    			}
    			if (getChildQuestionAnswer(INTERNATIONAL_ACTIVITIES_YNQ, EXPLANATION) != null) {
    			    internationalActivities.setInternationalActivitiesExplanation(getChildQuestionAnswer(INTERNATIONAL_ACTIVITIES_YNQ, EXPLANATION));
    			}
    		} else if (S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(internationalActivitiesAnswer)) {
    		    internationalActivities.setActivitiesPartnershipsCountries(answerExplanation);
    		}
		} else {
		    internationalActivities.setInternationalActivitiesIndicator(null);
		}
		rrOtherProjectInfo.setInternationalActivities(internationalActivities);
	}

	/*
	 * This method will set the values to vertebrate animals supplement details
	 */
	private void setVertebrateAnimalsSupplementDetails(
			RROtherProjectInfo13Document.RROtherProjectInfo13 rrOtherProjectInfo,
			Organization organization,
			ProposalSpecialReview proposalSpecialReview) {
		rrOtherProjectInfo.setVertebrateAnimalsIndicator(YesNoDataType.Y_YES);
		VertebrateAnimalsSupplement vertebrateAnimalsSupplement = VertebrateAnimalsSupplement.Factory
				.newInstance();
		setVertebrateAnimalsIACUCReviewDetails(proposalSpecialReview,
				vertebrateAnimalsSupplement);
		if (organization != null
				&& organization.getAnimalWelfareAssurance() != null) {
			vertebrateAnimalsSupplement.setAssuranceNumber(organization
					.getAnimalWelfareAssurance());
		}
		rrOtherProjectInfo
				.setVertebrateAnimalsSupplement(vertebrateAnimalsSupplement);
	}

	/*
	 * This method will set the values to human subject supplement details
	 */
	private void setHumaSubjectSupplementDetails(
			RROtherProjectInfo13Document.RROtherProjectInfo13 rrOtherProjectInfo,
			Organization organization,
			ProposalSpecialReview proposalSpecialReview) {
		rrOtherProjectInfo.
		setHumanSubjectsIndicator(YesNoDataType.Y_YES);
		HumanSubjectsSupplement humanSubjectsSupplement = HumanSubjectsSupplement.Factory
				.newInstance();
		HumanSubjectsSupplement.ExemptionNumbers exemptionNumbers = HumanSubjectsSupplement.ExemptionNumbers.Factory
				.newInstance();

		if (proposalSpecialReview.getApprovalTypeCode() != null) {
			
			
			setExemptions(proposalSpecialReview, humanSubjectsSupplement,
					exemptionNumbers);
			setHumanSubjectIRBReviewIndicator(proposalSpecialReview,
					humanSubjectsSupplement);
		}
		Boolean paramValue= KraServiceLocator.getService(ParameterService.class).getParameterValueAsBoolean("KC-PROTOCOL", "Document", "irb.protocol.development.proposal.linking.enabled");
		 if(paramValue){
			if (proposalSpecialReview.getSpecialReviewExemptions() != null && !proposalSpecialReview.getSpecialReviewExemptions().isEmpty()) {
				humanSubjectsSupplement.setExemptFedReg(YesNoDataType.Y_YES);				
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
		humanSubjectsSupplement.setExemptionNumbers(exemptionNumbers);} else {
			if (proposalSpecialReview.getApprovalDate() != null) {
				humanSubjectsSupplement
				.setHumanSubjectIRBReviewIndicator(YesNoDataType.N_NO);
			}else{
				humanSubjectsSupplement
				.setHumanSubjectIRBReviewIndicator(YesNoDataType.Y_YES);
			}
		}
			}
		if (organization != null && organization.getHumanSubAssurance() != null) {
			humanSubjectsSupplement.setHumanSubjectAssuranceNumber(organization
					.getHumanSubAssurance().substring(3));
		}
		if (humanSubjectsSupplement != null) {
			rrOtherProjectInfo
					.setHumanSubjectsSupplement(humanSubjectsSupplement);
		}
	}

	/*
	 * This method will set the Vertebrate Animals IACUC Review details are date
	 * and indicator based on condition
	 */
	private void setVertebrateAnimalsIACUCReviewDetails(
			ProposalSpecialReview proposalSpecialReview,
			VertebrateAnimalsSupplement vertebrateAnimalsSupplement) {
		if (SPECIAL_REVIEW_ANIMAL_USAGE.equals(proposalSpecialReview
				.getApprovalTypeCode())) {
			vertebrateAnimalsSupplement
					.setVertebrateAnimalsIACUCReviewIndicator(YesNoDataType.Y_YES);
		} else {
			vertebrateAnimalsSupplement
					.setVertebrateAnimalsIACUCReviewIndicator(YesNoDataType.N_NO);
			if (proposalSpecialReview.getApprovalDate() != null) {
				vertebrateAnimalsSupplement
						.setVertebrateAnimalsIACUCApprovalDateReviewDate(s2sUtilService
								.convertDateToCalendar(proposalSpecialReview
										.getApprovalDate()));
			}
		}
		if (KraServiceLocator.getService(ParameterService.class).getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_IACUC, 
		        Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.IACUC_PROTOCOL_PROPOSAL_DEVELOPMENT_LINKING_ENABLED_PARAMETER)) {
		    if (proposalSpecialReview.getApprovalDate() != null) {
		        vertebrateAnimalsSupplement.setVertebrateAnimalsIACUCReviewIndicator(YesNoDataType.Y_YES);
		    } else {
		        vertebrateAnimalsSupplement.setVertebrateAnimalsIACUCReviewIndicator(YesNoDataType.N_NO);
		    }
		}
	}

	/*
	 * This method will set the exemptions to human subjects supplement
	 */
	private void setExemptions(ProposalSpecialReview proposalSpecialReview,
			HumanSubjectsSupplement humanSubjectsSupplement,
			HumanSubjectsSupplement.ExemptionNumbers exemptionNumbers) {
	
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
			humanSubjectsSupplement.setExemptFedReg(YesNoDataType.Y_YES);
		} 
		else{
		    humanSubjectsSupplement.setExemptFedReg(YesNoDataType.N_NO);
		}
		
	}

	/*
	 * This method will set the Human Subject IRB Review Indicator
	 */
	private void setHumanSubjectIRBReviewIndicator(
			ProposalSpecialReview proposalSpecialReview,
			HumanSubjectsSupplement humanSubjectsSupplement) {
		if (SPECIAL_REVIEW_HUMAN_SUBJECTS.equals(proposalSpecialReview
				.getApprovalTypeCode())) {
			humanSubjectsSupplement
					.setHumanSubjectIRBReviewIndicator(YesNoDataType.Y_YES);
		} else {
			humanSubjectsSupplement
					.setHumanSubjectIRBReviewIndicator(YesNoDataType.N_NO);
			if (proposalSpecialReview.getApprovalDate() != null) {
				humanSubjectsSupplement
						.setHumanSubjectIRBReviewDate(s2sUtilService
								.convertDateToCalendar(proposalSpecialReview
										.getApprovalDate()));
			}
		}
	}
	
	/**
     * 
     * This method is used to get the answer for a particular Questionnaire question
     * question based on the question id .
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

	/*
	 * This method will set the attachments to RR Other Project info document
	 * based on the type of attachment
	 */
	private void setAttachments(
			RROtherProjectInfo13Document.RROtherProjectInfo13 rrOtherProjectInfo) {
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
	private void setOtherAttachments(
			RROtherProjectInfo13Document.RROtherProjectInfo13 rrOtherProjectInfo) {
		OtherAttachments otherAttachments = OtherAttachments.Factory
				.newInstance();
		otherAttachments.setOtherAttachmentArray(getAttachedFileDataTypes());
		rrOtherProjectInfo.setOtherAttachments(otherAttachments);
	}

	/*
	 * 
	 * This method is used to get the answer for ProposalYnq
	 * 
	 */
	private ProposalYnq getAnswer(String questionId) {
		String question;
		ProposalYnq ynQ = null;
		for (ProposalYnq proposalYnq : pdDoc.getDevelopmentProposal()
				.getProposalYnqs()) {
			question = proposalYnq.getQuestionId();
			if (question != null && question.equals(questionId)) {
				ynQ = proposalYnq;
				break;
			}
		}
		return ynQ;
	}

	/*
	 * 
	 * This method is used to get List of attachments from
	 * NarrativeAttachmentList
	 */
	private AttachedFileDataType[] getAttachedFileDataTypes() {
		AttachedFileDataType attachedFileDataType = null;
		List<AttachedFileDataType> attachedFileDataTypeList = new ArrayList<AttachedFileDataType>();
		DevelopmentProposal developmentProposal = pdDoc
				.getDevelopmentProposal();
		for (Narrative narrative : developmentProposal.getNarratives()) {
			if (narrative.getNarrativeTypeCode() != null
					&& (Integer.parseInt(narrative.getNarrativeTypeCode()) == OTHER_ATTACHMENT || Integer
							.parseInt(narrative.getNarrativeTypeCode()) == SUPPLIMENTARY_ATTACHMENT)) {
				attachedFileDataType= getAttachedFileType(narrative);
				if(attachedFileDataType != null){
					attachedFileDataTypeList.add(attachedFileDataType);
				}
			}
		}
		return attachedFileDataTypeList.toArray(new AttachedFileDataType[0]);
	}

	/**
	 * This method creates {@link XmlObject} of type
	 * {@link RROtherProjectInfo13Document} by populating data from the given
	 * {@link ProposalDevelopmentDocument}
	 * 
	 * @param proposalDevelopmentDocument
	 *            for which the {@link XmlObject} needs to be created
	 * @return {@link XmlObject} which is generated using the given
	 *         {@link ProposalDevelopmentDocument}
	 * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(ProposalDevelopmentDocument)
	 */
	public XmlObject getFormObject(
			ProposalDevelopmentDocument proposalDevelopmentDocument) {
		this.pdDoc = proposalDevelopmentDocument;
		return getRROtherProjectInfo();
	}

	/**
	 * This method typecasts the given {@link XmlObject} to the required
	 * generator type and returns back the document of that generator type.
	 * 
	 * @param xmlObject
	 *            which needs to be converted to the document type of the
	 *            required generator
	 * @return {@link XmlObject} document of the required generator type
	 * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(XmlObject)
	 */
	public XmlObject getFormObject(XmlObject xmlObject) {
		RROtherProjectInfo13 rrOtherProjectInfo = (RROtherProjectInfo13) xmlObject;
		RROtherProjectInfo13Document rrOtherProjectInfoDocument = RROtherProjectInfo13Document.Factory
				.newInstance();
		rrOtherProjectInfoDocument.setRROtherProjectInfo13(rrOtherProjectInfo);
		return rrOtherProjectInfoDocument;
	}
}
