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
import org.kuali.kra.coi.CoiAttachmentType;
import org.kuali.kra.coi.CoiDisclProject;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiDisclosureAssociate;
import org.kuali.kra.coi.personfinancialentity.PersonFinIntDisclosure;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ObjectUtils;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoiDisclosureAttachment extends CoiDisclosureAssociate implements Comparable<CoiDisclosureAttachment>{

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
    private String eventTypeCode;
    private Long originalCoiDisclosureId; 
    @SkipVersioning
    private CoiDisclosure originalCoiDisclosure;
    private Long financialEntityId;
    private String typeCode;
    private CoiAttachmentType coiAttachmentType;
    private final String MESSAGE_UPDATED_BY = "message.updated.by";
    @SkipVersioning
    private PersonFinIntDisclosure financialEntity;
    private static String updatedByString;
    private transient Long attachmentIdForPermission;

    private String usageSectionId;
    
    public CoiDisclosureAttachment() {
        super();
    }
    
    public CoiDisclosureAttachment(CoiDisclosure coiDisclosure) {
        this.setCoiDisclosure(coiDisclosure);
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
    
    public String getFinancialEntityName() {
        refreshReferenceObject("financialEntity");
        return ObjectUtils.isNotNull(getFinancialEntity()) ? getFinancialEntity().getEntityName() : "";
    }
    
    public String getEventTypeCode() {
        return eventTypeCode;
    }

    public void setEventTypeCode(String eventTypeCode) {
        this.eventTypeCode = eventTypeCode;
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
        if (attachmentId != null) {
            this.attachmentIdForPermission = attachmentId;
        }
    }
    public Long getAttachmentIdForPermission() {
        if (attachmentIdForPermission == null) {
            attachmentIdForPermission = getAttachmentId();
        }
        return attachmentIdForPermission;
    }
    public void setAttachmentIdForPermission(Long attachmentId) {
        this.attachmentIdForPermission = attachmentId;
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
    public CoiAttachmentType getCoiAttachmentType() {
        return coiAttachmentType;
    }
    public void setCoiAttachmentType(CoiAttachmentType coiAttachmentType) {
        this.coiAttachmentType = coiAttachmentType;
    }
    public String getTypeCode() {
        return typeCode;
    }
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
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
    
    public String getProjectName() {
        refreshReferenceObject("coiDisclProjects");
        for (CoiDisclProject project : getCoiDisclosure().getCoiDisclProjects()) {
            if (StringUtils.equalsIgnoreCase(project.getProjectId(), getProjectId())) {
                return project.getCoiProjectTitle();
            }
        }
        return "";
    }
   
    @Override
    public int compareTo(CoiDisclosureAttachment arg0) {

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
    

    public Long getOriginalCoiDisclosureId() {
        return originalCoiDisclosureId;
    }

    public void setOriginalCoiDisclosureId(Long originalCoiDisclosureId) {
        this.originalCoiDisclosureId = originalCoiDisclosureId;
    }

    public CoiDisclosure getOriginalCoiDisclosure() {
        return originalCoiDisclosure;
    }

    public void setOriginalCoiDisclosure(CoiDisclosure originalCoiDisclosure) {
        this.originalCoiDisclosure = originalCoiDisclosure;
    }

    public String getShortDescription() {
        String result = StringUtils.abbreviate(getDescription(), 20) + ": " + StringUtils.abbreviate(getFile().getName(), 20);
        if (!StringUtils.isEmpty(getUpdateUser())) {
            result += ": " + getUpdatedByString() + " " + getUpdateUser();
        }
        return result;
    }

    private String getUpdatedByString() {
        if (updatedByString == null) {
            updatedByString = CoreApiServiceLocator.getKualiConfigurationService().getPropertyValueAsString(MESSAGE_UPDATED_BY);
        }
        return updatedByString;
    }
    
    protected void postRemove() {
        //if there aren't another other attachments to the actual file, then delete.
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("fileId", getFileId());
        BusinessObjectService boService = KcServiceLocator.getService(BusinessObjectService.class);
        if (boService.countMatching(CoiDisclosureAttachment.class, values) == 1) {
            boService.delete(getFile());
        } 
    }

    public String getUsageSectionId() {
        return usageSectionId;
    }

    public void setUsageSectionId(String usageSectionId) {
        this.usageSectionId = usageSectionId;
    }

}
