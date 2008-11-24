/*
 * Copyright 2006-2008 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.award.rules;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.document.Document;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.award.bo.AwardIndirectCostRate;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.rule.AddIndirectCostRateRule;
import org.kuali.kra.award.rule.event.AddAwardIndirectCostRateEvent;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

/**
 * Main Business Rule class for <code>{@link AwardDocument}</code>. Responsible for delegating rules to independent rule classes.
 *
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class AwardDocumentRule extends ResearchDocumentRuleBase implements AddIndirectCostRateRule {
    
    public static final String DOCUMENT_ERROR_PATH = "document";
    public static final String AWARD_ERROR_PATH = "award";
    
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(Document document) {
        return super.processCustomRouteDocumentBusinessRules(document);
    }

    @Override
    protected boolean processCustomSaveDocumentBusinessRules(Document document) {
        boolean retval = true;
        if (!(document instanceof AwardDocument)) {
            return false;
        }
        
        retval &= processAwardIndirectCostRateBusinessRules(document);

        return retval;
    }


    /**
     * @see org.kuali.core.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.core.document.Document)
     */
    public boolean processRunAuditBusinessRules(Document document){
        return super.processRunAuditBusinessRules(document);
    }
    
    /**
     * 
     * @see org.kuali.kra.award.rule.AddIndirectCostRateRule#processAddIndirectCostRatesBusinessRules(org.kuali.kra.award.rule.event.AddAwardIndirectCostRateEvent)
     */
    public boolean processAddIndirectCostRatesBusinessRules(AddAwardIndirectCostRateEvent 
            addAwardIndirectCostRateEvent) {        
        return new AwardIndirectCostRateRule().processAddIndirectCostRatesBusinessRules(
                addAwardIndirectCostRateEvent);            
    }    
    
    protected boolean processAwardIndirectCostRateBusinessRules(Document document) {
        boolean retval = true;
        AwardDocument awardDocument = (AwardDocument)document;
        if(StringUtils.equalsIgnoreCase(
                getKualiConfigurationService().getParameter(Constants.PARAMETER_MODULE_AWARD, 
                        Constants.PARAMETER_COMPONENT_DOCUMENT,
                        KeyConstants.MIT_IDC_VALIDATION_ENABLED).getParameterValue(),KeyConstants.MIT_IDC_VALIDATION_ENABLED_VALUE_FOR_COMPARISON)){
            retval = isIndirectCostRateInputInPairs(awardDocument);
        }        
        return retval;
    }
    
    protected KualiConfigurationService getKualiConfigurationService(){
        return KraServiceLocator.getService(KualiConfigurationService.class);
    }
    
    protected boolean isIndirectCostRateInputInPairs(AwardDocument awardDocument){
        HashMap<Integer,String> a1 = new HashMap<Integer,String>();
        HashMap<Integer,String> b1 = new HashMap<Integer,String>();        
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        int i=0;
        
        errorMap.addToErrorPath(DOCUMENT_ERROR_PATH);
        errorMap.addToErrorPath(AWARD_ERROR_PATH);
        
        for(AwardIndirectCostRate awardIndirectCostRate:awardDocument.getAward().getAwardIndirectCostRate()){
            if(StringUtils.equalsIgnoreCase(awardIndirectCostRate.getOnCampusFlag(),"N")){
                a1.put(awardIndirectCostRate.getIdcRateTypeCode(), awardIndirectCostRate.getOnCampusFlag());
            }else if(StringUtils.equalsIgnoreCase(awardIndirectCostRate.getOnCampusFlag(),"F")){
                b1.put(awardIndirectCostRate.getIdcRateTypeCode(), awardIndirectCostRate.getOnCampusFlag());
            }
        }       
        
        for(AwardIndirectCostRate awardIndirectCostRate:awardDocument.getAward().getAwardIndirectCostRate()){            
            if((a1.containsKey(awardIndirectCostRate.getIdcRateTypeCode()) && !b1.containsKey(awardIndirectCostRate.getIdcRateTypeCode()))
                    ||(b1.containsKey(awardIndirectCostRate.getIdcRateTypeCode()) && !a1.containsKey(awardIndirectCostRate.getIdcRateTypeCode()))){                
                errorMap.putError("awardIndirectCostRate[" + i + "].idcRateTypeCode", KeyConstants.INDIRECT_COST_RATE_NOT_IN_PAIR);
                return false;
            }
            i++;
        }
        return true;        
    }
}