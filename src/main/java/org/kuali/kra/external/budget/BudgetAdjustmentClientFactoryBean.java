/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.external.budget;

import org.kuali.kra.budget.calculator.BudgetCalculationService;
import org.kuali.kra.external.budget.impl.BudgetAdjustmentClientImpl;
import org.kuali.kra.external.budget.impl.BudgetAdjustmentKSBClientImpl;
import org.kuali.kra.external.unit.service.InstitutionalUnitService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.springframework.beans.factory.FactoryBean;

public class BudgetAdjustmentClientFactoryBean implements FactoryBean {

    private boolean sharedRice;
    private DocumentService documentService;
    private ParameterService parameterService;
    private InstitutionalUnitService institutionalUnitService;
    private BusinessObjectService businessObjectService;
    private BudgetCalculationService budgetCalculationService;
    private BudgetAdjustmentServiceHelper budgetAdjustmentServiceHelper;

    public Object getObject() throws Exception {
        BudgetAdjustmentClient object = null; 
        if(sharedRice)
            object = (BudgetAdjustmentClient) (BudgetAdjustmentKSBClientImpl.getInstance());
        else
            object = (BudgetAdjustmentClient) (BudgetAdjustmentClientImpl.getInstance());
        
        object.setDocumentService(documentService);
        object.setParameterService(parameterService);
        object.setBusinessObjectService(businessObjectService);
        object.setInstitutionalUnitService(institutionalUnitService);
        object.setBudgetCalculationService(budgetCalculationService);
        object.setBudgetAdjustmentServiceHelper(budgetAdjustmentServiceHelper);
        return object;
    }
    

    public Class getObjectType() {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean isSingleton() {
        // TODO Auto-generated method stub
        return false;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
    
    public void setBudgetAdjustmentServiceHelper(BudgetAdjustmentServiceHelper budgetAdjustmentServiceHelper) {
        this.budgetAdjustmentServiceHelper = budgetAdjustmentServiceHelper;
    }
    
    public void setInstitutionalUnitService(InstitutionalUnitService institutionalUnitService) {
        this.institutionalUnitService = institutionalUnitService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    public void setBudgetCalculationService(BudgetCalculationService budgetCalculationService) {
        this.budgetCalculationService = budgetCalculationService;
    }
    public void setSharedRice(boolean sharedRice) {
        this.sharedRice = sharedRice;
    }
    
    
}
