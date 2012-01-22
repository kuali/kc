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

import gov.grants.apply.forms.phs398CoverPageSupplementV11.PHS398CoverPageSupplementDocument;
import gov.grants.apply.forms.phs398CoverPageSupplementV11.PHS398CoverPageSupplementDocument.PHS398CoverPageSupplement;
import gov.grants.apply.forms.phs398CoverPageSupplementV11.PHS398CoverPageSupplementDocument.PHS398CoverPageSupplement.ClinicalTrial;
import gov.grants.apply.forms.phs398CoverPageSupplementV11.PHS398CoverPageSupplementDocument.PHS398CoverPageSupplement.ContactPersonInfo;
import gov.grants.apply.forms.phs398CoverPageSupplementV11.PHS398CoverPageSupplementDocument.PHS398CoverPageSupplement.PDPI;
import gov.grants.apply.forms.phs398CoverPageSupplementV11.PHS398CoverPageSupplementDocument.PHS398CoverPageSupplement.StemCells;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType;

import java.util.List;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonDegree;
import org.kuali.kra.proposaldevelopment.bo.ProposalYnq;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.generator.bo.DepartmentalPerson;
import org.kuali.kra.s2s.service.S2SUtilService;
import org.kuali.kra.s2s.util.S2SConstants;

