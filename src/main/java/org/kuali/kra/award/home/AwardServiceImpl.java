/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Commawardy License, Version 2.0 (the "License");
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
package org.kuali.kra.award.home;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.kuali.kra.award.budget.AwardBudgetExt;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.distributionincome.BudgetProjectIncome;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.BudgetParentDocument;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.summary.BudgetSummaryService;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.budget.modular.BudgetModular;
import org.kuali.kra.service.ServiceHelper;
import org.kuali.kra.service.VersionException;
import org.kuali.kra.service.VersionHistoryService;
import org.kuali.kra.service.VersioningService;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.ParameterService;
import org.kuali.rice.kns.util.ObjectUtils;

/** {@inheritDoc} */
public class AwardServiceImpl implements AwardService {
    
    private static final String AWARD_NUMBER = "awardNumber";
    private static final String AWARD_ID = "awardId";
    private static final String SEQUENCE_NUMBER = "sequenceNumber";
    
    private BusinessObjectService businessObjectService;
    private VersioningService versioningService;
    private DocumentService documentService;
    private VersionHistoryService versionHistoryService;
    private BudgetSummaryService budgetSummaryService;
    private ParameterService parameterService;

    /**
     * Note Awards are ordered by sequenceNumber
     * @see org.kuali.kra.award.home.AwardService#findAwardsForAwardNumber(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<Award> findAwardsForAwardNumber(String awardNumber) {
        List<Award> results = new ArrayList<Award>(businessObjectService.findMatchingOrderBy(Award.class, 
                                                                ServiceHelper.getInstance().buildCriteriaMap(AWARD_NUMBER, awardNumber),
                                                                SEQUENCE_NUMBER,
                                                                true));
        return results;
    }    
    
    /** {@inheritDoc} */
    public Award getAward(Long awardId) {
        return awardId != null ? (Award) businessObjectService.findByPrimaryKey(Award.class, 
                                        ServiceHelper.getInstance().buildCriteriaMap(AWARD_ID, awardId)) : null;
    }
    
    /** {@inheritDoc} */
    public Award getAward(String awardId) {
        return awardId != null ? (Award) businessObjectService.findByPrimaryKey(Award.class, 
                                        ServiceHelper.getInstance().buildCriteriaMap(AWARD_ID, awardId)) : null;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public AwardDocument createNewAwardVersion(AwardDocument awardDocument) throws VersionException, WorkflowException {
        Award newVersion = getVersioningService().createNewVersion(awardDocument.getAward());
        newVersion.getFundingProposals().clear();
        AwardDocument newAwardDocument = (AwardDocument) getDocumentService().getNewDocument(AwardDocument.class);
        newAwardDocument.getDocumentHeader().setDocumentDescription(awardDocument.getDocumentHeader().getDocumentDescription());
        newAwardDocument.setAward(newVersion);
        newVersion.setAwardDocument(newAwardDocument);
        newVersion.setAwardTransactionTypeCode(0);
        newVersion.getSyncChanges().clear();
        newVersion.getSyncStatuses().clear();
        newVersion.setSyncChild(false);
        copyBudgetData(awardDocument, newAwardDocument);
        return newAwardDocument;
    }

    private void copyBudgetData(AwardDocument award, AwardDocument newVersion) throws WorkflowException {
        List<String> budgetDocNumbers = new ArrayList<String>(award.getBudgetDocumentVersions().size());
        for (BudgetDocumentVersion budgetDocumentVersion : award.getBudgetDocumentVersions()) {
            budgetDocNumbers.add(budgetDocumentVersion.getDocumentNumber());
        }
        int i = 1;
        for (String docNumber : budgetDocNumbers) {
            copyAwardBudgetVersion(docNumber, newVersion, i++);
        }
        award.refreshReferenceObject("budgetDocumentVersions");
    }

    @SuppressWarnings("unchecked")
    private BudgetDocument copyAwardBudgetVersion(String documentNumber, BudgetParentDocument dest, int budgetVersionNumber)
            throws WorkflowException {
        BudgetDocument budgetDocument = (BudgetDocument) getDocumentService().getByDocumentHeaderId(documentNumber);

        budgetDocument.toCopy();
        budgetDocument.setVersionNumber(null);
        if (budgetDocument.getBudgets().isEmpty()) {
            return null;
        }
        budgetDocument.getBudget().setBudgetVersionNumber(budgetVersionNumber);
        Map<String, Object> objectMap = new HashMap<String, Object>();
        fixNumericProperty(budgetDocument, "setBudgetId", Long.class, null, objectMap);
        objectMap.clear();
        fixNumericProperty(budgetDocument, "setBudgetPeriodId", Long.class, null, objectMap);
        objectMap.clear();
        fixNumericProperty(budgetDocument, "setBudgetLineItemId", Long.class, null, objectMap);
        objectMap.clear();
        fixNumericProperty(budgetDocument, "setBudgetLineItemCalculatedAmountId", Long.class, null, objectMap);
        objectMap.clear();
        fixNumericProperty(budgetDocument, "setBudgetPersonnelLineItemId", Long.class, null, objectMap);
        objectMap.clear();
        fixNumericProperty(budgetDocument, "setBudgetPersonnelCalculatedAmountId", Long.class, null, objectMap);
        objectMap.clear();
        fixNumericProperty(budgetDocument, "setBudgetPersonnelRateAndBaseId", Long.class, null, objectMap);
        objectMap.clear();
        fixNumericProperty(budgetDocument, "setBudgetRateAndBaseId", Long.class, null, objectMap);
        objectMap.clear();
        fixNumericProperty(budgetDocument, "setVersionNumber", Integer.class, null, objectMap);
        objectMap.clear();

        ObjectUtils.materializeAllSubObjects(budgetDocument.getBudget());

        Budget budget = budgetDocument.getBudget();
        AwardBudgetExt awardBudget = (AwardBudgetExt) budget;
        awardBudget.setBudgetStatus(getParameterService().getParameterValue(AwardBudgetDocument.class,
                KeyConstants.AWARD_BUDGET_STATUS_IN_PROGRESS));

        budget.setFinalVersionFlag(false);
        budgetDocument.setParentDocumentKey(dest.getDocumentNumber());
        budgetDocument.setParentDocument(dest);

        // Work around for 1-to-1 Relationship between BudgetPeriod & BudgetModular
        Map<BudgetPeriod, BudgetModular> tmpBudgetModulars = new HashMap<BudgetPeriod, BudgetModular>();
        for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
            BudgetModular tmpObject = null;
            if (budgetPeriod.getBudgetModular() != null) {
                tmpObject = (BudgetModular) ObjectUtils.deepCopy(budgetPeriod.getBudgetModular());
            }
            tmpBudgetModulars.put(budgetPeriod, tmpObject);
            budgetPeriod.setBudgetModular(null);
        }

        List<BudgetProjectIncome> srcProjectIncomeList = budget.getBudgetProjectIncomes();
        budget.setBudgetProjectIncomes(new ArrayList<BudgetProjectIncome>());
        budget.setBudgetDocument(budgetDocument);
        budget.setDocumentNumber(budgetDocument.getDocumentNumber());
        getDocumentService().saveDocument(budgetDocument);

        for (BudgetPeriod tmpBudgetPeriod : budget.getBudgetPeriods()) {
            BudgetModular tmpBudgetModular = tmpBudgetModulars.get(tmpBudgetPeriod);
            if (tmpBudgetModular != null) {
                tmpBudgetModular.setBudgetPeriodId(tmpBudgetPeriod.getBudgetPeriodId());
                tmpBudgetPeriod.setBudgetModular(tmpBudgetModular);
            }

            for (BudgetProjectIncome budgetProjectIncome : srcProjectIncomeList) {
                if (budgetProjectIncome.getBudgetPeriodNumber().intValue() == tmpBudgetPeriod.getBudgetPeriod().intValue()) {
                    budgetProjectIncome.setBudgetPeriodId(tmpBudgetPeriod.getBudgetPeriodId());
                    budgetProjectIncome.setBudgetId(tmpBudgetPeriod.getBudget().getBudgetId());
                    budgetProjectIncome.setVersionNumber(new Long(0));
                }
            }
        }

        budget.setBudgetProjectIncomes(srcProjectIncomeList);
        getBudgetSummaryService().calculateBudget(budgetDocument.getBudget());
        getDocumentService().saveDocument(budgetDocument);
        budgetDocument.getParentDocument().refreshReferenceObject("budgetDocumentVersions");
        return budgetDocument;
    }

