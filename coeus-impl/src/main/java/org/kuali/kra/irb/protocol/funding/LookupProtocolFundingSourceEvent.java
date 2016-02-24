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
