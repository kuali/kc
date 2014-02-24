/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.external.award.impl;


import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.kfs.integration.cg.dto.AccountCreationStatusDTO;
import org.kuali.kfs.integration.cg.dto.AccountParametersDTO;
import org.kuali.kfs.module.external.kc.service.AccountCreationService;
import org.kuali.kra.award.commitments.AwardFandaRate;
import org.kuali.kra.award.contacts.AwardUnitContact;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.UnitAdministratorType;
import org.kuali.kra.external.award.AccountCreationClient;
import org.kuali.kra.external.award.FinancialIndirectCostRecoveryTypeCode;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import java.sql.Date;
import java.util.*;



/**
 * This class is the implementation of the client that
 * connects to the financial web service that creates 
 * an account.
 * This class was generated by Apache CXF 2.2.9
 * Wed Aug 04 14:02:35 MST 2010
 * Generated source version: 2.2.9
 * 
 */
public abstract class AccountCreationClientBase implements AccountCreationClient {
    
	protected static final String SOAP_SERVICE_NAME = "accountCreationServiceSOAP";
	protected static final QName SERVICE_NAME = new QName(Constants.FINANCIAL_SYSTEM_SERVICE_NAMESPACE, "accountCreationServiceSOAP");
    private static final String ERROR_MESSAGE = "Cannot connect to the service. The service may be down, please try again later.";

    private static final Log LOG = LogFactory.getLog(AccountCreationClientBase.class);

    private DocumentService documentService;
    private BusinessObjectService businessObjectService;
    private ParameterService parameterService;
    
    protected abstract AccountCreationService getServiceHandle();
    
    /**
     * @see org.kuali.kra.external.award.AccountCreationClient#isValidAccountNumber(java.lang.String)
     */
    public String isValidAccountNumber(String accountNumber) {
        boolean isValidAccountNumber = false;
        
        try {
            AccountCreationService port = getServiceHandle();   
            LOG.info("Connecting to financial system...");
            isValidAccountNumber = port.isValidAccount(accountNumber);
        } catch (Exception e) {
            LOG.error(ERROR_MESSAGE + e.getMessage(), e);
            return null;
        }    
        return isValidAccountNumber + "";
    }
    
    /**
     * @see org.kuali.kra.external.award.AccountCreationClient#isValidChartAccount(java.lang.String, java.lang.String)
     */
    public String isValidChartAccount(String chartOfAccountsCode, String accountNumber) {
        boolean isValidChartOfAccountsCode = false;
        
        try {
            AccountCreationService port = getServiceHandle();   
            LOG.info("Connecting to financial system...");
            isValidChartOfAccountsCode = port.isValidChartAccount(chartOfAccountsCode, accountNumber);
        } catch (Exception e) {
            LOG.error(ERROR_MESSAGE + e.getMessage(), e);
            return null;
        }    
        return isValidChartOfAccountsCode + "";
    }
    
