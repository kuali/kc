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
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.editable.ProposalColumnsToAlter;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.maintenance.KualiMaintainableImpl;
import org.kuali.rice.kns.maintenance.Maintainable;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiMaintenanceForm;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.List;


public class KraMaintainableImpl extends KualiMaintainableImpl {

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
}
