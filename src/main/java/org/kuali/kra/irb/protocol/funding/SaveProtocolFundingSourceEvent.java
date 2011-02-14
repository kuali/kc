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
package org.kuali.kra.irb.protocol.funding;

import java.util.List;

import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rule.event.KraDocumentEventBaseExtension;
import org.kuali.rice.kns.document.Document;

/**
 * Represents the event for saving a Protocol Funding Source.
 */
public class SaveProtocolFundingSourceEvent extends KraDocumentEventBaseExtension {
    
    private List<ProtocolFundingSource> protocolFundingSources;
    
    /**
     * Constructs a SaveProtocolFundingSourceEvent.
     * 
     * @param errorPathPrefix
     * @param document
     * @param protocolFundingSources
     */
    public SaveProtocolFundingSourceEvent(String errorPathPrefix, Document document, List<ProtocolFundingSource> protocolFundingSources) {
        super("saving protocol funding sources to document " + getDocumentId(document), errorPathPrefix, document);
        this.protocolFundingSources = protocolFundingSources;
    }

    public List<ProtocolFundingSource> getProtocolFundingSources() {
        return protocolFundingSources;
    }

    public void setProtocolFundingSources(List<ProtocolFundingSource> protocolFundingSources) {
        this.protocolFundingSources = protocolFundingSources;
    }

    @Override
    public BusinessRuleInterface<SaveProtocolFundingSourceEvent> getRule() {
        return new SaveProtocolFundingSourceRule();
    }
    
}