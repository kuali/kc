/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.propdev.impl.notification;

import org.kuali.coeus.common.notification.impl.NotificationRendererBase;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentService;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.propdev.impl.editable.ProposalChangedData;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Renders fields for the Proposal Development notifications.
 */

@Component("proposalDevelopmentNotificationRenderer")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ProposalDevelopmentNotificationRenderer extends NotificationRendererBase {

    private static final long serialVersionUID = 1143944858168503090L;

    private DevelopmentProposal developmentProposal;
    private ProposalChangedData proposalChangedData;
    private Narrative modifiedNarrative;
    private ProposalPerson proposalPerson;

    @Autowired
    @Qualifier("proposalDevelopmentService")
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

    @Override
    public Map<String, String> getDefaultReplacementParameters() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        Map<String, String> result = super.getDefaultReplacementParameters();
        result.put("{DOCUMENT_NUMBER}",developmentProposal.getProposalDocument().getDocumentNumber());
        result.put("{PROPOSAL_NUMBER}", developmentProposal.getProposalNumber());
        result.put("{PROPOSAL_TITLE}", developmentProposal.getTitle());
        result.put("{PRINCIPAL INVESTIGATOR}",developmentProposal.getPrincipalInvestigatorName());
        result.put("{SPONSOR_CODE}", developmentProposal.getSponsorCode());
        result.put("{SPONSOR_NAME}", developmentProposal.getSponsorName());
        result.put("{START_DATE}",developmentProposal.getRequestedStartDateInitial().toString());
        result.put("{END_DATE}",developmentProposal.getRequestedEndDateInitial().toString());
        result.put("{PROGRAM_ANNOUNCEMENT_NUMBER}", developmentProposal.getProgramAnnouncementNumber());
        result.put("{PROGRAM_ANNOUNCEMENT_TITLE}", developmentProposal.getProgramAnnouncementTitle());
        result.put("{CFDA_NUMBER}", developmentProposal.getCfdaNumber());
        if (developmentProposal.getDeadlineDate() != null) {
            result.put("{DEADLINE_DATE}", dateFormatter.format(developmentProposal.getDeadlineDate()));
        } else {
            result.put("{DEADLINE_DATE}", "");
        }
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
        if (modifiedNarrative != null) {
            result.put("{NARRATIVE_MODULE_NUM}", modifiedNarrative.getModuleNumber().toString());
            result.put("{NARRATIVE_TYPE}", modifiedNarrative.getNarrativeType().getDescription());
            result.put("{NARRATIVE_MODULE_DESCRIPTION}", modifiedNarrative.getModuleTitle() == null ? "" : modifiedNarrative.getModuleTitle());
        }
        if (proposalPerson != null) {
            result.put("{USER_NAME}",proposalPerson.getUserName());
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

    public Narrative getModifiedNarrative() {
        return modifiedNarrative;
    }

    public void setModifiedNarrative(Narrative modifiedNarrative) {
        this.modifiedNarrative = modifiedNarrative;
    }

    public ProposalPerson getProposalPerson() {
        return proposalPerson;
    }

    public void setProposalPerson(ProposalPerson proposalPerson) {
        this.proposalPerson = proposalPerson;
    }
}
