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
package org.kuali.kra.external.budget;

import org.kuali.coeus.common.budget.framework.calculator.BudgetCalculationService;
import org.kuali.kra.external.budget.impl.BudgetAdjustmentClientImpl;
import org.kuali.kra.external.budget.impl.BudgetAdjustmentKSBClientImpl;
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
