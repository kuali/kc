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
 * This abstract class has methods that are common to all the versions of PHS398Checklist form.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class PHS398ChecklistBaseGenerator extends S2SBaseFormGenerator {
    protected S2SUtilService s2sUtilService;
    protected S2SBudgetCalculatorService s2sBudgetCalculatorService;
    protected static final int NARRATIVE_CODE_CERTIFICATIONS_ATTACHMENT = 38;
    protected static final String PROPOSAL_YNQ_QUESTION_22 = "22";
    protected static final String PROPOSAL_YNQ_QUESTION_23 = "23";
    protected static final String PROPOSAL_YNQ_QUESTION_16 = "16";
    protected static final int CERTIFICATIONS_ATTACHMENT_CODE = 38;
    protected static final int PROJECT_INCOME_DESCRIPTION_MAX_LENGTH = 150;
    protected static final int PROPOSAL_TYPE_CODE_6 = 6;

    /**
     * 
     * Constructs a PHS398ChecklistBaseGenerator.java.
     */
    public PHS398ChecklistBaseGenerator() {
        s2sUtilService = KraServiceLocator.getService(S2SUtilService.class);
        s2sBudgetCalculatorService = KraServiceLocator.getService(S2SBudgetCalculatorService.class);
    }
}
