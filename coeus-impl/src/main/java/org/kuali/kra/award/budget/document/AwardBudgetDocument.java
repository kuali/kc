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
package org.kuali.kra.award.budget.document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.api.rate.RateClassType;
import org.kuali.coeus.common.budget.framework.rate.BudgetRate;
import org.kuali.coeus.common.budget.framework.rate.RateType;
import org.kuali.coeus.common.framework.custom.DocumentCustomData;
import org.kuali.coeus.common.framework.auth.perm.Permissionable;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.workflow.KcDocumentRejectionService;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.award.budget.AwardBudgetExt;
import org.kuali.kra.award.budget.AwardBudgetLineItemExt;
import org.kuali.kra.award.budget.AwardBudgetService;
import org.kuali.kra.award.commitments.FandaRateType;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.DocumentNextvalue;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetParentDocument;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.coeus.common.framework.krms.KrmsRulesContext;
import org.kuali.coeus.common.impl.krms.KcKrmsFactBuilderServiceHelper;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.COMPONENT;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.NAMESPACE;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.action.ActionTaken;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kew.framework.postprocessor.ActionTakenEvent;
import org.kuali.rice.kew.framework.postprocessor.DocumentRouteStatusChange;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.document.Copyable;
import org.kuali.rice.krad.document.SessionDocument;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;
import org.kuali.rice.krms.api.engine.Facts.Builder;

@NAMESPACE(namespace=Constants.MODULE_NAMESPACE_AWARD_BUDGET)
@COMPONENT(component=ParameterConstants.DOCUMENT_COMPONENT)
public class AwardBudgetDocument extends KcTransactionalDocumentBase implements Copyable, SessionDocument, Permissionable, KrmsRulesContext {
	
    private static final String AWARD_BUDGET_DOCUMENT_TYPE_CODE = "ABGT";
    private static org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(AwardBudgetDocument.class);
    
    private ScaleTwoDecimal obligatedTotal;

    private transient AwardBudgetService awardBudgetService;
    private Award  currentAward;
    private List<AwardBudgetExt> budgets;

    private static final long serialVersionUID = 3564659576355229703L;
    
    private transient BusinessObjectService businessObjectService;

    @Override
    public void populateAgendaQualifiers(Map<String, String> qualifiers) {
        qualifiers.put(KcKrmsConstants.UNIT_NUMBER, getLeadUnitNumber());
    }

