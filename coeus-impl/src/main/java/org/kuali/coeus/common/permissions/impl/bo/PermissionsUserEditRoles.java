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
package org.kuali.coeus.common.permissions.impl.bo;

import org.apache.commons.lang3.StringUtils;
import org.kuali.rice.krad.bo.BusinessObjectBase;

import java.util.ArrayList;
import java.util.List;

/**
 * A <b>PermissionsUserEditRoles</b> is used as a form used by the Edit Roles
 * web page.  Users can click on the roles that the user will be assigned to.
 * Along with the assigned roles, we also maintain the user's username in order
 * to know which user is being modified.  The line number is used when JavaScript is
 * enabled.  It is used to know which entry in the Permission's User table to
 * dynamically update with the new set of roles.  Please see <b>AbstractPermissionsActionHelper</b>
 * for a more detailed explanation regarding JavaScript.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@SuppressWarnings("serial")
public class PermissionsUserEditRoles extends BusinessObjectBase {
    
    private String userName;
    private boolean principalInvestigator;
    private List<PermissionsRoleState> roleStates;
    private int lineNum = 0;
    private boolean javaScriptEnabled;
    
    public PermissionsUserEditRoles() {
       roleStates = new ArrayList<PermissionsRoleState>();
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
    
    public void setPrinipalInvestigator(boolean principalInvestigator) {
        this.principalInvestigator = principalInvestigator;
    }
    
    public boolean isPrincipalInvestigator() {
        return principalInvestigator;
    }

    public List<PermissionsRoleState> getRoleStates() {
        return roleStates;
    }

    public void setRoleStates(List<PermissionsRoleState> roleStates) {
        this.roleStates = roleStates;
    }

    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }
    
    public int getLineNum() {
        return lineNum;
    }
    
    public void setJavaScriptEnabled(boolean javaScriptEnabled) {
        this.javaScriptEnabled = javaScriptEnabled;
    }
    
    public boolean getJavaScriptEnabled() {
        return this.javaScriptEnabled;
    }
    
    /**
     * Set a role state to true/false.
     * @param roleName the name of the role
     * @param state the new state
     */
    public void setRoleState(String roleName, Boolean state) {
        for (PermissionsRoleState roleState : roleStates) {
            if (StringUtils.equals(roleName, roleState.getRole().getName())) {
                roleState.setState(state);
                break;
            }
        }
    }

    /**
     * Clear all of the role states.
     */
    public void clear() {
        for (PermissionsRoleState roleState : roleStates) {
            roleState.setState(Boolean.FALSE);
        }
    }
   
    @Override
    public void refresh() {
        // do nothing
    }
}
