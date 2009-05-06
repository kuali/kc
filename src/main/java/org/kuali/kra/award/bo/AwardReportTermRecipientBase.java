/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.award.bo.ContactType;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Rolodex;

@SuppressWarnings("serial")
public abstract class AwardReportTermRecipientBase extends KraPersistableBusinessObjectBase {

    private String contactTypeCode; 
    private Integer rolodexId;
    private Integer numberOfCopies; 
    
    private ContactType contactType;
    private Rolodex rolodex; 

    @SuppressWarnings("unchecked")
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("contactTypeCode", getContactTypeCode());
        hashMap.put("rolodexId", getRolodexId());
        hashMap.put("numberOfCopies", getNumberOfCopies());
        return hashMap;
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

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
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
        if (!(obj instanceof AwardReportTermRecipientBase)){
            return false;
        }   
        final AwardReportTermRecipientBase other = (AwardReportTermRecipientBase) obj;
        return equals(other);
    }

    /**
     * This method...
     * @param other
     * @return
     */
    private boolean equals(final AwardReportTermRecipientBase other) {
        if (contactTypeCode == null) {
            if (other.contactTypeCode != null){
                return false;
            }   
        }else if (!contactTypeCode.equals(other.contactTypeCode)){
            return false;
        }   
        if (numberOfCopies == null) {
            if (other.numberOfCopies != null){
                return false;
            }   
        }else if (!numberOfCopies.equals(other.numberOfCopies)){
            return false;
        }   
        if (rolodexId == null) {
            if (other.rolodexId != null){
                return false;
            }   
        }else if (!rolodexId.equals(other.rolodexId)){
            return false;
        }   
        return true;
    }
    
    

}
