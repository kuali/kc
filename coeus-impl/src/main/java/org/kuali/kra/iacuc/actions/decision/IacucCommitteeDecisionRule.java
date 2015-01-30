/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.iacuc.actions.decision;

import org.kuali.coeus.common.committee.impl.service.CommitteeScheduleAttendanceServiceBase;
import org.kuali.kra.iacuc.committee.service.IacucCommitteeScheduleAttendanceService;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.protocol.actions.decision.CommitteeDecisionRuleBase;

public class IacucCommitteeDecisionRule extends CommitteeDecisionRuleBase<IacucCommitteeDecision> {

    @Override
    protected String getNoCommentsForRevisionsErrorMessageHook() {
        return KeyConstants.ERROR_PROTOCOL_RECORD_COMMITEE_NO_MINOR_MAJOR_DISAPPROVE_REVIEWER_COMMENTS;
    }

    @Override
    protected Class<? extends CommitteeScheduleAttendanceServiceBase> getCommitteeScheduleAttendanceServiceClassHook() {
        return IacucCommitteeScheduleAttendanceService.class;
    }
    
}
