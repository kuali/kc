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

import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolEventBase;
import org.kuali.rice.krad.document.Document;

/**
 * 
 * This class provide rule processing for Funding Source lookup events. It is tightly coupled with the
 * rule class itself, and uses the generic type extension to avoid the monolithic document rule interface implementation.
 * 
 */
public class LookupProtocolFundingSourceEvent extends ProtocolEventBase<LookupProtocolFundingSourceRule> {
    
    private String fundingSourceTypeCode;

    public LookupProtocolFundingSourceEvent(String errorPathPrefix, ProtocolDocument document, String fundingSourceTypeCode, ErrorType type) {
        super("looking up a funding source for Protocol document " + getDocumentId(document), errorPathPrefix, document, type);
        this.fundingSourceTypeCode = fundingSourceTypeCode;
    }
    
    public LookupProtocolFundingSourceEvent(String errorPathPrefix, Document document, String fundingSourceTypeCode, ErrorType type) {
        this(errorPathPrefix, (ProtocolDocument) document, fundingSourceTypeCode, type);
    }
    
    public LookupProtocolFundingSourceRule getRule() {
        return new LookupProtocolFundingSourceRule();
    }
    
    public String getFundingSourceTypeCode() {
        return fundingSourceTypeCode;
    }

    public void setFundingSourceTypeCode(String fundingSourceTypeCode) {
        this.fundingSourceTypeCode = fundingSourceTypeCode;
    }
    
}