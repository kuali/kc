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

import gov.grants.apply.forms.edSF424SupplementV11.EDSF424SupplementDocument;
import gov.grants.apply.forms.edSF424SupplementV11.EDSF424SupplementDocument.EDSF424Supplement;
import gov.grants.apply.forms.edSF424SupplementV11.EDSF424SupplementDocument.EDSF424Supplement.AssuranceNumber;
import gov.grants.apply.forms.edSF424SupplementV11.EDSF424SupplementDocument.EDSF424Supplement.ExemptionsNumber;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType;
import gov.grants.apply.system.globalLibraryV20.YesNoNotApplicableDataType;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.api.org.OrganizationContract;
import org.kuali.coeus.common.api.ynq.YnqConstant;
import org.kuali.coeus.common.questionnaire.api.answer.AnswerContract;
import org.kuali.coeus.common.questionnaire.api.answer.AnswerHeaderContract;
import org.kuali.coeus.propdev.api.person.ProposalPersonContract;
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

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class is used to generate XML Document object for grants.gov
 * EDSF424SupplementV1.1. This form is generated using XMLBean API's generated
 * by compiling EDSF424SupplementV1.1 schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@FormGenerator("EDSF424SupplementV1_1Generator")
public class EDSF424SupplementV1_1Generator extends
		EDSF424SupplementBaseGenerator {

    @Value("http://apply.grants.gov/forms/ED_SF424_Supplement-V1.1")
    private String namespace;

    @Value("ED_SF424_Supplement-V1.1")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/ED_SF424_Supplement-V1.1.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.edSF424SupplementV11")
    private String packageName;

    @Value(DEFAULT_SORT_INDEX)
    private int sortIndex;

    @Autowired
    @Qualifier("s2SConfigurationService")
    private S2SConfigurationService s2SConfigurationService;

	/**
	 * 
	 * This method returns EDSF424SupplementDocument object based on proposal
	 * development document which contains the EDSF424SupplementDocument
	 * informations
	 * NoviceApplicant,HumanResearch,HumanResearchExempt,ExemptionsNumber,AssuranceNumber,and
	 * attachment for a particular proposal
	 * 
	 * @return edsf424SupplementDocument(EDSF424SupplementDocument)
	 *         {@link XmlObject} of type EDSF424SupplementDocument.
	 */
	private EDSF424SupplementDocument getEDSF424Supplement() {

		EDSF424SupplementDocument edsf424SupplementDocument = EDSF424SupplementDocument.Factory
				.newInstance();
		EDSF424Supplement edsf424Supplement = EDSF424Supplement.Factory
				.newInstance();
		edsf424Supplement.setFormVersion(FormVersion.v1_1.getVersion());
		ProposalPersonContract pi = getS2SProposalPersonService().getPrincipalInvestigator(pdDoc);
		edsf424Supplement.setProjectDirector(globLibV20Generator
				.getContactPersonDataType(pi));
		String answer = null;
		List<? extends AnswerHeaderContract> answerHeaders = getPropDevQuestionAnswerService().getQuestionnaireAnswerHeaders(pdDoc.getDevelopmentProposal().getProposalNumber());
		if (answerHeaders != null && !answerHeaders.isEmpty()) {
            for (AnswerHeaderContract answerHeader : answerHeaders) {
                List<? extends AnswerContract> answerDetails = answerHeader.getAnswers();
                for (AnswerContract answers : answerDetails) {
                    Integer seqId = getQuestionAnswerService().findQuestionById(answers.getQuestionId()).getQuestionSeqId();
                    if (seqId != null
                            && seqId.equals(
                                    PROPOSAL_YNQ_NOVICE_APPLICANT)) {
                        if (answers.getAnswer() != null) {
                            answer = answers.getAnswer();
                        }
                        if (YnqConstant.YES.code().equals(answer)) {
                            edsf424Supplement
                                    .setIsNoviceApplicant(YesNoNotApplicableDataType.Y_YES);
                        } else if (YnqConstant.NO.code().equals(answer)) {
                            edsf424Supplement
                                    .setIsNoviceApplicant(YesNoNotApplicableDataType.N_NO);
                        } else if (YnqConstant.NA.code().equals(answer)) {
                            edsf424Supplement
                                    .setIsNoviceApplicant(YesNoNotApplicableDataType.NA_NOT_APPLICABLE);
                        }
                    }
                }
            }
        }

		edsf424Supplement.setIsHumanResearch(YesNoDataType.N_NO);
		OrganizationContract organization = pdDoc.getDevelopmentProposal()
				.getApplicantOrganization().getOrganization();
		for (ProposalSpecialReviewContract specialReview : pdDoc
				.getDevelopmentProposal().getPropSpecialReviews()) {
			if (specialReview.getSpecialReviewType() != null
					&& specialReview.getSpecialReviewType().getCode().equals(
                    SPECIAL_REVIEW_CODE)) {
				edsf424Supplement.setIsHumanResearch(YesNoDataType.Y_YES);
				if (specialReview.getApprovalType() != null
						&& specialReview.getApprovalType().getCode().equals(
                        APPROVAL_TYPE_CODE)) {
					edsf424Supplement
							.setIsHumanResearchExempt(YesNoDataType.Y_YES);
					ExemptionsNumber exemptionsNumber = ExemptionsNumber.Factory
							.newInstance();
					exemptionsNumber
							.setIsHumanResearchExempt(YesNoDataType.Y_YES);
					if (specialReview.getSpecialReviewExemptions() != null
							&& specialReview.getSpecialReviewExemptions().size() > 0) {
					    List<String> exemptionTypeCodes = new ArrayList<String>();
					    for (ProposalSpecialReviewExemptionContract exemption : specialReview.getSpecialReviewExemptions()) {
					        exemptionTypeCodes.add(exemption.getExemptionType().getCode());
					    }
						exemptionsNumber.setStringValue(
                                colToString(exemptionTypeCodes));
					}
					edsf424Supplement.setExemptionsNumber(exemptionsNumber);
				} else {
					edsf424Supplement
							.setIsHumanResearchExempt(YesNoDataType.N_NO);
					if (organization != null) {
						AssuranceNumber assuranceNumber = AssuranceNumber.Factory
								.newInstance();
						assuranceNumber
								.setIsHumanResearchExempt(YesNoDataType.N_NO);
						if (organization.getHumanSubAssurance() != null) {
							assuranceNumber.setStringValue(organization
									.getHumanSubAssurance());
						}
						edsf424Supplement.setAssuranceNumber(assuranceNumber);
					}
				}
				Boolean paramValue= s2SConfigurationService.getValueAsBoolean(ConfigurationConstants.IRB_PROTOCOL_DEVELOPMENT_PROPOSAL_LINKING_ENABLED);
			    if(paramValue){
			    	ExemptionsNumber exemptionsNumber = ExemptionsNumber.Factory
					.newInstance();
			    	if (specialReview.getSpecialReviewExemptions() != null
							&& specialReview.getSpecialReviewExemptions().size() > 0) {
			    		edsf424Supplement
						.setIsHumanResearchExempt(YesNoDataType.Y_YES);
			    		exemptionsNumber
						.setIsHumanResearchExempt(YesNoDataType.Y_YES);	
			    		List<String> exemptionTypeCodes = new ArrayList<String>();
					    for (ProposalSpecialReviewExemptionContract exemption : specialReview.getSpecialReviewExemptions()) {
					        exemptionTypeCodes.add(exemption.getExemptionType().getCode());
					    }
						exemptionsNumber.setStringValue(
                                colToString(exemptionTypeCodes));
						edsf424Supplement.setExemptionsNumber(exemptionsNumber);
			    	} else {
			    		edsf424Supplement.setIsHumanResearch(YesNoDataType.N_NO);
			    		if (organization != null) {
							AssuranceNumber assuranceNumber = AssuranceNumber.Factory
									.newInstance();
							assuranceNumber
									.setIsHumanResearchExempt(YesNoDataType.N_NO);
							if (organization.getHumanSubAssurance() != null) {
								assuranceNumber.setStringValue(organization
										.getHumanSubAssurance());
							}
							edsf424Supplement.setAssuranceNumber(assuranceNumber);
						}
			    		}}
				break;
			} else {
				edsf424Supplement.setIsHumanResearch(YesNoDataType.N_NO);
			}
			
		    	}
		AttachedFileDataType attachedFileDataType = null;
		for (NarrativeContract narrative : pdDoc.getDevelopmentProposal()
				.getNarratives()) {
			if (narrative.getNarrativeType().getCode() != null
					&& Integer.parseInt(narrative.getNarrativeType().getCode()) == NARRATIVE_TYPE_ED_SF424_SUPPLIMENT) {
				attachedFileDataType = getAttachedFileType(narrative);
				if(attachedFileDataType != null){
					edsf424Supplement.setAttachment(attachedFileDataType);
					break;
				}
			}
		}
		edsf424SupplementDocument.setEDSF424Supplement(edsf424Supplement);
		return edsf424SupplementDocument;
	}

	/**
	 * This method creates {@link XmlObject} of type
	 * {@link EDSF424SupplementDocument} by populating data from the given
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
		return getEDSF424Supplement();
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