/**
 * Class for generating the XML object for grants.gov
 * PHS398CoverPageSupplementV1_1. Form is generated using XMLBean classes and is
 * based on PHS398CoverPageSupplement schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class PHS398CoverPageSupplementV1_1Generator extends
		PHS398CoverPageSupplementBaseGenerator {

	private static final String OSP_ADMINISTRATOR = "2";

	/**
	 * 
	 * Constructs a PHS398CoverPageSupplementV1_1Generator.java.
	 */
	public PHS398CoverPageSupplementV1_1Generator() {
		s2sUtilService = KraServiceLocator.getService(S2SUtilService.class);
	}

	/**
	 * 
	 * This method gives information of Cover Page Supplement such as PDPI
	 * details,Clinical Trail information,Contact person information.
	 * 
	 * @return coverPageSupplementDocument {@link XmlObject} of type
	 *         PHS398CoverPageSupplementDocument.
	 */
	private PHS398CoverPageSupplementDocument getCoverPageSupplement() {

		PHS398CoverPageSupplementDocument coverPageSupplementDocument = PHS398CoverPageSupplementDocument.Factory
				.newInstance();
		PHS398CoverPageSupplement coverPageSupplement = PHS398CoverPageSupplement.Factory
				.newInstance();
		coverPageSupplement.setFormVersion(S2SConstants.FORMVERSION_1_1);
		coverPageSupplement.setPDPI(getPDPI());
		coverPageSupplement.setClinicalTrial(getClinicalTrial());
		coverPageSupplement.setContactPersonInfo(getContactPersonInfo());
		StemCells stemCells = getStemCells();
		coverPageSupplement.setStemCells(stemCells);
		coverPageSupplementDocument
				.setPHS398CoverPageSupplement(coverPageSupplement);
		return coverPageSupplementDocument;
	}

	/**
	 * 
	 * This method gives the personal details such as Name, New Investigator,
	 * Degrees of Principal Investigator
	 * 
	 * @return PDPI object containing Principal Investigator details.
	 */
	private PDPI getPDPI() {

		PDPI pdpi = PDPI.Factory.newInstance();
		ProposalPerson PI = s2sUtilService.getPrincipalInvestigator(pdDoc);
		pdpi.setPDPIName(globLibV20Generator.getHumanNameDataType(PI));
		// Set default values for mandatory fields
		pdpi.setIsNewInvestigator(YesNoDataType.N_NO);

		ProposalYnq proposalYnq = getProposalYnQ(IS_NEW_INVESTIGATOR);
		if (PI != null) {

			if (proposalYnq != null) {
				YesNoDataType.Enum answer = null;
				if (proposalYnq.getAnswer() != null) {
					answer = (proposalYnq.getAnswer().equals(
							S2SConstants.PROPOSAL_YNQ_ANSWER_Y) ? YesNoDataType.Y_YES
							: YesNoDataType.N_NO);
					pdpi.setIsNewInvestigator(answer);
				}

			}
			String[] degreeArr = null;
			if (PI.getProposalPersonDegrees() != null) {
				degreeArr = new String[PI.getProposalPersonDegrees().size()];
			}
			int size = 0;
			for (ProposalPersonDegree personDegree : PI
					.getProposalPersonDegrees()) {
				// Degrees: 0...3
				if (size > MAX_NUMBER_OF_DEGREES) {
					break;
				}
				if (personDegree.getDegree() != null) {
					if (personDegree.getDegree().length() > PERSON_DEGREE_MAX_LENGTH) {
						degreeArr[size] = personDegree.getDegree().substring(0,
								PERSON_DEGREE_MAX_LENGTH);
					} else {
						degreeArr[size] = personDegree.getDegree();
					}
					size++;
				}
			}
			pdpi.setDegreesArray(degreeArr);
		}
		return pdpi;
	}

	/**
	 * 
	 * This method is used to get Clinical Trial information
	 * 
	 * @return ClinicalTrial object containing Clinical Trail Details.
	 */
	private ClinicalTrial getClinicalTrial() {

		ClinicalTrial clinicalTrial = ClinicalTrial.Factory.newInstance();
        ProposalYnq proposalYnq = getProposalYnQ(IS_CLINICAL_TRIAL);
        if (proposalYnq != null) {
            YesNoDataType.Enum answer = null;
            if (proposalYnq.getAnswer() != null) {
                answer = (proposalYnq.getAnswer().equals(
                        S2SConstants.PROPOSAL_YNQ_ANSWER_Y) ? YesNoDataType.Y_YES
                        : YesNoDataType.N_NO);
            }
            clinicalTrial.setIsClinicalTrial(answer);
        }
		proposalYnq = getProposalYnQ(PHASE_III_CLINICAL_TRIAL);
		if (proposalYnq != null) {
			YesNoDataType.Enum answer = null;
			if (proposalYnq.getAnswer() != null) {
				answer = (proposalYnq.getAnswer().equals(
						S2SConstants.PROPOSAL_YNQ_ANSWER_Y) ? YesNoDataType.Y_YES
						: YesNoDataType.N_NO);
			}
			clinicalTrial.setIsPhaseIIIClinicalTrial(answer);
		}
		return clinicalTrial;
	}

	/**
	 * 
	 * This method gives the Contact person information such as contact
	 * Name,Phone,Fax,EmailAddress,Title,Address.
	 * 
	 * @return ContactPersonInfo object containing contact person details.
	 */
	private ContactPersonInfo getContactPersonInfo() {
		ContactPersonInfo contactPersonInfo = ContactPersonInfo.Factory
				.newInstance();
		DepartmentalPerson person = s2sUtilService.getContactPerson(pdDoc);
		contactPersonInfo.setContactName(globLibV20Generator
				.getHumanNameDataType(person));
		contactPersonInfo.setContactPhone(person.getOfficePhone());
		if (person.getFaxNumber() != null
				&& !person.getFaxNumber().equals("")) {
			contactPersonInfo.setContactFax(person.getFaxNumber());
		}
		if (person.getEmailAddress() != null
				&& !person.getEmailAddress().equals("")) {
			contactPersonInfo.setContactEmail(person
					.getEmailAddress());
		}
		contactPersonInfo.setContactTitle(person.getPrimaryTitle());
		contactPersonInfo.setContactAddress(globLibV20Generator
				.getAddressDataType(person));
		return contactPersonInfo;
	}

	/**
	 * 
	 * This method is used to get information of Stem Cells for the form
	 * PHS398CoverPage
	 * 
	 * @return StemCells object containing information about Human stem Cells
	 *         involvement.
	 */
	private StemCells getStemCells() {

		StemCells stemCells = StemCells.Factory.newInstance();
		YesNoDataType.Enum answer = null;
		ProposalYnq proposalYnq = getProposalYnQ(IS_HUMAN_STEM_CELLS_INVOLVED);
		if (proposalYnq != null) {
			if (S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(proposalYnq
					.getAnswer())) {
				answer = YesNoDataType.Y_YES;
			} else {
				answer = YesNoDataType.N_NO;
			}
			stemCells.setIsHumanStemCellsInvolved(answer);
			if (answer.equals(YesNoDataType.Y_YES)) {
				String explanation = proposalYnq.getExplanation();

				if (explanation != null) {
					if (S2SConstants.VALUE_UNKNOWN
							.equalsIgnoreCase(explanation)) {
						stemCells.setStemCellsIndicator(answer);
					} else {
						List<String> cellLines = getCellLines(explanation);
						if (cellLines.size() > 0) {
							stemCells.setCellLinesArray(cellLines
									.toArray(new String[0]));
						}
					}
				}
			}
		}
		return stemCells;
	}

	/**
	 * This method creates {@link XmlObject} of type
	 * {@link PHS398CoverPageSupplementDocument} by populating data from the
	 * given {@link ProposalDevelopmentDocument}
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
		return getCoverPageSupplement();
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
		PHS398CoverPageSupplement coverPageSupplement = (PHS398CoverPageSupplement) xmlObject;
		PHS398CoverPageSupplementDocument coverPageSupplementDocument = PHS398CoverPageSupplementDocument.Factory
				.newInstance();
		coverPageSupplementDocument
				.setPHS398CoverPageSupplement(coverPageSupplement);
		return coverPageSupplementDocument;
	}
}
