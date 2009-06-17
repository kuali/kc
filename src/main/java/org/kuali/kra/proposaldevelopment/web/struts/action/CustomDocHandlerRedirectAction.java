/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kns.util.KNSConstants;

import org.kuali.rice.kew.routing.web.ClientAppDocHandlerRedirectAction;

public class CustomDocHandlerRedirectAction extends ClientAppDocHandlerRedirectAction {

    @Override
    public ActionForward start(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward returnForward = super.start(mapping, form, request, response);
        
        String docHandler = returnForward.getPath();
        docHandler = docHandler.replace(KNSConstants.DOC_HANDLER_METHOD, Constants.HEADER_TAB);
        docHandler += "&" + KNSConstants.METHOD_TO_CALL_PATH + "=methodToCall.headerTab.headerDispatch.reload.navigateTo.actions.x=Proposal Actions";
          
        returnForward = new ActionForward(docHandler, returnForward.getRedirect());
        return returnForward;
    }
}
