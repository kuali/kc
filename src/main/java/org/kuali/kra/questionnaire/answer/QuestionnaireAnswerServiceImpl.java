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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.bo.CoeusSubModule;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolFinderDao;
import org.kuali.kra.questionnaire.Questionnaire;
import org.kuali.kra.questionnaire.QuestionnaireQuestion;
import org.kuali.kra.questionnaire.QuestionnaireUsage;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ObjectUtils;

/**
 * 
 * This class implemented the questionnaire answer related methods.
 */
/**
 * This class...
 */
public class QuestionnaireAnswerServiceImpl implements QuestionnaireAnswerService {

    private static final String MODULE_ITEM_CODE = "moduleItemCode";
    private static final String MODULE_SUB_ITEM_CODE = "moduleSubItemCode";
    private static final String MODULE_ITEM_KEY = "moduleItemKey";
    private static final String MODULE_SUB_ITEM_KEY = "moduleSubItemKey";
    private static final String YES = "Y";
    private static final String NO = "N";
    private BusinessObjectService businessObjectService;
    private ProtocolFinderDao protocolFinderDao;

    /*
     * Get the questionnaire that is 'final' for the specified module.
     */
    public List<QuestionnaireUsage> getPublishedQuestionnaire(String coeusModule, String coeusSubModule, boolean finalDoc) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(MODULE_ITEM_CODE, coeusModule);
        fieldValues.put(MODULE_SUB_ITEM_CODE, coeusSubModule);

        List<QuestionnaireUsage> usages = new ArrayList<QuestionnaireUsage>();
        List<String> questionnaireIds = new ArrayList<String>();
        List<QuestionnaireUsage> questionnaireUsages = (List<QuestionnaireUsage>) businessObjectService.findMatching(
                QuestionnaireUsage.class, fieldValues);

        // use this sort, to list the higher version before lower version
        if (CollectionUtils.isNotEmpty(questionnaireUsages)) {
            Collections.sort((List<QuestionnaireUsage>) questionnaireUsages);
            // Collections.reverse((List<QuestionnaireUsage>) questionnaireUsages);
        }

