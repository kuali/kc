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
package org.kuali.kra.iacuc.actions.submit;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.common.committee.service.CommonCommitteeService;
import org.kuali.kra.iacuc.actions.IacucActionHelper;
import org.kuali.kra.iacuc.actions.IacucProtocolActionBean;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.protocol.actions.ActionHelper;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewerBean;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmitAction;
import org.springframework.util.AutoPopulatingList;

/**
 * This class is really just a "form" for submitting a protocol for review in the Submit for Review Action.
 */
public class IacucProtocolSubmitAction extends IacucProtocolActionBean implements ProtocolSubmitAction {


    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 4456005318168340284L;
    
    private String submissionTypeCode = "";
    private String protocolReviewTypeCode = "";
    private String submissionQualifierTypeCode = "";
    protected String committeeId = "";
    protected String scheduleId = "";
    protected boolean committeeIdChanged = false;
    protected boolean scheduleIdChanged = false;
    protected boolean reviewerListAvailable = false;
    protected int numberOfReviewers = 0;

    /*
     * We use a AutoPopulatingList because we need it to grow. When JavaScript is enabled, it will display the list of reviewers. When
     * the form is submitted, this list will automatically grow to accommodate all of the reviewers.
     */
    protected List<ProtocolReviewerBean> reviewers = new AutoPopulatingList<ProtocolReviewerBean>(IacucProtocolReviewerBean.class);
    
// TODO *********commented the code below during IACUC refactoring*********     
//    private List<ExpeditedReviewCheckListItem> expeditedReviewCheckList = null;
//    private List<ExemptStudiesCheckListItem> exemptStudiesCheckList = null;
    
    private String newCommitteeId = "";
    private String newScheduleId = "";


    private boolean javascriptEnabled = true;

    /**
     * Constructs a ProtocolSubmitAction.
     * 
     * @param actionHelper Reference back to the action helper for this bean
     */
    public IacucProtocolSubmitAction(ActionHelper actionHelper) {
        super(actionHelper);
    }

    protected Class<? extends ProtocolReviewerBean> getProtocolReviewerBeanClassHook() {
        return IacucProtocolReviewerBean.class;        
    }

    /**
     * Prepare the Submit for Review for rendering with JSP.
     */
    public void prepareView() {
        /*
         * The Submit for Review has to work with and without JavaScript. When JavaScript is enabled, the newly selected committee
         * and schedule are what we want to continue to display. When JavaScript is disabled, we have to change the schedule dates
         * that we display if the committee has changed.
         */
        if (!this.getJavascriptEnabled()) {
            if ((!StringUtils.isBlank(this.committeeId)) && (!this.committeeIdChanged) && (!StringUtils.isBlank(this.scheduleId))) {
                if (this.scheduleIdChanged) {
                    reviewers.clear();
                   // buildReviewers();
                }
            }
            else {
                reviewers.clear();
                this.reviewerListAvailable = false;
            }
        }
        else {
            // use the numberOfReviewers property (sent in as a hidden input field) to truncate the reviewers collection if needed
            this.reviewers.subList(this.numberOfReviewers, this.reviewers.size()).clear();            
        }
    }
    

    public void setNumberOfReviewers(int numberOfReviewers) {
        this.numberOfReviewers = numberOfReviewers;
    }


    public CommonCommitteeService getCommitteeService() {
        return KraServiceLocator.getService(CommonCommitteeService.class);
    }

    public String getSubmissionTypeCode() {
        return submissionTypeCode;
    }

    public void setSubmissionTypeCode(String submissionTypeCode) {
        this.submissionTypeCode = submissionTypeCode;
    }

    public String getProtocolReviewTypeCode() {
        return protocolReviewTypeCode;
    }

    public void setProtocolReviewTypeCode(String protocolReviewTypeCode) {
        this.protocolReviewTypeCode = protocolReviewTypeCode;
    }

    public String getSubmissionQualifierTypeCode() {
        return submissionQualifierTypeCode;
    }

    public void setSubmissionQualifierTypeCode(String submissionQualifierTypeCode) {
        this.submissionQualifierTypeCode = submissionQualifierTypeCode;
    }

    public String getCommitteeId() {
        return committeeId;
    }

    public void setCommitteeId(String committeeId) {
        this.committeeIdChanged = true;
        if (StringUtils.equals(this.committeeId, committeeId)) {
            this.committeeIdChanged = false;
        }
        this.committeeId = committeeId;

        // TODO: to be removed eventually
        this.newCommitteeId = committeeId;
    }

    // TODO: to be removed eventually deleted
    public void setNewCommitteeId(String id) {
        this.newCommitteeId = id;
    }

    // TODO: to be removed eventually with references renamed to getCommitteeId()
    public String getNewCommitteeId() {
        return newCommitteeId;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleIdChanged = true;
        if (StringUtils.equals(this.scheduleId, scheduleId)) {
            this.scheduleIdChanged = false;
        }
        this.scheduleId = scheduleId;
        
        // TODO: to be removed eventually
        this.newScheduleId = scheduleId;
    }

    // TODO: to be removed eventually with references renamed to getScheduleId()
    public String getNewScheduleId() {
        return newScheduleId;
    }

    public boolean isReviewerListAvailable() {
        return reviewerListAvailable;
    }

    public List<ProtocolReviewerBean> getReviewers() {
        return reviewers;
    }

    public ProtocolReviewerBean getReviewer(int i) {
        return reviewers.get(i);
    }

    /**
     * We display the reviewers in two columns. These are the reviewers in the left column.
     * 
     * @return
     */
    public List<ProtocolReviewerBean> getLeftReviewers() {
        List<ProtocolReviewerBean> leftReviewers = new ArrayList<ProtocolReviewerBean>();
        for (int i = 0; i < (reviewers.size() + 1) / 2; i++) {
            leftReviewers.add(reviewers.get(i));
        }
        return leftReviewers;
    }

    /**
     * We display the reviewers in two columns. These are the reviewers in the right column.
     * 
     * @return
     */
    public List<ProtocolReviewerBean> getRightReviewers() {
        List<ProtocolReviewerBean> rightReviewers = new ArrayList<ProtocolReviewerBean>();
        for (int i = (reviewers.size() + 1) / 2; i < reviewers.size(); i++) {
            rightReviewers.add(reviewers.get(i));
        }
        return rightReviewers;
    }

    public void setReviewers(List<ProtocolReviewerBean> reviewerBeans) {
        this.reviewers = reviewerBeans;
    }

    public boolean getJavascriptEnabled() {
        return javascriptEnabled;
    }

    public void setJavascriptEnabled(boolean javascriptEnabled) {
        this.javascriptEnabled = javascriptEnabled;
    }

    /**
     * Constructs a ProtocolSubmitAction.
     * 
     * @param actionHelper Reference back to the action helper for this bean
     */
    public IacucProtocolSubmitAction(IacucActionHelper actionHelper) {
        super(actionHelper);
    }

    
    /**
     * Create the list of reviewers based upon the currently selected committee and schedule.
     */
   /* private void buildReviewers() {
        this.reviewerListAvailable = true;
        List<CommitteeMembership> members = getProtocol().filterOutProtocolPersonnel(
                getCommitteeService().getAvailableMembers(this.committeeId, this.scheduleId));
        for (CommitteeMembership member : members) {
            ProtocolReviewerBean reviewer = new ProtocolReviewerBean(member);
            reviewers.add(reviewer);
        }
    }*/
    

}