    /**
     * This method calls the web service on KFS to create a C&G account.
     * @see org.kuali.kra.external.award.AccountCreationClient#createAwardAccount(org.kuali.kra.award.home.Award)
     */
    public void createAwardAccount(Award award) throws DatatypeConfigurationException, WorkflowException {

        AccountParametersDTO accountParameters = getAccountParameters(award);
        AccountCreationStatusDTO createAccountResult = null;
        
        try {
            AccountCreationService port = getServiceHandle();   
            LOG.info("Connecting to financial system...");
            createAccountResult = port.createAccount(accountParameters);
        } catch (Exception e) {
            LOG.error(ERROR_MESSAGE + e.getMessage(), e);
            GlobalVariables.getMessageMap().putError(KeyConstants.CANNOT_CONNECT_TO_SERVICE, KeyConstants.CANNOT_CONNECT_TO_SERVICE);
        }
            
        // If the account did not get created display the errors
        // the result should never be null if the client connects to the financial system.
        if (createAccountResult != null) {
            // if failure status
            if (!StringUtils.equalsIgnoreCase(createAccountResult.getStatus(), "success")) {
                String completeErrorMessage = "";
                List<String> errorMessages = createAccountResult.getErrorMessages();
                for (String errorMessage : errorMessages) {
                    completeErrorMessage += errorMessage;
                }
                GlobalVariables.getMessageMap().putError(KeyConstants.CREATE_ACCOUNT_SERVICE_ERRORS, 
                                                     KeyConstants.CREATE_ACCOUNT_SERVICE_ERRORS, 
                                                     completeErrorMessage);
            } else {
                // if account created successfully, then update the award table with the document number and date
                //if there are error messages but the document was saved in KFS
               
                String financialAccountDocumentNumber = createAccountResult.getDocumentNumber();
                if (financialAccountDocumentNumber == null) {
                    GlobalVariables.getMessageMap().putError(KeyConstants.DOCUMENT_NUMBER_NULL, KeyConstants.DOCUMENT_NUMBER_NULL);
                    LOG.warn("Document number returned from KFS account creation service is null.");
                } else {
                    // force account number to upper case since KFS does that. Account number should not be null
                    // at this point.
                    String accountNumber = award.getAccountNumber().toUpperCase();
                    award.setAccountNumber(accountNumber);
                    award.setFinancialAccountDocumentNumber(financialAccountDocumentNumber);
                    Calendar calendar = Calendar.getInstance();
                    award.setFinancialAccountCreationDate(new Date(calendar.getTime().getTime()));
                    award.setFinancialChartOfAccountsCode(createAccountResult.getChartOfAccountsCode());
                    AwardDocument awardDocument = award.getAwardDocument();
                    documentService.saveDocument(awardDocument);
    
                }      
                if (ObjectUtils.isNotNull(createAccountResult.getErrorMessages()) 
                        && !createAccountResult.getErrorMessages().isEmpty()) {
                    String completeErrorMessage = "";
                    List<String> errorMessages = createAccountResult.getErrorMessages();
                    for (String errorMessage : errorMessages) {
                        completeErrorMessage += errorMessage;
                    }
                    GlobalVariables.getMessageMap().putError(KeyConstants.DOCUMENT_SAVED_WITH_ERRORS, 
                                                                 KeyConstants.DOCUMENT_SAVED_WITH_ERRORS,
                                                                 completeErrorMessage);
                }
            }  
        }
    }

   
    /**
     * This method sets the necessary values in the AccountParametersDTO object to be sent 
     * across to the financial service.
     * @param award
     * @throws DatatypeConfigurationException
     */
    protected AccountParametersDTO getAccountParameters(Award award) throws DatatypeConfigurationException {

        AccountParametersDTO accountParameters  = new AccountParametersDTO();
        
        setName(award, accountParameters);
        //Account number
        accountParameters.setAccountNumber(award.getAccountNumber());
        setDefaultAddress(award, accountParameters);
        setAdminAddress(award, accountParameters);       
        
        //cfdaNumber
        String cfdaNumber = award.getCfdaNumber();
        if  (cfdaNumber != null) {
            accountParameters.setCfdaNumber(cfdaNumber);
        }
        
        //effective date
        Date effectiveDate = award.getAwardEffectiveDate(); 
        GregorianCalendar dateEffective = new GregorianCalendar();
        dateEffective.setTime(effectiveDate);
        XMLGregorianCalendar gregorianDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateEffective);
        accountParameters.setEffectiveDate(gregorianDate);
        
