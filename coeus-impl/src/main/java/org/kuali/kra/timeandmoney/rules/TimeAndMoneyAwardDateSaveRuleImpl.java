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
package org.kuali.kra.timeandmoney.rules;

import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.AwardAmountInfoService;
import org.kuali.kra.award.AwardDateRulesHelper;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.award.version.service.AwardVersionService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.kra.timeandmoney.TimeAndMoneyForm;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.rule.event.TimeAndMoneyAwardDateSaveEvent;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.sql.Date;
import java.util.Map.Entry;


public class TimeAndMoneyAwardDateSaveRuleImpl extends KcTransactionalDocumentRuleBase implements TimeAndMoneyAwardDateSaveRule {

    private static final String AWARD_EFFECTIVE_DATE_PROPERTY = "document.awardList[0].awardEffectiveDate";

    private ParameterService parameterService;

    public boolean processSaveAwardDatesBusinessRules(TimeAndMoneyAwardDateSaveEvent timeAndMoneyAwardDateSaveEvent) {
        TimeAndMoneyDocument timeAndMoneyDocument = (TimeAndMoneyDocument) timeAndMoneyAwardDateSaveEvent.getDocument();
        clearFieldsFromUserSessionMap();
        return validateObligatedDates(timeAndMoneyDocument);
    }
    
    private boolean validateObligatedDates(TimeAndMoneyDocument document) {
        boolean valid = true;
        int i = 0;
        for(Entry<String, AwardHierarchyNode> awardHierarchyNode : document.getAwardHierarchyNodes().entrySet()) {
            Award award = getAwardVersionService().getWorkingAwardVersion(awardHierarchyNode.getValue().getAwardNumber());
            AwardAmountInfo aai = getAwardAmountInfoService().fetchAwardAmountInfoWithHighestTransactionId(award.getAwardAmountInfos());
            Date obligatedStartDate = awardHierarchyNode.getValue().getCurrentFundEffectiveDate();
            Date obligatedEndDate = awardHierarchyNode.getValue().getObligationExpirationDate();
            Date projectEndDate = awardHierarchyNode.getValue().getFinalExpirationDate();
            Date projectStartDate = aai.getAward().getAwardEffectiveDate();
            ScaleTwoDecimal obligatedTotal = awardHierarchyNode.getValue().getAmountObligatedToDate();
            
            MessageMap errorMap = GlobalVariables.getMessageMap();
            String fieldStarter =  "awardHierarchyNodeItems[" + (i + 1);
            String awardId = award.getAwardNumber();
            
            String finalExpirationField = fieldStarter + "].finalExpirationDate";
            String obligationExirationField = fieldStarter + "].obligationExpirationDate";
            String currentFundEffectiveField = fieldStarter + "].currentFundEffectiveDate";
            
            boolean validateProjectStartBeforeProjectEnd = AwardDateRulesHelper.validateProjectStartBeforeProjectEnd(errorMap, projectStartDate, projectEndDate, finalExpirationField, awardId);
            boolean validateObligationStartBeforeObligationEnd = AwardDateRulesHelper.validateObligationStartBeforeObligationEnd(errorMap, obligatedStartDate, obligatedEndDate, obligationExirationField, awardId);
            boolean validateProjectStartBeforeObligationStart = AwardDateRulesHelper.validateProjectStartBeforeObligationStart(errorMap, projectStartDate, obligatedStartDate, currentFundEffectiveField, awardId);
            boolean validateProjectStartBeforeObligationEnd = AwardDateRulesHelper.validateProjectStartBeforeObligationEnd(errorMap, projectStartDate, obligatedEndDate, obligationExirationField, awardId);
            boolean validateObligationStartBeforeProjectEnd = AwardDateRulesHelper.validateObligationStartBeforeProjectEnd(errorMap, obligatedStartDate, projectEndDate, currentFundEffectiveField, awardId);
            boolean validateObligationEndBeforeProjectEnd = AwardDateRulesHelper.validateObligationEndBeforeProjectEnd(errorMap, obligatedEndDate, projectEndDate, obligationExirationField, awardId);
            
            if (!validateProjectStartBeforeProjectEnd) {
                addFieldToUserSessionMap(finalExpirationField);
            }
            if (!validateProjectStartBeforeObligationStart || !validateObligationStartBeforeProjectEnd) {
                addFieldToUserSessionMap(currentFundEffectiveField);
            }
            if (!validateObligationStartBeforeObligationEnd || !validateProjectStartBeforeObligationEnd || !validateObligationEndBeforeProjectEnd) {
                addFieldToUserSessionMap(obligationExirationField);
            }
            
            valid = valid && validateProjectStartBeforeProjectEnd && validateObligationStartBeforeObligationEnd && validateProjectStartBeforeObligationStart 
                    && validateProjectStartBeforeObligationEnd && validateObligationStartBeforeProjectEnd && validateObligationEndBeforeProjectEnd;
            
            if (obligatedTotal != null && obligatedTotal.isGreaterThan(new ScaleTwoDecimal(0))) {
                if (obligatedStartDate == null) {
                    if ("1".equals(getParameterService().getParameterValueAsString(Constants.PARAMETER_MODULE_AWARD, Constants.PARAMETER_COMPONENT_DOCUMENT, "ENABLE_AWD_ANT_OBL_DIRECT_INDIRECT_COST"))) {
                        reportError(currentFundEffectiveField, KeyConstants.ERROR_AWARD_EFFECTIVE_DATE_TOTAL);
                    } else {
                        reportError(currentFundEffectiveField, KeyConstants.ERROR_AWARD_EFFECTIVE_DATE);
                    }
                    addFieldToUserSessionMap(currentFundEffectiveField);
                    valid = false;
                }
                if (obligatedEndDate == null) {
                    if ("1".equals(getParameterService().getParameterValueAsString(Constants.PARAMETER_MODULE_AWARD, Constants.PARAMETER_COMPONENT_DOCUMENT, "ENABLE_AWD_ANT_OBL_DIRECT_INDIRECT_COST"))) {
                        reportError(obligationExirationField, KeyConstants.ERROR_OBLIGATION_EXPIRATION_DATE);
                    } else {
                        reportError(obligationExirationField, KeyConstants.ERROR_OBLIGATION_EXPIRATION_DATE);
                    }
                    addFieldToUserSessionMap(obligationExirationField);
                    valid = false;
                }
            }
            i++;
        }
        return valid;
    }
    
