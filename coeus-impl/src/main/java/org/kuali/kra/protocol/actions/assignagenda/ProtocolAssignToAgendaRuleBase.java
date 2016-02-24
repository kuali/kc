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
package org.kuali.kra.protocol.actions.assignagenda;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.kra.infrastructure.KeyConstants;

/**
 * Validate the assignment of a protocol to a agenda.
 */
public abstract class ProtocolAssignToAgendaRuleBase<E extends ProtocolAssignToAgendaEventBase> extends KcTransactionalDocumentRuleBase implements KcBusinessRule<E> {

    private static final String COMMITTEE_ID_FIELD = "committeeId";
    private static final String ACTION_DATE_FIELD = "actionDate";
    
    @Override
    public boolean processRules(E event) {
        boolean isValid = true;
        
        if (StringUtils.isBlank(event.getProtocolAssignToAgendaBean().getCommitteeId())) {
            isValid = false;
            reportError(COMMITTEE_ID_FIELD, KeyConstants.ERROR_PROTOCOL_COMMITTEE_NOT_SELECTED);
        }
        
        if (event.getProtocolAssignToAgendaBean().getActionDate() == null) {
            isValid = false;
            reportError(ACTION_DATE_FIELD, KeyConstants.ERROR_PROTOCOL_ASSIGN_TO_AGENDA_NO_ACTION_DATE);
        }
       
        return isValid;
    }
    
}
