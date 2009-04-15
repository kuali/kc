/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.bo;

import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.upload.FormFile;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.NarrativeRight;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.document.authorization.NarrativeTask;
import org.kuali.kra.proposaldevelopment.service.ProposalPersonService;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.kra.service.PersonService;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.kra.web.struts.form.KraTransactionalDocumentFormBase;

/**
 * 
 * This class is BO for narrarive
 */
public class Narrative extends KraPersistableBusinessObjectBase {
    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(Narrative.class);
    
    private String proposalNumber;
    private Integer moduleNumber;
    private String comments;
    private String contactName;
    private String emailAddress;
    private Integer moduleSequenceNumber;
    private String moduleStatusCode;
    private String moduleTitle;
    private String narrativeTypeCode;
    private String phoneNumber;
    private NarrativeType narrativeType;
    private NarrativeStatus narrativeStatus;
    private String fileName;
    private List<NarrativeUserRights> narrativeUserRights;
    private List<NarrativeAttachment> narrativeAttachmentList;
    transient private FormFile narrativeFile;
    private String institutionalAttachmentTypeCode;
    private Timestamp timestampDisplay;
    private String uploadUserDisplay;

    public Narrative() {
        narrativeAttachmentList = new ArrayList<NarrativeAttachment>(1);
        narrativeUserRights = new ArrayList<NarrativeUserRights>();
        setModuleStatusCode("I");
    }
    
    protected String findLoggedInUserPersonId() {
        String loggedInUser = GlobalVariables.getUserSession().getUniversalUser().getPersonUserIdentifier();
        return getService(PersonService.class).getPersonByName(loggedInUser).getPersonId();//get person id for looged in user
    }

    public Integer getModuleNumber() {
        return moduleNumber;
    }

    public void setModuleNumber(Integer moduleNumber) {
        this.moduleNumber = moduleNumber;
    }

    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Integer getModuleSequenceNumber() {
        return moduleSequenceNumber;
    }

    public void setModuleSequenceNumber(Integer moduleSequenceNumber) {
        this.moduleSequenceNumber = moduleSequenceNumber;
    }

    public String getModuleStatusCode() {
        return moduleStatusCode;
    }

    public void setModuleStatusCode(String moduleStatusCode) {
        this.moduleStatusCode = moduleStatusCode;
    }

    public String getModuleTitle() {
        return moduleTitle;
    }

    public void setModuleTitle(String moduleTitle) {
        this.moduleTitle = moduleTitle;
    }

    public String getNarrativeTypeCode() {
        return narrativeTypeCode;
    }

    public void setNarrativeTypeCode(String narrativeTypeCode) {
        this.narrativeTypeCode = narrativeTypeCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = new LinkedHashMap();
        hashMap.put("moduleNumber", getModuleNumber());
        hashMap.put("proposalNumber", getProposalNumber());
        hashMap.put("comments", getComments());
        hashMap.put("contactName", getContactName());
        hashMap.put("emailAddress", getEmailAddress());
        hashMap.put("moduleSequenceNumber", getModuleSequenceNumber());
        hashMap.put("moduleStatusCode", getModuleStatusCode());
        hashMap.put("moduleTitle", getModuleTitle());
        hashMap.put("narrativeTypeCode", getNarrativeTypeCode());
        hashMap.put("phoneNumber", getPhoneNumber());
        return hashMap;
    }

    public NarrativeType getNarrativeType() {
        return narrativeType;
    }

    public void setNarrativeType(NarrativeType narrativeType) {
        this.narrativeType = narrativeType;
    }

    public List<NarrativeUserRights> getNarrativeUserRights() {
        if (narrativeUserRights != null) {
            Collections.sort(this.narrativeUserRights, new Comparator() {
                public int compare(Object o1, Object o2) {
                    NarrativeUserRights r1 = (NarrativeUserRights) o1;
                    NarrativeUserRights r2 = (NarrativeUserRights) o2;
                    if (r1 == null || r2 == null) return 0;
                    String name1 = r1.getPersonName();
                    String name2 = r2.getPersonName();
                    if (name1 == null || name2 == null) return 0;
                    return name1.compareTo(name2);
                }
            });
        }
        return narrativeUserRights;
    }

