/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.irb.actions.notifyirb;

import org.kuali.kra.irb.actions.ActionHelper;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.request.ProtocolRequestBean;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;

import java.io.Serializable;

/**
 * This class is really just a "form" for notifying the IRB.
 */
public class ProtocolNotifyIrbBean extends ProtocolRequestBean implements Serializable {
    
    private static final long serialVersionUID = -1572148230502384077L;
    private String submissionQualifierTypeCode;
    private String reviewTypeCode;
    private String comment = "";
    
    /**
     * Constructs a ProtocolNotifyIrbBean.
     * @param actionHelper Reference back to the action helper for this bean
     */
    public ProtocolNotifyIrbBean(ActionHelper actionHelper, String beanName) {
        super(actionHelper, ProtocolActionType.NOTIFY_IRB, ProtocolSubmissionType.NOTIFY_IRB, beanName);
    }

    public String getSubmissionQualifierTypeCode() {
        return submissionQualifierTypeCode;
    }

    public void setSubmissionQualifierTypeCode(String submissionQualifierTypeCode) {
        this.submissionQualifierTypeCode = submissionQualifierTypeCode;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public String getReviewTypeCode() {
        return reviewTypeCode;
    }

    public void setReviewTypeCode(String reviewTypeCode) {
        this.reviewTypeCode = reviewTypeCode;
    }
}
