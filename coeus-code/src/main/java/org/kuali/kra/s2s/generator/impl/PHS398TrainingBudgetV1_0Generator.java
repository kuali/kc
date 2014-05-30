/*
 * @(#)PHS398TrainingBudgStream.java
 *
 * Copyright (c) Massachusetts Institute of Technology
 * 77 Massachusetts Avenue, Cambridge, MA 02139-4307
 * All rights reserved.
 */

package org.kuali.kra.s2s.generator.impl;

import gov.grants.apply.forms.phs398TrainingBudgetV10.PHS398TrainingBudgetDocument;
import gov.grants.apply.forms.phs398TrainingBudgetV10.PHS398TrainingBudgetDocument.PHS398TrainingBudget;
import gov.grants.apply.forms.phs398TrainingBudgetV10.PHS398TrainingBudgetDocument.PHS398TrainingBudget.BudgetType;
import gov.grants.apply.forms.phs398TrainingBudgetV10.PHS398TrainingBudgetYearDataType;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.budget.api.rate.TrainingStipendRateContract;
import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.propdev.api.s2s.S2SConfigurationService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.s2s.question.ProposalDevelopmentS2sQuestionnaireService;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.location.ProposalSite;
import org.kuali.coeus.propdev.impl.budget.ProposalBudgetService;
import org.kuali.kra.questionnaire.Questionnaire;
import org.kuali.kra.questionnaire.QuestionnaireQuestion;
import org.kuali.kra.questionnaire.answer.Answer;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.question.Question;
import org.kuali.kra.s2s.ConfigurationConstants;
import org.kuali.kra.s2s.S2SException;
import org.kuali.coeus.budget.api.rate.TrainingStipendRateService;
import org.kuali.coeus.propdev.api.attachment.NarrativeContract;
import org.kuali.kra.s2s.generator.S2SBaseFormGenerator;
import org.kuali.kra.s2s.generator.bo.IndirectCostDetails;
import org.kuali.kra.s2s.generator.bo.IndirectCostInfo;
import org.kuali.kra.s2s.service.S2SBudgetCalculatorService;
import org.kuali.kra.s2s.util.AuditError;
import org.kuali.kra.s2s.util.S2SConstants;
import org.kuali.rice.kew.api.exception.WorkflowException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * This class is for generating PHS398TrainingBudgetV1_0
 */
public class PHS398TrainingBudgetV1_0Generator extends S2SBaseFormGenerator {

    private static final String UNDERGRADS = "Undergraduates";
    private static final String PREDOC = "Predoctoral";
    private static final String POSTDOC = "Postdoctoral";
    private static final String STIPEND_AMOUNT = "amount";
    private static final String BUDGET_PERIOD = "period";
    private S2SBudgetCalculatorService s2sBudgetCalculatorService;
    private ProposalBudgetService proposalBudgetService;
    private S2SConfigurationService s2SConfigurationService;
    private TrainingStipendRateService trainingStipendRateService;
    private static final int PHS_TRAINING_BUDGET_BUDGETJUSTIFICATION_130 = 130;

    private static final Integer[] PREDOC_PARENT_QUESTION_IDS_PERIOD1 = { 2, 5, 8, 11, 53, 54, 56 };
    private static final Integer[] PREDOC_PARENT_QUESTION_IDS_PERIOD2 = { 59, 60, 63, 66, 108, 109, 111 };
    private static final Integer[] PREDOC_PARENT_QUESTION_IDS_PERIOD3 = { 114, 115, 118, 121, 164, 165, 167 };
    private static final Integer[] PREDOC_PARENT_QUESTION_IDS_PERIOD4 = { 170, 171, 174, 177, 219, 220, 222 };
    private static final Integer[] PREDOC_PARENT_QUESTION_IDS_PERIOD5 = { 225, 226, 229, 232, 274, 275, 277 };

    private static final Integer[] POSTDOC_PARENT_QUESTION_IDS_PERIOD1 = { 17, 26, 35, 44 };
    private static final Integer[] POSTDOC_PARENT_QUESTION_IDS_PERIOD2 = { 72, 81, 90, 99 };
    private static final Integer[] POSTDOC_PARENT_QUESTION_IDS_PERIOD3 = { 127, 136, 146, 155 };
    private static final Integer[] POSTDOC_PARENT_QUESTION_IDS_PERIOD4 = { 183, 192, 201, 210 };
    private static final Integer[] POSTDOC_PARENT_QUESTION_IDS_PERIOD5 = { 238, 247, 256, 265 };
    private static final int FN_INDEX = 0;
    private static final int SN_INDEX = 1;
    private static final int FD_INDEX = 2;
    private static final int SD_INDEX = 3;
    private static final int ZERO = 0;

    /** Creates a new instance of PHS398TrainingBudgetV1_0Generator */
    public PHS398TrainingBudgetV1_0Generator() {
        s2sBudgetCalculatorService = KcServiceLocator.getService(S2SBudgetCalculatorService.class);
        s2SConfigurationService = KcServiceLocator.getService(S2SConfigurationService.class);
        trainingStipendRateService = KcServiceLocator.getService(TrainingStipendRateService.class);
        proposalBudgetService = KcServiceLocator.getService(ProposalBudgetService.class);
    }

    public XmlObject getFormObject(ProposalDevelopmentDocument proposalDevelopmentDocument) throws S2SException {
        PHS398TrainingBudgetDocument trainingBudgetTypeDocument = PHS398TrainingBudgetDocument.Factory.newInstance();
        trainingBudgetTypeDocument.setPHS398TrainingBudget(getPHS398TrainingBudget(proposalDevelopmentDocument));
        return trainingBudgetTypeDocument;
    }

