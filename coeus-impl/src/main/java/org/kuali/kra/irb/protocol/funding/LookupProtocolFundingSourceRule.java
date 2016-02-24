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

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;

/**
 * Validates the conditions necessary for looking up a funding source in the system.
 */
public class LookupProtocolFundingSourceRule extends KcTransactionalDocumentRuleBase implements KcBusinessRule<LookupProtocolFundingSourceEvent> {

    @Override
    public boolean processRules(LookupProtocolFundingSourceEvent event) {
        boolean valid = true;
        
        if (event.getFundingSourceTypeCode() == null) {
            this.getErrorReporter().reportError(Constants.PROTOCOL_FUNDING_SOURCE_TYPE_CODE_FIELD, KeyConstants.ERROR_FUNDING_LOOKUP_NOT_FOUND);
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
            this.getErrorReporter().reportError(Constants.PROTOCOL_FUNDING_SOURCE_TYPE_CODE_FIELD, KeyConstants.ERROR_FUNDING_LOOKUP_UNAVAIL);
            isValid = false;
        }   
       
        return isValid;
    }
    
}
