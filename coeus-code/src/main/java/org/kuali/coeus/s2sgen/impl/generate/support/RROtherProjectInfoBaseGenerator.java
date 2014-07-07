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

import org.kuali.coeus.s2sgen.impl.datetime.S2SDateTimeService;
import org.kuali.coeus.s2sgen.impl.generate.S2SBaseFormGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * This abstract class has methods that are common to all the versions of
 * RROtherProjectInfo form.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class RROtherProjectInfoBaseGenerator extends
		S2SBaseFormGenerator {

	// Special Review Id's
	public static final int HUMAN_SUBJECT_SUPPLEMENT = 1;
	public static final int VERTEBRATE_ANIMALS_SUPPLEMENT = 2;
	// Its an YnQ fields that are required for RROtherProjectInfo
	public static final Integer PROPRIETARY_INFORMATION_INDICATOR = 122;
	public static final Integer ENVIRONMENTAL_IMPACT_YNQ = 123;
	public static final Integer ENVIRONMENTAL_EXEMPTION_YNQ = 124;
	public static final Integer INTERNATIONAL_ACTIVITIES_YNQ = 126;
	public static final Integer INTERNATIONAL_ACTIVITIES_EXPL = 127;
	public static final Integer EXPLANATION = 107;
	// Attachments
	public static final int EQUIPMENT_ATTACHMENT = 3;
	public static final int FACILITIES_ATTACHMENT = 2;
	public static final int NARRATIVE_ATTACHMENT = 1;
	public static final int BIBLIOGRAPHY_ATTACHMENT = 4;
	public static final int ABSTRACT_PROJECT_SUMMARY_ATTACHMENT = 5;
	public static final int OTHER_ATTACHMENT = 8;
	public static final int SUPPLIMENTARY_ATTACHMENT = 15;
	public static final String SPECIAL_REVIEW_HUMAN_SUBJECTS = "1";
	public static final String SPECIAL_REVIEW_ANIMAL_USAGE = "1";
	public static final int APPROVAL_TYPE_EXCEMPT = 4;
	protected static final int EXPLANATION_MAX_LENGTH = 55;
	
	public static final String NOT_ANSWERED = "No";

    @Autowired
    @Qualifier("s2SDateTimeService")
    protected S2SDateTimeService s2SDateTimeService;

    public S2SDateTimeService getS2SDateTimeService() {
        return s2SDateTimeService;
    }

    public void setS2SDateTimeService(S2SDateTimeService s2SDateTimeService) {
        this.s2SDateTimeService = s2SDateTimeService;
    }
}
