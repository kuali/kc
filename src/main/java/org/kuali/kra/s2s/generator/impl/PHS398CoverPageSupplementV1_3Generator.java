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

import gov.grants.apply.forms.phs398CoverPageSupplement13V13.PHS398CoverPageSupplement13Document;
import gov.grants.apply.forms.phs398CoverPageSupplement13V13.PHS398CoverPageSupplement13Document.PHS398CoverPageSupplement13;
import gov.grants.apply.forms.phs398CoverPageSupplement13V13.PHS398CoverPageSupplement13Document.PHS398CoverPageSupplement13.ClinicalTrial;
import gov.grants.apply.forms.phs398CoverPageSupplement13V13.PHS398CoverPageSupplement13Document.PHS398CoverPageSupplement13.ContactPersonInfo;
import gov.grants.apply.forms.phs398CoverPageSupplement13V13.PHS398CoverPageSupplement13Document.PHS398CoverPageSupplement13.PDPI;
import gov.grants.apply.forms.phs398CoverPageSupplement13V13.PHS398CoverPageSupplement13Document.PHS398CoverPageSupplement13.StemCells;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType;

import java.util.List;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalYnq;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.generator.bo.DepartmentalPerson;
import org.kuali.kra.s2s.util.S2SConstants;

/**
 * Class for generating the XML object for grants.gov
 * PHS398CoverPageSupplementV1_3. Form is generated using XMLBean classes and is
 * based on PHS398CoverPageSupplement schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class PHS398CoverPageSupplementV1_3Generator extends
		PHS398CoverPageSupplementBaseGenerator {

	/**
	 * 
	 * This method gives information of Cover Page Supplement such as PDPI
	 * details,Clinical Trail information,Contact person information.
	 * 
	 * @return coverPageSupplementDocument {@link XmlObject} of type
	 *         PHS398CoverPageSupplement13Document.
	 */
	private PHS398CoverPageSupplement13Document getCoverPageSupplement() {
		PHS398CoverPageSupplement13Document coverPageSupplementDocument = PHS398CoverPageSupplement13Document.Factory
				.newInstance();
		PHS398CoverPageSupplement13 coverPageSupplement = PHS398CoverPageSupplement13.Factory
				.newInstance();
		coverPageSupplement.setFormVersion(S2SConstants.FORMVERSION_1_3);
		coverPageSupplement.setPDPI(getPDPI());
		coverPageSupplement.setClinicalTrial(getClinicalTrial());
		coverPageSupplement.setContactPersonInfo(getContactPersonInfo());
		StemCells stemCells = getStemCells();
		coverPageSupplement.setStemCells(stemCells);
		coverPageSupplementDocument
				.setPHS398CoverPageSupplement13(coverPageSupplement);
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
		if (proposalYnq != null && proposalYnq.getAnswer() != null) {
			pdpi.setIsNewInvestigator(getProposalYnQAnswer(proposalYnq));
		}
		return pdpi;
	}

	private YesNoDataType.Enum getProposalYnQAnswer(ProposalYnq proposalYnq) {
		return proposalYnq.getAnswer().equals(
				S2SConstants.PROPOSAL_YNQ_ANSWER_Y) ? YesNoDataType.Y_YES
				: YesNoDataType.N_NO;
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
        if (proposalYnq != null && proposalYnq.getAnswer() != null) {
            clinicalTrial
                    .setIsClinicalTrial(getProposalYnQAnswer(proposalYnq));
        }
		proposalYnq = getProposalYnQ(PHASE_III_CLINICAL_TRIAL);
		if (proposalYnq != null && proposalYnq.getAnswer() != null) {
			clinicalTrial
					.setIsPhaseIIIClinicalTrial(getProposalYnQAnswer(proposalYnq));
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
			if (proposalYnq.getAnswer() != null) {
				answer = getProposalYnQAnswer(proposalYnq);
			}
			stemCells.setIsHumanStemCellsInvolved(answer);
			if (YesNoDataType.Y_YES.equals(answer)) {
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
	 * {@link PHS398CoverPageSupplement13Document} by populating data from the
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
		PHS398CoverPageSupplement13 coverPageSupplement = (PHS398CoverPageSupplement13) xmlObject;
		PHS398CoverPageSupplement13Document coverPageSupplementDocument = PHS398CoverPageSupplement13Document.Factory
				.newInstance();
		coverPageSupplementDocument
				.setPHS398CoverPageSupplement13(coverPageSupplement);
		return coverPageSupplementDocument;
	}
}
