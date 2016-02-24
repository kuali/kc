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
package org.kuali.kra.iacuc.committee.service.impl;

import org.kuali.coeus.common.committee.impl.meeting.CommitteeScheduleAttachmentsBase;
import org.kuali.coeus.common.committee.impl.service.impl.CommitteeScheduleServiceImplBase;
import org.kuali.kra.iacuc.committee.bo.IacucCommittee;
import org.kuali.kra.iacuc.committee.bo.IacucCommitteeSchedule;
import org.kuali.kra.iacuc.committee.meeting.IacucCommitteeScheduleAttachments;
import org.kuali.kra.iacuc.committee.meeting.IacucCommitteeScheduleMinute;
import org.kuali.kra.iacuc.committee.service.IacucCommitteeScheduleService;

public class IacucCommitteeScheduleServiceImpl extends CommitteeScheduleServiceImplBase<IacucCommitteeSchedule, IacucCommittee, IacucCommitteeScheduleMinute> implements IacucCommitteeScheduleService{

    @Override
    protected IacucCommitteeSchedule getNewCommiteeScheduleInstanceHook() {
        return new IacucCommitteeSchedule(); 
    }

    @Override
    protected Class<IacucCommitteeScheduleMinute> getCommitteeScheduleMinuteBOClassHook() {
        return IacucCommitteeScheduleMinute.class;
    }

    @Override
    protected CommitteeScheduleAttachmentsBase getNewCommitteeScheduleAttachmentsInstanceHook() {
        return new IacucCommitteeScheduleAttachments();
    }

}
