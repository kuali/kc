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
package org.kuali.kra.coi.disclosure;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.drools.core.util.StringUtils;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.coi.CoiDiscDetail;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.Disclosurable;
import org.kuali.kra.coi.notesandattachments.attachments.CoiDisclosureAttachment;
import org.kuali.kra.coi.notesandattachments.notes.CoiDisclosureNotepad;
import org.kuali.rice.krad.util.ObjectUtils;

public class CoiDisclosureProjectBean implements Serializable {
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -100427824220789523L;
    // TODO : should create interface "CoiDisclosureable" for these project. It is to close to 4.0 release
    // so wait after 4.0
    private KraPersistableBusinessObjectBase disclosureProject;
    private List<CoiDiscDetail> projectDiscDetails;
    private CoiDisclosure coiDisclosure;
    private String projectName;
    private String projectId;
    private Date approvalDate; 
    private List<CoiDisclosureAttachment> projectDiscAttachments;
    private List<CoiDisclosureNotepad> projectDiscNotepads;

    public CoiDisclosureProjectBean() {
        projectDiscDetails = new ArrayList<CoiDiscDetail> ();
        projectDiscAttachments = new ArrayList<CoiDisclosureAttachment> ();
        projectDiscNotepads = new ArrayList<CoiDisclosureNotepad> ();
    }
    

    public KraPersistableBusinessObjectBase getDisclosureProject() {
        return disclosureProject;
    }

    public void setDisclosureProject(KraPersistableBusinessObjectBase disclosureProject) {
        this.disclosureProject = disclosureProject;
    }

    public List<CoiDiscDetail> getProjectDiscDetails() {
        return projectDiscDetails;
    }

    public void setProjectDiscDetails(List<CoiDiscDetail> projectDiscDetails) {
        this.projectDiscDetails = projectDiscDetails;
    }

    public CoiDisclosure getCoiDisclosure() {
        if (coiDisclosure == null) {
            coiDisclosure = getOriginalCoiDisclosure();
        }
        return coiDisclosure;
    }

    private CoiDisclosure getOriginalCoiDisclosure() {
        
        if (CollectionUtils.isNotEmpty(projectDiscDetails)) {
            if (projectDiscDetails.get(0).getOriginalCoiDisclosure() == null) {
                return projectDiscDetails.get(0).getCoiDisclosure();
            } else {
                return projectDiscDetails.get(0).getOriginalCoiDisclosure();
            }
        } else {
            return null;
        }
    }
    
    public void setCoiDisclosure(CoiDisclosure coiDisclosure) {
        this.coiDisclosure = coiDisclosure;
    }


    public String getProjectName() {
        if (StringUtils.isEmpty(projectName) && !getCoiDisclosure().isAnnualEvent()) {
            if (ObjectUtils.isNotNull(disclosureProject)) {
                projectName = ((Disclosurable)disclosureProject).getProjectName();
            } 
        }
        return projectName;
    }


    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }


    public boolean isCurrentProject() {
        
        if (CollectionUtils.isNotEmpty(projectDiscDetails)) {
            return projectDiscDetails.get(0).getOriginalCoiDisclosure() == null;
        } else {
            return false;
        }
    }

    public String getProjectId() {
        if (StringUtils.isEmpty(projectId)  && !getCoiDisclosure().isAnnualEvent()) {
            if (ObjectUtils.isNotNull(disclosureProject)) {
                projectId = ((Disclosurable)disclosureProject).getProjectId();
            }
        }
        return projectId;
    }


    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }


    public Date getApprovalDate() {
        return approvalDate;
    }


    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }


    public List<CoiDisclosureAttachment> getProjectDiscAttachments() {
        return projectDiscAttachments;
    }


    public void setProjectDiscAttachments(List<CoiDisclosureAttachment> projectDiscAttachments) {
        this.projectDiscAttachments = projectDiscAttachments;
    }


    public List<CoiDisclosureNotepad> getProjectDiscNotepads() {
        return projectDiscNotepads;
    }


    public void setProjectDiscNotepads(List<CoiDisclosureNotepad> projectDiscNotepads) {
        this.projectDiscNotepads = projectDiscNotepads;
    }

}
