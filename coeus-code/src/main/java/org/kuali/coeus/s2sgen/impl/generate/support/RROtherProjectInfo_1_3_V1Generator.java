/*
 * Copyright 2005-2013 The Kuali Foundation.
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
package org.kuali.coeus.s2sgen.impl.generate.support;

import gov.grants.apply.forms.rrOtherProjectInfo13V13.RROtherProjectInfo13Document;
import gov.grants.apply.forms.rrOtherProjectInfo13V13.RROtherProjectInfo13Document.RROtherProjectInfo13.*;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.api.org.OrganizationContract;
import org.kuali.coeus.common.api.ynq.YnqConstant;
import org.kuali.coeus.common.questionnaire.api.answer.AnswerHeaderContract;
import org.kuali.coeus.propdev.api.core.DevelopmentProposalContract;
import org.kuali.coeus.propdev.api.s2s.S2SConfigurationService;
import org.kuali.coeus.propdev.api.specialreview.ProposalSpecialReviewContract;
import org.kuali.coeus.propdev.api.specialreview.ProposalSpecialReviewExemptionContract;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.propdev.api.attachment.NarrativeContract;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;

import org.kuali.coeus.s2sgen.api.core.ConfigurationConstants;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Class for generating the XML object for grants.gov RROtherProjectInfoV1.2.
 * Form is generated using XMLBean classes and is based on RROtherProjectInfo
 * schema.
 * <p>
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@FormGenerator("RROtherProjectInfo_1_3_V1Generator")
public class RROtherProjectInfo_1_3_V1Generator extends
		RROtherProjectInfoBaseGenerator {
	private static final Integer HISTORIC_DESTIONATION_YNQ = 125;
	private static final int NSF_DATAMGMNT_ATTAACHMENT = 200;

	List<? extends AnswerHeaderContract> answerHeaders;

    @Value("http://apply.grants.gov/forms/RR_OtherProjectInfo_1_3-V1.3")
    private String namespace;

    @Value("RR_OtherProjectInfo_1_3-V1.3")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/RR_OtherProjectInfo_1_3-V1.3.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.rrOtherProjectInfo13V13")
    private String packageName;

    @Value("140")
    private int sortIndex;

    @Autowired
    @Qualifier("s2SConfigurationService")
    private S2SConfigurationService s2SConfigurationService;

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
		rrOtherProjectInfo.setFormVersion(FormVersion.v1_3.getVersion());
		answerHeaders = getPropDevQuestionAnswerService().getQuestionnaireAnswerHeaders(pdDoc.getDevelopmentProposal().getProposalNumber());
		rrOtherProjectInfo.
		setHumanSubjectsIndicator(YesNoDataType.N_NO);
		rrOtherProjectInfo.setVertebrateAnimalsIndicator(YesNoDataType.N_NO);
		OrganizationContract organization = pdDoc.getDevelopmentProposal()
				.getApplicantOrganization().getOrganization();
		setHumanSubjAndVertebrateAnimals(rrOtherProjectInfo, organization);
		setProprietaryInformationIndicator(rrOtherProjectInfo);
		setEnvironmentalImpactDetails(rrOtherProjectInfo);
		setHistoricDestionation(rrOtherProjectInfo);
		setInternationalActivities(rrOtherProjectInfo);
		setAttachments(rrOtherProjectInfo);
		rrOtherProjectInfoDocument.setRROtherProjectInfo13(rrOtherProjectInfo);
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(rrOtherProjectInfoDocument.toString().getBytes());            
        sortAttachments(byteArrayInputStream);
		return rrOtherProjectInfoDocument;
	}

	/*
	 * This method will set the values to historic destination
	 */
	private void setHistoricDestionation(
			RROtherProjectInfo13Document.RROtherProjectInfo13 rrOtherProjectInfo) {
	    String historicDestinationAnswer = getAnswer(HISTORIC_DESTIONATION_YNQ, answerHeaders);
	    if (historicDestinationAnswer != null && !historicDestinationAnswer.equals(NOT_ANSWERED)) {
	        YesNoDataType.Enum answer = YnqConstant.YES.code().equals(historicDestinationAnswer) ? YesNoDataType.Y_YES
	                : YesNoDataType.N_NO;
	        String answerExplanation = getChildQuestionAnswer(HISTORIC_DESTIONATION_YNQ, EXPLANATION, answerHeaders);
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
	        } else if (YnqConstant.YES.code().equals(historicDestinationAnswer)) {
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
		String propertyInformationAnswer = getAnswer(PROPRIETARY_INFORMATION_INDICATOR, answerHeaders);
		if (propertyInformationAnswer != null && !propertyInformationAnswer.equals(NOT_ANSWERED)) {
    		answer = YnqConstant.YES.code().equals(
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
		String environmentalImpactAnswer = getAnswer(ENVIRONMENTAL_IMPACT_YNQ, answerHeaders);
		if (environmentalImpactAnswer != null && !environmentalImpactAnswer.equals(NOT_ANSWERED)) {
    		YesNoDataType.Enum answer = YnqConstant.YES.code()
    				.equals(environmentalImpactAnswer) ? YesNoDataType.Y_YES
    				: YesNoDataType.N_NO;
    		answerExplanation =  getChildQuestionAnswer(ENVIRONMENTAL_IMPACT_YNQ, EXPLANATION, answerHeaders);
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
    		} else if (YnqConstant.YES.code().equals(environmentalImpactAnswer)) {
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
			OrganizationContract organization) {
		rrOtherProjectInfo.
		setHumanSubjectsIndicator(YesNoDataType.N_NO); 
		rrOtherProjectInfo.setVertebrateAnimalsIndicator(YesNoDataType.N_NO); 
		for (ProposalSpecialReviewContract proposalSpecialReview : pdDoc
				.getDevelopmentProposal().getPropSpecialReviews()) {
			if (proposalSpecialReview.getSpecialReviewType() != null) {
				if (Integer.valueOf(proposalSpecialReview
						.getSpecialReviewType().getCode()) == HUMAN_SUBJECT_SUPPLEMENT) {
					setHumaSubjectSupplementDetails(rrOtherProjectInfo,
                            organization, proposalSpecialReview);
				} else if (Integer.valueOf(proposalSpecialReview
						.getSpecialReviewType().getCode()) == VERTEBRATE_ANIMALS_SUPPLEMENT) {
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
		answerExplanation = getChildQuestionAnswer(ENVIRONMENTAL_EXEMPTION_YNQ, EXPLANATION, answerHeaders);
		String ynqAnswer = getAnswer(ENVIRONMENTAL_EXEMPTION_YNQ, answerHeaders);
		if (YnqConstant.YES.code().equals(ynqAnswer)) {
			answer = YesNoDataType.Y_YES;
		} else {
			answer = YesNoDataType.N_NO;
		}
		EnvironmentalImpact.EnvironmentalExemption environmentalExemption = EnvironmentalImpact.EnvironmentalExemption.Factory
        .newInstance();
		if (ynqAnswer != null && !ynqAnswer.equals(NOT_ANSWERED)) {
    		if (!YnqConstant.NA.code().equals(ynqAnswer)) {
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
    			} else if (YnqConstant.YES.code().equals(ynqAnswer)) {
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
		String internationalActivitiesAnswer = getAnswer(INTERNATIONAL_ACTIVITIES_YNQ, answerHeaders);
		if (internationalActivitiesAnswer != null && !internationalActivitiesAnswer.equals(NOT_ANSWERED)) {
    		answer = YnqConstant.YES.code().equals(
                    internationalActivitiesAnswer) ? YesNoDataType.Y_YES : YesNoDataType.N_NO;
    		answerExplanation = getAnswer(INTERNATIONAL_ACTIVITIES_EXPL, answerHeaders);
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
    			if (getChildQuestionAnswer(INTERNATIONAL_ACTIVITIES_YNQ, EXPLANATION, answerHeaders) != null) {
    			    internationalActivities.setInternationalActivitiesExplanation(getChildQuestionAnswer(INTERNATIONAL_ACTIVITIES_YNQ, EXPLANATION, answerHeaders));
    			}
    		} else if (YnqConstant.YES.code().equals(internationalActivitiesAnswer)) {
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
			OrganizationContract organization,
			ProposalSpecialReviewContract proposalSpecialReview) {
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
			OrganizationContract organization,
			ProposalSpecialReviewContract proposalSpecialReview) {
		rrOtherProjectInfo.
		setHumanSubjectsIndicator(YesNoDataType.Y_YES);
		HumanSubjectsSupplement humanSubjectsSupplement = HumanSubjectsSupplement.Factory
				.newInstance();
		HumanSubjectsSupplement.ExemptionNumbers exemptionNumbers = HumanSubjectsSupplement.ExemptionNumbers.Factory
				.newInstance();

		if (proposalSpecialReview.getApprovalType() != null) {
			
			
			setExemptions(proposalSpecialReview, humanSubjectsSupplement,
					exemptionNumbers);
			setHumanSubjectIRBReviewIndicator(proposalSpecialReview,
					humanSubjectsSupplement);
		}
		Boolean paramValue= s2SConfigurationService.getValueAsBoolean(ConfigurationConstants.IRB_PROTOCOL_DEVELOPMENT_PROPOSAL_LINKING_ENABLED);
		 if(paramValue){
			if (proposalSpecialReview.getSpecialReviewExemptions() != null && !proposalSpecialReview.getSpecialReviewExemptions().isEmpty()) {
				humanSubjectsSupplement.setExemptFedReg(YesNoDataType.Y_YES);				
				List<HumanSubjectsSupplement.ExemptionNumbers.ExemptionNumber.Enum> exemptionNumberList = new ArrayList<HumanSubjectsSupplement.ExemptionNumbers.ExemptionNumber.Enum>();
				HumanSubjectsSupplement.ExemptionNumbers.ExemptionNumber.Enum exemptionNumberEnum = null;
				for (ProposalSpecialReviewExemptionContract exemption : proposalSpecialReview.getSpecialReviewExemptions()) {
					exemptionNumberEnum = HumanSubjectsSupplement.ExemptionNumbers.ExemptionNumber.Enum
							.forInt(Integer.parseInt(exemption.getExemptionType().getCode()));
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
			ProposalSpecialReviewContract proposalSpecialReview,
			VertebrateAnimalsSupplement vertebrateAnimalsSupplement) {
		if (SPECIAL_REVIEW_ANIMAL_USAGE.equals(proposalSpecialReview
				.getApprovalType().getCode())) {
			vertebrateAnimalsSupplement
					.setVertebrateAnimalsIACUCReviewIndicator(YesNoDataType.Y_YES);
		} else {
			vertebrateAnimalsSupplement
					.setVertebrateAnimalsIACUCReviewIndicator(YesNoDataType.N_NO);
			if (proposalSpecialReview.getApprovalDate() != null) {
				vertebrateAnimalsSupplement
						.setVertebrateAnimalsIACUCApprovalDateReviewDate(s2SDateTimeService
								.convertDateToCalendar(proposalSpecialReview
										.getApprovalDate()));
			}
		}
		if (s2SConfigurationService.getValueAsBoolean(ConfigurationConstants.IACUC_PROTOCOL_PROPOSAL_DEVELOPMENT_LINKING_ENABLED_PARAMETER)) {
		    if (proposalSpecialReview.getApprovalDate() == null) {
		        vertebrateAnimalsSupplement.setVertebrateAnimalsIACUCReviewIndicator(YesNoDataType.Y_YES);
		    } else {
		        vertebrateAnimalsSupplement.setVertebrateAnimalsIACUCReviewIndicator(YesNoDataType.N_NO);
		    }
		}
	}

	/*
	 * This method will set the exemptions to human subjects supplement
	 */
	private void setExemptions(ProposalSpecialReviewContract proposalSpecialReview,
			HumanSubjectsSupplement humanSubjectsSupplement,
			HumanSubjectsSupplement.ExemptionNumbers exemptionNumbers) {
	
		if (Integer.parseInt(proposalSpecialReview.getApprovalType().getCode()) == APPROVAL_TYPE_EXCEMPT) {
			if (proposalSpecialReview.getSpecialReviewExemptions() != null) {
				List<HumanSubjectsSupplement.ExemptionNumbers.ExemptionNumber.Enum> exemptionNumberList = new ArrayList<HumanSubjectsSupplement.ExemptionNumbers.ExemptionNumber.Enum>();
				HumanSubjectsSupplement.ExemptionNumbers.ExemptionNumber.Enum exemptionNumberEnum = null;
				for (ProposalSpecialReviewExemptionContract exemption : proposalSpecialReview.getSpecialReviewExemptions()) {
					exemptionNumberEnum = HumanSubjectsSupplement.ExemptionNumbers.ExemptionNumber.Enum
							.forInt(Integer.parseInt(exemption.getExemptionType().getCode()));
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
			ProposalSpecialReviewContract proposalSpecialReview,
			HumanSubjectsSupplement humanSubjectsSupplement) {
        if (SPECIAL_REVIEW_HUMAN_SUBJECTS.equals(proposalSpecialReview
                .getApprovalType().getCode())) {
            humanSubjectsSupplement
                    .setHumanSubjectIRBReviewIndicator(YesNoDataType.Y_YES);
        } else {
            humanSubjectsSupplement
                    .setHumanSubjectIRBReviewIndicator(YesNoDataType.N_NO);
            if (proposalSpecialReview.getApprovalDate() != null) {
                humanSubjectsSupplement
                        .setHumanSubjectIRBReviewDate(s2SDateTimeService
                                .convertDateToCalendar(proposalSpecialReview
                                        .getApprovalDate()));
            }
        }
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
        for (NarrativeContract narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeType().getCode() != null) {
                switch(Integer.parseInt(narrative.getNarrativeType().getCode())){
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
	 * This method is used to get List of attachments from
	 * NarrativeAttachment
	 */
	private AttachedFileDataType[] getAttachedFileDataTypes() {
		AttachedFileDataType attachedFileDataType = null;
		List<AttachedFileDataType> attachedFileDataTypeList = new ArrayList<AttachedFileDataType>();
		DevelopmentProposalContract developmentProposal = pdDoc
				.getDevelopmentProposal();
		for (NarrativeContract narrative : developmentProposal.getNarratives()) {
			if (narrative.getNarrativeType().getCode() != null
					&& (Integer.parseInt(narrative.getNarrativeType().getCode()) == OTHER_ATTACHMENT)) {
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
	 * {@link ProposalDevelopmentDocumentContract}
	 * 
	 * @param proposalDevelopmentDocument
	 *            for which the {@link XmlObject} needs to be created
	 * @return {@link XmlObject} which is generated using the given
	 *         {@link ProposalDevelopmentDocumentContract}
	 */
	public XmlObject getFormObject(
			ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
		this.pdDoc = proposalDevelopmentDocument;
		return getRROtherProjectInfo();
	}

    public S2SConfigurationService getS2SConfigurationService() {
        return s2SConfigurationService;
    }

    public void setS2SConfigurationService(S2SConfigurationService s2SConfigurationService) {
        this.s2SConfigurationService = s2SConfigurationService;
    }

    @Override
    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    @Override
    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    @Override
    public Resource getStylesheet() {
        return stylesheet;
    }

    public void setStylesheet(Resource stylesheet) {
        this.stylesheet = stylesheet;
    }

    @Override
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }
}
