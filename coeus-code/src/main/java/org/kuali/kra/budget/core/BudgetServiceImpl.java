/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.budget.core;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.budget.AwardBudgetExt;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.budget.calculator.QueryList;
import org.kuali.kra.budget.calculator.RateClassType;
import org.kuali.kra.budget.calculator.query.Equals;
import org.kuali.kra.budget.distributionincome.BudgetProjectIncome;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.BudgetParentDocument;
import org.kuali.kra.budget.lookup.keyvalue.CostElementValuesFinder;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItemBase;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.personnel.BudgetPerson;
import org.kuali.kra.budget.personnel.BudgetPersonSalaryDetails;
import org.kuali.kra.budget.personnel.BudgetPersonnelDetails;
import org.kuali.kra.budget.personnel.ValidCeJobCode;
import org.kuali.kra.budget.rates.BudgetRate;
import org.kuali.kra.budget.rates.BudgetRatesService;
import org.kuali.kra.budget.rates.ValidCeRateType;
import org.kuali.kra.budget.summary.BudgetSummaryService;
import org.kuali.kra.budget.versions.*;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.budget.bo.BudgetSubAwardPeriodDetail;
import org.kuali.kra.proposaldevelopment.budget.modular.BudgetModular;
import org.kuali.kra.service.FiscalYearMonthService;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.core.web.format.FormatException;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.rules.rule.event.DocumentAuditEvent;
import org.kuali.rice.krad.service.*;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * This class implements methods specified by BudgetDocumentService interface
 */
public class BudgetServiceImpl<T extends BudgetParent> implements BudgetService<T> {
    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(BudgetServiceImpl.class);
    
    private DocumentService documentService;
    private BusinessObjectService businessObjectService;
    private ParameterService parameterService;
    private BudgetRatesService<T> budgetRatesService;
    private PessimisticLockService pessimisticLockService;
    private BudgetVersionRule budgetVersionRule;
    private BudgetSummaryService budgetSummaryService;
    private FiscalYearMonthService fiscalYearMonthService;    

    
    /**
     * Service method for adding a {@link BudgetVersionOverview} to a {@link ProposalDevelopmentDocument}. If a 
     * {@link BudgetVersionOverview} instance with the  <code>versionName</code> already exists 
     * in the {@link ProposalDevelopmentDocument}, then a hard error will occur. Try it and you'll see what I mean.
     * 
     * @param document instance to add {@link BudgetVersionOverview} to
     * @param versionName of the {@link BudgetVersionOverview}
     */
    @Override
    public BudgetDocument<T> addBudgetVersion(BudgetParentDocument<T> document, String versionName) throws WorkflowException {
        if (!isBudgetVersionNameValid(document, versionName)) {
            LOG.debug("Buffered Version not Valid");
            return null;
        }

        BudgetDocument<T> newBudgetDoc = getNewBudgetVersion(document, versionName);
        if(newBudgetDoc==null) return null;
        
        return newBudgetDoc;
    }

    protected BudgetDocument<T> getNewBudgetVersion(BudgetParentDocument<T> parentDocument, String versionName) throws WorkflowException {
        BudgetCommonService<T> budgetCommonService = BudgetCommonServiceFactory.createInstance(parentDocument);
        return budgetCommonService.getNewBudgetVersion(parentDocument, versionName);
    }

    /**
     * Runs business rules on the given name of a {@link BudgetVersionOverview} instance and {@link ProposalDevelopmentDocument} instance to 
     * determine if it is ok to add a {@link BudgetVersionOverview} instance to a {@link BudgetDocument} instance. If the business rules fail, 
     * this should be false and there will be errors in the error map.<br/>
     *
     * <p>Takes care of all the setup and calling of the {@link KualiRuleService}. Uses the {@link AddBudgetVersionEvent}.</p>
     *
     * @param document {@link ProposalDevelopmentDocument} to validate against
     * @param name of the pseudo-{@link BudgetVersionOverview} instance to validate
     * @returns true if the rules passed, false otherwise
     */
    @Override
    public boolean isBudgetVersionNameValid(BudgetParentDocument<T> document,  String name) {
        LOG.debug("Invoking budgetrule getBudgetVersionRule()");
        return new AddBudgetVersionEvent(document, name).invokeRuleMethod(getBudgetVersionRule());
    }
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
    /**
     * Retrieve injected <code>{@link AddBudgetVersionRule}</code> singleton
     * 
     * @return AddBudgetVersionRule
     */
    public BudgetVersionRule getBudgetVersionRule() {
        return budgetVersionRule;
    }

