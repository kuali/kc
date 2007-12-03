/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.DateTimeService;
import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.Ynq;
import org.kuali.kra.bo.YnqExplanationType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.YnqConstants;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonYnq;
import org.kuali.kra.proposaldevelopment.bo.ProposalYnq;
import org.kuali.kra.proposaldevelopment.bo.YnqGroupName;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.service.YnqService;

public class YnqServiceImpl implements YnqService {

    private BusinessObjectService businessObjectService;
    
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
        Map questionTypeMap = new HashMap();
        /* filter by question type */
        questionTypeMap.put("questionType", questionType);
        String orderBy = "groupName";
        Collection<Ynq> allTypes = new ArrayList();
        allTypes = businessObjectService.findMatchingOrderBy(Ynq.class, questionTypeMap, orderBy, false);
        List<Ynq> ynqs = new ArrayList();
        
        /* also filter all questions based on effective date - current date >= effective date */
        Date currentDate= ((DateTimeService)KraServiceLocator.getService(Constants.DATE_TIME_SERVICE_NAME)).getCurrentSqlDateMidnight();
        /*
        Calendar currentDateCal = ((DateTimeService)KraServiceLocator.getService(Constants.DATE_TIME_SERVICE_NAME)).getCurrentCalendar();
        int currentYear = currentDateCal.get(currentDateCal.YEAR);
        int currentMonth = currentDateCal.get(currentDateCal.MONTH);
        int currentDay = currentDateCal.get(currentDateCal.DATE);
        currentDateCal.set(currentYear, currentMonth, currentDay, 0, 0, 0);
        Date currentDate = currentDateCal.getTime();
        */
        for(Ynq type: allTypes) {
            if(type.getEffectiveDate().compareTo(currentDate) < 0   ) {
                ynqs.add(type);
            }
        } 
        return ynqs;
    }

    /**
     *
     * @param proposalPerson
     * @return ProposalPerson
     */
    public ProposalPerson getPersonYNQ(ProposalPerson proposalPerson) {
        /* get YNQ for person */
        if(proposalPerson.getProposalPersonYnqs().isEmpty()) {
            String questionType = Constants.QUESTION_TYPE_INDIVIDUAL;
            List<Ynq> ynqs = (KraServiceLocator.getService(YnqService.class).getYnq(questionType));
            for (Ynq type : ynqs) {
                ProposalPersonYnq proposalPersonYnq = new ProposalPersonYnq();
                proposalPersonYnq.setQuestionId(type.getQuestionId());
                proposalPersonYnq.setYnq(type); 
                proposalPerson.getProposalPersonYnqs().add(proposalPersonYnq);
            }
        }
        return proposalPerson;
    }
    
    /**
     * @see org.kuali.kra.proposaldevelopment.service.YnqService#populateQuestions()
     */
    /* get YNQ for proposal */
    public void populateProposalQuestions(List<ProposalYnq> proposalYnqs, List<YnqGroupName> ynqGroupNames) {
        //List<ProposalYnq> proposalYnqs = proposalDevelopmentDocument.getProposalYnqs();
        //List<YnqGroupName> ynqGroupNames = proposalDevelopmentDocument.getYnqGroupNames();
        String questionType = Constants.QUESTION_TYPE_PROPOSAL;
        List<Ynq> ynqs = getYnq(questionType);
        for (Ynq type : ynqs) {
            ProposalYnq proposalYnq = new ProposalYnq();
            proposalYnq.setQuestionId(type.getQuestionId());
            proposalYnq.setYnq(type); 
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
            proposalYnqs.add(proposalYnq);
            /* add distinct group names */
            if(!isDuplicateGroupName(type.getGroupName(), ynqGroupNames)) {
                YnqGroupName ynqGroupName = new YnqGroupName();
                ynqGroupName.setGroupName(type.getGroupName());
                ynqGroupNames.add(ynqGroupName);
            }
        }
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
        Collection<ProposalPerson> allTypes = new ArrayList();
        allTypes = businessObjectService.findAll(ProposalPerson.class);
        List<ProposalPerson> proposalPerson = new ArrayList();
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


}
