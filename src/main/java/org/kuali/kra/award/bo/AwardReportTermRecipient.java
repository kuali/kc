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

/**
 * 
 * This class represents the AwardReportTermRecipient business object
 * 
 */
public class AwardReportTermRecipient extends AwardReportTermRecipientBase {
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -3941499915900100395L;
    private Long awardReportTermRecipientId;
    private Integer awardReportTermId;
    private Long contactId;
//    private String contactTypeCode; 
//    private Integer rolodexId;
//    private Integer numberOfCopies; 
//    
//    private ContactType contactType;
//    private Rolodex rolodex; 
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

//    /**
//     *
//     * @return
//     */
//    public String getContactTypeCode() {
//        return contactTypeCode;
//    }
//
//    /**
//     *
//     * @param contactTypeCode
//     */
//    public void setContactTypeCode(String contactTypeCode) {
//        this.contactTypeCode = contactTypeCode;
//    }
//
//    /**
//     *
//     * @return
//     */
//    public Integer getRolodexId() {
//        return rolodexId;
//    }
//
//    /**
//     *
//     * @param rolodexId
//     */
//    public void setRolodexId(Integer rolodexId) {
//        this.rolodexId = rolodexId;
//    }
//
//    /**
//     *
//     * @return
//     */
//    public Integer getNumberOfCopies() {
//        return numberOfCopies;
//    }
//
//    /**
//     *
//     * @param numberOfCopies
//     */
//    public void setNumberOfCopies(Integer numberOfCopies) {
//        this.numberOfCopies = numberOfCopies;
//    }
//
//    /**
//     *
//     * @return
//     */
//    public ContactType getContactType() {
//        return contactType;
//    }
//
//    /**
//     *
//     * @param contactType
//     */
//    public void setContactType(ContactType contactType) {
//        this.contactType = contactType;
//    }
//
//    /**
//     *
//     * @return
//     */
//    public Rolodex getRolodex() {
//        return rolodex;
//    }
//
//    /**
//     *
//     * @param rolodex
//     */
//    public void setRolodex(Rolodex rolodex) {
//        this.rolodex = rolodex;
//    }
//    
    /**
     * 
     * @see org.kuali.rice.kns.bo.BusinessObjectBase#toStringMapper()
     */
    @SuppressWarnings("unchecked")
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = super.toStringMapper();
        hashMap.put("awardReportTermRecipientId", getAwardReportTermRecipientId());
        hashMap.put("awardReportTermId", getAwardReportTermId());
//        hashMap.put("contactId", getContactId());
//        hashMap.put("contactTypeCode", getContactTypeCode());
//        hashMap.put("rolodexId", getRolodexId());
//        hashMap.put("numberOfCopies", getNumberOfCopies());
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
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((awardReportTerm == null) ? 0 : awardReportTerm.hashCode());
        result = prime * result + ((awardReportTermId == null) ? 0 : awardReportTermId.hashCode());
        result = prime * result + ((awardReportTermRecipientId == null) ? 0 : awardReportTermRecipientId.hashCode());
        result = prime * result + ((contactId == null) ? 0 : contactId.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        AwardReportTermRecipient other = (AwardReportTermRecipient) obj;
        if (awardReportTerm == null) {
            if (other.awardReportTerm != null)
                return false;
        }
        else if (!awardReportTerm.equals(other.awardReportTerm))
            return false;
        if (awardReportTermId == null) {
            if (other.awardReportTermId != null)
                return false;
        }
        else if (!awardReportTermId.equals(other.awardReportTermId))
            return false;
        if (awardReportTermRecipientId == null) {
            if (other.awardReportTermRecipientId != null)
                return false;
        }
        else if (!awardReportTermRecipientId.equals(other.awardReportTermRecipientId))
            return false;
        if (contactId == null) {
            if (other.contactId != null)
                return false;
        }
        else if (!contactId.equals(other.contactId))
            return false;
        return true;
    }

   
    
}