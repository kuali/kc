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
import org.kuali.kra.award.document.AwardDocument;
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
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalService;
import org.kuali.kra.institutionalproposal.specialreview.InstitutionalProposalSpecialReview;
import org.kuali.kra.institutionalproposal.specialreview.InstitutionalProposalSpecialReviewExemption;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolFinderDao;
import org.kuali.kra.irb.protocol.funding.ProtocolFundingSource;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.document.Document;
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
     * @see org.kuali.kra.common.specialreview.service.SpecialReviewService#isLinkedToSpecialReview(java.lang.String, java.lang.Integer, java.lang.String)
     */
    public boolean isLinkedToSpecialReview(String fundingSourceId, Integer fundingSourceType, String protocolNumber) {
        boolean isLinkedToSpecialReview = false;
        
        if (StringUtils.equals(FundingSourceType.AWARD, String.valueOf(fundingSourceType))) {
            if (NumberUtils.isNumber(fundingSourceId)) {
                Award award = getAwardService().getAward(Long.valueOf(fundingSourceId));
                if (award != null) {
                    isLinkedToSpecialReview = isLinkedToSpecialReviews(award.getSpecialReviews(), protocolNumber);
                }
            }
        } else if (StringUtils.equals(FundingSourceType.INSTITUTIONAL_PROPOSAL, String.valueOf(fundingSourceType))) {
            if (StringUtils.isNotBlank(fundingSourceId)) {
                InstitutionalProposal institutionalProposal = getInstitutionalProposalService().getInstitutionalProposal(fundingSourceId);
                if (institutionalProposal != null) {
                    isLinkedToSpecialReview = isLinkedToSpecialReviews(institutionalProposal.getSpecialReviews(), protocolNumber);
                }
            }
        }
        
        return isLinkedToSpecialReview;
    }
    
    private boolean isLinkedToSpecialReviews(List<? extends SpecialReview<?>> specialReviews, String protocolNumber) {
        boolean isLinkedToSpecialReviews = false;
        
        for (SpecialReview<?> specialReview : specialReviews) {
            if (StringUtils.equals(specialReview.getProtocolNumber(), protocolNumber)) {
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
        String fundingSourceName, String fundingSourceTitle) throws WorkflowException {
        
        ProtocolDocument document = getProtocolDocument(protocolNumber);
        if (document != null && fundingSourceId != null && StringUtils.isNotBlank(fundingSourceNumber) && NumberUtils.isNumber(fundingSourceTypeCode)) {
            ProtocolFundingSource protocolFundingSource = new ProtocolFundingSource();
            protocolFundingSource.setProtocolId(document.getProtocol().getProtocolId());
            protocolFundingSource.setProtocolNumber(document.getProtocol().getProtocolNumber());
            
            protocolFundingSource.setFundingSource(String.valueOf(fundingSourceId));
            protocolFundingSource.setFundingSourceNumber(fundingSourceNumber);
            protocolFundingSource.setFundingSourceTypeCode(Integer.valueOf(fundingSourceTypeCode));
            protocolFundingSource.setFundingSourceName(fundingSourceName);
            protocolFundingSource.setFundingSourceTitle(fundingSourceTitle);
            document.getProtocol().getProtocolFundingSources().add(protocolFundingSource);
            
            getDocumentService().saveDocument(document);
        }
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.specialreview.service.SpecialReviewService#deleteProtocolFundingSourceForSpecialReview(java.lang.String, java.lang.Long, 
     *      java.lang.String)
     */
    public void deleteProtocolFundingSourceForSpecialReview(String protocolNumber, Long fundingSourceId, String fundingSourceType) throws WorkflowException {
        ProtocolDocument document = getProtocolDocument(protocolNumber);
        if (document != null) {
            List<ProtocolFundingSource> deletedProtocolFundingSources = new ArrayList<ProtocolFundingSource>();
            
            for (ProtocolFundingSource protocolFundingSource : document.getProtocol().getProtocolFundingSources()) {
                if (StringUtils.equals(protocolFundingSource.getFundingSource(), String.valueOf(fundingSourceId)) 
                    && StringUtils.equals(String.valueOf(protocolFundingSource.getFundingSourceTypeCode()), fundingSourceType)) {
                    deletedProtocolFundingSources.add(protocolFundingSource);
                }
            }
            
            for (ProtocolFundingSource deletedProtocolFundingSource : deletedProtocolFundingSources) {
                document.getProtocol().getProtocolFundingSources().remove(deletedProtocolFundingSource);
            }
            
            getDocumentService().saveDocument(document);
        }
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.specialreview.service.SpecialReviewService#addSpecialReviewForProtocolFundingSource(java.lang.String, java.lang.Integer, 
     *      java.lang.String, java.sql.Date, java.sql.Date, java.sql.Date, java.util.List)
     */
    public void addSpecialReviewForProtocolFundingSource(String fundingSourceId, Integer fundingSourceType, String protocolNumber, Date applicationDate, 
        Date approvalDate, Date expirationDate, List<String> exemptionTypeCodes) throws WorkflowException {
        
        if (StringUtils.equals(FundingSourceType.AWARD, String.valueOf(fundingSourceType))) {
            AwardDocument document = getAwardDocument(fundingSourceId);
            addAwardSpecialReview(document, protocolNumber, applicationDate, approvalDate, expirationDate, exemptionTypeCodes);
        } else if (StringUtils.equals(FundingSourceType.INSTITUTIONAL_PROPOSAL, String.valueOf(fundingSourceType))) {
            InstitutionalProposalDocument document = getInstitutionalProposalDocument(fundingSourceId);
            addInstitutionalProposalSpecialReview(document, protocolNumber, applicationDate, approvalDate, expirationDate, exemptionTypeCodes);
        }
    }
    
    private void addAwardSpecialReview(AwardDocument document, String protocolNumber, Date applicationDate, Date approvalDate, Date expirationDate, 
        List<String> exemptionTypeCodes) throws WorkflowException {
        
        if (document != null) {
            AwardSpecialReview specialReview = new AwardSpecialReview();
            specialReview.setSpecialReviewNumber(document.getDocumentNextValue(Constants.SPECIAL_REVIEW_NUMBER));
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
            document.getAward().getSpecialReviews().add(specialReview);

            getDocumentService().saveDocument(document);
        }
    }
    
    private void addInstitutionalProposalSpecialReview(InstitutionalProposalDocument document, String protocolNumber, Date applicationDate, 
        Date approvalDate, Date expirationDate, List<String> exemptionTypeCodes) throws WorkflowException {
        
        if (document != null) {
            InstitutionalProposalSpecialReview specialReview = new InstitutionalProposalSpecialReview();
            specialReview.setSpecialReviewNumber(document.getDocumentNextValue(Constants.SPECIAL_REVIEW_NUMBER));
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
            document.getInstitutionalProposal().getSpecialReviews().add(specialReview);
            
            getDocumentService().saveDocument(document);
        }
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.specialreview.service.SpecialReviewService#deleteSpecialReviewForProtocolFundingSource(java.lang.String, java.lang.Integer, 
     *      java.lang.String)
     */
    public void deleteSpecialReviewForProtocolFundingSource(String fundingSourceId, Integer fundingSourceType, String protocolNumber) throws WorkflowException {
        if (StringUtils.equals(FundingSourceType.AWARD, String.valueOf(fundingSourceType))) {
            AwardDocument document = getAwardDocument(fundingSourceId);
            deleteAwardSpecialReview(document, protocolNumber);
        } else if (StringUtils.equals(FundingSourceType.INSTITUTIONAL_PROPOSAL, String.valueOf(fundingSourceType))) {
            InstitutionalProposalDocument document = getInstitutionalProposalDocument(fundingSourceId);
            deleteInstitutionalProposalSpecialReview(document, protocolNumber);
        }
    }
    
    private void deleteAwardSpecialReview(AwardDocument document, String protocolNumber) throws WorkflowException {
        if (document != null) {
            List<AwardSpecialReview> deletedSpecialReviews = new ArrayList<AwardSpecialReview>();
            
            for (AwardSpecialReview specialReview : document.getAward().getSpecialReviews()) {
                if (StringUtils.equals(specialReview.getProtocolNumber(), protocolNumber)) {
                    deletedSpecialReviews.add(specialReview);
                }
            }
            
            for (AwardSpecialReview deletedSpecialReview : deletedSpecialReviews) {
                document.getAward().getSpecialReviews().remove(deletedSpecialReview);
            }
            
            getDocumentService().saveDocument(document);
        }
    }
    
    private void deleteInstitutionalProposalSpecialReview(InstitutionalProposalDocument document, String protocolNumber) throws WorkflowException {
        if (document != null) {
            List<InstitutionalProposalSpecialReview> deletedSpecialReviews = new ArrayList<InstitutionalProposalSpecialReview>();
            
            for (InstitutionalProposalSpecialReview specialReview : document.getInstitutionalProposal().getSpecialReviews()) {
                if (StringUtils.equals(specialReview.getProtocolNumber(), protocolNumber)) {
                    deletedSpecialReviews.add(specialReview);
                }
            }
            
            for (InstitutionalProposalSpecialReview deletedSpecialReview : deletedSpecialReviews) {
                document.getInstitutionalProposal().getSpecialReviews().remove(deletedSpecialReview);
            }
            
            getDocumentService().saveDocument(document);
        }
    }
    
    private AwardDocument getAwardDocument(String awardId) throws WorkflowException {
        AwardDocument document = null;
        
        if (NumberUtils.isNumber(awardId)) {
            Award award = getAwardService().getAward(Long.valueOf(awardId));
            if (award != null) {
                String documentNumber = award.getAwardDocument().getDocumentNumber();
                document = (AwardDocument) getDocumentService().getByDocumentHeaderId(documentNumber);
            }
        }
        
        return document;
    }
    
    private InstitutionalProposalDocument getInstitutionalProposalDocument(String institutionalProposalId) throws WorkflowException {
        InstitutionalProposalDocument document = null;
        
        if (StringUtils.isNotBlank(institutionalProposalId)) {
            InstitutionalProposal institutionalProposal = getInstitutionalProposalService().getInstitutionalProposal(institutionalProposalId);
            if (institutionalProposal != null) {
                String documentNumber = institutionalProposal.getInstitutionalProposalDocument().getDocumentNumber();
                document = (InstitutionalProposalDocument) getDocumentService().getByDocumentHeaderId(documentNumber);
            }
        }
        
        return document;
    }
    
    private ProtocolDocument getProtocolDocument(String protocolNumber) throws WorkflowException {
        ProtocolDocument document = null;
        
        if (StringUtils.isNotBlank(protocolNumber)) {
            Protocol protocol = getProtocolFinderDao().findCurrentProtocolByNumber(protocolNumber);
            if (protocol != null) {
                String documentNumber = protocol.getProtocolDocument().getDocumentNumber();
                document = (ProtocolDocument) getDocumentService().getByDocumentHeaderId(documentNumber);
            }
        }
        
        return document;
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

}