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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.keyvalue.DefaultMapEntry;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.core.bo.user.UniversalUser;
import org.kuali.core.document.Document;
import org.kuali.core.rule.DocumentAuditRule;
import org.kuali.core.rules.DocumentRuleBase;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.DictionaryValidationService;
import org.kuali.core.util.AuditCluster;
import org.kuali.core.util.AuditError;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.ExceptionUtils;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.budget.bo.BudgetVersionOverview;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService;

import edu.emory.mathcs.backport.java.util.AbstractMap.SimpleEntry;
import edu.emory.mathcs.backport.java.util.AbstractMap.SimpleImmutableEntry;

/**
 * Base implementation class for KRA document business rules
 *
 * @author $Author: gmcgrego $
 * @version $Revision: 1.13 $
 */
public abstract class ResearchDocumentRuleBase extends DocumentRuleBase implements DocumentAuditRule {

    private static final Log LOG = LogFactory.getLog(ResearchDocumentRuleBase.class);

    /**
     * Wrapper around global errorMap.put call, to allow better logging
     * 
     * @param propertyName
     * @param errorKey
     * @param errorParams
     */
    protected void reportError(String propertyName, String errorKey, String... errorParams) {
        LOG.debug("reportError(String, String, String) - start");

        GlobalVariables.getErrorMap().putError(propertyName, errorKey, errorParams);
        if (LOG.isDebugEnabled()) {
            LOG.debug("rule failure at " + ExceptionUtils.describeStackLevels(1, 2));
        }
    }
    
    /**
     * Adds an audit error to the
     * {@link GlobalVariables#getAuditErrorMap() GlobalVariables#getAuditErrorMap()}.
     * 
     * @param error the error to add.
     * @param errorKey the error map key
     * @param clusterLabel the cluster label
     * @param clusterCategory the cluster category
     * @throws IllegalArgumentException if error, errorKey, clusterLabel, or clusterCategory are null or
     * if errorKey, clusterLabel, or clusterCategory are whitespace
     */
    protected void addAuditError(AuditError error, String errorKey, String clusterLabel, String clusterCategory) {
        if (error == null || StringUtils.isBlank(errorKey)
            || StringUtils.isBlank(clusterLabel) || StringUtils.isBlank(clusterCategory)) {
            throw new IllegalArgumentException(new StringBuilder("null argument error: ")
                .append(error)
                .append(" errorkey: ")
                .append(errorKey)
                .append(" clusterLabel: ")
                .append(clusterLabel)
                .append(" clusterCategory: ")
                .append(clusterCategory).toString());
        }
        
        @SuppressWarnings("unchecked")
        final Map<String, AuditCluster> errorMap = GlobalVariables.getAuditErrorMap();
        
        AuditCluster cluster = errorMap.get(errorKey);
        
        if (cluster == null) {
            cluster = new AuditCluster(clusterLabel, new ArrayList<AuditError>(), clusterCategory);
            errorMap.put(errorKey, cluster);
        }
        
        @SuppressWarnings("unchecked")
        final Collection<AuditError> errors = cluster.getAuditErrorList();
        errors.add(error);
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
        for (BudgetVersionOverview budgetVersion: proposalDevelopmentDocument.getBudgetVersionOverviews()) {
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
        UniversalUser user = GlobalVariables.getUserSession().getUniversalUser();
        String username = user.getPersonUserIdentifier();
        ProposalAuthorizationService proposalAuthService = KraServiceLocator.getService(ProposalAuthorizationService.class);
        return proposalAuthService.hasPermission(username, doc, permissionName);
    }
    
    /**
     * Does the current user have the given role for the proposal?
     * @param doc the Proposal Development Document
     * @param roleName the name of the role
     * @return true if user has role; otherwise false
     */
    protected boolean hasRole(ProposalDevelopmentDocument doc, String roleName) {
        UniversalUser user = GlobalVariables.getUserSession().getUniversalUser();
        String username = user.getPersonUserIdentifier();
        ProposalAuthorizationService proposalAuthService = KraServiceLocator.getService(ProposalAuthorizationService.class);
        return proposalAuthService.hasRole(username, doc, roleName);
    }

    /**
     * Does the given user have the given permission for the proposal?
     * @param username the user's username
     * @param doc the Proposal Development Document
     * @param permissionName the name of the permission
     * @return true if user has permission; otherwise false
     */
    protected boolean hasPermission(String username, ProposalDevelopmentDocument doc, String permissionName) {
        ProposalAuthorizationService proposalAuthService = KraServiceLocator.getService(ProposalAuthorizationService.class);
        return proposalAuthService.hasPermission(username, doc, permissionName);
    }
    
    /**
     * Does the given user have the given role for the proposal?
     * @param username the user's username
     * @param doc the Proposal Development Document
     * @param roleName the name of the role
     * @return true if user has role; otherwise false
     */
    protected boolean hasRole(String username, ProposalDevelopmentDocument doc, String roleName) {
        ProposalAuthorizationService proposalAuthService = KraServiceLocator.getService(ProposalAuthorizationService.class);
        return proposalAuthService.hasRole(username, doc, roleName);   
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
        return KraServiceLocator.getService(BusinessObjectService.class);
    }
}
