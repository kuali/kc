/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.service.impl;

import org.kuali.core.service.KualiConfigurationService;
import org.kuali.kra.infrastructure.Constants;

public class SystemParameterRetrievalServiceImpl implements org.kuali.kra.proposaldevelopment.service.SystemParameterRetrievalService {
    
    private KualiConfigurationService kualiConfigurationService;
    
    public String getParameterValue(String paramName, String defaultValue) {
        String paramValue = null;
        try {
            paramValue = kualiConfigurationService.getParameter(
                Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT, paramName).getParameterValue();
        } catch (IllegalArgumentException ex) {
            paramValue = defaultValue;
        }
        return paramValue;
    }

    public KualiConfigurationService getKualiConfigurationService() {
        return kualiConfigurationService;
    }

    public void setKualiConfigurationService(KualiConfigurationService kualiConfigurationService) {
        this.kualiConfigurationService = kualiConfigurationService;
    }

}
