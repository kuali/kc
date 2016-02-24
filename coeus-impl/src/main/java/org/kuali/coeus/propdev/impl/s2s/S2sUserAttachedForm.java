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
package org.kuali.coeus.propdev.impl.s2s;

import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.propdev.api.s2s.S2sUserAttachedFormContract;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;
import org.springframework.web.multipart.MultipartFile;

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

    @OneToMany(mappedBy="s2sUserAttachedForm", orphanRemoval = true, cascade = { CascadeType.ALL })
    private List<S2sUserAttachedFormAtt> s2sUserAttachedFormAtts;

    @OneToMany(mappedBy="s2sUserAttachedForm", orphanRemoval = true, cascade = { CascadeType.ALL })
    private List<S2sUserAttachedFormFile> s2sUserAttachedFormFileList;

    @Transient
    private transient boolean edit = false;
    
    @Transient
    private transient MultipartFile newFormFile;

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
    public MultipartFile getNewFormFile() {
        return newFormFile;
    }

    /**
     * Sets the newFormFile attribute value.
     * @param newFormFile The newFormFile to set.
     */
    public void setNewFormFile(MultipartFile newFormFile) {
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
