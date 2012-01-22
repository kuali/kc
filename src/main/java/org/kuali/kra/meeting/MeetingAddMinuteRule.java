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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * 
 * This class implements the business when adding committee schedule minute.
 */
public class MeetingAddMinuteRule extends ResearchDocumentRuleBase implements BusinessRuleInterface<MeetingAddMinuteEvent> {

    private static final String ATTENDANCE_ENTRY_TYPE = "2";
    private static final String PROTOCOL_ENTRY_TYPE = "3";
    private static final String COMM_SCHEDULE_ACT_ITEM_ENTRY_TYPE = "4";

    private static final String NEW_COMM_SCHD_MINUTE = "meetingHelper.newCommitteeScheduleMinute";
    private static final String DOT = ".";
    private static final String GENERATE_ATTENDANCE_FIELD = "generateAttendance";
    private static final String PROTOCOL_ID_FK_FIELD = "protocolIdFk";
    private static final String PROTOCOL_CONTINGENCY_CODE_FIELD = "protocolContingencyCode";
    private static final String COMM_SCHD_MINUTE_ACT_ITEMS_ID_FK_FIELD = "commScheduleActItemsIdFk";

    /**
     * Validates a new committee schedule minute, based on its minute entry type.
     * @param event
     * @return
     */
    public boolean processRules(MeetingAddMinuteEvent event) {
        boolean isValid = true;
        
        CommitteeScheduleMinute minute = event.getMeetingHelper().getNewCommitteeScheduleMinute();
        
        isValid &= validateFields(minute);

        if (ATTENDANCE_ENTRY_TYPE.equals(minute.getMinuteEntryTypeCode())) {
            List<CommitteeScheduleAttendance> attendances = event.getMeetingHelper().getCommitteeSchedule().getCommitteeScheduleAttendances();
            isValid &= validateAttendance(minute, attendances);
        } else if (PROTOCOL_ENTRY_TYPE.equals(minute.getMinuteEntryTypeCode())) {
            isValid &= validateProtocol(minute);
        } else if (COMM_SCHEDULE_ACT_ITEM_ENTRY_TYPE.equals(minute.getMinuteEntryTypeCode())) {
            List<CommScheduleActItem> items = event.getMeetingHelper().getCommitteeSchedule().getCommScheduleActItems();
            isValid &= validateActionItem(minute, items);
        }

        return isValid;
    }
    
    private boolean validateFields(CommitteeScheduleMinute committeeScheduleMinute) {
        boolean isValid = true;
        
        GlobalVariables.getMessageMap().addToErrorPath(NEW_COMM_SCHD_MINUTE);
        getDictionaryValidationService().validateBusinessObject(committeeScheduleMinute);
        GlobalVariables.getMessageMap().removeFromErrorPath(NEW_COMM_SCHD_MINUTE);
        isValid &= GlobalVariables.getMessageMap().hasNoErrors();
        
        return isValid;
    }
    
    /**
     * Runs the validation rules a minute of type Attendance
     * @param committeeScheduleMinute
     * @param attendances
     * @return
     */
    private boolean validateAttendance(CommitteeScheduleMinute committeeScheduleMinute, List<CommitteeScheduleAttendance> attendances) {
        boolean isValid = true;
        
        if (committeeScheduleMinute.isGenerateAttendance() && attendances.isEmpty()) {
            reportError(NEW_COMM_SCHD_MINUTE + DOT + GENERATE_ATTENDANCE_FIELD, KeyConstants.ERROR_EMPTY_ATTENDANCE);
            isValid = false;
        }
        
        return isValid;
    }
    
    /**
     * Runs the validation rules a minute of type Protocol
     * @param committeeScheduleMinute
     * @return
     */
    private boolean validateProtocol(CommitteeScheduleMinute committeeScheduleMinute) {
        boolean isValid = true;
        
        if (committeeScheduleMinute.getProtocolIdFk() == null) {
            reportError(NEW_COMM_SCHD_MINUTE + DOT + PROTOCOL_ID_FK_FIELD, KeyConstants.ERROR_EMPTY_PROTOCOL);
            isValid = false;
        }
        if (StringUtils.isNotBlank(committeeScheduleMinute.getProtocolContingencyCode())) {
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put(PROTOCOL_CONTINGENCY_CODE_FIELD, committeeScheduleMinute.getProtocolContingencyCode());
            if (getBusinessObjectService().findByPrimaryKey(ProtocolContingency.class, fieldValues) == null) {
                reportError(NEW_COMM_SCHD_MINUTE + DOT + PROTOCOL_CONTINGENCY_CODE_FIELD, KeyConstants.ERROR_EMPTY_PROTOCOL_CONTINGENCY);
                isValid = false;
            }
        }
        
        return isValid;
    }
    
    /**
     * Runs the validation rules a minute of type Action Item (Other Business)
     * @param committeeScheduleMinute
     * @param commScheduleActItems
     * @return
     */
    private boolean validateActionItem(CommitteeScheduleMinute committeeScheduleMinute, List<CommScheduleActItem> commScheduleActItems) {
        boolean isValid = true;
        
        if (commScheduleActItems.isEmpty()) {
            reportError(NEW_COMM_SCHD_MINUTE + DOT + COMM_SCHD_MINUTE_ACT_ITEMS_ID_FK_FIELD, KeyConstants.ERROR_EMPTY_ACTION_ITEMS);
            isValid = false;
        }
        if (committeeScheduleMinute.getCommScheduleActItemsIdFk() == null) {
            reportError(NEW_COMM_SCHD_MINUTE + DOT + COMM_SCHD_MINUTE_ACT_ITEMS_ID_FK_FIELD, KeyConstants.ERROR_EMPTY_ACTION_ITEMS_DESCRIPTION);
            isValid = false;
        }
        
        return isValid;
    }

}