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
package org.kuali.kra.subaward.bo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.sql.Timestamp;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.upload.FormFile;
import org.kuali.coeus.common.framework.attachment.KcAttachmentDataDao;
import org.kuali.coeus.sys.api.model.KcFile;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;

public class SubAwardAttachments extends SubAwardAssociate implements Comparable<SubAwardAttachments>,KcFile {

     private String  subAwardCode;
     private Integer sequenceNumber;
     private Integer attachmentId;
     private SubAwardAttachmentType typeAttachment;
     private String  description;
     private Long subAwardId;
     private transient FormFile newFile;
     private String subAwardAttachmentTypeCode;
     private Integer documentId;
     private String fileName;
     private String fileDataId;
     private String oldFileDataId;
     private transient SoftReference<byte[]> document;
     private String mimeType;
     private Boolean selectToPrint = false;
     private String fileNameSplit;
     private String documentStatusCode;
     private boolean modifyAttachment=false;

     private String lastUpdateUser;
     private Timestamp lastUpdateTimestamp;
     
     private transient KcAttachmentDataDao kcAttachmentDataDao;
     /**
     * Gets the fileNameSplit attribute. 
     * @return Returns the fileNameSplit.
     */
    public String getFileNameSplit() {
        return fileNameSplit;
    }

    /**
     * Sets the fileNameSplit attribute value.
     * @param fileNameSplit The fileNameSplit to set.
     */
    public void setFileNameSplit(String fileNameSplit) {
        this.fileNameSplit = fileNameSplit;
    }

    public final Boolean getSelectToPrint() {
         return selectToPrint;
     }

     public final void setSelectToPrint(Boolean selectToPrint) {
         this.selectToPrint = selectToPrint;
     }
     

     

     @Override
     public String getName() {
         return getFileName();
     }

     @Override
     public String getType() {
         return getMimeType();
 }
     @Override
     public byte[] getData() {
         return getDocument();
     }
     
     public SubAwardAttachmentType getTypeAttachment() {
        return typeAttachment;
    }
    public void setTypeAttachment(SubAwardAttachmentType typeAttachment) {
        this.typeAttachment = typeAttachment;
    }
    public byte[] getAttachmentContent() {
         return getDocument();
     }
     public String getContentType() {
         return getMimeType();
     }
     public void setContentType(String contentType) {
         setMimeType(contentType);
     }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public byte[] getDocument() {
        if (document != null) {
            byte[] existingData = document.get();
            if (existingData != null) {
                return existingData;
            }
        }
        //if we didn't have a softreference, grab the data from the db
        byte[] newData = getKcAttachmentDataDao().getData(fileDataId);
        document = new SoftReference<byte[]>(newData);
        return newData;
    }

    public void setDocument(byte[] document) {
        if (document == null || document.length == 0) {
            setFileDataId(null);
        } else {
            setFileDataId(getKcAttachmentDataDao().saveData(document, null));
        }
        this.document = new SoftReference<byte[]>(document);
    }
    public SubAwardAttachments() {
         super();
     }
     
     public SubAwardAttachments(final SubAward subaward) {
         this.setSubAward(subaward);
     }
     
     public String getFileName() {
         return fileName;
     }

     public void setFileName(String fileName) {
         this.fileName = fileName;
     }
     
    public Long getSubAwardId() {
        return subAwardId;
    }

    public void setSubAwardId(Long subAwardId) {
        this.subAwardId = subAwardId;
    }

    public String getSubAwardAttachmentTypeCode() {
        return subAwardAttachmentTypeCode;
    }

