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

import org.kuali.kra.award.contacts.AwardSponsorContact;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Rolodex;

/**
 * This class...
 */
@SuppressWarnings("serial")
public abstract class AwardSponsorContactBase extends KraPersistableBusinessObjectBase implements AwardSynchronizable {
    private String contactTypeCode; 
    private Integer rolodexId; 
    
    private ContactType contactType; 
    private Rolodex rolodex;
    /**
     * 
     * @see org.kuali.kra.award.bo.AwardSynchronizable#getSyncBaseClass()
     */
    public Class getSyncBaseClass() {
        return AwardSponsorContactBase.class;
    }
    /**
     * 
     * @see org.kuali.kra.award.bo.AwardSynchronizable#getAwardSyncClass()
     */
    public Class getAwardSyncClass() {
        return AwardSponsorContact.class;
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
    @SuppressWarnings("unchecked")
    @Override 
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = new LinkedHashMap();
        hashMap.put("contactTypeCode", getContactTypeCode());
        hashMap.put("rolodexId", getRolodexId());
        return hashMap;
    }

}
