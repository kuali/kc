package org.kuali.kra.s2s.backport;


import gov.grants.apply.forms.phsFellowshipSupplemental20V20.CitizenshipDataType;
import gov.grants.apply.forms.phsFellowshipSupplemental31V31.*;
import gov.grants.apply.forms.phsFellowshipSupplemental31V31.DegreeTypeDataType;
import gov.grants.apply.forms.phsFellowshipSupplemental31V31.FieldOfTrainingDataType;
import gov.grants.apply.forms.phsFellowshipSupplemental31V31.PHSFellowshipSupplemental31Document.PHSFellowshipSupplemental31;
import gov.grants.apply.forms.phsFellowshipSupplemental31V31.PHSFellowshipSupplemental31Document.PHSFellowshipSupplemental31.InstitutionalEnvironment.InstitutionalEnvironmentCommitmenttoTraining;
import gov.grants.apply.forms.phsFellowshipSupplemental31V31.PHSFellowshipSupplemental31Document.PHSFellowshipSupplemental31.Sponsors;
import gov.grants.apply.forms.phsFellowshipSupplemental31V31.PHSFellowshipSupplemental31Document.PHSFellowshipSupplemental31.OtherResearchTrainingPlan;
import gov.grants.apply.forms.phsFellowshipSupplemental31V31.PHSFellowshipSupplemental31Document.PHSFellowshipSupplemental31.OtherResearchTrainingPlan.*;
import gov.grants.apply.forms.phsFellowshipSupplemental31V31.PHSFellowshipSupplemental31Document.PHSFellowshipSupplemental31.OtherResearchTrainingPlan.InclusionOfChildren;
import gov.grants.apply.forms.phsFellowshipSupplemental31V31.PHSFellowshipSupplemental31Document.PHSFellowshipSupplemental31.OtherResearchTrainingPlan.InclusionOfWomenAndMinorities;
import gov.grants.apply.forms.phsFellowshipSupplemental31V31.PHSFellowshipSupplemental31Document.PHSFellowshipSupplemental31.Budget;
import gov.grants.apply.forms.phsFellowshipSupplemental31V31.PHSFellowshipSupplemental31Document.PHSFellowshipSupplemental31.Budget.FederalStipendRequested;
import gov.grants.apply.forms.phsFellowshipSupplemental31V31.PHSFellowshipSupplemental31Document.PHSFellowshipSupplemental31.Budget.InstitutionalBaseSalary;
import gov.grants.apply.forms.phsFellowshipSupplemental31V31.PHSFellowshipSupplemental31Document.PHSFellowshipSupplemental31.Budget.SupplementationFromOtherSources;
import gov.grants.apply.forms.phsFellowshipSupplemental31V31.PHSFellowshipSupplemental31Document.PHSFellowshipSupplemental31.ResearchTrainingPlan;
import gov.grants.apply.forms.phsFellowshipSupplemental31V31.PHSFellowshipSupplemental31Document.PHSFellowshipSupplemental31.FellowshipApplicant.BackgroundandGoals;
import gov.grants.apply.forms.phsFellowshipSupplemental31V31.PHSFellowshipSupplemental31Document.PHSFellowshipSupplemental31.ResearchTrainingPlan.ProgressReportPublicationList;
import gov.grants.apply.forms.phsFellowshipSupplemental31V31.PHSFellowshipSupplemental31Document.PHSFellowshipSupplemental31.Introduction.IntroductionToApplication;
import gov.grants.apply.forms.phsFellowshipSupplemental31V31.PHSFellowshipSupplemental31Document.PHSFellowshipSupplemental31.OtherResearchTrainingPlan.ProtectionOfHumanSubjects;
import gov.grants.apply.forms.phsFellowshipSupplemental31V31.PHSFellowshipSupplemental31Document.PHSFellowshipSupplemental31.AdditionalInformation;
import gov.grants.apply.forms.phsFellowshipSupplemental31V31.PHSFellowshipSupplemental31Document.PHSFellowshipSupplemental31.AdditionalInformation.ConcurrentSupportDescription;
import gov.grants.apply.forms.phsFellowshipSupplemental31V31.PHSFellowshipSupplemental31Document.PHSFellowshipSupplemental31.AdditionalInformation.CurrentPriorNRSASupport;

import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.attachmentsV10.AttachmentGroupMin0Max100DataType;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType.Enum;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlObject;

import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.infrastructure.CitizenshipTypes;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentS2sQuestionnaireService;
import org.kuali.kra.proposaldevelopment.specialreview.ProposalSpecialReview;
import org.kuali.kra.questionnaire.Questionnaire;
import org.kuali.kra.questionnaire.QuestionnaireQuestion;
import org.kuali.kra.questionnaire.answer.Answer;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.question.Question;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.generator.impl.PHS398FellowshipSupplementalBaseGenerator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.*;


public class PHS398FellowshipSupplementalV3_1Generator extends PHS398FellowshipSupplementalBaseGenerator {


    private static final Logger LOG = Logger.getLogger(PHS398FellowshipSupplementalV3_1Generator.class);

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

