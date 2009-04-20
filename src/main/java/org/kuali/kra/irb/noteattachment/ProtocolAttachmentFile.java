/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.noteattachment;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;

import org.apache.struts.upload.FormFile;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * Represents a Protocol Attachment File.
 */
public class ProtocolAttachmentFile extends KraPersistableBusinessObjectBase {
    
    /** the max file name length. length={@value}*/
    public static final int MAX_FILE_NAME_LENGTH = 150;
    
    /** the max file type length. length={@value}*/
    public static final int MAX_FILE_TYPE_LENGTH = 250;
    
    private static final long serialVersionUID = 8999619585664343780L;
    
    private Long id;
    private String name;
    private String type;
    private byte[] data;
    
    /**
     * empty ctor to satisfy JavaBean convention.
     */
    public ProtocolAttachmentFile() {
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
    private ProtocolAttachmentFile(String name, String type, byte[] data) {
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
    public static final ProtocolAttachmentFile createFromFormFile(FormFile formFile) {
        
        if (formFile == null) {
            throw new IllegalArgumentException("the formFile is null");
        }
        
        final String fName = removeFrontForLength(formFile.getFileName(), MAX_FILE_NAME_LENGTH);
        final String fType = removeFrontForLength(formFile.getContentType(), MAX_FILE_TYPE_LENGTH);
        
        try {
            return new ProtocolAttachmentFile(fName, fType, formFile.getFileData());
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
    private static String removeFrontForLength(String aString, int aLength) {
        assert aString != null : "aString is null";
        assert aLength < 0 : "aLength is negative";
        
        if (aString.length() > aLength) {
            StringBuilder tempString = new StringBuilder(aString);
            tempString.delete(0, tempString.length() - aLength);
            return tempString.toString();
        }
        return aString;
    }
    
    /**
     * Gets the the Protocol Attachment File id.
     * @return the Protocol Attachment File id.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Sets the the Protocol Attachment File id.
     * @param id the Protocol Attachment File id.
     */
    public void setId(Long id) {
        this.id = id;
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
    
    /**
     * Gets the Protocol Attachment File data.
     * @return the Protocol Attachment File data
     */
    public byte[] getData() {
        return (this.data == null) ? null : this.data.clone();
    }
    
    /**
     * Sets the Protocol Attachment File data.
     * @param data the Protocol Attachment File data
     */
    public void setData(byte[] data) {
        this.data = (data == null) ? null : data.clone();
    }
    
    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("id", this.getId());
        hashMap.put("name", this.getName());
        hashMap.put("contentType", this.getType());
        return hashMap;
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(this.data);
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ProtocolAttachmentFile)) {
            return false;
        }
        ProtocolAttachmentFile other = (ProtocolAttachmentFile) obj;
        if (!Arrays.equals(this.data, other.data)) {
            return false;
        }
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!this.id.equals(other.id)) {
            return false;
        }
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!this.name.equals(other.name)) {
            return false;
        }
        if (this.type == null) {
            if (other.type != null) {
                return false;
            }
        } else if (!this.type.equals(other.type)) {
            return false;
        }
        return true;
    }
    
    /**
     * Exception thrown when unable to create instance from static factory.
     */
    public static class CreateException extends RuntimeException {
        
        /**
         * Wraps caused-by Throwable.
         * @param t the Throwable
         */
        public CreateException(Throwable t) {
            super(t);
        }
    }
}
