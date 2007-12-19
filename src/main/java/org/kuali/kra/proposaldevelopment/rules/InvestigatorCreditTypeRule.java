/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.rules;

import static org.kuali.core.util.GlobalVariables.getErrorMap;
import static org.kuali.core.util.ObjectUtils.equalByKeys;
import static org.kuali.kra.infrastructure.Constants.INVESTIGATOR_CREDIT_TYPE_CODE_PROPERTY_KEY;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_INVESTIGATOR_CREDIT_TYPE_EXISTS;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.util.Collection;

import org.kuali.core.document.MaintenanceDocument;
import org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.kra.proposaldevelopment.bo.InvestigatorCreditType;

/**
 * Business Rules implementation for modifying <code>{@link InvestigatorCreditType}</code> Business Object instances.
 * 
 * @see org.kuali.kra.proposaldevelopment.bo.InvestigatorCreditType
 */
public class InvestigatorCreditTypeRule extends MaintenanceDocumentRuleBase {

    /**
     * Default constructor.
     * 
     * Constructs a InvestigatorCreditTypeRule instance.
     */
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
     * @see org.kuali.core.rules.DocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.core.document.Document)
     */
    public boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document)   {
        boolean retval = true;
        if (document.isNew()) {
            InvestigatorCreditType newField = (InvestigatorCreditType) getNewBo();
            
            for (InvestigatorCreditType existingField : (Collection<InvestigatorCreditType>) getBusinessObjectService().findAll(InvestigatorCreditType.class)) {
                retval &= !equalByKeys(existingField, newField);
                getErrorMap().putError(INVESTIGATOR_CREDIT_TYPE_CODE_PROPERTY_KEY, ERROR_INVESTIGATOR_CREDIT_TYPE_EXISTS, existingField.getInvCreditTypeCode()); 
            }
        }
        
        return retval;
    }
    
    /**
     * Read Only Access to <code>{@link BusinessObjectService}</code>
     * 
     * @return BusinessObjectService instance
     */
    public BusinessObjectService getBusinessObjectService() {
        return getService(BusinessObjectService.class);
    }    
}
