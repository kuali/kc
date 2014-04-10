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
package org.kuali.kra.proposaldevelopment.rules;

import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.*;
import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;

import java.lang.reflect.Method;
import java.util.*;

import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;
import static org.kuali.kra.infrastructure.Constants.*;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_CREDIT_SPLIT_UPBOUND;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_TOTAL_CREDIT_SPLIT_UPBOUND;

/**
 * Validates Credit Splits on a <code>{@link org.kuali.coeus.propdev.impl.person.ProposalPerson}</code> and/or <code>{@link org.kuali.coeus.propdev.impl.person.ProposalPersonUnit}</code> by
 * traversing the tree of <code>{@link org.kuali.coeus.propdev.impl.person.ProposalPerson}</code> <code>{@link org.kuali.coeus.propdev.impl.person.ProposalPersonUnit}</code> instances.
 *
 * @author $Author: gmcgrego $
 * @version $Revision: 1.11 $
 */
public class CreditSplitValidator {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(CreditSplitValidator.class);
    private static final ScaleTwoDecimal CREDIT_UPBOUND = new ScaleTwoDecimal(100.00);
    private static final ScaleTwoDecimal CREDIT_LOWBOUND = ScaleTwoDecimal.ZERO;
    
    private static final String VALIDATING_MESSAGE = "Validating ";
    private static final String VALIDATING_CT_MESSAGE = "Validating credit type ";
    private static final String UNIT_VALIDATION_MESSAGE = "Unit validation passed ";
    private static final String INV_VALIDATION_MESSAGE = "Investigator validation passed ";
    private static final String AUDIT_ADDITION_MESSAGE_1 = "Adding " ;
    private static final String AUDIT_ADDITION_MESSAGE_2 = " audit error.";
        
    /**
     * Validates the credit splits of an entire document by traversing it. If the Investigator is instead a Principal Investigator,
     * the units should all add up to 100.0.
     *
     * @param document The document to validate the credit splits of
     * @return boolean
     */
    public boolean validate(ProposalDevelopmentDocument document) {
        Collection<InvestigatorCreditType> creditTypes = getKeyPersonnelService().getInvestigatorCreditTypes();
        boolean retval = true;
        
        for (InvestigatorCreditType creditType : creditTypes) {
            LOG.info(VALIDATING_CT_MESSAGE + creditType.getDescription());
            if (creditType.addsToHundred()) {
                retval &= validate(document.getDevelopmentProposal().getInvestigators(), creditType);
            }
        }


        return retval;
    }
    
    /**
     * Validates credit splits of all investigators in a <code>{@link ProposalDevelopmentDocument}</code>. Takes a 
     * <code>{@link Collection}</code> of investigators for a given credit type, and validates credit splits 
     * for each investigator as well as iterating and validating credit splits for each unit belonging to an 
     * investigator.
     * 
     * @param investigators
     * @param creditTypeCode
     * @return true if the investigator collection is valid for the credit type, and false if it's invalid
     */
    public boolean validate(Collection<ProposalPerson> investigators, InvestigatorCreditType creditType) {
        boolean retval = true;
        
        DecimalHolder investigatorCreditTotal = new DecimalHolder(ScaleTwoDecimal.ZERO);

        if (!validateCreditSplitable(investigators.iterator(), creditType, investigatorCreditTotal)) {
            addAuditError(ERROR_TOTAL_CREDIT_SPLIT_UPBOUND, creditType.getDescription());                    
            retval = false;
        }

        LOG.info(INV_VALIDATION_MESSAGE + retval);

        for (ProposalPerson investigator : investigators) {
            DecimalHolder unitCreditTotal = new DecimalHolder(ScaleTwoDecimal.ZERO);
            
            if (!validateCreditSplitable(investigator.getUnits().iterator(), creditType, unitCreditTotal)) {
                addAuditError(ERROR_CREDIT_SPLIT_UPBOUND, creditType.getDescription(), getCreditSplitableName(investigator));
                retval = false;
            }

            LOG.info(UNIT_VALIDATION_MESSAGE + retval);
        }
        
        
        return retval;
    }

