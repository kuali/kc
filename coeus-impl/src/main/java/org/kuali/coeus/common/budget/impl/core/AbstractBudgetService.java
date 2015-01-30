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
package org.kuali.coeus.common.budget.impl.core;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.core.*;
import org.kuali.coeus.common.budget.framework.version.BudgetVersionOverview;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.query.QueryList;
import org.kuali.coeus.common.budget.api.rate.RateClassType;
import org.kuali.coeus.common.budget.framework.query.operator.Equals;
import org.kuali.coeus.common.budget.framework.income.BudgetProjectIncome;
import org.kuali.coeus.common.budget.framework.core.BudgetAuditEvent;
import org.kuali.coeus.common.budget.framework.core.BudgetParent;
import org.kuali.coeus.common.budget.framework.core.BudgetParentDocument;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItemBase;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonSalaryDetails;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.coeus.common.budget.framework.rate.BudgetRate;
import org.kuali.coeus.common.budget.framework.rate.BudgetRatesService;
import org.kuali.coeus.common.budget.framework.rate.ValidCeRateType;
import org.kuali.coeus.common.budget.framework.summary.BudgetSummaryService;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRulesEngine;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.kra.award.budget.AwardBudgetLineItemExt;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.budget.subaward.BudgetSubAwardPeriodDetail;
import org.kuali.coeus.propdev.impl.budget.modular.BudgetModular;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.document.DocumentBase;
import org.kuali.rice.krad.service.*;
import org.kuali.rice.krad.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * This class implements methods specified by BudgetDocumentService interface
 */
public abstract class AbstractBudgetService<T extends BudgetParent> implements BudgetService<T> {
    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(AbstractBudgetService.class);
    
    @Autowired
    @Qualifier("documentService")
    private DocumentService documentService;
    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;
    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;
    @Autowired
    @Qualifier("budgetRatesService")
    private BudgetRatesService<T> budgetRatesService;
    @Autowired
    @Qualifier("pessimisticLockService")
    private PessimisticLockService pessimisticLockService;
    @Autowired
    @Qualifier("budgetSummaryService")
    private BudgetSummaryService budgetSummaryService;
    @Autowired
    @Qualifier("kualiRuleService")
    private KualiRuleService kualiRuleService;

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;
    
    @Autowired
    @Qualifier("kcBusinessRulesEngine")
    private KcBusinessRulesEngine kcBusinessRulesEngine;
    
    @Autowired
    @Qualifier("legacyDataAdapter")
    private LegacyDataAdapter legacyDataAdapter;

    /**
     * Service method for adding a {@link BudgetVersionOverview} to a {@link ProposalDevelopmentDocument}. If a 
     * {@link BudgetVersionOverview} instance with the  <code>versionName</code> already exists 
     * in the {@link ProposalDevelopmentDocument}, then a hard error will occur. Try it and you'll see what I mean.
     * 
     * @param budgetParentDocument instance to add {@link BudgetVersionOverview} to
     * @param versionName of the {@link BudgetVersionOverview}
     */
    @Override
    public Budget addBudgetVersion(BudgetParentDocument budgetParentDocument, String versionName, Map options) {
        if (!isBudgetVersionNameValid(budgetParentDocument.getBudgetParent(), versionName)) {
            LOG.debug("Buffered Version not Valid");
            return null;
        }

        Budget newBudgetDoc = getNewBudgetVersion(budgetParentDocument, versionName, options);
        
        return newBudgetDoc;
    }

    protected abstract Budget getNewBudgetVersion(BudgetParentDocument<T> parent, String versionName, Map<String, Object> options);

    /**
     * Runs business rules on the given name of a {@link BudgetVersionOverview} instance and {@link ProposalDevelopmentDocument} instance to 
     * determine if it is ok to add a {@link BudgetVersionOverview} instance to a {@link Budget} instance. If the business rules fail, 
     * this should be false and there will be errors in the error map.<br/>
     *
     * <p>Takes care of all the setup and calling of the {@link KualiRuleService}. Uses the {@link org.kuali.coeus.common.budget.framework.version.AddBudgetVersionEvent}.</p>
     *
     * @param document {@link ProposalDevelopmentDocument} to validate against
     * @param name of the pseudo-{@link BudgetVersionOverview} instance to validate
     * @returns true if the rules passed, false otherwise
     */
    public abstract boolean isBudgetVersionNameValid(BudgetParent parent, String name);
    
