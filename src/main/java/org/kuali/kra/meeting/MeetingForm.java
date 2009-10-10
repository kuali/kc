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
import org.kuali.kra.web.struts.form.ResetElementsHelper;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.web.struts.form.KualiForm;

public class MeetingForm extends KualiForm {
    private MeetingHelper meetingHelper;
    // textarea needs formKey & document.  use KualiDocumentFormBase; then this is not needed?
    private String formKey;
    private Document document;

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


    @Override
    public boolean shouldPropertyBePopulatedInForm(String requestParameterName, HttpServletRequest request) {
        return true;
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
        // resetElements is related to checkbox values.
        ResetElementsHelper.resetElements(this, ResetElementsHelper.getElementsToReset(request));
        this.getMeetingHelper().setAbsenteeList("");
    }

}
