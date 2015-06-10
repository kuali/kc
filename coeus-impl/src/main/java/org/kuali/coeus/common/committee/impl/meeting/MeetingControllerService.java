package org.kuali.coeus.common.committee.impl.meeting;

import javax.servlet.http.HttpServletRequest;

/**
 * MeetingControllerService used by Meeting form and action classes
 */
public interface MeetingControllerService {

    /**
     * Populates the MeetingFormBase passed in with the current schedule from the database based on scheduleId.
     */
    void populateSchedule(MeetingFormBase form, HttpServletRequest request, String scheduleId);

}
