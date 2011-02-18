/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.external.effortReporting.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.external.effortReporting.service.EffortReportingService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.ParameterService;
import org.kuali.rice.kns.util.ObjectUtils;

/**
 * Default implementation of the
 * {@link org.kuali.kra.external.effortReporting.service.EffortReportingService EffortReportingService}.
 * @author Kuali Coeus Development Team
 */
public class EffortReportingServiceImpl implements EffortReportingService {

    private BusinessObjectService businessObjectService;
    private ParameterService parameterService;
	private static final Log LOG = LogFactory.getLog(EffortReportingServiceImpl.class);
	
	
	/**
	 * returns the id of the project director.
	 * @see org.kuali.kra.external.effortReporting.service.EffortReportingService#getProjectDirector(java.lang.String)
	 */
	public String getProjectDirector(String financialAccountNumber) {
		Award award = getAward(financialAccountNumber);
		if (ObjectUtils.isNotNull(award)) {
			String role = award.getPrincipalInvestigator().getPersonId();
			return role;
		}
		else {
			return null;
		}
	}
	
	/**
	 * Method checks if the award has a federal sponsor.
	 * If the award sponsor type code or the prime sponsor type is federal, then
	 * the document should be routed.
	 * @see org.kuali.kra.external.effortReporting.service.EffortReportingService#isFederalSponsor(java.lang.String)
	 */
	public boolean isFederalSponsor(String financialAccountNumber) {
		
		Award award = getAward(financialAccountNumber);
		if (ObjectUtils.isNotNull(award)) {
			
			String federalSponsorTypeCode = parameterService.getParameterValue(AwardDocument.class, Constants.FEDERAL_SPONSOR_TYPE_CODE);
			String awardSponsorType = award.getSponsor().getSponsorTypeCode();
			
			//If the sponsor type or prime sponsor type is federal, then document should be routed, return true.
			if ((ObjectUtils.isNotNull(awardSponsorType) && awardSponsorType.equals(federalSponsorTypeCode)) || (ObjectUtils.isNotNull(award.getPrimeSponsor()) 
				 && award.getPrimeSponsor().getSponsorType().getSponsorTypeCode().equals(federalSponsorTypeCode))) {
				return true;
			} 
		}
		
		return false;
	}
		
	/**
	 * This helper method returns the award based on the financial account number.
	 * @param financialAccountNumber
	 * @return
	 */
	protected Award getAward(String financialAccountNumber) {
		List<Award> awards;
        HashMap<String, String> searchCriteria =  new HashMap<String, String>();
        searchCriteria.put("accountNumber", financialAccountNumber);  
		awards = new ArrayList<Award>(businessObjectService.findMatching(Award.class, searchCriteria));
		if (ObjectUtils.isNotNull(awards) && !awards.isEmpty()) {
			return awards.get(0);
		} else {
		    LOG.warn("No award found for the corresponding account number.");
			return null;
		}	
	}
	
	/**
     * Sets the businessObjectService attribute value. Injected by Spring.
     * 
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * Sets the parameterService attribute value. Injected by Spring.
     * 
     * @param parameterService The parameterService to set.
     */
    public void setParameterService(ParameterService parameterService) {
    	this.parameterService = parameterService;
    }
}
