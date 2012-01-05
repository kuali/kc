/*
 * Copyright 2005-2010 The Kuali Foundation
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

import static org.kuali.kra.logging.BufferedLogger.debug;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.budget.AwardBudgetExt;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.commitments.AwardFandaRate;
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
import org.kuali.kra.budget.personnel.BudgetPersonService;
import org.kuali.kra.budget.personnel.BudgetPersonnelDetails;
import org.kuali.kra.budget.personnel.ValidCeJobCode;
import org.kuali.kra.budget.rates.BudgetRate;
import org.kuali.kra.budget.rates.BudgetRatesService;
import org.kuali.kra.budget.rates.ValidCeRateType;
import org.kuali.kra.budget.summary.BudgetSummaryService;
import org.kuali.kra.budget.versions.AddBudgetVersionEvent;
import org.kuali.kra.budget.versions.AddBudgetVersionRule;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.budget.versions.BudgetVersionOverview;
import org.kuali.kra.budget.versions.BudgetVersionRule;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.budget.modular.BudgetModular;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.service.DeepCopyPostProcessor;
import org.kuali.rice.core.util.KeyLabelPair;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.kns.bo.DocumentHeader;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.rule.event.DocumentAuditEvent;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KualiRuleService;
import org.kuali.rice.kns.service.ParameterService;
import org.kuali.rice.kns.service.PessimisticLockService;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KualiDecimal;
import org.kuali.rice.kns.util.ObjectUtils;
import org.kuali.rice.kns.web.format.FormatException;

/**
 * This class implements methods specified by BudgetDocumentService interface
 */
public class BudgetServiceImpl<T extends BudgetParent> implements BudgetService<T> {
    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(BudgetServiceImpl.class);
    
