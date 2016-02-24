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
package org.kuali.kra.bo;

import org.kuali.rice.krad.bo.BusinessObjectBase;

import java.util.List;

/**
 * The RolePersons BO is simply a role name with a list of the
 * Persons in that role.
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class RolePersons extends BusinessObjectBase {
    
   
    private List<String> approver;
    private List<String> aggregator;
    private List<String> narrativewriter;
    private List<String> budgetcreator;
    private List<String> viewer;
    
    
  
    public void refresh() {
        // do nothing
    }

   

    public List<String> getapprover() {
        return approver;
    }

    public void setapprover(List<String> approver) {
        this.approver = approver;
    }

    public List<String> getAggregator() {
        return aggregator;
    }

    public void setAggregator(List<String> aggregator) {
        this.aggregator = aggregator;
    }

    public List<String> getNarrativewriter() {
        return narrativewriter;
    }

    public void setNarrativewriter(List<String> narrativewriter) {
        this.narrativewriter = narrativewriter;
    }

    public List<String> getBudgetcreator() {
        return budgetcreator;
    }

    public void setBudgetcreator(List<String> budgetcreator) {
        this.budgetcreator = budgetcreator;
    }

    public List<String> getViewer() {
        return viewer;
    }

    public void setViewer(List<String> viewer) {
        this.viewer = viewer;
    }
}
