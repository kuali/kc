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
package org.kuali.coeus.s2sgen.impl.generate.support;

import gov.grants.apply.forms.rrOtherProjectInfoV11.RROtherProjectInfoDocument;
import gov.grants.apply.forms.rrOtherProjectInfoV11.RROtherProjectInfoDocument.RROtherProjectInfo;
import gov.grants.apply.forms.rrOtherProjectInfoV11.RROtherProjectInfoDocument.RROtherProjectInfo.*;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType.Enum;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.api.org.OrganizationContract;
import org.kuali.coeus.common.api.ynq.YnqConstant;
import org.kuali.coeus.common.questionnaire.api.answer.AnswerHeaderContract;
import org.kuali.coeus.propdev.api.specialreview.ProposalSpecialReviewContract;
import org.kuali.coeus.propdev.api.specialreview.ProposalSpecialReviewExemptionContract;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.propdev.api.attachment.NarrativeContract;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;


import java.util.ArrayList;
import java.util.List;

/**
 * Class for generating the XML object for grants.gov RROtherProjectInfoV1.1. Form is generated using XMLBean classes and is based
 * on RROtherProjectInfo schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@FormGenerator("RROtherProjectInfoV1_1Generator")
public class RROtherProjectInfoV1_1Generator extends RROtherProjectInfoBaseGenerator {

    @Value("http://apply.grants.gov/forms/RR_OtherProjectInfo-V1.1")
    private String namespace;

    @Value("RR_OtherProjectInfo-V1.1")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/RR_OtherProjectInfo-V1.1.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.rrOtherProjectInfoV11")
    private String packageName;

    @Value("140")
    private int sortIndex;

    private static final Log LOG = LogFactory.getLog(RROtherProjectInfoV1_1Generator.class);
    List<? extends AnswerHeaderContract> answerHeaders;

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
        rrOtherProjectInfo.setFormVersion(FormVersion.v1_1.getVersion());
        answerHeaders = getPropDevQuestionAnswerService().getQuestionnaireAnswerHeaders(pdDoc.getDevelopmentProposal().getProposalNumber());
        setHumanSubjAndVertebrateAnimals(rrOtherProjectInfo);
        Enum answer = YesNoDataType.N_NO;
        String answerExplanation = " ";

        /**
         * ProprietaryInformationIndicator is of YnQ type
         */
        EnvironmentalImpact environmentalImpact = EnvironmentalImpact.Factory.newInstance();
        InternationalActivities internationalActivities = InternationalActivities.Factory.newInstance();
        String propertyInformationAnswer = getAnswer(PROPRIETARY_INFORMATION_INDICATOR, answerHeaders);
        if(propertyInformationAnswer != null && !propertyInformationAnswer.equals(NOT_ANSWERED)){
            answer = (YnqConstant.YES.code().equals(propertyInformationAnswer) ? YesNoDataType.Y_YES : YesNoDataType.N_NO);
            rrOtherProjectInfo.setProprietaryInformationIndicator(answer);
        } else {
            rrOtherProjectInfo.setProprietaryInformationIndicator(null);
        }
        /**
         * EnvironmentalImpact is of YnQ type
         */
        String environmentalImpactAnswer = getAnswer(ENVIRONMENTAL_IMPACT_YNQ, answerHeaders);
        answer = (YnqConstant.YES.code().equals(environmentalImpactAnswer) ? YesNoDataType.Y_YES : YesNoDataType.N_NO);
        answerExplanation = getChildQuestionAnswer(ENVIRONMENTAL_IMPACT_YNQ, EXPLANATION, answerHeaders);
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
        if (YnqConstant.YES.code().equals(environmentalImpactAnswer) &&  answerExplanation == null) {
            environmentalImpact.setEnvironmentalImpactExplanation(answerExplanation);
        }
        if (answer.equals(YesNoDataType.Y_YES)) {
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
                    if (YnqConstant.YES.code().equals(ynqAnswer)) {
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
        String internationalActivitiesAnswer = getAnswer(INTERNATIONAL_ACTIVITIES_YNQ, answerHeaders);
        if (internationalActivitiesAnswer != null && !internationalActivitiesAnswer.equals(NOT_ANSWERED)) {
            answer = YnqConstant.YES.code().equals(internationalActivitiesAnswer) ? YesNoDataType.Y_YES : YesNoDataType.N_NO;
            answerExplanation = getAnswer(INTERNATIONAL_ACTIVITIES_EXPL, answerHeaders);
            internationalActivities.setInternationalActivitiesIndicator(answer);
            if (answerExplanation != null && !answerExplanation.equals(NOT_ANSWERED)) {
                internationalActivities.setActivitiesPartnershipsCountries(answerExplanation);
                if (getChildQuestionAnswer(INTERNATIONAL_ACTIVITIES_YNQ, EXPLANATION, answerHeaders) != null) {
                    internationalActivities.setInternationalActivitiesExplanation(getChildQuestionAnswer(INTERNATIONAL_ACTIVITIES_YNQ, EXPLANATION, answerHeaders));
                }
            }
            if (YnqConstant.YES.code().equals(internationalActivitiesAnswer) && answerExplanation == null) {
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
		OrganizationContract organization = pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization();
        rrOtherProjectInfo.setHumanSubjectsIndicator(YesNoDataType.N_NO);
        rrOtherProjectInfo.setVertebrateAnimalsIndicator(YesNoDataType.N_NO);
        for (ProposalSpecialReviewContract proposalSpecialReview : pdDoc.getDevelopmentProposal().getPropSpecialReviews()) {
            if (proposalSpecialReview.getSpecialReviewType() != null) {
                switch (Integer.parseInt(proposalSpecialReview.getSpecialReviewType().getCode())) {
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
                        if (SPECIAL_REVIEW_ANIMAL_USAGE.equals(proposalSpecialReview.getApprovalType().getCode())) {
                            vertebrateAnimalsSupplement.setVertebrateAnimalsIACUCReviewIndicator(YesNoDataType.Y_YES);
                        }
                        else {
                            vertebrateAnimalsSupplement.setVertebrateAnimalsIACUCReviewIndicator(YesNoDataType.N_NO);
                            if (proposalSpecialReview.getApprovalDate() != null) {
                                vertebrateAnimalsSupplement.setVertebrateAnimalsIACUCApprovalDateReviewDate(s2SDateTimeService
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
			ProposalSpecialReviewContract proposalSpecialReview) {
		rrOtherProjectInfo.setHumanSubjectsIndicator(YesNoDataType.Y_YES);
		HumanSubjectsSupplement humanSubjectsSupplement = HumanSubjectsSupplement.Factory.newInstance();
		HumanSubjectsSupplement.ExemptionNumbers exemptionNumbers = HumanSubjectsSupplement.ExemptionNumbers.Factory
		        .newInstance();
		if (proposalSpecialReview.getApprovalType() != null) {
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
		    }
		    if (SPECIAL_REVIEW_HUMAN_SUBJECTS.equals(proposalSpecialReview.getApprovalType().getCode())) {
		        humanSubjectsSupplement.setHumanSubjectIRBReviewIndicator(YesNoDataType.Y_YES);
		    }
		    else {
		        humanSubjectsSupplement.setHumanSubjectIRBReviewIndicator(YesNoDataType.N_NO);
		        if (proposalSpecialReview.getApprovalDate() != null) {
		            humanSubjectsSupplement.setHumanSubjectIRBReviewDate(s2SDateTimeService
		                    .convertDateToCalendar(proposalSpecialReview.getApprovalDate()));
		        }
		    }
		}
		return humanSubjectsSupplement;
	}


    /**
     * 
     * This method is used to get List of attachments from NarrativeAttachment
     * 
     * @return attachedFileDataTypes(AttachedFileDataType[])
     */
    private AttachedFileDataType[] getAttachedFileDataTypes() {
        LOG.info("Getting AttachedFileDataType ");
        List<AttachedFileDataType> attachedFileDataTypeList = new ArrayList<AttachedFileDataType>();
        AttachedFileDataType attachedFileDataType = null;
        for (NarrativeContract narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeType().getCode() != null
                    && (Integer.parseInt(narrative.getNarrativeType().getCode()) == OTHER_ATTACHMENT || Integer.parseInt(narrative.getNarrativeType().getCode()) == SUPPLIMENTARY_ATTACHMENT)) {
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
     * {@link ProposalDevelopmentDocumentContract}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocumentContract}
     */
    public XmlObject getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getRROtherProjectInfo();
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
