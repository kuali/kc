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
package org.kuali.kra.coi.notesandattachments;


import edu.mit.irb.irbnamespace.ProtocolDocument;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.upload.FormFile;
import org.kuali.coeus.common.framework.attachment.AttachmentFile;
import org.kuali.coeus.common.framework.attachment.KcAttachmentService;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.util.CollectionUtils;
import org.kuali.kra.coi.*;
import org.kuali.kra.coi.auth.CoiDisclosureDeleteUpdateAttachmentTask;
import org.kuali.kra.coi.auth.CoiDisclosureDeleteUpdateNoteTask;
import org.kuali.kra.coi.auth.CoiDisclosureTask;
import org.kuali.kra.coi.lookup.keyvalue.CoiDisclosureProjectValuesFinder;
import org.kuali.kra.coi.notesandattachments.attachments.CoiDisclosureAttachment;
import org.kuali.kra.coi.notesandattachments.attachments.CoiDisclosureAttachmentFilter;
import org.kuali.kra.coi.notesandattachments.notes.CoiDisclosureNotepad;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.kim.api.identity.principal.Principal;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.*;


public class CoiNotesAndAttachmentsHelper {

    private static final Log LOG = LogFactory.getLog(CoiNotesAndAttachmentsHelper.class);

    private CoiDisclosureAttachment newCoiDisclosureAttachment;
    private CoiDisclosureForm coiDisclosureForm;
    private final BusinessObjectService businessObjectService;
    private CoiDisclosureAttachmentFilter newAttachmentFilter;
    private final ParameterService parameterService;
    private List<AttachmentFile> filesToDelete;
    private CoiDisclosureNotepad newCoiDisclosureNotepad;
    private final DateTimeService dateTimeService;
    private final IdentityService identityService;

    private static final String CONFIRM_YES_DELETE_ATTACHMENT = "confirmDeleteCoiDisclosureAttachment";
    private static final String PERSON_NOT_FOUND_FORMAT_STRING = "%s (not found)";
    // Currently setting this to 1 since there are no CoiDisclosure attachment types
    private static final String ATTACHMENT_TYPE_CD = "1";
    private boolean viewRestricted;
    private boolean addAttachments;
    private boolean addNotepads;
    private boolean modifyAttachments;
    private boolean modifyNotepads;
    private boolean addCoiReviewerComments;
    private Map<Integer, Boolean> canDeleteUpdateAttachment = new HashMap<Integer, Boolean>();
    private Map<Integer, Boolean> canDeleteUpdateNote = new HashMap<Integer, Boolean>();
    
    public CoiNotesAndAttachmentsHelper(CoiDisclosureForm coiDisclosureForm) {
        this.coiDisclosureForm = coiDisclosureForm;
        this.businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        this.parameterService = KcServiceLocator.getService(ParameterService.class);
        this.dateTimeService = KcServiceLocator.getService(DateTimeService.class);
        this.identityService = KcServiceLocator.getService(IdentityService.class);
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
        initializeNotePaseUserNames(this.getCoiDisclosure().getCoiDisclosureNotepads());
    }

    /**
     * Initialize the permissions for viewing/editing the Custom Data web page.
     */
    private void initializePermissions() {
        addAttachments = canAddCoiDisclosureAttachments();
        addNotepads = canAddCoiDisclosureNotes();
        modifyAttachments = canMaintainCoiDisclosureAttachments();
        modifyNotepads = canMaintainCoiDisclosureNotes();
        viewRestricted = canViewRestrictedProtocolNotepads();
        addCoiReviewerComments = canAddCoiReviewerComments();
        
        // initialize individual permissions for notes and attachments
        canDeleteUpdateNotes();
        canDeleteUpdateAttachments();
    }
    
    protected void initializeNotePaseUserNames(List<CoiDisclosureNotepad> notepads) {
        for (CoiDisclosureNotepad notePad : notepads) {
            Person person = this.getPersonService().getPersonByPrincipalName(notePad.getUpdateUser());
            notePad.setUpdateUserFullName(person==null?String.format(PERSON_NOT_FOUND_FORMAT_STRING, notePad.getUpdateUser()):person.getName());
            
            if (StringUtils.isNotBlank(notePad.getCreateUser())) {
                Person creator = this.getPersonService().getPersonByPrincipalName(notePad.getCreateUser());
                notePad.setCreateUserFullName(creator==null?String.format(PERSON_NOT_FOUND_FORMAT_STRING, notePad.getCreateUser()):creator.getName());
            } else {
                notePad.setCreateUserFullName("");
            }
        }
    }

