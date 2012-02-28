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
package org.kuali.kra.award.budget.document;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.budget.AwardBudgetExt;
import org.kuali.kra.award.budget.AwardBudgetService;
import org.kuali.kra.award.commitments.FandaRateType;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.calculator.RateClassType;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.BudgetParentDocument;
import org.kuali.kra.budget.rates.BudgetRate;
import org.kuali.kra.budget.rates.RateType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.kew.KraDocumentRejectionService;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.COMPONENT;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.NAMESPACE;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.action.ActionTaken;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kew.framework.postprocessor.ActionTakenEvent;
import org.kuali.rice.kew.framework.postprocessor.DocumentRouteStatusChange;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

@NAMESPACE(namespace=Constants.MODULE_NAMESPACE_AWARD_BUDGET)
@COMPONENT(component=ParameterConstants.DOCUMENT_COMPONENT)
public class AwardBudgetDocument extends BudgetDocument<org.kuali.kra.award.home.Award> {

    private static final String AWARD_BUDGET_DOCUMENT_TYPE_CODE = "ABGT";
    private static org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(AwardBudgetDocument.class);
    
    private BudgetDecimal obligatedTotal;
    private boolean budgetRateTypePopulated;
    private transient BudgetParentDocument<Award> newestBudgetParentDocument;
    private transient AwardBudgetService awardBudgetService;
    private Award  currentAward;
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 3564659576355229703L;

    @Override
    public void initialize() {
        Award award = getParentDocument().getBudgetParent();
        this.setCurrentAward(award);
        AwardBudgetExt awardBudget = getAwardBudget();
        awardBudget.setObligatedTotal(new BudgetDecimal(award.getBudgetTotalCostLimit().bigDecimalValue()));
        List<BudgetRate> budgetRates = awardBudget.getBudgetRates();
        populateBudgetRateTypes(awardBudget,budgetRates);
    }
    
    /**
     * Gets the parentDocument attribute. 
     * @return Returns the parentDocument.
     */
    @Override
    public BudgetParentDocument<Award> getParentDocument() {
        BudgetParentDocument<Award> parent = super.getParentDocument();
        if (parent == null) {
            return null;
        } else if (parent.getBudgetParent() == null) {
            return parent;
        } else {
            Award currentAward = getAwardBudgetService().getActiveOrNewestAward(parent.getBudgetParent().getAwardNumber());
            newestBudgetParentDocument = currentAward.getAwardDocument();
        }
        return newestBudgetParentDocument;
    }
    
    /**
     * Gets the currentAward attribute. 
     * @return Returns the currentAward.
     */
    public Award getCurrentAward() {
        return currentAward;
    }

    /**
     * Sets the currentAward attribute value.
     * @param currentAward The currentAward to set.
     */
    public void setCurrentAward(Award currentAward) {
        this.currentAward = currentAward;
    }

    /**
     * @see org.kuali.kra.document.ResearchDocumentBase#getDocumentTypeCode()
     */
    @Override
    public String getDocumentTypeCode() {
        return AWARD_BUDGET_DOCUMENT_TYPE_CODE;
    }

    /**
     * 
     * This method returns Budget object. Creates new budget instance if the budgets list is empty
     * @return Budget
     */
    public Budget getBudget(){
        if(getBudgets().isEmpty()){
            getBudgets().add(new AwardBudgetExt());
        }
        Budget budget = getBudgets().get(0);
        return budget; 
    }
    
    private void populateBudgetRateTypes(Budget budget,List<BudgetRate> budgetRates) {
        for (BudgetRate budgetRate : budgetRates) {
            if(RateClassType.OVERHEAD.getRateClassType().equals(budgetRate.getRateClassType())){
                budgetRate.setBudget(budget);
                if(budgetRate.getNonEditableRateFlag()){
                    budgetRate.setRateType(createAwardRateType(budgetRate));
                }
            }
        }
        
    }

    private RateType createAwardRateType(BudgetRate rate) {
        FandaRateType awardRateType = getBusinesssObjectService().findBySinglePrimaryKey(FandaRateType.class, rate.getRateTypeCode());
        RateType newRateType = new RateType();
        newRateType.setRateClassCode(rate.getRateClassCode());
        newRateType.setRateTypeCode(awardRateType.getFandaRateTypeCode().toString());
        newRateType.setDescription(awardRateType.getDescription());
        return newRateType;
    }

