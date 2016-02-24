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
package org.kuali.kra.coi.notesandattachments.attachments;


import org.apache.commons.lang3.StringUtils;
import org.apache.struts.upload.FormFile;
import org.kuali.coeus.common.framework.attachment.AttachmentFile;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.coi.PersonFinIntDisclosureAssociate;
import org.kuali.kra.coi.personfinancialentity.PersonFinIntDisclosure;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class FinancialEntityAttachment extends PersonFinIntDisclosureAssociate implements Comparable<FinancialEntityAttachment>{

    private static final long serialVersionUID = 8722598360752485817L;
    private Long attachmentId;
    private Long fileId;
    private Long financialEntityId;
    private PersonFinIntDisclosure financialEntity;
    
    private transient AttachmentFile attachmentFile;
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
        this.attachmentFile = (AttachmentFile)ObjectUtils.deepCopy(oldAtt.getAttachmentFile());
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
    public AttachmentFile getAttachmentFile() {
        return attachmentFile;
    }
    public String getFileName() {
        return (attachmentFile == null) ? "" : attachmentFile.getName();
    }
    public void setFile(AttachmentFile attachmentFile) {
        this.attachmentFile = attachmentFile;
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

        return 0;
    }
  
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
       
        if (this.attachmentFile == null) {
            if (other.attachmentFile != null) {
                return false;
            }
        } else if (!this.attachmentFile.equals(other.attachmentFile)) {
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

    public boolean matches(FinancialEntityAttachment other) {
        if (this == other) {
            return true;
        }
        else if (!this.getFileId().equals(other.getFileId())) {
            return false;
        }
        else if (!StringUtils.equals(this.getUpdateUserFullName(), other.getUpdateUserFullName())) {
            return false;
        }
        else if (!StringUtils.equals(this.getDescription(), other.getDescription())) {
            return false;
        }
        else if (!StringUtils.equals(this.getContactName(), other.getContactName())) {
            return false;
        }
        else if (!StringUtils.equals(this.getContactEmailAddress(), other.getContactEmailAddress())) {
            return false;
        }
        else if (!StringUtils.equals(this.getContactPhoneNumber(), other.getContactPhoneNumber())) {
            return false;
        }
        else if (!StringUtils.equals(this.getComments(), other.getComments())) {
            return false;
        }
        else {
            return true;
        }
    }
    
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((this.attachmentFile == null) ? 0 : this.attachmentFile.hashCode());
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
    
    @Override
    public void prePersist() {
        super.prePersist();
        if (getAttachmentFile() != null) {
            KcServiceLocator.getService(BusinessObjectService.class).save(getAttachmentFile());
            getAttachmentFile().refreshReferenceObject("id");   
            setFileId(getAttachmentFile().getId());
        }
    }
}
