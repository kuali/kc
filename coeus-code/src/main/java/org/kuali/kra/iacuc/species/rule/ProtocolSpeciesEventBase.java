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
package org.kuali.kra.iacuc.species.rule;

import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.species.IacucProtocolSpecies;

/**
 * This class is abstract base class for Event Notification
 */
public abstract class ProtocolSpeciesEventBase extends KcDocumentEventBase implements ProtocolSpeciesEvent {
    
    private IacucProtocolSpecies protocolSpecies;
    
    protected ProtocolSpeciesEventBase(String description, String errorPathPrefix, IacucProtocolDocument document, IacucProtocolSpecies protocolSpecies) {
        super(description, errorPathPrefix, document);
        this.protocolSpecies = protocolSpecies;
    }

    @Override
    protected void logEvent() {
    }
    
    public IacucProtocolSpecies getProtocolSpecies() {
        return this.protocolSpecies;
    }

}
