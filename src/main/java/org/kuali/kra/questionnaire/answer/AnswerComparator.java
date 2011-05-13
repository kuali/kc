/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.questionnaire.answer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;

/**
 * 
 * This class is implementing comparator for Answer in order to sort answer based
 * on questionnaire questions order
 */
public class AnswerComparator implements Comparator<Answer>  {

    public int compare(Answer ans1, Answer argAnswer) {
        int retVal = 0;
        if (ObjectUtils.equals(ans1.getQuestionNumber(), argAnswer.getQuestionNumber())) {
            retVal =  ans1.getAnswerNumber().compareTo(argAnswer.getAnswerNumber());
        } else if (ObjectUtils.equals(ans1.getQuestionnaireQuestion().getParentQuestionNumber(), argAnswer.getQuestionnaireQuestion()
                .getParentQuestionNumber())) {
            //retVal = this.getQuestionNumber().compareTo(argAnswer.getQuestionNumber());
            retVal = ans1.getQuestionnaireQuestion().getQuestionSeqNumber().compareTo(argAnswer.getQuestionnaireQuestion().getQuestionSeqNumber());
        } else if (ObjectUtils.equals(ans1.getQuestionnaireQuestion().getParentQuestionNumber(), argAnswer.getQuestionNumber())) {
            retVal = 1;
        } else if (ObjectUtils.equals(ans1.getQuestionNumber(), argAnswer.getQuestionnaireQuestion().getParentQuestionNumber())) {
            retVal = -1;
        } else if (ans1.getQuestionnaireQuestion().getParentQuestionNumber() == 0
                && argAnswer.getQuestionnaireQuestion().getParentQuestionNumber() != 0) {
            retVal = ans1.getQuestionnaireQuestion().getQuestionSeqNumber().compareTo(getRootAnswer(argAnswer).getQuestionnaireQuestion().getQuestionSeqNumber());
        } else if (ans1.getQuestionnaireQuestion().getParentQuestionNumber() != 0
                && argAnswer.getQuestionnaireQuestion().getParentQuestionNumber() == 0) {
            retVal = getRootAnswer(ans1).getQuestionnaireQuestion().getQuestionSeqNumber().compareTo(argAnswer.getQuestionnaireQuestion().getQuestionSeqNumber());
//        } else if (ans1.getQuestionnaireQuestion().getParentQuestionNumber() != 0
//                && argAnswer.getQuestionnaireQuestion().getParentQuestionNumber() != 0) {
//            if (ObjectUtils.equals(getRootAnswer(ans1).getQuestionNumber(), getRootAnswer(argAnswer).getQuestionNumber())) {
//                retVal = compareAtSameDepth(ans1, argAnswer);
//            } else {
//                retVal = getRootAnswer(ans1).getQuestionnaireQuestion().getQuestionSeqNumber().compareTo(getRootAnswer(argAnswer).getQuestionnaireQuestion().getQuestionSeqNumber());
//            }
        } else {
            retVal = 0;
        }
        return retVal;
    }
    private Answer getRootAnswer(Answer argAnswer) {

        Answer thisAnswer = argAnswer;
        while (thisAnswer.getQuestionnaireQuestion().getParentQuestionNumber() > 0) {
            thisAnswer = thisAnswer.getParentAnswer().get(0);
        }
        return thisAnswer;
    }
    
    private int compareAtSameDepth(Answer thisAnswer, Answer argAnswer) {
        int depth = 1;
        while (getAncestor(thisAnswer,depth).getQuestionNumber().equals(getAncestor(argAnswer,depth).getQuestionNumber())) {
            depth++;
        }
        return getAncestor(thisAnswer,depth).getQuestionnaireQuestion().getQuestionSeqNumber().compareTo(getAncestor(argAnswer,depth).getQuestionnaireQuestion().getQuestionSeqNumber());
    }
    private Answer getAncestor(Answer argAnswer, int depth) {
        List<Answer> answers = new ArrayList<Answer>();
        answers.add(argAnswer);
        Answer thisAnswer = argAnswer;
        int internalDepth = depth;
        while (thisAnswer.getQuestionnaireQuestion().getParentQuestionNumber() > 0) {
            thisAnswer = thisAnswer.getParentAnswer().get(0);
            answers.add(thisAnswer);
            ++internalDepth;
        }
        return answers.get(answers.size() - (internalDepth));
    }

}
