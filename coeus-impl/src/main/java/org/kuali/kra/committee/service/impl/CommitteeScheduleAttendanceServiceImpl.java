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
package org.kuali.kra.committee.service.impl;

import org.kuali.coeus.common.committee.impl.service.impl.CommitteeScheduleAttendanceServiceImplBase;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.service.CommitteeScheduleAttendanceService;
import org.kuali.kra.committee.service.CommitteeService;


public class CommitteeScheduleAttendanceServiceImpl  extends CommitteeScheduleAttendanceServiceImplBase<CommitteeService, Committee, CommitteeDocument, CommitteeSchedule> 
                                                     implements CommitteeScheduleAttendanceService {

    @Override
    protected Class<CommitteeDocument> getCommitteeDocumentClassBOHook() {
        return CommitteeDocument.class;
    }
    
}