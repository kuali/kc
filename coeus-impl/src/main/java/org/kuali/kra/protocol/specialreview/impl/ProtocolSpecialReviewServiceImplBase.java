/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.protocol.specialreview.impl;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.compliance.core.SpecialReview;
import org.kuali.coeus.propdev.impl.state.ProposalState;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.specialreview.ProposalSpecialReview;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolFinderDao;
import org.kuali.kra.protocol.specialreview.ProtocolSpecialReviewService;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.HashMap;
import java.util.Map;


public abstract class ProtocolSpecialReviewServiceImplBase implements ProtocolSpecialReviewService {

    private transient ProtocolFinderDao protocolFinderDao;

    public DevelopmentProposal getPropososalDevelopment(String proposalNumber) {
        final String PROPOSAL_NUMBER = "PROPOSAL_NUMBER";
        DevelopmentProposal dp = null;
        if (proposalNumber != null) {
            Map<String, String> key = new HashMap<String, String>();
            key.put(PROPOSAL_NUMBER, proposalNumber);
            dp = KcServiceLocator.getService(BusinessObjectService.class).findByPrimaryKey(DevelopmentProposal.class, key);
        }
        return dp;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void populateSpecialReview(SpecialReview specialReview) {
        String protocolNumber = specialReview.getProtocolNumber();
        if ( protocolNumber == null)
        {
            return;
        }
        String lastApprovedProtocolNumber = protocolNumber;
        
        if (StringUtils.contains(protocolNumber, AMENDMENT_KEY)) {
            lastApprovedProtocolNumber = StringUtils.substringBefore(protocolNumber, AMENDMENT_KEY);
        } else if (StringUtils.contains(protocolNumber, RENEWAL_KEY)) {
            lastApprovedProtocolNumber = StringUtils.substringBefore(protocolNumber, RENEWAL_KEY);
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