    @Override
    public void initialize() {
        Award award = (Award) getBudget().getBudgetParent();
        this.setCurrentAward(award);
        AwardBudgetExt awardBudget = getAwardBudget();
        awardBudget.setObligatedTotal(new ScaleTwoDecimal(award.getBudgetTotalCostLimit().bigDecimalValue()));
        List<BudgetRate> budgetRates = awardBudget.getBudgetRates();
        populateBudgetRateTypes(awardBudget,budgetRates);
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

    @Override
    public String getDocumentTypeCode() {
        return AWARD_BUDGET_DOCUMENT_TYPE_CODE;
    }
    
    /**
     *
     * This method returns Budget object. Creates new budget instance if the budgets list is empty
     * @return Budget
     */
    public AwardBudgetExt getBudget(){
    	if (budgets == null) {
    		budgets = new ArrayList<>();
    	}
    	if (budgets.isEmpty()) {
    		budgets.add(new AwardBudgetExt());
    	}
    	return budgets.get(0);
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
        FandaRateType awardRateType = getBusinessObjectService().findBySinglePrimaryKey(FandaRateType.class, rate.getRateTypeCode());
        RateType newRateType = new RateType();
        newRateType.setRateClassCode(rate.getRateClassCode());
        newRateType.setRateTypeCode(awardRateType.getFandaRateTypeCode().toString());
        newRateType.setDescription(awardRateType.getDescription());
        return newRateType;
    }

    public AwardBudgetExt getAwardBudget(){
        return (AwardBudgetExt)getBudget();
    }

    /**
     * Gets the obligatedAmount attribute. 
     * @return Returns the obligatedAmount.
     */
    public ScaleTwoDecimal getObligatedTotal() {
        return obligatedTotal;
    }

    /**
     * Sets the obligatedTotal attribute value.
     * @param obligatedTotal The obligatedTotal to set.
     */
    public void setObligatedTotal(ScaleTwoDecimal obligatedTotal) {
        this.obligatedTotal = obligatedTotal;
    }
    
    /**
     * Added mthod to enable change of status when the document changes KEW status.
     * @see org.kuali.rice.krad.document.DocumentBase#doRouteStatusChange(org.kuali.rice.kew.framework.postprocessor.DocumentRouteStatusChange)
     */
    public void doRouteStatusChange(DocumentRouteStatusChange dto) {
        executeAsLastActionUser(() -> {

            super.doRouteStatusChange(dto);
            String newStatus = dto.getNewRouteStatus();
            String oldStatus = dto.getOldRouteStatus();

            if (LOG.isDebugEnabled()) {
                LOG.debug(String.format("Route status change on AwardBudgetDocument #%s from %s to %s.", getDocumentNumber(), oldStatus, newStatus));
            }

            this.setCurrentAward(getBudget().getBudgetParent());

            if (StringUtils.equals(newStatus, KewApiConstants.ROUTE_HEADER_DISAPPROVED_CD)) {
                getAwardBudget().setAwardBudgetStatusCode(Constants.BUDGET_STATUS_CODE_DISAPPROVED);
                getBusinessObjectService().save(getAwardBudget());
            } else if (StringUtils.equals(newStatus, KewApiConstants.ROUTE_HEADER_CANCEL_CD)) {
                getAwardBudget().setAwardBudgetStatusCode(Constants.BUDGET_STATUS_CODE_CANCELLED);
                getBusinessObjectService().save(getAwardBudget());
            } else if (StringUtils.equals(newStatus, KewApiConstants.ROUTE_HEADER_FINAL_CD)) {
                getAwardBudget().setAwardBudgetStatusCode(Constants.BUDGET_STATUS_CODE_TO_BE_POSTED);
                getBusinessObjectService().save(getAwardBudget());
            }

            return null;
        });
    }
    
    
    /**
     * Added method to detect when the document is being approved from the initial node.  In this case
     * the document is actually being re-submitted after a rejection.  The award budget status then 
     * changes back to In Progress.
     */
    public void doActionTaken(ActionTakenEvent event) {
        executeAsLastActionUser( () -> {
            super.doActionTaken(event);
            ActionTaken actionTaken = event.getActionTaken();
            KcDocumentRejectionService documentRejectionService = KcServiceLocator.getService(KcDocumentRejectionService.class);
            if (LOG.isDebugEnabled()) {
                LOG.debug(String.format("Action taken on document %s: event code %s, action taken is %s", getDocumentNumber(), event.getDocumentEventCode(), actionTaken.getActionTaken().getCode()));
            }
            if (StringUtils.equals(KewApiConstants.ACTION_TAKEN_APPROVED_CD, actionTaken.getActionTaken().getCode()) && documentRejectionService.isDocumentOnInitialNode(this.getDocumentHeader().getWorkflowDocument())) {
                //the document is being approved from the initial node.
                //this means it was rejected and is now being approved by the initiator.
                //set the status back to in progress
                getAwardBudget().setAwardBudgetStatusCode(Constants.BUDGET_STATUS_CODE_IN_PROGRESS);
                getBusinessObjectService().save(getAwardBudget());
            }
            return null;
        });
    }

    public void documentHasBeenRejected( String reason ) {
        this.getAwardBudget().setAwardBudgetStatusCode(Constants.BUDGET_STATUS_CODE_RETURNED);
        try {
            KcServiceLocator.getService(DocumentService.class).saveDocument(this);
        }
        catch (WorkflowException e) {
            throw new RuntimeException( "Could not save award document on action  taken.");
        }
    }
    
    @Override
    public void prepareForSave() {
        //force ojb to precache the budget as an AwardBudgetExt.
        //without this it caches the budget as a Budget which causes problems
        //when assuming it must be an awardbudgetext for an awardbudgetdocument
        if (this.getBudget() != null && this.getBudget().getBudgetId() != null) {
            AwardBudgetExt budget = getBusinessObjectService().findBySinglePrimaryKey(AwardBudgetExt.class, this.getBudget().getBudgetId());
            Map<String, Long> params = new HashMap<String, Long>();
            params.put("budgetId", budget.getBudgetId());
            Collection<AwardBudgetLineItemExt> items = getBusinessObjectService().findMatching(AwardBudgetLineItemExt.class, params);
        }
        if (ObjectUtils.isNull(this.getVersionNumber())) {
            this.setVersionNumber(new Long(0));
        }
        this.getBudget().getRateClassTypes();
        this.getBudget().handlePeriodToProjectIncomeRelationship();
    }
    
    public BusinessObjectService getBusinessObjectService(){
    	if (businessObjectService == null ) {
    		businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
    	}
    	return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
		this.businessObjectService = businessObjectService;
	}

	protected AwardBudgetService getAwardBudgetService() {
        if (awardBudgetService == null) {
            awardBudgetService = KcServiceLocator.getService(AwardBudgetService.class);
        }
        return awardBudgetService;
    }

    public void setAwardBudgetService(AwardBudgetService awardBudgetService) {
        this.awardBudgetService = awardBudgetService;
    }

    @Override
    public void populateContextQualifiers(Map<String, String> qualifiers) {
        qualifiers.put("namespaceCode", Constants.MODULE_NAMESPACE_AWARD_BUDGET);
        qualifiers.put("name", KcKrmsConstants.AwardBudget.BUDGET_CONTEXT);
    }
    @Override
    public void addFacts(Builder factsBuilder) {
        KcKrmsFactBuilderServiceHelper fbService = KcServiceLocator.getService("awardBudgetFactBuilderService");
        fbService.addFacts(factsBuilder, this);
    }

	public List<AwardBudgetExt> getBudgets() {
		return budgets;
	}

	public void setBudgets(List<AwardBudgetExt> budgets) {
		this.budgets = budgets;
	}
	
    public Integer getHackedDocumentNextValue(String propertyName) {
        Integer propNextValue = 1;
        // search for property and get the latest number - increment for next call 
        for (Object element : getDocumentNextvalues()) {
            DocumentNextvalue documentNextvalue = (DocumentNextvalue) element;
            if (documentNextvalue.getPropertyName().equalsIgnoreCase(propertyName)) {
                propNextValue = documentNextvalue.getNextValue();
                Map<String, Object> budgetIdMap = new HashMap<String, Object>();
                budgetIdMap.put("budgetId", getBudget().getBudgetId());
                if (budgetIdMap != null) {
                    List<BudgetLineItem> lineItemNumber = (List<BudgetLineItem>) getBusinessObjectService().findMatchingOrderBy(BudgetLineItem.class, budgetIdMap, "lineItemNumber", true);
                    if (lineItemNumber != null) {
                        for (BudgetLineItem budgetLineItem : lineItemNumber) {
                            if (propNextValue.intValue() == budgetLineItem.getLineItemNumber().intValue()) {
                                propNextValue++;
                            }
                        }
                    }
                }
                documentNextvalue.setNextValue(propNextValue + 1);
            }
        }

        /*****BEGIN BLOCK *****/
        if (propNextValue == 1) {
            Map<String, Object> pkMap = new HashMap<String, Object>();
            pkMap.put("documentKey", getBudget().getBudgetId());
            pkMap.put("propertyName", propertyName);
            DocumentNextvalue documentNextvalue = (DocumentNextvalue) getBusinessObjectService().findByPrimaryKey(DocumentNextvalue.class, pkMap);
            if (documentNextvalue != null) {
                propNextValue = documentNextvalue.getNextValue();
                documentNextvalue.setNextValue(propNextValue + 1);
                getDocumentNextvalues().add(documentNextvalue);
            }
        }
        /*****END BLOCK********/
        // property does not exist - set initial value and increment for next call 
        if (propNextValue == 1) {
            DocumentNextvalue documentNextvalue = new DocumentNextvalue();
            documentNextvalue.setNextValue(propNextValue + 1);
            documentNextvalue.setPropertyName(propertyName);
            documentNextvalue.setDocumentKey(getDocumentNumber());
            getDocumentNextvalues().add(documentNextvalue);
        }
        setDocumentNextvalues(getDocumentNextvalues());
        return propNextValue;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        managedLists.addAll(getBudget().buildListOfDeletionAwareLists());
        return managedLists;
    }

    @Override
    public String getCustomLockDescriptor(Person user) {
        String activeLockRegion = (String) GlobalVariables.getUserSession().retrieveObject(KraAuthorizationConstants.ACTIVE_LOCK_REGION);
        if (StringUtils.isNotEmpty(activeLockRegion)) {
                return getDocumentBoNumber() + "-" + activeLockRegion + "-" + activeLockRegion + "-" + GlobalVariables.getUserSession().getPrincipalName();
        }
        return null;
    }

    public String getDocumentBoNumber() {
        return getBudget().getBudgetId().toString();
    }

    public String getDocumentKey() {
        return getBudget().getBudgetParent().getDocument().getBudgetPermissionable().getDocumentKey();
    }

    public String getDocumentNumberForPermission() {
        return getDocumentNumber();
    }

    public List<String> getRoleNames() {
        return getBudget().getBudgetParent().getDocument().getBudgetPermissionable().getRoleNames();
    }

    public String getNamespace() {
        return Constants.MODULE_NAMESPACE_BUDGET;
    }

    public String getLeadUnitNumber() {
        return getBudget().getBudgetParent().getDocument().getBudgetPermissionable().getLeadUnitNumber();
    }

    public String getDocumentRoleTypeCode() {
        return RoleConstants.PROPOSAL_ROLE_TYPE;
    }

    @Override
    public List<String> getLockClearningMethodNames() {
        List<String> methodToCalls = super.getLockClearningMethodNames();
        methodToCalls.add("returnToProposal");
        methodToCalls.add("returnToAward");
        return methodToCalls;
    }

    public boolean isProcessComplete() {
        return true;
    }

    @Override
    public List<? extends DocumentCustomData> getDocumentCustomData() {
        return new ArrayList();
    }	
    
    public BudgetParentDocument getParentDocument() {
    	return this.getBudget().getBudgetParent().getDocument();
    }
}
