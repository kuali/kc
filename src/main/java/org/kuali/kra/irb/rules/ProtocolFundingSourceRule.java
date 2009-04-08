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
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.bo.ProtocolFundingSource;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.irb.rule.AddProtocolFundingSourceRule;
import org.kuali.kra.irb.rule.event.AddProtocolFundingSourceEvent;
import org.kuali.kra.irb.service.ProtocolFundingSourceService;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

public class ProtocolFundingSourceRule extends ResearchDocumentRuleBase implements AddProtocolFundingSourceRule {
        
    public boolean processAddProtocolFundingSourceBusinessRules(AddProtocolFundingSourceEvent addProtocolFundingSourceEvent) {
        boolean isValid = true;

        ProtocolFundingSource fundingSrc = addProtocolFundingSourceEvent.getFundingSource();
        if (fundingSrc == null) {            
            isValid = false;
            reportError(Constants.PROTO_FUNDING_SRC_TYPE_CODE_FIELD, KeyConstants.ERROR_PROTOCOL_FUNDING_TYPE_NOT_FOUND);             
        } else {
            isValid = checkFundingSource(fundingSrc);
        }
        
        return isValid;
    }
    
    private boolean checkFundingSource(ProtocolFundingSource fundingSrc) {
        boolean isValid = true;
        String fundingId =  fundingSrc.getFundingSource();
        String fundingName =  fundingSrc.getFundingSourceName();

        if (!getProtocolFundingSourceService().isValidIdForType(fundingSrc)) {
            isValid = false;
            reportError(Constants.PROTO_FUNDING_SRC_ID_FIELD, KeyConstants.ERROR_PROTOCOL_FUNDING_ID_INVALID_FOR_TYPE, 
                        fundingSrc.getFundingSourceType().getDescription(), fundingId);         
        }
        
        if (StringUtils.isBlank(fundingId)) {
            isValid = false;
            reportError(Constants.PROTO_FUNDING_SRC_ID_FIELD, KeyConstants.ERROR_PROTOCOL_FUNDING_ID_NOT_FOUND); 
        }               
        if ( fundingSrc.getFundingSourceType() ==null 
             || StringUtils.isBlank(fundingSrc.getFundingSourceType().getDescription())) {
            isValid = false;
            reportError(Constants.PROTO_FUNDING_SRC_TYPE_CODE_FIELD, KeyConstants.ERROR_PROTOCOL_FUNDING_TYPE_NOT_FOUND); 
        } 
        if ( StringUtils.isBlank(fundingName) ) {
            isValid = false;
            reportError(Constants.PROTO_FUNDING_SRC_NAME_FIELD, KeyConstants.ERROR_PROTOCOL_FUNDING_NAME_NOT_FOUND);         
        }

        return isValid;
    }
    

    private ProtocolFundingSourceService getProtocolFundingSourceService() {
        return getService(ProtocolFundingSourceService.class);
    }

}
