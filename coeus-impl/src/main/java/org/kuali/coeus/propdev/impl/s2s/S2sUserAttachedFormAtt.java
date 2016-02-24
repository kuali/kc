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
    
    @Column(name = "PROPOSAL_NUMBER")
    private String proposalNumber;
    
    @Column(name = "CONTENT_TYPE")
    private String type;
    
    @Column(name = "FILE_NAME")
    private String name;
    
    @Column(name = "CONTENT_ID")
    private String contentId;
    
    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "S2S_USER_ATTACHED_FORM_ID")
    private S2sUserAttachedForm s2sUserAttachedForm;

    @OneToMany(mappedBy="s2sUserAttachedFormAtt", orphanRemoval = true, cascade = { CascadeType.ALL })
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
        return s2sUserAttachedForm != null ? s2sUserAttachedForm.getId() : null;
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

    public void setS2sUserAttachedForm(S2sUserAttachedForm s2sUserAttachedForm) {
        this.s2sUserAttachedForm = s2sUserAttachedForm;
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
