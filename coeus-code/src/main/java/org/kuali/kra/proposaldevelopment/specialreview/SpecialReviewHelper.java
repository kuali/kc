/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.specialreview;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.common.specialreview.web.struts.form.SpecialReviewHelperBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.document.authorization.ProposalTask;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentSpecialReviewService;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.krad.util.GlobalVariables;

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
    
    /**
     * Constructs a SpecialReviewHelper.
     * @param form the container form
     */
    public SpecialReviewHelper(ProposalDevelopmentForm form) {
        proposalDevelopmentDocument = form.getProposalDevelopmentDocument();
        modifySpecialReviewPermission = BooleanUtils.toBoolean((String) form.getEditingMode().get("modifyProposal"));
        setNewSpecialReview(new ProposalSpecialReview());
        setLinkedProtocolNumbers(new ArrayList<String>());
    }
    
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

    private TaskAuthorizationService getTaskAuthorizationService() {
        return KraServiceLocator.getService(TaskAuthorizationService.class);
    }

    private String getUserIdentifier() {
        return GlobalVariables.getUserSession().getPrincipalId();
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
            proposalDevelopmentSpecialReviewService = KraServiceLocator.getService(ProposalDevelopmentSpecialReviewService.class);
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