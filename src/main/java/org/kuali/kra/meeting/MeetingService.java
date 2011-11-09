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
package org.kuali.kra.meeting;

import java.util.List;

import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.rice.kns.bo.PersistableBusinessObject;

/**
 * 
 * This class is to provide service for meeting management
 */
public interface MeetingService {
    
    /**
     * 
     * This method is to save the changed meeting data properly.
     * @param committeeSchedule
     * @param deletedBos
     */
    public void saveMeetingDetails(CommitteeSchedule committeeSchedule, List<? extends PersistableBusinessObject> deletedBos);
    
    /**
     * This method will refresh the protocol submission data associated with the given schedule.
     * @param committeeSchedule
     */
    public void refreshProtocolSubmissionsFor(CommitteeSchedule committeeSchedule);

    
    /**
     * 
     * This method is for dwr/ajax to fetch protocol contingency description when user enter protocol contingency code.
     * @param protocolContingencyCode
     * @return
     */
    public String getStandardReviewComment(String protocolContingencyCode);
    
    
    /**
     * 
     * This method is to add new other action to other action list.
     * @param committeeSchedule
     * @param newOtherAction
     */
    public void addOtherAction(CommScheduleActItem newOtherAction, CommitteeSchedule committeeSchedule);    
   
    /**
     * 
     * This method is to delete the selected other action from the list.
     * 
     * @param committeeSchedule
     * @param itemNumber
     * @param deletedOtherActions
     */
    public void deleteOtherAction(CommitteeSchedule committeeSchedule, int itemNumber, List<CommScheduleActItem> deletedOtherActions);

    /**
     * 
     * This method is to move member from present list to absent list.
     * 
     * @param memberPresentBeans
     * @param memberAbsentBeans
     * @param itemNumber
     */
    public void markAbsent(List<MemberPresentBean> memberPresentBeans, List<MemberAbsentBean> memberAbsentBeans,  int itemNumber);

    /**
     * 
     * This method is to add new committee schedule minute entry to minute entry list.
     * @param meetingHelper
     */
    public void addCommitteeScheduleMinute(MeetingHelper meetingHelper);
    
    /**
     * 
     * This method is to delete committee schedule minute entry from minute entry list.
     * @param committeeSchedule
     * @param deletedCommitteeScheduleMinutes
     * @param itemNumber
     */
    public void deleteCommitteeScheduleMinute(CommitteeSchedule committeeSchedule, List<CommitteeScheduleMinute> deletedCommitteeScheduleMinutes, int itemNumber);
    
    /**
     * 
     * This method is to populate meeting form/helper data when meeting page is loaded.
     * @param meetingHelper
     * @param commSchedule
     * @param lineNumber
     */
    public void populateFormHelper(MeetingHelper meetingHelper, CommitteeSchedule commSchedule, int lineNumber);
    
    /**
     * 
     * This method is to move member absent to member present list.
     * @param meetingHelper
     * @param itemNumber
     */
    public void presentVoting(MeetingHelper meetingHelper, int itemNumber);
    
    /**
     * 
     * This method is to move absent member to other present.
     * @param meetingHelper
     * @param itemNumber
     */
    public void presentOther(MeetingHelper meetingHelper, int itemNumber);

    /**
     * 
     * This method is to add the selected person or rolodex to other present list.
     * @param meetingHelper
     */
    public void addOtherPresent(MeetingHelper meetingHelper);

    /**
     * 
     * This method is to delete other present. if the deleted other present is a member, then this person will be added to absent
     * list.
     * @param meetingHelper
     * @param itemNumber
     */
    public void deleteOtherPresent(MeetingHelper meetingHelper, int itemNumber);
    
}
