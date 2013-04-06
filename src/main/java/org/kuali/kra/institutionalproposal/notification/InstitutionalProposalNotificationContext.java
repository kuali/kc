/*
 * Copyright 2005-2013 The Kuali Foundation
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

import java.util.List;

import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.common.notification.NotificationContextBase;
import org.kuali.kra.common.notification.NotificationRenderer;
import org.kuali.kra.common.notification.service.KcNotificationModuleRoleService;
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.util.EmailAttachment;

/**
 * This class extends the notification context base and provides some helpful functions for any Institutional Proposal specific events.
 */
public class InstitutionalProposalNotificationContext extends NotificationContextBase {

    private static final long serialVersionUID = 2192183278537033594L;
    
    private InstitutionalProposal proposal;
    private String documentNumber;
    private String actionTypeCode;
    private String contextName;
    private List<EmailAttachment> emailAttachments;
    private String forwardName;

    /**
     * Constructs an Institutional Proposal notification context and sets the necessary services.
     * @param institutionalProposal
     * @param actionTypeCode
     * @param contextName
     * @param renderer
     */
    public InstitutionalProposalNotificationContext(InstitutionalProposal institutionalProposal, String actionTypeCode, String contextName, 
                                                    NotificationRenderer renderer, String forwardName) {
        super(renderer);
        
        this.proposal = institutionalProposal;
        this.documentNumber = institutionalProposal.getInstitutionalProposalDocument().getDocumentNumber();
        this.actionTypeCode = actionTypeCode;
        this.contextName = contextName;
        this.forwardName = forwardName;
        
        setNotificationService(KraServiceLocator.getService(KcNotificationService.class));
        setNotificationModuleRoleService(KraServiceLocator.getService(KcNotificationModuleRoleService.class));
        InstitutionalProposalNotificationRoleQualifierService roleQualifier = KraServiceLocator.getService(InstitutionalProposalNotificationRoleQualifierService.class);
        roleQualifier.setInstitutionalProposal(institutionalProposal);
        setNotificationRoleQualifierService(roleQualifier);
    }
    
    public InstitutionalProposalNotificationContext(InstitutionalProposal institutionalProposal, String actionTypeCode, String contextName, String forwardName) {
        this(institutionalProposal, actionTypeCode, contextName, KraServiceLocator.getService(InstitutionalProposalNotificationRenderer.class), forwardName);
        ((InstitutionalProposalNotificationRenderer) this.getRenderer()).setInstitutionalProposal(institutionalProposal);
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.NotificationContextBase#getModuleCode()
     */
    public String getModuleCode() {
        return CoeusModule.INSTITUTIONAL_PROPOSAL_MODULE_CODE;
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.NotificationContextBase#getDocumentNumber()
     */
    public String getDocumentNumber() {
        return documentNumber;
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.NotificationContext#getActionTypeCode()
     */
    public String getActionTypeCode() {
        return actionTypeCode;
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.NotificationContext#getContextName()
     */
    public String getContextName() {
        return contextName;
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.NotificationContext#getEmailAttachments()
     */
    public List<EmailAttachment> getEmailAttachments() {
        return emailAttachments;
    }

    /**
     * {@inheritDoc}
     * @param emailAttachments
     */
    public void setEmailAttachments(List<EmailAttachment> emailAttachments) {
        this.emailAttachments = emailAttachments;
    }

    public String getForwardName() {
        return forwardName;
    }

    public void setForwardName(String forwardName) {
        this.forwardName = forwardName;
    }
    
}