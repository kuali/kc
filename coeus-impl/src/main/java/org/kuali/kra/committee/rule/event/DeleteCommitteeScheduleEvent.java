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
package org.kuali.kra.committee.rule.event;

import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.common.committee.impl.document.CommitteeDocumentBase;
import org.kuali.coeus.common.committee.impl.rule.event.DeleteCommitteeScheduleEventBase;
import org.kuali.coeus.common.committee.impl.rules.DeleteCommitteeScheduleRuleBase;
import org.kuali.coeus.common.committee.impl.web.struts.form.schedule.ScheduleData;
import org.kuali.kra.committee.rules.DeleteCommitteeScheduleRule;
import org.kuali.rice.krad.document.Document;

import java.util.List;

/**
 * 
 * This class is the rule event for deleting committee schedule.
 */
public class DeleteCommitteeScheduleEvent extends DeleteCommitteeScheduleEventBase {
    
    public DeleteCommitteeScheduleEvent(String errorPathPrefix, CommitteeDocumentBase document, ScheduleData scheduleData, List<CommitteeScheduleBase> committeeSchedules, ErrorType type) {
        super(errorPathPrefix, document, scheduleData, committeeSchedules, type);
    }

    
    public DeleteCommitteeScheduleEvent(String errorPathPrefix, Document document, ScheduleData scheduleData, List<CommitteeScheduleBase> committeeSchedules, ErrorType type) {
        this(errorPathPrefix, (CommitteeDocumentBase) document, scheduleData, committeeSchedules, type);
    }

    @Override
    protected DeleteCommitteeScheduleRuleBase getNewDeleteCommitteeScheduleRuleInstanceHook() {
        return new DeleteCommitteeScheduleRule();
    }
    
}
