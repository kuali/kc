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
package org.kuali.kra.service.impl;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.kuali.kra.bo.Ynq;
import org.kuali.kra.bo.YnqExplanationType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.YnqConstants;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonYnq;
import org.kuali.kra.proposaldevelopment.bo.ProposalYnq;
import org.kuali.kra.proposaldevelopment.bo.YnqGroupName;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.service.YnqService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DateTimeService;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;
public class YnqServiceImpl implements YnqService {

    private BusinessObjectService businessObjectService;
    private DateTimeService dateTimeService;
    
    
    /**
     * @see org.kuali.kra.proposaldevelopment.service.YnqService#getYnqExplanationTypes()
     */
    public List<YnqExplanationType> getYnqExplanationTypes() {
        Collection<YnqExplanationType> allTypes = new ArrayList();
        allTypes = businessObjectService.findAll(YnqExplanationType.class);
        List<YnqExplanationType> ynqExplanationTypes = new ArrayList();
        for(YnqExplanationType type: allTypes) {
            ynqExplanationTypes.add(type);
        } 
        return ynqExplanationTypes;
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.service.YnqService#getYnq(java.lang.String)
     */
    public List<Ynq> getYnq(String questionType) {
        Map<String, String> questionTypeMap = new HashMap<String, String>();
        /* filter by question type */
        questionTypeMap.put("questionType", questionType);
        /* filter by status - fetch all active questions */
        questionTypeMap.put("status", Constants.STATUS_ACTIVE); 

        Collection<Ynq> allTypes = getBusinessObjectService().findMatchingOrderBy(Ynq.class, questionTypeMap, "groupName", false);
        List<Ynq> ynqs = new ArrayList<Ynq>();
        ynqs.addAll(allTypes);
        /* also filter all questions based on effective date - current date >= effective date */
        /* - Effective date filter currently not used
        Date currentDate = getDateTimeService().getCurrentSqlDateMidnight();
        for(Ynq type: allTypes) {
            if(type.getEffectiveDate().compareTo(currentDate) < 0   ) {
                ynqs.add(type);
            }
        } 
        */
        return ynqs;
    }

    /**
     *
     * @param proposalPerson
     * @return ProposalPerson
     */
    public ProposalPerson getPersonYNQ(ProposalPerson proposalPerson, ProposalDevelopmentDocument document) {
        /* get YNQ for person */
        boolean certificationRequired = false;
        
        if(proposalPerson.getRole() != null && proposalPerson.getRole().getCertificationRequired().equals("Y"))
        {
            certificationRequired = true;
        }
        else if( (isNotBlank(proposalPerson.getOptInCertificationStatus())) && (proposalPerson.getOptInCertificationStatus().equals("Y")))
        {
            certificationRequired = true;
        }
        else
        {
            certificationRequired = false;
        }
        
        /*if(proposalPerson.getRole() !=  null) {
            certificationRequired = proposalPerson.getRole().getCertificationRequired();
        }*/
        
        if(certificationRequired) {
            String questionType = Constants.QUESTION_TYPE_INDIVIDUAL;
            List<Ynq> ynqs = new ArrayList<Ynq>();
            
            /* retrieve questions to compare if document is not submitted to grants.gov / workflow */
            if(!isDocumentSubmitted(document))  {
                ynqs = getYnq(questionType);
            }

            if(proposalPerson.getProposalPersonYnqs().isEmpty()) {
                addCertificationQuestions(ynqs, proposalPerson);
            }else {
                Set<String> proposalPersonQuestionIds = new HashSet<String>();
                /* get all existing questions */
                for(ProposalPersonYnq proposalPersonYnq : proposalPerson.getProposalPersonYnqs()) {
                    proposalPersonQuestionIds.add(proposalPersonYnq.getQuestionId());
                }
                /* compare existing and new questions. if not found add to new list */
                List<Ynq> newYnqs = new ArrayList<Ynq>();
                for(Ynq ynq : ynqs) {
                    if(!proposalPersonQuestionIds.contains(ynq.getQuestionId())) {
                        newYnqs.add(ynq);
                    }
                }
                addCertificationQuestions(newYnqs, proposalPerson);
            }
        }
        return proposalPerson;
    }
    
    /* add proposal certification questions */
    private void addCertificationQuestions(List<Ynq> ynqs, ProposalPerson proposalPerson) {
        for (Ynq type : ynqs) {
            ProposalPersonYnq proposalPersonYnq = new ProposalPersonYnq();
            proposalPersonYnq.setQuestionId(type.getQuestionId());
            proposalPersonYnq.setYnq(type); 
            proposalPerson.getProposalPersonYnqs().add(proposalPersonYnq);
        }
    }
    
    
    /* set required fields comments - check question configuration to figure out required fields */
    private void setRequiredFields(Ynq type, ProposalYnq proposalYnq) {
        /* check Date required for column in required */
        if(type.getDateRequiredFor() == null) {
            proposalYnq.setReviewDateRequired(false);
        }else {
            proposalYnq.setReviewDateRequiredDescription(Constants.YNQ_REVIEW_DATE_REQUIRED.concat(getYnqRequiredLabel(type.getDateRequiredFor())));
        }
        /* check Explanation required for column is mandatory */
        if(type.getExplanationRequiredFor() == null) {
            proposalYnq.setExplanationRequried(false);
        }else {
            proposalYnq.setExplanationRequiredDescription(Constants.YNQ_EXPLANATION_REQUIRED.concat(getYnqRequiredLabel(type.getExplanationRequiredFor())));
        }
    }
 
    
    /* get group name from existing proposal questions */
    private void getGroupNames(List<ProposalYnq> proposalYnqs, List<YnqGroupName> ynqGroupNames) {
        for (ProposalYnq type : proposalYnqs) {
            setRequiredFields(type.getYnq(), type);
            /* add distinct group names */
            setGroupName(type.getYnq().getGroupName(), ynqGroupNames);
        }
    }

    /* get questions from YNQ configuration */
    private void getProposalQuestions(List<ProposalYnq> proposalYnqs, List<YnqGroupName> ynqGroupNames, List<Ynq> ynqs) {
        for (Ynq type : ynqs) {
            ProposalYnq proposalYnq = new ProposalYnq();
            proposalYnq.setQuestionId(type.getQuestionId());
            proposalYnq.setYnq(type); 
            setRequiredFields(type, proposalYnq);
            proposalYnqs.add(proposalYnq);
            /* add distinct group names */
            setGroupName(type.getGroupName(), ynqGroupNames);
        }
    }

    
    /* set group name */
    private void setGroupName(String groupName, List<YnqGroupName> ynqGroupNames) {
        /* add distinct group names */
        if(!isDuplicateGroupName(groupName, ynqGroupNames)) {
            YnqGroupName ynqGroupName = new YnqGroupName();
            ynqGroupName.setGroupName(groupName);
            ynqGroupNames.add(ynqGroupName);
        }
    }
    
    
    /**
     * @see org.kuali.kra.proposaldevelopment.service.YnqService#populateQuestions()
     */
    /* get YNQ for proposal */
    public void populateProposalQuestions(List<ProposalYnq> proposalYnqs, List<YnqGroupName> ynqGroupNames, ProposalDevelopmentDocument document) {
        String questionType = Constants.QUESTION_TYPE_PROPOSAL;
        List<Ynq> ynqs = new ArrayList<Ynq>();
        
        /* retrieve questions to compare if document is not submitted to grants.gov / workflow */
        if(!isDocumentSubmitted(document))  {
            ynqs = getYnq(questionType);
        }
        
        if(proposalYnqs.isEmpty()) {
            getProposalQuestions(proposalYnqs, ynqGroupNames, ynqs);
        }else {
            if(ynqGroupNames.isEmpty()) {
                getGroupNames(proposalYnqs, ynqGroupNames);
            }
            /* check for new questions */
            if(!ynqs.isEmpty())  {
                addNewProposalQuestions(proposalYnqs, ynqs, ynqGroupNames);
            }
        }
    }

    /* check existing proposal YNQ and add new questions if exists */
    public void addNewProposalQuestions(List<ProposalYnq> proposalYnqs, List<Ynq> ynqs, List<YnqGroupName> ynqGroupNames) {
        List<Ynq> newYnqs = new ArrayList<Ynq>();
        Set<String> proposalQuestionIds = new HashSet<String>();
        
        /* get all existing questions */
        for(ProposalYnq proposalYnq : proposalYnqs) {
            proposalQuestionIds.add(proposalYnq.getQuestionId());
        }
        
        /* compare existing and new questions. if not found add to new list */
        for(Ynq ynq : ynqs) {
            if(!proposalQuestionIds.contains(ynq.getQuestionId())) {
                newYnqs.add(ynq);
            }
        }
        getProposalQuestions(proposalYnqs, ynqGroupNames, newYnqs);
    }
    
    
    private boolean isDocumentSubmitted(ProposalDevelopmentDocument document) {
        boolean submitted = false;
        KualiWorkflowDocument wfd = document.getDocumentHeader().getWorkflowDocument();
        document.getDocumentHeader().getWorkflowDocument().stateIsInitiated();
        if(!wfd.stateIsInitiated()) {
            if(!wfd.stateIsSaved() ||
                    (document.getS2sAppSubmission() != null || !document.getS2sAppSubmission().isEmpty())) {
                submitted = true;
            }
        }
        return submitted;
    }
    
    private boolean isDuplicateGroupName(String groupName, List<YnqGroupName> ynqGroupNames) {
        boolean duplicateGroupName = false;
        for (YnqGroupName type : ynqGroupNames) {
            if(type.getGroupName().equalsIgnoreCase(groupName)) {
                duplicateGroupName = true;
                break;
            }
        }
        return duplicateGroupName;
    }

    /* get ynq explanation required / review date required column label */
    private String getYnqRequiredLabel(String ynqCode) {
        String retValue = null;
        for (YnqConstants ynqConstants : YnqConstants.values()) {
            if(ynqConstants.code().equalsIgnoreCase(ynqCode)) {
                retValue =  ynqConstants.description();
                break;
            }
        }
        return retValue;
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.service.YnqService#getProposalPerson()
     */
    public List<ProposalPerson> getProposalPerson() {
        Collection<ProposalPerson> allTypes = new ArrayList<ProposalPerson>();
        allTypes = businessObjectService.findAll(ProposalPerson.class);
        List<ProposalPerson> proposalPerson = new ArrayList<ProposalPerson>();
        for(ProposalPerson type: allTypes) {
            proposalPerson.add(type);
        } 
        return proposalPerson;
    }
    
    /**
     * Gets the businessObjectService attribute.
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * Sets the businessObjectService attribute value.
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public DateTimeService getDateTimeService() {
        return dateTimeService;
    }

    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }


}
