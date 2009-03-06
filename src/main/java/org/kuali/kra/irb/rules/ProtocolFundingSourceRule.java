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
package org.kuali.kra.irb.rules;

import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.irb.rule.AddProtocolFundingSourceRule;
import org.kuali.kra.irb.rule.event.AddProtocolFundingSourceEvent;
import org.kuali.kra.irb.service.ProtocolFundingSourceService;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

public class ProtocolFundingSourceRule extends ResearchDocumentRuleBase implements AddProtocolFundingSourceRule {
        
    public boolean processAddProtocolFundingSourceBusinessRules(AddProtocolFundingSourceEvent addProtocolFundingSourceEvent) {
        boolean isValid = true;
        ProtocolDocument document = (ProtocolDocument) addProtocolFundingSourceEvent.getDocument();
        
        if (document.getProtocol().getNewFundingSource() == null) {            
            isValid = false;
            reportError("document.protocol.newFundingSource.fundingSourceTypeCode", KeyConstants.ERROR_PROTOCOL_FUNDING_TYPE_NOT_FOUND);             
        }
        
        String fundingId =  document.getProtocol().getNewFundingSource().getFundingSource();
        String fundingName =  document.getProtocol().getNewFundingSource().getFundingSourceName();
        
        if (StringUtils.isNotBlank(fundingId) 
                && document.getProtocol().getNewFundingSource().getFundingSourceTypeCode() != null
                && StringUtils.isNotBlank(document.getProtocol().getNewFundingSource().getFundingSourceTypeCode().toString())) {
            if (StringUtils.isBlank(fundingName)) {
                isValid=false;
                reportError("document.protocol.newFundingSource.fundingSourceName", KeyConstants.ERROR_PROTOCOL_FUNDING_NAME_NOT_FOUND);     
            }
            if (!getProtocolFundingSourceService().isValidIdForType(document.getProtocol().getNewFundingSource())) {
                isValid = false;
                reportError("document.protocol.newFundingSource.fundingSource", KeyConstants.ERROR_PROTOCOL_FUNDING_ID_INVALID_FOR_TYPE, document.getProtocol().getNewFundingSource().getFundingSourceType().getDescription(), fundingId);         
            }
        } else {
            if (StringUtils.isBlank(fundingId)) {
                isValid = false;
                reportError("document.protocol.newFundingSource.fundingSource", KeyConstants.ERROR_PROTOCOL_FUNDING_ID_NOT_FOUND); 
            }               
            if ( document.getProtocol().getNewFundingSource().getFundingSourceType() ==null ||
                    StringUtils.isBlank(document.getProtocol().getNewFundingSource().getFundingSourceType().getDescription())) {
                isValid = false;
                reportError("document.protocol.newFundingSource.fundingSourceTypeCode", KeyConstants.ERROR_PROTOCOL_FUNDING_TYPE_NOT_FOUND); 
            } 
            if ( StringUtils.isBlank(fundingName) ) {
                isValid = false;
                reportError("document.protocol.newFundingSource.fundingSourceName", KeyConstants.ERROR_PROTOCOL_FUNDING_NAME_NOT_FOUND);         
            }
        }
        
        return isValid;
    }

    private ProtocolFundingSourceService getProtocolFundingSourceService() {
        return getService(ProtocolFundingSourceService.class);
    }

}
