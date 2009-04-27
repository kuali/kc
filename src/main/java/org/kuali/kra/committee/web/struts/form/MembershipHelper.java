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

import org.kuali.kra.committee.bo.CommitteeMembership;

public class MembershipHelper implements Serializable {
    
    /**
     * Each Helper must contain a reference to its document form
     * so that it can access the actual document.
     */
    private CommitteeForm form;

    private CommitteeMembership newCommitteeMembership;

    public MembershipHelper(CommitteeForm form) {
        setForm(form);
        setNewCommitteeMembership(new CommitteeMembership());
    }
    
    public void prepareView() {
    }

    public CommitteeMembership getNewCommitteeMembership() {
        return newCommitteeMembership;
    }

    public void setNewCommitteeMembership(CommitteeMembership newCommitteeMembership) {
        this.newCommitteeMembership = newCommitteeMembership;
    }

    public CommitteeForm getForm() {
        return form;
    }

    public void setForm(CommitteeForm form) {
        this.form = form;
    }
}
