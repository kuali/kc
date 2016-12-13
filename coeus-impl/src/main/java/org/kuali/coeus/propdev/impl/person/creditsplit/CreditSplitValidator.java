/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.propdev.impl.person.creditsplit;

import org.kuali.coeus.common.framework.type.InvestigatorCreditType;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.KeyPersonnelService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.*;

import static org.kuali.coeus.propdev.impl.datavalidation.ProposalDevelopmentDataValidationConstants.*;
import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;

import static org.kuali.kra.infrastructure.KeyConstants.ERROR_CREDIT_SPLIT_UPBOUND;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_TOTAL_CREDIT_SPLIT_UPBOUND;

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
     */
    public boolean validate(ProposalDevelopmentDocument document) {
        Collection<InvestigatorCreditType> creditTypes = getKeyPersonnelService().getInvestigatorCreditTypes();
        boolean retval = true;

        final List<ProposalPerson> investigators = document.getDevelopmentProposal().getPersonsSelectedForCreditSplit();

        if (!investigators.isEmpty()) {
            for (InvestigatorCreditType creditType : creditTypes) {
                LOG.info(VALIDATING_CT_MESSAGE + creditType.getDescription());
                if (creditType.addsToHundred()) {
                    retval &= validate(investigators, creditType);
                }
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
     * Validates a collection of anything splitable. This implies that it contains <code>{@link org.kuali.coeus.propdev.impl.person.creditsplit.CreditSplit}</code> instances.
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
     * 0.00 is used as the lower bound and is significant because this is where {@link org.kuali.coeus.propdev.impl.person.creditsplit.CreditSplit} is initiated. This is valid. 100.00 is the upper bound and represents an
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
     * @return boolean <code>true</code> if it is a valid percentage (falls between 0.00 and 100.00)
     */
    public boolean validateCreditSplit(Iterator<? extends CreditSplit> creditSplit_it, InvestigatorCreditType creditType, DecimalHolder lesserCummulative) {
        if (!creditSplit_it.hasNext()) {
            return false;                
        }
        
        CreditSplit creditSplit = creditSplit_it.next();
        if (creditType.getCode().equals(creditSplit.getInvCreditTypeCode())) {
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
     * This method should only be called if an audit error is intending to be added because it will actually add a <code>{@link List&lt;AuditError&gt;}</code>
     * to the auditErrorMap.
     * 
     * @return List of AuditError instances
     */
    private List<AuditError> getAuditErrors() {
        List<AuditError> auditErrors = new ArrayList<>();
        
        if (!GlobalVariables.getAuditErrorMap().containsKey(CREDIT_ALLOCATION_PAGE_NAME)) {
           GlobalVariables.getAuditErrorMap().put(CREDIT_ALLOCATION_PAGE_NAME, new AuditCluster(CREDIT_ALLOCATION_PAGE_NAME, auditErrors, AUDIT_ERRORS));
        }
        else {
            auditErrors = GlobalVariables.getAuditErrorMap().get(CREDIT_ALLOCATION_PAGE_NAME).getAuditErrorList();
        }
        
        return auditErrors;
    }

    /**
     * Convenience method for adding an <code>{@link AuditError}</code> with just a <code>messageKey</code>.
     *
     * The <code>{@link AuditError}</code> that is added is.
     * <code>CREDIT_SPLIT_KEY, messageKey, KEY_PERSONNEL_PAGE + "." + KEY_PERSONNEL_PANEL_ANCHOR</code>
     *
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
     * is used in a memento pattern.
     *
     * <code>{@link DecimalHolder}</code> is created to handle that case. <code>{@link DecimalHolder}</code> becomes
     * the memento for a changing <code>{@link ScaleTwoDecimal}</code> instance.
     * 
     * @see ScaleTwoDecimal
     */
    final class DecimalHolder implements Comparable<DecimalHolder> {
        private ScaleTwoDecimal value;

        public DecimalHolder(ScaleTwoDecimal val) {
            value = val;
        }

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

        @Override
        public int compareTo(DecimalHolder obj) {
            return value.compareTo(obj.getValue());
        }

        @Override
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
        public CreditSplitAuditError(String messageKey, String ... params) {
            super(CREDIT_ALLOCATION_PAGE_ID, messageKey, CREDIT_ALLOCATION_PAGE_ID, params);
        }
        
        @Override
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
     * did, it's not likely for all the properties to be called <code>name</code>. {@link CreditSplitable} relies on a subinterface called
     * {@link NamedCreditSplitable} to retrieve the name.
     *
     * @param splitable the splittable instance
     * @return <code>null</code> if the name could not be found or if the value of the name is also <code>null</code>; otherwise, the name is returned.
     */
    protected String getCreditSplitableName(CreditSplitable splitable) {
        if (splitable instanceof  NamedCreditSplitable) {
            return ((NamedCreditSplitable) splitable).getCreditSplitName();
        }
        
        return null;
    }
}

