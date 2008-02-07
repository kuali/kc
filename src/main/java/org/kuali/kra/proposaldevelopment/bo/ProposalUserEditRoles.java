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

import org.kuali.core.bo.BusinessObjectBase;

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
    private Boolean aggregator = Boolean.FALSE;
    private Boolean budgetCreator = Boolean.FALSE;
    private Boolean narrativeWriter = Boolean.FALSE;
    private Boolean viewer = Boolean.FALSE;
    private int lineNum = 0;
    private boolean javaScriptEnabled;
    
    /**
     * Constructs a ProposalUserEditRoles.
     */
    public ProposalUserEditRoles() {
       
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
     * Is the Aggregator role assigned?
     * @return true if the Aggregator role is assigned; otherwise false
     */
    public Boolean getAggregator() {
        return aggregator;
    }

    /**
     * Set the Aggregator role to assigned or unassigned.
     * @param aggregator true if assigned; false if unassigned
     */
    public void setAggregator(Boolean aggregator) {
        this.aggregator = aggregator;
    }

    /**
     * Is the Budget Creator role assigned?
     * @return true if the Budget Creator role is assigned; otherwise false
     */
    public Boolean getBudgetCreator() {
        return budgetCreator;
    }

    /**
     * Set the Budget Creator role to assigned or unassigned.
     * @param budgetCreator true if assigned; false if unassigned
     */
    public void setBudgetCreator(Boolean budgetCreator) {
        this.budgetCreator = budgetCreator;
    }

    /**
     * Is the Narrative Writer role assigned?
     * @return true if the Narrative Writer role is assigned; otherwise false
     */
    public Boolean getNarrativeWriter() {
        return narrativeWriter;
    }

    /**
     * Set the Narrative Writer role to assigned or unassigned.
     * @param narrativeWriter true if assigned; false if unassigned
     */
    public void setNarrativeWriter(Boolean narrativeWriter) {
        this.narrativeWriter = narrativeWriter;
    }

    /**
     * Is the Viewer role assigned?
     * @return true if the Viewer role is assigned; otherwise false
     */
    public Boolean getViewer() {
        return viewer;
    }

    /**
     * Set the Viewer role to assigned or unassigned.
     * @param viewer true if assigned; false if unassigned
     */
    public void setViewer(Boolean viewer) {
        this.viewer = viewer;
    }
    
    /**
     * Is this user assigned to the "unassigned" role?
     * @return true if unassigned; otherwise false
     */
    public Boolean getUnassigned() {
        return !getAggregator() && !getBudgetCreator() && !getNarrativeWriter() && !getViewer();
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
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap map = new LinkedHashMap();
        map.put("aggregator", getAggregator());
        map.put("budgetCreator", getBudgetCreator());
        map.put("narrativeWriter", getNarrativeWriter());
        map.put("viewer", getViewer());
        return map;
    }

    /**
     * @see org.kuali.core.bo.BusinessObject#refresh()
     */
    public void refresh() {
        // do nothing
    }
}
