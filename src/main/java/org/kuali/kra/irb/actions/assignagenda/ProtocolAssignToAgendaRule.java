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
package org.kuali.kra.irb.actions.assignagenda;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

/**
 * Validate the assignment of a protocol to a agenda.
 */
public class ProtocolAssignToAgendaRule extends ResearchDocumentRuleBase implements BusinessRuleInterface<ProtocolAssignToAgendaEvent> {

    private static final String COMMITTEE_ID_FIELD = "committeeId";
    private static final String ACTION_DATE_FIELD = "actionDate";
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.rule.BusinessRuleInterface#processRules(org.kuali.kra.rule.event.KraDocumentEventBaseExtension)
     */
    public boolean processRules(ProtocolAssignToAgendaEvent event) {
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