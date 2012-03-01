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
package org.kuali.kra.award.detailsdates;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.document.authorization.AwardDocumentAuthorizer;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardTransferringSponsor;
import org.kuali.kra.award.home.CFDA;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.external.award.AccountCreationClient;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.util.ObjectUtils;

/**
 * Default implementation of AwardDetailsAndDatesRule
 * 
 * @author Kuali Coeus development team (kc.dev@kuali.org)
 */
public class AwardDetailsAndDatesRuleImpl extends ResearchDocumentRuleBase implements AwardDetailsAndDatesRule {
    
    private static final String SPONSOR_CODE_PROPERTY_NAME = "detailsAndDatesFormHelper.newAwardTransferringSponsor.sponsorCode";
    private static final String ANTICIPATED_AMOUNT_PROPERTY_NAME = "awardAmountInfos[0].anticipatedTotalAmount";
    private static final String OBLIGATED_AMOUNT_PROPERTY_NAME = "awardAmountInfos[0].amountObligatedToDate";
    private static final String AWARD_EFFECTIVE_DATE_PROPERTY_NAME = "awardEffectiveDate";
    private static final String OBLIGATION_EXPIRATION_DATE_PROPERTY_NAME = "obligationExpirationDate";
    private static final String AWARD_ACCOUNT_NUMBER_PROPERTY_NAME = "accountNumber";
    private static final String AWARD_FIN_CHART_OF_ACCOUNTS_CODE_PROPERTY_NAME = "financialChartOfAccountsCode";
    private ParameterService parameterService;
    AccountCreationClient accountCreationClient;
    
    /**
     * @see org.kuali.kra.award.detailsdates.AwardDetailsAndDatesRule#processAddAwardTransferringSponsorEvent
     * (org.kuali.kra.award.rule.event.AddAwardTransferringSponsorEvent)
     */
    public boolean processAddAwardTransferringSponsorEvent(AddAwardTransferringSponsorEvent addAwardTransferringSponsorEvent) {
        boolean valid = true;
        Sponsor sponsor = addAwardTransferringSponsorEvent.getSponsorToBecomeAwardTransferringSponsor();
        if (isUnknownSponsor(sponsor)) {
            valid = false;
            reportError(SPONSOR_CODE_PROPERTY_NAME, KeyConstants.ERROR_INVALID_AWARD_TRANSFERRING_SPONSOR);
        }
        if (isDuplicateSponsor(sponsor, addAwardTransferringSponsorEvent.getAward().getAwardTransferringSponsors())) {
            valid = false;
            reportError(SPONSOR_CODE_PROPERTY_NAME, KeyConstants.ERROR_DUPLICATE_AWARD_TRANSFERRING_SPONSOR);
        }
        return valid;
    }
    
    // Check whether the Sponsor has a record in the system.
    private boolean isUnknownSponsor(Sponsor sponsor) {
        Sponsor dbSponsor = null;
        if (sponsor != null && sponsor.getSponsorCode() != null) {
            dbSponsor = (Sponsor) getBusinessObjectService().retrieve(sponsor);
        }
        return dbSponsor == null;
    }
    
    // Check whether the Sponsor has already been added.
    private boolean isDuplicateSponsor(Sponsor sponsor, List<AwardTransferringSponsor> awardTransferringSponsors) {
        for (AwardTransferringSponsor awardTransferringSponsor: awardTransferringSponsors) {
            if (awardTransferringSponsor.getSponsorCode().equals(sponsor.getSponsorCode())) {
                return true;
            }
        }
        return false;
    }

    public boolean processSaveAwardDetailsAndDates(AwardDetailsAndDatesSaveEvent awardDetailsAndDatesSaveEvent) {
        boolean valid = true;
        Award award = awardDetailsAndDatesSaveEvent.getAward();
        if(award.getAnticipatedTotal().isLessThan(award.getObligatedTotal())) {
            valid = false;
            reportError(ANTICIPATED_AMOUNT_PROPERTY_NAME, KeyConstants.ERROR_ANTICIPATED_AMOUNT);
        }
        if (award.getObligatedTotal().isLessThan(KualiDecimal.ZERO)) {
            valid = false;
            reportError(OBLIGATED_AMOUNT_PROPERTY_NAME, KeyConstants.ERROR_OBLIGATED_AMOUNT_NEGATIVE);
        }
        if (award.getAnticipatedTotal().isLessThan(KualiDecimal.ZERO)) {
            valid = false;
            reportError(ANTICIPATED_AMOUNT_PROPERTY_NAME, KeyConstants.ERROR_ANTICIPATED_AMOUNT_NEGATIVE);
        }
        if(award.getObligatedTotal().isGreaterThan(new KualiDecimal(0)) &&
                //award.getAwardEffectiveDate() == null) {
                award.getAwardAmountInfos().get(award.getAwardAmountInfos().size() - 1).getCurrentFundEffectiveDate() == null) {
            valid = false;
            reportError(AWARD_EFFECTIVE_DATE_PROPERTY_NAME, KeyConstants.ERROR_AWARD_EFFECTIVE_DATE);
        }
        if(award.getObligatedTotal().isGreaterThan(new KualiDecimal(0)) &&
                //award.getObligationExpirationDate() == null) {
                award.getAwardAmountInfos().get(award.getAwardAmountInfos().size() - 1).getObligationExpirationDate() == null) {
            valid = false;
            reportError(OBLIGATION_EXPIRATION_DATE_PROPERTY_NAME, KeyConstants.ERROR_OBLIGATION_EXPIRATION_DATE);
        }
        if (!isValidAccountNumber(award)) {
            valid &= false;
        }
        
        if (!isValidCfdaNumber(award)) {
            valid &= false; 
            reportError(Constants.CFDA_NUMBER, KeyConstants.ERROR_INVALID_CFDA, award.getCfdaNumber());
        }
        return valid;
    }
    
