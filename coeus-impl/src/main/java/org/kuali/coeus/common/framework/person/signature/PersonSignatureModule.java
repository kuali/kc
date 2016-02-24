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

import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

public class PersonSignatureModule extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = -3728654769919846123L;

    private Long personSignatureModuleId;
    private Long personSignatureId;
    private String moduleCode;
    private boolean defaultSignature;
    private boolean signatureActive;
    private PersonSignature personSignature;
    private CoeusModule coeusModule;
    
    public Long getPersonSignatureModuleId() {
        return personSignatureModuleId;
    }
    public void setPersonSignatureModuleId(Long personSignatureModuleId) {
        this.personSignatureModuleId = personSignatureModuleId;
    }
    public Long getPersonSignatureId() {
        return personSignatureId;
    }
    public void setPersonSignatureId(Long personSignatureId) {
        this.personSignatureId = personSignatureId;
    }
    public String getModuleCode() {
        return moduleCode;
    }
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }
    public boolean isDefaultSignature() {
        return defaultSignature;
    }
    public void setDefaultSignature(boolean defaultSignature) {
        this.defaultSignature = defaultSignature;
    }
    public boolean isSignatureActive() {
        return signatureActive;
    }
    public void setSignatureActive(boolean signatureActive) {
        this.signatureActive = signatureActive;
    }
    public PersonSignature getPersonSignature() {
        return personSignature;
    }
    public void setPersonSignature(PersonSignature personSignature) {
        this.personSignature = personSignature;
    }
    public CoeusModule getCoeusModule() {
        return coeusModule;
    }
    public void setCoeusModule(CoeusModule coeusModule) {
        this.coeusModule = coeusModule;
    }
    
}
