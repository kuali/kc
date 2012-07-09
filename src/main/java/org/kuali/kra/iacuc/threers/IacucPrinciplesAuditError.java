
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
package org.kuali.kra.iacuc.threers;

import static org.kuali.kra.infrastructure.Constants.AUDIT_ERRORS;

import java.util.ArrayList;
import java.util.List;

import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;

@SuppressWarnings("deprecation")
public class IacucPrinciplesAuditError extends AuditError {
    
    public static final String PRINCIPLES_PANEL_NAME = "threeRs";
    public static final String PRINCIPLES_CLUSTER_NAME = "alternateSearchAuditErrors";
    public static final String PRINCIPLES_ANCHOR_NAME = "Alternate Search Audit Errors";

    /**
     * 
     * @param messageKey to be delegated to <code>{@link AuditError}</code> superclass
     * @param params varargs array of parameters for the messagekey
     */
    public IacucPrinciplesAuditError(String errorKey, String messageKey, String ... params) {
        super(errorKey, messageKey, PRINCIPLES_PANEL_NAME + "." + PRINCIPLES_ANCHOR_NAME, params);
    }

    /**
     * This method should only be called if an audit error is intending to be added because it will actually add a <code>{@link List<AuditError>}</code>
     * to the auditErrorMap.
     * 
     * @return List of AuditError instances
     */
    private static List<AuditError> getAuditErrors() {
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        if (!KNSGlobalVariables.getAuditErrorMap().containsKey(PRINCIPLES_CLUSTER_NAME)) {
            KNSGlobalVariables.getAuditErrorMap().put(PRINCIPLES_CLUSTER_NAME, new AuditCluster(PRINCIPLES_PANEL_NAME, auditErrors, AUDIT_ERRORS));
        }
        else {
            auditErrors = ((AuditCluster)KNSGlobalVariables.getAuditErrorMap().get(PRINCIPLES_CLUSTER_NAME)).getAuditErrorList();
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
    public static void addAuditError(String errorKey, String messageKey, String ... params) {
        AuditError error = new IacucPrinciplesAuditError(errorKey, messageKey, params);

        if (!getAuditErrors().contains(error)) {
            getAuditErrors().add(error);
        }
    }

}