    /**
     * Inject <code>{@link AddBudgetVersionRule}</code> singleton
     * 
     * @return AddBudgetVersionRule
     */
    public void setBudgetVersionRule(BudgetVersionRule budgetVersionRule) {
        this.budgetVersionRule = budgetVersionRule;
    }
    

    protected void saveBudgetDocument(BudgetDocument<T> budgetDocument) throws WorkflowException {
        Budget budget = budgetDocument.getBudget();
        BudgetParentDocument<T> parentDocument = budgetDocument.getParentDocument(); 
        boolean isProposalBudget = new Boolean(parentDocument.getProposalBudgetFlag()).booleanValue();

        if(!isProposalBudget){
            AwardBudgetExt budgetExt = (AwardBudgetExt)budget;
            budgetExt.setAwardBudgetStatusCode(this.parameterService.getParameterValueAsString(AwardBudgetDocument.class, KeyConstants.AWARD_BUDGET_STATUS_IN_PROGRESS));
            budgetExt.setAwardBudgetTypeCode(this.parameterService.getParameterValueAsString(AwardBudgetDocument.class, KeyConstants.AWARD_BUDGET_TYPE_NEW));
            documentService.saveDocument(budgetDocument);
        }else{
            documentService.saveDocument(budgetDocument);
            documentService.routeDocument(budgetDocument, "Route to Final", new ArrayList());
        }
    }
    
