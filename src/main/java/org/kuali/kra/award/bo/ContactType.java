/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kuali.kra.award.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * 
 * This class represents the ContactType business object and is mapped
 * with CONTACT_TYPE table.
 */
public class ContactType extends KraPersistableBusinessObjectBase { 
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 8720276596982712409L;
    private String contactTypeCode; 
    private String description; 
    
    /*private SubcontractContact subcontractContact; 
    private TemplateReportTerms templateReportTerms; 
    private TemplateContact templateContact; 
    private AwardContact awardContact; 
    private AwardReportTerms awardReportTerms;*/ 
    
    /**
     * Constructs a ContactType.java
     */
    public ContactType() { 

    } 
    
    /**
     * 
     * This method...
     * @return
     */
    public String getContactTypeCode() {
        return contactTypeCode;
    }

    /**
     * 
     * @param contactTypeCode
     */
    public void setContactTypeCode(String contactTypeCode) {
        this.contactTypeCode = contactTypeCode;
    }

    /**
     * 
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /*public SubcontractContact getSubcontractContact() {
        return subcontractContact;
    }

    public void setSubcontractContact(SubcontractContact subcontractContact) {
        this.subcontractContact = subcontractContact;
    }

    public TemplateReportTerms getTemplateReportTerms() {
        return templateReportTerms;
    }

    public void setTemplateReportTerms(TemplateReportTerms templateReportTerms) {
        this.templateReportTerms = templateReportTerms;
    }

    public TemplateContact getTemplateContact() {
        return templateContact;
    }

    public void setTemplateContact(TemplateContact templateContact) {
        this.templateContact = templateContact;
    }

    public AwardContact getAwardContact() {
        return awardContact;
    }

    public void setAwardContact(AwardContact awardContact) {
        this.awardContact = awardContact;
    }

    public AwardReportTerms getAwardReportTerms() {
        return awardReportTerms;
    }

    public void setAwardReportTerms(AwardReportTerms awardReportTerms) {
        this.awardReportTerms = awardReportTerms;
    }*/

    /**
     * 
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("contactTypeCode", getContactTypeCode());
        hashMap.put("description", getDescription());
        return hashMap;
    }
    
}