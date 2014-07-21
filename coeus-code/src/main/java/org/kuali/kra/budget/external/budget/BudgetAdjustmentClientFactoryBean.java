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
package org.kuali.kra.budget.external.budget;

import org.kuali.coeus.common.budget.framework.calculator.BudgetCalculationService;
import org.kuali.kra.budget.external.budget.impl.BudgetAdjustmentClientImpl;
import org.kuali.kra.budget.external.budget.impl.BudgetAdjustmentKSBClientImpl;
import org.kuali.kra.external.unit.service.InstitutionalUnitService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("budgetAdjustmentClient")
public class BudgetAdjustmentClientFactoryBean implements FactoryBean {

    @Autowired
    @Qualifier("documentService")
    private DocumentService documentService;

    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;

    @Autowired
    @Qualifier("institutionalUnitService")
    private InstitutionalUnitService institutionalUnitService;

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Autowired
    @Qualifier("budgetCalculationService")
    private BudgetCalculationService budgetCalculationService;

    @Autowired
    @Qualifier("budgetAdjustmentServiceHelper")
    private BudgetAdjustmentServiceHelper budgetAdjustmentServiceHelper;

    @Autowired
    @Qualifier("kualiConfigurationService")
    private ConfigurationService configurationService;

    public Object getObject() throws Exception {
        BudgetAdjustmentClient object = null; 
        if(configurationService.getPropertyValueAsBoolean("shared.rice"))
            object = (BudgetAdjustmentClient) (BudgetAdjustmentKSBClientImpl.getInstance());
        else
            object = (BudgetAdjustmentClient) (BudgetAdjustmentClientImpl.getInstance());
        
        object.setDocumentService(documentService);
        object.setParameterService(parameterService);
        object.setBusinessObjectService(businessObjectService);
        object.setInstitutionalUnitService(institutionalUnitService);
        object.setBudgetCalculationService(budgetCalculationService);
        object.setBudgetAdjustmentServiceHelper(budgetAdjustmentServiceHelper);
        object.setConfigurationService(configurationService);
        return object;
    }
    

    public Class getObjectType() {

        return null;
    }

    public boolean isSingleton() {

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

    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }
}
