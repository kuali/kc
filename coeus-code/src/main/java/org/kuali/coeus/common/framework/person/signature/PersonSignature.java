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
package org.kuali.coeus.common.framework.person.signature;

import org.apache.struts.upload.FormFile;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.krad.bo.PersistableAttachment;

import java.util.ArrayList;
import java.util.List;

public class PersonSignature extends KcPersistableBusinessObjectBase implements PersistableAttachment {

    private static final long serialVersionUID = 1303059340811449915L;

    private Long personSignatureId;
    private String personId;
    private boolean signatureActive;
    private byte[] attachmentContent;

    private transient FormFile templateFile;
    private String fileName;
    private String contentType;


    private transient KcPersonService kcPersonService;

    private List<PersonSignatureModule> personSignatureModules = new ArrayList<PersonSignatureModule>();
    
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
    public List<PersonSignatureModule> getPersonSignatureModules() {
        return personSignatureModules;
    }
    public void setPersonSignatureModules(List<PersonSignatureModule> personSignatureModules) {
        this.personSignatureModules = personSignatureModules;
    }


    /**
     * Looks up and returns the KcPersonService.
     * @return the person service.
     */
    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }
        return this.kcPersonService;
    }
}