    public boolean isCanAddAttachment() {
        return this.addAttachments;
    }

    public boolean isAddNotepads() {
        return addNotepads;
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
    
    private  void canDeleteUpdateNotes() {
        List<CoiDisclosureNotepad> notes = getCoiDisclosure().getCoiDisclosureNotepads();
        for (int i=0; i < notes.size(); i++) {
            CoiDisclosureTask task = new CoiDisclosureDeleteUpdateNoteTask(TaskName.DELETE_UPDATE_NOTE, getCoiDisclosure(), notes.get(i));
            
            canDeleteUpdateNote.put(i, getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task));
        }
    }
    
    public Map<Integer, Boolean> getCanDeleteUpdateNotes() {
        return canDeleteUpdateNote;
    }
    
    private  void canDeleteUpdateAttachments() {
        List<CoiDisclosureAttachment> attachments = getCoiDisclosure().getCoiDisclosureAttachments();
        for (int i=0; i < attachments.size(); i++) {
            CoiDisclosureTask task = new CoiDisclosureDeleteUpdateAttachmentTask(TaskName.DELETE_UPDATE_ATTACHMENT, getCoiDisclosure(), attachments.get(i));
            canDeleteUpdateAttachment.put(i, getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task));
        }
    }
    
    public Map<Integer, Boolean> getCanDeleteUpdateNote() {
        return canDeleteUpdateNote;
    }
    
    public Map<Integer, Boolean> getCanDeleteUpdateAttachment() {
        return canDeleteUpdateAttachment;
    }
    
    private void initCoiDisclosureAttachment() {
        
        this.setNewCoiDisclosureAttachment(new CoiDisclosureAttachment(this.getCoiDisclosure()));
        CoiDisclosure coiDisclosure = coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure();
        String event = coiDisclosure.getEventTypeCode();
        if (StringUtils.equalsIgnoreCase(event, CoiDisclosureEventType.AWARD) ||
                StringUtils.equalsIgnoreCase(event, CoiDisclosureEventType.DEVELOPMENT_PROPOSAL) || 
                StringUtils.equalsIgnoreCase(event, CoiDisclosureEventType.INSTITUTIONAL_PROPOSAL) ||
                StringUtils.equalsIgnoreCase(event, CoiDisclosureEventType.IRB_PROTOCOL)) {
            String projectId = coiDisclosure.getCoiDisclProjects().get(0).getProjectId();
            newCoiDisclosureAttachment.setProjectId(projectId);
        }
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
        //The reporter should never be allowed to view restricted notes
        if (StringUtils.equals(getUserIdentifier(), getCoiDisclosure().getPersonId())) {
            return false;
        } else {
            CoiDisclosureTask task = new CoiDisclosureTask(TaskName.VIEW_COI_DISCLOSURE_RESTRICTED_NOTES, getCoiDisclosure());
            return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
        }
    }

    protected boolean canAddCoiDisclosureNotes() {
        CoiDisclosureTask task = new CoiDisclosureTask(TaskName.ADD_COI_DISCLOSURE_NOTES, getCoiDisclosure());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task); 
    }
       
    protected boolean canViewCoiDisclosure(){
        CoiDisclosureTask task = new CoiDisclosureTask(TaskName.VIEW_COI_DISCLOSURE, getCoiDisclosure());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task); 
    }
    
    public boolean isPerformCoiDisclosureActions(){
        CoiDisclosureTask task = new CoiDisclosureTask(TaskName.PERFORM_COI_DISCLOSURE_ACTIONS, getCoiDisclosure());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task); 
    }
    
    /**
     * If Assigned Reviewers create a comment in the Review Actions ==> Add Review Comment, pre-set the Note Type drop down to Reviewer Comment
     * @return
     */
    protected boolean canAddCoiReviewerComments() {   
        boolean userIsCoiReviewer = false; 
        if (canViewCoiDisclosure() && canMaintainCoiDisclosureNotes() && canMaintainCoiDisclosureAttachments() && isPerformCoiDisclosureActions()){            
            userIsCoiReviewer = true;
        }
        return userIsCoiReviewer;
    }

    protected boolean canAddCoiDisclosureAttachments() {
        CoiDisclosureTask task = new CoiDisclosureTask(TaskName.ADD_COI_DISCLOSURE_ATTACHMENTS, getCoiDisclosure());
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
        return KcServiceLocator.getService(TaskAuthorizationService.class);
    }

    public String getUserIdentifier() {
        return GlobalVariables.getUserSession().getPrincipalId();
    }
    
    public String getCurrentUser() {
        return ((Principal)identityService.getPrincipal(getUserIdentifier())).getPrincipalName();
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
            newCoiDisclosureAttachment.setEventTypeCode(getCoiDisclosure().getEventTypeCode() + "");
            if (getCoiDisclosure().getCoiDisclProjects().size() > 0) {
            	newCoiDisclosureAttachment.setProjectId(getCoiDisclosure().getCoiDisclProjects().get(0).getProjectId());
            }
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
        return CollectionUtils.getFromList(index, attachments);
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
            if (getKcAttachmentService().doesNewFileExist(attachment.getNewFile())) {
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

    private KcAttachmentService getKcAttachmentService() {
        return KcServiceLocator.getService(KcAttachmentService.class);
    }

    private void refreshAttachmentReferences(List<CoiDisclosureAttachment> coiDisclosureAttachments) {
        assert coiDisclosureAttachments != null : "the attachments was null";

        for (final CoiDisclosureAttachment attachment : coiDisclosureAttachments) {   
            if (attachment instanceof CoiDisclosureAttachment) {
                attachment.refreshReferenceObject("status");  
                attachment.refreshReferenceObject("coiAttachmentType");
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
                            LOG.error(e.getMessage(), e);
                        }

                    }
                }
            } catch(Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }

    }

    public void setOriginalDisclosureIdsIfNecessary(CoiDisclosure disclosure) {
        for (CoiDisclosureAttachment attachment : disclosure.getCoiDisclosureAttachments()) {
            if (attachment.getOriginalCoiDisclosureId() == null) {
                attachment.setOriginalCoiDisclosureId(disclosure.getCoiDisclosureId());
            }
        }
        for (CoiDisclosureNotepad note : disclosure.getCoiDisclosureNotepads()) {
            if (note.getOriginalCoiDisclosureId() == null) {
                note.setOriginalCoiDisclosureId(disclosure.getCoiDisclosureId());
            }
        }        
    }
        
    public boolean deleteExistingAttachmentByType(final int attachmentNumber) {
        final boolean deleted;
        deleted = deleteExistingAttachment(attachmentNumber, getCoiDisclosure().getCoiDisclosureAttachments());

        return deleted;
    }

    protected <T extends CoiDisclosureAttachment> boolean deleteExistingAttachment(final int index, final List<T> attachments) {

        if (!CollectionUtils.validIndexForList(index, attachments)) {
            return false;
        }

        CoiDisclosureAttachment attachment = attachments.get(index);
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

   
    /**
     * This method is called by the action class to add a new note
     */
    public void addNewNote() {
        final AddCoiDisclosureNotepadRule rule = new AddCoiDisclosureNotepadRuleImpl();
        final AddCoiDisclosureNotepadEvent event = new AddCoiDisclosureNotepadEvent((CoiDisclosureDocument) coiDisclosureForm.getDocument(), this.newCoiDisclosureNotepad);

        if (rule.processAddCoiDisclosureNotepadRules(event)) {
            newCoiDisclosureNotepad.setCreateUser(GlobalVariables.getUserSession().getPrincipalName());
            newCoiDisclosureNotepad.setCreateTimestamp(((DateTimeService) KcServiceLocator.getService(Constants.DATE_TIME_SERVICE_NAME)).getCurrentTimestamp());
            addNewNotepad(newCoiDisclosureNotepad);
            initCoiDisclosureNotepad();
        }
        

    }

    private void initCoiDisclosureNotepad() {
        final CoiDisclosureNotepad notepad = new CoiDisclosureNotepad(getCoiDisclosure());
        notepad.setEntryNumber(getNextEntryNumber());
        CoiDisclosure coiDisclosure = coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure();
        String event = coiDisclosure.getEventTypeCode();
        // If disclosure is an automatic event disclosure, prepopulate the projectId so that
        // this can be displayed in the notes and attachments projectId field. We do not want
        // a drop down in this case since there is only one value.
        if (StringUtils.equalsIgnoreCase(event, CoiDisclosureEventType.AWARD) ||
                StringUtils.equalsIgnoreCase(event, CoiDisclosureEventType.DEVELOPMENT_PROPOSAL) || 
                StringUtils.equalsIgnoreCase(event, CoiDisclosureEventType.INSTITUTIONAL_PROPOSAL) ||
                StringUtils.equalsIgnoreCase(event, CoiDisclosureEventType.IRB_PROTOCOL)) {
            String projectId = coiDisclosure.getCoiDisclProjects().get(0).getProjectId();
            notepad.setProjectId(projectId);
        }
        notepad.setEventTypeCode(event);
        notepad.setEditable(true);
        //If Assigned Reviewers create a comment in the Review Actions ==> Add Review Comment, pre-set the Note Type drop down to Reviewer Comment
        if (canAddCoiDisclosureNotes() && coiDisclosure.isSubmitted() && addCoiReviewerComments){
            notepad.setNoteTypeCode(CoiNoteType.REVIEWER_COMMENT_NOTE_TYPE_CODE);
        }
        setNewCoiDisclosureNotepad(notepad);
    }

    private void addNewNotepad(CoiDisclosureNotepad notepad) {
        setUpdateFields(notepad);
        // set notepad to be editable
        notepad.resetUpdateTimestamp();
        notepad.setEditable(false);
        notepad.setCoiDisclosureId(getCoiDisclosure().getCoiDisclosureId());
        notepad.setCoiDisclosureNumber(getCoiDisclosure().getCoiDisclosureNumber());
        notepad.setSequenceNumber(getCoiDisclosure().getSequenceNumber());        
        notepad.setEntryNumber(getNextEntryNumber());
        if (getCoiDisclosure().getCoiDisclProjects().size() > 0) {
            notepad.setProjectId(getCoiDisclosure().getCoiDisclProjects().get(0).getProjectId());
        }
        if (notepad.getFinancialEntity() == null && notepad.getFinancialEntityId() != null) {
            //due to the readonly view change for disabled fin ents, we need to make sure to have a
            // financial entity otherwise the fin ent will be blank on add.
            notepad.refreshReferenceObject("financialEntity");
        }
        getBusinessObjectService().save(notepad);
        getCoiDisclosure().getCoiDisclosureNotepads().add(notepad);   

    }

    protected void setUpdateFields(KcPersistableBusinessObjectBase bo) {
        bo.setUpdateUser(GlobalVariables.getUserSession().getPrincipalName());
        bo.setUpdateTimestamp(dateTimeService.getCurrentTimestamp());
    }


    /** gets the next entry number based on previously generated numbers. */
    private Integer getNextEntryNumber() {       
        return getCoiDisclosure().getCoiDisclosureNotepads().size() + 1;
    }

    public void setManageNotesOpen() {

    }

    /* 
     * edit an existing note
     */
    public void editNote(int noteToModify) {
        CoiDisclosureNotepad notepadObject = this.getCoiDisclosure().getCoiDisclosureNotepads().get(noteToModify);
        notepadObject.setUpdateUser(GlobalVariables.getUserSession().getPrincipalName());
        notepadObject.setUpdateTimestamp(dateTimeService.getCurrentTimestamp());
        notepadObject.setEditable(true);
    }

    public boolean deleteNote(int noteToDelete) {
        // rules check?
        this.deleteNotepad(noteToDelete);
        return true;
    }
    
    private void deleteNotepad(int noteToDelete) {
        this.getCoiDisclosure().getCoiDisclosureNotepads().remove(noteToDelete);
    }
    
    public PersonService getPersonService() {
        return KcServiceLocator.getService(PersonService.class);
    }
    
    public List<KeyValue> getProjectSelectListItems() {
        CoiDisclosureProjectValuesFinder finder = new CoiDisclosureProjectValuesFinder();
        List<KeyValue> values = finder.getKeyValues();
        return values;
    }

}
