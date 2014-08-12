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
package org.kuali.kra.irb.noteattachment;

import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.auth.ProtocolTask;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.noteattachment.NotesAttachmentsHelperBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * This is the "Helper" class for Protocol Notes And Attachments.
 */
public class NotesAttachmentsHelper extends NotesAttachmentsHelperBase {
   
    /**
     * Constructs a helper setting the dependencies to default values.
     * @param form the form
     * @throws IllegalArgumentException if the form is null
     */
    public NotesAttachmentsHelper(final ProtocolForm form) {
        super(form, KcServiceLocator.getService(ProtocolAttachmentService.class),
                   KcServiceLocator.getService(TaskAuthorizationService.class),
                   KcServiceLocator.getService(KcAuthorizationService.class),
                   KcServiceLocator.getService(DateTimeService.class),
                   KcServiceLocator.getService(ProtocolNotepadService.class),
                   KcServiceLocator.getService(ParameterService.class),
                   new ProtocolAttachmentVersioningUtility(form));
    }
        
    /**
     * Checks if Protocol Attachments can be modified.
     * @return true if can be modified false if cannot
     */
    public boolean canEditProtocolAttachments() {
        final ProtocolTask task = new ProtocolTask(TaskName.MODIFY_PROTOCOL_ATTACHMENTS, (Protocol) this.getProtocol());
        return this.authService.isAuthorized(this.getUserIdentifier(), task);
    }
        
    /**
     * Checks if Protocol Notepads can be modified.
     * @return true if can be modified false if cannot
     */
    public boolean canAddProtocolNotepads() {
        final ProtocolTask task = new ProtocolTask(TaskName.ADD_PROTOCOL_NOTES, (Protocol) this.getProtocol());
        return this.authService.isAuthorized(this.getUserIdentifier(), task);
    }
    
    /**
     * Checks if restricted Protocol Notepads can be viewed.
     * @return true if can be modified false if cannot
     */
    public boolean canViewRestrictedProtocolNotepads() {
        final ProtocolTask task = new ProtocolTask(TaskName.VIEW_RESTRICTED_NOTES, (Protocol) this.getProtocol());
        return this.authService.isAuthorized(this.getUserIdentifier(), task);
    }

    public boolean isProtocolAdmin() {
        return this.kraAuthorizationService.hasRole(GlobalVariables.getUserSession().getPrincipalId(), NAMESPACE, RoleConstants.IRB_ADMINISTRATOR);
    }
    
    @Override
    public AddProtocolAttachmentProtocolRuleImpl getAddProtocolAttachmentProtocolRuleInstanceHook() {
        return new AddProtocolAttachmentProtocolRuleImpl();
    }

    @Override
    protected ProtocolAttachmentProtocol createNewProtocolAttachmentProtocolInstanceHook(ProtocolBase protocol) {
        return new ProtocolAttachmentProtocol((Protocol) protocol);
    }

    @Override
    protected ProtocolAttachmentPersonnel createNewProtocolAttachmentPersonnelInstanceHook(ProtocolBase protocol) {
        return new ProtocolAttachmentPersonnel((Protocol) protocol);
    }

    @Override
    protected ProtocolAttachmentFilter createNewProtocolAttachmentFilterInstanceHook() {
        return new ProtocolAttachmentFilter();
    }

    @Override
    protected String getAttachmentDefaultSortKeyHook() {
        return Constants.PARAMETER_PROTOCOL_ATTACHMENT_DEFAULT_SORT;
    }

    @Override
    public Class<ProtocolAttachmentProtocol> getProtocolAttachmentProtocolClassHook() {
        return ProtocolAttachmentProtocol.class;
    }

    @Override
    public Class<ProtocolAttachmentPersonnel> getProtocolAttachmentPersonnelClassHook() {
        return ProtocolAttachmentPersonnel.class;
    }

    @Override
    public Class<ProtocolNotepad> getProtocolNotepadClassHook() {
        return ProtocolNotepad.class;
    }

    @Override
    public Class<ProtocolDocument> getProtocolDocumentClassHook() {
        return ProtocolDocument.class;
    }

    @Override
    protected ProtocolNotepad createNewProtocolNotepadInstanceHook(ProtocolBase protocol) {
        return new ProtocolNotepad((Protocol) protocol);
    }


}
