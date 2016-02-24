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
package org.kuali.kra.institutionalproposal.notification;

import org.apache.commons.lang3.StringUtils;
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
    private static final String MM_DD_YYYY = "MM/dd/yyyy";

    private InstitutionalProposal institutionalProposal;
    
    public InstitutionalProposalNotificationRenderer() {
        super();
    }

    public InstitutionalProposalNotificationRenderer(InstitutionalProposal institutionalProposal) {
        this.institutionalProposal = institutionalProposal;
    }

    @Override
    public Map<String, String> getDefaultReplacementParameters() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(MM_DD_YYYY);
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
            result.put("{DEADLINE_DATE}", StringUtils.EMPTY);
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
