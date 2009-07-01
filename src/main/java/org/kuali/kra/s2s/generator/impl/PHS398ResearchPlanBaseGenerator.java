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

import org.kuali.kra.s2s.generator.S2SBaseFormGenerator;

/**
 * This abstract class has methods that are common to all the versions of PHS398ResearchPlan form.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class PHS398ResearchPlanBaseGenerator extends S2SBaseFormGenerator {

    public static final int INTRODUCTION_TO_APPLICATION = 20;
    public static final int SPECIFIC_AIMS = 21;
    public static final int BACKGROUND_SIGNIFICANCE = 22;
    public static final int PROGRESS_REPORT = 23;
    public static final int RESEARCH_DESIGN_METHODS = 24;
    public static final int PROTECTION_OF_HUMAN_SUBJECTS = 25;
    public static final int INCLUSION_OF_WOMEN_AND_MINORITIES = 26;
    public static final int TARGETED_PLANNED_ENROLLMENT_TABLE = 27;
    public static final int INCLUSION_OF_CHILDREN = 28;
    public static final int DATA_AND_SAFETY_MONITORING_PLAN = 29;
    public static final int VERTEBRATE_ANIMALS = 30;
    public static final int CONSORTIUM_CONTRACTUAL_ARRANGEMENTS = 31;
    public static final int LETTERS_OF_SUPPORT = 32;
    public static final int RESOURCE_SHARING_PLANS = 33;
    public static final int APPENDIX = 34;

    public static final int INCLUSION_ENROLLMENT_REPORT = 43;
    public static final int PROGRESS_REPORT_PUBLICATION_LIST = 44;
    public static final int SELECT_AGENT_RESEARCH = 45;
    public static final int MULTIPLE_PI_LEADERSHIP_PLAN = 46;
    
    protected static final int PROPOSAL_TYPE_CODE_6 = 6;
}
