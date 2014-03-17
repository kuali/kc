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
package org.kuali.kra.institutionalproposal.notification;

import org.kuali.coeus.common.notification.impl.NotificationRendererBase;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Renders fields for the Institutional Proposal notifications.
 */
public class InstitutionalProposalNotificationRenderer extends NotificationRendererBase implements Serializable {

    private static final long serialVersionUID = 451541228341893685L;
    
    private InstitutionalProposal institutionalProposal;
    
    public InstitutionalProposalNotificationRenderer() {
        
    }
    
    /**
     * Constructs an Institutional Proposal notification renderer.
     * @param institutionalProposal
     */
    public InstitutionalProposalNotificationRenderer(InstitutionalProposal institutionalProposal) {
        this.institutionalProposal = institutionalProposal;
    }

    @Override
    public Map<String, String> getDefaultReplacementParameters() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        Map<String, String> result = super.getDefaultReplacementParameters();
        result.put("{PROPOSAL_NUMBER}", institutionalProposal.getProposalNumber());
        result.put("{PROPOSAL_TITLE}", institutionalProposal.getTitle());
        result.put("{PI_NAME}", institutionalProposal.getPiName());
        result.put("{LEAD_UNIT}", institutionalProposal.getLeadUnitNumber());
        result.put("{LEAD_UNIT_NAME}", institutionalProposal.getLeadUnitName());
        result.put("{SPONSOR_CODE}", institutionalProposal.getSponsorCode());
        result.put("{SPONSOR_NAME}", institutionalProposal.getSponsorName());
        result.put("{ACTIVITY_TYPE_CODE}", institutionalProposal.getActivityTypeCode());
        result.put("{ACTIVITY_TYPE_NAME}", institutionalProposal.getActivityTypeFromCode().getDescription());
        if (institutionalProposal.getDeadlineDate() != null) {
            result.put("{DEADLINE_DATE}", dateFormatter.format(institutionalProposal.getDeadlineDate()));
        } else {
            result.put("{DEADLINE_DATE}", "");
        }
        result.put("{DEADLINE_TIME}", institutionalProposal.getDeadlineTime());
        result.put("{CFDA_NUMBER}", institutionalProposal.getCfdaNumber());
        result.put("{OPPORTUNITY}", institutionalProposal.getOpportunity());
        return result;
    }

    public InstitutionalProposal getInstitutionalProposal() {
        return institutionalProposal;
    }

    public void setInstitutionalProposal(InstitutionalProposal institutionalProposal) {
        this.institutionalProposal = institutionalProposal;
    }
    
}