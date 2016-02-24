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
package org.kuali.kra.irb.actions.print;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.kra.infrastructure.KeyConstants;

public class ProtocolActionPrintRule extends KcTransactionalDocumentRuleBase implements KcBusinessRule<ProtocolActionPrintEvent> {
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
