/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.coi.disclosure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is to group disclosure project bean either by event type or by
 * financial entity. Currently we are listing it by projects so that we get to
 * display project details at the top level. 
 * We can regroup this completely by event type if required.
 * If we have to regroup by event type then project details here does not have
 * any effect.
 */
public class CoiGroupedMasterDisclosureBean implements Serializable {


    private static final long serialVersionUID = -5710247175508316897L;
    
    private String disclosureEventType;

    private String projectId;
    private String projectTitle;
    private String dispositionStatus;
    private String disclosureStatus;
    
    private String entityNumber;
    private String entityName;
    
    
    private List<CoiDisclosureProjectBean> allRelatedProjects;

    public CoiGroupedMasterDisclosureBean() {
        setAllRelatedProjects(new ArrayList<CoiDisclosureProjectBean>());
    }

    public List<CoiDisclosureProjectBean> getAllRelatedProjects() {
        return allRelatedProjects;
    }

    public void setAllRelatedProjects(List<CoiDisclosureProjectBean> allRelatedProjects) {
        this.allRelatedProjects = allRelatedProjects;
    }

    public String getDisclosureEventType() {
        return disclosureEventType;
    }

    public void setDisclosureEventType(String disclosureEventType) {
        this.disclosureEventType = disclosureEventType;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getDispositionStatus() {
        return dispositionStatus;
    }

    public void setDispositionStatus(String dispositionStatus) {
        this.dispositionStatus = dispositionStatus;
    }

    public String getDisclosureStatus() {
        return disclosureStatus;
    }

    public void setDisclosureStatus(String disclosureStatus) {
        this.disclosureStatus = disclosureStatus;
    }

    public String getEntityNumber() {
        return entityNumber;
    }

    public void setEntityNumber(String entityNumber) {
        this.entityNumber = entityNumber;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
    
}
