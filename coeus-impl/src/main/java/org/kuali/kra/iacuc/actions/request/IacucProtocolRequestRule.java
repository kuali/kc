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
package org.kuali.kra.iacuc.actions.request;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * Business rule for a protocol request.  If the mandatory option has been
 * set in the system params, the committee must be selected.
 */
@SuppressWarnings("unchecked")
public class IacucProtocolRequestRule extends KcTransactionalDocumentRuleBase implements KcBusinessRule<IacucProtocolRequestEvent> {
    
    private static final String MANDATORY = "M";
    private ParameterService parameterService;
    @Override
    public boolean processRules(IacucProtocolRequestEvent event) {
        
        boolean valid = true;
        
        if (isMandatory()) {
            valid &= validateCommittee(event.getPropertyKey(), event.getRequestBean());
        }
        return valid;
    }
    
    /**
     * If the committee is mandatory, verify that a committee has been selected.
     */
    private boolean validateCommittee(String propertyKey, IacucProtocolRequestBean requestBean) {
        boolean valid = true;
        if (StringUtils.isBlank(requestBean.getCommitteeId())) {
            valid = false;
            GlobalVariables.getMessageMap().putError(propertyKey + ".committeeId", 
                    KeyConstants.ERROR_PROTOCOL_COMMITTEE_NOT_SELECTED);
        }
        return valid;
    }

//TODO: Check parm in following method    
    /**
     * Is it mandatory for the submission to contain the committee and schedule?
     * @return true if mandatory; otherwise false
     */
    private boolean isMandatory() {
        final String param = this.getParameterService().getParameterValueAsString(IacucProtocolDocument.class, Constants.PARAMETER_IRB_COMM_SELECTION_DURING_SUBMISSION);
        
        return StringUtils.equalsIgnoreCase(MANDATORY, param);  
    }

    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return this.parameterService;
    }
}
