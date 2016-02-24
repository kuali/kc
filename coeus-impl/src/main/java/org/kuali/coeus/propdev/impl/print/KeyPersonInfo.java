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
package org.kuali.coeus.propdev.impl.print;


public class KeyPersonInfo extends CompensationInfo {


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
    public String getPersonId() {
        return personId;
    }

    /**
     * Setter for property personId.
     *
     * @param personId New value of property personId.
     */
    public void setPersonId(String personId) {
        this.personId = personId;
    }


    /**
     * Getter for property lastName.
     *
     * @return Value of property lastName.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for property lastName.
     *
     * @param lastName New value of property lastName.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter for property firstName.
     *
     * @return Value of property firstName.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for property firstName.
     *
     * @param firstName New value of property firstName.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for property middleName.
     *
     * @return Value of property middleName.
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Setter for property name.
     *
     * @param middleName New value of property middleName.
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }


    /**
     * Getter for property role.
     *
     * @return Value of property role.
     */
    public String getRole() {
        return role;
    }

    /**
     * Setter for property role.
     *
     * @param role New value of property role.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Getter for property role.
     *
     * @return Value of property role.
     */
    public String getKeyPersonRole() {
        return keyPersonRole;
    }

    /**
     * Setter for property role.
     *
     * @param keyPersonRole New value of property keyPersonRole.
     */
    public void setKeyPersonRole(String keyPersonRole) {
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
        KeyPersonInfo other = (KeyPersonInfo) obj;
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
