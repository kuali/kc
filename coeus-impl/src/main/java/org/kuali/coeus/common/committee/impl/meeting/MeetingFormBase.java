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
package org.kuali.coeus.common.committee.impl.meeting;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.kim.api.KimConstants;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.krad.document.Document;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * This class is a form for Meeting management,
 */

public abstract class MeetingFormBase extends KualiForm {
    private static final long serialVersionUID = -7825455832928793712L;

    private static final String ERROR_SCHEDULE_MULTIPLE_TABS = "error.schedule.multipleTabs";
    private static final String SCHEDULE_ID = "meetingHelper.committeeSchedule.id";

    private MeetingHelperBase meetingHelper;
    // textarea needs formKey & document.  
    private String formKey;
    private Document document;
    private boolean readOnly;

    public MeetingFormBase() {
        super();
        initialize();
    }

    /**
     * This method initialize all form variables
     */
    public void initialize() {
        setMeetingHelper(getNewMeetingHelperInstanceHook(this));
    }

    protected abstract MeetingHelperBase getNewMeetingHelperInstanceHook(MeetingFormBase meetingForm);

    public MeetingHelperBase getMeetingHelper() {
        return meetingHelper;
    }

    public void setMeetingHelper(MeetingHelperBase meetingHelper) {
        this.meetingHelper = meetingHelper;
    }

    public String getFormKey() {
        return formKey;
    }

    public void setFormKey(String formKey) {
        this.formKey = formKey;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    @Override
    public void reset(final ActionMapping mapping, final HttpServletRequest request) {
        super.reset(mapping, request);
        this.getMeetingHelper().setAbsenteeList("");
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    @Override
    public void populate(HttpServletRequest request) {
        String sessionScheduleId;
        if (this.getMeetingHelper() != null && this.getMeetingHelper().getCommitteeSchedule() != null && this.getMeetingHelper().getCommitteeSchedule().getId() != null) {
            sessionScheduleId = Long.toString(this.getMeetingHelper().getCommitteeSchedule().getId());
            String requestScheduleId = request.getParameter(SCHEDULE_ID);
            if (requestScheduleId != null && !sessionScheduleId.equals(requestScheduleId)) {
                getGlobalVariableService().getMessageMap().putError(SCHEDULE_ID, ERROR_SCHEDULE_MULTIPLE_TABS, "");
                setReadOnly(true);
            }
        }

        super.populate(request);
        populateFalseCheckboxes(request);
    }

    /**
     * Uses the "checkboxToReset" parameter to find checkboxes which had not been
     * populated in the request and attempts to populate them
     *
     * @param request the request to populate
     */

    private void populateFalseCheckboxes(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap.get("checkboxToReset") != null) {
            final String[] checkboxesToReset = request.getParameterValues("checkboxToReset");
            if (checkboxesToReset != null && checkboxesToReset.length > 0) {
                for (int i = 0; i < checkboxesToReset.length; i++) {
                    String propertyName = (String) checkboxesToReset[i];
                    if (!StringUtils.isBlank(propertyName) && parameterMap.get(propertyName) == null) {
                        populateForProperty(propertyName, KimConstants.KIM_ATTRIBUTE_BOOLEAN_FALSE_STR_VALUE_DISPLAY, parameterMap);
                    } else if (!StringUtils.isBlank(propertyName) && parameterMap.get(propertyName) != null && parameterMap.get(propertyName).length >= 1 && parameterMap.get(propertyName)[0].equalsIgnoreCase("on")) {
                        populateForProperty(propertyName, KimConstants.KIM_ATTRIBUTE_BOOLEAN_TRUE_STR_VALUE_DISPLAY, parameterMap);
                    }
                }
            }
        }
    }

    protected GlobalVariableService getGlobalVariableService() {
        return KcServiceLocator.getService(GlobalVariableService.class);
    }

    protected abstract MeetingControllerService getMeetingControllerService();
}
