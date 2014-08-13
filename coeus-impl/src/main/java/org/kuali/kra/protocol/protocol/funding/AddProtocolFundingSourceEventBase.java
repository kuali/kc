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

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.protocol.ProtocolEventBase;
import org.kuali.rice.krad.document.Document;

import java.util.List;

/**
 * 
 * This class implements the tightly coupled Event-Rule approach to Kuali Rule processing for Adding a ProtocolBase Funding Source.
 */
public abstract class AddProtocolFundingSourceEventBase extends ProtocolEventBase<ProtocolFundingSourceRuleBase> {
    
    private static final org.apache.commons.logging.Log LOG = 
        org.apache.commons.logging.LogFactory.getLog(AddProtocolFundingSourceEventBase.class);
    
    private static final String MSG = "adding a funding source to a ProtocolBase document ";
    private ProtocolFundingSourceBase fundingSource;
    private List<ProtocolFundingSourceBase> protocolFundingSources;



    protected AddProtocolFundingSourceEventBase(String description, String errorPathPrefix, Document document) {
        super(MSG + getDocumentId(document), errorPathPrefix, document, ErrorType.HARDERROR);
    }
    
    public AddProtocolFundingSourceEventBase(String description,  Document document, ProtocolFundingSourceBase fundingSource, 
            List<ProtocolFundingSourceBase> protocolFundingSources) {
        super(description+": "+MSG + getDocumentId(document), "", document, ErrorType.HARDERROR);

        this.protocolFundingSources = protocolFundingSources;
        this.fundingSource = fundingSource;
    }


    @Override
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        logMessage.append(" with ");

        // vary logging detail as needed
        if (document == null) {
            logMessage.append("null protocolDocument");
        }
        else {
            logMessage.append(document.toString());
        }

        LOG.debug(logMessage);   
    }

    public ProtocolFundingSourceBase getFundingSource() {
        return fundingSource;
    }

    public abstract ProtocolFundingSourceRuleBase getRule();

    public List<ProtocolFundingSourceBase> getProtocolFundingSources() {
        return protocolFundingSources;
    }

    public void setProtocolFundingSources(List<ProtocolFundingSourceBase> protocolFundingSources) {
        this.protocolFundingSources = protocolFundingSources;
    }
    
}
