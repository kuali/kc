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
package org.kuali.kra.institutionalproposal.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalNotepad;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalNotepadBean;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalNoteAttachmentService;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;
import org.kuali.rice.core.api.util.RiceConstants;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kns.datadictionary.BusinessObjectEntry;
import org.kuali.rice.krad.bo.Attachment;
import org.kuali.rice.krad.bo.Note;
import org.kuali.rice.krad.service.AttachmentService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.service.NoteService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.NoteType;


public class InstitutionalProposalNoteAttachmentServiceImpl implements InstitutionalProposalNoteAttachmentService {

    private AttachmentService attachmentService;
    private NoteService noteService;

    /**
     * This method is used to add a new Note/Attachment.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    @Override
    public ActionForward addNote(ActionMapping mapping, InstitutionalProposalForm institutionalProposalForm) throws Exception {
        InstitutionalProposalNotepad notepad = institutionalProposalForm.getInstitutionalProposalNotepadBean().getNewInstitutionalProposalNotepad();
        BusinessObjectEntry businessObjectEntry = (BusinessObjectEntry) KRADServiceLocatorWeb.getDataDictionaryService().getDataDictionary().getBusinessObjectEntry(InstitutionalProposalNotepad.class.getName());
        if (institutionalProposalForm.getAttachmentFile() != null) {
            if (!businessObjectEntry.isBoNotesEnabled()) {
                throw new RuntimeException("to add attachments, the DD file for InstitutionalProposalNotepad must be configured for boNotesEnabled=true");
            }
            else {
                FormFile attachmentFile = institutionalProposalForm.getAttachmentFile();
                if (attachmentFile == null) {
                    GlobalVariables.getMessageMap().putError(
                            String.format("%s.%s",
                                    KRADConstants.NEW_DOCUMENT_NOTE_PROPERTY_NAME,
                                    KRADConstants.NOTE_ATTACHMENT_FILE_PROPERTY_NAME),
                                    RiceKeyConstants.ERROR_UPLOADFILE_NULL);
                }

                Note newNote = new Note();
                newNote.setNoteText("Default text, will never be shown to user.");
                newNote.setNoteTypeCode(NoteType.BUSINESS_OBJECT.getCode());
                newNote.setNotePostedTimestampToCurrent();
                Attachment attachment = null;
                if (attachmentFile != null && !StringUtils.isBlank(attachmentFile.getFileName())) {
                    if (attachmentFile.getFileSize() == 0) {
                        GlobalVariables.getMessageMap().putError(
                                String.format("%s.%s",
                                        KRADConstants.NEW_DOCUMENT_NOTE_PROPERTY_NAME,
                                        KRADConstants.NOTE_ATTACHMENT_FILE_PROPERTY_NAME),
                                        RiceKeyConstants.ERROR_UPLOADFILE_EMPTY,
                                        attachmentFile.getFileName());
                    }
                    else {
                        String attachmentType = null;
                        attachment = getAttachmentService().createAttachment(notepad, attachmentFile.getFileName(), attachmentFile.getContentType(), attachmentFile.getFileSize(), attachmentFile.getInputStream(), attachmentType);
                    }
                    // create a new note from the data passed in
                    Note tmpNote = getNoteService().createNote(newNote, notepad, GlobalVariables.getUserSession().getPrincipalId());
                    tmpNote.addAttachment(attachment);
                    notepad.getAttachments().add(tmpNote);
                }
            }
        }
        institutionalProposalForm.getInstitutionalProposalNotepadBean().addNote(institutionalProposalForm.getInstitutionalProposalNotepadBean());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method is used to delete an existing note/attachment
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    @Override
    public ActionForward deleteNote(ActionMapping mapping, InstitutionalProposalForm institutionalProposalForm, int selection) throws Exception {
        InstitutionalProposalDocument document = institutionalProposalForm.getInstitutionalProposalDocument();
        InstitutionalProposalNotepad ipNotepad = document.getInstitutionalProposal().getInstitutionalProposalNotepads().get(selection);
        Note attachmentNote;
        if (ipNotepad.getAttachments().size() > 0) {
            attachmentNote = ipNotepad.getAttachments().get(0);
            Attachment attachment = attachmentNote.getAttachment();

            if (attachment != null) { // only do this if the note has been persisted
                //All references for the business object Attachment are auto-update="none",
                //so refreshNonUpdateableReferences() should work the same as refresh()
                if (attachmentNote.getNoteIdentifier() != null) { 
                    attachment.refreshNonUpdateableReferences();
                }
                getAttachmentService().deleteAttachmentContents(attachment);
            }
            // delete the note if the document is already saved
            if (!document.getDocumentHeader().getWorkflowDocument().isInitiated()) {
                getNoteService().deleteNote(attachmentNote);
            }
        }
        document.getInstitutionalProposal().getInstitutionalProposalNotepads().remove(selection);

        return mapping.findForward(RiceConstants.MAPPING_BASIC);
    }

    public AttachmentService getAttachmentService() {
        return this.attachmentService;
    }

    public void setAttachmentService(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    public NoteService getNoteService() {
        return this.noteService;
    }

    public void setNoteService(NoteService noteService) {
        this.noteService = noteService;
    }

}