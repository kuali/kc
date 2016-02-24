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
package org.kuali.kra.external.award;

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
