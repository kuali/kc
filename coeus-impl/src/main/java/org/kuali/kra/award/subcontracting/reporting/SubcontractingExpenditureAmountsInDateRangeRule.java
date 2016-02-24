/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.award.subcontracting.reporting;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.krad.util.GlobalVariables;

import java.sql.Date;

@SuppressWarnings("deprecation")
public class SubcontractingExpenditureAmountsInDateRangeRule {
    
    private static final String RANGE_START_DATE = "rangeStartDate";
    private static final String RANGE_END_DATE = "rangeEndDate";
    
    private DictionaryValidationService dictionaryValidationService;
    
    public boolean validateDateRange(Date rangeStartDate, Date rangeEndDate) {
        boolean rulePassed = false;
        String ddEntryName = SubcontractingExpenditureCategoryAmountsInDateRange.class.getSimpleName();
        // first check that both the range end points have been supplied 
        this.getDictionaryValidationService().validateAttributeRequired(ddEntryName, RANGE_START_DATE, rangeStartDate, false, RANGE_START_DATE);        
        this.getDictionaryValidationService().validateAttributeRequired(ddEntryName, RANGE_END_DATE, rangeEndDate, false, RANGE_END_DATE);
        rulePassed = GlobalVariables.getMessageMap().hasNoErrors();
            
        if (rulePassed) {
            // check that the start date is before the end date
            if(rangeEndDate.before(rangeStartDate)) {
                rulePassed = false;
                // report the error by putting the message in global map
                ErrorReporter reporter = KcServiceLocator.getService(ErrorReporter.class);
                reporter.reportError(RANGE_START_DATE, KeyConstants.EXPENDITURES_RANGE_START_DATE_AFTER_END_DATE);
            }
        }
        
        return rulePassed;
    }
    
    public DictionaryValidationService getDictionaryValidationService() {
        if (this.dictionaryValidationService == null) {
            this.dictionaryValidationService = KNSServiceLocator.getKNSDictionaryValidationService();
        }
        return this.dictionaryValidationService;
    }
    
    public void setDictionaryValidationService(DictionaryValidationService dictionaryValidationService) {
        this.dictionaryValidationService = dictionaryValidationService;
    }

}
