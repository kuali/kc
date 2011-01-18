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
package org.kuali.kra.common.specialreview.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.kuali.kra.common.specialreview.service.SpecialReviewService;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolFinderDao;
import org.kuali.kra.irb.protocol.funding.ProtocolFundingSource;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;

/**
 * Implements the SpecialReviewService.
 */
public class SpecialReviewServiceImpl implements SpecialReviewService {
    
    private static final String PROTOCOL_NUMBER = ".protocolNumber";
    
    private ProtocolFinderDao protocolFinderDao;
    private DocumentService documentService;
    private BusinessObjectService businessObjectService;
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.specialreview.service.SpecialReviewService#getProtocolSaveLocationPrefix(java.util.Map)
     */
    public String getProtocolSaveLocationPrefix(Map<String, String[]> parameters) {
        String prefix = null;
        
        for (String parameterName : parameters.keySet()) {
            if (parameterName.endsWith(PROTOCOL_NUMBER)) {
                prefix = StringUtils.removeEnd(parameterName, PROTOCOL_NUMBER);
                break;
            }
        }
        
        return prefix;
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.specialreview.service.SpecialReviewService#getProtocolIndex(java.lang.String)
     */
    public int getProtocolIndex(String prefix) {
        int index = -1;
        
        int lastLeftBracketIndex = StringUtils.lastIndexOf(prefix, '[');
        int lastRightBracketIndex = StringUtils.lastIndexOf(prefix, ']');
        if (lastLeftBracketIndex != -1 && lastRightBracketIndex != -1) {
            String lineNumber = prefix.substring(lastLeftBracketIndex + 1, lastRightBracketIndex);
            if (NumberUtils.isDigits(lineNumber)) {
                index = Integer.parseInt(lineNumber);
            }
        }
        
        return index;
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.specialreview.service.SpecialReviewService#getViewSpecialReviewProtocolRouteHeaderId(java.lang.String)
     */
    public Long getViewSpecialReviewProtocolRouteHeaderId(String protocolNumber) throws Exception {
        Long routeHeaderId = 0L;
        
        if (StringUtils.isNotBlank(protocolNumber)) {
            Protocol protocol = getProtocolFinderDao().findCurrentProtocolByNumber(protocolNumber);
            if (protocol != null) {
                Document document = getDocumentService().getByDocumentHeaderId(protocol.getProtocolDocument().getDocumentNumber());
                routeHeaderId = document.getDocumentHeader().getWorkflowDocument().getRouteHeaderId();
            }
        }
        
        return routeHeaderId;
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.specialreview.service.SpecialReviewService#isLinkedToProtocolFundingSource(java.lang.String, java.lang.Long, java.lang.String)
     */
    public boolean isLinkedToProtocolFundingSource(String protocolNumber, Long fundingSourceId, String fundingSourceType) {
        boolean isLinkedToProtocolFundingSource = false;
        
        if (StringUtils.isNotBlank(protocolNumber)) {
            Protocol protocol = getProtocolFinderDao().findCurrentProtocolByNumber(protocolNumber);
            if (protocol != null) {
                for (ProtocolFundingSource protocolFundingSource : protocol.getProtocolFundingSources()) {
                    if (StringUtils.equals(protocolFundingSource.getFundingSource(), String.valueOf(fundingSourceId)) 
                        && StringUtils.equals(String.valueOf(protocolFundingSource.getFundingSourceTypeCode()), fundingSourceType)) {
                        isLinkedToProtocolFundingSource = true;
                        break;
                    }
                }
            }
        }
        
        return isLinkedToProtocolFundingSource;
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.specialreview.service.SpecialReviewService#addProtocolFundingSourceForSpecialReview(java.lang.String, java.lang.String, 
     *      java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public void addProtocolFundingSourceForSpecialReview(String protocolNumber, Long fundingSourceId, String fundingSourceNumber, String fundingSourceTypeCode, 
        String fundingSourceName, String fundingSourceTitle) {
        
        if (StringUtils.isNotBlank(protocolNumber)) {
            Protocol protocol = getProtocolFinderDao().findCurrentProtocolByNumber(protocolNumber);
            if (protocol != null && fundingSourceId != null && StringUtils.isNotBlank(fundingSourceNumber) && NumberUtils.isNumber(fundingSourceTypeCode)) {
                ProtocolFundingSource protocolFundingSource = new ProtocolFundingSource();
                protocolFundingSource.setFundingSource(String.valueOf(fundingSourceId));
                protocolFundingSource.setFundingSourceNumber(fundingSourceNumber);
                protocolFundingSource.setFundingSourceTypeCode(Integer.valueOf(fundingSourceTypeCode));
                protocolFundingSource.setFundingSourceName(fundingSourceName);
                protocolFundingSource.setFundingSourceTitle(fundingSourceTitle);
                protocol.getProtocolFundingSources().add(protocolFundingSource);
                
                getBusinessObjectService().save(protocol);
            }
        }
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.specialreview.service.SpecialReviewService#deleteProtocolFundingSourceForSpecialReview(java.lang.String, java.lang.Long, 
     *      java.lang.String)
     */
    public void deleteProtocolFundingSourceForSpecialReview(String protocolNumber, Long fundingSourceId, String fundingSourceType) {
        if (StringUtils.isNotBlank(protocolNumber)) {
            Protocol protocol = getProtocolFinderDao().findCurrentProtocolByNumber(protocolNumber);
            if (protocol != null) {
                List<ProtocolFundingSource> deletedProtocolFundingSources = new ArrayList<ProtocolFundingSource>();
                
                for (ProtocolFundingSource protocolFundingSource : protocol.getProtocolFundingSources()) {
                    if (StringUtils.equals(protocolFundingSource.getFundingSource(), String.valueOf(fundingSourceId)) 
                        && StringUtils.equals(String.valueOf(protocolFundingSource.getFundingSourceTypeCode()), fundingSourceType)) {
                        deletedProtocolFundingSources.add(protocolFundingSource);
                    }
                }
                
                for (ProtocolFundingSource deletedProtocolFundingSource : deletedProtocolFundingSources) {
                    protocol.getProtocolFundingSources().remove(deletedProtocolFundingSource);
                }
                
                getBusinessObjectService().save(protocol);
            }
        }
    }
    
    public ProtocolFinderDao getProtocolFinderDao() {
        return protocolFinderDao;
    }

    public void setProtocolFinderDao(ProtocolFinderDao protocolFinderDao) {
        this.protocolFinderDao = protocolFinderDao;
    }

    public DocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}