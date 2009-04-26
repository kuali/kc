/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.bo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.kns.bo.BusinessObjectBase;

/**
 * A <b>ProposalUserEditRoles</b> is used as a form used by the Edit Roles
 * web page.  Users can click on the roles that the user will be assigned to.
 * Along with the assigned roles, we also maintain the user's username in order
 * to know which user to modify.  The line number is used when JavaScript is
 * enabled.  It is used to know which entry in the Permission's User table to
 * update with the new set of roles.  Please see the class ProposalDevelopmentPermissionsAction
 * for an explanation of need to know if JavaScript is enabled or not.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProposalUserEditRoles extends BusinessObjectBase {
    
    private String username;
    private List<ProposalRoleState> roleStates;
    private int lineNum = 0;
    private boolean javaScriptEnabled;
    
    /**
     * Constructs a ProposalUserEditRoles.
     */
    public ProposalUserEditRoles() {
       roleStates = new ArrayList<ProposalRoleState>();
    }
    
    /**
     * Set the user's username.
     * @param username the user's username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the user's username.
     * @return the user's username
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Is this user assigned to the "unassigned" role?
     * @return true if unassigned; otherwise false
     */
    //public Boolean getUnassigned() {
     //   return !getAggregator() && !getBudgetCreator() && !getNarrativeWriter() && !getViewer();
    //}
    
    public List<ProposalRoleState> getRoleStates() {
        return roleStates;
    }

    public void setRoleStates(List<ProposalRoleState> roleStates) {
        this.roleStates = roleStates;
    }

    /**
     * Set the line number.
     * @param lineNum the line number
     */
    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }
    
    /**
     * Get the line number.
     * @return the line number
     */
    public int getLineNum() {
        return lineNum;
    }
    
    /**
     * Set the JavaScript to enabled or disabled.
     * @param javaScriptEnabled true or false
     */
    public void setJavaScriptEnabled(boolean javaScriptEnabled) {
        this.javaScriptEnabled = javaScriptEnabled;
    }
    
    /**
     * Is JavaScript enabled?
     * @return true if JavaScript is enabled; otherwise false
     */
    public boolean getJavaScriptEnabled() {
        return this.javaScriptEnabled;
    }
   
    /**
     * @see org.kuali.rice.kns.bo.BusinessObjectBase#toStringMapper()
     */
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap map = new LinkedHashMap();
       
        return map;
    }

    /**
     * @see org.kuali.rice.kns.bo.BusinessObject#refresh()
     */
    public void refresh() {
        // do nothing
    }

    public void setRoleState(String roleName, Boolean state) {
        for (ProposalRoleState roleState : roleStates) {
            if (StringUtils.equals(roleName, roleState.getName())) {
                roleState.setState(state);
                break;
            }
        }
    }

    public void clear() {
        for (ProposalRoleState roleState : roleStates) {
            roleState.setState(Boolean.FALSE);
        }
    }
}
