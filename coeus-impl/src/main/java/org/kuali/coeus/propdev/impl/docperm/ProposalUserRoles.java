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
package org.kuali.coeus.propdev.impl.docperm;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.*;

/**
 * A ProposalUserRoles corresponds to one user with access to a proposal. That user can have one or more assigned roles. This class
 * is only used by the GUI for displaying the proposal users on a web page.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProposalUserRoles implements Serializable {

    private String username = "";
    private String fullname = "";
    private String unitNumber = "";
    private String unitName = "";
    private List<String> roleNames;
    
    public ProposalUserRoles() {
        roleNames = new ArrayList<String>();
    }

    /**
     * Get the user's username.
     * 
     * @return the user's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * List the user's unique username.
     * 
     * @param username the user's username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the user's full name.
     * 
     * @return the user's full name.
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * List the user's full name.
     * 
     * @param fullname the user's full name
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * Get the user's home unit number.
     * 
     * @return the user's home unit number.
     */
    public String getUnitNumber() {
        return unitNumber;
    }

    /**
     * List the user's home unit number.
     * 
     * @param unitNumber the user's home unit number
     */
    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    /**
     * Get the user's home unit name.
     * 
     * @return the user's home unit name.
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * List the user's home unit name.
     * 
     * @param unitName the user's home unit name
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    /**
     * Get the user's role id in the proposal.
     * 
     * @return the user's role id in the proposal.
     */
    public List<String> getRoleNames() {
        if (roleNames == null){
            roleNames = new ArrayList<String>();
        }
        return roleNames;
    }

    /**
     * List the user's role id.
     * 
     * @param roleNames the user role names we want to keep
     */
    public void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames;
    }

    /**
     * Add a role name.
     * 
     * @param roleName the role name
     */
    public void addRoleName(String roleName) {
        roleNames.add(roleName);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if ((obj == null) || (obj.getClass() != this.getClass()))
            return false;

        ProposalUserRoles theOther = (ProposalUserRoles) obj;

        boolean isEqual = true;

        isEqual &= StringUtils.equals(this.fullname, theOther.fullname);
        isEqual &= StringUtils.equals(this.unitName, theOther.unitName);
        isEqual &= StringUtils.equals(this.unitNumber, theOther.unitNumber);
        isEqual &= StringUtils.equals(this.username, theOther.username);
        isEqual &= this.getRoleNames().size() == theOther.getRoleNames().size();

        if (isEqual) {
            int i = 0;
            for (String roleName : this.roleNames) {
                isEqual &= StringUtils.equals(roleName, theOther.roleNames.get(i));
                i++;
            }
        }
        return isEqual;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + ObjectUtils.hashCode(fullname);
        hash = 31 * hash + ObjectUtils.hashCode(unitName);
        hash = 31 * hash + ObjectUtils.hashCode(unitNumber);
        hash = 31 * hash + ObjectUtils.hashCode(username);
        for (String roleName : roleNames) {
            hash = 31 * hash + ObjectUtils.hashCode(roleName);
        }
        return hash;
    }
}