    public void setNarrativeUserRights(List<NarrativeUserRights> narrativeUserRights) {
        this.narrativeUserRights = narrativeUserRights;
    }

    /**
     * Gets the narrativeStatus attribute.
     * 
     * @return Returns the narrativeStatus.
     */
    public NarrativeStatus getNarrativeStatus() {
        return narrativeStatus;
    }

    /**
     * Sets the narrativeStatus attribute value.
     * 
     * @param narrativeStatus The narrativeStatus to set.
     */
    public void setNarrativeStatus(NarrativeStatus narrativeStatus) {
        this.narrativeStatus = narrativeStatus;
    }

    public List<NarrativeAttachment> getNarrativeAttachmentList() {
        return narrativeAttachmentList;
    }

    public void setNarrativeAttachmentList(List<NarrativeAttachment> narrativePdfList) {
        this.narrativeAttachmentList = narrativePdfList;
    }

    public FormFile getNarrativeFile() {
        return narrativeFile;
    }

    public void setNarrativeFile(FormFile narrativeFile) {
        this.narrativeFile = narrativeFile;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 
     * This method is to return institutional attachment type.  This column does not exist in table.
     * Basically, it is for narrativetypecode in table. 
     * @return
     */
    public String getInstitutionalAttachmentTypeCode() {
        return narrativeTypeCode;
    }

    /**
     * 
     * This method set the institutional attachment type.  Since instituteattachmenttypecode is set as 'required' in DD.
     * So, it is set to narrativetypecode, and hence to return narrative type code.
     * @param institutionalAttachmentTypeCode
     */
    public void setInstitutionalAttachmentTypeCode(String institutionalAttachmentTypeCode) {
        //this.institutionalAttachmentTypeCode = institutionalAttachmentTypeCode;
        this.narrativeTypeCode = institutionalAttachmentTypeCode;
    }

    /**
     * Can the current user download (view) the attachment?
     * @param username 
     * @return true if the user can view the attachment; otherwise false
     */
    public boolean getDownloadAttachment(String username) {
        if (getNarrativeUserRights().isEmpty()) {
            refreshReferenceObject("narrativeUserRights");
        }
       
        TaskAuthorizationService taskAuthorizationService = KraServiceLocator.getService(TaskAuthorizationService.class);
        return taskAuthorizationService.isAuthorized(username, new NarrativeTask(TaskName.DOWNLOAD_NARRATIVE, getProposalDevelopmentDocument(), this));
    }

    /**
     * Can the current user replace the attachment?
     * @return true if the user can replace the attachment; otherwise false
     */
    public boolean getReplaceAttachment(String username) {
        if (getNarrativeUserRights().isEmpty()) {
            refreshReferenceObject("narrativeUserRights");
        }
      
        TaskAuthorizationService taskAuthorizationService = KraServiceLocator.getService(TaskAuthorizationService.class);
        return taskAuthorizationService.isAuthorized(username, new NarrativeTask(TaskName.REPLACE_NARRATIVE, getProposalDevelopmentDocument(), this));
    }
    
    /**
     * Can the current user delete the attachment?
     * @return true if the user can delete the attachment; otherwise false
     */
    public boolean getDeleteAttachment(String username) {
        if (getNarrativeUserRights().isEmpty()) {
            refreshReferenceObject("narrativeUserRights");
        }
      
        TaskAuthorizationService taskAuthorizationService = KraServiceLocator.getService(TaskAuthorizationService.class);
        return taskAuthorizationService.isAuthorized(username, new NarrativeTask(TaskName.DELETE_NARRATIVE, getProposalDevelopmentDocument(), this));
    }
    
    /**
     * Can the current user modify the user rights for the attachment?
     * @return true if the user can modify the user rights; otherwise false
     */
    public boolean getModifyNarrativeRights(String username) {
        if (getNarrativeUserRights().isEmpty()) {
            refreshReferenceObject("narrativeUserRights");
        }
      
        TaskAuthorizationService taskAuthorizationService = KraServiceLocator.getService(TaskAuthorizationService.class);
        return taskAuthorizationService.isAuthorized(username, new NarrativeTask(TaskName.MODIFY_NARRATIVE_RIGHTS, getProposalDevelopmentDocument(), this));
    }
    
    /**
     * Get the Proposal Development Document for the current session.  The
     * document is within the current form.
     * 
     * @return the current document or null if not found
     */
    private ProposalDevelopmentDocument getProposalDevelopmentDocument() {
        ProposalDevelopmentDocument doc = null;
        ProposalDevelopmentForm form = (ProposalDevelopmentForm) GlobalVariables.getKualiForm();
        if (form != null) {
            doc = form.getDocument();
        }
        return doc;
    }

    /**
     * 
     * This method used to populate the attachment to narrative object by reading FormFile 
     */
    public void populateAttachment() {
        FormFile narrativeFile = getNarrativeFile();
        if(narrativeFile==null) return;
        byte[] narrativeFileData;
        try {
            narrativeFileData = narrativeFile.getFileData();
            if (narrativeFileData.length > 0) {
                NarrativeAttachment narrativeAttachment;
                if (getNarrativeAttachmentList().isEmpty()) {
                    narrativeAttachment = new NarrativeAttachment();
                    getNarrativeAttachmentList().add(narrativeAttachment);
                }else {
                    narrativeAttachment = getNarrativeAttachmentList().get(0);
                    if (narrativeAttachment == null) {
                        narrativeAttachment = new NarrativeAttachment();
                        getNarrativeAttachmentList().set(0, narrativeAttachment);
                    }
                }
                String fileName = narrativeFile.getFileName();
                narrativeAttachment.setFileName(fileName);
                narrativeAttachment.setContentType(narrativeFile.getContentType());
                narrativeAttachment.setNarrativeData(narrativeFile.getFileData());
                narrativeAttachment.setProposalNumber(getProposalNumber());
                narrativeAttachment.setModuleNumber(getModuleNumber());
                setFileName(narrativeAttachment.getFileName());
            }else {
                getNarrativeAttachmentList().clear();
            }
        }catch (FileNotFoundException e) {
            getNarrativeAttachmentList().clear();
        }catch (IOException e) {
            getNarrativeAttachmentList().clear();
        }
    }
    
    /**
     * Gets index i from the narrativeUserRights list.
     * 
     * @param index
     * @return Question at index i
     */
    public NarrativeUserRights getNarrativeUserRight(int index) {
        while (getNarrativeUserRights().size() <= index) {
            getNarrativeUserRights().add(new NarrativeUserRights());
        }
        return (NarrativeUserRights) getNarrativeUserRights().get(index);
    }

    public void clearAttachment() {
        getNarrativeAttachmentList().clear();
    }
    
    @Override
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        managedLists.add(getNarrativeUserRights());
        return managedLists;
    }
    
