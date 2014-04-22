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
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

@Entity
@Table(name = "S2S_USER_ATTACHED_FORM_ATT")
public class S2sUserAttachedFormAtt extends KcPersistableBusinessObjectBase { 
    
    private static final long serialVersionUID = 1L;

    @PortableSequenceGenerator(name = "SEQ_S2S_USER_ATTD_FORM_ATT_ID")
    @GeneratedValue(generator = "SEQ_S2S_USER_ATTD_FORM_ATT_ID")
    @Id
    @Column(name = "S2S_USER_ATTACHED_FORM_ATT_ID")
    private Long s2sUserAttachedFormAttId; 
    
    @Column(name = "S2S_USER_ATTACHED_FORM_ID")
    private Long s2sUserAttachedFormId;
    
    @Column(name = "PROPOSAL_NUMBER")
    private String proposalNumber;
    
    @Column(name = "CONTENT_TYPE")
    private String contentType;
    
    @Column(name = "FILE_NAME")
    private String fileName;
    
    @Column(name = "CONTENT_ID")
    private String contentId;
    
    @Column(name = "ATTACHMENT")
    private byte[] attachment; 
    
    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "S2S_USER_ATTACHED_FORM_ID", insertable = false, updatable = false)
    private S2sUserAttachedForm s2sUserAttachedForm; 
    
    public S2sUserAttachedFormAtt() { 

    } 
    
    public Long getS2sUserAttachedFormAttId() {
        return s2sUserAttachedFormAttId;
    }

    public void setS2sUserAttachedFormAttId(Long s2sUserAttachedFormAttId) {
        this.s2sUserAttachedFormAttId = s2sUserAttachedFormAttId;
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

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    public S2sUserAttachedForm getS2sUserAttachedForm() {
        return s2sUserAttachedForm;
    }

    public void setS2sUserAttachedForm(S2sUserAttachedForm s2sUserAttachedForm) {
        this.s2sUserAttachedForm = s2sUserAttachedForm;
    }
//    @Override
//    protected void postPersist() {
//        super.postPersist();
//        setAttachment(null);
//    }
    
}