/*
 * Copyright 2005-2013 The Kuali Foundation.
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
package org.kuali.kra.s2s.phs398coverpagesupplement;


import gov.grants.apply.forms.phs398CoverPageSupplement20V20.PHS398CoverPageSupplement20Document;
import gov.grants.apply.forms.phs398CoverPageSupplement20V20.PHS398CoverPageSupplement20Document.PHS398CoverPageSupplement20;
import gov.grants.apply.forms.phs398CoverPageSupplement20V20.PHS398CoverPageSupplement20Document.PHS398CoverPageSupplement20.ClinicalTrial;
import gov.grants.apply.forms.phs398CoverPageSupplement20V20.PHS398CoverPageSupplement20Document.PHS398CoverPageSupplement20.IncomeBudgetPeriod;
import gov.grants.apply.forms.phs398CoverPageSupplement20V20.PHS398CoverPageSupplement20Document.PHS398CoverPageSupplement20.PDPI;
import gov.grants.apply.forms.phs398CoverPageSupplement20V20.PHS398CoverPageSupplement20Document.PHS398CoverPageSupplement20.StemCells;
import gov.grants.apply.system.globalLibraryV20.HumanNameDataType;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType.Enum;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.framework.rolodex.RolodexService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.ynq.ProposalYnq;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.budget.distributionincome.BudgetProjectIncome;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.questionnaire.answer.Answer;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.answer.QuestionnaireAnswerService;
import org.kuali.kra.s2s.generator.impl.PHS398CoverPageSupplementBaseGenerator;
import org.kuali.kra.s2s.service.S2SBudgetCalculatorService;
import org.kuali.kra.s2s.util.S2SConstants;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class for generating the XML object for grants.gov
 * PHS398CoverPageSupplementV1_3. Form is generated using XMLBean classes and is
 * based on PHS398CoverPageSupplement schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class PHS398CoverPageSupplement_2_0_V2Generator extends
		PHS398CoverPageSupplementBaseGenerator {
    
    private static final String YNQANSWER_121 = "121";
    protected static final String PROPOSAL_YNQ_QUESTION_114 = "114";
    protected static final String PROPOSAL_YNQ_QUESTION_115 = "115";
    protected static final String PROPOSAL_YNQ_QUESTION_116 = "116";
    protected static final String PROPOSAL_YNQ_QUESTION_117 = "117";
    protected static final String PROPOSAL_YNQ_QUESTION_118 = "118";
    protected static final String PROPOSAL_YNQ_QUESTION_119 = "119";
    protected static final String PROPOSAL_YNQ_QUESTION_120 = "120";
    protected static final int PROJECT_INCOME_DESCRIPTION_MAX_LENGTH = 150;
    protected S2SBudgetCalculatorService s2sBudgetCalculatorService;

    List<AnswerHeader> answerHeaders;
    private transient QuestionnaireAnswerService questionnaireAnswerService;
    private static final Log LOG = LogFactory
            .getLog(PHS398CoverPageSupplement_2_0_V2Generator.class);
    Enum ynqAnswer;
	/**
	 * 
	 * This method gives information of Cover Page Supplement such as PDPI
	 * details,Clinical Trail information,Contact person information.
	 * 
	 * @return coverPageSupplementDocument {@link XmlObject} of type
	 *         PHS398CoverPageSupplement20Document.
	 */
	private PHS398CoverPageSupplement20Document getCoverPageSupplement() {
	    PHS398CoverPageSupplement20Document coverPageSupplementDocument = PHS398CoverPageSupplement20Document.Factory
				.newInstance();
		PHS398CoverPageSupplement20 coverPageSupplement = PHS398CoverPageSupplement20.Factory
				.newInstance();
		answerHeaders = getQuestionnaireAnswers(pdDoc.getDevelopmentProposal(), true);
	    s2sBudgetCalculatorService = KcServiceLocator.getService(S2SBudgetCalculatorService.class);
		coverPageSupplement.setFormVersion(S2SConstants.FORMVERSION_2_0);
		coverPageSupplement.setPDPI(getPDPI());
		coverPageSupplement.setClinicalTrial(getClinicalTrial());
		setIsInventionsAndPatentsAndIsPreviouslyReported(coverPageSupplement);
		setFormerPDNameAndIsChangeOfPDPI(coverPageSupplement);
		setFormerInstitutionNameAndChangeOfInstitution(coverPageSupplement);
		BudgetDocument budgetDoc = null;
        try {
            budgetDoc = proposalBudgetService.getFinalBudgetVersion(pdDoc);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        if (budgetDoc != null && budgetDoc.getBudget() != null) {
            int numPeriods = budgetDoc.getBudget().getBudgetPeriods().size();
            setIncomeBudgetPeriods(coverPageSupplement, budgetDoc.getBudget()
                    .getBudgetProjectIncomes(),numPeriods);
        } else {
            coverPageSupplement.setProgramIncome(YesNoDataType.N_NO);
        }
        ynqAnswer = getYNQAnswer(YNQANSWER_121);
        coverPageSupplement.setDisclosurePermission(ynqAnswer);
		StemCells stemCells = getStemCells();
		coverPageSupplement.setStemCells(stemCells);
		coverPageSupplementDocument
				.setPHS398CoverPageSupplement20(coverPageSupplement);
		return coverPageSupplementDocument;
	}

	/**
	 * 
	 * This method gives the personal details such as Name, New Investigator,
	 * Degrees of Principal Investigator
	 * 
	 * @return PDPI object containing Principal Investigator details.
	 */
	private PDPI getPDPI() {
		PDPI pdpi = PDPI.Factory.newInstance();
		ProposalPerson PI = s2sUtilService.getPrincipalInvestigator(pdDoc);
		pdpi.setPDPIName(globLibV20Generator.getHumanNameDataType(PI));
		return pdpi;
	}

	private YesNoDataType.Enum getProposalYnQAnswer(ProposalYnq proposalYnq) {
		return proposalYnq.getAnswer().equals(
				S2SConstants.PROPOSAL_YNQ_ANSWER_Y) ? YesNoDataType.Y_YES
				: YesNoDataType.N_NO;
	}
	


	/**
	 * 
	 * This method is used to get Clinical Trial information
	 * 
	 * @return ClinicalTrial object containing Clinical Trail Details.
	 */
	private ClinicalTrial getClinicalTrial() {

        ClinicalTrial clinicalTrial = ClinicalTrial.Factory.newInstance();
        String answer = null;
        String subAnswer = null;
          answer = getAnswer(IS_CLINICAL_TRIAL);
        if (answer != null) {
            if (!answer.equals(NOT_ANSWERED)) {
                if (S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(answer)) {
                    clinicalTrial.setIsClinicalTrial(YesNoDataType.Y_YES);
                    subAnswer = getAnswer(PHASE_III_CLINICAL_TRIAL);
                        if (subAnswer != null && !subAnswer.equals(NOT_ANSWERED)) {
                            if (S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(subAnswer)) {
                                clinicalTrial.setIsPhaseIIIClinicalTrial(YesNoDataType.Y_YES);   
                            } else {
                                clinicalTrial.setIsPhaseIIIClinicalTrial(YesNoDataType.N_NO);   
                            }
                        }else{
                        	clinicalTrial.setIsPhaseIIIClinicalTrial(null);   
                        }
                } else {
                    clinicalTrial.setIsClinicalTrial(YesNoDataType.N_NO);
                }
            }
        }
        return clinicalTrial;
    }
	/*
     * This method will set the values to
     * setIsInventionsAndPatents,setIsPreviouslyReported based on condition
     */
    private void setIsInventionsAndPatentsAndIsPreviouslyReported(
            PHS398CoverPageSupplement20 coverPageSupplement) {
        String answer = getAnswer(PROPOSAL_YNQ_QUESTION_118);
        if (answer != null && !answer.equals(NOT_ANSWERED)) {
            if (S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(answer)) {
                String explanation = getAnswer(PROPOSAL_YNQ_QUESTION_119);
                if(explanation != null && !explanation.equals(NOT_ANSWERED)) {
                    if (S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(explanation)) {
                        coverPageSupplement.setIsInventionsAndPatents(YesNoDataType.Y_YES);
                        String subQuestionExplanation = getAnswer(PROPOSAL_YNQ_QUESTION_120);
                        if (subQuestionExplanation != null && !subQuestionExplanation.equals(NOT_ANSWERED)) {
                            if (S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(subQuestionExplanation)) {
                                coverPageSupplement.setIsPreviouslyReported(YesNoDataType.Y_YES);  
                            }else if(S2SConstants.PROPOSAL_YNQ_ANSWER_N.equals(subQuestionExplanation)) {
                                coverPageSupplement.setIsPreviouslyReported(YesNoDataType.N_NO);
                            }
                        } else {
                            coverPageSupplement.setIsPreviouslyReported(null);
                        }

                    } else if(S2SConstants.PROPOSAL_YNQ_ANSWER_N.equals(explanation)) {
                        coverPageSupplement.setIsInventionsAndPatents(YesNoDataType.N_NO);
                    }
                } else {
                    coverPageSupplement.setIsInventionsAndPatents(null);
                }
            }
        }
    }   

    /*
     * This method will set the values to setFormerPDName ,setIsChangeOfPDPI
     * based on condition
     */
    private void setFormerPDNameAndIsChangeOfPDPI(PHS398CoverPageSupplement20 coverPageSupplement) {
        String answer = getAnswer(PROPOSAL_YNQ_QUESTION_114);
        String explanation = getAnswer(PROPOSAL_YNQ_QUESTION_115);
        if (S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(answer)) {
            coverPageSupplement.setIsChangeOfPDPI(YesNoDataType.Y_YES);
            if (explanation != null) {
                RolodexService rolodexService = KcServiceLocator.getService(RolodexService.class);
                Rolodex rolodex = rolodexService.getRolodex(Integer.valueOf(explanation));
                HumanNameDataType formerPDName = globLibV20Generator
                        .getHumanNameDataType(rolodex);
                if (formerPDName != null
                        && formerPDName.getFirstName() != null
                        && formerPDName.getLastName() != null) {
                    coverPageSupplement.setFormerPDName(formerPDName);
                }
            }
            else{
                    coverPageSupplement.setFormerPDName(null);
            }
        } else {
            coverPageSupplement.setIsChangeOfPDPI(YesNoDataType.N_NO);
        }
    }

    /*
     * This method will set the values to setFormerInstitutionName
     * ,setIsChangeOfInstitution based on condition
     */
    private void setFormerInstitutionNameAndChangeOfInstitution(
            PHS398CoverPageSupplement20 coverPageSupplement) {
        String answer = getAnswer(PROPOSAL_YNQ_QUESTION_116);
        String explanation = getAnswer(PROPOSAL_YNQ_QUESTION_117);
        
        if (S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(answer)) {
            coverPageSupplement.setIsChangeOfInstitution(YesNoDataType.Y_YES);
            if (explanation != null) {
                coverPageSupplement.setFormerInstitutionName(explanation);
            }
            else {
                coverPageSupplement.setFormerInstitutionName(null);
            }   
        } else {
            coverPageSupplement.setIsChangeOfInstitution(YesNoDataType.N_NO);
        }
    }
    
    /*
     * This method will set values to income budget periods
     */
    private static void setIncomeBudgetPeriods(PHS398CoverPageSupplement20 coverPageSupplement,
            List<BudgetProjectIncome> projectIncomes, int numPeriods) {
        if (projectIncomes.isEmpty()) {
            coverPageSupplement.setProgramIncome(YesNoDataType.N_NO);
        } else {
            coverPageSupplement.setProgramIncome(YesNoDataType.Y_YES);
        }
        coverPageSupplement.setIncomeBudgetPeriodArray(getIncomeBudgetPeriod(projectIncomes));
    }
    
    /*
     * Method to sum up IncomeBudgetPeriod of different period Number
     */
    private static IncomeBudgetPeriod[] getIncomeBudgetPeriod(
            final List<BudgetProjectIncome> projectIncomes) {
        //TreeMap Used to maintain the order of the Budget periods.
        Map<Integer, IncomeBudgetPeriod> incomeBudgetPeriodMap = new TreeMap<Integer, IncomeBudgetPeriod>();
        BigDecimal anticipatedAmount;
        for (BudgetProjectIncome projectIncome : projectIncomes) {

            Integer budgetPeriodNumber = projectIncome.getBudgetPeriodNumber();
            IncomeBudgetPeriod incomeBudgPeriod = incomeBudgetPeriodMap
                    .get(budgetPeriodNumber);
            if (incomeBudgPeriod == null) {
                incomeBudgPeriod = IncomeBudgetPeriod.Factory.newInstance();
                incomeBudgPeriod.setBudgetPeriod(budgetPeriodNumber.intValue());
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
        Collection<IncomeBudgetPeriod> incomeBudgetPeriodCollection = incomeBudgetPeriodMap
                .values();
        return incomeBudgetPeriodCollection.toArray(new IncomeBudgetPeriod[0]);
    }
    /*
     * This method will get the project income description
     */
    protected static String getProjectIncomeDescription(BudgetProjectIncome projectIncome) {
        String description = null;
        if (projectIncome.getDescription() != null) {
            if (projectIncome.getDescription().length() > PROJECT_INCOME_DESCRIPTION_MAX_LENGTH) {
                description = projectIncome.getDescription().substring(0,
                        PROJECT_INCOME_DESCRIPTION_MAX_LENGTH);
            } else {
                description = projectIncome.getDescription();
            }
        }
        return description;
    }
	
	/**
	 * 
	 * This method is used to get information of Stem Cells for the form
	 * PHS398CoverPage
	 * 
	 * @return StemCells object containing information about Human stem Cells
	 *         involvement.
	 */
	private StemCells getStemCells() {

	    StemCells stemCells = StemCells.Factory.newInstance();  
	    Enum answers = YesNoDataType.N_NO;
	    String childAnswer = null;  
	    String answer = getAnswer(IS_HUMAN_STEM_CELLS_INVOLVED);
	    if (answer != null) {
	        if (!answer.equals(NOT_ANSWERED)) {
	            answers = S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(getAnswer(IS_HUMAN_STEM_CELLS_INVOLVED)) ? YesNoDataType.Y_YES : YesNoDataType.N_NO;
	            if (S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(answer)) {
	                stemCells.setIsHumanStemCellsInvolved(YesNoDataType.Y_YES);
	                String subAnswer = getAnswer(SPECIFIC_STEM_CELL_LINE);
	                if (subAnswer != null) {
	                    if(!subAnswer.equals(NOT_ANSWERED)) {
	                        if (S2SConstants.PROPOSAL_YNQ_ANSWER_Y.equals(subAnswer)) {
	                            stemCells.setStemCellsIndicator(YesNoDataType.N_NO);
	                            childAnswer = getAnswers(REGISTRATION_NUMBER);
	                        }
	                        else {
	                            stemCells.setStemCellsIndicator(YesNoDataType.Y_YES);
	                        }
	                    }
	                }
	                if (childAnswer != null) {
	                    if (S2SConstants.VALUE_UNKNOWN.equalsIgnoreCase(childAnswer)) {
	                        stemCells.setStemCellsIndicator(answers);
	                    } else {
	                        List<String> cellLines = getCellLines(childAnswer);
	                        if (cellLines.size() > 0) {
	                            stemCells.setCellLinesArray(cellLines.toArray(new String[0]));
	                        }
	                    }
	                }
	            } else {
	                stemCells.setIsHumanStemCellsInvolved(YesNoDataType.N_NO); 
	            }
	        }
	    }
	    return stemCells;
	}

	/*
     * This method will get the YNQ Answer for question id
     */
    private YesNoDataType.Enum getYNQAnswer(String questionID) {
        YesNoDataType.Enum answerType = null;
        String answer = getAnswer(questionID);
        if (answer != null && !answer.equals(NOT_ANSWERED)) {
            answerType = "Y".equals(answer) ? YesNoDataType.Y_YES
                : YesNoDataType.N_NO;
            return answerType;
        } else {
            return null;
        }
    }

    /*
     * This method will get the Answer for sub question
     */
    private String getAnswer(String questionId) {

        String answer = null;
        if (answerHeaders != null && !answerHeaders.isEmpty()) {
            for (AnswerHeader answerHeader : answerHeaders) {
                List<Answer> answerDetails = answerHeader.getAnswers();
                for (Answer answers : answerDetails) {
                    if (questionId.equals(answers.getQuestion().getQuestionId())) {
                        answer = answers.getAnswer();
                    }
                }
            }
        }
        return answer;   
    }

    /*
     * This method will get the childAnswer for sub question
     */
    private String getAnswers(String questionId) {

        String answer = null;
        String childAnswer = null;
        StringBuilder stringBuilder = new StringBuilder();
        if (answerHeaders != null && !answerHeaders.isEmpty()) {
            for (AnswerHeader answerHeader : answerHeaders) {
                List<Answer> answerDetails = answerHeader.getAnswers();
                for (Answer answers : answerDetails) {
                    if (questionId.equals(answers.getQuestion().getQuestionId())) {
                        answer = answers.getAnswer();
                        if (answer != null) {
                            if (!answer.equals(NOT_ANSWERED)) {
                                stringBuilder.append(answer);
                                stringBuilder.append(",");
                            }
                        }
                        childAnswer = stringBuilder.toString();
                    }
                }
            }
        }
        return childAnswer;
    }
	
	/**
	 * This method creates {@link XmlObject} of type
	 * {@link PHS398CoverPageSupplement20Document} by populating data from the
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
		return getCoverPageSupplement();
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
		PHS398CoverPageSupplement20 coverPageSupplement = (PHS398CoverPageSupplement20) xmlObject;
		PHS398CoverPageSupplement20Document coverPageSupplementDocument = PHS398CoverPageSupplement20Document.Factory
				.newInstance();
		coverPageSupplementDocument
				.setPHS398CoverPageSupplement20(coverPageSupplement);
		return coverPageSupplementDocument;
	}
}
