package org.kuali.kra.s2s.backport;

import gov.grants.apply.forms.phs398CoverPageSupplement30V30.PHS398CoverPageSupplement30Document;
import gov.grants.apply.forms.phs398CoverPageSupplement30V30.PHS398CoverPageSupplement30Document.PHS398CoverPageSupplement30;
import gov.grants.apply.forms.phs398CoverPageSupplement30V30.PHS398CoverPageSupplement30Document.PHS398CoverPageSupplement30.ClinicalTrial;
import gov.grants.apply.forms.phs398CoverPageSupplement30V30.PHS398CoverPageSupplement30Document.PHS398CoverPageSupplement30.IncomeBudgetPeriod;
import gov.grants.apply.forms.phs398CoverPageSupplement30V30.PHS398CoverPageSupplement30Document.PHS398CoverPageSupplement30.StemCells;
import gov.grants.apply.forms.phs398CoverPageSupplement30V30.PHS398CoverPageSupplement30Document.PHS398CoverPageSupplement30.VertebrateAnimals;
import gov.grants.apply.system.globalLibraryV20.HumanNameDataType;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType;
import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.budget.distributionincome.BudgetProjectIncome;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.budget.bo.ProposalDevelopmentBudgetExt;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.questionnaire.answer.Answer;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.generator.impl.PHS398CoverPageSupplementBaseGenerator;
import org.kuali.kra.s2s.service.S2SBudgetCalculatorService;
import org.kuali.kra.service.RolodexService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class PHS398CoverPageSupplement_3_0V3_0Generator extends PHS398CoverPageSupplementBaseGenerator {

    protected static final String PROPOSAL_YNQ_QUESTION_114 = "114";
    protected static final String PROPOSAL_YNQ_QUESTION_115 = "115";
    protected static final String PROPOSAL_YNQ_QUESTION_116 = "116";
    protected static final String PROPOSAL_YNQ_QUESTION_117 = "117";
    protected static final String PROPOSAL_YNQ_QUESTION_118 = "118";
    protected static final String PROPOSAL_YNQ_QUESTION_119 = "119";
    protected static final String PROPOSAL_YNQ_QUESTION_120 = "120";
    protected static final String PROPOSAL_YNQ_QUESTION_145 = "145";
    protected static final String PROPOSAL_YNQ_QUESTION_146 = "146";
    protected static final String PROPOSAL_YNQ_QUESTION_147 = "147";
    protected static final String PROPOSAL_YNQ_QUESTION_148 = "148";
    public static final String INCREASED_REGISTRATION_NUMBER = "149";
    protected static final int PROJECT_INCOME_DESCRIPTION_MAX_LENGTH = 150;
    private static final int MAX_EUTHANASIA_METHOD_DESC = 1000;

    private List<Answer> answers;

    private RolodexService rolodexService;
    protected S2SBudgetCalculatorService s2sBudgetCalculatorService;

    public PHS398CoverPageSupplement_3_0V3_0Generator() {
        s2sBudgetCalculatorService = KraServiceLocator.getService(S2SBudgetCalculatorService.class);
        rolodexService = KraServiceLocator.getService(RolodexService.class);
    }

    private PHS398CoverPageSupplement30Document getCoverPageSupplement() {
        PHS398CoverPageSupplement30Document coverPageSupplementDocument = PHS398CoverPageSupplement30Document.Factory.newInstance();
        PHS398CoverPageSupplement30 coverPageSupplement = PHS398CoverPageSupplement30.Factory.newInstance();

        answers = s2sUtilService.getQuestionnaireAnswers(pdDoc.getDevelopmentProposal(), getNamespace(), getFormName());
        coverPageSupplement.setFormVersion(FormVersion.v3_0.getVersion());
        coverPageSupplement.setClinicalTrial(getClinicalTrial());
        setIsInventionsAndPatentsAndIsPreviouslyReported(coverPageSupplement);
        setFormerPDNameAndIsChangeOfPDPI(coverPageSupplement);
        setFormerInstitutionNameAndChangeOfInstitution(coverPageSupplement);
        ProposalDevelopmentBudgetExt budget = null;
        try {
            BudgetDocument budgetDoc = s2sBudgetCalculatorService.getFinalBudgetVersion(pdDoc);
            budget = (ProposalDevelopmentBudgetExt)  (budgetDoc != null ? budgetDoc.getBudget() : null);
        } catch (S2SException e) {
            throw new RuntimeException(e);
        }

        if (budget != null) {
            setIncomeBudgetPeriods(coverPageSupplement, budget.getBudgetProjectIncomes());
        } else {
            coverPageSupplement.setProgramIncome(YesNoDataType.N_NO);
        }

        StemCells stemCells = getStemCells();
        coverPageSupplement.setStemCells(stemCells);

        VertebrateAnimals vertebrateAnimals = getVertebrateAnimals();
        coverPageSupplement.setVertebrateAnimals(vertebrateAnimals);

        coverPageSupplementDocument.setPHS398CoverPageSupplement30(coverPageSupplement);
        return coverPageSupplementDocument;
    }

    private ClinicalTrial getClinicalTrial() {

        ClinicalTrial clinicalTrial = ClinicalTrial.Factory.newInstance();
        String answer = getAnswer(IS_CLINICAL_TRIAL);
        if (answer != null && !answer.equals(NOT_ANSWERED)) {
            if (YnqConstant.YES.code().equals(answer)) {
                clinicalTrial.setIsClinicalTrial(YesNoDataType.Y_YES);
                setClinicalTrialSubQuestions(clinicalTrial);
            } else {
                clinicalTrial.setIsClinicalTrial(YesNoDataType.N_NO);
            }
        }
        return clinicalTrial;
    }

    private void setClinicalTrialSubQuestions(ClinicalTrial clinicalTrial) {

        String subAnswer = getAnswer(PHASE_III_CLINICAL_TRIAL);
        if (subAnswer != null && !subAnswer.equals(NOT_ANSWERED)) {
            if (YnqConstant.YES.code().equals(subAnswer)) {
                clinicalTrial.setIsPhaseIIIClinicalTrial(YesNoDataType.Y_YES);
            } else {
                clinicalTrial.setIsPhaseIIIClinicalTrial(YesNoDataType.N_NO);
            }
        } else {
            clinicalTrial.setIsPhaseIIIClinicalTrial(null);
        }
    }

    private VertebrateAnimals getVertebrateAnimals() {
        final VertebrateAnimals vertebrateAnimals = VertebrateAnimals.Factory.newInstance();
        String answer = getAnswer(PROPOSAL_YNQ_QUESTION_145);
        if (answer != null && !answer.equals(NOT_ANSWERED)) {
            if (YnqConstant.YES.code().equals(answer)) {
                setVertebrateAnimalsSubQuestions(vertebrateAnimals);
            }
        }
        return vertebrateAnimals;
    }

    private void setVertebrateAnimalsSubQuestions(VertebrateAnimals vertebrateAnimals) {
        String answer = getAnswer(PROPOSAL_YNQ_QUESTION_146);
        if (answer != null && YnqConstant.YES.code().equals(answer)) {
            vertebrateAnimals.setAnimalEuthanasiaIndicator(YesNoDataType.Y_YES);
        } else if (answer != null && YnqConstant.NO.code().equals(answer)) {
            vertebrateAnimals.setAnimalEuthanasiaIndicator(YesNoDataType.N_NO);
        }

        answer = getAnswer(PROPOSAL_YNQ_QUESTION_147);
        if (answer != null && YnqConstant.YES.code().equals(answer)) {
            vertebrateAnimals.setAVMAConsistentIndicator(YesNoDataType.Y_YES);
        } else if (answer != null && YnqConstant.NO.code().equals(answer)) {
            vertebrateAnimals.setAVMAConsistentIndicator(YesNoDataType.N_NO);
            answer = getAnswer(PROPOSAL_YNQ_QUESTION_148);
            if (answer != null) {
                vertebrateAnimals.setEuthanasiaMethodDescription(StringUtils.substring(answer.trim(), 0, MAX_EUTHANASIA_METHOD_DESC));
            }
        }
    }

    private void setIsInventionsAndPatentsAndIsPreviouslyReported(PHS398CoverPageSupplement30 coverPageSupplement) {
        String answer = getAnswer(PROPOSAL_YNQ_QUESTION_118);
        if (answer != null && !answer.equals(NOT_ANSWERED)) {
            if (YnqConstant.YES.code().equals(answer)) {
                setInventionsAndPatentsAndIsPreviouslyReportedSubQuestions(coverPageSupplement);
            }
        }
    }

    private void setInventionsAndPatentsAndIsPreviouslyReportedSubQuestions(PHS398CoverPageSupplement30 coverPageSupplement) {
        String explanation = getAnswer(PROPOSAL_YNQ_QUESTION_119);
        if (explanation != null && !explanation.equals(NOT_ANSWERED)) {
            if (YnqConstant.YES.code().equals(explanation)) {
                coverPageSupplement.setIsInventionsAndPatents(YesNoDataType.Y_YES);
                String subQuestionExplanation = getAnswer(PROPOSAL_YNQ_QUESTION_120);
                if (subQuestionExplanation != null && !subQuestionExplanation.equals(NOT_ANSWERED)) {
                    if (YnqConstant.YES.code().equals(subQuestionExplanation)) {
                        coverPageSupplement.setIsPreviouslyReported(YesNoDataType.Y_YES);
                    } else if (YnqConstant.NO.code().equals(subQuestionExplanation)) {
                        coverPageSupplement.setIsPreviouslyReported(YesNoDataType.N_NO);
                    }
                } else {
                    coverPageSupplement.setIsPreviouslyReported(null);
                }

            } else if (YnqConstant.NO.code().equals(explanation)) {
                coverPageSupplement.setIsInventionsAndPatents(YesNoDataType.N_NO);
            }
        } else {
            coverPageSupplement.setIsInventionsAndPatents(null);
        }
    }

    private void setFormerPDNameAndIsChangeOfPDPI(PHS398CoverPageSupplement30 coverPageSupplement) {
        String answer = getAnswer(PROPOSAL_YNQ_QUESTION_114);
        String explanation = getAnswer(PROPOSAL_YNQ_QUESTION_115);
        if (YnqConstant.YES.code().equals(answer)) {
            coverPageSupplement.setIsChangeOfPDPI(YesNoDataType.Y_YES);
            if (explanation != null) {
                Rolodex rolodex = rolodexService.getRolodex(Integer.valueOf(explanation));
                HumanNameDataType formerPDName = globLibV20Generator.getHumanNameDataType(rolodex);
                if (formerPDName != null
                        && formerPDName.getFirstName() != null
                        && formerPDName.getLastName() != null) {
                    coverPageSupplement.setFormerPDName(formerPDName);
                }
            } else {
                coverPageSupplement.setFormerPDName(null);
            }
        } else {
            coverPageSupplement.setIsChangeOfPDPI(YesNoDataType.N_NO);
        }
    }

    private void setFormerInstitutionNameAndChangeOfInstitution(PHS398CoverPageSupplement30 coverPageSupplement) {
        String answer = getAnswer(PROPOSAL_YNQ_QUESTION_116);
        String explanation = getAnswer(PROPOSAL_YNQ_QUESTION_117);

        if (YnqConstant.YES.code().equals(answer)) {
            coverPageSupplement.setIsChangeOfInstitution(YesNoDataType.Y_YES);
            if (explanation != null) {
                coverPageSupplement.setFormerInstitutionName(explanation);
            } else {
                coverPageSupplement.setFormerInstitutionName(null);
            }
        } else {
            coverPageSupplement.setIsChangeOfInstitution(YesNoDataType.N_NO);
        }
    }

    private static void setIncomeBudgetPeriods(PHS398CoverPageSupplement30 coverPageSupplement, List<BudgetProjectIncome> projectIncomes) {
        if (projectIncomes.isEmpty()) {
            coverPageSupplement.setProgramIncome(YesNoDataType.N_NO);
        } else {
            coverPageSupplement.setProgramIncome(YesNoDataType.Y_YES);
        }
        coverPageSupplement.setIncomeBudgetPeriodArray(getIncomeBudgetPeriod(projectIncomes));
    }

    private static IncomeBudgetPeriod[] getIncomeBudgetPeriod(final List<BudgetProjectIncome> projectIncomes) {
        //TreeMap Used to maintain the order of the Budget periods.
        Map<Integer, IncomeBudgetPeriod> incomeBudgetPeriodMap = new TreeMap<Integer, IncomeBudgetPeriod>();
        BigDecimal anticipatedAmount;
        for (BudgetProjectIncome projectIncome : projectIncomes) {

            Integer budgetPeriodNumber = projectIncome.getBudgetPeriodNumber();
            IncomeBudgetPeriod incomeBudgPeriod = incomeBudgetPeriodMap
                    .get(budgetPeriodNumber);
            if (incomeBudgPeriod == null) {
                incomeBudgPeriod = IncomeBudgetPeriod.Factory.newInstance();
                incomeBudgPeriod.setBudgetPeriod(budgetPeriodNumber);
                anticipatedAmount = BigDecimal.ZERO;
            } else {
                anticipatedAmount = incomeBudgPeriod.getAnticipatedAmount();
            }
            anticipatedAmount = anticipatedAmount.add(projectIncome
                    .getProjectIncome().bigDecimalValue());
            incomeBudgPeriod.setAnticipatedAmount(anticipatedAmount);
            String description = getProjectIncomeDescription(projectIncome);
            if (description != null) {
                if (incomeBudgPeriod.getSource() != null) {
                    incomeBudgPeriod.setSource(incomeBudgPeriod.getSource()
                            + ";" + description);
                } else {
                    incomeBudgPeriod.setSource(description);
                }
            }
            incomeBudgetPeriodMap.put(budgetPeriodNumber, incomeBudgPeriod);
        }
        return incomeBudgetPeriodMap.values().toArray(new IncomeBudgetPeriod[] {});
    }

    protected static String getProjectIncomeDescription(BudgetProjectIncome projectIncome) {
        return projectIncome.getDescription() != null ? StringUtils.substring(projectIncome.getDescription(), 0, PROJECT_INCOME_DESCRIPTION_MAX_LENGTH) : null;
    }

    private StemCells getStemCells() {

        StemCells stemCells = StemCells.Factory.newInstance();

        String answer = getAnswer(IS_HUMAN_STEM_CELLS_INVOLVED);

        if (answer != null && !answer.equals(NOT_ANSWERED)) {
            if (YnqConstant.YES.code().equals(answer)) {
                stemCells.setIsHumanStemCellsInvolved(YesNoDataType.Y_YES);
                setStemCellsSubQuestions(stemCells);
            } else {
                stemCells.setIsHumanStemCellsInvolved(YesNoDataType.N_NO);
            }
        }
        return stemCells;
    }

    private void setStemCellsSubQuestions(StemCells stemCells) {
        String subAnswer = getAnswer(SPECIFIC_STEM_CELL_LINE);
        String childAnswer = null;
        if (subAnswer != null) {
            if (!subAnswer.equals(NOT_ANSWERED)) {
                if (YnqConstant.YES.code().equals(subAnswer)) {
                    stemCells.setStemCellsIndicator(YesNoDataType.N_NO);
                    childAnswer = getAnswer(INCREASED_REGISTRATION_NUMBER);
                } else {
                    stemCells.setStemCellsIndicator(YesNoDataType.Y_YES);
                }
            }
        }
        if (childAnswer != null) {
            if (FieldValueConstants.VALUE_UNKNOWN.equalsIgnoreCase(childAnswer)) {
                stemCells.setStemCellsIndicator(YesNoDataType.Y_YES);
            } else {
                List<String> cellLines = getCellLines(childAnswer);
                if (cellLines.size() > 0) {
                    stemCells.setCellLinesArray(cellLines.toArray(new String[] {}));
                }
            }
        }
    }

    public XmlObject getFormObject(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getCoverPageSupplement();
    }

    public String getFormName() {
        return "PHS398_CoverPageSupplement_3_0-V3.0";
    }

    private String getAnswer(String questionId) {
        String answerStr = null;
        for (Answer answer : answers) {
            if (questionId.equals(answer.getQuestion().getQuestionId())) {
                if (StringUtils.isNotBlank(answerStr)) {
                    if (answer.getAnswer() != null) {
                        answerStr += ("," + answer.getAnswer());
                    }
                }
                else {
                    answerStr = answer.getAnswer();
                }

            }
        }
        return answerStr;
    }
}