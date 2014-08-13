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
package org.kuali.coeus.common.committee.impl.rules;

import org.kuali.coeus.common.committee.impl.rule.event.CommitteeScheduleFilterEvent;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.kra.infrastructure.KeyConstants;

import java.sql.Date;

public class CommitteeScheduleFilterDatesRule extends KcTransactionalDocumentRuleBase implements KcBusinessRule<CommitteeScheduleFilterEvent> {
    
    public static final String SCHEDULEDATE_FILTERSTARTDATE = "committeeHelper.scheduleData.filterStartDate";
    
    public static final String SCHEDULEDATE_FILTERENDDATE = "committeeHelper.scheduleData.filerEndDate";
    
    public static final String [] MSG = {"Start date", "End date"}; 
            
    @Override
    public boolean processRules(CommitteeScheduleFilterEvent filterCommitteeScheduleEvent) {
        
        boolean rulePassed = true;
        
        Date startDate = filterCommitteeScheduleEvent.getScheduleData().getFilterStartDate();
        Date endDate = filterCommitteeScheduleEvent.getScheduleData().getFilerEndDate();
        
        if(null == startDate) {
            reportError(SCHEDULEDATE_FILTERSTARTDATE, KeyConstants.ERROR_COMMITTEESCHEDULE_FILTER_DATE, MSG[0]);
            rulePassed = false;
        }
        if(null == endDate) {
            reportError(SCHEDULEDATE_FILTERENDDATE, KeyConstants.ERROR_COMMITTEESCHEDULE_FILTER_DATE, MSG[1]);
            rulePassed = false;
        }     
        
        if(rulePassed && startDate.toString().equals(endDate.toString())) {            
            reportError(SCHEDULEDATE_FILTERENDDATE, KeyConstants.ERROR_COMMITTEESCHEDULE_STARTANDENDDATE_EQUAL);
            rulePassed = false;
        }
        
        if(rulePassed && startDate.after(endDate)) {            
            reportError(SCHEDULEDATE_FILTERENDDATE, KeyConstants.ERROR_COMMITTEESCHEDULE_STARTANDENDDATE);
            rulePassed = false;
        }
        
        return rulePassed;
    }

}
