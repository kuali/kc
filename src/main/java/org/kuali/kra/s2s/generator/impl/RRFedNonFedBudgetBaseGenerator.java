/*
 * Copyright 2005-2010 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.s2s.generator.impl;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.personnel.BudgetPersonnelDetails;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.s2s.generator.S2SBaseFormGenerator;
import org.kuali.kra.s2s.generator.bo.KeyPersonInfo;
import org.kuali.kra.s2s.service.S2SBudgetCalculatorService;
import org.kuali.kra.s2s.service.S2SUtilService;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.service.DocumentService;

/**
 * This abstract class has methods that are common to all the versions of RRFedNonFedBudget form.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class RRFedNonFedBudgetBaseGenerator extends S2SBaseFormGenerator {
    protected S2SBudgetCalculatorService s2sBudgetCalculatorService;
    protected S2SUtilService s2sUtilService;
    private DocumentService documentService ;
    public static final String OTHERPERSONNEL_POSTDOC = "PostDoc";
    public static final String OTHERPERSONNEL_GRADUATE = "Grad";
    public static final String OTHERPERSONNEL_UNDERGRADUATE = "UnderGrad";
    public static final String OTHERPERSONNEL_SECRETARIAL = "Sec";
    public static final String OTHERCOST_DESCRIPTION = "Other";
    protected static final String NID_PD_PI = "PD/PI";
    public static final int ADDITIONAL_KEYPERSONS_ATTACHMENT = 11;
    public static final int BUDGET_JUSTIFICATION_ATTACHMENT = 131;
    public static final int ADDITIONAL_EQUIPMENT_ATTACHMENT = 12;
    
    protected static final int OTHERPERSONNEL_MAX_ALLOWED = 6;
    protected static final int ARRAY_LIMIT_IN_SCHEMA = 4;

    /**
     * 
     * Constructs a RRFedNonFedBudgetBaseGenerator.java.
     */
    public RRFedNonFedBudgetBaseGenerator() {
        s2sUtilService = KraServiceLocator.getService(S2SUtilService.class);
        s2sBudgetCalculatorService = KraServiceLocator.getService(S2SBudgetCalculatorService.class);
    }
    
    /**
     * This method check whether the key person has a personnel budget  
     * 
     * @param keyPerson
     *            (KeyPersonInfo) key person entry.
     * @param period
     *            budget period
     * @return true if key person has personnel budget else false.
     */
    protected Boolean hasPersonnelBudget(KeyPersonInfo keyPerson,int period){
        BudgetDocument budgetDocument = null;
        List<BudgetLineItem> budgetLineItemList = new ArrayList<BudgetLineItem>();
        List<BudgetPersonnelDetails> budgetPersonnelDetailsList = new ArrayList<BudgetPersonnelDetails>();
        
        try {
            budgetDocument = (BudgetDocument) getDocumentService()
            .getByDocumentHeaderId(pdDoc.getBudgetDocumentVersion(0).getDocumentNumber());
            }
            catch (WorkflowException e) {
                e.printStackTrace();
            }           
           budgetLineItemList = budgetDocument.getBudget().getBudgetPeriod(period-1).getBudgetLineItems();
           
           for(BudgetLineItem budgetLineItem : budgetLineItemList) {
             for(BudgetPersonnelDetails budgetPersonnelDetails : budgetLineItem.getBudgetPersonnelDetailsList()){                 
                if( budgetPersonnelDetails.getPersonId().equals(keyPerson.getPersonId())){
                    return true;
                }                 
             }
           }        
        return false;       
    }
    
    /**
     * @return the documentService
     */
    public DocumentService getDocumentService() {
        return KraServiceLocator.getService(DocumentService.class);
    }

    /**
     * @param documentService
     *            the documentService to set
     */
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }   

}
