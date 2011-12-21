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
package org.kuali.kra.irb.actions.submit;

import java.util.LinkedHashMap;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.irb.actions.notification.ProtocolNotificationRequestBean;
import org.kuali.rice.kns.bo.BusinessObjectBase;

/**
 * This class is really just a "form" for the reviewers that
 * are displayed to the user in the Submit for Review Action.
 * It is only displayed from BusinessObject in order to use
 * the Data Dictionary for displaying controls on the web page.
 */
@SuppressWarnings("serial")
public class ProtocolReviewerBean extends BusinessObjectBase {

    public static final String CREATE = "create";
    public static final String UPDATE = "update";
    public static final String REMOVE = "remove";
    private String personId;
    private String fullName;
    private String reviewerTypeCode;
    private boolean nonEmployeeFlag;
    // create/update/remove
    private String actionFlag;
    private ProtocolNotificationRequestBean notificationRequestBean;
    
    public ProtocolReviewerBean() {
        
    }
    
    public ProtocolReviewerBean(CommitteeMembership member) {
        if (!StringUtils.isBlank(member.getPersonId())) {
            this.setPersonId(member.getPersonId());
            this.setNonEmployeeFlag(false);
        }
        else {
            this.setPersonId(member.getRolodexId().toString());
            this.setNonEmployeeFlag(true);
        }
        this.setFullName(member.getPersonName());
    }
    
    public String getPersonId() {
        return personId;
    }
    
    public void setPersonId(String personId) {
        this.personId = personId;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getReviewerTypeCode() {
        return reviewerTypeCode;
    }
    
    public void setReviewerTypeCode(String reviewerTypeCode) {
        this.reviewerTypeCode = reviewerTypeCode;
    }
    
    public boolean getNonEmployeeFlag() {
        return nonEmployeeFlag;
    }
    
    public void setNonEmployeeFlag(boolean nonEmployeeFlag) {
        this.nonEmployeeFlag = nonEmployeeFlag;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap map = new LinkedHashMap();
        map.put("personId", getPersonId());
        map.put("fullName", getFullName());
        map.put("reviewTypeCode", getReviewerTypeCode());
        map.put("nonEmployeeFlag", getNonEmployeeFlag());
        return map;
    }

    public void refresh() {
       
    }
    
    public boolean isProtocolReviewerBeanForReviewer( ProtocolReviewer reviewer ) {
        boolean result = ( reviewer.getNonEmployeeFlag() && reviewer.getRolodexId()!=null && StringUtils.equals(this.getPersonId(), reviewer.getRolodexId().toString()))
                         ||
                         ( !reviewer.getNonEmployeeFlag() && reviewer.getPersonId()!=null && StringUtils.equals(reviewer.getPersonId(), this.getPersonId()));
        return result;
    }   
    
    public boolean isProtocolReviewerBeanForCommitteeMembership( CommitteeMembership membership ) {
        boolean result = ( membership.getPersonId()==null && membership.getRolodexId()!=null && StringUtils.equals(this.getPersonId(), membership.getRolodexId().toString() ))
                         ||
                         ( membership.getPersonId()!=null && membership.getPersonId()!=null && StringUtils.equals(membership.getPersonId(), this.getPersonId()));
        return result;
    }

    public String getActionFlag() {
        return actionFlag;
    }

    public void setActionFlag(String actionFlag) {
        this.actionFlag = actionFlag;
    }

    public ProtocolNotificationRequestBean getNotificationRequestBean() {
        return notificationRequestBean;
    }

    public void setNotificationRequestBean(ProtocolNotificationRequestBean notificationRequestBean) {
        this.notificationRequestBean = notificationRequestBean;
    }   
    
    
}
