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


/**
 * This class defines an Award contact role
 */

public interface ContactRole {
    String PI_CODE = "PI";
    String COI_CODE = "COI";
    String KEY_PERSON_CODE = "KP";
        
    String getRoleCode();
    String getRoleDescription();
    
//    ContactCategory getContactCategory();
}

// public class AwardContactRole extends KraPersistableBusinessObjectBase implements Comparable<AwardContactRole> {
//    public static final String COI_CODE = "997";
//    public static final String KEY_PERSON_CODE = "998";
//    public static final String PI_CODE = "999";
//    
//    private static final int COI_ORDER = 2;
//    private static final int KEY_PERSON_ORDER = 3;
//    private static final int PI_ORDER = 1;
//    
//    private static final long serialVersionUID = 5858998813645714513L;
//    
//    private Long awardContactRoleId;
//
//    private ContactCategory contactCategory;
//
//    private ContactType contactType;
//
//    public AwardContactRole() {
//        super();
//    }
//    AwardContactRole(ContactType contactType, ContactCategory contactCategory) {
//        this.contactType = contactType;
//        this.contactCategory = contactCategory; 
//    }
//    
//    public int compareTo(AwardContactRole other) {
//        if(other == null) { return -1; }
// 
//        Map<String, Integer> relativeValues = new HashMap<String, Integer>();
//        relativeValues.put(PI_CODE, PI_ORDER);
//        relativeValues.put(COI_CODE, COI_ORDER);
//        relativeValues.put(KEY_PERSON_CODE, KEY_PERSON_ORDER);
//        
//        return relativeValues.get(getContactRoleCode()).compareTo(relativeValues.get(other.getContactRoleCode()));       
//    }
//    
//    /**
//     * @see java.lang.Object#equals(java.lang.Object)
//     */
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (!(obj instanceof AwardContactRole)) {
//            return false;
//        }
//        AwardContactRole other = (AwardContactRole) obj;
//        if (contactType == null) {
//            if (other.contactType != null) {
//                return false;
//            }
//        } else if (!contactType.equals(other.contactType)) {
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * Gets the awardContactRoleId attribute. 
//     * @return Returns the awardContactRoleId.
//     */
//    public Long getAwardContactRoleId() {
//        return awardContactRoleId;
//    }
//
//    /**
//     * Gets the contactCategory attribute. 
//     * @return Returns the contactCategory.
//     */
//    public ContactCategory getContactCategory() {
//        return contactCategory;
//    }
//
//    /**
//     * Gets the contactCategory name
//     * @return
//     */
//    public String getContactCategoryCode() {
//        return contactCategory.name();
//    }
//
//    /**
//     * Gets the contactRoleCode attribute. 
//     * @return Returns the contactRoleCode.
//     */
//    public String getContactRoleCode() {
//        return contactType.getContactTypeCode();
//    }
//
//    /**
//     * Gets the contactType attribute. 
//     * @return Returns the contactType.
//     */
//    public ContactType getContactType() {
//        return contactType;
//    }
//
//    /**
//     * Gets the description attribute. 
//     * @return Returns the description.
//     */
//    public String getDescription() {
//        return contactType.getDescription();
//    }
//
//    /**
//     * @see java.lang.Object#hashCode()
//     */
//    @Override
//    public int hashCode() {
//        final int PRIME = 31;
//        int result = 1;
//        result = PRIME * result + ((getContactRoleCode() == null) ? 0 : getContactRoleCode().hashCode());
//        return result;
//    }
//    
//    /**
//     * This method determines if person is the Principal Investigator
//     * @return
//     */
//    public boolean isPrincipalInvestigator() {
//        return getContactCategoryCode() != null && getContactRoleCode().equals(PI_CODE);
//    }
//    
//    /**
//     * Sets the awardContactRoleId attribute value.
//     * @param awardContactRoleId The awardContactRoleId to set.
//     */
//    public void setAwardContactRoleId(Long awardContactRoleId) {
//        this.awardContactRoleId = awardContactRoleId;
//    }
//    
//    /**
//     * Sets the contactCategory attribute value.
//     * @param contactCategory The contactCategory to set.
//     */
//    public void setContactCategory(ContactCategory contactCategory) {
//        this.contactCategory = contactCategory;
//    }
//
//    public void setContactCategoryCode(String contactCategoryName) {
//        setContactCategory(ContactCategory.valueOf(contactCategoryName));
//    }
//
//    /**
//     * Sets the contactRoleCode attribute value.
//     * @param contactRoleCode The contactRoleCode to set.
//     */
//    public void setContactRoleCode(String contactRoleCode) {
//        contactType.setContactTypeCode(contactRoleCode);
//    }
//    
//    /**
//     * Sets the contactType attribute value.
//     * @param contactType The contactType to set.
//     */
//    public void setContactType(ContactType contactType) {
//        this.contactType = contactType;
//    }
//
//    /**
//     * Sets the description attribute value.
//     * @param description The description to set.
//     */
//    public void setDescription(String description) {
//        contactType.setDescription(description);
//    }
//
//    @Override
//    protected LinkedHashMap<String, Object> toStringMapper() {
//        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
//        map.put("contactType", contactType);
//        map.put("contactCategory", contactCategory.name());
//        return map;
//    }
//}
