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

import org.kuali.coeus.common.committee.impl.web.struts.form.schedule.ScheduleData;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.rules.CommitteeScheduleDeadlineDateRule;
import org.kuali.rice.krad.document.Document;

import java.util.List;

public class CommitteeScheduleDeadlineEvent extends CommitteeScheduleEventBase<CommitteeScheduleDeadlineDateRule> {
    
    public static final String MSG = "adding CommitteeSchedule to document ";
    
    public CommitteeScheduleDeadlineEvent(String errorPathPrefix, CommitteeDocument document, ScheduleData scheduleData, List<CommitteeSchedule> committeeSchedules, ErrorType type) {
        super(MSG + getDocumentId(document), errorPathPrefix, document, scheduleData, committeeSchedules, type);
    }
    
    public CommitteeScheduleDeadlineEvent(String errorPathPrefix, Document document, ScheduleData scheduleData, List<CommitteeSchedule> committeeSchedules, ErrorType type) {
        this(errorPathPrefix, (CommitteeDocument)document, scheduleData, committeeSchedules, type);
    }

    @SuppressWarnings("unchecked")
    @Override
    public KcBusinessRule getRule() {
        return new CommitteeScheduleDeadlineDateRule();
    }
}
