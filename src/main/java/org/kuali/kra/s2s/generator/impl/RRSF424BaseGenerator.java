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

/**
 * This abstract class has methods that are common to all the versions of RRSF424 form.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class RRSF424BaseGenerator extends S2SBaseFormGenerator {
    protected S2SUtilService s2sUtilService;
    protected S2SBudgetCalculatorService s2sBudgetCalculatorService;
    private static final String PROPOSAL_CONTACT_TYPE = "PROPOSAL_CONTACT_TYPE";
    protected static final String PRINCIPAL_INVESTIGATOR = "PI";
    protected static final int PRE_APPLICATION = 6;
    private static final String CONTACT_TYPE_O = "O";
    protected static final String CONTACT_TYPE_I = "I";
    protected static final String STATE_REVIEW_YES = "Y";
    protected static final String STATE_REVIEW_NO = "N";
    protected static final int PROGRAM_ANNOUNCEMENT_TITLE_MAX_LENGTH = 120;
    protected static final int PROPOSAL_TYPE_CODE_6 = 6;
    protected static final String PROPOSAL_YNQ_OTHER_AGENCY_SUBMISSION = "15";
    protected static final String VALUE_YES = "Yes";
    protected static final int PRIMARY_TITLE_MAX_LENGTH = 45;
    protected static final int DIRECTORY_TITLE_MAX_LENGTH = 45;
    protected static final int DEPARTMENT_NAME_MAX_LENGTH = 30;
    protected static final int ANSWER_EXPLANATION_MAX_LENGTH = 20; 

    /**
     * 
     * Constructs a RRSF424BaseGenerator.java.
     */
    public RRSF424BaseGenerator() {
        s2sUtilService = KraServiceLocator.getService(S2SUtilService.class);
        s2sBudgetCalculatorService = KraServiceLocator.getService(S2SBudgetCalculatorService.class);
    }

    /**
     * 
     * This method returns the type of contact person for a proposal
     * 
     * @return String contact type for the proposal
     */
    protected String getContactType() {
        String contactType = s2sUtilService.getParameterValue(PROPOSAL_CONTACT_TYPE);
        if (contactType == null || contactType.length() == 0) {
            contactType = CONTACT_TYPE_O;
        }
        return contactType;
    }
}
