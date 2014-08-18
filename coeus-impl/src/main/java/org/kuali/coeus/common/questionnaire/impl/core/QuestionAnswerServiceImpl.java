package org.kuali.coeus.common.questionnaire.impl.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.questionnaire.api.core.QuestionAnswerService;
import org.kuali.coeus.common.questionnaire.api.question.QuestionContract;
import org.kuali.coeus.common.questionnaire.api.core.QuestionnaireContract;
import org.kuali.coeus.common.framework.custom.arg.ArgValueLookup;
import org.kuali.coeus.common.questionnaire.framework.core.Questionnaire;
import org.kuali.coeus.common.questionnaire.framework.answer.Answer;
import org.kuali.coeus.common.questionnaire.framework.question.Question;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("questionAnswerService")
public class QuestionAnswerServiceImpl implements QuestionAnswerService {

    private static final Log LOG = LogFactory.getLog(QuestionAnswerServiceImpl.class);
    private static final String SUPPORTED_LOOKUP_CLASS = ArgValueLookup.class.getName();

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Override
    public String getAnswerDescription(Long answerId) {
        if (answerId == null) {
            throw new IllegalArgumentException("answerId is null");
        }

        final Answer answer = businessObjectService.findByPrimaryKey(Answer.class,
                Collections.singletonMap("id", answerId));
        final String lookupClass = answer.getQuestion().getLookupClass();

        if (!SUPPORTED_LOOKUP_CLASS.equals(lookupClass)) {
            throw new UnsupportedOperationException("Answer description retrieval not supported.  Answer id: answerId");
        }

        final Map<String, Object> criteria = new HashMap<String, Object>();
        criteria.put("argumentName", answer.getQuestion().getLookupReturn());
        criteria.put("value", answer.getAnswer());
        final Collection<ArgValueLookup> avls = businessObjectService.findMatching(ArgValueLookup.class, criteria);
        if (avls != null && avls.size() > 1) {
            LOG.warn("multiple arg value lookup results exist for argumentName: "
                    + answer.getQuestion().getLookupReturn() + " and value: " + answer.getAnswer());
        }
        return avls != null && !avls.isEmpty() ? avls.iterator().next().getDescription() : null;
    }

    @Override
    public boolean isAnswerDescriptionRetrievalSupported(Long answerId) {
        if (answerId == null) {
            throw new IllegalArgumentException("answerId is null");
        }

        final Map<String, Object> criteria = new HashMap<String, Object>();
        criteria.put("id", answerId);
        criteria.put("question.lookupClass", SUPPORTED_LOOKUP_CLASS);

        return businessObjectService.countMatching(Answer.class, criteria) > 0;
    }

    @Override
    public QuestionnaireContract findQuestionnaireById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }

        return businessObjectService.findByPrimaryKey(Questionnaire.class, Collections.singletonMap("id", id));
    }

    @Override
    public QuestionContract findQuestionById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }

        return businessObjectService.findByPrimaryKey(Question.class, Collections.singletonMap("id", id));
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
}
