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

import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.ProtocolEventBase;
import org.kuali.rice.krad.document.Document;

/**
 * 
 * This class provide rule processing for Funding Source lookup events. It is tightly coupled with the
 * rule class itself, and uses the generic type extension to avoid the monolithic document rule interface implementation.
 * 
 */
public abstract class LookupProtocolFundingSourceEventBase extends ProtocolEventBase<LookupProtocolFundingSourceRule> {
    
    private String fundingSourceTypeCode;

    public LookupProtocolFundingSourceEventBase(String errorPathPrefix, ProtocolDocumentBase document, String fundingSourceTypeCode, ErrorType type) {
        super("looking up a funding source for ProtocolBase document " + getDocumentId(document), errorPathPrefix, document, type);
        this.fundingSourceTypeCode = fundingSourceTypeCode;
    }
    
    public LookupProtocolFundingSourceEventBase(String errorPathPrefix, Document document, String fundingSourceTypeCode, ErrorType type) {
        this(errorPathPrefix, (ProtocolDocumentBase) document, fundingSourceTypeCode, type);
    }
    
    public abstract LookupProtocolFundingSourceRule getRule();
    
    
    
    public String getFundingSourceTypeCode() {
        return fundingSourceTypeCode;
    }

    public void setFundingSourceTypeCode(String fundingSourceTypeCode) {
        this.fundingSourceTypeCode = fundingSourceTypeCode;
    }
    
}
