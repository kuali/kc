/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.bo;

import org.apache.struts.upload.FormFile;
import org.kuali.rice.krad.bo.PersistableAttachment;
import org.kuali.rice.krad.util.ObjectUtils;

public class PersonSignature extends KraPersistableBusinessObjectBase implements PersistableAttachment {

    private static final long serialVersionUID = 1303059340811449915L;

    private Long personSignatureId;
    private String personId;
    private boolean defaultAdminSignature;
    private boolean signatureActive;
    private byte[] attachmentContent;

    private transient FormFile templateFile;
    private String fileName;
    private String contentType;
    
    public Long getPersonSignatureId() {
        return personSignatureId;
    }
    public void setPersonSignatureId(Long personSignatureId) {
        this.personSignatureId = personSignatureId;
    }
    public String getPersonId() {
        return personId;
    }
    public void setPersonId(String personId) {
        this.personId = personId;
    }
    public boolean isDefaultAdminSignature() {
        return defaultAdminSignature;
    }
    public void setDefaultAdminSignature(boolean defaultAdminSignature) {
        this.defaultAdminSignature = defaultAdminSignature;
    }
    public boolean isSignatureActive() {
        return signatureActive;
    }
    public void setSignatureActive(boolean signatureActive) {
        this.signatureActive = signatureActive;
    }
    public FormFile getTemplateFile() {
        return templateFile;
    }
    public void setTemplateFile(FormFile templateFile) {
        this.templateFile = templateFile;
    }
    @Override
    public byte[] getAttachmentContent() {
        return this.attachmentContent;
    }
    @Override
    public void setAttachmentContent(byte[] attachmentContent) {
        this.attachmentContent = attachmentContent;
    }
    @Override
    public String getFileName() {
        return fileName;
    }
    @Override
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    @Override
    public String getContentType() {
        return contentType;
    }
    @Override
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    
    public Unit getUnit() {
        return getPerson().getUnit();
    }

    public KcPerson getPerson() {
        return getKcPersonService().getKcPersonByPersonId(personId);
    }

}
