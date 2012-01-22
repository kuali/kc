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

import javax.xml.datatype.DatatypeConfigurationException;

import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.budget.calculator.BudgetCalculationService;
import org.kuali.kra.external.unit.service.InstitutionalUnitService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

public interface BudgetAdjustmentClient {

    void createBudgetAdjustmentDocument(AwardBudgetDocument awardBudgetDocument)throws DatatypeConfigurationException, WorkflowException, Exception;

    void setDocumentService(DocumentService documentService);

    void setParameterService(ParameterService parameterService);

    void setBusinessObjectService(BusinessObjectService businessObjectService);

    void setInstitutionalUnitService(InstitutionalUnitService institutionalUnitService);

    void setBudgetCalculationService(BudgetCalculationService budgetCalculationService);

    void setBudgetAdjustmentServiceHelper(BudgetAdjustmentServiceHelper businessAdjustmentServiceHelper);
    

}
