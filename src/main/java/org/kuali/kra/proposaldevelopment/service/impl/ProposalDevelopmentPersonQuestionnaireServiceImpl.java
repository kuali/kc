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
package org.kuali.kra.proposaldevelopment.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.questionnaire.ProposalPersonModuleQuestionnaireBean;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentPersonQuestionnaireService;
import org.kuali.kra.questionnaire.Questionnaire;
import org.kuali.kra.questionnaire.QuestionnaireQuestion;
import org.kuali.kra.questionnaire.answer.Answer;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.answer.QuestionnaireAnswerService;
import org.kuali.rice.kns.service.BusinessObjectService;


/**
 * 
 * This class...
 */
public class ProposalDevelopmentPersonQuestionnaireServiceImpl implements ProposalDevelopmentPersonQuestionnaireService {
    

    private static final String PROPOSAL_PERSON_QUESTIONNAIRE_NAME = "Proposal Person Certification";
    
    private BusinessObjectService businessObjectService;
    private QuestionnaireAnswerService questionnaireAnswerService;
    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalDevelopmentPersonQuestionnaireServiceImpl.class);
    
    /**
     * 
     * @see org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentPersonQuestionnaireService#getBaseQuestionnaire()
     */
    public Questionnaire getBaseQuestionnaire() {
        Map params = new HashMap();
        params.put("NAME", PROPOSAL_PERSON_QUESTIONNAIRE_NAME);
        Questionnaire questionnaire = (Questionnaire) this.businessObjectService.findByPrimaryKey(Questionnaire.class, params);
        return questionnaire;
    }
    
    /**
     * 
     * @see org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentPersonQuestionnaireService#getNewAnswerHeader(org.kuali.kra.proposaldevelopment.questionnaire.ProposalPersonModuleQuestionnaireBean)
     */
    public AnswerHeader getNewAnswerHeader(ProposalPersonModuleQuestionnaireBean proposalPersonModuleQuestionnaireBean) {
        Questionnaire questionnaire = getBaseQuestionnaire();
        
        AnswerHeader header = new AnswerHeader();
        header.setQuestionnaire(questionnaire);
        header.setQuestionnaireRefIdFk(questionnaire.getQuestionnaireRefId());
        header.setActiveQuestionnaire(true);
        header.setModuleItemCode(proposalPersonModuleQuestionnaireBean.getModuleItemCode());
        header.setModuleItemKey(proposalPersonModuleQuestionnaireBean.getModuleItemKey());
        header.setModuleSubItemCode(proposalPersonModuleQuestionnaireBean.getModuleSubItemCode());
        header.setModuleSubItemKey(proposalPersonModuleQuestionnaireBean.getModuleSubItemKey());
        
        List<Answer> answers = new ArrayList<Answer>();
        for (QuestionnaireQuestion q : questionnaire.getQuestionnaireQuestions()) {
            Answer answer = new Answer();
            answer.setAnswerHeader(header);
            answer.setQuestion(q.getQuestion());
            answer.setQuestionnaireQuestion(q);
            answer.setQuestionnaireQuestionsIdFk(q.getQuestionnaireQuestionsId());
            answer.setQuestionNumber(q.getQuestion().getQuestionId());
            answer.setQuestionRefIdFk(q.getQuestion().getQuestionRefId());
            answer.setAnswerNumber(q.getQuestionNumber());
            answers.add(answer);
        }
        header.setAnswers(answers);
        this.businessObjectService.save(header);
        return header;
    }
    
    /**
     * 
     * @see org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentPersonQuestionnaireService#getNewAnswerHeaders(org.kuali.kra.proposaldevelopment.questionnaire.ProposalPersonModuleQuestionnaireBean)
     */
    public List<AnswerHeader> getNewAnswerHeaders(ProposalPersonModuleQuestionnaireBean proposalPersonModuleQuestionnaireBean) {
        List<AnswerHeader> headers = new ArrayList<AnswerHeader>();
        headers.add(getNewAnswerHeader(proposalPersonModuleQuestionnaireBean));
        return headers;
    }
    
    /**
     * 
     * @see org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentPersonQuestionnaireService#getAnswerHeaders(org.kuali.kra.proposaldevelopment.bo.ProposalPerson)
     */
    public List<AnswerHeader> getAnswerHeaders(ProposalPerson proposalPerson) {
        ProposalPersonModuleQuestionnaireBean bean = new ProposalPersonModuleQuestionnaireBean(proposalPerson.getDevelopmentProposal(), proposalPerson);
        //`.pull from the DB
        List<AnswerHeader> headers = questionnaireAnswerService.getQuestionnaireAnswer(bean);
        if (headers == null || headers.isEmpty()) {
            headers = this.getNewAnswerHeaders(bean);
        }
        return headers;
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    public void setQuestionnaireAnswerService(QuestionnaireAnswerService questionnaireAnswerService) {
        this.questionnaireAnswerService = questionnaireAnswerService;
    }
}