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
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;


/**
 * Checks to see whether the document specified in the session has completed its asynchronous processing and is ready to be
 * reloaded.
 */
public class KraHoldingPageAction extends AbstractHoldingPageAction {

    private static final String RETURN_TO_PORTAL = "returnToPortal";

    /**
     * If this method is invoked due to a simple refresh from the holding page JSP, and not because the user has clicked 'return
     * to portal', then it will check if the 'current document' has completed its processing and if so it will return the back
     * location for that document as given in the user session by the key Constants.HOLDING_PAGE_RETURN_LOCATION. 
     * The 'current document' is the one that is obtained via the document service using the doc id provided in the session. 
     * By default this method will get this doc id from the top-level http session using the key KNSConstants.DOCUMENT_HTTP_SESSION_KEY. 
     * However, if you want a different doc id to be used, then before calling this method, then the following two steps must be performed: 
     *      1. insert the alternate doc id in the user session with some key constant, say 'X'. 
     *      2. insert 'X' also into the user session using the key Constants.ALTERNATE_DOC_ID_SESSION_KEY.
     * Basically, this method performs double indirection on the user session key. It will first check if the user session key
     * Constants.ALTERNATE_DOC_ID_SESSION_KEY has any value inserted for it, and if so will use that value again as a key into the
     * user session to get the alternate doc id (and then use that doc id to obtain the document from the doc service).
     * 
     * @see org.kuali.rice.kns.web.struts.action.KualiAction#execute(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        ActionForward forward = super.execute(mapping, form, request, response);
        // check if there is an alternate doc id key set in the user session
        String alternateDocIdSessionKey = (String) GlobalVariables.getUserSession().retrieveObject(
                Constants.ALTERNATE_DOC_ID_SESSION_KEY);
        Object documentId = null;
        if (alternateDocIdSessionKey != null) {
            // get the id from the user session (double indirection)
            documentId = GlobalVariables.getUserSession().retrieveObject(alternateDocIdSessionKey);
        }
        else {
            // get the id from the top level http session
            documentId = request.getSession().getAttribute(KRADConstants.DOCUMENT_HTTP_SESSION_KEY);
        }
        Document document = getDocumentService().getByDocumentHeaderId(documentId.toString());
        // check if the user clicked the 'Return to Portal' button
        if (RETURN_TO_PORTAL.equals(findMethodToCall(form, request))) {
            // clean up the session and also remove the messages meant for the return page
            cleanupUserSession(alternateDocIdSessionKey);
            GlobalVariables.getUserSession().removeObject(Constants.HOLDING_PAGE_MESSAGES);
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
        if (alternateDocIdSessionKey != null) {
            GlobalVariables.getUserSession().removeObject(Constants.ALTERNATE_DOC_ID_SESSION_KEY);
            GlobalVariables.getUserSession().removeObject(alternateDocIdSessionKey);
        }
    }

}