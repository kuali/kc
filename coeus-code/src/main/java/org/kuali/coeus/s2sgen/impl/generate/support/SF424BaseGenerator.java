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

import org.kuali.coeus.propdev.api.core.SubmissionInfoService;
import org.kuali.coeus.s2sgen.impl.budget.S2SBudgetCalculatorService;
import org.kuali.coeus.s2sgen.impl.datetime.S2SDateTimeService;
import org.kuali.coeus.s2sgen.impl.person.DepartmentalPersonService;
import org.kuali.coeus.s2sgen.impl.person.S2SProposalPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * This abstract class has methods that are common to all the versions of SF424 form.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class SF424BaseGenerator extends CommonSF424BaseGenerator {

    protected static final int APPLICANT_TYPE_1_INDEX = 0;
    protected static final int APPLICANT_TYPE_2_INDEX = 1;
    protected static final int APPLICANT_TYPE_3_INDEX = 2;
    
    public static final String ACTIVITY_TYPE_CODE_LS_SUFFIX_CONSTRUCTION = "C";
    public static final String ACTIVITY_TYPE_CODE_LS_SUFFIX_NONCONSTRUCTION = "N";
    public static final String ACTIVITY_TYPE_CODE_LS_SUFFIX_APPLICATION = "A";
    public static final String ACTIVITY_TYPE_CODE_LS_SUFFIX_PREAPPLICATION = "P";
    public static final int PROGRAM_ANNOUNCEMENT_TITLE_LENGTH = 120;
    public static final int AREAS_AFFECTED_MAX_LENGTH = 250;

    public static final String REVISIONCODE_STARTS_WITH_E = "E";
    public static final int CONGRESSIONAL_DISTRICTS_ATTACHMENT = 42;
    public static final int PROJECT_TITLE_ATTACHMENT = 41;
    public static final int ABSTRACTTYPE_CODE_AREAS_AFFECTED = 16;

    public static final String STATE_REVIEW_YES = "Y";
    public static final String STATE_REVIEW_NO = "N";
    public static final String STATE_REVIEW_NA = "X";
    public final static String PROPOSAL_YNQ_FEDERAL_DEBTS = "I7";

    public static final String CORE_SCHEMA_VERSION_1_0 = "1.0";
    protected static final String NON_CONSTRUCTION = "Non-Construction";

    protected static final String TARGET_CATEGORY_CODE_SUPPLIES = "43";
    protected static final String TARGET_CATEGORY_CODE_CONSTRUCTION = "40";
    protected static final String TARGET_CATEGORY_CODE_CONTRACTUAL = "04";
    protected static final String TARGET_CATEGORY_CODE_EQUIPMENT = "42";
    protected static final String TARGET_CATEGORY_CODE_OTHER_DIRECT_COSTS = "39";
    protected static final String TARGET_CATEGORY_CODE_EQUIPMENT_RENTAL = "45";
    protected static final String TARGET_CATEGORY_CODE_TRAVEL = "73";
    protected static final String TARGET_CATEGORY_CODE_FOREIGN_TRAVEL = "74";
    protected static final String TARGET_CATEGORY_CODE_PARTICIPANT_STIPENDS = "75";
    protected static final String TARGET_CATEGORY_CODE_PARTICIPANT_TUITION = "76";
    protected static final String TARGET_CATEGORY_CODE_PARTICIPANT_TRAVEL = "77";
    protected static final String TARGET_CATEGORY_CODE_PARTICIPANT_OTHER = "78";
    protected static final String TARGET_CATEGORY_CODE_PARTICIPANT_SUBSISTENCE = "79";
    protected static final String TARGET_CATEGORY_CODE_PUBLICATION_COSTS = "80";
    protected static final String TARGET_CATEGORY_CODE_CONSULTANT_COSTS = "81";
    protected static final String TARGET_CATEGORY_CODE_COMPUTER_SERVICES = "82";
    protected static final String TARGET_CATEGORY_TYPE_CODE_PERSONNEL = "P";

    protected static final String RATE_CLASS_TYPE_EMPLOYEE_BENEFITS = "E";
    protected static final String RATE_CLASS_TYPE_VACATION = "V";
    protected static final String PROGRAM_TYPE = "Non-Construction";

    protected static final String INCREASE_AWARD_CODE = "A";
    protected static final String DECREASE_AWARD_CODE = "B";
    protected static final String INCREASE_DURATION_CODE = "C";
    protected static final String DECREASE_DURATION_CODE = "D";
    protected static final String OTHER_SPECIFY_CODE = "E";
    protected static final String INCREASE_AWARD_INCREASE_DURATION_CODE = "AC";
    protected static final String INCREASE_AWARD_DECREASE_DURATION_CODE = "AD";
    protected static final String DECREASE_AWARD_INCREASE_DURATION_CODE = "BC";
    protected static final String DECREASE_AWARD_DECREASE_DURATION_CODE = "BD";

    protected static final int DEPARTMENT_NAME_MAX_LENGTH = 30;

    @Autowired
    @Qualifier("submissionInfoService")
    protected SubmissionInfoService submissionInfoService;

    @Autowired
    @Qualifier("departmentalPersonService")
    protected DepartmentalPersonService departmentalPersonService;

    @Autowired
    @Qualifier("s2SProposalPersonService")
    protected S2SProposalPersonService s2SProposalPersonService;

    @Autowired
    @Qualifier("s2SDateTimeService")
    protected S2SDateTimeService s2SDateTimeService;

    @Autowired
    @Qualifier("s2SBudgetCalculatorService")
    protected S2SBudgetCalculatorService s2sBudgetCalculatorService;

    public DepartmentalPersonService getDepartmentalPersonService() {
        return departmentalPersonService;
    }

    public void setDepartmentalPersonService(DepartmentalPersonService departmentalPersonService) {
        this.departmentalPersonService = departmentalPersonService;
    }

    public S2SBudgetCalculatorService getS2sBudgetCalculatorService() {
        return s2sBudgetCalculatorService;
    }

    public void setS2sBudgetCalculatorService(S2SBudgetCalculatorService s2sBudgetCalculatorService) {
        this.s2sBudgetCalculatorService = s2sBudgetCalculatorService;
    }

    public SubmissionInfoService getSubmissionInfoService() {
        return submissionInfoService;
    }

    public void setSubmissionInfoService(SubmissionInfoService submissionInfoService) {
        this.submissionInfoService = submissionInfoService;
    }

    public S2SProposalPersonService getS2SProposalPersonService() {
        return s2SProposalPersonService;
    }

    public void setS2SProposalPersonService(S2SProposalPersonService s2SProposalPersonService) {
        this.s2SProposalPersonService = s2SProposalPersonService;
    }

    public S2SDateTimeService getS2SDateTimeService() {
        return s2SDateTimeService;
    }

    public void setS2SDateTimeService(S2SDateTimeService s2SDateTimeService) {
        this.s2SDateTimeService = s2SDateTimeService;
    }
}
