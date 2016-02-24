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
package org.kuali.kra.irb.actions.request;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * Business rule for a protocol request.  If the mandatory option has been
 * set in the system params, the committee must be selected.
 */
@SuppressWarnings("unchecked")
public class ProtocolRequestRule extends KcTransactionalDocumentRuleBase implements KcBusinessRule<ProtocolRequestEvent> {
    
    private static final String MANDATORY = "M";
    private ParameterService parameterService;
    @Override
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

    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return this.parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
}
