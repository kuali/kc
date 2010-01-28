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
package org.kuali.kra.irb.actions.reviewcomments;

import org.kuali.kra.committee.service.CommitteeScheduleService;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ReviewComments;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.kra.meeting.MinuteEntryType;
import org.kuali.rice.kns.service.BusinessObjectService;

public class ReviewerCommentsServiceImpl implements ReviewerCommentsService {
    
    private BusinessObjectService businessObjectService;
    
    /**
     * Set the Business Object Service.
     * @param businessObjectService
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public void persistReviewerComments(ReviewComments reviewComments, Protocol protocol) {
        int nextEntryNumber = 0;
        BusinessObjectService bos = KraServiceLocator.getService(BusinessObjectService.class);
        for (CommitteeScheduleMinute minuteToDelete : reviewComments.getCommentsToDelete()) {
            bos.delete(minuteToDelete);
        }
        reviewComments.resetComentsToDelete();
        for (CommitteeScheduleMinute minute : reviewComments.getComments()) {
            minute.setEntryNumber(nextEntryNumber);
            boolean doUpdate = false;
            if(minute.getCommScheduleMinutesId() != null){
                CommitteeScheduleService css = KraServiceLocator.getService(CommitteeScheduleService.class);
                CommitteeScheduleMinute existing = css.getCommitteeScheduleMinute(minute.getCommScheduleMinutesId());
                doUpdate = !minute.equals(existing);
            }else{
                doUpdate = true;
            }
            if(doUpdate){
                minute.setMinuteEntryTypeCode(MinuteEntryType.PROTOCOL);
                minute.setSubmissionIdFk(protocol.getProtocolSubmission().getSubmissionId());
                minute.setProtocolIdFk(protocol.getProtocolSubmission().getProtocolId());
                minute.setScheduleIdFk(protocol.getProtocolSubmission().getScheduleIdFk());
                bos.save(minute);
            }
            nextEntryNumber++;
        }
    }

}
