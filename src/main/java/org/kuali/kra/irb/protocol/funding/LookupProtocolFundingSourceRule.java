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

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.protocol.funding.ProtocolFundingSourceServiceImpl.FundingSourceLookup;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.ErrorReporter;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

/**
 * Validates the conditions necessary for looking up a funding source in the system.
 */
public class LookupProtocolFundingSourceRule extends ResearchDocumentRuleBase implements BusinessRuleInterface<LookupProtocolFundingSourceEvent> {

    private final ErrorReporter errorReporter = new ErrorReporter();
    
    /**
     * ${@inheritDoc}
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
     * @param typeCode
     * @return
     */
    private boolean isValidLookup(Integer typeCode) {
        boolean isValid = true;
     
        if (!typeCode.equals(FundingSourceLookup.SPONSOR.getTypeCode())
                && !typeCode.equals(FundingSourceLookup.INSTITUTIONAL_PROPOSAL.getTypeCode())
                && !typeCode.equals(FundingSourceLookup.AWARD.getTypeCode())
                && !typeCode.equals(FundingSourceLookup.PROPOSAL_DEVELOPMENT.getTypeCode())
                && !typeCode.equals(FundingSourceLookup.UNIT.getTypeCode())) { 
            errorReporter.reportError(Constants.PROTOCOL_FUNDING_SOURCE_TYPE_CODE_FIELD, KeyConstants.ERROR_FUNDING_LOOKUP_UNAVAIL);
            isValid = false;
        }   
       
        return isValid;
    }
    
}