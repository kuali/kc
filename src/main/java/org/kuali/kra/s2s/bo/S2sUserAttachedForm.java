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

import org.apache.struts.upload.FormFile;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

import java.util.ArrayList;
import java.util.List;

public class S2sUserAttachedForm extends KraPersistableBusinessObjectBase { 
    

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 6167805669946501317L;
    private Long s2sUserAttachedFormId; 
    private String proposalNumber; 
    private Integer userAttachedFormNumber; 
    private String namespace; 
    private String formName; 
    private String formFileName; 
    private String description; 
    private List<S2sUserAttachedFormAtt> s2sUserAttachedFormAtts;
    private List<S2sUserAttachedFormFile> s2sUserAttachedFormFileList;
    private transient boolean edit = false;
    private transient FormFile newFormFile;
    private transient byte[] newFormFileBytes;
        
    public S2sUserAttachedForm() { 
        s2sUserAttachedFormAtts = new ArrayList<S2sUserAttachedFormAtt>();
        s2sUserAttachedFormFileList = new ArrayList<S2sUserAttachedFormFile>();
    } 
    
    public Long getS2sUserAttachedFormId() {
        return s2sUserAttachedFormId;
    }

    public void setS2sUserAttachedFormId(Long s2sUserAttachedFormId) {
        this.s2sUserAttachedFormId = s2sUserAttachedFormId;
    }

    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public Integer getUserAttachedFormNumber() {
        return userAttachedFormNumber;
    }

    public void setUserAttachedFormNumber(Integer userAttachedFormNumber) {
        this.userAttachedFormNumber = userAttachedFormNumber;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getFormFileName() {
        return formFileName;
    }

    public void setFormFileName(String formFileName) {
        this.formFileName = formFileName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the edit attribute. 
     * @return Returns the edit.
     */
    public boolean isEdit() {
        return edit;
    }

    /**
     * Sets the edit attribute value.
     * @param edit The edit to set.
     */
    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    /**
     * Gets the newFormFile attribute. 
     * @return Returns the newFormFile.
     */
    public FormFile getNewFormFile() {
        return newFormFile;
    }

    /**
     * Sets the newFormFile attribute value.
     * @param newFormFile The newFormFile to set.
     */
    public void setNewFormFile(FormFile newFormFile) {
        this.newFormFile = newFormFile;
    }

    /**
     * Gets the s2sUserAttachedFormAtts attribute. 
     * @return Returns the s2sUserAttachedFormAtts.
     */
    public List<S2sUserAttachedFormAtt> getS2sUserAttachedFormAtts() {
        return s2sUserAttachedFormAtts;
    }

    /**
     * Sets the s2sUserAttachedFormAtts attribute value.
     * @param s2sUserAttachedFormAtts The s2sUserAttachedFormAtts to set.
     */
    public void setS2sUserAttachedFormAtts(List<S2sUserAttachedFormAtt> s2sUserAttachedFormAtts) {
        this.s2sUserAttachedFormAtts = s2sUserAttachedFormAtts;
    }

    /**
     * Gets the s2sUserAttachedFormFileList attribute. 
     * @return Returns the s2sUserAttachedFormFileList.
     */
    public List<S2sUserAttachedFormFile> getS2sUserAttachedFormFileList() {
        return s2sUserAttachedFormFileList;
    }

    /**
     * Sets the s2sUserAttachedFormFileList attribute value.
     * @param s2sUserAttachedFormFileList The s2sUserAttachedFormFileList to set.
     */
    public void setS2sUserAttachedFormFileList(List<S2sUserAttachedFormFile> s2sUserAttachedFormFileList) {
        this.s2sUserAttachedFormFileList = s2sUserAttachedFormFileList;
    }

    /**
     * Gets the newFormFileBytes attribute. 
     * @return Returns the newFormFileBytes.
     */
    public byte[] getNewFormFileBytes() {
        return newFormFileBytes;
    }

    /**
     * Sets the newFormFileBytes attribute value.
     * @param newFormFileBytes The newFormFileBytes to set.
     */
    public void setNewFormFileBytes(byte[] newFormFileBytes) {
        this.newFormFileBytes = newFormFileBytes;
    }
}