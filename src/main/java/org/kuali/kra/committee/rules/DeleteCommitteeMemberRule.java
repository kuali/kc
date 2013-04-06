/*
 * Copyright 2005-2013 The Kuali Foundation
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

import org.kuali.kra.committee.service.CommitteeMembershipService;
import org.kuali.kra.common.committee.rules.DeleteCommitteeMemberRuleBase;
import org.kuali.kra.common.committee.service.CommitteeMembershipServiceBase;

/**
 * 
 * This class is to implement business rule for deleting committee member.
 */
public class DeleteCommitteeMemberRule extends DeleteCommitteeMemberRuleBase {

    @Override
    protected Class<? extends CommitteeMembershipServiceBase> getCommitteeMembershipServiceClassHook() {
        return CommitteeMembershipService.class;
    }
    
// TODO ********************** commented out during IRB backfit ************************    
//    private static final String ID = "document.committeeList[0].committeeMemberships[";
//    private static final String AS_REVIEWER = "as the person is a reviewer of the protocol";
//    private static final String AS_ATTENDANCE = "as the person has attended a schedule meeting";
//    private CommitteeMembershipService committeeMembershipService;
//    /**
//     * If member is assigned as a reviewer or as attendance of a meeting, then member can not be deleted.
//     * @see org.kuali.kra.rule.BusinessRuleInterface#processRules(org.kuali.kra.rule.event.KraDocumentEventBaseExtension)
//     */
//    public boolean processRules(DeleteCommitteeMemberEvent event) {
//
//        boolean rulePassed = true;
//        int i = 0;
//        for (CommitteeMembership member : event.getCommitteeMemberships()) {
//            if (member.isDelete() && getCommitteeMembershipService().isMemberAssignedToReviewer(member,
//                    ((CommitteeDocument) event.getDocument()).getCommittee().getCommitteeId())) {
//                reportError(ID + i + "].delete", KeyConstants.ERROR_COMMITTEEMEMBER_DELETE, AS_REVIEWER);
//                rulePassed = false;
//            }
//            if (member.isDelete() && getCommitteeMembershipService().isMemberAttendedMeeting(member,
//                    ((CommitteeDocument) event.getDocument()).getCommittee().getCommitteeId())) {
//                reportError(ID + i + "].delete", KeyConstants.ERROR_COMMITTEEMEMBER_DELETE, AS_ATTENDANCE);
//                rulePassed = false;
//            }
//            i++;
//        }
//        return rulePassed;
//    }
//
//    private CommitteeMembershipService getCommitteeMembershipService() {
//        if (committeeMembershipService == null) {
//            committeeMembershipService = KraServiceLocator.getService(CommitteeMembershipService.class);
//        }
//        return committeeMembershipService;
//    }

}
