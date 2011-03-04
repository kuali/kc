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

import gov.grants.apply.forms.nsfApplicationChecklist13V13.NSFApplicationChecklist13Document;
import gov.grants.apply.forms.nsfApplicationChecklist13V13.NSFApplicationChecklist13Document.NSFApplicationChecklist13;
import gov.grants.apply.forms.nsfApplicationChecklist13V13.NSFApplicationChecklist13Document.NSFApplicationChecklist13.CoverSheet;
import gov.grants.apply.forms.nsfApplicationChecklist13V13.NSFApplicationChecklist13Document.NSFApplicationChecklist13.Equipment;
import gov.grants.apply.forms.nsfApplicationChecklist13V13.NSFApplicationChecklist13Document.NSFApplicationChecklist13.NSFCover;
import gov.grants.apply.forms.nsfApplicationChecklist13V13.NSFApplicationChecklist13Document.NSFApplicationChecklist13.ProjectNarrative;
import gov.grants.apply.forms.nsfApplicationChecklist13V13.NSFApplicationChecklist13Document.NSFApplicationChecklist13.RRBudget;
import gov.grants.apply.forms.nsfApplicationChecklist13V13.NSFApplicationChecklist13Document.NSFApplicationChecklist13.RRSrProfile;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType;
import gov.grants.apply.system.globalLibraryV20.YesNoNotApplicableDataType;

import java.util.List;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.questionnaire.answer.Answer;
import org.kuali.kra.s2s.generator.S2SQuestionnairing;
import org.kuali.kra.s2s.util.S2SConstants;

