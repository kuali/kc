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
package org.kuali.kra.meeting;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.web.struts.form.KualiForm;

/**
 * 
 * This class is a form for Meeting management,
 */
public class MeetingForm extends KualiForm {
    private static final long serialVersionUID = -7825455832928793712L;
    private MeetingHelper meetingHelper;
    // textarea needs formKey & document.  
    private String formKey;
    private Document document;
    private boolean readOnly;

    public MeetingForm() {
        super();
        initialize();
    }

    /**
     * This method initialize all form variables
     */
    public void initialize() {
       setMeetingHelper(new MeetingHelper(this));
    }

    public MeetingHelper getMeetingHelper() {
        return meetingHelper;
    }


    public void setMeetingHelper(MeetingHelper meetingHelper) {
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

}
