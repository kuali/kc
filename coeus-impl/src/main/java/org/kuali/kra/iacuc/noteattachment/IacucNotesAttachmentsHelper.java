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
package org.kuali.kra.iacuc.noteattachment;

import org.kuali.coeus.common.framework.auth.SystemAuthorizationService;
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
                KcServiceLocator.getService(SystemAuthorizationService.class),
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
      return this.systemAuthorizationService.hasRole(GlobalVariables.getUserSession().getPrincipalId(), NAMESPACE, RoleConstants.IACUC_ADMINISTRATOR);
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
