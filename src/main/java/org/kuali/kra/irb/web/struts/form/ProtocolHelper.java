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
package org.kuali.kra.irb.web.struts.form;

import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import org.kuali.core.bo.user.UniversalUser;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.web.struts.form.KualiForm;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.document.authorization.ProtocolTask;
import org.kuali.kra.service.TaskAuthorizationService;

public class ProtocolHelper {
    

    /**
     * Each Helper must contain a reference to its document form
     * so that it can access the actual document.
     */
    private KualiForm form;
    
    private String referenceId1Label;
    private String referenceId2Label;

    private boolean billableReadOnly = false;

    public ProtocolHelper(ProtocolForm form) {
        this.form = form;
    }    
    
    public void prepareView() {
        // setup any Protocol System Parameters that will be needed
        KualiConfigurationService configService = getService(KualiConfigurationService.class);
        setReferenceId1Label((configService.getParameter(Constants.PARAMETER_MODULE_PROTOCOL, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.PARAMETER_MODULE_PROTOCOL_REFERENCEID1)).getParameterValue());
        setReferenceId2Label((configService.getParameter(Constants.PARAMETER_MODULE_PROTOCOL, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.PARAMETER_MODULE_PROTOCOL_REFERENCEID2)).getParameterValue());

        // call services...
        // set attributes needed by the view
        ProtocolDocument document = (ProtocolDocument)((ProtocolForm)form).getDocument();
        if (document == null || document.getProtocol() == null) {
            throw new IllegalArgumentException("invalid (null) ProtocolDocument in ProtocolForm");
        }
        document.getProtocol().resolvePrincipalInvestigator();        
        ProtocolTask task = new ProtocolTask(TaskName.MODIFY_PROTOCOL_BILLABLE, document.getProtocol());
        billableReadOnly = !getTaskAuthorizationService().isAuthorized(getUsername(), task);
    }


    /**
     * Constructor.
     */
    public ProtocolHelper(KualiForm form) {
        this.form = form;
    }

    public void setReferenceId1Label(String referenceId1Label) {
        this.referenceId1Label = referenceId1Label;
    }

    public String getReferenceId1Label() {
        return referenceId1Label;
    }
    
    public void setReferenceId2Label(String referenceId2Label) {
        this.referenceId2Label = referenceId2Label;
    }

    public String getReferenceId2Label() {
        return referenceId2Label;
    }

    public boolean getBillableReadOnly() {
        return billableReadOnly;
    }

    private TaskAuthorizationService getTaskAuthorizationService() {
        return KraServiceLocator.getService(TaskAuthorizationService.class);
    }

    private String getUsername() {
         UniversalUser user = GlobalVariables.getUserSession().getUniversalUser();
         return user.getPersonUserIdentifier();
    }
    
}
