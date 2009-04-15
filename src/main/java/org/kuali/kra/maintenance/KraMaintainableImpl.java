/*
 * Copyright 2006-2009 The Kuali Foundation
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
import org.kuali.core.document.MaintenanceDocumentBase;
import org.kuali.core.maintenance.KualiMaintainableImpl;
import org.kuali.core.maintenance.Maintainable;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.web.struts.form.KualiMaintenanceForm;
import org.kuali.kra.bo.CustomAttribute;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.bo.ProposalColumnsToAlter;

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

            String updateUser = GlobalVariables.getUserSession().getLoggedInUserNetworkId();

            // Since the UPDATE_USER column is only VACHAR(60), we need to truncate this string if it's longer than 60 characters
            if (updateUser.length() > 60) {
                updateUser = updateUser.substring(0, 60);
            }
            
            ((KraPersistableBusinessObjectBase)businessObject).setUpdateUser(updateUser);
            ((KraPersistableBusinessObjectBase)businessObject).setUpdateUserSet(true);

            // This is a solution to enable the lookreturn have a proper dropdown list ?
            if (businessObject instanceof CustomAttribute && StringUtils.isNotBlank(((CustomAttribute)businessObject).getLookupClass())) {
                    GlobalVariables.getUserSession().addObject(Constants.LOOKUP_CLASS_NAME, (Object)((CustomAttribute)businessObject).getLookupClass());
            }
            
            if (businessObject instanceof ProposalColumnsToAlter && StringUtils.isNotBlank(((ProposalColumnsToAlter)businessObject).getLookupClass())) {
                GlobalVariables.getUserSession().addObject(Constants.LOOKUP_CLASS_NAME, (Object)((ProposalColumnsToAlter)businessObject).getLookupClass());
            }

        }

    }

    public List getSections(Maintainable oldMaintainable) {

        // businessObject is empty, so we have to dig into global variables
        // this is used when retrieving doc from doc search
        // GlobalVariables.getKualiForm() may have issue if it is from different thread
        if (businessObject instanceof CustomAttribute) {
            if (GlobalVariables.getKualiForm() != null && GlobalVariables.getKualiForm() instanceof KualiMaintenanceForm) {
                CustomAttribute customAttribute = (CustomAttribute)((MaintenanceDocumentBase)((KualiMaintenanceForm)GlobalVariables.getKualiForm()).getDocument()).getDocumentBusinessObject();
                if (StringUtils.isNotBlank(customAttribute.getLookupClass())) {
                    if (StringUtils.isBlank((String)GlobalVariables.getUserSession().retrieveObject(Constants.LOOKUP_CLASS_NAME)) && ((((List)GlobalVariables.getUserSession().retrieveObject(Constants.LOOKUP_RETURN_FIELDS))) == null || ((List)GlobalVariables.getUserSession().retrieveObject(Constants.LOOKUP_RETURN_FIELDS)).size() == 0)) {
                        GlobalVariables.getUserSession().addObject(Constants.LOOKUP_CLASS_NAME, (Object)customAttribute.getLookupClass());                    
                    }
                }
            }
        }
        
        if (businessObject instanceof ProposalColumnsToAlter) {
            if (GlobalVariables.getKualiForm() != null && GlobalVariables.getKualiForm() instanceof KualiMaintenanceForm) {
                ProposalColumnsToAlter proposalColumnsToAlter = (ProposalColumnsToAlter)((MaintenanceDocumentBase)((KualiMaintenanceForm)GlobalVariables.getKualiForm()).getDocument()).getDocumentBusinessObject();
                if (StringUtils.isNotBlank(proposalColumnsToAlter.getLookupClass())) {
                    if (StringUtils.isBlank((String)GlobalVariables.getUserSession().retrieveObject(Constants.LOOKUP_CLASS_NAME)) && ((((List)GlobalVariables.getUserSession().retrieveObject(Constants.LOOKUP_RETURN_FIELDS))) == null || ((List)GlobalVariables.getUserSession().retrieveObject(Constants.LOOKUP_RETURN_FIELDS)).size() == 0)) {
                        GlobalVariables.getUserSession().addObject(Constants.LOOKUP_CLASS_NAME, (Object)proposalColumnsToAlter.getLookupClass());                    
                    }
                }
            }
        }
        
        return super.getSections(oldMaintainable);
    }
}
