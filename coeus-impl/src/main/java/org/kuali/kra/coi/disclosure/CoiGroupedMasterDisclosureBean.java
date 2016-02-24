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
