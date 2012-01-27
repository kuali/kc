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

import org.kuali.kra.common.notification.bo.NotificationModuleRoleQualifier;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;

/**
 * Implements the InstitutionalProposalNotificationRoleQualifierService.
 */
public class InstitutionalProposalNotificationRoleQualifierServiceImpl implements InstitutionalProposalNotificationRoleQualifierService {

    private InstitutionalProposal institutionalProposal;
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.service.KcNotificationRoleQualifierService#getRoleQualifierValue(
     *      org.kuali.kra.common.notification.bo.NotificationModuleRoleQualifier)
     */
    @Override
    public String getRoleQualifierValue(NotificationModuleRoleQualifier qualifier) {
        String roleQualifierValue = null;
        
        // TODO: fill in role qualifier values here
        
        return roleQualifierValue;
    }

    public InstitutionalProposal getInstitutionalProposal() {
        return institutionalProposal;
    }
    
    public void setInstitutionalProposal(InstitutionalProposal institutionalProposal) {
        this.institutionalProposal = institutionalProposal;
    }

}