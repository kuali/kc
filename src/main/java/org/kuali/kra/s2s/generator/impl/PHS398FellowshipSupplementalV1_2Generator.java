/*
 * Copyright 2005-2010 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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

import gov.grants.apply.forms.phsFellowshipSupplemental12V12.CitizenshipDataType;
import gov.grants.apply.forms.phsFellowshipSupplemental12V12.DegreeTypeDataType;
import gov.grants.apply.forms.phsFellowshipSupplemental12V12.FieldOfTrainingDataType;
import gov.grants.apply.forms.phsFellowshipSupplemental12V12.PHSFellowshipSupplemental12Document;
import gov.grants.apply.forms.phsFellowshipSupplemental12V12.PHSFellowshipSupplemental12Document.PHSFellowshipSupplemental12;
import gov.grants.apply.forms.phsFellowshipSupplemental12V12.PHSFellowshipSupplemental12Document.PHSFellowshipSupplemental12.AdditionalInformation;
import gov.grants.apply.forms.phsFellowshipSupplemental12V12.PHSFellowshipSupplemental12Document.PHSFellowshipSupplemental12.ApplicationType;
import gov.grants.apply.forms.phsFellowshipSupplemental12V12.PHSFellowshipSupplemental12Document.PHSFellowshipSupplemental12.Budget;
import gov.grants.apply.forms.phsFellowshipSupplemental12V12.PHSFellowshipSupplemental12Document.PHSFellowshipSupplemental12.ResearchTrainingPlan;
import gov.grants.apply.forms.phsFellowshipSupplemental12V12.PHSFellowshipSupplemental12Document.PHSFellowshipSupplemental12.AdditionalInformation.ActivitiesPlannedUnderThisAward;
import gov.grants.apply.forms.phsFellowshipSupplemental12V12.PHSFellowshipSupplemental12Document.PHSFellowshipSupplemental12.AdditionalInformation.ConcurrentSupportDescription;
import gov.grants.apply.forms.phsFellowshipSupplemental12V12.PHSFellowshipSupplemental12Document.PHSFellowshipSupplemental12.AdditionalInformation.CurrentPriorNRSASupport;
import gov.grants.apply.forms.phsFellowshipSupplemental12V12.PHSFellowshipSupplemental12Document.PHSFellowshipSupplemental12.AdditionalInformation.DissertationAndResearchExperience;
import gov.grants.apply.forms.phsFellowshipSupplemental12V12.PHSFellowshipSupplemental12Document.PHSFellowshipSupplemental12.AdditionalInformation.FellowshipTrainingAndCareerGoals;
import gov.grants.apply.forms.phsFellowshipSupplemental12V12.PHSFellowshipSupplemental12Document.PHSFellowshipSupplemental12.AdditionalInformation.GraduateDegreeSought;
import gov.grants.apply.forms.phsFellowshipSupplemental12V12.PHSFellowshipSupplemental12Document.PHSFellowshipSupplemental12.AdditionalInformation.StemCells;
import gov.grants.apply.forms.phsFellowshipSupplemental12V12.PHSFellowshipSupplemental12Document.PHSFellowshipSupplemental12.ApplicationType.TypeOfApplication;
import gov.grants.apply.forms.phsFellowshipSupplemental12V12.PHSFellowshipSupplemental12Document.PHSFellowshipSupplemental12.Budget.FederalStipendRequested;
import gov.grants.apply.forms.phsFellowshipSupplemental12V12.PHSFellowshipSupplemental12Document.PHSFellowshipSupplemental12.Budget.InstitutionalBaseSalary;
import gov.grants.apply.forms.phsFellowshipSupplemental12V12.PHSFellowshipSupplemental12Document.PHSFellowshipSupplemental12.Budget.SupplementationFromOtherSources;
import gov.grants.apply.forms.phsFellowshipSupplemental12V12.PHSFellowshipSupplemental12Document.PHSFellowshipSupplemental12.Budget.InstitutionalBaseSalary.AcademicPeriod;
import gov.grants.apply.forms.phsFellowshipSupplemental12V12.PHSFellowshipSupplemental12Document.PHSFellowshipSupplemental12.ResearchTrainingPlan.InclusionEnrollmentReport;
import gov.grants.apply.forms.phsFellowshipSupplemental12V12.PHSFellowshipSupplemental12Document.PHSFellowshipSupplemental12.ResearchTrainingPlan.InclusionOfChildren;
import gov.grants.apply.forms.phsFellowshipSupplemental12V12.PHSFellowshipSupplemental12Document.PHSFellowshipSupplemental12.ResearchTrainingPlan.InclusionOfWomenAndMinorities;
import gov.grants.apply.forms.phsFellowshipSupplemental12V12.PHSFellowshipSupplemental12Document.PHSFellowshipSupplemental12.ResearchTrainingPlan.IntroductionToApplication;
import gov.grants.apply.forms.phsFellowshipSupplemental12V12.PHSFellowshipSupplemental12Document.PHSFellowshipSupplemental12.ResearchTrainingPlan.ProgressReportPublicationList;
import gov.grants.apply.forms.phsFellowshipSupplemental12V12.PHSFellowshipSupplemental12Document.PHSFellowshipSupplemental12.ResearchTrainingPlan.ProtectionOfHumanSubjects;
import gov.grants.apply.forms.phsFellowshipSupplemental12V12.PHSFellowshipSupplemental12Document.PHSFellowshipSupplemental12.ResearchTrainingPlan.ResearchStrategy;
import gov.grants.apply.forms.phsFellowshipSupplemental12V12.PHSFellowshipSupplemental12Document.PHSFellowshipSupplemental12.ResearchTrainingPlan.ResourceSharingPlan;
import gov.grants.apply.forms.phsFellowshipSupplemental12V12.PHSFellowshipSupplemental12Document.PHSFellowshipSupplemental12.ResearchTrainingPlan.RespectiveContributions;
import gov.grants.apply.forms.phsFellowshipSupplemental12V12.PHSFellowshipSupplemental12Document.PHSFellowshipSupplemental12.ResearchTrainingPlan.ResponsibleConductOfResearch;
import gov.grants.apply.forms.phsFellowshipSupplemental12V12.PHSFellowshipSupplemental12Document.PHSFellowshipSupplemental12.ResearchTrainingPlan.SelectAgentResearch;
import gov.grants.apply.forms.phsFellowshipSupplemental12V12.PHSFellowshipSupplemental12Document.PHSFellowshipSupplemental12.ResearchTrainingPlan.SelectionOfSponsorAndInstitution;
import gov.grants.apply.forms.phsFellowshipSupplemental12V12.PHSFellowshipSupplemental12Document.PHSFellowshipSupplemental12.ResearchTrainingPlan.SpecificAims;
import gov.grants.apply.forms.phsFellowshipSupplemental12V12.PHSFellowshipSupplemental12Document.PHSFellowshipSupplemental12.ResearchTrainingPlan.TargetedPlannedEnrollment;
import gov.grants.apply.forms.phsFellowshipSupplemental12V12.PHSFellowshipSupplemental12Document.PHSFellowshipSupplemental12.ResearchTrainingPlan.VertebrateAnimals;
import gov.grants.apply.forms.phsFellowshipSupplemental12V12.PHSFellowshipSupplemental12Document.PHSFellowshipSupplemental12.Sponsors;
import gov.grants.apply.forms.phsFellowshipSupplemental12V12.PHSFellowshipSupplemental12Document.PHSFellowshipSupplemental12.Sponsors.SponsorCosponsorInformation;

import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.attachmentsV10.AttachmentGroupMin0Max100DataType;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.ProposalDevelopmentUtils;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentS2sQuestionnaireService;
import org.kuali.kra.questionnaire.Questionnaire;
import org.kuali.kra.questionnaire.QuestionnaireQuestion;
import org.kuali.kra.questionnaire.answer.Answer;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.question.Question;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.util.S2SConstants;

/**
 * 
 * Class for generating the XML object for grants.gov
 * PHS398FellowshipSupplementalV1_1 Form is generated using XMLBean classes and
 * is based on PHS398FellowshipSupplementalV1_1 schema
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class PHS398FellowshipSupplementalV1_2Generator extends
		PHS398FellowshipSupplementalBaseGenerator {

	private static final Log LOG = LogFactory
			.getLog(PHS398FellowshipSupplementalV1_2Generator.class);

    static final int Q_HUMANINDEF    = 1;
    static final int Q_CLINICAL      = 2;
    static final int Q_CLINICAL3     = 3;
    static final int Q_VERTINDEF     = 4;
    static final int Q_STEMCELLS     = 5;
    static final int Q_STEMCELLIND   = 6;
    static final int Q_STEMCELLLINES = 7;
    static final int Q_HUMANSUBJ     = 10001;
    static final int Q_VERTSUBJ      = 10002;
    static final int Q_DEGREE_SOUGHT = 42;
    static final int Q_DEGREE_DATE   = 35;
    static final int Q_DEGREE_TYPE   = 15;
    static final int Q_DEGREE_TYPE2  = 99;
    static final int Q_CUR_PRIOR_NRSA = 31;
    static final int Q_FIELD_OF_TRAINING = 22;

    protected static final int SPONSOR_COSPONSOR = 134;

    static final String TUITION_COST_ELEMENTS_RA = "422310";
    static final String TUITION_COST_ELEMENTS_Other = "420111";
	/*
	 * This method is used to get PHSFellowshipSupplemental12 XMLObject and set
	 * the data to it from DevelopmentProposal data.
	 */
	private PHSFellowshipSupplemental12Document getPHSFellowshipSupplemental12() {
        PHSFellowshipSupplemental12Document phsFellowshipSupplementalDocument = PHSFellowshipSupplemental12Document.Factory.newInstance();
        PHSFellowshipSupplemental12 phsFellowshipSupplemental = phsFellowshipSupplementalDocument.addNewPHSFellowshipSupplemental12();
		phsFellowshipSupplemental.setFormVersion(S2SConstants.FORMVERSION_1_2);
		phsFellowshipSupplemental.setApplicationType(getApplicationType());
		phsFellowshipSupplemental.setAppendix(getAppendix());
		phsFellowshipSupplemental.setSponsors(setSponsorsInfo());
		phsFellowshipSupplemental.setAdditionalInformation(getAdditionalInformation());
		phsFellowshipSupplemental.setResearchTrainingPlan(getResearchTrainingPlan());
		phsFellowshipSupplemental.setBudget(getBudget());
		return phsFellowshipSupplementalDocument;
	}

    /*
     * This method is used to get Budget XMLObject and set the data to it from
     * ProposalYnq based on questionId and answers.
     */
    private Budget getBudget() {
        Budget budget = Budget.Factory.newInstance();
        Map<Integer, String> budgetMap = new HashMap<Integer, String>();
        List<AnswerHeader> answers = findQuestionnaireWithAnswers(pdDoc.getDevelopmentProposal());
        for (AnswerHeader answerHeader : answers) {
            Questionnaire questionnaire = answerHeader.getQuestionnaire();
            List<QuestionnaireQuestion> questionnaireQuestions = questionnaire.getQuestionnaireQuestions();
            for (QuestionnaireQuestion questionnaireQuestion : questionnaireQuestions) {
                Answer answerBO = getAnswer(questionnaireQuestion,answerHeader);
                String answer = answerBO.getAnswer();
                Question question = questionnaireQuestion.getQuestion();
                if (answer != null){
                    switch (question.getQuestionId()) {
                        case SENIOR:
                            budgetMap.put(SENIOR, answer);
                            break;
                        case SUPP_FUNDING_REQ:
                            budgetMap.put(SUPP_FUNDING_REQ, answer);
                            break;
                        case SUPP_SOURCE:
                            budgetMap.put(SUPP_SOURCE, answer);
                            break;
                        case SUPP_FUNDING_AMT:
                            budgetMap.put(SUPP_FUNDING_AMT, answer);
                            break;
                        case SUPP_MONTHS:
                            budgetMap.put(SUPP_MONTHS, answer);
                            break;
                        case SUPP_TYPE:
                            budgetMap.put(SUPP_TYPE, answer);
                            break;
                        case SALARY_MONTH:
                            budgetMap.put(SALARY_MONTH, answer);
                            break;
                        case ACAD_PERIOD:
                            budgetMap.put(ACAD_PERIOD, answer);
                            break;
                        case BASE_SALARY:
                            budgetMap.put(BASE_SALARY, answer);
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        budget.setTuitionAndFeesRequested(YesNoDataType.N_NO);
        getInstitutionalBaseSalary(budget, budgetMap);
        getFederalStipendRequested(budget);
        getSupplementationFromOtherSources(budget, budgetMap);
        setTuitionRequestedYears(budget);
        return budget;
    }

    /*
     * This method is used to get TuitionRequestedYears data to Budget XMLObject
     * from List of BudgetLineItem based on CostElement value of
     * TUITION_COST_ELEMENTS
     */
    private void setTuitionRequestedYears(Budget budget) {
        BudgetDocument budgetDoc = getBudgetDocument();
        if (budgetDoc == null) {
            return;
        }
        BudgetDecimal tuitionTotal = BudgetDecimal.ZERO;
        for (BudgetPeriod budgetPeriod : budgetDoc.getBudget().getBudgetPeriods()) {
            BudgetDecimal tuition = BudgetDecimal.ZERO;
            for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
                if (TUITION_COST_ELEMENTS_RA.equals(budgetLineItem.getCostElementBO().getCostElement()) ||
                        TUITION_COST_ELEMENTS_Other.equals(budgetLineItem.getCostElementBO().getCostElement())) {
                    tuition = tuition.add(budgetLineItem.getLineItemCost());
                }
            }
            tuitionTotal = tuitionTotal.add(tuition);
            switch (budgetPeriod.getBudgetPeriod()) {
            case 1:
                budget.setTuitionRequestedYear1(tuition.bigDecimalValue());
                break;
            case 2:
                budget.setTuitionRequestedYear2(tuition.bigDecimalValue());
                break;
            case 3:
                budget.setTuitionRequestedYear3(tuition.bigDecimalValue());
                break;
            case 4:
                budget.setTuitionRequestedYear4(tuition.bigDecimalValue());
                break;
            case 5:
                budget.setTuitionRequestedYear5(tuition.bigDecimalValue());
                break;
            case 6:
                budget.setTuitionRequestedYear6(tuition.bigDecimalValue());
                break;
            default:
                break;
            }
        }
        budget.setTuitionRequestedTotal(tuitionTotal.bigDecimalValue());
        if (!tuitionTotal.equals(BudgetDecimal.ZERO)) {
            budget.setTuitionAndFeesRequested(YesNoDataType.Y_YES);
        }
    }

    /*
     * This method is used to set data to SupplementationFromOtherSources
     * XMLObject from budgetMap data for Budget
     */
    private void getSupplementationFromOtherSources(Budget budget, Map<Integer, String> budgetMap) {
        if (budgetMap.get(SUPP_FUNDING_REQ) != null
                && budgetMap.get(SUPP_FUNDING_REQ).toString().equals(S2SConstants.PROPOSAL_YNQ_ANSWER_Y)) {
            SupplementationFromOtherSources supplementationFromOtherSources = 
                SupplementationFromOtherSources.Factory.newInstance();
            if (budgetMap.get(SUPP_SOURCE) != null) {
                supplementationFromOtherSources.setSource(budgetMap.get(SUPP_SOURCE).toString());
            }
            if (budgetMap.get(SUPP_FUNDING_AMT) != null) {
                supplementationFromOtherSources.setAmount(new BigDecimal(budgetMap.get(SUPP_FUNDING_AMT).toString()));
            }
            if (budgetMap.get(SUPP_MONTHS) != null) {
                supplementationFromOtherSources.setNumberOfMonths(new BigDecimal(budgetMap.get(SUPP_MONTHS).toString()));
            }
            if (budgetMap.get(SUPP_TYPE) != null) {
                supplementationFromOtherSources.setType(budgetMap.get(SUPP_TYPE).toString());
            }
            budget.setSupplementationFromOtherSources(supplementationFromOtherSources);
        }
    }

    /*
     * This method is used to get FederalStipendRequested XMLObject and set
     * additional information data to it.
     */
    private void getFederalStipendRequested(Budget budget) {
        FederalStipendRequested federalStipendRequested = FederalStipendRequested.Factory.newInstance();
        BudgetDocument budgetDoc = getBudgetDocument();
        if (budgetDoc != null) {
            org.kuali.kra.budget.core.Budget pBudget = budgetDoc.getBudget();
            BudgetDecimal sumOfLineItemCost = BudgetDecimal.ZERO;
            BudgetDecimal numberOfMonths = BudgetDecimal.ZERO;
            for (BudgetPeriod budgetPeriod : pBudget.getBudgetPeriods()) {
                if (budgetPeriod.getBudgetPeriod() == 1) {
                    for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
                        if (budgetLineItem.getCostElementBO().getCostElement().equals(STIPEND_COST_ELEMENTS)) {
                            sumOfLineItemCost = sumOfLineItemCost.add(budgetLineItem.getLineItemCost());
                            numberOfMonths = numberOfMonths.add(getNumberOfMonths(
                                    budgetLineItem.getStartDate(), budgetLineItem.getEndDate()));
                        }
                    }
                }
            }
            federalStipendRequested.setAmount(sumOfLineItemCost.bigDecimalValue());
            federalStipendRequested.setNumberOfMonths(numberOfMonths.bigDecimalValue());
            budget.setFederalStipendRequested(federalStipendRequested);
            
        }
    }

    /*
     * This method is used to get final version of BudgetDocument from
     * s2SBudgetCalculatorService using pdDoc
     */
    private BudgetDocument getBudgetDocument() {
        BudgetDocument budgetDoc = null;
        try {
            budgetDoc = s2SBudgetCalculatorService.getFinalBudgetVersion(pdDoc);
        } catch (S2SException e) {
            LOG.error("Error while getting Budget", e);
        }
        return budgetDoc;
    }

    /*
     * This method is used to set data to InstitutionalBaseSalary XMLObject from
     * budgetMap data for Budget
     */
    private void getInstitutionalBaseSalary(Budget budget, Map<Integer, String> budgetMap) {
        InstitutionalBaseSalary institutionalBaseSalary = InstitutionalBaseSalary.Factory.newInstance();
        if (budgetMap.get(SENIOR) != null
                && budgetMap.get(SENIOR).toString().equals(S2SConstants.PROPOSAL_YNQ_ANSWER_Y)) {
            if (budgetMap.get(BASE_SALARY) != null) {
                institutionalBaseSalary.setAmount(new BigDecimal(budgetMap.get(BASE_SALARY).toString()));
            }
            if (budgetMap.get(ACAD_PERIOD) != null) {
                institutionalBaseSalary.setAcademicPeriod(AcademicPeriod.Enum.forString(budgetMap.get(ACAD_PERIOD).toString()));
            }
            if (budgetMap.get(SALARY_MONTH) != null) {
                institutionalBaseSalary.setNumberOfMonths(new BigDecimal(budgetMap.get(SALARY_MONTH).toString()));
            }
            budget.setInstitutionalBaseSalary(institutionalBaseSalary);
        }
    }

	/*
	 * This method used to set data to ResearchTrainingPlan XMLObject from
	 * DevelopmentProposal
	 */
	private ResearchTrainingPlan getResearchTrainingPlan() {
		ResearchTrainingPlan researchTrainingPlan = ResearchTrainingPlan.Factory.newInstance();
		setHumanSubjectInvolvedAndVertebrateAnimalUsed(researchTrainingPlan);
		setNarrativeDataForResearchTrainingPlan(researchTrainingPlan);
		return researchTrainingPlan;
	}

	/**
	 * This method is used to set Narrative Data to ResearchTrainingPlan
	 * XMLObject based on NarrativeTypeCode.
	 * 
	 * @param researchTrainingPlan
	 */
	private void setNarrativeDataForResearchTrainingPlan(
			ResearchTrainingPlan researchTrainingPlan) {
		AttachedFileDataType attachedFileDataType = null;
		for (Narrative narrative : pdDoc.getDevelopmentProposal()
				.getNarratives()) {
			if (narrative.getNarrativeTypeCode() != null) {
				switch (Integer.parseInt(narrative.getNarrativeTypeCode())) {
				case INTRODUCTION_TO_APPLICATION:
	                attachedFileDataType = getAttachedFileType(narrative);
	                if(attachedFileDataType == null){
	                    continue;
	                }
					IntroductionToApplication introductionToApplication = IntroductionToApplication.Factory
							.newInstance();
					introductionToApplication.setAttFile(attachedFileDataType);
					researchTrainingPlan
							.setIntroductionToApplication(introductionToApplication);
					break;
				case SPECIFIC_AIMS:
	                attachedFileDataType = getAttachedFileType(narrative);
	                if(attachedFileDataType == null){
	                    continue;
	                }
					SpecificAims specificAims = SpecificAims.Factory
							.newInstance();
					specificAims.setAttFile(attachedFileDataType);
					researchTrainingPlan.setSpecificAims(specificAims);
					break;
                case RESEARCH_STRATEGY:
                    attachedFileDataType = getAttachedFileType(narrative);
                    if(attachedFileDataType == null){
                        continue;
                    }
                    ResearchStrategy researchStrategy = ResearchStrategy.Factory
                            .newInstance();
                    researchStrategy.setAttFile(attachedFileDataType);
                    researchTrainingPlan.setResearchStrategy(researchStrategy);
                    break;
				case INCLUSION_ENROLLMENT_REPORT:
	                attachedFileDataType = getAttachedFileType(narrative);
	                if(attachedFileDataType == null){
	                    continue;
	                }
					InclusionEnrollmentReport inclusionEnrollmentReport = InclusionEnrollmentReport.Factory
							.newInstance();
					inclusionEnrollmentReport.setAttFile(attachedFileDataType);
					researchTrainingPlan
							.setInclusionEnrollmentReport(inclusionEnrollmentReport);
					break;
				case PROGRESS_REPORT_PUBLICATION_LIST:
	                attachedFileDataType = getAttachedFileType(narrative);
	                if(attachedFileDataType == null){
	                    continue;
	                }
					ProgressReportPublicationList progressReportPublicationList = ProgressReportPublicationList.Factory
							.newInstance();
					progressReportPublicationList
							.setAttFile(attachedFileDataType);
					researchTrainingPlan
							.setProgressReportPublicationList(progressReportPublicationList);
					break;
				case PROTECTION_OF_HUMAN_SUBJECTS:
	                attachedFileDataType = getAttachedFileType(narrative);
	                if(attachedFileDataType == null){
	                    continue;
	                }
					ProtectionOfHumanSubjects protectionOfHumanSubjects = ProtectionOfHumanSubjects.Factory
							.newInstance();
					protectionOfHumanSubjects.setAttFile(attachedFileDataType);
					researchTrainingPlan
							.setProtectionOfHumanSubjects(protectionOfHumanSubjects);
					break;
				case INCLUSION_OF_WOMEN_AND_MINORITIES:
	                attachedFileDataType = getAttachedFileType(narrative);
	                if(attachedFileDataType == null){
	                    continue;
	                }
					InclusionOfWomenAndMinorities inclusionOfWomenAndMinorities = InclusionOfWomenAndMinorities.Factory
							.newInstance();
					inclusionOfWomenAndMinorities
							.setAttFile(attachedFileDataType);
					researchTrainingPlan
							.setInclusionOfWomenAndMinorities(inclusionOfWomenAndMinorities);
					break;
				case TARGETED_PLANNED_ENROLLMENT:
	                attachedFileDataType = getAttachedFileType(narrative);
	                if(attachedFileDataType == null){
	                    continue;
	                }
					TargetedPlannedEnrollment tarPlannedEnrollmentTable = TargetedPlannedEnrollment.Factory
							.newInstance();
					tarPlannedEnrollmentTable.setAttFile(attachedFileDataType);
					researchTrainingPlan
							.setTargetedPlannedEnrollment(tarPlannedEnrollmentTable);
					break;
				case INCLUSION_OF_CHILDREN:
	                attachedFileDataType = getAttachedFileType(narrative);
	                if(attachedFileDataType == null){
	                    continue;
	                }
					InclusionOfChildren inclusionOfChildren = InclusionOfChildren.Factory
							.newInstance();
					inclusionOfChildren.setAttFile(attachedFileDataType);
					researchTrainingPlan
							.setInclusionOfChildren(inclusionOfChildren);
					break;
				case VERTEBRATE_ANIMALS:
	                attachedFileDataType = getAttachedFileType(narrative);
	                if(attachedFileDataType == null){
	                    continue;
	                }
					VertebrateAnimals vertebrateAnimals = VertebrateAnimals.Factory
							.newInstance();
					vertebrateAnimals.setAttFile(attachedFileDataType);
					researchTrainingPlan
							.setVertebrateAnimals(vertebrateAnimals);
					break;
				case SELECT_AGENT_RESEARCH:
	                attachedFileDataType = getAttachedFileType(narrative);
	                if(attachedFileDataType == null){
	                    continue;
	                }
					SelectAgentResearch selectAgentResearch = SelectAgentResearch.Factory
							.newInstance();
					selectAgentResearch.setAttFile(attachedFileDataType);
					researchTrainingPlan
							.setSelectAgentResearch(selectAgentResearch);
					break;
				case RESOURCE_SHARING_PLANS:
	                attachedFileDataType = getAttachedFileType(narrative);
	                if(attachedFileDataType == null){
	                    continue;
	                }
					ResourceSharingPlan resourceSharingPlan = ResourceSharingPlan.Factory
							.newInstance();
					resourceSharingPlan.setAttFile(attachedFileDataType);
					researchTrainingPlan
							.setResourceSharingPlan(resourceSharingPlan);
					break;
				case RESPECTIVE_CONTRIBUTIONS:
	                attachedFileDataType = getAttachedFileType(narrative);
	                if(attachedFileDataType == null){
	                    continue;
	                }
					RespectiveContributions respectiveContributions = RespectiveContributions.Factory
							.newInstance();
					respectiveContributions.setAttFile(attachedFileDataType);
					researchTrainingPlan
							.setRespectiveContributions(respectiveContributions);
					break;
				case SELECTION_OF_SPONSOR_AND_INSTITUTION:
	                attachedFileDataType = getAttachedFileType(narrative);
	                if(attachedFileDataType == null){
	                    continue;
	                }
					SelectionOfSponsorAndInstitution selectionOfSponsorAndInstitution = SelectionOfSponsorAndInstitution.Factory
							.newInstance();
					selectionOfSponsorAndInstitution
							.setAttFile(attachedFileDataType);
					researchTrainingPlan
							.setSelectionOfSponsorAndInstitution(selectionOfSponsorAndInstitution);
					break;
				case RESPONSIBLE_CONDUCT_OF_RESEARCH:
	                attachedFileDataType = getAttachedFileType(narrative);
	                if(attachedFileDataType == null){
	                    continue;
	                }
					ResponsibleConductOfResearch responsibleConductOfResearch = ResponsibleConductOfResearch.Factory
							.newInstance();
					responsibleConductOfResearch
							.setAttFile(attachedFileDataType);
					researchTrainingPlan
							.setResponsibleConductOfResearch(responsibleConductOfResearch);
					break;
				default:
					break;
				}
			}
		}
	}
    /**
     * This method is used to set Narrative Data to ResearchTrainingPlan
     * XMLObject based on NarrativeTypeCode.
     * 
     * @param researchTrainingPlan
     */
    private Sponsors setSponsorsInfo() {
        Sponsors sponsors = Sponsors.Factory.newInstance();
        AttachedFileDataType attachedFileDataType = null;
        for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            int typeCode = Integer.parseInt(narrative.getNarrativeTypeCode());
            if ((narrative.getNarrativeTypeCode() != null) && (typeCode == SPONSOR_COSPONSOR)) {
                attachedFileDataType = getAttachedFileType(narrative);
                if(attachedFileDataType == null){
                    continue;
                }
                SponsorCosponsorInformation sponsorCosponsorInfo = SponsorCosponsorInformation.Factory.newInstance();
                sponsorCosponsorInfo.setAttFile(attachedFileDataType);
                sponsors.setSponsorCosponsorInformation(sponsorCosponsorInfo);
                break;  // done with loop, we found what we're looking for
            }
        }
        return sponsors;
    }


	/**
	 * This method is used to set HumanSubjectInvoved and VertebrateAnimalUsed
	 * XMLObject Data.
	 * 
	 * @param developmentProposal
	 * @param researchTrainingPlan
	 */
	private void setHumanSubjectInvolvedAndVertebrateAnimalUsed(
			ResearchTrainingPlan researchTrainingPlan) {
		researchTrainingPlan.setHumanSubjectsInvolved(YesNoDataType.N_NO);
        researchTrainingPlan.setHumanSubjectsIndefinite(YesNoDataType.N_NO);
		researchTrainingPlan.setVertebrateAnimalsUsed(YesNoDataType.N_NO);
        researchTrainingPlan.setVertebrateAnimalsIndefinite(YesNoDataType.N_NO);
        researchTrainingPlan.setClinicalTrial(YesNoDataType.N_NO);
        researchTrainingPlan.setPhase3ClinicalTrial(YesNoDataType.N_NO);
		List<AnswerHeader> answers = findQuestionnaireWithAnswers(pdDoc.getDevelopmentProposal());
        for (AnswerHeader answerHeader : answers) {
            Questionnaire questionnaire = answerHeader.getQuestionnaire();
            List<QuestionnaireQuestion> questionnaireQuestions = questionnaire.getQuestionnaireQuestions();
            for (QuestionnaireQuestion questionnaireQuestion : questionnaireQuestions) {
                Answer answerBO = getAnswer(questionnaireQuestion,answerHeader);
                String answer = answerBO.getAnswer();
                Question question = questionnaireQuestion.getQuestion();
                if (answer != null){
                    switch (question.getQuestionId()) {
                        case Q_HUMANSUBJ:
                            if ("y".equals(answer.toLowerCase())) {
                                researchTrainingPlan.setHumanSubjectsInvolved(YesNoDataType.Y_YES);
                            }
                            break;
                        case Q_HUMANINDEF:
                            if ("y".equals(answer.toLowerCase())) {
                                researchTrainingPlan.setHumanSubjectsIndefinite(YesNoDataType.Y_YES);
                            }
                            break;
                        case Q_VERTSUBJ:
                            if ("y".equals(answer.toLowerCase())) {
                                researchTrainingPlan.setVertebrateAnimalsUsed(YesNoDataType.Y_YES);
                            }
                            break;
                        case Q_VERTINDEF:
                            if ("y".equals(answer.toLowerCase())) {
                                researchTrainingPlan.setVertebrateAnimalsIndefinite(YesNoDataType.Y_YES);
                            }
                            break;
                        case Q_CLINICAL:
                            if ("y".equals(answer.toLowerCase())) {
                                researchTrainingPlan.setClinicalTrial(YesNoDataType.Y_YES);
                            }
                            break;
                        case Q_CLINICAL3:
                            if ("y".equals(answer.toLowerCase())) {
                                researchTrainingPlan.setPhase3ClinicalTrial(YesNoDataType.Y_YES);
                            }
                            break;
                            
                        default:
                            break;
                        }
                    }
             }  //switch question id
        }   //for num questions
	}

    private List<AnswerHeader> findQuestionnaireWithAnswers(DevelopmentProposal developmentProposal) {
        ProposalDevelopmentS2sQuestionnaireService questionnaireAnswerService = getProposalDevelopmentS2sQuestionnaireService();
        return questionnaireAnswerService.getProposalAnswerHeaderForForm(developmentProposal, 
                "http://apply.grants.gov/forms/PHS_Fellowship_Supplemental_1_2-V1.2", "PHS_Fellowship_Supplemental_1_2-V1.2");
    }
    private ProposalDevelopmentS2sQuestionnaireService getProposalDevelopmentS2sQuestionnaireService() {
        return KraServiceLocator.getService(ProposalDevelopmentS2sQuestionnaireService.class);
    }
    private Answer getAnswer(QuestionnaireQuestion questionnaireQuestion, AnswerHeader answerHeader) {
        List<Answer> answers = answerHeader.getAnswers();
        for (Answer answer : answers) {
            if(answer.getQuestionnaireQuestionsIdFk().equals(questionnaireQuestion.getQuestionnaireQuestionsId())){
                return answer;
            }
        }
        return null;
    }

	
	/*
	 * This method is used to set additional information data to
	 * AdditionalInformation XMLObject from DevelopmentProposal, ProposalYnq
	 */
	private AdditionalInformation getAdditionalInformation() {
		AdditionalInformation additionalInformation = AdditionalInformation.Factory.newInstance();
		StemCells stemCells = StemCells.Factory.newInstance();
        additionalInformation.setAlernatePhoneNumber("None");
		GraduateDegreeSought graduateDegreeSought = GraduateDegreeSought.Factory.newInstance();
		boolean setGradDegree = false;
		ArrayList<String> cellLinesList = new ArrayList<String>(Arrays.asList(stemCells.getCellLinesArray())); 
		additionalInformation.setConcurrentSupport(YesNoDataType.N_NO);  // default
        additionalInformation.setCurrentPriorNRSASupportIndicator(YesNoDataType.N_NO);
		ProposalPerson principalInvestigator = s2sUtilService.getPrincipalInvestigator(pdDoc);
		if (principalInvestigator != null) {
			if (principalInvestigator.getCountryOfCitizenship() != null && principalInvestigator.getCountryOfCitizenship().length()>0) {
				additionalInformation.setCitizenship(CitizenshipDataType.Enum.forString(principalInvestigator.getCountryOfCitizenship()));
			} else {
			    additionalInformation.setCitizenship(CitizenshipDataType.PERMANENT_RESIDENT_OF_U_S);
			}
			if(principalInvestigator.getSecondaryOfficePhone() != null && principalInvestigator.getSecondaryOfficePhone().length()>0) {
			    additionalInformation.setAlernatePhoneNumber(principalInvestigator.getSecondaryOfficePhone());
			}
		}
		
		List<AnswerHeader> answers = findQuestionnaireWithAnswers(pdDoc.getDevelopmentProposal());
		for (AnswerHeader answerHeader : answers) {
		    Questionnaire questionnaire = answerHeader.getQuestionnaire();
		    List<QuestionnaireQuestion> questionnaireQuestions = questionnaire.getQuestionnaireQuestions();
		    for (QuestionnaireQuestion questionnaireQuestion : questionnaireQuestions) {
		        Answer answerBO = getAnswer(questionnaireQuestion,answerHeader);
		        String answer = answerBO.getAnswer();
		        Question question = questionnaireQuestion.getQuestion();
		        if (answer != null){
		            switch (question.getQuestionId()) {
		                case Q_STEMCELLS:
		                    stemCells.setIsHumanStemCellsInvolved(answer.equals(S2SConstants.PROPOSAL_YNQ_ANSWER_Y)  
		                                ? YesNoDataType.Y_YES
	                                    : YesNoDataType.N_NO);
		                    break;
		                case Q_STEMCELLIND:
		                    // NOTE: the following indicator is set backwards, i.e. question answered yes 
		                    // gets set in form as No.  (Different wording of questions....) 
                            stemCells.setStemCellsIndicator(answer.equals(S2SConstants.PROPOSAL_YNQ_ANSWER_Y) 
                                        ? YesNoDataType.N_NO
                                        : YesNoDataType.Y_YES);
		                    break;
		                case Q_STEMCELLLINES:
		                    cellLinesList.add(answer);
		                    break;
		                case Q_DEGREE_SOUGHT:
		                    setGradDegree = answer.equals(S2SConstants.PROPOSAL_YNQ_ANSWER_Y);
                            break;
		                case Q_DEGREE_DATE:
                            String temp = answer.substring(0,3) + answer.substring(6);  
		                    graduateDegreeSought.setDegreeDate(temp);  // use just month and year
                            break;
		                case Q_DEGREE_TYPE:
                        case Q_DEGREE_TYPE2:
                            DegreeTypeDataType.Enum degreeSought = null;
                            try {
                                degreeSought = degreeSought.forString(answer);
                            }
                            catch (Exception e) {
                            }
                            
                            if (degreeSought == null) {
                                // problem converting, so don't set degree type in form
                                setGradDegree = false;
                            } else {
		                        graduateDegreeSought.setDegreeType(degreeSought);
                            }
                            break;
		                case Q_FIELD_OF_TRAINING:
		                    FieldOfTrainingDataType.Enum fieldOfTraining = null;
		                    try {
		                        fieldOfTraining = fieldOfTraining.forString(answer);
		                    } catch (Exception e) {
		                    }
		                    if (fieldOfTraining == null) {
		                        fieldOfTraining = FieldOfTrainingDataType.X_8000_OTHER_PREDOMINANTLY_CLINICAL_RESEARCH_TRAINING;
		                    }
		                    additionalInformation.setFieldOfTraining(fieldOfTraining);
		                    break;
                        case Q_CUR_PRIOR_NRSA:
                            additionalInformation.setCurrentPriorNRSASupportIndicator(answer.equals(S2SConstants.PROPOSAL_YNQ_ANSWER_Y) 
                                        ? YesNoDataType.Y_YES
                                        : YesNoDataType.N_NO);
                            break;
		                default:
		                    break;
	                    }
	             }  //switch question id
	        }   //for num questions
		}
		stemCells.setCellLinesArray((String[]) cellLinesList.toArray(new String[0]));
		additionalInformation.setStemCells(stemCells);
		if (setGradDegree) {
		    additionalInformation.setGraduateDegreeSought(graduateDegreeSought);
		}
		additionalInformation.setCurrentPriorNRSASupportArray(getCurrentPriorNRSASupportArray());
		additionalInformation.setConcurrentSupport(YesNoDataType.N_NO);
		AttachedFileDataType attachedFileDataType = null;
		for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
			if (narrative.getNarrativeTypeCode() != null) {
				switch (Integer.parseInt(narrative.getNarrativeTypeCode())) {
				case CONCURRENT_SUPPORT:
	                attachedFileDataType = getAttachedFileType(narrative);
	                if(attachedFileDataType == null){
	                    continue;
	                }
					ConcurrentSupportDescription concurrentSupportDescription = ConcurrentSupportDescription.Factory
							.newInstance();
					concurrentSupportDescription.setAttFile(attachedFileDataType);
					additionalInformation.setConcurrentSupport(YesNoDataType.Y_YES);
					additionalInformation.setConcurrentSupportDescription(concurrentSupportDescription);
					break;
				case FELLOWSHIP:
	                attachedFileDataType = getAttachedFileType(narrative);
	                if(attachedFileDataType == null){
	                    continue;
	                }
					FellowshipTrainingAndCareerGoals fellowshipTrainingAndCareerGoals = 
					    FellowshipTrainingAndCareerGoals.Factory.newInstance();
					fellowshipTrainingAndCareerGoals.setAttFile(attachedFileDataType);
					additionalInformation.setFellowshipTrainingAndCareerGoals(fellowshipTrainingAndCareerGoals);
					break;
				case DISSERTATION:
	                attachedFileDataType = getAttachedFileType(narrative);
	                if(attachedFileDataType == null){
	                    continue;
	                }
					DissertationAndResearchExperience dissertationAndResearchExperience = DissertationAndResearchExperience.Factory
							.newInstance();
					dissertationAndResearchExperience
							.setAttFile(attachedFileDataType);
					additionalInformation
							.setDissertationAndResearchExperience(dissertationAndResearchExperience);
					break;
				case ACTIVITIES:
	                attachedFileDataType = getAttachedFileType(narrative);
	                if(attachedFileDataType == null){
	                    continue;
	                }
					ActivitiesPlannedUnderThisAward activitiesPlannedUnderThisAward = 
					    ActivitiesPlannedUnderThisAward.Factory	.newInstance();
					activitiesPlannedUnderThisAward.setAttFile(attachedFileDataType);
					additionalInformation.setActivitiesPlannedUnderThisAward(activitiesPlannedUnderThisAward);
					break;
				default:
					break;

				}
			}
		}
		return additionalInformation;
	}

	/*
	 * This method is used to get Arrays of CurrentPriorNRSASupport XMLObject
	 * and set data to it from List of ProposalYnq
	 */
	private CurrentPriorNRSASupport[] getCurrentPriorNRSASupportArray() {
		List<CurrentPriorNRSASupport> currentPriorNRSASupportList = new ArrayList<CurrentPriorNRSASupport>();
		List<Answer> answerList = new ArrayList<Answer>();
		String nsrSupport = null;
		for (Answer questionnaireAnswer : s2sUtilService.getQuestionnaireAnswers(pdDoc.getDevelopmentProposal(),getNamespace(),getFormName())) {
			int questionId = questionnaireAnswer.getQuestionNumber();
			String answer = questionnaireAnswer.getAnswer();
			if (answer != null) {
				switch (questionId) {
				case KIRST_START_KNOWN:
				case KIRST_END_KNOWN:
				case KIRST_START_DT:
				case KIRST_END_DT:
				case KIRST_GRANT_KNOWN:
				case KIRST_GRANT_NUM:
				case PRE_OR_POST:
				case IND_OR_INST:
					if (questionId == KIRST_START_KNOWN) {
						if (answer.equals(QUESTION_ANSWER_NO)) {
							answer = S2SConstants.VALUE_UNKNOWN;
							questionId = KIRST_START_DT;
						} else {
							break;
						}
						if (questionId == KIRST_END_KNOWN) {
							if (answer.equals(QUESTION_ANSWER_NO)) {
								answer = S2SConstants.VALUE_UNKNOWN;
								questionId = KIRST_END_DT;
							} else {
								break;
							}
						}
						if (questionId == KIRST_GRANT_KNOWN) {
							if (answer.equals(QUESTION_ANSWER_NO)) {
								answer = S2SConstants.VALUE_UNKNOWN;
								questionId = KIRST_GRANT_NUM;
							} else {
								break;
							}
						}
						Answer quesAnswer = new Answer();
						quesAnswer.setAnswer(answer);
						quesAnswer.setQuestionNumber(questionId);
						answerList.add(quesAnswer);
					}
					break;
				case NRSA_SUPPORT:
					nsrSupport = answer
							.equals(S2SConstants.PROPOSAL_YNQ_ANSWER_Y) ? NSR_SUPPORT_YES
							: NSR_SUPPORT_NO;
					break;
				default:
					break;
				}
			}
		}
		Collections.sort(answerList, new Comparator<Answer>() {
			public int compare(Answer answer1, Answer answer2) {
				return answer1.getQuestionNumber().compareTo(
						answer2.getQuestionNumber());
			}

		});
		List<CurrentPriorNRSASupport.Level.Enum> levelList = new ArrayList<CurrentPriorNRSASupport.Level.Enum>();
		List<CurrentPriorNRSASupport.Type.Enum> typeList = new ArrayList<CurrentPriorNRSASupport.Type.Enum>();
		List<Calendar> startDateList = new ArrayList<Calendar>();
		List<Calendar> endDateList = new ArrayList<Calendar>();
		List<String> grantNumberList = new ArrayList<String>();
		for (Answer questionnaireAnswer : answerList) {
			if (nsrSupport != null && nsrSupport.equals(NSR_SUPPORT_YES)) {
				if (questionnaireAnswer.getQuestionNumber() == PRE_OR_POST) {
					levelList.add(CurrentPriorNRSASupport.Level.Enum
							.forString(questionnaireAnswer.getAnswer()));
				}
				if (questionnaireAnswer.getQuestionNumber() == IND_OR_INST) {
					typeList.add(CurrentPriorNRSASupport.Type.Enum
							.forString(questionnaireAnswer.getAnswer()));
				}
				if (questionnaireAnswer.getQuestionNumber() == KIRST_START_DT) {
					startDateList.add(s2sUtilService
							.convertDateStringToCalendar(questionnaireAnswer
									.getAnswer()));
				}
				if (questionnaireAnswer.getQuestionNumber() == KIRST_END_DT) {
					endDateList.add(s2sUtilService
							.convertDateStringToCalendar(questionnaireAnswer
									.getAnswer()));
				}
				if (questionnaireAnswer.getQuestionNumber() == KIRST_GRANT_NUM) {
					grantNumberList.add(questionnaireAnswer.getAnswer());
				}
			}
		}
		for (int index = 0; levelList.size() > index; index++) {
			CurrentPriorNRSASupport currentPriorNRSASupport = CurrentPriorNRSASupport.Factory
					.newInstance();
			currentPriorNRSASupport.setLevel(levelList.get(index));
			currentPriorNRSASupport.setType(typeList.get(index));
			currentPriorNRSASupport.setStartDate(startDateList.get(index));
			currentPriorNRSASupport.setEndDate(endDateList.get(index));
			currentPriorNRSASupport.setGrantNumber(grantNumberList.get(index));
			currentPriorNRSASupportList.add(currentPriorNRSASupport);
		}
		return currentPriorNRSASupportList
				.toArray(new CurrentPriorNRSASupport[currentPriorNRSASupportList
						.size()]);
	}

	/*
	 * This method is used to get AttachmentGroupMin0Max100DataType xmlObject
	 * and set data to it based on narrative type code
	 */
	private AttachmentGroupMin0Max100DataType getAppendix() {
		AttachmentGroupMin0Max100DataType attachmentGroupType = AttachmentGroupMin0Max100DataType.Factory
				.newInstance();
		List<AttachedFileDataType> attachedFileDataTypeList = new ArrayList<AttachedFileDataType>();
		AttachedFileDataType attachedFileDataType = null;
		for (Narrative narrative : pdDoc.getDevelopmentProposal()
				.getNarratives()) {
			if (narrative.getNarrativeTypeCode() != null
					&& Integer.parseInt(narrative.getNarrativeTypeCode()) == APPENDIX) {
				attachedFileDataType = getAttachedFileType(narrative);
				if(attachedFileDataType != null){
					attachedFileDataTypeList.add(attachedFileDataType);
				}
			}
		}
		attachmentGroupType.setAttachedFileArray(attachedFileDataTypeList
				.toArray(new AttachedFileDataType[attachedFileDataTypeList
						.size()]));
		return attachmentGroupType;
	}

	/*
	 * This method is used to get ApplicationType XMLObject and set data to it
	 * from types of Application.
	 */
	private ApplicationType getApplicationType() {
		ApplicationType applicationType = ApplicationType.Factory.newInstance();
		applicationType.setTypeOfApplication(getTypeOfApplication());
		return applicationType;
	}

	/*
	 * This method is used to get TypeOfApplication based on proposalTypeCode of
	 * DevelopmentProposal
	 */
	private TypeOfApplication.Enum getTypeOfApplication() {
		String proposalTypeCode = pdDoc.getDevelopmentProposal()
				.getProposalTypeCode();
		TypeOfApplication.Enum typeOfApplication = null;
		if (proposalTypeCode != null) {
			if (proposalTypeCode.equals(ProposalDevelopmentUtils.getProposalDevelopmentDocumentParameter(
                    ProposalDevelopmentUtils.PROPOSAL_TYPE_CODE_NEW_PARM))) {
				typeOfApplication = TypeOfApplication.NEW;
			} else if (proposalTypeCode.equals(ProposalDevelopmentUtils.getProposalDevelopmentDocumentParameter(
                    ProposalDevelopmentUtils.PROPOSAL_TYPE_CODE_CONTINUATION_PARM))) {
				typeOfApplication = TypeOfApplication.CONTINUATION;
			} else if (proposalTypeCode.equals(ProposalDevelopmentUtils.getProposalDevelopmentDocumentParameter(
                    ProposalDevelopmentUtils.PROPOSAL_TYPE_CODE_REVISION_PARM))) {
				typeOfApplication = TypeOfApplication.REVISION;
			} else if (proposalTypeCode.equals(ProposalDevelopmentUtils.getProposalDevelopmentDocumentParameter(
                    ProposalDevelopmentUtils.PROPOSAL_TYPE_CODE_RENEWAL_PARM))) {
				typeOfApplication = TypeOfApplication.RENEWAL;
			} else if (proposalTypeCode.equals(ProposalDevelopmentUtils.getProposalDevelopmentDocumentParameter(
                    ProposalDevelopmentUtils.PROPOSAL_TYPE_CODE_RESUBMISSION_PARM))) {
				typeOfApplication = TypeOfApplication.RESUBMISSION;
			} else if (proposalTypeCode.equals(PROPOSAL_TYPE_CODE_NEW7)) {
				typeOfApplication = TypeOfApplication.NEW;
			}
		}
		return typeOfApplication;
	}

	/*
	 * 
	 * This method computes the number of months between any 2 given
	 * {@link Date} objects
	 * 
	 * @param dateStart starting date. @param dateEnd end date.
	 * 
	 * @return number of months between the start date and end date.
	 */
	private BudgetDecimal getNumberOfMonths(Date dateStart, Date dateEnd) {
		BudgetDecimal monthCount = BudgetDecimal.ZERO;
		int fullMonthCount = 0;

		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		startDate.setTime(dateStart);
		endDate.setTime(dateEnd);

		startDate.clear(Calendar.HOUR);
		startDate.clear(Calendar.MINUTE);
		startDate.clear(Calendar.SECOND);
		startDate.clear(Calendar.MILLISECOND);

		endDate.clear(Calendar.HOUR);
		endDate.clear(Calendar.MINUTE);
		endDate.clear(Calendar.SECOND);
		endDate.clear(Calendar.MILLISECOND);

		if (startDate.after(endDate)) {
			return BudgetDecimal.ZERO;
		}
		int startMonthDays = startDate.getActualMaximum(Calendar.DATE)
				- startDate.get(Calendar.DATE);
		startMonthDays++;
		int startMonthMaxDays = startDate.getActualMaximum(Calendar.DATE);
		BudgetDecimal startMonthFraction = new BudgetDecimal(startMonthDays)
				.divide(new BudgetDecimal(startMonthMaxDays));

		int endMonthDays = endDate.get(Calendar.DATE);
		int endMonthMaxDays = endDate.getActualMaximum(Calendar.DATE);

		BudgetDecimal endMonthFraction = new BudgetDecimal(endMonthDays)
				.divide(new BudgetDecimal(endMonthMaxDays));

		startDate.set(Calendar.DATE, 1);
		endDate.set(Calendar.DATE, 1);

		while (startDate.getTimeInMillis() < endDate.getTimeInMillis()) {
			startDate.set(Calendar.MONTH, startDate.get(Calendar.MONTH) + 1);
			fullMonthCount++;
		}
		fullMonthCount = fullMonthCount - 1;
		monthCount = monthCount.add(new BudgetDecimal(fullMonthCount)).add(
				startMonthFraction).add(endMonthFraction);
		return monthCount;
	}

	/**
	 * This method creates {@link XmlObject} of type
	 * {@link PHSFellowshipSupplementalDocument} by populating data from the
	 * given {@link ProposalDevelopmentDocument}
	 * 
	 * @param proposalDevelopmentDocument
	 *            for which the {@link XmlObject} needs to be created
	 * @return {@link XmlObject} which is generated using the given
	 *         {@link ProposalDevelopmentDocument}
	 * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(ProposalDevelopmentDocument)
	 */
	public XmlObject getFormObject(
			ProposalDevelopmentDocument proposalDevelopmentDocument) {
		this.pdDoc = proposalDevelopmentDocument;
		return getPHSFellowshipSupplemental12();
	}

    public String getFormName() {
        return "PHS_Fellowship_Supplemental_1_2-V1.2";
    }

    public String getNamespace() {
        return "http://apply.grants.gov/forms/PHS_Fellowship_Supplemental_1_2-V1.2";
    }
}
