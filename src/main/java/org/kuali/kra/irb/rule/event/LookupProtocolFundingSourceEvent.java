/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.rule.event;

import org.kuali.core.document.Document;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.irb.rules.LookupProtocolFundingSourceRule;

/**
 * 
 * This class provide rule processing for Funding Source lookup events. It is tightly coupled with the
 * rule class itself, and uses the generic type extension to avoid the monolithic document rule interface implementation.
 * 
 */
public class LookupProtocolFundingSourceEvent extends ProtocolEventBase<LookupProtocolFundingSourceRule> {

    
    public static final String MSG = "looking up a funding source for Protocol document ";
    private String lookupBoName;

    public LookupProtocolFundingSourceEvent(String errorPathPrefix, ProtocolDocument document, String lookupBoName, ErrorType type) {
        super(MSG + getDocumentId(document), errorPathPrefix, document, type);
        this.lookupBoName = lookupBoName;
    }
    
    public LookupProtocolFundingSourceEvent(String errorPathPrefix, Document document, String lookupBoName,ErrorType type) {
        this(errorPathPrefix, (ProtocolDocument)document, lookupBoName,type);
    }
    
    public LookupProtocolFundingSourceRule getRule() {
        return new LookupProtocolFundingSourceRule();
    }

    
    public String getLookupBoName() {
        return lookupBoName;
    }

    public void setLookupBoName(String lookupBoName) {
        this.lookupBoName = lookupBoName;
    }
}
