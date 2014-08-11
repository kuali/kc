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
package org.kuali.coeus.s2sgen.impl.generate.support;


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
import org.kuali.coeus.common.api.ynq.YnqConstant;
import org.kuali.coeus.common.questionnaire.api.answer.AnswerHeaderContract;
import org.kuali.coeus.common.api.rolodex.RolodexContract;
import org.kuali.coeus.common.api.rolodex.RolodexService;
import org.kuali.coeus.common.budget.api.income.BudgetProjectIncomeContract;
import org.kuali.coeus.propdev.api.budget.ProposalDevelopmentBudgetExtContract;
import org.kuali.coeus.propdev.api.person.ProposalPersonContract;
import org.kuali.coeus.s2sgen.impl.budget.S2SCommonBudgetService;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;

import org.kuali.coeus.s2sgen.impl.util.FieldValueConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

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
@FormGenerator("PHS398CoverPageSupplement_2_0_V2Generator")
public class PHS398CoverPageSupplement_2_0_V2Generator extends
		PHS398CoverPageSupplementBaseGenerator {
    
    private static final Integer YNQANSWER_121 = 121;
    protected static final Integer PROPOSAL_YNQ_QUESTION_114 = 114;
    protected static final Integer PROPOSAL_YNQ_QUESTION_115 = 115;
    protected static final Integer PROPOSAL_YNQ_QUESTION_116 = 116;
    protected static final Integer PROPOSAL_YNQ_QUESTION_117 = 117;
    protected static final Integer PROPOSAL_YNQ_QUESTION_118 = 118;
    protected static final Integer PROPOSAL_YNQ_QUESTION_119 = 119;
    protected static final Integer PROPOSAL_YNQ_QUESTION_120 = 120;
    protected static final int PROJECT_INCOME_DESCRIPTION_MAX_LENGTH = 150;

    List<? extends AnswerHeaderContract> answerHeaders;
    private static final Log LOG = LogFactory
            .getLog(PHS398CoverPageSupplement_2_0_V2Generator.class);
    Enum ynqAnswer;

    @Value("http://apply.grants.gov/forms/PHS398_CoverPageSupplement_2_0-V2.0")
    private String namespace;

    @Value("PHS398_CoverPageSupplement_2_0-V2.0")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/PHS398_CoverPageSupplement_2_0-V2.0.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.phs398CoverPageSupplement20V20")
    private String packageName;

    @Value("185")
    private int sortIndex;

    @Autowired
    @Qualifier("rolodexService")
    private RolodexService rolodexService;

    @Autowired
    @Qualifier("s2SCommonBudgetService")
    private S2SCommonBudgetService s2SCommonBudgetService;
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
		answerHeaders = getPropDevQuestionAnswerService().getQuestionnaireAnswerHeaders(pdDoc.getDevelopmentProposal().getProposalNumber());
		coverPageSupplement.setFormVersion(FormVersion.v2_0.getVersion());
		coverPageSupplement.setPDPI(getPDPI());
		coverPageSupplement.setClinicalTrial(getClinicalTrial());
		setIsInventionsAndPatentsAndIsPreviouslyReported(coverPageSupplement);
		setFormerPDNameAndIsChangeOfPDPI(coverPageSupplement);
		setFormerInstitutionNameAndChangeOfInstitution(coverPageSupplement);
        ProposalDevelopmentBudgetExtContract budget = s2SCommonBudgetService.getBudget(pdDoc.getDevelopmentProposal());

        if (budget != null) {
            int numPeriods = budget.getBudgetPeriods().size();
            setIncomeBudgetPeriods(coverPageSupplement, budget
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
		ProposalPersonContract PI = s2SProposalPersonService.getPrincipalInvestigator(pdDoc);
		pdpi.setPDPIName(globLibV20Generator.getHumanNameDataType(PI));
		return pdpi;
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
          answer = getAnswer(IS_CLINICAL_TRIAL,answerHeaders);
        if (answer != null) {
            if (!answer.equals(NOT_ANSWERED)) {
                if (YnqConstant.YES.code().equals(answer)) {
                    clinicalTrial.setIsClinicalTrial(YesNoDataType.Y_YES);
                    subAnswer = getAnswer(PHASE_III_CLINICAL_TRIAL,answerHeaders);
                        if (subAnswer != null && !subAnswer.equals(NOT_ANSWERED)) {
                            if (YnqConstant.YES.code().equals(subAnswer)) {
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
        String answer = getAnswer(PROPOSAL_YNQ_QUESTION_118,answerHeaders);
        if (answer != null && !answer.equals(NOT_ANSWERED)) {
            if (YnqConstant.YES.code().equals(answer)) {
                String explanation = getAnswer(PROPOSAL_YNQ_QUESTION_119,answerHeaders);
                if(explanation != null && !explanation.equals(NOT_ANSWERED)) {
                    if (YnqConstant.YES.code().equals(explanation)) {
                        coverPageSupplement.setIsInventionsAndPatents(YesNoDataType.Y_YES);
                        String subQuestionExplanation = getAnswer(PROPOSAL_YNQ_QUESTION_120,answerHeaders);
                        if (subQuestionExplanation != null && !subQuestionExplanation.equals(NOT_ANSWERED)) {
                            if (YnqConstant.YES.code().equals(subQuestionExplanation)) {
                                coverPageSupplement.setIsPreviouslyReported(YesNoDataType.Y_YES);  
                            }else if(YnqConstant.NO.code().equals(subQuestionExplanation)) {
                                coverPageSupplement.setIsPreviouslyReported(YesNoDataType.N_NO);
                            }
                        } else {
                            coverPageSupplement.setIsPreviouslyReported(null);
                        }

                    } else if(YnqConstant.NO.code().equals(explanation)) {
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
        String answer = getAnswer(PROPOSAL_YNQ_QUESTION_114,answerHeaders);
        String explanation = getAnswer(PROPOSAL_YNQ_QUESTION_115,answerHeaders);
        if (YnqConstant.YES.code().equals(answer)) {
            coverPageSupplement.setIsChangeOfPDPI(YesNoDataType.Y_YES);
            if (explanation != null) {
                RolodexContract rolodex = rolodexService.getRolodex(Integer.valueOf(explanation));
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
        String answer = getAnswer(PROPOSAL_YNQ_QUESTION_116,answerHeaders);
        String explanation = getAnswer(PROPOSAL_YNQ_QUESTION_117,answerHeaders);
        
        if (YnqConstant.YES.code().equals(answer)) {
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
            List<? extends BudgetProjectIncomeContract> projectIncomes, int numPeriods) {
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
            final List<? extends BudgetProjectIncomeContract> projectIncomes) {
        //TreeMap Used to maintain the order of the Budget periods.
        Map<Integer, IncomeBudgetPeriod> incomeBudgetPeriodMap = new TreeMap<Integer, IncomeBudgetPeriod>();
        BigDecimal anticipatedAmount;
        for (BudgetProjectIncomeContract projectIncome : projectIncomes) {

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
    protected static String getProjectIncomeDescription(BudgetProjectIncomeContract projectIncome) {
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
	    String answer = getAnswer(IS_HUMAN_STEM_CELLS_INVOLVED,answerHeaders);
	    if (answer != null) {
	        if (!answer.equals(NOT_ANSWERED)) {
	            answers = YnqConstant.YES.code().equals(getAnswer(IS_HUMAN_STEM_CELLS_INVOLVED, answerHeaders)) ? YesNoDataType.Y_YES : YesNoDataType.N_NO;
	            if (YnqConstant.YES.code().equals(answer)) {
	                stemCells.setIsHumanStemCellsInvolved(YesNoDataType.Y_YES);
	                String subAnswer = getAnswer(SPECIFIC_STEM_CELL_LINE,answerHeaders);
	                if (subAnswer != null) {
	                    if(!subAnswer.equals(NOT_ANSWERED)) {
	                        if (YnqConstant.YES.code().equals(subAnswer)) {
	                            stemCells.setStemCellsIndicator(YesNoDataType.N_NO);
	                            childAnswer = getAnswers(REGISTRATION_NUMBER,answerHeaders);
	                        }
	                        else {
	                            stemCells.setStemCellsIndicator(YesNoDataType.Y_YES);
	                        }
	                    }
	                }
	                if (childAnswer != null) {
	                    if (FieldValueConstants.VALUE_UNKNOWN.equalsIgnoreCase(childAnswer)) {
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
    private YesNoDataType.Enum getYNQAnswer(Integer questionID) {
        YesNoDataType.Enum answerType = null;
        String answer = getAnswer(questionID,answerHeaders);
        if (answer != null && !answer.equals(NOT_ANSWERED)) {
            answerType = "Y".equals(answer) ? YesNoDataType.Y_YES
                : YesNoDataType.N_NO;
            return answerType;
        } else {
            return null;
        }
    }
	
	/**
	 * This method creates {@link XmlObject} of type
	 * {@link PHS398CoverPageSupplement20Document} by populating data from the
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
		return getCoverPageSupplement();
	}

    public RolodexService getRolodexService() {
        return rolodexService;
    }

    public void setRolodexService(RolodexService rolodexService) {
        this.rolodexService = rolodexService;
    }

    public S2SCommonBudgetService getS2SCommonBudgetService() {
        return s2SCommonBudgetService;
    }

    public void setS2SCommonBudgetService(S2SCommonBudgetService s2SCommonBudgetService) {
        this.s2SCommonBudgetService = s2SCommonBudgetService;
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
