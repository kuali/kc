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
package org.kuali.kra.irb.actions.print;

import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

public class ProtocolActionPrintRule extends ResearchDocumentRuleBase implements BusinessRuleInterface<ProtocolActionPrintEvent> {
    private static final String PROTOCOL_PRINT_TYPE_FIELD = "actionHelper.reportType";

    public boolean processRules(ProtocolActionPrintEvent event) {
        
        boolean valid = true;
        
        if (!event.getFullReport() && !event.getSummaryReport() && !event.getHistoryReport() && !event.getReviewCommentsReport()) {
                reportError(PROTOCOL_PRINT_TYPE_FIELD, KeyConstants.ERROR_COMMITTEE_ACTION_PRINT_REPORT_NOT_SPECIFIED);
                valid = false;
        }
        
        return valid;
    }
}
