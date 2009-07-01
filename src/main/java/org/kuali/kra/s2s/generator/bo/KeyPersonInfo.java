/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.s2s.generator.bo;


import java.util.LinkedHashMap;


public class KeyPersonInfo extends CompensationInfo{


    private int sortId = 0;
    private String personId = null;
    private Integer rolodexId = null;
    private String lastName = null;
    private String firstName = null;
    private String middleName = null;
//    private String prefix = null;
//    private String suffix = null;
    private String role = null;
    private boolean nonMITPersonFlag;

    /**
     * Getter for property sortId.
     * 
     * @return Value of property sortId.
     */
    public int getSortId() {
        return sortId;
    }

    /**
     * Setter for property sortId.
     * 
     * @param personId New value of property sortId.
     */
    public void setSortId(int sortId) {
        this.sortId = sortId;
    }

    /**
     * Getter for property personId.
     * 
     * @return Value of property personId.
     */
    public java.lang.String getPersonId() {
        return personId;
    }

    /**
     * Setter for property personId.
     * 
     * @param personId New value of property personId.
     */
    public void setPersonId(java.lang.String personId) {
        this.personId = personId;
    }


    /**
     * Getter for property lastName.
     * 
     * @return Value of property lastName.
     */
    public java.lang.String getLastName() {
        return lastName;
    }

    /**
     * Setter for property lastName.
     * 
     * @param lastName New value of property lastName.
     */
    public void setLastName(java.lang.String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter for property firstName.
     * 
     * @return Value of property firstName.
     */
    public java.lang.String getFirstName() {
        return firstName;
    }

    /**
     * Setter for property firstName.
     * 
     * @param firstName New value of property firstName.
     */
    public void setFirstName(java.lang.String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for property middleName.
     * 
     * @return Value of property middleName.
     */
    public java.lang.String getMiddleName() {
        return middleName;
    }

    /**
     * Setter for property name.
     * 
     * @param name New value of property name.
     */
    public void setMiddleName(java.lang.String middleName) {
        this.middleName = middleName;
    }


    /**
     * Getter for property role.
     * 
     * @return Value of property role.
     */
    public java.lang.String getRole() {
        return role;
    }

    /**
     * Setter for property role.
     * 
     * @param role New value of property role.
     */
    public void setRole(java.lang.String role) {
        this.role = role;
    }

    /**
     * Setter for nonMITPersonFlag.
     * 
     * @return nonMITPersonFlag
     */
    public boolean isNonMITPersonFlag() {
        return nonMITPersonFlag;
    }

    /**
     * Getter for nonMITPersonFlag
     * 
     * @param nonMITPersonFlag
     */
    public void setNonMITPersonFlag(boolean nonMITPersonFlag) {
        this.nonMITPersonFlag = nonMITPersonFlag;
    }


    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = new LinkedHashMap();
        hashMap.put("sortId", getSortId());
        hashMap.put("personId", getPersonId());
        hashMap.put("lastName", getLastName());
        hashMap.put("firstName", getFirstName());
        hashMap.put("middleName", getMiddleName());
        hashMap.put("role", getRole());
        hashMap.put("nonMITPersonFlag", isNonMITPersonFlag());
        return hashMap;
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
}
