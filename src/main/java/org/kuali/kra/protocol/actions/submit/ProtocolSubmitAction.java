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
package org.kuali.kra.protocol.actions.submit;

import java.io.Serializable;
import java.util.List;

import org.kuali.kra.common.committee.service.CommonCommitteeService;
import org.kuali.kra.protocol.actions.ProtocolActionBean;

/**
 * This class is really just a "form" for submitting a protocol for review in the Submit for Review Action.
 */
public interface ProtocolSubmitAction extends ProtocolActionBean, Serializable {

    public void prepareView();

    public void setNumberOfReviewers(int numberOfReviewers);

    public CommonCommitteeService getCommitteeService();

    public String getSubmissionTypeCode();

    public void setSubmissionTypeCode(String submissionTypeCode);

    public String getProtocolReviewTypeCode();

    public void setProtocolReviewTypeCode(String protocolReviewTypeCode);

    public String getSubmissionQualifierTypeCode();

    public void setSubmissionQualifierTypeCode(String submissionQualifierTypeCode);

    public String getCommitteeId();
    
    public void setCommitteeId(String committeeId);
    
    // TODO: to be removed eventually deleted
    public void setNewCommitteeId(String id);

    // TODO: to be removed eventually with references renamed to getCommitteeId()
    public String getNewCommitteeId();

    public String getScheduleId();

    public void setScheduleId(String scheduleId);

    // TODO: to be removed eventually with references renamed to getScheduleId()
    public String getNewScheduleId();

    public boolean isReviewerListAvailable();

    public List<ProtocolReviewerBean> getReviewers();

    public ProtocolReviewerBean getReviewer(int i);

    /**
     * We display the reviewers in two columns. These are the reviewers in the left column.
     * 
     * @return
     */
    public List<ProtocolReviewerBean> getLeftReviewers();
    
    /**
     * We display the reviewers in two columns. These are the reviewers in the right column.
     * 
     * @return
     */
    public List<ProtocolReviewerBean> getRightReviewers();

    public void setReviewers(List<ProtocolReviewerBean> reviewerBeans);
    
    public boolean getJavascriptEnabled();

    public void setJavascriptEnabled(boolean javascriptEnabled);

}
