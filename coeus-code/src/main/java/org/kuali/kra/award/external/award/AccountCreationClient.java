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
package org.kuali.kra.award.external.award;

import org.kuali.kra.award.home.Award;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

import javax.xml.datatype.DatatypeConfigurationException;

public interface AccountCreationClient {

    /**
     * This method creates and award account.
     * @param award
     * @throws DatatypeConfigurationException
     * @throws WorkflowException
     */
    void createAwardAccount(Award award)throws DatatypeConfigurationException, WorkflowException;
    
    /**
     * This method checks if the financial account number is valid.
     * @param accountNumber
     * @return
     */
    String isValidAccountNumber(String accountNumber);
    
    /**
     * This method checks if the combination of account number and chart is valid.
     * @param chartOfAccountsCode
     * @param accountNumber
     * @return
     */
    String isValidChartAccount(String chartOfAccountsCode, String accountNumber);

    void setDocumentService(DocumentService documentService);

    void setBusinessObjectService(BusinessObjectService businessObjectService);
    
    void setParameterService(ParameterService parameterService);

    void setConfigurationService(ConfigurationService configurationService);
}
