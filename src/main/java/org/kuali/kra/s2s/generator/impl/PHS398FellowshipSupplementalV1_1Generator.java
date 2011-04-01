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

import gov.grants.apply.forms.phsFellowshipSupplemental11V11.CitizenshipDataType;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.DegreeTypeDataType;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.FieldOfTrainingDataType;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.AdditionalInformation;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.ApplicationType;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.Budget;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.ResearchTrainingPlan;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.AdditionalInformation.ActivitiesPlannedUnderThisAward;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.AdditionalInformation.ConcurrentSupportDescription;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.AdditionalInformation.CurrentPriorNRSASupport;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.AdditionalInformation.DissertationAndResearchExperience;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.AdditionalInformation.FellowshipTrainingAndCareerGoals;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.AdditionalInformation.GraduateDegreeSought;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.AdditionalInformation.StemCells;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.ApplicationType.TypeOfApplication;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.Budget.FederalStipendRequested;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.Budget.InstitutionalBaseSalary;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.Budget.SupplementationFromOtherSources;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.Budget.InstitutionalBaseSalary.AcademicPeriod;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.ResearchTrainingPlan.InclusionEnrollmentReport;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.ResearchTrainingPlan.InclusionOfChildren;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.ResearchTrainingPlan.InclusionOfWomenAndMinorities;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.ResearchTrainingPlan.IntroductionToApplication;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.ResearchTrainingPlan.ProgressReportPublicationList;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.ResearchTrainingPlan.ProtectionOfHumanSubjects;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.ResearchTrainingPlan.ResearchStrategy;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.ResearchTrainingPlan.ResourceSharingPlan;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.ResearchTrainingPlan.RespectiveContributions;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.ResearchTrainingPlan.ResponsibleConductOfResearch;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.ResearchTrainingPlan.SelectAgentResearch;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.ResearchTrainingPlan.SelectionOfSponsorAndInstitution;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.ResearchTrainingPlan.SpecificAims;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.ResearchTrainingPlan.TargetedPlannedEnrollment;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.ResearchTrainingPlan.VertebrateAnimals;
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
import org.kuali.kra.proposaldevelopment.ProposalDevelopmentUtils;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.specialreview.ProposalSpecialReview;
import org.kuali.kra.questionnaire.answer.Answer;
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
public class PHS398FellowshipSupplementalV1_1Generator extends
		PHS398FellowshipSupplementalBaseGenerator {

	private static final Log LOG = LogFactory
			.getLog(PHS398FellowshipSupplementalV1_1Generator.class);

	/*
	 * This method is used to get PHSFellowshipSupplemental11 XMLObject and set
	 * the data to it from DevelopmentProposal data.
	 */
	private PHSFellowshipSupplemental11 getPHSFellowshipSupplemental11() {
		PHSFellowshipSupplemental11 phsFellowshipSupplemental = PHSFellowshipSupplemental11.Factory
				.newInstance();
		phsFellowshipSupplemental.setFormVersion(S2SConstants.FORMVERSION_1_1);
		phsFellowshipSupplemental.setApplicationType(getApplicationType());
		phsFellowshipSupplemental.setAppendix(getAppendix());
		phsFellowshipSupplemental.setAdditionalInformation(getAdditionalInformation());
		phsFellowshipSupplemental
				.setResearchTrainingPlan(getResearchTrainingPlan());
		phsFellowshipSupplemental.setBudget(getBudget());
		return phsFellowshipSupplemental;
	}

	/*
	 * This method is used to get Budget XMLObject and set the data to it from
	 * ProposalYnq based on questionId and answers.
	 */
	private Budget getBudget() {
		Budget budget = Budget.Factory.newInstance();
		Map<Integer, String> budgetMap = new HashMap<Integer, String>();

		for (Answer questionnaireAnswer : s2sUtilService.getQuestionnaireAnswers(pdDoc.getDevelopmentProposal(),getNamespace(),getFormName())) {
			String answer = questionnaireAnswer.getAnswer();
			if (answer != null) {
				switch (questionnaireAnswer.getQuestionNumber()) {
				case SENIOR:
					budgetMap.put(SENIOR, answer);
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
		budget.setTuitionAndFeesRequested(YesNoDataType.N_NO);
		if(getInstitutionalBaseSalary(budgetMap)!=null){
		    budget.setInstitutionalBaseSalary(getInstitutionalBaseSalary(budgetMap));
		}
		budget.setFederalStipendRequested(getFederalStipendRequested());
		if(getSupplementationFromOtherSources(budgetMap)!=null){
		    budget.setSupplementationFromOtherSources(getSupplementationFromOtherSources(budgetMap));
		}
		setTutionRequestedYears(budget);
		return budget;
	}

	/*
	 * This method is used to get TutionRequestedYears data to Budget XMLObject
	 * from List of BudgetLineItem based on CostElement value of
	 * TUITION_COST_ELEMENTS
	 */
	private void setTutionRequestedYears(Budget budget) {
		BudgetDocument budgetDoc = getBudgetDocument();
		if (budgetDoc == null) {
			return;
		}
		BudgetDecimal tutionTotal = BudgetDecimal.ZERO;
		for (BudgetPeriod budgetPeriod : budgetDoc.getBudget()
				.getBudgetPeriods()) {
			BudgetDecimal tution = BudgetDecimal.ZERO;
			for (BudgetLineItem budgetLineItem : budgetPeriod
					.getBudgetLineItems()) {
				if (budgetLineItem.getCostElementBO().getCostElement().equals(
						TUITION_COST_ELEMENTS)) {
					tution = tution.add(budgetLineItem.getLineItemCost());
				}
			}
			tutionTotal = tutionTotal.add(tution);
			switch (budgetPeriod.getBudgetPeriod()) {
			case 1:
				budget.setTuitionRequestedYear1(tution.bigDecimalValue());
				break;
			case 2:
				budget.setTuitionRequestedYear2(tution.bigDecimalValue());
				break;
			case 3:
				budget.setTuitionRequestedYear3(tution.bigDecimalValue());
				break;
			case 4:
				budget.setTuitionRequestedYear4(tution.bigDecimalValue());
				break;
			case 5:
				budget.setTuitionRequestedYear5(tution.bigDecimalValue());
				break;
			case 6:
				budget.setTuitionRequestedYear6(tution.bigDecimalValue());
				break;
			default:
				break;
			}
		}
		budget.setTuitionRequestedTotal(tutionTotal.bigDecimalValue());
		if (!tutionTotal.equals(BudgetDecimal.ZERO)) {
			budget.setTuitionAndFeesRequested(YesNoDataType.Y_YES);
		}
	}

	/*
	 * This method is used to set data to SupplementationFromOtherSources
	 * XMLObject from budgetMap data for Budget
	 */
	private SupplementationFromOtherSources getSupplementationFromOtherSources(
			Map<Integer, String> budgetMap) {
	    SupplementationFromOtherSources supplementationFromOtherSources=null;
		if (budgetMap.get(SENIOR) != null
				&& budgetMap.get(SENIOR).toString().equals(
						S2SConstants.PROPOSAL_YNQ_ANSWER_Y)) {
	        supplementationFromOtherSources = SupplementationFromOtherSources.Factory.newInstance();
			if (budgetMap.get(SUPP_SOURCE) != null) {
				supplementationFromOtherSources.setSource(budgetMap.get(
						SUPP_SOURCE).toString());
			}
			if (budgetMap.get(SUPP_FUNDING_AMT) != null) {
				supplementationFromOtherSources.setAmount(new BigDecimal(
						budgetMap.get(SUPP_FUNDING_AMT).toString()));
			}else{
			    supplementationFromOtherSources.setAmount(null);
			}
			if (budgetMap.get(SUPP_MONTHS) != null) {
				supplementationFromOtherSources
						.setNumberOfMonths(new BigDecimal(budgetMap.get(
								SUPP_MONTHS).toString()));
			}
			if (budgetMap.get(SUPP_TYPE) != null) {
				supplementationFromOtherSources.setType(budgetMap
						.get(SUPP_TYPE).toString());
			}
		}
		return supplementationFromOtherSources;
	}

	/*
	 * This method is used to get FederalStipendRequested XMLObject and set
	 * additional information data to it.
	 */
	private FederalStipendRequested getFederalStipendRequested() {
		FederalStipendRequested federalStipendRequested = FederalStipendRequested.Factory
				.newInstance();
		BudgetDocument budgetDoc = getBudgetDocument();
		if (budgetDoc == null) {
			return federalStipendRequested;
		}
		org.kuali.kra.budget.core.Budget budget = budgetDoc.getBudget();
		BudgetDecimal sumOfLineItemCost = BudgetDecimal.ZERO;
		BudgetDecimal numberOfMonths = BudgetDecimal.ZERO;
		for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
			if (budgetPeriod.getBudgetPeriod() == 1) {
				for (BudgetLineItem budgetLineItem : budgetPeriod
						.getBudgetLineItems()) {
					if (budgetLineItem.getCostElementBO().getCostElement()
							.equals(STIPEND_COST_ELEMENTS)) {
						sumOfLineItemCost = sumOfLineItemCost
								.add(budgetLineItem.getLineItemCost());
						numberOfMonths = numberOfMonths.add(getNumberOfMonths(
								budgetLineItem.getStartDate(), budgetLineItem
										.getEndDate()));
					}
				}
			}
		}
		federalStipendRequested.setAmount(sumOfLineItemCost.bigDecimalValue());
		federalStipendRequested.setNumberOfMonths(numberOfMonths
				.bigDecimalValue());
		return federalStipendRequested;
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
	private InstitutionalBaseSalary getInstitutionalBaseSalary(
			Map<Integer, String> budgetMap) {
	    InstitutionalBaseSalary institutionalBaseSalary=null;
		if (budgetMap.get(SENIOR) != null
				&& budgetMap.get(SENIOR).toString().equals(
						S2SConstants.PROPOSAL_YNQ_ANSWER_Y)) {
	        institutionalBaseSalary = InstitutionalBaseSalary.Factory.newInstance();
			if (budgetMap.get(BASE_SALARY) != null) {
				institutionalBaseSalary.setAmount(new BigDecimal(budgetMap.get(
						BASE_SALARY).toString()));
			}else{
			    institutionalBaseSalary.setAmount(null);
			}
			if (budgetMap.get(ACAD_PERIOD) != null) {
				institutionalBaseSalary.setAcademicPeriod(AcademicPeriod.Enum
						.forString(budgetMap.get(ACAD_PERIOD).toString()));
			}
			if (budgetMap.get(SALARY_MONTH) != null) {
				institutionalBaseSalary.setNumberOfMonths(new BigDecimal(
						budgetMap.get(SALARY_MONTH).toString()));
			}
		}
		return institutionalBaseSalary;
	}

	/*
	 * This method used to set data to ResearchTrainingPlan XMLObject from
	 * DevelopmentProposal
	 */
	private ResearchTrainingPlan getResearchTrainingPlan() {
		ResearchTrainingPlan researchTrainingPlan = ResearchTrainingPlan.Factory
				.newInstance();
		setHumanSubjectInvolvedAndVertebrateAnimalUsed(researchTrainingPlan);
		setQuestionnareAnswerForResearchTrainingPlan(researchTrainingPlan);
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
				default:
					break;
				}
			}
		}
	}

	/**
	 * This method is used to set QuestionnareAnswer data to
	 * ResearchTrainingPlan XMLObject
	 * 
	 * @param researchTrainingPlan
	 */
	private void setQuestionnareAnswerForResearchTrainingPlan(
			ResearchTrainingPlan researchTrainingPlan) {
	    researchTrainingPlan.setHumanSubjectsIndefinite(YesNoDataType.N_NO);
        researchTrainingPlan.setVertebrateAnimalsIndefinite(YesNoDataType.N_NO);
        researchTrainingPlan.setHumanSubjectsIndefinite(YesNoDataType.N_NO);
        researchTrainingPlan.setClinicalTrial(YesNoDataType.N_NO);
        researchTrainingPlan.setPhase3ClinicalTrial(YesNoDataType.N_NO);
		for (Answer questionnaireAnswer : s2sUtilService.getQuestionnaireAnswers(pdDoc.getDevelopmentProposal(), getNamespace(),getFormName())) {
			String answer = questionnaireAnswer.getAnswer();
			if (answer != null) {
				switch (questionnaireAnswer.getQuestionnaireQuestion().getQuestion().getQuestionId()) {
				case HUMAN:
					researchTrainingPlan
							.setHumanSubjectsIndefinite(answer
									.equals(S2SConstants.PROPOSAL_YNQ_ANSWER_Y) ? YesNoDataType.Y_YES
									: YesNoDataType.N_NO);
					break;
				case VERT:
					researchTrainingPlan
							.setVertebrateAnimalsIndefinite(answer
									.equals(S2SConstants.PROPOSAL_YNQ_ANSWER_Y) ? YesNoDataType.Y_YES
									: YesNoDataType.N_NO);
					break;
				case CLINICAL:
					researchTrainingPlan
							.setClinicalTrial(answer
									.equals(S2SConstants.PROPOSAL_YNQ_ANSWER_Y) ? YesNoDataType.Y_YES
									: YesNoDataType.N_NO);
					break;
				case PHASE3CLINICAL:
					researchTrainingPlan
							.setPhase3ClinicalTrial(answer
									.equals(S2SConstants.PROPOSAL_YNQ_ANSWER_Y) ? YesNoDataType.Y_YES
									: YesNoDataType.N_NO);
					break;
				default:
					break;
				}
			}
		}
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
		researchTrainingPlan.setVertebrateAnimalsUsed(YesNoDataType.N_NO);
		for (ProposalSpecialReview propSpecialReview : pdDoc
				.getDevelopmentProposal().getPropSpecialReviews()) {
			switch (Integer.parseInt(propSpecialReview.getSpecialReviewTypeCode())) {
			case 1:
				researchTrainingPlan
						.setHumanSubjectsInvolved(YesNoDataType.Y_YES);
				break;
			case 2:
				researchTrainingPlan
						.setVertebrateAnimalsUsed(YesNoDataType.Y_YES);
				break;
			default:
				break;
			}
		}
	}

	/*
	 * This method is used to set additional information data to
	 * AdditionalInformation XMLObject from DevelopmentProposal, ProposalYnq
	 */
	private AdditionalInformation getAdditionalInformation() {
		AdditionalInformation additionalInformation = AdditionalInformation.Factory.newInstance();
		StemCells stemCells = StemCells.Factory.newInstance();
	    stemCells.setIsHumanStemCellsInvolved(YesNoDataType.N_NO);
	    stemCells.setStemCellsIndicator(YesNoDataType.N_NO);
		GraduateDegreeSought graduateDegreeSought = GraduateDegreeSought.Factory.newInstance();
		ProposalPerson principalInvestigator = s2sUtilService.getPrincipalInvestigator(pdDoc);
		if (principalInvestigator != null) {
			if (principalInvestigator.getCountryOfCitizenship() != null && !principalInvestigator.getCountryOfCitizenship().equals(""))  {
				additionalInformation.setCitizenship(
				        CitizenshipDataType.Enum.forString(principalInvestigator.getCountryOfCitizenship()));
			}else{
			    additionalInformation.setCitizenship(CitizenshipDataType.PERMANENT_RESIDENT_OF_U_S);
			}
			additionalInformation.setAlernatePhoneNumber(principalInvestigator.getSecondaryOfficePhone());
		}
		for (Answer questionnaireAnswer : s2sUtilService.getQuestionnaireAnswers(pdDoc.getDevelopmentProposal(), getNamespace(),getFormName())) {
			String answer = questionnaireAnswer.getAnswer();
			if (answer != null) {
				switch (questionnaireAnswer.getQuestionnaireQuestion().getQuestion().getQuestionId()) {
				case FIELD_TRAINING:
					if (!answer.toUpperCase().equals(SUB_CATEGORY_NOT_FOUND)) {
					    FieldOfTrainingDataType.Enum e = FieldOfTrainingDataType.Enum.forString(answer);
						additionalInformation.setFieldOfTraining(e);
					}
					break;
				case NRSA_SUPPORT:
					additionalInformation
							.setCurrentPriorNRSASupportIndicator(answer
									.equals(S2SConstants.PROPOSAL_YNQ_ANSWER_Y) ? YesNoDataType.Y_YES
									: YesNoDataType.N_NO);
					break;
				case SUBMITTED_DIFF_INST:
					additionalInformation
							.setChangeOfInstitution(answer
									.equals(S2SConstants.PROPOSAL_YNQ_ANSWER_Y) ? YesNoDataType.Y_YES
									: YesNoDataType.N_NO);
					break;
				case FORMER_INST:
					additionalInformation.setFormerInstitution(answer);
					break;
				case STEMCELLS:
					stemCells
							.setIsHumanStemCellsInvolved(answer
									.equals(S2SConstants.PROPOSAL_YNQ_ANSWER_Y) ? YesNoDataType.Y_YES
									: YesNoDataType.N_NO);
					break;
				case CELLLINEIND:
					stemCells.setStemCellsIndicator(answer
									.equals(S2SConstants.PROPOSAL_YNQ_ANSWER_Y) ? YesNoDataType.Y_YES
									: YesNoDataType.N_NO);
					break;
				case STEMCELLLINES:
					List<String> cellLinesList = Arrays.asList(stemCells
							.getCellLinesArray());
					cellLinesList.add(answer);
					stemCells.setCellLinesArray((String[]) cellLinesList
							.toArray());
					break;
				case DEGREE_TYPE_SOUGHT:
					graduateDegreeSought.setDegreeType(DegreeTypeDataType.Enum
							.forString(answer));
					break;
				case DEG_EXP_COMP_DATE:
					graduateDegreeSought.setDegreeDate(answer.substring(6, 10)
							+ STRING_SEPRATOR + answer.substring(0, 2));
					break;
				case OTHER_MASTERS:
				case OTHER_DOCT:
				case OTHER_DDOT:
				case OTHER_VDOT:
				case OTHER_DBOTH:
				case OTHER_MDOT:
					graduateDegreeSought.setOtherDegreeTypeText(answer);
					break;
				default:
					break;
				}
			}
		}
		additionalInformation.setStemCells(stemCells);
		if(graduateDegreeSought.getDegreeType()!=null){
		    additionalInformation.setGraduateDegreeSought(graduateDegreeSought);
		}
		additionalInformation
				.setCurrentPriorNRSASupportArray(getCurrentPriorNRSASupportArray());
		additionalInformation.setConcurrentSupport(YesNoDataType.N_NO);
		AttachedFileDataType attachedFileDataType = null;
		for (Narrative narrative : pdDoc.getDevelopmentProposal()
				.getNarratives()) {
			if (narrative.getNarrativeTypeCode() != null) {
				switch (Integer.parseInt(narrative.getNarrativeTypeCode())) {
				case CONCURRENT_SUPPORT:
	                attachedFileDataType = getAttachedFileType(narrative);
	                if(attachedFileDataType == null){
	                    continue;
	                }
					ConcurrentSupportDescription concurrentSupportDescription = ConcurrentSupportDescription.Factory
							.newInstance();
					concurrentSupportDescription
							.setAttFile(attachedFileDataType);
					additionalInformation
							.setConcurrentSupport(YesNoDataType.Y_YES);
					additionalInformation
							.setConcurrentSupportDescription(concurrentSupportDescription);
					break;
				case FELLOWSHIP:
	                attachedFileDataType = getAttachedFileType(narrative);
	                if(attachedFileDataType == null){
	                    continue;
	                }
					FellowshipTrainingAndCareerGoals fellowshipTrainingAndCareerGoals = FellowshipTrainingAndCareerGoals.Factory
							.newInstance();
					fellowshipTrainingAndCareerGoals
							.setAttFile(attachedFileDataType);
					additionalInformation
							.setFellowshipTrainingAndCareerGoals(fellowshipTrainingAndCareerGoals);
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
					ActivitiesPlannedUnderThisAward activitiesPlannedUnderThisAward = ActivitiesPlannedUnderThisAward.Factory
							.newInstance();
					activitiesPlannedUnderThisAward
							.setAttFile(attachedFileDataType);
					additionalInformation
							.setActivitiesPlannedUnderThisAward(activitiesPlannedUnderThisAward);
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
        PHSFellowshipSupplemental11 phsFellowshipSupplemental = getPHSFellowshipSupplemental11();
        PHSFellowshipSupplemental11Document phsFellowshipSupplementalDocument = PHSFellowshipSupplemental11Document.Factory
                .newInstance();
        phsFellowshipSupplementalDocument
                .setPHSFellowshipSupplemental11(phsFellowshipSupplemental);
        return phsFellowshipSupplementalDocument;
	}

	/**
	 * This method typecasts the given {@link XmlObject} to the required
	 * generator type and returns back the document of that generator type.
	 * 
	 * @param xmlObject
	 *            which needs to be converted to the document type of the
	 *            required generator
	 * @return {@link XmlObject} document of the required generator type
	 * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(XmlObject)
	 */
	public XmlObject getFormObject(XmlObject xmlObject) {
		PHSFellowshipSupplemental11 phsFellowshipSupplemental = (PHSFellowshipSupplemental11) xmlObject;
		PHSFellowshipSupplemental11Document phsFellowshipSupplementalDocument = PHSFellowshipSupplemental11Document.Factory
				.newInstance();
		phsFellowshipSupplementalDocument
				.setPHSFellowshipSupplemental11(phsFellowshipSupplemental);
		return phsFellowshipSupplementalDocument;
	}

    public String getFormName() {
        return "PHS_Fellowship_Supplemental_1_1-V1.1";
    }

    public String getNamespace() {
        return "http://apply.grants.gov/forms/PHS_Fellowship_Supplemental_1_1-V1.1";
    }
}
