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
package org.kuali.kra.institutionalproposal.specialreview;

import org.apache.commons.lang3.BooleanUtils;
import org.kuali.coeus.common.framework.compliance.core.SpecialReviewHelperBase;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;
import org.kuali.rice.kns.authorization.AuthorizationConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines the Special Review Helper for Institutional Proposal.
 */
public class SpecialReviewHelper extends SpecialReviewHelperBase<InstitutionalProposalSpecialReview> {

    private static final long serialVersionUID = 6509860722698432447L;

    private static final String PROTOCOL_INSTITUTIONAL_PROPOSAL_LINKING_ENABLED_PARAMETER = "irb.protocol.institute.proposal.linking.enabled";
    private static final String IACUC_PROTOCOL_INSTITUTIONAL_PROPOSAL_LINKING_ENABLED_PARAMETER = "iacuc.protocol.institute.proposal.linking.enabled";
    
    private InstitutionalProposalForm form;
    
    /**
     * Constructs a SpecialReviewHelper.
     * @param form the container form
     */
    public SpecialReviewHelper(InstitutionalProposalForm form) {
        this.form = form;
        setNewSpecialReview(new InstitutionalProposalSpecialReview());
        setLinkedProtocolNumbers(new ArrayList<String>());
    }
    
    /**
     * Synchronizes the information between this Institutional Proposal's Special Reviews and the corresponding Protocol Funding Sources.
     */
    public void syncProtocolFundingSourcesWithSpecialReviews() {
        String fundingSourceNumber = form.getInstitutionalProposalDocument().getInstitutionalProposal().getProposalNumber();
        String fundingSourceTypeCode = FundingSourceType.INSTITUTIONAL_PROPOSAL;
        String fundingSourceName = form.getInstitutionalProposalDocument().getInstitutionalProposal().getSponsorName();
        String fundingSourceTitle = form.getInstitutionalProposalDocument().getInstitutionalProposal().getTitle();
        syncProtocolFundingSourcesWithSpecialReviews(fundingSourceNumber, fundingSourceTypeCode, fundingSourceName, fundingSourceTitle);
    }

    @Override
    protected boolean hasModifySpecialReviewPermission(String principalId) {
        return BooleanUtils.toBoolean((String) form.getEditingMode().get(AuthorizationConstants.EditMode.FULL_ENTRY));
    }
    
    @Override
    protected boolean isIrbProtocolLinkingEnabledForModule() {
        return getParameterService().getParameterValueAsBoolean(NAMESPACE_CODE, PARAMETER_CODE, PROTOCOL_INSTITUTIONAL_PROPOSAL_LINKING_ENABLED_PARAMETER);
    }

    @Override
    protected boolean isIacucProtocolLinkingEnabledForModule() {
        return getParameterService().getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_IACUC, PARAMETER_CODE, IACUC_PROTOCOL_INSTITUTIONAL_PROPOSAL_LINKING_ENABLED_PARAMETER);
    }

    @Override
    protected List<InstitutionalProposalSpecialReview> getSpecialReviews() {
        return form.getInstitutionalProposalDocument().getInstitutionalProposal().getSpecialReviews();
    }

    @Override
    public boolean isCanCreateIrbProtocol() {
        return false;
    }
    @Override
    public boolean isCanCreateIacucProtocol() {
        return false;
    }
    
}
