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
package org.kuali.kra.meeting;

import java.sql.Date;
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
     * This method is to get the last agenda generation date.
     * @param scheduleId
     * @return
     */
    public Date getAgendaGenerationDate(Long scheduleId);

    /**
     * 
     * This method is to save the changed meeting data properly.
     * @param committeeSchedule
     * @param deletedBos
     */
    public void SaveMeetingDetails(CommitteeSchedule committeeSchedule, List<? extends PersistableBusinessObject> deletedBos);

    
    /**
     * 
     * This method is for dwr/ajax to fetch protocol contingency description when user enter protocol contingency code.
     * @param protocolContingencyCode
     * @return
     */
    public String getStandardReviewComment(String protocolContingencyCode);
}
