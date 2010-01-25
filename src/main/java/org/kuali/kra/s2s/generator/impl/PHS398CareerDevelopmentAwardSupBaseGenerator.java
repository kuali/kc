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
import org.kuali.kra.s2s.service.S2SUtilService;

/**
 * This abstract class has methods that are common to all the versions of
 * PHS398CareerDevelopmentAwardSup form.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class PHS398CareerDevelopmentAwardSupBaseGenerator extends S2SBaseFormGenerator {
	protected S2SUtilService s2sUtilService;
	public static final int INTRODUCTION_TO_APPLICATION = 20;
	public static final int SPECIFIC_AIMS = 21;
	public static final int BACKGROUND_SIGNIFICANCE = 22;
	public static final int RESEARCH_DESIGN_METHODS = 24;
	public static final int INCLUSION_ENROLLMENT_REPORT = 43;
	public static final int PROGRESS_REPORT_PUBLICATION_LIST = 44;
	public static final int PROTECTION_OF_HUMAN_SUBJECTS = 25;
	public static final int INCLUSION_OF_WOMEN_AND_MINORITIES = 26;
	public static final int TARGETED_PLANNED_ENROLLMENT_TABLE = 27;
	public static final int INCLUSION_OF_CHILDREN = 28;
	public static final int VERTEBRATE_ANIMALS = 30;
	public static final int SELECT_AGENT_RESEARCH = 45;
	public static final int PHS_CAREER_PRELIM_STUDIES_PROGREP = 82;
	public static final int PHS_CAREER_CONSORTIUM_CONTRACT = 31;
	public static final int PHS_CAREER_RESOURCE_SHARING_PLAN = 33;
	public static final int CANDIDATE_BACKGROUND = 62;
	public static final int CAREER_GOALS_AND_OBJECTIVES = 63;
	public static final int CAREER_DEVELOPMENT_AND_TRAINING = 64;
	public static final int RESPONSIBLE_CONDUCT_OF_RESEARCH = 65;
	public static final int PHS398_MENTORING_PLAN = 66;
	public static final int PHS398_MENTOR_STATEMENTS_LETTERS = 67;
	public static final int PSH398_INSTITUTIONAL_ENVIRONMENT = 68;
	public static final int PHS398_INSTITUTIONAL_COMMITMENT = 69;
	public static final int PHS_CAREER_APPENDIX = 34;
	public static final int PHS_CAREER_REASEARCH_STRATEGY = 128;
	
	public PHS398CareerDevelopmentAwardSupBaseGenerator(){
		s2sUtilService = KraServiceLocator.getService(S2SUtilService.class);
	}
}