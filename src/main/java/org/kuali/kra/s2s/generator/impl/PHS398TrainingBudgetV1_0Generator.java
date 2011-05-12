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
package org.kuali.kra.s2s.generator.impl;

import java.math.BigDecimal;
import java.util.List;

import gov.grants.apply.forms.phs398TrainingBudgetV10.PHS398TrainingBudgetDocument;
import gov.grants.apply.forms.phs398TrainingBudgetV10.PHS398TrainingBudgetYearDataType;
import gov.grants.apply.forms.phs398TrainingBudgetV10.PHS398TrainingBudgetDocument.PHS398TrainingBudget;
import gov.grants.apply.forms.phs398TrainingBudgetV10.PHS398TrainingBudgetDocument.PHS398TrainingBudget.BudgetType;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.calculator.QueryList;
import org.kuali.kra.budget.calculator.query.And;
import org.kuali.kra.budget.calculator.query.Equals;
import org.kuali.kra.budget.calculator.query.LesserThan;
import org.kuali.kra.budget.calculator.query.Or;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.rates.TrainingStipendRate;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.ProposalSite;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentS2sQuestionnaireService;
import org.kuali.kra.questionnaire.Questionnaire;
import org.kuali.kra.questionnaire.QuestionnaireQuestion;
import org.kuali.kra.questionnaire.answer.Answer;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.question.Question;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.generator.S2SBaseFormGenerator;
import org.kuali.kra.s2s.generator.bo.IndirectCostDetails;
import org.kuali.kra.s2s.generator.bo.IndirectCostInfo;
import org.kuali.kra.s2s.service.S2SBudgetCalculatorService;
import org.kuali.kra.s2s.util.S2SConstants;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DateTimeService;
import org.kuali.rice.kns.service.ParameterService;

public class PHS398TrainingBudgetV1_0Generator extends S2SBaseFormGenerator {
    private static final String TUITION_OTHER_COST_ELEMENTS = "TUITION_OTHER_COST_ELEMENTS";
    private static final String TUITION_PREDOC_SINGLE_DEG_COST_ELEMENTS = "TUITION_PREDOC_SINGLE_DEG_COST_ELEMENTS";
    private static final String TUITION_PREDOC_DUAL_DEG_COST_ELEMENTS = "TUITION_PREDOC_DUAL_DEG_COST_ELEMENTS";
    private static final String TUITION_UNDERGRAD_COST_ELEMENTS = "TUITION_UNDERGRAD_COST_ELEMENTS";
    private static final String TUITION_POSTDOC_DEG_COST_ELEMENTS = "TUITION_POSTDOC_DEG_COST_ELEMENTS";
    private static final String TUITION_POSTDOC_NONDEG_COST_ELEMENTS = "TUITION_POSTDOC_NONDEG_COST_ELEMENTS";
    private static final String SUBCONTRACT_COST_ELEMENTS = "SUBCONTRACT_COST_ELEMENTS";
    private static final String TRAINING_REL_COST_ELEMENTS = "TRAINING_REL_COST_ELEMENTS";
    private static final String TRAINEE_TRAVEL_COST_ELEMENTS = "TRAINEE_TRAVEL_COST_ELEMENTS";
    private static final String UNDERGRADS ="Undergraduates";
    private static final String PREDOC = "Predoctoral";
    private static final String POSTDOC = "Postdoctoral";
    private static BigDecimal   CUM_DIRECTCOST = null;
    private static BigDecimal   CUM_TOTAL_DIRECT_OTHERCOSTS  = null;
    private static final int PHS_TRAINING_BUDGET_BUDGETJUSTIFICATION_130 = 130;
    
