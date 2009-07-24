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
package org.kuali.kra.rules;

import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.keyvalue.DefaultMapEntry;
import org.kuali.kra.budget.bo.BudgetVersionOverview;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.DocumentAuditRule;
import org.kuali.rice.kns.rules.DocumentRuleBase;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.ExceptionUtils;
import org.kuali.rice.kns.util.GlobalVariables;

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


    /**
     * Delegates to {@link ErrorReporter#reportError(String, String, String...) ErrorReporter#reportError(String, String, String...)}
     * to keep api compatibility.
     * @see ErrorReporter#reportError(String, String, String...)
     */
    protected void reportError(String propertyName, String errorKey, String... errorParams) {
        this.errorReporter.reportError(propertyName, errorKey, errorParams);
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

        GlobalVariables.getErrorMap().putErrorWithoutFullErrorPath(propertyName, errorKey, errorParams);
        if (LOG.isDebugEnabled()) {
            LOG.debug("rule failure at " + ExceptionUtils.describeStackLevels(1, 2));
        }
    }

    public boolean processRunAuditBusinessRules(Document document) {
        return new ResearchDocumentBaseAuditRule().processRunAuditBusinessRules(document);        
    }

    /**
     *
     * This method checks budget versions business rules.
     *
     * @param proposalDevelopmentDocument the document
     * @param runDatactionaryValidation if dd validation should be run
     * @return true if valid false if not
     * @throws NullPointerException if the proposalDevelopmentDocument is null
     */
    protected boolean processBudgetVersionsBusinessRule(
        final ProposalDevelopmentDocument proposalDevelopmentDocument,
        final boolean runDatactionaryValidation) {
        if (proposalDevelopmentDocument == null) {
            throw new NullPointerException("the proposalDevelopmentDocument is null.");
        }

        boolean valid = true;
        final ErrorMap errorMap = GlobalVariables.getErrorMap();
        boolean finalVersionFound = false;

        final DictionaryValidationService dictionaryValidationService
            = this.getDictionaryValidationService();

        int index = 0;
        for (BudgetVersionOverview budgetVersion: proposalDevelopmentDocument.getDevelopmentProposal().getBudgetVersionOverviews()) {
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

            final String budgetStatusCompleteCode = getKualiConfigurationService().getParameter(
                Constants.PARAMETER_MODULE_BUDGET, Constants.PARAMETER_COMPONENT_DOCUMENT,
                Constants.BUDGET_STATUS_COMPLETE_CODE).getParameterValue();

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
        UniversalUser user = (UniversalUser) GlobalVariables.getUserSession().getPerson();
        String username = user.getPersonUserIdentifier();
        KraAuthorizationService kraAuthorizationService = KraServiceLocator.getService(KraAuthorizationService.class);
        return kraAuthorizationService.hasPermission(username, doc, permissionName);
    }
    
    /**
     * Does the current user have the given role for the proposal?
     * @param doc the Proposal Development Document
     * @param roleName the name of the role
     * @return true if user has role; otherwise false
     */
    protected boolean hasRole(ProposalDevelopmentDocument doc, String roleName) {
        UniversalUser user = (UniversalUser) GlobalVariables.getUserSession().getPerson();
        String username = user.getPersonUserIdentifier();
        KraAuthorizationService kraAuthorizationService = KraServiceLocator.getService(KraAuthorizationService.class);
        return kraAuthorizationService.hasRole(username, doc, roleName);
    }

    /**
     * Does the given user have the given permission for the proposal?
     * @param username the user's username
     * @param doc the Proposal Development Document
     * @param permissionName the name of the permission
     * @return true if user has permission; otherwise false
     */
    protected boolean hasPermission(String username, ProposalDevelopmentDocument doc, String permissionName) {
        KraAuthorizationService kraAuthorizationService = KraServiceLocator.getService(KraAuthorizationService.class);
        return kraAuthorizationService.hasPermission(username, doc, permissionName);
    }
    
    /**
     * Does the given user have the given role for the proposal?
     * @param username the user's username
     * @param doc the Proposal Development Document
     * @param roleName the name of the role
     * @return true if user has role; otherwise false
     */
    protected boolean hasRole(String username, ProposalDevelopmentDocument doc, String roleName) {
        KraAuthorizationService kraAuthorizationService = KraServiceLocator.getService(KraAuthorizationService.class);
        return kraAuthorizationService.hasRole(username, doc, roleName);   
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
     * <br/>
     * This method does not throw any Exceptions because we assert that if an Exception is caught here, then the 
     * BusinessObject represented by <code>boClass</code> is not valid.
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

            try {
                if (getBusinessObjectService().countMatching(boClass, fieldValues) > 0) {
                    retval = true;
                }
            }
            catch (Exception e) {
                // Read Javadoc for explanation
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
     * Gets the error reporter.
     * @return the error reporter
     */
    public ErrorReporter getErrorReporter() {
        return this.errorReporter;
    }
}
