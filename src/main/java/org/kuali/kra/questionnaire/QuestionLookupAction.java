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
package org.kuali.kra.questionnaire;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.rice.kns.web.struts.action.KualiAction;

public class QuestionLookupAction extends KualiAction {

    @Override
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // TODO Auto-generated method stub
        return super.refresh(mapping, form, request, response);
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // TODO Auto-generated method stub
        ActionForward forward = super.execute(mapping, form, request, response);
        String lookupType = request.getParameter("lookupType");
        if (StringUtils.isNotBlank(lookupType)) {
            if (lookupType.equals("single")) {
                forward = mapping.findForward("singleLookup");
            } else if (lookupType.equals("multivalue")) {
                forward = mapping.findForward("multiLookup");
            }
        }
        return forward;
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, ServletRequest request, ServletResponse response)
            throws Exception {
        // TODO Auto-generated method stub
        ActionForward forward = super.execute(mapping, form, request, response);
        String lookupType = request.getParameter("lookupType");
        if (StringUtils.isNotBlank(lookupType)) {
            if (lookupType.equals("single")) {
                forward = mapping.findForward("singleLookup");
            } else if (lookupType.equals("multivalue")) {
                forward = mapping.findForward("multiLookup");
            }
        }
        return forward;

    }

}
