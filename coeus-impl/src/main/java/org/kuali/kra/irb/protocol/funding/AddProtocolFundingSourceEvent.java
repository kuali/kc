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
