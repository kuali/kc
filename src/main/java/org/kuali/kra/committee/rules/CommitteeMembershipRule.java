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
package org.kuali.kra.committee.rules;

import java.util.List;

import org.apache.axis.utils.StringUtils;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.rule.AddCommitteeMembershipRule;
import org.kuali.kra.committee.rule.event.AddCommitteeMembershipEvent;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;

/**
 * 
 * This class contains the rules to validate a <code>{@link CommitteeMembership}</code>
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class CommitteeMembershipRule extends CommitteeDocumentRule 
                                     implements AddCommitteeMembershipRule,
                                                SaveCommitteeMembershipRule {

    /**
     * 
     * Process the validation rules for an <code>{@link AddCommitteeMembershipEvent}</code>.
     * --explain rules --
     * 
     * @param addProtocolParticipantEvent
     * @return <code>true</code> if valid, <code>false</code> otherwise
     */
    public boolean processAddCommitteeMembershipBusinessRules(AddCommitteeMembershipEvent addCommitteeMembershipEvent) {
        final String PROPERTY_NAME_PERSON_NAME = "membershipHelper.newCommitteeMembership.personName";
        boolean isValid = true;
        CommitteeMembership committeeMembership = addCommitteeMembershipEvent.getCommitteeMembership();
        if ( (StringUtils.isEmpty(committeeMembership.getPersonId())) && (StringUtils.isEmpty(committeeMembership.getRolodexId())) ) { 
            isValid = false;
            reportError(PROPERTY_NAME_PERSON_NAME, KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_PERSON_NOT_SPECIFIED);
        } else if (isDuplicate((CommitteeDocument) addCommitteeMembershipEvent.getDocument(), committeeMembership)){
            isValid = false;
            reportError(PROPERTY_NAME_PERSON_NAME, KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_PERSON_DUPLICATE);
        }
        return isValid;
    }
    /**
     * 
     * Check if the <code>{@link CommitteeMembership}</code> is already part of the <code {@link Committee}</code>.
     * 
     * @param committeeDocument to which the committee member is added
     * @param newCommitteeMembership which should be added to the committee.
     * @return <code>true</code> if it is a duplicate, <code>false</code> otherwise
     */
    private boolean isDuplicate(CommitteeDocument committeeDocument, CommitteeMembership newCommitteeMembership) {
        boolean isDuplicate = false;
        List<CommitteeMembership> committeeMemberships = committeeDocument.getCommittee().getCommitteeMemberships();
        for (CommitteeMembership committeeMembership : committeeMemberships) {
            if (committeeMembership.equals(newCommitteeMembership)) {
                isDuplicate = true;
            }
        }
        return isDuplicate;        
    }

}
