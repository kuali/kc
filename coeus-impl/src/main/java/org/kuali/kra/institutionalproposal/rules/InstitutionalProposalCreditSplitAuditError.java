/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.institutionalproposal.rules;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.kuali.kra.infrastructure.Constants.*;
import static org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPersonCreditSplitRule.PROPOSAL_CREDIT_SPLIT_LIST_ERROR_KEY;

public class InstitutionalProposalCreditSplitAuditError extends AuditError {

    private static final Log LOG = LogFactory.getLog(InstitutionalProposalCreditSplitAuditError.class);

    /**
     * 
     * @param messageKey to be delegated to <code>{@link AuditError}</code> superclass
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
    
    /**
     * This method should only be called if an audit error is intending to be added because it will actually add a <code>{@link List&lt;AuditError&gt;}</code>
     * to the auditErrorMap.
     * 
     * @return List of AuditError instances
     */
    private static List<AuditError> getAuditErrors() {
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        if (!GlobalVariables.getAuditErrorMap().containsKey("contactsAuditErrors")) {
            GlobalVariables.getAuditErrorMap().put("contactsAuditErrors", new AuditCluster(CONTACTS_PANEL_NAME, auditErrors, AUDIT_ERRORS));
        }
        else {
            auditErrors = ((AuditCluster)GlobalVariables.getAuditErrorMap().get("contactsAuditErrors")).getAuditErrorList();
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
    public static void addAuditError(String messageKey, String ... params) {
        AuditError error = new InstitutionalProposalCreditSplitAuditError(messageKey, params);
        
        if (!getAuditErrors().contains(error)) {
            getAuditErrors().add(error);
            LOG.info("Adding audit error " + messageKey + " to audit list.");
        }
    }

}
