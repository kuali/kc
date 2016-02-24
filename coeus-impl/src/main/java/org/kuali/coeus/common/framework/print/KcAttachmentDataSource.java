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
package org.kuali.coeus.common.framework.print;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.attachment.KcAttachmentDataDao;
import org.kuali.coeus.sys.api.model.KcFile;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;

import javax.persistence.*;

import java.lang.ref.WeakReference;
import java.sql.Timestamp;

/**
 * 
 * This class helps create the foundation of attachment data sources.
 */
@MappedSuperclass
public abstract class KcAttachmentDataSource extends KcPersistableBusinessObjectBase implements KcFile {

    @Column(name = "FILE_NAME")
    private String name;

    @Column(name = "CONTENT_TYPE")
    private String type;

    @Column(name = "FILE_DATA_ID")
    private String fileDataId;
    
    @Column(name = "UPLOAD_USER")
    private String uploadUser;
    
    @Column(name = "UPLOAD_TIMESTAMP")
    private Timestamp uploadTimestamp;

    @Transient
    private transient WeakReference<byte[]> data;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public byte[] getData() {
        if (data != null) {
        	byte[] existingData = data.get();
        	if (existingData != null) {
        		return existingData;
        	}
        }
        //if we didn't have a weakreference, grab the data from the db
        byte[] newData = getKcAttachmentDao().getData(fileDataId);
        data = new WeakReference<byte[]>(newData);
        return newData;
    }

    private KcAttachmentDataDao getKcAttachmentDao() {
        return KcServiceLocator.getService(KcAttachmentDataDao.class);
    }

    public String getFileDataId() {
        return fileDataId;
    }

    public void setFileDataId(String fileDataId) {
        this.fileDataId = fileDataId;
    }

    public void setData(byte[] data) {
        if (data == null) {
            getKcAttachmentDao().removeData(fileDataId);
        } else {
            fileDataId = getKcAttachmentDao().saveData(data, fileDataId);
        }
        this.data = new WeakReference<byte[]>(data);
    }

	public String getUploadUser() {
		return uploadUser;
	}

	public void setUploadUser(String uploadUser) {
		this.uploadUser = uploadUser;
	}

	public Timestamp getUploadTimestamp() {
		return uploadTimestamp;
	}

	public void setUploadTimestamp(Timestamp uploadTimestamp) {
		this.uploadTimestamp = uploadTimestamp;
	}
	
    @Override
    @PrePersist
    protected void prePersist() {
    	super.prePersist();
    	if (StringUtils.isBlank(uploadUser)) {
    		uploadUser = getUpdateUser();
    	}
    	if (uploadTimestamp == null) {
    		uploadTimestamp = getUpdateTimestamp();
    	}
    }

    @Override
    @PreUpdate
    protected void preUpdate() {
        super.preUpdate();
        if (StringUtils.isBlank(uploadUser)) {
            uploadUser = getUpdateUser();
        }
        if (uploadTimestamp == null) {
            uploadTimestamp = getUpdateTimestamp();
        }
    }
}
