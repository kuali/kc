/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.coi;

import org.kuali.rice.krad.bo.BusinessObject;

/**
 * This class exists only so you can do lookups on undisclosed events.
 */
public class CoiDisclosureUndisclosedEvents implements BusinessObject {

    
    public String reporter;
    public String projectTitle;
    public String projectType;
    public String projectId;
    
    
    public String getReporter() {
        return reporter;
    }


    public void setReporter(String reporter) {
        this.reporter = reporter;
    }


    public String getProjectTitle() {
        return projectTitle;
    }


    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }


    public String getProjectType() {
        return projectType;
    }


    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }


    public String getProjectId() {
        return projectId;
    }


    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }


    @Override
    public void refresh() {
        // TODO Auto-generated method stub
        
    }


}
