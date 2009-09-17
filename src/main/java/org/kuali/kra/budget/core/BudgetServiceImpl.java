/*
 * Copyright 2006-2009 The Kuali Foundation
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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.budget.calculator.QueryList;
import org.kuali.kra.budget.calculator.RateClassType;
import org.kuali.kra.budget.calculator.query.Equals;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.BudgetParentDocument;
import org.kuali.kra.budget.lookup.keyvalue.CostElementValuesFinder;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItemBase;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.personnel.BudgetPerson;
import org.kuali.kra.budget.personnel.BudgetPersonService;
import org.kuali.kra.budget.personnel.PersonRolodex;
import org.kuali.kra.budget.personnel.ValidCeJobCode;
import org.kuali.kra.budget.rates.BudgetProposalRate;
import org.kuali.kra.budget.rates.BudgetRatesService;
import org.kuali.kra.budget.rates.ValidCeRateType;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.budget.versions.BudgetVersionOverview;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.budget.modular.BudgetModular;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.event.AddBudgetVersionEvent;
import org.kuali.kra.proposaldevelopment.rules.BudgetVersionRule;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.bo.DocumentHeader;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.document.authorization.PessimisticLock;
import org.kuali.rice.kns.rule.event.DocumentAuditEvent;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.service.KualiRuleService;
import org.kuali.rice.kns.service.PessimisticLockService;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.ObjectUtils;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

/**
 * This class implements methods specified by BudgetDocumentService interface
 */
public class BudgetServiceImpl implements BudgetService {
    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(BudgetServiceImpl.class);
    
