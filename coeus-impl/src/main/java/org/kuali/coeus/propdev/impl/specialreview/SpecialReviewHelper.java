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
package org.kuali.coeus.propdev.impl.specialreview;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.compliance.core.SpecialReviewHelperBase;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines the Special Review Helper for Development Proposal.
 */
public class SpecialReviewHelper extends SpecialReviewHelperBase<ProposalSpecialReview> {

    private static final long serialVersionUID = 8832539481443727887L;

    private ProposalDevelopmentSpecialReviewService proposalDevelopmentSpecialReviewService;
    private ProposalDevelopmentDocument proposalDevelopmentDocument;
    private boolean modifySpecialReviewPermission;
    
    public SpecialReviewHelper(ProposalDevelopmentDocument proposalDevelopmentDocument, boolean modifySpecialReviewPermission) {
        this.proposalDevelopmentDocument = proposalDevelopmentDocument;
        this.modifySpecialReviewPermission = modifySpecialReviewPermission;
        setNewSpecialReview(new ProposalSpecialReview());
        setLinkedProtocolNumbers(new ArrayList<String>()); 
    }
    

    @Override
    protected boolean hasModifySpecialReviewPermission(String principalId) {
        return modifySpecialReviewPermission;
    }
    
    @Override
    protected boolean isIrbProtocolLinkingEnabledForModule() {
        return getProposalDevelopmentSpecialReviewService().isIrbLinkingEnabled();
    }

    @Override
    protected boolean isIacucProtocolLinkingEnabledForModule() {
        return getProposalDevelopmentSpecialReviewService().isIacucLinkingEnabled();
    }

    public boolean isCreateIrbProtocolEnabled() {
        return getProposalDevelopmentSpecialReviewService().isCreateProtocolFromProposalEnabled(Constants.PROPOSAL_DEVELOPMENT_CREATE_IRB_PROTOCOL_ENABLED_PARAMETER);
    }

    public boolean isCreateIacucProtocolEnabled() {
        return getProposalDevelopmentSpecialReviewService().isCreateProtocolFromProposalEnabled(Constants.PROPOSAL_DEVELOPMENT_CREATE_IACUC_PROTOCOL_ENABLED_PARAMETER);
    }

        @Override
    protected List<ProposalSpecialReview> getSpecialReviews() {
        return getProposalDevelopmentDocument().getDevelopmentProposal().getPropSpecialReviews();
    }

    @Override
    public boolean isCanCreateIrbProtocol() {
        return getProposalDevelopmentSpecialReviewService().canCreateIrbProtocol(getProposalDevelopmentDocument());
    }

    @Override
    public boolean isCanCreateIacucProtocol() {
        return getProposalDevelopmentSpecialReviewService().canCreateIacucProtocol(getProposalDevelopmentDocument());
    }

    public void populatePropSpecialReviewApproverView(String summarySpecialReview)
    {

        if (!StringUtils.isEmpty(summarySpecialReview) )
       {
           String [] splitString =StringUtils.split(summarySpecialReview, ",");
           List<ProposalSpecialReview> propSpecialReviewFilteredList = new ArrayList<ProposalSpecialReview>();
            for(ProposalSpecialReview proposalSpecialReview : getProposalDevelopmentDocument().getDevelopmentProposal().getPropSpecialReviews())
            {
                for(int i=0; i<splitString.length; i++ ) {
                    if ( proposalSpecialReview.getSpecialReviewTypeCode().equals(splitString[i] ) )
                    {
                        propSpecialReviewFilteredList.add(proposalSpecialReview);
                    }
                }
            }
            getProposalDevelopmentDocument().getDevelopmentProposal().setPropSpecialReviews(propSpecialReviewFilteredList);
       }
   }

    public ProposalDevelopmentSpecialReviewService getProposalDevelopmentSpecialReviewService() {
        if (proposalDevelopmentSpecialReviewService == null) {
            proposalDevelopmentSpecialReviewService = KcServiceLocator.getService(ProposalDevelopmentSpecialReviewService.class);
        }
        return proposalDevelopmentSpecialReviewService;
    }

    public void setProposalDevelopmentSpecialReviewService(
            ProposalDevelopmentSpecialReviewService proposalDevelopmentSpecialReviewService) {
        this.proposalDevelopmentSpecialReviewService = proposalDevelopmentSpecialReviewService;
    }

    public ProposalDevelopmentDocument getProposalDevelopmentDocument() {
        return proposalDevelopmentDocument;
    }

    public void setProposalDevelopmentDocument(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this.proposalDevelopmentDocument = proposalDevelopmentDocument;
    }

}