    private PHS398TrainingBudget getPHS398TrainingBudget(ProposalDevelopmentDocument proposalDevelopmentDocument) throws S2SException{
        DevelopmentProposal developmentProposal = proposalDevelopmentDocument.getDevelopmentProposal();
        BudgetDocument<DevelopmentProposal> budgetDocument = null;
        try {
            budgetDocument = proposalBudgetService.getFinalBudgetVersion(proposalDevelopmentDocument);
        } catch (WorkflowException e) {
            throw new S2SException(e);
        }
        PHS398TrainingBudget trainingBudgetType = PHS398TrainingBudget.Factory.newInstance();
        Budget budget;
        if (budgetDocument != null) {
            budget = budgetDocument.getBudget();
            trainingBudgetType.setFormVersion(S2SConstants.FORMVERSION_1_0);
            trainingBudgetType.setBudgetType(BudgetType.PROJECT);
            setOrganizationData(trainingBudgetType, developmentProposal);
        }
        else {
            return trainingBudgetType;
        }



        int numPeople = 0;

        BigDecimal stipendAmountOtherFull = new BigDecimal("0"), stipendAmountOtherShort = new BigDecimal("0");
        BigDecimal stipendAmountF = new BigDecimal("0"), stipendAmountJ = new BigDecimal("0");
        BigDecimal stipendAmountPreSingFull = new BigDecimal("0"), stipendAmountPreDualFull = new BigDecimal("0");
        BigDecimal stipendAmountPreSingShort = new BigDecimal("0"), stipendAmountPreDualShort = new BigDecimal("0");
        BigDecimal stipendAmount0 = new BigDecimal("0"), stipendAmount1 = new BigDecimal("0"), stipendAmount2 = new BigDecimal("0"), stipendAmount3 = new BigDecimal(
            "0"), stipendAmount4 = new BigDecimal("0");
        BigDecimal stipendAmount5 = new BigDecimal("0"), stipendAmount6 = new BigDecimal("0"), stipendAmount7 = new BigDecimal("0");
        BigDecimal stipendAmountDeg0 = new BigDecimal("0"), stipendAmountDeg1 = new BigDecimal("0"), stipendAmountDeg2 = new BigDecimal(
            "0"), stipendAmountDeg3 = new BigDecimal("0"), stipendAmountDeg4 = new BigDecimal("0");
        BigDecimal stipendAmountDeg5 = new BigDecimal("0"), stipendAmountDeg6 = new BigDecimal("0"), stipendAmountDeg7 = new BigDecimal(
            "0");
        BigDecimal stipendAmountNonDeg0 = new BigDecimal("0"), stipendAmountNonDeg1 = new BigDecimal("0"), stipendAmountNonDeg2 = new BigDecimal(
            "0"), stipendAmountNonDeg3 = new BigDecimal("0"), stipendAmountNonDeg4 = new BigDecimal("0");
        BigDecimal stipendAmountNonDeg5 = new BigDecimal("0"), stipendAmountNonDeg6 = new BigDecimal("0"), stipendAmountNonDeg7 = new BigDecimal(
            "0");

        /***** cumulative stipends **/
        BigDecimal cumUndergradStipends = new BigDecimal("0"), cumPreDocSingleStipends = new BigDecimal("0"), cumPreDocDualStipends = new BigDecimal(
            "0"), cumPreDocTotalStipends = new BigDecimal("0");
        BigDecimal cumPostDocNonDegStipends = new BigDecimal("0"), cumPostDocDegStipends = new BigDecimal("0"), cumPostDocTotalStipends = new BigDecimal(
            "0");
        BigDecimal cumOtherStipends = new BigDecimal("0");

        /***** cumulative tuition **/
        BigDecimal cumUndergradTuition = new BigDecimal("0"), cumPreDocSingleTuition = new BigDecimal("0"), cumPreDocDualTuition = new BigDecimal(
            "0"), cumPreDocTotalTuition = new BigDecimal("0");
        BigDecimal cumPostDocNonDegTuition = new BigDecimal("0"), cumPostDocDegTuition = new BigDecimal("0"), cumPostDocTotalTuition = new BigDecimal(
            "0");
        BigDecimal cumOtherTuition = new BigDecimal("0");

        /********** cumulative costs **/
        BigDecimal cumTrainingCosts = new BigDecimal("0"), cumTravelCosts = new BigDecimal("0"), cumConsCosts = new BigDecimal("0");
        BigDecimal cumResearchTotalDirectCosts = new BigDecimal("0"), cumTotalOtherDirectCosts = new BigDecimal("0"), cumTotalDirectCosts = new BigDecimal(
            "0");
        BigDecimal cumTotalIndCosts1 = new BigDecimal("0"), cumTotalIndCosts2 = new BigDecimal("0"), cumTotalIndCosts = new BigDecimal(
            "0");
        BigDecimal cumTotalDirectAndIndCosts = new BigDecimal("0");

        BigDecimal researchDirectCosts = new BigDecimal("0");
        BigDecimal totalOtherDirectCostsRequested = new BigDecimal("0");
        /********************************
         * get budget periods
         *********************************/
        List<BudgetPeriod> budgetPeriods = budget.getBudgetPeriods();
        for (BudgetPeriod budgetPeriod : budgetPeriods) {
            PHS398TrainingBudgetYearDataType phs398TrainingBudgetYearDataType = trainingBudgetType.addNewBudgetYear();
            ScaleTwoDecimal trainingTraveCost = getBudgetPeriodCost(budgetPeriod, ConfigurationConstants.TRAINEE_TRAVEL_COST_ELEMENTS);
            phs398TrainingBudgetYearDataType.setTraineeTravelRequested(trainingTraveCost.bigDecimalValue());
            ScaleTwoDecimal trainingCost = getBudgetPeriodCost(budgetPeriod, ConfigurationConstants.TRAINING_REL_COST_ELEMENTS);
            phs398TrainingBudgetYearDataType.setTrainingRelatedExpensesRequested(trainingCost.bigDecimalValue());
            ScaleTwoDecimal consTrainingCost = getBudgetPeriodCost(budgetPeriod, ConfigurationConstants.SUBCONTRACT_COST_ELEMENTS);
            phs398TrainingBudgetYearDataType.setConsortiumTrainingCostsRequested(consTrainingCost.bigDecimalValue());

            phs398TrainingBudgetYearDataType.setPostdocNonDegreeTuitionAndFeesRequested(getBudgetPeriodCost(budgetPeriod,
                    ConfigurationConstants.TUITION_POSTDOC_NONDEG_COST_ELEMENTS).bigDecimalValue());
            phs398TrainingBudgetYearDataType.setPostdocDegreeTuitionAndFeesRequested(getBudgetPeriodCost(budgetPeriod,
                    ConfigurationConstants.TUITION_POSTDOC_DEG_COST_ELEMENTS).bigDecimalValue());
            phs398TrainingBudgetYearDataType.setUndergraduateTuitionAndFeesRequested(getBudgetPeriodCost(budgetPeriod,
                    ConfigurationConstants.TUITION_UNDERGRAD_COST_ELEMENTS).bigDecimalValue());
            phs398TrainingBudgetYearDataType.setPredocDualDegreeTuitionAndFeesRequested(getBudgetPeriodCost(budgetPeriod,
                    ConfigurationConstants.TUITION_PREDOC_DUAL_DEG_COST_ELEMENTS).bigDecimalValue());
            phs398TrainingBudgetYearDataType.setPredocSingleDegreeTuitionAndFeesRequested(getBudgetPeriodCost(budgetPeriod,
                    ConfigurationConstants.TUITION_PREDOC_SINGLE_DEG_COST_ELEMENTS).bigDecimalValue());
            phs398TrainingBudgetYearDataType.setOtherTuitionAndFeesRequested(getBudgetPeriodCost(budgetPeriod,
                    ConfigurationConstants.TUITION_OTHER_COST_ELEMENTS).bigDecimalValue());

            phs398TrainingBudgetYearDataType.setPeriodEndDate(DateUtils.toCalendar(budgetPeriod.getEndDate()));
            phs398TrainingBudgetYearDataType.setPeriodStartDate(DateUtils.toCalendar(budgetPeriod.getStartDate()));


            /******************************
             * add to cumulative amounts for tuition and costs
             ******************************/
            cumUndergradTuition = cumUndergradTuition.add(phs398TrainingBudgetYearDataType
                    .getUndergraduateTuitionAndFeesRequested());
            cumPreDocSingleTuition = cumPreDocSingleTuition.add(phs398TrainingBudgetYearDataType
                    .getPredocSingleDegreeTuitionAndFeesRequested());
            cumPreDocDualTuition = cumPreDocDualTuition.add(phs398TrainingBudgetYearDataType
                    .getPredocDualDegreeTuitionAndFeesRequested());

            cumPostDocNonDegTuition = cumPostDocNonDegTuition.add(phs398TrainingBudgetYearDataType
                    .getPostdocNonDegreeTuitionAndFeesRequested());
            cumPostDocDegTuition = cumPostDocDegTuition.add(phs398TrainingBudgetYearDataType
                    .getPostdocDegreeTuitionAndFeesRequested());
            cumPostDocTotalTuition = cumPostDocNonDegTuition.add(cumPostDocDegTuition);
            cumOtherTuition = cumOtherTuition.add(phs398TrainingBudgetYearDataType.getOtherTuitionAndFeesRequested());
            cumTrainingCosts = cumTrainingCosts.add(phs398TrainingBudgetYearDataType.getTrainingRelatedExpensesRequested());
            cumTravelCosts = cumTravelCosts.add(phs398TrainingBudgetYearDataType.getTraineeTravelRequested());
            cumConsCosts = cumConsCosts.add(phs398TrainingBudgetYearDataType.getConsortiumTrainingCostsRequested());

            /************************
             * getting first two indirect cost type
             ************************/

            IndirectCostInfo indirectCostInfo = s2sBudgetCalculatorService.getIndirectCosts(budgetPeriod.getBudget(), budgetPeriod);
            List<IndirectCostDetails> cvIndirectCost = indirectCostInfo.getIndirectCostDetails();
            BigDecimal totIndCosts = new BigDecimal("0");
            for (int i = 0; i < cvIndirectCost.size() & i < 2; i++) {
                IndirectCostDetails indireCost = cvIndirectCost.get(i);
                totIndCosts = totIndCosts.add(indireCost.getFunds().bigDecimalValue());
                switch (i) {
                    case (0):
                        phs398TrainingBudgetYearDataType.setIndirectCostType1(indireCost.getCostType());
                        phs398TrainingBudgetYearDataType.setIndirectCostBase1(indireCost.getBase().bigDecimalValue());
                        phs398TrainingBudgetYearDataType.setIndirectCostFundsRequested1(indireCost.getFunds().bigDecimalValue());
                        phs398TrainingBudgetYearDataType.setIndirectCostRate1(indireCost.getRate().bigDecimalValue());
                        cumTotalIndCosts1 =  cumTotalIndCosts1.add(phs398TrainingBudgetYearDataType.getIndirectCostFundsRequested1());
                        break;
                    case (1):
                        phs398TrainingBudgetYearDataType.setIndirectCostType1(indireCost.getCostType());
                        phs398TrainingBudgetYearDataType.setIndirectCostBase2(indireCost.getBase().bigDecimalValue());
                        phs398TrainingBudgetYearDataType.setIndirectCostFundsRequested2(indireCost.getFunds().bigDecimalValue());
                        phs398TrainingBudgetYearDataType.setIndirectCostRate2(indireCost.getRate().bigDecimalValue());
                        cumTotalIndCosts2 =  cumTotalIndCosts2.add(phs398TrainingBudgetYearDataType.getIndirectCostFundsRequested2());
                        break;
                    default:
                        break;
                }

            }
            phs398TrainingBudgetYearDataType.setTotalIndirectCostsRequested(totIndCosts);


            int numPostDocLevel0, numPostDocLevel1, numPostDocLevel2, numPostDocLevel3, numPostDocLevel4 = 0;
            int numPostDocLevel5, numPostDocLevel6, numPostDocLevel7 = 0;

            Map<String,String> hmNonDegree = new HashMap<String,String>();
            Map<String,String> hmDegree = new HashMap<String,String>();

            hmNonDegree.put("fulllevel0", "0");
            hmNonDegree.put("fulllevel1", "0");
            hmNonDegree.put("fulllevel2", "0");
            hmNonDegree.put("fulllevel3", "0");
            hmNonDegree.put("fulllevel4", "0");
            hmNonDegree.put("fulllevel5", "0");
            hmNonDegree.put("fulllevel6", "0");
            hmNonDegree.put("fulllevel7", "0");
            hmNonDegree.put("shortlevel0", "0");
            hmNonDegree.put("shortlevel1", "0");
            hmNonDegree.put("shortlevel2", "0");
            hmNonDegree.put("shortlevel3", "0");
            hmNonDegree.put("shortlevel4", "0");
            hmNonDegree.put("shortlevel5", "0");
            hmNonDegree.put("shortlevel6", "0");
            hmNonDegree.put("shortlevel7", "0");


            /********************************************************
             * get questionnaire answers for undergrads and predocs and others
             ********************************************************/

            String answer = null;
            int preDocCountFull = 0, preDocCountShort = 0;
            int undergradFirstYearNum = 0, undergradJrNum = 0;
            BigDecimal otherShortStipends = new BigDecimal("0"), otherFullStipends = new BigDecimal("0");
            List<AnswerHeader> answerHeaders = findQuestionnaireWithAnswers(developmentProposal, budgetPeriod.getBudgetPeriod());
            if (answerHeaders != null){
                for (AnswerHeader answerHeader : answerHeaders) {
                    Questionnaire questionnaire = answerHeader.getQuestionnaire();
                    List<QuestionnaireQuestion> questionnaireQuestions = questionnaire.getQuestionnaireQuestions();
                    for (QuestionnaireQuestion questionnaireQuestion : questionnaireQuestions) {
                        Answer answerBO = getAnswer(questionnaireQuestion, answerHeader);
                        answer = answerBO.getAnswer();
                        Question question = questionnaireQuestion.getQuestion();
                        if (answer != null) {
                            int answerIntVal = 0;
                            try {
                                answerIntVal = Integer.parseInt(answer);
                            }
                            catch (NumberFormatException ex) {
                            }
                            if (isPreDocParentQuestionFromPeriodExists(questionnaireQuestion, budgetPeriod)) {
                                switch (question.getQuestionSeqIdAsInteger()) {
                                    case 72:
                                        if (answer != null)
                                            phs398TrainingBudgetYearDataType.setUndergraduateNumFullTime(answerIntVal);
                                        break;
                                    case 73:
                                        // short term undergrad
                                        if (answer != null)
                                            phs398TrainingBudgetYearDataType.setUndergraduateNumShortTerm(answerIntVal);
                                        break;
                                    case 74:
                                        // stipends first year
                                        if (answer != null)
                                            undergradFirstYearNum = undergradFirstYearNum + answerIntVal;

                                        break;
                                    case 75:
                                        // stipends junior
                                        if (answer != null)
                                            undergradJrNum = undergradJrNum + answerIntVal;

                                        break;
                                    case 77:
                                        // full time single degree predoc
                                        if (answer != null) {
                                            phs398TrainingBudgetYearDataType.setPredocSingleDegreeNumFullTime(answerIntVal);
                                            preDocCountFull = preDocCountFull+ phs398TrainingBudgetYearDataType.getPredocSingleDegreeNumFullTime();
                                        }
                                        break;
                                    case 78:
                                        // short term single degree predoc
                                        if (answer != null) {
                                            phs398TrainingBudgetYearDataType.setPredocSingleDegreeNumShortTerm(answerIntVal);
                                            preDocCountShort = preDocCountShort+ phs398TrainingBudgetYearDataType.getPredocSingleDegreeNumShortTerm();
                                        }
                                        break;
                                    case 79:
                                        // full term dual degree predoc
                                        if (answer != null) {
                                            phs398TrainingBudgetYearDataType.setPredocDualDegreeNumFullTime(answerIntVal);
                                            preDocCountFull = preDocCountFull+ phs398TrainingBudgetYearDataType.getPredocDualDegreeNumFullTime();
                                        }
                                        break;
                                    case 80:
                                        // short term dual degree predoc
                                        if (answer != null) {
                                            phs398TrainingBudgetYearDataType.setPredocDualDegreeNumShortTerm(answerIntVal);
                                            preDocCountShort = preDocCountShort+ phs398TrainingBudgetYearDataType.getPredocDualDegreeNumShortTerm();
                                        }
                                        break;
                                    case 95:
                                        // others full term
                                        if (answer != null)
                                            phs398TrainingBudgetYearDataType.setOtherNumFullTime(answerIntVal);
                                        break;
                                    case 97:
                                        // others short term
                                        if (answer != null)
                                            phs398TrainingBudgetYearDataType.setOtherNumShortTerm(answerIntVal);
                                        break;
                                    case 96:
                                        // others full term stipend
                                        if (answer != null)
                                            otherFullStipends = new BigDecimal(answer.toString());
                                        break;
                                    case 98:
                                        // others short term stipend
                                        if (answer != null)
                                            otherShortStipends = new BigDecimal(answer.toString());
                                        break;
                                }
                            }
                            if (isPostDocParentQuestionFromPeriodExists(questionnaireQuestion, budgetPeriod, FN_INDEX)) {
                                switch (question.getQuestionSeqIdAsInteger()) {
                                    case 86:
                                        // trainees at stipend level 0
                                        if (answer != null)
                                            hmNonDegree.put("fulllevel0", answer);
                                        break;
                                    case 87:
                                        // trainees at stipend level 1
                                        if (answer != null)
                                            hmNonDegree.put("fulllevel1", answer);
                                        break;
                                    case 88:
                                        // trainees at stipend level 2
                                        if (answer != null)
                                            hmNonDegree.put("fulllevel2", answer);
                                        break;
                                    case 89:
                                        // trainees at stipend level 3
                                        if (answer != null)
                                            hmNonDegree.put("fulllevel3", answer);
                                        break;
                                    case 90:
                                        // trainees at stipend level 4
                                        if (answer != null)
                                            hmNonDegree.put("fulllevel4", answer);
                                        break;
                                    case 91:
                                        // trainees at stipend level 5
                                        if (answer != null)
                                            hmNonDegree.put("fulllevel5", answer);
                                        break;
                                    case 92:
                                        // trainees at stipend level 6
                                        if (answer != null)
                                            hmNonDegree.put("fulllevel6", answer);
                                        break;
                                    case 93:
                                        // trainees at stipend level 7
                                        if (answer != null)
                                            hmNonDegree.put("fulllevel7", answer);
                                        break;
                                    default:
                                        break;
                                }
                            }
                            if (isPostDocParentQuestionFromPeriodExists(questionnaireQuestion, budgetPeriod, SN_INDEX)) {
                                switch (question.getQuestionSeqIdAsInteger()) {
                                    case 86:
                                        // trainees at stipend level 0
                                        if (answer != null)
                                            hmNonDegree.put("shortlevel0", answer);
                                        break;
                                    case 87:
                                        // trainees at stipend level 1
                                        if (answer != null)
                                            hmNonDegree.put("shortlevel1", answer);
                                        break;
                                    case 88:
                                        // trainees at stipend level 2
                                        if (answer != null)
                                            hmNonDegree.put("shortlevel2", answer);
                                        break;
                                    case 89:
                                        // trainees at stipend level 3
                                        if (answer != null)
                                            hmNonDegree.put("shortlevel3", answer);
                                        break;
                                    case 90:
                                        // trainees at stipend level 4
                                        if (answer != null)
                                            hmNonDegree.put("shortlevel4", answer);
                                        break;
                                    case 91:
                                        // trainees at stipend level 5
                                        if (answer != null)
                                            hmNonDegree.put("shortlevel5", answer);
                                        break;
                                    case 92:
                                        // trainees at stipend level 6
                                        if (answer != null)
                                            hmNonDegree.put("shortlevel6", answer);
                                        break;
                                    case 93:
                                        // trainees at stipend level 7
                                        if (answer != null)
                                            hmNonDegree.put("shortlevel7", answer);
                                        break;
                                    default:
                                        break;

                                }
                            }
                        }
                    }
                }
            }
            phs398TrainingBudgetYearDataType.setUndergraduateNumFirstYearSophomoreStipends(undergradFirstYearNum);
            phs398TrainingBudgetYearDataType.setUndergraduateNumJuniorSeniorStipends(undergradJrNum);
            phs398TrainingBudgetYearDataType.setOtherStipendsRequested(otherShortStipends.add(otherFullStipends));
            phs398TrainingBudgetYearDataType.setPredocTotalNumShortTerm(preDocCountShort);
            phs398TrainingBudgetYearDataType.setPredocTotalNumFullTime(preDocCountFull);
            cumOtherStipends = cumOtherStipends.add(phs398TrainingBudgetYearDataType.getOtherStipendsRequested());


            /***********************************************************
             * set post doc non degree full time total number
             ***********************************************************/


            int postDocNumNonDegreeFullTime = Integer.parseInt(hmNonDegree.get("fulllevel0").toString())
                    + Integer.parseInt(hmNonDegree.get("fulllevel1").toString())
                    + Integer.parseInt(hmNonDegree.get("fulllevel2").toString())
                    + Integer.parseInt(hmNonDegree.get("fulllevel3").toString())
                    + Integer.parseInt(hmNonDegree.get("fulllevel4").toString())
                    + Integer.parseInt(hmNonDegree.get("fulllevel5").toString())
                    + Integer.parseInt(hmNonDegree.get("fulllevel6").toString())
                    + Integer.parseInt(hmNonDegree.get("fulllevel7").toString());

            phs398TrainingBudgetYearDataType.setPostdocNumNonDegreeFullTime(postDocNumNonDegreeFullTime);

            /***********************************************************
             * set post doc non degree short term total number
             ***********************************************************/

            int postDocNumNonDegreeShortTerm = Integer.parseInt(hmNonDegree.get("shortlevel0").toString())
                    + Integer.parseInt(hmNonDegree.get("shortlevel1").toString())
                    + Integer.parseInt(hmNonDegree.get("shortlevel2").toString())
                    + Integer.parseInt(hmNonDegree.get("shortlevel3").toString())
                    + Integer.parseInt(hmNonDegree.get("shortlevel4").toString())
                    + Integer.parseInt(hmNonDegree.get("shortlevel5").toString())
                    + Integer.parseInt(hmNonDegree.get("shortlevel6").toString())
                    + Integer.parseInt(hmNonDegree.get("shortlevel7").toString());

            phs398TrainingBudgetYearDataType.setPostdocNumNonDegreeShortTerm(postDocNumNonDegreeShortTerm);


            /************************************************
             * set post doc non degree level numbers
             *************************************************/
            phs398TrainingBudgetYearDataType.setPostdocNumNonDegreeStipendLevel0(Integer.parseInt(hmNonDegree.get("fulllevel0")
                    .toString())
                    + Integer.parseInt(hmNonDegree.get("shortlevel0").toString()));

            phs398TrainingBudgetYearDataType.setPostdocNumNonDegreeStipendLevel1(Integer.parseInt(hmNonDegree.get("fulllevel1")
                    .toString())
                    + Integer.parseInt(hmNonDegree.get("shortlevel1").toString()));
            phs398TrainingBudgetYearDataType.setPostdocNumNonDegreeStipendLevel2(Integer.parseInt(hmNonDegree.get("fulllevel2")
                    .toString())
                    + Integer.parseInt(hmNonDegree.get("shortlevel2").toString()));
            phs398TrainingBudgetYearDataType.setPostdocNumNonDegreeStipendLevel3(Integer.parseInt(hmNonDegree.get("fulllevel3")
                    .toString())
                    + Integer.parseInt(hmNonDegree.get("shortlevel3").toString()));
            phs398TrainingBudgetYearDataType.setPostdocNumNonDegreeStipendLevel4(Integer.parseInt(hmNonDegree.get("fulllevel4")
                    .toString())
                    + Integer.parseInt(hmNonDegree.get("shortlevel4").toString()));
            phs398TrainingBudgetYearDataType.setPostdocNumNonDegreeStipendLevel5(Integer.parseInt(hmNonDegree.get("fulllevel5")
                    .toString())
                    + Integer.parseInt(hmNonDegree.get("shortlevel5").toString()));
            phs398TrainingBudgetYearDataType.setPostdocNumNonDegreeStipendLevel6(Integer.parseInt(hmNonDegree.get("fulllevel6")
                    .toString())
                    + Integer.parseInt(hmNonDegree.get("shortlevel6").toString()));
            phs398TrainingBudgetYearDataType.setPostdocNumNonDegreeStipendLevel7(Integer.parseInt(hmNonDegree.get("fulllevel7")
                    .toString())
                    + Integer.parseInt(hmNonDegree.get("shortlevel7").toString()));

            answer = null;

            hmDegree.put("fulllevel0", "0");
            hmDegree.put("fulllevel1", "0");
            hmDegree.put("fulllevel2", "0");
            hmDegree.put("fulllevel3", "0");
            hmDegree.put("fulllevel4", "0");
            hmDegree.put("fulllevel5", "0");
            hmDegree.put("fulllevel6", "0");
            hmDegree.put("fulllevel7", "0");
            hmDegree.put("shortlevel0", "0");
            hmDegree.put("shortlevel1", "0");
            hmDegree.put("shortlevel2", "0");
            hmDegree.put("shortlevel3", "0");
            hmDegree.put("shortlevel4", "0");
            hmDegree.put("shortlevel5", "0");
            hmDegree.put("shortlevel6", "0");
            hmDegree.put("shortlevel7", "0");
            if (answerHeaders != null){
                for (AnswerHeader answerHeader : answerHeaders) {
                    Questionnaire questionnaire = answerHeader.getQuestionnaire();
                    List<QuestionnaireQuestion> questionnaireQuestions = questionnaire.getQuestionnaireQuestions();
                    for (QuestionnaireQuestion questionnaireQuestion : questionnaireQuestions) {
                        Answer answerBO = getAnswer(questionnaireQuestion, answerHeader);
                        answer = answerBO.getAnswer();
                        Question question = questionnaireQuestion.getQuestion();
                        if (answer != null) {
                            int answerIntVal = 0;
                            try {answerIntVal = Integer.parseInt(answer);}catch (NumberFormatException ex) {}
                            if (isPostDocParentQuestionFromPeriodExists(questionnaireQuestion, budgetPeriod, FD_INDEX)) {
                                switch (question.getQuestionSeqIdAsInteger()) {
                                    case 86:
                                        // trainees at stipend level 0
                                        if (answer != null)
                                            hmDegree.put("fulllevel0", answer);
                                        break;
                                    case 87:
                                        // trainees at stipend level 1
                                        if (answer != null)
                                            hmDegree.put("fulllevel1", answer);
                                        break;
                                    case 88:
                                        // trainees at stipend level 2
                                        if (answer != null)
                                            hmDegree.put("fulllevel2", answer);
                                        break;
                                    case 89:
                                        // trainees at stipend level 3
                                        if (answer != null)
                                            hmDegree.put("fulllevel3", answer);
                                        break;
                                    case 90:
                                        // trainees at stipend level 4
                                        if (answer != null)
                                            hmDegree.put("fulllevel4", answer);
                                        break;
                                    case 91:
                                        // trainees at stipend level 5
                                        if (answer != null)
                                            hmDegree.put("fulllevel5", answer);
                                        break;
                                    case 92:
                                        // trainees at stipend level 6
                                        if (answer != null)
                                            hmDegree.put("fulllevel6", answer);
                                        break;
                                    case 93:
                                        // trainees at stipend level 7
                                        if (answer != null)
                                            hmDegree.put("fulllevel7", answer);
                                        break;
                                    default:
                                        break;

                                }
                            }
                            if (isPostDocParentQuestionFromPeriodExists(questionnaireQuestion, budgetPeriod, SD_INDEX)) {
                                switch (question.getQuestionSeqIdAsInteger()) {
                                    case 86:
                                        // trainees at stipend level 0
                                        if (answer != null)
                                            hmDegree.put("shortlevel0", answer);
                                        break;
                                    case 87:
                                        // trainees at stipend level 1
                                        if (answer != null)
                                            hmDegree.put("shortlevel1", answer);
                                        break;
                                    case 88:
                                        // trainees at stipend level 2
                                        if (answer != null)
                                            hmDegree.put("shortlevel2", answer);
                                        break;
                                    case 89:
                                        // trainees at stipend level 3
                                        if (answer != null)
                                            hmDegree.put("shortlevel3", answer);
                                        break;
                                    case 90:
                                        // trainees at stipend level 4
                                        if (answer != null)
                                            hmDegree.put("shortlevel4", answer);
                                        break;
                                    case 91:
                                        // trainees at stipend level 5
                                        if (answer != null)
                                            hmDegree.put("shortlevel5", answer);
                                        break;
                                    case 92:
                                        // trainees at stipend level 6
                                        if (answer != null)
                                            hmDegree.put("shortlevel6", answer);
                                        break;
                                    case 93:
                                        // trainees at stipend level 7
                                        if (answer != null)
                                            hmDegree.put("shortlevel7", answer);
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                    }
                }
            }


            /******************************************************
             * set post doc degree seeking numbers for each level
             ******************************************************/
            phs398TrainingBudgetYearDataType.setPostdocNumDegreeStipendLevel0(Integer.parseInt(hmDegree.get("fulllevel0")
                    .toString())
                    + Integer.parseInt(hmDegree.get("shortlevel0").toString()));
            phs398TrainingBudgetYearDataType.setPostdocNumDegreeStipendLevel1(Integer.parseInt(hmDegree.get("fulllevel1")
                    .toString())
                    + Integer.parseInt(hmDegree.get("shortlevel1").toString()));
            phs398TrainingBudgetYearDataType.setPostdocNumDegreeStipendLevel2(Integer.parseInt(hmDegree.get("fulllevel2")
                    .toString())
                    + Integer.parseInt(hmDegree.get("shortlevel2").toString()));
            phs398TrainingBudgetYearDataType.setPostdocNumDegreeStipendLevel3(Integer.parseInt(hmDegree.get("fulllevel3")
                    .toString())
                    + Integer.parseInt(hmDegree.get("shortlevel3").toString()));
            phs398TrainingBudgetYearDataType.setPostdocNumDegreeStipendLevel4(Integer.parseInt(hmDegree.get("fulllevel4")
                    .toString())
                    + Integer.parseInt(hmDegree.get("shortlevel4").toString()));
            phs398TrainingBudgetYearDataType.setPostdocNumDegreeStipendLevel5(Integer.parseInt(hmDegree.get("fulllevel5")
                    .toString())
                    + Integer.parseInt(hmDegree.get("shortlevel5").toString()));
            phs398TrainingBudgetYearDataType.setPostdocNumDegreeStipendLevel6(Integer.parseInt(hmDegree.get("fulllevel6")
                    .toString())
                    + Integer.parseInt(hmDegree.get("shortlevel6").toString()));
            phs398TrainingBudgetYearDataType.setPostdocNumDegreeStipendLevel7(Integer.parseInt(hmDegree.get("fulllevel7")
                    .toString())
                    + Integer.parseInt(hmDegree.get("shortlevel7").toString()));

            /************************************************
             * set post doc degree seeking full time number
             **********************************************/

            int postDocNumDegreeFulltime = Integer.parseInt(hmDegree.get("fulllevel0").toString())
                    + Integer.parseInt(hmDegree.get("fulllevel1").toString())
                    + Integer.parseInt(hmDegree.get("fulllevel2").toString())
                    + Integer.parseInt(hmDegree.get("fulllevel3").toString())
                    + Integer.parseInt(hmDegree.get("fulllevel4").toString())
                    + Integer.parseInt(hmDegree.get("fulllevel5").toString())
                    + Integer.parseInt(hmDegree.get("fulllevel6").toString())
                    + Integer.parseInt(hmDegree.get("fulllevel7").toString());

            phs398TrainingBudgetYearDataType.setPostdocNumDegreeFullTime(postDocNumDegreeFulltime);

            /***********************************************
             *set post doc degree seeking short term number
             * ************************************************/

            int postDocNumDegreeShortTerm = Integer.parseInt(hmDegree.get("shortlevel0").toString())
                    + Integer.parseInt(hmDegree.get("shortlevel1").toString())
                    + Integer.parseInt(hmDegree.get("shortlevel2").toString())
                    + Integer.parseInt(hmDegree.get("shortlevel3").toString())
                    + Integer.parseInt(hmDegree.get("shortlevel4").toString())
                    + Integer.parseInt(hmDegree.get("shortlevel5").toString())
                    + Integer.parseInt(hmDegree.get("shortlevel6").toString())
                    + Integer.parseInt(hmDegree.get("shortlevel7").toString());

            phs398TrainingBudgetYearDataType.setPostdocNumDegreeShortTerm(postDocNumDegreeShortTerm);

            // Total numbers of post docs
            phs398TrainingBudgetYearDataType.setPostdocTotalShortTerm(phs398TrainingBudgetYearDataType
                    .getPostdocNumDegreeShortTerm()
                    + phs398TrainingBudgetYearDataType.getPostdocNumNonDegreeShortTerm());

            phs398TrainingBudgetYearDataType.setPostdocTotalFullTime(phs398TrainingBudgetYearDataType.getPostdocNumDegreeFullTime()
                    + phs398TrainingBudgetYearDataType.getPostdocNumNonDegreeFullTime());

            // total numbers of post docs for each level
            phs398TrainingBudgetYearDataType.setPostdocTotalStipendLevel0(phs398TrainingBudgetYearDataType
                    .getPostdocNumNonDegreeStipendLevel0()
                    + phs398TrainingBudgetYearDataType.getPostdocNumDegreeStipendLevel0());

            phs398TrainingBudgetYearDataType.setPostdocTotalStipendLevel1(phs398TrainingBudgetYearDataType
                    .getPostdocNumNonDegreeStipendLevel1()
                    + phs398TrainingBudgetYearDataType.getPostdocNumDegreeStipendLevel1());
            phs398TrainingBudgetYearDataType.setPostdocTotalStipendLevel2(phs398TrainingBudgetYearDataType
                    .getPostdocNumNonDegreeStipendLevel2()
                    + phs398TrainingBudgetYearDataType.getPostdocNumDegreeStipendLevel2());
            phs398TrainingBudgetYearDataType.setPostdocTotalStipendLevel3(phs398TrainingBudgetYearDataType
                    .getPostdocNumNonDegreeStipendLevel3()
                    + phs398TrainingBudgetYearDataType.getPostdocNumDegreeStipendLevel3());
            phs398TrainingBudgetYearDataType.setPostdocTotalStipendLevel4(phs398TrainingBudgetYearDataType
                    .getPostdocNumNonDegreeStipendLevel4()
                    + phs398TrainingBudgetYearDataType.getPostdocNumDegreeStipendLevel4());
            phs398TrainingBudgetYearDataType.setPostdocTotalStipendLevel5(phs398TrainingBudgetYearDataType
                    .getPostdocNumNonDegreeStipendLevel5()
                    + phs398TrainingBudgetYearDataType.getPostdocNumDegreeStipendLevel5());
            phs398TrainingBudgetYearDataType.setPostdocTotalStipendLevel6(phs398TrainingBudgetYearDataType
                    .getPostdocNumNonDegreeStipendLevel6()
                    + phs398TrainingBudgetYearDataType.getPostdocNumDegreeStipendLevel6());
            phs398TrainingBudgetYearDataType.setPostdocTotalStipendLevel7(phs398TrainingBudgetYearDataType
                    .getPostdocNumNonDegreeStipendLevel7()
                    + phs398TrainingBudgetYearDataType.getPostdocNumDegreeStipendLevel7());


            /******************************************************
             * get stipend amounts
             ******************************************************/

            // undergrad
            numPeople = phs398TrainingBudgetYearDataType.getUndergraduateNumFirstYearSophomoreStipends();
            stipendAmountF = getStipendAmount(budgetPeriod,UNDERGRADS, 0,numPeople);
            numPeople = phs398TrainingBudgetYearDataType.getUndergraduateNumJuniorSeniorStipends();
            stipendAmountJ = getStipendAmount(budgetPeriod,UNDERGRADS, 1,numPeople);
            phs398TrainingBudgetYearDataType.setUndergraduateStipendsRequested(stipendAmountF.add(stipendAmountJ));

            cumUndergradStipends = cumUndergradStipends.add(phs398TrainingBudgetYearDataType.getUndergraduateStipendsRequested());

            // predoc
            numPeople = phs398TrainingBudgetYearDataType.getPredocSingleDegreeNumFullTime();
            stipendAmountPreSingFull = getStipendAmount(budgetPeriod,PREDOC, 0,numPeople);
            numPeople = phs398TrainingBudgetYearDataType.getPredocDualDegreeNumFullTime();
            stipendAmountPreDualFull = getStipendAmount(budgetPeriod,PREDOC, 0,numPeople);

            numPeople = phs398TrainingBudgetYearDataType.getPredocSingleDegreeNumShortTerm();
            stipendAmountPreSingShort = getStipendAmount(budgetPeriod,PREDOC, 0,numPeople);
            numPeople = phs398TrainingBudgetYearDataType.getPredocDualDegreeNumShortTerm();
            stipendAmountPreDualShort = getStipendAmount(budgetPeriod,PREDOC, 0,numPeople);

            phs398TrainingBudgetYearDataType.setPredocSingleDegreeStipendsRequested(stipendAmountPreSingFull.add(stipendAmountPreSingShort));
            phs398TrainingBudgetYearDataType.setPredocDualDegreeStipendsRequested(stipendAmountPreDualFull.add(stipendAmountPreDualShort));
            phs398TrainingBudgetYearDataType.setPredocTotalStipendsRequested(stipendAmountPreSingFull.add(stipendAmountPreDualFull
                    .add(stipendAmountPreSingShort).add(stipendAmountPreDualShort)));

            // cumulative amounts
            cumPreDocSingleStipends = cumPreDocSingleStipends.add(stipendAmountPreSingFull).add(stipendAmountPreSingShort);
            cumPreDocDualStipends = cumPreDocDualStipends.add(stipendAmountPreDualFull).add(stipendAmountPreDualShort);
            cumPreDocTotalStipends = cumPreDocSingleStipends.add(cumPreDocDualStipends);
            cumPreDocTotalTuition = cumPreDocDualTuition.add(cumPreDocSingleTuition);

            // postdoc

            numPostDocLevel0 = phs398TrainingBudgetYearDataType.getPostdocNumNonDegreeStipendLevel0();
            stipendAmountNonDeg0 = getStipendAmount(budgetPeriod,POSTDOC, 0,numPostDocLevel0);
            numPostDocLevel0 = phs398TrainingBudgetYearDataType.getPostdocNumDegreeStipendLevel0();
            stipendAmountDeg0 = getStipendAmount(budgetPeriod,POSTDOC, 0,numPostDocLevel0);

            numPostDocLevel1 = phs398TrainingBudgetYearDataType.getPostdocNumNonDegreeStipendLevel1();
            stipendAmountNonDeg1 = getStipendAmount(budgetPeriod,POSTDOC, 1,numPostDocLevel1);
            numPostDocLevel1 = phs398TrainingBudgetYearDataType.getPostdocNumDegreeStipendLevel1();
            stipendAmountDeg1 = getStipendAmount(budgetPeriod,POSTDOC, 1,numPostDocLevel1);

            numPostDocLevel2 = phs398TrainingBudgetYearDataType.getPostdocNumNonDegreeStipendLevel2();
            stipendAmountNonDeg2 = getStipendAmount(budgetPeriod,POSTDOC, 2,numPostDocLevel2);
            numPostDocLevel2 = phs398TrainingBudgetYearDataType.getPostdocNumDegreeStipendLevel2();
            stipendAmountDeg2 = getStipendAmount(budgetPeriod,POSTDOC, 2,numPostDocLevel2);

            numPostDocLevel3 = phs398TrainingBudgetYearDataType.getPostdocNumNonDegreeStipendLevel3();
            stipendAmountNonDeg3 = getStipendAmount(budgetPeriod,POSTDOC, 3,numPostDocLevel3);
            numPostDocLevel3 = phs398TrainingBudgetYearDataType.getPostdocNumDegreeStipendLevel3();
            stipendAmountDeg3 = getStipendAmount(budgetPeriod,POSTDOC, 3,numPostDocLevel3);

            numPostDocLevel4 = phs398TrainingBudgetYearDataType.getPostdocNumNonDegreeStipendLevel4();
            stipendAmountNonDeg4 = getStipendAmount(budgetPeriod,POSTDOC, 4,numPostDocLevel4);
            numPostDocLevel4 = phs398TrainingBudgetYearDataType.getPostdocNumDegreeStipendLevel4();
            stipendAmountDeg4 = getStipendAmount(budgetPeriod,POSTDOC, 4,numPostDocLevel4);

            numPostDocLevel5 = phs398TrainingBudgetYearDataType.getPostdocNumNonDegreeStipendLevel5();
            stipendAmountNonDeg5 = getStipendAmount(budgetPeriod,POSTDOC, 5,numPostDocLevel5);
            numPostDocLevel5 = phs398TrainingBudgetYearDataType.getPostdocNumDegreeStipendLevel5();
            stipendAmountDeg5 = getStipendAmount(budgetPeriod,POSTDOC, 5,numPostDocLevel5);

            numPostDocLevel6 = phs398TrainingBudgetYearDataType.getPostdocNumNonDegreeStipendLevel6();
            stipendAmountNonDeg6 = getStipendAmount(budgetPeriod,POSTDOC, 6,numPostDocLevel6);
            numPostDocLevel6 = phs398TrainingBudgetYearDataType.getPostdocNumDegreeStipendLevel6();
            stipendAmountDeg6 = getStipendAmount(budgetPeriod,POSTDOC, 6,numPostDocLevel6);

            numPostDocLevel7 = phs398TrainingBudgetYearDataType.getPostdocNumNonDegreeStipendLevel7();
            stipendAmountNonDeg7 = getStipendAmount(budgetPeriod,POSTDOC, 7,numPostDocLevel7);
            numPostDocLevel7 = phs398TrainingBudgetYearDataType.getPostdocNumDegreeStipendLevel7();
            stipendAmountDeg7 = getStipendAmount(budgetPeriod,POSTDOC, 7,numPostDocLevel7);

            phs398TrainingBudgetYearDataType.setPostdocDegreeStipendsRequested(stipendAmountDeg0.add(stipendAmountDeg1).add(
                    stipendAmountDeg2).add(stipendAmountDeg3).add(stipendAmountDeg4).add(stipendAmountDeg5).add(stipendAmountDeg6)
                    .add(stipendAmountDeg7));

            phs398TrainingBudgetYearDataType.setPostdocNonDegreeStipendsRequested(stipendAmountNonDeg0.add(stipendAmountNonDeg1)
                    .add(stipendAmountNonDeg2).add(stipendAmountNonDeg3).add(stipendAmountNonDeg4).add(stipendAmountNonDeg5).add(
                            stipendAmountNonDeg6).add(stipendAmountNonDeg7));

            phs398TrainingBudgetYearDataType.setPostdocTotalStipendsRequested(phs398TrainingBudgetYearDataType
                    .getPostdocNonDegreeStipendsRequested().add(
                            phs398TrainingBudgetYearDataType.getPostdocDegreeStipendsRequested()));


            /******************************************************
             * set total amounts
             ******************************************************/

            phs398TrainingBudgetYearDataType.setPostdocTotalTuitionAndFeesRequested(phs398TrainingBudgetYearDataType
                    .getPostdocDegreeTuitionAndFeesRequested().add(
                            phs398TrainingBudgetYearDataType.getPostdocNonDegreeTuitionAndFeesRequested()));
            phs398TrainingBudgetYearDataType.setPredocTotalTuitionAndFeesRequested(phs398TrainingBudgetYearDataType
                    .getPredocDualDegreeTuitionAndFeesRequested().add(
                            phs398TrainingBudgetYearDataType.getPredocSingleDegreeTuitionAndFeesRequested()));
            phs398TrainingBudgetYearDataType.setTotalTuitionAndFeesRequested(phs398TrainingBudgetYearDataType
                    .getPredocTotalTuitionAndFeesRequested().add(
                            phs398TrainingBudgetYearDataType.getPostdocTotalTuitionAndFeesRequested().add(
                                    phs398TrainingBudgetYearDataType.getUndergraduateTuitionAndFeesRequested())).add(
                            phs398TrainingBudgetYearDataType.getOtherTuitionAndFeesRequested()));
            phs398TrainingBudgetYearDataType.setTotalStipendsRequested(phs398TrainingBudgetYearDataType
                    .getPostdocTotalStipendsRequested().add(
                            phs398TrainingBudgetYearDataType.getPredocTotalStipendsRequested().add(
                                    phs398TrainingBudgetYearDataType.getUndergraduateStipendsRequested())).add(
                            phs398TrainingBudgetYearDataType.getOtherStipendsRequested()));
            phs398TrainingBudgetYearDataType.setTotalStipendsAndTuitionFeesRequested(phs398TrainingBudgetYearDataType
                    .getTotalStipendsRequested().add(
                            phs398TrainingBudgetYearDataType.getPostdocTotalTuitionAndFeesRequested().add(
                                    phs398TrainingBudgetYearDataType.getPredocTotalTuitionAndFeesRequested().add(
                                            phs398TrainingBudgetYearDataType.getUndergraduateTuitionAndFeesRequested().add(
                                                    phs398TrainingBudgetYearDataType.getOtherTuitionAndFeesRequested())))));

            // the total tdirect costs from r and r budget line, which is RESEARCH_DIRECT_COST, has to have the
            // total stipends and tuition subtracted from it.

            researchDirectCosts = budgetPeriod.getTotalDirectCost().subtract(trainingCost).
                                    subtract(trainingTraveCost).subtract(consTrainingCost).bigDecimalValue();
            researchDirectCosts = researchDirectCosts.subtract(phs398TrainingBudgetYearDataType.getTotalStipendsAndTuitionFeesRequested());
            phs398TrainingBudgetYearDataType.setResearchDirectCostsRequested(researchDirectCosts);
            if (phs398TrainingBudgetYearDataType.getResearchDirectCostsRequested() != null) {
                Double researchDirectCostValue = phs398TrainingBudgetYearDataType.getResearchDirectCostsRequested().doubleValue();
                if (researchDirectCostValue < ZERO) {
                    String researchDirectCostValueStipend = researchDirectCostValue.toString();
                    String budgetYear = budgetPeriod.getBudgetPeriod().toString(); 
                    AuditError stipendError = new AuditError(Constants.NO_FIELD, S2SConstants.GRANTS_GOV_STIPEND_ERROR_MESSAGE, Constants.GRANTS_GOV_PAGE + "."
                            + Constants.GRANTS_GOV_PANEL_ANCHOR );
                    String errorMessage = stipendError.getMessageKey();
                    errorMessage = errorMessage.replace(STIPEND_AMOUNT, researchDirectCostValueStipend);
                    errorMessage = errorMessage.replace(BUDGET_PERIOD, budgetYear);
                    stipendError.setMessageKey(errorMessage);
                    getAuditErrors().add(stipendError);
                }    
            }

            totalOtherDirectCostsRequested = budgetPeriod.getTotalDirectCost().bigDecimalValue();
            totalOtherDirectCostsRequested = totalOtherDirectCostsRequested.subtract(phs398TrainingBudgetYearDataType
                    .getTotalStipendsAndTuitionFeesRequested());
            phs398TrainingBudgetYearDataType.setTotalOtherDirectCostsRequested(totalOtherDirectCostsRequested);

            phs398TrainingBudgetYearDataType.setTotalDirectCostsRequested(phs398TrainingBudgetYearDataType
                    .getTotalOtherDirectCostsRequested().add(
                            phs398TrainingBudgetYearDataType.getTotalStipendsAndTuitionFeesRequested()));

            phs398TrainingBudgetYearDataType.setTotalDirectAndIndirectCostsRequested(phs398TrainingBudgetYearDataType
                    .getTotalDirectCostsRequested().add(phs398TrainingBudgetYearDataType.getTotalIndirectCostsRequested()));


            /******************************************************
             * add to cumulative amounts
             ******************************************************/

            cumPostDocNonDegStipends = cumPostDocNonDegStipends.add(phs398TrainingBudgetYearDataType.getPostdocNonDegreeStipendsRequested());
            cumPostDocDegStipends = cumPostDocDegStipends.add(phs398TrainingBudgetYearDataType.getPostdocDegreeStipendsRequested());
            cumPostDocTotalStipends = cumPostDocNonDegStipends.add(cumPostDocDegStipends);

            cumResearchTotalDirectCosts = cumResearchTotalDirectCosts.add(phs398TrainingBudgetYearDataType.getResearchDirectCostsRequested());
            cumTotalOtherDirectCosts = cumTotalOtherDirectCosts.add(phs398TrainingBudgetYearDataType.getTotalOtherDirectCostsRequested());

        }

        // cumulative amounts
        trainingBudgetType.setCumulativeUndergraduateStipendsRequested(cumUndergradStipends);
        trainingBudgetType.setCumulativeUndergraduateTuitionAndFeesRequested(cumUndergradTuition);

        trainingBudgetType.setCumulativeOtherStipendsRequested(cumOtherStipends);
        trainingBudgetType.setCumulativeOtherTuitionAndFeesRequested(cumOtherTuition);
        trainingBudgetType.setCumulativePostdocDegreeStipendsRequested(cumPostDocDegStipends);
        trainingBudgetType.setCumulativePostdocDegreeTuitionAndFeesRequested(cumPostDocDegTuition);
        trainingBudgetType.setCumulativePostdocNonDegreeStipendsRequested(cumPostDocNonDegStipends);
        trainingBudgetType.setCumulativePostdocNonDegreeTuitionAndFeesRequested(cumPostDocNonDegTuition);
        trainingBudgetType.setCumulativePostdocTotalStipendsRequested(cumPostDocTotalStipends);
        trainingBudgetType.setCumulativePostdocTotalTuitionAndFeesRequested(cumPostDocTotalTuition);

        trainingBudgetType.setCumulativePredocDualDegreeStipendsRequested(cumPreDocDualStipends);
        trainingBudgetType.setCumulativePredocDualDegreeTuitionAndFeesRequested(cumPreDocDualTuition);
        trainingBudgetType.setCumulativePredocSingleDegreeStipendsRequested(cumPreDocSingleStipends);
        trainingBudgetType.setCumulativePredocSingleDegreeTuitionAndFeesRequested(cumPreDocSingleTuition);
        trainingBudgetType.setCumulativePredocTotalStipendsRequested(cumPreDocTotalStipends);
        trainingBudgetType.setCumulativePredocTotalTuitionAndFeesRequested(cumPreDocTotalTuition);

        trainingBudgetType.setCumulativeTotalStipendsRequested(cumPostDocTotalStipends.add(cumPreDocTotalStipends).add(
                cumOtherStipends).add(cumUndergradStipends));

        trainingBudgetType.setCumulativeTuitionAndFeesRequested(cumPostDocTotalTuition.add(cumPreDocTotalTuition).add(
                cumOtherTuition).add(cumUndergradTuition));
        trainingBudgetType.setCumulativeTotalStipendsAndTuitionFeesRequested(trainingBudgetType
                .getCumulativeTotalStipendsRequested().add(trainingBudgetType.getCumulativeTuitionAndFeesRequested()));


        trainingBudgetType.setCumulativeConsortiumTrainingCostsRequested(cumConsCosts);
        trainingBudgetType.setCumulativeResearchDirectCostsRequested(cumResearchTotalDirectCosts);

        trainingBudgetType.setCumulativeTotalDirectCostsRequested(trainingBudgetType
                .getCumulativeTotalStipendsAndTuitionFeesRequested().add(cumTotalOtherDirectCosts));
        trainingBudgetType.setCumulativeTotalIndirectCostsRequested(cumTotalIndCosts1.add(cumTotalIndCosts2));
        trainingBudgetType.setCumulativeTotalOtherDirectCostsRequested(cumTotalOtherDirectCosts);
        trainingBudgetType.setCumulativeTotalDirectAndIndirectCostsRequested(trainingBudgetType
                .getCumulativeTotalDirectCostsRequested().add(cumTotalIndCosts1.add(cumTotalIndCosts2)));
        trainingBudgetType.setCumulativeTraineeTravelRequested(cumTravelCosts);
        trainingBudgetType.setCumulativeTrainingRelatedExpensesRequested(cumTrainingCosts);
    
       AttachedFileDataType attachedFileDataType = null;        
       for (NarrativeContract narrative : developmentProposal.getNarratives()) {
           if (narrative.getNarrativeType().getCode() != null) {
               if (Integer.parseInt(narrative.getNarrativeType().getCode()) == PHS_TRAINING_BUDGET_BUDGETJUSTIFICATION_130) {
                   attachedFileDataType = getAttachedFileType(narrative);                    
                   if (attachedFileDataType == null) {                        
                       continue;                    
                       }else{                        
                           break;                    
                           }                
                   }            
               }        
           }        
       if(attachedFileDataType == null){            
           attachedFileDataType = AttachedFileDataType.Factory.newInstance();        
           }        
       trainingBudgetType.setBudgetJustification(attachedFileDataType);
       
        return trainingBudgetType;
    }

    private void setOrganizationData(PHS398TrainingBudget trainingBudgetType, DevelopmentProposal developmentProposal) {
        ProposalSite applicantOrgSite = developmentProposal.getApplicantOrganization();
        if (applicantOrgSite != null) {
            Organization organization = applicantOrgSite.getOrganization();
            if (organization != null) {
                String dunsNumber = organization.getDunsNumber();
                if (dunsNumber != null) {
                    trainingBudgetType.setDUNSNumber(dunsNumber);
                }
                trainingBudgetType.setOrganizationName(organization.getOrganizationName());
            }
        }
    }

    private ScaleTwoDecimal getBudgetPeriodCost(BudgetPeriod budgetPeriod, String costType) {
        ScaleTwoDecimal totalLineItemCost = ScaleTwoDecimal.ZERO;
        String costElementsStrValue = s2SConfigurationService.getValueAsString(costType);
        String[] costElements = costElementsStrValue.split(",");
        for (int i = 0; i < costElements.length; i++) {
            String costElement = costElements[i];
            List<BudgetLineItem> budgetLineItems = budgetPeriod.getBudgetLineItems();
            for (BudgetLineItem budgetLineItem : budgetLineItems) {
                if (budgetLineItem.getCostElement().equals(costElement)) {
                    totalLineItemCost = totalLineItemCost.add(budgetLineItem.getLineItemCost());
                }
            }
        }
        return totalLineItemCost;
    }

    private List<AnswerHeader> findQuestionnaireWithAnswers(DevelopmentProposal developmentProposal, Integer budgetPeriod) {
        ProposalDevelopmentS2sQuestionnaireService questionnaireAnswerSerice = getProposalDevelopmentS2sQuestionnaireService();
        return questionnaireAnswerSerice.getProposalAnswerHeaderForForm(developmentProposal,
                "http://apply.grants.gov/forms/PHS398_TrainingBudget-V1.0", "PHS398_TrainingBudget-V1.0");
    }

    private ProposalDevelopmentS2sQuestionnaireService getProposalDevelopmentS2sQuestionnaireService() {
        return KcServiceLocator.getService(ProposalDevelopmentS2sQuestionnaireService.class);
    }

    private Answer getAnswer(QuestionnaireQuestion questionnaireQuestion, AnswerHeader answerHeader) {
        List<Answer> answers = answerHeader.getAnswers();
        for (Answer answer : answers) {
            if (answer.getQuestionnaireQuestionsId().equals(questionnaireQuestion.getId())) {
                return answer;
            }
        }
        return null;
    }

    private boolean isPostDocParentQuestionFromPeriodExists(QuestionnaireQuestion questionnaireQuestion, BudgetPeriod budgetPeriod,
            int termIndex) {
        return getPostDocParentQuestionsForPeriod(budgetPeriod, termIndex).equals(questionnaireQuestion.getParentQuestionNumber());
    }

    private Integer getPostDocParentQuestionsForPeriod(BudgetPeriod budgetPeriod, int termIndex) {
        Integer parentId = 0;
        switch (budgetPeriod.getBudgetPeriod()) {
            case (1):
                parentId = POSTDOC_PARENT_QUESTION_IDS_PERIOD1[termIndex];
                break;
            case (2):
                parentId = POSTDOC_PARENT_QUESTION_IDS_PERIOD2[termIndex];
                break;
            case (3):
                parentId = POSTDOC_PARENT_QUESTION_IDS_PERIOD3[termIndex];
                break;
            case (4):
                parentId = POSTDOC_PARENT_QUESTION_IDS_PERIOD4[termIndex];
                break;
            case (5):
                parentId = POSTDOC_PARENT_QUESTION_IDS_PERIOD5[termIndex];
                break;
        }
        return parentId;
    }

    private Integer[] getPreDocParentQuestionsForPeriod(BudgetPeriod budgetPeriod) {
        Integer[] parentIds = new Integer[0];
        switch (budgetPeriod.getBudgetPeriod()) {
            case (1):
                parentIds = PREDOC_PARENT_QUESTION_IDS_PERIOD1;
                break;
            case (2):
                parentIds = PREDOC_PARENT_QUESTION_IDS_PERIOD2;
                break;
            case (3):
                parentIds = PREDOC_PARENT_QUESTION_IDS_PERIOD3;
                break;
            case (4):
                parentIds = PREDOC_PARENT_QUESTION_IDS_PERIOD4;
                break;
            case (5):
                parentIds = PREDOC_PARENT_QUESTION_IDS_PERIOD5;
                break;
        }
        return parentIds;

    }

    private boolean isPreDocParentQuestionFromPeriodExists(QuestionnaireQuestion questionnaireQuestion, BudgetPeriod budgetPeriod) {
          return Arrays.asList(getPreDocParentQuestionsForPeriod(budgetPeriod)).contains(questionnaireQuestion.getParentQuestionNumber());
    }
    private BigDecimal getStipendAmount(BudgetPeriod budgetPeriod, String careerLevel, int experienceLevel, int numPeople) {
        BigDecimal stipendCost = ScaleTwoDecimal.ZERO.bigDecimalValue();
        TrainingStipendRateContract trainingStipendRate = trainingStipendRateService.findClosestMatchTrainingStipendRate(budgetPeriod.getStartDate(), careerLevel, experienceLevel);
        if (trainingStipendRate != null) {
            stipendCost = trainingStipendRate.getStipendRate().bigDecimalValue().multiply(new ScaleTwoDecimal(numPeople).bigDecimalValue());
        }
        return stipendCost;
    }

}
