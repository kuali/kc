/*
 * Copyright 2005-2014 The Kuali Foundation.
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
package org.kuali.coeus.s2sgen.impl.budget;


public class KeyPersonDto extends CompensationDto {


    private int sortId = 0;
    private String personId = null;
    private Integer rolodexId = null;
    private String lastName = null;
    private String firstName = null;
    private String middleName = null;
    private String role = null;
    private String keyPersonRole = null;
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
     * @param sortId New value of property sortId.
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
     * @param middleName New value of property middleName.
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
     * Getter for property role.
     * 
     * @return Value of property role.
     */
    public java.lang.String getKeyPersonRole() {
        return keyPersonRole;
    }

    /**
     * Setter for property role.
     * 
     * @param keyPersonRole New value of property keyPersonRole.
     */
    public void setKeyPersonRole(java.lang.String keyPersonRole) {
        this.keyPersonRole = keyPersonRole;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (nonMITPersonFlag ? 1231 : 1237);
        result = prime * result + ((personId == null) ? 0 : personId.hashCode());
        result = prime * result + ((rolodexId == null) ? 0 : rolodexId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        KeyPersonDto other = (KeyPersonDto) obj;
        if (nonMITPersonFlag != other.nonMITPersonFlag)
            return false;
        if (personId == null) {
            if (other.personId != null)
                return false;
        }
        else if (!personId.equals(other.personId))
            return false;
        if (rolodexId == null) {
            if (other.rolodexId != null)
                return false;
        }
        else if (!rolodexId.equals(other.rolodexId))
            return false;
        return true;
    }
}
