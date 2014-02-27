/*
 * Copyright 2005-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.irb.actions.submit;

import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionQualifierTypeBase;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class tracks the data associated with the submisison of a protocol for review.
 */
public class ProtocolSubmission extends ProtocolSubmissionBase {


    private static final long serialVersionUID = 2158830045312905591L;
    
    private List<ProtocolExemptStudiesCheckListItem> exemptStudiesCheckList = new ArrayList<ProtocolExemptStudiesCheckListItem>();

    private List<ProtocolExpeditedReviewCheckListItem> expeditedReviewCheckList = new ArrayList<ProtocolExpeditedReviewCheckListItem>();
    
    public void setExemptStudiesCheckList(List<ProtocolExemptStudiesCheckListItem> exemptStudiesCheckList) {
        this.exemptStudiesCheckList = exemptStudiesCheckList;
    }

    public List<ProtocolExemptStudiesCheckListItem> getExemptStudiesCheckList() {
        return exemptStudiesCheckList;
    }

    public void setExpeditedReviewCheckList(List<ProtocolExpeditedReviewCheckListItem> expeditedReviewCheckList) {
        this.expeditedReviewCheckList = expeditedReviewCheckList;
    }

    public List<ProtocolExpeditedReviewCheckListItem> getExpeditedReviewCheckList() {
        return expeditedReviewCheckList;
    }

    /**
     * This method returns true if this submission has the same submission id as the passed in submission id.
     * @param o a ProtocolSubmission object to compare for equality
     * @return a boolean
     */
    @Override
    public boolean equals(Object o) {
        ProtocolSubmission ps = (ProtocolSubmission) o;
        try {
            return this.getSubmissionId().equals(ps.getSubmissionId());
        } catch (Exception e) {
            //an NPE would happen if the submission IDs aren't set.  
            return false;
        }
    }

    @Override
    protected ProtocolSubmissionQualifierTypeBase getNewInstanceProtocolSubmissionQualifierTypeHook() {
        return new ProtocolSubmissionQualifierType();
    }
    
    @Override
    public Committee getCommittee() {
        return (Committee)super.getCommittee();
    }
    
    @Override
    public CommitteeSchedule getCommitteeSchedule() {
        return (CommitteeSchedule)super.getCommitteeSchedule();
    }
}
