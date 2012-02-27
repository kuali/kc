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
import gov.grants.apply.forms.phsFellowshipSupplemental12V12.PHSFellowshipSupplemental12Document.PHSFellowshipSupplemental12.Sponsors;
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
import gov.grants.apply.forms.phsFellowshipSupplemental12V12.PHSFellowshipSupplemental12Document.PHSFellowshipSupplemental12.Sponsors.SponsorCosponsorInformation;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.attachmentsV10.AttachmentGroupMin0Max100DataType;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType.Enum;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.calculator.QueryList;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.infrastructure.CitizenshipTypes;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.ProposalDevelopmentUtils;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentS2sQuestionnaireService;
import org.kuali.kra.proposaldevelopment.specialreview.ProposalSpecialReview;
import org.kuali.kra.questionnaire.Questionnaire;
import org.kuali.kra.questionnaire.QuestionnaireQuestion;
import org.kuali.kra.questionnaire.answer.Answer;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.question.Question;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.util.S2SConstants;

/**
 * 
 * Class for generating the XML object for grants.gov PHS398FellowshipSupplementalV1_1 Form is generated using XMLBean classes and
 * is based on PHS398FellowshipSupplementalV1_1 schema
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class PHS398FellowshipSupplementalV1_2Generator extends PHS398FellowshipSupplementalBaseGenerator {


    private static final Log LOG = LogFactory.getLog(PHS398FellowshipSupplementalV1_2Generator.class);

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
    private static final int DEGREE_TYPE_SOUGHT = 99;
    private static final int DEG_EXP_COMP_DATE = 35;
    private static final int NRSA_SUPPORT = 24;
    private static final int FIELD_TRAINING = 22;
    private static final int BROAD_TRAINING = 23;
    private static final int OTHER_MASTERS = 16;
    private static final int OTHER_DOCT = 17;
    private static final int OTHER_DDOT = 18;
    private static final int OTHER_VDOT = 19;
    private static final int OTHER_DBOTH = 100;
    private static final int OTHER_MDOT = 21;
    private static final int SUBMITTED_DIFF_INST = 28;
    private static final int SENIOR_FELL = 36;
    private static final int OTHER_SUPP_SOURCE = 37;
    private static final int SUPP_FUNDING_AMT = 38;
    private static final int SUPP_MONTHS = 51;
    private static final int SUPP_SOURCE = 41;
    private static final int SUPP_TYPE = 40;
    private static final int BASE_SALARY = 47;
    private static final int ACAD_PERIOD = 48;
    private static final int SALARY_MONTHS = 50;

    private static final int APPENDIX = 96;
    private static final int SPONSOR_COSPONSOR = 134;
    
    private static final String ANSWER_YES = "Yes";
    private static final String ANSWER_NO = "No";
    private static final String ANSWER_Y = "Y";
    private static final String ANSWER_N = "N";
    /*
     * This method is used to get PHSFellowshipSupplemental12 XMLObject and set the data to it from DevelopmentProposal data.
     */
    private PHSFellowshipSupplemental12Document getPHSFellowshipSupplemental12() {
        PHSFellowshipSupplemental12Document phsFellowshipSupplementalDocument = PHSFellowshipSupplemental12Document.Factory
                .newInstance();
        PHSFellowshipSupplemental12 phsFellowshipSupplemental = phsFellowshipSupplementalDocument
                .addNewPHSFellowshipSupplemental12();
        phsFellowshipSupplemental.setFormVersion(S2SConstants.FORMVERSION_1_2);
        phsFellowshipSupplemental.setApplicationType(getApplicationType());
        phsFellowshipSupplemental.setAppendix(getAppendix());
        setQuestionnaireData(phsFellowshipSupplemental);
        return phsFellowshipSupplementalDocument;
    }
    private List<Answer> getAnswers(QuestionnaireQuestion questionnaireQuestion, AnswerHeader answerHeader) {
        List<Answer> returnAnswers = new ArrayList<Answer>();
        if (answerHeader != null) {
            List<Answer> answers = answerHeader.getAnswers();
            //List<Answer> answerList = new ArrayList<Answer>();
            for (Answer answer : answers) {
                if (answer.getQuestionnaireQuestionsIdFk().equals(questionnaireQuestion.getQuestionnaireQuestionsId())) {               
                    returnAnswers.add(answer);
                }
            }
        }
        return returnAnswers;
    }
    private void setQuestionnaireData(PHSFellowshipSupplemental12 phsFellowshipSupplemental) {
        Map<Integer, String> hmBudgetQuestions = new HashMap<Integer, String>();
        List<AnswerHeader> answers = findQuestionnaireWithAnswers(pdDoc.getDevelopmentProposal());
        ResearchTrainingPlan researchTrainingPlan = phsFellowshipSupplemental.addNewResearchTrainingPlan();
        setHumanSubjectInvolvedAndVertebrateAnimalUsed(researchTrainingPlan);
        setNarrativeDataForResearchTrainingPlan(phsFellowshipSupplemental, researchTrainingPlan);
        AdditionalInformation additionalInfoType = phsFellowshipSupplemental.addNewAdditionalInformation();
        GraduateDegreeSought graduateDegreeSought = GraduateDegreeSought.Factory.newInstance();
        StemCells stemCellstype = StemCells.Factory.newInstance();
        QueryList<KirschsteinBean> cvKirsch = new QueryList<KirschsteinBean>();
        for (AnswerHeader answerHeader : answers) {
            Questionnaire questionnaire = answerHeader.getQuestionnaire();
            List<QuestionnaireQuestion> questionnaireQuestions = questionnaire.getQuestionnaireQuestions();
            for (QuestionnaireQuestion questionnaireQuestion : questionnaireQuestions) {
                Answer answerBO = getAnswer(questionnaireQuestion, answerHeader);
                String answer = answerBO.getAnswer();
                Question question = questionnaireQuestion.getQuestion();
                Integer questionNumber = questionnaireQuestion.getQuestionNumber();
                Integer parentQuestionNumber = questionnaireQuestion.getParentQuestionNumber();
                Integer questionId = question.getQuestionIdAsInteger();
                if (answer != null) {
                        if( !answer .equalsIgnoreCase(ANSWER_YES) || !answer.equalsIgnoreCase(ANSWER_NO)) {
                    switch (questionId) {
                        case HUMAN:
                            researchTrainingPlan.setHumanSubjectsIndefinite(getYesNoEnum(answer));
                            break;
                        case VERT:
                            // will the inclusion of vertebrate animals use be indefinite
                            if (answer != null)
                                researchTrainingPlan.setVertebrateAnimalsIndefinite(getYesNoEnum(answer));
                            break;
                        case CLINICAL:
                            // clinical trial
                            if (answer != null)
                                researchTrainingPlan.setClinicalTrial(getYesNoEnum(answer));
                            break;
                        case PHASE3CLINICAL:
                            // phase 3 clinical trial
                            if (answer != null)
                                researchTrainingPlan.setPhase3ClinicalTrial(getYesNoEnum(answer));
                            break;
                        case STEMCELLS:
                            // stem cells used
                            if (answer != null)
                                stemCellstype.setIsHumanStemCellsInvolved(getYesNoEnum(answer));
                            break;
                        case CELLLINEIND:
                            // stem cell line indicator
                            if (answer != null)
                                stemCellstype.setStemCellsIndicator(getYesNoEnum(answer));
                            break;
                        case STEMCELLLINES:
                            List<Answer> answerList = getAnswers(questionnaireQuestion,answerHeader);                      
                            for (Answer questionnaireAnswerBO: answerList) {
                                String questionnaireSubAnswer =  questionnaireAnswerBO.getAnswer();
                                if(questionnaireSubAnswer!=null){
                                    stemCellstype.addCellLines(questionnaireAnswerBO.getAnswer());
                                }
                            }
                            break;
                        case DEGREE_TYPE_SOUGHT:
                            graduateDegreeSought.setDegreeType(DegreeTypeDataType.Enum.forString(answer));
                            break;
                        case DEG_EXP_COMP_DATE:
                            graduateDegreeSought.setDegreeDate(answer.substring(6, 10) + STRING_SEPRATOR + answer.substring(0, 2));
                            break;
                        case OTHER_MASTERS:
                            graduateDegreeSought.setOtherDegreeTypeText(answer);
                            graduateDegreeSought.setOtherDegreeTypeText(answer);
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
                        case OTHER_MDOT:
                            graduateDegreeSought.setDegreeType(DegreeTypeDataType.MDOT_OTHER_DOCTOR_OF_MEDICINE);
                            graduateDegreeSought.setOtherDegreeTypeText(answer);
                            break;
                        case BROAD_TRAINING:
                        case FIELD_TRAINING:
                            if (!answer.toUpperCase().equals("SUB CATEGORY NOT FOUND"))
                                additionalInfoType.setFieldOfTraining(FieldOfTrainingDataType.Enum.forString(answer));
                            break;
                        case NRSA_SUPPORT:
                            additionalInfoType.setCurrentPriorNRSASupportIndicator(getYesNoEnum(answer));
                            break;
                        case KIRST_START_KNOWN: 
                        case KIRST_END_KNOWN: 
                        case KIRST_START_DT: 
                        case KIRST_END_DT: 
                        case KIRST_GRANT_KNOWN: 
                        case KIRST_GRANT_NUM: 
                        case PRE_OR_POST: 
                        case IND_OR_INST: 
                            if (questionId == KIRST_START_KNOWN) {
                                if (answer.equals("N")) {
                                    answer = S2SConstants.VALUE_UNKNOWN;
                                    questionId = KIRST_START_DT;
                                }else
                                    break;
                            }
                            if (questionId == KIRST_END_KNOWN) {
                                if (answer.equals("N")) {
                                    answer = S2SConstants.VALUE_UNKNOWN;
                                    questionId = KIRST_END_DT;
                                }else
                                    break;
                            }
                            if (questionId == KIRST_GRANT_KNOWN) {
                                if (answer.equals("N")) {
                                    answer = S2SConstants.VALUE_UNKNOWN;
                                    questionId = KIRST_GRANT_NUM;
                                }else
                                    break;
                            }
                            KirschsteinBean cbKirschstein = new KirschsteinBean();
                            cbKirschstein.setAnswer(answer);
                            cbKirschstein.setQuestionId(questionId);
                            cbKirschstein.setQuestionNumber(questionNumber);
                            cbKirschstein.setParentQuestionNumber(parentQuestionNumber);
                            cvKirsch.add(cbKirschstein);
                            break;
                        case SUBMITTED_DIFF_INST:
                            additionalInfoType.setChangeOfInstitution(getYesNoEnum(answer));
                            break;
                        case 29:
                            additionalInfoType.setFormerInstitution(answer);
                            break;
                        case SENIOR_FELL:
                            hmBudgetQuestions.put(SENIOR_FELL, answer);
                            break;
                        case OTHER_SUPP_SOURCE:
                            hmBudgetQuestions.put(OTHER_SUPP_SOURCE, answer);
                            break;
                        case SUPP_SOURCE:
                            hmBudgetQuestions.put(SUPP_SOURCE, answer);
                            break;
                        case SUPP_FUNDING_AMT:
                            hmBudgetQuestions.put(SUPP_FUNDING_AMT, answer);
                            break;
                        case SUPP_MONTHS:
                            hmBudgetQuestions.put(SUPP_MONTHS, answer);
                            break;
                        case SUPP_TYPE:
                            hmBudgetQuestions.put(SUPP_TYPE, answer);
                            break;
                        case SALARY_MONTHS:
                            hmBudgetQuestions.put(SALARY_MONTHS, answer);
                            break;
                        case ACAD_PERIOD:
                            hmBudgetQuestions.put(ACAD_PERIOD, answer);
                            break;
                        case BASE_SALARY:
                            hmBudgetQuestions.put(BASE_SALARY, answer);
                            break;
                        default:
                            break;

                    }
                   }
                   if( answer.equalsIgnoreCase(ANSWER_YES) || answer.equalsIgnoreCase(ANSWER_NO)){
                       switch (questionId) {
                           case HUMAN:
                               researchTrainingPlan.setHumanSubjectsIndefinite(null);
                               researchTrainingPlan.setHumanSubjectsInvolved(null);
                               break;
                           case VERT:                                                              
                               researchTrainingPlan.setVertebrateAnimalsIndefinite(null);
                               researchTrainingPlan.setVertebrateAnimalsUsed(null);
                               break;
                           case CLINICAL:                              
                               researchTrainingPlan.setClinicalTrial(null);
                               break;
                           case PHASE3CLINICAL:                               
                               if(researchTrainingPlan.getClinicalTrial()!=null){
                                   if((researchTrainingPlan.getClinicalTrial().equals(getYesNoEnum("N"))))
                                       researchTrainingPlan.setPhase3ClinicalTrial(getYesNoEnum(answer));
                                   else
                                       researchTrainingPlan.setPhase3ClinicalTrial(null);
                               }
                               break;
                           case FIELD_TRAINING:                               
                               additionalInfoType.setFieldOfTraining(null);
                               break;
                           case STEMCELLS:
                              stemCellstype.setIsHumanStemCellsInvolved(null);
                               break;
                           case NRSA_SUPPORT:
                               additionalInfoType.setCurrentPriorNRSASupportIndicator(null);
                               break;
                           default:
                               break;
                       }
                   }
                }
                else if (answer == null ) {
                    switch (questionId) {
                        case HUMAN:
                            researchTrainingPlan.setHumanSubjectsIndefinite(null);
                            researchTrainingPlan.setHumanSubjectsInvolved(null);
                            break;
                        case VERT:                           
                            researchTrainingPlan.setVertebrateAnimalsIndefinite(null);
                            researchTrainingPlan.setVertebrateAnimalsUsed(null);
                            break;
                        case CLINICAL:
                           researchTrainingPlan.setClinicalTrial(null);
                            break;
                        case PHASE3CLINICAL:
                            if(researchTrainingPlan.getClinicalTrial() != null)
                           researchTrainingPlan.setPhase3ClinicalTrial(null);
                            break; 
                        case FIELD_TRAINING:                               
                           additionalInfoType.setFieldOfTraining(null);
                        break; 
                        case STEMCELLS:
                           stemCellstype.setIsHumanStemCellsInvolved(null);
                            break;
                        case NRSA_SUPPORT:
                           additionalInfoType.setCurrentPriorNRSASupportIndicator(null);
                            break;
                        default:
                            break;
                    }                    
                }
            }
        }
        if (stemCellstype != null)
            additionalInfoType.setStemCells(stemCellstype);
        if (graduateDegreeSought.getDegreeType() != null)
            additionalInfoType.setGraduateDegreeSought(graduateDegreeSought);
        QueryList<KirschsteinBean> cvType = new QueryList<KirschsteinBean>();
        QueryList<KirschsteinBean> cvStart = new QueryList<KirschsteinBean>();
        QueryList<KirschsteinBean> cvEnd = new QueryList<KirschsteinBean>();
        QueryList<KirschsteinBean> cvLevel = new QueryList<KirschsteinBean>();
        QueryList<KirschsteinBean> cvGrant = new QueryList<KirschsteinBean>();
        KirschsteinBean kbBean1 = new KirschsteinBean();
        KirschsteinBean kbBean2 = new KirschsteinBean();
        KirschsteinBean kbBean3 = new KirschsteinBean();
        KirschsteinBean kbBean4 = new KirschsteinBean();
        KirschsteinBean kbBean5 = new KirschsteinBean();

        if (additionalInfoType.getCurrentPriorNRSASupportIndicator() != null) {
            if (additionalInfoType.getCurrentPriorNRSASupportIndicator().equals(YesNoDataType.Y_YES)) {
                KirschsteinBean kbBean = new KirschsteinBean();
                cvKirsch.sort("questionNumber");
                for (int i = 0; i < cvKirsch.size(); i++) {
                    kbBean = (KirschsteinBean) cvKirsch.get(i);
                    switch (kbBean.getQuestionId()) {
                        case PRE_OR_POST:
                            cvLevel.add(kbBean);
                            break;
                        case IND_OR_INST:
                            cvType.add(kbBean);
                            break;
                        case KIRST_START_DT:
                            cvStart.add(kbBean);
                            break;
                        case KIRST_END_DT:
                            cvEnd.add(kbBean);
                            break;
                        case KIRST_GRANT_NUM:
                            cvGrant.add(kbBean);
                            break;
                    }

                }
            }
            List<CurrentPriorNRSASupport> currentPriorNRSASupportList = new ArrayList<CurrentPriorNRSASupport>();
            int numberRepeats = cvLevel.size();
            if (numberRepeats > 0) {
                for (int j = 0; j < numberRepeats; j++) {
                    kbBean1 = (KirschsteinBean) cvLevel.get(j);
                    kbBean2 = (KirschsteinBean) cvType.get(j);
                    kbBean3 = (KirschsteinBean) cvStart.get(j);
                    kbBean4 = (KirschsteinBean) cvEnd.get(j);
                    kbBean5 = (KirschsteinBean) cvGrant.get(j);
                    CurrentPriorNRSASupport nrsaSupportType = CurrentPriorNRSASupport.Factory.newInstance();
                    nrsaSupportType.setLevel(CurrentPriorNRSASupport.Level.Enum.forString(kbBean1.getAnswer()));
                    nrsaSupportType.setType(CurrentPriorNRSASupport.Type.Enum.forString(kbBean2.getAnswer()));
                    if(!kbBean3.getAnswer().equals(S2SConstants.VALUE_UNKNOWN)){
                        nrsaSupportType.setStartDate(s2sUtilService.convertDateStringToCalendar(kbBean3.getAnswer()));
                    }
                    if(!kbBean4.getAnswer().equals(S2SConstants.VALUE_UNKNOWN)){
                        nrsaSupportType.setEndDate(s2sUtilService.convertDateStringToCalendar(kbBean4.getAnswer().toString()));
                    }
                    nrsaSupportType.setGrantNumber(kbBean5.getAnswer());
                    currentPriorNRSASupportList.add(nrsaSupportType);
                }
            }
            additionalInfoType.setCurrentPriorNRSASupportArray(currentPriorNRSASupportList.toArray(new CurrentPriorNRSASupport[0]));
        }
        phsFellowshipSupplemental.setBudget(getBudget(hmBudgetQuestions));
        setAdditionalInformation(additionalInfoType);
    }


    /**
     * This method is to return YesNoDataType enum
     * 
     * @param answer
     * @return
     */
    private Enum getYesNoEnum(String answer) {
        return answer.equals("Y") ? YesNoDataType.Y_YES : YesNoDataType.N_NO;
    }

    /*
     * This method is used to get Budget XMLObject and set the data to it from ProposalYnq based on questionId and answers.
     */
    private Budget getBudget(Map<Integer,String> budgetMap) {
        Budget budget = Budget.Factory.newInstance();
        budget.setTuitionAndFeesRequested(YesNoDataType.N_NO);
        getInstitutionalBaseSalary(budget, budgetMap);
        getFederalStipendRequested(budget);
        getSupplementationFromOtherSources(budget, budgetMap);
        setTuitionRequestedYears(budget);
        return budget;
    }

    /*
     * This method is used to get TuitionRequestedYears data to Budget XMLObject from List of BudgetLineItem based on CostElement
     * value of TUITION_COST_ELEMENTS
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
                if (getCostElementsByParam(TUITION_COST_ELEMENTS).contains(budgetLineItem.getCostElementBO().getCostElement())) {
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
     * This method is used to set data to SupplementationFromOtherSources XMLObject from budgetMap data for Budget
     */
    private void getSupplementationFromOtherSources(Budget budget, Map<Integer, String> hmBudgetQuestions) {

        if (!hmBudgetQuestions.isEmpty()) {
            if (hmBudgetQuestions.get(OTHER_SUPP_SOURCE) != null) {
                if (hmBudgetQuestions.get(OTHER_SUPP_SOURCE).toString().toUpperCase().equals("Y")) {
                    SupplementationFromOtherSources supplementationFromOtherSources = budget
                            .addNewSupplementationFromOtherSources();
                    if (hmBudgetQuestions.get(SUPP_SOURCE) != null) {
                        supplementationFromOtherSources.setSource(hmBudgetQuestions.get(SUPP_SOURCE).toString());
                        supplementationFromOtherSources.setAmount(new BigDecimal(hmBudgetQuestions.get(SUPP_FUNDING_AMT).toString()));
                        try {
                            supplementationFromOtherSources.setNumberOfMonths(new BigDecimal(hmBudgetQuestions.get(SUPP_MONTHS).toString()));
                        }catch (Exception ex) {}
                        supplementationFromOtherSources.setType(hmBudgetQuestions.get(SUPP_TYPE).toString());

                    }
                }
            }
        }
    }

    /*
     * This method is used to get FederalStipendRequested XMLObject and set additional information data to it.
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
                        if (getCostElementsByParam(STIPEND_COST_ELEMENTS).contains(
                                budgetLineItem.getCostElementBO().getCostElement())) {
                            sumOfLineItemCost = sumOfLineItemCost.add(budgetLineItem.getLineItemCost());
                            numberOfMonths = numberOfMonths.add(getNumberOfMonths(budgetLineItem.getStartDate(), budgetLineItem
                                    .getEndDate()));
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
     * This method is used to get final version of BudgetDocument from s2SBudgetCalculatorService using pdDoc
     */
    private BudgetDocument getBudgetDocument() {
        BudgetDocument budgetDoc = null;
        try {
            budgetDoc = s2SBudgetCalculatorService.getFinalBudgetVersion(pdDoc);
        }
        catch (S2SException e) {
            LOG.error("Error while getting Budget", e);
        }
        return budgetDoc;
    }

    /*
     * This method is used to set data to InstitutionalBaseSalary XMLObject from budgetMap data for Budget
     */
    private void getInstitutionalBaseSalary(Budget budget, Map<Integer, String> budgetMap) {
        InstitutionalBaseSalary institutionalBaseSalary = InstitutionalBaseSalary.Factory.newInstance();
        if (budgetMap.get(SENIOR_FELL) != null && budgetMap.get(SENIOR_FELL).toString().equals(S2SConstants.PROPOSAL_YNQ_ANSWER_Y)) {
            if (budgetMap.get(BASE_SALARY) != null) {
                institutionalBaseSalary.setAmount(new BigDecimal(budgetMap.get(BASE_SALARY).toString()));
            }
            if (budgetMap.get(ACAD_PERIOD) != null) {
                institutionalBaseSalary.setAcademicPeriod(AcademicPeriod.Enum.forString(budgetMap.get(ACAD_PERIOD).toString()));
            }
            if (budgetMap.get(SALARY_MONTHS) != null) {
                institutionalBaseSalary.setNumberOfMonths(new BigDecimal(budgetMap.get(SALARY_MONTHS).toString()));
            }
            budget.setInstitutionalBaseSalary(institutionalBaseSalary);
        }
    }

    /**
     * This method is used to set Narrative Data to ResearchTrainingPlan XMLObject based on NarrativeTypeCode.
     * 
     * @param researchTrainingPlan
     */
    private void setNarrativeDataForResearchTrainingPlan(PHSFellowshipSupplemental12 phsFellowshipSupplemental,
            ResearchTrainingPlan researchTrainingPlan) {
        AttachedFileDataType attachedFileDataType = null;
        researchTrainingPlan.addNewSpecificAims();
        researchTrainingPlan.addNewResearchStrategy();
        researchTrainingPlan.addNewRespectiveContributions();
        researchTrainingPlan.addNewSelectionOfSponsorAndInstitution();
        researchTrainingPlan.addNewResponsibleConductOfResearch();
        Sponsors sponsors = phsFellowshipSupplemental.addNewSponsors();
        SponsorCosponsorInformation sponsorCosponsorInfo = sponsors.addNewSponsorCosponsorInformation();
        for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeTypeCode() != null) {
                switch (Integer.parseInt(narrative.getNarrativeTypeCode())) {
                    case INTRODUCTION_TO_APPLICATION:
                        attachedFileDataType = getAttachedFileType(narrative);
                        if (attachedFileDataType == null) {
                            continue;
                        }
                        IntroductionToApplication introductionToApplication = IntroductionToApplication.Factory.newInstance();
                        introductionToApplication.setAttFile(attachedFileDataType);
                        researchTrainingPlan.setIntroductionToApplication(introductionToApplication);
                        break;
                    case SPECIFIC_AIMS:
                        attachedFileDataType = getAttachedFileType(narrative);
                        if (attachedFileDataType == null) {
                            continue;
                        }
                        SpecificAims specificAims =SpecificAims.Factory.newInstance();
                        specificAims.setAttFile(attachedFileDataType);
                        researchTrainingPlan.setSpecificAims(specificAims);
                        break;
                    case RESEARCH_STRATEGY:
                        attachedFileDataType = getAttachedFileType(narrative);
                        if (attachedFileDataType == null) {
                            continue;
                        }
                        ResearchStrategy researchStrategy = ResearchStrategy.Factory.newInstance();
                        researchStrategy.setAttFile(attachedFileDataType);
                        researchTrainingPlan.setResearchStrategy(researchStrategy);
                        break;
                    case INCLUSION_ENROLLMENT_REPORT:
                        attachedFileDataType = getAttachedFileType(narrative);
                        if (attachedFileDataType == null) {
                            continue;
                        }
                        InclusionEnrollmentReport inclusionEnrollmentReport = InclusionEnrollmentReport.Factory.newInstance();
                        inclusionEnrollmentReport.setAttFile(attachedFileDataType);
                        researchTrainingPlan.setInclusionEnrollmentReport(inclusionEnrollmentReport);
                        break;
                    case PROGRESS_REPORT_PUBLICATION_LIST:
                        attachedFileDataType = getAttachedFileType(narrative);
                        if (attachedFileDataType == null) {
                            continue;
                        }
                        ProgressReportPublicationList progressReportPublicationList = ProgressReportPublicationList.Factory
                                .newInstance();
                        progressReportPublicationList.setAttFile(attachedFileDataType);
                        researchTrainingPlan.setProgressReportPublicationList(progressReportPublicationList);
                        break;
                    case PROTECTION_OF_HUMAN_SUBJECTS:
                        attachedFileDataType = getAttachedFileType(narrative);
                        if (attachedFileDataType == null) {
                            continue;
                        }
                        ProtectionOfHumanSubjects protectionOfHumanSubjects = ProtectionOfHumanSubjects.Factory.newInstance();
                        protectionOfHumanSubjects.setAttFile(attachedFileDataType);
                        researchTrainingPlan.setProtectionOfHumanSubjects(protectionOfHumanSubjects);
                        break;
                    case INCLUSION_OF_WOMEN_AND_MINORITIES:
                        attachedFileDataType = getAttachedFileType(narrative);
                        if (attachedFileDataType == null) {
                            continue;
                        }
                        InclusionOfWomenAndMinorities inclusionOfWomenAndMinorities = InclusionOfWomenAndMinorities.Factory
                                .newInstance();
                        inclusionOfWomenAndMinorities.setAttFile(attachedFileDataType);
                        researchTrainingPlan.setInclusionOfWomenAndMinorities(inclusionOfWomenAndMinorities);
                        break;
                    case TARGETED_PLANNED_ENROLLMENT:
                        attachedFileDataType = getAttachedFileType(narrative);
                        if (attachedFileDataType == null) {
                            continue;
                        }
                        TargetedPlannedEnrollment tarPlannedEnrollmentTable = TargetedPlannedEnrollment.Factory.newInstance();
                        tarPlannedEnrollmentTable.setAttFile(attachedFileDataType);
                        researchTrainingPlan.setTargetedPlannedEnrollment(tarPlannedEnrollmentTable);
                        break;
                    case INCLUSION_OF_CHILDREN:
                        attachedFileDataType = getAttachedFileType(narrative);
                        if (attachedFileDataType == null) {
                            continue;
                        }
                        InclusionOfChildren inclusionOfChildren = InclusionOfChildren.Factory.newInstance();
                        inclusionOfChildren.setAttFile(attachedFileDataType);
                        researchTrainingPlan.setInclusionOfChildren(inclusionOfChildren);
                        break;
                    case VERTEBRATE_ANIMALS:
                        attachedFileDataType = getAttachedFileType(narrative);
                        if (attachedFileDataType == null) {
                            continue;
                        }
                        VertebrateAnimals vertebrateAnimals = VertebrateAnimals.Factory.newInstance();
                        vertebrateAnimals.setAttFile(attachedFileDataType);
                        researchTrainingPlan.setVertebrateAnimals(vertebrateAnimals);
                        break;
                    case SELECT_AGENT_RESEARCH:
                        attachedFileDataType = getAttachedFileType(narrative);
                        if (attachedFileDataType == null) {
                            continue;
                        }
                        SelectAgentResearch selectAgentResearch = SelectAgentResearch.Factory.newInstance();
                        selectAgentResearch.setAttFile(attachedFileDataType);
                        researchTrainingPlan.setSelectAgentResearch(selectAgentResearch);
                        break;
                    case RESOURCE_SHARING_PLANS:
                        attachedFileDataType = getAttachedFileType(narrative);
                        if (attachedFileDataType == null) {
                            continue;
                        }
                        ResourceSharingPlan resourceSharingPlan = ResourceSharingPlan.Factory.newInstance();
                        resourceSharingPlan.setAttFile(attachedFileDataType);
                        researchTrainingPlan.setResourceSharingPlan(resourceSharingPlan);
                        break;
                    case RESPECTIVE_CONTRIBUTIONS:
                        attachedFileDataType = getAttachedFileType(narrative);
                        if (attachedFileDataType == null) {
                            continue;
                        }
                        RespectiveContributions respectiveContributions = RespectiveContributions.Factory.newInstance();
                        respectiveContributions.setAttFile(attachedFileDataType);
                        researchTrainingPlan.setRespectiveContributions(respectiveContributions);
                        break;
                    case SELECTION_OF_SPONSOR_AND_INSTITUTION:
                        attachedFileDataType = getAttachedFileType(narrative);
                        if (attachedFileDataType == null) {
                            continue;
                        }
                        SelectionOfSponsorAndInstitution selectionOfSponsorAndInstitution = SelectionOfSponsorAndInstitution.Factory
                                .newInstance();
                        selectionOfSponsorAndInstitution.setAttFile(attachedFileDataType);
                        researchTrainingPlan.setSelectionOfSponsorAndInstitution(selectionOfSponsorAndInstitution);
                        break;
                    case RESPONSIBLE_CONDUCT_OF_RESEARCH:
                        attachedFileDataType = getAttachedFileType(narrative);
                        if (attachedFileDataType == null) {
                            continue;
                        }
                        ResponsibleConductOfResearch responsibleConductOfResearch = ResponsibleConductOfResearch.Factory
                                .newInstance();
                        responsibleConductOfResearch.setAttFile(attachedFileDataType);
                        researchTrainingPlan.setResponsibleConductOfResearch(responsibleConductOfResearch);
                        break;
                    case SPONSOR_COSPONSOR:
                        attachedFileDataType = getAttachedFileType(narrative);
                        if (attachedFileDataType == null) {
                            continue;
                        }
                        sponsorCosponsorInfo.setAttFile(attachedFileDataType);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * This method is used to set Narrative Data to ResearchTrainingPlan XMLObject based on NarrativeTypeCode.
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
                if (attachedFileDataType == null) {
                    continue;
                }
                SponsorCosponsorInformation sponsorCosponsorInfo = SponsorCosponsorInformation.Factory.newInstance();
                sponsorCosponsorInfo.setAttFile(attachedFileDataType);
                sponsors.setSponsorCosponsorInformation(sponsorCosponsorInfo);
                break;
            }
        }
        return sponsors;
    }


    /**
     * This method is used to set HumanSubjectInvoved and VertebrateAnimalUsed XMLObject Data.
     * 
     * @param developmentProposal
     * @param researchTrainingPlan
     */
    private void setHumanSubjectInvolvedAndVertebrateAnimalUsed(ResearchTrainingPlan researchTrainingPlan) {
        researchTrainingPlan.setHumanSubjectsInvolved(YesNoDataType.N_NO);       
        researchTrainingPlan.setVertebrateAnimalsUsed(YesNoDataType.N_NO);        
        for (ProposalSpecialReview propSpecialReview : pdDoc.getDevelopmentProposal().getPropSpecialReviews()) {
            switch (Integer.parseInt(propSpecialReview.getSpecialReviewTypeCode())) {
                case 1:
                    researchTrainingPlan.setHumanSubjectsInvolved(YesNoDataType.Y_YES);
                    break;
                case 2:
                    researchTrainingPlan.setVertebrateAnimalsUsed(YesNoDataType.Y_YES);
                    break;
                default:
                    break;
            }
        }
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
            if (answer.getQuestionnaireQuestionsIdFk().equals(questionnaireQuestion.getQuestionnaireQuestionsId())) {
                return answer;
            }
        }
        return null;
    }


    /*
     * This method is used to set additional information data to AdditionalInformation XMLObject from DevelopmentProposal,
     * ProposalYnq
     */
    private void setAdditionalInformation(AdditionalInformation additionalInformation) {
        Boolean hasInvestigator = false;
        additionalInformation.setAlernatePhoneNumber("None");
        additionalInformation.addNewFellowshipTrainingAndCareerGoals();
        additionalInformation.addNewActivitiesPlannedUnderThisAward();        
        for (ProposalPerson proposalPerson : pdDoc.getDevelopmentProposal().getProposalPersons()) {
            if (proposalPerson.isInvestigator()) {
                hasInvestigator = true;
                CitizenshipTypes citizenShip = s2sUtilService.getCitizenship(proposalPerson);
                if (citizenShip.getCitizenShip().trim().equals(CitizenshipDataType.NON_U_S_CITIZEN_WITH_TEMPORARY_VISA.toString())) {
                    additionalInformation.setCitizenship(CitizenshipDataType.NON_U_S_CITIZEN_WITH_TEMPORARY_VISA);
                }
                else if (citizenShip.getCitizenShip().trim().equals(CitizenshipDataType.PERMANENT_RESIDENT_OF_U_S.toString())) {
                    additionalInformation.setCitizenship(CitizenshipDataType.PERMANENT_RESIDENT_OF_U_S);
                }
                else if (citizenShip.getCitizenShip().trim().equals(
                        CitizenshipDataType.U_S_CITIZEN_OR_NONCITIZEN_NATIONAL.toString())) {
                    additionalInformation.setCitizenship(CitizenshipDataType.U_S_CITIZEN_OR_NONCITIZEN_NATIONAL);
                }
                else if (citizenShip.getCitizenShip().trim().equals(
                        CitizenshipDataType.PERMANENT_RESIDENT_OF_U_S_PENDING.toString())) {
                    additionalInformation.setCitizenship(CitizenshipDataType.PERMANENT_RESIDENT_OF_U_S_PENDING);
                }


            }
        }
        if(!hasInvestigator){
            additionalInformation.setCitizenship(null);
        }

        additionalInformation.setConcurrentSupport(YesNoDataType.N_NO);
        AttachedFileDataType attachedFileDataType = null;
        for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeTypeCode() != null) {
                switch (Integer.parseInt(narrative.getNarrativeTypeCode())) {
                    case CONCURRENT_SUPPORT:
                        attachedFileDataType = getAttachedFileType(narrative);
                        if (attachedFileDataType == null) {
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
                        if (attachedFileDataType == null) {
                            continue;
                        }
                        FellowshipTrainingAndCareerGoals fellowshipTrainingAndCareerGoals = FellowshipTrainingAndCareerGoals.Factory
                                .newInstance();
                        fellowshipTrainingAndCareerGoals.setAttFile(attachedFileDataType);
                        additionalInformation.setFellowshipTrainingAndCareerGoals(fellowshipTrainingAndCareerGoals);
                        break;
                    case DISSERTATION:
                        attachedFileDataType = getAttachedFileType(narrative);
                        if (attachedFileDataType == null) {
                            continue;
                        }
                        DissertationAndResearchExperience dissertationAndResearchExperience = DissertationAndResearchExperience.Factory
                                .newInstance();
                        dissertationAndResearchExperience.setAttFile(attachedFileDataType);
                        additionalInformation.setDissertationAndResearchExperience(dissertationAndResearchExperience);
                        break;
                    case ACTIVITIES:
                        attachedFileDataType = getAttachedFileType(narrative);
                        if (attachedFileDataType == null) {
                            continue;
                        }
                        ActivitiesPlannedUnderThisAward activitiesPlannedUnderThisAward = ActivitiesPlannedUnderThisAward.Factory
                                .newInstance();
                        activitiesPlannedUnderThisAward.setAttFile(attachedFileDataType);
                        additionalInformation.setActivitiesPlannedUnderThisAward(activitiesPlannedUnderThisAward);
                        break;
                    default:
                        break;

                }
            }
        }
    }


    /*
     * This method is used to get AttachmentGroupMin0Max100DataType xmlObject and set data to it based on narrative type code
     */
    private AttachmentGroupMin0Max100DataType getAppendix() {
        AttachmentGroupMin0Max100DataType attachmentGroupType = AttachmentGroupMin0Max100DataType.Factory.newInstance();
        List<AttachedFileDataType> attachedFileDataTypeList = new ArrayList<AttachedFileDataType>();
        AttachedFileDataType attachedFileDataType = null;
        for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeTypeCode() != null && Integer.parseInt(narrative.getNarrativeTypeCode()) == APPENDIX) {
                attachedFileDataType = getAttachedFileType(narrative);
                if (attachedFileDataType != null) {
                    attachedFileDataTypeList.add(attachedFileDataType);
                }
            }
        }
        attachmentGroupType.setAttachedFileArray(attachedFileDataTypeList.toArray(new AttachedFileDataType[attachedFileDataTypeList
                .size()]));
        return attachmentGroupType;
    }

    /*
     * This method is used to get ApplicationType XMLObject and set data to it from types of Application.
     */
    private ApplicationType getApplicationType() {
        ApplicationType applicationType = ApplicationType.Factory.newInstance();
        applicationType.setTypeOfApplication(getTypeOfApplication());
        return applicationType;
    }

    /*
     * This method is used to get TypeOfApplication based on proposalTypeCode of DevelopmentProposal
     */
    private TypeOfApplication.Enum getTypeOfApplication() {
        String proposalTypeCode = pdDoc.getDevelopmentProposal().getProposalTypeCode();
        TypeOfApplication.Enum typeOfApplication = null;
        if (proposalTypeCode != null) {
            if (proposalTypeCode.equals(ProposalDevelopmentUtils
                    .getProposalDevelopmentDocumentParameter(ProposalDevelopmentUtils.PROPOSAL_TYPE_CODE_NEW_PARM))) {
                typeOfApplication = TypeOfApplication.NEW;
            }
            else if (proposalTypeCode.equals(ProposalDevelopmentUtils
                    .getProposalDevelopmentDocumentParameter(ProposalDevelopmentUtils.PROPOSAL_TYPE_CODE_CONTINUATION_PARM))) {
                typeOfApplication = TypeOfApplication.CONTINUATION;
            }
            else if (proposalTypeCode.equals(ProposalDevelopmentUtils
                    .getProposalDevelopmentDocumentParameter(ProposalDevelopmentUtils.PROPOSAL_TYPE_CODE_REVISION_PARM))) {
                typeOfApplication = TypeOfApplication.REVISION;
            }
            else if (proposalTypeCode.equals(ProposalDevelopmentUtils
                    .getProposalDevelopmentDocumentParameter(ProposalDevelopmentUtils.PROPOSAL_TYPE_CODE_RENEWAL_PARM))) {
                typeOfApplication = TypeOfApplication.RENEWAL;
            }
            else if (proposalTypeCode.equals(ProposalDevelopmentUtils
                    .getProposalDevelopmentDocumentParameter(ProposalDevelopmentUtils.PROPOSAL_TYPE_CODE_RESUBMISSION_PARM))) {
                typeOfApplication = TypeOfApplication.RESUBMISSION;
            }
            else if (proposalTypeCode.equals(PROPOSAL_TYPE_CODE_NEW7)) {
                typeOfApplication = TypeOfApplication.NEW;
            }
        }
        return typeOfApplication;
    }

    /*
     * 
     * This method computes the number of months between any 2 given {@link Date} objects
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
        int startMonthDays = startDate.getActualMaximum(Calendar.DATE) - startDate.get(Calendar.DATE);
        startMonthDays++;
        int startMonthMaxDays = startDate.getActualMaximum(Calendar.DATE);
        BudgetDecimal startMonthFraction = new BudgetDecimal(startMonthDays).divide(new BudgetDecimal(startMonthMaxDays));

        int endMonthDays = endDate.get(Calendar.DATE);
        int endMonthMaxDays = endDate.getActualMaximum(Calendar.DATE);

        BudgetDecimal endMonthFraction = new BudgetDecimal(endMonthDays).divide(new BudgetDecimal(endMonthMaxDays));

        startDate.set(Calendar.DATE, 1);
        endDate.set(Calendar.DATE, 1);

        while (startDate.getTimeInMillis() < endDate.getTimeInMillis()) {
            startDate.set(Calendar.MONTH, startDate.get(Calendar.MONTH) + 1);
            fullMonthCount++;
        }
        fullMonthCount = fullMonthCount - 1;
        monthCount = monthCount.add(new BudgetDecimal(fullMonthCount)).add(startMonthFraction).add(endMonthFraction);
        return monthCount;
    }

    /**
     * This method creates {@link XmlObject} of type {@link PHSFellowshipSupplementalDocument} by populating data from the given
     * {@link ProposalDevelopmentDocument}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocument}
     * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(ProposalDevelopmentDocument)
     */
    public XmlObject getFormObject(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getPHSFellowshipSupplemental12();
    }

    public String getFormName() {
        return "PHS_Fellowship_Supplemental_1_2-V1.2";
    }

    public String getNamespace() {
        return "http://apply.grants.gov/forms/PHS_Fellowship_Supplemental_1_2-V1.2";
    }

    public class KirschsteinBean {
        String answer;
        Integer questionId;
        Integer questionNumber;
        Integer parentQuestionNumber;

        /**
         * Gets the answer attribute.
         * 
         * @return Returns the answer.
         */
        public String getAnswer() {
            return answer;
        }

        /**
         * Sets the answer attribute value.
         * 
         * @param answer The answer to set.
         */
        public void setAnswer(String answer) {
            this.answer = answer;
        }

        /**
         * Gets the questionId attribute.
         * 
         * @return Returns the questionId.
         */
        public Integer getQuestionId() {
            return questionId;
        }

        /**
         * Sets the questionId attribute value.
         * 
         * @param questionId The questionId to set.
         */
        public void setQuestionId(Integer questionId) {
            this.questionId = questionId;
        }

        /**
         * Gets the questionNumber attribute.
         * 
         * @return Returns the questionNumber.
         */
        public Integer getQuestionNumber() {
            return questionNumber;
        }

        /**
         * Sets the questionNumber attribute value.
         * 
         * @param questionNumber The questionNumber to set.
         */
        public void setQuestionNumber(Integer questionNumber) {
            this.questionNumber = questionNumber;
        }

        /**
         * Gets the parentQuestionNumber attribute.
         * 
         * @return Returns the parentQuestionNumber.
         */
        public Integer getParentQuestionNumber() {
            return parentQuestionNumber;
        }

        /**
         * Sets the parentQuestionNumber attribute value.
         * 
         * @param parentQuestionNumber The parentQuestionNumber to set.
         */
        public void setParentQuestionNumber(Integer parentQuestionNumber) {
            this.parentQuestionNumber = parentQuestionNumber;
        }

    }
}