    /**
     * Retrieve injected <code>{@link PessimisticLockService}</code> singleton
     * 
     * @return PessimisticLockService
     */
    public PessimisticLockService getPessimisticLockService() {
        return pessimisticLockService;
    }

    /**
     * Inject <code>{@link PessimisticLockService}</code> singleton
     * 
     * @param pessimisticLockService to assign
     */
    public void setPessimisticLockService(PessimisticLockService pessimisticLockService) {
        this.pessimisticLockService = pessimisticLockService;
    }
    
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    
    /**
     * Sets the ParameterService.
     * @param parameterService the parameter service. 
     */
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    
    /**
     * Recurse through all of the BOs and if a BO has a ProposalNumber property,
     * set its value to the new proposal number.
     * @param object the object
     */
    @SuppressWarnings("unchecked")
    protected void fixProperty(Object object, String methodName, Class clazz, Object propertyValue, Map<String, Object> objectMap){
        if(ObjectUtils.isNotNull(object)) {
            if (object instanceof PersistableBusinessObject) {
                PersistableBusinessObject objectWId = (PersistableBusinessObject) object;
                if (objectMap.get(objectWId.getObjectId()) != null) return;
                objectMap.put(((PersistableBusinessObject) object).getObjectId(), object);
                
                Method[] methods = object.getClass().getMethods();
                for (Method method : methods) {
                    if (method.getName().equals(methodName)) {
                        if (!(object instanceof DocumentBase)) {
                              try {
                                if(clazz.equals(Long.class))
                                    method.invoke(object, (Long) propertyValue);  
                                else 
                                    method.invoke(object, (Integer) propertyValue);
                               } catch (Throwable e) { }  
                        }
                    } else if (isPropertyGetterMethod(method, methods)) {
                        Object value = null;
                        try {
                            value = method.invoke(object);
                        } catch (Throwable e) {
                            //We don't need to propagate this exception
                        }
                        
                        if(value != null) {
                            if (value instanceof Collection) {
                                Collection<Object> c = (Collection<Object>) value;
                                Iterator<Object> iter = c.iterator();
                                while (iter.hasNext()) {
                                    Object entry = iter.next();
                                    fixProperty(entry, methodName, clazz, propertyValue, objectMap);
                                }
                            } else {
                                fixProperty(value, methodName, clazz, propertyValue, objectMap);
                            }   
                        }
                    }
                }
            }
        }
    }
    
    
    /**
     * Is the given method a getter method for a property?  Must conform to
     * the following:
     * <ol>
     * <li>Must start with the <b>get</b></li>
     * <li>Must have a corresponding setter method</li>
     * <li>Must have zero arguments.</li>
     * </ol>
     * @param method the method to check
     * @param methods the other methods in the object
     * @return true if it is property getter method; otherwise false
     */
    protected boolean isPropertyGetterMethod(Method method, Method methods[]) {
        if (method.getName().startsWith("get") && method.getParameterTypes().length == 0) {
            String setterName = method.getName().replaceFirst("get", "set");
            for (Method m : methods) {
                if (m.getName().equals(setterName)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean ValidInflationCeRate(BudgetLineItemBase budgetLineItem) {
        //QueryEngine queryEngine = new QueryEngine();
        //BudgetLineItemCalculatedAmount budgetLineItemCalculatedAmt = null;
        Map<String, String> costElementQMap = new HashMap<String, String>();
        costElementQMap.put("costElement", budgetLineItem.getCostElement());
        CostElement costElementBO = (CostElement) businessObjectService.findByPrimaryKey(CostElement.class, costElementQMap);
        budgetLineItem.setCostElementBO(costElementBO);
        Map<String, String> validCeQMap = new HashMap<String, String>();
        validCeQMap.put("costElement", budgetLineItem.getCostElement());
        costElementBO.refreshReferenceObject("validCeRateTypes");
        List<ValidCeRateType> validCeRateTypes = costElementBO.getValidCeRateTypes();
        QueryList<ValidCeRateType> qValidCeRateTypes = validCeRateTypes == null ? new QueryList<ValidCeRateType>() : 
                        new QueryList<ValidCeRateType>(validCeRateTypes);
        // Check whether it contains Inflation Rate
        Equals eqInflation = new Equals("rateClassType", RateClassType.INFLATION.getRateClassType());
        QueryList<ValidCeRateType> inflationValidCeRates = qValidCeRateTypes.filter(eqInflation);
        if (!inflationValidCeRates.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public String getActivityTypeForBudget(Budget budget) {
        BudgetParent budgetParent = budget.getBudgetParent().getDocument().getBudgetParent();
        Map<String,Object> qMap = new HashMap<String,Object>();
        qMap.put("budgetId",budget.getBudgetId());
        List<BudgetRate> allPropRates = (List)businessObjectService.findMatching(
                BudgetRate.class, qMap);
        if (CollectionUtils.isNotEmpty(allPropRates)) {
            qMap.put("activityTypeCode",budgetParent.getActivityTypeCode());
            Collection<BudgetRate> matchActivityTypePropRates =businessObjectService.findMatching(
                BudgetRate.class, qMap);
            if (CollectionUtils.isNotEmpty(matchActivityTypePropRates)) {
                for (BudgetRate budgetRate : allPropRates) { 
                    if (!budgetRate.getActivityTypeCode().equals(budgetParent.getActivityTypeCode())) {
                        return budgetRate.getActivityTypeCode();
                    }
                }
                return budgetParent.getActivityTypeCode();                
            } else {
                return allPropRates.get(0).getActivityTypeCode();
            }
        }
                
        return "x";
        
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<BudgetRate> getSavedProposalRates(Budget budgetToOpen) {
        Map<String,Long> qMap = new HashMap<String,Long>();
        qMap.put("budgetId",budgetToOpen.getBudgetId());
        return businessObjectService.findMatching(BudgetRate.class, qMap);
    }

    @SuppressWarnings("unchecked")
    protected boolean applyAuditRuleForBudgetDocument(Budget budgetVersion) throws Exception {
        return getKcBusinessRulesEngine().applyRules(new BudgetAuditEvent(budgetVersion));
    }

    /**
     * @throws NoSuchMethodException 
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @throws FormatException
     */
    @SuppressWarnings("unchecked")
    @Override
    public Budget copyBudgetVersion(Budget budget, boolean onlyOnePeriod){
        if (onlyOnePeriod) {
            //Copy full first version, then include empty periods for remainder
            List<BudgetPeriod> oldBudgetPeriods = budget.getBudgetPeriods(); 
            for ( int i = 1 ; i < oldBudgetPeriods.size(); i++ ) {
                BudgetPeriod period = oldBudgetPeriods.get(i);
                period.getBudgetLineItems().clear();
                period.setCostSharingAmount(new ScaleTwoDecimal(0.0));
                period.setExpenseTotal(new ScaleTwoDecimal(0.0));
                period.setTotalCost(new ScaleTwoDecimal(0.0));
                period.setTotalCostLimit(new ScaleTwoDecimal(0.0));
                period.setTotalDirectCost(new ScaleTwoDecimal(0.0));
                period.setTotalIndirectCost(new ScaleTwoDecimal(0.0));
                period.setUnderrecoveryAmount(new ScaleTwoDecimal(0.0));
            }            
            
            /**
             * KRACOEUS-6312
             * Zero out any applicable BudgetSubAwardPeriodDetail lines.
             */
            if (budget.getBudgetSubAwards() != null && budget.getBudgetSubAwards().size() > 0) {
                List<BudgetSubAwardPeriodDetail> budetSubawardPeriodDetail = budget.getBudgetSubAwards().get(0).getBudgetSubAwardPeriodDetails();
                for ( int i = 1 ; i < budetSubawardPeriodDetail.size(); i++ ) {
                    BudgetSubAwardPeriodDetail period = budetSubawardPeriodDetail.get(i);
                    period.setAmountsModified(true);
                    period.setCostShare(new ScaleTwoDecimal(0.0));
                    period.setDirectCost(new ScaleTwoDecimal(0.0));
                    period.setIndirectCost(new ScaleTwoDecimal(0.0));
                    period.setTotalCost(new ScaleTwoDecimal(0.0));
                }
            }
        }
        
        budget.setBudgetVersionNumber(budget.getBudgetParent().getNextBudgetVersionNumber());
        try {
            Map<String, Object> objectMap = new HashMap<String, Object>();
            fixProperty(budget, "setBudgetId", Long.class, null, objectMap);
            objectMap.clear();
            fixProperty(budget, "setBudgetPeriodId", Long.class, null, objectMap);
            objectMap.clear();
            fixProperty(budget, "setBudgetLineItemId", Long.class, null, objectMap);
            objectMap.clear();
            fixProperty(budget, "setBudgetLineItemCalculatedAmountId", Long.class, null, objectMap);
            objectMap.clear();
            fixProperty(budget, "setBudgetPersonnelLineItemId", Long.class, null, objectMap);
            objectMap.clear();
            fixProperty(budget, "setBudgetPersonnelCalculatedAmountId", Long.class, null, objectMap);
            objectMap.clear();
            fixProperty(budget, "setBudgetPersonnelRateAndBaseId", Long.class, null, objectMap);
            objectMap.clear();
            fixProperty(budget, "setBudgetRateAndBaseId", Long.class, null, objectMap);
            objectMap.clear();
            fixProperty(budget, "setVersionNumber", Integer.class, null, objectMap);
            objectMap.clear();
            fixProperty(budget, "setAwardBudgetPeriodSummaryCalculatedAmountId", Long.class, null, objectMap);
            objectMap.clear();
            fixProperty(budget, "setFinalVersionFlag", Boolean.class, Boolean.FALSE, objectMap);
            objectMap.clear();
            

        }catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        
        //Work around for 1-to-1 Relationship between BudgetPeriod & BudgetModular
        Map<String, BudgetModular> tmpBudgetModulars = new HashMap<String, BudgetModular>(); 
        for(BudgetPeriod budgetPeriod: budget.getBudgetPeriods()) {
            BudgetModular tmpObject = null;
            if(budgetPeriod.getBudgetModular() != null) {
                tmpObject = (BudgetModular) ObjectUtils.deepCopy(budgetPeriod.getBudgetModular());
            }
            tmpBudgetModulars.put(""+budgetPeriod.getBudget().getVersionNumber() + budgetPeriod.getBudgetPeriod(), tmpObject);
            budgetPeriod.setBudgetModular(null);
        }

        copyLineItemToPersonnelDetails(budget);
        budget.setVersionNumber(null);
        // setting this to null so copied budget can be posted.
        budget.setBudgetAdjustmentDocumentNumber(null);
        List<BudgetProjectIncome> projectIncomes = budget.getBudgetProjectIncomes();
        budget.setBudgetProjectIncomes(new ArrayList<BudgetProjectIncome>());
        if (projectIncomes != null && !projectIncomes.isEmpty()) {
            updateProjectIncomes(budget, projectIncomes);
        }
        for(BudgetPeriod tmpBudgetPeriod: budget.getBudgetPeriods()) {
            BudgetModular tmpBudgetModular = tmpBudgetModulars.get(""+tmpBudgetPeriod.getBudget().getVersionNumber() + tmpBudgetPeriod.getBudgetPeriod());
            if(tmpBudgetModular != null) {
                tmpBudgetModular.setBudgetPeriodId(tmpBudgetPeriod.getBudgetPeriodId());
                tmpBudgetPeriod.setBudgetModular(tmpBudgetModular);
            }
        }

        return budget;
    }
    
    protected abstract void calculateBudgetOnSave(Budget budget);
   
    /**
     * 
     * This method is to handle budgetprojectincomes independently.
     * budgetprojectincomes is a collection of 'budget', but it also reference to budgetperiod.
     * During copy budgetperiodid is set to null.  If save with budgetdocument, then budgetperiodid will not be set.
     * So, has to use this to set manually.
     * @param projectIncomes
     */
    protected void updateProjectIncomes(Budget budget, List<BudgetProjectIncome> projectIncomes) {
        List<BudgetProjectIncome> budgetProjectIncomes = new ArrayList<BudgetProjectIncome>();
        for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
            for (BudgetProjectIncome projectIncome : projectIncomes) {
                if (budgetPeriod.getBudgetPeriod().equals(projectIncome.getBudgetPeriodNumber())) {
                    projectIncome.setBudgetId(budget.getBudgetId());
                    projectIncome.setBudgetPeriodId(budgetPeriod.getBudgetPeriodId());
                    budgetProjectIncomes.add(projectIncome);
                }
            }
        }
    }
    /**
     * 
     * Do this so that new personnel details(or copied ones) can be calculated
     */
    protected void copyLineItemToPersonnelDetails(Budget budget) {
        for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
            if (budgetPeriod.getBudgetLineItems() != null && !budgetPeriod.getBudgetLineItems().isEmpty()) {
                for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {        
                    if (budgetLineItem.getBudgetPersonnelDetailsList() != null && !budgetLineItem.getBudgetPersonnelDetailsList().isEmpty()) {
                        for (BudgetPersonnelDetails budgetPersonnelDetails : budgetLineItem.getBudgetPersonnelDetailsList()) {
                        	budgetPersonnelDetails.setBudget(budgetLineItem.getBudget());
                            budgetPersonnelDetails.setBudgetId(budgetLineItem.getBudgetId());
                            budgetPersonnelDetails.setBudgetPeriod(budgetLineItem.getBudgetPeriod());
                            budgetPersonnelDetails.setLineItemNumber(budgetLineItem.getLineItemNumber());
                            budgetPersonnelDetails.setCostElement(budgetLineItem.getCostElement());
                            budgetPersonnelDetails.setCostElementBO(budgetLineItem.getCostElementBO());
                       }
                    }
                }
            }
        }
    }    
    
    /**
     * Gets the budgetRatesService attribute. 
     * @return Returns the budgetRatesService.
     */
    public BudgetRatesService<T> getBudgetRatesService() {
        return budgetRatesService;
    }

    /**
     * Sets the budgetRatesService attribute value.
     * @param budgetRatesService The budgetRatesService to set.
     */
    public void setBudgetRatesService(BudgetRatesService<T> budgetRatesService) {
        this.budgetRatesService = budgetRatesService;
    }

    /**
     * Gets the budgetSummaryService attribute. 
     * @return Returns the budgetSummaryService.
     */
    public BudgetSummaryService getBudgetSummaryService() {
        return budgetSummaryService;
    }

    /**
     * Sets the budgetSummaryService attribute value.
     * @param budgetSummaryService The budgetSummaryService to set.
     */
    public void setBudgetSummaryService(BudgetSummaryService budgetSummaryService) {
        this.budgetSummaryService = budgetSummaryService;
    }

    @Override
    public String populateBudgetPersonSalaryDetailsInPeriods(String budgetId, String personSequenceNumber, String personId ){
        String baseSalary = "";
        BusinessObjectService boService = getBusinessObjectService();
        HashMap budgetPersonInPeriodsSalaryMap = new HashMap();
        budgetPersonInPeriodsSalaryMap.put("personSequenceNumber", personSequenceNumber);
        budgetPersonInPeriodsSalaryMap.put("budgetId", budgetId);
        budgetPersonInPeriodsSalaryMap.put("personId", personId);
        Collection<BudgetPersonSalaryDetails> budgetPersonSalaryDetails = boService.findMatching(BudgetPersonSalaryDetails.class, budgetPersonInPeriodsSalaryMap);
        if (budgetPersonSalaryDetails != null && !budgetPersonSalaryDetails.isEmpty()) {
            for (BudgetPersonSalaryDetails salaryDetails : budgetPersonSalaryDetails) {
                baseSalary = baseSalary + salaryDetails.getBaseSalary() + ","; 
            }
        
            baseSalary = baseSalary.substring(0, baseSalary.lastIndexOf(","));
        }
        return baseSalary;
    }
    @Override
    public void populateNewBudgetLineItem(BudgetLineItem newBudgetLineItem, BudgetPeriod budgetPeriod) {
        Budget budget = budgetPeriod.getBudget();
        newBudgetLineItem.setBudgetPeriod(budgetPeriod.getBudgetPeriod());
        newBudgetLineItem.setBudgetPeriodBO(budgetPeriod);
        newBudgetLineItem.setBudgetPeriodId(budgetPeriod.getBudgetPeriodId());
        newBudgetLineItem.setStartDate(budgetPeriod.getStartDate());
        newBudgetLineItem.setEndDate(budgetPeriod.getEndDate());
        newBudgetLineItem.setBudgetId(budget.getBudgetId());
        newBudgetLineItem.setLineItemNumber(budget.getNextValue(Constants.BUDGET_LINEITEM_NUMBER));
        newBudgetLineItem.setApplyInRateFlag(true);
        newBudgetLineItem.setSubmitCostSharingFlag(budget.getSubmitCostSharingFlag());
        refreshBudgetLineCostElement(newBudgetLineItem);
        // on/off campus flag enhancement
        String onOffCampusFlag = budget.getOnOffCampusFlag();
        if (onOffCampusFlag.equalsIgnoreCase(BudgetConstants.DEFAULT_CAMPUS_FLAG)) {
            newBudgetLineItem.setOnOffCampusFlag(newBudgetLineItem.getCostElementBO().getOnOffCampusFlag()); 
        } else {
            newBudgetLineItem.setOnOffCampusFlag(onOffCampusFlag.equalsIgnoreCase(Constants.ON_CAMUS_FLAG));                 
        }
        newBudgetLineItem.setBudgetCategoryCode(newBudgetLineItem.getCostElementBO().getBudgetCategoryCode());
        newBudgetLineItem.setLineItemSequence(newBudgetLineItem.getLineItemNumber());
        refreshBudgetLineBudgetCategory(newBudgetLineItem);
        
        if(isBudgetFormulatedCostEnabled()){
            List<String> formulatedCostElements = getFormulatedCostElements();
            if(formulatedCostElements.contains(newBudgetLineItem.getCostElement())){
                newBudgetLineItem.setFormulatedCostElementFlag(true);
            }
        }
    }

    private void refreshBudgetLineCostElement(BudgetLineItem newBudgetLineItem) {
		if(StringUtils.isNotEmpty(newBudgetLineItem.getCostElement())) {
			if (newBudgetLineItem instanceof AwardBudgetLineItemExt) {
				getLegacyDataAdapter().refreshReferenceObject(newBudgetLineItem, "costElementBO");
	    	} else {
	    		getDataObjectService().wrap(newBudgetLineItem).fetchRelationship("costElementBO");
	    	}
		}
    }

    private void refreshBudgetLineBudgetCategory(BudgetLineItem newBudgetLineItem) {
		if(StringUtils.isNotEmpty(newBudgetLineItem.getBudgetCategoryCode())) {
			if (newBudgetLineItem instanceof AwardBudgetLineItemExt) {
				getLegacyDataAdapter().refreshReferenceObject(newBudgetLineItem, "budgetCategory");
	    	} else {
	    		getDataObjectService().wrap(newBudgetLineItem).fetchRelationship("budgetCategory");
	    	}
		}
    }
    
    protected boolean isBudgetFormulatedCostEnabled() {
        String formulatedCostEnabled = getParameterService().getParameterValueAsString(Budget.class, Constants.FORMULATED_COST_ENABLED);
        return (formulatedCostEnabled!=null && formulatedCostEnabled.equalsIgnoreCase("Y"))?true:false;
    }
    protected List<String> getFormulatedCostElements() {
        String formulatedCEsValue = getParameterService().getParameterValueAsString(Budget.class, Constants.FORMULATED_COST_ELEMENTS);
        String[] formulatedCEs = formulatedCEsValue==null?new String[0]:formulatedCEsValue.split(",");
        return Arrays.asList(formulatedCEs);
    }

    protected DocumentService getDocumentService() {
        return documentService;
    }

    protected BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    protected ParameterService getParameterService() {
        return parameterService;
    }

    protected KualiRuleService getKualiRuleService() {
        return kualiRuleService;
    }

    public void setKualiRuleService(KualiRuleService kualiRuleService) {
        this.kualiRuleService = kualiRuleService;
    }

    protected DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }

    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }

	protected KcBusinessRulesEngine getKcBusinessRulesEngine() {
		return kcBusinessRulesEngine;
	}

	public void setKcBusinessRulesEngine(KcBusinessRulesEngine kcBusinessRulesEngine) {
		this.kcBusinessRulesEngine = kcBusinessRulesEngine;
	}

	public LegacyDataAdapter getLegacyDataAdapter() {
		return legacyDataAdapter;
	}

	public void setLegacyDataAdapter(LegacyDataAdapter legacyDataAdapter) {
		this.legacyDataAdapter = legacyDataAdapter;
	}
}
