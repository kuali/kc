/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
