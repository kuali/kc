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
package org.kuali.coeus.propdev.impl.s2s;

import org.apache.struts.upload.FormFile;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "S2S_USER_ATTACHED_FORM")
public class S2sUserAttachedForm extends KcPersistableBusinessObjectBase { 
    
    private static final long serialVersionUID = 1L;

    @PortableSequenceGenerator(name = "SEQ_S2S_USER_ATTD_FORM_ID")
    @GeneratedValue(generator = "SEQ_S2S_USER_ATTD_FORM_ID")
    @Id
    @Column(name = "S2S_USER_ATTCHED_FORM_ID")
    private Long s2sUserAttachedFormId; 
    
    @Column(name = "PROPOSAL_NUMBER")
    private String proposalNumber; 
    
    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "PROPOSAL_NUMBER", insertable = false, updatable = false)
    private DevelopmentProposal developmentProposal;
    
    @Transient
    private Integer userAttachedFormNumber;
    
    @Column(name = "NAMESPACE")
    private String namespace; 
    
    @Column(name = "FORM_NAME")
    private String formName; 
    
    @Column(name = "FORM_FILE_NAME")
    private String formFileName; 
    
    @Column(name = "FILE_FILE")
    private byte[] formFile; 
    
    @Column(name = "XML_FILE")
    private String xmlFile; 
    
    @Column(name = "DESCRIPTION")
    private String description; 
    
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = { CascadeType.ALL })
    @JoinColumn(name = "S2S_USER_ATTACHED_FORM_ID", referencedColumnName = "S2S_USER_ATTACHED_FORM_ID")
    private List<S2sUserAttachedFormAtt> s2sUserAttachedFormAtts;
    
    @Transient
    private transient boolean edit = false;
    
    @Transient
    private transient FormFile newFormFile;
    
    @Transient
    private transient boolean newFormFileError = false;
    
    @Transient
    private boolean xmlDataExists;
    
    @Transient
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

	public DevelopmentProposal getDevelopmentProposal() {
		return developmentProposal;
	}

	public void setDevelopmentProposal(DevelopmentProposal developmentProposal) {
		this.developmentProposal = developmentProposal;
	}
}