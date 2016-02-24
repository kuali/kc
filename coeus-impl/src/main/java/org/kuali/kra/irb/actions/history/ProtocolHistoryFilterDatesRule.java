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
package org.kuali.kra.irb.actions.history;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;

import java.util.Date;

public class ProtocolHistoryFilterDatesRule extends KcTransactionalDocumentRuleBase implements KcBusinessRule<ProtocolHistoryFilterDatesEvent> {

    private static final String BEGINNING_ON_DATE = "Beginning On Date";
    private static final String ENDING_ON_DATE = "Ending On Date";
    
    @Override
    public boolean processRules(ProtocolHistoryFilterDatesEvent event) {
        boolean isValid = true;
        
        Date startDate = event.getStartDate();
        Date endDate = event.getEndDate();
        
        if (startDate == null) {
            reportError(Constants.PROTOCOL_HISTORY_DATE_RANGE_FILTER_START_DATE_KEY, 
                    KeyConstants.ERROR_REQUIRED, BEGINNING_ON_DATE);
            isValid = false;
        }
        
        if (endDate == null) {
            reportError(Constants.PROTOCOL_HISTORY_DATE_RANGE_FILTER_END_DATE_KEY, 
                    KeyConstants.ERROR_REQUIRED, ENDING_ON_DATE);
            isValid = false;
        }   
        
        if (startDate != null && endDate != null && startDate.after(endDate)) {
            reportError(Constants.PROTOCOL_HISTORY_DATE_RANGE_FILTER_START_DATE_KEY, 
                    KeyConstants.ERROR_START_DATE_AFTER_END_DATE, BEGINNING_ON_DATE, ENDING_ON_DATE);
            isValid = false;
        }
        
        return isValid;
    }

}
