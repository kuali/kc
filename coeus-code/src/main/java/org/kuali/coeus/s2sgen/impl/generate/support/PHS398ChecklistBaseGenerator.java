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
import org.kuali.coeus.s2sgen.impl.budget.S2SCommonBudgetService;
import org.kuali.coeus.s2sgen.impl.generate.S2SBaseFormGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * This abstract class has methods that are common to all the versions of PHS398Checklist form.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class PHS398ChecklistBaseGenerator extends S2SBaseFormGenerator {

    protected static final int NARRATIVE_CODE_CERTIFICATIONS_ATTACHMENT = 38;
    protected static final Integer PROPOSAL_YNQ_QUESTION_114 = 114;
    protected static final Integer PROPOSAL_YNQ_QUESTION_115 = 115;
    protected static final Integer PROPOSAL_YNQ_QUESTION_116 = 116;
    protected static final Integer PROPOSAL_YNQ_QUESTION_117 = 117;
    protected static final Integer PROPOSAL_YNQ_QUESTION_118 = 118;
    protected static final Integer PROPOSAL_YNQ_QUESTION_119 = 119;
    protected static final Integer PROPOSAL_YNQ_QUESTION_120 = 120;
    protected static final int CERTIFICATIONS_ATTACHMENT_CODE = 38;
    protected static final int PROJECT_INCOME_DESCRIPTION_MAX_LENGTH = 150;
    protected static final int PROPOSAL_TYPE_CODE_6 = 6;
    public static final String NOT_ANSWERED = "No";

    @Autowired
    @Qualifier("submissionInfoService")
    protected SubmissionInfoService submissionInfoService;

    @Autowired
    @Qualifier("s2SCommonBudgetService")
    protected S2SCommonBudgetService s2SCommonBudgetService;

    public SubmissionInfoService getSubmissionInfoService() {
        return submissionInfoService;
    }

    public void setSubmissionInfoService(SubmissionInfoService submissionInfoService) {
        this.submissionInfoService = submissionInfoService;
    }

    public S2SCommonBudgetService getS2SCommonBudgetService() {
        return s2SCommonBudgetService;
    }

    public void setS2SCommonBudgetService(S2SCommonBudgetService s2SCommonBudgetService) {
        this.s2SCommonBudgetService = s2SCommonBudgetService;
    }
}
