/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.maintenance;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.CustomAttribute;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.bo.ProposalColumnsToAlter;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.maintenance.KualiMaintainableImpl;
import org.kuali.rice.kns.maintenance.Maintainable;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiMaintenanceForm;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * This class...
 */
public class KraMaintainableImpl extends KualiMaintainableImpl {

    /**
     *
     * @see org.kuali.core.maintenance.Maintainable#prepareForSave()
     */
    @Override
    public void prepareForSave() {
        super.prepareForSave();
        if ((businessObject != null) && (businessObject instanceof KraPersistableBusinessObjectBase)) {
            ((KraPersistableBusinessObjectBase) businessObject).setUpdateUser(GlobalVariables.getUserSession().getPrincipalName());
            ((KraPersistableBusinessObjectBase) businessObject).setUpdateUserSet(true);

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
