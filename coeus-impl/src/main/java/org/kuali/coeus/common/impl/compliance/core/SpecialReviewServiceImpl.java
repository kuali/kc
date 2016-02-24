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
package org.kuali.coeus.common.impl.compliance.core;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.kuali.coeus.common.framework.compliance.core.*;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.specialreview.ProposalSpecialReview;
import org.kuali.coeus.propdev.impl.specialreview.ProposalSpecialReviewExemption;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.award.specialreview.AwardSpecialReview;
import org.kuali.kra.award.specialreview.AwardSpecialReviewExemption;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.iacuc.IacucProtocolFinderDao;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalService;
import org.kuali.kra.institutionalproposal.specialreview.InstitutionalProposalSpecialReview;
import org.kuali.kra.institutionalproposal.specialreview.InstitutionalProposalSpecialReviewExemption;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolFinderDao;
import org.kuali.kra.irb.protocol.funding.ProtocolFundingSource;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Implements the SpecialReviewService.
 */
@Component("specialReviewService")
@Lazy
public class SpecialReviewServiceImpl implements SpecialReviewService {
    
    private static final String PROTOCOL_NUMBER = ".protocolNumber";

    @Autowired
    @Qualifier("awardService")
    private AwardService awardService;
    @Autowired
    @Qualifier("institutionalProposalService")
    private InstitutionalProposalService institutionalProposalService;
    @Autowired
    @Qualifier("protocolFinderDao")
    private ProtocolFinderDao protocolFinderDao;
    @Autowired
    @Qualifier("iacucProtocolFinderDao")
    private IacucProtocolFinderDao iacucProtocolFinderDao;
    @Autowired
    @Qualifier("documentService")
    private DocumentService documentService;
    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;
    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;
    
    @Override
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
    
    @Override
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

    @Override
    public String getViewSpecialReviewProtocolRouteHeaderId(String protocolNumber) throws Exception {
        String routeHeaderId = null;
        
        if (StringUtils.isNotBlank(protocolNumber)) {
            Protocol protocol = getProtocolFinderDao().findCurrentProtocolByNumber(protocolNumber);
            if (protocol != null) {
                Document document = getDocumentService().getByDocumentHeaderId(protocol.getProtocolDocument().getDocumentNumber());
                routeHeaderId = document.getDocumentHeader().getWorkflowDocument().getDocumentId();
            }
        }
        
        return routeHeaderId;
    }

    @Override
    public String getViewSpecialReviewProtocolRouteHeaderId(String protocolNumber, String specialReviewTypeCode) throws Exception {
        String routeHeaderId = null;
        
        if (StringUtils.isNotBlank(protocolNumber)) {
            if (SpecialReviewType.HUMAN_SUBJECTS.equals(specialReviewTypeCode) )
            {
                Protocol protocol = getProtocolFinderDao().findCurrentProtocolByNumber(protocolNumber);
                if (protocol != null) {
                Document document = getDocumentService().getByDocumentHeaderId(protocol.getProtocolDocument().getDocumentNumber());
                routeHeaderId = document.getDocumentHeader().getWorkflowDocument().getDocumentId();
                }
            }
            else if (SpecialReviewType.ANIMAL_USAGE.equals(specialReviewTypeCode) )
            {
                org.kuali.kra.protocol.ProtocolBase protocol = getIacucProtocolFinderDao().findCurrentProtocolByNumber(protocolNumber);
                if (protocol != null) {
                Document document = getDocumentService().getByDocumentHeaderId(protocol.getProtocolDocument().getDocumentNumber());
                routeHeaderId = document.getDocumentHeader().getWorkflowDocument().getDocumentId();
                }
            }

        }
        
        return routeHeaderId;
    }

    
    @Override
    @SuppressWarnings("unchecked")
    public boolean isLinkedToProtocolFundingSource(String protocolNumber, String fundingSourceNumber, String fundingSourceTypeCode) {
        boolean isLinkedToProtocolFundingSource = false;
        
        if (StringUtils.isNotBlank(protocolNumber)) {
            Protocol protocol = (Protocol)getProtocolFinderDao().findCurrentProtocolByNumber(protocolNumber);
            if (protocol != null) {
                List<ProtocolFundingSource> protocolFundingSources = (List)protocol.getProtocolFundingSources();
                for (ProtocolFundingSource protocolFundingSource : protocolFundingSources) {
                    if (StringUtils.equals(protocolFundingSource.getFundingSourceNumber(), fundingSourceNumber) 
                        && StringUtils.equals(protocolFundingSource.getFundingSourceTypeCode(), fundingSourceTypeCode)) {
                        isLinkedToProtocolFundingSource = true;
                        break;
                    }
                }
            }
        }
        
        return isLinkedToProtocolFundingSource;
    }

