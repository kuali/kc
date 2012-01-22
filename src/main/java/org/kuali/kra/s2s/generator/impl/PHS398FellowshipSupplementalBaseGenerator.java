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

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.generator.S2SBaseFormGenerator;
import org.kuali.kra.s2s.generator.S2SQuestionnairing;
import org.kuali.kra.s2s.service.S2SBudgetCalculatorService;
import org.kuali.kra.s2s.service.S2SUtilService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

/**
 * This abstract class has methods that are common to all the versions of
 * PHS398FellowshipSupplementalBaseGenerator form.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class PHS398FellowshipSupplementalBaseGenerator extends
		S2SBaseFormGenerator implements S2SQuestionnairing{

	protected S2SUtilService s2sUtilService;
	protected S2SBudgetCalculatorService s2SBudgetCalculatorService;
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

	/**
	 * 
	 * Constructs a PHS398FellowshipSupplementalBaseGenerator.java.
	 */
	public PHS398FellowshipSupplementalBaseGenerator() {
		s2sUtilService = KraServiceLocator.getService(S2SUtilService.class);
		s2SBudgetCalculatorService = KraServiceLocator.getService(S2SBudgetCalculatorService.class);
		parameterService = KraServiceLocator.getService(ParameterService.class);
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

}
