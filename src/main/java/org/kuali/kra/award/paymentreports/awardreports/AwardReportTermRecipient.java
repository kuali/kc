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

package org.kuali.kra.award.paymentreports.awardreports;

import java.util.LinkedHashMap;

import org.kuali.kra.award.bo.AwardSyncable;
import org.kuali.kra.award.bo.ContactType;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Rolodex;

/**
 * 
 * This class represents the AwardReportTermRecipient business object
 * 
 */
public class AwardReportTermRecipient extends KraPersistableBusinessObjectBase {
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -3941499915900100395L;
    private Long awardReportTermRecipientId;
    private Long awardReportTermId;
    private Long contactId;
    
    @AwardSyncable private String contactTypeCode; 
    @AwardSyncable private Integer rolodexId;
    @AwardSyncable private Integer numberOfCopies; 
    
    private ContactType contactType;
    private Rolodex rolodex; 
    private AwardReportTerm awardReportTerm; 
    
    /**
     * 
     * Constructs a AwardReportTerm.java.
     */
    public AwardReportTermRecipient() { 

    } 
    
    /**
     * 
     * @return
     */
    public Long getAwardReportTermId() {
        return awardReportTermId;
    }

    /**
     * 
     * @param awardReportTermId
     */
    public void setAwardReportTermId(Long awardReportTermId) {
        this.awardReportTermId = awardReportTermId;
    }

    /**
     * 
     * @see org.kuali.rice.kns.bo.BusinessObjectBase#toStringMapper()
     */
    @SuppressWarnings("unchecked")
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap();
        hashMap.put("awardReportTermRecipientId", getAwardReportTermRecipientId());
        hashMap.put("awardReportTermId", getAwardReportTermId());
        hashMap.put("contactId", getContactId());
        hashMap.put("contactTypeCode", getContactTypeCode());
        hashMap.put("rolodexId", getRolodexId());
        hashMap.put("numberOfCopies", getNumberOfCopies());
        return hashMap;
    }

    /**
     *
     * @return
     */
    public Long getAwardReportTermRecipientId() {
        return awardReportTermRecipientId;
    }

    /**
     *
     * @param awardReportTermRecipientId
     */
    public void setAwardReportTermRecipientId(Long awardReportTermRecipientId) {
        this.awardReportTermRecipientId = awardReportTermRecipientId;
    }

    /**
     *
     * @return
     */
    public AwardReportTerm getAwardReportTerm() {
        return awardReportTerm;
    }

    /**
     *
     * @param awardReportTerm
     */
    public void setAwardReportTerm(AwardReportTerm awardReportTerm) {
        this.awardReportTerm = awardReportTerm;
    }

    /**
     *
     * @return
     */
    public Long getContactId() {
        return contactId;
    }

    /**
     *
     * @param contactId
     */
    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    /**
     * Gets the contactTypeCode attribute. 
     * @return Returns the contactTypeCode.
     */
    public String getContactTypeCode() {
        return contactTypeCode;
    }

    /**
     * Sets the contactTypeCode attribute value.
     * @param contactTypeCode The contactTypeCode to set.
     */
    public void setContactTypeCode(String contactTypeCode) {
        this.contactTypeCode = contactTypeCode;
    }

    /**
     * Gets the rolodexId attribute. 
     * @return Returns the rolodexId.
     */
    public Integer getRolodexId() {
        return rolodexId;
    }

    /**
     * Sets the rolodexId attribute value.
     * @param rolodexId The rolodexId to set.
     */
    public void setRolodexId(Integer rolodexId) {
        this.rolodexId = rolodexId;
    }

    /**
     * Gets the numberOfCopies attribute. 
     * @return Returns the numberOfCopies.
     */
    public Integer getNumberOfCopies() {
        return numberOfCopies;
    }

    /**
     * Sets the numberOfCopies attribute value.
     * @param numberOfCopies The numberOfCopies to set.
     */
    public void setNumberOfCopies(Integer numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    /**
     * Gets the contactType attribute. 
     * @return Returns the contactType.
     */
    public ContactType getContactType() {
        return contactType;
    }

    /**
     * Sets the contactType attribute value.
     * @param contactType The contactType to set.
     */
    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    /**
     * Gets the rolodex attribute. 
     * @return Returns the rolodex.
     */
    public Rolodex getRolodex() {
        return rolodex;
    }

    /**
     * Sets the rolodex attribute value.
     * @param rolodex The rolodex to set.
     */
    public void setRolodex(Rolodex rolodex) {
        this.rolodex = rolodex;
    }

    

    
   
    
}