    /**
     * Determine if two Narratives have the same values.
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj instanceof Narrative) {
            Narrative other = (Narrative) obj;
            return StringUtils.equals(this.proposalNumber, other.proposalNumber) &&
                   this.moduleNumber.equals(other.moduleNumber) &&
                   StringUtils.equals(this.comments, other.comments) &&
                   StringUtils.equals(this.contactName, other.contactName) &&
                   StringUtils.equals(this.emailAddress, other.emailAddress) &&
                   this.moduleSequenceNumber.equals(other.moduleSequenceNumber) &&
                   StringUtils.equals(this.moduleStatusCode, other.moduleStatusCode) &&
                   StringUtils.equals(this.moduleTitle, other.moduleTitle) &&
                   StringUtils.equals(this.narrativeTypeCode, other.narrativeTypeCode) &&
                   StringUtils.equals(this.phoneNumber, other.phoneNumber) &&
                   this.narrativeType.equals(other.narrativeType) &&
                   this.narrativeStatus.equals(other.narrativeStatus) &&
                   StringUtils.equals(this.fileName, other.fileName);
        }
        return false;
    }

    public Timestamp getTimestampDisplay() {
        return timestampDisplay;
    }

    public void setTimestampDisplay(Timestamp timestampDisplay) {
        this.timestampDisplay = timestampDisplay;
    }

    public String getUploadUserDisplay() {
        return uploadUserDisplay;
    }

    public void setUploadUserDisplay(String uploadUserDisplay) {
        this.uploadUserDisplay = uploadUserDisplay;
    }
}
