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
