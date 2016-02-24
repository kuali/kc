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
package org.kuali.kra.award.detailsdates;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardTransferringSponsor;
import org.kuali.kra.award.home.CFDA;
import org.kuali.kra.external.award.AccountCreationClient;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Default implementation of AwardDetailsAndDatesRule
 * 
 * @author Kuali Coeus development team (kc.dev@kuali.org)
 */
public class AwardDetailsAndDatesRuleImpl extends KcTransactionalDocumentRuleBase implements AwardDetailsAndDatesRule {
    
    private static final String SPONSOR_CODE_PROPERTY_NAME = "detailsAndDatesFormHelper.newAwardTransferringSponsor.sponsorCode";
    private static final String ANTICIPATED_AMOUNT_PROPERTY_NAME = "awardAmountInfos[0].anticipatedTotalAmount";
    private static final String OBLIGATED_AMOUNT_PROPERTY_NAME = "awardAmountInfos[0].amountObligatedToDate";
    private static final String AWARD_EFFECTIVE_DATE_PROPERTY_NAME = "awardAmountInfos[0].currentFundEffectiveDate";
    private static final String OBLIGATION_EXPIRATION_DATE_PROPERTY_NAME = "awardAmountInfos[0].obligationExpirationDate";
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
        if (award.getObligatedTotal().isLessThan(ScaleTwoDecimal.ZERO)) {
            valid = false;
            reportError(OBLIGATED_AMOUNT_PROPERTY_NAME, KeyConstants.ERROR_OBLIGATED_AMOUNT_NEGATIVE);
        }
        if (award.getAnticipatedTotal().isLessThan(ScaleTwoDecimal.ZERO)) {
            valid = false;
            reportError(ANTICIPATED_AMOUNT_PROPERTY_NAME, KeyConstants.ERROR_ANTICIPATED_AMOUNT_NEGATIVE);
        }
        if(award.getObligatedTotal().isGreaterThan(new ScaleTwoDecimal(0)) &&
                //award.getAwardEffectiveDate() == null) {
                award.getAwardAmountInfos().get(award.getAwardAmountInfos().size() - 1).getCurrentFundEffectiveDate() == null) {
            valid = false;
            if ("1".equals(getParameterService().getParameterValueAsString(Constants.PARAMETER_MODULE_AWARD, Constants.PARAMETER_COMPONENT_DOCUMENT, "ENABLE_AWD_ANT_OBL_DIRECT_INDIRECT_COST"))) {
                reportError(AWARD_EFFECTIVE_DATE_PROPERTY_NAME, KeyConstants.ERROR_AWARD_EFFECTIVE_DATE_TOTAL);
            } else {
                reportError(AWARD_EFFECTIVE_DATE_PROPERTY_NAME, KeyConstants.ERROR_AWARD_EFFECTIVE_DATE);
            }
        }
        if(award.getObligatedTotal().isGreaterThan(new ScaleTwoDecimal(0)) &&
                //award.getObligationExpirationDate() == null) {
                award.getAwardAmountInfos().get(award.getAwardAmountInfos().size() - 1).getObligationExpirationDate() == null) {
            valid = false;
            if ("1".equals(getParameterService().getParameterValueAsString(Constants.PARAMETER_MODULE_AWARD, Constants.PARAMETER_COMPONENT_DOCUMENT, "ENABLE_AWD_ANT_OBL_DIRECT_INDIRECT_COST"))) {
                reportError(OBLIGATION_EXPIRATION_DATE_PROPERTY_NAME, KeyConstants.ERROR_OBLIGATION_EXPIRATION_DATE_TOTAL);
            } else {
                reportError(OBLIGATION_EXPIRATION_DATE_PROPERTY_NAME, KeyConstants.ERROR_OBLIGATION_EXPIRATION_DATE);
            }
        }
        if (!isValidAccountNumber((AwardDocument) awardDetailsAndDatesSaveEvent.getDocument())) {
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
     *  Only if the financial system integration parameter is on,
     *  use the financial system service to verify if the account number is valid.
     * @param award
     * @return
     */
    protected boolean isValidAccountNumber(AwardDocument awardDocument) {
        boolean isValid = true;
        Award award = awardDocument.getAward();
      
        String accountNumber = award.getAccountNumber();
        String financialDocNbr = award.getFinancialAccountDocumentNumber();
        String chartOfAccountsCode = award.getFinancialChartOfAccountsCode();

        /* Only check if financial doc number is absent.
         * If the financial doc nbr is present, it means the account number 
         * is present as a result of creating a financial account. 
         * Need not check for valid account number or chart in this case because KFS returned these values. 
         * At this point if the account doc in KFS is only being saved and not routed then this will return
         * false (which is incorrect behavior) because the account does not *exist* yet on KFS.*/
        if (isIntegrationParameterOn() && StringUtils.isEmpty(financialDocNbr) && validationRequired(award)) { 
            if (ObjectUtils.isNotNull(accountNumber) || ObjectUtils.isNotNull(chartOfAccountsCode)) {               
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
     * not required. Validation is required ONLY if the account number on the eDoc
     * is different from the one stored for the award in the db. This prevents all the
     * round trips back and forth to KFS to verify the account number and chart.
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
            accountCreationClient = (AccountCreationClient) KcServiceLocator.getService("accountCreationClient");
        }
        return accountCreationClient;
    }
    
    protected boolean isIntegrationParameterOn() {
        boolean integrationOn = getParameterService().getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_AWARD, 
                                Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.FIN_SYSTEM_INTEGRATION_ON_OFF_PARAMETER);
        return integrationOn;
    }
    
    protected ParameterService getParameterService() {
        if(parameterService == null) {
            parameterService = KcServiceLocator.getService("parameterService");
        }
        return parameterService;
        
    }
}
