/*
 * Copyright 2007 The Kuali Foundation.
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
package org.kuali.kra.proposaldevelopment.rules;

import static org.kuali.core.util.GlobalVariables.getAuditErrorMap;
import static org.kuali.kra.infrastructure.Constants.AUDIT_ERRORS;
import static org.kuali.kra.infrastructure.Constants.CREDIT_SPLIT_KEY;
import static org.kuali.kra.infrastructure.Constants.KEY_PERSONNEL_PAGE;
import static org.kuali.kra.infrastructure.Constants.KEY_PERSONNEL_PANEL_ANCHOR;
import static org.kuali.kra.infrastructure.Constants.KEY_PERSONNEL_PANEL_NAME;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_CREDIT_SPLIT_LOWBOUND;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_CREDIT_SPLIT_UPBOUND;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_TOTAL_CREDIT_SPLIT_UPBOUND;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.kuali.core.util.AuditCluster;
import org.kuali.core.util.AuditError;
import org.kuali.core.util.KualiDecimal;
import org.kuali.kra.proposaldevelopment.bo.CreditSplit;
import org.kuali.kra.proposaldevelopment.bo.CreditSplitable;
import org.kuali.kra.proposaldevelopment.bo.InvestigatorCreditType;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;

/**
 * Validates Credit Splits on a <code>{@link ProposalPerson}</code> and/or <code>{@link ProposalPersonUnit}</code> by
 * traversing the tree of <code>{@link ProposalPerson}</code> <code>{@link ProposalPersonUnit}</code> instances.
 *
 * @author $Author: lprzybyl $
 * @version $Revision: 1.5 $
 */
public class CreditSplitValidator {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(CreditSplitValidator.class);
    private static final KualiDecimal CREDIT_UPBOUND = new KualiDecimal(100.00);
    private static final KualiDecimal CREDIT_LOWBOUND = KualiDecimal.ZERO;
    
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
            LOG.info("validating credit type " + creditType.getDescription());
            if (creditType.addsToHundred()) {
                retval &= validate(document.getInvestigators(), creditType);
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
        
        DecimalHolder investigatorCreditTotal = new DecimalHolder(KualiDecimal.ZERO);

        retval &= validateCreditSplitable(investigators.iterator(), creditType, investigatorCreditTotal);
        LOG.info("Passed investigator validation " + retval);

        for (ProposalPerson investigator : investigators) {
            DecimalHolder unitCreditTotal = new DecimalHolder(KualiDecimal.ZERO);
            
            retval &= validateCreditSplitable(investigator.getUnits().iterator(), creditType, unitCreditTotal);
            LOG.info("Passed unit validation " + retval);
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
            if (CREDIT_UPBOUND.compareTo(greaterCummulative.getValue()) != 0) {
                addAuditError(ERROR_TOTAL_CREDIT_SPLIT_UPBOUND, creditType.getDescription());
                return false;
            }
            
            return true;
        }
        boolean retval = true;
        
        CreditSplitable splitable = splitable_it.next();
        LOG.info("Validating " + splitable.getName());
     
        DecimalHolder lesserCummulative = new DecimalHolder(KualiDecimal.ZERO);        
        retval &= validateCreditSplit(splitable.getCreditSplits().iterator(), creditType, lesserCummulative);
     
        greaterCummulative.add(lesserCummulative);
             
        return retval & validateCreditSplitable(splitable_it, creditType, greaterCummulative);
    }


