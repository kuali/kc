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

import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.ErrorReporter;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

/**
 * Validates the conditions necessary for looking up a funding source in the system.
 */
public class LookupProtocolFundingSourceRule extends ResearchDocumentRuleBase implements BusinessRuleInterface<LookupProtocolFundingSourceEvent> {

    private final ErrorReporter errorReporter = new ErrorReporter();
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.rule.BusinessRuleInterface#processRules(org.kuali.kra.rule.event.KraDocumentEventBaseExtension)
     */
    public boolean processRules(LookupProtocolFundingSourceEvent event) {
        boolean valid = true;
        
        if (event.getFundingSourceTypeCode() == null) {
            errorReporter.reportError(Constants.PROTOCOL_FUNDING_SOURCE_TYPE_CODE_FIELD, KeyConstants.ERROR_FUNDING_LOOKUP_NOT_FOUND);
            valid = false;            
        } else {
            valid &= isValidLookup(event.getFundingSourceTypeCode());
        }
        
        return valid;
    }
    
    /**
     * Throws a validation error if the lookup is of type Other or an unknown type.
     * @param fundingSourceTypeCode
     * @return
     */
    private boolean isValidLookup(String fundingSourceTypeCode) {
        boolean isValid = true;
     
        if (!FundingSourceType.SPONSOR.equals(fundingSourceTypeCode)
                && !FundingSourceType.UNIT.equals(fundingSourceTypeCode)
                && !FundingSourceType.PROPOSAL_DEVELOPMENT.equals(fundingSourceTypeCode)
                && !FundingSourceType.INSTITUTIONAL_PROPOSAL.equals(fundingSourceTypeCode)
                && !FundingSourceType.AWARD.equals(fundingSourceTypeCode)) { 
            errorReporter.reportError(Constants.PROTOCOL_FUNDING_SOURCE_TYPE_CODE_FIELD, KeyConstants.ERROR_FUNDING_LOOKUP_UNAVAIL);
            isValid = false;
        }   
       
        return isValid;
    }
    
}