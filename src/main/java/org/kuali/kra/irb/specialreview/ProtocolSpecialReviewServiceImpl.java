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
package org.kuali.kra.irb.specialreview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.SpecialReviewApprovalType;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolFinderDao;
import org.kuali.kra.common.specialreview.bo.SpecialReview;
import org.kuali.kra.irb.actions.submit.ProtocolExemptStudiesCheckListItem;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalState;
import org.kuali.kra.proposaldevelopment.specialreview.ProposalSpecialReview;
import org.kuali.rice.krad.service.BusinessObjectService;

public class ProtocolSpecialReviewServiceImpl implements ProtocolSpecialReviewService {

    private transient ProtocolFinderDao protocolFinderDao;

    @SuppressWarnings({ "rawtypes", "unchecked" })
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
        
        Protocol protocol = getProtocolFinderDao().findCurrentProtocolByNumber(lastApprovedProtocolNumber);

        if (protocol != null) {
            specialReview.setApprovalTypeCode(SpecialReviewApprovalType.LINK_TO_IRB);
            
            if (specialReview.getClass().equals(ProposalSpecialReview.class)) {
                ProposalSpecialReview psr = (ProposalSpecialReview) specialReview;
                DevelopmentProposal dp = getPropososalDevelopment(psr.getProposalNumber());
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
            List<String> exemptionTypeCodes = new ArrayList<String>();
            for (ProtocolExemptStudiesCheckListItem checkListItem : protocol.getProtocolSubmission().getExemptStudiesCheckList()) {
                exemptionTypeCodes.add(checkListItem.getExemptStudiesCheckListCode());
            }
            specialReview.setExemptionTypeCodes(exemptionTypeCodes);
        }

    
    }

    public ProtocolFinderDao getProtocolFinderDao() {
        if (protocolFinderDao == null) {
            protocolFinderDao = KraServiceLocator.getService(ProtocolFinderDao.class);
        }
        return protocolFinderDao;
    }

    public void setProtocolFinderDao(ProtocolFinderDao protocolFinderDao) {
        this.protocolFinderDao = protocolFinderDao;
    }

    /**
     * 
     * This method gets a DevelopmentProposal object based on the proposalNumber
     * @param proposalNumber
     * @return
     */
    protected DevelopmentProposal getPropososalDevelopment(String proposalNumber) {
        final String PROPOSAL_NUMBER = "PROPOSAL_NUMBER";
        DevelopmentProposal dp = null;
        if (proposalNumber != null) {
            Map<String, String> key = new HashMap<String, String>();
            key.put(PROPOSAL_NUMBER, proposalNumber);
            dp = KraServiceLocator.getService(BusinessObjectService.class).findByPrimaryKey(DevelopmentProposal.class, key);
        }
        return dp;
    }

}
