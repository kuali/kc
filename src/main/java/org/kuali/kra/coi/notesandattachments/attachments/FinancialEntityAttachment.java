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


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.upload.FormFile;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.bo.AttachmentFile;
import org.kuali.kra.coi.PersonFinIntDisclosureAssociate;
import org.kuali.kra.coi.personfinancialentity.PersonFinIntDisclosure;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.util.GlobalVariables;

public class FinancialEntityAttachment extends PersonFinIntDisclosureAssociate implements Comparable<FinancialEntityAttachment>{
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 8722598360752485817L;
    private Long attachmentId;
    private Long fileId;
    private Long financialEntityId;
    private PersonFinIntDisclosure financialEntity;
    
    private transient AttachmentFile file;
    private transient FormFile newFile;
    @SkipVersioning
    private transient String updateUserFullName;   
    private Long personFinIntDisclosureId;
    private String description;
    private String contactName;
    private String contactEmailAddress;
    private String contactPhoneNumber;
    private String comments;
    private String statusCode;
    private Timestamp updateTimestamp;
    
    public FinancialEntityAttachment() {
        super();
    }

    public FinancialEntityAttachment(FinancialEntityAttachment oldAtt) {
        this.attachmentId = null;
        this.fileId = oldAtt.fileId;
        this.financialEntityId = oldAtt.financialEntityId;
        this.personFinIntDisclosureId = oldAtt.personFinIntDisclosureId;
        this.description = oldAtt.description;
        this.contactName = oldAtt.contactName;
        this.contactEmailAddress = oldAtt.contactEmailAddress;
        this.contactPhoneNumber = oldAtt.contactPhoneNumber;
        this.comments = oldAtt.comments;
        this.statusCode = oldAtt.statusCode;
        this.updateTimestamp = oldAtt.updateTimestamp;
        
    }
    public FinancialEntityAttachment(PersonFinIntDisclosure personFinIntDisclosure) {
        this.setPersonFinIntDisclosure(personFinIntDisclosure);
    }

    
    public Long getFinancialEntityId() {
        return financialEntityId;
    }

    public void setFinancialEntityId(Long financialEntityId) {
        this.financialEntityId = financialEntityId;
    }

    public PersonFinIntDisclosure getFinancialEntity() {
        return financialEntity;
    }

    public void setFinancialEntity(PersonFinIntDisclosure financialEntity) {
        this.financialEntity = financialEntity;
    }

    public Timestamp getUpdateTimestamp() {
        return updateTimestamp;
    }
    public void setUpdateTimestamp(Timestamp updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
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
    public AttachmentFile getFile() {
        return file;
    }
    public String getFileName() {
        return (file == null) ? "" : file.getName();
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
    public Long getPersonFinIntDisclosureId() {
        return personFinIntDisclosureId;
    }
    public void setPersonFinIntDisclosureId(Long personFinIntDisclosureId) {
        this.personFinIntDisclosureId = personFinIntDisclosureId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
    public String getStatusCode() {
        return statusCode;
    }

    public void setUpdateUser(String updateUser) {
        if (updateUser == null || getUpdateUser() == null  ) {
        super.setUpdateUser(updateUser);
        }
    }
    
    @Override
    public int compareTo(FinancialEntityAttachment arg0) {
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
        FinancialEntityAttachment other = (FinancialEntityAttachment) obj;
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
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
        result = prime * result + ((this.file == null) ? 0 : this.file.hashCode());
        result = prime * result + ((this.fileId == null) ? 0 : this.fileId.hashCode());
        return result;
    }

    public void updateParms() {
        setUpdateUser(GlobalVariables.getUserSession().getPrincipalName());
        setUpdateTimestamp(((DateTimeService)CoreApiServiceLocator.getDateTimeService()).getCurrentTimestamp());
    }
    
    public static List<FinancialEntityAttachment> copyAttachmentList(List<FinancialEntityAttachment>oldList) {
        List<FinancialEntityAttachment> newList = new ArrayList<FinancialEntityAttachment>();
        for (FinancialEntityAttachment att : oldList) {
            newList.add(new FinancialEntityAttachment(att));
        }
        return newList;
    }
}
