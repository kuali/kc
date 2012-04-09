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
package org.kuali.kra.protocol;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.protocol.ProtocolForm;
import org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase;

/**
 * The ProtocolAction is the base class for all Protocol actions.  Each derived
 * Action class corresponds to one tab (web page).  The derived Action class handles
 * all user requests for that particular tab (web page).
 */
public abstract class ProtocolAction extends KraTransactionalDocumentActionBase {
    
    public ActionForward protocol(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ((ProtocolForm)form).getProtocolHelper().prepareView();
        return mapping.findForward(getProtocolForwardNameHook());
    }
    
    // TODO invoke these hooks at appropriate points in action methods to get the actual forward name from the subclasses
    protected abstract String getProtocolForwardNameHook();
    
    protected abstract String getQuestionnaireForwardNameHook();
    
    protected abstract String getPersonnelForwardNameHook();

    protected abstract String getCustomDataForwardNameHook();

    protected abstract String getSpecialReviewForwardNameHook();

    protected abstract String getNoteAndAttachmentForwardNameHook();

    protected abstract String getProtocolActionsForwardNameHook();

    protected abstract String getProtocolOnlineReviewForwardNameHook();
    
    protected abstract String getProtocolPermissionsForwardNameHook();

}
