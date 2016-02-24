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
