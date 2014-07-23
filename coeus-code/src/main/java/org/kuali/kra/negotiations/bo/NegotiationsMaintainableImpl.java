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
package org.kuali.kra.negotiations.bo;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.maintenance.KraMaintainableImpl;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.rice.kns.service.KNSServiceLocator;

import java.util.Collection;

/**
 * 
 * This class takes of the business rules for saving a negotiation grouping base object.
 */
public class NegotiationsMaintainableImpl extends KraMaintainableImpl {
    

    private static final long serialVersionUID = -4054390064236611181L;

    @Override
    public void prepareForSave() {
        NegotiationsGroupingBase negotiationBase = (NegotiationsGroupingBase) this.businessObject;
        
        boolean isNew = negotiationBase.getId() == null;
        
        validateCode(negotiationBase, isNew);

        super.prepareForSave();
    }
    
    private void validateCode(NegotiationsGroupingBase negotiationBase, boolean isNew) {
        Collection<? extends NegotiationsGroupingBase> dbCollection = KNSServiceLocator.getBusinessObjectService().findAll(negotiationBase.getClass());
        
        for (NegotiationsGroupingBase dbBase : dbCollection) {
            if (isNew) {
                if (StringUtils.equalsIgnoreCase(negotiationBase.getCode(), dbBase.getCode())) {
                    reportError(negotiationBase.getCode());
                }
            } else {
                if (!negotiationBase.getId().equals(dbBase.getId()) 
                        && StringUtils.equalsIgnoreCase(negotiationBase.getCode(), dbBase.getCode())) {
                    reportError(negotiationBase.getCode());
                }
            }
        }
    }
    
    private void reportError(String codeValue) {
        ErrorReporter errorReporter = KcServiceLocator.getService(ErrorReporter.class);
        errorReporter.reportError("document.newMaintainableObject.code", KeyConstants.NEGOTIATION_STATUS_USED, codeValue);
    }
}