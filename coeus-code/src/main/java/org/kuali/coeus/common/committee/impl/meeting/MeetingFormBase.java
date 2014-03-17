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
package org.kuali.coeus.common.committee.impl.meeting;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionMapping;
import org.kuali.rice.kim.api.KimConstants;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.krad.document.Document;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 
 * This class is a form for Meeting management,
 */
@SuppressWarnings("deprecation")
public abstract class MeetingFormBase extends KualiForm {
    private static final long serialVersionUID = -7825455832928793712L;
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
        super.populate(request);
        populateFalseCheckboxes(request);
    }

    /**
     * Uses the "checkboxToReset" parameter to find checkboxes which had not been
     * populated in the request and attempts to populate them
     * 
     * @param request the request to populate
     */
    @SuppressWarnings("unchecked")
    private void populateFalseCheckboxes(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap.get("checkboxToReset") != null) {
            final String[] checkboxesToReset = request.getParameterValues("checkboxToReset");
            if(checkboxesToReset != null && checkboxesToReset.length > 0) {
                for (int i = 0; i < checkboxesToReset.length; i++) {
                    String propertyName = (String) checkboxesToReset[i];
                    if ( !StringUtils.isBlank(propertyName) && parameterMap.get(propertyName) == null ) {
                        populateForProperty(propertyName, KimConstants.KIM_ATTRIBUTE_BOOLEAN_FALSE_STR_VALUE_DISPLAY, parameterMap);
                    }  
                    else if ( !StringUtils.isBlank(propertyName) && parameterMap.get(propertyName) != null && parameterMap.get(propertyName).length >= 1 && parameterMap.get(propertyName)[0].equalsIgnoreCase("on") ) {
                        populateForProperty(propertyName, KimConstants.KIM_ATTRIBUTE_BOOLEAN_TRUE_STR_VALUE_DISPLAY, parameterMap); 
                    }
                }
            }
        }
    }

}
