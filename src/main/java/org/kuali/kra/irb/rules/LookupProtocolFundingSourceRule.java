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

import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.rule.event.LookupProtocolFundingSourceEvent;
import org.kuali.kra.irb.service.impl.ProtocolFundingSourceServiceImpl.FundingSourceLookup;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.springframework.util.StringUtils;

public class LookupProtocolFundingSourceRule extends ResearchDocumentRuleBase implements BusinessRuleInterface<LookupProtocolFundingSourceEvent> {

    public boolean processRules(LookupProtocolFundingSourceEvent event) {
        return isValidLookup(event.getLookupBoName());
    }
    
    private boolean isValidLookup(String boClassName) {
        boolean isValid = true;
        ErrorMap errMap = GlobalVariables.getErrorMap();
        if (!StringUtils.hasText(boClassName)) { 
            errMap.putError(Constants.PROTO_FUNDING_SRC_TYPE_CODE_FIELD, KeyConstants.ERROR_FUNDING_LOOKUP_NOT_FOUND);            
            isValid = false;            
        } else if (!boClassName.equalsIgnoreCase(FundingSourceLookup.SPONSOR.getLookupName())
                    && !boClassName.equalsIgnoreCase(FundingSourceLookup.AWARD.getLookupName())
                    && !boClassName.equalsIgnoreCase(FundingSourceLookup.PROPOSAL_DEVELOPMENT.getLookupName())
                    && !boClassName.equalsIgnoreCase(FundingSourceLookup.UNIT.getLookupName()) ) { 
            
            if (boClassName.equalsIgnoreCase(FundingSourceLookup.INSTITUTE_PROPOSAL.getLookupName())) { 

                errMap.putError(Constants.PROTO_FUNDING_SRC_TYPE_CODE_FIELD, KeyConstants.ERROR_FUNDING_LOOKUPTEMP_UNAVAIL, boClassName);         
               isValid = false;
           }  else if (boClassName.equalsIgnoreCase(FundingSourceLookup.OTHER.getLookupName())) {
               errMap.putError(Constants.PROTO_FUNDING_SRC_TYPE_CODE_FIELD, KeyConstants.ERROR_FUNDING_LOOKUP_UNAVAIL, boClassName);
               isValid = false;
           } else { 
               errMap.putError(Constants.PROTO_FUNDING_SRC_TYPE_CODE_FIELD, KeyConstants.ERROR_FUNDING_LOOKUP_NOT_FOUND);            
               isValid = false;            
           }
       }   
        return isValid;
    }
}