    public void setSubAwardAttachmentTypeCode(String subAwardAttachmentTypeCode) {
        this.subAwardAttachmentTypeCode = subAwardAttachmentTypeCode;
    }

    
    public String getAttachmentDescription() {
        return "Subaward Attachment";
    }

    

    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }
     
    /**
     * Gets the newFile.
     * @return newFile.
     */
    public FormFile getNewFile() {
        return this.newFile;
    }
    /**
     * Sets the  newFile.
     */
    public void setNewFile(FormFile newFile) {
        this.newFile = newFile;
    }
    
    public String getSubAwardCode() {
        return subAwardCode;
    }
    
    public void setSubAwardCode(String subAwardCode) {
        this.subAwardCode = subAwardCode;
    }
    
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }
    
    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }
    
    public Integer getAttachmentId() {
        return attachmentId;
    }
    
    public void setAttachmentId(Integer attachmentId) {
        this.attachmentId = attachmentId;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public boolean isNew() {
        return this.getAttachmentId() == null;
    }
    
    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((documentId == null) ? 0 : documentId.hashCode());
        result = prime * result + ((subAwardAttachmentTypeCode == null) ? 0 : subAwardAttachmentTypeCode.hashCode());
        result = prime * result + ((this.fileDataId == null) ? 0 : this.fileDataId.hashCode());
        result = prime * result + ((this.attachmentId == null) ? 0 : this.attachmentId.hashCode());
        result = prime * result + ((mimeType == null) ? 0 : mimeType.hashCode());
        return result;
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
        SubAwardAttachments other = (SubAwardAttachments) obj;
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
        if (subAwardAttachmentTypeCode == null) {
            if (other.subAwardAttachmentTypeCode != null) {
                return false;
            }
        } else if (!subAwardAttachmentTypeCode.equals(other.subAwardAttachmentTypeCode)) {
            return false;
        }
        if (this.fileDataId == null) {
            if (other.fileDataId != null) {
                return false;
            }
        } else if (!this.fileDataId.equals(other.fileDataId)) {
            return false;
        }
        if (this.attachmentId == null) {
            if (other.attachmentId != null) {
                return false;
            }
        } else if (!this.attachmentId.equals(other.attachmentId)) {
            return false;
        }
        if (mimeType == null) {
            if (other.mimeType != null)
                return false;
        }
        else if (!mimeType.equals(other.mimeType))
            return false;
        return true;
    }
    
    /**
     * 
     * @see org.kuali.kra.bo.KraPersistableBusinessObjectBase#beforeUpdate(org.apache.ojb.broker.PersistenceBroker)
     */
    @Override
    protected void preUpdate() {
        super.preUpdate();
        if (this.getVersionNumber() == null) {
            this.setVersionNumber(new Long(0));
        }
    }

    /**
     * 
     * This method returns the full name of the update user.
     * @return
     */
    public String getLastUpdateUserName() {
        Person updateUser = KcServiceLocator.getService(PersonService.class).getPersonByPrincipalName(this.getLastUpdateUser());
        return updateUser != null ? updateUser.getName() : this.getUpdateUser();
    }
    /**.
    *
    * This method used to populate the attachment
    * by reading FormFile
    */
   public void populateAttachment() {
       FormFile newFile = getNewFile();
       if (newFile == null) {
       return;
       }
       byte[] newFileData;
       try {
           newFileData = newFile.getFileData();
           setDocument(newFileData);
           if (newFileData.length > 0) {
               mimeType = newFile.getContentType();
               fileName = newFile.getFileName();
           }
       } catch (FileNotFoundException e) {
       } catch (IOException e) {
       }
   }

    public String getDocumentStatusCode() {
		return documentStatusCode;
	}

	public void setDocumentStatusCode(String documentStatusCode) {
		this.documentStatusCode = documentStatusCode;
	}

	public boolean isModifyAttachment() {
		return modifyAttachment;
	}

	public void setModifyAttachment(boolean modifyAttachment) {
		this.modifyAttachment = modifyAttachment;
	}

	@Override
    public void resetPersistenceState() {
        this.setAttachmentId(null);
    }

    @Override
    public int compareTo(SubAwardAttachments o) {
        return this.getAttachmentId().compareTo(o.getAttachmentId());
       
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    public Timestamp getLastUpdateTimestamp() {
        return lastUpdateTimestamp;
    }

    public void setLastUpdateTimestamp(Timestamp lastUpdateTimestamp) {
        this.lastUpdateTimestamp = lastUpdateTimestamp;
    }

    @Override
    public void prePersist() {
        super.prePersist();
        if (lastUpdateUser == null) {
            setLastUpdateUser(getUpdateUser());
        }
        if (lastUpdateTimestamp == null) {
            setLastUpdateTimestamp(getUpdateTimestamp());
        }
    }

	public String getFileDataId() {
		return fileDataId;
	}

	public void setFileDataId(String fileDataId) {
		if (!StringUtils.equals(this.fileDataId, fileDataId)) {
			oldFileDataId = this.fileDataId;
		}
		this.fileDataId = fileDataId;
	}
	
	@Override
    public void postRemove() {
		super.postRemove();
        if (getFileDataId() != null) {
            getKcAttachmentDataDao().removeData(getFileDataId());
        }
    }
	
	@Override
	public void postUpdate() {
		super.postUpdate();
		if (oldFileDataId != null && !StringUtils.equals(fileDataId, oldFileDataId)) {
			getKcAttachmentDataDao().removeData(oldFileDataId);
			oldFileDataId = null;
		}
	}
    
	public KcAttachmentDataDao getKcAttachmentDataDao() {
		if (kcAttachmentDataDao == null) {
			kcAttachmentDataDao = KcServiceLocator.getService(KcAttachmentDataDao.class);
		}
		return kcAttachmentDataDao;
	}
	public void setKcAttachmentDataDao(KcAttachmentDataDao kcAttachmentDao) {
		this.kcAttachmentDataDao = kcAttachmentDao;
	}

	public String getOldFileDataId() {
		return oldFileDataId;
	}

	public void setOldFileDataId(String oldFileDataId) {
		this.oldFileDataId = oldFileDataId;
	}
}
