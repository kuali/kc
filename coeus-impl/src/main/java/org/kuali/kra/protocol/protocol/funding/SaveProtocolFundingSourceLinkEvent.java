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
