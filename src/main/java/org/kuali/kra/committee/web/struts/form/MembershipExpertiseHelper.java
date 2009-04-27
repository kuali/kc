/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.committee.web.struts.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.committee.bo.CommitteeMembershipExpertise;

public class MembershipExpertiseHelper implements Serializable {
    
    /**
     * Each Helper must contain a reference to its document form
     * so that it can access the actual document.
     */
    private CommitteeForm form;

    // Needed when multipleValuesLookup populates a CommitteeMembership with the CommitteeMembershipExpertise,
    // so it know which CommitteeMembership should get them.
    private int memberIndex;

    private List<CommitteeMembershipExpertise> newCommitteeMembershipExpertise;
    
    public MembershipExpertiseHelper(CommitteeForm form) {
        setForm(form);
        setNewCommitteeMembershipExpertise(new ArrayList<CommitteeMembershipExpertise>());
    }

    public List<CommitteeMembershipExpertise> getNewCommitteeMembershipExpertise() {
        if (getForm().getCommitteeDocument().getCommittee().getCommitteeMemberships().size() > this.newCommitteeMembershipExpertise.size()) {
            this.newCommitteeMembershipExpertise.add(this.newCommitteeMembershipExpertise.size(), new CommitteeMembershipExpertise());
        }
        return newCommitteeMembershipExpertise;
    }

    public void setNewCommitteeMembershipExpertise(List <CommitteeMembershipExpertise> newCommitteeMembershipExpertise) {
        this.newCommitteeMembershipExpertise = newCommitteeMembershipExpertise;
    }

    public CommitteeForm getForm() {
        return form;
    }

    public void setForm(CommitteeForm form) {
        this.form = form;
    }

    public void setMemberIndex(int memberIndex) {
        this.memberIndex = memberIndex;
    }

    public int getMemberIndex() {
        return memberIndex;
    }

}
