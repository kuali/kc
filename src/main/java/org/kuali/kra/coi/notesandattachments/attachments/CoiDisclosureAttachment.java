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
package org.kuali.kra.coi.notesandattachments.attachments;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts.upload.FormFile;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.award.notesandattachments.attachments.AwardAttachment;
import org.kuali.kra.bo.AttachmentFile;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiDisclosureAssociate;
import org.kuali.kra.proposaldevelopment.bo.NarrativeAttachment;

public class CoiDisclosureAttachment extends CoiDisclosureAssociate implements Comparable<CoiDisclosureAttachment>{
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 8722598360752485817L;
    private Long attachmentId;
    private Long fileId;
    private Integer documentId;
    
    private transient AttachmentFile file;
    private transient FormFile newFile;
    @SkipVersioning
    private transient String updateUserFullName;   
    private Long coiDisclosureId;
    private String description;
    private String contactName;
    private String contactEmailAddress;
    private String contactPhoneNumber;
    private String comments;
    private String documentStatusCode;
    private Timestamp createTimestamp;
    private String projectId;
    private String entityNumber;
    private Long entitySequenceNumber;
    
    public CoiDisclosureAttachment() {
        super();
    }
    
    public CoiDisclosureAttachment(CoiDisclosure coiDisclosure) {
        this.setCoiDisclosure(coiDisclosure);
    }

    
    public String getEntityNumber() {
        return entityNumber;
    }

    public void setEntityNumber(String entityNumber) {
        this.entityNumber = entityNumber;
    }

    public Long getEntitySequenceNumber() {
        return entitySequenceNumber;
    }

    public void setEntitySequenceNumber(Long entitySequenceNumber) {
        this.entitySequenceNumber = entitySequenceNumber;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }
    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }
   
    public String getContactEmailAddress() {
        return contactEmailAddress;
    }
    public void setContactEmailAddress(String contactEmailAddress) {
        this.contactEmailAddress = contactEmailAddress;
    }
    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }
    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }
    public String getContactName() {
        return contactName;
    }
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
   
    public Long getAttachmentId() {
        return attachmentId;
    }
    public void setAttachmentId(Long attachmentId) {
        this.attachmentId = attachmentId;
    }
    public Long getFileId() {
        return fileId;
    }
    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }
    public Integer getDocumentId() {
        return documentId;
    }
    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }
    public AttachmentFile getFile() {
        return file;
    }
    public void setFile(AttachmentFile file) {
        this.file = file;
    }
    public FormFile getNewFile() {
        return newFile;
    }
    public void setNewFile(FormFile newFile) {
        this.newFile = newFile;
    }
    public String getUpdateUserFullName() {
        return updateUserFullName;
    }
    public void setUpdateUserFullName(String updateUserFullName) {
        this.updateUserFullName = updateUserFullName;
    }
    public Long getCoiDisclosureId() {
        return coiDisclosureId;
    }
    public void setCoiDisclosureId(Long coiDisclosureId) {
        this.coiDisclosureId = coiDisclosureId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setDocumentStatusCode(String documentStatusCode) {
        this.documentStatusCode = documentStatusCode;
    }
    public String getDocumentStatusCode() {
        return documentStatusCode;
    }
    public static void addAttachmentToCollection(CoiDisclosureAttachment coiDisclosureAttachment,
            List<CoiDisclosureAttachment> coiDisclosureAttachments) {
        if (coiDisclosureAttachment == null) {
            throw new IllegalArgumentException("the attachment is null");
        }
        
        if (coiDisclosureAttachments == null) {
            throw new IllegalArgumentException("the toList is null");
        }
        
        coiDisclosureAttachments.add(coiDisclosureAttachment);
        
    }
    public void setUpdateUser(String updateUser) {
        if (updateUser == null || getUpdateUser() == null  ) {
        super.setUpdateUser(updateUser);
        }
    }
    
    @Override
    public int compareTo(CoiDisclosureAttachment arg0) {
        // TODO Auto-generated method stub
        return 0;
    }
  
    /** {@inheritDoc} */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CoiDisclosureAttachment other = (CoiDisclosureAttachment) obj;
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        if (documentId == null) {
            if (other.documentId != null) {
                return false;
            }
        } else if (!documentId.equals(other.documentId)) {
            return false;
        }
       
        if (this.file == null) {
            if (other.file != null) {
                return false;
            }
        } else if (!this.file.equals(other.file)) {
            return false;
        }
        if (this.fileId == null) {
            if (other.fileId != null) {
                return false;
            }
        } else if (!this.fileId.equals(other.fileId)) {
            return false;
        }
        return true;
    }
    
    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((documentId == null) ? 0 : documentId.hashCode());
        result = prime * result + ((this.file == null) ? 0 : this.file.hashCode());
        result = prime * result + ((this.fileId == null) ? 0 : this.fileId.hashCode());
        return result;
    }
    
    protected void populateAttachment() {
        FormFile newFile = getNewFile();
        if(newFile==null) return;
        byte[] fileData;
       /* try {
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
                setContentType(narrativeAttachment.getContentType());
            }else {
                getNarrativeAttachmentList().clear();
            }
        }catch (FileNotFoundException e) {
            getNarrativeAttachmentList().clear();
        }catch (IOException e) {
            getNarrativeAttachmentList().clear();
        }*/
    }
}
