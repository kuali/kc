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
package org.kuali.kra.iacuc.actions.submit;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.threers.IacucPrinciplesAuditError;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.protocol.ProtocolDocument;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewType;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmitAction;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmitActionRule;

/**
 * Validate a protocol submission to the IRB for review.
 */
/**
 * This class...
 */
public class IacucProtocolSubmitActionRule extends ProtocolSubmitActionRule {

    private final String PROTOCOL_ALT_SEARCH_REQUIRED_PROPERTY_KEY = "document.protocolList[0].iacucPrinciples[0].searchRequired";
    private final String PROTOCOL_ALT_SEARCH_PROPERTY_KEY = "iacucAlternateSearchHelper.newAlternateSearch.databases";

    
    /**
     * @see org.kuali.kra.irb.actions.submit.ExecuteProtocolSubmitActionRule#processSubmitAction(org.kuali.kra.iacuc.IacucProtocolDocument,
     *      org.kuali.kra.irb.actions.submit.ProtocolSubmitAction)
     */
    @Override
    public boolean processSubmitAction(ProtocolDocument document, ProtocolSubmitAction submitAction) {

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
        if (StringUtils.isBlank(searchRequired)) {
            IacucPrinciplesAuditError.addAuditError(PROTOCOL_ALT_SEARCH_REQUIRED_PROPERTY_KEY, KeyConstants.IACUC_PROTOCOL_ALT_SEARCH_QUESTION_NOT_ANSWERED);
            reportError(PROTOCOL_ALT_SEARCH_REQUIRED_PROPERTY_KEY, KeyConstants.IACUC_PROTOCOL_ALT_SEARCH_QUESTION_NOT_ANSWERED);
            return false;
        } else if (StringUtils.equals("Y", searchRequired) && protocol.getIacucAlternateSearches().isEmpty()) {
            IacucPrinciplesAuditError.addAuditError(PROTOCOL_ALT_SEARCH_PROPERTY_KEY, KeyConstants.IACUC_PROTOCOL_ALT_SEARCH_DATA_NOT_ENTERED);
            reportError(PROTOCOL_ALT_SEARCH_PROPERTY_KEY, KeyConstants.IACUC_PROTOCOL_ALT_SEARCH_DATA_NOT_ENTERED);
            return false;
        }
        return true;
    }

    @Override
    protected Class<? extends ProtocolSubmissionType> getProtocolSubmissionTypeClassHook() {
        return IacucProtocolSubmissionType.class;
    }

    @Override
    protected Class<? extends ProtocolReviewType> getProtocolReviewTypeClassHook() {
        return IacucProtocolReviewType.class;
    }

    @Override
    protected Class<? extends ProtocolDocument> getProtocolDocumentClassHook() {
        return IacucProtocolDocument.class;
    }


}
