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
package org.kuali.kra.proposaldevelopment.web.bean;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.infrastructure.RoleConstants;

/**
 * A ProposalUserRoles corresponds to one user with access to a
 * proposal.  That user can have one or more assigned roles.  This
 * class is only used by the GUI for displaying the proposal users
 * on a web page. 
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProposalUserRoles {

    private String username = "";
    private String fullname = "";
    private String unitNumber = "";
    private String unitName = "";
    private List<String> roleNames;

    /**
     * Constructs a ProposalUser.
     */
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
     * Set the user's unique username.
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
     * Set the user's full name.
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
     * Set the user's home unit number.
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
     * Set the user's home unit name.
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
        return roleNames;
    }

    /**
     * Set the user's role id.
     * 
     * @param roleId the user's role id
     */
    public void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames;
    }
    
    /**
     * Add a role name.
     * @param roleName the role name
     */
    public void addRoleName(String roleName) {
        this.roleNames.add(roleName);
    }

    /**
     * Get the user's role labels in the proposal.
     * 
     * @return the user's role labels in the proposal.
     */
    public List<String> getRoleLabels() {
        List<String> labels = new ArrayList<String>();
        for (String roleName : roleNames) {
            if (RoleConstants.UNASSIGNED.equals(roleName)) {
                labels.add(RoleConstants.UNASSIGNED_LABEL);
            }
            else if (RoleConstants.AGGREGATOR.equals(roleName)) {
                labels.add(RoleConstants.AGGREGATOR_LABEL);
            }
            else if (RoleConstants.BUDGET_CREATOR.equals(roleName)) {
                labels.add(RoleConstants.BUDGET_CREATOR_LABEL);
            }
            else if (RoleConstants.NARRATIVE_WRITER.equals(roleName)) {
                labels.add(RoleConstants.NARRATIVE_WRITER_LABEL);
            }
            else if (RoleConstants.VIEWER.equals(roleName)) {
                labels.add(RoleConstants.VIEWER_LABEL);
            }
        }
        return labels;
    }
}
