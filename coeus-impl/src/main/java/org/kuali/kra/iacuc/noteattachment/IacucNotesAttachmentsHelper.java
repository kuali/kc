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
package org.kuali.kra.iacuc.noteattachment;

import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.auth.IacucProtocolTask;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.ProtocolFormBase;
import org.kuali.kra.protocol.auth.ProtocolTaskBase;
import org.kuali.kra.protocol.noteattachment.*;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.util.GlobalVariables;



/**
 * This is the "Helper" class for IACUC ProtocolBase Notes And Attachments.
 */
public class IacucNotesAttachmentsHelper extends NotesAttachmentsHelperBase {

    public IacucNotesAttachmentsHelper(ProtocolFormBase form) {
        super(form, (ProtocolAttachmentService) KcServiceLocator.getService("iacucProtocolAttachmentService"),
                KcServiceLocator.getService(TaskAuthorizationService.class),
                KcServiceLocator.getService(KcAuthorizationService.class),
                KcServiceLocator.getService(DateTimeService.class),
                (ProtocolNotepadService) KcServiceLocator.getService("iacucProtocolNotepadService"),
                KcServiceLocator.getService(ParameterService.class),
                new IacucProtocolAttachmentVersioningUtility(form));
    }

    @Override
    public boolean canEditProtocolAttachments() {
        final ProtocolTaskBase task = new IacucProtocolTask(TaskName.MODIFY_IACUC_PROTOCOL_ATTACHMENTS, (IacucProtocol) this.getProtocol());
        return this.authService.isAuthorized(this.getUserIdentifier(), task);
    }

    @Override
    protected ProtocolAttachmentProtocolBase createNewProtocolAttachmentProtocolInstanceHook(ProtocolBase protocol) {
        return new IacucProtocolAttachmentProtocol(protocol);
    }

    @Override
    protected ProtocolAttachmentPersonnelBase createNewProtocolAttachmentPersonnelInstanceHook(ProtocolBase protocol) {
        return new IacucProtocolAttachmentPersonnel(protocol);
    }

    @Override
    protected ProtocolAttachmentFilterBase createNewProtocolAttachmentFilterInstanceHook() {
        return new IacucProtocolAttachmentFilter();
    }

    @Override
    protected String getAttachmentDefaultSortKeyHook() {
        return Constants.PARAMETER_IACUC_PROTOCOL_ATTACHMENT_DEFAULT_SORT;
    }

    @Override
    public boolean canAddProtocolNotepads() {
        final ProtocolTaskBase task = new IacucProtocolTask(TaskName.ADD_IACUC_PROTOCOL_NOTES, (IacucProtocol) this.getProtocol());
        return this.authService.isAuthorized(this.getUserIdentifier(), task);
    }

    @Override
    public boolean canViewRestrictedProtocolNotepads() {
        final ProtocolTaskBase task = new IacucProtocolTask(TaskName.IACUC_VIEW_RESTRICTED_NOTES, (IacucProtocol) this.getProtocol());
        return this.authService.isAuthorized(this.getUserIdentifier(), task);
    }

    @Override
    public boolean isProtocolAdmin() {
      return this.kraAuthorizationService.hasRole(GlobalVariables.getUserSession().getPrincipalId(), NAMESPACE, RoleConstants.IACUC_ADMINISTRATOR);
    }

    @Override
    public Class<? extends ProtocolAttachmentProtocolBase> getProtocolAttachmentProtocolClassHook() {
        return IacucProtocolAttachmentProtocol.class;
    }

    @Override
    public Class<? extends ProtocolAttachmentPersonnelBase> getProtocolAttachmentPersonnelClassHook() {
        return IacucProtocolAttachmentPersonnel.class;
    }

    @Override
    public Class<? extends ProtocolNotepadBase> getProtocolNotepadClassHook() {
        return IacucProtocolNotepad.class;
    }

    @Override
    public Class<? extends ProtocolDocumentBase> getProtocolDocumentClassHook() {
        return IacucProtocolDocument.class;
    }

    @Override
    protected ProtocolNotepadBase createNewProtocolNotepadInstanceHook(ProtocolBase protocol) {
        return new IacucProtocolNotepad(protocol);
    }

    @Override
    public AddProtocolAttachmentProtocolRule getAddProtocolAttachmentProtocolRuleInstanceHook() {
        return new AddIacucProtocolAttachmentProtocolRuleImpl();
    }

}