    private S2SBudgetCalculatorService s2sBudgetCalculatorService;
    private DateTimeService dateTimeService;
    private ParameterService parameterService;
    public PHS398TrainingBudgetV1_0Generator(){
        s2sBudgetCalculatorService = KraServiceLocator.getService(S2SBudgetCalculatorService.class);
        dateTimeService = KraServiceLocator.getService(DateTimeService.class);
        parameterService = KraServiceLocator.getService(ParameterService.class);
    }
    public XmlObject getFormObject(ProposalDevelopmentDocument proposalDevelopmentDocument) throws S2SException {
        DevelopmentProposal developmentProposal = proposalDevelopmentDocument.getDevelopmentProposal();
        BudgetDocument<DevelopmentProposal> budgetDocument = s2sBudgetCalculatorService.getFinalBudgetVersion(proposalDevelopmentDocument);
        PHS398TrainingBudgetDocument trainingBudgetTypeDocument = PHS398TrainingBudgetDocument.Factory.newInstance();
        PHS398TrainingBudget trainingBudgetType = trainingBudgetTypeDocument.addNewPHS398TrainingBudget();  
        CUM_DIRECTCOST = new BigDecimal(0);
        CUM_TOTAL_DIRECT_OTHERCOSTS = new BigDecimal(0);
        AttachedFileDataType attachedFileDataType;
        for (Narrative narrative : developmentProposal.getNarratives()) {
             if (narrative.getNarrativeTypeCode() != null) {
                 if(Integer.parseInt(narrative.getNarrativeTypeCode())==PHS_TRAINING_BUDGET_BUDGETJUSTIFICATION_130){                     
                         attachedFileDataType = getAttachedFileType(narrative);
                         if(attachedFileDataType == null){
                             continue;
                         }
                         trainingBudgetType.setBudgetJustification(attachedFileDataType);                       
                 }
             } 
        }       
        
        if(budgetDocument!=null){
            Budget budget = budgetDocument.getBudget();
            trainingBudgetType.setFormVersion(S2SConstants.FORMVERSION_1_0);
            trainingBudgetType.setBudgetType(BudgetType.PROJECT);
            setBasicInfo(trainingBudgetType,developmentProposal,budget);
            setBudgetPeriods(trainingBudgetType,developmentProposal,budget);
        }
        return trainingBudgetTypeDocument;
    }
    private void setBudgetPeriods(PHS398TrainingBudget trainingBudgetType, DevelopmentProposal developmentProposal,Budget budget) {
        List<BudgetPeriod> budgetPeriods = budget.getBudgetPeriods();
        for (BudgetPeriod budgetPeriod : budgetPeriods) {
            setBudgetYearData(trainingBudgetType,developmentProposal,budgetPeriod);
        }
    }
    private void setBudgetYearData(PHS398TrainingBudget trainingBudgetType, DevelopmentProposal developmentProposal,BudgetPeriod budgetPeriod) {
        PHS398TrainingBudgetYearDataType  phs398TrainingBudgetYearDataType = trainingBudgetType.addNewBudgetYear();
        BudgetDecimal traineeTravelCost = getBudgetPeriodCost(budgetPeriod,TRAINEE_TRAVEL_COST_ELEMENTS);
        phs398TrainingBudgetYearDataType.setTraineeTravelRequested(traineeTravelCost.bigDecimalValue());
        BudgetDecimal trainingRelatedCost = getBudgetPeriodCost(budgetPeriod,TRAINING_REL_COST_ELEMENTS);
        phs398TrainingBudgetYearDataType.setTrainingRelatedExpensesRequested(trainingRelatedCost.bigDecimalValue());
        BudgetDecimal subcontractCost = getBudgetPeriodCost(budgetPeriod,SUBCONTRACT_COST_ELEMENTS);
        phs398TrainingBudgetYearDataType.setConsortiumTrainingCostsRequested(subcontractCost.bigDecimalValue());
        
        BudgetDecimal periodResearchDirect = budgetPeriod.getTotalDirectCost();
        periodResearchDirect = periodResearchDirect.subtract(traineeTravelCost).subtract(trainingRelatedCost).subtract(subcontractCost);

        BudgetDecimal totIndCosts = BudgetDecimal.ZERO;
        phs398TrainingBudgetYearDataType.setPeriodEndDate(dateTimeService.getCalendar(budgetPeriod.getEndDate()));
        phs398TrainingBudgetYearDataType.setPeriodStartDate(dateTimeService.getCalendar(budgetPeriod.getStartDate()));
        phs398TrainingBudgetYearDataType.setPostdocNonDegreeTuitionAndFeesRequested(getBudgetPeriodCost(budgetPeriod,TUITION_POSTDOC_NONDEG_COST_ELEMENTS).bigDecimalValue());
        phs398TrainingBudgetYearDataType.setPostdocDegreeTuitionAndFeesRequested(getBudgetPeriodCost(budgetPeriod,TUITION_POSTDOC_DEG_COST_ELEMENTS).bigDecimalValue());
        phs398TrainingBudgetYearDataType.setUndergraduateTuitionAndFeesRequested(getBudgetPeriodCost(budgetPeriod,TUITION_UNDERGRAD_COST_ELEMENTS).bigDecimalValue());
        phs398TrainingBudgetYearDataType.setPredocDualDegreeTuitionAndFeesRequested(getBudgetPeriodCost(budgetPeriod,TUITION_PREDOC_DUAL_DEG_COST_ELEMENTS).bigDecimalValue());
        phs398TrainingBudgetYearDataType.setPredocSingleDegreeTuitionAndFeesRequested(getBudgetPeriodCost(budgetPeriod,TUITION_PREDOC_SINGLE_DEG_COST_ELEMENTS).bigDecimalValue());
        phs398TrainingBudgetYearDataType.setOtherTuitionAndFeesRequested(getBudgetPeriodCost(budgetPeriod,TUITION_OTHER_COST_ELEMENTS).bigDecimalValue());
        BigDecimal bigDecimal = new BigDecimal(0);
        IndirectCostInfo indirectCostInfo = s2sBudgetCalculatorService.getIndirectCosts(budgetPeriod.getBudget(), budgetPeriod); 
        List<IndirectCostDetails> cvIndirectCost = indirectCostInfo.getIndirectCostDetails();
        for (int i = 0; i < cvIndirectCost.size() & i<2; i++) {
            IndirectCostDetails indireCost = cvIndirectCost.get(i);
            bigDecimal = bigDecimal.add(indireCost.getFunds().bigDecimalValue());//
            switch(i){
                case(0):
                    phs398TrainingBudgetYearDataType.setIndirectCostType1(indireCost.getCostType());
                    phs398TrainingBudgetYearDataType.setIndirectCostBase1(indireCost.getBase().bigDecimalValue());
                    phs398TrainingBudgetYearDataType.setIndirectCostFundsRequested1(indireCost.getFunds().bigDecimalValue());
                    phs398TrainingBudgetYearDataType.setIndirectCostRate1(indireCost.getRate().bigDecimalValue());
                    break;
                case(1):
                    phs398TrainingBudgetYearDataType.setIndirectCostType1(indireCost.getCostType());
                    phs398TrainingBudgetYearDataType.setIndirectCostBase2(indireCost.getBase().bigDecimalValue());
                    phs398TrainingBudgetYearDataType.setIndirectCostFundsRequested2(indireCost.getFunds().bigDecimalValue());
                    phs398TrainingBudgetYearDataType.setIndirectCostRate2(indireCost.getRate().bigDecimalValue());
                    break;
                default:
                    break;
            }
        }
         phs398TrainingBudgetYearDataType.setTotalIndirectCostsRequested(bigDecimal);
        setCumulativeTuitionCosts(trainingBudgetType,phs398TrainingBudgetYearDataType);
        setPreDocQuestions(trainingBudgetType,phs398TrainingBudgetYearDataType,developmentProposal,budgetPeriod);
        setPostDocQuestions(trainingBudgetType,phs398TrainingBudgetYearDataType,developmentProposal,budgetPeriod);
        
        setStipendAmounts(trainingBudgetType,phs398TrainingBudgetYearDataType,developmentProposal,budgetPeriod);


        phs398TrainingBudgetYearDataType.setResearchDirectCostsRequested(periodResearchDirect.bigDecimalValue().subtract(phs398TrainingBudgetYearDataType.getTotalStipendsAndTuitionFeesRequested()));

        BigDecimal totalOtherDirectCostsRequested = budgetPeriod.getTotalDirectCost().bigDecimalValue();
        totalOtherDirectCostsRequested = totalOtherDirectCostsRequested.subtract(phs398TrainingBudgetYearDataType.getTotalStipendsAndTuitionFeesRequested());
        phs398TrainingBudgetYearDataType.setTotalOtherDirectCostsRequested(totalOtherDirectCostsRequested);

        phs398TrainingBudgetYearDataType.setTotalDirectCostsRequested(phs398TrainingBudgetYearDataType.getTotalOtherDirectCostsRequested().add(
                                      phs398TrainingBudgetYearDataType.getTotalStipendsAndTuitionFeesRequested()) );

        phs398TrainingBudgetYearDataType.setTotalDirectAndIndirectCostsRequested(phs398TrainingBudgetYearDataType.getTotalDirectCostsRequested().add(
                                      phs398TrainingBudgetYearDataType.getTotalIndirectCostsRequested()));
        CUM_DIRECTCOST = CUM_DIRECTCOST.add(phs398TrainingBudgetYearDataType.getResearchDirectCostsRequested());
        
        
    }
    private void setStipendAmounts(PHS398TrainingBudget trainingBudgetType,
            PHS398TrainingBudgetYearDataType phs398TrainingBudgetYearDataType, DevelopmentProposal developmentProposal,
            BudgetPeriod budgetPeriod) {

        //undergrad
        int numPeople = phs398TrainingBudgetYearDataType.getUndergraduateNumFirstYearSophomoreStipends() ;
        BigDecimal stipendAmountF = getStipendAmount(budgetPeriod,UNDERGRADS,0,numPeople);
        numPeople =  phs398TrainingBudgetYearDataType.getUndergraduateNumJuniorSeniorStipends();
        BigDecimal stipendAmountJ = getStipendAmount(budgetPeriod,UNDERGRADS,1,numPeople);
        phs398TrainingBudgetYearDataType.setUndergraduateStipendsRequested(stipendAmountF.add(stipendAmountJ));

//        cumUndergradStipends = cumUndergradStipends.add(phs398TrainingBudgetYearDataType.getUndergraduateStipendsRequested());
//
        //predoc
        numPeople = phs398TrainingBudgetYearDataType.getPredocSingleDegreeNumFullTime();
        BigDecimal stipendAmountPreSingFull = getStipendAmount(budgetPeriod,PREDOC,0,numPeople);
        numPeople = phs398TrainingBudgetYearDataType.getPredocDualDegreeNumFullTime();
        BigDecimal stipendAmountPreDualFull = getStipendAmount(budgetPeriod,PREDOC,0,numPeople);
        numPeople = phs398TrainingBudgetYearDataType.getPredocSingleDegreeNumShortTerm();
        BigDecimal stipendAmountPreSingShort = getStipendAmount(budgetPeriod,PREDOC,0,numPeople);
        numPeople = phs398TrainingBudgetYearDataType.getPredocDualDegreeNumShortTerm();
        BigDecimal stipendAmountPreDualShort = getStipendAmount(budgetPeriod,PREDOC,0,numPeople);
        
        phs398TrainingBudgetYearDataType.setPredocSingleDegreeStipendsRequested( stipendAmountPreSingFull.add( stipendAmountPreSingShort));
        phs398TrainingBudgetYearDataType.setPredocDualDegreeStipendsRequested(stipendAmountPreDualFull.add( stipendAmountPreDualShort));
        phs398TrainingBudgetYearDataType.setPredocTotalStipendsRequested(stipendAmountPreSingFull.add(stipendAmountPreDualFull.
                   add(stipendAmountPreSingShort).add(stipendAmountPreDualShort)));

        //cumulative amounts
//        cumPreDocSingleStipends=cumPreDocSingleStipends.add(stipendAmountPreSingFull).add(stipendAmountPreSingShort);
//        cumPreDocDualStipends=cumPreDocDualStipends.add(stipendAmountPreDualFull).add(stipendAmountPreDualShort)  ;
//        cumPreDocTotalStipends = cumPreDocSingleStipends.add(cumPreDocDualStipends);
//        cumPreDocTotalTuition  = cumPreDocDualTuition.add(cumPreDocSingleTuition);

        //postdoc

        int numPostDocLevel0 = phs398TrainingBudgetYearDataType.getPostdocNumNonDegreeStipendLevel0();
        BigDecimal stipendAmountNonDeg0 = getStipendAmount(budgetPeriod,POSTDOC,0,numPostDocLevel0);
        numPostDocLevel0 = phs398TrainingBudgetYearDataType.getPostdocNumDegreeStipendLevel0();
        BigDecimal stipendAmountDeg0 = getStipendAmount(budgetPeriod,POSTDOC,0,numPostDocLevel0);
        
        int numPostDocLevel1 = phs398TrainingBudgetYearDataType.getPostdocNumNonDegreeStipendLevel1() ;
        BigDecimal stipendAmountNonDeg1 = getStipendAmount(budgetPeriod,POSTDOC,1,numPostDocLevel1);
        numPostDocLevel1 =  phs398TrainingBudgetYearDataType.getPostdocNumDegreeStipendLevel1();
        BigDecimal stipendAmountDeg1 = getStipendAmount(budgetPeriod,POSTDOC,1,numPostDocLevel1);
       
        int numPostDocLevel2 = phs398TrainingBudgetYearDataType.getPostdocNumNonDegreeStipendLevel2() ;
        BigDecimal stipendAmountNonDeg2 = getStipendAmount(budgetPeriod,POSTDOC,2,numPostDocLevel2);
        numPostDocLevel2 =  phs398TrainingBudgetYearDataType.getPostdocNumDegreeStipendLevel2();
        BigDecimal stipendAmountDeg2 = getStipendAmount(budgetPeriod,POSTDOC,2,numPostDocLevel2);

        int numPostDocLevel3 = phs398TrainingBudgetYearDataType.getPostdocNumNonDegreeStipendLevel3() ;
        BigDecimal stipendAmountNonDeg3 = getStipendAmount(budgetPeriod,POSTDOC,3,numPostDocLevel3);
        numPostDocLevel3 =  phs398TrainingBudgetYearDataType.getPostdocNumDegreeStipendLevel3();
        BigDecimal stipendAmountDeg3 = getStipendAmount(budgetPeriod,POSTDOC,3,numPostDocLevel3);

        int numPostDocLevel4 = phs398TrainingBudgetYearDataType.getPostdocNumNonDegreeStipendLevel4() ;
        BigDecimal stipendAmountNonDeg4 = getStipendAmount(budgetPeriod,POSTDOC,4,numPostDocLevel4);
        numPostDocLevel4 =  phs398TrainingBudgetYearDataType.getPostdocNumDegreeStipendLevel4();
        BigDecimal stipendAmountDeg4 = getStipendAmount(budgetPeriod,POSTDOC,4,numPostDocLevel4);

        int numPostDocLevel5 = phs398TrainingBudgetYearDataType.getPostdocNumNonDegreeStipendLevel5() ;
        BigDecimal stipendAmountNonDeg5 = getStipendAmount(budgetPeriod,POSTDOC,5,numPostDocLevel5);
        numPostDocLevel5 =  phs398TrainingBudgetYearDataType.getPostdocNumDegreeStipendLevel5();
        BigDecimal stipendAmountDeg5 = getStipendAmount(budgetPeriod,POSTDOC,5,numPostDocLevel5);

        int numPostDocLevel6 = phs398TrainingBudgetYearDataType.getPostdocNumNonDegreeStipendLevel6() ;
        BigDecimal stipendAmountNonDeg6 = getStipendAmount(budgetPeriod,POSTDOC,6,numPostDocLevel6);
        numPostDocLevel6 =  phs398TrainingBudgetYearDataType.getPostdocNumDegreeStipendLevel6();
        BigDecimal stipendAmountDeg6 = getStipendAmount(budgetPeriod,POSTDOC,6,numPostDocLevel6);

        int numPostDocLevel7= phs398TrainingBudgetYearDataType.getPostdocNumNonDegreeStipendLevel7() ;
        BigDecimal stipendAmountNonDeg7 = getStipendAmount(budgetPeriod,POSTDOC,7,numPostDocLevel7);
        numPostDocLevel7 =  phs398TrainingBudgetYearDataType.getPostdocNumDegreeStipendLevel7();
        BigDecimal stipendAmountDeg7 = getStipendAmount(budgetPeriod,POSTDOC,7,numPostDocLevel7);

        phs398TrainingBudgetYearDataType.setPostdocDegreeStipendsRequested(stipendAmountDeg0.add(stipendAmountDeg1).add(stipendAmountDeg2).add(
                stipendAmountDeg3).add(stipendAmountDeg4).add(stipendAmountDeg5).add(stipendAmountDeg6).add(stipendAmountDeg7));

        phs398TrainingBudgetYearDataType.setPostdocNonDegreeStipendsRequested(stipendAmountNonDeg0.add(stipendAmountNonDeg1).add(stipendAmountNonDeg2).add(
                stipendAmountNonDeg3).add(stipendAmountNonDeg4).add(stipendAmountNonDeg5).add(stipendAmountNonDeg6).add(stipendAmountNonDeg7));

        phs398TrainingBudgetYearDataType.setPostdocTotalStipendsRequested(phs398TrainingBudgetYearDataType.getPostdocNonDegreeStipendsRequested()
                    .add( phs398TrainingBudgetYearDataType.getPostdocDegreeStipendsRequested()));
      
        phs398TrainingBudgetYearDataType.setPostdocTotalTuitionAndFeesRequested(
                phs398TrainingBudgetYearDataType.getPostdocDegreeTuitionAndFeesRequested().add(
                phs398TrainingBudgetYearDataType.getPostdocNonDegreeTuitionAndFeesRequested()));
        phs398TrainingBudgetYearDataType.setPredocTotalTuitionAndFeesRequested(
                phs398TrainingBudgetYearDataType.getPredocDualDegreeTuitionAndFeesRequested().add(
                phs398TrainingBudgetYearDataType.getPredocSingleDegreeTuitionAndFeesRequested()));
        phs398TrainingBudgetYearDataType.setTotalTuitionAndFeesRequested(
                phs398TrainingBudgetYearDataType.getPredocTotalTuitionAndFeesRequested().add(
                phs398TrainingBudgetYearDataType.getPostdocTotalTuitionAndFeesRequested().add(
                phs398TrainingBudgetYearDataType.getUndergraduateTuitionAndFeesRequested())).add(
                phs398TrainingBudgetYearDataType.getOtherTuitionAndFeesRequested()));
        phs398TrainingBudgetYearDataType.setTotalStipendsRequested(
                phs398TrainingBudgetYearDataType.getPostdocTotalStipendsRequested().add(
                phs398TrainingBudgetYearDataType.getPredocTotalStipendsRequested().add(
                phs398TrainingBudgetYearDataType.getUndergraduateStipendsRequested())  ).add(
                phs398TrainingBudgetYearDataType.getOtherStipendsRequested()));
        phs398TrainingBudgetYearDataType.setTotalStipendsAndTuitionFeesRequested(
                 phs398TrainingBudgetYearDataType.getTotalStipendsRequested().add(
                 phs398TrainingBudgetYearDataType.getPostdocTotalTuitionAndFeesRequested().add(
                 phs398TrainingBudgetYearDataType.getPredocTotalTuitionAndFeesRequested().add(
                 phs398TrainingBudgetYearDataType.getUndergraduateTuitionAndFeesRequested().add(
                 phs398TrainingBudgetYearDataType.getOtherTuitionAndFeesRequested())))));

        
        
    }
    private BigDecimal getStipendAmount(BudgetPeriod budgetPeriod, String careerLevel, int experienceLevel, int numPeople) {
        BudgetDecimal stipendCost = BudgetDecimal.ZERO;
        List<TrainingStipendRate> trainingStipendRates = (List<TrainingStipendRate>)getBusinessObjectService().findAll(TrainingStipendRate.class);
        QueryList<TrainingStipendRate> trainingStipendRatesQueryList = new QueryList<TrainingStipendRate>(trainingStipendRates);
        Equals eqStartDate = new Equals("effectiveDate",budgetPeriod.getStartDate());
        LesserThan ltStartDate = new LesserThan("effectiveDate",budgetPeriod.getStartDate());
        Or lessThanOrEqualsStartDate = new Or(eqStartDate,ltStartDate);
        QueryList<TrainingStipendRate> filteredTrainingStipendRates = trainingStipendRatesQueryList.filter(lessThanOrEqualsStartDate);
        filteredTrainingStipendRates.sort("effectiveDate", false);
        Equals eqCareerLevel = new Equals("careerLevel",careerLevel);
        Equals eqExperienceLevel = new Equals("experienceLevel",experienceLevel);
        And eqCareerLevelAndeqExperienceLevel = new And(eqCareerLevel,eqExperienceLevel);
        filteredTrainingStipendRates = filteredTrainingStipendRates.filter(eqCareerLevelAndeqExperienceLevel);
        if(!filteredTrainingStipendRates.isEmpty()){
            TrainingStipendRate trainingStipendRate = filteredTrainingStipendRates.get(0);
            stipendCost = trainingStipendRate.getStipendRate().multiply(new BudgetDecimal(numPeople));
        }
        return stipendCost.bigDecimalValue();
    }
    private BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }
    private void setPostDocQuestions(PHS398TrainingBudget trainingBudgetType,
            PHS398TrainingBudgetYearDataType phs398TrainingBudgetYearDataType, DevelopmentProposal developmentProposal,
            BudgetPeriod budgetPeriod) {
          int fulllevel0=0,fulllevel1=0,fulllevel2=0,fulllevel3=0,fulllevel4=0,fulllevel5=0,fulllevel6=0,fulllevel7 = 0;
          int shortlevel0=0,shortlevel1=0,shortlevel2=0,shortlevel3=0,shortlevel4=0, shortlevel5=0 ,shortlevel6=0,shortlevel7 = 0;
          int fulllevelDegree0=0,fulllevelDegree1=0,fulllevelDegree2=0,fulllevelDegree3=0,fulllevelDegree4=0,fulllevelDegree5=0,fulllevelDegree6=0,fulllevelDegree7 = 0;
          int shortlevelDegree0=0,shortlevelDegree1=0,shortlevelDegree2=0,shortlevelDegree3=0,shortlevelDegree4=0, shortlevelDegree5=0 ,shortlevelDegree6=0,shortlevelDegree7 = 0;
          Integer fullTermNonDegreeParentQuestionnaireId = null;
          Integer shortTermNonDegreeParentQuestionnaireId = null;
          Integer fullTermDegreeParentQuestionnaireId = null;
          Integer shortTermDegreeParentQuestionnaireId = null;
          switch(budgetPeriod.getBudgetPeriod()){
              case(1):
                  fullTermNonDegreeParentQuestionnaireId = 17;
                  shortTermNonDegreeParentQuestionnaireId = 26;
                  fullTermDegreeParentQuestionnaireId = 35;
                  shortTermDegreeParentQuestionnaireId = 44;
                  break;
              case(2):
                  fullTermNonDegreeParentQuestionnaireId = 72;
                  shortTermNonDegreeParentQuestionnaireId = 81;
                  fullTermDegreeParentQuestionnaireId = 90;
                  shortTermDegreeParentQuestionnaireId = 99;
                  break;
              case(3):
                  fullTermNonDegreeParentQuestionnaireId = 127;
                  shortTermNonDegreeParentQuestionnaireId = 136;
                  fullTermDegreeParentQuestionnaireId = 146;
                  shortTermDegreeParentQuestionnaireId = 155;
                  break;
              case(4):
                  fullTermNonDegreeParentQuestionnaireId = 183;
                  shortTermNonDegreeParentQuestionnaireId = 192;
                  fullTermDegreeParentQuestionnaireId = 201;
                  shortTermDegreeParentQuestionnaireId = 210;
                  break;
              case(5):
                  fullTermNonDegreeParentQuestionnaireId = 238;
                  shortTermNonDegreeParentQuestionnaireId = 247;
                  fullTermDegreeParentQuestionnaireId = 256;
                  shortTermDegreeParentQuestionnaireId = 265;
                  break;
          }
          List<AnswerHeader> answerHeaders = findQuestionnaireWithAnswers(developmentProposal,budgetPeriod.getBudgetPeriod());
          if(answerHeaders!=null)
          for (AnswerHeader answerHeader : answerHeaders) {
              Questionnaire questionnaire = answerHeader.getQuestionnaire();
              List<QuestionnaireQuestion> questionnaireQuestions = questionnaire.getQuestionnaireQuestions();
              for (QuestionnaireQuestion questionnaireQuestion : questionnaireQuestions) {
                      Answer answer = getAnswer(questionnaireQuestion,answerHeader);
                      String answerStr = answer.getAnswer();
                      Question question = questionnaireQuestion.getQuestion();
                      if(answer!=null){
                          int answerIntVal = 0;
                          try{
                              answerIntVal = Integer.parseInt(answerStr);
                          }catch(NumberFormatException ex){}
                          switch (question.getQuestionId()) {
                               case 86:
                                   if(questionnaireQuestion.getParentQuestionNumber().equals(fullTermNonDegreeParentQuestionnaireId)){
                                       fulllevel0 = answerIntVal;
                                   }
                                   if(questionnaireQuestion.getParentQuestionNumber().equals(shortTermNonDegreeParentQuestionnaireId)){
                                       shortlevel0 = answerIntVal;
                                   }
                                   if(questionnaireQuestion.getParentQuestionNumber().equals(fullTermDegreeParentQuestionnaireId)){
                                       fulllevelDegree0 = answerIntVal;
                                   }
                                   if(questionnaireQuestion.getParentQuestionNumber().equals(shortTermDegreeParentQuestionnaireId)){
                                       shortlevelDegree0 = answerIntVal;
                                   }
                                   break;
                               case 87:
                                   if(questionnaireQuestion.getParentQuestionNumber().equals(fullTermNonDegreeParentQuestionnaireId)){
                                       fulllevel1 = answerIntVal;
                                   }
                                   if(questionnaireQuestion.getParentQuestionNumber().equals(shortTermNonDegreeParentQuestionnaireId)){
                                       shortlevel1 = answerIntVal;
                                   }
                                   if(questionnaireQuestion.getParentQuestionNumber().equals(fullTermDegreeParentQuestionnaireId)){
                                       fulllevelDegree1 = answerIntVal;
                                   }
                                   if(questionnaireQuestion.getParentQuestionNumber().equals(shortTermDegreeParentQuestionnaireId)){
                                       shortlevelDegree1 = answerIntVal;
                                   }
                                   break;
                               case 88:
                                   if(questionnaireQuestion.getParentQuestionNumber().equals(fullTermNonDegreeParentQuestionnaireId)){
                                       fulllevel2 = answerIntVal;
                                   }
                                   if(questionnaireQuestion.getParentQuestionNumber().equals(shortTermNonDegreeParentQuestionnaireId)){
                                       shortlevel2 = answerIntVal;
                                   }
                                   if(questionnaireQuestion.getParentQuestionNumber().equals(fullTermDegreeParentQuestionnaireId)){
                                       fulllevelDegree2 = answerIntVal;
                                   }
                                   if(questionnaireQuestion.getParentQuestionNumber().equals(shortTermDegreeParentQuestionnaireId)){
                                       shortlevelDegree2 = answerIntVal;
                                   }
                                   break;
                               case 89:
                                   if(questionnaireQuestion.getParentQuestionNumber().equals(fullTermNonDegreeParentQuestionnaireId)){
                                       fulllevel3 = answerIntVal;
                                   }
                                   if(questionnaireQuestion.getParentQuestionNumber().equals(shortTermNonDegreeParentQuestionnaireId)){
                                       shortlevel3 = answerIntVal;
                                   }
                                   if(questionnaireQuestion.getParentQuestionNumber().equals(fullTermDegreeParentQuestionnaireId)){
                                       fulllevelDegree3 = answerIntVal;
                                   }
                                   if(questionnaireQuestion.getParentQuestionNumber().equals(shortTermDegreeParentQuestionnaireId)){
                                       shortlevelDegree3 = answerIntVal;
                                   }
                                   break;
                               case 90:
                                   if(questionnaireQuestion.getParentQuestionNumber().equals(fullTermNonDegreeParentQuestionnaireId)){
                                       fulllevel4 = answerIntVal;
                                   }
                                   if(questionnaireQuestion.getParentQuestionNumber().equals(shortTermNonDegreeParentQuestionnaireId)){
                                       shortlevel4 = answerIntVal;
                                   }
                                   if(questionnaireQuestion.getParentQuestionNumber().equals(fullTermDegreeParentQuestionnaireId)){
                                       fulllevelDegree4 = answerIntVal;
                                   }
                                   if(questionnaireQuestion.getParentQuestionNumber().equals(shortTermDegreeParentQuestionnaireId)){
                                       shortlevelDegree4 = answerIntVal;
                                   }
                                   break;
                               case 91:
                                   if(questionnaireQuestion.getParentQuestionNumber().equals(fullTermNonDegreeParentQuestionnaireId)){
                                       fulllevel5 = answerIntVal;
                                   }
                                   if(questionnaireQuestion.getParentQuestionNumber().equals(shortTermNonDegreeParentQuestionnaireId)){
                                       shortlevel5 = answerIntVal;
                                   }
                                   if(questionnaireQuestion.getParentQuestionNumber().equals(fullTermDegreeParentQuestionnaireId)){
                                       fulllevelDegree5 = answerIntVal;
                                   }
                                   if(questionnaireQuestion.getParentQuestionNumber().equals(shortTermDegreeParentQuestionnaireId)){
                                       shortlevelDegree5 = answerIntVal;
                                   }
                                   break;
                               case 92:
                                   if(questionnaireQuestion.getParentQuestionNumber().equals(fullTermNonDegreeParentQuestionnaireId)){
                                       fulllevel6 = answerIntVal;
                                   }
                                   if(questionnaireQuestion.getParentQuestionNumber().equals(shortTermNonDegreeParentQuestionnaireId)){
                                       shortlevel6 = answerIntVal;
                                   }
                                   if(questionnaireQuestion.getParentQuestionNumber().equals(fullTermDegreeParentQuestionnaireId)){
                                       fulllevelDegree6 = answerIntVal;
                                   }
                                   if(questionnaireQuestion.getParentQuestionNumber().equals(shortTermDegreeParentQuestionnaireId)){
                                       shortlevelDegree6 = answerIntVal;
                                   }
                                   break;
                               case 93:
                                   if(questionnaireQuestion.getParentQuestionNumber().equals(fullTermNonDegreeParentQuestionnaireId)){
                                       fulllevel7 = answerIntVal;
                                   }
                                   if(questionnaireQuestion.getParentQuestionNumber().equals(shortTermNonDegreeParentQuestionnaireId)){
                                       shortlevel7 = answerIntVal;
                                   }
                                   if(questionnaireQuestion.getParentQuestionNumber().equals(fullTermDegreeParentQuestionnaireId)){
                                       fulllevelDegree7 = answerIntVal;
                                   }
                                   if(questionnaireQuestion.getParentQuestionNumber().equals(shortTermDegreeParentQuestionnaireId)){
                                       shortlevelDegree7 = answerIntVal;
                                   }
                                   break;
                               default:
                                   break;
                          }
                      }
                  }
          }
          int postDocNumNonDegreeFullTime = fulllevel0+fulllevel1+fulllevel2+fulllevel3+fulllevel4+fulllevel5+fulllevel6+fulllevel7;
          phs398TrainingBudgetYearDataType.setPostdocNumNonDegreeFullTime(postDocNumNonDegreeFullTime);
          int postDocNumNonDegreeShortTerm =shortlevel0+shortlevel1+shortlevel2+shortlevel3+shortlevel4+shortlevel5+shortlevel6+shortlevel7;
          phs398TrainingBudgetYearDataType.setPostdocNumNonDegreeShortTerm(postDocNumNonDegreeShortTerm);
          phs398TrainingBudgetYearDataType.setPostdocNumNonDegreeStipendLevel0(fulllevel0+shortlevel0);
          phs398TrainingBudgetYearDataType.setPostdocNumNonDegreeStipendLevel1(fulllevel1+shortlevel1);
          phs398TrainingBudgetYearDataType.setPostdocNumNonDegreeStipendLevel2(fulllevel2+shortlevel2);
          phs398TrainingBudgetYearDataType.setPostdocNumNonDegreeStipendLevel3(fulllevel3+shortlevel3);
          phs398TrainingBudgetYearDataType.setPostdocNumNonDegreeStipendLevel4(fulllevel4+shortlevel4);
          phs398TrainingBudgetYearDataType.setPostdocNumNonDegreeStipendLevel5(fulllevel5+shortlevel5);
          phs398TrainingBudgetYearDataType.setPostdocNumNonDegreeStipendLevel6(fulllevel6+shortlevel6);
          phs398TrainingBudgetYearDataType.setPostdocNumNonDegreeStipendLevel7(fulllevel7+shortlevel7);
          
          
          phs398TrainingBudgetYearDataType.setPostdocNumDegreeStipendLevel0(fulllevelDegree0+shortlevelDegree0);
            phs398TrainingBudgetYearDataType.setPostdocNumDegreeStipendLevel1(fulllevelDegree1+shortlevelDegree1);
            phs398TrainingBudgetYearDataType.setPostdocNumDegreeStipendLevel2(fulllevelDegree2+shortlevelDegree2);
            phs398TrainingBudgetYearDataType.setPostdocNumDegreeStipendLevel3(fulllevelDegree3+shortlevelDegree3);
            phs398TrainingBudgetYearDataType.setPostdocNumDegreeStipendLevel4(fulllevelDegree4+shortlevelDegree4);
            phs398TrainingBudgetYearDataType.setPostdocNumDegreeStipendLevel5(fulllevelDegree5+shortlevelDegree5);
            phs398TrainingBudgetYearDataType.setPostdocNumDegreeStipendLevel6(fulllevelDegree6+shortlevelDegree6);
            phs398TrainingBudgetYearDataType.setPostdocNumDegreeStipendLevel7(fulllevelDegree7+shortlevelDegree7);
            int postDocNumDegreeFulltime = fulllevelDegree0+fulllevelDegree1+fulllevelDegree2+fulllevelDegree3+fulllevelDegree4+fulllevelDegree5+fulllevelDegree6+fulllevelDegree7;
            phs398TrainingBudgetYearDataType.setPostdocNumDegreeFullTime(postDocNumDegreeFulltime);
            int postDocNumDegreeShortTerm = shortlevelDegree0+shortlevelDegree1+shortlevelDegree2+shortlevelDegree3+shortlevelDegree4+shortlevelDegree5+shortlevelDegree6+shortlevelDegree7;

             phs398TrainingBudgetYearDataType.setPostdocNumDegreeShortTerm(postDocNumDegreeShortTerm);
             phs398TrainingBudgetYearDataType.setPostdocTotalShortTerm(
                      phs398TrainingBudgetYearDataType.getPostdocNumDegreeShortTerm() +
                      phs398TrainingBudgetYearDataType.getPostdocNumNonDegreeShortTerm());
             phs398TrainingBudgetYearDataType.setPostdocTotalFullTime(
                      phs398TrainingBudgetYearDataType.getPostdocNumDegreeFullTime() +
                      phs398TrainingBudgetYearDataType.getPostdocNumNonDegreeFullTime());
                phs398TrainingBudgetYearDataType.setPostdocTotalStipendLevel0(
                  phs398TrainingBudgetYearDataType.getPostdocNumNonDegreeStipendLevel0() +
                  phs398TrainingBudgetYearDataType.getPostdocNumDegreeStipendLevel0());

                phs398TrainingBudgetYearDataType.setPostdocTotalStipendLevel1(
                  phs398TrainingBudgetYearDataType.getPostdocNumNonDegreeStipendLevel1() +
                  phs398TrainingBudgetYearDataType.getPostdocNumDegreeStipendLevel1());
                phs398TrainingBudgetYearDataType.setPostdocTotalStipendLevel2(
                  phs398TrainingBudgetYearDataType.getPostdocNumNonDegreeStipendLevel2() +
                  phs398TrainingBudgetYearDataType.getPostdocNumDegreeStipendLevel2());
                phs398TrainingBudgetYearDataType.setPostdocTotalStipendLevel3(
                  phs398TrainingBudgetYearDataType.getPostdocNumNonDegreeStipendLevel3() +
                  phs398TrainingBudgetYearDataType.getPostdocNumDegreeStipendLevel3());
                phs398TrainingBudgetYearDataType.setPostdocTotalStipendLevel4(
                  phs398TrainingBudgetYearDataType.getPostdocNumNonDegreeStipendLevel4() +
                  phs398TrainingBudgetYearDataType.getPostdocNumDegreeStipendLevel4());
                phs398TrainingBudgetYearDataType.setPostdocTotalStipendLevel5(
                  phs398TrainingBudgetYearDataType.getPostdocNumNonDegreeStipendLevel5() +
                  phs398TrainingBudgetYearDataType.getPostdocNumDegreeStipendLevel5());
                phs398TrainingBudgetYearDataType.setPostdocTotalStipendLevel6(
                  phs398TrainingBudgetYearDataType.getPostdocNumNonDegreeStipendLevel6() +
                  phs398TrainingBudgetYearDataType.getPostdocNumDegreeStipendLevel6());
                phs398TrainingBudgetYearDataType.setPostdocTotalStipendLevel7(
                  phs398TrainingBudgetYearDataType.getPostdocNumNonDegreeStipendLevel7() +
                  phs398TrainingBudgetYearDataType.getPostdocNumDegreeStipendLevel7());

          
          
    }
    private Answer getAnswer(QuestionnaireQuestion questionnaireQuestion,AnswerHeader answerHeader) {
        List<Answer> answers = answerHeader.getAnswers();
        for (Answer answer : answers) {
            if(answer.getQuestionnaireQuestionsIdFk().equals(questionnaireQuestion.getQuestionnaireQuestionsId())){
                return answer;
            }
        }
        return null;
    }
    private void setPreDocQuestions(PHS398TrainingBudget trainingBudgetType,
            PHS398TrainingBudgetYearDataType phs398TrainingBudgetYearDataType, DevelopmentProposal developmentProposal,
            BudgetPeriod budgetPeriod) {
        int undergradFirstYearNum = 0;
        int undergradJrNum = 0;
        int preDocCountFull = 0;
        int preDocCountShort = 0;
        BigDecimal otherFullStipends = BigDecimal.ZERO;
        BigDecimal otherShortStipends = BigDecimal.ZERO;
        
        BigDecimal cumOtherStipends = trainingBudgetType.getCumulativeOtherStipendsRequested();
        List<AnswerHeader> answerHeaders = findQuestionnaireWithAnswers(developmentProposal,budgetPeriod.getBudgetPeriod());
        if(answerHeaders!=null)
        for (AnswerHeader answerHeader : answerHeaders) {
            Questionnaire questionnaire = answerHeader.getQuestionnaire();
            List<QuestionnaireQuestion> questionnaireQuestions = questionnaire.getQuestionnaireQuestions();
            for (QuestionnaireQuestion questionnaireQuestion : questionnaireQuestions) {
                    Answer answerBO = getAnswer(questionnaireQuestion,answerHeader);
                    String answer = answerBO.getAnswer();
                    Question question = questionnaireQuestion.getQuestion();
                    if(answer!=null){
                        int answerIntVal=0;
                        try{
                            answerIntVal = Integer.parseInt(answer);
                        }catch(NumberFormatException ex){}
                        switch (question.getQuestionId()) {
                          case 72:
                              //full term undergrad
                              phs398TrainingBudgetYearDataType.setUndergraduateNumFullTime(answerIntVal);
                              break;
                          case 73:
                            //short term undergrad
                              phs398TrainingBudgetYearDataType.setUndergraduateNumShortTerm(answerIntVal);
                              break;
                          case 74:
                              //stipends first year
                              undergradFirstYearNum = undergradFirstYearNum + answerIntVal;
                              break;
                          case 75:
                            //stipends junior
                              undergradJrNum = undergradJrNum + answerIntVal;
                              break;
                          case 77:
                               //full time single degree predoc
                                phs398TrainingBudgetYearDataType.setPredocSingleDegreeNumFullTime(answerIntVal);
                                preDocCountFull = preDocCountFull + phs398TrainingBudgetYearDataType.getPredocSingleDegreeNumFullTime();
                                break;
                          case 78:
                               //short term single degree predoc
                                phs398TrainingBudgetYearDataType.setPredocSingleDegreeNumShortTerm(answerIntVal);
                                preDocCountShort = preDocCountShort + phs398TrainingBudgetYearDataType.getPredocSingleDegreeNumShortTerm();
                                break;
                          case 79:
                               //full term dual degree predoc
                                phs398TrainingBudgetYearDataType.setPredocDualDegreeNumFullTime(answerIntVal);
                                 preDocCountFull = preDocCountFull + phs398TrainingBudgetYearDataType.getPredocDualDegreeNumFullTime();
                                 break;
                          case 80:
                               //short term dual degree predoc
                                phs398TrainingBudgetYearDataType.setPredocDualDegreeNumShortTerm(answerIntVal);
                                preDocCountShort = preDocCountShort + phs398TrainingBudgetYearDataType.getPredocDualDegreeNumShortTerm();
                                break;
                          case 95:
                                 //others full term
                                phs398TrainingBudgetYearDataType.setOtherNumFullTime(answerIntVal);
                                break;
                          case 97:
                              //others short term
                                phs398TrainingBudgetYearDataType.setOtherNumShortTerm(answerIntVal);
                                break;
                         case 96:
                                 //others full term stipend
                                otherFullStipends = new BigDecimal(answerIntVal);
                                break;
                         case 98:
                              //others short term stipend
                                 otherShortStipends =new BigDecimal(answerIntVal);
                                break;
            
                          default:
                                break;
                        }
                    }

             }  //switch question id
          }   //for num questions
          phs398TrainingBudgetYearDataType.setOtherStipendsRequested(otherFullStipends.add(otherShortStipends));
          cumOtherStipends = cumOtherStipends.add(phs398TrainingBudgetYearDataType.getOtherStipendsRequested());

          phs398TrainingBudgetYearDataType.setUndergraduateNumFirstYearSophomoreStipends(undergradFirstYearNum);
          phs398TrainingBudgetYearDataType.setUndergraduateNumJuniorSeniorStipends(undergradJrNum);
          phs398TrainingBudgetYearDataType.setOtherStipendsRequested(otherShortStipends.add(otherFullStipends));
          phs398TrainingBudgetYearDataType.setPredocTotalNumShortTerm(preDocCountShort);
          phs398TrainingBudgetYearDataType.setPredocTotalNumFullTime(preDocCountFull);
    }
    private List<AnswerHeader> findQuestionnaireWithAnswers(DevelopmentProposal developmentProposal, Integer budgetPeriod) {
        ProposalDevelopmentS2sQuestionnaireService questionnaireAnswerSerice = getProposalDevelopmentS2sQuestionnaireService();
        return questionnaireAnswerSerice.getProposalAnswerHeaderForForm(developmentProposal, 
                                "http://apply.grants.gov/forms/PHS398_TrainingBudget-V1.0", "PHS398_TrainingBudget-V1.0");
    }
    private ProposalDevelopmentS2sQuestionnaireService getProposalDevelopmentS2sQuestionnaireService() {
        return KraServiceLocator.getService(ProposalDevelopmentS2sQuestionnaireService.class);
    }
    private void setCumulativeTuitionCosts(PHS398TrainingBudget trainingBudgetType,
            PHS398TrainingBudgetYearDataType phs398TrainingBudgetYearDataType) {
       
        BigDecimal cumulativeStipendTotal = new BigDecimal(0);
        BigDecimal cumulativeTuitionAndFeeTotal = new BigDecimal(0);
        BigDecimal cumUndergradStipends = checkNullCost(trainingBudgetType.getCumulativeUndergraduateStipendsRequested());
        BigDecimal cumUndergradTuition = checkNullCost(trainingBudgetType.getCumulativeUndergraduateTuitionAndFeesRequested());
        BigDecimal cumOtherStipends = checkNullCost(trainingBudgetType.getCumulativeOtherStipendsRequested());
        BigDecimal cumOtherTuition = checkNullCost(trainingBudgetType.getCumulativeOtherTuitionAndFeesRequested());
        BigDecimal cumPostDocDegStipends = checkNullCost(trainingBudgetType.getCumulativePostdocDegreeStipendsRequested());

        BigDecimal cumPostDocDegTuition = checkNullCost(trainingBudgetType.getCumulativePostdocDegreeTuitionAndFeesRequested());
        BigDecimal cumPostDocNonDegStipends = checkNullCost(trainingBudgetType.getCumulativePostdocNonDegreeStipendsRequested());
        BigDecimal cumPostDocNonDegTuition = checkNullCost(trainingBudgetType.getCumulativePostdocNonDegreeStipendsRequested());
        BigDecimal cumPostDocTotalStipends = checkNullCost(trainingBudgetType.getCumulativePostdocTotalStipendsRequested());
        BigDecimal cumPostDocTotalTuition = checkNullCost(trainingBudgetType.getCumulativePostdocTotalTuitionAndFeesRequested());
        BigDecimal cumPreDocDualStipends = checkNullCost(trainingBudgetType.getCumulativePredocDualDegreeStipendsRequested());
        BigDecimal cumPreDocDualTuition = checkNullCost(trainingBudgetType.getCumulativePredocDualDegreeTuitionAndFeesRequested());
        BigDecimal cumPreDocSingleStipends = checkNullCost(trainingBudgetType.getCumulativePredocSingleDegreeStipendsRequested());
        BigDecimal cumPreDocSingleTuition = checkNullCost(trainingBudgetType.getCumulativePredocSingleDegreeTuitionAndFeesRequested());
        cumulativeStipendTotal = (new BigDecimal(cumPreDocDualStipends.floatValue()+cumPreDocSingleStipends.floatValue()));
        cumulativeTuitionAndFeeTotal = (new BigDecimal(cumPreDocDualTuition.floatValue()+cumPreDocSingleTuition.floatValue()));
        BigDecimal cumConsCosts = checkNullCost(trainingBudgetType.getCumulativeConsortiumTrainingCostsRequested());
        BigDecimal cumResearchTotalDirectCosts = checkNullCost(trainingBudgetType.getCumulativeResearchDirectCostsRequested());
   
        BigDecimal cumTravelCosts = checkNullCost(trainingBudgetType.getCumulativeTraineeTravelRequested());
        BigDecimal cumTrainingCosts = checkNullCost(trainingBudgetType.getCumulativeTrainingRelatedExpensesRequested());

        BigDecimal cumTotalIndCosts = checkNullCost(trainingBudgetType.getCumulativeTotalIndirectCostsRequested());
        BigDecimal indirectCostFundsRequested1 = checkNullCost(phs398TrainingBudgetYearDataType.getIndirectCostFundsRequested1());
        BigDecimal indirectCostFundsRequested2 = checkNullCost(phs398TrainingBudgetYearDataType.getIndirectCostFundsRequested2());
        
        
        cumUndergradTuition = cumUndergradTuition.add(phs398TrainingBudgetYearDataType.getUndergraduateTuitionAndFeesRequested());
        cumPreDocSingleTuition = cumPreDocSingleTuition.add(phs398TrainingBudgetYearDataType.getPredocSingleDegreeTuitionAndFeesRequested());
        cumPreDocDualTuition = cumPreDocDualTuition.add(phs398TrainingBudgetYearDataType.getPredocDualDegreeTuitionAndFeesRequested());

        cumPostDocNonDegTuition =cumPostDocNonDegTuition.add( phs398TrainingBudgetYearDataType.getPostdocNonDegreeTuitionAndFeesRequested());
        cumPostDocDegTuition  = cumPostDocDegTuition.add(phs398TrainingBudgetYearDataType.getPostdocDegreeTuitionAndFeesRequested());
        cumPostDocTotalTuition  = cumPostDocNonDegTuition.add(cumPostDocDegTuition);
        cumOtherTuition =cumOtherTuition.add(phs398TrainingBudgetYearDataType.getOtherTuitionAndFeesRequested());
        cumTrainingCosts = cumTrainingCosts.add(phs398TrainingBudgetYearDataType.getTrainingRelatedExpensesRequested());
        cumTravelCosts =  cumTravelCosts.add(phs398TrainingBudgetYearDataType.getTraineeTravelRequested());
        cumConsCosts = cumConsCosts.add(phs398TrainingBudgetYearDataType.getConsortiumTrainingCostsRequested());
        
        cumTotalIndCosts = cumTotalIndCosts.add(indirectCostFundsRequested1.add(indirectCostFundsRequested2));
        
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
       	trainingBudgetType.setCumulativePredocTotalStipendsRequested(cumulativeStipendTotal);
       	trainingBudgetType.setCumulativePredocTotalTuitionAndFeesRequested(cumulativeTuitionAndFeeTotal);

        trainingBudgetType.setCumulativeTotalStipendsRequested(cumPostDocTotalStipends.add(cumulativeStipendTotal).add(
                cumOtherStipends).add(cumUndergradStipends));

        trainingBudgetType.setCumulativeTuitionAndFeesRequested(cumPostDocTotalTuition.add(cumulativeTuitionAndFeeTotal).add(
                cumOtherTuition).add(cumUndergradTuition));
        trainingBudgetType.setCumulativeTotalStipendsAndTuitionFeesRequested( trainingBudgetType.getCumulativeTotalStipendsRequested().add(
                   trainingBudgetType.getCumulativeTuitionAndFeesRequested()));
    
        trainingBudgetType.setCumulativeConsortiumTrainingCostsRequested(cumConsCosts);
        cumResearchTotalDirectCosts = CUM_DIRECTCOST;
        trainingBudgetType.setCumulativeResearchDirectCostsRequested(cumResearchTotalDirectCosts);
      
        trainingBudgetType.setCumulativeTotalDirectCostsRequested(trainingBudgetType.getCumulativeTotalStipendsAndTuitionFeesRequested().add(
                             checkNullCost(trainingBudgetType.getCumulativeTotalDirectCostsRequested()) ));//cumTotalOtherDirectCosts
        trainingBudgetType.setCumulativeTotalIndirectCostsRequested(cumTotalIndCosts);
      trainingBudgetType.setCumulativeTotalDirectAndIndirectCostsRequested(trainingBudgetType.getCumulativeTotalDirectCostsRequested().add(cumTotalIndCosts));
        trainingBudgetType.setCumulativeTraineeTravelRequested(cumTravelCosts);
        trainingBudgetType.setCumulativeTrainingRelatedExpensesRequested(cumTrainingCosts);
       
        CUM_TOTAL_DIRECT_OTHERCOSTS = cumTrainingCosts.add(cumTravelCosts).add(cumResearchTotalDirectCosts).add(cumConsCosts);
        trainingBudgetType.setCumulativeTotalOtherDirectCostsRequested(CUM_TOTAL_DIRECT_OTHERCOSTS);//x
        
    }
    private BigDecimal checkNullCost(BigDecimal cumulativeUndergraduateStipendsRequested) {
        return cumulativeUndergraduateStipendsRequested==null?BigDecimal.ZERO:cumulativeUndergraduateStipendsRequested;
    }
    private BudgetDecimal getBudgetPeriodCost(BudgetPeriod budgetPeriod, String costType) {
        BudgetDecimal totalLineItemCost = BudgetDecimal.ZERO;
        String costElementsStrValue = parameterService.getParameterValue(ProposalDevelopmentDocument.class, costType);
        String[] costElements = costElementsStrValue.split(",");
        for (int i = 0; i < costElements.length; i++) {
            String costElement = costElements[i];
            List<BudgetLineItem> budgetLineItems = budgetPeriod.getBudgetLineItems();
            for (BudgetLineItem budgetLineItem : budgetLineItems) {
                if(budgetLineItem.getCostElement().equals(costElement)){
                    totalLineItemCost = totalLineItemCost.add(budgetLineItem.getLineItemCost());
                }
            }
        }
        return totalLineItemCost;
    }
    private void setBasicInfo(PHS398TrainingBudget trainingBudgetType, DevelopmentProposal developmentProposal,Budget budget) {
        ProposalSite applicantOrgSite = developmentProposal.getApplicantOrganization();
        if(applicantOrgSite!=null){
            Organization organization= applicantOrgSite.getOrganization();
            if(organization!=null){
                String dunsNumber = organization.getDunsNumber();
                if(dunsNumber!=null){
                    trainingBudgetType.setDUNSNumber(dunsNumber);
                }
                trainingBudgetType.setOrganizationName(organization.getOrganizationName());
            }
        }
    }

}
