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
package org.kuali.coeus.propdev.impl.notification;

import org.kuali.coeus.common.framework.mail.EmailAttachment;
import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.common.notification.impl.NotificationContextBase;
import org.kuali.coeus.common.notification.impl.NotificationRenderer;
import org.kuali.coeus.common.notification.impl.bo.NotificationTypeRecipient;
import org.kuali.coeus.common.notification.impl.exception.UnknownRoleException;
import org.kuali.coeus.common.notification.impl.service.KcNotificationModuleRoleService;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;

import java.util.HashMap;
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

    @Override
    public void populateRoleQualifiers(NotificationTypeRecipient notificationRecipient) throws UnknownRoleException {
        if (notificationRecipient.getRoleQualifiers() == null) {
            notificationRecipient.setRoleQualifiers(new HashMap<String,String>());
        }
        notificationRecipient.getRoleQualifiers().put("documentNumber", this.getDocumentNumber());
        super.populateRoleQualifiers(notificationRecipient);
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
