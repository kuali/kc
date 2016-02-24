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
