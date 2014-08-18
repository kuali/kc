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

import org.kuali.coeus.common.framework.mail.EmailAttachment;
import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.common.notification.impl.NotificationContextBase;
import org.kuali.coeus.common.notification.impl.NotificationRenderer;
import org.kuali.coeus.common.notification.impl.service.KcNotificationModuleRoleService;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;

import java.util.List;

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
        
        setNotificationService(KcServiceLocator.getService(KcNotificationService.class));
        setNotificationModuleRoleService(KcServiceLocator.getService(KcNotificationModuleRoleService.class));
        InstitutionalProposalNotificationRoleQualifierService roleQualifier = KcServiceLocator.getService(InstitutionalProposalNotificationRoleQualifierService.class);
        roleQualifier.setInstitutionalProposal(institutionalProposal);
        setNotificationRoleQualifierService(roleQualifier);
    }
    
    public InstitutionalProposalNotificationContext(InstitutionalProposal institutionalProposal, String actionTypeCode, String contextName, String forwardName) {
        this(institutionalProposal, actionTypeCode, contextName, KcServiceLocator.getService(InstitutionalProposalNotificationRenderer.class), forwardName);
        ((InstitutionalProposalNotificationRenderer) this.getRenderer()).setInstitutionalProposal(institutionalProposal);
    }
    
    @Override
    public String getModuleCode() {
        return CoeusModule.INSTITUTIONAL_PROPOSAL_MODULE_CODE;
    }
    
    @Override
    public String getDocumentNumber() {
        return documentNumber;
    }
    
    @Override
    public String getActionTypeCode() {
        return actionTypeCode;
    }
    
    @Override
    public String getContextName() {
        return contextName;
    }

    @Override
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