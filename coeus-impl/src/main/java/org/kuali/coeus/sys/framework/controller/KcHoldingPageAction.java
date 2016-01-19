/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.sys.framework.controller;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Checks to see whether the document specified in the session has completed its asynchronous processing and is ready to be
 * reloaded.
 */
public class KcHoldingPageAction extends AbstractHoldingPageAction {

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
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        ActionForward forward = super.execute(mapping, form, request, response);
        // check if there is an alternate doc id key set in the user session
        String alternateDocIdSessionKey = (String) GlobalVariables.getUserSession().retrieveObject(
                Constants.ALTERNATE_DOC_ID_SESSION_KEY);
        final Object documentId;
        if (alternateDocIdSessionKey != null) {
            // get the id from the user session (double indirection)
            documentId = GlobalVariables.getUserSession().retrieveObject(alternateDocIdSessionKey);
        }
        else {
            // get the id from the top level http session
            documentId = request.getSession().getAttribute(KRADConstants.DOCUMENT_HTTP_SESSION_KEY);
        }
        Document document = getByDocumentHeaderId(documentId.toString());
        // check if the user clicked the 'Return to Portal' button
        if (RETURN_TO_PORTAL.equals(findMethodToCall(form, request))) {
            // clean up the session and also remove the messages meant for the return page
            cleanupUserSession(alternateDocIdSessionKey);
            GlobalVariables.getUserSession().removeObject(Constants.HOLDING_PAGE_MESSAGES);
        } else if(GlobalVariables.getUserSession().retrieveObject(Constants.FORCE_HOLDING_PAGE_FOR_ACTION_LIST) != null) {
            // this is a temporary solution
            // introduced to unload the block ui in embedded mode
            GlobalVariables.getUserSession().removeObject(Constants.FORCE_HOLDING_PAGE_FOR_ACTION_LIST); 
            forward = getReturnPath(alternateDocIdSessionKey);
        }
        else if (isDocumentPostprocessingComplete(document)) {
            forward = getReturnPath(alternateDocIdSessionKey);
        }
        
        return forward;
    }

    /**
     * This method is to get the return location and clean up session.
     */
    private ActionForward getReturnPath(String alternateDocIdSessionKey) {
        String backLocation = (String) GlobalVariables.getUserSession().retrieveObject(Constants.HOLDING_PAGE_RETURN_LOCATION);
        cleanupUserSession(alternateDocIdSessionKey);
        return new ActionForward(backLocation, true);
    }

    private void cleanupUserSession(String alternateDocIdSessionKey) {
        GlobalVariables.getUserSession().removeObject(Constants.HOLDING_PAGE_RETURN_LOCATION);
        if (alternateDocIdSessionKey != null) {
            GlobalVariables.getUserSession().removeObject(Constants.ALTERNATE_DOC_ID_SESSION_KEY);
            GlobalVariables.getUserSession().removeObject(alternateDocIdSessionKey);
        }
    }

}