    /**
     * Validates a collection of anything splitable. This implies that it contains <code>{@link CreditSplit}</code> instances.
     * 
     * @param splitable_it
     * @param creditType
     * @param greaterCummulative
     * @return boolean is valid?
     */
    public boolean validateCreditSplitable(Iterator<? extends CreditSplitable> splitable_it, InvestigatorCreditType creditType, DecimalHolder greaterCummulative) {
        if (!splitable_it.hasNext()) {
            return isCreditSplitTotalValid(greaterCummulative.getValue());
        }
        boolean retval = true;
        
        CreditSplitable splitable = splitable_it.next();
        LOG.info(VALIDATING_MESSAGE + getCreditSplitableName(splitable));
     
        DecimalHolder lesserCummulative = new DecimalHolder(ScaleTwoDecimal.ZERO);
        retval &= validateCreditSplit(splitable.getCreditSplits().iterator(), creditType, lesserCummulative);
     
        greaterCummulative.add(lesserCummulative);
             
        return retval & validateCreditSplitable(splitable_it, creditType, greaterCummulative);
    }
    
    /**
     * Determines if the total credit split value for a {@link CreditSplitable} instance is valid or not. The upper and lower bounds for {@link CreditSplit} are 100.00 and 0.00.
     * 0.00 is used as the lower bound and is significant because this is where {@link CreditSplit} is initiated. This is valid. 100.00 is the upper bound and represents an 
     * adequate split of credit. Anything other than these is not considered valid 
     *
     * @param total value of the credit split
     * @return <code>false</code> if the credit split total is anything other than 100.00 or 0.00; otherwise, return <code>true</code> 
     */
    private boolean isCreditSplitTotalValid(ScaleTwoDecimal total) {
        return (CREDIT_UPBOUND.compareTo(total) == 0 || CREDIT_LOWBOUND.compareTo(total) > 0);
    }


    /**
     * Validates a collection of anything splits. Negative values and values exceeding 100.00 are not permissible. 
     * 
     * @param creditSplit_it
     * @param creditType
     * @param lesserCummulative
     * @return boolean <code>true</code> if it is a valid percentage (falls between 0.00 and 100.00)
     */
    public boolean validateCreditSplit(Iterator<? extends CreditSplit> creditSplit_it, InvestigatorCreditType creditType, DecimalHolder lesserCummulative) {
        if (!creditSplit_it.hasNext()) {
            return false;                
        }
        
        CreditSplit creditSplit = creditSplit_it.next();
        if (creditType.getInvCreditTypeCode().equals(creditSplit.getInvCreditTypeCode())) {
            lesserCummulative.add(creditSplit.getCredit());
            LOG.info("Credit split is " + creditSplit.getCredit());
            return isCreditSplitValid(creditSplit.getCredit());
        }
     
        return validateCreditSplit(creditSplit_it, creditType, lesserCummulative);
    }
    
    /**
     * Determine if the value of the credit split is valid. Values not between 0.00 and 100.00 percent are considered invalid.
     * 
     * @param value of the credit split to validate
     * @return <code>false</code> if negative or greater than 100.00
     */
    protected boolean isCreditSplitValid(ScaleTwoDecimal value) {
        boolean retval = true;
        
        // Validate that the current credit split isn't greater than 100% or less than 0%
        if (CREDIT_UPBOUND.compareTo(value) < 0) {                
            retval = false;
        }
        else if (CREDIT_LOWBOUND.compareTo(value) > 0) {               
            retval = false;
        }
       
        return retval;
    }
    
    /**
     * This method should only be called if an audit error is intending to be added because it will actually add a <code>{@link List<AuditError>}</code>
     * to the auditErrorMap.
     * 
     * @return List of AuditError instances
     */
    private List<AuditError> getAuditErrors() {
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        
        if (!KNSGlobalVariables.getAuditErrorMap().containsKey("keyPersonnelAuditErrors")) {
           KNSGlobalVariables.getAuditErrorMap().put("keyPersonnelAuditErrors", new AuditCluster(KEY_PERSONNEL_PANEL_NAME, auditErrors, AUDIT_ERRORS));
        }
        else {
            auditErrors = ((AuditCluster)KNSGlobalVariables.getAuditErrorMap().get("keyPersonnelAuditErrors")).getAuditErrorList();
        }
        
        return auditErrors;
    }

    /**
     * Convenience method for adding an <code>{@link AuditError}</code> with just a <code>messageKey</code>.<br/>
     * <br/>
     * The <code>{@link AuditError}</code> that is added is.<br/>
     * <code>CREDIT_SPLIT_KEY, messageKey, KEY_PERSONNEL_PAGE + "." + KEY_PERSONNEL_PANEL_ANCHOR</code>
     * 
     * @param messageKey
     * @see CreditSplitAuditError
     * @see AuditError
     * @see KNSGlobalVariables.getAuditErrorMap()
     */
    private void addAuditError(String messageKey, String ... params) {
        AuditError error = new CreditSplitAuditError(messageKey, params);
        
        if (!getAuditErrors().contains(error)) {
            getAuditErrors().add(error);
            LOG.info(AUDIT_ADDITION_MESSAGE_1 + messageKey + AUDIT_ADDITION_MESSAGE_2);
        }
    }
    
