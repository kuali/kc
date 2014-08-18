/*
 * Copyright 2005-2014 The Kuali Foundation
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
