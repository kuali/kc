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

import org.kuali.coeus.common.committee.impl.meeting.CommitteeScheduleAttachmentsBase;
import org.kuali.coeus.common.committee.impl.service.impl.CommitteeScheduleServiceImplBase;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.service.CommitteeScheduleService;
import org.kuali.kra.meeting.CommitteeScheduleAttachments;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Committee Service implementation.
 */
@Transactional
public class CommitteeScheduleServiceImpl extends CommitteeScheduleServiceImplBase<CommitteeSchedule, Committee, CommitteeScheduleMinute> implements CommitteeScheduleService {

    @Override
    protected CommitteeSchedule getNewCommiteeScheduleInstanceHook() {
        return new CommitteeSchedule();
    }

    @Override
    protected Class<CommitteeScheduleMinute> getCommitteeScheduleMinuteBOClassHook() {
        return CommitteeScheduleMinute.class;
    }

    @Override
    protected CommitteeScheduleAttachmentsBase getNewCommitteeScheduleAttachmentsInstanceHook() {
        return new CommitteeScheduleAttachments();
    }
}