    @Override
    public void updateDocumentDescription(BudgetVersionOverview budgetVersion) {
        LegacyDataAdapter boService = KcServiceLocator.getService(LegacyDataAdapter.class);
        Map<String, Object> keyMap = new HashMap<String, Object>();
        keyMap.put("documentNumber", budgetVersion.getDocumentNumber());
        DocumentHeader docHeader = (DocumentHeader) boService.findByPrimaryKey(DocumentHeader.class, keyMap);
        if (!docHeader.getDocumentDescription().equals(budgetVersion.getDocumentDescription())) {
            docHeader.setDocumentDescription(budgetVersion.getDocumentDescription());
            boService.save(docHeader);
        }
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
                        if (!(object instanceof BudgetDocument)) {
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
    
    @SuppressWarnings("unchecked")
    @Override
    public Collection<BudgetRate> getSavedBudgetRates(Budget budget) {
        Map<String,Long> qMap = new HashMap<String, Long>();
        qMap.put("budgetId",budget.getBudgetId());
        Collection<BudgetRate> rates = businessObjectService.findMatching(BudgetRate.class, qMap);
        for (BudgetRate rate : rates) {
            java.util.Calendar startDate = new java.util.GregorianCalendar();
            startDate.setTime(rate.getStartDate());
            Integer newFY = this.getFiscalYearMonthService().getFiscalYearFromDate(startDate);
            rate.setFiscalYear(newFY.toString());
        }
        return rates;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean checkActivityTypeChange(Collection<BudgetRate> allPropRates, String activityTypeCode) {
        if (CollectionUtils.isNotEmpty(allPropRates)) {
            Equals equalsActivityType = new Equals("activityTypeCode", activityTypeCode);
            QueryList matchActivityTypePropRates = new QueryList(allPropRates).filter(equalsActivityType);
            if (CollectionUtils.isEmpty(matchActivityTypePropRates) || allPropRates.size() != matchActivityTypePropRates.size()) {
                return true;
            }
        }
                
        return false;
    }

    @Override
    public boolean checkActivityTypeChange(BudgetParentDocument<T> budgetParentDoc, Budget budget) {
        return checkActivityTypeChange(getSavedBudgetRates(budget), budgetParentDoc.getBudgetParent().getActivityTypeCode());
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
    public String getActivityTypeForBudget(BudgetDocument<T> budgetDocument) {
        Budget budget = budgetDocument.getBudget();
        BudgetParent budgetParent = budgetDocument.getParentDocument().getBudgetParent();
        if(budgetParent==null){
            budgetDocument.refreshReferenceObject("parentDocument");
        }
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
    public List<ValidCeJobCode> getApplicableCostElements(Long budgetId, String personSequenceNumber) {
        List<ValidCeJobCode> validCostElements = null;

        String jobCodeValidationEnabledInd = this.parameterService.getParameterValueAsString(
                BudgetDocument.class, Constants.BUDGET_JOBCODE_VALIDATION_ENABLED);
        
        if(StringUtils.isNotEmpty(jobCodeValidationEnabledInd) && jobCodeValidationEnabledInd.equals("Y")) { 
            Map fieldValues = new HashMap();
            fieldValues.put("budgetId", budgetId);
            fieldValues.put("personSequenceNumber", personSequenceNumber);
            BudgetPerson budgetPerson = (BudgetPerson) businessObjectService.findByPrimaryKey(BudgetPerson.class, fieldValues);
            
            fieldValues.clear();
            if(budgetPerson != null && StringUtils.isNotEmpty(budgetPerson.getJobCode())) {
                fieldValues.put("jobCode", budgetPerson.getJobCode().toUpperCase());
                validCostElements = (List<ValidCeJobCode>) businessObjectService.findMatching(ValidCeJobCode.class, fieldValues);
            }
        }
        
        return validCostElements;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public String getApplicableCostElementsForAjaxCall(Long budgetId,String personSequenceNumber, 
            String budgetCategoryTypeCode) {
        
        String resultStr = "";
        
        if(isAuthorizedToAccess(budgetCategoryTypeCode)){
            if (StringUtils.isNotBlank(budgetCategoryTypeCode) && budgetCategoryTypeCode.contains(Constants.COLON)) {
                budgetCategoryTypeCode = StringUtils.split(budgetCategoryTypeCode, Constants.COLON)[0];
            }

            List<ValidCeJobCode> validCostElements = getApplicableCostElements(budgetId, personSequenceNumber);

            if(CollectionUtils.isNotEmpty(validCostElements)) {
                for (ValidCeJobCode validCE : validCostElements) {
                    Map fieldValues = new HashMap();
                    fieldValues.put("costElement", validCE.getCostElement());
                    CostElement costElement = (CostElement) businessObjectService.findByPrimaryKey(CostElement.class, fieldValues);
                    resultStr += "," + validCE.getCostElement() + ";" + costElement.getDescription();
                }
                resultStr += ",ceLookup;false";
            } else {
                CostElementValuesFinder ceValuesFinder = new CostElementValuesFinder();
                ceValuesFinder.setBudgetCategoryTypeCode(budgetCategoryTypeCode);
                List<KeyValue> allPersonnelCostElements = ceValuesFinder.getKeyValues();
                for (KeyValue keyValue : allPersonnelCostElements) {
                    if(StringUtils.isNotEmpty(keyValue.getKey().toString())) {
                        resultStr += "," + keyValue.getKey() + ";" + keyValue.getValue();
                    }
                }
                resultStr += ",ceLookup;true";
            }
        }
        
        return resultStr;
    }

    @Override
    public String getBudgetExpensePanelName(BudgetPeriod budgetPeriod, BudgetLineItem budgetLineItem) {
        StringBuffer panelName = new StringBuffer();
        if(budgetLineItem.getBudgetCategory() == null) {
            budgetLineItem.refreshReferenceObject("budgetCategory");
        }
        
        if(budgetLineItem.getBudgetCategory() != null && budgetLineItem.getBudgetCategory().getBudgetCategoryType() == null) {
            budgetLineItem.getBudgetCategory().refreshReferenceObject("budgetCategoryType");
        }
        
        if(budgetLineItem.getBudgetCategory() != null && budgetLineItem.getBudgetCategory().getBudgetCategoryType() != null) {
            panelName.append(budgetLineItem.getBudgetCategory().getBudgetCategoryType().getDescription());
        }
        
        return panelName.toString();
    }

    @Override
    public String getParticipantSupportCategoryCode() {
        return parameterService.getParameterValueAsString(BudgetDocument.class, Constants.BUDGET_CATEGORY_TYPE_PARTICIPANT_SUPPORT);
    }

    @Override
    public List<BudgetLineItem> getMatchingLineItems(List<BudgetLineItem> lineItems, List<String> budgetCategoryType) {
        List<BudgetLineItem> result = new ArrayList<BudgetLineItem>();
        for (BudgetLineItem lineItem : lineItems) {
            if (budgetCategoryType.contains(lineItem.getBudgetCategory().getBudgetCategoryTypeCode())) {
                result.add(lineItem);
            }
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<BudgetRate> getSavedProposalRates(BudgetVersionOverview budgetToOpen) {
        Map<String,Long> qMap = new HashMap<String,Long>();
        qMap.put("budgetId",budgetToOpen.getBudgetId());
        return businessObjectService.findMatching(BudgetRate.class, qMap);
    }


    @SuppressWarnings("unchecked")
    @Override
    public boolean validateBudgetAuditRule(BudgetParentDocument<T> parentDocument) throws Exception {
        boolean valid = true;
        boolean finalAndCompleteBudgetVersionFound = false;
        boolean budgetVersionsExists = false;
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        String budgetStatusCompleteCode = this.parameterService.getParameterValueAsString(BudgetDocument.class, Constants.BUDGET_STATUS_COMPLETE_CODE);
        for (BudgetDocumentVersion budgetDocumentVersion : parentDocument.getBudgetDocumentVersions()) {
            BudgetVersionOverview budgetVersion = budgetDocumentVersion.getBudgetVersionOverview();
            budgetVersionsExists = true;
            if (budgetVersion.isFinalVersionFlag()) {
                valid &= applyAuditRuleForBudgetDocument(budgetVersion);
                if (parentDocument.getBudgetParent().getBudgetStatus() != null 
                        && parentDocument.getBudgetParent().getBudgetStatus().equals(budgetStatusCompleteCode)) {
                    finalAndCompleteBudgetVersionFound = true;
                } else {
                    finalAndCompleteBudgetVersionFound = false;
                }
            }
        }
        if (budgetVersionsExists && !finalAndCompleteBudgetVersionFound) {
            auditErrors.add(new AuditError("document.budgetDocumentVersion[0].budgetVersionOverview", KeyConstants.AUDIT_ERROR_NO_BUDGETVERSION_COMPLETE_AND_FINAL, Constants.PD_BUDGET_VERSIONS_PAGE + "." + Constants.BUDGET_VERSIONS_PANEL_ANCHOR));
            valid &= false;
        }
        if (auditErrors.size() > 0) {
            KNSGlobalVariables.getAuditErrorMap().put("budgetVersionErrors", new AuditCluster(Constants.BUDGET_VERSION_PANEL_NAME, auditErrors, Constants.AUDIT_ERRORS));
        }

        return valid;
    }

    @Override
    public boolean validateBudgetAuditRuleBeforeSaveBudgetVersion(BudgetParentDocument<T> proposalDevelopmentDocument)
            throws Exception {
        boolean valid = true;
        for (BudgetDocumentVersion budgetDocumentVersion : proposalDevelopmentDocument.getBudgetDocumentVersions()) {
            BudgetVersionOverview budgetVersion = budgetDocumentVersion.getBudgetVersionOverview();
            
            String budgetStatusCompleteCode = this.parameterService.getParameterValueAsString(BudgetDocument.class,
                    Constants.BUDGET_STATUS_COMPLETE_CODE);
            // if status is complete and version is not final, then business rule will take care of it
            if (budgetVersion.isFinalVersionFlag() && budgetVersion.getBudgetStatus() != null
                    && budgetVersion.getBudgetStatus().equals(budgetStatusCompleteCode)) {
                valid &= applyAuditRuleForBudgetDocument(budgetVersion);
            }
            if (!valid) {
                break;
            }
        }

        if (!valid) {
            // audit warnings are OK.  only audit errors prevent to change to complete status.
            valid = true;
            for (Object key : KNSGlobalVariables.getAuditErrorMap().keySet()) {
                AuditCluster auditCluster = (AuditCluster)KNSGlobalVariables.getAuditErrorMap().get(key);
                if (auditCluster.getCategory().equals(Constants.AUDIT_ERRORS)) {
                    valid = false;
                    break;
                }
            }
        }
        
        //if the budget is considered valid, then any resulting audit errors or warnings do not matter
        //for the purposes of checking if the budget is valid to save.
        if (valid) {
            KNSGlobalVariables.getAuditErrorMap().clear();
        }

        return valid;
    }

    @SuppressWarnings("unchecked")
    protected boolean applyAuditRuleForBudgetDocument(BudgetVersionOverview budgetVersion) throws Exception {
        DocumentService documentService = KcServiceLocator.getService(DocumentService.class);
        BudgetDocument<T> budgetDocument = (BudgetDocument<T>) documentService.getByDocumentHeaderId(budgetVersion.getDocumentNumber());
        return KcServiceLocator.getService(KualiRuleService.class).applyRules(new DocumentAuditEvent(budgetDocument));

    }
    
    /**
     * @throws NoSuchMethodException 
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @throws FormatException
     */
    @SuppressWarnings("unchecked")
    @Override
    public BudgetDocument copyBudgetVersion(BudgetDocument budgetDocument, boolean onlyOnePeriod) throws WorkflowException {
        String parentDocumentNumber = budgetDocument.getParentDocument().getDocumentNumber();
        budgetDocument.toCopy();
        budgetDocument.getParentDocument().getDocumentHeader().setDocumentNumber(parentDocumentNumber);
        budgetDocument.getParentDocument().setDocumentNumber(parentDocumentNumber);
        if(budgetDocument.getBudgets().isEmpty()) { 
            throw new RuntimeException("Not able to find any Budget Version associated with this document");
        }
        Budget budget = budgetDocument.getBudget();
        
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
        
        budget.setBudgetVersionNumber(budgetDocument.getParentDocument().getNextBudgetVersionNumber());
        try {
            Map<String, Object> objectMap = new HashMap<String, Object>();
            fixProperty(budgetDocument, "setBudgetId", Long.class, null, objectMap);
            objectMap.clear();
            fixProperty(budgetDocument, "setBudgetPeriodId", Long.class, null, objectMap);
            objectMap.clear();
            fixProperty(budgetDocument, "setBudgetLineItemId", Long.class, null, objectMap);
            objectMap.clear();
            fixProperty(budgetDocument, "setBudgetLineItemCalculatedAmountId", Long.class, null, objectMap);
            objectMap.clear();
            fixProperty(budgetDocument, "setBudgetPersonnelLineItemId", Long.class, null, objectMap);
            objectMap.clear();
            fixProperty(budgetDocument, "setBudgetPersonnelCalculatedAmountId", Long.class, null, objectMap);
            objectMap.clear();
            fixProperty(budgetDocument, "setBudgetPersonnelRateAndBaseId", Long.class, null, objectMap);
            objectMap.clear();
            fixProperty(budgetDocument, "setBudgetRateAndBaseId", Long.class, null, objectMap);
            objectMap.clear();
            fixProperty(budgetDocument, "setVersionNumber", Integer.class, null, objectMap);
            objectMap.clear();
            fixProperty(budgetDocument, "setAwardBudgetPeriodSummaryCalculatedAmountId", Long.class, null, objectMap);
            objectMap.clear();
            fixProperty(budgetDocument, "setFinalVersionFlag", Boolean.class, Boolean.FALSE, objectMap);
            objectMap.clear();
            
            ObjectUtils.materializeAllSubObjects(budgetDocument);
        }catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        
        //Work around for 1-to-1 Relationship between BudgetPeriod & BudgetModular
        Map<String, BudgetModular> tmpBudgetModulars = new HashMap<String, BudgetModular>(); 
        for(BudgetPeriod budgetPeriod: budgetDocument.getBudget().getBudgetPeriods()) {
            BudgetModular tmpObject = null;
            if(budgetPeriod.getBudgetModular() != null) {
                tmpObject = (BudgetModular) ObjectUtils.deepCopy(budgetPeriod.getBudgetModular());
            }
            tmpBudgetModulars.put(""+budgetPeriod.getBudget().getVersionNumber() + budgetPeriod.getBudgetPeriod(), tmpObject);
            budgetPeriod.setBudgetModular(null);
        }

        copyLineItemToPersonnelDetails(budgetDocument);
        budgetDocument.setVersionNumber(null);
        // setting this to null so copied budget can be posted.
        budgetDocument.getBudget().setBudgetAdjustmentDocumentNumber(null);
        List<BudgetProjectIncome> projectIncomes = budgetDocument.getBudget().getBudgetProjectIncomes();
        budgetDocument.getBudget().setBudgetProjectIncomes(new ArrayList<BudgetProjectIncome>());
        documentService.saveDocument(budgetDocument);
        if (projectIncomes != null && !projectIncomes.isEmpty()) {
            updateProjectIncomes(budgetDocument, projectIncomes);
        }
        getBudgetCommonService(budget.getBudgetDocument().getParentDocument()).calculateBudgetOnSave(budget);
        for(BudgetPeriod tmpBudgetPeriod: budgetDocument.getBudget().getBudgetPeriods()) {
            BudgetModular tmpBudgetModular = tmpBudgetModulars.get(""+tmpBudgetPeriod.getBudget().getVersionNumber() + tmpBudgetPeriod.getBudgetPeriod());
            if(tmpBudgetModular != null) {
                tmpBudgetModular.setBudgetPeriodId(tmpBudgetPeriod.getBudgetPeriodId());
                tmpBudgetPeriod.setBudgetModular(tmpBudgetModular);
            }
        }
        
        saveBudgetDocument(budgetDocument);
        budgetDocument.getParentDocument().refreshBudgetDocumentVersions();
        return budgetDocument;
    }
    
    protected BudgetCommonService<BudgetParent> getBudgetCommonService(BudgetParentDocument parentBudgetDocument) {
        return BudgetCommonServiceFactory.createInstance(parentBudgetDocument);
    }    
    /**
     * 
     * This method is to handle budgetprojectincomes independently.
     * budgetprojectincomes is a collection of 'budget', but it also reference to budgetperiod.
     * During copy budgetperiodid is set to null.  If save with budgetdocument, then budgetperiodid will not be set.
     * So, has to use this to set manually.
     * @param budgetDocument
     * @param projectIncomes
     */
    protected void updateProjectIncomes(BudgetDocument budgetDocument, List<BudgetProjectIncome> projectIncomes) {
        List<BudgetProjectIncome> budgetProjectIncomes = new ArrayList<BudgetProjectIncome>();
        for (BudgetPeriod budgetPeriod : budgetDocument.getBudget().getBudgetPeriods()) {
            for (BudgetProjectIncome projectIncome : projectIncomes) {
                if (budgetPeriod.getBudgetPeriod().equals(projectIncome.getBudgetPeriodNumber())) {
                    projectIncome.setBudgetId(budgetDocument.getBudget().getBudgetId());
                    projectIncome.setBudgetPeriodId(budgetPeriod.getBudgetPeriodId());
                    budgetProjectIncomes.add(projectIncome);
                }
            }
        }
        businessObjectService.save(budgetProjectIncomes);
        budgetDocument.getBudget().refreshReferenceObject("budgetProjectIncomes");

    }
    /**
     * 
     * Do this so that new personnel details(or copied ones) can be calculated
     * @param budgetDocument
     */
    protected void copyLineItemToPersonnelDetails(BudgetDocument budgetDocument) {
        for (BudgetPeriod budgetPeriod : budgetDocument.getBudget().getBudgetPeriods()) {
            if (budgetPeriod.getBudgetLineItems() != null && !budgetPeriod.getBudgetLineItems().isEmpty()) {
                for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {        
                    if (budgetLineItem.getBudgetPersonnelDetailsList() != null && !budgetLineItem.getBudgetPersonnelDetailsList().isEmpty()) {
                        for (BudgetPersonnelDetails budgetPersonnelDetails : budgetLineItem.getBudgetPersonnelDetailsList()) {
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
    
    /*
     * a utility method to check if dwr/ajax call really has authorization
     * 'updateProtocolFundingSource' also accessed by non ajax call
     */
    
    private boolean isAuthorizedToAccess(String budgetCategoryTypeCode) {
        boolean isAuthorized = true;
        if(budgetCategoryTypeCode.contains(Constants.COLON)){
            if (GlobalVariables.getUserSession() != null) {
                // TODO : this is a quick hack for KC 3.1.1 to provide authorization check for dwr/ajax call. dwr/ajax will be replaced by
                // jquery/ajax in rice 2.0
                String[] invalues = StringUtils.split(budgetCategoryTypeCode, Constants.COLON);
                String docFormKey = invalues[1];
                if (StringUtils.isBlank(docFormKey)) {
                    isAuthorized = false;
                } else {
                    Object formObj = GlobalVariables.getUserSession().retrieveObject(docFormKey);
                    if (formObj == null || !(formObj instanceof BudgetForm)) {
                        isAuthorized = false;
                    } else {
                        Map<String, String> editModes = ((BudgetForm)formObj).getEditingMode();
                        isAuthorized = BooleanUtils.toBoolean(editModes.get(AuthorizationConstants.EditMode.FULL_ENTRY))
                        || BooleanUtils.toBoolean(editModes.get(AuthorizationConstants.EditMode.VIEW_ONLY))
                        || BooleanUtils.toBoolean(editModes.get("modifyBudgtes"))
                        || BooleanUtils.toBoolean(editModes.get("viewBudgets"))
                        || BooleanUtils.toBoolean(editModes.get("addBudget"));
                    }
                }

            } else {
                // TODO : it seemed that tomcat has this issue intermittently ?
                LOG.info("dwr/ajax does not have session ");
            }
        }
        return isAuthorized;
    }

    public FiscalYearMonthService getFiscalYearMonthService() {
        return fiscalYearMonthService;
    }

    public void setFiscalYearMonthService(FiscalYearMonthService fiscalYearMonthService) {
        this.fiscalYearMonthService = fiscalYearMonthService;
    }

    @Override
    public String populateBudgetPersonSalaryDetailsInPeriods(String budgetId, String personSequenceNumber, String personId ){
        String baseSalary = "";
        BusinessObjectService boService = KcServiceLocator.getService(BusinessObjectService.class);
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
        newBudgetLineItem.setBudgetPeriodId(budgetPeriod.getBudgetPeriodId());
        newBudgetLineItem.setStartDate(budgetPeriod.getStartDate());
        newBudgetLineItem.setEndDate(budgetPeriod.getEndDate());
        newBudgetLineItem.setBudgetId(budget.getBudgetId());
        newBudgetLineItem.setLineItemNumber(budget.getBudgetDocument().getHackedDocumentNextValue(Constants.BUDGET_LINEITEM_NUMBER));
        newBudgetLineItem.setApplyInRateFlag(true);
        newBudgetLineItem.setSubmitCostSharingFlag(budget.getSubmitCostSharingFlag());
        newBudgetLineItem.refreshReferenceObject("costElementBO");
        
        // on/off campus flag enhancement
        String onOffCampusFlag = budget.getOnOffCampusFlag();
        if (onOffCampusFlag.equalsIgnoreCase(Constants.DEFALUT_CAMUS_FLAG)) {
            newBudgetLineItem.setOnOffCampusFlag(newBudgetLineItem.getCostElementBO().getOnOffCampusFlag()); 
        } else {
            newBudgetLineItem.setOnOffCampusFlag(onOffCampusFlag.equalsIgnoreCase(Constants.ON_CAMUS_FLAG));                 
        }
        newBudgetLineItem.setBudgetCategoryCode(newBudgetLineItem.getCostElementBO().getBudgetCategoryCode());
        newBudgetLineItem.setLineItemSequence(newBudgetLineItem.getLineItemNumber());
        
        if(isBudgetFormulatedCostEnabled()){
            List<String> formulatedCostElements = getFormulatedCostElements();
            if(formulatedCostElements.contains(newBudgetLineItem.getCostElement())){
                newBudgetLineItem.setFormulatedCostElementFlag(true);
            }
        }
    }
    
    protected boolean isBudgetFormulatedCostEnabled() {
        String formulatedCostEnabled = getParameterService().getParameterValueAsString(BudgetDocument.class, Constants.FORMULATED_COST_ENABLED);
        return (formulatedCostEnabled!=null && formulatedCostEnabled.equalsIgnoreCase("Y"))?true:false;
    }
    protected List<String> getFormulatedCostElements() {
        String formulatedCEsValue = getParameterService().getParameterValueAsString(BudgetDocument.class, Constants.FORMULATED_COST_ELEMENTS);
        String[] formulatedCEs = formulatedCEsValue==null?new String[0]:formulatedCEsValue.split(",");
        return Arrays.asList(formulatedCEs);
    }
    
    @Override
    public void setBudgetStatuses(BudgetParentDocument<T> parentDocument) {
        
        String budgetStatusIncompleteCode = getParameterService().getParameterValueAsString(
                BudgetDocument.class, Constants.BUDGET_STATUS_INCOMPLETE_CODE);
        
        for (BudgetDocumentVersion budgetDocumentVersion: parentDocument.getBudgetDocumentVersions()) {
            BudgetVersionOverview budgetVersion =  budgetDocumentVersion.getBudgetVersionOverview();
            if (budgetVersion.isFinalVersionFlag()) {
                budgetVersion.setBudgetStatus(parentDocument.getBudgetParent().getBudgetStatus());
            }
            else {
                budgetVersion.setBudgetStatus(budgetStatusIncompleteCode);
            }
        }
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

}
