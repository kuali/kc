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
