/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.framework.person.attr;

import org.apache.struts.upload.FormFile;
import org.kuali.coeus.sys.api.model.KcFile;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.krad.bo.PersistableAttachment;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

import javax.persistence.*;

@Entity
@Table(name = "PERSON_BIOSKETCH")
public class PersonBiosketch extends KcPersistableBusinessObjectBase implements PersistableAttachment, KcFile {

    private static final long serialVersionUID = 6206100185207514370L;

    @PortableSequenceGenerator(name = "SEQ_PERSON_BIOSKETCH_ID")
    @GeneratedValue(generator = "SEQ_PERSON_BIOSKETCH_ID")
    @Id
    @Column(name = "PERSON_BIOSKETCH_ID")
    private Long personBiosketchId;

    @Column(name = "PERSON_ID")
    private String personId;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "CONTENT_TYPE")
    private String contentType;

    @Column(name = "ATTACHMENT_FILE")
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] attachmentContent;

    @Transient
    private transient FormFile attachmentFile;
    
    public Long getPersonBiosketchId() {
        return personBiosketchId;
    }
    
    public void setPersonBiosketchId(Long personBiosketchId) {
        this.personBiosketchId = personBiosketchId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getFileName() {
        return fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public String getContentType() {
        return contentType;
    }
    
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    
    public byte[] getAttachmentContent() {
        return attachmentContent;
    }
    
    public void setAttachmentContent(byte[] attachmentContent) {
        this.attachmentContent = attachmentContent;
    }
    
    public FormFile getAttachmentFile() {
        return attachmentFile;
    }
    
    public void setAttachmentFile(FormFile attachmentFile) {
        this.attachmentFile = attachmentFile;
    }

    @Override
    public String getName() {
        return getFileName();
    }

    @Override
    public String getType() {
        return getContentType();
    }

    @Override
    public byte[] getData() {
        return getAttachmentContent();
    }
}