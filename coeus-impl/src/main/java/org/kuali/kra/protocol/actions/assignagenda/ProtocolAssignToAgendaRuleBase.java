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
