/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.iacuc.actions.submit;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewTypeBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionTypeBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmitAction;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmitActionRuleBase;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;

import java.util.ArrayList;
import java.util.List;

import static org.kuali.kra.infrastructure.Constants.KNS_AUDIT_ERRORS;

/**
 * Validate a protocol submission to the IRB for review.
 */

@SuppressWarnings("deprecation")
public class IacucProtocolSubmitActionRule extends ProtocolSubmitActionRuleBase {

    private final String PROTOCOL_ALT_SEARCH_REQUIRED_PROPERTY_KEY = "document.protocolList[0].iacucPrinciples[0].searchRequired";
    private final String PROTOCOL_ALT_SEARCH_PROPERTY_KEY = "iacucAlternateSearchHelper.newAlternateSearch.databases";
    private static final String PRINCIPLES_PANEL_NAME = "threeRs";
    private static final String PRINCIPLES_CLUSTER_NAME = "alternateSearchAuditErrors";
    private static final String PRINCIPLES_ANCHOR_NAME = "Alternate Search";

    
    /**
     * @see org.kuali.kra.irb.actions.submit.ExecuteProtocolSubmitActionRule#processSubmitAction(org.kuali.kra.iacuc.IacucProtocolDocument,
     *      org.kuali.kra.irb.actions.submit.ProtocolSubmitAction)
     */
    @Override
    public boolean processSubmitAction(ProtocolDocumentBase document, ProtocolSubmitAction submitAction) {

        boolean isValid = super.processSubmitAction(document, submitAction);
        isValid &= validateThreeRs(submitAction);
        return isValid;
    }

    /**
     * If the user has indicated that pain is coming, then require alternate search
     */
    private boolean validateThreeRs(ProtocolSubmitAction submitAction) {
        IacucProtocol protocol = (IacucProtocol)submitAction.getProtocol();
        String searchRequired = protocol.getIacucPrinciples().get(0).getSearchRequired();
        List<AuditError> auditErrors = getAuditErrors();

        if (StringUtils.isBlank(searchRequired)) {
            auditErrors.add(new AuditError(PROTOCOL_ALT_SEARCH_REQUIRED_PROPERTY_KEY , KeyConstants.IACUC_PROTOCOL_ALT_SEARCH_QUESTION_NOT_ANSWERED, PRINCIPLES_PANEL_NAME + "." + PRINCIPLES_ANCHOR_NAME));
        } else if (StringUtils.equals("Y", searchRequired) && protocol.getIacucAlternateSearches().isEmpty()) {
            auditErrors.add(new AuditError(PROTOCOL_ALT_SEARCH_PROPERTY_KEY , KeyConstants.IACUC_PROTOCOL_ALT_SEARCH_DATA_NOT_ENTERED, PRINCIPLES_PANEL_NAME + "." + PRINCIPLES_ANCHOR_NAME));
        }
        if (auditErrors.size() > 0) {
            KNSGlobalVariables.getAuditErrorMap().put(PRINCIPLES_CLUSTER_NAME, new AuditCluster(PRINCIPLES_ANCHOR_NAME, auditErrors, KNS_AUDIT_ERRORS));
            return false;
        } else {
            return true;
        }
    }

    @Override
    protected Class<? extends ProtocolSubmissionTypeBase> getProtocolSubmissionTypeClassHook() {
        return IacucProtocolSubmissionType.class;
    }

    @Override
    protected Class<? extends ProtocolReviewTypeBase> getProtocolReviewTypeClassHook() {
        return IacucProtocolReviewType.class;
    }

    @Override
    protected Class<? extends ProtocolDocumentBase> getProtocolDocumentClassHook() {
        return IacucProtocolDocument.class;
    }

    private List<AuditError> getAuditErrors() {
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        
        if (!KNSGlobalVariables.getAuditErrorMap().containsKey(PRINCIPLES_CLUSTER_NAME)) {
            auditErrors = new ArrayList<AuditError>();
        }
        else {
            auditErrors = ((AuditCluster)KNSGlobalVariables.getAuditErrorMap().get(PRINCIPLES_CLUSTER_NAME)).getAuditErrorList();
        }        
        return auditErrors;
    }

}
