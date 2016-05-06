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
package org.kuali.coeus.common.framework.attachment;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.upload.FormFile;
import org.kuali.coeus.sys.api.model.KcFile;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.framework.version.sequence.associate.SeparateAssociate;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Arrays;

/**
 * Represents a Attachment File.
 */
public class AttachmentFile extends SeparateAssociate implements KcFile {

    /** the max file name length. length={@value}*/
    public static final int MAX_FILE_NAME_LENGTH = 150;

    /** the max file type length. length={@value}*/
    public static final int MAX_FILE_TYPE_LENGTH = 250;

    private static final long serialVersionUID = 8999619585664343780L;

    private Long id;
    
    private String name;

    private String type;
    
    private transient WeakReference<byte[]> fileDataData;

    private byte[] data;
    
    private String fileDataId;
    
    private String oldFileDataId;
    
    private transient KcAttachmentDataDao kcAttachmentDataDao;

    /**
     * empty ctor to satisfy JavaBean convention.
     */
    public AttachmentFile() {
        super();
    }

    /**
     * Convenience ctor to set the relevant properties of this class.
     * 
     * <p>
     * This ctor does not validate any of the properties.
     * </p>
     * 
     * @param name the name.
     * @param type the type.
     * @param data the data.
     */
    public AttachmentFile(String name, String type, byte[] data) {
        this.setName(name);
        this.setType(type);
        this.setData(data);
    }

    /**
     * factory method creating an instance from a {@link FormFile FormFile}.
     * <p>
     * If the file name's length > {@link #MAX_FILE_NAME_LENGTH} then the name will be
     * modified.
     * </p>
     * 
     * <p>
     * If the file type's length > {@link #MAX_FILE_TYPE_LENGTH} then the type will be
     * modified.
     * </p>
     * @param formFile the {@link FormFile FormFile}
     * @return an instance
     * @throws IllegalArgumentException if the formfile is null.
     * @throws CreateException if unable to create from FormFile.
     */
    public static final AttachmentFile createFromFormFile(FormFile formFile) {
        if (formFile == null) {
            throw new IllegalArgumentException("the formFile is null");
        }
        final String fName = removeFrontForLength(formFile.getFileName(), MAX_FILE_NAME_LENGTH);
        final String fType = removeFrontForLength(formFile.getContentType(), MAX_FILE_TYPE_LENGTH);
        try {
            return new AttachmentFile(fName, fType, formFile.getFileData());
        } catch (IOException e) {
            throw new CreateException(e);
        }
    }

    /**
     * Removes the start of String in order to meet the passed in length.
     * @param aString the string.
     * @param aLength the length.
     * @return the modified string.
     */
    static String removeFrontForLength(String aString, int aLength) {
        assert aString != null : "aString is null";
        assert aLength > 0 : "aLength is negative: " + aLength;
        if (aString.length() > aLength) {
            StringBuilder tempString = new StringBuilder(aString);
            tempString.delete(0, tempString.length() - aLength);
            return tempString.toString();
        }
        return aString;
    }

    /**
     * Gets the Protocol Attachment File name.
     * @return the Protocol Attachment File name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the Protocol Attachment File name.
     * @param name the Protocol Attachment File name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the Protocol Attachment File type.
     * @return the Protocol Attachment File type
     */
    public String getType() {
        return this.type;
    }

    /**
     * Sets the Protocol Attachment File type.
     * @param type the Protocol Attachment File type
     */
    public void setType(String type) {
        this.type = type;
    }
    
    public byte[] getData() {
    	if (fileDataId != null) {
	        if (fileDataData != null) {
	            byte[] existingData = fileDataData.get();
	            if (existingData != null) {
	                return existingData;
	            }
	        }
	        //if we didn't have a softreference, grab the data from the db
	        byte[] newData = getKcAttachmentDataDao().getData(fileDataId);
	        fileDataData = new WeakReference<byte[]>(newData);
	        return newData;
    	} else {
    		return (data == null) ? null : data.clone();
    	}
    }

    public void setData(byte[] data) {
        if (data == null || data.length == 0) {
            setFileDataId(null);
        } else {
            setFileDataId(getKcAttachmentDataDao().saveData(data, null));
        }
        this.fileDataData = new WeakReference<byte[]>(data);
        this.data = null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Arrays.hashCode(this.getData());
        result = prime * result + ((this.getName() == null) ? 0 : this.getName().hashCode());
        result = prime * result + ((this.getType() == null) ? 0 : this.getType().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        AttachmentFile other = (AttachmentFile) obj;
        if (!Arrays.equals(this.getData(), other.getData())) {
            return false;
        }
        if (this.getName() == null) {
            if (other.getName() != null) {
                return false;
            }
        } else if (!this.getName().equals(other.getName())) {
            return false;
        }
        if (this.getType() == null) {
            if (other.getType() != null) {
                return false;
            }
        } else if (!this.getType().equals(other.getType())) {
            return false;
        }
        return true;
    }

    /**
     * Exception thrown when unable to create instance from static factory.
     */
    public static class CreateException extends RuntimeException {

        private static final long serialVersionUID = -230592614193518930L;

        /**
         * Wraps caused-by Throwable.
         * @param t the Throwable
         */
        public CreateException(Throwable t) {
            super(t);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
