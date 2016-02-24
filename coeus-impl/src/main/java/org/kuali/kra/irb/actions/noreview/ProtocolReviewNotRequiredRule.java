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
package org.kuali.kra.irb.actions.noreview;

import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * This class managed the business rules of marking a protocol as not required.
 */
public class ProtocolReviewNotRequiredRule extends KcTransactionalDocumentRuleBase implements ExecuteProtocolReviewNotRequiredRule {

    @Override
    public boolean processReviewNotRequiredRule(ProtocolDocument document, ProtocolReviewNotRequiredBean actionBean) {
        boolean valid = true;
        String fieldNameStarter = "actionHelper.protocolReviewNotRequiredBean.";
        if (actionBean.getActionDate() == null) {
            valid = false;
            GlobalVariables.getMessageMap().putError(fieldNameStarter + "actionDate", KeyConstants.ERROR_PROTOCOL_APPROVAL_DATE_REQUIRED);
        }
        
        if (actionBean.getDecisionDate() == null) {
            valid = false;
            GlobalVariables.getMessageMap().putError(fieldNameStarter + "decisionDate", KeyConstants.ERROR_PROTOCOL_APPROVAL_DATE_REQUIRED);
        }
        
        return valid;
    }

}
