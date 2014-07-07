package org.kuali.coeus.propdev.impl.questionnaire;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.questionnaire.api.answer.AnswerContract;
import org.kuali.coeus.common.questionnaire.api.answer.AnswerHeaderContract;
import org.kuali.coeus.propdev.api.questionnaire.PropDevQuestionAnswerService;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.s2s.question.ProposalDevelopmentS2sQuestionnaireService;
import org.kuali.coeus.common.questionnaire.framework.core.Questionnaire;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireQuestion;
import org.kuali.coeus.common.questionnaire.framework.answer.Answer;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean;
import org.kuali.coeus.common.questionnaire.framework.answer.QuestionnaireAnswerService;
import org.kuali.rice.krad.data.DataObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("propDevQuestionAnswerService")
public class PropDevQuestionAnswerServiceImpl implements PropDevQuestionAnswerService {

    @Autowired
    @Qualifier("proposalDevelopmentS2sQuestionnaireService")
    private ProposalDevelopmentS2sQuestionnaireService proposalDevelopmentS2sQuestionnaireService;

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    @Autowired
    @Qualifier("questionnaireAnswerService")
    private QuestionnaireAnswerService questionnaireAnswerService;

    @Override
    public List<? extends AnswerContract> getQuestionnaireAnswers(String proposalNumber, String namespace, String formName) {
        if (StringUtils.isBlank(proposalNumber)) {
            throw new IllegalArgumentException("proposalNumber is blank");
        }

        if (StringUtils.isBlank(namespace)) {
            throw new IllegalArgumentException("namespace is blank");
        }

        if (StringUtils.isBlank(formName)) {
            throw new IllegalArgumentException("formName is blank");
        }

        final DevelopmentProposal developmentProposal = dataObjectService.find(DevelopmentProposal.class, proposalNumber);
        if (developmentProposal != null) {
            final List<AnswerHeader> answerHeaders = getProposalDevelopmentS2sQuestionnaireService().getProposalAnswerHeaderForForm(
                    developmentProposal, namespace, formName);
            final List<Answer> questionnaireAnswers = new ArrayList<Answer>();
            for (AnswerHeader answerHeader : answerHeaders) {
                final Questionnaire questionnaire = answerHeader.getQuestionnaire();
                final List<QuestionnaireQuestion> questionnaireQuestions = questionnaire.getQuestionnaireQuestions();
                for (QuestionnaireQuestion questionnaireQuestion : questionnaireQuestions) {
                    final Answer questionAnswer = getAnswer(questionnaireQuestion, answerHeader);
                    if (questionAnswer != null) {
                        questionnaireAnswers.add(questionAnswer);
                    }
                }
            }
            return ListUtils.emptyIfNull(questionnaireAnswers);
        }
        return Collections.emptyList();
    }

    @Override
    public List<? extends AnswerHeaderContract> getQuestionnaireAnswerHeaders(String proposalNumber, String namespace, String formName) {
        if (StringUtils.isBlank(proposalNumber)) {
            throw new IllegalArgumentException("proposalNumber is blank");
        }

        if (StringUtils.isBlank(namespace)) {
            throw new IllegalArgumentException("namespace is blank");
        }

        if (StringUtils.isBlank(formName)) {
            throw new IllegalArgumentException("formName is blank");
        }

        final DevelopmentProposal developmentProposal = dataObjectService.find(DevelopmentProposal.class, proposalNumber);
        return ListUtils.emptyIfNull(proposalDevelopmentS2sQuestionnaireService.getProposalAnswerHeaderForForm(developmentProposal, namespace, formName));
    }

    @Override
    public List<? extends AnswerHeaderContract> getQuestionnaireAnswerHeaders(String proposalNumber) {
        if (StringUtils.isBlank(proposalNumber)) {
            throw new IllegalArgumentException("proposalNumber is blank");
        }

        final DevelopmentProposal developmentProposal = dataObjectService.find(DevelopmentProposal.class, proposalNumber);
        final ModuleQuestionnaireBean moduleQuestionnaireBean = new ProposalDevelopmentModuleQuestionnaireBean(developmentProposal);
        return ListUtils.emptyIfNull(questionnaireAnswerService.getQuestionnaireAnswer(moduleQuestionnaireBean));
    }

    protected Answer getAnswer(QuestionnaireQuestion questionnaireQuestion, AnswerHeader answerHeader) {
        final List<Answer> answers = answerHeader.getAnswers();
        for (Answer answer : answers) {
            if (answer.getQuestionnaireQuestionsId().equals(questionnaireQuestion.getId())) {
                return answer;
            }
        }
        return null;
    }

    public ProposalDevelopmentS2sQuestionnaireService getProposalDevelopmentS2sQuestionnaireService() {
        return proposalDevelopmentS2sQuestionnaireService;
    }

    public void setProposalDevelopmentS2sQuestionnaireService(ProposalDevelopmentS2sQuestionnaireService proposalDevelopmentS2sQuestionnaireService) {
        this.proposalDevelopmentS2sQuestionnaireService = proposalDevelopmentS2sQuestionnaireService;
    }

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }

    public QuestionnaireAnswerService getQuestionnaireAnswerService() {
        return questionnaireAnswerService;
    }

    public void setQuestionnaireAnswerService(QuestionnaireAnswerService questionnaireAnswerService) {
        this.questionnaireAnswerService = questionnaireAnswerService;
    }
}
