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
package org.kuali.coeus.common.budget.impl.rate;

import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.coeus.common.budget.framework.rate.RateClass;
import org.kuali.coeus.common.budget.framework.rate.RateType;
import org.kuali.rice.kns.document.MaintenanceDocument;

import java.util.HashMap;
import java.util.Map;

public class RateClassExistenceRule extends KcMaintenanceDocumentRuleBase {
    

    public RateClassExistenceRule() {
        super();
    }
    
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        return checkExistence(document);
    }
    
    @Override
    protected boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        return checkExistence(document);
    }

    /**
     * 
     * This method to check rateclasscode exist in table.
     * @param maintenanceDocument
     * @return
     */
    private boolean checkExistence(MaintenanceDocument maintenanceDocument) {


        boolean valid= true;
        if (LOG.isDebugEnabled()) {
            LOG.debug("new maintainable is: " + maintenanceDocument.getNewMaintainableObject().getClass());
        }
        RateType rateType = (RateType) maintenanceDocument.getNewMaintainableObject().getDataObject();

        Map pkMap = new HashMap();
        pkMap.put("rateClassCode", rateType.getRateClassCode());
        valid=checkExistenceFromTable(RateClass.class,pkMap,"rateClassCode", "Rate Class");


        return valid;

    }
    


}
