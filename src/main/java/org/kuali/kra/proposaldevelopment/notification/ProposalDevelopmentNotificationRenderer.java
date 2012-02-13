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
package org.kuali.kra.proposaldevelopment.notification;

import java.text.SimpleDateFormat;
import java.util.Map;

import org.kuali.kra.common.notification.NotificationRendererBase;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalChangedData;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService;

/**
 * Renders fields for the Proposal Development notifications.
 */
public class ProposalDevelopmentNotificationRenderer extends NotificationRendererBase {

    private static final long serialVersionUID = 1143944858168503090L;

    private DevelopmentProposal developmentProposal;
    private ProposalChangedData proposalChangedData;
    
    private transient ProposalDevelopmentService proposalDevelopmentService;
    
    public ProposalDevelopmentNotificationRenderer() {
        
    }
    
    /**
     * Constructs a Proposal Development notification renderer.
     * @param developmentProposal
     */
    public ProposalDevelopmentNotificationRenderer(DevelopmentProposal developmentProposal) {
        this.developmentProposal = developmentProposal;
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.NotificationRenderer#getReplacementParameters()
     */
    public Map<String, String> getDefaultReplacementParameters() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        Map<String, String> result = super.getDefaultReplacementParameters();
        result.put("{PROPOSAL_NUMBER}", developmentProposal.getProposalNumber());
        result.put("{PROPOSAL_TITLE}", developmentProposal.getTitle());
        result.put("{SPONSOR_CODE}", developmentProposal.getSponsorCode());
        result.put("{SPONSOR_NAME}", developmentProposal.getSponsorName());
        result.put("{PROGRAM_ANNOUNCEMENT_NUMBER}", developmentProposal.getProgramAnnouncementNumber());
        result.put("{PROGRAM_ANNOUNCEMENT_TITLE}", developmentProposal.getProgramAnnouncementTitle());
        result.put("{CFDA_NUMBER}", developmentProposal.getCfdaNumber());
        result.put("{DEADLINE_DATE}", dateFormatter.format(developmentProposal.getDeadlineDate()));
        result.put("{PI_NAME}", developmentProposal.getPrincipalInvestigatorName());
        result.put("{LEAD_UNIT}", developmentProposal.getUnitNumber());
        result.put("{LEAD_UNIT_NAME}", developmentProposal.getUnit().getUnitName());
        result.put("{PRIME_SPONSOR_CODE}", developmentProposal.getPrimeSponsorCode());
        result.put("{PRIME_SPONSOR_NAME}", developmentProposal.getPrimeSponsor() != null ? developmentProposal.getPrimeSponsor().getSponsorName() : "");
        InstitutionalProposal instProp = getProposalDevelopmentService().getInstitutionalProposal(developmentProposal.getProposalNumber());
        result.put("{INSTITUTIONAL_PROPOSAL_NUMBER}", instProp != null ? instProp.getProposalNumber() : "");
        if (proposalChangedData != null) {
            result.put("{OVERRIDE_FIELD_NAME}", proposalChangedData.getEditableColumn().getColumnLabel());
            result.put("{OVERRIDE_FIELD_VALUE}", proposalChangedData.getDisplayValue());
        }
        return result;
    }

    public DevelopmentProposal getDevelopmentProposal() {
        return developmentProposal;
    }

    public void setDevelopmentProposal(DevelopmentProposal developmentProposal) {
        this.developmentProposal = developmentProposal;
    }

    public ProposalDevelopmentService getProposalDevelopmentService() {
        return proposalDevelopmentService;
    }

    public void setProposalDevelopmentService(ProposalDevelopmentService proposalDevelopmentService) {
        this.proposalDevelopmentService = proposalDevelopmentService;
    }

    public ProposalChangedData getProposalChangedData() {
        return proposalChangedData;
    }

    public void setProposalChangedData(ProposalChangedData proposalChangedData) {
        this.proposalChangedData = proposalChangedData;
    }
    
    
}