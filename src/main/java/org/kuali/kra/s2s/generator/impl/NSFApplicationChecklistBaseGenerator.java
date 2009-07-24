/*
 * Copyright 2008 The Kuali Foundation.
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

import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.ProposalAbstract;
import org.kuali.kra.proposaldevelopment.bo.ProposalYnq;
import org.kuali.kra.s2s.generator.S2SBaseFormGenerator;
import org.kuali.kra.s2s.util.S2SConstants;

/**
 * 
 * 
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class NSFApplicationChecklistBaseGenerator extends S2SBaseFormGenerator {

    protected static final String PROPOSAL_TYPE_CODE_8 = "8";
    protected static final String PROPOSAL_TYPE_CODE_5 = "5";
    protected static final String PROPOSAL_TYPE_CODE_3 = "3";
    protected static final String PROPOSAL_TYPE_CODE_2 = "2";
    protected static final String NARRATIVE_TYPE_CODE_5 = "5";
    protected static final String NARRATIVE_TYPE_CODE_1 = "1";
    protected static final String NARRATIVE_TYPE_CODE_4 = "4";
    protected static final String NARRATIVE_TYPE_CODE_2 = "2";
    protected static final String NARRATIVE_TYPE_CODE_3 = "3";
    protected static final String NARRATIVE_TYPE_CODE_15 = "15";
    protected static final String NARRATIVE_TYPE_CODE_7 = "7";
    protected static final String ABSTRACT_TYPE_CODE_15 = "15";
    protected static final String ABSTRACT_TYPE_CODE_14 = "14";
    protected static final String ABSTRACT_TYPE_CODE_12 = "12";
    protected static final String PROPOSAL_YNQ_QUESTION_21 = "21";
    protected static final String PROPOSAL_YNQ_QUESTION_FG = "FG";
    protected static final int ANSWER_YES = 1;
    protected static final int ANSWER_NO = 2;
    protected static final int ANSWER_NA = 3;

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
    protected static final int QUESTION_ID_11 = 11;
    protected static final int QUESTION_ID_12 = 12;
    protected static final int QUESTION_ID_13 = 13;
    protected static final int QUESTION_ID_14 = 14;
    protected static final int QUESTION_ID_15 = 15;
    protected static final int QUESTION_ID_16 = 16;
    protected static final int QUESTION_ID_17 = 17;
    protected static final int QUESTION_ID_18 = 18;
    protected static final int QUESTION_ID_19 = 19;
    protected static final int QUESTION_ID_20 = 20;
    protected static final int QUESTION_ID_21 = 21;
    protected static final int QUESTION_ID_22 = 22;
    protected static final int QUESTION_ID_23 = 23;
    protected static final int QUESTION_ID_24 = 24;
    protected static final int QUESTION_ID_25 = 25;
    protected static final int QUESTION_ID_26 = 26;
    protected static final int QUESTION_ID_27 = 27;
    protected static final int QUESTION_ID_28 = 28;
    protected static final int QUESTION_ID_29 = 29;
    protected static final int QUESTION_ID_30 = 30;
    protected static final int QUESTION_ID_31 = 31;
    protected static final int QUESTION_ID_32 = 32;


    /**
     * 
     * This method returns the YesNoDataType answer for all the Questions based on the QuestionId passes in to the method.
     * 
     * @param questionId passed to get the corresponding answer.
     * @return answer (int) Proposal Ynq answer returned for the corresponding question id.
     */
    protected int getChecklistAnswer(int questionId) {

        int answer = ANSWER_NO;
        switch (questionId) {
            case 1: {
                answer = ANSWER_YES;
                break;
            }
            case 2: {
                String proposaltypecode = pdDoc.getDevelopmentProposal().getProposalTypeCode();
                if (proposaltypecode != null
                        && (proposaltypecode.equals(PROPOSAL_TYPE_CODE_8) || proposaltypecode.equals(PROPOSAL_TYPE_CODE_5))) {
                    answer = ANSWER_YES;
                }
                else {
                    answer = ANSWER_NA;
                }
                break;
            }
            case 3: {
                for (ProposalYnq proYnq : pdDoc.getDevelopmentProposal().getProposalYnqs()) {
                    if (proYnq.getQuestionId() != null && proYnq.getQuestionId().equals(PROPOSAL_YNQ_QUESTION_21)) {
                        String ynqAnswer = proYnq.getAnswer();
                        if (ynqAnswer != null && ynqAnswer.equals(S2SConstants.PROPOSAL_YNQ_ANSWER_Y)) {
                            answer = ANSWER_YES;
                        }
                        else {
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
                String proposaltypecode = pdDoc.getDevelopmentProposal().getProposalTypeCode();
                if (proposaltypecode != null
                        && (proposaltypecode.equals(PROPOSAL_TYPE_CODE_2) || proposaltypecode.equals(PROPOSAL_TYPE_CODE_3))) {
                    answer = ANSWER_NO;
                }
                else {
                    answer = ANSWER_YES;
                }
                break;
            }
            case 5: {
                for (ProposalYnq proYnq : pdDoc.getDevelopmentProposal().getProposalYnqs()) {
                    if (proYnq.getQuestionId() != null && proYnq.getQuestionId().equals(PROPOSAL_YNQ_QUESTION_FG)) {
                        String ynqAnswer = proYnq.getAnswer();
                        if (ynqAnswer != null && ynqAnswer.equals(S2SConstants.PROPOSAL_YNQ_ANSWER_Y)) {
                            answer = ANSWER_YES;
                        }
                        else {
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
                for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
                    if (narrative.getNarrativeTypeCode() != null && narrative.getNarrativeTypeCode().equals(NARRATIVE_TYPE_CODE_5)) {
                        answer = ANSWER_YES;
                        break;
                    }
                    else {
                        answer = ANSWER_NO;
                        break;
                    }
                }
                break;
            }
            case 9: {
                for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
                    if (narrative.getNarrativeTypeCode() != null && narrative.getNarrativeTypeCode().equals(NARRATIVE_TYPE_CODE_1)) {
                        answer = ANSWER_YES;
                        break;
                    }
                    else {
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
                if (pdDoc.getDevelopmentProposal().getProposalTypeCode() != null) {
                    proposaltypecode = pdDoc.getDevelopmentProposal().getProposalTypeCode();
                }
                if (PROPOSAL_TYPE_CODE_8.equals(proposaltypecode) || PROPOSAL_TYPE_CODE_5.equals(proposaltypecode)) {
                    answer = ANSWER_YES;
                }
                else {
                    answer = ANSWER_NA;
                }
                break;
            }
            case 14: {
                for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
                    if (narrative.getNarrativeTypeCode() != null && narrative.getNarrativeTypeCode().equals(NARRATIVE_TYPE_CODE_4)) {
                        answer = ANSWER_YES;
                    }
                    else {
                        answer = ANSWER_NO;
                    }
                    break;
                }
                break;
            }
            case 15: {
                for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
                    if (narrative.getNarrativeTypeCode() != null && narrative.getNarrativeTypeCode().equals(NARRATIVE_TYPE_CODE_2)) {
                        answer = ANSWER_YES;
                    }
                    else {
                        answer = ANSWER_NO;
                    }
                    break;
                }
                break;
            }
            case 16: {
                for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
                    if (narrative.getNarrativeTypeCode() != null && narrative.getNarrativeTypeCode().equals(NARRATIVE_TYPE_CODE_3)) {
                        answer = ANSWER_YES;
                    }
                    else {
                        answer = ANSWER_NO;
                    }
                    break;
                }
                break;
            }
            case 17: {
                for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
                    if (narrative.getNarrativeTypeCode() != null && narrative.getNarrativeTypeCode().equals(NARRATIVE_TYPE_CODE_15)) {
                        answer = ANSWER_YES;
                    }
                    else {
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
                for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
                    if (narrative.getNarrativeTypeCode() != null && narrative.getNarrativeTypeCode().equals(NARRATIVE_TYPE_CODE_7)) {
                        answer = ANSWER_YES;
                    }
                    else {
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
                for (ProposalAbstract proAbstract : pdDoc.getDevelopmentProposal().getProposalAbstracts()) {
                    if (proAbstract.getAbstractTypeCode() != null
                            && proAbstract.getAbstractTypeCode().equals(ABSTRACT_TYPE_CODE_15)) {
                        answer = ANSWER_YES;
                    }
                    else {
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
                for (ProposalAbstract proAbstract : pdDoc.getDevelopmentProposal().getProposalAbstracts()) {
                    if (proAbstract.getAbstractTypeCode() != null
                            && (proAbstract.getAbstractTypeCode().equals(ABSTRACT_TYPE_CODE_12) || proAbstract
                                    .getAbstractTypeCode().equals(ABSTRACT_TYPE_CODE_14))) {
                        answer = ANSWER_YES;
                        break;
                    }
                    else {
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
}
