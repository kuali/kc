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
package org.kuali.kra.irb.actions.assigncmtsched;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * Validate the assignment of a protocol to a committee.
 */
public class ProtocolAssignCmtSchedRule extends KcTransactionalDocumentRuleBase implements ExecuteProtocolAssignCmtSchedRule {
   
    /**
     * Verify that a committee has been selected.
     * @see org.kuali.kra.irb.actions.assigncmtsched.ExecuteProtocolAssignCmtSchedRule#processAssignToCommitteeSchedule(org.kuali.kra.irb.ProtocolDocument, org.kuali.kra.irb.actions.assigncmtsched.ProtocolAssignCmtSchedBean)
     */
    @SuppressWarnings("deprecation")
    public boolean processAssignToCommitteeSchedule(ProtocolDocument document, ProtocolAssignCmtSchedBean actionBean) {
        boolean valid = true;
        if (StringUtils.isBlank(actionBean.getNewCommitteeId())) {
            valid = false;
            GlobalVariables.getMessageMap().putError(Constants.PROTOCOL_ASSIGN_CMT_SCHED_ACTION_PROPERTY_KEY + ".committeeId", 
                                                   KeyConstants.ERROR_PROTOCOL_COMMITTEE_NOT_SELECTED);
        }
        if (document.getProtocol().isFollowupAction(ProtocolActionType.NOTIFIED_COMMITTEE) && StringUtils.isBlank(actionBean.getNewScheduleId())) {
            valid = false;
            GlobalVariables.getMessageMap().putError(Constants.PROTOCOL_ASSIGN_CMT_SCHED_ACTION_PROPERTY_KEY + ".scheduleId", 
                                                   KeyConstants.ERROR_PROTOCOL_SCHEDULE_NOT_SELECTED);
        }
        return valid;
    }
}