    private DocumentService documentService;
    private BusinessObjectService businessObjectService;
    private KualiConfigurationService kualiConfigurationService;
    private BudgetPersonService budgetPersonService;
    private BudgetRatesService budgetRatesService;
    private PessimisticLockService pessimisticLockService;
    private BudgetVersionRule budgetVersionRule;
    

    
    /**
     * Service method for adding a {@link BudgetVersionOverview} to a {@link ProposalDevelopmentDocument}. If a 
     * {@link BudgetVersionOverview} instance with the  <code>versionName</code> already exists 
     * in the {@link ProposalDevelopmentDocument}, then a hard error will occur. Try it and you'll see what I mean.
     * 
     * @param document instance to add {@link BudgetVersionOverview} to
     * @param versionName of the {@link BudgetVersionOverview}
     */
    public void addBudgetVersion(BudgetParentDocument document, String versionName) throws WorkflowException {
        if (!isBudgetVersionNameValid(document, versionName)) {
            debug("Buffered Version not Valid");
            return;
        }

        BudgetDocument newBudgetDoc = getNewBudgetVersion(document, versionName);
        
        PessimisticLock budgetLockForProposalDoc = null;

        for(PessimisticLock pdLock : document.getPessimisticLocks()) {
            if(pdLock.getLockDescriptor()!=null && pdLock.getLockDescriptor().contains(KraAuthorizationConstants.LOCK_DESCRIPTOR_BUDGET)) {
                budgetLockForProposalDoc = pdLock;
                break;
            }
        }

        try {
            PessimisticLock budgetLockForBudgetDoc = getPessimisticLockService().generateNewLock(newBudgetDoc.getDocumentNumber(), budgetLockForProposalDoc.getLockDescriptor(), budgetLockForProposalDoc.getOwnedByUser());
            newBudgetDoc.addPessimisticLock(budgetLockForBudgetDoc);
        } catch (Exception e) {
            
        }

//        document.addNewBudgetVersion(newBudgetDoc, versionName, false);
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
    public boolean isBudgetVersionNameValid(BudgetParentDocument document,  String name) {
        debug("Invoking budgetrule " + getBudgetVersionRule());
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
     * Retrieve injected <code>{@link BudgetVersionRule}</code> singleton
     * 
     * @return BudgetVersionRule
     */
    public BudgetVersionRule getBudgetVersionRule() {
        return budgetVersionRule;
    }

    /**
     * Inject <code>{@BudgetVersionRule}</code> singleton
     * 
     * @return BudgetVersionRule
     */
    public void setBudgetVersionRule(BudgetVersionRule budgetVersionRule) {
        this.budgetVersionRule = budgetVersionRule;
    }
    
    /**
     * @see org.kuali.kra.budget.core.BudgetService#getNewBudgetVersion(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument, java.lang.String)
     */
    public BudgetDocument getNewBudgetVersion(BudgetParentDocument parentDocument, String documentDescription) throws WorkflowException {
        
        BudgetDocument budgetDocument;
        Integer budgetVersionNumber = parentDocument.getNextBudgetVersionNumber();
        
        budgetDocument = (BudgetDocument) documentService.getNewDocument(BudgetDocument.class);
        budgetDocument.setParentDocument(parentDocument);
        budgetDocument.setParentDocumentKey(parentDocument.getDocumentNumber());
        budgetDocument.setParentDocumentTypeCode(parentDocument.getDocumentTypeCode());
        budgetDocument.getDocumentHeader().setDocumentDescription(documentDescription);
        
        Budget budget = budgetDocument.getBudget();
        budget.setBudgetVersionNumber(budgetVersionNumber);
        budget.setBudgetDocument(budgetDocument);
        
        budget.setStartDate(parentDocument.getBudgetParent().getRequestedStartDateInitial());
        budget.setEndDate(parentDocument.getBudgetParent().getRequestedEndDateInitial());
        budget.setOhRateTypeCode(kualiConfigurationService.getParameterValue(
                Constants.PARAMETER_MODULE_BUDGET, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.BUDGET_DEFAULT_OVERHEAD_RATE_TYPE_CODE));
        budget.setOhRateClassCode(kualiConfigurationService.getParameterValue(
                Constants.PARAMETER_MODULE_BUDGET, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.BUDGET_DEFAULT_OVERHEAD_RATE_CODE));
        budget.setUrRateClassCode(kualiConfigurationService.getParameterValue(
                Constants.PARAMETER_MODULE_BUDGET, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.BUDGET_DEFAULT_UNDERRECOVERY_RATE_CODE));
        budget.setModularBudgetFlag(kualiConfigurationService.getParameterValue(
                Constants.PARAMETER_MODULE_BUDGET, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.BUDGET_DEFAULT_MODULAR_FLAG).equalsIgnoreCase(Constants.TRUE_FLAG));
        String budgetStatusIncompleteCode = KraServiceLocator.getService(KualiConfigurationService.class).getParameterValue(
                Constants.PARAMETER_MODULE_BUDGET, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.BUDGET_STATUS_INCOMPLETE_CODE);
        budget.setBudgetStatus(budgetStatusIncompleteCode);

        // Copy in key personnel
        for (PersonRolodex proposalPerson: parentDocument.getBudgetParent().getPersonRolodexList()) {
            if (!proposalPerson.isOtherSignificantContributorFlag()) {
                BudgetPerson budgetPerson = new BudgetPerson(proposalPerson);
                budgetPersonService.populateBudgetPersonData(budget, budgetPerson);
                budgetPerson.setEffectiveDate(parentDocument.getBudgetParent().getRequestedStartDateInitial());
                budget.addBudgetPerson(budgetPerson);
            }
        }

        //Rates-Refresh Scenario-1
        budget.setRateClassTypesReloaded(true);
        documentService.saveDocument(budgetDocument);
        documentService.routeDocument(budgetDocument, "Route to Final", new ArrayList());
        budgetDocument = (BudgetDocument) documentService.getByDocumentHeaderId(budgetDocument.getDocumentNumber());
        parentDocument.refreshReferenceObject("budgetDocumentVersions");
//        budgetDocument.refreshReferenceObject("budgets");
//        budgetDocument.setParentDocument(parentDocument);
        return budgetDocument;
    }
    
    /**
     * @see org.kuali.kra.budget.core.BudgetService#copyBudgetVersion(org.kuali.kra.budget.document.BudgetDocument)
     */
    public BudgetDocument copyBudgetVersion(BudgetDocument budgetDocument) throws WorkflowException {
        budgetDocument.toCopy();
        ObjectUtils.materializeAllSubObjects(budgetDocument);

        try {
            Map<String, Object> objectMap = new HashMap<String, Object>();
            fixProperty(budgetDocument, "setBudgetPeriodId", Long.class, null, objectMap);
            objectMap.clear();
            fixProperty(budgetDocument, "setBudgetVersionNumber", Integer.class, 
                    budgetDocument.getBudget().getBudgetVersionNumber(), objectMap); 
            objectMap.clear();
            fixProperty(budgetDocument, "setVersionNumber", Long.class, new Long(0), objectMap);
            objectMap.clear();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        // jira 1365 re-open issue
        //Work around for 1-to-1 Relationship between BudgetPeriod & BudgetModular
        Map<String, BudgetModular> tmpBudgetModulars = new HashMap<String, BudgetModular>(); 
        for(BudgetPeriod budgetPeriod: budgetDocument.getBudget().getBudgetPeriods()) {
            BudgetModular tmpObject = null;
            if(budgetPeriod.getBudgetModular() != null) {
                tmpObject = (BudgetModular) ObjectUtils.deepCopy(budgetPeriod.getBudgetModular());
            }
//            tmpBudgetModulars.put(budgetPeriod.getProposalNumber()+ (budgetPeriod.getVersionNumber()+1) + budgetPeriod.getBudgetPeriod(), tmpObject);
            tmpBudgetModulars.put(""+budgetPeriod.getBudgetId()+ (budgetPeriod.getBudget().getVersionNumber()+1) + budgetPeriod.getBudgetPeriod(), tmpObject);
            budgetPeriod.setBudgetModular(null);
        }

        
        budgetDocument.setVersionNumber(null);
        
        documentService.saveDocument(budgetDocument);
        for(BudgetPeriod tmpBudgetPeriod: budgetDocument.getBudget().getBudgetPeriods()) {
//            BudgetModular tmpBudgetModular = tmpBudgetModulars.get(tmpBudgetPeriod.getProposalNumber()+ tmpBudgetPeriod.getVersionNumber() + tmpBudgetPeriod.getBudgetPeriod());
            BudgetModular tmpBudgetModular = tmpBudgetModulars.get(tmpBudgetPeriod.getBudget().getBudgetId()+ tmpBudgetPeriod.getBudget().getVersionNumber() + tmpBudgetPeriod.getBudgetPeriod());
            if(tmpBudgetModular != null) {
                tmpBudgetModular.setBudgetPeriodId(tmpBudgetPeriod.getBudgetPeriodId());
                tmpBudgetPeriod.setBudgetModular(tmpBudgetModular);
            }
        }
        
        documentService.saveDocument(budgetDocument);
        documentService.routeDocument(budgetDocument, "Route to Final", new ArrayList());
        return budgetDocument;
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

    public void setKualiConfigurationService(KualiConfigurationService kualiConfigurationService) {
        this.kualiConfigurationService = kualiConfigurationService;
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
    private void fixProperty(Object object, String methodName, Class clazz, Object propertyValue, Map<String, Object> objectMap){
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
                                Collection c = (Collection) value;
                                Iterator iter = c.iterator();
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
    private boolean isPropertyGetterMethod(Method method, Method methods[]) {
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
    
    public Collection<BudgetProposalRate> getSavedProposalRates(Budget budget) {
        Map qMap = new HashMap();
        qMap.put("budgetId",budget.getBudgetId());
        return businessObjectService.findMatching(BudgetProposalRate.class, qMap);
    }
    
    public boolean checkActivityTypeChange(Collection<BudgetProposalRate> allPropRates, String activityTypeCode) {
        if (CollectionUtils.isNotEmpty(allPropRates)) {
            Equals equalsActivityType = new Equals("activityTypeCode", activityTypeCode);
            QueryList matchActivityTypePropRates = new QueryList(allPropRates).filter(equalsActivityType);
            if (CollectionUtils.isEmpty(matchActivityTypePropRates) || allPropRates.size() != matchActivityTypePropRates.size()) {
                return true;
            }
        }
                
        return false;
    }
    
    public boolean checkActivityTypeChange(BudgetParentDocument pdDoc, Budget budget) {
        return checkActivityTypeChange(getSavedProposalRates(budget), pdDoc.getBudgetParent().getActivityTypeCode());
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
        QueryList<ValidCeRateType> qValidCeRateTypes = validCeRateTypes == null ? new QueryList() : new QueryList(validCeRateTypes);
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

    public String getActivityTypeForBudget(BudgetDocument budgetDocument) {
        Budget budget = budgetDocument.getBudget();
        BudgetParent budgetParent = budgetDocument.getParentDocument().getBudgetParent();
        if(budgetParent==null){
            budgetDocument.refreshReferenceObject("parentDocument");
        }
        Map<String,Object> qMap = new HashMap<String,Object>();
        qMap.put("budgetId",budget.getBudgetId());
        ArrayList<BudgetProposalRate> allPropRates = (ArrayList)businessObjectService.findMatching(
                BudgetProposalRate.class, qMap);
        if (CollectionUtils.isNotEmpty(allPropRates)) {
            qMap.put("activityTypeCode",budgetParent.getActivityTypeCode());
            Collection<BudgetProposalRate> matchActivityTypePropRates =businessObjectService.findMatching(
                BudgetProposalRate.class, qMap);
            if (CollectionUtils.isNotEmpty(matchActivityTypePropRates)) {
                for (BudgetProposalRate budgetProposalRate : allPropRates) { 
                    if (!budgetProposalRate.getActivityTypeCode().equals(budgetParent.getActivityTypeCode())) {
                        return budgetProposalRate.getActivityTypeCode();
                    }
                }
                return budgetParent.getActivityTypeCode();                
            } else {
                return allPropRates.get(0).getActivityTypeCode();
            }
        }
                
        return "x";
        
    }



    public List<ValidCeJobCode> getApplicableCostElements(Long budgetId, String personSequenceNumber) {
        List<ValidCeJobCode> validCostElements = null;

        String jobCodeValidationEnabledInd = KraServiceLocator.getService(KualiConfigurationService.class).getParameter(
                Constants.PARAMETER_MODULE_BUDGET, Constants.PARAMETER_COMPONENT_DOCUMENT,
                Constants.BUDGET_JOBCODE_VALIDATION_ENABLED).getParameterValue();
        
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
    
    public String getApplicableCostElementsForAjaxCall(Long budgetId,String personSequenceNumber, 
            String budgetCategoryTypeCode) {
        
        String resultStr = "";
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
        
        return resultStr;
    }

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

    public Collection<BudgetProposalRate> getSavedProposalRates(BudgetVersionOverview budgetToOpen) {
        Map qMap = new HashMap();
        qMap.put("budgetId",budgetToOpen.getBudgetId());
        return businessObjectService.findMatching(BudgetProposalRate.class, qMap);
    }

    /**
     * 
     * @see org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService#validateBudgetAuditRule(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
     */
    public boolean validateBudgetAuditRule(BudgetParentDocument parentDocument) throws Exception {
        boolean valid = true;
        boolean finalAndCompleteBudgetVersionFound = false;
        boolean budgetVersionsExists = false;
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        String budgetStatusCompleteCode = KraServiceLocator.getService(KualiConfigurationService.class).getParameter(
                Constants.PARAMETER_MODULE_BUDGET, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.BUDGET_STATUS_COMPLETE_CODE).getParameterValue();
        for (BudgetDocumentVersion budgetDocumentVersion : parentDocument.getBudgetDocumentVersions()) {
            BudgetVersionOverview budgetVersion = budgetDocumentVersion.getBudgetVersionOverview();
            budgetVersionsExists = true;
            if (budgetVersion.isFinalVersionFlag()) {
                valid &= applyAuditRuleForBudgetDocument(budgetVersion);
                if (parentDocument.getBudgetParent().getBudgetStatus()!= null 
                        && parentDocument.getBudgetParent().getBudgetStatus().equals(budgetStatusCompleteCode)) {
                    finalAndCompleteBudgetVersionFound = true;
                }
            }
        }
        if(budgetVersionsExists && !finalAndCompleteBudgetVersionFound){
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
    public boolean validateBudgetAuditRuleBeforeSaveBudgetVersion(BudgetParentDocument proposalDevelopmentDocument)
            throws Exception {
        boolean valid = true;
        for (BudgetDocumentVersion budgetDocumentVersion : proposalDevelopmentDocument.getBudgetDocumentVersions()) {
            BudgetVersionOverview budgetVersion = budgetDocumentVersion.getBudgetVersionOverview();
            
            String budgetStatusCompleteCode = KraServiceLocator.getService(KualiConfigurationService.class).getParameter(
                    Constants.PARAMETER_MODULE_BUDGET, Constants.PARAMETER_COMPONENT_DOCUMENT,
                    Constants.BUDGET_STATUS_COMPLETE_CODE).getParameterValue();
            // if status is complete and version is not final, then business rule will take care of it
            if (budgetVersion.isFinalVersionFlag() && budgetVersion.getBudgetStatus() != null
                    && budgetVersion.getBudgetStatus().equals(budgetStatusCompleteCode)) {
                valid &= applyAuditRuleForBudgetDocument(budgetVersion);
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

    private boolean applyAuditRuleForBudgetDocument(BudgetVersionOverview budgetVersion) throws Exception {
        DocumentService documentService = KraServiceLocator.getService(DocumentService.class);
        BudgetDocument budgetDocument = (BudgetDocument) documentService.getByDocumentHeaderId(budgetVersion.getDocumentNumber());
        return KraServiceLocator.getService(KualiRuleService.class).applyRules(new DocumentAuditEvent(budgetDocument));

    }
    
    
    /**
     * Gets the budgetRatesService attribute. 
     * @return Returns the budgetRatesService.
     */
    public BudgetRatesService getBudgetRatesService() {
        return budgetRatesService;
    }

    /**
     * Sets the budgetRatesService attribute value.
     * @param budgetRatesService The budgetRatesService to set.
     */
    public void setBudgetRatesService(BudgetRatesService budgetRatesService) {
        this.budgetRatesService = budgetRatesService;
    }

}
