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
package org.kuali.kra.irb.protocol.funding;

import org.kuali.kra.protocol.protocol.funding.AddProtocolFundingSourceEventBase;
import org.kuali.rice.krad.document.Document;

import java.util.List;

/**
 * 
 * This class implements the tightly coupled Event-Rule approach to Kuali Rule processing for Adding a Protocol Funding Source.
 */
public class AddProtocolFundingSourceEvent extends AddProtocolFundingSourceEventBase  {
    
    public AddProtocolFundingSourceEvent(String description, Document document, ProtocolFundingSource fundingSource, List<ProtocolFundingSource> protocolFundingSources) {
            super(description, document, fundingSource, (List)protocolFundingSources);
    }
    
    protected AddProtocolFundingSourceEvent(String description, String errorPathPrefix, Document document) {
        super(description, errorPathPrefix, document);
    }
    
    @Override
    public ProtocolFundingSourceRule getRule() {
        return new ProtocolFundingSourceRule();
    }
    
}
