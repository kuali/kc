package org.kuali.coeus.s2sgen.impl.generate.support;

import org.kuali.coeus.common.api.unit.UnitContract;
import org.kuali.coeus.common.questionnaire.api.answer.AnswerContract;
import org.kuali.coeus.common.questionnaire.api.answer.AnswerHeaderContract;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.propdev.api.questionnaire.PropDevQuestionAnswerService;

import org.kuali.coeus.s2sgen.impl.budget.S2SCommonBudgetService;
import org.kuali.coeus.s2sgen.impl.generate.S2SBaseFormGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CommonSF424BaseGenerator extends S2SBaseFormGenerator  {

    public static final Long PROPOSAL_YNQ_QUESTION_129 = 129L;
    public static final Long PROPOSAL_YNQ_QUESTION_130 = 130L;
    public static final Long PROPOSAL_YNQ_QUESTION_131 = 131L;
    private static final String YNQ_NOT_REVIEWED = "X";
    protected static final String YNQ_REVIEW_DATE = "reviewDate";
    protected static final String YNQ_STATE_REVIEW_DATA = "stateReviewData";
    protected static final String YNQ_STATE_NOT_SELECTED = "Not Selected";
    protected static final String YNQ_STATE_NOT_COVERED = "Not Covered";

    private static final int DIVISION_NAME_MAX_LENGTH = 30;

    protected static final String YNQ_ANSWER = "answer";

    @Autowired
    @Qualifier("propDevQuestionAnswerService")
    private PropDevQuestionAnswerService propDevQuestionAnswerService;

    @Autowired
    @Qualifier("s2SCommonBudgetService")
    protected S2SCommonBudgetService s2SCommonBudgetService;

    /**
     * This method returns a map containing the answers related to EOState REview for a given proposal
     *
     * @param pdDoc Proposal Development Document.
     * @return Map<String, String> map containing the answers related to EOState Review for a given proposal.
     */
    public Map<String, String> getEOStateReview(ProposalDevelopmentDocumentContract pdDoc) {
        Map<String, String> stateReview = new HashMap<String, String>();
        List<? extends AnswerHeaderContract> answerHeaders = propDevQuestionAnswerService.getQuestionnaireAnswerHeaders(pdDoc.getDevelopmentProposal().getProposalNumber());
        for (AnswerContract answers : answerHeaders.get(0).getAnswers()) {
            if (answers.getQuestionId() != null
                    && answers.getQuestionId().equals(PROPOSAL_YNQ_QUESTION_129)) {
                if (stateReview.get(YNQ_ANSWER) == null) {
                    stateReview.put(YNQ_ANSWER, answers.getAnswer());
                }
            }
            if (answers.getQuestionId() != null
                    && answers.getQuestionId().equals(PROPOSAL_YNQ_QUESTION_130)) {
                if (stateReview.get(YNQ_REVIEW_DATE) == null) {
                    stateReview.put(YNQ_REVIEW_DATE, answers.getAnswer());
                }
            }
            if (answers.getQuestionId() != null
                    && answers.getQuestionId().equals(PROPOSAL_YNQ_QUESTION_131)) {
                if (stateReview.get(YNQ_STATE_REVIEW_DATA) == null) {
                    stateReview.put(YNQ_STATE_REVIEW_DATA, answers.getAnswer());
                }
            }
        }
        // If question is not answered or question is inactive
        if (stateReview.size() == 0) {
            stateReview.put(YNQ_ANSWER, YNQ_NOT_REVIEWED);
            stateReview.put(YNQ_REVIEW_DATE, null);
        }
        return stateReview;
    }

    /**
     * This method is to get division name using the OwnedByUnit and traversing through the parent units till the top level
     *
     * @param pdDoc Proposal development document.
     * @return divisionName based on the OwnedByUnit.
     */
    public String getDivisionName(ProposalDevelopmentDocumentContract pdDoc) {
        String divisionName = null;
        if (pdDoc != null && pdDoc.getDevelopmentProposal().getOwnedByUnit() != null) {
            UnitContract ownedByUnit = pdDoc.getDevelopmentProposal().getOwnedByUnit();
            // traverse through the parent units till the top level unit
            while (ownedByUnit.getParentUnit() != null) {
                ownedByUnit = ownedByUnit.getParentUnit();
            }
            divisionName = ownedByUnit.getUnitName();
            if (divisionName.length() > DIVISION_NAME_MAX_LENGTH) {
                divisionName = divisionName.substring(0, DIVISION_NAME_MAX_LENGTH);
            }
        }
        return divisionName;
    }

    public PropDevQuestionAnswerService getPropDevQuestionAnswerService() {
        return propDevQuestionAnswerService;
    }

    public void setPropDevQuestionAnswerService(PropDevQuestionAnswerService propDevQuestionAnswerService) {
        this.propDevQuestionAnswerService = propDevQuestionAnswerService;
    }

    public S2SCommonBudgetService getS2SCommonBudgetService() {
        return s2SCommonBudgetService;
    }

    public void setS2SCommonBudgetService(S2SCommonBudgetService s2SCommonBudgetService) {
        this.s2SCommonBudgetService = s2SCommonBudgetService;
    }
}
