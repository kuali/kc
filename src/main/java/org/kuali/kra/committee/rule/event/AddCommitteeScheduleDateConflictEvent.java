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
import org.kuali.kra.committee.rule.AddCommitteeScheduleDateConflictRule;
import org.kuali.kra.committee.web.struts.form.schedule.ScheduleData;

public class AddCommitteeScheduleDateConflictEvent extends CommitteeScheduleEventBase {

    public AddCommitteeScheduleDateConflictEvent(String errorPathPrefix, CommitteeDocument document, ScheduleData scheduleData, List<CommitteeSchedule> committeeSchedules) {
        super("adding CommitteeSchedule to document " + getDocumentId(document), errorPathPrefix, document, scheduleData, committeeSchedules);
    }
    
    public AddCommitteeScheduleDateConflictEvent(String errorPathPrefix, Document document, ScheduleData scheduleData, List<CommitteeSchedule> committeeSchedules) {
        this(errorPathPrefix, (CommitteeDocument)document, scheduleData, committeeSchedules);
    }

    @SuppressWarnings("unchecked")
    public Class getRuleInterfaceClass() {
        return AddCommitteeScheduleDateConflictRule.class;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AddCommitteeScheduleDateConflictRule)rule).processAddCommitteeScheduleRuleBusinessRules(this);
    }

}
