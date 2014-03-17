/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.committee.impl.meeting;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.kra.infrastructure.KeyConstants;

public class MeetingAddAttachmentsRule  extends KcTransactionalDocumentRuleBase implements KcBusinessRule<MeetingAddAttachmentsEvent> {

    String propertyPrefix ="meetingHelper.newCommitteeScheduleAttachments" ;
    private static final String ATTACHMENTS_ID = ".attachmentsTypeCode";
    private static final String ATTACHMENTS_FILE = ".document" ;
    private static final String noValue = "";
    
    @Override
    public boolean processRules(MeetingAddAttachmentsEvent event) {

        boolean isValid = true;
        CommitteeScheduleAttachmentsBase committeeScheduleAttachments = event.getMeetingHelper().getNewCommitteeScheduleAttachments();
        if (committeeScheduleAttachments.getAttachmentsTypeCode() == null){
            isValid = false;
            reportError(propertyPrefix+ATTACHMENTS_ID
                    , KeyConstants.ERROR_COMMITTEESCHEDULEATTACHMENTS_ATTACHMENTTYPE ); 
        }
        if (committeeScheduleAttachments.getNewFile().getFileName().equals(noValue)){
            isValid = false;
            reportError(propertyPrefix+ATTACHMENTS_FILE
                    , KeyConstants.ERROR_COMMITTEESCHEDULEATTACHMENTS_FILENAME ); 
        }
        return isValid;
    }
}
