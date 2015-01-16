/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.person.creditsplit;

import org.kuali.coeus.common.framework.type.InvestigatorCreditType;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;


import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;
import static org.kuali.kra.infrastructure.Constants.INVESTIGATOR_CREDIT_TYPE_CODE_PROPERTY_KEY;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_INVESTIGATOR_CREDIT_TYPE_EXISTS;

/**
 * Business Rules implementation for modifying <code>{@link InvestigatorCreditType}</code> Business Object instances.
 * 
 * @see org.kuali.coeus.common.framework.type.InvestigatorCreditType
 */
public class InvestigatorCreditTypeRule extends MaintenanceDocumentRuleBase {

    public InvestigatorCreditTypeRule() {
        super();
    }
    
    /**
     * Cannot add duplicates of this document. Verify that the document does not already exist. To do this.
     * <ol>
     *   <li>Check if the document is new or not.</li>
     *   <li>Check for existing business objects with the same primary key values.</li>
     * </ol>
     * 
     * @see org.kuali.rice.krad.rules.DocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.rice.krad.document.Document)
     */
    public boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document)   {
        boolean retval = true;
        if (document.isNew()) {
            InvestigatorCreditType newField = (InvestigatorCreditType) getNewBo();
            
            for (InvestigatorCreditType existingField : getDataObjectService().findAll(InvestigatorCreditType.class).getResults()) {
                retval &= !ObjectUtils.equalByKeys(existingField, newField);
                
                if(!retval) {
                    GlobalVariables.getMessageMap().putError(INVESTIGATOR_CREDIT_TYPE_CODE_PROPERTY_KEY, ERROR_INVESTIGATOR_CREDIT_TYPE_EXISTS, existingField.getCode());
                } 
            }
        }
        
        return retval;
    }

    public DataObjectService getDataObjectService() {
        return getService(DataObjectService.class);
    }    
}
