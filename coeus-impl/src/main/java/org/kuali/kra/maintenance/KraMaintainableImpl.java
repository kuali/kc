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
package org.kuali.kra.maintenance;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.custom.attr.CustomAttribute;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.workflow.LastActionService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.editable.ProposalColumnsToAlter;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.identity.principal.Principal;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.maintenance.KualiMaintainableImpl;
import org.kuali.rice.kns.maintenance.Maintainable;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiMaintenanceForm;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;

import javax.persistence.Transient;
import java.util.List;
import java.util.concurrent.Callable;


public class KraMaintainableImpl extends KualiMaintainableImpl {

    private transient GlobalVariableService globalVariableService;

    private transient LastActionService lastActionService;

    private transient IdentityService identityService;

    @Override
    public void prepareForSave() {
        super.prepareForSave();
        if ((businessObject != null) && (businessObject instanceof KcPersistableBusinessObjectBase)) {
            ((KcPersistableBusinessObjectBase) businessObject).setUpdateUser(GlobalVariables.getUserSession().getPrincipalName());
            ((KcPersistableBusinessObjectBase) businessObject).setUpdateUserSet(true);

            // This is a solution to enable the lookreturn have a proper dropdown list ?
            if (businessObject instanceof CustomAttribute && StringUtils.isNotBlank(((CustomAttribute)businessObject).getLookupClass())) {
                    GlobalVariables.getUserSession().addObject(Constants.LOOKUP_CLASS_NAME, (Object)((CustomAttribute)businessObject).getLookupClass());
            }
            
            if (businessObject instanceof ProposalColumnsToAlter && StringUtils.isNotBlank(((ProposalColumnsToAlter)businessObject).getLookupClass())) {
                GlobalVariables.getUserSession().addObject(Constants.LOOKUP_CLASS_NAME, (Object)((ProposalColumnsToAlter)businessObject).getLookupClass());
            }

        }

    }

    public List getSections(MaintenanceDocument document, Maintainable oldMaintainable) {

        // businessObject is empty, so we have to dig into global variables
        // this is used when retrieving doc from doc search
        // KNSGlobalVariables.getKualiForm() may have issue if it is from different thread
        if (businessObject instanceof CustomAttribute) {
            if (KNSGlobalVariables.getKualiForm() != null && KNSGlobalVariables.getKualiForm() instanceof KualiMaintenanceForm) {
                CustomAttribute customAttribute = (CustomAttribute)((MaintenanceDocumentBase)((KualiMaintenanceForm)KNSGlobalVariables.getKualiForm()).getDocument()).getDocumentBusinessObject();
                if (StringUtils.isNotBlank(customAttribute.getLookupClass())) {
                    if (StringUtils.isBlank((String)GlobalVariables.getUserSession().retrieveObject(Constants.LOOKUP_CLASS_NAME)) && ((((List)GlobalVariables.getUserSession().retrieveObject(Constants.LOOKUP_RETURN_FIELDS))) == null || ((List)GlobalVariables.getUserSession().retrieveObject(Constants.LOOKUP_RETURN_FIELDS)).size() == 0)) {
                        GlobalVariables.getUserSession().addObject(Constants.LOOKUP_CLASS_NAME, (Object)customAttribute.getLookupClass());                    
                    }
                }
            }
        }
        
        if (businessObject instanceof ProposalColumnsToAlter) {
            if (KNSGlobalVariables.getKualiForm() != null && KNSGlobalVariables.getKualiForm() instanceof KualiMaintenanceForm) {
                ProposalColumnsToAlter proposalColumnsToAlter = (ProposalColumnsToAlter)((MaintenanceDocumentBase)((KualiMaintenanceForm)KNSGlobalVariables.getKualiForm()).getDocument()).getDocumentBusinessObject();
                if (StringUtils.isNotBlank(proposalColumnsToAlter.getLookupClass())) {
                    if (StringUtils.isBlank((String)GlobalVariables.getUserSession().retrieveObject(Constants.LOOKUP_CLASS_NAME)) && ((((List)GlobalVariables.getUserSession().retrieveObject(Constants.LOOKUP_RETURN_FIELDS))) == null || ((List)GlobalVariables.getUserSession().retrieveObject(Constants.LOOKUP_RETURN_FIELDS)).size() == 0)) {
                        GlobalVariables.getUserSession().addObject(Constants.LOOKUP_CLASS_NAME, (Object)proposalColumnsToAlter.getLookupClass());                    
                    }
                }
            }
        }
        
        return super.getSections(document, oldMaintainable);
    }

    protected <T> T executeAsLastActionUser(Callable<T> callable) {
        try {
            final String lastPrincipalId = getLastActionService().findLastUserActionTakenPrincipalId(getDocumentNumber());
            if (StringUtils.isNotBlank(lastPrincipalId) && !lastPrincipalId.equals(getGlobalVariableService().getUserSession().getPrincipalId())) {
                final Principal principal = getIdentityService().getPrincipal(lastPrincipalId);
                if (principal != null && StringUtils.isNotBlank(principal.getPrincipalName())) {
                    return GlobalVariables.doInNewGlobalVariables(new UserSession(principal.getPrincipalName()), callable);
                }
            }
            return callable.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected GlobalVariableService getGlobalVariableService() {
        if (this.globalVariableService == null) {
            this.globalVariableService = KcServiceLocator.getService(GlobalVariableService.class);
        }
        return this.globalVariableService;
    }

    protected void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }

    LastActionService getLastActionService() {
        if (this.lastActionService == null) {
            this.lastActionService = KcServiceLocator.getService(LastActionService.class);
        }
        return this.lastActionService;
    }

    void setLastActionService(LastActionService lastActionService) {
        this.lastActionService = lastActionService;
    }

    IdentityService getIdentityService() {
        if (this.identityService == null) {
            this.identityService = KcServiceLocator.getService(IdentityService.class);
        }
        return this.identityService;
    }

    void setIdentityService(IdentityService identityService) {
        this.identityService = identityService;
    }
}
