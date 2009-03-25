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
package org.kuali.kra.committee.rule.event;

import java.util.List;

import org.kuali.core.document.Document;
import org.kuali.core.rule.BusinessRule;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.rule.TimeCommitteeScheduleRule;
import org.kuali.kra.committee.web.struts.form.schedule.ScheduleData;

public class CommitteeScheduleTimeEvent extends CommitteeScheduleEventBase {
    
    public static final String MSG = "adding CommitteeSchedule to document ";
    
    public CommitteeScheduleTimeEvent(String errorPathPrefix, CommitteeDocument document, ScheduleData scheduleData, List<CommitteeSchedule> committeeSchedules, Event type) {
        super(MSG + getDocumentId(document), errorPathPrefix, document, scheduleData, committeeSchedules, type);
    }
    
    public CommitteeScheduleTimeEvent(String errorPathPrefix, Document document, ScheduleData scheduleData, List<CommitteeSchedule> committeeSchedules, Event type) {
        this(errorPathPrefix, (CommitteeDocument)document, scheduleData, committeeSchedules, type);
    }
    
    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    @SuppressWarnings("unchecked")
    public Class getRuleInterfaceClass() {
        return TimeCommitteeScheduleRule.class;
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.core.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((TimeCommitteeScheduleRule)rule).processCommitteeScheduleTimeRules(this);
    }
}