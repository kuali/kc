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
package org.kuali.kra.common.committee.rule.event;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.kra.common.committee.bo.CommitteeScheduleBase;
import org.kuali.kra.common.committee.document.CommitteeDocumentBase;
import org.kuali.kra.common.committee.rules.CommitteeScheduleWeekDayRule;
import org.kuali.kra.common.committee.web.struts.form.schedule.ScheduleData;
import org.kuali.rice.krad.document.Document;

import java.util.List;

public class CommitteeScheduleWeekDayEvent extends CommitteeScheduleEventBase<CommitteeScheduleWeekDayRule> {
    
    public static final String MSG = "adding CommitteeScheduleBase to document ";
    
    public CommitteeScheduleWeekDayEvent(String errorPathPrefix, CommitteeDocumentBase document, ScheduleData scheduleData, List<CommitteeScheduleBase> committeeSchedules, ErrorType type) {
        super(MSG + getDocumentId(document), errorPathPrefix, document, scheduleData, committeeSchedules, type);
    }
    
    public CommitteeScheduleWeekDayEvent(String errorPathPrefix, Document document, ScheduleData scheduleData, List<CommitteeScheduleBase> committeeSchedules, ErrorType type) {
        this(errorPathPrefix, (CommitteeDocumentBase)document, scheduleData, committeeSchedules, type);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public KcBusinessRule getRule() {
        return new CommitteeScheduleWeekDayRule();
    }
}
