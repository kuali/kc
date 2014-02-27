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
package org.kuali.kra.iacuc.actions.assignagenda;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.protocol.actions.assignagenda.ProtocolAssignToAgendaEventBase;

/**
 * The event that occurs when the IRB Administrator assigns a protocol to an agenda.
 */
public class IacucProtocolAssignToAgendaEvent extends ProtocolAssignToAgendaEventBase {


    public IacucProtocolAssignToAgendaEvent(IacucProtocolDocument document, IacucProtocolAssignToAgendaBean protocolAssignToAgendaBean) {
        super(document, protocolAssignToAgendaBean);
    }

    @Override
    @SuppressWarnings("unchecked")
    public KcBusinessRule getRule() {
        return new IacucProtocolAssignToAgendaRule();
    }
    
}
