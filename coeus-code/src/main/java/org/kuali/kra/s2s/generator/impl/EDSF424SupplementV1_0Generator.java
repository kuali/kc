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

import gov.grants.apply.forms.edSF424SupplementV10.EDSF424SupplementDocument;
import gov.grants.apply.forms.edSF424SupplementV10.EDSF424SupplementDocument.EDSF424Supplement;
import gov.grants.apply.forms.edSF424SupplementV10.EDSF424SupplementDocument.EDSF424Supplement.AssuranceNumber;
import gov.grants.apply.forms.edSF424SupplementV10.EDSF424SupplementDocument.EDSF424Supplement.ExemptionsNumber;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.globalLibraryV10.YesNoDataType;
import gov.grants.apply.system.globalLibraryV10.YesNoNotApplicableDataType;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.common.specialreview.impl.bo.SpecialReviewExemption;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalYnq;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.specialreview.ProposalSpecialReview;
import org.kuali.kra.s2s.util.S2SConstants;

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
public class EDSF424SupplementV1_0Generator extends
		EDSF424SupplementBaseGenerator {

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
		edsf424Supplement.setFormVersion(S2SConstants.FORMVERSION_1_0);
		ProposalPerson pi = s2sUtilService.getPrincipalInvestigator(pdDoc);
		edsf424Supplement.setProjectDirector(globLibV10Generator
				.getContactPersonDataType(pi));
		String answer = null;
		for (ProposalYnq proposalYnq : pdDoc.getDevelopmentProposal()
				.getProposalYnqs()) {

			if (proposalYnq.getQuestionId() != null
					&& proposalYnq.getQuestionId().equals(
							PROPOSAL_YNQ_NOVICE_APPLICANT)) {
				if (proposalYnq.getAnswer() != null) {
					answer = proposalYnq.getAnswer();
				}
				if (S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(answer)) {
					edsf424Supplement
							.setIsNoviceApplicant(YesNoNotApplicableDataType.YES);
				} else if (S2SConstants.PROPOSAL_YNQ_ANSWER_N.equals(answer)) {
					edsf424Supplement
							.setIsNoviceApplicant(YesNoNotApplicableDataType.NO);
				} else if (S2SConstants.PROPOSAL_YNQ_ANSWER_NA.equals(answer)) {
					edsf424Supplement
							.setIsNoviceApplicant(YesNoNotApplicableDataType.NOT_APPLICABLE);
				}
			}
		}
		edsf424Supplement.setIsHumanResearch(YesNoDataType.NO);
		Organization organization = pdDoc.getDevelopmentProposal()
				.getApplicantOrganization().getOrganization();
		for (ProposalSpecialReview specialReview : pdDoc
				.getDevelopmentProposal().getPropSpecialReviews()) {
			if (specialReview.getSpecialReviewTypeCode() != null
					&& specialReview.getSpecialReviewTypeCode().equals(
							SPECIAL_REVIEW_CODE)) {
				edsf424Supplement.setIsHumanResearch(YesNoDataType.YES);
				if (specialReview.getApprovalTypeCode() != null
						&& specialReview.getApprovalTypeCode().equals(
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
					    for (SpecialReviewExemption exemption : specialReview.getSpecialReviewExemptions()) {
					        exemptionTypeCodes.add(exemption.getExemptionTypeCode());
					    }
						exemptionsNumber.setStringValue(s2sUtilService
						        .convertStringListToString(exemptionTypeCodes));
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
		for (Narrative narrative : pdDoc.getDevelopmentProposal()
				.getNarratives()) {
			if (narrative.getNarrativeTypeCode() != null
					&& Integer.parseInt(narrative.getNarrativeTypeCode()) == NARRATIVE_TYPE_ED_SF424_SUPPLIMENT) {
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
	 * {@link ProposalDevelopmentDocument}
	 * 
	 * @param proposalDevelopmentDocument
	 *            for which the {@link XmlObject} needs to be created
	 * @return {@link XmlObject} which is generated using the given
	 *         {@link ProposalDevelopmentDocument}
	 * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(ProposalDevelopmentDocument)
	 * 
	 */
	public XmlObject getFormObject(
			ProposalDevelopmentDocument proposalDevelopmentDocument) {

		this.pdDoc = proposalDevelopmentDocument;
		return getEDSF424Supplement();
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

		EDSF424SupplementDocument edsf4SupplementDocument = EDSF424SupplementDocument.Factory
				.newInstance();
		EDSF424Supplement edsf424Supplement = (EDSF424Supplement) xmlObject;
		edsf4SupplementDocument.setEDSF424Supplement(edsf424Supplement);
		return edsf4SupplementDocument;
	}
}
