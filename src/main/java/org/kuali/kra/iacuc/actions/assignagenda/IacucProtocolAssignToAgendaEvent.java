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
package org.kuali.kra.iacuc.actions.assignagenda;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rule.event.KraDocumentEventBaseExtension;

/**
 * The event that occurs when the IRB Administrator assigns a protocol to an agenda.
 */
public class IacucProtocolAssignToAgendaEvent extends KraDocumentEventBaseExtension {

    private IacucProtocolAssignToAgendaBean protocolAssignToAgendaBean;
    
    /**
     * Constructs a ProtocolAssignToAgendaEvent.
     * @param document the document to validate
     * @param protocolAssignToAgendaBean the bean that keeps the data
     */
    public IacucProtocolAssignToAgendaEvent(ProtocolDocument document, IacucProtocolAssignToAgendaBean protocolAssignToAgendaBean) {
        super("Submitting to agenda document " + getDocumentId(document), Constants.PROTOCOL_ASSIGN_TO_AGENDA_ACTION_PROPERTY_KEY, document);
        
        this.protocolAssignToAgendaBean = protocolAssignToAgendaBean;
    }
    
    public IacucProtocolAssignToAgendaBean getProtocolAssignToAgendaBean() {
        return protocolAssignToAgendaBean;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BusinessRuleInterface getRule() {
        return new IacucProtocolAssignToAgendaRule();
    }
    
}