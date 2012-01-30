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
package org.kuali.kra.external.agency.impl;

import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.namespace.QName;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kfs.integration.cg.dto.AgencyCreationStatusDTO;
import org.kuali.kfs.integration.cg.dto.AgencyParametersDTO;
import org.kuali.kfs.integration.cg.service.AgencyCreationService;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.external.agency.AgencyCreationClient;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.service.DateTimeService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.ObjectUtils;

/**
 * This class is the implementation of the client that
 * connects to the financial web service that creates 
 * an Agency.
 * 
 */

public abstract class AgencyCreationClientBase implements AgencyCreationClient {

    private AgencyParametersDTO agencyParameters;
    
    private static final Log LOG = LogFactory.getLog(AgencyCreationClientBase.class);
    protected static final QName SERVICE_NAME = new QName("KFS", "agencyCreationServiceSOAP");
    private static final String ERROR_MESSAGE = "Cannot connect to the service. The service may be down, please try again later.";

    protected abstract AgencyCreationService getServiceHandle();
   
    
    /**
     * This method calls the web service on KFS to create a C&G agency.
     * @see org.kuali.kra.external.agency.AgencyCreationClient#createAgency(org.kuali.kra.agency.home.Agency)
     */
    public void createAgency(Sponsor sponsor) throws DatatypeConfigurationException, WorkflowException {
        
        setAgencyParameters(sponsor);
        agencyParameters = getAgencyParameters();
        AgencyCreationStatusDTO createAgencyResult = null;
        
        try {
            AgencyCreationService port = getServiceHandle();
            LOG.info("Connecting to financial system...");
            createAgencyResult = port.createAgency(agencyParameters);
        } catch (Exception e) {
            LOG.error(ERROR_MESSAGE + e.getMessage(), e);
            GlobalVariables.getMessageMap().putError(KeyConstants.FIN_SYS_SERVICE_CANNOT_CONNECT, KeyConstants.FIN_SYS_SERVICE_CANNOT_CONNECT);
            writeAgencyCreationLogFile();
        }
            
        // If the agency did not get created display the errors
        // the result should never be null if the client connects to the financial system.
        if (createAgencyResult != null) {
            // if failure status
            if (!StringUtils.equalsIgnoreCase(createAgencyResult.getStatus(), "success")) {
                String completeErrorMessage = "";
                List<String> errorMessages = createAgencyResult.getErrorMessages();
                for (String errorMessage : errorMessages) {
                    completeErrorMessage += errorMessage;
                }
                GlobalVariables.getMessageMap().putError(KeyConstants.FIN_SYS_SERVICE_ERRORS, 
                                                     KeyConstants.FIN_SYS_SERVICE_ERRORS, 
                                                     completeErrorMessage);
            } else {
                String financialAgencyDocumentNumber = createAgencyResult.getDocumentNumber();
                if (financialAgencyDocumentNumber == null) {
                    GlobalVariables.getMessageMap().putError(KeyConstants.FIN_SYS_SERVICE_DOCUMENT_NUMBER_NULL, KeyConstants.FIN_SYS_SERVICE_DOCUMENT_NUMBER_NULL);
                    LOG.warn("Document number returned from KFS agency creation service is null.");
                }       
                if (ObjectUtils.isNotNull(createAgencyResult.getErrorMessages()) 
                        && !createAgencyResult.getErrorMessages().isEmpty()) {
                    String completeErrorMessage = "";
                    List<String> errorMessages = createAgencyResult.getErrorMessages();
                    for (String errorMessage : errorMessages) {
                        completeErrorMessage += errorMessage;
                    }
                    GlobalVariables.getMessageMap().putError(KeyConstants.FIN_SYS_SERVICE_SAVED_WITH_ERRORS, 
                                                                 KeyConstants.FIN_SYS_SERVICE_SAVED_WITH_ERRORS,
                                                                 completeErrorMessage);
                }
            }  
        }
    }

      
    /**
     * This method sets the necessary values in the AgencyParametersDTO object to be sent 
     * across to the financial service.
     * @param agency
     * @throws DatatypeConfigurationException
     */
    protected void setAgencyParameters(Sponsor sponsor) throws DatatypeConfigurationException {
        
        agencyParameters = new AgencyParametersDTO();
        
        Rolodex rolodex = KNSServiceLocator.getBusinessObjectService().findBySinglePrimaryKey(Rolodex.class, sponsor.getRolodexId());
        if(rolodex!=null){
            agencyParameters.setAddressName(rolodex.getAddressLine1());
            agencyParameters.setContactName(rolodex.getFullName());
            agencyParameters.setAddressLine1(rolodex.getAddressLine1());
            agencyParameters.setAddressLine2(rolodex.getAddressLine2());
            agencyParameters.setAddressLine3(rolodex.getAddressLine3());
            agencyParameters.setCityName(rolodex.getCity());
            agencyParameters.setStateCode(rolodex.getState());
            agencyParameters.setZipCode(rolodex.getPostalCode());
            agencyParameters.setCountryCode(rolodex.getCountryCode());
            agencyParameters.setPhoneNumber(rolodex.getPhoneNumber());
            agencyParameters.setFaxNumber(rolodex.getFaxNumber());
            agencyParameters.setContactEmailAddress(rolodex.getEmailAddress());
        }
        
        
        agencyParameters.setActive(true);
        agencyParameters.setAgencyNumber(sponsor.getSponsorCode());
        agencyParameters.setAgencyTypeCode(sponsor.getSponsorTypeCode());
        agencyParameters.setFullName(sponsor.getSponsorName());

        //todo make real
        //todo make real
        //todo make real
        //todo make real
        //todo make real
        //todo make real
        //todo make real
        //todo make real
        agencyParameters.setPrincipalId("6162502038");  //temp do khuntley
        
        agencyParameters.setReportingName(sponsor.getAcronym());
        
    }
   
    
    protected AgencyParametersDTO getAgencyParameters() {
        return agencyParameters;
    }
    
    private void writeAgencyCreationLogFile(){
        DateTimeService dateTimeService = KNSServiceLocator.getDateTimeService();
        String runtimeStamp = dateTimeService.toDateTimeStringForFilename(new java.util.Date());
        // ModuleConfiguration systemConfiguration = KNSServiceLocator.getModuleServiceByNamespaceCode("KFS-AR").getModuleConfiguration();
        // String destinationFolderPath = ((FinancialSystemModuleConfiguration) systemConfiguration).getBatchFileDirectories().get(0);
        // String errOutputFile1 = destinationFolderPath + File.separator + Constants.FIN_SYSTEM_INTEGRATION_AGENCY_CREATION_LOG + "_" + runtimeStamp + Constants.LOG_FILE_EXTENSION;

    }
}
