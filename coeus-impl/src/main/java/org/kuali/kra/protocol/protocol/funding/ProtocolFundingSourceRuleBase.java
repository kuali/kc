/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
import org.kuali.coeus.common.api.sponsor.SponsorService;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * This class provides business logic for adding a protocol funding source to a protocol. 
 * Also it uses newer paradigm for KC Event/Rule creation for reduced Interface implementation in the ProtocolDocumentRuleBase.
 */
public abstract class ProtocolFundingSourceRuleBase extends KcTransactionalDocumentRuleBase implements KcBusinessRule<AddProtocolFundingSourceEventBase> {
    
    private ProtocolFundingSourceService protocolFundingSourceService;
        
    /**
     * This method will validate funding source based on type &amp; check for duplicates.
     * @param addProtocolFundingSourceEvent
     * @return
     */
    public boolean processAddProtocolFundingSourceBusinessRules(AddProtocolFundingSourceEventBase addProtocolFundingSourceEvent) {
        boolean isValid = true;

        ProtocolFundingSourceBase fundingSrc = addProtocolFundingSourceEvent.getFundingSource();
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
     * @see org.kuali.coeus.sys.framework.rule.KcBusinessRule#processRules(org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension)
     */
    public boolean processRules(AddProtocolFundingSourceEventBase addProtocolFundingSourceEvent) {
        return processAddProtocolFundingSourceBusinessRules(addProtocolFundingSourceEvent);
    }
    
    private boolean checkFundingSource(ProtocolFundingSourceBase fundingSource) {
        boolean isValid = true;
        FundingSourceType fundingSourceType = fundingSource.getFundingSourceType();
        String fundingSourceNumber = fundingSource.getFundingSourceNumber();
        String fundingSourceName =  fundingSource.getFundingSourceName();
        String fundingSourceTypeCode =  fundingSource.getFundingSourceTypeCode();

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
                && getProtocolFundingSourceService().isEditable(fundingSource.getFundingSourceTypeCode())
                && !FundingSourceType.OTHER.equals(fundingSourceTypeCode)) {
            isValid = false;
            reportError(Constants.PROTOCOL_FUNDING_SOURCE_NAME_FIELD, KeyConstants.ERROR_PROTOCOL_FUNDING_SOURCE_NAME_NOT_FOUND);         
        }
        if (StringUtils.equalsIgnoreCase(fundingSource.getFundingSourceTypeCode(), FundingSourceType.SPONSOR)) {
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("sponsorCode", fundingSource.getFundingSourceNumber());
            Sponsor sp = this.getBusinessObjectService().findByPrimaryKey(Sponsor.class, fieldValues);
            if (!this.getSponsorService().isValidSponsor(sp)) {
                isValid = false;
                reportError(Constants.PROTOCOL_FUNDING_SOURCE_NUMBER_FIELD, KeyConstants.ERROR_INVALID_SPONSOR_CODE);
            }
        }

        return isValid;
    }
    
    private boolean checkForDuplicates(AddProtocolFundingSourceEventBase addProtocolFundingSourceEvent) {
        boolean isValid = true;
        
        ProtocolFundingSourceBase fundingSrc = addProtocolFundingSourceEvent.getFundingSource();
        List<ProtocolFundingSourceBase> fundingSources = addProtocolFundingSourceEvent.getProtocolFundingSources();
        for (ProtocolFundingSourceBase theFundingSource : fundingSources) {
            if (fundingSrc.equals(theFundingSource)) {
                isValid = false;
                reportError(Constants.PROTOCOL_FUNDING_SOURCE_NUMBER_FIELD, KeyConstants.ERROR_PROTOCOL_FUNDING_SOURCE_DUPLICATE); 
            }
        }
        
        return isValid;
    }

    private ProtocolFundingSourceService getProtocolFundingSourceService() {
        if (protocolFundingSourceService == null) {
            protocolFundingSourceService =  KcServiceLocator.getService(getProtocolFundingSourceServiceClassHook());
        }
        return protocolFundingSourceService;
    }

    protected abstract Class<? extends ProtocolFundingSourceService> getProtocolFundingSourceServiceClassHook();

    /**
     * This method is for mocks in JUnit.
     * @param protocolFundingSourceService
     */
    public void setProtocolFundingSourceService(ProtocolFundingSourceService protocolFundingSourceService) {
        this.protocolFundingSourceService = protocolFundingSourceService;
    }
    
    private SponsorService getSponsorService() {
        return KcServiceLocator.getService(SponsorService.class);
    }
    
}
