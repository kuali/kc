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

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.common.framework.module.CoeusSubModule;
import org.kuali.coeus.common.questionnaire.framework.answer.Answer;
import org.kuali.coeus.common.questionnaire.framework.answer.QuestionnaireAnswerService;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.kra.coi.questionnaire.DisclosureModuleQuestionnaireBean;
import org.kuali.kra.iacuc.questionnaire.IacucProtocolModuleQuestionnaireBean;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.questionnaire.ProtocolModuleQuestionnaireBean;
import org.kuali.coeus.common.framework.krms.KrmsRulesContext;
import org.kuali.coeus.common.framework.krms.KrmsRulesExecutionService;
import org.kuali.coeus.propdev.impl.questionnaire.ProposalDevelopmentModuleQuestionnaireBean;
import org.kuali.coeus.propdev.impl.s2s.question.ProposalDevelopmentS2sModuleQuestionnaireBean;
import org.kuali.coeus.propdev.impl.person.question.ProposalPersonModuleQuestionnaireBean;
import org.kuali.kra.protocol.questionnaire.QuestionnaireHelperBase;
import org.kuali.coeus.common.questionnaire.framework.core.Questionnaire;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireConstants;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireQuestion;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireService;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireUsage;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean;
import org.kuali.coeus.common.questionnaire.framework.question.QuestionDTO;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * This class implemented the questionnaire answer related methods.
 */

@Component("questionnaireAnswerService")
public class QuestionnaireAnswerServiceImpl implements QuestionnaireAnswerService {

    private static final String MODULE_ITEM_CODE = "moduleItemCode";
    private static final String MODULE_SUB_ITEM_CODE = "moduleSubItemCode";
    private static final String MODULE_ITEM_KEY = "moduleItemKey";
    private static final String MODULE_SUB_ITEM_KEY = "moduleSubItemKey";
    public static final String SEQUENCE_NUMBER = "sequenceNumber";
    public static final String QUESTIONNAIRE_AGENDA_TYPE_ID = "KC1004";

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;

    @Autowired
    @Qualifier("questionnaireService")
    private QuestionnaireService questionnaireService;