    @Override
    public boolean isLinkedToSpecialReview(String fundingSourceNumber, String fundingSourceTypeCode, String protocolNumber) {
        boolean isLinkedToSpecialReview = false;
        
        if (StringUtils.equals(FundingSourceType.AWARD, fundingSourceTypeCode)) {
            Award award = getAward(fundingSourceNumber);
            if (award != null) {
                isLinkedToSpecialReview = isLinkedToSpecialReviews(award.getSpecialReviews(), protocolNumber);
            }
        } else if (StringUtils.equals(FundingSourceType.INSTITUTIONAL_PROPOSAL, fundingSourceTypeCode)) {
            InstitutionalProposal institutionalProposal = getInstitutionalProposal(fundingSourceNumber);
            if (institutionalProposal != null) {
                institutionalProposal.refreshReferenceObject("specialReviews");
                isLinkedToSpecialReview = isLinkedToSpecialReviews(institutionalProposal.getSpecialReviews(), protocolNumber);
            }
        }
        else if (StringUtils.equals(FundingSourceType.PROPOSAL_DEVELOPMENT, fundingSourceTypeCode)) {
            DevelopmentProposal developmentProposal = getDevelopmentProposal(fundingSourceNumber);
            if (developmentProposal != null) {
                isLinkedToSpecialReview = isLinkedToSpecialReviews(developmentProposal.getPropSpecialReviews(), protocolNumber);
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

    @Override
    public void addProtocolFundingSourceForSpecialReview(String protocolNumber, String fundingSourceNumber, String fundingSourceTypeCode, 
        String fundingSourceName, String fundingSourceTitle) {
        
        Protocol protocol = getProtocolFinderDao().findCurrentProtocolByNumber(protocolNumber);
        if (protocol != null && StringUtils.isNotBlank(fundingSourceNumber) && NumberUtils.isNumber(fundingSourceTypeCode)) {
            ProtocolFundingSource protocolFundingSource = new ProtocolFundingSource();
            protocolFundingSource.setProtocolNumber(protocolNumber);
            protocolFundingSource.setFundingSourceNumber(fundingSourceNumber);
            protocolFundingSource.setFundingSourceTypeCode(fundingSourceTypeCode);
            protocolFundingSource.setFundingSourceName(fundingSourceName);
            protocolFundingSource.setFundingSourceTitle(fundingSourceTitle);
            protocol.getProtocolFundingSources().add(protocolFundingSource);
            
            getBusinessObjectService().save(protocol);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void deleteProtocolFundingSourceForSpecialReview(String protocolNumber, String fundingSourceNumber, String fundingSourceTypeCode) {
        Protocol protocol = getProtocolFinderDao().findCurrentProtocolByNumber(protocolNumber);
        if (protocol != null) {
            List<ProtocolFundingSource> deletedProtocolFundingSources = new ArrayList<ProtocolFundingSource>();
            
            List<ProtocolFundingSource> protocolFundingSources = (List)protocol.getProtocolFundingSources();
            for (ProtocolFundingSource protocolFundingSource : protocolFundingSources) {
                if (StringUtils.equals(protocolFundingSource.getFundingSourceNumber(), fundingSourceNumber) 
                    && StringUtils.equals(protocolFundingSource.getFundingSourceTypeCode(), fundingSourceTypeCode)) {
                    deletedProtocolFundingSources.add(protocolFundingSource);
                }
            }
            
            getBusinessObjectService().delete(deletedProtocolFundingSources);
        }
    }

    @Override
    public void addSpecialReviewForProtocolFundingSource(String fundingSourceNumber, String fundingSourceTypeCode, String protocolNumber, Date applicationDate, 
        Date approvalDate, Date expirationDate, String specialReviewType, List<String> exemptionTypeCodes) {
        
        if (StringUtils.equals(FundingSourceType.AWARD, fundingSourceTypeCode)) {
            Award award = getAward(fundingSourceNumber);
            addAwardSpecialReview(award, protocolNumber, specialReviewType, applicationDate, approvalDate, expirationDate, exemptionTypeCodes);
        } else if (StringUtils.equals(FundingSourceType.INSTITUTIONAL_PROPOSAL, fundingSourceTypeCode)) {
            InstitutionalProposal institutionalProposal = getInstitutionalProposal(fundingSourceNumber);
            addInstitutionalProposalSpecialReview(institutionalProposal, protocolNumber, specialReviewType, applicationDate, approvalDate, expirationDate, exemptionTypeCodes);
        }
        else if (StringUtils.equals(FundingSourceType.PROPOSAL_DEVELOPMENT, fundingSourceTypeCode)){
            DevelopmentProposal developmentProposal = getDevelopmentProposal(fundingSourceNumber);
            addDevelopmentProposalSpecialReview(developmentProposal, protocolNumber, specialReviewType, applicationDate, approvalDate, expirationDate, exemptionTypeCodes);
        }
    }
    
    private void addAwardSpecialReview(Award award, String protocolNumber, String specialReviewType, Date applicationDate, Date approvalDate, Date expirationDate,
        List<String> exemptionTypeCodes) {
        
        if (award != null) {
            Integer specialReviewNumber = award.getAwardDocument().getDocumentNextValue(Constants.SPECIAL_REVIEW_NUMBER);
            
            AwardSpecialReview specialReview = new AwardSpecialReview();
            specialReview.setSpecialReviewNumber(specialReviewNumber);
            specialReview.setSpecialReviewTypeCode(specialReviewType);
            specialReview.setApprovalTypeCode(getSpecialReviewProtocolLinkType(specialReviewType));
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
            specialReview.setComments(ComplianceConstants.NEW_SPECIAL_REVIEW_COMMENT);
            award.getSpecialReviews().add(specialReview);
            
            getBusinessObjectService().save(award);
        }
    }
    
    private void addInstitutionalProposalSpecialReview(InstitutionalProposal institutionalProposal, String protocolNumber, String specialReviewType, Date applicationDate,
        Date approvalDate, Date expirationDate, List<String> exemptionTypeCodes) {
        
        if (institutionalProposal != null) {
            Integer specialReviewNumber = institutionalProposal.getInstitutionalProposalDocument().getDocumentNextValue(Constants.SPECIAL_REVIEW_NUMBER);
            
            InstitutionalProposalSpecialReview specialReview = new InstitutionalProposalSpecialReview();
            specialReview.setSpecialReviewNumber(specialReviewNumber);
            specialReview.setSpecialReviewTypeCode(specialReviewType);
            specialReview.setApprovalTypeCode(getSpecialReviewProtocolLinkType(specialReviewType));
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
            specialReview.setComments(ComplianceConstants.NEW_SPECIAL_REVIEW_COMMENT);
            institutionalProposal.getSpecialReviews().add(specialReview);
            
            getBusinessObjectService().save(institutionalProposal);
        }
    }

    protected void addDevelopmentProposalSpecialReview(DevelopmentProposal developmentProposal, String protocolNumber, String specialReviewType, Date applicationDate,
                                                     Date approvalDate, Date expirationDate, List<String> exemptionTypeCodes) {

        if (developmentProposal != null) {
            Integer specialReviewNumber = developmentProposal.getProposalDocument().getDocumentNextValue(Constants.SPECIAL_REVIEW_NUMBER);

            ProposalSpecialReview specialReview = new ProposalSpecialReview();
            specialReview.setDevelopmentProposal(developmentProposal);
            specialReview.setSpecialReviewNumber(specialReviewNumber);
            specialReview.setSpecialReviewTypeCode(specialReviewType);
            specialReview.setApprovalTypeCode(getSpecialReviewProtocolLinkType(specialReviewType));
            specialReview.setProtocolNumber(protocolNumber);
            specialReview.setApplicationDate(applicationDate);
            specialReview.setApprovalDate(approvalDate);
            specialReview.setExpirationDate(expirationDate);
            for (String exemptionTypeCode : exemptionTypeCodes) {
                ProposalSpecialReviewExemption specialReviewExemption = new ProposalSpecialReviewExemption();
                specialReviewExemption.setProposalSpecialReview(specialReview);
                specialReviewExemption.setExemptionTypeCode(exemptionTypeCode);
                specialReview.getSpecialReviewExemptions().add(specialReviewExemption);
            }
            specialReview.setComments(ComplianceConstants.NEW_SPECIAL_REVIEW_COMMENT);
            developmentProposal.getPropSpecialReviews().add(specialReview);

            getDataObjectService().save(developmentProposal);
        }
    }

    @Override
    public void deleteSpecialReviewForProtocolFundingSource(String fundingSourceNumber, String fundingSourceTypeCode, String protocolNumber) {
        if (StringUtils.equals(FundingSourceType.AWARD, fundingSourceTypeCode)) {
            Award award = getAward(fundingSourceNumber);
            deleteAwardSpecialReview(award, protocolNumber);
        } else if (StringUtils.equals(FundingSourceType.INSTITUTIONAL_PROPOSAL, fundingSourceTypeCode)) {
            InstitutionalProposal institutionalProposal = getInstitutionalProposal(fundingSourceNumber);
            deleteInstitutionalProposalSpecialReview(institutionalProposal, protocolNumber);
        }
        else if (StringUtils.equals(FundingSourceType.PROPOSAL_DEVELOPMENT, fundingSourceTypeCode)) {
            DevelopmentProposal developmentProposal = getDevelopmentProposal(fundingSourceNumber);
            deleteDevelopmentProposalSpecialReview(developmentProposal, protocolNumber);
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
            institutionalProposal.refreshReferenceObject("specialReviews");

            for (InstitutionalProposalSpecialReview specialReview : institutionalProposal.getSpecialReviews()) {
                if (StringUtils.equals(specialReview.getProtocolNumber(), protocolNumber)) {
                    deletedSpecialReviews.add(specialReview);
                }
            }
            
            getBusinessObjectService().delete(deletedSpecialReviews);
        }
    }

    protected void deleteDevelopmentProposalSpecialReview(DevelopmentProposal developmentProposal, String protocolNumber) {
        if (developmentProposal != null) {
            for (ProposalSpecialReview specialReview : developmentProposal.getPropSpecialReviews()) {
                if (StringUtils.equals(specialReview.getProtocolNumber(), protocolNumber)) {
                    getDataObjectService().delete(specialReview);
                    break;
                }
            }
        }
    }
    
    private Award getAward(String fundingSourceNumber) {
        Award award = null;
        
        List<Award> awards = getAwardService().findAwardsForAwardNumber(fundingSourceNumber);
        
        if (!awards.isEmpty()) {
            award = awards.get(awards.size() - 1);
        }
        
        return award;
    }
    
    private InstitutionalProposal getInstitutionalProposal(String fundingSourceNumber) {
        InstitutionalProposal institutionalProposal = getInstitutionalProposalService().getActiveInstitutionalProposalVersion(fundingSourceNumber);
        
        if (institutionalProposal == null) {
            institutionalProposal = getInstitutionalProposalService().getPendingInstitutionalProposalVersion(fundingSourceNumber);
        }

        return institutionalProposal;
    }

    protected DevelopmentProposal getDevelopmentProposal(String proposalNumber) {
        return getDataObjectService().find(DevelopmentProposal.class, proposalNumber);
    }

    protected String getSpecialReviewProtocolLinkType(String specialReviewType){
        if (specialReviewType.equals(SpecialReviewType.HUMAN_SUBJECTS)){
            return SpecialReviewApprovalType.LINK_TO_IRB;
        }
        else{
            return SpecialReviewApprovalType.LINK_TO_IACUC;
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
    
    public IacucProtocolFinderDao getIacucProtocolFinderDao() {
        return iacucProtocolFinderDao;
    }

    public void setIacucProtocolFinderDao(IacucProtocolFinderDao iacucProtocolFinderDao) {
        this.iacucProtocolFinderDao = iacucProtocolFinderDao;
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

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }
}
