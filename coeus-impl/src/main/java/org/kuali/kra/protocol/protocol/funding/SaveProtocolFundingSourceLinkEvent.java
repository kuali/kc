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
package org.kuali.kra.protocol.protocol.funding;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.krad.document.Document;

import java.util.List;

/**
 * Represents the event for saving a ProtocolBase Funding Source.
 */
public class SaveProtocolFundingSourceLinkEvent extends KcDocumentEventBaseExtension {
    
    private List<ProtocolFundingSourceBase> protocolFundingSources;
    
    private List<ProtocolFundingSourceBase> deletedProtocolFundingSources;
    
    /**
     * Constructs a SaveProtocolFundingSourceEvent.
     * 
     * @param errorPathPrefix
     * @param document
     * @param protocolFundingSources
     */
    public SaveProtocolFundingSourceLinkEvent(Document document, List<ProtocolFundingSourceBase> protocolFundingSources, 
        List<ProtocolFundingSourceBase> deletedProtocolFundingSources) {
        
        super("saving protocol funding sources to document " + getDocumentId(document), Constants.EMPTY_STRING, document);
        this.protocolFundingSources = protocolFundingSources;
        this.deletedProtocolFundingSources = deletedProtocolFundingSources;
    }

    public List<ProtocolFundingSourceBase> getProtocolFundingSources() {
        return protocolFundingSources;
    }

    public void setProtocolFundingSources(List<ProtocolFundingSourceBase> protocolFundingSources) {
        this.protocolFundingSources = protocolFundingSources;
    }

    public List<ProtocolFundingSourceBase> getDeletedProtocolFundingSources() {
        return deletedProtocolFundingSources;
    }

    public void setDeletedProtocolFundingSources(List<ProtocolFundingSourceBase> deletedProtocolFundingSources) {
        this.deletedProtocolFundingSources = deletedProtocolFundingSources;
    }

    @Override
    public KcBusinessRule<SaveProtocolFundingSourceLinkEvent> getRule() {
        return new SaveProtocolFundingSourceLinkRule();
    }
    
}
