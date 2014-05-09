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

public class S2sUserAttachedFormFile extends KraPersistableBusinessObjectBase { 
    

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 379923661813734826L;
    private Long s2sUserAttachedFormFileId; 
    private Long s2sUserAttachedFormId; 
    private byte[] formFile; 
    private String xmlFile; 
       
    public S2sUserAttachedFormFile() { 

    }

    /**
     * Gets the s2sUserAttachedFormFileId attribute. 
     * @return Returns the s2sUserAttachedFormFileId.
     */
    public Long getS2sUserAttachedFormFileId() {
        return s2sUserAttachedFormFileId;
    }

    /**
     * Sets the s2sUserAttachedFormFileId attribute value.
     * @param s2sUserAttachedFormFileId The s2sUserAttachedFormFileId to set.
     */
    public void setS2sUserAttachedFormFileId(Long s2sUserAttachedFormFileId) {
        this.s2sUserAttachedFormFileId = s2sUserAttachedFormFileId;
    }

    /**
     * Gets the s2sUserAttachedFormId attribute. 
     * @return Returns the s2sUserAttachedFormId.
     */
    public Long getS2sUserAttachedFormId() {
        return s2sUserAttachedFormId;
    }

    /**
     * Sets the s2sUserAttachedFormId attribute value.
     * @param s2sUserAttachedFormId The s2sUserAttachedFormId to set.
     */
    public void setS2sUserAttachedFormId(Long s2sUserAttachedFormId) {
        this.s2sUserAttachedFormId = s2sUserAttachedFormId;
    }

    /**
     * Gets the formFile attribute. 
     * @return Returns the formFile.
     */
    public byte[] getFormFile() {
        return formFile;
    }

    /**
     * Sets the formFile attribute value.
     * @param formFile The formFile to set.
     */
    public void setFormFile(byte[] formFile) {
        this.formFile = formFile;
    }

    /**
     * Gets the xmlFile attribute. 
     * @return Returns the xmlFile.
     */
    public String getXmlFile() {
        return xmlFile;
    }

    /**
     * Sets the xmlFile attribute value.
     * @param xmlFile The xmlFile to set.
     */
    public void setXmlFile(String xmlFile) {
        this.xmlFile = xmlFile;
    }
  
}