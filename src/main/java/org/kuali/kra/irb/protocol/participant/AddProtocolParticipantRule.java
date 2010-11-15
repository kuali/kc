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
package org.kuali.kra.irb.protocol.participant;

import org.kuali.kra.rule.BusinessRuleInterface;

/**
 * This interface addresses the adds rule for adding a new <code>ProtocolParticipant</code>.
 * 
 * @author Kuali Research Administration Team (kc.dev@kuali.org)
 */
public class AddProtocolParticipantRule extends ProtocolParticipantRuleBase implements BusinessRuleInterface<AddProtocolParticipantEvent> {

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.rule.BusinessRuleInterface#processRules(org.kuali.kra.rule.event.KraDocumentEventBaseExtension)
     */
    public boolean processRules(AddProtocolParticipantEvent event) {
        return processAddProtocolParticipantEvent(event);
    }

}