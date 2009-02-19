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
    private Integer awardReportTermId;
    private Long contactId;
    private String contactTypeCode; 
    private Integer rolodexId;
    private Integer numberOfCopies; 
    
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
    public Integer getAwardReportTermId() {
        return awardReportTermId;
    }

    /**
     * 
     * @param awardReportTermId
     */
    public void setAwardReportTermId(Integer awardReportTermId) {
        this.awardReportTermId = awardReportTermId;
    }

    /**
     *
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
    public Integer getRolodexId() {
        return rolodexId;
    }

    /**
     *
     * @param rolodexId
     */
    public void setRolodexId(Integer rolodexId) {
        this.rolodexId = rolodexId;
    }

    /**
     *
     * @return
     */
    public Integer getNumberOfCopies() {
        return numberOfCopies;
    }

    /**
     *
     * @param numberOfCopies
     */
    public void setNumberOfCopies(Integer numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    /**
     *
     * @return
     */
    public ContactType getContactType() {
        return contactType;
    }

    /**
     *
     * @param contactType
     */
    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    /**
     *
     * @return
     */
    public Rolodex getRolodex() {
        return rolodex;
    }

    /**
     *
     * @param rolodex
     */
    public void setRolodex(Rolodex rolodex) {
        this.rolodex = rolodex;
    }
    
    /**
     * 
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
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
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((contactId == null) ? 0 : contactId.hashCode());
        result = PRIME * result + ((contactTypeCode == null) ? 0 : contactTypeCode.hashCode());
        result = PRIME * result + ((numberOfCopies == null) ? 0 : numberOfCopies.hashCode());
        result = PRIME * result + ((rolodexId == null) ? 0 : rolodexId.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }   
        if (obj == null){
            return false;
        }   
        if (!(obj instanceof AwardReportTermRecipient)){
            return false;
        }           
        return equals((AwardReportTermRecipient) obj);
    }
    
    /**
     * 
     * Convenience method to check equality of another AwardFandaRate
     * @param awardReportTermRecipient
     * @return
     */
    public boolean equals(AwardReportTermRecipient awardReportTermRecipient){
        if (contactId == null) {
            if (awardReportTermRecipient.contactId != null){
                return false;
            }
                
        }else if (!contactId.equals(awardReportTermRecipient.contactId)){
            return false;
        }   
        if (contactTypeCode == null) {
            if (awardReportTermRecipient.contactTypeCode != null){
                return false;
            }   
        }else if (!contactTypeCode.equals(awardReportTermRecipient.contactTypeCode)){
            return false;
        }   
        if (numberOfCopies == null) {
            if (awardReportTermRecipient.numberOfCopies != null){
                return false;
            }   
        }else if (!numberOfCopies.equals(awardReportTermRecipient.numberOfCopies)){
            return false;
        }   
        if (rolodexId == null) {
            if (awardReportTermRecipient.rolodexId != null){
                return false;
            }   
        }else if (!rolodexId.equals(awardReportTermRecipient.rolodexId)){
            return false;
        }   
        return true;
    }
    
    
    
}