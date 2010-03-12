/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.institutionalproposal.fundedawards;

import java.io.Serializable;

import org.apache.commons.lang.ArrayUtils;
import org.kuali.kra.institutionalproposal.ProposalStatus;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;

/**
 * Web tier helper bean for Funded Awards panel in Institutional Proposal Actions.
 */
public class FundedAwardsBean implements Serializable {
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 7202420195219545687L;
    
    private InstitutionalProposalForm institutionalProposalForm;
    
    /**
     * Constructs a FundedAwardsBean.java.
     */
    public FundedAwardsBean() {
        super();
    }
    
    /**
     * Constructs a FundedAwardsBean.java.
     * @param institutionalProposalForm the Form.
     */
    public FundedAwardsBean(InstitutionalProposalForm institutionalProposalForm) {
        this();
        this.institutionalProposalForm = institutionalProposalForm;
    }
    
    /**
     * Unlock the selected awards.  If all awards are unlocked, proposal status should be set to 'pending'.
     */
    public void removeUnlockedAwards() {
        InstitutionalProposal institutionalProposal = institutionalProposalForm.getInstitutionalProposalDocument().getInstitutionalProposal();
        ArrayUtils.reverse(institutionalProposalForm.getSelectedAwardFundingProposals());
        for (String indexToRemove : institutionalProposalForm.getSelectedAwardFundingProposals()) {
            institutionalProposal.getAwardFundingProposals().remove(Integer.parseInt(indexToRemove));
        }
        if (institutionalProposal.getAwardFundingProposals().isEmpty()) {
            institutionalProposal.setStatusCode(ProposalStatus.PENDING);
        }
        institutionalProposalForm.setSelectedAwardFundingProposals(null);
    }
    
    /**
     * Mark all funded awards in the list as selected.
     */
    public void selectAllFundedAwards() {
        String[] selectedAwardFundingProposals = 
            new String[institutionalProposalForm.getInstitutionalProposalDocument().getInstitutionalProposal().getAwardFundingProposals().size()];
        for (int i = 0; i < institutionalProposalForm.getInstitutionalProposalDocument().getInstitutionalProposal().getAwardFundingProposals().size(); i++) {
            selectedAwardFundingProposals[i] = Integer.toString(i);
        }
        institutionalProposalForm.setSelectedAwardFundingProposals(selectedAwardFundingProposals);
    }

}
