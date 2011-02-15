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

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.award.specialreview.AwardSpecialReview;
import org.kuali.kra.award.specialreview.AwardSpecialReviewExemption;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.bo.SpecialReviewApprovalType;
import org.kuali.kra.bo.SpecialReviewType;
import org.kuali.kra.common.specialreview.bo.SpecialReview;
import org.kuali.kra.common.specialreview.service.SpecialReviewService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalService;
import org.kuali.kra.institutionalproposal.specialreview.InstitutionalProposalSpecialReview;
import org.kuali.kra.institutionalproposal.specialreview.InstitutionalProposalSpecialReviewExemption;
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
    private static final String NEW_SPECIAL_REVIEW_COMMENT = "A Special Review has been inserted.";
    
    private AwardService awardService;
    private InstitutionalProposalService institutionalProposalService;
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
    public boolean isLinkedToProtocolFundingSource(String protocolNumber, Long fundingSourceId, String fundingSourceTypeCode) {
        boolean isLinkedToProtocolFundingSource = false;
        
        if (StringUtils.isNotBlank(protocolNumber)) {
            Protocol protocol = getProtocolFinderDao().findCurrentProtocolByNumber(protocolNumber);
            if (protocol != null) {
                for (ProtocolFundingSource protocolFundingSource : protocol.getProtocolFundingSources()) {
                    if (StringUtils.equals(protocolFundingSource.getFundingSource(), String.valueOf(fundingSourceId)) 
                        && StringUtils.equals(String.valueOf(protocolFundingSource.getFundingSourceTypeCode()), fundingSourceTypeCode)) {
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
     * @see org.kuali.kra.common.specialreview.service.SpecialReviewService#isLinkedToSpecialReview(java.lang.Long, java.lang.String, java.lang.String)
     */
    public boolean isLinkedToSpecialReview(Long fundingSourceId, String fundingSourceTypeCode, String protocolNumber) {
        boolean isLinkedToSpecialReview = false;
        
        if (StringUtils.equals(FundingSourceType.AWARD, fundingSourceTypeCode)) {
            Award award = getAwardService().getAward(fundingSourceId);
            if (award != null) {
                isLinkedToSpecialReview = isLinkedToSpecialReviews(award.getSpecialReviews(), protocolNumber);
            }
        } else if (StringUtils.equals(FundingSourceType.INSTITUTIONAL_PROPOSAL, fundingSourceTypeCode)) {
            InstitutionalProposal institutionalProposal = getInstitutionalProposalService().getInstitutionalProposal(String.valueOf(fundingSourceId));
            if (institutionalProposal != null) {
                isLinkedToSpecialReview = isLinkedToSpecialReviews(institutionalProposal.getSpecialReviews(), protocolNumber);
            }
        }
        
        return isLinkedToSpecialReview;
    }
    
    private boolean isLinkedToSpecialReviews(List<? extends SpecialReview<?>> specialReviews, String protocolNumber) {
        boolean isLinkedToSpecialReviews = false;
        
        for (SpecialReview<?> specialReview : specialReviews) {
            if (SpecialReviewType.HUMAN_SUBJECTS.equals(specialReview.getSpecialReviewTypeCode()) 
                && StringUtils.equals(specialReview.getProtocolNumber(), protocolNumber)) {
                isLinkedToSpecialReviews = true;
                break;
            }
        }
        
        return isLinkedToSpecialReviews;
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.specialreview.service.SpecialReviewService#addProtocolFundingSourceForSpecialReview(java.lang.String, java.lang.String, 
     *      java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public void addProtocolFundingSourceForSpecialReview(String protocolNumber, Long fundingSourceId, String fundingSourceNumber, String fundingSourceTypeCode, 
        String fundingSourceName, String fundingSourceTitle) {
        
        Protocol protocol = getProtocolFinderDao().findCurrentProtocolByNumber(protocolNumber);
        if (protocol != null && fundingSourceId != null && StringUtils.isNotBlank(fundingSourceNumber) && NumberUtils.isNumber(fundingSourceTypeCode)) {
            Long protocolId = protocol.getProtocolId();
            
            ProtocolFundingSource protocolFundingSource = new ProtocolFundingSource();
            protocolFundingSource.setProtocolId(protocolId);
            protocolFundingSource.setProtocolNumber(protocolNumber);
            protocolFundingSource.setFundingSource(String.valueOf(fundingSourceId));
            protocolFundingSource.setFundingSourceNumber(fundingSourceNumber);
            protocolFundingSource.setFundingSourceTypeCode(Integer.valueOf(fundingSourceTypeCode));
            protocolFundingSource.setFundingSourceName(fundingSourceName);
            protocolFundingSource.setFundingSourceTitle(fundingSourceTitle);
            
            getBusinessObjectService().save(protocolFundingSource);
        }
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.specialreview.service.SpecialReviewService#deleteProtocolFundingSourceForSpecialReview(java.lang.String, java.lang.Long, 
     *      java.lang.String)
     */
    public void deleteProtocolFundingSourceForSpecialReview(String protocolNumber, Long fundingSourceId, String fundingSourceTypeCode) {
        Protocol protocol = getProtocolFinderDao().findCurrentProtocolByNumber(protocolNumber);
        if (protocol != null) {
            List<ProtocolFundingSource> deletedProtocolFundingSources = new ArrayList<ProtocolFundingSource>();
            
            for (ProtocolFundingSource protocolFundingSource : protocol.getProtocolFundingSources()) {
                if (StringUtils.equals(protocolFundingSource.getFundingSource(), String.valueOf(fundingSourceId)) 
                    && StringUtils.equals(String.valueOf(protocolFundingSource.getFundingSourceTypeCode()), fundingSourceTypeCode)) {
                    deletedProtocolFundingSources.add(protocolFundingSource);
                }
            }
            
            getBusinessObjectService().delete(deletedProtocolFundingSources);
        }
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.specialreview.service.SpecialReviewService#addSpecialReviewForProtocolFundingSource(java.lang.Long, java.lang.String, 
     *      java.lang.String, java.sql.Date, java.sql.Date, java.sql.Date, java.util.List)
     */
    public void addSpecialReviewForProtocolFundingSource(Long fundingSourceId, String fundingSourceTypeCode, String protocolNumber, Date applicationDate, 
        Date approvalDate, Date expirationDate, List<String> exemptionTypeCodes) {
        
        if (StringUtils.equals(FundingSourceType.AWARD, fundingSourceTypeCode)) {
            Award award = getAwardService().getAward(fundingSourceId);
            addAwardSpecialReview(award, protocolNumber, applicationDate, approvalDate, expirationDate, exemptionTypeCodes);
        } else if (StringUtils.equals(FundingSourceType.INSTITUTIONAL_PROPOSAL, fundingSourceTypeCode)) {
            InstitutionalProposal institutionalProposal = getInstitutionalProposalService().getInstitutionalProposal(String.valueOf(fundingSourceId));
            addInstitutionalProposalSpecialReview(institutionalProposal, protocolNumber, applicationDate, approvalDate, expirationDate, exemptionTypeCodes);
        }
    }
    
    private void addAwardSpecialReview(Award award, String protocolNumber, Date applicationDate, Date approvalDate, Date expirationDate, 
        List<String> exemptionTypeCodes) {
        
        if (award != null) {
            Long awardId = award.getAwardId();
            Integer specialReviewNumber = award.getAwardDocument().getDocumentNextValue(Constants.SPECIAL_REVIEW_NUMBER);
            
            AwardSpecialReview specialReview = new AwardSpecialReview();
            specialReview.setAwardId(awardId);
            specialReview.setSpecialReviewNumber(specialReviewNumber);
            specialReview.setSpecialReviewTypeCode(SpecialReviewType.HUMAN_SUBJECTS);
            specialReview.setApprovalTypeCode(SpecialReviewApprovalType.LINK_TO_IRB);
            specialReview.setProtocolNumber(protocolNumber);
            specialReview.setApplicationDate(applicationDate);
            specialReview.setApprovalDate(approvalDate);
            specialReview.setExpirationDate(expirationDate);
            for (String exemptionTypeCode : exemptionTypeCodes) {
                AwardSpecialReviewExemption specialReviewExemption = new AwardSpecialReviewExemption();
                specialReviewExemption.setAwardSpecialReview(specialReview);
                specialReviewExemption.setExemptionTypeCode(exemptionTypeCode);
                specialReview.getSpecialReviewExemptions().add(specialReviewExemption);
            }
            specialReview.setComments(NEW_SPECIAL_REVIEW_COMMENT);

            getBusinessObjectService().save(specialReview);
        }
    }
    
    private void addInstitutionalProposalSpecialReview(InstitutionalProposal institutionalProposal, String protocolNumber, Date applicationDate, 
        Date approvalDate, Date expirationDate, List<String> exemptionTypeCodes) {
        
        if (institutionalProposal != null) {
            Long proposalId = institutionalProposal.getProposalId();
            Integer specialReviewNumber = institutionalProposal.getInstitutionalProposalDocument().getDocumentNextValue(Constants.SPECIAL_REVIEW_NUMBER);
            
            InstitutionalProposalSpecialReview specialReview = new InstitutionalProposalSpecialReview();
            specialReview.setProposalId(proposalId);
            specialReview.setSpecialReviewNumber(specialReviewNumber);
            specialReview.setSpecialReviewTypeCode(SpecialReviewType.HUMAN_SUBJECTS);
            specialReview.setApprovalTypeCode(SpecialReviewApprovalType.LINK_TO_IRB);
            specialReview.setProtocolNumber(protocolNumber);
            specialReview.setApplicationDate(applicationDate);
            specialReview.setApprovalDate(approvalDate);
            specialReview.setExpirationDate(expirationDate);
            for (String exemptionTypeCode : exemptionTypeCodes) {
                InstitutionalProposalSpecialReviewExemption specialReviewExemption = new InstitutionalProposalSpecialReviewExemption();
                specialReviewExemption.setInstitutionalProposalSpecialReview(specialReview);
                specialReviewExemption.setExemptionTypeCode(exemptionTypeCode);
                specialReview.getSpecialReviewExemptions().add(specialReviewExemption);
            }
            specialReview.setComments(NEW_SPECIAL_REVIEW_COMMENT);
            
            getBusinessObjectService().save(specialReview);
        }
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.specialreview.service.SpecialReviewService#deleteSpecialReviewForProtocolFundingSource(java.lang.Long, java.lang.String, 
     *      java.lang.String)
     */
    public void deleteSpecialReviewForProtocolFundingSource(Long fundingSourceId, String fundingSourceTypeCode, String protocolNumber) {
        if (StringUtils.equals(FundingSourceType.AWARD, fundingSourceTypeCode)) {
            Award award = getAwardService().getAward(fundingSourceId);
            deleteAwardSpecialReview(award, protocolNumber);
        } else if (StringUtils.equals(FundingSourceType.INSTITUTIONAL_PROPOSAL, fundingSourceTypeCode)) {
            InstitutionalProposal institutionalProposal = getInstitutionalProposalService().getInstitutionalProposal(String.valueOf(fundingSourceId));
            deleteInstitutionalProposalSpecialReview(institutionalProposal, protocolNumber);
        }
    }
    
    private void deleteAwardSpecialReview(Award award, String protocolNumber) {
        if (award != null) {
            List<AwardSpecialReview> deletedSpecialReviews = new ArrayList<AwardSpecialReview>();
            
            for (AwardSpecialReview specialReview : award.getSpecialReviews()) {
                if (StringUtils.equals(specialReview.getProtocolNumber(), protocolNumber)) {
                    deletedSpecialReviews.add(specialReview);
                }
            }
            
            getBusinessObjectService().delete(deletedSpecialReviews);
        }
    }
    
    private void deleteInstitutionalProposalSpecialReview(InstitutionalProposal institutionalProposal, String protocolNumber) {
        if (institutionalProposal != null) {
            List<InstitutionalProposalSpecialReview> deletedSpecialReviews = new ArrayList<InstitutionalProposalSpecialReview>();
            
            for (InstitutionalProposalSpecialReview specialReview : institutionalProposal.getSpecialReviews()) {
                if (StringUtils.equals(specialReview.getProtocolNumber(), protocolNumber)) {
                    deletedSpecialReviews.add(specialReview);
                }
            }
            
            getBusinessObjectService().delete(deletedSpecialReviews);
        }
    }
    
    public AwardService getAwardService() {
        return awardService;
    }
    
    public void setAwardService(AwardService awardService) {
        this.awardService = awardService;
    }
    
    public InstitutionalProposalService getInstitutionalProposalService() {
        return institutionalProposalService;
    }
    
    public void setInstitutionalProposalService(InstitutionalProposalService institutionalProposalService) {
        this.institutionalProposalService = institutionalProposalService;
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