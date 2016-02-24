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
package org.kuali.kra.meeting;

import org.kuali.coeus.common.committee.impl.meeting.MeetingControllerService;
import org.kuali.coeus.common.committee.impl.meeting.MeetingFormBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;

/**
 * 
 * This class is a form for Meeting management,
 */
public class MeetingForm extends MeetingFormBase {
    

    private static final long serialVersionUID = -8524010367028447411L;

    @Override
    protected MeetingHelper getNewMeetingHelperInstanceHook(MeetingFormBase meetingForm) {
        return new MeetingHelper(meetingForm);
    }

    protected MeetingControllerService getMeetingControllerService() {
        return KcServiceLocator.getService("meetingControllerService");
    }
}
