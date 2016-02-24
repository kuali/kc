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

import org.apache.commons.collections4.CollectionUtils;
import org.kuali.kra.coi.CoiDiscDetail;
import org.kuali.kra.coi.CoiDisclProject;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.notesandattachments.attachments.CoiDisclosureAttachment;
import org.kuali.kra.coi.notesandattachments.notes.CoiDisclosureNotepad;
import org.kuali.kra.coi.questionnaire.DisclProjectQuestionnaireHelper;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CoiDisclosureProjectBean implements Serializable {

    private static final long serialVersionUID = -100427824220789523L;
    private CoiDisclProject coiDisclProject;
    private CoiDisclosure coiDisclosure;
    private String projectName;
    private String projectId;
    private Date approvalDate; 
    private List<CoiDisclosureAttachment> projectDiscAttachments;
    private List<CoiDisclosureNotepad> projectDiscNotepads;
    private DisclProjectQuestionnaireHelper projectQuestionnaireHelper;
    private boolean excludeFE; 

    public CoiDisclosureProjectBean() {
        coiDisclProject = new CoiDisclProject();
        projectDiscAttachments = new ArrayList<CoiDisclosureAttachment> ();
        projectDiscNotepads = new ArrayList<CoiDisclosureNotepad> ();
    }
    
    public CoiDisclProject getCoiDisclProject() {
        return coiDisclProject;
    }

    public void setCoiDisclProject(CoiDisclProject coiDisclProject) {
        this.coiDisclProject = coiDisclProject;
    }

    public CoiDisclosure getCoiDisclosure() {
        if (coiDisclosure == null) {
            coiDisclosure = getOriginalCoiDisclosure();
        }
        return coiDisclosure;
    }

    private CoiDisclosure getOriginalCoiDisclosure() {
        List<CoiDiscDetail> projectDiscDetails = coiDisclProject.getCoiDiscDetails();
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
        if(getCoiDisclosure().isAnnualEvent() || getCoiDisclosure().isUpdateEvent()) {
            return null;
        }else {
            return projectName;
        }
    }


    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }


    public boolean isCurrentProject() {
        List<CoiDiscDetail> projectDiscDetails = coiDisclProject.getCoiDiscDetails();
        if (CollectionUtils.isNotEmpty(projectDiscDetails)) {
            return projectDiscDetails.get(0).getOriginalCoiDisclosure() == null;
        } else {
            return false;
        }
    }

    public String getProjectId() {
        if(getCoiDisclosure().isAnnualEvent() || getCoiDisclosure().isUpdateEvent()) {
            return null;
        }else {
            return projectId;
        }
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


    public boolean isExcludeFE() {
        return excludeFE;
    }


    public void setExcludeFE(boolean excludeFE) {
        this.excludeFE = excludeFE;
    }

    public DisclProjectQuestionnaireHelper getProjectQuestionnaireHelper() {
        return projectQuestionnaireHelper;
    }  

    public void setProjectQuestionnaireHelper(DisclProjectQuestionnaireHelper projectQuestionnaireHelper) {
        this.projectQuestionnaireHelper = projectQuestionnaireHelper;
    }
    
    public void populateAnswers(CoiDisclosure originalDisclosure) {
        projectQuestionnaireHelper = new DisclProjectQuestionnaireHelper(coiDisclProject, coiDisclProject.getCoiDisclosure(), originalDisclosure);
        projectQuestionnaireHelper.populateAnswers();
    }
    
    public void versionDisclosureAnswers(CoiDisclosure originalDisclosure) {
        projectQuestionnaireHelper = new DisclProjectQuestionnaireHelper(coiDisclProject, coiDisclProject.getCoiDisclosure(), originalDisclosure);
        projectQuestionnaireHelper.versionAnswers();
    }
    
    
    public List<AnswerHeader> getAnswerHeaders() {
        return this.getProjectQuestionnaireHelper().getAnswerHeaders();
    }

}
