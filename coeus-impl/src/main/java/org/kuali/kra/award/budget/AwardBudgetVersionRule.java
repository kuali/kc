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
package org.kuali.kra.award.budget;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.coeus.common.budget.framework.version.AddBudgetVersionEvent;
import org.kuali.coeus.common.budget.framework.version.BudgetVersionRule;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Award award = (Award) event.getBudgetParent();
        if(!award.getObligatedDistributableTotal().isPositive()){
            GlobalVariables.getMessageMap().putError(event.getErrorPath(), 
                  KeyConstants.ERROR_BUDGET_OBLIGATED_AMOUNT_INVALID, "Name");
            success &= false;
        }
        if(award.getRequestedStartDateInitial() == null){
            GlobalVariables.getMessageMap().putError(event.getErrorPath(), 
                    KeyConstants.ERROR_AWARD_BUDGET_START_DATE_MISSING);
            success &= false;
        }
        if(award.getRequestedEndDateInitial() == null){
            GlobalVariables.getMessageMap().putError(event.getErrorPath(), 
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
        String rootAwardNumber = award.getAwardNumber();
        fieldValues1.put("rootAwardNumber", rootAwardNumber);

        List<TimeAndMoneyDocument> timeAndMoneyDocuments = 
            (List<TimeAndMoneyDocument>)getBusinessObjectService().findMatchingOrderBy(TimeAndMoneyDocument.class, fieldValues1, "documentNumber", true);
        
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
            GlobalVariables.getMessageMap().putError(event.getErrorPath(), KeyConstants.ERROR_AWARD_OR_MONEY_DOC_NOT_FINAL);
            success &= false;
        }
        
        return success;
    }
    
    /**
     * Gets the businessObjectService attribute. 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
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
        documentService = KcServiceLocator.getService(DocumentService.class);
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
            parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
}
