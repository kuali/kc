/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.common.questionnaire.impl.answer;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.questionnaire.framework.answer.Answer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * This class is implementing comparator for Answer in order to sort answer based
 * on questionnaire questions order
 */
public class AnswerComparator implements Comparator<Answer>  {
    private static final Log LOG = LogFactory.getLog(AnswerComparator.class);
    
    public int compare(Answer ans1, Answer argAnswer) {

        int retVal = 0;
       if (ObjectUtils.equals(ans1.getQuestionNumber(), argAnswer.getQuestionNumber())) {
           // question with multiple answers.  then compare answer number
           retVal =  ans1.getAnswerNumber().compareTo(argAnswer.getAnswerNumber());
        } else if (ObjectUtils.equals(ans1.getQuestionnaireQuestion().getParentQuestionNumber(), argAnswer.getQuestionnaireQuestion()
                .getParentQuestionNumber())) {
            // questions with same parent
            retVal = ans1.getQuestionnaireQuestion().getQuestionSeqNumber().compareTo(argAnswer.getQuestionnaireQuestion().getQuestionSeqNumber());
        } else if (ObjectUtils.equals(ans1.getQuestionnaireQuestion().getParentQuestionNumber(), argAnswer.getQuestionNumber())) {
            // argAnswer is the parent question of ans1
            retVal = 1;
        } else if (ObjectUtils.equals(ans1.getQuestionNumber(), argAnswer.getQuestionnaireQuestion().getParentQuestionNumber())) {
            // ans1 is the parent question of argAnswer
            retVal = -1;
        } else if (ans1.getQuestionnaireQuestion().getParentQuestionNumber() == 0
                && argAnswer.getQuestionnaireQuestion().getParentQuestionNumber() != 0) {
            // ans1 is the root question
            retVal = ans1.getQuestionnaireQuestion().getQuestionSeqNumber().compareTo(getRootAnswer(argAnswer).getQuestionnaireQuestion().getQuestionSeqNumber());
            if (retVal == 0) {
                // ans1 is the root question of argAnswer
                retVal = -1;
            }
        } else if (ans1.getQuestionnaireQuestion().getParentQuestionNumber() != 0
                && argAnswer.getQuestionnaireQuestion().getParentQuestionNumber() == 0) {
            // argAnswer is the root question
            retVal = getRootAnswer(ans1).getQuestionnaireQuestion().getQuestionSeqNumber().compareTo(argAnswer.getQuestionnaireQuestion().getQuestionSeqNumber());
            if (retVal == 0) {
                // argAnswer is the root question of ans1
                retVal = 1;
            }
        } else if (ans1.getQuestionnaireQuestion().getParentQuestionNumber() != 0
                && argAnswer.getQuestionnaireQuestion().getParentQuestionNumber() != 0) {
            // both are not root question
            if (ObjectUtils.equals(getRootAnswer(ans1).getQuestionNumber(), getRootAnswer(argAnswer).getQuestionNumber())) {
                // both has the same root question
                retVal = compareAtSameDepth(ans1, argAnswer);
            } else {
                retVal = getRootAnswer(ans1).getQuestionnaireQuestion().getQuestionSeqNumber().compareTo(getRootAnswer(argAnswer).getQuestionnaireQuestion().getQuestionSeqNumber());
            }
        } else {
            // set up log now to see why it comes to here
            LOG.info("no comparison matched "+ans1.getQuestionnaireQuestionsId()+"-"+argAnswer.getQuestionnaireQuestionsId());
            retVal = 0;
        }
        return retVal;
    }
    
    /*
     * get the root answer for the selected answer
     */
    private Answer getRootAnswer(Answer argAnswer) {

        Answer thisAnswer = argAnswer;
        while (thisAnswer.getQuestionnaireQuestion().getParentQuestionNumber() > 0) {
            thisAnswer = thisAnswer.getParentAnswers().get(0);
        }
        return thisAnswer;
    }
    
    /*
     * compare question answer if they neither is the root question, but they have the same
     * root question; ie, they are from the same question tree.
     * 
     */
    private int compareAtSameDepth(Answer thisAnswer, Answer argAnswer) {
        int retVal = 0;

        List<Answer> ancestors1 = getAncestors(thisAnswer);
        List<Answer> ancestors2 = getAncestors(argAnswer);
        // comparing by starting from root.  Whenever, it shows that they are in different branch
        // then use the split nodes sequence number to decide the order.
        if (ancestors1.size() <= ancestors2.size()) {
            retVal = -1;
            for (int i = 0; i < ancestors1.size(); i++) {

                if (!ObjectUtils.equals(ancestors1.get(i).getQuestionNumber(), ancestors2.get(i).getQuestionNumber())) {
                    retVal = ancestors1.get(i).getQuestionnaireQuestion().getQuestionSeqNumber().compareTo(
                            ancestors2.get(i).getQuestionnaireQuestion().getQuestionSeqNumber());
                    break;
                }


            }
        }
        else {
            for (int i = 0; i < ancestors2.size(); i++) {
                retVal = 1;

                if (!ObjectUtils.equals(ancestors2.get(i).getQuestionNumber(), ancestors1.get(i).getQuestionNumber())) {
                    retVal = ancestors1.get(i).getQuestionnaireQuestion().getQuestionSeqNumber().compareTo(
                            ancestors2.get(i).getQuestionnaireQuestion().getQuestionSeqNumber());
                    break;
                }


            }

        }
        return retVal;
    }

    /*
     * get all the ancestors of this answer's question.  then reverse to list from root to leaf question.
     */
    private List<Answer> getAncestors(Answer argAnswer) {
        List<Answer> answers = new ArrayList<Answer>();
        answers.add(argAnswer);
        Answer thisAnswer = argAnswer;
        while (thisAnswer.getQuestionnaireQuestion().getParentQuestionNumber() > 0) {
            thisAnswer = thisAnswer.getParentAnswers().get(0);
            answers.add(thisAnswer);

        }
        Collections.reverse(answers);
        return answers;
    }

}

