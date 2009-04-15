/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.bo;

import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.core.bo.BusinessObjectBase;

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
    
    
  
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap map = new LinkedHashMap();
        map.put(approver,this.getapprover());
        map.put(aggregator,this.getAggregator());
        map.put(narrativewriter,this.getNarrativewriter());
        map.put(viewer,this.getViewer());
        return map;
    }

    public void refresh() {
        // do nothing
    }

   

    public List<String> getapprover() {
        return approver;
    }

    public void setapprover(List<String> approver) {
        this.approver = approver;
        System.out.println(approver);
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
