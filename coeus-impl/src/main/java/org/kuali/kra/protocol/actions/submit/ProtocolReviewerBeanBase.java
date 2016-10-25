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
package org.kuali.kra.protocol.actions.submit;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipBase;
import org.kuali.kra.protocol.notification.ProtocolNotificationRequestBeanBase;
import org.kuali.rice.krad.bo.BusinessObjectBase;

/**
 * 
 * 
 * 
 */
/**
 * This class is really just a "form" for the reviewers that
 * are displayed to the user in the Submit for Review Action.
 * It is only displayed from BusinessObject in order to use
 * the Data Dictionary for displaying controls on the web page.
 */
@SuppressWarnings("serial")
public abstract class ProtocolReviewerBeanBase extends BusinessObjectBase {

    public static final String CREATE = "create";
    public static final String UPDATE = "update";
    public static final String REMOVE = "remove";
    private String personId;
    private String fullName;
    private String reviewerTypeCode;
    private boolean nonEmployeeFlag;
    // create/update/remove
    private String actionFlag;
    private ProtocolNotificationRequestBeanBase notificationRequestBean;
    
    public ProtocolReviewerBeanBase() {
        
    }
    
    public ProtocolReviewerBeanBase(CommitteeMembershipBase member) {
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

    public void refresh() {
       
    }
    
    public boolean isProtocolReviewerBeanForReviewer( ProtocolReviewer reviewer ) {
        boolean result = ( reviewer.getNonEmployeeFlag() && reviewer.getRolodexId()!=null && StringUtils.equals(this.getPersonId(), reviewer.getRolodexId().toString()))
                         ||
                         ( !reviewer.getNonEmployeeFlag() && reviewer.getPersonId()!=null && StringUtils.equals(reviewer.getPersonId(), this.getPersonId()));
        return result;
    }   
    
    public boolean isProtocolReviewerBeanForCommitteeMembership( CommitteeMembershipBase membership ) {
        boolean result = ( membership.getPersonId()==null && membership.getRolodexId()!=null && StringUtils.equals(this.getPersonId(), membership.getRolodexId().toString() ))
                         ||
                         ( membership.getPersonId()!=null && StringUtils.equals(membership.getPersonId(), this.getPersonId()));
        return result;
    }   
    
    public String getActionFlag() {
        return actionFlag;
    }

    public void setActionFlag(String actionFlag) {
        this.actionFlag = actionFlag;
    }
    

    public ProtocolNotificationRequestBeanBase getNotificationRequestBean() {
        return notificationRequestBean;
    }

    public void setNotificationRequestBean(ProtocolNotificationRequestBeanBase notificationRequestBean) {
        this.notificationRequestBean = notificationRequestBean;
    }   
    
    
}
