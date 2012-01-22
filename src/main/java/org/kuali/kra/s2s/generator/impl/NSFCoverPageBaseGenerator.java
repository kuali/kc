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

import java.util.Calendar;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.s2s.generator.S2SBaseFormGenerator;
import org.kuali.kra.s2s.service.S2SUtilService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * This abstract class has methods that are common to all the versions of
 * NSFCoverPage form
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */

public abstract class NSFCoverPageBaseGenerator extends S2SBaseFormGenerator {
	protected DateTimeService dateTimeService;
	protected static final String QUESTION_ID_BEGIN_INVESTIGATOR = "12";
	protected static final String QUESTION_ID_EXPLORATORY_RESEARCH = "14";
	protected static final String QUESTION_ID_HISTORIC_PLACES = "G6";
	protected static final String PROPOSAL_YNQ_LOBBYING_ACTIVITIES = "10088";
	protected static final String QUESTION_ID_RESOLUTION_GRAPHICS = "20";
	protected static final String PRINCIPAL_INVESTIGATOR = "PI";
	protected static final String PI_C0_INVESTIGATOR = "COI";
	protected static final int PERSONAL_DATA = 13;
	protected static final int PROPRIETARY_INFORMATION = 14;

	protected static final int PROGRAM_ANNOUNCEMENT_NUMBER_MAX_LENGTH = 40;
	protected static final int QUESTION_CURRENT_PI = 1;
	protected static final int QUESTION_BEGIN_INVESTIGATOR = 2;
	protected static final int QUESTION_EARLY_CONCEPT_GRANT = 4;
	protected static final int QUESTION_RAPIDRESPONSE_GRANT = 3;
	protected static final int QUESTION_ACCOMPLISHMENT_RENEWAL = 5;
	protected static final int QUESTION_RESOLUTION_GRAPHICS = 6;
	
	protected static final int SINGLE_COPY_DOCUMENT = 87;
	protected static final Integer QUESTIONNAIRE_ID_2 = 2;
	protected static final String ORGANIZATION_ID_PARAMETER = "organizationId";
	protected static final String LOBBYING_QUESTION_ID = "H0";
	protected static final String ANSWER_INDICATOR_VALUE="Y";
	protected S2SUtilService s2sUtilService;
	protected BusinessObjectService businessObjectService;

	/**
	 * 
	 * Constructs a NSFCoverPageBaseGenerator.java.
	 */
	public NSFCoverPageBaseGenerator() {
		dateTimeService = KraServiceLocator.getService(DateTimeService.class);
		s2sUtilService = KraServiceLocator.getService(S2SUtilService.class);
		businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
	}

	/**
	 * 
	 * This method Converts the String that is passed to it into a calendar
	 * object. The argument will be set as the Year in the Calendar Object.
	 * 
	 * @param year -
	 *            The String value to be converted to Calendar Object
	 * @return calendar value corresponding to the year(String) passed.
	 */
	public Calendar getYearAsCalendar(String year) {
		Calendar calendar = dateTimeService.getCurrentCalendar();
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.DATE, 0);
		calendar.set(Calendar.MONTH, 0);
		try {
			calendar.set(Calendar.YEAR, Integer.parseInt(year));
		} catch (NumberFormatException ex) {
			calendar.set(Calendar.YEAR, 0);
		}
		return calendar;
	}

}