    /**
     * Validates a collection of anything splits. 
     * 
     * @param creditSplit_it
     * @param creditType
     * @param lesserCummulative
     * @return boolean is valid?
     */
    public boolean validateCreditSplit(Iterator<? extends CreditSplit> creditSplit_it, InvestigatorCreditType creditType, DecimalHolder lesserCummulative) {
        if (!creditSplit_it.hasNext()) {
            return true;                
        }
        
        boolean retval = true;
        
        CreditSplit creditSplit = creditSplit_it.next();
        if (creditType.getInvCreditTypeCode().equals(creditSplit.getInvCreditTypeCode())) {
            lesserCummulative.add(creditSplit.getCredit());
            
            // Validate that the current credit split isn't greater than 100% or less than 0%
            if (CREDIT_UPBOUND.compareTo(creditSplit.getCredit()) < 0) {                
                retval = false;
                addAuditError(ERROR_CREDIT_SPLIT_UPBOUND, creditType.getDescription());
            }
            else if (CREDIT_LOWBOUND.compareTo(creditSplit.getCredit()) > 0) {               
                retval = false;
                addAuditError(ERROR_CREDIT_SPLIT_LOWBOUND, creditType.getDescription());
            }
            
            // Found the credit split we're looking for, so now we return
            return retval;
        }
     
        return validateCreditSplit(creditSplit_it, creditType, lesserCummulative);
    }
    
    /**
     * This method should only be called if an audit error is intending to be added because it will actually add a <code>{@link List<AuditError>}</code>
     * to the auditErrorMap.
     * 
     * @return List of AuditError instances
     */
    private List<AuditError> getAuditErrors() {
        List<AuditError> auditErrors = auditErrors = new ArrayList<AuditError>();
        
        if (!getAuditErrorMap().containsKey("keyPersonnelAuditErrors")) {
            getAuditErrorMap().put("keyPersonnelAuditErrors", new AuditCluster(KEY_PERSONNEL_PANEL_NAME, auditErrors, AUDIT_ERRORS));
        }
        else {
            auditErrors = ((AuditCluster) getAuditErrorMap().get("keyPersonnelAuditErrors")).getAuditErrorList();
        }
        
        return auditErrors;
    }
    
    /**
     * Delegates to <code>{@link #addAuditError(String, String...)}</code>
     * 
     * Convenience method for adding an <code>{@link AuditError}</code> with just a <code>messageKey</code>.<br/>
     * <br/>
     * The <code>{@link AuditError}</code> that is added is.<br/>
     * <code>CREDIT_SPLIT_KEY, messageKey, KEY_PERSONNEL_PAGE + "." + KEY_PERSONNEL_PANEL_ANCHOR</code>
     * 
     * @param messageKey
     * @see CreditSplitAuditError
     * @see AuditError
     * @see GlobalVariables#getAuditErrorMap()
     * @see #addAuditError(String, String...)
     */
    private void addAuditError(String messageKey) {
        addAuditError(messageKey, null);
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
     * @see GlobalVariables#getAuditErrorMap()
     */
    private void addAuditError(String messageKey, String ... params) {
        AuditError error = new CreditSplitAuditError(messageKey, params);
        
        if (!getAuditErrors().contains(error)) {
            getAuditErrors().add(error);
            LOG.info("Adding " + messageKey + " error");
        }
    }
    
    private KeyPersonnelService getKeyPersonnelService() {
        return getService(KeyPersonnelService.class);
    }
    
    /**
     * A class for holding a <code>{@link KualiDecimal}</code> instance. There is no way to add to
     * or modify the value of a <code>{@link KualiDecimal}</code> without changing its reference; therefore,
     * pointing to a new instance. This causes a problem where a <code>{@link KualiDecimal}</code> instance
     * is used in a memento pattern.<br/>
     * <br/>
     * <code>{@link DecimalHolder}</code> is created to handle that case. <code>{@link DecimalHolder}</code> becomes
     * the memento for a changing <code>{@link KualiDecimal}</code> instance.
     * 
     * @see KualiDecimal
     */
    final class DecimalHolder implements Comparable<DecimalHolder> {
        private KualiDecimal value;
        
        /**
         * Create a <code>{@link DecimalHolder}</code> from a <code>{@link KualiDecimal}</code>.
         * 
         * @param val a <code>{@link KualiDecimal}</code> instance
         */
        public DecimalHolder(KualiDecimal val) {
            value = val;
        }

        /**
         * Get the contained <code>{@link KualiDecimal}</code> instance.
         * 
         * @return KualiDecimal
         */
        public KualiDecimal getValue() {
            return value;
        }

        public void setValue(KualiDecimal value) {
            this.value = value;
        }
        
        
        public void add(KualiDecimal val) {
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
}

