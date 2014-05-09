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
package org.kuali.kra.s2s.bo;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class S2sUserAttachedFormAttFile extends KraPersistableBusinessObjectBase { 
    

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 435752267453958277L;
    private Long s2sUserAttachedFormAttFileId; 
    private Long s2sUserAttachedFormAttId; 
    private byte[] attachment; 
    
    public S2sUserAttachedFormAttFile() { 

    }

    /**
     * Gets the s2sUserAttachedFormAttFileId attribute. 
     * @return Returns the s2sUserAttachedFormAttFileId.
     */
    public Long getS2sUserAttachedFormAttFileId() {
        return s2sUserAttachedFormAttFileId;
    }

    /**
     * Sets the s2sUserAttachedFormAttFileId attribute value.
     * @param s2sUserAttachedFormAttFileId The s2sUserAttachedFormAttFileId to set.
     */
    public void setS2sUserAttachedFormAttFileId(Long s2sUserAttachedFormAttFileId) {
        this.s2sUserAttachedFormAttFileId = s2sUserAttachedFormAttFileId;
    }

    /**
     * Gets the s2sUserAttachedFormAttId attribute. 
     * @return Returns the s2sUserAttachedFormAttId.
     */
    public Long getS2sUserAttachedFormAttId() {
        return s2sUserAttachedFormAttId;
    }

    /**
     * Sets the s2sUserAttachedFormAttId attribute value.
     * @param s2sUserAttachedFormAttId The s2sUserAttachedFormAttId to set.
     */
    public void setS2sUserAttachedFormAttId(Long s2sUserAttachedFormAttId) {
        this.s2sUserAttachedFormAttId = s2sUserAttachedFormAttId;
    }

    /**
     * Gets the attachment attribute. 
     * @return Returns the attachment.
     */
    public byte[] getAttachment() {
        return attachment;
    }

    /**
     * Sets the attachment attribute value.
     * @param attachment The attachment to set.
     */
    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    } 
    
}