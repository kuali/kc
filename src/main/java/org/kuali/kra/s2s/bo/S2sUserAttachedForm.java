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
import java.util.LinkedHashMap;
import java.util.List;

public class S2sUserAttachedForm extends KraPersistableBusinessObjectBase { 
    
    private static final long serialVersionUID = 1L;

    private Long s2sUserAttachedFormId; 
    private String proposalNumber; 
    private Integer userAttachedFormNumber; 
    private String namespace; 
    private String formName; 
    private String formFileName; 
    private byte[] formFile; 
    private String xmlFile; 
    private String description; 
    private List<S2sUserAttachedFormAtt> s2sUserAttachedFormAtts;
    private transient boolean edit = false;
    private transient FormFile newFormFile;
    private transient boolean newFormFileError = false;
    private boolean xmlDataExists;
    private boolean formFileDataExists;
        
    public S2sUserAttachedForm() { 
        s2sUserAttachedFormAtts = new ArrayList<S2sUserAttachedFormAtt>();
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

    public byte[] getFormFile() {
        return formFile;
    }

    public void setFormFile(byte[] formFile) {
        this.formFile = formFile;
    }

    public String getXmlFile() {
        return xmlFile;
    }

    public void setXmlFile(String xmlFile) {
        this.xmlFile = xmlFile;
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
     * Gets the newFormFileError attribute. 
     * @return Returns the newFormFileError.
     */
    public boolean isNewFormFileError() {
        return newFormFileError;
    }

    /**
     * Sets the newFormFileError attribute value.
     * @param newFormFileError The newFormFileError to set.
     */
    public void setNewFormFileError(boolean newFormFileError) {
        this.newFormFileError = newFormFileError;
    }

    /**
     * Gets the xmlDataExists attribute. 
     * @return Returns the xmlDataExists.
     */
    public boolean isXmlDataExists() {
        return xmlDataExists;
    }

    /**
     * Sets the xmlDataExists attribute value.
     * @param xmlDataExists The xmlDataExists to set.
     */
    public void setXmlDataExists(boolean xmlDataExists) {
        this.xmlDataExists = xmlDataExists;
    }

    /**
     * Gets the formFileDataExists attribute. 
     * @return Returns the formFileDataExists.
     */
    public boolean isFormFileDataExists() {
        return formFileDataExists;
    }

    /**
     * Sets the formFileDataExists attribute value.
     * @param formFileDataExists The formFileDataExists to set.
     */
    public void setFormFileDataExists(boolean formFileDataExists) {
        this.formFileDataExists = formFileDataExists;
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
//    @Override
//    protected void postPersist() {
//        super.postPersist();
//        setFormFileDataExists(getFormFile()!=null);
//        setFormFile(null);
//        setXmlDataExists(getXmlFile()!=null);
//        setXmlFile(null);
//    }
}