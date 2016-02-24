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
package org.kuali.coeus.common.impl.ynq;

import org.kuali.coeus.common.framework.ynq.Ynq;
import org.kuali.coeus.common.framework.ynq.YnqExplanationType;
import org.kuali.coeus.common.framework.ynq.YnqGroupName;
import org.kuali.coeus.common.framework.ynq.YnqService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.person.ProposalPersonYnq;
import org.kuali.coeus.propdev.impl.ynq.ProposalYnq;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.common.api.ynq.YnqConstant;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("ynqService")
public class YnqServiceImpl implements YnqService {

	@Autowired
	@Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;
    
    @Override
    @SuppressWarnings("unchecked")
    public List<YnqExplanationType> getYnqExplanationTypes() {
        Collection<YnqExplanationType> allTypes = new ArrayList();
        allTypes = businessObjectService.findAll(YnqExplanationType.class);
        List<YnqExplanationType> ynqExplanationTypes = new ArrayList();
        for(YnqExplanationType type: allTypes) {
            ynqExplanationTypes.add(type);
        } 
        return ynqExplanationTypes;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Ynq> getYnq(String questionType) {
        Map<String, String> questionTypeMap = new HashMap<String, String>();
        /* filter by question type */
        questionTypeMap.put("questionType", questionType);
        /* filter by status - fetch all active questions */
        questionTypeMap.put("status", Constants.STATUS_ACTIVE); 

        // get YNQs sorted by group and question id
        Collection<Ynq> allTypes = getBusinessObjectService().findMatchingOrderBy(Ynq.class, questionTypeMap, "sortId", true);
        List<Ynq> ynqs = new ArrayList<Ynq>();
        ynqs.addAll(allTypes);
        // Preserves the sort ID ordering because Collections.sort is "guaranteed to be stable: equal elements will not be reordered as a result of the sort."
        Collections.sort(ynqs, new GroupNameComparator());
        return ynqs;
    }

    /**
     * Compares two YNQs by groupName.
     */
    protected class GroupNameComparator implements Comparator<Ynq> {

        public int compare(Ynq o1, Ynq o2) {
            int comparator;
            
            if (o1.getSortId() != null && o2.getSortId() != null) {
                comparator = o1.getSortId().compareTo(o2.getSortId());
            } else {
                comparator = o1.getQuestionId().compareTo(o2.getQuestionId());
            }
            
            return comparator;
        }
    }
    
    /**
     *
     * @param proposalPerson
     * @return ProposalPerson
     */
    public ProposalPerson getPersonYNQ(ProposalPerson proposalPerson, ProposalDevelopmentDocument document) {
        /* get YNQ for person */
        boolean certificationRequired = false;
        
        if(proposalPerson.getRole() != null && proposalPerson.getRole().getCertificationRequired()) {
            certificationRequired = true;
        } else if (proposalPerson.getOptInCertificationStatus()) {
            certificationRequired = true;
        } else {
            certificationRequired = false;
        }
        
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
    protected void addCertificationQuestions(List<Ynq> ynqs, ProposalPerson proposalPerson) {
        for (Ynq type : ynqs) {
            ProposalPersonYnq proposalPersonYnq = new ProposalPersonYnq();
            proposalPersonYnq.setYnq(type); 
            proposalPersonYnq.setProposalPerson(proposalPerson);
            proposalPerson.getProposalPersonYnqs().add(proposalPersonYnq);
        }
    }
    
    
    /* set required fields comments - check question configuration to figure out required fields */
    protected void setRequiredFields(Ynq type, ProposalYnq proposalYnq) {
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
    protected void getGroupNames(List<ProposalYnq> proposalYnqs, List<YnqGroupName> ynqGroupNames) {
        for (ProposalYnq type : proposalYnqs) {
            setRequiredFields(type.getYnq(), type);
            /* add distinct group names */
            setGroupName(type.getYnq().getGroupName(), ynqGroupNames);
        }
    }

    /* get questions from YNQ configuration */
    protected void getProposalQuestions(List<ProposalYnq> proposalYnqs, List<YnqGroupName> ynqGroupNames, List<Ynq> ynqs) {
        for (Ynq type : ynqs) {
            ProposalYnq proposalYnq = new ProposalYnq();
            proposalYnq.setQuestionId(type.getQuestionId());
            proposalYnq.setYnq(type); 
            setRequiredFields(type, proposalYnq);
            proposalYnq.setSortId(type.getSortId());
            proposalYnqs.add(proposalYnq);
            /* add distinct group names */
            setGroupName(type.getGroupName(), ynqGroupNames);
        }
    }

    
    /* set group name */
    protected void setGroupName(String groupName, List<YnqGroupName> ynqGroupNames) {
        /* add distinct group names */
        if(!isDuplicateGroupName(groupName, ynqGroupNames)) {
            YnqGroupName ynqGroupName = new YnqGroupName();
            ynqGroupName.setGroupName(groupName);
            ynqGroupNames.add(ynqGroupName);
        }
    }
    
    
    @Override
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
    protected void addNewProposalQuestions(List<ProposalYnq> proposalYnqs, List<Ynq> ynqs, List<YnqGroupName> ynqGroupNames) {
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
    
    
    protected boolean isDocumentSubmitted(ProposalDevelopmentDocument document) {
        boolean submitted = false;
        WorkflowDocument wfd = document.getDocumentHeader().getWorkflowDocument();
        document.getDocumentHeader().getWorkflowDocument().isInitiated();
        if(!wfd.isInitiated()) {
            if(!wfd.isSaved() ||
                    (document.getDevelopmentProposal().getS2sAppSubmission() != null && !document.getDevelopmentProposal().getS2sAppSubmission().isEmpty())) {
                submitted = true;
            }
        }
        return submitted;
    }
    
    protected boolean isDuplicateGroupName(String groupName, List<YnqGroupName> ynqGroupNames) {
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
    protected String getYnqRequiredLabel(String ynqCode) {
        String retValue = null;
        for (YnqConstant ynqConstants : YnqConstant.values()) {
            if(ynqConstants.code().equalsIgnoreCase(ynqCode)) {
                retValue =  ynqConstants.description();
                break;
            }
        }
        return retValue;
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


}