    @Autowired
    @Qualifier("krmsRulesExecutionService")
    private KrmsRulesExecutionService krmsRulesExecutionService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }



    /*
     * Get the questionnaire that is 'final' for the specified module.
     */
    public List<QuestionnaireUsage> getPublishedQuestionnaire(ModuleQuestionnaireBean moduleQuestionnaireBean) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(MODULE_ITEM_CODE, moduleQuestionnaireBean.getModuleItemCode());
        fieldValues.put(MODULE_SUB_ITEM_CODE, moduleQuestionnaireBean.getModuleSubItemCode());

        List<QuestionnaireUsage> usages = new ArrayList<QuestionnaireUsage>();
        List<String> questionnaireIds = new ArrayList<String>();
        List<QuestionnaireUsage> questionnaireUsages = (List<QuestionnaireUsage>) businessObjectService.findMatching(
                QuestionnaireUsage.class, fieldValues);

        // use this sort, to list the higher version before lower version
        if (CollectionUtils.isNotEmpty(questionnaireUsages)) {
            Collections.sort((List<QuestionnaireUsage>) questionnaireUsages);
        }
        
        List<String> ruleIds = new ArrayList<String>();
        for (QuestionnaireUsage questionnaireUsage : questionnaireUsages) {
            if (StringUtils.isNotBlank(questionnaireUsage.getRuleId())) {
                ruleIds.add(questionnaireUsage.getRuleId());
            }
        }
        Map<String, Boolean> ruleResults = new HashMap<String, Boolean>();
        if (!ruleIds.isEmpty()) {
            ruleResults = runApplicableRules(ruleIds, moduleQuestionnaireBean);
        }

        // the higher version will have higher questionnaireid
        for (QuestionnaireUsage questionnaireUsage : questionnaireUsages) {
            if (!questionnaireIds.contains(questionnaireUsage.getQuestionnaire().getQuestionnaireSeqId())) {
                questionnaireIds.add(questionnaireUsage.getQuestionnaire().getQuestionnaireSeqId());

                if (moduleQuestionnaireBean.isFinalDoc() || (getQuestionnaireService().isCurrentQuestionnaire(questionnaireUsage.getQuestionnaire()) && questionnaireUsage.getQuestionnaire().isActive())) {
                    if (StringUtils.isNotBlank(questionnaireUsage.getRuleId())) {
                        if (ruleResults.containsKey(questionnaireUsage.getRuleId()) && ruleResults.get(questionnaireUsage.getRuleId()).booleanValue()) {
                            usages.add(questionnaireUsage);
                        }
                    } else {
                        usages.add(questionnaireUsage);
                    }
                }

            }
        }
        return usages;
    }

    /*
     * set up answer headers for the initial load of module questionnaire answers
     */
    protected List<AnswerHeader> initAnswerHeaders(ModuleQuestionnaireBean moduleQuestionnaireBean, Map<String, AnswerHeader> answerHeaderMap) {
        List<AnswerHeader> answerHeaders = new ArrayList<AnswerHeader>();
        for (QuestionnaireUsage questionnaireUsage : getPublishedQuestionnaire(moduleQuestionnaireBean)) {
            String questionnaireId = questionnaireUsage.getQuestionnaire().getQuestionnaireSeqId();
            if (answerHeaderMap.containsKey(questionnaireId)) {
                answerHeaders.add(answerHeaderMap.get(questionnaireId));
                if (!questionnaireUsage.getQuestionnaire().getId().equals(answerHeaderMap.get(questionnaireId).getQuestionnaireId())) {
                    // the current qnaire is "Active"
                    if (questionnaireUsage.getQuestionnaire().isActive()) {
                        answerHeaderMap.get(questionnaireId).setNewerVersionPublished(true);
                        answerHeaderMap.get(questionnaireId).setActiveQuestionnaire(true);
                    }
                    else {
                        answerHeaderMap.get(questionnaireId).setActiveQuestionnaire(false);
                    }
                }
                answerHeaderMap.get(questionnaireId).setLabel(questionnaireUsage.getQuestionnaireLabel());
            }
            else {

                if ((!moduleQuestionnaireBean.isFinalDoc() && getQuestionnaireService().isCurrentQuestionnaire(questionnaireUsage.getQuestionnaire()))
                        && questionnaireUsage.getQuestionnaire().isActive()) {
                    // filter out an not saved and usage is not include in current qn
                    answerHeaders.add(setupAnswerForQuestionnaire(questionnaireUsage, moduleQuestionnaireBean));
                }

            }
        }
        return answerHeaders;
    }

    /**
     * 
     * @see org.kuali.coeus.common.questionnaire.framework.answer.QuestionnaireAnswerService#getNewVersionAnswerHeader(org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean,
     *      org.kuali.coeus.common.questionnaire.framework.core.Questionnaire)
     */
    public AnswerHeader getNewVersionAnswerHeader(ModuleQuestionnaireBean moduleQuestionnaireBean, Questionnaire questionnaire) {
        AnswerHeader answerHeader = new AnswerHeader();
        List<QuestionnaireUsage> usages = getPublishedQuestionnaire(moduleQuestionnaireBean);
        for (QuestionnaireUsage questionnaireUsage : usages) {
            if (questionnaireUsage.getQuestionnaire().getQuestionnaireSeqId().equals(questionnaire.getQuestionnaireSeqId())
                    && questionnaireUsage.getQuestionnaire().getSequenceNumber() >= questionnaire.getSequenceNumber()) {
                answerHeader = setupAnswerForQuestionnaire(questionnaireUsage, moduleQuestionnaireBean);
            }
        }
        return answerHeader;
    }


    @Override
    public List<AnswerHeader> versioningQuestionnaireAnswer(ModuleQuestionnaireBean moduleQuestionnaireBean,
            Integer newSequenceNumber) {
        List<AnswerHeader> newAnswerHeaders = new ArrayList<AnswerHeader>();
        List<String> questionnaireIds = getAssociateedQuestionnaireIds(moduleQuestionnaireBean);
        for (AnswerHeader answerHeader : retrieveAnswerHeaders(moduleQuestionnaireBean)) {
            if (questionnaireIds.contains(answerHeader.getQuestionnaire().getQuestionnaireSeqId())) {
                AnswerHeader copiedAnswerHeader = (AnswerHeader) ObjectUtils.deepCopy(answerHeader);
                copiedAnswerHeader.setModuleSubItemKey(newSequenceNumber.toString());
                copiedAnswerHeader.setId(null);
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
     * @param destModuleQuestionnaireBean the ModuleQuestionnaireBean you would like to copy the AnswerHeader objects to.
     * 
     * @return a list of AnswerHeader objects.
     */
    protected List<AnswerHeader> copyAnswerHeadersToNewModuleQB(ModuleQuestionnaireBean srcModuleQuestionnaireBean,
            ModuleQuestionnaireBean destModuleQuestionnaireBean) {

        List<AnswerHeader> newAnswerHeaders = new ArrayList<AnswerHeader>();
        List<String> questionnaireIds = getAssociateedQuestionnaireIds(destModuleQuestionnaireBean);
        List<AnswerHeader> srcModuleAnswerHeaders = retrieveAnswerHeaders(srcModuleQuestionnaireBean);
        for (AnswerHeader answerHeader : srcModuleAnswerHeaders) {
            if (questionnaireIds.contains(answerHeader.getQuestionnaire().getQuestionnaireSeqId())) {
                AnswerHeader copiedAnswerHeader = (AnswerHeader) ObjectUtils.deepCopy(answerHeader);
                copiedAnswerHeader.setNewModuleQuestionnaireBeanReferenceData(destModuleQuestionnaireBean);
                copiedAnswerHeader.setId(null);
                copiedAnswerHeader.setModuleItemKey(destModuleQuestionnaireBean.getModuleItemKey());
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
     * @see org.kuali.coeus.common.questionnaire.framework.answer.QuestionnaireAnswerService#getQuestionnaireAnswer(org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean)
     */
    public List<AnswerHeader> getQuestionnaireAnswer(ModuleQuestionnaireBean moduleQuestionnaireBean) {
        Map<String, AnswerHeader> answerHeaderMap = new HashMap<String, AnswerHeader>();
        List<AnswerHeader> answers = retrieveAnswerHeaders(moduleQuestionnaireBean);

        if (globalVariableService.getUserSession() != null) {
            globalVariableService.getUserSession().removeObject(moduleQuestionnaireBean.getSessionContextKey() + "-rulereferenced");
        }
        for (AnswerHeader answerHeader : answers) {
            if (!answerHeaderMap.containsKey(answerHeader.getQuestionnaire().getQuestionnaireSeqId())
                    || answerHeaderMap.get(answerHeader.getQuestionnaire().getQuestionnaireSeqId()).getQuestionnaire().getSequenceNumber()
                            < answerHeader.getQuestionnaire().getSequenceNumber()) {
                setupChildAnswerIndicator(answerHeader);
                answerHeaderMap.put(answerHeader.getQuestionnaire().getQuestionnaireSeqId(), answerHeader);
            }
        }

        List<AnswerHeader> answerHeaders = initAnswerHeaders(moduleQuestionnaireBean, answerHeaderMap);
        for (AnswerHeader answerHeader : answerHeaders) {
            Collections.sort(answerHeader.getAnswers(), new AnswerComparator());
            answerHeader.setCompleted(isQuestionnaireAnswerComplete(answerHeader.getAnswers()));
            answerHeader.setHasVisibleQuestion(hasVisibleQuestion(answerHeader.getAnswers()));
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
        for (QuestionnaireUsage questionnaireUsage : getPublishedQuestionnaire(moduleQuestionnaireBean)) {
            questionnaireIds.add(questionnaireUsage.getQuestionnaire().getQuestionnaireSeqId());
        }
        return questionnaireIds;

    }

    /**
     * This method returns the latest questionnaire instance associated with the given questionnaire ID; the latest instance 
     * is the one with the largest sequence number.
     * @param questionnaireId
     * @return
     */
    protected Questionnaire getLatestQuestionnaireVersion(Integer questionniareSeqId) {
        Questionnaire latestQnnrInstance = null;
        Map<String, Long> fieldValues = new HashMap<String, Long>();
        fieldValues.put(QuestionnaireConstants.QUESTIONNAIRE_SEQUENCE_ID_PARAMETER_NAME, Long.valueOf(questionniareSeqId));
        List<Questionnaire> questionnaires = (List<Questionnaire>) businessObjectService.findMatchingOrderBy(Questionnaire.class,
                fieldValues, SEQUENCE_NUMBER, false);
        // since we sorted by descending order of seq numbers, and the largest seq number is the latest version, so we return the
        // first element of the results list
        if(!questionnaires.isEmpty()) {
            latestQnnrInstance = questionnaires.get(0);            
        }
        return latestQnnrInstance;        
    }
    
    
    @Override
    public boolean checkIfQuestionnaireIsActiveForModule(Integer questionniareSeqId, String moduleItemCode, String moduleSubItemCode) {
        boolean isActive = false;
        Questionnaire latestQnnrInstance = getLatestQuestionnaireVersion(questionniareSeqId);
        if(null != latestQnnrInstance && latestQnnrInstance.isActive()) {
            isActive = latestQnnrInstance.hasUsageFor(moduleItemCode, moduleSubItemCode);
        }       
        return isActive;               
    }

    @Override
    public boolean checkIfQuestionnaireIsMandatoryForModule(Integer questionniareSeqId, String moduleItemCode, String moduleSubItemCode) {
        Questionnaire latestQnnrInstance = getLatestQuestionnaireVersion(questionniareSeqId);
        if(null != latestQnnrInstance && latestQnnrInstance.isActive()) {
            for (QuestionnaireUsage usage : latestQnnrInstance.getQuestionnaireUsages()) {
                if (usage.isMandatory()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 
     * @see org.kuali.coeus.common.questionnaire.framework.answer.QuestionnaireAnswerService#copyAnswerToNewVersion(org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader,
     *      org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader)
     */
    public void copyAnswerToNewVersion(AnswerHeader oldAnswerHeader, AnswerHeader newAnswerHeader) {
        List<List<Answer>> oldParentAnswers = setupParentAnswers(oldAnswerHeader.getAnswers());
        List<List<Answer>> newParentAnswers = setupParentAnswers(newAnswerHeader.getAnswers());
        for (Answer oldAnswer : oldAnswerHeader.getAnswers()) {
            if (StringUtils.isNotBlank(oldAnswer.getAnswer())) {
                for (Answer newAnswer : newAnswerHeader.getAnswers()) {
                    if (oldAnswer.getQuestion().getQuestionSeqId().equals(newAnswer.getQuestion().getQuestionSeqId())
                            && oldAnswer.getQuestion().getId().equals(newAnswer.getQuestion().getId())
                            && oldAnswer.getAnswerNumber().equals(newAnswer.getAnswerNumber())) {
                        if ((oldAnswer.getQuestionnaireQuestion().getParentQuestionNumber() == 0 
                                && newAnswer.getQuestionnaireQuestion().getParentQuestionNumber() == 0) 
                            || (oldAnswer.getQuestionnaireQuestion().getParentQuestionNumber() > 0
                                && newAnswer.getQuestionnaireQuestion().getParentQuestionNumber() > 0
                                && YES.equals(newParentAnswers.get(newAnswer.getQuestionnaireQuestion().getParentQuestionNumber()).get(0).getMatchedChild())
                                && isSameLevel(oldAnswer, oldParentAnswers, newAnswer, newParentAnswers))) {
                                newAnswer.setAnswer(oldAnswer.getAnswer());
                                newAnswer.setMatchedChild(YES);
                                break;
                        }
                    }
                }
            }
        }
        // set up indicator then to empty answer that should not be copied
        setupChildAnswerIndicator(newAnswerHeader);
        for (Answer answer : newAnswerHeader.getAnswers()) {
            if (StringUtils.isNotBlank(answer.getAnswer()) && NO.equals(answer.getMatchedChild())) {
                answer.setAnswer("");
            }
        }
        newAnswerHeader.setCompleted(isQuestionnaireAnswerComplete(newAnswerHeader.getAnswers()));
    }


    /**
     * @see org.kuali.coeus.common.questionnaire.framework.answer.QuestionnaireAnswerService#copyAnswerHeaders(org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean,
     *      org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean)
     */
    public List<AnswerHeader> copyAnswerHeaders(ModuleQuestionnaireBean srcModuleQuestionnaireBean,
            ModuleQuestionnaireBean destModuleQuestionnaireBean) {
        List<AnswerHeader> answerHeaders = copyAnswerHeadersToNewModuleQB(srcModuleQuestionnaireBean, destModuleQuestionnaireBean);
        for (AnswerHeader header : answerHeaders) {
            businessObjectService.save(header);
        }
        return answerHeaders;
    }


    @Override
    public void preSave(List<AnswerHeader> answerHeaders) {
        for (AnswerHeader answerHeader : answerHeaders) {
            int i = 0;
            for (Answer answer : answerHeader.getAnswers()) {
                if (answer.getQuestion().getMaxAnswers() != null && answer.getQuestion().getMaxAnswers() > 1
                        && answer.getAnswerNumber() == 1) {
                    if(!Long.valueOf(Constants.QUESTION_RESPONSE_TYPE_MULTIPLE_CHOICE).equals(answer.getQuestion().getQuestionTypeId())) {
                        moveAnswer(answerHeader.getAnswers(), i);
                    }
                }
                i++;
            }
            answerHeader.setCompleted(isQuestionnaireAnswerComplete(answerHeader.getAnswers()));
            setupChildAnswerIndicator(answerHeader);
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
     * go up the question answer hierarchy till the first level. make sure all question are matched with same version.
     */
    protected boolean isSameLevel(Answer oldAnswer, List<List<Answer>> oldParentAnswers, Answer newAnswer,
            List<List<Answer>> newParentAnswers) {
        boolean questionHierarchyMatched = true;
        Answer oAnswer = oldAnswer;
        Answer nAnswer = newAnswer;
        while (questionHierarchyMatched && oAnswer.getQuestionnaireQuestion().getParentQuestionNumber() > 0
                && nAnswer.getQuestionnaireQuestion().getParentQuestionNumber() > 0) {
            if (!oAnswer.getQuestionId().equals(nAnswer.getQuestionId())) {
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
    protected AnswerHeader setupAnswerForQuestionnaire(QuestionnaireUsage questionnaireUsage, ModuleQuestionnaireBean moduleQuestionnaireBean) {
    	Questionnaire questionnaire = questionnaireUsage.getQuestionnaire();
        AnswerHeader answerHeader = new AnswerHeader(moduleQuestionnaireBean, questionnaire.getQuestionnaireRefIdAsLong());
        answerHeader.setQuestionnaire(questionnaire);
        List<Answer> answers = new ArrayList<Answer>();
        List<QuestionnaireQuestion> questions = new ArrayList<QuestionnaireQuestion>();
        for (QuestionnaireQuestion question : questionnaire.getQuestionnaireQuestions()) {
            if (question.getParentQuestionNumber() == 0) {
                answers.addAll(setupAnswersForQuestion(question));
                answers.addAll(getChildQuestions(questionnaire, question));
            }
        }
        for (Answer answer : answers) {
            answer.setAnswerHeader(answerHeader);
        }
        answerHeader.setAnswers(answers);
        setupChildAnswerIndicator(answerHeader);
        answerHeader.setLabel(questionnaireUsage.getQuestionnaireLabel());
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
        if (questionnaireQuestion.getQuestion().getDisplayedAnswers() != null && Long.valueOf(Constants.QUESTION_RESPONSE_TYPE_MULTIPLE_CHOICE).equals(questionnaireQuestion.getQuestion().getQuestionTypeId())) {
            if(!questionnaireQuestion.getQuestion().isRadioButton()) {
                maxAnswers = questionnaireQuestion.getQuestion().getDisplayedAnswers();
            }
        }
        for (int i = 0; i < maxAnswers; i++) {
            Answer answer = new Answer();
            answer.setQuestion(questionnaireQuestion.getQuestion());
            answer.setQuestionNumber(questionnaireQuestion.getQuestionNumber());
            answer.setQuestionId(questionnaireQuestion.getQuestion().getId());
            answer.setQuestionnaireQuestionsId(questionnaireQuestion.getId());
            answer.setQuestionnaireQuestion(questionnaireQuestion);
            answer.setAnswerNumber(i + 1);
            answers.add(answer);
        }
        return answers;
    }

    @Override
    public void setupChildAnswerIndicator(AnswerHeader answerHeader) {
        List<Answer> answers = answerHeader.getAnswers();
        List<List<Answer>> parentAnswers = setupParentAnswers(answers);
        List<String> ruleIds = new ArrayList<String>();
        for (Answer answer : answers) {
            if (answer.getQuestionnaireQuestion() != null && answer.getQuestionnaireQuestion().getParentQuestionNumber() > 0) {
                answer.setParentAnswers(parentAnswers.get(answer.getQuestionnaireQuestion().getParentQuestionNumber()));
            }
            if (StringUtils.isNotBlank(answer.getQuestionnaireQuestion().getRuleId())) {
                ruleIds.add(answer.getQuestionnaireQuestion().getRuleId());
            }
            if (ConditionType.RULE_EVALUATION.getCondition().equals(answer.getQuestionnaireQuestion().getCondition())) {
                ruleIds.add(answer.getQuestionnaireQuestion().getConditionValue());
            }
        }
        Collections.sort(answers, new AnswerComparator());
        
        Map<String, Boolean> ruleResults = new HashMap<String, Boolean>();
        if (!ruleIds.isEmpty()) {
             ruleResults = runApplicableRules(ruleIds, getModuleSpecificBean(answerHeader));
        }

        for (Answer answer : answers) {
            QuestionnaireQuestion questionnaireQuestion = answer.getQuestionnaireQuestion();
            if (questionnaireQuestion.getParentQuestionNumber() == 0) {
                String ruleId = questionnaireQuestion.getRuleId();
                if (StringUtils.isNotBlank(ruleId)) {
                    if (ruleResults.containsKey(ruleId) && ruleResults.get(ruleId).booleanValue()) {
                        answer.setMatchedChild(YES);
                        answer.setRuleMatched(true);
                    } else {
                        answer.setMatchedChild(NO);
                        answer.setRuleMatched(false);
                    }
                } else {
                    answer.setMatchedChild(YES);
                }
            }
            else {
                answer.setParentAnswers(parentAnswers.get(questionnaireQuestion.getParentQuestionNumber()));
                if (StringUtils.isBlank(questionnaireQuestion.getCondition())) {
                    if (isParentNotDisplayed(parentAnswers.get(questionnaireQuestion.getParentQuestionNumber()))) {
                        answer.setMatchedChild(NO);
                    }
                    else {
                        answer.setMatchedChild(YES);
                    }
                }
                else if (isParentNotDisplayed(parentAnswers.get(questionnaireQuestion.getParentQuestionNumber()))) {
                    answer.setMatchedChild(NO);
                    if (ConditionType.RULE_EVALUATION.getCondition().equals(questionnaireQuestion.getCondition())) {
                        // evaluate this rule, so the ruleReferenced map can be populated
                        String ruleId = questionnaireQuestion.getConditionValue();
                         if (ruleResults.containsKey(ruleId) && ruleResults.get(ruleId).booleanValue()) {
                             answer.setRuleMatched(true);
                         } else {
                             answer.setRuleMatched(false);
                         }
                    }
                }
                else if ((ConditionType.RULE_EVALUATION.getCondition().equals(questionnaireQuestion.getCondition()) 
                            && ruleResults.containsKey(questionnaireQuestion.getConditionValue()) && ruleResults.get(questionnaireQuestion.getConditionValue()).booleanValue()) 
                       || isAnyAnswerMatched(questionnaireQuestion.getCondition(), 
                               parentAnswers.get(questionnaireQuestion.getParentQuestionNumber()), questionnaireQuestion.getConditionValue())) {
                    answer.setMatchedChild(YES);
                }
                else {
                    answer.setMatchedChild(NO);
                }
            }
        }

        prepareQuestionnaireView(answerHeader);
    }
    
    protected void prepareQuestionnaireView(AnswerHeader answerHeader) {
        answerHeader.setQuestions(new ArrayList<QuestionDTO>());
        for (Answer answer : answerHeader.getAnswers()) {
            QuestionDTO currentQuestion = null;
            for (QuestionDTO question : answerHeader.getQuestions()) {
                if (question.getQuestionnaireQuestion().getQuestionNumber().equals(answer.getQuestionNumber())) {
                    currentQuestion = question;
                    break;
                }
            }
            if (currentQuestion == null) {
                currentQuestion = new QuestionDTO(answer.getQuestionnaireQuestion());
                answerHeader.getQuestions().add(currentQuestion);
                currentQuestion.setChildMatched(StringUtils.equals(answer.getMatchedChild(), YES));
                currentQuestion.setRuleMatched(answer.isRuleMatched());
                currentQuestion.setParentAnswers(answer.getParentAnswers());
            }
            currentQuestion.getAnswers().add(answer);
        }
    }
    
    /*
     * check if all the required answers are entered.
     */
    public boolean isQuestionnaireAnswerComplete(List<Answer> answers) {
        final Map<Long, Boolean> questionIdsAnswered = new HashMap<>();
        boolean isComplete = true;
        for (Answer answer : answers) {
            if (!Long.valueOf(Constants.QUESTION_RESPONSE_TYPE_MULTIPLE_CHOICE).equals(answer.getQuestion().getQuestionTypeId())) {
                if (YES.equals(answer.getMatchedChild()) && StringUtils.isBlank(answer.getAnswer()) && answer.getAnswerNumber() == 1) {
                    isComplete = false;
                }
            } else if (YES.equals(answer.getMatchedChild())) {
                final Boolean questionHasAnswer = questionIdsAnswered.get(answer.getQuestionId());
                if(questionHasAnswer == null || !questionHasAnswer.booleanValue()) {
                    questionIdsAnswered.put(answer.getQuestionId(), answer.getAnswer() != null);
                }
           }
        }
        for(boolean answered : questionIdsAnswered.values()) {
            isComplete &= answered;
        }
        return isComplete;
    }
   
    /**
     * 
     * Checks to see that at least one answer was matched by the rule and is visible.
     * @param answers
     * @return
     */
    public boolean hasVisibleQuestion(List<Answer> answers) {

        boolean isVisible = false;
        for (Answer answer : answers) {
            if (StringUtils.equals(YES, answer.getMatchedChild())) {
                isVisible = true;
                break;
            }
        }
        return isVisible;
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
        else if (Integer.parseInt(condition) == 11 || Integer.parseInt(condition) == 12) {
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
                "11"), AFTER_DATE("12"), RULE_EVALUATION("13");

        String condition;

        ConditionType(String condition) {
            this.condition = condition;
        }

        public String getCondition() {
            return condition;
        }


    }
    
    public List<AnswerHeader> getPrintAnswerHeadersForProtocol(ModuleQuestionnaireBean moduleQuestionnaireBean, String protocolNumber, QuestionnaireHelperBase questionnaireHelper) {

        boolean isAmendmentOrRenewal = protocolNumber.contains("A") || protocolNumber.contains("R");
        String originalProtocolNumber = protocolNumber;
        questionnaireHelper.populatePrintAnswers();        
        List<AnswerHeader> printAnswerHeaders = questionnaireHelper.getPrintAnswerHeaders();
        if (isAmendmentOrRenewal) {
            originalProtocolNumber = protocolNumber.substring(0, 10);
            List<AnswerHeader> headers = new ArrayList<AnswerHeader>();
            for (AnswerHeader printAnswerHeader : printAnswerHeaders) {
                if (!(CoeusSubModule.PROTOCOL_SUBMISSION.equals(printAnswerHeader.getModuleSubItemCode()) && printAnswerHeader.getModuleItemKey().equals(originalProtocolNumber))
                        && printAnswerHeader.getModuleItemKey().equals(protocolNumber)) {
                    headers.add(printAnswerHeader);
                }
            }
            return headers;
        }
        else {
            return printAnswerHeaders;
        }
    }

    
    private Map<String, Boolean> runApplicableRules(List<String> ruleIds, ModuleQuestionnaireBean moduleQuestionnaireBean) {
        KrmsRulesContext rulesContext = moduleQuestionnaireBean.getKrmsRulesContextFromBean();
        Map<String, Boolean> ruleResults = new HashMap<String, Boolean>();
        if (rulesContext != null) {
            ruleResults.putAll(getKrmsRulesExecutionService().runApplicableRules(ruleIds, rulesContext, QUESTIONNAIRE_AGENDA_TYPE_ID));
        }
        
        // use session to cache the evaluation results for now
        globalVariableService.getUserSession().addObject(moduleQuestionnaireBean.getSessionContextKey() + "-rulereferenced", ruleResults);
        
        return ruleResults;
    }
    
    public ModuleQuestionnaireBean getModuleSpecificBean(AnswerHeader answerHeader) {
        return getModuleSpecificBean(answerHeader.getModuleItemCode(), answerHeader.getModuleItemKey(), answerHeader.getModuleSubItemCode(), answerHeader.getModuleSubItemKey(), true);
    }
    
    public ModuleQuestionnaireBean getModuleSpecificBean(String moduleItemCode, String moduleItemKey, String moduleSubItemCode, String moduleSubItemKey, boolean finalDoc) {
        if (CoeusModule.COI_DISCLOSURE_MODULE_CODE.equals(moduleItemCode)) {
            return new DisclosureModuleQuestionnaireBean(moduleItemCode, moduleItemKey, moduleSubItemCode, moduleSubItemKey, finalDoc);
        } else if (CoeusModule.IACUC_PROTOCOL_MODULE_CODE.equals(moduleItemCode)) {
            return new IacucProtocolModuleQuestionnaireBean(moduleItemCode, moduleItemKey, moduleSubItemCode, moduleSubItemKey, finalDoc);
        } else if (CoeusModule.IRB_MODULE_CODE.equals(moduleItemCode)) {
            return new ProtocolModuleQuestionnaireBean(moduleItemCode, moduleItemKey, moduleSubItemCode, moduleSubItemKey, finalDoc);
        } else if (CoeusModule.PROPOSAL_DEVELOPMENT_MODULE_CODE.equals(moduleItemCode)) {
            if (isProposalPersonSubModule(moduleSubItemCode)) {
                return new ProposalPersonModuleQuestionnaireBean(moduleItemCode, moduleItemKey, moduleSubItemCode, moduleSubItemKey, finalDoc);
            } else if (CoeusSubModule.PROPOSAL_S2S_SUBMODULE.equals(moduleSubItemCode)) {
                return new ProposalDevelopmentS2sModuleQuestionnaireBean(moduleItemCode, moduleItemKey, moduleSubItemCode, moduleSubItemKey, finalDoc);
            } else {
                return new ProposalDevelopmentModuleQuestionnaireBean(moduleItemCode, moduleItemKey, moduleSubItemCode, moduleSubItemKey, finalDoc);
            }
        } else {
            throw new IllegalArgumentException("Unrecognized moduleItemCode");
        }
    }

    protected boolean isProposalPersonSubModule(String moduleSubItemCode) {
        return StringUtils.equals(moduleSubItemCode,CoeusSubModule.PROPOSAL_PERSON_CERTIFICATION) ||
                StringUtils.equals(moduleSubItemCode, getParameterService().getParameterValueAsString(Constants.KC_GENERIC_PARAMETER_NAMESPACE,
                        Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, ProposalPersonModuleQuestionnaireBean.MODULE_SUB_ITEM_CODE_COI_CERTIFICATION)) ||
                StringUtils.equals(moduleSubItemCode, getParameterService().getParameterValueAsString(Constants.KC_GENERIC_PARAMETER_NAMESPACE,
                        Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, ProposalPersonModuleQuestionnaireBean.MODULE_SUB_ITEM_CODE_KP_CERTIFICATION)) ||
                StringUtils.equals(moduleSubItemCode, getParameterService().getParameterValueAsString(Constants.KC_GENERIC_PARAMETER_NAMESPACE,
                        Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, ProposalPersonModuleQuestionnaireBean.MODULE_SUB_ITEM_CODE_PI_CERTIFICATION));
    }

    protected QuestionnaireService getQuestionnaireService() {
        return questionnaireService;
    }

    public void setQuestionnaireService(QuestionnaireService questionnaireService) {
        this.questionnaireService = questionnaireService;
    }

    public List<AnswerHeader> getNewVersionOfQuestionnaireAnswer(ModuleQuestionnaireBean moduleQuestionnaireBean) {
        List<AnswerHeader> newAnswerHeaders = getQuestionnaireAnswer(moduleQuestionnaireBean);
        for (AnswerHeader answerHeader : newAnswerHeaders) {
            answerHeader.setId(null);
            for(Answer answer : answerHeader.getAnswers()) {
                answer.setId(null);
            }
        }
        return newAnswerHeaders;
    }

    public KrmsRulesExecutionService getKrmsRulesExecutionService() {
        return krmsRulesExecutionService;
    }

    public void setKrmsRulesExecutionService(KrmsRulesExecutionService krmsRulesExecutionService) {
        this.krmsRulesExecutionService = krmsRulesExecutionService;
    }

    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }

    public ParameterService getParameterService() {
        return this.parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
}
