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
