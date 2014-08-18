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
package org.kuali.coeus.propdev.impl.notification;

import org.kuali.coeus.common.framework.mail.EmailAttachment;
import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.common.notification.impl.NotificationContextBase;
import org.kuali.coeus.common.notification.impl.NotificationRenderer;
import org.kuali.coeus.common.notification.impl.service.KcNotificationModuleRoleService;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;

import java.util.List;

/**
 * This class extends the notification context base and provides some helpful functions for any Proposal Development specific events.
 */
public class ProposalDevelopmentNotificationContext extends NotificationContextBase {

    private static final long serialVersionUID = 7899968257291957401L;
    
    private DevelopmentProposal proposal;
    private String documentNumber;
    private String actionTypeCode;
    private String contextName;
    private List<EmailAttachment> emailAttachments;

    private transient KcNotificationService kcNotificationService;
    private transient KcNotificationModuleRoleService kcNotificationModuleRoleService;
    private transient ProposalDevelopmentNotificationRoleQualifierService proposalDevelopmentNotificationRoleQualifierService;

    protected KcNotificationService getKcNotificationService (){
        if (kcNotificationService == null)
            kcNotificationService = KcServiceLocator.getService(KcNotificationService.class);
        return kcNotificationService;
    }


    protected ProposalDevelopmentNotificationRoleQualifierService getProposalDevelopmentNotificationRoleQualifierService(){
        if (proposalDevelopmentNotificationRoleQualifierService == null)
            proposalDevelopmentNotificationRoleQualifierService = KcServiceLocator.getService(ProposalDevelopmentNotificationRoleQualifierService.class);
        return proposalDevelopmentNotificationRoleQualifierService;
    }

    protected KcNotificationModuleRoleService getKcNotificationModuleRoleService(){
        if(kcNotificationModuleRoleService == null)
               kcNotificationModuleRoleService = KcServiceLocator.getService(KcNotificationModuleRoleService.class);
        return kcNotificationModuleRoleService;
    }

    /**
     * Constructs a Proposal Development notification context and sets the necessary services.
     * @param developmentProposal
     * @param actionTypeCode
     * @param contextName
     * @param renderer
     */
    public ProposalDevelopmentNotificationContext(DevelopmentProposal developmentProposal, String actionTypeCode, String contextName, 
                                                  NotificationRenderer renderer) {
        super(renderer);
        
        this.proposal = developmentProposal;
        this.documentNumber = developmentProposal.getProposalDocument().getDocumentNumber();
        this.actionTypeCode = actionTypeCode;
        this.contextName = contextName;
        
        setNotificationService(getKcNotificationService());
        setNotificationModuleRoleService(getKcNotificationModuleRoleService());
        ProposalDevelopmentNotificationRoleQualifierService roleQualifier = getProposalDevelopmentNotificationRoleQualifierService();
        roleQualifier.setDevelopmentProposal(developmentProposal);
        setNotificationRoleQualifierService(roleQualifier);
    }
    
    /**
     * 
     * Constructs a ProposalDevelopmentNotificationContext.java using a default Proposal Development Renderer.
     * @param developmentProposal
     * @param actionTypeCode
     * @param contextName
     */
    public ProposalDevelopmentNotificationContext(DevelopmentProposal developmentProposal, String actionTypeCode, String contextName) {
        this(developmentProposal, actionTypeCode, contextName,
                KcServiceLocator.getService(ProposalDevelopmentNotificationRenderer.class));
    }
    
    @Override
    public String getModuleCode() {
        return CoeusModule.PROPOSAL_DEVELOPMENT_MODULE_CODE;
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

    public DevelopmentProposal getProposal() {
        return proposal;
    }

    public void setProposal(DevelopmentProposal proposal) {
        this.proposal = proposal;
    }
    
}