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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.bo.SponsorHierarchy;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.s2s.generator.S2SBaseFormGenerator;
import org.kuali.kra.s2s.service.S2SBudgetCalculatorService;
import org.kuali.kra.s2s.service.S2SUtilService;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * This abstract class has methods that are common to all the versions of
 * RRBudget form.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class RRBudgetBaseGenerator extends S2SBaseFormGenerator {
	protected S2SBudgetCalculatorService s2sBudgetCalculatorService;
	protected S2SUtilService s2sUtilService;
	protected BusinessObjectService businessObjectService;
	public static final String OTHERCOST_DESCRIPTION = "Other";
	public static final String OTHERPERSONNEL_POSTDOC = "PostDoc";
	public static final String OTHERPERSONNEL_GRADUATE = "Grad";
	public static final String OTHERPERSONNEL_UNDERGRADUATE = "UnderGrad";
	public static final String OTHERPERSONNEL_SECRETARIAL = "Sec";
	public static final String NARRATIVE_ATTACHMENT_LIST = "narrativeAttachmentList";
	public static final int BUDGET_JUSTIFICATION_ATTACHMENT = 7;
	public static final int ADDITIONAL_KEYPERSONS_ATTACHMENT = 11;
	public static final int ADDITIONAL_EQUIPMENT_ATTACHMENT = 12;

	protected static final int OTHERPERSONNEL_MAX_ALLOWED = 6;
	protected static final int ARRAY_LIMIT_IN_SCHEMA = 4;
	protected static final String SPONSOR_GROUPS = "SPONSOR GROUPS";
	protected static final String NIH_CO_INVESTIGATOR = "Co-Investigator";
	protected static final String KEYPERSON_CO_PD_PI = "CO-PD/PI";

	/**
	 * 
	 * Constructs a RRBudgetBaseGenerator.java.
	 */
	public RRBudgetBaseGenerator() {
		s2sUtilService = KraServiceLocator.getService(S2SUtilService.class);
		s2sBudgetCalculatorService = KraServiceLocator
				.getService(S2SBudgetCalculatorService.class);
		businessObjectService = KraServiceLocator
				.getService(BusinessObjectService.class);
	}

	protected boolean isNihSponsor(String sponsorCode) {
		boolean isNih = false;
		Map<String, String> sponsorHirarchyMap = new HashMap<String, String>();
		sponsorHirarchyMap.put(Constants.HIERARCHY_NAME, SPONSOR_GROUPS);
		List<SponsorHierarchy> sponsorHierarchyList = (List<SponsorHierarchy>) businessObjectService
				.findMatching(SponsorHierarchy.class, sponsorHirarchyMap);
		for (SponsorHierarchy sponsorHierarchy : sponsorHierarchyList) {
			if (sponsorCode.equals(sponsorHierarchy.getSponsorCode())) {
				String level1 = sponsorHierarchy.getLevel1();
				if (level1.equalsIgnoreCase(Constants.NIH_SPONSOR_ACRONYM)) {
					isNih = true;
					break;
				}
			}
		}
		return isNih;
	}
}
