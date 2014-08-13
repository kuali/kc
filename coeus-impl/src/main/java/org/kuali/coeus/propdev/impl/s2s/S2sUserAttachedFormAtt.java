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

import javax.persistence.*;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.propdev.api.s2s.S2sUserAttachedFormAttContract;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "S2S_USER_ATTACHED_FORM_ATT")
public class S2sUserAttachedFormAtt extends KcPersistableBusinessObjectBase implements S2sUserAttachedFormAttContract {
    
    private static final long serialVersionUID = 1L;

    @PortableSequenceGenerator(name = "SEQ_S2S_USER_ATTD_FORM_ATT_ID")
    @GeneratedValue(generator = "SEQ_S2S_USER_ATTD_FORM_ATT_ID")
    @Id
    @Column(name = "S2S_USER_ATTACHED_FORM_ATT_ID")
    private Long id;
    
    @Column(name = "S2S_USER_ATTACHED_FORM_ID")
    private Long s2sUserAttachedFormId;
    
    @Column(name = "PROPOSAL_NUMBER")
    private String proposalNumber;
    
    @Column(name = "CONTENT_TYPE")
    private String type;
    
    @Column(name = "FILE_NAME")
    private String name;
    
    @Column(name = "CONTENT_ID")
    private String contentId;
    
    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "S2S_USER_ATTACHED_FORM_ID", insertable = false, updatable = false)
    private S2sUserAttachedForm s2sUserAttachedForm;

    @OneToMany(cascade = { CascadeType.ALL })
    @JoinColumn(name = "S2S_USER_ATTACHED_FORM_ATT_ID", referencedColumnName = "S2S_USER_ATTACHED_FORM_ATT_ID")
    private List<S2sUserAttachedFormAttFile> s2sUserAttachedFormAttFiles;

    public S2sUserAttachedFormAtt() {
        s2sUserAttachedFormAttFiles = new ArrayList<S2sUserAttachedFormAttFile>();
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getS2sUserAttachedFormId() {
        return s2sUserAttachedFormId;
    }

    public void setS2sUserAttachedFormId(Long s2sUserAttachedFormId) {
        this.s2sUserAttachedFormId = s2sUserAttachedFormId;
    }

    @Override
    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public S2sUserAttachedForm getS2sUserAttachedForm() {
        return s2sUserAttachedForm;
    }

    @Override
    public List<S2sUserAttachedFormAttFile> getS2sUserAttachedFormAttFiles() {
        return s2sUserAttachedFormAttFiles;
    }

    public void setS2sUserAttachedFormAttFiles(List<S2sUserAttachedFormAttFile> s2sUserAttachedFormAttFiles) {
        this.s2sUserAttachedFormAttFiles = s2sUserAttachedFormAttFiles;
    }

	@Override
	public byte[] getData() {
		return getS2sUserAttachedFormAttFiles().get(0).getAttachment();
	}

    
}