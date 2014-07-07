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

import org.kuali.coeus.s2sgen.impl.generate.S2SBaseFormGenerator;
import org.kuali.coeus.s2sgen.impl.person.S2SProposalPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * This abstract class has methods that are common to all the versions of
 * PHS398CareerDevelopmentAwardSup form.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class PHS398CareerDevelopmentAwardSupBaseGenerator extends S2SBaseFormGenerator {

	public static final int NARRATIVE_TYPE_INTRODUCTION_TO_APPLICATION = 70;
	public static final int NARRATIVE_TYPE_SPECIFIC_AIMS = 71;
	public static final int NARRATIVE_TYPE_BACKGROUND_SIGNIFICANCE = 72;
	public static final int NARRATIVE_TYPE_RESEARCH_DESIGN_METHODS = 73;
	public static final int NARRATIVE_TYPE_INCLUSION_ENROLLMENT_REPORT = 79;
	public static final int NARRATIVE_TYPE_PROGRESS_REPORT_PUBLICATION_LIST = 80;
	public static final int NARRATIVE_TYPE_PROTECTION_OF_HUMAN_SUBJECTS = 74;
	public static final int NARRATIVE_TYPE_INCLUSION_OF_WOMEN_AND_MINORITIES = 75;
	public static final int NARRATIVE_TYPE_TARGETED_PLANNED_ENROLLMENT_TABLE = 76;
	public static final int NARRATIVE_TYPE_INCLUSION_OF_CHILDREN = 77;
	public static final int NARRATIVE_TYPE_VERTEBRATE_ANIMALS = 78;
	public static final int NARRATIVE_TYPE_SELECT_AGENT_RESEARCH = 81;
	public static final int NARRATIVE_TYPE_PHS_CAREER_PRELIM_STUDIES_PROGREP = 82;
	public static final int NARRATIVE_TYPE_PHS_CAREER_CONSORTIUM_CONTRACT = 83;
	public static final int NARRATIVE_TYPE_PHS_CAREER_RESOURCE_SHARING_PLAN = 84;
	public static final int NARRATIVE_TYPE_CANDIDATE_BACKGROUND = 62;
	public static final int NARRATIVE_TYPE_CAREER_GOALS_AND_OBJECTIVES = 63;
	public static final int NARRATIVE_TYPE_CAREER_DEVELOPMENT_AND_TRAINING = 64;
	public static final int NARRATIVE_TYPE_RESPONSIBLE_CONDUCT_OF_RESEARCH = 65;
	public static final int NARRATIVE_TYPE_PHS398_MENTORING_PLAN = 66;
	public static final int NARRATIVE_TYPE_PHS398_MENTOR_STATEMENTS_LETTERS = 67;
	public static final int NARRATIVE_TYPE_PSH398_INSTITUTIONAL_ENVIRONMENT = 68;
	public static final int NARRATIVE_TYPE_PHS398_INSTITUTIONAL_COMMITMENT = 69;
	public static final int NARRATIVE_TYPE_PHS_CAREER_APPENDIX = 85;
	public static final int NARRATIVE_TYPE_PHS_CAREER_REASEARCH_STRATEGY = 128;

    @Autowired
    @Qualifier("s2SProposalPersonService")
    protected S2SProposalPersonService s2SProposalPersonService;

    public S2SProposalPersonService getS2SProposalPersonService() {
        return s2SProposalPersonService;
    }

    public void setS2SProposalPersonService(S2SProposalPersonService s2SProposalPersonService) {
        this.s2SProposalPersonService = s2SProposalPersonService;
    }
}