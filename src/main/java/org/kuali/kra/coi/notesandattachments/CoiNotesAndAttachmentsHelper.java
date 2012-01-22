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
package org.kuali.kra.coi.notesandattachments;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts.upload.FormFile;
import org.kuali.kra.bo.AttachmentFile;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiDisclosureDocument;
import org.kuali.kra.coi.CoiDisclosureForm;
import org.kuali.kra.coi.auth.CoiDisclosureTask;
import org.kuali.kra.coi.notesandattachments.attachments.CoiDisclosureAttachment;
import org.kuali.kra.coi.notesandattachments.attachments.CoiDisclosureAttachmentFilter;
import org.kuali.kra.coi.notesandattachments.notes.CoiDisclosureNotepad;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.noteattachment.DeleteProtocolNotepadEvent;
import org.kuali.kra.irb.noteattachment.DeleteProtocolNotepadRule;
import org.kuali.kra.irb.noteattachment.DeleteProtocolNotepadRuleImpl;
import org.kuali.kra.irb.noteattachment.ProtocolNotepad;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.kra.util.CollectionUtil;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.util.GlobalVariables;

import edu.mit.irb.irbnamespace.ProtocolDocument;


public class CoiNotesAndAttachmentsHelper {

    private CoiDisclosureAttachment newCoiDisclosureAttachment;
    private CoiDisclosureForm coiDisclosureForm;
    private final BusinessObjectService businessObjectService;
    private CoiDisclosureAttachmentFilter newAttachmentFilter;
    private final ParameterService parameterService;
    private List<AttachmentFile> filesToDelete;
    private CoiDisclosureNotepad newCoiDisclosureNotepad;
    private final DateTimeService dateTimeService;

    private static final String CONFIRM_YES_DELETE_ATTACHMENT = "confirmDeleteCoiDisclosureAttachment";
    // Currently setting this to 1 since there are no CoiDisclosure attachment types
    private static final String ATTACHMENT_TYPE_CD = "1";
    private boolean viewRestricted;
    private boolean modifyAttachments;
    private boolean modifyNotepads;


    public CoiNotesAndAttachmentsHelper(CoiDisclosureForm coiDisclosureForm) {
        this.coiDisclosureForm = coiDisclosureForm;
        this.businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        this.parameterService = KraServiceLocator.getService(ParameterService.class);
        this.dateTimeService = KraServiceLocator.getService(DateTimeService.class);       
        filesToDelete = new ArrayList<AttachmentFile>();
    }

    public CoiDisclosureAttachment getNewCoiDisclosureAttachment() {
        if (this.newCoiDisclosureAttachment == null) {
            this.initCoiDisclosureAttachment();
        }

        return this.newCoiDisclosureAttachment;
    }

    public void prepareView() {
        this.initializePermissions();      
    }

    /**
     * Initialize the permissions for viewing/editing the Custom Data web page.
     */
    private void initializePermissions() {
        modifyAttachments = canMaintainCoiDisclosureAttachments();
        modifyNotepads = canMaintainCoiDisclosureNotes();
        viewRestricted = canViewRestrictedProtocolNotepads();
    }

    public boolean isModifyAttachments() {
        return this.modifyAttachments;
    }

    public boolean isModifyNotepads() {
        return modifyNotepads;
    }

    public void setNewCoiDisclosureAttachment(CoiDisclosureAttachment coiDisclosureAttachment) {
        this.newCoiDisclosureAttachment = coiDisclosureAttachment;
    }

    private void initCoiDisclosureAttachment() {
        this.setNewCoiDisclosureAttachment(new CoiDisclosureAttachment(this.getCoiDisclosure()));
    }

    public CoiDisclosureForm getCoiDisclosureForm() {
        return coiDisclosureForm;
    }

    public void setCoiDisclosureForm(CoiDisclosureForm coiDisclosureForm) {
        this.coiDisclosureForm = coiDisclosureForm;
    }

    public boolean isViewRestricted() {
        return viewRestricted;
    }

    public void setViewRestricted(boolean viewRestricted) {
        this.viewRestricted = viewRestricted;
    }
    private boolean canViewRestrictedProtocolNotepads() {
        CoiDisclosureTask task = new CoiDisclosureTask(TaskName.VIEW_COI_DISCLOSURE_RESTRICTED_NOTES, getCoiDisclosure());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }

