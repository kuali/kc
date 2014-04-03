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
package org.kuali.kra.s2s.generator.impl;

import org.apache.commons.lang.ObjectUtils;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.proposaldevelopment.budget.service.ProposalBudgetService;
import org.kuali.kra.s2s.generator.S2SBaseFormGenerator;
import org.kuali.kra.s2s.generator.S2SQuestionnairing;
import org.kuali.kra.s2s.service.S2SUtilService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This abstract class has methods that are common to all the versions of
 * PHS398FellowshipSupplementalBaseGenerator form.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class PHS398FellowshipSupplementalBaseGenerator extends
		S2SBaseFormGenerator implements S2SQuestionnairing{

	protected S2SUtilService s2sUtilService;
	protected ProposalBudgetService proposalBudgetService;
	protected ParameterService parameterService;
    

	protected static final String NSR_SUPPORT_NO = "No";
	protected static final String NSR_SUPPORT_YES = "Yes";
	protected static final String QUESTION_ANSWER_NO = "N";
	
	protected static final String TUITION_COST_ELEMENTS = "TUITION_COST_ELEMENTS";
	protected static final String STIPEND_COST_ELEMENTS = "STIPEND_COST_ELEMENTS";
	protected static final char STRING_SEPRATOR = '-';
	protected static final String SUB_CATEGORY_NOT_FOUND = "SUB CATEGORY NOT FOUND";
	protected static final String PROPOSAL_TYPE_CODE_NEW7 = "7";
	protected static final Integer QUESTIONNAIRE_ID_1 = 1;
	
    protected static final int INTRODUCTION_TO_APPLICATION = 97;
    protected static final int SPECIFIC_AIMS = 98;
    protected static final int INCLUSION_ENROLLMENT_REPORT = 102;
    protected static final int PROGRESS_REPORT_PUBLICATION_LIST = 103;
    protected static final int PROTECTION_OF_HUMAN_SUBJECTS = 104;
    protected static final int INCLUSION_OF_WOMEN_AND_MINORITIES = 105;
    protected static final int TARGETED_PLANNED_ENROLLMENT = 106;
    protected static final int INCLUSION_OF_CHILDREN = 107;
    protected static final int VERTEBRATE_ANIMALS = 108;
    protected static final int SELECT_AGENT_RESEARCH = 109;
    protected static final int RESOURCE_SHARING_PLANS = 110;
    protected static final int RESPECTIVE_CONTRIBUTIONS = 88;
    protected static final int SELECTION_OF_SPONSOR_AND_INSTITUTION = 89;
    protected static final int RESPONSIBLE_CONDUCT_OF_RESEARCH = 90;
    protected static final int RESEARCH_STRATEGY = 127;
    protected static final int CONCURRENT_SUPPORT = 91;
    protected static final int FELLOWSHIP = 92;
    protected static final int DISSERTATION = 93;
    protected static final int ACTIVITIES = 94;

    protected static final Comparator<KirschsteinBean> BY_QUESTION_NUMBER =  new Comparator<KirschsteinBean>() {
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

	/**
	 * 
	 * Constructs a PHS398FellowshipSupplementalBaseGenerator.java.
	 */
	public PHS398FellowshipSupplementalBaseGenerator() {
		s2sUtilService = KcServiceLocator.getService(S2SUtilService.class);
        proposalBudgetService = KcServiceLocator.getService(ProposalBudgetService.class);
		parameterService = KcServiceLocator.getService(ParameterService.class);
	}
    protected List<String> getCostElementsByParam(String costElementParam) {
        String costElementsParamValue = parameterService.getParameterValueAsString(ProposalDevelopmentDocument.class, costElementParam);
        String[] costElements = costElementsParamValue.split(",");
        List<String> costElementList = new ArrayList<String>();
        for (int i = 0; i < costElements.length; i++) {
            costElementList.add(costElements[i]);
        }
        return costElementList;
    }

    public static class KirschsteinBean {
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
