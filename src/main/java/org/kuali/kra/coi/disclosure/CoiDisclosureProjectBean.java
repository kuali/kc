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
import org.kuali.kra.coi.CoiDisclProject;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiDisclosureForm;
import org.kuali.kra.coi.Disclosurable;
import org.kuali.kra.coi.notesandattachments.attachments.CoiDisclosureAttachment;
import org.kuali.kra.coi.notesandattachments.notes.CoiDisclosureNotepad;
import org.kuali.kra.coi.questionnaire.DisclProjectQuestionnaireHelper;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.rice.krad.util.ObjectUtils;

public class CoiDisclosureProjectBean implements Serializable {
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -100427824220789523L;
    // TODO : should create interface "CoiDisclosureable" for these project. It is to close to 4.0 release
    // so wait after 4.0
    //private KraPersistableBusinessObjectBase disclosureProject;
    //private List<CoiDiscDetail> projectDiscDetails;
    private CoiDisclProject coiDisclProject;
    private CoiDisclosure coiDisclosure;
    private String projectName;
    private String projectId;
    private Date approvalDate; 
    private List<CoiDisclosureAttachment> projectDiscAttachments;
    private List<CoiDisclosureNotepad> projectDiscNotepads;
    //private List<AnswerHeader> answerHeaders;
    private DisclProjectQuestionnaireHelper projectQuestionnaireHelper;
    private boolean excludeFE; 

    public CoiDisclosureProjectBean() {
        coiDisclProject = new CoiDisclProject();
        //projectDiscDetails = new ArrayList<CoiDiscDetail> ();
        projectDiscAttachments = new ArrayList<CoiDisclosureAttachment> ();
        projectDiscNotepads = new ArrayList<CoiDisclosureNotepad> ();
        //answerHeaders = new ArrayList<AnswerHeader> ();
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
        if (StringUtils.isEmpty(projectName) && !getCoiDisclosure().isAnnualEvent()) {
            if (ObjectUtils.isNotNull(coiDisclProject)) {
                projectName = coiDisclProject.getProjectName();
            } 
        }
        return projectName;
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
        if (StringUtils.isEmpty(projectId)  && !getCoiDisclosure().isAnnualEvent()) {
            if (ObjectUtils.isNotNull(coiDisclProject)) {
                projectId = coiDisclProject.getProjectId();
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


//    public List<AnswerHeader> getAnswerHeaders() {
//        return answerHeaders;
//    }
//
//
//    public void setAnswerHeaders(List<AnswerHeader> answerHeaders) {
//        this.answerHeaders = answerHeaders;
//    }

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
    
    public void populateAnswers(String originalDisclosureId) {
        projectQuestionnaireHelper = new DisclProjectQuestionnaireHelper(coiDisclProject, coiDisclProject.getCoiDisclosure(), originalDisclosureId);
        projectQuestionnaireHelper.populateAnswers();
    }
    
    public List<AnswerHeader> getAnswerHeaders() {
        return this.getProjectQuestionnaireHelper().getAnswerHeaders();
    }

}