    protected boolean canMaintainCoiDisclosureNotes() {
        CoiDisclosureTask task = new CoiDisclosureTask(TaskName.MAINTAIN_COI_DISCLOSURE_NOTES, getCoiDisclosure());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task); 
    }

    protected boolean canMaintainCoiDisclosureAttachments() {
        CoiDisclosureTask task = new CoiDisclosureTask(TaskName.MAINTAIN_COI_DISCLOSURE_ATTACHMENTS, getCoiDisclosure());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task); 
    }

    protected TaskAuthorizationService getTaskAuthorizationService() {
        return KraServiceLocator.getService(TaskAuthorizationService.class);
    }

    protected String getUserIdentifier() {
        return GlobalVariables.getUserSession().getPrincipalId();
    }


    /**
     * Adds the "new" coiDisclosureAttachment to the CoiDisclosure Document.  Before
     * adding this method executes validation.  If the validation fails the attachment is not added.
     */
    public void addNewCoiDisclosureAttachment() {
        this.refreshAttachmentReferences(Collections.singletonList(this.getNewCoiDisclosureAttachment()));
        this.syncNewFiles(Collections.singletonList(this.getNewCoiDisclosureAttachment()));


        final AddCoiDisclosureAttachmentRule rule = new AddCoiDisclosureAttachmentRuleImpl();
        final AddCoiDisclosureAttachmentEvent event = new AddCoiDisclosureAttachmentEvent(coiDisclosureForm.getDocument(), newCoiDisclosureAttachment);


        assignDocumentId(Collections.singletonList(this.getNewCoiDisclosureAttachment()), 
                this.createTypeToMaxDocNumber(getCoiDisclosure().getCoiDisclosureAttachments()));
        if (rule.processAddCoiDisclosureAttachmentRules(event)) {
            this.newCoiDisclosureAttachment.setCoiDisclosureId(getCoiDisclosure().getCoiDisclosureId()); 
            newCoiDisclosureAttachment.setSequenceNumber(getCoiDisclosure().getSequenceNumber());
            this.getCoiDisclosure().addAttachment(newCoiDisclosureAttachment);
            getBusinessObjectService().save(newCoiDisclosureAttachment);

            this.initCoiDisclosureAttachment();
        }

    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public ParameterService getParameterService() {
        return parameterService;
    }

    public DateTimeService getDateTimeService() {
        return dateTimeService;
    }

    /**
     * initializes a new attachment personnel setting the protocol id.
     */
    /*  protected void initNewAttachment() {
        this.setNewCoiDisclosureAttachment(new CoiDisclosureAttachment(this.getCoiDisclosure()));
    }*/

    public <T extends CoiDisclosureAttachment> String retrieveConfirmMethodByType() {
        final String confirmMethod;

        confirmMethod = CONFIRM_YES_DELETE_ATTACHMENT;

        return confirmMethod;
    }

    public <T extends CoiDisclosureAttachment> T retrieveExistingAttachmentByType(final int attachmentNumber) {
        final T attachment;
        attachment = (T) retrieveExistingAttachment(attachmentNumber, getCoiDisclosure().getCoiDisclosureAttachments());
        return attachment;
    }

    private static <T extends CoiDisclosureAttachment> T retrieveExistingAttachment(final int index, final List<T> attachments) {
        return CollectionUtil.getFromList(index, attachments);
    }

    private <T extends CoiDisclosureAttachment> Map<String, Integer> createTypeToMaxDocNumber(final Collection<T> attachments) {
        assert attachments != null : "the attachments was null";

        final Map<String, Integer> typeToDocNumber = new HashMap<String, Integer>();

        for (final T attachment : attachments) {
            final Integer curMax = typeToDocNumber.get(ATTACHMENT_TYPE_CD);
            if (curMax == null || curMax.intValue() < attachment.getDocumentId().intValue()) {
                typeToDocNumber.put(ATTACHMENT_TYPE_CD, attachment.getDocumentId());
            }
        }

        return typeToDocNumber;
    }

    /**
     * Creates the next doc number from a passed in doc number.  If null 1 is returned.
     * @param docNumber the doc number to base the new number off of.
     * @return the new doc number.
     */
    private static Integer createNextDocNumber(final Integer docNumber) {
        return docNumber == null ? NumberUtils.INTEGER_ONE : Integer.valueOf(docNumber.intValue() + 1);
    }

    /**
     * This method...
     * @param attachments
     */
    private void assignDocumentId(List<CoiDisclosureAttachment> attachments, final Map<String, Integer> typeToDocNumber) {
        for (CoiDisclosureAttachment attachment : attachments) {
            final Integer nextDocNumber = createNextDocNumber(typeToDocNumber.get(ATTACHMENT_TYPE_CD));
            attachment.setDocumentId(nextDocNumber);
        }
    }

    /**
     * initializes a new attachment filter
     */
    private void initAttachmentFilter() {
        CoiDisclosureAttachmentFilter disclosureFilter = new CoiDisclosureAttachmentFilter();

        //Lets see if there is a default set for the attachment sort
        try {
            String defaultSortBy = parameterService.getParameterValueAsString(ProtocolDocument.class, Constants.PARAMETER_COI_ATTACHMENT_DEFAULT_SORT);
            if (StringUtils.isNotBlank(defaultSortBy)) {
                disclosureFilter.setSortBy(defaultSortBy);
            }
        } catch (Exception e) {
            //No default found, do nothing.
        }

        this.setNewAttachmentFilter(disclosureFilter);
    }

    public CoiDisclosure getCoiDisclosure() {

        if (this.coiDisclosureForm.getDocument() == null) {
            throw new IllegalArgumentException("the document is null");
        }

        if (this.coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure() == null) {
            throw new IllegalArgumentException("the coiDisclosure is null");
        }
        return this.coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure();
    }

    public CoiDisclosureAttachmentFilter getNewAttachmentFilter() {
        if (newAttachmentFilter == null) {
            this.initAttachmentFilter();
        }
        return newAttachmentFilter;
    }

    public void setNewAttachmentFilter(CoiDisclosureAttachmentFilter newAttachmentFilter) {
        this.newAttachmentFilter = newAttachmentFilter;
    }

    public void addNewCoiDisclosureAttachmentFilter() {
        this.getCoiDisclosure().setCoiDisclosureAttachmentFilter(getNewAttachmentFilter());
    }

    public void processSave() {
        refreshAttachmentReferences(this.getCoiDisclosure().getCoiDisclosureAttachments());

        syncNewFiles(getCoiDisclosure().getCoiDisclosureAttachments());

        /* if (this.versioningUtil.versioningRequired()) {
            this.versioningUtil.versionExstingAttachments();
        }*/

    }


    protected void syncNewFiles(List<CoiDisclosureAttachment> coiDisclosureAttachments) {
        assert coiDisclosureAttachments != null : "the attachments was null";

        for (final CoiDisclosureAttachment attachment : coiDisclosureAttachments) {
            if (CoiNotesAndAttachmentsHelper.doesNewFileExist(attachment)) {
                final AttachmentFile newFile = AttachmentFile.createFromFormFile(attachment.getNewFile());
                //setting the sequence number to the old file sequence number
                if (attachment.getFile() != null) {
                    newFile.setSequenceNumber(attachment.getFile().getSequenceNumber());
                }
                attachment.setFile(newFile);
                // set to null, so the subsequent post will not creating new file again
                attachment.setNewFile(null);
            }
        }

    }

    private static boolean doesNewFileExist(CoiDisclosureAttachment attachment) {
        assert attachment != null : "the attachment was null";
        return attachment.getNewFile() != null && StringUtils.isNotBlank(attachment.getNewFile().getFileName());
    }

    private void refreshAttachmentReferences(List<CoiDisclosureAttachment> coiDisclosureAttachments) {
        assert coiDisclosureAttachments != null : "the attachments was null";

        for (final CoiDisclosureAttachment attachment : coiDisclosureAttachments) {   
            if (attachment instanceof CoiDisclosureAttachment) {
                attachment.refreshReferenceObject("status");   
            }

        }

    }

    public List<AttachmentFile> getFilesToDelete() {
        return filesToDelete;
    }

    public void setFilesToDelete(List<AttachmentFile> filesToDelete) {
        this.filesToDelete = filesToDelete;
    }


    public void fixReloadedAttachments(Map parameterMap) {
        Iterator keys = parameterMap.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next().toString();
            String fieldNameStarter = "coiDisclosureRefreshButtonClicked";
            try {
                if (key.indexOf(fieldNameStarter) > -1) {
                    //we have a refresh button checker field
                    String fieldValue = ((String[]) parameterMap.get(key))[0];
                    if ("T".equals(fieldValue)) {
                        //a refresh button has been clicked, now we just need to update the appropriate attachment status code
                        int numericVal = Integer.valueOf(key.substring(fieldNameStarter.length()));
                        CoiDisclosureAttachment attachment = retrieveExistingAttachmentByType(numericVal);
                        //getCoiDisclosure().getCoiDisclosureAttachments().add(numericVal, attachment);
                        FormFile file = attachment.getNewFile();
                        if(file == null) return;
                        byte[] fileData;
                        try {
                            fileData = file.getFileData();
                            if (fileData.length > 0) {
                                AttachmentFile newFile = new AttachmentFile();
                                newFile.setType(file.getContentType());
                                newFile.setName(file.getFileName());
                                newFile.setData(file.getFileData());
                                attachment.setFile(newFile);
                                getBusinessObjectService().save(attachment);
                            }
                        } catch (Exception e) {

                        }

                    }
                }
            } catch(Exception e) {

            }
        }

    }

    public boolean deleteExistingAttachmentByType(final int attachmentNumber) {
        final boolean deleted;
        deleted = deleteExistingAttachment(attachmentNumber, getCoiDisclosure().getCoiDisclosureAttachments());

        return deleted;
    }

    protected <T extends CoiDisclosureAttachment> boolean deleteExistingAttachment(final int index, final List<T> attachments) {

        if (!CollectionUtil.validIndexForList(index, attachments)) {
            return false;
        }

        CoiDisclosureAttachment attachment = attachments.get(index);
        getBusinessObjectService().delete(attachment);
        attachments.remove(attachment);
        return true;
    }

    public void setNewCoiDisclosureNotepad(CoiDisclosureNotepad newCoiDisclosureNotepad) {
        this.newCoiDisclosureNotepad = newCoiDisclosureNotepad;
    }

    public CoiDisclosureNotepad getNewCoiDisclosureNotepad() {
        if (newCoiDisclosureNotepad == null) {
            initCoiDisclosureNotepad();
        }
        return newCoiDisclosureNotepad;

    }

    private void initCoiDisclosureNotepad() {
        final CoiDisclosureNotepad notepad = new CoiDisclosureNotepad(getCoiDisclosure());
        notepad.setEntryNumber(getNextEntryNumber());
        this.setNewCoiDisclosureNotepad(notepad);
    }

    public void addNewNote() {
        final AddCoiDisclosureNotepadRule rule = new AddCoiDisclosureNotepadRuleImpl();
        final AddCoiDisclosureNotepadEvent event = new AddCoiDisclosureNotepadEvent((CoiDisclosureDocument) coiDisclosureForm.getDocument(), this.newCoiDisclosureNotepad);

        if (!rule.processAddCoiDisclosureNotepadRules(event)) {
            return;
        }
        this.addNewNotepad(newCoiDisclosureNotepad);
        initCoiDisclosureNotepad();

    }

    private void addNewNotepad(CoiDisclosureNotepad notepad) {
        setUpdateFields(notepad);
        // set notepad to be editable
        notepad.setEditable(true);
        notepad.setCoiDisclosureId(getCoiDisclosure().getCoiDisclosureId());
        notepad.setCoiDisclosureNumber(getCoiDisclosure().getCoiDisclosureNumber());
        notepad.setSequenceNumber(getCoiDisclosure().getSequenceNumber());        
        notepad.setEntryNumber(getNextEntryNumber());
        this.getCoiDisclosure().getCoiDisclosureNotepads().add(notepad);   

    }

    protected DocumentService getDocumentService() {
        return KraServiceLocator.getService(DocumentService.class);    
    }

    protected void setUpdateFields(KraPersistableBusinessObjectBase bo) {
        bo.setUpdateUser(GlobalVariables.getUserSession().getPrincipalName());
        bo.setUpdateTimestamp(dateTimeService.getCurrentTimestamp());
    }


    /** gets the next entry number based on previously generated numbers. */
    private Integer getNextEntryNumber() {       
        return getCoiDisclosure().getCoiDisclosureNotepads().size() + 1;
    }

    public void setManageNotesOpen() {

    }

    public boolean deleteNote(int noteToDelete) {
        // rules check?
        this.deleteNotepad(noteToDelete);
        return true;
    }

    private void deleteNotepad(int noteToDelete) {
        this.getCoiDisclosure().getCoiDisclosureNotepads().remove(noteToDelete);
    }

}
