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

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rule.event.KraDocumentEventBaseExtension;
import org.kuali.rice.krad.document.Document;

/**
 * Represents the event for saving a Protocol Funding Source.
 */
public class SaveProtocolFundingSourceLinkEvent extends KraDocumentEventBaseExtension {
    
    private List<ProtocolFundingSource> protocolFundingSources;
    
    private List<ProtocolFundingSource> deletedProtocolFundingSources;
    
    /**
     * Constructs a SaveProtocolFundingSourceEvent.
     * 
     * @param errorPathPrefix
     * @param document
     * @param protocolFundingSources
     */
    public SaveProtocolFundingSourceLinkEvent(Document document, List<ProtocolFundingSource> protocolFundingSources, 
        List<ProtocolFundingSource> deletedProtocolFundingSources) {
        
        super("saving protocol funding sources to document " + getDocumentId(document), Constants.EMPTY_STRING, document);
        this.protocolFundingSources = protocolFundingSources;
        this.deletedProtocolFundingSources = deletedProtocolFundingSources;
    }

    public List<ProtocolFundingSource> getProtocolFundingSources() {
        return protocolFundingSources;
    }

    public void setProtocolFundingSources(List<ProtocolFundingSource> protocolFundingSources) {
        this.protocolFundingSources = protocolFundingSources;
    }

    public List<ProtocolFundingSource> getDeletedProtocolFundingSources() {
        return deletedProtocolFundingSources;
    }

    public void setDeletedProtocolFundingSources(List<ProtocolFundingSource> deletedProtocolFundingSources) {
        this.deletedProtocolFundingSources = deletedProtocolFundingSources;
    }

    @Override
    public BusinessRuleInterface<SaveProtocolFundingSourceLinkEvent> getRule() {
        return new SaveProtocolFundingSourceLinkRule();
    }
    
}