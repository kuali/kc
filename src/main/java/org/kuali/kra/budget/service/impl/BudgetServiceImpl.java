/*
 * Copyright 2008 The Kuali Foundation.
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
package org.kuali.kra.budget.service.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.kuali.core.bo.DocumentHeader;
import org.kuali.core.bo.PersistableBusinessObject;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.DocumentService;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.util.ObjectUtils;
import org.kuali.kra.budget.bo.BudgetPerson;
import org.kuali.kra.budget.bo.BudgetVersionOverview;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.service.BudgetPersonService;
import org.kuali.kra.budget.service.BudgetService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

import edu.iu.uis.eden.exception.WorkflowException;

/**
 * This class implements methods specified by BudgetDocumentService interface
 */
public class BudgetServiceImpl implements BudgetService {
    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(BudgetServiceImpl.class);
    
    private DocumentService documentService;
    private BusinessObjectService businessObjectService;
    private KualiConfigurationService kualiConfigurationService;
    private BudgetPersonService budgetPersonService;
    
    /**
     * @see org.kuali.kra.budget.service.BudgetService#getNewBudgetVersion(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument, java.lang.String)
     */
    public BudgetDocument getNewBudgetVersion(ProposalDevelopmentDocument pdDoc, String documentDescription) throws WorkflowException {
        
        BudgetDocument budgetDocument;
        Integer budgetVersionNumber = pdDoc.getNextBudgetVersionNumber();
        
        budgetDocument = (BudgetDocument) documentService.getNewDocument(BudgetDocument.class);
        budgetDocument.setProposalNumber(pdDoc.getProposalNumber());
        budgetDocument.setBudgetVersionNumber(budgetVersionNumber);
        budgetDocument.getDocumentHeader().setFinancialDocumentDescription(documentDescription);
        budgetDocument.setStartDate(pdDoc.getRequestedStartDateInitial());
        budgetDocument.setEndDate(pdDoc.getRequestedEndDateInitial());
        budgetDocument.setOhRateTypeCode(kualiConfigurationService.getParameterValue(
                Constants.PARAMETER_MODULE_BUDGET, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.BUDGET_DEFAULT_OVERHEAD_RATE_TYPE_CODE));
        budgetDocument.setOhRateClassCode(kualiConfigurationService.getParameterValue(
                Constants.PARAMETER_MODULE_BUDGET, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.BUDGET_DEFAULT_OVERHEAD_RATE_CODE));
        budgetDocument.setUrRateClassCode(kualiConfigurationService.getParameterValue(
                Constants.PARAMETER_MODULE_BUDGET, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.BUDGET_DEFAULT_UNDERRECOVERY_RATE_CODE));
        budgetDocument.setModularBudgetFlag(kualiConfigurationService.getParameterValue(
                Constants.PARAMETER_MODULE_BUDGET, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.BUDGET_DEFAULT_MODULAR_FLAG).equalsIgnoreCase(Constants.TRUE_FLAG));
        
        // Copy in key personnel
        for (ProposalPerson proposalPerson: pdDoc.getProposalPersons()) {
            if (!proposalPerson.isOtherSignificantContributorFlag()) {
                BudgetPerson budgetPerson = new BudgetPerson(proposalPerson);
                budgetPersonService.populateBudgetPersonData(budgetDocument, budgetPerson);
                budgetPerson.setEffectiveDate(pdDoc.getRequestedStartDateInitial());
                budgetDocument.addBudgetPerson(budgetPerson);
            }
        }
        
        documentService.saveDocument(budgetDocument);
        
        documentService.routeDocument(budgetDocument, "Route to Final", new ArrayList());
        return budgetDocument;
    }
    
    /**
     * @see org.kuali.kra.budget.service.BudgetService#copyBudgetVersion(org.kuali.kra.budget.document.BudgetDocument)
     */
    public BudgetDocument copyBudgetVersion(BudgetDocument budgetDocument) throws WorkflowException {
        budgetDocument.toCopy();
        ObjectUtils.materializeAllSubObjects(budgetDocument);

        try {
            Map<String, Object> objectMap = new HashMap<String, Object>();
            fixProperty(budgetDocument, "setBudgetPeriodId", Long.class, null, objectMap);
            objectMap.clear();
            fixProperty(budgetDocument, "setBudgetVersionNumber", Integer.class, budgetDocument.getBudgetVersionNumber(), objectMap); 
            objectMap.clear();
            fixProperty(budgetDocument, "setVersionNumber", Long.class, new Long(0), objectMap);
            objectMap.clear();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        budgetDocument.setVersionNumber(null);
        
        documentService.saveDocument(budgetDocument);
        documentService.routeDocument(budgetDocument, "Route to Final", new ArrayList());
        return budgetDocument;
    }
    
    public void updateDocumentDescription(BudgetVersionOverview budgetVersion) {
        BusinessObjectService boService = KraServiceLocator.getService(BusinessObjectService.class);
        Map<String, Object> keyMap = new HashMap<String, Object>();
        keyMap.put("documentNumber", budgetVersion.getDocumentNumber());
        DocumentHeader docHeader = (DocumentHeader) boService.findByPrimaryKey(DocumentHeader.class, keyMap);
        if (!docHeader.getFinancialDocumentDescription().equals(budgetVersion.getDocumentDescription())) {
            docHeader.setFinancialDocumentDescription(budgetVersion.getDocumentDescription());
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
    
    /**
     * Recurse through all of the BOs and if a BO has a ProposalNumber property,
     * set its value to the new proposal number.
     * @param object the object
     * @param proposalNumber the proposal number
     */
    private void fixProperty(Object object, String methodName, Class clazz, Object propertyValue, Map<String, Object> objectMap) throws Exception {
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
}
