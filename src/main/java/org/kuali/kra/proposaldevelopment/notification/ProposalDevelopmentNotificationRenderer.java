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

import java.util.Map;

import org.kuali.kra.common.notification.NotificationRendererBase;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;

/**
 * Renders fields for the Proposal Development notifications.
 */
public class ProposalDevelopmentNotificationRenderer extends NotificationRendererBase {

    private static final long serialVersionUID = 1143944858168503090L;

    private DevelopmentProposal developmentProposal;
    
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
        return super.getDefaultReplacementParameters();
    }

    public DevelopmentProposal getDevelopmentProposal() {
        return developmentProposal;
    }

    public void setDevelopmentProposal(DevelopmentProposal developmentProposal) {
        this.developmentProposal = developmentProposal;
    }
    
}