    protected void addFieldToUserSessionMap(String fieldName) {
        if (KNSGlobalVariables.getKualiForm() instanceof TimeAndMoneyForm) {
            TimeAndMoneyForm form = (TimeAndMoneyForm) KNSGlobalVariables.getKualiForm();
            form.getFieldsInError().add(fieldName);
        }
    }
    
    protected void clearFieldsFromUserSessionMap() {
        if (KNSGlobalVariables.getKualiForm() instanceof TimeAndMoneyForm) {
            TimeAndMoneyForm form = (TimeAndMoneyForm) KNSGlobalVariables.getKualiForm();
            form.getFieldsInError().clear();
        }
    }
    
    
    private AwardAmountInfoService getAwardAmountInfoService() {
        return KcServiceLocator.getService(AwardAmountInfoService.class);
    }
    
    public AwardVersionService getAwardVersionService() {
        return KcServiceLocator.getService(AwardVersionService.class);
    }
    
    public boolean enforceAwardStartDatePopulated(Award award) {
        boolean valid = true;
        if(award.getAwardEffectiveDate() == null) {
            reportError(AWARD_EFFECTIVE_DATE_PROPERTY, KeyConstants.ERROR_AWARD_EFFECTIVE_DATE_TIME_AND_MONEY);
            valid = false;
        }
        return valid;
    }

    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return this.parameterService;
    }
}