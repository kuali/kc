/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.institutionalproposal.proposallog;

import java.util.Calendar;
import java.util.Map;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.maintenance.KraMaintainableImpl;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.Maintainable;
import org.kuali.rice.kns.service.DateTimeService;
import org.kuali.rice.kns.util.KNSConstants;

public class ProposalLogMaintainableImpl extends KraMaintainableImpl implements Maintainable {

    private static final long serialVersionUID = 4690638717398206040L;
    private static final String PERSON_OBJECT_REFERENCE = "person";
    private static final int FISCAL_YEAR_START_MONTH = 7;
    
    private DateTimeService dateTimeService;
    
    /**
     * @see org.kuali.rice.kns.maintenance.Maintainable#refresh(String refreshCaller, Map fieldValues, MaintenanceDocument document)
     */
    @Override
    @SuppressWarnings("unchecked")
    public void refresh(String refreshCaller, Map fieldValues, MaintenanceDocument document) {
        super.refresh(refreshCaller, fieldValues, document);
        
        // If a person has been selected, lead unit should default to the person's home unit.
        String referencesToRefresh = (String) fieldValues.get(KNSConstants.REFERENCES_TO_REFRESH);
        if (referencesToRefresh != null && referencesToRefresh.contains(PERSON_OBJECT_REFERENCE)) {
            ProposalLog proposalLog = (ProposalLog) this.getBusinessObject();
            if (proposalLog.getPerson() != null) {
                proposalLog.setLeadUnit(proposalLog.getPerson().getHomeUnit());
            }
        }
    }
    
    /**
     * @see org.kuali.rice.kns.maintenance.Maintainable#refresh(String refreshCaller, Map fieldValues, MaintenanceDocument document)
     */
    @Override
    public void prepareForSave() {
        super.prepareForSave();
        // If this is the initial save, we need to set the fiscal year and month.
        ProposalLog proposalLog = (ProposalLog) this.getBusinessObject();
        proposalLog.setFiscalMonth(getFiscalMonth());
        proposalLog.setFiscalYear(getFiscalYear());
    }
    
    // The fiscal month starts in July.
    private int getFiscalMonth() {
        int month = this.getDateTimeService().getCurrentCalendar().get(Calendar.MONTH);
        if (month >= FISCAL_YEAR_START_MONTH) {
            month = month - 6;
        } else {
            month = month + 6;
        }
        return month;
    }
    
    // The fiscal year is based on the fiscal month, which starts in July.
    private int getFiscalYear() {
        int year = this.getDateTimeService().getCurrentCalendar().get(Calendar.YEAR);
        int month = this.getDateTimeService().getCurrentCalendar().get(Calendar.MONTH);
        if (month >= FISCAL_YEAR_START_MONTH) {
            year = year + 1;
        }
        return year;
    }
    
    private DateTimeService getDateTimeService() {
        if (this.dateTimeService == null) {
            dateTimeService = KraServiceLocator.getService(DateTimeService.class);
        }
        return this.dateTimeService;
    }

}
