/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.institutionalproposal.rules;

import java.util.HashMap;
import java.util.Map;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.IndirectcostRateType;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalCostShare;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalUnrecoveredFandA;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.KualiDecimal;

/**
 * This class...
 */
public class InstitutionalProposalUnrecoveredFandARuleImpl extends ResearchDocumentRuleBase implements
        InstitutionalProposalUnrecoveredFandARule {
    

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 8573006511997627073L;
    
    private static final String NEW_PROPOSAL_UNRECOVERED_FNA = "newInstitutionalProposalUnrecoveredFandA";
    InstitutionalProposalCostShare institutionalProposalCostShare;

    /**
     * @see org.kuali.kra.institutionalproposal.rules.InstitutionalProposalUnrecoveredFandARule#processAddInstitutionalProposalUnrecoveredFandABusinessRules(org.kuali.kra.institutionalproposal.rules.InstitutionalProposalAddUnrecoveredFandARuleEvent)
     */
    public boolean processAddInstitutionalProposalUnrecoveredFandABusinessRules(
            InstitutionalProposalAddUnrecoveredFandARuleEvent institutionalProposalAddUnrecoveredFandARuleEvent) {
        return processCommonValidations(institutionalProposalAddUnrecoveredFandARuleEvent.getUnrecoveredFandAForValidation());
    }

    /**
     * @see org.kuali.kra.institutionalproposal.rules.InstitutionalProposalUnrecoveredFandARule#processSaveInstitutionalProposalUnrecoveredFandABusinessRules(org.kuali.kra.institutionalproposal.rules.InstitutionalProposalSaveUnrecoveredFandARuleEvent)
     */
    public boolean processSaveInstitutionalProposalUnrecoveredFandABusinessRules(
            InstitutionalProposalSaveUnrecoveredFandARuleEvent institutionalProposalAddUnrecoveredFandARuleEvent) {
        return processCommonValidations(institutionalProposalAddUnrecoveredFandARuleEvent.getUnrecoveredFandAForValidation());
    }
    
    /**
     * This method processes common validations for business rules
     * @param event
     * @return
     */
    public boolean processCommonValidations(InstitutionalProposalUnrecoveredFandA institutionalProposalUnrecoveredFandA) {
        boolean validFiscalYearRange = validateUnrecoveredFandAFiscalYearRange(institutionalProposalUnrecoveredFandA);
        
     // test if percentage is valid
        boolean validPercentage = validatePercentage(institutionalProposalUnrecoveredFandA.getApplicableIndirectcostRate());
        
        // test if type is selected and valid
        boolean validRateType = validateRateType(institutionalProposalUnrecoveredFandA.getIndirectcostRateTypeCode());
        
        // test if amount is entered and valid
        boolean validAmount = validateAmount(institutionalProposalUnrecoveredFandA.getAmount());
        
 
        return validFiscalYearRange && validPercentage && validRateType && validAmount;
    }
    
    /**
    *
    * Test fiscal year for valid range.
    * @param AwardCostShare, ErrorMap
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

    private boolean validatePercentage(KualiDecimal percentage) {
        boolean isValid = true;
        if (percentage!=null && percentage.isLessThan(new KualiDecimal(0))) {
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
            BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
            Map<String,Integer> fieldValues = new HashMap<String,Integer>();
            fieldValues.put("indirectcostRateTypeCode", rateTypeCode);
            if (businessObjectService.countMatching(IndirectcostRateType.class, fieldValues) != 1) {
                isValid = false;
                this.reportError(Constants.IP_UNRECOVERED_FNA_ACTION_PROPERTY_KEY + ".rateTypeCode", KeyConstants.ERROR_PROPOSAL_UFNA_RATE_TYPE_CODE_INVALID, new String[] { rateTypeCode.toString() });
            }
        }
        return isValid;
    }

    private boolean validateAmount(KualiDecimal amount) {
        boolean isValid = true;
        if (amount == null) {
            isValid = false;
            this.reportError(Constants.IP_UNRECOVERED_FNA_ACTION_PROPERTY_KEY + ".commitmentAmount", KeyConstants.ERROR_PROPOSAL_UFNA_AMOUNT_REQUIRED);
        }
        else if (amount.isLessThan(new KualiDecimal(0))) {
            isValid = false;
            this.reportError(Constants.IP_UNRECOVERED_FNA_ACTION_PROPERTY_KEY + ".commitmentAmount", KeyConstants.ERROR_PROPOSAL_UFNA_AMOUNT_INVALID, new String[] { amount.toString() });
        }
        return isValid;
    }

}
