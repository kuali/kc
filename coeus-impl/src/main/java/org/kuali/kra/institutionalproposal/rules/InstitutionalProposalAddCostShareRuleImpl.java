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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.CostShareType;
import org.kuali.coeus.common.framework.costshare.CostShareRuleResearchDocumentBase;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalCostShare;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.HashMap;
import java.util.Map;

public class InstitutionalProposalAddCostShareRuleImpl extends CostShareRuleResearchDocumentBase implements
        InstitutionalProposalAddCostShareRule {

    private InstitutionalProposalCostShare institutionalProposalCostShare;
    private String fieldStarter = "";
    private boolean displayNullFieldErrors = true;
    
    @Override
    public boolean processAddInstitutionalProposalCostShareBusinessRules(InstitutionalProposalAddCostShareRuleEvent institutionalProposalAddCostShareRuleEvent) {
        this.fieldStarter = "institutionalProposalCostShareBean.newInstitutionalProposalCostShare";
        this.displayNullFieldErrors = true;
        return proccessRules(institutionalProposalAddCostShareRuleEvent);
    }
    
    @Override
    public boolean processInstitutionalProposalCostShareBusinessRules(InstitutionalProposalAddCostShareRuleEvent institutionalProposalAddCostShareRuleEvent, int i) {
        this.fieldStarter = "document.institutionalProposalList[0].institutionalProposalCostShares["  + i + "]";
        this.displayNullFieldErrors = false;
        return proccessRules(institutionalProposalAddCostShareRuleEvent);
    }
    
    private boolean proccessRules(InstitutionalProposalAddCostShareRuleEvent institutionalProposalAddCostShareRuleEvent) {
        this.institutionalProposalCostShare = institutionalProposalAddCostShareRuleEvent.getCostShareForValidation();
        
        boolean isValid = processCommonValidations(institutionalProposalCostShare);
        
        // test if percentage is valid
        isValid &= validatePercentage(institutionalProposalCostShare.getCostSharePercentage());
        
        // test if type is selected and valid
        isValid &= validateCostShareType(institutionalProposalCostShare.getCostShareTypeCode());
        
        // test if commitment amount is entered and valid
        isValid &= validateAmount(institutionalProposalCostShare.getAmount());
        
        isValid &= validateSourceAccount(institutionalProposalCostShare.getSourceAccount());
        
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
        String projectPeriodField = this.fieldStarter + ".projectPeriod";
        return this.validateProjectPeriod(institutionalProposalCostShare.getProjectPeriod(), projectPeriodField);
    }
    
    /*
    private String getProjectPeriodLabel() {
        String label = KcServiceLocator.getService(CostShareService.class).getCostShareLabel();
        return label;
    }*/

    private boolean validatePercentage(ScaleTwoDecimal percentage) {
        boolean isValid = true;
        String costSharePercentageField = this.fieldStarter + ".costSharePercentage";
        if (percentage != null && percentage.isLessThan(new ScaleTwoDecimal(0))) {
            isValid = false;
            this.reportError(costSharePercentageField, KeyConstants.ERROR_COST_SHARE_PERCENTAGE_RANGE);
        }
        return isValid;
    }
    
    private boolean validateCostShareType(Integer costShareTypeCode) {
        boolean isValid = true;
        String costShareTypeCodeField = this.fieldStarter + ".costShareTypeCode";
        if (costShareTypeCode == null) {
            isValid = false;
            if (displayNullFieldErrors) {
                this.reportError(costShareTypeCodeField, KeyConstants.ERROR_IP_COST_SHARE_TYPE_REQUIRED);
            }
        } else {
            BusinessObjectService businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
            Map<String,Integer> fieldValues = new HashMap<String,Integer>();
            fieldValues.put("costShareTypeCode", costShareTypeCode);
            if (businessObjectService.countMatching(CostShareType.class, fieldValues) != 1) {
                isValid = false;
                this.reportError(costShareTypeCodeField, KeyConstants.ERROR_IP_COST_SHARE_TYPE_INVALID, new String[] { costShareTypeCode.toString() });
            }
        }
        return isValid;
    }

    private boolean validateAmount(ScaleTwoDecimal commitmentAmount) {
        boolean isValid = true;
        String commitmentAmountField = this.fieldStarter + ".amount";
        if (commitmentAmount == null) {
            isValid = false;
            if (displayNullFieldErrors) {
                this.reportError(commitmentAmountField, KeyConstants.ERROR_IP_COST_SHARE_COMMITMENT_AMOUNT_REQUIRED);
            }
        } else if (commitmentAmount.isLessThan(new ScaleTwoDecimal(0))) {
            isValid = false;
            this.reportError(commitmentAmountField, KeyConstants.ERROR_IP_COST_SHARE_COMMITMENT_AMOUNT_INVALID, new String[] { commitmentAmount.toString() });
        }
        return isValid;
    }
    
    private boolean validateSourceAccount(String sourceAccount) {
        boolean isValid = true;
        String sourceAccountField = this.fieldStarter + ".sourceAccount";
        if (StringUtils.isEmpty(sourceAccount)) {
            isValid = false;
            if (displayNullFieldErrors) {
                this.reportError(sourceAccountField, KeyConstants.ERROR_IP_COST_SHARE_SOURCE_ACCOUNT_REQUIRED);
            }
        }
        
        return isValid;
    }
}
