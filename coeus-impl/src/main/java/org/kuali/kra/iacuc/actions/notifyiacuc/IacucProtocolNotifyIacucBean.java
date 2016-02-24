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
package org.kuali.kra.iacuc.actions.notifyiacuc;

import org.kuali.kra.iacuc.actions.IacucActionHelper;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.request.IacucProtocolRequestBean;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionType;

import java.io.Serializable;

/**
 * This class is really just a "form" for notifying the IRB.
 */
public class IacucProtocolNotifyIacucBean extends  IacucProtocolRequestBean implements Serializable {
    
    private static final long serialVersionUID = -1572148230502384077L;
    private String submissionQualifierTypeCode;
    private String reviewTypeCode;
    private String comment = "";
    
    /**
     * Constructs a ProtocolNotifyIrbBean.
     * @param actionHelper Reference back to the action helper for this bean
     */
    public IacucProtocolNotifyIacucBean(IacucActionHelper actionHelper, String beanName) {
        super(actionHelper, IacucProtocolActionType.NOTIFY_IACUC, IacucProtocolSubmissionType.NOTIFY_IACUC, beanName);
        setCommitteeId(actionHelper.getProtocol().getProtocolSubmission().getCommitteeId());
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
