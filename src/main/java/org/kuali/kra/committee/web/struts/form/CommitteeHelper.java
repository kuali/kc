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
package org.kuali.kra.committee.web.struts.form;

import java.sql.Date;
import java.util.List;

import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.service.CommitteeScheduleService;
import org.kuali.kra.infrastructure.KraServiceLocator;

public class CommitteeHelper {
    
    private CommitteeForm form;
    
    public CommitteeHelper(CommitteeForm form) {
        this.form = form;
    }
    
    public void prepareView() {
        prepareCommitteeScheduleDeleteView();
        prepareCommitteeScheduleDateConflictView();
    }
    
    private void prepareCommitteeScheduleDeleteView(){
        
        List<CommitteeSchedule> committeeSchedules = form.getCommitteeDocument().getCommittee().getCommitteeSchedules();
        boolean flag = false;
        CommitteeScheduleService service = getCommitteeScheduleService();
        for(CommitteeSchedule committeeSchedule: committeeSchedules) {            
            flag = service.isCommitteeScheduleDeletable(committeeSchedule);
            committeeSchedule.setDelete(flag);
        }    
    }
    
    private void prepareCommitteeScheduleDateConflictView() {
        List<CommitteeSchedule> committeeSchedules = form.getCommitteeDocument().getCommittee().getCommitteeSchedules();
        Date dt = null;
        for(CommitteeSchedule committeeSchedule: committeeSchedules) {
            dt = committeeSchedule.getScheduledDate();
            committeeSchedule.setCopyOfScheduleDate(dt);
        }
    }
    
    private CommitteeScheduleService getCommitteeScheduleService() {
        return KraServiceLocator.getService(CommitteeScheduleService.class);
    }
}
