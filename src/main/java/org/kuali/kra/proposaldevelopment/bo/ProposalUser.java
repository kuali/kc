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
package org.kuali.kra.proposaldevelopment.bo;

import java.util.LinkedHashMap;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.bo.BusinessObjectBase;
import org.kuali.kra.infrastructure.RoleConstants;

/**
 * A <b>ProposalUser</b> represents a user who has a role in a Proposal's ACL.
 * The roles are Aggregator, Budget Creator, Narrative Writer, Viewer, and
 * unassigned.  The unassigned role has no privileges.  This class is simply
 * used for adding users to a proposal.  When a user is added to a proposal,
 * he/she is assigned only one initial role.  Other roles can be assigned at
 * a later time by editing the roles for that user.  In other words, this
 * BO is simply used as a form.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProposalUser extends BusinessObjectBase {
    
    private String username = "";
    private String fullname = "";
    private String unitNumber = "";
    private String unitName = "";
    private String roleName = "";
    
    /**
     * Constructs a ProposalUser.
     */
    public ProposalUser() {
       
    }

    /**
     * Get the user's username.
     * @return the user's username.
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Set the user's unique username.
     * @param username the user's username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the user's full name.
     * @return the user's full name.
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * Set the user's full name.
     * @param fullname the user's full name
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    
    /**
     * Get the user's home unit number.
     * @return the user's home unit number.
     */
    public String getUnitNumber() {
        return unitNumber;
    }

    /**
     * Set the user's home unit number.
     * @param unitNumber the user's home unit number
     */
    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }
    
    /**
     * Get the user's home unit name.
     * @return the user's home unit name.
     */
    public String getUnitName() {
        return unitName;
    }
    
    /**
     * Set the user's home unit name.
     * @param unitName the user's home unit name
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
    
    /**
     * Get the user's role id in the proposal.
     * @return the user's role id in the proposal.
     */
    public String getRoleName() {
        return roleName;
    }
    
    /**
     * Set the user's role id.
     * @param roleId the user's role id
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * Get the user's role name in the proposal.
     * @return the user's role name in the proposal.
     */
    public String getRoleLabel() {
        if (RoleConstants.UNASSIGNED.equals(roleName)) {
            return "unassigned";
        } else if (RoleConstants.AGGREGATOR.equals(roleName)) {
            return "Aggregator";
        } else if (RoleConstants.BUDGET_CREATOR.equals(roleName)) {
            return "Budget Creator";
        } else if (RoleConstants.NARRATIVE_WRITER.equals(roleName)) {
            return "Narrative Writer";
        } else if (RoleConstants.VIEWER.equals(roleName)) {
            return "Viewer";
        } else {
            return "";
        }
    }
    
    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap map = new LinkedHashMap();
        map.put("username", getUsername());
        map.put("fullname", getFullname());
        map.put("unitNumber", getUnitNumber());
        map.put("unitName", getUnitName());
        map.put("roleName", getRoleName());
        map.put("roleLabel", getRoleLabel());
        return map;
    }

    /**
     * @see org.kuali.core.bo.BusinessObject#refresh()
     */
    public void refresh() {
        // do nothing
    }
    
    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!obj.getClass().equals(this.getClass())) return false;
        
        ProposalUser user = (ProposalUser) obj;
        return StringUtils.equals(this.username, user.username) &&
               StringUtils.equals(this.roleName, user.roleName);
    }
}