        // the higher version will have higher questionnaireidfk
        for (QuestionnaireUsage questionnaireUsage : questionnaireUsages) {
            if (!questionnaireIds.contains(questionnaireUsage.getQuestionnaire().getQuestionnaireId())) {
                questionnaireIds.add(questionnaireUsage.getQuestionnaire().getQuestionnaireId());
                if (finalDoc || isCurrentQuestionnaire(questionnaireUsage.getQuestionnaire())) {
                    // TODO : if usage is not in current qn, also, this qn is not saved
                    // this will cause problem because it should not be included. so, may have to
                    // filter out in initanswerheaders
                    usages.add(questionnaireUsage);
                }
            }
        }
        return usages;
    }

    /*
     * set up answer headers for the initial load of module questionnaire answers
     */
    protected List<AnswerHeader> initAnswerHeaders(ModuleQuestionnaireBean moduleQuestionnaireBean, Map<String, AnswerHeader> answerHeaderMap, boolean finalDoc) {
        List<AnswerHeader> answerHeaders = new ArrayList<AnswerHeader>();
        for (QuestionnaireUsage questionnaireUsage : getPublishedQuestionnaire(moduleQuestionnaireBean.getModuleItemCode(),
                moduleQuestionnaireBean.getModuleSubItemCode(), finalDoc)) {
            String questionnaireId = questionnaireUsage.getQuestionnaire().getQuestionnaireId();
            if (answerHeaderMap.containsKey(questionnaireId)) {
                answerHeaders.add(answerHeaderMap.get(questionnaireId));
                if (!questionnaireUsage.getQuestionnaire().getQuestionnaireRefId().equals(answerHeaderMap.get(questionnaireId).getQuestionnaireRefIdFk())) {
                    // the current qnaire is "Active"
                    if (questionnaireUsage.getQuestionnaire().getIsFinal()) {
                        answerHeaderMap.get(questionnaireId).setNewerVersionPublished(true);
                        answerHeaderMap.get(questionnaireId).setActiveQuestionnaire(true);
                    }
                    else {
                        answerHeaderMap.get(questionnaireId).setActiveQuestionnaire(false);
                    }
                }
            }
            else {
                if ((!finalDoc || isCurrentQuestionnaire(questionnaireUsage.getQuestionnaire()))
                        && questionnaireUsage.getQuestionnaire().getIsFinal()) {
                    // filter out an not saved and usage is not include in current qn
                    answerHeaders.add(setupAnswerForQuestionnaire(questionnaireUsage.getQuestionnaire(), moduleQuestionnaireBean));
                }
            }
        }
        return answerHeaders;
    }

    /**
     * 
     * @see org.kuali.kra.questionnaire.answer.QuestionnaireAnswerService#getNewVersionAnswerHeader(org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean,
     *      org.kuali.kra.questionnaire.Questionnaire)
     */
    public AnswerHeader getNewVersionAnswerHeader(ModuleQuestionnaireBean moduleQuestionnaireBean, Questionnaire questionnaire) {
        AnswerHeader answerHeader = new AnswerHeader();
        List<QuestionnaireUsage> usages = getPublishedQuestionnaire(moduleQuestionnaireBean.getModuleItemCode(),
                moduleQuestionnaireBean.getModuleSubItemCode(), moduleQuestionnaireBean.isFinalDoc());
        for (QuestionnaireUsage questionnaireUsage : usages) {
            if (questionnaireUsage.getQuestionnaire().getQuestionnaireId().equals(questionnaire.getQuestionnaireId())
                    && questionnaireUsage.getQuestionnaire().getSequenceNumber() > questionnaire.getSequenceNumber()) {
                answerHeader = setupAnswerForQuestionnaire(questionnaireUsage.getQuestionnaire(), moduleQuestionnaireBean);
            }
        }
        return answerHeader;
    }


    /**
     * 
     * @see org.kuali.kra.questionnaire.answer.QuestionnaireAnswerService#versioningQuestionnaireAnswer(org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean)
     */
    public List<AnswerHeader> versioningQuestionnaireAnswer(ModuleQuestionnaireBean moduleQuestionnaireBean,
            Integer newSequenceNumber) {
        List<AnswerHeader> newAnswerHeaders = new ArrayList<AnswerHeader>();
        List<String> questionnaireIds = getAssociateedQuestionnaireIds(moduleQuestionnaireBean);
        for (AnswerHeader answerHeader : retrieveAnswerHeaders(moduleQuestionnaireBean)) {
            if (questionnaireIds.contains(answerHeader.getQuestionnaire().getQuestionnaireId())) {
                AnswerHeader copiedAnswerHeader = (AnswerHeader) ObjectUtils.deepCopy(answerHeader);
                copiedAnswerHeader.setModuleSubItemKey(newSequenceNumber.toString());
                copiedAnswerHeader.setAnswerHeaderId(null);
                for (Answer answer : copiedAnswerHeader.getAnswers()) {
                    answer.setId(null);
                }
                newAnswerHeaders.add(copiedAnswerHeader);
            }
        }
        return newAnswerHeaders;
    }

    /**
     * Copy all of the answer headers associated with a source ModuleQuestionnaireBean and associate them with a destination
     * ModuleQuestionnaire Bean. This method DOES NOT persist any of the newly created objects.
     * 
     * @param srcModuleQuestionnaireBean the ModulQuestionnaireBean containing the data pointing to the source questionnaires.
     * @param newModuleQuestionnaireBean the ModuleQuestionnaireBean you would like to copy the AnswerHeader objects to.
     * 
     * @return a list of AnswerHeader objects.
     */
    protected List<AnswerHeader> copyAnswerHeadersToNewModuleQB(ModuleQuestionnaireBean srcModuleQuestionnaireBean,
            ModuleQuestionnaireBean destModuleQuestionnaireBean) {

        List<AnswerHeader> newAnswerHeaders = new ArrayList<AnswerHeader>();
        List<String> questionnaireIds = getAssociateedQuestionnaireIds(srcModuleQuestionnaireBean);
        for (AnswerHeader answerHeader : retrieveAnswerHeaders(srcModuleQuestionnaireBean)) {
            if (questionnaireIds.contains(answerHeader.getQuestionnaire().getQuestionnaireId())) {
                AnswerHeader copiedAnswerHeader = (AnswerHeader) ObjectUtils.deepCopy(answerHeader);
                copiedAnswerHeader.setNewModuleQuestionnaireBeanReferenceData(destModuleQuestionnaireBean);
                copiedAnswerHeader.setAnswerHeaderId(null);
                for (Answer answer : copiedAnswerHeader.getAnswers()) {
                    answer.setId(null);
                }
                newAnswerHeaders.add(copiedAnswerHeader);
            }
        }
        return newAnswerHeaders;
    }

    /**
     * This will be called when 'questionnaire' page is clicked.
     * 
     * @see org.kuali.kra.questionnaire.answer.QuestionnaireAnswerService#getQuestionnaireAnswer(org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean)
     */
    public List<AnswerHeader> getQuestionnaireAnswer(ModuleQuestionnaireBean moduleQuestionnaireBean) {
        Map<String, AnswerHeader> answerHeaderMap = new HashMap<String, AnswerHeader>();
        List<AnswerHeader> answers = retrieveAnswerHeaders(moduleQuestionnaireBean);
        for (AnswerHeader answerHeader : answers) {
            if (!answerHeaderMap.containsKey(answerHeader.getQuestionnaire().getQuestionnaireId())
                    || Long.parseLong(answerHeaderMap.get(answerHeader.getQuestionnaire().getQuestionnaireId()).getQuestionnaireRefIdFk()) 
                            < Long.parseLong(answerHeader.getQuestionnaireRefIdFk())) {
                setupChildAnswerIndicator(answerHeader.getAnswers());
                answerHeaderMap.put(answerHeader.getQuestionnaire().getQuestionnaireId(), answerHeader);
            }
        }

        List<AnswerHeader> answerHeaders = initAnswerHeaders(moduleQuestionnaireBean, answerHeaderMap,
                moduleQuestionnaireBean.isFinalDoc());
        for (AnswerHeader answerHeader : answerHeaders) {
            Collections.sort(answerHeader.getAnswers(), new AnswerComparator());
            answerHeader.setCompleted(isQuestionnaireAnswerComplete(answerHeader.getAnswers()));
        }
        return answerHeaders;
    }

    protected List<AnswerHeader> retrieveAnswerHeaders(ModuleQuestionnaireBean moduleQuestionnaireBean) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(MODULE_ITEM_CODE, moduleQuestionnaireBean.getModuleItemCode());
        fieldValues.put(MODULE_SUB_ITEM_CODE, moduleQuestionnaireBean.getModuleSubItemCode());
        fieldValues.put(MODULE_ITEM_KEY, moduleQuestionnaireBean.getModuleItemKey());
        fieldValues.put(MODULE_SUB_ITEM_KEY, moduleQuestionnaireBean.getModuleSubItemKey());
        return (List<AnswerHeader>) businessObjectService.findMatching(AnswerHeader.class, fieldValues);
    }

    protected List<String> getAssociateedQuestionnaireIds(ModuleQuestionnaireBean moduleQuestionnaireBean) {
        List<String> questionnaireIds = new ArrayList<String>();
        for (QuestionnaireUsage questionnaireUsage : getPublishedQuestionnaire(moduleQuestionnaireBean.getModuleItemCode(),
                moduleQuestionnaireBean.getModuleSubItemCode(), moduleQuestionnaireBean.isFinalDoc())) {
            questionnaireIds.add(questionnaireUsage.getQuestionnaire().getQuestionnaireId());
        }
        return questionnaireIds;

    }

    protected boolean isCurrentQuestionnaire(Questionnaire questionnaire) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("questionnaireId", questionnaire.getQuestionnaireId());
        List<Questionnaire> questionnaires = (List<Questionnaire>) businessObjectService.findMatchingOrderBy(Questionnaire.class, fieldValues, "sequenceNumber", false);
        // "isFinal" is used to check whether the questionnaire is 'Active' or not.
        return questionnaire.getQuestionnaireRefId().equals(questionnaires.get(0).getQuestionnaireRefId());
        // && questionnaire.getIsFinal();
    }

    /**
     * This method returns the latest questionnaire instance associated with the given questionnaire ID; the latest instance 
     * is the one with the largest sequence number.
     * @param questionnaireId
     * @return
     */
    protected Questionnaire getLatestQuestionnaireVersion(Integer questionnaireId) {
        Questionnaire latestQnnrInstance = null;
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("questionnaireId", questionnaireId.toString());
        List<Questionnaire> questionnaires = (List<Questionnaire>) businessObjectService.findMatchingOrderBy(Questionnaire.class,
                fieldValues, "sequenceNumber", false);
        // since we sorted by descending order of seq numbers, and the largest seq number is the latest version, so we return the
        // first element of the results list
        if(!questionnaires.isEmpty()) {
            latestQnnrInstance = questionnaires.get(0);            
        }
        return latestQnnrInstance;        
    }
    
    
    /**
     * @see org.kuali.kra.questionnaire.answer.QuestionnaireAnswerService#checkIfQuestionnaireIsActiveForModule(java.lang.Integer, java.lang.String, java.lang.String)
     */
    public boolean checkIfQuestionnaireIsActiveForModule(Integer questionnaireId, String moduleItemCode, String moduleSubItemCode) {
        boolean isActive = false;
        Questionnaire latestQnnrInstance = getLatestQuestionnaireVersion(questionnaireId);
        if(null != latestQnnrInstance && latestQnnrInstance.getIsFinal()) {
            isActive = latestQnnrInstance.hasUsageFor(moduleItemCode, moduleSubItemCode);
        }       
        return isActive;               
    }

    /**
     * 
     * @see org.kuali.kra.questionnaire.answer.QuestionnaireAnswerService#copyAnswerToNewVersion(org.kuali.kra.questionnaire.answer.AnswerHeader,
     *      org.kuali.kra.questionnaire.answer.AnswerHeader)
     */
    public void copyAnswerToNewVersion(AnswerHeader oldAnswerHeader, AnswerHeader newAnswerHeader) {
        List<List<Answer>> oldParentAnswers = setupParentAnswers(oldAnswerHeader.getAnswers());
        List<List<Answer>> newParentAnswers = setupParentAnswers(newAnswerHeader.getAnswers());
        for (Answer oldAnswer : oldAnswerHeader.getAnswers()) {
            if (oldAnswer.getQuestionnaireQuestion().getParentQuestionNumber() == 0
                    && StringUtils.isNotBlank(oldAnswer.getAnswer())) {
                for (Answer newAnswer : newAnswerHeader.getAnswers()) {
                    if (oldAnswer.getQuestion().getQuestionId().equals(newAnswer.getQuestion().getQuestionId())
                            && newAnswer.getQuestionnaireQuestion().getParentQuestionNumber() == 0
                            && oldAnswer.getQuestion().getQuestionRefId().equals(newAnswer.getQuestion().getQuestionRefId())) {
                        newAnswer.setAnswer(oldAnswer.getAnswer());
                        newAnswer.setMatchedChild(YES);
                        break;
                    }
                }
            }
            else if (oldAnswer.getQuestionnaireQuestion().getParentQuestionNumber() > 0
                    && StringUtils.isNotBlank(oldAnswer.getAnswer())) {
                copyChildAnswer(oldAnswer, oldParentAnswers, newAnswerHeader, newParentAnswers);
            }
        }
        // set up indicator then to empty answer that should not be copied
        setupChildAnswerIndicator(newAnswerHeader.getAnswers());
        for (Answer answer : newAnswerHeader.getAnswers()) {
            if (StringUtils.isNotBlank(answer.getAnswer()) && NO.equals(answer.getMatchedChild())) {
                answer.setAnswer("");
            }
        }
        newAnswerHeader.setCompleted(isQuestionnaireAnswerComplete(newAnswerHeader.getAnswers()));
    }


    /**
     * @see org.kuali.kra.questionnaire.answer.QuestionnaireAnswerService#copyAnswerHeaders(org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean,
     *      org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean)
     */
    public List<AnswerHeader> copyAnswerHeaders(ModuleQuestionnaireBean srcModuleQuestionnaireBean,
            ModuleQuestionnaireBean destModuleQuestionnaireBean) {
        List<AnswerHeader> answerHeaders = copyAnswerHeadersToNewModuleQB(srcModuleQuestionnaireBean, destModuleQuestionnaireBean);
        for (AnswerHeader header : answerHeaders) {
            businessObjectService.save(header);
        }
        return answerHeaders;
    }


    /**
     * 
     * @see org.kuali.kra.questionnaire.answer.QuestionnaireAnswerService#preSave(java.util.List)
     */
    public void preSave(List<AnswerHeader> answerHeaders) {
        for (AnswerHeader answerHeader : answerHeaders) {
            int i = 0;
            for (Answer answer : answerHeader.getAnswers()) {
                if (answer.getQuestion().getMaxAnswers() != null && answer.getQuestion().getMaxAnswers() > 1
                        && answer.getAnswerNumber() == 1) {
                    moveAnswer(answerHeader.getAnswers(), i);
                }
                i++;
            }
            answerHeader.setCompleted(isQuestionnaireAnswerComplete(answerHeader.getAnswers()));
        }
    }

    /*
     * if maxanswer > 1. Then make sure non-blank answers are moved to top of the answer array. This is for coeus equivalency
     */
    protected void moveAnswer(List<Answer> answers, int index) {
        int i = 0;
        while (i < answers.get(index).getQuestion().getMaxAnswers() - 1) {
            if (StringUtils.isBlank(answers.get(index + i).getAnswer())) {
                int j = i + 1;
                while (j < answers.get(index).getQuestion().getMaxAnswers()) {
                    if (StringUtils.isNotBlank(answers.get(index + j).getAnswer())) {
                        answers.get(index + i).setAnswer(answers.get(index + j).getAnswer());
                        answers.get(index + j).setAnswer("");
                        break;
                    }
                    j++;
                }
            }
            i++;
        }
    }

    /*
     * 
     */
    protected void copyChildAnswer(Answer oldAnswer, List<List<Answer>> oldParentAnswers, AnswerHeader newAnswerHeader,
            List<List<Answer>> newParentAnswers) {
        for (Answer newAnswer : newAnswerHeader.getAnswers()) {
            if (oldAnswer.getQuestion().getQuestionId().equals(newAnswer.getQuestion().getQuestionId())
                    && newAnswer.getQuestionnaireQuestion().getParentQuestionNumber() > 0
                    && YES.equals(newParentAnswers.get(newAnswer.getQuestionnaireQuestion().getParentQuestionNumber()).get(0)
                            .getMatchedChild()) && isSameLevel(oldAnswer, oldParentAnswers, newAnswer, newParentAnswers)) {
                newAnswer.setAnswer(oldAnswer.getAnswer());
                newAnswer.setMatchedChild(YES);
                break;
            }
        }

    }

    /*
     * go up the question answer hierarchy till the first level. make sure all question are matched with same version.
     */
    protected boolean isSameLevel(Answer oldAnswer, List<List<Answer>> oldParentAnswers, Answer newAnswer,
            List<List<Answer>> newParentAnswers) {
        boolean questionHierarchyMatched = true;
        Answer oAnswer = oldAnswer;
        Answer nAnswer = newAnswer;
        while (questionHierarchyMatched && oAnswer.getQuestionnaireQuestion().getParentQuestionNumber() > 0
                && nAnswer.getQuestionnaireQuestion().getParentQuestionNumber() > 0) {
            if (!oAnswer.getQuestionRefIdFk().equals(nAnswer.getQuestionRefIdFk())) {
                questionHierarchyMatched = false;
            }
            oAnswer = oldParentAnswers.get(oAnswer.getQuestionnaireQuestion().getParentQuestionNumber()).get(0);
            nAnswer = newParentAnswers.get(nAnswer.getQuestionnaireQuestion().getParentQuestionNumber()).get(0);
        }
        return questionHierarchyMatched && oAnswer.getQuestionnaireQuestion().getParentQuestionNumber() == 0
                && nAnswer.getQuestionnaireQuestion().getParentQuestionNumber() == 0;
    }

    /*
     * set up the parent answer for a child question answer. Parent question may contain multiple answers, so a 'List' type is
     * needed.
     */
    protected List<List<Answer>> setupParentAnswers(List<Answer> answers) {
        List<List<Answer>> parentAnswers = initParentAnswers(answers);
        for (Answer answer : answers) {
            parentAnswers.get(answer.getQuestionNumber()).add(answer);
        }
        return parentAnswers;
    }

    /*
     * init parent answers array with empty array
     */
    protected List<List<Answer>> initParentAnswers(List<Answer> answers) {
        int maxQuestionNumber = 0;
        List<List<Answer>> parentAnswers = new ArrayList<List<Answer>>();
        for (Answer answer : answers) {
            if (answer.getQuestionNumber() > maxQuestionNumber) {
                while (maxQuestionNumber < answer.getQuestionNumber()) {
                    parentAnswers.add(new ArrayList<Answer>());
                    maxQuestionNumber++;
                }
            }
        }
        parentAnswers.add(new ArrayList<Answer>());
        return parentAnswers;

    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /*
     * initialize answer fields based on question
     */
    protected AnswerHeader setupAnswerForQuestionnaire(Questionnaire questionnaire, ModuleQuestionnaireBean moduleQuestionnaireBean) {
        AnswerHeader answerHeader = new AnswerHeader(moduleQuestionnaireBean, questionnaire.getQuestionnaireRefIdAsLong());
        answerHeader.setQuestionnaire(questionnaire);
        List<Answer> answers = new ArrayList<Answer>();
        for (QuestionnaireQuestion question : questionnaire.getQuestionnaireQuestions()) {
            if (question.getParentQuestionNumber() == 0) {
                answers.addAll(setupAnswersForQuestion(question));
                answers.addAll(getChildQuestions(questionnaire, question));
            }
        }
        setupChildAnswerIndicator(answers);
        answerHeader.setAnswers(answers);
        return answerHeader;
    }


    /*
     * Load the descendant questions for this questionnaire question.
     */
    protected List<Answer> getChildQuestions(Questionnaire questionnaire, QuestionnaireQuestion question) {
        List<Answer> answers = new ArrayList<Answer>();
        for (QuestionnaireQuestion questionnaireQuestion : questionnaire.getQuestionnaireQuestions()) {
            if (questionnaireQuestion.getParentQuestionNumber() != 0
                    && questionnaireQuestion.getParentQuestionNumber().equals(question.getQuestionNumber())) {
                answers.addAll(setupAnswersForQuestion(questionnaireQuestion));
                answers.addAll(getChildQuestions(questionnaire, questionnaireQuestion));
            }
        }
        return answers;

    }

    /*
     * Utility method to really fill the answer fields from question.
     */
    protected List<Answer> setupAnswersForQuestion(QuestionnaireQuestion questionnaireQuestion) {
        List<Answer> answers = new ArrayList<Answer>();
        int maxAnswers = 1;
        if (questionnaireQuestion.getQuestion().getMaxAnswers() != null) {
            maxAnswers = questionnaireQuestion.getQuestion().getMaxAnswers();
        }
        for (int i = 0; i < maxAnswers; i++) {
            Answer answer = new Answer();
            answer.setQuestion(questionnaireQuestion.getQuestion());
            answer.setQuestionNumber(questionnaireQuestion.getQuestionNumber());
            answer.setQuestionRefIdFk(questionnaireQuestion.getQuestion().getQuestionRefId());
            answer.setQuestionnaireQuestionsIdFk(questionnaireQuestion.getQuestionnaireQuestionsId());
            answer.setQuestionnaireQuestion(questionnaireQuestion);
            answer.setAnswerNumber(i + 1);
            answers.add(answer);
        }
        return answers;
    }

    /**
     * 
     * @see org.kuali.kra.questionnaire.answer.QuestionnaireAnswerService#setupChildAnswerIndicator(java.util.List)
     */
    public void setupChildAnswerIndicator(List<Answer> answers) {
        List<List<Answer>> parentAnswers = setupParentAnswers(answers);
        for (Answer answer : answers) {
            if (answer.getQuestionnaireQuestion().getParentQuestionNumber() > 0) {
                answer.setParentAnswer(parentAnswers.get(answer.getQuestionnaireQuestion().getParentQuestionNumber()));
            }
        }
        Collections.sort(answers, new AnswerComparator());

        for (Answer answer : answers) {
            if (answer.getQuestionnaireQuestion().getParentQuestionNumber() == 0) {
                answer.setMatchedChild(YES);
            }
            else {
                answer.setParentAnswer(parentAnswers.get(answer.getQuestionnaireQuestion().getParentQuestionNumber()));
                if (StringUtils.isBlank(answer.getQuestionnaireQuestion().getCondition())) {
                    if (isParentNotDisplayed(parentAnswers.get(answer.getQuestionnaireQuestion().getParentQuestionNumber()))) {
                        answer.setMatchedChild(NO);
                    }
                    else {
                        answer.setMatchedChild(YES);
                    }
                }
                else if (isParentNotDisplayed(parentAnswers.get(answer.getQuestionnaireQuestion().getParentQuestionNumber()))) {
                    answer.setMatchedChild(NO);
                }
                else if (isAnyAnswerMatched(answer.getQuestionnaireQuestion().getCondition(), parentAnswers.get(answer
                        .getQuestionnaireQuestion().getParentQuestionNumber()), answer.getQuestionnaireQuestion()
                        .getConditionValue())) {
                    answer.setMatchedChild(YES);
                }
                else {
                    answer.setMatchedChild(NO);
                }
            }
        }

    }

    /*
     * check if all the required answers are entered.
     */
    public boolean isQuestionnaireAnswerComplete(List<Answer> answers) {

        boolean isComplete = true;
        for (Answer answer : answers) {
            if (YES.equals(answer.getMatchedChild()) && StringUtils.isBlank(answer.getAnswer()) && answer.getAnswerNumber() == 1) {
                isComplete = false;
                break;
            }
        }
        return isComplete;
    }

    /*
     * Check if parent answer : if it is not matched to be displayed or answer(s) is entered.
     */
    protected boolean isParentNotDisplayed(List<Answer> parentAnswers) {
        boolean valid = true;
        for (Answer answer : parentAnswers) {
            // parent is not displayed
            if (NO.equals(answer.getMatchedChild())) {
                valid = true;
                break;
            }
            else if (YES.equals(answer.getMatchedChild())) {
                valid = false;
                break;
            }
            else {
                // if one of the parents answer is entered
                valid = valid && StringUtils.isBlank(answer.getAnswer());
                if (!valid) {
                    break;
                }
            }
        }
        return valid;
    }

    /*
     * check if any answer matched conditionvalue
     */
    protected boolean isAnyAnswerMatched(String condition, List<Answer> parentAnswers, String conditionValue) {
        boolean valid = false;
        for (Answer answer : parentAnswers) {
            if (StringUtils.isNotBlank(answer.getAnswer())) {
                valid = isAnswerMatched(condition, answer.getAnswer(), conditionValue);
            }
            if (valid) {
                break;
            }
        }
        return valid;
    }

    /*
     * Following are supported condition : var responseArray = [ 'select', 'Contains text value', 'Begins with text', 'Ends with
     * text', 'Matches text', 'Less than number', 'Less than or equals number', 'Equals number', 'Not Equal to number', 'Greater
     * than or equals number', 'Greater than number', 'Before date', 'After date' ];
     */

    protected boolean isAnswerMatched(String condition, String parentAnswer, String conditionValue) {
        boolean valid = false;
        if (ConditionType.CONTAINS_TEXT.getCondition().equals(condition)) {
            valid = StringUtils.containsIgnoreCase(parentAnswer, conditionValue);
        }
        else if (ConditionType.BEGINS_WITH_TEXT.getCondition().equals(condition)) {
            valid = (StringUtils.startsWithIgnoreCase(parentAnswer, conditionValue));
        }
        else if (ConditionType.ENDS_WITH_TEXT.getCondition().equals(condition)) {
            valid = (StringUtils.endsWithIgnoreCase(parentAnswer, conditionValue));
        }
        else if (ConditionType.MATCH_TEXT.getCondition().equals(condition)) {
            valid = parentAnswer.equalsIgnoreCase(conditionValue);
        }
        else if (Integer.parseInt(condition) >= 5 && Integer.parseInt(condition) <= 10) {
            valid = (ConditionType.LESS_THAN_NUMBER.getCondition().equals(condition) && (Integer.parseInt(parentAnswer) < Integer
                    .parseInt(conditionValue)))
                    || (ConditionType.LESS_THAN_OR_EQUALS_NUMBER.getCondition().equals(condition) && (Integer
                            .parseInt(parentAnswer) <= Integer.parseInt(conditionValue)))
                    || (ConditionType.EQUALS_NUMBER.getCondition().equals(condition) && (Integer.parseInt(parentAnswer) == Integer
                            .parseInt(conditionValue)))
                    || (ConditionType.NOT_EQUAL_TO_NUMBER.getCondition().equals(condition) && (Integer.parseInt(parentAnswer) != Integer
                            .parseInt(conditionValue)))
                    || (ConditionType.GREATER_THAN_OR_EQUALS_NUMBER.getCondition().equals(condition) && (Integer
                            .parseInt(parentAnswer) >= Integer.parseInt(conditionValue)))
                    || (ConditionType.GREATER_THAN_NUMBER.getCondition().equals(condition) && (Integer.parseInt(parentAnswer) > Integer
                            .parseInt(conditionValue)));
        }
        else if (Integer.parseInt(condition) >= 11) {
            final DateFormat dateFormat = new SimpleDateFormat(Constants.DEFAULT_DATE_FORMAT_PATTERN);
            try {
                Date date1 = new Date(dateFormat.parse(parentAnswer).getTime());
                Date date2 = new Date(dateFormat.parse(conditionValue).getTime());
                valid = (ConditionType.BEFORE_DATE.getCondition().equals(condition) && (date1.before(date2)))
                        || (ConditionType.AFTER_DATE.getCondition().equals(condition) && (date1.after(date2)));
            }
            catch (Exception e) {

            }

        }
        return valid;
    }

    /*
     * enum for conditions.
     */
    protected enum ConditionType {
        CONTAINS_TEXT("1"), BEGINS_WITH_TEXT("2"), ENDS_WITH_TEXT("3"), MATCH_TEXT("4"), LESS_THAN_NUMBER("5"), LESS_THAN_OR_EQUALS_NUMBER(
                "6"), EQUALS_NUMBER("7"), NOT_EQUAL_TO_NUMBER("8"), GREATER_THAN_OR_EQUALS_NUMBER("9"), GREATER_THAN_NUMBER("10"), BEFORE_DATE(
                "11"), AFTER_DATE("12");

        String condition;

        ConditionType(String condition) {
            this.condition = condition;
        }

        public String getCondition() {
            return condition;
        }


    }

    public void setProtocolFinderDao(ProtocolFinderDao protocolFinderDao) {
        this.protocolFinderDao = protocolFinderDao;
    }

    /**
     * 
     * @see org.kuali.kra.questionnaire.answer.QuestionnaireAnswerService#getAnswerHeadersForProtocol(java.lang.String)
     */
    public List<AnswerHeader> getAnswerHeadersForProtocol(String protocolNumber) {
        boolean isAmendmentOrRenewal = protocolNumber.contains("A") || protocolNumber.contains("R");
        String originalProtocolNumber = protocolNumber;
        if (isAmendmentOrRenewal) {
            originalProtocolNumber = protocolNumber.substring(0, 10);
        }
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(MODULE_ITEM_CODE, CoeusModule.IRB_MODULE_CODE);
        fieldValues.put(MODULE_ITEM_KEY, getProtocolNumbers(originalProtocolNumber));
        // fieldValues.put(MODULE_ITEM_KEY, protocolNumber);
        List<AnswerHeader> answerHeaders = (List<AnswerHeader>) businessObjectService.findMatching(AnswerHeader.class, fieldValues);
        if (isAmendmentOrRenewal) {
            List<AnswerHeader> headers = new ArrayList<AnswerHeader>();
            for (AnswerHeader answerHeader : answerHeaders) {
                if (!(CoeusSubModule.PROTOCOL_SUBMISSION.equals(answerHeader.getModuleSubItemCode()) && answerHeader
                        .getModuleItemKey().equals(originalProtocolNumber))
                        && answerHeader.getModuleItemKey().equals(protocolNumber)) {
                    // && (CoeusSubModule.ZERO_SUBMODULE.equals(answerHeader.getModuleSubItemCode()) ||
                    // answerHeader.getModuleItemKey().equals(protocolNumber))) {
                    headers.add(answerHeader);
                }
            }
            return headers;
        }
        else {
            return answerHeaders;
        }
    }

    /*
     * get the unique protocol numbers for the protocolnumber. The unique protocol numbers may contain amendment and renewal
     * protocol's protocol numbers.
     */
    private List<String> getProtocolNumbers(String protocolNumber) {
        List<String> protocolNumbers = new ArrayList<String>();
        for (Protocol protocol : protocolFinderDao.findProtocols(protocolNumber)) {
            if (!protocolNumbers.contains(protocol.getProtocolNumber())) {
                protocolNumbers.add(protocol.getProtocolNumber());
            }
        }
        return protocolNumbers;
    }
}
