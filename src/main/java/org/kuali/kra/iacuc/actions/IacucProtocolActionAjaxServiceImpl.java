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
package org.kuali.kra.iacuc.actions;


import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolReviewerType;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.actions.ProtocolActionAjaxServiceImpl;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

public class IacucProtocolActionAjaxServiceImpl extends ProtocolActionAjaxServiceImpl implements IacucProtocolActionAjaxService {
    
    private ParameterService parameterService;
    
    public static final String DEFAULT_REVIEW_TYPE_PARAMETER_NAME = "IACUC_ALL_COMM_REVIEWERS_DEFAULT_ASSIGNED";

    public Class<? extends Protocol> getProtocolClassHook() {
        return IacucProtocol.class;
    }

    @Override
    public Class getProtocolReviewerTypeClassHook() {
        return IacucProtocolReviewerType.class;
    }
    
    @Override
    public String getDefaultCommitteeReviewTypeCode() {
        String paramVal = this.getParameterService().getParameterValueAsString("KC-IACUC", "Document", DEFAULT_REVIEW_TYPE_PARAMETER_NAME);
        return paramVal;
    }

    public ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }


}
