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
package org.kuali.kra.irb.actions.request;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * Business rule for a protocol request.  If the mandatory option has been
 * set in the system params, the committee must be selected.
 */
@SuppressWarnings("unchecked")
public class ProtocolRequestRule extends ResearchDocumentRuleBase implements BusinessRuleInterface<ProtocolRequestEvent> {
    
    private static final String MANDATORY = "M";

    /**
     * @see org.kuali.kra.rule.BusinessRuleInterface#processRules(org.kuali.kra.rule.event.KraDocumentEventBaseExtension)
     */
    public boolean processRules(ProtocolRequestEvent event) {
        
        boolean valid = true;
        
        if (isMandatory()) {
            valid &= validateCommittee(event.getPropertyKey(), event.getRequestBean());
        }
        return valid;
    }
    
    /**
     * If the committee is mandatory, verify that a committee has been selected.
     */
    private boolean validateCommittee(String propertyKey, ProtocolRequestBean requestBean) {
        boolean valid = true;
        if (StringUtils.isBlank(requestBean.getCommitteeId())) {
            valid = false;
            GlobalVariables.getMessageMap().putError(propertyKey + ".committeeId", 
                    KeyConstants.ERROR_PROTOCOL_COMMITTEE_NOT_SELECTED);
        }
        return valid;
    }
    
    /**
     * Is it mandatory for the submission to contain the committee and schedule?
     * @return true if mandatory; otherwise false
     */
    private boolean isMandatory() {
        final String param = this.getParameterService().getParameterValueAsString(ProtocolDocument.class, Constants.PARAMETER_IRB_COMM_SELECTION_DURING_SUBMISSION);
        
        return StringUtils.equalsIgnoreCase(MANDATORY, param);  
    }
}
