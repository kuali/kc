/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.institutionalproposal.rules;

import static org.kuali.kra.infrastructure.Constants.AUDIT_ERRORS;
import static org.kuali.kra.infrastructure.Constants.CONTACTS_PANEL_ANCHOR;
import static org.kuali.kra.infrastructure.Constants.CONTACTS_PANEL_NAME;
import static org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPersonCreditSplitRule.PROPOSAL_CREDIT_SPLIT_LIST_ERROR_KEY;
import static org.kuali.kra.logging.BufferedLogger.info;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;

public class InstitutionalProposalCreditSplitAuditError extends AuditError {
        
    /**
     * 
     * @param messageKey to be delegated to <code>{@link AuditError}</code> superclass
     * @param params varargs array of parameters for the messagekey
     */
    public InstitutionalProposalCreditSplitAuditError(String messageKey) {
        this(messageKey, null);
    }
        
    /**
     * 
     * @param messageKey to be delegated to <code>{@link AuditError}</code> superclass
     * @param params varargs array of parameters for the messagekey
     */
    public InstitutionalProposalCreditSplitAuditError(String messageKey, String ... params) {
        super(PROPOSAL_CREDIT_SPLIT_LIST_ERROR_KEY, messageKey, CONTACTS_PANEL_NAME + "." + CONTACTS_PANEL_ANCHOR, params);
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
    
    /**
     * This method should only be called if an audit error is intending to be added because it will actually add a <code>{@link List<AuditError>}</code>
     * to the auditErrorMap.
     * 
     * @return List of AuditError instances
     */
    private static List<AuditError> getAuditErrors() {
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        if (!KNSGlobalVariables.getAuditErrorMap().containsKey("contactsAuditErrors")) {
            KNSGlobalVariables.getAuditErrorMap().put("contactsAuditErrors", new AuditCluster(CONTACTS_PANEL_NAME, auditErrors, AUDIT_ERRORS));
        }
        else {
            auditErrors = ((AuditCluster)KNSGlobalVariables.getAuditErrorMap().get("contactsAuditErrors")).getAuditErrorList();
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
    public static void addAuditError(String messageKey, String ... params) {
        AuditError error = new InstitutionalProposalCreditSplitAuditError(messageKey, params);
        
        if (!getAuditErrors().contains(error)) {
            getAuditErrors().add(error);
            info("Adding audit error ", messageKey, " to audit list.");
        }
    }

}