    private KeyPersonnelService getKeyPersonnelService() {
        return getService(KeyPersonnelService.class);
    }
    
    /**
     * A class for holding a <code>{@link ScaleTwoDecimal}</code> instance. There is no way to add to
     * or modify the value of a <code>{@link ScaleTwoDecimal}</code> without changing its reference; therefore,
     * pointing to a new instance. This causes a problem where a <code>{@link ScaleTwoDecimal}</code> instance
     * is used in a memento pattern.<br/>
     * <br/>
     * <code>{@link DecimalHolder}</code> is created to handle that case. <code>{@link DecimalHolder}</code> becomes
     * the memento for a changing <code>{@link ScaleTwoDecimal}</code> instance.
     * 
     * @see ScaleTwoDecimal
     */
    final class DecimalHolder implements Comparable<DecimalHolder> {
        private ScaleTwoDecimal value;
        
        /**
         * Create a <code>{@link DecimalHolder}</code> from a <code>{@link ScaleTwoDecimal}</code>.
         * 
         * @param val a <code>{@link ScaleTwoDecimal}</code> instance
         */
        public DecimalHolder(ScaleTwoDecimal val) {
            value = val;
        }

        /**
         * Get the contained <code>{@link ScaleTwoDecimal}</code> instance.
         * 
         * @return ScaleTwoDecimal
         */
        public ScaleTwoDecimal getValue() {
            return value;
        }

        public void setValue(ScaleTwoDecimal value) {
            this.value = value;
        }
        
        
        public void add(ScaleTwoDecimal val) {
            value = value.add(val);
        }
        
        public void add(DecimalHolder val) {
            value = value.add(val.getValue());
        }

        /**
         * @see java.lang.Comparable#compareTo(java.lang.Object)
         */
        public int compareTo(DecimalHolder obj) {
            return value.compareTo(obj.getValue());
        }
        
        /**
         * @see java.lang.Object#toString()
         */
        public String toString() {
            return value.toString();
        }
    }

    /**
     * Mock inherited <code>{@link AuditError}</code> class that allows comparisons of <code>{@link AuditError}</code> objects for
     * credit split.
     */
    final class CreditSplitAuditError extends AuditError {
        
        /**
         * 
         * @param messageKey to be delegated to <code>{@link AuditError}</code> superclass
         * @param params varargs array of parameters for the messagekey
         */
        public CreditSplitAuditError(String messageKey) {
            this(messageKey, null);
        }
        
        /**
         * 
         * @param messageKey to be delegated to <code>{@link AuditError}</code> superclass
         * @param params varargs array of parameters for the messagekey
         */
        public CreditSplitAuditError(String messageKey, String ... params) {
            super(CREDIT_SPLIT_KEY, messageKey, KEY_PERSONNEL_PAGE + "." + KEY_PERSONNEL_PANEL_ANCHOR, params);
        }
        
        /**
         * @see java.lang.Object#equals(java.lang.Object)
         */
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            
            AuditError error = (AuditError) obj;
            boolean retval = true;
            
            retval &= getErrorKey().equals(error.getErrorKey());
            retval &= getMessageKey().equals(error.getMessageKey());
            retval &= getLink().equals(error.getLink());
            
            retval &= Arrays.equals(getParams(), error.getParams());
            
            return retval;
        }
    }
    
    /**
     * Discover the name of a {@link CreditSplitable}. Not all {@link CreditSplitable} instances will have a <code>name</code> property. Even if they
     * did, it's not likely for all the properties to be called <code>name</code>. {@link CreditSplitable} relies on a property to be annotated
     * as being the name of the {@link CreditSplitable}. This checks for that annotation and returns the name. 
     * 
     * 
     * @param splitable 
     * @return <code>null</code> if the name could not be found or if the value of the name is also <code>null</code>; otherwise, the name is returned.
     */
    private String getCreditSplitableName(CreditSplitable splitable) {
        
        for (Method method : splitable.getClass().getMethods()) {
            if (method.isAnnotationPresent(CreditSplitNameInfo.class)) {
                LOG.info("Found method name " + method.getName());
                try {
                    return (String) method.invoke(splitable, null);
                } 
                catch (Exception e) {
                    LOG.warn("Could not find the name property for the credit splitable object of class " + splitable.getClass().getName()
                            + ". Make sure the " + CreditSplitNameInfo.class.getSimpleName() + " annotation is declared on the name property of " 
                                + splitable.getClass().getSimpleName());

                }
            }
        }
        
        return null;
    }
}

