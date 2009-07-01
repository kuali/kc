/*
 * Copyright 2008 The Kuali Foundation.
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

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.s2s.generator.S2SBaseFormGenerator;
import org.kuali.kra.s2s.service.S2SBudgetCalculatorService;
import org.kuali.kra.s2s.service.S2SUtilService;
import org.kuali.rice.kns.service.DateTimeService;

/**
 * This abstract class has methods that are common to all the versions of SF424 form.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class SF424BaseGenerator extends S2SBaseFormGenerator {
    protected S2SUtilService s2sUtilService;
    protected S2SBudgetCalculatorService s2sBudgetCalculatorService;
    protected DateTimeService dateTimeService;

    protected static final int APPLICANT_TYPE_1_INDEX = 0;
    protected static final int APPLICANT_TYPE_2_INDEX = 1;
    protected static final int APPLICANT_TYPE_3_INDEX = 2;
    
    public static final String ACTIVITY_TYPE_CODE_CONSTRUCTION = "9";
    public static final String ACTIVITY_TYPE_CODE_LS_SUFFIX_CONSTRUCTION = "C";
    public static final String ACTIVITY_TYPE_CODE_LS_SUFFIX_NONCONSTRUCTION = "N";
    public static final String ACTIVITY_TYPE_CODE_LS_SUFFIX_APPLICATION = "A";
    public static final String ACTIVITY_TYPE_CODE_LS_SUFFIX_PREAPPLICATION = "P";
    public static final String S2S_SUBMISSION_TYPE_CODE_NOTSELECTED = "1";
    public static final int PROGRAM_ANNOUNCEMENT_TITLE_LENGTH = 120;
    public static final int AREAS_AFFECTED_MAX_LENGTH = 250;

    public static final String CERTIFICATION_AGREE_YES = "Y: Yes";
    public static final String APPLICANT_OTHERSPECIFY_FEDERAL = "Federal Government";
    public static final String APPLICANT_OTHERSPECIFY_DISADVANTAGED = "socially and Economically Disadvantaged";
    public static final String APPLICANT_OTHERSPECIFY_WOMEN = "Women owned";
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
    protected static final int RATE_TYPE_ADMINISTRATIVE_SALARIES = 2;
    protected static final int RATE_TYPE_SUPPORT_STAFF_SALARIES = 3;
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

    protected static final int PROPOSAL_TYPE_RESUBMISSION = 4;
    protected static final int DEPARTMENT_NAME_MAX_LENGTH = 30;
    
    /**
     * 
     * Constructs a SF424BaseGenerator.java.
     */
    public SF424BaseGenerator() {
        s2sUtilService = KraServiceLocator.getService(S2SUtilService.class);
        s2sBudgetCalculatorService = KraServiceLocator.getService(S2SBudgetCalculatorService.class);
        dateTimeService = KraServiceLocator.getService(DateTimeService.class);
    }
}