    private DocumentService documentService;
    private BusinessObjectService businessObjectService;
    private ParameterService parameterService;
    private BudgetPersonService budgetPersonService;
    private BudgetRatesService<T> budgetRatesService;
    private PessimisticLockService pessimisticLockService;
    private BudgetVersionRule budgetVersionRule;
    private DeepCopyPostProcessor deepCopyPostProcessor;
    private BudgetSummaryService budgetSummaryService;
    

    
    /**
     * Service method for adding a {@link BudgetVersionOverview} to a {@link ProposalDevelopmentDocument}. If a 
     * {@link BudgetVersionOverview} instance with the  <code>versionName</code> already exists 
     * in the {@link ProposalDevelopmentDocument}, then a hard error will occur. Try it and you'll see what I mean.
     * 
     * @param document instance to add {@link BudgetVersionOverview} to
     * @param versionName of the {@link BudgetVersionOverview}
     */
    public BudgetDocument<T> addBudgetVersion(BudgetParentDocument<T> document, String versionName) throws WorkflowException {
        if (!isBudgetVersionNameValid(document, versionName)) {
            debug("Buffered Version not Valid");
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
    public boolean isBudgetVersionNameValid(BudgetParentDocument<T> document,  String name) {
        debug("Invoking budgetrule getBudgetVersionRule()");
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
    

    /**
     * This method...
     * @param budgetDocument
     * @param isProposalBudget
     * @param budget
     * @param budgetParent
     * @throws WorkflowException
     */
    protected void saveBudgetDocument(BudgetDocument<T> budgetDocument) throws WorkflowException {
        Budget budget = budgetDocument.getBudget();
        BudgetParentDocument<T> parentDocument = budgetDocument.getParentDocument(); 
        boolean isProposalBudget = new Boolean(parentDocument.getProposalBudgetFlag()).booleanValue();

        if(!isProposalBudget){
            AwardBudgetExt budgetExt = (AwardBudgetExt)budget;
            budgetExt.setAwardBudgetStatusCode(this.parameterService.getParameterValue(AwardBudgetDocument.class, KeyConstants.AWARD_BUDGET_STATUS_IN_PROGRESS));
            budgetExt.setAwardBudgetTypeCode(this.parameterService.getParameterValue(AwardBudgetDocument.class, KeyConstants.AWARD_BUDGET_TYPE_NEW));
            documentService.saveDocument(budgetDocument);
        }else{
            documentService.saveDocument(budgetDocument);
            documentService.routeDocument(budgetDocument, "Route to Final", new ArrayList());
        }
    }
    
    
    public void updateDocumentDescription(BudgetVersionOverview budgetVersion) {
        BusinessObjectService boService = KraServiceLocator.getService(BusinessObjectService.class);
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

    public void setBudgetPersonService(BudgetPersonService budgetPersonService) {
        this.budgetPersonService = budgetPersonService;
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    
    /**
     * Recurse through all of the BOs and if a BO has a ProposalNumber property,
     * set its value to the new proposal number.
     * @param object the object
     * @param proposalNumber the proposal number
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
    public Collection<BudgetRate> getSavedProposalRates(Budget budget) {
        Map<String,Long> qMap = new HashMap<String, Long>();
        qMap.put("budgetId",budget.getBudgetId());
        return businessObjectService.findMatching(BudgetRate.class, qMap);
    }
    
    @SuppressWarnings("unchecked")
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
    
    public boolean checkActivityTypeChange(BudgetParentDocument<T> budgetParentDoc, Budget budget) {
        return checkActivityTypeChange(getSavedProposalRates(budget), budgetParentDoc.getBudgetParent().getActivityTypeCode());
    }
    
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
    public String getActivityTypeForBudget(BudgetDocument<T> budgetDocument) {
        Budget budget = budgetDocument.getBudget();
        BudgetParent budgetParent = budgetDocument.getParentDocument().getBudgetParent();
        if(budgetParent==null){
            budgetDocument.refreshReferenceObject("parentDocument");
        }
        Map<String,Object> qMap = new HashMap<String,Object>();
        qMap.put("budgetId",budget.getBudgetId());
        ArrayList<BudgetRate> allPropRates = (ArrayList)businessObjectService.findMatching(
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
    public List<ValidCeJobCode> getApplicableCostElements(Long budgetId, String personSequenceNumber) {
        List<ValidCeJobCode> validCostElements = null;

        String jobCodeValidationEnabledInd = this.parameterService.getParameterValue(
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
                List<KeyLabelPair> allPersonnelCostElements = ceValuesFinder.getKeyValues();
                for (KeyLabelPair keyLabelPair : allPersonnelCostElements) {
                    if(StringUtils.isNotEmpty(keyLabelPair.getKey().toString())) {
                        resultStr += "," + keyLabelPair.getKey() + ";" + keyLabelPair.getLabel();
                    }
                }
                resultStr += ",ceLookup;true";
            }
        }
        
        return resultStr;
    }

    @SuppressWarnings("unchecked")
    public List<String> getExistingGroupNames(String budgetId, String budgetPeriod) {
        List<String> groupNames = new ArrayList<String>();
        Map fieldValues = new HashMap();
        fieldValues.put("budgetId", budgetId);
        fieldValues.put("budgetPeriodId", budgetPeriod);
        List<BudgetLineItem> budgetLineItems = (List<BudgetLineItem>) businessObjectService.findByPrimaryKey(BudgetLineItem.class, fieldValues);
        
        for(BudgetLineItem budgetLineItem: budgetLineItems) {
            if(StringUtils.isNotEmpty(budgetLineItem.getGroupName())) {
                groupNames.add(budgetLineItem.getGroupName());
            }
        }
        
        return groupNames;
    }

    public String getExistingGroupNamesForAjaxCall(String budgetId, String budgetPeriod) {
        List<String> groupNames = getExistingGroupNames(budgetId, budgetPeriod);
        String resultStr = "";
        
        for (String groupName : groupNames) {
            resultStr += "," + groupName;
        }
        
        return resultStr;
    }
    
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
//            panelName.append(" (");
//            panelName.append(budgetPeriod.getBudgetLineItems().size());
//            panelName.append(" line item");
//            if(budgetPeriod.getBudgetLineItems().size() > 1)
//                panelName.append("s");
//            panelName.append(")");
        }
        
        return panelName.toString();
    }

    @SuppressWarnings("unchecked")
    public Collection<BudgetRate> getSavedProposalRates(BudgetVersionOverview budgetToOpen) {
        Map<String,Long> qMap = new HashMap<String,Long>();
        qMap.put("budgetId",budgetToOpen.getBudgetId());
        return businessObjectService.findMatching(BudgetRate.class, qMap);
    }

    /**
     * 
     * @see org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService#validateBudgetAuditRule(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
     */
    @SuppressWarnings("unchecked")
    public boolean validateBudgetAuditRule(BudgetParentDocument<T> parentDocument) throws Exception {
        boolean valid = true;
        boolean finalAndCompleteBudgetVersionFound = false;
        boolean budgetVersionsExists = false;
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        String budgetStatusCompleteCode = this.parameterService.getParameterValue(BudgetDocument.class, Constants.BUDGET_STATUS_COMPLETE_CODE);
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
            GlobalVariables.getAuditErrorMap().put("budgetVersionErrors", new AuditCluster(Constants.BUDGET_VERSION_PANEL_NAME, auditErrors, Constants.AUDIT_ERRORS));
        }

        return valid;
    }
    
    /**
     * 
     * @see org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService#validateBudgetAuditRuleBeforeSaveBudgetVersion(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
     */
    public boolean validateBudgetAuditRuleBeforeSaveBudgetVersion(BudgetParentDocument<T> proposalDevelopmentDocument)
            throws Exception {
        boolean valid = true;
        for (BudgetDocumentVersion budgetDocumentVersion : proposalDevelopmentDocument.getBudgetDocumentVersions()) {
            BudgetVersionOverview budgetVersion = budgetDocumentVersion.getBudgetVersionOverview();
            
            String budgetStatusCompleteCode = this.parameterService.getParameterValue(BudgetDocument.class,
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
            for (Object key : GlobalVariables.getAuditErrorMap().keySet()) {
                AuditCluster auditCluster = (AuditCluster)GlobalVariables.getAuditErrorMap().get(key);
                if (auditCluster.getCategory().equals(Constants.AUDIT_ERRORS)) {
                    valid = false;
                    break;
                }
            }
        }

        return valid;
    }

    @SuppressWarnings("unchecked")
    protected boolean applyAuditRuleForBudgetDocument(BudgetVersionOverview budgetVersion) throws Exception {
        DocumentService documentService = KraServiceLocator.getService(DocumentService.class);
        BudgetDocument<T> budgetDocument = (BudgetDocument<T>) documentService.getByDocumentHeaderId(budgetVersion.getDocumentNumber());
        return KraServiceLocator.getService(KualiRuleService.class).applyRules(new DocumentAuditEvent(budgetDocument));

    }
    
    /**
     * @throws NoSuchMethodException 
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @throws FormatException 
     * @see org.kuali.kra.budget.core.BudgetService#copyBudgetVersion(org.kuali.kra.budget.document.BudgetDocument)
     */
    public BudgetDocument copyBudgetVersion(BudgetDocument budgetDocument) throws WorkflowException {
        budgetDocument.toCopy();
        if(budgetDocument.getBudgets().isEmpty()) 
            throw new RuntimeException("Not able to find any Budget Version associated with this document");
        Budget budget = budgetDocument.getBudget();
        
        budget.setBudgetVersionNumber(budgetDocument.getParentDocument().getNextBudgetVersionNumber());
        try {
//            deepCopyPostProcessor.fixProperty(budgetDocument, Long.class, null, 
//                    new String[]{"setBudgetId","setBudgetPeriodId","setBudgetLineItemId",
//                                    "setBudgetLineItemCalculatedAmountId","setBudgetPersonnelLineItemId",
//                                    "setBudgetPersonnelCalculatedAmountId","setBudgetPersonnelRateAndBaseId","setBudgetRateAndBaseId"});
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
            
//            budgetDocument = (BudgetDocument)getDeepCopyPostProcessor().processDeepCopyIgnoreAnnotation(budgetDocument);
//            budget.setBudgetDocument(budgetDocument);
            ObjectUtils.materializeAllSubObjects(budgetDocument);
        }
        catch (Exception e) {
            e.printStackTrace();
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
        for (BudgetProjectIncome projectIncome : projectIncomes) {
            projectIncome.setBudgetId(budgetDocument.getBudget().getBudgetId());
            for (BudgetPeriod budgetPeriod : budgetDocument.getBudget().getBudgetPeriods()) {
                if (budgetPeriod.getBudgetPeriod().equals(projectIncome.getBudgetPeriodNumber())) {
                    projectIncome.setBudgetPeriodId(budgetPeriod.getBudgetPeriodId());
                    break;
                }
            }
        }
        businessObjectService.save(projectIncomes);
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
     * Sets the deepCopyPostProcessor attribute value.
     * @param deepCopyPostProcessor The deepCopyPostProcessor to set.
     */
    public void setDeepCopyPostProcessor(DeepCopyPostProcessor deepCopyPostProcessor) {
        this.deepCopyPostProcessor = deepCopyPostProcessor;
    }

    /**
     * Gets the deepCopyPostProcessor attribute. 
     * @return Returns the deepCopyPostProcessor.
     */
    public DeepCopyPostProcessor getDeepCopyPostProcessor() {
        return deepCopyPostProcessor;
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

}
