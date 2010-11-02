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
package org.kuali.kra.irb.actions.grantexemption;

import java.io.Serializable;
import java.sql.Date;

import org.kuali.kra.irb.actions.ActionHelper;
import org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsBean;

/**
 * This class is really just a "form" for granting an exemption.
 */
public class ProtocolGrantExemptionBean implements Serializable {

    private static final long serialVersionUID = -369805742361963806L;
    
    private Date approvalDate = new Date(System.currentTimeMillis());
    private String comments = "";
    private Date actionDate = new Date(System.currentTimeMillis());
    
    private ReviewCommentsBean reviewCommentsBean;
    
    /**
     * Constructs a ProtocolGrantExemptionBean.
     * @param actionHelper a reference back to the parent helper
     */
    public ProtocolGrantExemptionBean(ActionHelper actionHelper) {
        reviewCommentsBean = new ReviewCommentsBean();
        reviewCommentsBean.setProtocol(actionHelper.getProtocol());
    }
    
    public Date getApprovalDate() {
        return approvalDate;
    }
    
    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }
    
    public String getComments() {
        return comments;
    }
    
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    public Date getActionDate() {
        return actionDate;
    }
    
    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }
    
    public ReviewCommentsBean getReviewCommentsBean() {
        return reviewCommentsBean;
    }

    public void setReviewCommentsBean(ReviewCommentsBean reviewCommentsBean) {
        this.reviewCommentsBean = reviewCommentsBean;
    }

    public GrantExemptionCorrespondence getCorrespondence() {
        GrantExemptionCorrespondence correspondence = new GrantExemptionCorrespondence();
        return correspondence;
    }
    
}