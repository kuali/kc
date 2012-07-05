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
package org.kuali.kra.iacuc.protocol.funding;

import org.kuali.kra.bo.SpecialReviewApprovalType;
import org.kuali.kra.bo.SpecialReviewType;
import org.kuali.kra.common.specialreview.service.impl.SpecialReviewServiceImpl;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.protocol.funding.impl.ProtocolProposalDevelopmentDocumentServiceImpl;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.specialreview.ProposalSpecialReview;

/**
 * 
 * This service creates Proposal Development Document from Protocol for users authorized to create proposal. This created
 * proposal is then added to Protocol Funding sources. 
 */
public class IacucProtocolProposalDevelopmentDocumentServiceImpl extends ProtocolProposalDevelopmentDocumentServiceImpl {

    /*
     * Populate Special review specific to IACUC protocol
     */
    @Override
    public void populateProposalSpecialReview(Protocol protocol, ProposalDevelopmentDocument proposalDocument)
    {
    if (protocol != null) {
        Integer specialReviewNumber = proposalDocument.getDocumentNextValue(Constants.SPECIAL_REVIEW_NUMBER);
        
        ProposalSpecialReview specialReview = new ProposalSpecialReview();
        specialReview.setSpecialReviewNumber(specialReviewNumber);
        specialReview.setSpecialReviewTypeCode(SpecialReviewType.ANIMAL_USAGE);
        specialReview.setApprovalTypeCode(SpecialReviewApprovalType.PENDING);
        specialReview.setProtocolNumber(protocol.getProtocolNumber());
        specialReview.setProposalNumber(proposalDocument.getDevelopmentProposal().getProposalNumber());
        
        specialReview.setProtocolStatus(protocol.getProtocolStatus().getDescription());
        specialReview.setComments(SpecialReviewServiceImpl.NEW_SPECIAL_REVIEW_COMMENT);
        proposalDocument.getDevelopmentProposal().getPropSpecialReviews().add(specialReview);
        }
    }

}