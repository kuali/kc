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

import org.kuali.coeus.common.api.ynq.YnqConstant;
import org.kuali.coeus.propdev.api.abstrct.ProposalAbstractContract;
import org.kuali.coeus.propdev.api.s2s.S2SConfigurationService;
import org.kuali.coeus.propdev.api.ynq.ProposalYnqContract;

import org.kuali.coeus.propdev.api.attachment.NarrativeContract;

import org.kuali.coeus.s2sgen.api.core.ConfigurationConstants;
import org.kuali.coeus.s2sgen.impl.generate.S2SBaseFormGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * This class will contain all common features that will be used in generators
 * for different versions of NSFApplicationCheckList forms
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class NSFApplicationChecklistBaseGenerator extends
		S2SBaseFormGenerator {
	private static final String PROPOSAL_TYPE_CODE_8 = "8";
	private static final String NARRATIVE_TYPE_CODE_5 = "5";
	private static final String NARRATIVE_TYPE_DEFAULT = "1";
	private static final String NARRATIVE_TYPE_BIBLIOGRAPHY = "4";
	private static final String NARRATIVE_TYPE_FACILITIES = "2";
	private static final String NARRATIVE_TYPE_EQUIPMENT = "3";
	private static final String NARRATIVE_TYPE_SUPLIMENTARY_DOC = "15";
	private static final String NARRATIVE_TYPE_BUDGET_JUSTIFICATION = "7";
	private static final String ABSTRACT_TYPE_DEVIATION_AUTH = "15";
	private static final String ABSTRACT_TYPE_REVIEWERS_NOT_TO_INCLUDE = "14";
	private static final String ABSTRACT_TYPE_CODE_SUGGESTED_REVIEWERS = "12";
	private static final String PROPOSAL_YNQ_QUESTION_21 = "21";
	private static final String PROPOSAL_YNQ_QUESTION_FG = "FG";
	private static final int ANSWER_YES = 1;
	private static final int ANSWER_NO = 2;
	private static final int ANSWER_NA = 3;

	protected static final int QUESTION_ID_1 = 1;
	protected static final int QUESTION_ID_2 = 2;
	protected static final int QUESTION_ID_3 = 3;
	protected static final int QUESTION_ID_4 = 4;
	protected static final int QUESTION_ID_5 = 5;
	protected static final int QUESTION_ID_6 = 6;
	protected static final int QUESTION_ID_7 = 7;
	protected static final int QUESTION_ID_8 = 8;
	protected static final int QUESTION_ID_9 = 9;
	protected static final int QUESTION_ID_10 = 10;
	protected static final int QUESTION_ID_SPACE_CHANGE = 11;
	protected static final int QUESTION_ID_NIH_INVESTIGAYOR = 12;
	protected static final int QUESTION_ID_NSF_INVESTIGATOR = 13;
	protected static final int QUESTION_ID_NSF_SMALL_GRANT = 14;
	protected static final int QUESTION_ID_OTHER_AGENCY = 15;
	protected static final int QUESTION_ID_INVENTION_PATENT = 16;
	protected static final int QUESTION_ID_CLINICAL_TRIAL = 17;
	protected static final int QUESTION_ID_STEM_CELL = 18;
	protected static final int QUESTION_ID_CURRENT_SERVING_PI = 19;
	protected static final int QUESTION_ID_HIGH_RESOLUTION = 20;
	protected static final int QUESTION_ID_FULL_APPLICATION = 21;
	protected static final int QUESTION_ID_PI_CHANGE = 22;
	protected static final int QUESTION_ID_GRANTEE_INSTITUTION_CHANGE = 23;
	protected static final int QUESTION_ID_PI_PARTICIPATION_US = 24;
	protected static final int QUESTION_ID_PI_PARTICIPATION_FOREIGN = 25;
	protected static final int QUESTION_ID_EDUCATION_DEPT = 26;
	protected static final int QUESTION_ID_ENVIRINMENT_IMPACT = 27;
	protected static final int QUESTION_ID_28 = 28;
	protected static final int QUESTION_ID_29 = 29;
	protected static final int QUESTION_ID_30 = 30;
	protected static final int QUESTION_ID_31 = 31;
	protected static final int QUESTION_ID_32 = 32;

	protected static final int PRELIMINARY = 1110;
	protected static final int MERIT_REVIEW = 1111;
	protected static final int MENTORING = 1113;
	protected static final int PRIOR_SUPPORT = 1114;
	protected static final int HR_QUESTION = 1108;
	protected static final int HR_REQUIRED_INFO = 1117;

    @Autowired
    @Qualifier("s2SConfigurationService")
    protected S2SConfigurationService s2SConfigurationService;

	/**
	 * 
	 * This method returns the YesNoDataType answer for all the Questions based
	 * on the QuestionId passes in to the method.
	 * 
	 * @param questionId
	 *            passed to get the corresponding answer.
	 * @return answer (int) Proposal Ynq answer returned for the corresponding
	 *         question id.
	 */
	protected int getChecklistAnswer(int questionId) {

		int answer = ANSWER_NO;
		switch (questionId) {
		case 1: {
			answer = ANSWER_YES;
			break;
		}
		case 2: {
			String proposaltypecode = pdDoc.getDevelopmentProposal()
					.getProposalType().getCode();
			if (proposaltypecode != null
					&& (proposaltypecode.equals(s2SConfigurationService.getValueAsString(
                    ConfigurationConstants.PROPOSAL_TYPE_CODE_REVISION)) || proposaltypecode
							.equals(PROPOSAL_TYPE_CODE_8))) {
				answer = ANSWER_YES;
			} else {
				answer = ANSWER_NA;
			}
			break;
		}
		case 3: {
			for (ProposalYnqContract proYnq : pdDoc.getDevelopmentProposal()
					.getProposalYnqs()) {
				if (proYnq.getYnq() != null
						&& proYnq.getYnq().getQuestionId().equals(
								PROPOSAL_YNQ_QUESTION_21)) {
					String ynqAnswer = proYnq.getAnswer();
					if (ynqAnswer != null
							&& ynqAnswer
									.equals(YnqConstant.YES.code())) {
						answer = ANSWER_YES;
					} else {
						answer = ANSWER_NA;
					}
					break;
				}
			}
			if (answer == 0) {
				answer = ANSWER_NA;
			}
			break;
		}
		case 4: {
			String proposaltypecode = pdDoc.getDevelopmentProposal()
					.getProposalType().getCode();
			if (proposaltypecode != null
					&& (proposaltypecode.equals(s2SConfigurationService.getValueAsString(
                    ConfigurationConstants.PROPOSAL_TYPE_CODE_CONTINUATION)) || proposaltypecode
							.equals(s2SConfigurationService.getValueAsString(
                                    ConfigurationConstants.PROPOSAL_TYPE_CODE_RENEWAL)))) {
				answer = ANSWER_NO;
			} else {
				answer = ANSWER_YES;
			}
			break;
		}
		case 5: {
			for (ProposalYnqContract proYnq : pdDoc.getDevelopmentProposal()
					.getProposalYnqs()) {
				if (proYnq.getYnq() != null
						&& proYnq.getYnq().getQuestionId().equals(
								PROPOSAL_YNQ_QUESTION_FG)) {
					String ynqAnswer = proYnq.getAnswer();
					if (ynqAnswer != null
							&& ynqAnswer
									.equals(YnqConstant.YES.code())) {
						answer = ANSWER_YES;
					} else {
						answer = ANSWER_NO;
					}
					break;
				}
			}
			break;
		}
		case 6: {
			answer = ANSWER_YES;
			break;
		}
		case 7: {
			answer = ANSWER_YES;
			break;
		}
		case 8: {
			for (NarrativeContract narrative : pdDoc.getDevelopmentProposal()
					.getNarratives()) {
				if (narrative.getNarrativeType().getCode() != null
						&& narrative.getNarrativeType().getCode().equals(
								NARRATIVE_TYPE_CODE_5)) {
					answer = ANSWER_YES;
					break;
				} else {
					answer = ANSWER_NO;
					break;
				}
			}
			break;
		}
		case 9: {
			for (NarrativeContract narrative : pdDoc.getDevelopmentProposal()
					.getNarratives()) {
				if (narrative.getNarrativeType().getCode() != null
						&& narrative.getNarrativeType().getCode().equals(
								NARRATIVE_TYPE_DEFAULT)) {
					answer = ANSWER_YES;
					break;
				} else {
					answer = ANSWER_NO;
					break;
				}
			}
			break;
		}
		case 10: {
			answer = ANSWER_YES;
			break;
		}
		case 11: {
			answer = ANSWER_YES;
			break;
		}
		case 12: {
			answer = ANSWER_YES;
			break;
		}
		case 13: {
			String proposaltypecode = null;
			if (pdDoc.getDevelopmentProposal().getProposalType().getCode() != null) {
				proposaltypecode = pdDoc.getDevelopmentProposal()
						.getProposalType().getCode();
			}
			if (s2SConfigurationService.getValueAsString(
                    ConfigurationConstants.PROPOSAL_TYPE_CODE_REVISION).equals(proposaltypecode)) {
				answer = ANSWER_YES;
			} else {
				answer = ANSWER_NA;
			}
			break;
		}
		case 14: {
			for (NarrativeContract narrative : pdDoc.getDevelopmentProposal()
					.getNarratives()) {
				if (narrative.getNarrativeType().getCode() != null
						&& narrative.getNarrativeType().getCode().equals(
								NARRATIVE_TYPE_BIBLIOGRAPHY)) {
					answer = ANSWER_YES;
				} else {
					answer = ANSWER_NO;
				}
				break;
			}
			break;
		}
		case 15: {
			for (NarrativeContract narrative : pdDoc.getDevelopmentProposal()
					.getNarratives()) {
				if (narrative.getNarrativeType().getCode() != null
						&& narrative.getNarrativeType().getCode().equals(
								NARRATIVE_TYPE_FACILITIES)) {
					answer = ANSWER_YES;
				} else {
					answer = ANSWER_NO;
				}
				break;
			}
			break;
		}
		case 16: {
			for (NarrativeContract narrative : pdDoc.getDevelopmentProposal()
					.getNarratives()) {
				if (narrative.getNarrativeType().getCode() != null
						&& narrative.getNarrativeType().getCode().equals(
								NARRATIVE_TYPE_EQUIPMENT)) {
					answer = ANSWER_YES;
				} else {
					answer = ANSWER_NO;
				}
				break;
			}
			break;
		}
		case 17: {
			for (NarrativeContract narrative : pdDoc.getDevelopmentProposal()
					.getNarratives()) {
				if (narrative.getNarrativeType().getCode() != null
						&& narrative.getNarrativeType().getCode().equals(
								NARRATIVE_TYPE_SUPLIMENTARY_DOC)) {
					answer = ANSWER_YES;
				} else {
					answer = ANSWER_NA;
				}
				break;
			}
			break;
		}
		case 18: {
			answer = ANSWER_YES;
			break;
		}
		case 19: {
			answer = ANSWER_YES;
			break;
		}
		case 20: {
			answer = ANSWER_YES;
			break;
		}
		case 21: {
			answer = ANSWER_YES;
			break;
		}
		case 22: {
			answer = ANSWER_YES;
			break;
		}
		case 23: {
			answer = ANSWER_YES;
			break;
		}
		case 24: {
			for (NarrativeContract narrative : pdDoc.getDevelopmentProposal()
					.getNarratives()) {
				if (narrative.getNarrativeType().getCode() != null
						&& narrative.getNarrativeType().getCode().equals(
								NARRATIVE_TYPE_BUDGET_JUSTIFICATION)) {
					answer = ANSWER_YES;
				} else {
					answer = ANSWER_NA;
				}
				break;
			}
			break;
		}
		case 25: {
			answer = ANSWER_YES;
			break;
		}
		case 26: {
			answer = ANSWER_YES;
			break;
		}
		case 27: {
			answer = ANSWER_YES;
			break;
		}
		case 28: {
			answer = ANSWER_YES;
			break;
		}
		case 29: {
			answer = ANSWER_NA;
			break;
		}
		case 30: {
			for (ProposalAbstractContract proAbstract : pdDoc.getDevelopmentProposal()
					.getProposalAbstracts()) {
				if (proAbstract.getAbstractType() != null
						&& proAbstract.getAbstractType().getCode().equals(
								ABSTRACT_TYPE_DEVIATION_AUTH)) {
					answer = ANSWER_YES;
				} else {
					answer = ANSWER_NA;
				}
				break;
			}
			break;
		}
		case 31: {
			answer = ANSWER_NA;
			break;
		}
		case 32: {
			for (ProposalAbstractContract proAbstract : pdDoc.getDevelopmentProposal()
					.getProposalAbstracts()) {
				if (proAbstract.getAbstractType() != null
						&& (proAbstract.getAbstractType().getCode().equals(
								ABSTRACT_TYPE_CODE_SUGGESTED_REVIEWERS) || proAbstract
								.getAbstractType().getCode().equals(
										ABSTRACT_TYPE_REVIEWERS_NOT_TO_INCLUDE))) {
					answer = ANSWER_YES;
					break;
				} else {
					answer = ANSWER_NA;
					break;
				}
			}
		}
		default: {
			answer = ANSWER_NO;
			break;
		}
		}
		return answer;
	}

    public S2SConfigurationService getS2SConfigurationService() {
        return s2SConfigurationService;
    }

    public void setS2SConfigurationService(S2SConfigurationService s2SConfigurationService) {
        this.s2SConfigurationService = s2SConfigurationService;
    }
}
