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
package org.kuali.kra.irb.onlinereview.dao;

import java.util.List;

import org.apache.commons.httpclient.protocol.Protocol;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.kra.meeting.CommitteeScheduleMinute;

public interface ProtocolOnlineReviewDao {
    /*
     * Returns a list of CommitteeMembership records.  The records returned are committee members
     * that are not reviewers on the provided submission.
     * 
     * @param submission The submission for which you wish to generate the list of available committtee members for.
     */
    public List<CommitteeMembership> findAvailableCommitteeMembers(ProtocolSubmission submission);
    
    //public List<ProtocolOnlineReview> findProtocolOnlineReviews( )
    
    public List<CommitteeScheduleMinute> findAllFinalProtocolOnlineReviewComments(Protocol protocol, ProtocolSubmission submission);
    
    public List<ProtocolOnlineReview> findAllFinalProtocolOnlineReviews(Protocol protocol, ProtocolSubmission submission);
    
    
}