    private static final String APPENDIX = "96";
    private static final String SPONSOR_COSPONSOR = "134";
    protected static final String LETTER_COLLAB_CONTRIB_CONSULT = "157";
    protected static final String PHS_FELLOW_INSTITUTION_ENVIRON_COMMITMENT = "158";
    protected static final String DATA_SAFETY_MONITORING_PLAN = "159";
    protected static final String PHS_FELLOW_AUTH_KEY_BIO_CHEM_RESOURCES = "160";
    protected static final int ARE_VERTEBRATE_ANIMALS_EUTHANISED = 146;
    protected static final String INCLUSION_OF_CHILDREN = "107";
    protected static final String INTRODUCTION_TO_APPLICATION = "97";
    protected static final String SPECIFIC_AIMS = "98";
    protected static final String RESEARCH_STRATEGY = "127";
    protected static final String RESPECTIVE_CONTRIBUTIONS = "88";
    protected static final String SELECTION_OF_SPONSOR_AND_INSTITUTION = "89";
    protected static final String PROGRESS_REPORT_PUBLICATION_LIST = "103";
    protected static final String RESPONSIBLE_CONDUCT_OF_RESEARCH = "90";
    protected static final String PROTECTION_OF_HUMAN_SUBJECTS = "104";
    protected static final String INCLUSION_OF_WOMEN_AND_MINORITIES = "105";
    protected static final String CONCURRENT_SUPPORT = "91";
    protected static final String VERTEBRATE_ANIMALS = "108";
    protected static final String SELECT_AGENT_RESEARCH = "109";
    protected static final String RESOURCE_SHARING_PLANS = "110";



    // Is method consistent with American Veterinary Medical Association (AVMA) guidelines?
    protected static final int CONSISTENT_AVMA_GUIDELINES = 147;
    // If NO to AVMA Guidelines, describe method and provide scientific justification in 1000 characters or less
    protected static final int NO_AVMA_METHOD_SCIENTIFIC_JUSTIFICATION = 148;
    protected static final int FIELD_OF_TRAINING = 200;
    protected static final String FELLOWSHIP_BACKGROUND_AND_GOALS = "156";

    public static final String TEMPORARY_VISA_ALSO_APPLIED_FOR_PERMANENT_RESIDENT_STATUS = "Temporary Visa also applied for permanent resident status";

    protected static final Comparator<KirschsteinBean> BY_QUESTION_NUMBER = new Comparator<KirschsteinBean>() {
        @Override
        public int compare(KirschsteinBean o1, KirschsteinBean o2) {
            if (o1 == o2) {
                return 0;
            }

            if (o1 != null && o2 != null) {
                return ObjectUtils.compare(o1.questionNumber, o2.questionNumber);
            }

            return o1 != null ? -1 : 1;
        }
    };

    protected PHSFellowshipSupplemental31Document getPHSFellowshipSupplemental31() {
        PHSFellowshipSupplemental31Document phsFellowshipSupplementalDocument = PHSFellowshipSupplemental31Document.Factory
                .newInstance();
        PHSFellowshipSupplemental31 phsFellowshipSupplemental = phsFellowshipSupplementalDocument
                .addNewPHSFellowshipSupplemental31();
        ResearchTrainingPlan researchTrainingPlan = phsFellowshipSupplemental.addNewResearchTrainingPlan();
        setNarrativeDataForResearchTrainingPlan(phsFellowshipSupplemental, researchTrainingPlan);
        setOtherResearchTrainingPlanVertebrate(phsFellowshipSupplemental);
        phsFellowshipSupplemental.setFormVersion(FormVersion.v3_1.getVersion());
        final AttachmentGroupMin0Max100DataType appendix = getAppendix();
        if (appendix != null) {
            phsFellowshipSupplemental.setAppendix(appendix);
        }
        setQuestionnaireData(phsFellowshipSupplemental);
        return phsFellowshipSupplementalDocument;
    }

