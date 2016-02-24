/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