    /*
     * This check is only done if the integration parameter is on, otherwise the regular checks 
     * are used
     */
    protected boolean isValidCfdaNumber(Award award) {
        if (isIntegrationParameterOn() && StringUtils.isNotEmpty(award.getCfdaNumber())) {
            Map<String, String> criteria = new HashMap<String, String>();
            criteria.put("cfdaNumber", award.getCfdaNumber());
            CFDA cfdaNumber = (CFDA) getBusinessObjectService().findByPrimaryKey(CFDA.class, criteria);
            return ObjectUtils.isNotNull(cfdaNumber) ? true : false;
        } 
        return true;
    }
    
    /**
     * This method checks if the account number is valid and if the chart of accounts code is present,
     *  it checks if the combination of account number and chart code is valid.
     * @param award
     * @return
     */
    protected boolean isValidAccountNumber(Award award) {
        boolean isValid = true;
        // Only if the financial system integration parameter is on,
        // use the financial system service to verify if the account number is valid,
        String accountNumber = award.getAccountNumber();
        String financialDocNbr = award.getFinancialAccountDocumentNumber();
        String chartOfAccountsCode = award.getFinancialChartOfAccountsCode();

        // If the financial doc nbr is present, it means the account number is present as a result of  
        // creating a financial account. Need not check for valid account number of chart in this case.
        // Because at this point if the account doc in KFS is only being saved and not routed then this will return
        // false because the account does not exist yet on KFS.
        if (isIntegrationParameterOn() && StringUtils.isEmpty(financialDocNbr) && validationRequired(award)) { 
            if (ObjectUtils.isNotNull(accountNumber) || ObjectUtils.isNotNull(chartOfAccountsCode)) {
                // check is user is authorized to link accounts
                AwardDocumentAuthorizer authorizer = new AwardDocumentAuthorizer();
                if (!authorizer.hasCreateAccountPermission()) {
                    reportError(AWARD_ACCOUNT_NUMBER_PROPERTY_NAME, KeyConstants.NO_PERMISSION_TO_LINK_ACCOUNT);
                    return false;
                }
            
                AccountCreationClient client = getAccountCreationClientService();            

                if (ObjectUtils.isNull(chartOfAccountsCode) || ObjectUtils.isNull(accountNumber)) {
                    isValid &= false;
                   //report error
                    reportError(AWARD_ACCOUNT_NUMBER_PROPERTY_NAME, KeyConstants.BOTH_ACCOUNT_AND_CHART_REQUIRED);
                } else {
                    String isValidChartAccount = client.isValidChartAccount(chartOfAccountsCode, accountNumber);
                    if (ObjectUtils.isNull(isValidChartAccount)) {
                        // Error if cannot connect to financial system service
                        reportError(AWARD_FIN_CHART_OF_ACCOUNTS_CODE_PROPERTY_NAME, KeyConstants.VALIDATION_DID_NOT_OCCUR);
                        isValid &= false; 
                    }
                    if (StringUtils.equalsIgnoreCase(isValidChartAccount, "false")) {
                        reportError(AWARD_ACCOUNT_NUMBER_PROPERTY_NAME, 
                                    KeyConstants.AWARD_CHART_OF_ACCOUNTS_CODE_NOT_VALID, 
                                    award.getAccountNumber(), award.getFinancialChartOfAccountsCode());               
                        isValid &= false;
                    }
                }
            }   
        } 
        return isValid;
    }
    
    /**
     * If the award account number and the chart did not change, validation is
     * not required.
     * @param award
     * @return
     */
    protected boolean validationRequired(Award award) {
        boolean isRequired = true;
        // If awardId is null, new award is being created, so validation required
        if (ObjectUtils.isNotNull(award.getAwardId())) {
            Map<String, String> criteria = new HashMap<String, String>();
            criteria.put("awardId", award.getAwardId() + "");
            Award awardStored = (Award) getBusinessObjectService().findByPrimaryKey(Award.class, criteria);
            if (ObjectUtils.isNotNull(awardStored)) {
                String accountNumberStored = awardStored.getAccountNumber(); 
                String chartStored = awardStored.getFinancialChartOfAccountsCode();
                if (ObjectUtils.isNull(award.getAccountNumber()) || ObjectUtils.isNull(award.getFinancialChartOfAccountsCode())) {
                    return true;
                }
                if (award.getAccountNumber().equalsIgnoreCase(accountNumberStored)
                    && chartStored.equalsIgnoreCase(award.getFinancialChartOfAccountsCode())) {
                    isRequired &= false;
                }
            }
        }
        return isRequired;
    }
    
    protected AccountCreationClient getAccountCreationClientService() {
        if (accountCreationClient == null) {
            accountCreationClient = (AccountCreationClient) KraServiceLocator.getService("accountCreationClient");
        }
        return accountCreationClient;
    }
    
    protected boolean isIntegrationParameterOn() {
        String integrationOn = getParameterService().getParameterValueAsString(Constants.MODULE_NAMESPACE_AWARD, 
                                Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.FIN_SYSTEM_INTEGRATION_ON_OFF_PARAMETER);
        return StringUtils.equalsIgnoreCase(integrationOn, Constants.FIN_SYSTEM_INTEGRATION_ON) ? true : false;
    }
    
    protected ParameterService getParameterService() {
        if(parameterService == null) {
            parameterService = KraServiceLocator.getService("parameterService");
        }
        return parameterService;
        
    }
}