    /**
     * Recurse through all of the BOs and if a BO has a specific property, set its value to the new value.
     * 
     * @param object the object
     * @param propertyValue
     */
    @SuppressWarnings("unchecked")
    private void fixNumericProperty(Object object, String methodName, Class clazz, Object propertyValue,
            Map<String, Object> objectMap) {
        if (ObjectUtils.isNotNull(object) && object instanceof PersistableBusinessObject) {
            PersistableBusinessObject objectWId = (PersistableBusinessObject) object;
            if (objectMap.get(objectWId.getObjectId()) != null) {
                return;
            }
            objectMap.put(((PersistableBusinessObject) object).getObjectId(), object);

            Method[] methods = object.getClass().getMethods();
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    try {
                        if (clazz.equals(Long.class)) {
                            method.invoke(object, (Long) propertyValue);
                        } else {
                            method.invoke(object, (Integer) propertyValue);
                        }
                    }
                    catch (Throwable e) {
                        // We don't need to propagate this exception
                    }
                }
                else if (isPropertyGetterMethod(method, methods)) {
                    Object value = null;
                    try {
                        value = method.invoke(object);
                    }
                    catch (Throwable e) {
                        // We don't need to propagate this exception
                    }

                    if (value != null) {
                        if (value instanceof Collection) {
                            Collection c = (Collection) value;
                            Iterator iter = c.iterator();
                            while (iter.hasNext()) {
                                Object entry = iter.next();
                                fixNumericProperty(entry, methodName, clazz, propertyValue, objectMap);
                            }
                        }
                        else {
                            fixNumericProperty(value, methodName, clazz, propertyValue, objectMap);
                        }
                    }
                }
            }
        }
    }

    /**
     * Is the given method a getter method for a property? Must conform to the following:
     * <ol>
     * <li>Must start with the <b>get</b></li>
     * <li>Must have a corresponding setter method</li>
     * <li>Must have zero arguments.</li>
     * </ol>
     * 
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

    protected VersioningService getVersioningService() {
        return versioningService;
    }

    public void setVersioningService(VersioningService versioningService) {
        this.versioningService = versioningService;
    }

    protected DocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    protected VersionHistoryService getVersionHistoryService() {
        return versionHistoryService;
    }

    public void setVersionHistoryService(VersionHistoryService versionHistoryService) {
        this.versionHistoryService = versionHistoryService;
    }

    protected BudgetSummaryService getBudgetSummaryService() {
        return budgetSummaryService;
    }

    public void setBudgetSummaryService(BudgetSummaryService budgetSummaryService) {
        this.budgetSummaryService = budgetSummaryService;
    }

    protected ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
}
