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
import org.kuali.coeus.propdev.api.s2s.S2sUserAttachedFormContract;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "S2S_USER_ATTACHED_FORM")
public class S2sUserAttachedForm extends KcPersistableBusinessObjectBase implements S2sUserAttachedFormContract {
    

    @PortableSequenceGenerator(name = "SEQ_S2S_USER_ATTD_FORM_ID")
    @GeneratedValue(generator = "SEQ_S2S_USER_ATTD_FORM_ID")
    @Id
    @Column(name = "S2S_USER_ATTACHED_FORM_ID")
    private Long id;
    
    @Column(name = "PROPOSAL_NUMBER")
    private String proposalNumber;
    
    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "PROPOSAL_NUMBER", insertable = false, updatable = false)
    private DevelopmentProposal developmentProposal;
    
    @Column(name = "NAMESPACE")
    private String namespace; 
    
    @Column(name = "FORM_NAME")
    private String formName; 
    
    @Column(name = "FORM_FILE_NAME")
    private String formFileName;
    
    @Column(name = "DESCRIPTION")
    private String description; 
    
    @OneToMany(orphanRemoval = true, cascade = { CascadeType.ALL })
    @JoinColumn(name = "S2S_USER_ATTACHED_FORM_ID", referencedColumnName = "S2S_USER_ATTACHED_FORM_ID")
    private List<S2sUserAttachedFormAtt> s2sUserAttachedFormAtts;

    @OneToMany(orphanRemoval = true, cascade = { CascadeType.ALL })
    @JoinColumn(name = "S2S_USER_ATTACHED_FORM_ID", referencedColumnName = "S2S_USER_ATTACHED_FORM_ID")
    private List<S2sUserAttachedFormFile> s2sUserAttachedFormFileList;

    @Transient
    private transient boolean edit = false;
    
    @Transient
    private transient FormFile newFormFile;

    @Transient
    private transient byte[] newFormFileBytes;
        
    public S2sUserAttachedForm() { 
        s2sUserAttachedFormAtts = new ArrayList<S2sUserAttachedFormAtt>();
        s2sUserAttachedFormFileList = new ArrayList<S2sUserAttachedFormFile>();
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    @Override
    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    @Override
    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    @Override
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

	public DevelopmentProposal getDevelopmentProposal() {
		return developmentProposal;
	}

	public void setDevelopmentProposal(DevelopmentProposal developmentProposal) {
		this.developmentProposal = developmentProposal;
	}

}