    private BusinessObjectService getBusinesssObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }

    public AwardBudgetExt getAwardBudget(){
        return (AwardBudgetExt)getBudget();
    }

    /**
     * Gets the obligatedAmount attribute. 
     * @return Returns the obligatedAmount.
     */
    public BudgetDecimal getObligatedTotal() {
        return obligatedTotal;
    }

    /**
     * Sets the obligatedAmount attribute value.
     * @param obligatedAmount The obligatedAmount to set.
     */
    public void setObligatedTotal(BudgetDecimal obligatedTotal) {
        this.obligatedTotal = obligatedTotal;
    }
    
    /**
     * Added mthod to enable change of status when the document changes KEW status.
     * @see org.kuali.rice.krad.document.DocumentBase#doRouteStatusChange(org.kuali.rice.kew.framework.postprocessor.DocumentRouteStatusChange)
     */
    public void doRouteStatusChange(DocumentRouteStatusChange dto) {
        super.doRouteStatusChange(dto);
        String newStatus = dto.getNewRouteStatus();
        String oldStatus = dto.getOldRouteStatus();
        boolean changeStatus = false;
        this.setCurrentAward(getParentDocument().getBudgetParent());
      
        if( LOG.isDebugEnabled() )
            LOG.debug(String.format( "Route status change on AwardBudgetDocument #%s from %s to %s.", getDocumentNumber(), oldStatus, newStatus ));
        
        //Only know what to do with disapproved right now, left the
        if( StringUtils.equals( newStatus, KewApiConstants.ROUTE_HEADER_DISAPPROVED_CD )) {
            getAwardBudget().setAwardBudgetStatusCode(Constants.BUDGET_STATUS_CODE_DISAPPROVED );
        } else if( StringUtils.equals( newStatus, KewApiConstants.ROUTE_HEADER_CANCEL_CD ) ) {
            getAwardBudget().setAwardBudgetStatusCode(Constants.BUDGET_STATUS_CODE_CANCELLED );
        } else if( StringUtils.equals( newStatus, KewApiConstants.ROUTE_HEADER_FINAL_CD )) {
            getAwardBudget().setAwardBudgetStatusCode(Constants.BUDGET_STATUS_CODE_TO_BE_POSTED);
            changeStatus = true;
        }
        
        if( changeStatus ) {
            try {
                KraServiceLocator.getService(DocumentService.class).saveDocument(this);
            }
            catch (WorkflowException e) {
                throw new RuntimeException( "Could not save award document on action  taken.");
            }
        }  
    }
    
    
    /**
     * Added method to detect when the document is being approved from the initial node.  In this case
     * the document is actually being re-submitted after a rejection.  The award budget status then 
     * changes back to In Progress.
     * @see org.kuali.rice.krad.document.DocumentBase#doActionTaken(org.kuali.rice.kew.framework.postprocessor.ActionTakenEvent)
     */
    public void doActionTaken(ActionTakenEvent event) {
        super.doActionTaken(event);
        ActionTaken actionTaken = event.getActionTaken();
        KraDocumentRejectionService documentRejectionService = KraServiceLocator.getService(KraDocumentRejectionService.class);
        if( LOG.isDebugEnabled() ) {
            LOG.debug( String.format( "Action taken on document %s: event code %s, action taken is %s"  , getDocumentNumber(), event.getDocumentEventCode(), actionTaken.getActionTaken().getCode()) );
        }
        if( StringUtils.equals( KewApiConstants.ACTION_TAKEN_APPROVED_CD, actionTaken.getActionTaken().getCode()) && documentRejectionService.isDocumentOnInitialNode(this) ) {
            //the document is being approved from the initial node.
            //this means it was rejected and is now being approved by the initiator.
            //set the status back to in progress
            getAwardBudget().setAwardBudgetStatusCode( Constants.BUDGET_STATUS_CODE_IN_PROGRESS);
            try {
                KraServiceLocator.getService(DocumentService.class).saveDocument(this);
            }
            catch (WorkflowException e) {
                throw new RuntimeException( "Could not save award document on action  taken.");
            }
        }
        
      
    }

    
    /**
     * @see org.kuali.kra.budget.document.BudgetDocument#documentHasBeenRejected(java.lang.String)
     */
    @Override
    public void documentHasBeenRejected( String reason ) {
        this.getAwardBudget().setAwardBudgetStatusCode(Constants.BUDGET_STATUS_CODE_REJECTED);
        try {
            KraServiceLocator.getService(DocumentService.class).saveDocument(this);
        }
        catch (WorkflowException e) {
            throw new RuntimeException( "Could not save award document on action  taken.");
        }
    } 
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void prepareForSave() {
        //force ojb to precache the budget as an AwardBudgetExt.
        //without this it caches the budget as a Budget which causes problems
        //when assuming it must be an awardbudgetext for an awardbudgetdocument
        if (this.getBudget() != null && this.getBudget().getBudgetId() != null) {
            AwardBudgetExt budget = KraServiceLocator.getService(BusinessObjectService.class).findBySinglePrimaryKey(AwardBudgetExt.class, this.getBudget().getBudgetId());
        }
        super.prepareForSave();
    }

    protected AwardBudgetService getAwardBudgetService() {
        if (awardBudgetService == null) {
            awardBudgetService = KraServiceLocator.getService(AwardBudgetService.class);
        }
        return awardBudgetService;
    }

    public void setAwardBudgetService(AwardBudgetService awardBudgetService) {
        this.awardBudgetService = awardBudgetService;
    }
 
}
