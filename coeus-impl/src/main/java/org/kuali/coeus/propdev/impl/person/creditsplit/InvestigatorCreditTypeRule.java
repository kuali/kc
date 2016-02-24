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
package org.kuali.coeus.propdev.impl.person.creditsplit;

import org.kuali.coeus.common.framework.type.InvestigatorCreditType;
import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;

import java.util.Collection;

import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;
import static org.kuali.kra.infrastructure.Constants.INVESTIGATOR_CREDIT_TYPE_CODE_PROPERTY_KEY;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_INVESTIGATOR_CREDIT_TYPE_EXISTS;

/**
 * Business Rules implementation for modifying <code>{@link InvestigatorCreditType}</code> Business Object instances.
 * 
 * @see org.kuali.coeus.common.framework.type.InvestigatorCreditType
 */
public class InvestigatorCreditTypeRule extends KcMaintenanceDocumentRuleBase {

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
            
            for (InvestigatorCreditType existingField : (Collection<InvestigatorCreditType>) getBusinessObjectService().findAll(InvestigatorCreditType.class)) {
                retval &= !ObjectUtils.equalByKeys(existingField, newField);
                
                if(!retval) {
                    GlobalVariables.getMessageMap().putError(INVESTIGATOR_CREDIT_TYPE_CODE_PROPERTY_KEY, ERROR_INVESTIGATOR_CREDIT_TYPE_EXISTS, existingField.getCode());
                } 
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
