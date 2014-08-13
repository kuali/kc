/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.coi.lookup;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.framework.auth.task.ApplicationTask;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.coi.CoiDisclosureForm;
import org.kuali.kra.coi.disclosure.CoiDisclosureAction;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CoiDisclosurePersonSearchAction extends CoiDisclosureAction {

    public static final String REFRESH_CALLER = "kcPersonLookupable";
    
    @SuppressWarnings("deprecation")
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        if(isAuthorizedForCoiLookups()) {
            if (StringUtils.equals(coiDisclosureForm.getRefreshCaller(), REFRESH_CALLER)) {
                return viewMasterDisclosure(coiDisclosureForm.getPersonId(), coiDisclosureForm, mapping);
            }
        }
        return mapping.findForward(KRADConstants.MAPPING_PORTAL);
        
    }
    
    protected boolean isAuthorizedForCoiLookups() {
        ApplicationTask task = new ApplicationTask(TaskName.LOOKUP_COI_DISCLOSURES);
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected TaskAuthorizationService getTaskAuthorizationService() {
        return KcServiceLocator.getService(TaskAuthorizationService.class);
    }
    
    private String getUserIdentifier() {
        return GlobalVariables.getUserSession().getPrincipalId();
    }
}
