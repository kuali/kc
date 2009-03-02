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

import org.kuali.core.document.Document;
import org.kuali.core.rule.BusinessRule;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.rule.AddCommitteeScheduleRule;
import org.kuali.kra.committee.web.struts.form.schedule.ScheduleData;

public class AddCommitteeScheduleEvent extends CommitteeScheduleEventBase {

    public AddCommitteeScheduleEvent(String errorPathPrefix, CommitteeDocument document, ScheduleData scheduleData) {
        super("adding CommitteeSchedule to document " + getDocumentId(document), errorPathPrefix, document, scheduleData);
    }
    
    public AddCommitteeScheduleEvent(String errorPathPrefix, Document document, ScheduleData scheduleData) {
        this(errorPathPrefix, (CommitteeDocument)document, scheduleData);
    }

    @SuppressWarnings("unchecked")
    public Class getRuleInterfaceClass() {
        return AddCommitteeScheduleRule.class;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AddCommitteeScheduleRule)rule).processAddCommitteeScheduleRuleBusinessRules(this);
    }

}
