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
package org.kuali.kra.common.committee.service;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.common.committee.bo.CommitteeBase;
import org.kuali.kra.common.committee.bo.CommitteeScheduleBase;
import org.kuali.kra.common.committee.meeting.CommitteeScheduleMinuteBase;
import org.kuali.kra.common.committee.web.struts.form.schedule.ScheduleData;


public interface CommitteeScheduleServiceBase<CS extends CommitteeScheduleBase<CS, CMT, ?, CSM>, 
                                                CMT extends CommitteeBase<CMT,?,CS>,
                                                CSM extends CommitteeScheduleMinuteBase<CSM, CS>> {
    
    /**
     * This method implementer must check if passed CommitteeScheduleBase is deleteable.
     * <br/>
     * Rule:
     * <br/>
     * Any past schedule date must not be allowed to delete.
     * <br/>
     * Allow delete if NO Protocol is assigned to CommitteeScheduleBase.
     * @param committeeSchedule
     * @return
     */
    public Boolean isCommitteeScheduleDeletable(CS committeeSchedule);
    
    /**
     * This method implementer must add new non conflicting, non-repeating schedule dates to existing CommitteeBase.CommitteeScheduleBase list.
     * @param scheduleData
     * @param committee
     * @throws ParseException
     */
    public void addSchedule(ScheduleData scheduleData, CMT committee) throws ParseException;
    
    /**
     * 
     * This method returns a list of minutes based on a protocol ID
     * @param protocolId
     * @return
     */
    public List<CSM> getMinutesByProtocol(Long protocolId);
    
    /**
     * 
     * This method returns a list of minutes based on a protocol submission ID
     * @param submissionID
     * @return
     */
    public List<CSM> getMinutesByProtocolSubmission(Long submissionID);
    
    /**
     * 
     * This method returns a list of minutes based on a schedule ID
     * @param scheduleId
     * @return
     */
    public List<CSM> getMinutesBySchedule(Long scheduleId);
    
    /**
     * 
     * This method returns a specific schedule minute based on a committee id
     * NOTE: a null is returned if the ID doesn't exist in the DB.
     * @param committeeId
     * @return
     */
    public CSM getCommitteeScheduleMinute(Long committeeScheduleId);
    
    /**
     * This method will downloadAttachment  to CommitteeScheduleAttachmentsBase.
     * @param committeScheduleAttachments
     * @return
     */
     public void downloadAttachment(KraPersistableBusinessObjectBase attachmentDataSource, HttpServletResponse response) throws Exception;
}
