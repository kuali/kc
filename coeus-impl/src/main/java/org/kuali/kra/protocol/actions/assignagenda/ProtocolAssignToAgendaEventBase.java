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

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.protocol.ProtocolDocumentBase;

/**
 * The event that occurs when the IRB Administrator assigns a protocol to an agenda.
 */
public abstract class ProtocolAssignToAgendaEventBase extends KcDocumentEventBaseExtension {

    private ProtocolAssignToAgendaBean protocolAssignToAgendaBean;
    
    /**
     * Constructs a ProtocolAssignToAgendaEventBase.
     * @param document the document to validate
     * @param protocolAssignToAgendaBean the bean that keeps the data
     */
    public ProtocolAssignToAgendaEventBase(ProtocolDocumentBase document, ProtocolAssignToAgendaBean protocolAssignToAgendaBean) {
        super("Submitting to agenda document " + getDocumentId(document), Constants.PROTOCOL_ASSIGN_TO_AGENDA_ACTION_PROPERTY_KEY, document);
        
        this.protocolAssignToAgendaBean = protocolAssignToAgendaBean;
    }
    
    
    public ProtocolAssignToAgendaBean getProtocolAssignToAgendaBean() {
        return protocolAssignToAgendaBean;
    }

    
    public abstract KcBusinessRule getRule();

}
