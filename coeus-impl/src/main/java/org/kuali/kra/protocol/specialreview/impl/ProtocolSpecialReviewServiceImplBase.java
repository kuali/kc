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
package org.kuali.kra.protocol.specialreview.impl;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.compliance.core.SpecialReview;
import org.kuali.coeus.propdev.impl.state.ProposalState;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.specialreview.ProposalSpecialReview;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolFinderDao;
import org.kuali.kra.protocol.ProtocolSpecialVersion;
import org.kuali.kra.protocol.specialreview.ProtocolSpecialReviewService;
import org.kuali.rice.krad.data.DataObjectService;


public abstract class ProtocolSpecialReviewServiceImplBase implements ProtocolSpecialReviewService {

    private transient ProtocolFinderDao protocolFinderDao;
    private DataObjectService dataObjectService;


    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void populateSpecialReview(SpecialReview specialReview) {
        String protocolNumber = specialReview.getProtocolNumber();
        if ( protocolNumber == null)
        {
            return;
        }
        String lastApprovedProtocolNumber = protocolNumber;
        
        if (StringUtils.contains(protocolNumber, ProtocolSpecialVersion.AMENDMENT.getCode())) {
            lastApprovedProtocolNumber = StringUtils.substringBefore(protocolNumber, ProtocolSpecialVersion.AMENDMENT.getCode());
        } else if (StringUtils.contains(protocolNumber, ProtocolSpecialVersion.RENEWAL.getCode())) {
            lastApprovedProtocolNumber = StringUtils.substringBefore(protocolNumber, ProtocolSpecialVersion.RENEWAL.getCode());
        }
        
        ProtocolBase protocol = getProtocolFinderDao().findCurrentProtocolByNumber(lastApprovedProtocolNumber);

        if (protocol != null) {
            setSpecialReviewApprovalTypeHook(specialReview);
            
            if (specialReview.getClass().equals(ProposalSpecialReview.class)) {
                ProposalSpecialReview psr = (ProposalSpecialReview) specialReview;
                DevelopmentProposal dp = getPropososalDevelopment(psr.getDevelopmentProposal().getProposalNumber());
                if (dp != null 
                        && (StringUtils.equals(dp.getProposalStateTypeCode(), ProposalState.APPROVED_AND_SUBMITTED)
                                || StringUtils.equals(dp.getProposalStateTypeCode(), ProposalState.DISAPPROVED)
                                || StringUtils.equals(dp.getProposalStateTypeCode(), ProposalState.APPROVED_POST_SUBMISSION)
                                || StringUtils.equals(dp.getProposalStateTypeCode(), ProposalState.DISAPPROVED_POST_SUBMISSION)
                                || StringUtils.equals(dp.getProposalStateTypeCode(), ProposalState.APPROVAL_PENDING_SUBMITTED))
                        && specialReview.getProtocolStatus() != null) {
                    // if the proposal is complete, do not get the fresh copy of the IRB status
                } else {
                    specialReview.setProtocolStatus(protocol.getProtocolStatus().getDescription());
                }
            } else {
                specialReview.setProtocolStatus(protocol.getProtocolStatus().getDescription());
            }
            
            specialReview.setProtocolNumber(protocol.getProtocolNumber());
            specialReview.setApplicationDate(protocol.getProtocolSubmission().getSubmissionDate());
            specialReview.setApprovalDate(protocol.getLastApprovalDate() == null ? protocol.getApprovalDate() : protocol.getLastApprovalDate());
            specialReview.setExpirationDate(protocol.getExpirationDate());
            
            setProtocolExemptStudiesCheckListItemHook(protocol, specialReview);
            
            specialReview.setLinkedToProtocol(true);
        }
    
    }

    // injected by spring
    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public DevelopmentProposal getPropososalDevelopment(String proposalNumber) {
        DevelopmentProposal dp = null;
        if (proposalNumber != null) {
            dp = getDataObjectService().find(DevelopmentProposal.class, proposalNumber);
        }
        return dp;
    }

    public ProtocolFinderDao getProtocolFinderDao() {
        if (protocolFinderDao == null) {
            protocolFinderDao = KcServiceLocator.getService(ProtocolFinderDao.class);
        }
        return protocolFinderDao;
    }

    public void setProtocolFinderDao(ProtocolFinderDao protocolFinderDao) {
        this.protocolFinderDao = protocolFinderDao;
    }

    @SuppressWarnings("rawtypes")
    protected abstract void setSpecialReviewApprovalTypeHook(SpecialReview  specialReview);
    
    @SuppressWarnings("rawtypes")
    protected abstract void setProtocolExemptStudiesCheckListItemHook(ProtocolBase protocol, SpecialReview  specialReview);

}
