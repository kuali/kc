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

import gov.grants.apply.forms.phs398CoverPageSupplementV10.PHS398CoverPageSupplementDocument;
import gov.grants.apply.forms.phs398CoverPageSupplementV10.PHS398CoverPageSupplementDocument.PHS398CoverPageSupplement;
import gov.grants.apply.forms.phs398CoverPageSupplementV10.PHS398CoverPageSupplementDocument.PHS398CoverPageSupplement.ClinicalTrial;
import gov.grants.apply.forms.phs398CoverPageSupplementV10.PHS398CoverPageSupplementDocument.PHS398CoverPageSupplement.ContactPersonInfo;
import gov.grants.apply.forms.phs398CoverPageSupplementV10.PHS398CoverPageSupplementDocument.PHS398CoverPageSupplement.PDPI;
import gov.grants.apply.forms.phs398CoverPageSupplementV10.PHS398CoverPageSupplementDocument.PHS398CoverPageSupplement.StemCells;
import gov.grants.apply.system.globalLibraryV10.YesNoDataType;
import gov.grants.apply.system.globalLibraryV10.YesNoDataType.Enum;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.ynq.ProposalYnq;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonDegree;
import org.kuali.kra.questionnaire.answer.Answer;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.s2s.generator.bo.DepartmentalPerson;
import org.kuali.kra.s2s.util.S2SConstants;

import java.util.List;

/**
 * Class for generating the XML object for grants.gov
 * PHS398CoverPageSupplementV1_0. Form is generated using XMLBean classes and is
 * based on PHS398CoverPageSupplement schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class PHS398CoverPageSupplementV1_0Generator extends
		PHS398CoverPageSupplementBaseGenerator {
    
    List<AnswerHeader> answerHeaders;
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
		answerHeaders = getQuestionnaireAnswers(pdDoc.getDevelopmentProposal(), true);
		coverPageSupplement.setFormVersion(S2SConstants.FORMVERSION_1_0);
		coverPageSupplement.setPDPI(getPDPI());
		coverPageSupplement.setClinicalTrial(getClinicalTrial());
		coverPageSupplement.setContactPersonInfo(getContactPersonInfo(pdDoc));
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
		pdpi.setPDPIName(globLibV10Generator.getHumanNameDataType(PI));
		// Set default values for mandatory fields
		pdpi.setIsNewInvestigator(YesNoDataType.NO);

		if (PI != null) {
			ProposalYnq proposalYnq = getProposalYnQ(IS_NEW_INVESTIGATOR);
			if (proposalYnq != null) {
				YesNoDataType.Enum answer = null;
				if (proposalYnq.getAnswer() != null) {
					answer = (proposalYnq.getAnswer().equals(
							S2SConstants.PROPOSAL_YNQ_ANSWER_Y) ? YesNoDataType.YES
							: YesNoDataType.NO);
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
        String answer = null;
        String subAnswer = null;
        answer = getAnswer(IS_CLINICAL_TRIAL);
        if (answer != null) {
            if (!answer.equals(NOT_ANSWERED)) {
                if (S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(answer)) {
                    clinicalTrial.setIsClinicalTrial(YesNoDataType.YES);
                    subAnswer = getAnswer(PHASE_III_CLINICAL_TRIAL);
                    if (subAnswer != null) {
                        if (!subAnswer.equals(NOT_ANSWERED)) {
                            if (S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(subAnswer)) {
                                clinicalTrial.setIsPhaseIIIClinicalTrial(YesNoDataType.YES);   
                            } else {
                                clinicalTrial.setIsPhaseIIIClinicalTrial(YesNoDataType.NO);   
                            }

                        }
                    }
                } else {
                    clinicalTrial.setIsClinicalTrial(YesNoDataType.NO);
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
	private ContactPersonInfo getContactPersonInfo(
			ProposalDevelopmentDocument pdDoc) {
		ContactPersonInfo contactPersonInfo = ContactPersonInfo.Factory
				.newInstance();
		DepartmentalPerson contactPerson = s2sUtilService.getContactPerson(pdDoc);
		if (contactPerson != null) {
			contactPersonInfo.setContactName(globLibV10Generator
					.getHumanNameDataType(contactPerson));
			contactPersonInfo.setContactPhone(contactPerson.getOfficePhone());
			if (contactPerson.getFaxNumber() != null
					&& !contactPerson.getFaxNumber().equals("")) {
				contactPersonInfo.setContactFax(contactPerson.getFaxNumber());
			}
			if (contactPerson.getEmailAddress() != null
					&& !contactPerson.getEmailAddress().equals("")) {
				contactPersonInfo.setContactEmail(contactPerson.getEmailAddress());
			}
			contactPersonInfo.setContactTitle(contactPerson.getPrimaryTitle());
			contactPersonInfo.setContactAddress(globLibV10Generator
					.getAddressRequireCountryDataType(contactPerson));
		}
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
	    Enum answers = YesNoDataType.NO;
	    String childAnswer = null;  
	    String answer = getAnswer(IS_HUMAN_STEM_CELLS_INVOLVED);
	    if (answer != null) {
	        if (!answer.equals(NOT_ANSWERED)) {
	            answers = S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(getAnswer(IS_HUMAN_STEM_CELLS_INVOLVED)) ? YesNoDataType.YES : YesNoDataType.NO;
	            if (S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(answer)) {
	                stemCells.setIsHumanStemCellsInvolved(YesNoDataType.YES);
	                String subAnswer = getAnswer(SPECIFIC_STEM_CELL_LINE);
	                if (subAnswer != null) {
	                    if(!subAnswer.equals(NOT_ANSWERED)) {
	                        if (S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(subAnswer)) {
	                            stemCells.setStemCellsIndicator(YesNoDataType.NO);
	                            childAnswer = getAnswers(REGISTRATION_NUMBER);
	                        }
	                        else {
	                            stemCells.setStemCellsIndicator(YesNoDataType.YES);
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
	                stemCells.setIsHumanStemCellsInvolved(YesNoDataType.NO); 
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
		PHS398CoverPageSupplement phsCoverPageSupplement = (PHS398CoverPageSupplement) xmlObject;
		PHS398CoverPageSupplementDocument phsCoverPageSupplementDocument = PHS398CoverPageSupplementDocument.Factory
				.newInstance();
		phsCoverPageSupplementDocument
				.setPHS398CoverPageSupplement(phsCoverPageSupplement);
		return phsCoverPageSupplementDocument;
	}
}
