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

import gov.grants.apply.forms.edSF424SupplementV10.EDSF424SupplementDocument;
import gov.grants.apply.forms.edSF424SupplementV10.EDSF424SupplementDocument.EDSF424Supplement;
import gov.grants.apply.forms.edSF424SupplementV10.EDSF424SupplementDocument.EDSF424Supplement.AssuranceNumber;
import gov.grants.apply.forms.edSF424SupplementV10.EDSF424SupplementDocument.EDSF424Supplement.ExemptionsNumber;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.globalLibraryV10.YesNoDataType;
import gov.grants.apply.system.globalLibraryV10.YesNoNotApplicableDataType;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.api.org.OrganizationContract;
import org.kuali.coeus.common.api.ynq.YnqConstant;
import org.kuali.coeus.propdev.api.attachment.NarrativeContract;
import org.kuali.coeus.propdev.api.person.ProposalPersonContract;
import org.kuali.coeus.propdev.api.specialreview.ProposalSpecialReviewContract;
import org.kuali.coeus.propdev.api.specialreview.ProposalSpecialReviewExemptionContract;
import org.kuali.coeus.propdev.api.ynq.ProposalYnqContract;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;


import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class is used to generate XML Document object for grants.gov
 * EDSF424SupplementV1.0. This form is generated using XMLBean API's generated
 * by compiling EDSF424SupplementV1.0 schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@FormGenerator("EDSF424SupplementV1_0Generator")
public class EDSF424SupplementV1_0Generator extends
		EDSF424SupplementBaseGenerator {

    @Value("http://apply.grants.gov/forms/ED_SF424_Supplement-V1.0")
    private String namespace;

    @Value("ED_SF424_Supplement-V1.0")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/ED_SF424_Supplement-V1.0.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.edSF424SupplementV10")
    private String packageName;

    @Value(DEFAULT_SORT_INDEX)
    private int sortIndex;

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
		edsf424Supplement.setFormVersion(FormVersion.v1_0.getVersion());
		ProposalPersonContract pi = getS2SProposalPersonService().getPrincipalInvestigator(pdDoc);
		edsf424Supplement.setProjectDirector(globLibV10Generator
				.getContactPersonDataType(pi));
		String answer = null;
		for (ProposalYnqContract proposalYnq : pdDoc.getDevelopmentProposal()
				.getProposalYnqs()) {

			if (proposalYnq.getYnq() != null
					&& proposalYnq.getYnq().getQuestionId().equals(
							PROPOSAL_YNQ_NOVICE_APPLICANT)) {
				if (proposalYnq.getAnswer() != null) {
					answer = proposalYnq.getAnswer();
				}
				if (YnqConstant.YES.code().equals(answer)) {
					edsf424Supplement
							.setIsNoviceApplicant(YesNoNotApplicableDataType.YES);
				} else if (YnqConstant.NO.code().equals(answer)) {
					edsf424Supplement
							.setIsNoviceApplicant(YesNoNotApplicableDataType.NO);
				} else if (YnqConstant.NA.code().equals(answer)) {
					edsf424Supplement
							.setIsNoviceApplicant(YesNoNotApplicableDataType.NOT_APPLICABLE);
				}
			}
		}
		edsf424Supplement.setIsHumanResearch(YesNoDataType.NO);
		OrganizationContract organization = pdDoc.getDevelopmentProposal()
				.getApplicantOrganization().getOrganization();
		for (ProposalSpecialReviewContract specialReview : pdDoc
				.getDevelopmentProposal().getPropSpecialReviews()) {
			if (specialReview.getSpecialReviewType() != null
					&& specialReview.getSpecialReviewType().getCode().equals(
                    SPECIAL_REVIEW_CODE)) {
				edsf424Supplement.setIsHumanResearch(YesNoDataType.YES);
				if (specialReview.getApprovalType() != null
						&& specialReview.getApprovalType().getCode().equals(
                        APPROVAL_TYPE_CODE)) {
					edsf424Supplement
							.setIsHumanResearchExempt(YesNoDataType.YES);
					ExemptionsNumber exemptionsNumber = ExemptionsNumber.Factory
							.newInstance();
					exemptionsNumber
							.setIsHumanResearchExempt(YesNoDataType.YES);
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
							.setIsHumanResearchExempt(YesNoDataType.NO);
					if (organization != null) {
						AssuranceNumber assuranceNumber = AssuranceNumber.Factory
								.newInstance();
						assuranceNumber
								.setIsHumanResearchExempt(YesNoDataType.NO);
						if (organization.getHumanSubAssurance() != null) {
							assuranceNumber.setStringValue(organization
									.getHumanSubAssurance());
						}
						edsf424Supplement.setAssuranceNumber(assuranceNumber);
					}
				}
				break;
			} else {
				edsf424Supplement.setIsHumanResearch(YesNoDataType.NO);
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
	 * 
	 */
	public XmlObject getFormObject(
			ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {

		this.pdDoc = proposalDevelopmentDocument;
		return getEDSF424Supplement();
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
