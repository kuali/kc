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
package org.kuali.kra.protocol.actions.amendrenew;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.kra.infrastructure.KeyConstants;

/**
 * Business rule for modifying  amendment sections.  The user is required to enter a summary.
 * For protocol amendments they must select at least one module which will be modified in the amendment.
 */
@SuppressWarnings("unchecked")
public abstract class ModifyAmendmentSectionsRuleBase extends KcTransactionalDocumentRuleBase implements KcBusinessRule<ModifyAmendmentSectionsEventBase> {

    public boolean processRules(ModifyAmendmentSectionsEventBase event) {
        
        boolean valid = true;
        
        if (StringUtils.isBlank(event.getAmendmentBean().getSummary())) {
            valid = false;
            reportError(event.getPropertyName(), KeyConstants.ERROR_PROTOCOL_SUMMARY_IS_REQUIRED);
        }
        
        if (event.isAmendment() && !event.getAmendmentBean().isSomeSelected()) {
            valid = false;
            reportError(event.getPropertyName(), KeyConstants.ERROR_PROTOCOL_SELECT_MODULE);
        }
        
        return valid;
    }
}
