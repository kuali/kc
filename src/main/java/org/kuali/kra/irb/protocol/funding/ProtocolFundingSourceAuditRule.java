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

import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceAuditRuleBase;

public class ProtocolFundingSourceAuditRule extends ProtocolFundingSourceAuditRuleBase {
    
// TODO ********************** commented out during IRB backfit ************************    
//    private static final String FUNDING_SOURCE_AUDIT_ERRORS = "requiredFieldsAuditErrors";
//    private static final String FUNDING_SOURCE_AUDIT_LINK = Constants.PROTOCOL_PROTOCOL_PAGE + "." + Constants.PROTOCOL_PROTOCOL_FUNDING_SRC_PANEL_ANCHOR;
//    
//    /**
//     * {@inheritDoc}
//     * @see org.kuali.kra.rules.ResearchDocumentRuleBase#processRunAuditBusinessRules(org.kuali.rice.krad.document.Document)
//     */
//    public boolean processRunAuditBusinessRules(Document document) {
//        boolean isValid = true;
//        ProtocolDocument protocolDocument = (ProtocolDocument) document;
//        
//        isValid = processRequiredProtocolFundingSources(protocolDocument.getProtocol());
//        
//        return isValid;
//    }
//    
//    @SuppressWarnings("unchecked")
//    private boolean processRequiredProtocolFundingSources(Protocol protocol) {
//        boolean isValid = true;
//        
//        Collection<FundingSourceType> fundingSourceTypes = getBusinessObjectService().findAll(FundingSourceType.class);
//        for (FundingSourceType fundingSourceType : fundingSourceTypes) {
//            if (fundingSourceType.getFundingSourceTypeFlag()) {
//                if (!getProtocolContainsFundingSource(protocol, fundingSourceType.getFundingSourceTypeCode())) {
//                    AuditError auditError = new AuditError(Constants.PROTOCOL_FUNDING_SRC_KEY, KeyConstants.ERROR_PROTOCOL_FUNDING_SOURCE_REQUIRED, 
//                                                           FUNDING_SOURCE_AUDIT_LINK, new String[] {fundingSourceType.getDescription()});
//                    addAuditError(auditError, FUNDING_SOURCE_AUDIT_ERRORS, Constants.PROTOCOL_PROTOCOL_PANEL_NAME, Constants.AUDIT_ERRORS);
//                    isValid = false;
//                }
//            }
//        }
//        
//        return isValid;
//    }
//    
//    private boolean getProtocolContainsFundingSource(Protocol protocol, String fundingSourceTypeCode) {
//        boolean contains = false;
//        
//        for (ProtocolFundingSource protocolFundingSource : protocol.getProtocolFundingSources()) {
//            if (StringUtils.equals(fundingSourceTypeCode, protocolFundingSource.getFundingSourceTypeCode())) {
//                contains = true;
//                break;
//            }
//        }
//        
//        return contains;
//    }

}