    private void setOtherResearchTrainingPlanVertebrate(PHSFellowshipSupplemental31 phsFellowshipSupplemental) {
        OtherResearchTrainingPlan otherResearchTrainingPlan = phsFellowshipSupplemental.getOtherResearchTrainingPlan();
        if (otherResearchTrainingPlan == null) {
            otherResearchTrainingPlan = phsFellowshipSupplemental.addNewOtherResearchTrainingPlan();
        }
        List<? extends AnswerHeader> answers = findQuestionnaireWithAnswers(pdDoc.getDevelopmentProposal());
        for (AnswerHeader answerHeader : answers) {
            Questionnaire questionnaire = answerHeader.getQuestionnaire();
            List<? extends QuestionnaireQuestion> questionnaireQuestions = questionnaire.getQuestionnaireQuestions();
            for (QuestionnaireQuestion questionnaireQuestion : questionnaireQuestions) {
                Answer answerBO = getAnswer(questionnaireQuestion, answerHeader);
                String answer = answerBO != null ? answerBO.getAnswer() : null;
                Question question = questionnaireQuestion.getQuestion();
                Integer questionId = question.getQuestionIdAsInteger();
                if (answer != null) {
                    switch (questionId) {
                        case VERT:
                            // will the inclusion of vertebrate animals use be indefinite
                            otherResearchTrainingPlan.setVertebrateAnimalsIndefinite(getYesNoEnum(answer));
                            break;
                        case ARE_VERTEBRATE_ANIMALS_EUTHANISED:
                            // Are vertebrate animals euthanized
                            otherResearchTrainingPlan.setAreAnimalsEuthanized(getYesNoEnum(answer));
                            break;
                        case CONSISTENT_AVMA_GUIDELINES:
                            otherResearchTrainingPlan.setAVMAConsistentIndicator(getYesNoEnum(answer));
                            break;
                        case NO_AVMA_METHOD_SCIENTIFIC_JUSTIFICATION:
                            otherResearchTrainingPlan.setEuthanasiaMethodDescription(answer);
                            break;
                    }
                }
            }
        }

        AttachedFileDataType attachedFileDataType;
        for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            final String code = narrative.getNarrativeTypeCode();
            if (code != null) {
                if (code.equalsIgnoreCase(VERTEBRATE_ANIMALS)) {
                    attachedFileDataType = getAttachedFileType(narrative);
                    if (attachedFileDataType != null) {
                        VertebrateAnimals vertebrateAnimals = VertebrateAnimals.Factory.newInstance();
                        vertebrateAnimals.setAttFile(attachedFileDataType);
                        if (otherResearchTrainingPlan == null) {
                            otherResearchTrainingPlan = phsFellowshipSupplemental.addNewOtherResearchTrainingPlan();
                        }
                        otherResearchTrainingPlan.setVertebrateAnimals(vertebrateAnimals);
                    }
                } else if (code.equalsIgnoreCase(SELECT_AGENT_RESEARCH)) {
                    attachedFileDataType = getAttachedFileType(narrative);
                    if (attachedFileDataType != null) {
                        SelectAgentResearch selectAgentResearch = SelectAgentResearch.Factory.newInstance();
                        selectAgentResearch.setAttFile(attachedFileDataType);
                        if (otherResearchTrainingPlan == null) {
                            otherResearchTrainingPlan = phsFellowshipSupplemental.addNewOtherResearchTrainingPlan();
                        }
                        otherResearchTrainingPlan.setSelectAgentResearch(selectAgentResearch);
                    }
                } else if (code.equalsIgnoreCase(RESOURCE_SHARING_PLANS)) {
                    ResourceSharingPlan resourceSharingPlan = ResourceSharingPlan.Factory.newInstance();
                    attachedFileDataType = getAttachedFileType(narrative);
                    if (attachedFileDataType != null) {
                        resourceSharingPlan.setAttFile(attachedFileDataType);
                        if (otherResearchTrainingPlan == null) {
                            otherResearchTrainingPlan = phsFellowshipSupplemental.addNewOtherResearchTrainingPlan();
                        }
                        otherResearchTrainingPlan.setResourceSharingPlan(resourceSharingPlan);
                    }
                } else if (code.equalsIgnoreCase(PHS_FELLOW_AUTH_KEY_BIO_CHEM_RESOURCES)) {
                    KeyBiologicalAndOrChemicalResources keyBiologicalAndOrChemicalResources = KeyBiologicalAndOrChemicalResources.Factory.newInstance();
                    attachedFileDataType = getAttachedFileType(narrative);
                    if (attachedFileDataType != null) {
                        keyBiologicalAndOrChemicalResources.setAttFile(attachedFileDataType);
                        if (otherResearchTrainingPlan == null) {
                            otherResearchTrainingPlan = phsFellowshipSupplemental.addNewOtherResearchTrainingPlan();
                        }
                        otherResearchTrainingPlan.setKeyBiologicalAndOrChemicalResources(keyBiologicalAndOrChemicalResources);
                    }
                }
            }
        }
    }

    private void setQuestionnaireData(PHSFellowshipSupplemental31 phsFellowshipSupplemental) {
        Map<Integer, String> hmBudgetQuestions = new HashMap<Integer, String>();
        List<? extends AnswerHeader> answers = findQuestionnaireWithAnswers(pdDoc.getDevelopmentProposal());
        OtherResearchTrainingPlan otherResearchTrainingPlan = phsFellowshipSupplemental.getOtherResearchTrainingPlan();
        if (otherResearchTrainingPlan == null) {
            otherResearchTrainingPlan = phsFellowshipSupplemental.addNewOtherResearchTrainingPlan();
        }
        setHumanSubjectInvolvedAndVertebrateAnimalUsed(otherResearchTrainingPlan);
        AdditionalInformation additionalInfoType = phsFellowshipSupplemental.addNewAdditionalInformation();
        AdditionalInformation.GraduateDegreeSought graduateDegreeSought = AdditionalInformation.GraduateDegreeSought.Factory.newInstance();
        AdditionalInformation.StemCells stemCellstype = AdditionalInformation.StemCells.Factory.newInstance();
        List<KirschsteinBean> cvKirsch = new ArrayList<KirschsteinBean>();
        for (AnswerHeader answerHeader : answers) {
            Questionnaire questionnaire = answerHeader.getQuestionnaire();
            List<? extends QuestionnaireQuestion> questionnaireQuestions = questionnaire.getQuestionnaireQuestions();
            for (QuestionnaireQuestion questionnaireQuestion : questionnaireQuestions) {
                Answer answerBO = getAnswer(questionnaireQuestion, answerHeader);
                String answer = answerBO != null ? answerBO.getAnswer() : null;
                Question question = questionnaireQuestion.getQuestion();
                Integer questionNumber = questionnaireQuestion.getQuestionNumber();
                Integer parentQuestionNumber = questionnaireQuestion.getParentQuestionNumber();
                Integer questionId = question.getQuestionIdAsInteger();
                if (answer != null) {
                    switch (questionId) {
                        case HUMAN:
                            otherResearchTrainingPlan.setHumanSubjectsIndefinite(getYesNoEnum(answer));
                            break;
                        case CLINICAL:
                            // clinical trial
                            otherResearchTrainingPlan.setClinicalTrial(getYesNoEnum(answer));
                            break;
                        case PHASE3CLINICAL:
                            // phase 3 clinical trial
                            otherResearchTrainingPlan.setPhase3ClinicalTrial(getYesNoEnum(answer));
                            break;
                        case STEMCELLS:
                            // stem cells used
                            stemCellstype.setIsHumanStemCellsInvolved(getYesNoEnum(answer));
                            break;
                        case CELLLINEIND:
                            // stem cell line indicator
                            stemCellstype.setStemCellsIndicator(answer
                                    .equals(YnqConstant.NO.code()) ? YesNoDataType.Y_YES
                                    : YesNoDataType.N_NO);
                            break;
                        case STEMCELLLINES:
                            List<? extends Answer> answerList = getAnswers(questionnaireQuestion, answerHeader);
                            for (Answer questionnaireAnswerBO : answerList) {
                                String questionnaireSubAnswer = questionnaireAnswerBO.getAnswer();
                                if (questionnaireSubAnswer != null) {
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
                        case OTHER_DBOTH:
                            if (graduateDegreeSought.getDegreeType().equals(DegreeTypeDataType.OTH_OTHER)) {
                                graduateDegreeSought.setOtherDegreeTypeText(answer);
                            }
                            break;
                        case OTHER_DOCT:
                            graduateDegreeSought.setDegreeType(DegreeTypeDataType.DOTH_OTHER_DOCTORATE);
                            graduateDegreeSought.setOtherDegreeTypeText(answer);
                            break;
                        case BROAD_TRAINING:
                        case FIELD_OF_TRAINING:
                            if (!answer.toUpperCase().equals("SUB CATEGORY NOT FOUND")) {
                                final FieldOfTrainingDataType.Enum fieldOfTraining = FieldOfTrainingDataType.Enum.forString(answer);
                                additionalInfoType.setFieldOfTraining(fieldOfTraining);
                            }
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
                                    answer = FieldValueConstants.VALUE_UNKNOWN;
                                    questionId = KIRST_START_DT;
                                } else
                                    break;
                            }
                            if (questionId == KIRST_END_KNOWN) {
                                if (answer.equals("N")) {
                                    answer = FieldValueConstants.VALUE_UNKNOWN;
                                    questionId = KIRST_END_DT;
                                } else
                                    break;
                            }
                            if (questionId == KIRST_GRANT_KNOWN) {
                                if (answer.equals("N")) {
                                    answer = FieldValueConstants.VALUE_UNKNOWN;
                                    questionId = KIRST_GRANT_NUM;
                                } else
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
                } else if (answer == null) {
                    switch (questionId) {
                        case HUMAN:
                            otherResearchTrainingPlan.setHumanSubjectsIndefinite(null);
                            otherResearchTrainingPlan.setHumanSubjectsInvolved(null);
                            break;
                        case VERT:
                            otherResearchTrainingPlan.setVertebrateAnimalsIndefinite(null);
                            otherResearchTrainingPlan.setVertebrateAnimalsUsed(null);
                            break;
                        case CLINICAL:
                            otherResearchTrainingPlan.setClinicalTrial(null);
                            break;
                        case PHASE3CLINICAL:
                            if (otherResearchTrainingPlan.getClinicalTrial() == (YesNoDataType.Y_YES)) {
                                otherResearchTrainingPlan.setPhase3ClinicalTrial(null);
                            }
                            break;
                        case FIELD_OF_TRAINING:
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
        List<KirschsteinBean> cvType = new ArrayList<KirschsteinBean>();
        List<KirschsteinBean> cvStart = new ArrayList<KirschsteinBean>();
        List<KirschsteinBean> cvEnd = new ArrayList<KirschsteinBean>();
        List<KirschsteinBean> cvLevel = new ArrayList<KirschsteinBean>();
        List<KirschsteinBean> cvGrant = new ArrayList<KirschsteinBean>();

        KirschsteinBean kbBean1 = null;
        KirschsteinBean kbBean2 = null;
        KirschsteinBean kbBean3 = null;
        KirschsteinBean kbBean4 = null;
        KirschsteinBean kbBean5 = null;

        if (additionalInfoType.getCurrentPriorNRSASupportIndicator() != null) {
            if (additionalInfoType.getCurrentPriorNRSASupportIndicator().equals(YesNoDataType.Y_YES)) {
                KirschsteinBean kbBean;
                Collections.sort(cvKirsch, BY_QUESTION_NUMBER);
                for (KirschsteinBean aCvKirsch : cvKirsch) {
                    kbBean = aCvKirsch;
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
                    kbBean1 = cvLevel.get(j);
                    if (cvType.size() - 1 >= j) {
                        kbBean2 = cvType.get(j);
                    }
                    if (cvStart.size() - 1 >= j) {
                        kbBean3 = cvStart.get(j);
                    }
                    if (cvEnd.size() - 1 >= j) {
                        kbBean4 = cvEnd.get(j);
                    }
                    if (cvGrant.size() - 1 >= j) {
                        kbBean5 = cvGrant.get(j);
                    }
                    CurrentPriorNRSASupport nrsaSupportType = CurrentPriorNRSASupport.Factory.newInstance();
                    if (kbBean1 != null) {
                        nrsaSupportType.setLevel(CurrentPriorNRSASupport.Level.Enum.forString(kbBean1.getAnswer()));
                    }
                    if (kbBean2 != null) {
                        nrsaSupportType.setType(CurrentPriorNRSASupport.Type.Enum.forString(kbBean2.getAnswer()));
                    }
                    if (kbBean3 != null && !kbBean3.getAnswer().equals(FieldValueConstants.VALUE_UNKNOWN)) {
                        nrsaSupportType.setStartDate(s2sUtilService.convertDateStringToCalendar(kbBean3.getAnswer()));
                    }
                    if (kbBean4 != null && !kbBean4.getAnswer().equals(FieldValueConstants.VALUE_UNKNOWN)) {
                        nrsaSupportType.setEndDate(s2sUtilService.convertDateStringToCalendar(kbBean4.getAnswer()));
                    }
                    if (kbBean5 != null) {
                        nrsaSupportType.setGrantNumber(kbBean5.getAnswer());
                    }
                    currentPriorNRSASupportList.add(nrsaSupportType);
                }

            }
            additionalInfoType.setCurrentPriorNRSASupportArray(currentPriorNRSASupportList.toArray(new CurrentPriorNRSASupport[0]));
        }
        phsFellowshipSupplemental.setBudget(createBudgetElements(hmBudgetQuestions));
        setAdditionalInformation(additionalInfoType);
    }

    protected Budget createBudgetElements(Map<Integer, String> budgetMap) {
        Budget budget = Budget.Factory.newInstance();
        budget.setTuitionAndFeesRequested(YesNoDataType.N_NO);
        getInstitutionalBaseSalary(budget, budgetMap);
        getFederalStipendRequested(budget);
        getSupplementationFromOtherSources(budget, budgetMap);
        setTuitionRequestedYears(budget);
        return budget;
    }

    protected List<Answer> getAnswers(QuestionnaireQuestion questionnaireQuestion, AnswerHeader answerHeader) {
        List<Answer> returnAnswers = new ArrayList<Answer>();
        if (answerHeader != null) {
            List<? extends Answer> answers = answerHeader.getAnswers();
            for (Answer answer : answers) {
                if (answer.getQuestionnaireQuestionsIdFk().equals(questionnaireQuestion.getQuestionnaireQuestionsId())) {
                    returnAnswers.add(answer);
                }
            }
        }
        return returnAnswers;
    }

    /**
     * This method is to return YesNoDataType enum
     */
    protected Enum getYesNoEnum(String answer) {
        return answer.equals("Y") ? YesNoDataType.Y_YES : YesNoDataType.N_NO;
    }

    /*
     * This method is used to get TuitionRequestedYears data to Budget XMLObject from List of BudgetLineItem based on CostElement
     * value of TUITION_COST_ELEMENTS
     */
    protected void setTuitionRequestedYears(Budget budget) {
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
    protected void getSupplementationFromOtherSources(Budget budget, Map<Integer, String> hmBudgetQuestions) {

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
                        } catch (Exception ex) {
                        }
                        supplementationFromOtherSources.setType(hmBudgetQuestions.get(SUPP_TYPE).toString());

                    }
                }
            }
        }
    }

    /*
     * This method is used to get FederalStipendRequested XMLObject and set additional information data to it.
     */
    protected void getFederalStipendRequested(Budget budget) {
        FederalStipendRequested federalStipendRequested = FederalStipendRequested.Factory.newInstance();
        BudgetDocument budgetDoc = getBudgetDocument();
        if (budgetDoc != null) {
            BudgetDecimal sumOfLineItemCost = BudgetDecimal.ZERO;
            BudgetDecimal numberOfMonths = BudgetDecimal.ZERO;
            for (BudgetPeriod budgetPeriod : budgetDoc.getBudget().getBudgetPeriods()) {
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
     * This method is used to set data to InstitutionalBaseSalary XMLObject from budgetMap data for Budget
     */
    protected void getInstitutionalBaseSalary(Budget budget, Map<Integer, String> budgetMap) {
        InstitutionalBaseSalary institutionalBaseSalary = InstitutionalBaseSalary.Factory.newInstance();
        if (budgetMap.get(SENIOR_FELL) != null && budgetMap.get(SENIOR_FELL).toString().equals(YnqConstant.YES.code())) {
            if (budgetMap.get(BASE_SALARY) != null) {
                institutionalBaseSalary.setAmount(new BigDecimal(budgetMap.get(BASE_SALARY).toString()));
            }
            if (budgetMap.get(ACAD_PERIOD) != null) {
                institutionalBaseSalary.setAcademicPeriod(PHSFellowshipSupplemental31Document.PHSFellowshipSupplemental31.Budget.InstitutionalBaseSalary.AcademicPeriod.Enum.forString(budgetMap.get(ACAD_PERIOD).toString()));
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
    protected void setNarrativeDataForResearchTrainingPlan(PHSFellowshipSupplemental31 phsFellowshipSupplemental,
                                                           ResearchTrainingPlan researchTrainingPlan) {
        AttachedFileDataType attachedFileDataType;
        Sponsors sponsors = phsFellowshipSupplemental.addNewSponsors();
        OtherResearchTrainingPlan otherResearchTrainingPlan = null;
        PHSFellowshipSupplemental31.InstitutionalEnvironment institutionalEnvironment = phsFellowshipSupplemental.addNewInstitutionalEnvironment();
        PHSFellowshipSupplemental31.FellowshipApplicant fellowshipApplicant = phsFellowshipSupplemental.addNewFellowshipApplicant();
        PHSFellowshipSupplemental31.Introduction introduction = phsFellowshipSupplemental.addNewIntroduction();

        for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            final String code = narrative.getNarrativeTypeCode();
            if (code != null) {
                if (code.equalsIgnoreCase(INTRODUCTION_TO_APPLICATION)) {
                    attachedFileDataType = getAttachedFileType(narrative);
                    if (attachedFileDataType != null) {
                        IntroductionToApplication introductionToApplication = IntroductionToApplication.Factory.newInstance();
                        introductionToApplication.setAttFile(attachedFileDataType);
                        introduction.setIntroductionToApplication(introductionToApplication);
                    }
                } else if (code.equalsIgnoreCase(FELLOWSHIP_BACKGROUND_AND_GOALS)) {
                    attachedFileDataType = getAttachedFileType(narrative);
                    if (attachedFileDataType != null) {
                        BackgroundandGoals backgroundandGoals = BackgroundandGoals.Factory.newInstance();
                        backgroundandGoals.setAttFile(attachedFileDataType);
                        fellowshipApplicant.setBackgroundandGoals(backgroundandGoals);
                    }
                } else if (code.equalsIgnoreCase(SPECIFIC_AIMS)) {
                    attachedFileDataType = getAttachedFileType(narrative);
                    if (attachedFileDataType != null) {
                        ResearchTrainingPlan.SpecificAims specificAims = ResearchTrainingPlan.SpecificAims.Factory.newInstance();
                        specificAims.setAttFile(attachedFileDataType);
                        researchTrainingPlan.setSpecificAims(specificAims);
                    }
                } else if (code.equalsIgnoreCase(RESEARCH_STRATEGY)) {
                    attachedFileDataType = getAttachedFileType(narrative);
                    if (attachedFileDataType != null) {
                        ResearchTrainingPlan.ResearchStrategy researchStrategy = ResearchTrainingPlan.ResearchStrategy.Factory.newInstance();
                        researchStrategy.setAttFile(attachedFileDataType);
                        researchTrainingPlan.setResearchStrategy(researchStrategy);
                    }
                } else if (code.equalsIgnoreCase(RESPECTIVE_CONTRIBUTIONS)) {
                    attachedFileDataType = getAttachedFileType(narrative);
                    if (attachedFileDataType != null) {
                        ResearchTrainingPlan.RespectiveContribution respectiveContribution = ResearchTrainingPlan.RespectiveContribution.Factory.newInstance();
                        respectiveContribution.setAttFile(attachedFileDataType);
                        researchTrainingPlan.setRespectiveContribution(respectiveContribution);
                    }
                } else if (code.equalsIgnoreCase(SELECTION_OF_SPONSOR_AND_INSTITUTION)) {
                    attachedFileDataType = getAttachedFileType(narrative);
                    if (attachedFileDataType != null) {
                        ResearchTrainingPlan.SponsorandInstitution sponsorAndInstitution = ResearchTrainingPlan.SponsorandInstitution.Factory
                                .newInstance();
                        sponsorAndInstitution.setAttFile(attachedFileDataType);
                        researchTrainingPlan.setSponsorandInstitution(sponsorAndInstitution);
                    }
                } else if (code.equalsIgnoreCase(PROGRESS_REPORT_PUBLICATION_LIST)) {
                    attachedFileDataType = getAttachedFileType(narrative);
                    if (attachedFileDataType != null) {
                        ResearchTrainingPlan.ProgressReportPublicationList progressReportPublicationList = ProgressReportPublicationList.Factory
                                .newInstance();
                        progressReportPublicationList.setAttFile(attachedFileDataType);
                        researchTrainingPlan.setProgressReportPublicationList(progressReportPublicationList);
                    }
                } else if (code.equalsIgnoreCase(RESPONSIBLE_CONDUCT_OF_RESEARCH)) {
                    attachedFileDataType = getAttachedFileType(narrative);
                    if (attachedFileDataType != null) {
                        ResearchTrainingPlan.TrainingInResponsibleConductOfResearch responsibleConductOfResearch = ResearchTrainingPlan.TrainingInResponsibleConductOfResearch.Factory
                                .newInstance();
                        responsibleConductOfResearch.setAttFile(attachedFileDataType);
                        researchTrainingPlan.setTrainingInResponsibleConductOfResearch(responsibleConductOfResearch);
                    }
                }
                // -- Sponsor(s), Collaborator(s), and Consultant(s) Section
                else if (code.equalsIgnoreCase(SPONSOR_COSPONSOR)) {
                    attachedFileDataType = getAttachedFileType(narrative);
                    if (attachedFileDataType != null) {
                        Sponsors.SponsorAndCoSponsorStatements sponsorCosponsorInfo = sponsors.addNewSponsorAndCoSponsorStatements();
                        sponsorCosponsorInfo.setAttFile(attachedFileDataType);
                    }
                } else if (code.equalsIgnoreCase(LETTER_COLLAB_CONTRIB_CONSULT)) {
                    attachedFileDataType = getAttachedFileType(narrative);
                    if (attachedFileDataType != null) {
                        Sponsors.LettersOfSupport lettersOfSupport = Sponsors.LettersOfSupport.Factory.newInstance();
                        lettersOfSupport.setAttFile(attachedFileDataType);
                        sponsors.setLettersOfSupport(lettersOfSupport);
                    }
                }
                // Institutional Environment and Commitment to Training Section
                else if (code.equalsIgnoreCase(PHS_FELLOW_INSTITUTION_ENVIRON_COMMITMENT)) {
                    attachedFileDataType = getAttachedFileType(narrative);
                    if (attachedFileDataType != null) {
                        InstitutionalEnvironmentCommitmenttoTraining institutionalEnvironmentCommitmenttoTraining =
                                InstitutionalEnvironmentCommitmenttoTraining.Factory.newInstance();
                        institutionalEnvironmentCommitmenttoTraining.setAttFile(attachedFileDataType);
                        institutionalEnvironment.setInstitutionalEnvironmentCommitmenttoTraining(institutionalEnvironmentCommitmenttoTraining);
                    }
                }
                // Other research training plan section
                else if (code.equalsIgnoreCase(PROTECTION_OF_HUMAN_SUBJECTS)) {
                    attachedFileDataType = getAttachedFileType(narrative);
                    if (attachedFileDataType != null) {
                        ProtectionOfHumanSubjects protectionOfHumanSubjects = ProtectionOfHumanSubjects.Factory.newInstance();
                        protectionOfHumanSubjects.setAttFile(attachedFileDataType);
                        if (otherResearchTrainingPlan == null) {
                            otherResearchTrainingPlan = phsFellowshipSupplemental.addNewOtherResearchTrainingPlan();
                        }
                        otherResearchTrainingPlan.setProtectionOfHumanSubjects(protectionOfHumanSubjects);
                    }
                } else if (code.equalsIgnoreCase(DATA_SAFETY_MONITORING_PLAN)) {
                    attachedFileDataType = getAttachedFileType(narrative);
                    if (attachedFileDataType != null) {
                        DataSafetyMonitoringPlan dataSafetyMonitoringPlan = DataSafetyMonitoringPlan.Factory.newInstance();
                        dataSafetyMonitoringPlan.setAttFile(attachedFileDataType);
                        if (otherResearchTrainingPlan == null) {
                            otherResearchTrainingPlan = phsFellowshipSupplemental.addNewOtherResearchTrainingPlan();
                        }
                        otherResearchTrainingPlan.setDataSafetyMonitoringPlan(dataSafetyMonitoringPlan);
                    }
                } else if (code.equalsIgnoreCase(INCLUSION_OF_WOMEN_AND_MINORITIES)) {
                    attachedFileDataType = getAttachedFileType(narrative);
                    if (attachedFileDataType != null) {
                        InclusionOfWomenAndMinorities inclusionOfWomenAndMinorities = InclusionOfWomenAndMinorities.Factory
                                .newInstance();
                        inclusionOfWomenAndMinorities.setAttFile(attachedFileDataType);
                        if (otherResearchTrainingPlan == null) {
                            otherResearchTrainingPlan = phsFellowshipSupplemental.addNewOtherResearchTrainingPlan();
                        }
                        otherResearchTrainingPlan.setInclusionOfWomenAndMinorities(inclusionOfWomenAndMinorities);
                    }
                } else if (code.equalsIgnoreCase(INCLUSION_OF_CHILDREN)) {
                    attachedFileDataType = getAttachedFileType(narrative);
                    if (attachedFileDataType != null) {
                        InclusionOfChildren inclusionOfChildren = InclusionOfChildren.Factory.newInstance();
                        inclusionOfChildren.setAttFile(attachedFileDataType);
                        if (otherResearchTrainingPlan == null) {
                            otherResearchTrainingPlan = phsFellowshipSupplemental.addNewOtherResearchTrainingPlan();
                        }
                        otherResearchTrainingPlan.setInclusionOfChildren(inclusionOfChildren);
                    }
                }
            }
        }

        setMandatoryAttachmentsOnResearchTrainingPlan(researchTrainingPlan);
        setMandatoryAttachmentsOnFellowshipApplicant(fellowshipApplicant);
    }

    private void setMandatoryAttachmentsOnFellowshipApplicant(PHSFellowshipSupplemental31.FellowshipApplicant fellowshipApplicant) {
        if(fellowshipApplicant.getBackgroundandGoals() == null) {
            fellowshipApplicant.setBackgroundandGoals(BackgroundandGoals.Factory.newInstance());
        }
    }

    private void setMandatoryAttachmentsOnResearchTrainingPlan(ResearchTrainingPlan researchTrainingPlan) {
        if(researchTrainingPlan.getSpecificAims() == null) {
            researchTrainingPlan.setSpecificAims(ResearchTrainingPlan.SpecificAims.Factory.newInstance());
        }
        if(researchTrainingPlan.getResearchStrategy() == null) {
            researchTrainingPlan.setResearchStrategy(ResearchTrainingPlan.ResearchStrategy.Factory.newInstance());
        }
        if(researchTrainingPlan.getRespectiveContribution() == null) {
            researchTrainingPlan.setRespectiveContribution(ResearchTrainingPlan.RespectiveContribution.Factory.newInstance());
        }
        if(researchTrainingPlan.getSponsorandInstitution() == null) {
            researchTrainingPlan.setSponsorandInstitution(ResearchTrainingPlan.SponsorandInstitution.Factory.newInstance());
        }
        if(researchTrainingPlan.getTrainingInResponsibleConductOfResearch() == null) {
            researchTrainingPlan.setTrainingInResponsibleConductOfResearch(ResearchTrainingPlan.TrainingInResponsibleConductOfResearch.Factory.newInstance());
        }
    }



    /**
     * This method is used to set HumanSubjectInvoved and VertebrateAnimalUsed XMLObject Data.
     *
     * @param researchTrainingPlan
     */
    protected void setHumanSubjectInvolvedAndVertebrateAnimalUsed(OtherResearchTrainingPlan researchTrainingPlan) {
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

    private List<? extends AnswerHeader> findQuestionnaireWithAnswers(DevelopmentProposal developmentProposal) {
        ProposalDevelopmentS2sQuestionnaireService questionnaireAnswerService = getProposalDevelopmentS2sQuestionnaireService();
        return questionnaireAnswerService.getProposalAnswerHeaderForForm(developmentProposal,
                "http://apply.grants.gov/forms/PHS_Fellowship_Supplemental_3_1-V3.1", "PHS_Fellowship_Supplemental_3_1");
    }

    private Answer getAnswer(QuestionnaireQuestion questionnaireQuestion, AnswerHeader answerHeader) {
        List<? extends Answer> answers = answerHeader.getAnswers();
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
        setCitizenshipAndAlternatePhoneNumber(additionalInformation);
        setAdditionalInformationConcurrentSupport(additionalInformation);
    }

    private void setCitizenshipAndAlternatePhoneNumber(AdditionalInformation additionalInformation) {
        ProposalPerson principalInvestigator = s2sUtilService.getPrincipalInvestigator(pdDoc);
        for (ProposalPerson proposalPerson : pdDoc.getDevelopmentProposal().getProposalPersons()) {
            if (proposalPerson.isInvestigator()) {
                CitizenshipTypes citizenShip = s2sUtilService.getCitizenship(proposalPerson);
                if(citizenShip!=null && StringUtils.isNotBlank(citizenShip.getCitizenShip())){
                    if (citizenShip.getCitizenShip().trim().equals(CitizenshipDataType.NON_U_S_CITIZEN_WITH_TEMPORARY_VISA.toString())) {
                        additionalInformation.setUSCitizen(YesNoDataType.N_NO);
                        additionalInformation.setNonUSCitizen(NonUSCitizenDataType.WITH_A_TEMPORARY_U_S_VISA);
                    }
                    else if (citizenShip.getCitizenShip().trim().equals(CitizenshipDataType.PERMANENT_RESIDENT_OF_U_S.toString())) {
                        additionalInformation.setUSCitizen(YesNoDataType.N_NO);
                        additionalInformation.setNonUSCitizen(NonUSCitizenDataType.WITH_A_PERMANENT_U_S_RESIDENT_VISA);
                    }
                    else if (citizenShip.getCitizenShip().trim().equals(
                            CitizenshipDataType.U_S_CITIZEN_OR_NONCITIZEN_NATIONAL.toString())) {
                        additionalInformation.setUSCitizen(YesNoDataType.Y_YES);
                    }
                    else if (citizenShip.getCitizenShip().trim().equals(
                            TEMPORARY_VISA_ALSO_APPLIED_FOR_PERMANENT_RESIDENT_STATUS)) {
                        additionalInformation.setUSCitizen(YesNoDataType.N_NO);
                        additionalInformation.setNonUSCitizen(NonUSCitizenDataType.WITH_A_TEMPORARY_U_S_VISA);
                        additionalInformation.setPermanentResidentByAwardIndicator(YesNoDataType.Y_YES);
                    }
                }
            }
        }
        if (additionalInformation.getUSCitizen() == null && additionalInformation.getNonUSCitizen() == null) {
            additionalInformation.setUSCitizen(YesNoDataType.N_NO);
        }

        if (principalInvestigator != null && principalInvestigator.getMobilePhoneNumber() != null) {
            additionalInformation.setAlernatePhoneNumber(principalInvestigator.getMobilePhoneNumber());
        }
    }

    private void setAdditionalInformationConcurrentSupport(AdditionalInformation additionalInformation) {
        additionalInformation.setConcurrentSupport(YesNoDataType.N_NO);
        AttachedFileDataType attachedFileDataType;
        for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            final String code = narrative.getNarrativeTypeCode();
            if (code != null) {
                if(code.equalsIgnoreCase(CONCURRENT_SUPPORT)) {
                    attachedFileDataType = getAttachedFileType(narrative);
                    if (attachedFileDataType != null) {
                        ConcurrentSupportDescription concurrentSupportDescription = ConcurrentSupportDescription.Factory
                                .newInstance();
                        concurrentSupportDescription.setAttFile(attachedFileDataType);
                        additionalInformation.setConcurrentSupport(YesNoDataType.Y_YES);
                        additionalInformation.setConcurrentSupportDescription(concurrentSupportDescription);
                    }
                }
            }
        }
    }

    private AttachmentGroupMin0Max100DataType getAppendix() {
        AttachmentGroupMin0Max100DataType attachmentGroupType = null;
        List<AttachedFileDataType> attachedFileDataTypeList = new ArrayList<AttachedFileDataType>();
        AttachedFileDataType attachedFileDataType;
        for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            final String code = narrative.getNarrativeTypeCode();
            if (code != null && code.equalsIgnoreCase(APPENDIX)) {
                attachedFileDataType = getAttachedFileType(narrative);
                if (attachedFileDataType != null) {
                    attachedFileDataTypeList.add(attachedFileDataType);
                }
            }
        }
        if(attachedFileDataTypeList.size() != 0) {
            attachmentGroupType = AttachmentGroupMin0Max100DataType.Factory.newInstance();
            attachmentGroupType.setAttachedFileArray(attachedFileDataTypeList.toArray(new AttachedFileDataType[attachedFileDataTypeList
                    .size()]));
        }
        return attachmentGroupType;
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
        BigDecimal monthCount = BudgetDecimal.ZERO.bigDecimalValue();
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
        BigDecimal startMonthFraction = new BudgetDecimal(startMonthDays).bigDecimalValue().divide(new BudgetDecimal(startMonthMaxDays).bigDecimalValue(), RoundingMode.HALF_UP);

        int endMonthDays = endDate.get(Calendar.DATE);
        int endMonthMaxDays = endDate.getActualMaximum(Calendar.DATE);

        BigDecimal endMonthFraction = new BudgetDecimal(endMonthDays).bigDecimalValue().divide(new BudgetDecimal(endMonthMaxDays).bigDecimalValue(), RoundingMode.HALF_UP);

        startDate.set(Calendar.DATE, 1);
        endDate.set(Calendar.DATE, 1);

        while (startDate.getTimeInMillis() < endDate.getTimeInMillis()) {
            startDate.set(Calendar.MONTH, startDate.get(Calendar.MONTH) + 1);
            fullMonthCount++;
        }
        fullMonthCount = fullMonthCount - 1;
        monthCount = monthCount.add(new BudgetDecimal(fullMonthCount).bigDecimalValue()).add(startMonthFraction).add(endMonthFraction);
        return new BudgetDecimal(monthCount);
    }

    /**
     * This method creates {@link XmlObject} of type {@link PHSFellowshipSupplemental31Document} by populating data from the given
     * {@link ProposalDevelopmentDocument}
     *
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocument}
     */
    public XmlObject getFormObject(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getPHSFellowshipSupplemental31();
    }

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

    private ProposalDevelopmentS2sQuestionnaireService getProposalDevelopmentS2sQuestionnaireService() {
        return KraServiceLocator.getService(ProposalDevelopmentS2sQuestionnaireService.class);
    }

    @Override
    public String getFormName() {
        return "PHS_Fellowship_Supplemental_3_1";
    }

    @Override
    public String getNamespace() {
        return "http://apply.grants.gov/forms/PHS_Fellowship_Supplemental_3_1-V3.1";
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
