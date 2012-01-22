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
package org.kuali.kra.rules;

import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.util.AbstractMap.SimpleEntry;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.keyvalue.DefaultMapEntry;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.BudgetParentDocument;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.budget.versions.BudgetVersionOverview;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.DocumentRuleBase;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

/**
 * Base implementation class for KRA document business rules
 *
 * @author $Author: gmcgrego $
 * @version $Revision: 1.13 $
 */
public abstract class ResearchDocumentRuleBase extends DocumentRuleBase implements DocumentAuditRule {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ResearchDocumentRuleBase.class);
    public static final String DOCUMENT_ERROR_PATH = "document";
    public static final boolean VALIDATION_REQUIRED = true;
    public static final boolean CHOMP_LAST_LETTER_S_FROM_COLLECTION_NAME = false;
    private final ErrorReporter errorReporter = new ErrorReporter();

    private BusinessObjectService businessObjectService;
    private ParameterService parameterService;

    /**
     * Delegates to {@link ErrorReporter#reportError(String, String, String...) ErrorReporter#reportError(String, String, String...)}
     * to keep api compatibility.
     * @see ErrorReporter#reportError(String, String, String...)
     */
    protected void reportError(String propertyName, String errorKey, String... errorParams) {
        this.errorReporter.reportError(propertyName, errorKey, errorParams);
    }
    
    /**
     * Delegates to {@link ErrorReporter#reportError(String, String, String...) ErrorReporter#reportError(String, String, String...)}
     * to keep api compatibility.
     * @see ErrorReporter#reportError(String, String, String...)
     */
    protected void reportWarning(String propertyName, String errorKey, String... errorParams) {
        this.errorReporter.reportWarning(propertyName, errorKey, errorParams);
    }
    
    /**
     * Delegates to {@link ErrorReporter#reportAuditError(AuditError, String, String, String) ErrorReporter#reportAuditError(AuditError, String, String, String)}
     * to keep api compatibility.
     * @see ErrorReporter#reportAuditError(AuditError, String, String, String)
     */
    protected void addAuditError(AuditError error, String errorKey, String clusterLabel, String clusterCategory) {
        this.errorReporter.reportAuditError(error, errorKey, clusterLabel, clusterCategory);
    }
    
    /**
     * Delegates to {@link ErrorReporter#reportSoftError(String, String, String...) ErrorReporter#reportSoftError(String, String, String...)}
     * to keep api compatibility.
     * @see ErrorReporter#reportSoftError(String, String, String...)
     */
    protected void reportSoftError(String propertyName, String errorKey, String... errorParams) {
        this.errorReporter.reportSoftError(propertyName, errorKey, errorParams);
    }

    /**
     * Delegates to {@link ErrorReporter#getSoftErrors() ErrorReporter#getSoftErrors()}
     * to keep api compatibility.
     * @see ErrorReporter#getSoftErrors()
     */
    public Map<String, Collection<SoftError>> getSoftErrors() {
        return this.errorReporter.getSoftErrors();
    }
    
    /**
     * Wrapper around global errorMap.put call, to allow better logging
     * 
     * @param propertyName
     * @param errorKey
     * @param errorParams
     */
    protected void reportErrorWithoutFullErrorPath(String propertyName, String errorKey, String... errorParams) {
        LOG.debug("reportErrorWithoutFullErrorPath(String, String, String) - start");

        GlobalVariables.getMessageMap().putErrorWithoutFullErrorPath(propertyName, errorKey, errorParams);
        if (LOG.isDebugEnabled()) {
            LOG.debug("rule failure at " + ErrorReporter.getMethodPath(1, 2));
        }
    }

    public boolean processRunAuditBusinessRules(Document document) {
        return new ResearchDocumentBaseAuditRule().processRunAuditBusinessRules(document);        
    }

    /**
     *
     * This method checks budget versions business rules.
     *
     * @param budgetParentDocument the document
     * @param runDatactionaryValidation if dd validation should be run
     * @return true if valid false if not
     * @throws NullPointerException if the proposalDevelopmentDocument is null
     */
    protected boolean processBudgetVersionsBusinessRule(
        final BudgetParentDocument budgetParentDocument,
        final boolean runDatactionaryValidation) {
        if (budgetParentDocument == null) {
            throw new NullPointerException("the parentDocument is null.");
        }

        boolean valid = true;
        final MessageMap errorMap = GlobalVariables.getMessageMap();
        boolean finalVersionFound = false;

        final DictionaryValidationService dictionaryValidationService = (DictionaryValidationService) this.getDictionaryValidationService();

        int index = 0;
        for (BudgetDocumentVersion budgetDocumentVersion: budgetParentDocument.getBudgetDocumentVersions()) {
            BudgetVersionOverview budgetVersion = budgetDocumentVersion.getBudgetVersionOverview();
            if (runDatactionaryValidation) {
                dictionaryValidationService.validateBusinessObject(budgetVersion, true);
            }
            if (budgetVersion.isFinalVersionFlag()) {
                if (finalVersionFound) {
                    errorMap.putError("finalVersionFlag", KeyConstants.ERROR_MULTIPLE_FINAL_BUDGETS);
                } else {
                    finalVersionFound = true;
                }
            }

            final String budgetStatusCompleteCode = getParameterService().getParameterValueAsString(BudgetDocument.class, Constants.BUDGET_STATUS_COMPLETE_CODE);

            if (budgetStatusCompleteCode.equalsIgnoreCase(budgetVersion.getBudgetStatus())) {
                if (!budgetVersion.isFinalVersionFlag()) {
                    errorMap.putError("budgetVersionOverview[" + index + "].budgetStatus",
                        KeyConstants.ERROR_NO_FINAL_BUDGET);
                    valid = false;
                }
            }
            index++;
        }

        return valid;
    }

    /**
     * Does the current user have the given permission for the proposal?
     * @param doc the Proposal Development Document
     * @param permissionName the name of the permission
     * @return true if user has permission; otherwise false
     */
    protected boolean hasPermission(ProposalDevelopmentDocument doc, String permissionName) {
        String userId = GlobalVariables.getUserSession().getPrincipalId();
        KraAuthorizationService kraAuthorizationService = KraServiceLocator.getService(KraAuthorizationService.class);
        return kraAuthorizationService.hasPermission(userId, doc, permissionName); 
    }
    
    /**
     * Does the current user have the given role for the proposal?
     * @param doc the Proposal Development Document
     * @param roleName the name of the role
     * @return true if user has role; otherwise false
     */
    protected boolean hasRole(ProposalDevelopmentDocument doc, String roleName) {
        String userId = GlobalVariables.getUserSession().getPrincipalId();
        KraAuthorizationService kraAuthorizationService = KraServiceLocator.getService(KraAuthorizationService.class);
        return kraAuthorizationService.hasRole(userId, doc, roleName);
    }

    /**
     * Does the given user have the given permission for the proposal?
     * @param username the user's username
     * @param doc the Proposal Development Document
     * @param permissionName the name of the permission
     * @return true if user has permission; otherwise false
     */
    protected boolean hasPermission(String userId, ProposalDevelopmentDocument doc, String permissionName) {
        KraAuthorizationService kraAuthorizationService = KraServiceLocator.getService(KraAuthorizationService.class);
        return kraAuthorizationService.hasPermission(userId, doc, permissionName);
    }
    
    /**
     * Does the given user have the given role for the proposal?
     * @param username the user's username
     * @param doc the Proposal Development Document
     * @param roleName the name of the role
     * @return true if user has role; otherwise false
     */
    protected boolean hasRole(String userId, ProposalDevelopmentDocument doc, String roleName) {
        KraAuthorizationService kraAuthorizationService = KraServiceLocator.getService(KraAuthorizationService.class);
        return kraAuthorizationService.hasRole(userId, doc, roleName);   
    }
    
    /**
     * Convenience method for creating a <code>{@link SimpleEntry}</code> out of a key/value pair
     * 
     * @param key
     * @param value
     * @return SimpleImmutableEntry
     */
    protected Entry<String, String> keyValue(String key, Object value) {

        @SuppressWarnings("unchecked") //Commons Collections does not support Generics
        final Entry<String, String> entry
            = (value == null) ? new DefaultMapEntry(key, "") :  new DefaultMapEntry(key, value.toString());

        return entry;
    }
    /**
     * The opposite of <code>{@link #isValid(Class, SimpleEntry...)}</code>
     * 
     * @param boClass the class of the business object to validate
     * @param entries varargs array of <code>{@link SimpleEntry}</code> key/value pair instances
     * @return true if invalid; false if valid
     * @see #isValid(Class, SimpleImmutableEntry...)
     */
    protected boolean isInvalid(Class<?> boClass, Entry<String, String> ... entries) {
        return !isValid(boClass, entries);
    }
    
    /**
     * Is the given code valid?  Query the database for a matching code
     * If found, it is valid; otherwise it is invalid.<br/>
     * 
     * @param boClass the class of the business object to validate
     * @param entries varargs array of <code>{@link SimpleEntry}</code> key/value pair instances
     * @return true if invalid; false if valid
     * @see #isInvalid(Class, SimpleImmutableEntry...)
     */
    protected boolean isValid(Class<?> boClass, Entry<String,String> ... entries) {
        boolean retval = false;
        
        if (entries != null && entries.length > 0) {
            Map<String,String> fieldValues = new HashMap<String,String>();
            
            for (Entry<String,String> entry : entries) {
                fieldValues.put(entry.getKey(), entry.getValue());
            }

                if (getBusinessObjectService().countMatching(boClass, fieldValues) > 0) {
                    retval = true;
            }
        }
        return retval;
    }
    
    /**
     * Locate in Spring the <code>{@link BusinessObjectService}</code> singleton instance
     * 
     * @return BusinessObjectService
     */
    protected final BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }
    
    /**
     * This is a convenience method to use jmock to set the businessObjectService for unit testing.
     * @param businessObjectService
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService){
        this.businessObjectService = businessObjectService;
    }
    
    /**
     * Looks up and returns the ParameterService.
     * @return the parameter service. 
     */
    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KraServiceLocator.getService(ParameterService.class);        
        }
        return this.parameterService;
    }
    
    /**
     * This is a convenience method to use jmock to set the parameterService for unit testing.
     * @param businessObjectService
     */
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
    
    /**
     * Gets the error reporter.
     * @return the error reporter
     */
    public ErrorReporter getErrorReporter() {
        return this.errorReporter;
    }
}
