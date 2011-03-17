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

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

/**
 * 
 * This class provides business logic for adding a protocol funding source to a protocol. 
 * Also it uses newer paradigm for KC Event/Rule creation for reduced Interface implementation in the ProtocolDocumentRule.
 */
public class ProtocolFundingSourceRule extends ResearchDocumentRuleBase implements BusinessRuleInterface<AddProtocolFundingSourceEvent>{
    
    private ProtocolFundingSourceService protocolFundingSourceService;
        
    /**
     * This method will validate funding source based on type & check for duplicates.
     * @param addProtocolFundingSourceEvent
     * @return
     */
    public boolean processAddProtocolFundingSourceBusinessRules(AddProtocolFundingSourceEvent addProtocolFundingSourceEvent) {
        boolean isValid = true;

        ProtocolFundingSource fundingSrc = addProtocolFundingSourceEvent.getFundingSource();
        if (fundingSrc == null) {            
            isValid = false;
            reportError(Constants.PROTOCOL_FUNDING_SOURCE_TYPE_CODE_FIELD, KeyConstants.ERROR_PROTOCOL_FUNDING_SOURCE_TYPE_NOT_FOUND);             
        } else {
            isValid &= checkFundingSource(fundingSrc);
            isValid &= checkForDuplicates(addProtocolFundingSourceEvent);
        }
        
        return isValid;
    }
    
    /**
     * This is the standard methodName to process rule from the BusinessRuleInterface.
     * 
     * @see org.kuali.kra.rule.BusinessRuleInterface#processRules(org.kuali.kra.rule.event.KraDocumentEventBaseExtension)
     */
    public boolean processRules(AddProtocolFundingSourceEvent addProtocolFundingSourceEvent) {
        return processAddProtocolFundingSourceBusinessRules(addProtocolFundingSourceEvent);
    }
    
    private boolean checkFundingSource(ProtocolFundingSource fundingSource) {
        boolean isValid = true;
        FundingSourceType fundingSourceType = fundingSource.getFundingSourceType();
        String fundingSourceNumber = fundingSource.getFundingSourceNumber();
        String fundingSourceName =  fundingSource.getFundingSourceName();

        if (StringUtils.isBlank(fundingSource.getFundingSourceTypeCode())) {
            isValid = false;
            reportError(Constants.PROTOCOL_FUNDING_SOURCE_TYPE_CODE_FIELD, KeyConstants.ERROR_PROTOCOL_FUNDING_SOURCE_TYPE_NOT_FOUND); 
        }
        if (StringUtils.isBlank(fundingSourceNumber)) {
            isValid = false;
            reportError(Constants.PROTOCOL_FUNDING_SOURCE_NUMBER_FIELD, KeyConstants.ERROR_PROTOCOL_FUNDING_SOURCE_NUMBER_NOT_FOUND); 
        } else if (fundingSourceType != null && !getProtocolFundingSourceService().isValidIdForType(fundingSource)) {
            isValid = false;
            reportError(Constants.PROTOCOL_FUNDING_SOURCE_NUMBER_FIELD, KeyConstants.ERROR_PROTOCOL_FUNDING_SOURCE_NUMBER_INVALID_FOR_TYPE, 
                    fundingSourceType.getDescription(), fundingSourceNumber);      
        }         
        if (StringUtils.isBlank(fundingSourceName) && StringUtils.isNotBlank(fundingSource.getFundingSourceTypeCode())
                && getProtocolFundingSourceService().isEditable(fundingSource.getFundingSourceTypeCode())) {
            isValid = false;
            reportError(Constants.PROTOCOL_FUNDING_SOURCE_NAME_FIELD, KeyConstants.ERROR_PROTOCOL_FUNDING_SOURCE_NAME_NOT_FOUND);         
        }

        return isValid;
    }
    
    private boolean checkForDuplicates(AddProtocolFundingSourceEvent addProtocolFundingSourceEvent) {
        boolean isValid = true;
        
        ProtocolFundingSource fundingSrc = addProtocolFundingSourceEvent.getFundingSource();
        List<ProtocolFundingSource> fundingSources = addProtocolFundingSourceEvent.getProtocolFundingSources();
        for (ProtocolFundingSource theFundingSource : fundingSources) {
            if (fundingSrc.equals(theFundingSource)) {
                isValid = false;
                reportError(Constants.PROTOCOL_FUNDING_SOURCE_NUMBER_FIELD, KeyConstants.ERROR_PROTOCOL_FUNDING_SOURCE_DUPLICATE); 
            }
        }
        
        return isValid;
    }

    private ProtocolFundingSourceService getProtocolFundingSourceService() {
        if (protocolFundingSourceService == null) {
            protocolFundingSourceService =  KraServiceLocator.getService(ProtocolFundingSourceService.class);
        }
        return protocolFundingSourceService;
    }

    /**
     * This method is for mocks in JUnit.
     * @param protocolFundingSourceService
     */
    public void setProtocolFundingSourceService(ProtocolFundingSourceService protocolFundingSourceService) {
        this.protocolFundingSourceService = protocolFundingSourceService;
    }
    
}
