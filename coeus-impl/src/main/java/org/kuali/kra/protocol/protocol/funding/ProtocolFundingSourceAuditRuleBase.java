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
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

import java.util.Collection;

public abstract class ProtocolFundingSourceAuditRuleBase extends KcTransactionalDocumentRuleBase implements DocumentAuditRule {
    
    private static final String FUNDING_SOURCE_AUDIT_ERRORS = "requiredFieldsAuditErrors";
    private static final String FUNDING_SOURCE_AUDIT_LINK = Constants.PROTOCOL_PROTOCOL_PAGE + "." + Constants.PROTOCOL_PROTOCOL_FUNDING_SRC_PANEL_ANCHOR;
    
    @Override
    public boolean processRunAuditBusinessRules(Document document) {
        boolean isValid = true;
        ProtocolDocumentBase protocolDocument = (ProtocolDocumentBase) document;
        
        // disable for now.  Flag in maintenance object is supposed to control whether funding source type is 
        // available to user, not whether it is required. So I'll leave the code in and disable it. In the future,
        // if the need arises for a "required" flag, the code is here and would just need to be tweaked.
//        isValid = processRequiredProtocolFundingSources(protocolDocument.getProtocol());
        
        return isValid;
    }
    
    @SuppressWarnings("unchecked")
    private boolean processRequiredProtocolFundingSources(ProtocolBase protocol) {
        boolean isValid = true;
        
        Collection<FundingSourceType> fundingSourceTypes = getBusinessObjectService().findAll(FundingSourceType.class);
        for (FundingSourceType fundingSourceType : fundingSourceTypes) {
            if (fundingSourceType.getFundingSourceTypeFlag()) {
                if (!getProtocolContainsFundingSource(protocol, fundingSourceType.getFundingSourceTypeCode())) {
                    AuditError auditError = new AuditError(Constants.PROTOCOL_FUNDING_SRC_KEY, KeyConstants.ERROR_PROTOCOL_FUNDING_SOURCE_REQUIRED, 
                                                           FUNDING_SOURCE_AUDIT_LINK, new String[] {fundingSourceType.getDescription()});
                    addAuditError(auditError, FUNDING_SOURCE_AUDIT_ERRORS, Constants.PROTOCOL_PROTOCOL_PANEL_NAME, Constants.AUDIT_ERRORS);
                    isValid = false;
                }
            }
        }
        
        return isValid;
    }
    
    private boolean getProtocolContainsFundingSource(ProtocolBase protocol, String fundingSourceTypeCode) {
        boolean contains = false;
        
        for (ProtocolFundingSourceBase protocolFundingSource : protocol.getProtocolFundingSources()) {
            if (StringUtils.equals(fundingSourceTypeCode, protocolFundingSource.getFundingSourceTypeCode())) {
                contains = true;
                break;
            }
        }
        
        return contains;
    }

}
