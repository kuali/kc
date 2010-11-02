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
package org.kuali.kra.irb.actions.acknowledgement;

import java.io.Serializable;
import java.sql.Date;

import org.kuali.kra.irb.actions.ActionHelper;
import org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsBean;

/**
 * This class is the form data of IRB acknowledgement sub-panel.
 */
public class IrbAcknowledgementBean implements Serializable {

    private static final long serialVersionUID = 9084925682470626330L;
    
    private String comments = "";
    private Date actionDate = new Date(System.currentTimeMillis());
    
    private ReviewCommentsBean reviewCommentsBean;
    
    /**
     * Constructs a IrbAcknowledgementBean.
     * @param actionHelper a reference back to the parent helper
     */
    public IrbAcknowledgementBean(ActionHelper actionHelper) {
        reviewCommentsBean = new ReviewCommentsBean();
        reviewCommentsBean.setProtocol(actionHelper.getProtocol());
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
    
}