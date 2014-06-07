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
package org.kuali.kra.protocol.personnel;

import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.rice.krad.rules.rule.event.DocumentEvent;

/**
 * Event triggered when a protocol unit state is modified on a 
 * <code>{@link ProtocolDocumentBase}</code>
 *
 */
public interface ProtocolUnitEvent extends DocumentEvent {
    /**
     * @return <code>{@link ProtocolUnitBase}</code> that triggered this event.
     */
    public ProtocolUnitBase getProtocolUnit();
    
    public int getPersonIndex();

}