        //expiration date
        Date expirationDate = award.getProjectEndDate();
        GregorianCalendar dateExpiration = new GregorianCalendar();
        dateExpiration.setTime(expirationDate);
        gregorianDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateExpiration);
        accountParameters.setExpirationDate(gregorianDate);
        
        // expense guideline text
        String expenseGuidelineText = award.getAwardNumber();
        accountParameters.setExpenseGuidelineText(expenseGuidelineText);
        
        setIncomeGuidelineText(award, accountParameters);
        
        //account purpose text
        accountParameters.setPurposeText(award.getTitle());

        // unit number
        accountParameters.setUnit(award.getUnitNumber());
        //Principal id
        accountParameters.setPrincipalId(GlobalVariables.getUserSession().getPrincipalId());

        // get the current FandaRate
        AwardFandaRate currentFandaRate = award.getCurrentFandaRate();
        
        String rateClassCode = currentFandaRate.getFandaRateType().getRateClassCode();
        String rateTypeCode = currentFandaRate.getFandaRateType().getRateTypeCode();
        String icrTypeCode = getIndirectCostTypeCode(rateClassCode, rateTypeCode);
        //campus on/off indicator
        accountParameters.setOffCampusIndicator(!currentFandaRate.getOnOffCampusFlag()); 
        //indirect cost rate
        String icrRateCode = award.getIcrRateCode();
        if (Award.ICR_RATE_CODE_NONE.equals(icrRateCode)) {
            accountParameters.setIndirectCostRate("");
        } else {
            accountParameters.setIndirectCostRate(icrRateCode);
        }
        
        // indirect cost type code
        accountParameters.setIndirectCostTypeCode(icrTypeCode + "");
        
        //higher education function code
        accountParameters.setHigherEdFunctionCode(award.getActivityType().getHigherEducationFunctionCode());
        
        return accountParameters;
        
    }
   
    protected String getIndirectCostTypeCode(String rateClassCode, String rateTypeCode) {
        Map <String, Object> criteria = new HashMap<String, Object>();
        criteria.put("rateClassCode", rateClassCode);
        criteria.put("rateTypeCode", rateTypeCode);
        FinancialIndirectCostRecoveryTypeCode icrCostTypeCode= (FinancialIndirectCostRecoveryTypeCode) businessObjectService.findByPrimaryKey(FinancialIndirectCostRecoveryTypeCode.class, criteria);
        return ObjectUtils.isNotNull(icrCostTypeCode)? icrCostTypeCode.getIcrTypeCode() : "";
    }

    /**
     * This method sets the name.
     * @param award
     */
    protected void setName(Award award, AccountParametersDTO accountParameters) {
        
        final int ACCOUNT_NAME_LENGTH = 40;
        // Account name
        String accountName = "";
        if (ObjectUtils.isNotNull(award.getSponsor().getAcronym())) {
            accountName += award.getSponsor().getAcronym() + "-";
        }
        if (ObjectUtils.isNotNull(award.getSponsorAwardNumber())) { accountName += award.getSponsorAwardNumber() + "-"; }
        
        if (ObjectUtils.isNotNull(award.getPrincipalInvestigator()) 
            && ObjectUtils.isNotNull(award.getPrincipalInvestigator().getPerson())) {
            accountName +=  award.getPrincipalInvestigator().getPerson().getLastName()
                               + award.getPrincipalInvestigator().getPerson().getFirstName();
        }
            
        // Trimming the name 
        if (ObjectUtils.isNotNull(accountName) && accountName.length() > ACCOUNT_NAME_LENGTH) {
            accountName = accountName.substring(0, ACCOUNT_NAME_LENGTH - 1);
        }
        accountParameters.setAccountName(accountName);
    }
    
    /**
     * This method sets the default address.
     * @param award
     */
    protected void setDefaultAddress(Award award, AccountParametersDTO accountParameters) {
        //default address is the PI address
        KcPerson principalInvestigator = award.getPrincipalInvestigator().getPerson();
        if (ObjectUtils.isNotNull(principalInvestigator)) {
            
            String streetAddress = "";
            if (principalInvestigator.getAddressLine1() != null) {
                streetAddress += principalInvestigator.getAddressLine1();
            }
            if (principalInvestigator.getAddressLine2() != null) {
                streetAddress += principalInvestigator.getAddressLine2();
            }
            
            if (principalInvestigator.getAddressLine3() != null) {
                streetAddress += principalInvestigator.getAddressLine3();
            } 
            accountParameters.setDefaultAddressStreetAddress(streetAddress);
            accountParameters.setDefaultAddressCityName(principalInvestigator.getCity());
            // getState returns state code
            accountParameters.setDefaultAddressStateCode(principalInvestigator.getState());
            accountParameters.setDefaultAddressZipCode(principalInvestigator.getPostalCode());
        }
    }
    
    /**
     * This method sets the admin address.
     * @param award
     */
    protected void setAdminAddress(Award award, AccountParametersDTO accountParameters) {
        List<AwardUnitContact> unitContacts = award.getAwardUnitContacts();
        for (AwardUnitContact contact : unitContacts) {
            contact.refreshReferenceObject("unitAdministratorType");
            // Send the address of the administrative contact
            UnitAdministratorType adminType = contact.getUnitAdministratorType();
            if (ObjectUtils.isNotNull(adminType) 
                && "Administrative Contact".equals(adminType.getDescription())) {
                KcPerson adminPerson = contact.getPerson();
                if (ObjectUtils.isNotNull(adminPerson)) {
                    String adminStreetAddress = "";
                    if (adminPerson.getAddressLine1() != null) {
                        adminStreetAddress += adminPerson.getAddressLine1();
                    }
                    if (adminPerson.getAddressLine2() != null) {
                        adminStreetAddress += adminPerson.getAddressLine2();
                    }
                    
                    if (adminPerson.getAddressLine3() != null) {
                        adminStreetAddress += adminPerson.getAddressLine3();
                    }
             
                    accountParameters.setAdminContactAddressStreetAddress(adminStreetAddress);
                    accountParameters.setAdminContactAddressStreetAddress(adminPerson.getAddressLine1());
                    accountParameters.setAdminContactAddressCityName(adminPerson.getCity());
                    accountParameters.setAdminContactAddressStateCode(adminPerson.getState());
                    accountParameters.setAdminContactAddressZipCode(adminPerson.getPostalCode());
                }
            }
        }
    }
    
    /**
     * This method sets the income guideline text.
     * @param award
     */
    protected void setIncomeGuidelineText(Award award, AccountParametersDTO accountParameters) {
        //income guideline text
        award.refreshReferenceObject("awardBasisOfPayment");        
        String paymentBasis = award.getAwardBasisOfPayment().getDescription();
        award.refreshReferenceObject("awardMethodOfPayment");
        String paymentMethod = award.getAwardMethodOfPayment().getDescription(); 
        
        String incomeGuidelineText = ""; 
        if (paymentBasis != null) {
            incomeGuidelineText += paymentBasis;
        }
        if (paymentMethod != null) {
            incomeGuidelineText += " " + paymentMethod;
        }
        accountParameters.setIncomeGuidelineText(incomeGuidelineText);
    }
  

    /**
     * Sets the documentService attribute value. Injected by Spring.
     * 
     * @param documentService The documentService to set.
     */
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    public ParameterService getParameterService() {
        return parameterService;
    }
    
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
}