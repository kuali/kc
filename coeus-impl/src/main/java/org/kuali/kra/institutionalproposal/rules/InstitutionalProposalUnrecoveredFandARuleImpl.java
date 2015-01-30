/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.institutionalproposal.rules;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.institutionalproposal.IndirectcostRateType;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalUnrecoveredFandA;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class InstitutionalProposalUnrecoveredFandARuleImpl extends KcTransactionalDocumentRuleBase implements
        InstitutionalProposalUnrecoveredFandARule {
    

    BusinessObjectService businessObjectService;
    
    private static final String NEW_PROPOSAL_UNRECOVERED_FNA = "newInstitutionalProposalUnrecoveredFandA";

    @Override
    public boolean processAddInstitutionalProposalUnrecoveredFandABusinessRules(
            InstitutionalProposalAddUnrecoveredFandARuleEvent institutionalProposalAddUnrecoveredFandARuleEvent) {
        return processCommonValidations(institutionalProposalAddUnrecoveredFandARuleEvent.getUnrecoveredFandAForValidation(), 
                institutionalProposalAddUnrecoveredFandARuleEvent.getInstitutionalProposalUnrecoveredFandAs());
    }

    @Override
    public boolean processSaveInstitutionalProposalUnrecoveredFandABusinessRules(
            InstitutionalProposalSaveUnrecoveredFandARuleEvent institutionalProposalAddUnrecoveredFandARuleEvent) {
        return processCommonValidations(institutionalProposalAddUnrecoveredFandARuleEvent.getUnrecoveredFandAForValidation(), 
                institutionalProposalAddUnrecoveredFandARuleEvent.getInstitutionalProposalUnrecoveredFandAs());
    }
    
    /**
     * This method processes common validations for business rules
     * @param event
     * @return
     */
    public boolean processCommonValidations(InstitutionalProposalUnrecoveredFandA institutionalProposalUnrecoveredFandA, List<InstitutionalProposalUnrecoveredFandA> institutionalProposalUnrecoveredFandAs) {
        boolean validFiscalYearRange = validateUnrecoveredFandAFiscalYearRange(institutionalProposalUnrecoveredFandA);
        
     // test if percentage is valid
        boolean validPercentage = validatePercentage(institutionalProposalUnrecoveredFandA.getApplicableIndirectcostRate());
        
        // test if type is selected and valid
        boolean validRateType = validateRateType(institutionalProposalUnrecoveredFandA.getIndirectcostRateTypeCode());
        
        // test if source account is valid
        boolean validSourceAccount = validateSourceAccount(institutionalProposalUnrecoveredFandA.getSourceAccount());
        
        // test if amount is entered and valid
        boolean validAmount = validateAmount(institutionalProposalUnrecoveredFandA.getAmount());
        
        // test if row is a duplicate
        boolean validRows = checkNoDuplicates(institutionalProposalUnrecoveredFandA, institutionalProposalUnrecoveredFandAs);
 
        return validFiscalYearRange && validPercentage && validRateType && validSourceAccount && validAmount  && validRows;
    }
    
    /**
    *
    * Test fiscal year for valid range.
    * @param AwardCostShare, MessageMap
    * @return Boolean
    */
    public boolean validateUnrecoveredFandAFiscalYearRange(InstitutionalProposalUnrecoveredFandA institutionalProposalUnrecoveredFandA){
        boolean valid = true;
        if (institutionalProposalUnrecoveredFandA.getFiscalYear() != null) {
            try {
                int fiscalYear = Integer.parseInt(institutionalProposalUnrecoveredFandA.getFiscalYear());
                if(fiscalYear < Constants.MIN_FISCAL_YEAR || fiscalYear > Constants.MAX_FISCAL_YEAR) {
                    valid = false;
                    reportError(NEW_PROPOSAL_UNRECOVERED_FNA + ".fiscalYear", 
                            KeyConstants.ERROR_PROPOSAL_UFNA_FISCAL_YEAR_RANGE);
                }
            } catch (NumberFormatException e) {
                valid = false;
                reportError(NEW_PROPOSAL_UNRECOVERED_FNA + ".fiscalYear", 
                        KeyConstants.ERROR_PROPOSAL_UFNA_FISCAL_YEAR_FORMAT);
            }
        }
        else {
            valid = false;
            reportError(NEW_PROPOSAL_UNRECOVERED_FNA+".fiscalYear", 
                    KeyConstants.ERROR_PROPOSAL_UFNA_FISCAL_YEAR_REQUIRED);
        }
        return valid;
    }

    private boolean validatePercentage(ScaleTwoDecimal percentage) {
        boolean isValid = true;
        if (percentage!=null && percentage.isLessThan(new ScaleTwoDecimal(0))) {
            isValid = false;
            this.reportError(Constants.IP_UNRECOVERED_FNA_ACTION_PROPERTY_KEY + ".costSharePercentage", KeyConstants.ERROR_PROPOSAL_UFNA_APPLICABLE_RATE_INVALID);
        }
        return isValid;
    }
    
    private boolean validateRateType(Integer rateTypeCode) {
        boolean isValid = true;
        if (rateTypeCode == null) {
            isValid = false;
            this.reportError(Constants.IP_UNRECOVERED_FNA_ACTION_PROPERTY_KEY + ".rateTypeCode", KeyConstants.ERROR_PROPOSAL_UFNA_RATE_TYPE_CODE_REQUIRED);
        }
        else {
            BusinessObjectService thisBusinessObjectService;
            if (businessObjectService == null) {
                thisBusinessObjectService = getBusinessObjectService();
            }else {
                thisBusinessObjectService = businessObjectService;
            }
            Map<String,Integer> fieldValues = new HashMap<String,Integer>();
            fieldValues.put("indirectcostRateTypeCode", rateTypeCode);
            if (thisBusinessObjectService.countMatching(IndirectcostRateType.class, fieldValues) != 1) {
                isValid = false;
                this.reportError(Constants.IP_UNRECOVERED_FNA_ACTION_PROPERTY_KEY + ".rateTypeCode", KeyConstants.ERROR_PROPOSAL_UFNA_RATE_TYPE_CODE_INVALID, new String[] { rateTypeCode.toString() });
            }
        }
        return isValid;
    }
    
    private boolean validateSourceAccount(String a) {
        boolean isValid = true;
        if (StringUtils.isBlank(a)) {
            isValid = false;
            this.reportError(Constants.IP_UNRECOVERED_FNA_ACTION_PROPERTY_KEY + ".sourceAccount", KeyConstants.ERROR_PROPOSAL_UFNA_SOURCE_ACCOUNT_REQUIRED);
        }
        return isValid;
    }

    private boolean validateAmount(ScaleTwoDecimal amount) {
        boolean isValid = true;
        if (amount == null) {
            isValid = false;
            this.reportError(Constants.IP_UNRECOVERED_FNA_ACTION_PROPERTY_KEY + ".commitmentAmount", KeyConstants.ERROR_PROPOSAL_UFNA_AMOUNT_REQUIRED);
        }
        else if (amount.isLessThan(new ScaleTwoDecimal(0))) {
            isValid = false;
            this.reportError(Constants.IP_UNRECOVERED_FNA_ACTION_PROPERTY_KEY + ".commitmentAmount", KeyConstants.ERROR_PROPOSAL_UFNA_AMOUNT_INVALID, new String[] { amount.toString() });
        }
        return isValid;
    }
    
    private boolean checkNoDuplicates(InstitutionalProposalUnrecoveredFandA institutionalProposalUnrecoveredFandA, List<InstitutionalProposalUnrecoveredFandA> institutionalProposalUnrecoveredFandAs) {
        boolean noDuplicates = true;
        for (InstitutionalProposalUnrecoveredFandA a : institutionalProposalUnrecoveredFandAs) {
            if ((a != institutionalProposalUnrecoveredFandA) &&
                    StringUtils.equals(a.getFiscalYear(), institutionalProposalUnrecoveredFandA.getFiscalYear()) &&
                    ObjectUtils.equals(a.getIndirectcostRateTypeCode(), institutionalProposalUnrecoveredFandA.getIndirectcostRateTypeCode()) &&
                    ObjectUtils.equals(a.getApplicableIndirectcostRate(), institutionalProposalUnrecoveredFandA.getApplicableIndirectcostRate()) &&
                    ObjectUtils.equals(a.getOnCampusFlag(), institutionalProposalUnrecoveredFandA.getOnCampusFlag()) &&
                    StringUtils.equals(a.getSourceAccount(), institutionalProposalUnrecoveredFandA.getSourceAccount()) &&
                    ObjectUtils.equals(a.getAmount(), institutionalProposalUnrecoveredFandA.getAmount())) {
                noDuplicates = false;
                this.reportError(Constants.IP_UNRECOVERED_FNA_ACTION_PROPERTY_KEY + ".fiscalYear", KeyConstants.ERROR_PROPOSAL_UFNA_DUPLICATE_ROW);

            }
        }
        return noDuplicates;
    }
    
    /**
     * Accessor for <code>{@link BusinessObjectService}</code>
     *
     * @param bos BusinessObjectService
     */
    public void setBusinessObjectService(BusinessObjectService bos) {
        businessObjectService = bos;
    }
    
}
