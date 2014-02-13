/*
 * Copyright 2005-2013 The Kuali Foundation.
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

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.budget.core.BudgetService;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.personnel.BudgetPersonnelDetails;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.generator.S2SBaseFormGenerator;
import org.kuali.kra.s2s.generator.bo.KeyPersonInfo;
import org.kuali.kra.s2s.service.S2SBudgetCalculatorService;
import org.kuali.kra.s2s.service.S2SUtilService;
import org.kuali.kra.s2s.util.S2SConstants;
import org.kuali.kra.s2s.validator.S2SErrorHandler;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.krad.service.DocumentService;

import java.util.ArrayList;
import java.util.List;

/**
 * This abstract class has methods that are common to all the versions of RRFedNonFedBudget form.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class RRFedNonFedBudgetBaseGenerator extends S2SBaseFormGenerator {
    protected S2SBudgetCalculatorService s2sBudgetCalculatorService;
    protected S2SUtilService s2sUtilService;
    private DocumentService documentService ;
    protected BudgetService budgetService;
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
        s2sUtilService = KcServiceLocator.getService(S2SUtilService.class);
        s2sBudgetCalculatorService = KcServiceLocator.getService(S2SBudgetCalculatorService.class);
        budgetService = KcServiceLocator.getService(BudgetService.class);
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
                } else if (keyPerson.getRolodexId() != null && budgetPersonnelDetails.getPersonId()
                        .equals(keyPerson.getRolodexId().toString())) {
                    return true;
                }                
             }
           }        
        return false;       
    }
    
    /**
     * Perform manual validations on the budget. Similarly done in RRBudgetBaseGenerator.
     * @param pdDoc
     * @return
     * @throws S2SException
     */
    protected boolean validateBudgetForForm(ProposalDevelopmentDocument pdDoc) throws S2SException {
        boolean valid = true;

        BudgetDocument budget = s2sBudgetCalculatorService.getFinalBudgetVersion(pdDoc);
        if(budget != null) {
            for (BudgetPeriod period : budget.getBudget().getBudgetPeriods()) {
                List<String> participantSupportCode = new ArrayList<String>();
                participantSupportCode.add(budgetService.getParticipantSupportCategoryCode());
                List<BudgetLineItem> participantSupportLineItems = 
                    budgetService.getMatchingLineItems(period.getBudgetLineItems(), participantSupportCode);
                int numberOfParticipants = period.getNumberOfParticipants() == null ? 0 : period.getNumberOfParticipants();
                if (!participantSupportLineItems.isEmpty() && numberOfParticipants == 0) {
                    AuditError auditError= S2SErrorHandler.getError(S2SConstants.PARTICIPANT_COUNT_REQUIRED);
                    AuditError error= new AuditError(auditError.getErrorKey(),
                            auditError.getMessageKey()+period.getBudgetPeriod(),auditError.getLink());
                    getAuditErrors().add(error);
                    valid = false;
                } else if (numberOfParticipants > 0 && participantSupportLineItems.isEmpty()) {
                    getAuditErrors().add(S2SErrorHandler.getError(S2SConstants.PARTICIPANT_COSTS_REQUIRED));
                    valid = false;
                }
            }
        }
        return valid;
    }
    
    /**
     * @return the documentService
     */
    public DocumentService getDocumentService() {
        return KcServiceLocator.getService(DocumentService.class);
    }

    /**
     * @param documentService
     *            the documentService to set
     */
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    protected BudgetService getBudgetService() {
        return budgetService;
    }

    public void setBudgetService(BudgetService budgetService) {
        this.budgetService = budgetService;
    }   

}
