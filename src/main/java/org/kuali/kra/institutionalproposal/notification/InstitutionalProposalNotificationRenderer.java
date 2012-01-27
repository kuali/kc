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
package org.kuali.kra.institutionalproposal.notification;

import java.util.Map;

import org.kuali.kra.common.notification.NotificationRendererBase;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;

/**
 * Renders fields for the Institutional Proposal notifications.
 */
public class InstitutionalProposalNotificationRenderer extends NotificationRendererBase {

    private static final long serialVersionUID = 451541228341893685L;
    
    private InstitutionalProposal institutionalProposal;
    
    /**
     * Constructs an Institutional Proposal notification renderer.
     * @param institutionalProposal
     */
    public InstitutionalProposalNotificationRenderer(InstitutionalProposal institutionalProposal) {
        this.institutionalProposal = institutionalProposal;
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.NotificationRenderer#getReplacementParameters()
     */
    public Map<String, String> getDefaultReplacementParameters() {
        return super.getDefaultReplacementParameters();
    }

    public InstitutionalProposal getInstitutionalProposal() {
        return institutionalProposal;
    }

    public void setInstitutionalProposal(InstitutionalProposal institutionalProposal) {
        this.institutionalProposal = institutionalProposal;
    }
    
}