/**
 * 
 * This class is used to generate XML Document object for grants.gov
 * NSFApplicationChecklistV1.3. This form is generated using XMLBean classes and
 * is based on NSF_ApplicationChecklist-V1.3 schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class NSFApplicationChecklistV1_3Generator extends NSFApplicationChecklistBaseGenerator implements S2SQuestionnairing{

	private static String QUESTIONNAIRE_ANSWER_YES = "Y";
	private static String QUESTIONNAIRE_ANSWER_NO = "N";
	private static String QUESTIONNAIRE_ANSWER_X = "X";;

	/**
	 * 
	 * This method returns NSFApplicationChecklist13Document object based on
	 * proposal development document which contains the
	 * NSFApplicationChecklist13Document information for a particular proposal
	 * 
	 * @return nsfChecklistDocument {@link XmlObject} of type
	 *         NSFApplicationChecklist13Document.
	 */
	private NSFApplicationChecklist13Document getNSFApplicationChecklist13() {
		NSFApplicationChecklist13Document nsfChecklistDocument = NSFApplicationChecklist13Document.Factory
				.newInstance();
		NSFApplicationChecklist13 nsfChecklist = NSFApplicationChecklist13.Factory
				.newInstance();
		nsfChecklist.setFormVersion(S2SConstants.FORMVERSION_1_3);
		CoverSheet coverSheet = getCoverSheet();
		ProjectNarrative projectNarrative = getProjectNarrative();
		setQuestionnaireAnswers(coverSheet, projectNarrative);
		nsfChecklist.setCoverSheet(coverSheet);
		nsfChecklist.setCheckRRSite(YesNoDataType.Enum
				.forInt(getChecklistAnswer(QUESTION_ID_6)));
		nsfChecklist.setCheckRROtherInfo(YesNoDataType.Enum
				.forInt(getChecklistAnswer(QUESTION_ID_7)));
		nsfChecklist.setCheckProjectSummary(YesNoDataType.Enum
				.forInt(getChecklistAnswer(QUESTION_ID_8)));
		nsfChecklist.setProjectNarrative(projectNarrative);
		nsfChecklist.setCheckBiblio(YesNoDataType.Enum
				.forInt(getChecklistAnswer(QUESTION_ID_NSF_SMALL_GRANT)));
		nsfChecklist.setCheckFacilities(YesNoDataType.Enum
				.forInt(getChecklistAnswer(QUESTION_ID_OTHER_AGENCY)));
		nsfChecklist.setEquipment(getEquipment());
		nsfChecklist.setRRSrProfile(getRRSrProfile());
		nsfChecklist.setCheckRRPersonalData(YesNoDataType.Enum
				.forInt(getChecklistAnswer(QUESTION_ID_PI_CHANGE)));
		nsfChecklist.setRRBudget(getRRBudget());
		nsfChecklist.setNSFCover(getNSFCover());
		nsfChecklistDocument.setNSFApplicationChecklist13(nsfChecklist);
		return nsfChecklistDocument;
	}

	/**
	 * Setting the Coversheet and ProjectNarrative details according the
	 * Questionnaire Answers.
	 * 
	 * @param coverSheet
	 * @param projectNarrative
	 */
	private void setQuestionnaireAnswers(CoverSheet coverSheet,
			ProjectNarrative projectNarrative) {
		List<Answer> answers = s2sUtilService.getQuestionnaireAnswers(pdDoc.getDevelopmentProposal(),getNamespace(),getFormName());
		for (Answer answer : answers) {
			switch (answer.getQuestion().getQuestionId()) {
			case PRELIMINARY:
				YesNoNotApplicableDataType.Enum yesNoNotApplicableDataType = YesNoNotApplicableDataType.N_NO;
				if (QUESTIONNAIRE_ANSWER_YES.equals(answer.getAnswer())) {
					yesNoNotApplicableDataType = YesNoNotApplicableDataType.Y_YES;
				} else if (QUESTIONNAIRE_ANSWER_NO.equals(answer.getAnswer())) {
					yesNoNotApplicableDataType = YesNoNotApplicableDataType.N_NO;
				} else if (QUESTIONNAIRE_ANSWER_X.equals(answer.getAnswer())) {
					yesNoNotApplicableDataType = YesNoNotApplicableDataType.NA_NOT_APPLICABLE;
				}
				coverSheet.setCheckFullApp(yesNoNotApplicableDataType);
				break;
			case MERIT_REVIEW:
				if (QUESTIONNAIRE_ANSWER_YES.equals(answer.getAnswer())) {
					projectNarrative
							.setCheckMeritReview(YesNoNotApplicableDataType.Y_YES);
				} else if (QUESTIONNAIRE_ANSWER_NO.equals(answer.getAnswer())) {
					projectNarrative
							.setCheckMeritReview(YesNoNotApplicableDataType.N_NO);
				} else if (QUESTIONNAIRE_ANSWER_X.equals(answer.getAnswer())) {
					projectNarrative
							.setCheckMeritReview(YesNoNotApplicableDataType.NA_NOT_APPLICABLE);
				}
				break;

			case MENTORING:
				if (QUESTIONNAIRE_ANSWER_YES.equals(answer.getAnswer())) {
					projectNarrative
							.setCheckMentoring(YesNoNotApplicableDataType.Y_YES);
				} else if (QUESTIONNAIRE_ANSWER_NO.equals(answer.getAnswer())) {
					projectNarrative
							.setCheckMentoring(YesNoNotApplicableDataType.N_NO);
				}
				break;

			case PRIOR_SUPPORT:
				// Does narrative include info regarding prior support?
				if (QUESTIONNAIRE_ANSWER_YES.equals(answer.getAnswer()))
					projectNarrative
							.setCheckPriorSupport(YesNoNotApplicableDataType.Y_YES);
				if (QUESTIONNAIRE_ANSWER_NO.equals(answer.getAnswer()))
					projectNarrative
							.setCheckPriorSupport(YesNoNotApplicableDataType.N_NO);
				if (QUESTIONNAIRE_ANSWER_X.equals(answer.getAnswer()))
					projectNarrative
							.setCheckPriorSupport(YesNoNotApplicableDataType.NA_NOT_APPLICABLE);
				break;
			case HR_QUESTION:
				/*
				 * HR Info that is mandatory for renwals from academic
				 * institutions.
				 */
				if (QUESTIONNAIRE_ANSWER_NO.equals(answer)) {
					projectNarrative
							.setCheckHRInfo(YesNoNotApplicableDataType.N_NO);
				}
				break;
			case HR_REQUIRED_INFO:
				/*
				 * HR Info that is mandatory for renwals from academic
				 * institutions.
				 */
				if (QUESTIONNAIRE_ANSWER_YES.equals(answer)) {
					projectNarrative
							.setCheckHRInfo(YesNoNotApplicableDataType.Y_YES);
				}
				break;
			default:
				break;
			}
		}
	}

	/**
	 * 
	 * This method returns CoverSheet,Renewal,Full Application,Type of
	 * application and Application certification information for the CoverSheet
	 * type.
	 * 
	 * @return CoverSheet object containing cover sheet information details.
	 */
	private CoverSheet getCoverSheet() {
		CoverSheet coverSheet = CoverSheet.Factory.newInstance();
		coverSheet.setCheckCoverSheet(YesNoDataType.Enum
				.forInt(getChecklistAnswer(QUESTION_ID_1)));
		coverSheet.setCheckRenewal(YesNoNotApplicableDataType.Enum
				.forInt(getChecklistAnswer(QUESTION_ID_2)));
		coverSheet.setCheckTypeApp(YesNoDataType.Enum
				.forInt(getChecklistAnswer(QUESTION_ID_4)));
		coverSheet.setCheckAppCert(YesNoDataType.Enum
				.forInt(getChecklistAnswer(QUESTION_ID_5)));
		return coverSheet;
	}

	/**
	 * 
	 * This method checks for the informations like Does narrative include merit
	 * review criteria, URL's should not be included in the narrative Does
	 * narrative include info regarding prior support,HR Info that is mandatory
	 * for renewals from academic institutions
	 * 
	 * @return ProjectNarrative object containing project narrative information
	 *         details.
	 */
	private ProjectNarrative getProjectNarrative() {
		ProjectNarrative projectNarrative = ProjectNarrative.Factory
				.newInstance();
		projectNarrative.setCheckProjectNarrative(YesNoDataType.Enum
				.forInt(getChecklistAnswer(QUESTION_ID_9)));
		projectNarrative.setCheckURL(YesNoNotApplicableDataType.Enum
				.forInt(getChecklistAnswer(QUESTION_ID_SPACE_CHANGE)));
		return projectNarrative;
	}

	/**
	 * 
	 * This method gets informations like attachment of Equipments,Supplementary
	 * information,Additional items relevant to NSF Program complete.
	 * 
	 * @return Equipment object containing equipment information details.
	 */
	private Equipment getEquipment() {
		Equipment equipment = Equipment.Factory.newInstance();
		equipment.setCheckEquipment(YesNoDataType.Enum
				.forInt(getChecklistAnswer(QUESTION_ID_INVENTION_PATENT)));
		equipment.setCheckSuppDoc(YesNoNotApplicableDataType.Enum
				.forInt(getChecklistAnswer(QUESTION_ID_CLINICAL_TRIAL)));
		equipment.setCheckAdditionalItems(YesNoNotApplicableDataType.Enum
				.forInt(getChecklistAnswer(QUESTION_ID_STEM_CELL)));
		return equipment;
	}

	/**
	 * 
	 * This method returns CurrentPendingSupport, BioSketch and RRSeniorProfile
	 * information for the the RRSrProfile type.
	 * 
	 * @return RRSrProfile object containing profile details.
	 */
	private RRSrProfile getRRSrProfile() {
		RRSrProfile rrSrProfile = RRSrProfile.Factory.newInstance();
		rrSrProfile.setCheckRRSrProfile(YesNoDataType.Enum
				.forInt(getChecklistAnswer(QUESTION_ID_CURRENT_SERVING_PI)));
		rrSrProfile.setCheckBioSketch(YesNoDataType.Enum
				.forInt(getChecklistAnswer(QUESTION_ID_HIGH_RESOLUTION)));
		rrSrProfile.setCheckCurrentPendingSupport(YesNoDataType.Enum
				.forInt(getChecklistAnswer(QUESTION_ID_FULL_APPLICATION)));
		return rrSrProfile;
	}

	/**
	 * 
	 * This method returns RRBudget,RRBudgetJustification and Cost sharing
	 * information for the RRBudget type.
	 * 
	 * @return RRBudget object containing RRbudget related information.
	 */
	private RRBudget getRRBudget() {
		RRBudget rrBudget = RRBudget.Factory.newInstance();
		rrBudget
				.setCheckRRBudget(YesNoDataType.Enum
						.forInt(getChecklistAnswer(QUESTION_ID_GRANTEE_INSTITUTION_CHANGE)));
		rrBudget.setCheckRRBudgetJustification(YesNoNotApplicableDataType.Enum
				.forInt(getChecklistAnswer(QUESTION_ID_PI_PARTICIPATION_US)));
		return rrBudget;
	}

	/**
	 * 
	 * This method gets NSF Cover information such as NSF Deviation
	 * authorization, Do not include, NSF FLL, NSF unit.
	 * 
	 * @return NSFCover object containing nsf cover details.
	 */
	private NSFCover getNSFCover() {
		NSFCover nsfCover = NSFCover.Factory.newInstance();
		nsfCover.setCheckNSFCover(YesNoDataType.Enum
				.forInt(getChecklistAnswer(QUESTION_ID_EDUCATION_DEPT)));
		nsfCover.setCheckNSFUnit(YesNoDataType.Enum
				.forInt(getChecklistAnswer(QUESTION_ID_ENVIRINMENT_IMPACT)));
		nsfCover.setCheckNSFOtherInfo(YesNoNotApplicableDataType.Enum
				.forInt(getChecklistAnswer(QUESTION_ID_28)));
		nsfCover.setCheckNSFSFLLL(YesNoNotApplicableDataType.Enum
				.forInt(getChecklistAnswer(QUESTION_ID_29)));
		nsfCover.setCheckNSFDevAuth(YesNoNotApplicableDataType.Enum
				.forInt(getChecklistAnswer(QUESTION_ID_30)));
		nsfCover.setCheckNSFReg(YesNoNotApplicableDataType.Enum
				.forInt(getChecklistAnswer(QUESTION_ID_31)));
		nsfCover.setCheckDoNotInclude(YesNoNotApplicableDataType.Enum
				.forInt(getChecklistAnswer(QUESTION_ID_32)));
		return nsfCover;
	}

	/**
	 * This method creates {@link XmlObject} of type
	 * {@link NSFApplicationChecklist13Document} by populating data from the
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
		return getNSFApplicationChecklist13();
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
		NSFApplicationChecklist13 nsfChecklist = (NSFApplicationChecklist13) xmlObject;
		NSFApplicationChecklist13Document nsfChecklistDocument = NSFApplicationChecklist13Document.Factory
				.newInstance();
		nsfChecklistDocument.setNSFApplicationChecklist13(nsfChecklist);
		return nsfChecklistDocument;
	}

    public String getFormName() {
        return "NSF_ApplicationChecklist_1_3-V1.3";
    }

    public String getNamespace() {
        return "http://apply.grants.gov/forms/NSF_ApplicationChecklist_1_3-V1.3";
    }

}
