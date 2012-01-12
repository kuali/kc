/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;


/**
 * Checks to see whether the document specified in the session has completed its asynchronous processing and is ready to be reloaded.
 */
public class KraHoldingPageAction extends AbstractHoldingPageAction {
    
    private static final String RETURN_TO_PORTAL = "returnToPortal";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        ActionForward forward = super.execute(mapping, form, request, response);
        // before getting the document id, we check if there is an alternate doc id session key set
        String alternateDocIdSessionKey = (String) GlobalVariables.getUserSession().retrieveObject(Constants.ALTERNATE_DOC_ID_SESSION_KEY);
        Object documentId = null;
        if(alternateDocIdSessionKey != null) {
            // double indirection on the user session
            documentId = GlobalVariables.getUserSession().retrieveObject(alternateDocIdSessionKey);
        }
        else {
            documentId = request.getSession().getAttribute(KNSConstants.DOCUMENT_HTTP_SESSION_KEY);
        }
        Document document = getDocumentService().getByDocumentHeaderId(documentId.toString());
        // check if the user clicked the 'Return to Portal' button
        if(RETURN_TO_PORTAL.equals(findMethodToCall(form, request))) {
            // just clean up the session 
            cleanupUserSession(alternateDocIdSessionKey);
        }
        else if (isDocumentPostprocessingComplete(document)) {
            // get the return location and clean up session
            String backLocation = (String) GlobalVariables.getUserSession().retrieveObject(Constants.HOLDING_PAGE_RETURN_LOCATION);
            cleanupUserSession(alternateDocIdSessionKey);
            forward = new ActionForward(backLocation, true);
        }
        
        return forward;
    }
    
    private void cleanupUserSession(String alternateDocIdSessionKey) {
        GlobalVariables.getUserSession().removeObject(Constants.HOLDING_PAGE_RETURN_LOCATION);
        if(alternateDocIdSessionKey != null) {
            GlobalVariables.getUserSession().removeObject(Constants.ALTERNATE_DOC_ID_SESSION_KEY);
            GlobalVariables.getUserSession().removeObject(alternateDocIdSessionKey);
        }
    }

}