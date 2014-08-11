/*
 * Copyright 2005-2014 The Kuali Foundation.
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
package org.kuali.coeus.s2sgen.impl.generate.support;

import gov.grants.apply.forms.phsFellowshipSupplemental11V11.CitizenshipDataType;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.DegreeTypeDataType;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.FieldOfTrainingDataType;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.AdditionalInformation;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.AdditionalInformation.*;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.ApplicationType;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.ApplicationType.TypeOfApplication;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.Budget;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.Budget.FederalStipendRequested;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.Budget.InstitutionalBaseSalary;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.Budget.InstitutionalBaseSalary.AcademicPeriod;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.Budget.SupplementationFromOtherSources;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.ResearchTrainingPlan;
import gov.grants.apply.forms.phsFellowshipSupplemental11V11.PHSFellowshipSupplemental11Document.PHSFellowshipSupplemental11.ResearchTrainingPlan.*;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.attachmentsV10.AttachmentGroupMin0Max100DataType;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.api.ynq.YnqConstant;
import org.kuali.coeus.common.budget.api.nonpersonnel.BudgetLineItemContract;
import org.kuali.coeus.common.budget.api.period.BudgetPeriodContract;
import org.kuali.coeus.common.questionnaire.api.answer.AnswerContract;
import org.kuali.coeus.common.questionnaire.api.answer.AnswerHeaderContract;
import org.kuali.coeus.common.questionnaire.api.core.QuestionnaireContract;
import org.kuali.coeus.common.questionnaire.api.core.QuestionnaireQuestionContract;
import org.kuali.coeus.common.questionnaire.api.question.QuestionContract;
import org.kuali.coeus.propdev.api.budget.ProposalDevelopmentBudgetExtContract;
import org.kuali.coeus.propdev.api.core.DevelopmentProposalContract;
import org.kuali.coeus.propdev.api.person.ProposalPersonContract;
import org.kuali.coeus.propdev.api.specialreview.ProposalSpecialReviewContract;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;

import org.kuali.coeus.s2sgen.impl.util.FieldValueConstants;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.s2sgen.impl.citizenship.CitizenshipType;
import org.kuali.coeus.propdev.api.attachment.NarrativeContract;
import org.kuali.coeus.s2sgen.api.core.ConfigurationConstants;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;


import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * 
 * Class for generating the XML object for grants.gov
 * PHS398FellowshipSupplementalV1_1 Form is generated using XMLBean classes and
 * is based on PHS398FellowshipSupplementalV1_1 schema
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@FormGenerator("PHS398FellowshipSupplementalV1_1Generator")
public class PHS398FellowshipSupplementalV1_1Generator extends
		PHS398FellowshipSupplementalBaseGenerator {

	private static final Log LOG = LogFactory
			.getLog(PHS398FellowshipSupplementalV1_1Generator.class);
    private static final int HUMAN = 1;
    private static final int VERT = 4;
    private static final int CLINICAL = 2;
    private static final int PHASE3CLINICAL = 3;
    private static final int STEMCELLS = 5;
    private static final int KIRST_START_KNOWN = 43;
    private static final int KIRST_END_KNOWN = 49;
    private static final int KIRST_START_DT = 44;
    private static final int KIRST_END_DT = 45;
    private static final int KIRST_GRANT_KNOWN = 46;
    private static final int KIRST_GRANT_NUM = 27;
    private static final int PRE_OR_POST = 32;
    private static final int IND_OR_INST = 33;
    private static final int STEMCELLLINES = 7;
    private static final int CELLLINEIND = 6;
    private static final int  DEGREE_TYPE_SOUGHT =15;
    private static final int DEG_EXP_COMP_DATE = 35;
    private static final int NRSA_SUPPORT = 24;
    private static final int FIELD_TRAINING = 22;
    private static final int BROAD_TRAINING = 23;
    private static final int OTHER_MASTERS=16;
    private static final int  OTHER_DOCT=17;
    private static final int  OTHER_DDOT=18;
    private static final int OTHER_VDOT=19;
    private static final int  OTHER_DBOTH=20;
    private static final int   OTHER_MDOT=21;
    private static final int SUBMITTED_DIFF_INST=28;
    private static final int FORMER_INST=29;
    private static final int SENIOR_FELL = 36;
    private static final int OTHER_SUPP_SOURCE = 37;
    private static final int SUPP_FUNDING_AMT = 38;
    private static final int SUPP_MONTHS = 51;
    private static final int  SUPP_SOURCE = 41;
    private static final int  SUPP_TYPE = 40;

    private static final int SALARY_MONTH = 50;
    private static final int ACAD_PERIOD = 48;
    private static final int BASE_SALARY = 47;
    
    private static final int APPENDIX = 96;

    @Value("http://apply.grants.gov/forms/PHS_Fellowship_Supplemental_1_1-V1.1")
    private String namespace;

    @Value("PHS_Fellowship_Supplemental_1_1-V1.1")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/PHS_fellowship_supplemental-V1.1.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.phsFellowshipSupplemental11V11")
    private String packageName;

    @Value("210")
    private int sortIndex;
    
	/*
	 * This method is used to get PHSFellowshipSupplemental11 XMLObject and set
	 * the data to it from DevelopmentProposal data.
	 */
	private PHSFellowshipSupplemental11 getPHSFellowshipSupplemental11() {
		PHSFellowshipSupplemental11 phsFellowshipSupplemental = PHSFellowshipSupplemental11.Factory
				.newInstance();
		phsFellowshipSupplemental.setFormVersion(FormVersion.v1_1.getVersion());
		phsFellowshipSupplemental.setApplicationType(getApplicationType());
		phsFellowshipSupplemental.setAppendix(getAppendix());
		phsFellowshipSupplemental.setAdditionalInformation(getAdditionalInformation());
		phsFellowshipSupplemental
				.setResearchTrainingPlan(getResearchTrainingPlan());
		phsFellowshipSupplemental.setBudget(getBudget());
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(phsFellowshipSupplemental.toString().getBytes());            
        sortAttachments(byteArrayInputStream);
		return phsFellowshipSupplemental;
	}

	/*
	 * This method is used to get Budget XMLObject and set the data to it from
	 * ProposalYnq based on questionId and answers.
	 */
	private Budget getBudget() {
		Budget budget = Budget.Factory.newInstance();
		Map<Integer, String> budgetMap = new HashMap<Integer, String>();

		for (AnswerContract questionnaireAnswer : getPropDevQuestionAnswerService().getQuestionnaireAnswers(pdDoc.getDevelopmentProposal().getProposalNumber(), getNamespace(), getFormName())) {
			String answer = questionnaireAnswer.getAnswer();
			if (answer != null) {
				switch (getQuestionAnswerService().findQuestionById(questionnaireAnswer.getQuestionId()).getQuestionSeqId()) {
				case SENIOR_FELL:
					budgetMap.put(SENIOR_FELL, answer);
					break;
                case OTHER_SUPP_SOURCE:
                    budgetMap.put(OTHER_SUPP_SOURCE, answer);
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
        ProposalDevelopmentBudgetExtContract budgetEx = s2SCommonBudgetService.getBudget(pdDoc.getDevelopmentProposal());
		if (budgetEx == null) {
			return;
		}
		ScaleTwoDecimal tutionTotal = ScaleTwoDecimal.ZERO;
		for (BudgetPeriodContract budgetPeriod : budgetEx
				.getBudgetPeriods()) {
			ScaleTwoDecimal tution = ScaleTwoDecimal.ZERO;
			for (BudgetLineItemContract budgetLineItem : budgetPeriod.getBudgetLineItems()) {
				if (getCostElementsByParam(ConfigurationConstants.TUITION_COST_ELEMENTS).contains(budgetLineItem.getCostElementBO().getCostElement())) {
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
		if (!tutionTotal.equals(ScaleTwoDecimal.ZERO)) {
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
		if (budgetMap.get(OTHER_SUPP_SOURCE) != null
				&& budgetMap.get(OTHER_SUPP_SOURCE).equals(
						YnqConstant.YES.code())) {
	        supplementationFromOtherSources = SupplementationFromOtherSources.Factory.newInstance();
			if (budgetMap.get(SUPP_SOURCE) != null) {
				supplementationFromOtherSources.setSource(budgetMap.get(
						SUPP_SOURCE));
			}
			if (budgetMap.get(SUPP_FUNDING_AMT) != null) {
				supplementationFromOtherSources.setAmount(new BigDecimal(
						budgetMap.get(SUPP_FUNDING_AMT)));
			}else{
			    supplementationFromOtherSources.setAmount(null);
			}
			if (budgetMap.get(SUPP_MONTHS) != null) {
				supplementationFromOtherSources
						.setNumberOfMonths(new BigDecimal(budgetMap.get(
								SUPP_MONTHS)));
			}
			if (budgetMap.get(SUPP_TYPE) != null) {
				supplementationFromOtherSources.setType(budgetMap
						.get(SUPP_TYPE));
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
        ProposalDevelopmentBudgetExtContract budget = s2SCommonBudgetService.getBudget(pdDoc.getDevelopmentProposal());
		if (budget == null) {
			return federalStipendRequested;
		}
		ScaleTwoDecimal sumOfLineItemCost = ScaleTwoDecimal.ZERO;
		ScaleTwoDecimal numberOfMonths = ScaleTwoDecimal.ZERO;
		for (BudgetPeriodContract budgetPeriod : budget.getBudgetPeriods()) {
			if (budgetPeriod.getBudgetPeriod() == 1) {
				for (BudgetLineItemContract budgetLineItem : budgetPeriod.getBudgetLineItems()) {
					if (getCostElementsByParam(ConfigurationConstants.STIPEND_COST_ELEMENTS).contains(budgetLineItem.getCostElementBO().getCostElement())) {
						sumOfLineItemCost = sumOfLineItemCost.add(budgetLineItem.getLineItemCost());
						numberOfMonths = numberOfMonths.add(getNumberOfMonths(budgetLineItem.getStartDate(), budgetLineItem.getEndDate()));
					}
				}
			}
		}
		federalStipendRequested.setAmount(sumOfLineItemCost.bigDecimalValue());
		federalStipendRequested.setNumberOfMonths(numberOfMonths.bigDecimalValue());
		return federalStipendRequested;
	}

	/*
	 * This method is used to set data to InstitutionalBaseSalary XMLObject from
	 * budgetMap data for Budget
	 */
	private InstitutionalBaseSalary getInstitutionalBaseSalary(
			Map<Integer, String> budgetMap) {
	    InstitutionalBaseSalary institutionalBaseSalary=null;
		if (budgetMap.get(SENIOR_FELL) != null
				&& budgetMap.get(SENIOR_FELL).equals(
						YnqConstant.YES.code())) {
	        institutionalBaseSalary = InstitutionalBaseSalary.Factory.newInstance();
			if (budgetMap.get(BASE_SALARY) != null) {
				institutionalBaseSalary.setAmount(new BigDecimal(budgetMap.get(
						BASE_SALARY)));
			}else{
			    institutionalBaseSalary.setAmount(null);
			}
			if (budgetMap.get(ACAD_PERIOD) != null) {
				institutionalBaseSalary.setAcademicPeriod(AcademicPeriod.Enum
						.forString(budgetMap.get(ACAD_PERIOD)));
			}
			if (budgetMap.get(SALARY_MONTH) != null) {
				institutionalBaseSalary.setNumberOfMonths(new BigDecimal(
						budgetMap.get(SALARY_MONTH)));
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
        SpecificAims specificAims = SpecificAims.Factory.newInstance();
        ResearchStrategy researchStrategy = ResearchStrategy.Factory.newInstance();
        RespectiveContributions respectiveContributions = RespectiveContributions.Factory.newInstance();
        SelectionOfSponsorAndInstitution selectionOfSponsorAndInstitution = SelectionOfSponsorAndInstitution.Factory.newInstance();
        ResponsibleConductOfResearch responsibleConductOfResearch = ResponsibleConductOfResearch.Factory.newInstance();

		for (NarrativeContract narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
			if (narrative.getNarrativeType().getCode() != null) {
				switch (Integer.parseInt(narrative.getNarrativeType().getCode())) {
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
					specificAims.setAttFile(attachedFileDataType);
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
					respectiveContributions.setAttFile(attachedFileDataType);
					break;
				case SELECTION_OF_SPONSOR_AND_INSTITUTION:
	                attachedFileDataType = getAttachedFileType(narrative);
	                if(attachedFileDataType == null){
	                    continue;
	                }
					selectionOfSponsorAndInstitution
							.setAttFile(attachedFileDataType);
					break;
				case RESPONSIBLE_CONDUCT_OF_RESEARCH:
	                attachedFileDataType = getAttachedFileType(narrative);
	                if(attachedFileDataType == null){
	                    continue;
	                }
					responsibleConductOfResearch.setAttFile(attachedFileDataType);
					break;
				case RESEARCH_STRATEGY:
	                attachedFileDataType = getAttachedFileType(narrative);
	                if(attachedFileDataType == null){
	                    continue;
	                }
					researchStrategy.setAttFile(attachedFileDataType);
					break;
				default:
					break;
				}
			}
		}
        researchTrainingPlan.setSpecificAims(specificAims);
        researchTrainingPlan.setResearchStrategy(researchStrategy);
        researchTrainingPlan.setRespectiveContributions(respectiveContributions);
        researchTrainingPlan.setSelectionOfSponsorAndInstitution(selectionOfSponsorAndInstitution);
        researchTrainingPlan.setResponsibleConductOfResearch(responsibleConductOfResearch);

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
		for (AnswerContract questionnaireAnswer : getPropDevQuestionAnswerService().getQuestionnaireAnswers(pdDoc.getDevelopmentProposal().getProposalNumber(), getNamespace(), getFormName())) {
			String answer = questionnaireAnswer.getAnswer();
			if (answer != null) {
				switch (getQuestionAnswerService().findQuestionById(questionnaireAnswer.getQuestionId()).getQuestionSeqId()) {
				case HUMAN:
					researchTrainingPlan
							.setHumanSubjectsIndefinite(answer
									.equals(YnqConstant.YES.code()) ? YesNoDataType.Y_YES
									: YesNoDataType.N_NO);
					break;
				case VERT:
					researchTrainingPlan
							.setVertebrateAnimalsIndefinite(answer
									.equals(YnqConstant.YES.code()) ? YesNoDataType.Y_YES
									: YesNoDataType.N_NO);
					break;
				case CLINICAL:
					researchTrainingPlan
							.setClinicalTrial(answer
									.equals(YnqConstant.YES.code()) ? YesNoDataType.Y_YES
									: YesNoDataType.N_NO);
					break;
				case PHASE3CLINICAL:
					researchTrainingPlan
							.setPhase3ClinicalTrial(answer
									.equals(YnqConstant.YES.code()) ? YesNoDataType.Y_YES
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
	 * @param researchTrainingPlan
	 */
	private void setHumanSubjectInvolvedAndVertebrateAnimalUsed(
			ResearchTrainingPlan researchTrainingPlan) {
		researchTrainingPlan.setHumanSubjectsInvolved(YesNoDataType.N_NO);
		researchTrainingPlan.setVertebrateAnimalsUsed(YesNoDataType.N_NO);
		for (ProposalSpecialReviewContract propSpecialReview : pdDoc
				.getDevelopmentProposal().getPropSpecialReviews()) {
			switch (Integer.parseInt(propSpecialReview.getSpecialReviewType().getCode())) {
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
        additionalInformation.setCitizenship(CitizenshipDataType.U_S_CITIZEN_OR_NONCITIZEN_NATIONAL);
        StemCells stemCells = StemCells.Factory.newInstance();
        stemCells.setIsHumanStemCellsInvolved(YesNoDataType.N_NO);
        stemCells.setStemCellsIndicator(YesNoDataType.N_NO);
        GraduateDegreeSought graduateDegreeSought = GraduateDegreeSought.Factory.newInstance();
        ProposalPersonContract principalInvestigator = s2SProposalPersonService.getPrincipalInvestigator(pdDoc);
        ArrayList<String> cellLinesList = new ArrayList<String>(Arrays.asList(stemCells.getCellLinesArray())); 
		for (ProposalPersonContract proposalPerson : pdDoc.getDevelopmentProposal()
				.getProposalPersons()) {
			if (proposalPerson.isInvestigator()) {	
				CitizenshipType citizenShip=s2SProposalPersonService.getCitizenship(proposalPerson);
				if(citizenShip.getCitizenShip().trim().equals(CitizenshipDataType.NON_U_S_CITIZEN_WITH_TEMPORARY_VISA.toString())){
					additionalInformation.setCitizenship(CitizenshipDataType.NON_U_S_CITIZEN_WITH_TEMPORARY_VISA);
				}
				else if(citizenShip.getCitizenShip().trim().equals(CitizenshipDataType.PERMANENT_RESIDENT_OF_U_S.toString())){
					additionalInformation.setCitizenship(CitizenshipDataType.PERMANENT_RESIDENT_OF_U_S);
				}
				else if(citizenShip.getCitizenShip().trim().equals(CitizenshipDataType.U_S_CITIZEN_OR_NONCITIZEN_NATIONAL.toString())){
					additionalInformation.setCitizenship(CitizenshipDataType.U_S_CITIZEN_OR_NONCITIZEN_NATIONAL);
				}
				else if(citizenShip.getCitizenShip().trim().equals(CitizenshipDataType.PERMANENT_RESIDENT_OF_U_S_PENDING.toString())){
                    additionalInformation.setCitizenship(CitizenshipDataType.PERMANENT_RESIDENT_OF_U_S_PENDING);
                }				
			}
		}
		if (principalInvestigator != null && principalInvestigator.getMobilePhoneNumber() != null) {
			additionalInformation.setAlernatePhoneNumber(principalInvestigator.getMobilePhoneNumber());
		}
        List<? extends AnswerHeaderContract> answerHeaders = getPropDevQuestionAnswerService().getQuestionnaireAnswerHeaders(pdDoc.getDevelopmentProposal().getProposalNumber(), getNamespace(), getFormName());
		for (AnswerHeaderContract answerHeader : answerHeaders) {
            for (AnswerContract questionnaireAnswer : answerHeader.getAnswers()) {
                String answer = questionnaireAnswer.getAnswer();

                Integer seqId = getQuestionAnswerService().findQuestionById(questionnaireAnswer.getQuestionId()).getQuestionSeqId();
                if(seqId.equals(STEMCELLLINES)){
                    List<AnswerContract> answerList = getAnswers(questionnaireAnswer.getQuestionnaireQuestionsId(),answerHeader);
                    for (AnswerContract questionnaireAnswerBO: answerList) {
                        String questionnaireSubAnswer =  questionnaireAnswerBO.getAnswer();
                        if(questionnaireSubAnswer!=null){
                            cellLinesList.add(questionnaireSubAnswer);
                            stemCells.addCellLines(questionnaireSubAnswer);
                        }
                    }
                }

                if (answer != null) {
                    switch (seqId) {
                        case BROAD_TRAINING:
                case FIELD_TRAINING:
                            if (!answer.toUpperCase().equals(SUB_CATEGORY_NOT_FOUND)) {
                                FieldOfTrainingDataType.Enum e = FieldOfTrainingDataType.Enum.forString(answer);
                                additionalInformation.setFieldOfTraining(e);
                            }
                            break;
                case NRSA_SUPPORT:
                            additionalInformation
                                    .setCurrentPriorNRSASupportIndicator(answer
                                            .equals(YnqConstant.YES.code()) ? YesNoDataType.Y_YES
                                            : YesNoDataType.N_NO);
                            break;
                case SUBMITTED_DIFF_INST:
                            additionalInformation
                                    .setChangeOfInstitution(answer
                                            .equals(YnqConstant.YES.code()) ? YesNoDataType.Y_YES
                                            : YesNoDataType.N_NO);
                            break;
                case FORMER_INST:
                            additionalInformation.setFormerInstitution(answer);
                            break;
                case STEMCELLS:
                            stemCells
                                    .setIsHumanStemCellsInvolved(answer
                                            .equals(YnqConstant.YES.code()) ? YesNoDataType.Y_YES
                                            : YesNoDataType.N_NO);
                            break;
                case CELLLINEIND:
                            stemCells.setStemCellsIndicator(answer
                                    .equals(YnqConstant.YES.code()) ? YesNoDataType.Y_YES
                                    : YesNoDataType.N_NO);
                            break;

                case DEGREE_TYPE_SOUGHT:
                            graduateDegreeSought.setDegreeType(DegreeTypeDataType.Enum.forString(answer));
                            break;
                case DEG_EXP_COMP_DATE:
                            graduateDegreeSought.setDegreeDate(answer.substring(6, 10)
                                    + STRING_SEPRATOR + answer.substring(0, 2));
                            break;
                case OTHER_MASTERS:
                            graduateDegreeSought.setOtherDegreeTypeText(answer);
                            break;
                case OTHER_DOCT:
                            graduateDegreeSought.setOtherDegreeTypeText(answer);
                            break;
                case OTHER_DDOT:
                            graduateDegreeSought.setDegreeType(DegreeTypeDataType.DDOT_OTHER_DOCTOR_OF_MEDICAL_DENTISTRY);
                            graduateDegreeSought.setOtherDegreeTypeText(answer);
                            break;
                case OTHER_VDOT:
                            graduateDegreeSought.setDegreeType(DegreeTypeDataType.VDOT_OTHER_DOCTOR_OF_VETERINARY_MEDICINE);
                            graduateDegreeSought.setOtherDegreeTypeText(answer);
                            break;
                case OTHER_DBOTH:
                            graduateDegreeSought.setDegreeType(DegreeTypeDataType.DBOTH_OTHER_DOUBLE_DEGREE_PROGRAM);
                            graduateDegreeSought.setOtherDegreeTypeText(answer);
                            break;
                case OTHER_MDOT:
                            graduateDegreeSought.setDegreeType(DegreeTypeDataType.MDOT_OTHER_DOCTOR_OF_MEDICINE);
                            graduateDegreeSought.setOtherDegreeTypeText(answer);
                            break;
                        default:
                            break;
                    }
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
        FellowshipTrainingAndCareerGoals fellowshipTrainingAndCareerGoals = FellowshipTrainingAndCareerGoals.Factory.newInstance();
        ActivitiesPlannedUnderThisAward activitiesPlannedUnderThisAward = ActivitiesPlannedUnderThisAward.Factory.newInstance();

        AttachedFileDataType attachedFileDataType = null;
        for (NarrativeContract narrative : pdDoc.getDevelopmentProposal()
                .getNarratives()) {
            if (narrative.getNarrativeType().getCode() != null) {
                switch (Integer.parseInt(narrative.getNarrativeType().getCode())) {
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
                    fellowshipTrainingAndCareerGoals.setAttFile(attachedFileDataType);
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
                    activitiesPlannedUnderThisAward.setAttFile(attachedFileDataType);
                    break;
                default:
                    break;

                }
            }
        }
        additionalInformation.setFellowshipTrainingAndCareerGoals(fellowshipTrainingAndCareerGoals);
        additionalInformation.setActivitiesPlannedUnderThisAward(activitiesPlannedUnderThisAward);
        return additionalInformation;
    }

	/*
	 * This method is used to get Arrays of CurrentPriorNRSASupport XMLObject
	 * and set data to it from List of ProposalYnq
	 */
	private CurrentPriorNRSASupport[] getCurrentPriorNRSASupportArray() {
        List<CurrentPriorNRSASupport> currentPriorNRSASupportList = new ArrayList<CurrentPriorNRSASupport>();
        List<AnswerContract> answerList = new ArrayList<AnswerContract>();
        String nsrSupport = null;
        
        List<? extends AnswerHeaderContract> answers = findQuestionnaireWithAnswers(pdDoc.getDevelopmentProposal());
        for (AnswerHeaderContract answerHeader : answers) {
            QuestionnaireContract questionnaire = questionAnswerService.findQuestionnaireById(answerHeader.getQuestionnaireId());
            List<? extends QuestionnaireQuestionContract> questionnaireQuestions = questionnaire.getQuestionnaireQuestions();
            for (QuestionnaireQuestionContract questionnaireQuestion : questionnaireQuestions) {
                AnswerContract answerBO = getAnswer(questionnaireQuestion,answerHeader);
                String answer = answerBO != null ? answerBO.getAnswer() : null;
                QuestionContract question = questionnaireQuestion.getQuestion();

                if (answer != null) {
                    Integer questionId = question.getQuestionSeqId();
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
                            answer = FieldValueConstants.VALUE_UNKNOWN;
                            questionId = KIRST_START_DT;
                        } else {
                            break;
                        }
                    }
                        if (questionId == KIRST_END_KNOWN) {
                            if (answer.equals(QUESTION_ANSWER_NO)) {
                                answer = FieldValueConstants.VALUE_UNKNOWN;
                                questionId = KIRST_END_DT;
                            } else {
                                break;
                            }
                        }
                        if (questionId == KIRST_GRANT_KNOWN) {
                            if (answer.equals(QUESTION_ANSWER_NO)) {
                                answer = FieldValueConstants.VALUE_UNKNOWN;
                                questionId = KIRST_GRANT_NUM;
                            } else {
                                break;
                            }
                        }
                        InternalAnswer quesAnswer = new InternalAnswer();
                        quesAnswer.setAnswer(answer);
                        quesAnswer.setQuestionNumber(questionId);
                        answerList.add(quesAnswer);

                    break;
                case NRSA_SUPPORT:
                    nsrSupport = answer
                            .equals(YnqConstant.YES.code()) ? NSR_SUPPORT_YES
                            : NSR_SUPPORT_NO;
                    break;
                default:
                    break;
                }
            }
        }
    }
        Collections.sort(answerList, new Comparator<AnswerContract>() {
            public int compare(AnswerContract answer1, AnswerContract answer2) {
                return answer1.getQuestionNumber().compareTo(
                        answer2.getQuestionNumber());
            }

        });
        List<CurrentPriorNRSASupport.Level.Enum> levelList = new ArrayList<CurrentPriorNRSASupport.Level.Enum>();
        List<CurrentPriorNRSASupport.Type.Enum> typeList = new ArrayList<CurrentPriorNRSASupport.Type.Enum>();
        List<Calendar> startDateList = new ArrayList<Calendar>();
        List<Calendar> endDateList = new ArrayList<Calendar>();
        List<String> grantNumberList = new ArrayList<String>();
        for (AnswerContract questionnaireAnswer : answerList) {
            if (nsrSupport != null && nsrSupport.equals(NSR_SUPPORT_YES)) {
                if (questionnaireAnswer.getQuestionNumber() == PRE_OR_POST) {
                    levelList.add(CurrentPriorNRSASupport.Level.Enum
                            .forString(questionnaireAnswer.getAnswer()));
                }
                if (questionnaireAnswer.getQuestionNumber() == IND_OR_INST) {
                    typeList.add(CurrentPriorNRSASupport.Type.Enum
                            .forString(questionnaireAnswer.getAnswer()));
                }
                if (questionnaireAnswer.getQuestionNumber() == KIRST_START_DT){
                    if(questionnaireAnswer.getAnswer().equals(FieldValueConstants.VALUE_UNKNOWN)) {
                        startDateList.add(null);
                    }else{
                        startDateList.add(s2SDateTimeService.convertDateStringToCalendar(questionnaireAnswer.getAnswer()));
                    }
                }
                if (questionnaireAnswer.getQuestionNumber() == KIRST_END_DT){
                    if(questionnaireAnswer.getAnswer().equals(FieldValueConstants.VALUE_UNKNOWN)) {
                        endDateList.add(null);
                    }else{
                        endDateList.add(s2SDateTimeService.convertDateStringToCalendar(questionnaireAnswer.getAnswer()));
                    }
                }
                if (questionnaireAnswer.getQuestionNumber() == KIRST_GRANT_NUM) {
                    grantNumberList.add(questionnaireAnswer.getAnswer());
                }
            }
        }
        for (int index = 0; levelList.size() > index; index++) {
            CurrentPriorNRSASupport currentPriorNRSASupport = CurrentPriorNRSASupport.Factory.newInstance();
            currentPriorNRSASupport.setLevel(levelList.get(index));
            currentPriorNRSASupport.setType(typeList.get(index));
            if(!startDateList.isEmpty() && startDateList.get(index)!=null)
                currentPriorNRSASupport.setStartDate(startDateList.get(index));
            if(!endDateList.isEmpty() && endDateList.get(index)!=null)
                currentPriorNRSASupport.setEndDate(endDateList.get(index));
            currentPriorNRSASupport.setGrantNumber(grantNumberList.get(index));
            currentPriorNRSASupportList.add(currentPriorNRSASupport);
        }
        return currentPriorNRSASupportList.toArray(new CurrentPriorNRSASupport[currentPriorNRSASupportList.size()]);
    }
	 private AnswerContract getAnswer(QuestionnaireQuestionContract questionnaireQuestion, AnswerHeaderContract answerHeader) {
	        List<? extends AnswerContract> answers = answerHeader.getAnswers();
	        for (AnswerContract answer : answers) {
	            if(answer.getQuestionnaireQuestionsId().equals(questionnaireQuestion.getId())){
	                return answer;
	            }
	        }
	        return null;
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
		for (NarrativeContract narrative : pdDoc.getDevelopmentProposal()
				.getNarratives()) {
			if (narrative.getNarrativeType().getCode() != null
					&& Integer.parseInt(narrative.getNarrativeType().getCode()) == APPENDIX) {
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
				.getProposalType().getCode();
		TypeOfApplication.Enum typeOfApplication = null;
		if (proposalTypeCode != null) {
			if (proposalTypeCode.equals(s2SConfigurationService.getValueAsString(
                    ConfigurationConstants.PROPOSAL_TYPE_CODE_NEW))) {
				typeOfApplication = TypeOfApplication.NEW;
			} else if (proposalTypeCode.equals(s2SConfigurationService.getValueAsString(
                    ConfigurationConstants.PROPOSAL_TYPE_CODE_CONTINUATION))) {
				typeOfApplication = TypeOfApplication.CONTINUATION;
			} else if (proposalTypeCode.equals(s2SConfigurationService.getValueAsString(
                    ConfigurationConstants.PROPOSAL_TYPE_CODE_REVISION))) {
				typeOfApplication = TypeOfApplication.REVISION;
			} else if (proposalTypeCode.equals(s2SConfigurationService.getValueAsString(
                    ConfigurationConstants.PROPOSAL_TYPE_CODE_RENEWAL))) {
				typeOfApplication = TypeOfApplication.RENEWAL;
			} else if (proposalTypeCode.equals(s2SConfigurationService.getValueAsString(
                    ConfigurationConstants.PROPOSAL_TYPE_CODE_RESUBMISSION))) {
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
	private ScaleTwoDecimal getNumberOfMonths(Date dateStart, Date dateEnd) {
		BigDecimal monthCount = ScaleTwoDecimal.ZERO.bigDecimalValue();
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
			return ScaleTwoDecimal.ZERO;
		}
		int startMonthDays = startDate.getActualMaximum(Calendar.DATE)
				- startDate.get(Calendar.DATE);
		startMonthDays++;
		int startMonthMaxDays = startDate.getActualMaximum(Calendar.DATE);
		BigDecimal startMonthFraction = new ScaleTwoDecimal(startMonthDays).bigDecimalValue()
				.divide(new ScaleTwoDecimal(startMonthMaxDays).bigDecimalValue(), RoundingMode.HALF_UP);

		int endMonthDays = endDate.get(Calendar.DATE);
		int endMonthMaxDays = endDate.getActualMaximum(Calendar.DATE);

		BigDecimal endMonthFraction = new ScaleTwoDecimal(endMonthDays).bigDecimalValue()
				.divide(new ScaleTwoDecimal(endMonthMaxDays).bigDecimalValue(), RoundingMode.HALF_UP);

		startDate.set(Calendar.DATE, 1);
		endDate.set(Calendar.DATE, 1);

		while (startDate.getTimeInMillis() < endDate.getTimeInMillis()) {
			startDate.set(Calendar.MONTH, startDate.get(Calendar.MONTH) + 1);
			fullMonthCount++;
		}
		fullMonthCount = fullMonthCount - 1;
		monthCount = monthCount.add(new ScaleTwoDecimal(fullMonthCount).bigDecimalValue()).add(
				startMonthFraction).add(endMonthFraction);
		return new ScaleTwoDecimal(monthCount);
	}

	/**
	 * This method creates {@link XmlObject} of type
	 * {@link PHSFellowshipSupplemental11Document} by populating data from the
	 * given {@link ProposalDevelopmentDocumentContract}
	 * 
	 * @param proposalDevelopmentDocument
	 *            for which the {@link XmlObject} needs to be created
	 * @return {@link XmlObject} which is generated using the given
	 *         {@link ProposalDevelopmentDocumentContract}
	 */
	public XmlObject getFormObject(
			ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
		this.pdDoc = proposalDevelopmentDocument;
        PHSFellowshipSupplemental11 phsFellowshipSupplemental = getPHSFellowshipSupplemental11();
        PHSFellowshipSupplemental11Document phsFellowshipSupplementalDocument = PHSFellowshipSupplemental11Document.Factory
                .newInstance();
        phsFellowshipSupplementalDocument
                .setPHSFellowshipSupplemental11(phsFellowshipSupplemental);
        return phsFellowshipSupplementalDocument;
	}

    private List<AnswerContract> getAnswers(Long questonnaireQuestionId, AnswerHeaderContract answerHeader) {
        List<AnswerContract> returnAnswers = new ArrayList<AnswerContract>();
        if (answerHeader != null) {
            List<? extends AnswerContract> answers = answerHeader.getAnswers();
            for (AnswerContract answer : answers) {
                if (answer.getQuestionnaireQuestionsId().equals(questonnaireQuestionId)) {
                    returnAnswers.add(answer);
                }
            }
        }
        return returnAnswers;
    }
    private List<? extends AnswerHeaderContract> findQuestionnaireWithAnswers(DevelopmentProposalContract developmentProposal) {
        return getPropDevQuestionAnswerService().getQuestionnaireAnswerHeaders(developmentProposal.getProposalNumber(),
                "http://apply.grants.gov/forms/PHS_Fellowship_Supplemental_1_1-V1.1", "PHS_Fellowship_Supplemental_1_1-V1.1");
    }

    private static class InternalAnswer implements AnswerContract {
        private Long id;
        private Integer questionNumber;
        private Integer answerNumber;
        private String answer;
        private Long answerHeaderId;
        private Long questionId;
        private Long questionnaireQuestionsId;
        private List<InternalAnswer> parentAnswers;

        public Long getId() {
            return id;
        }

        public Integer getQuestionNumber() {
            return questionNumber;
        }

        public Integer getAnswerNumber() {
            return answerNumber;
        }

        public String getAnswer() {
            return answer;
        }

        public Long getAnswerHeaderId() {
            return answerHeaderId;
        }

        public Long getQuestionId() {
            return questionId;
        }

        public Long getQuestionnaireQuestionsId() {
            return questionnaireQuestionsId;
        }

        public List<InternalAnswer> getParentAnswers() {
            return parentAnswers;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setQuestionNumber(Integer questionNumber) {
            this.questionNumber = questionNumber;
        }

        public void setAnswerNumber(Integer answerNumber) {
            this.answerNumber = answerNumber;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public void setAnswerHeaderId(Long answerHeaderId) {
            this.answerHeaderId = answerHeaderId;
        }

        public void setQuestionId(Long questionId) {
            this.questionId = questionId;
        }

        public void setQuestionnaireQuestionsId(Long questionnaireQuestionsId) {
            this.questionnaireQuestionsId = questionnaireQuestionsId;
        }

        public void setParentAnswers(List<InternalAnswer> parentAnswers) {
            this.parentAnswers = parentAnswers;
        }
    }

    @Override
    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    @Override
    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    @Override
    public Resource getStylesheet() {
        return stylesheet;
    }

    public void setStylesheet(Resource stylesheet) {
        this.stylesheet = stylesheet;
    }

    @Override
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }
}
