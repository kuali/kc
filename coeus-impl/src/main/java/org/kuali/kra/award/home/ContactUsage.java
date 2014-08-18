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
package org.kuali.kra.award.home;

import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;


/**
 * This class represents the ContactUsage business object and is mapped
 * to the CONTACT_USAGE table.
 */
public class ContactUsage  extends KcPersistableBusinessObjectBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 2198994554339151877L;
    
    private Long contactUsageId;
    private String contactTypeCode;    
    private String moduleCode;
    
    private ContactType contactType;
    private CoeusModule coeusModule;
    
    
    public ContactUsage() {
    }

    public ContactUsage(String contactTypeCode, String moduleCode) {
        this.contactTypeCode = contactTypeCode;
        this.moduleCode = moduleCode;
    }

    public Long getContactUsageId() {
        return contactUsageId;
    }

    public void setContactUsageId(Long contactUsageId) {
        this.contactUsageId = contactUsageId;
    }

    public String getContactTypeCode() {
        return contactTypeCode;
    }

    public void setContactTypeCode(String contactTypeCode) {
        this.contactTypeCode = contactTypeCode;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    public CoeusModule getCoeusModule() {
        return coeusModule;
    }

    public void setCoeusModule(CoeusModule coeusModule) {
        this.coeusModule = coeusModule;
    }
    
}
