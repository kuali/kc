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
package org.kuali.kra.iacuc.actions;

import org.kuali.kra.protocol.actions.ProtocolActionTypeBase;

public class IacucProtocolActionType extends ProtocolActionTypeBase { 

    private static final long serialVersionUID = 162958759286804034L;

    // As ugly as it is, we lay out all the potential values here so we can use them in
    // business logic elsewhere in the IACUC module.
    public static final String IACUC_PROTOCOL_CREATED = "100";
    public static final String SUBMITTED_TO_IACUC = "101";
    public static final String AMENDMENT_CREATED = "102";
    public static final String CONTINUATION = "103";
    public static final String RENEWAL_CREATED = "104";
    public static final String CONTINUATION_AMENDMENT = "105";
    public static final String RENEWAL_AMENDMENT = "106";
    public static final String REQUEST_DEACTIVATE = "107";
    public static final String REQUEST_LIFT_HOLD = "108";
    public static final String NOTIFIED_COMMITTEE = "109";
    public static final String CORRESPONDENCE_GENERATED = "110";
    public static final String RENEWAL_REMINDER_GENERATED = "111";
    public static final String IACUC_REMINDER_GENERATED = "112";
    
    public static final String ADMINISTRATIVE_CORRECTION = "113";
    public static final String NOTIFY_IACUC = "114";
    public static final String IACUC_WITHDRAWN = "115";
    public static final String IACUC_ABANDON = "117";
    
    public static final String IACUC_WITHDRAW_SUBMISSION = "131";
    
    public static final String ASSIGNED_TO_AGENDA = "200";
    public static final String REMOVED_FROM_AGENDA = "201";
    public static final String RESCHEDULED = "202";
    public static final String TABLED = "203";
    public static final String IACUC_APPROVED = "204";
    public static final String RESPONSE_APPROVAL = "205";
    public static final String IACUC_ACKNOWLEDGEMENT = "206";
    public static final String IACUC_REVIEW_NOT_REQUIRED = "207";
    public static final String LIFT_HOLD = "208";
    public static final String IACUC_MINOR_REVISIONS_REQUIRED = "209";
    public static final String RETURNED_TO_PI = "210";
    public static final String IACUC_MAJOR_REVISIONS_REQUIRED = "211";
    public static final String DESIGNATED_REVIEW_APPROVAL = "212";
    public static final String IACUC_REVISIONS_REQUIRED = "213";
    public static final String FULL_COMMITEE_REQUIRED = "214";
    public static final String ADMINISTRATIVE_APPROVAL = "215";
    public static final String ADMINISTRATIVELY_INCOMPLETE = "216";
    public static final String ADMINISTRATIVELY_WITHDRAWN = "300";
    public static final String IACUC_DISAPPROVED = "301";
    public static final String EXPIRED = "302";
    public static final String DEACTIVATED = "303";
    public static final String ADMINISTRATIVELY_DEACTIVATED = "304"; 
    public static final String HOLD = "305"; 
    public static final String TERMINATED = "306"; 
    public static final String SUSPENDED = "307";     
    public static final String MODIFY_PROTOCOL_SUBMISSION = "309";
    public static final String MANAGE_REVIEW_COMMENTS = "310";
    public static final String IACUC_REQUEST_SUSPEND = "311";
    public static final String REMOVE_FROM_AGENDA = "312";
    public static final String ASSIGNED_TO_COMMITTEE = "313";

    public static final String AMENDMENT_CREATED_NOTIFICATION = "323";
    public static final String RENEWAL_CREATED_NOTIFICATION = "322";
    public static final String CONTINUATION_CREATED_NOTIFICATION = "322";
    
    public static final String ASSIGN_REVIEWER = "901";

    public static final String IACUC_DELETED =  "906";
    
    //We need a unique Action code to map if the option to be chosen is 'Approve' since we need to enable 'Approve' action subsequently 
    public static final String RECORD_COMMITTEE_DECISION = "318";
    
    public static final String MODIFY_AMENDMENT_SECTION = "125";
    
    // action type to be used only by workflow disapproval post-processpor
    public static final String REJECTED_IN_ROUTING = "401";
    
    //No corresponding actionTypeCode for these in Coeus  
    public static final String REVIEW_COMPLETE = "902";
    public static final String REVIEW_REJECTED = "903";
    public static final String FUNDING_SOURCE = "904";
    public static final String REVIEW_DELETED = "905";
    public static final String REVIEW_TYPE_DETERMINATION = "908";
    public static final String RENEWAL_WITH_AMENDMENT_CREATED = "909";

}
