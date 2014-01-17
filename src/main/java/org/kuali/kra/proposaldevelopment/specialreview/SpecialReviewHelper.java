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
package org.kuali.kra.proposaldevelopment.specialreview;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.common.specialreview.web.struts.form.SpecialReviewHelperBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.document.authorization.ProposalTask;
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

    private static final String PROTOCOL_DEVELOPMENT_PROPOSAL_LINKING_ENABLED_PARAMETER = "irb.protocol.development.proposal.linking.enabled";
    private static final String IACUC_PROTOCOL_PROPOSAL_DEVELOPMENT_LINKING_ENABLED_PARAMETER = "iacuc.protocol.proposal.development.linking.enabled";
    private ProposalDevelopmentForm form;
    
    /**
     * Constructs a SpecialReviewHelper.
     * @param form the container form
     */
    public SpecialReviewHelper(ProposalDevelopmentForm form) {
        this.form = form;
        setNewSpecialReview(new ProposalSpecialReview());
        setLinkedProtocolNumbers(new ArrayList<String>());
    }

    @Override
    protected boolean hasModifySpecialReviewPermission(String principalId) {
        return BooleanUtils.toBoolean((String) form.getEditingMode().get("modifyProposal"));
    }
    
    @Override
    protected boolean isIrbProtocolLinkingEnabledForModule() {
        return getParameterService().getParameterValueAsBoolean(NAMESPACE_CODE, PARAMETER_CODE, PROTOCOL_DEVELOPMENT_PROPOSAL_LINKING_ENABLED_PARAMETER);
    }

    @Override
    protected boolean isIacucProtocolLinkingEnabledForModule() {
        return getParameterService().getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_IACUC, PARAMETER_CODE, IACUC_PROTOCOL_PROPOSAL_DEVELOPMENT_LINKING_ENABLED_PARAMETER);
    }

    @Override
    protected List<ProposalSpecialReview> getSpecialReviews() {
        return form.getProposalDevelopmentDocument().getDevelopmentProposal().getPropSpecialReviews();
    }

    @Override
    public boolean isCanCreateIrbProtocol() {
        boolean canCreateIrbProtocol=false;
        // check for Protocol creation permission for IRB Protocol
        ProposalTask irbTask = new ProposalTask(ProposalTask.CREATE_IRB_PROTOCOL_FROM_PROPOSAL, form.getProposalDevelopmentDocument());
        canCreateIrbProtocol = getTaskAuthorizationService().isAuthorized(getUserIdentifier(), irbTask);
        return canCreateIrbProtocol;
    }

    @Override
    public boolean isCanCreateIacucProtocol() {
        boolean canCreateIacucProtocol=false;
        // check for Protocol creation permission for IACUC Protocol
        ProposalTask iacucTask = new ProposalTask(ProposalTask.CREATE_IACUC_PROTOCOL_FROM_PROPOSAL, form.getProposalDevelopmentDocument());
        canCreateIacucProtocol = getTaskAuthorizationService().isAuthorized(getUserIdentifier(), iacucTask);
        return canCreateIacucProtocol;
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
            for(ProposalSpecialReview proposalSpecialReview : form.getProposalDevelopmentDocument().getDevelopmentProposal().getPropSpecialReviews())
            {
                for(int i=0; i<splitString.length; i++ ) {
                    if ( proposalSpecialReview.getSpecialReviewTypeCode().equals(splitString[i] ) )
                    {
                        propSpecialReviewFilteredList.add(proposalSpecialReview);
                    }
                }
            }
            form.getProposalDevelopmentDocument().getDevelopmentProposal().setPropSpecialReviews(propSpecialReviewFilteredList);
       }
   }
}