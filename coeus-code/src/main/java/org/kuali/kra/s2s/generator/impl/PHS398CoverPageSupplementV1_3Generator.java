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

import gov.grants.apply.forms.phs398CoverPageSupplement13V13.PHS398CoverPageSupplement13Document;
import gov.grants.apply.forms.phs398CoverPageSupplement13V13.PHS398CoverPageSupplement13Document.PHS398CoverPageSupplement13;
import gov.grants.apply.forms.phs398CoverPageSupplement13V13.PHS398CoverPageSupplement13Document.PHS398CoverPageSupplement13.ClinicalTrial;
import gov.grants.apply.forms.phs398CoverPageSupplement13V13.PHS398CoverPageSupplement13Document.PHS398CoverPageSupplement13.ContactPersonInfo;
import gov.grants.apply.forms.phs398CoverPageSupplement13V13.PHS398CoverPageSupplement13Document.PHS398CoverPageSupplement13.PDPI;
import gov.grants.apply.forms.phs398CoverPageSupplement13V13.PHS398CoverPageSupplement13Document.PHS398CoverPageSupplement13.StemCells;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType.Enum;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.ynq.ProposalYnq;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.kra.questionnaire.answer.Answer;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.s2s.generator.bo.DepartmentalPerson;
import org.kuali.kra.s2s.util.S2SConstants;

import java.util.List;

/**
 * Class for generating the XML object for grants.gov
 * PHS398CoverPageSupplementV1_3. Form is generated using XMLBean classes and is
 * based on PHS398CoverPageSupplement schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class PHS398CoverPageSupplementV1_3Generator extends
		PHS398CoverPageSupplementBaseGenerator {
    
    List<AnswerHeader> answerHeaders;
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
		answerHeaders = getQuestionnaireAnswers(pdDoc.getDevelopmentProposal(), true);
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
        String answer = null;
        String subAnswer = null;
        answer = getAnswer(IS_CLINICAL_TRIAL);
        if (answer != null) {
            if (!answer.equals(NOT_ANSWERED)) {
                if (S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(answer)) {
                    clinicalTrial.setIsClinicalTrial(YesNoDataType.Y_YES);
                    subAnswer = getAnswer(PHASE_III_CLINICAL_TRIAL);
                        if (subAnswer != null && !subAnswer.equals(NOT_ANSWERED)) {
                            if (S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(subAnswer)) {
                                clinicalTrial.setIsPhaseIIIClinicalTrial(YesNoDataType.Y_YES);   
                            } else {
                                clinicalTrial.setIsPhaseIIIClinicalTrial(YesNoDataType.N_NO);   
                            }

                        } else {
                            clinicalTrial.setIsPhaseIIIClinicalTrial(null);
                        }
                } else {
                    clinicalTrial.setIsClinicalTrial(YesNoDataType.N_NO);
                }
            }
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
	    Enum answers = YesNoDataType.N_NO;
	    String childAnswer = null;  
	    String answer = getAnswer(IS_HUMAN_STEM_CELLS_INVOLVED);
	    if (answer != null) {
	        if (!answer.equals(NOT_ANSWERED)) {
	            answers = S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(getAnswer(IS_HUMAN_STEM_CELLS_INVOLVED)) ? YesNoDataType.Y_YES : YesNoDataType.N_NO;
	            if (S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(answer)) {
	                stemCells.setIsHumanStemCellsInvolved(YesNoDataType.Y_YES);
	                String subAnswer = getAnswer(SPECIFIC_STEM_CELL_LINE);
	                if (subAnswer != null) {
	                    if(!subAnswer.equals(NOT_ANSWERED)) {
	                        if (S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(subAnswer)) {
	                            stemCells.setStemCellsIndicator(YesNoDataType.N_NO);
	                            childAnswer = getAnswers(REGISTRATION_NUMBER);
	                        }
	                        else {
	                            stemCells.setStemCellsIndicator(YesNoDataType.Y_YES);
	                        }
	                    }
	                }
	                if (childAnswer != null) {
	                    if (S2SConstants.VALUE_UNKNOWN.equalsIgnoreCase(childAnswer)) {
	                        stemCells.setStemCellsIndicator(answers);
	                    } else {
	                        List<String> cellLines = getCellLines(childAnswer);
	                        if (cellLines.size() > 0) {
	                            stemCells.setCellLinesArray(cellLines.toArray(new String[0]));
	                        }
	                    }
	                }
	            } else {
	                stemCells.setIsHumanStemCellsInvolved(YesNoDataType.N_NO); 
	            }
	        }
	    }
	    return stemCells;
	}


    /*
     * This method will get the Answer for sub question
     */
    private String getAnswer(String questionId) {

        String answer = null;
        if (answerHeaders != null && !answerHeaders.isEmpty()) {
            for (AnswerHeader answerHeader : answerHeaders) {
                List<Answer> answerDetails = answerHeader.getAnswers();
                for (Answer answers : answerDetails) {
                    if (questionId.equals(answers.getQuestion().getQuestionId())) {
                        answer = answers.getAnswer();
                    }
                }
            }
        }
        return answer;   
    }

    /*
     * This method will get the childAnswer for sub question
     */
    private String getAnswers(String questionId) {

        String answer = null;
        String childAnswer = null;
        StringBuilder stringBuilder = new StringBuilder();
        if (answerHeaders != null && !answerHeaders.isEmpty()) {
            for (AnswerHeader answerHeader : answerHeaders) {
                List<Answer> answerDetails = answerHeader.getAnswers();
                for (Answer answers : answerDetails) {
                    if (questionId.equals(answers.getQuestion().getQuestionId())) {
                        answer = answers.getAnswer();
                        if (answer != null) {
                            if (!answer.equals(NOT_ANSWERED)) {
                                stringBuilder.append(answer);
                                stringBuilder.append(",");
                            }
                        }
                        childAnswer = stringBuilder.toString();
                    }
                }
            }
        }
        return childAnswer;
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
