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

import org.kuali.kra.bo.CostShareType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalCostShare;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.KualiDecimal;

/**
 * This class...
 */
public class InstitutionalProposalAddCostShareRuleImpl extends ResearchDocumentRuleBase implements
        InstitutionalProposalAddCostShareRule {

    private static final String NEW_PROPOSAL_COST_SHARE = "newInstitutionalProposalCostShare";
    InstitutionalProposalCostShare institutionalProposalCostShare;

    /**
     * This method checks the Cost Share fields for validity.
     * @param awardCostShareRuleEvent
     * @return true if valid, false otherwise
     */
    public boolean processAddInstitutionalProposalCostShareBusinessRules(InstitutionalProposalAddCostShareRuleEvent institutionalProposalAddCostShareRuleEvent) {
        this.institutionalProposalCostShare = institutionalProposalAddCostShareRuleEvent.getCostShareForValidation();
        
        boolean isValid = processCommonValidations(institutionalProposalCostShare);
        
        // test if percentage is valid
        isValid &= validatePercentage(institutionalProposalCostShare.getCostSharePercentage());
        
        // test if type is selected and valid
        isValid &= validateCostShareType(institutionalProposalCostShare.getCostShareTypeCode());
        
        // test if commitment amount is entered and valid
        isValid &= validateAmount(institutionalProposalCostShare.getAmount());
        
        return isValid;
    }
    
    /**
     * This method processes common validations for business rules
     * @param event
     * @return
     */
    public boolean processCommonValidations(InstitutionalProposalCostShare institutionalProposalCostShare) {
        boolean validFiscalYearRange = validateCostShareFiscalYearRange(institutionalProposalCostShare);
        
        return validFiscalYearRange;
    }
    
   /**
    *
    * Test fiscal year for valid range.
    * @param AwardCostShare, ErrorMap
    * @return Boolean
    */
    public boolean validateCostShareFiscalYearRange(InstitutionalProposalCostShare institutionalProposalCostShare){
        boolean valid = true;
        if (institutionalProposalCostShare.getFiscalYear() != null) {
            try {
                int fiscalYear = Integer.parseInt(institutionalProposalCostShare.getFiscalYear());
                if(fiscalYear < Constants.MIN_FISCAL_YEAR || fiscalYear > Constants.MAX_FISCAL_YEAR) {
                    valid = false;
                    reportError(NEW_PROPOSAL_COST_SHARE+".fiscalYear", 
                            KeyConstants.ERROR_IP_FISCAL_YEAR_RANGE);
                }
            } catch (NumberFormatException e) {
                valid = false;
                reportError(NEW_PROPOSAL_COST_SHARE+".fiscalYear", 
                        KeyConstants.ERROR_IP_FISCAL_YEAR_INCORRECT_FORMAT);
            }
        }
        else {
            valid = false;
            reportError(NEW_PROPOSAL_COST_SHARE+".fiscalYear", 
                    KeyConstants.ERROR_IP_FISCAL_YEAR_REQUIRED);
        }
        return valid;
    }

    private boolean validatePercentage(KualiDecimal percentage) {
        boolean isValid = true;
        if (percentage!=null && percentage.isLessThan(new KualiDecimal(0))) {
            isValid = false;
            this.reportError(Constants.COST_SHARE_ADD_ACTION_PROPERTY_KEY + ".costSharePercentage", KeyConstants.ERROR_COST_SHARE_PERCENTAGE_RANGE);
        }
        return isValid;
    }
    
    private boolean validateCostShareType(Integer costShareTypeCode) {
        boolean isValid = true;
        if (costShareTypeCode == null) {
            isValid = false;
            this.reportError(Constants.IP_COST_SHARE_ADD_ACTION_PROPERTY_KEY + ".costShareTypeCode", KeyConstants.ERROR_IP_COST_SHARE_TYPE_REQUIRED);
        }
        else {
            BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
            Map<String,Integer> fieldValues = new HashMap<String,Integer>();
            fieldValues.put("costShareTypeCode", costShareTypeCode);
            if (businessObjectService.countMatching(CostShareType.class, fieldValues) != 1) {
                isValid = false;
                this.reportError(Constants.IP_COST_SHARE_ADD_ACTION_PROPERTY_KEY + ".costShareTypeCode", KeyConstants.ERROR_IP_COST_SHARE_TYPE_INVALID, new String[] { costShareTypeCode.toString() });
            }
        }
        return isValid;
    }

    private boolean validateAmount(KualiDecimal commitmentAmount) {
        boolean isValid = true;
        if (commitmentAmount == null) {
            isValid = false;
            this.reportError(Constants.IP_COST_SHARE_ADD_ACTION_PROPERTY_KEY + ".commitmentAmount", KeyConstants.ERROR_IP_COST_SHARE_COMMITMENT_AMOUNT_REQUIRED);
        }
        else if (commitmentAmount.isLessThan(new KualiDecimal(0))) {
            isValid = false;
            this.reportError(Constants.IP_COST_SHARE_ADD_ACTION_PROPERTY_KEY + ".commitmentAmount", KeyConstants.ERROR_IP_COST_SHARE_COMMITMENT_AMOUNT_INVALID, new String[] { commitmentAmount.toString() });
        }
        return isValid;
    }
}
