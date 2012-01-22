/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.web.struts.authorization;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.kuali.kra.authorization.Task;
import org.kuali.kra.infrastructure.TaskGroupName;
import org.kuali.kra.web.struts.authorization.impl.WebTaskFactoryImpl;
import org.kuali.rice.krad.util.KRADConstants;

/**
 * Narrative Task Factory is used to create a Narrative Task.  The derived
 * class is responsible for returning the actual narrative to insert into the
 * task.  A narrative could be a proposal or institutional attachment.
 */
public abstract class NarrativeTaskFactory extends WebTaskFactoryImpl {
    
    private static final String LINE_NUMBER = "line";
    
    /**
     * @see org.kuali.kra.web.struts.authorization.WebTaskFactory#createTask(org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest)
     */
    public abstract Task createTask(ActionForm form, HttpServletRequest request);
    
    /**
     * @see org.kuali.kra.web.struts.authorization.impl.WebTaskFactoryImpl#getTaskGroupName()
     */
    @Override
    public final String getTaskGroupName() {
        return TaskGroupName.NARRATIVE;
    }
    
    /**
     * Get the line number from the HTTP request.  
     * @param request the HTTP request
     * @return the line number within the request
     */
    protected final int getLineNumber(HttpServletRequest request) {
        int lineNumber = 0;
        String lineStr = request.getParameter(LINE_NUMBER);
        if (lineStr == null) {
            lineNumber = getSelectedLine(request);
        } else {
            try {
                lineNumber = Integer.parseInt(lineStr);
            } catch (Exception ex) {
                // do nothing; 0 will be returned
            }
        }
        return lineNumber;
    }

    /**
     * Parses the method to call attribute to pick off the line number 
     * which should have an action performed on it.
     * @param request the HTTP request
     * @return the line number
     */
    private int getSelectedLine(HttpServletRequest request) {
        int selectedLine = -1;
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            String lineNumber = StringUtils.substringBetween(parameterName, ".line", ".");
            selectedLine = Integer.parseInt(lineNumber);
        }

        return selectedLine;
    }
}
