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
package org.kuali.coeus.common.committee.impl.rule.event;

import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.common.committee.impl.web.struts.form.schedule.ScheduleData;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.rice.krad.document.Document;

import java.util.List;

@SuppressWarnings("unchecked")
public abstract class CommitteeScheduleEventBase<Z extends KcBusinessRule> extends KcDocumentEventBaseExtension {
    
    /**
     * Enum helps identify type of error to respond.
     */
    public enum ErrorType {HARDERROR, SOFTERROR};
    
    private ScheduleData scheduleData;
    
    private List<CommitteeScheduleBase> committeeSchedules;
    
    private ErrorType type;
    
    public CommitteeScheduleEventBase(String description, String errorPathPrefix, Document document, ScheduleData scheduleData, List<CommitteeScheduleBase> committeeSchedules, ErrorType type) {        
        super(description, errorPathPrefix, document);
        this.scheduleData = scheduleData;
        this.committeeSchedules = committeeSchedules;
        this.type = type;
    }
    
    /**
     * This method should return instance of ScheduleDate.
     * @return
     */
    public ScheduleData getScheduleData() {
        return this.scheduleData;
    }
    
    /**
     * This method should return instance of CommitteeSchedules.
     * @return
     */
    public List<CommitteeScheduleBase> getCommitteeSchedules(){
        return this.committeeSchedules;
    }
    
    /**
     * This method should return CommitteeScheduleEvent.event.
     * @return
     */
    public ErrorType getType() {
        return this.type;
    }

}
