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
package org.kuali.kra.award.budget;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.budget.versions.AddBudgetVersionEvent;
import org.kuali.kra.budget.versions.BudgetVersionRule;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;

public class AwardBudgetVersionRule extends BudgetVersionRule {

    BusinessObjectService businessObjectService;
    DocumentService documentService;
    ParameterService parameterService;
    
    @Override
    public boolean processAddBudgetVersion(AddBudgetVersionEvent event) throws WorkflowException {
        //cannot check data on budget here because when creating an award budget the budget could be
        //saved when copying the previously posted budget which means even if the rules here fail, the
        //budget will already have been created. So must check the rules against the award only.
        //Also the budget is null in the event when this is called right now.
        boolean success = true;
        Award award = ((AwardDocument) event.getDocument()).getAward();
        if(!award.getObligatedDistributableTotal().isPositive()){
            GlobalVariables.getMessageMap().putError(event.getErrorPathPrefix(), 
                  KeyConstants.ERROR_BUDGET_OBLIGATED_AMOUNT_INVALID, "Name");
            success &= false;
        }
        if(award.getRequestedStartDateInitial() == null){
            GlobalVariables.getMessageMap().putError(event.getErrorPathPrefix(), 
                    KeyConstants.ERROR_AWARD_BUDGET_START_DATE_MISSING);
            success &= false;
        }
        if(award.getRequestedEndDateInitial() == null){
            GlobalVariables.getMessageMap().putError(event.getErrorPathPrefix(), 
                    KeyConstants.ERROR_AWARD_BUDGET_END_DATE_MISSING);
            success &= false;
        }
        
        Map<String, Long> fieldValues = new HashMap<String, Long>();
        fieldValues.put("awardId", award.getAwardId());
        List<Award> awards = (List<Award>)getBusinessObjectService().findMatchingOrderBy(Award.class, fieldValues, "awardId", true);
        boolean anyAwardVersionFinal = false;
        boolean anyTimeAndMoneyDocumentsFinal = false;
        //test for a "Final" Award version.
        for(Award testAward : awards) {
            Award wfAward = 
                ((AwardDocument) getDocumentService().getByDocumentHeaderId(award.getAwardDocument().getDocumentHeader().getDocumentNumber())).getAward();
            if (wfAward.getAwardDocument().getDocumentHeader().hasWorkflowDocument()) {
                if (wfAward.getAwardDocument().getDocumentHeader().getWorkflowDocument().isFinal()) {
                    anyAwardVersionFinal = true;
                    break;
                }
            }
        }
        //get latest award version to test that there is a "Final" Time And Money document.
        Award testTandMAward = awards.get(awards.size() - 1);
        String timeAndMoneyDocumentNumber = null;
        //get the first T&M document created for this award version to test that it is final.  If this one is not final, then there
        //cannot be a final T&M document.
        for(AwardAmountInfo awardAmountInfo : award.getAwardAmountInfos()) {
            if (!(awardAmountInfo.getTimeAndMoneyDocumentNumber() == null)) {
                timeAndMoneyDocumentNumber = awardAmountInfo.getTimeAndMoneyDocumentNumber();
                break;
            }
        }
        Map<String, Object> fieldValues1 = new HashMap<String, Object>();
        fieldValues1.put("documentNumber", timeAndMoneyDocumentNumber);
        List<TimeAndMoneyDocument> timeAndMoneyDocuments =
            (List<TimeAndMoneyDocument>)getBusinessObjectService().findMatching(TimeAndMoneyDocument.class, fieldValues1);
        if(!timeAndMoneyDocuments.isEmpty()) {
            TimeAndMoneyDocument timeAndMoneyDocument = 
                (TimeAndMoneyDocument)getDocumentService().getByDocumentHeaderId(timeAndMoneyDocuments.get(0).getDocumentHeader().getDocumentNumber());
            if(timeAndMoneyDocument.getDocumentHeader().hasWorkflowDocument()) {
                if(timeAndMoneyDocument.getDocumentHeader().getWorkflowDocument().isFinal()) {
                    anyTimeAndMoneyDocumentsFinal = true;
                }
            }
        }
        if(!anyAwardVersionFinal && !anyTimeAndMoneyDocumentsFinal) {
            GlobalVariables.getMessageMap().putError(event.getErrorPathPrefix(), KeyConstants.ERROR_AWARD_OR_MONEY_DOC_NOT_FINAL);
            success &= false;
        }
        
        
        
        
        return success;
    }
    
    /**
     * Gets the businessObjectService attribute. 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        return businessObjectService;
    }

    /**
     * Sets the businessObjectService attribute value.
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    /**
     * Gets the documentService attribute. 
     * @return Returns the documentService.
     */
    public DocumentService getDocumentService() {
        documentService = KraServiceLocator.getService(DocumentService.class);
        return documentService;
    }

    /**
     * Sets the documentService attribute value.
     * @param documentService The documentService to set.
     */
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    protected ParameterService getParameterService() {
        if (parameterService == null) {
            parameterService = KraServiceLocator.getService(ParameterService.class);
        }
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
}
