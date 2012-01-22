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
package org.kuali.kra.proposaldevelopment.specialreview;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.BooleanUtils;
import org.kuali.kra.common.specialreview.web.struts.form.SpecialReviewHelperBase;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;

/**
 * Defines the Special Review Helper for Development Proposal.
 */
public class SpecialReviewHelper extends SpecialReviewHelperBase<ProposalSpecialReview> {

    private static final long serialVersionUID = 8832539481443727887L;

    private static final String PROTOCOL_DEVELOPMENT_PROPOSAL_LINKING_ENABLED_PARAMETER = "irb.protocol.development.proposal.linking.enabled";
    
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
    protected boolean isProtocolLinkingEnabledForModule() {
        return getParameterService().getParameterValueAsBoolean(NAMESPACE_CODE, PARAMETER_CODE, PROTOCOL_DEVELOPMENT_PROPOSAL_LINKING_ENABLED_PARAMETER);
    }

    @Override
    protected List<ProposalSpecialReview> getSpecialReviews() {
        return form.getDocument().getDevelopmentProposal().getPropSpecialReviews();
    }
    
}