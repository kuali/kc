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
