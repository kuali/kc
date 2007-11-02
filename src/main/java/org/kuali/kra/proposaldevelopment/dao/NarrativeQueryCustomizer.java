/*
 * Copyright 2007 The Kuali Foundation.
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
package org.kuali.kra.proposaldevelopment.dao;

import static org.kuali.kra.infrastructure.Constants.PARAMETER_COMPONENT_DOCUMENT;
import static org.kuali.kra.infrastructure.Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.kra.dao.QueryCustomizerDefaultImpl;

public class NarrativeQueryCustomizer extends QueryCustomizerDefaultImpl {
    public void addAttribute(String name, String value) {
        String narrTypeGroup = null;//getService(KualiConfigurationService.class).getParameterValue(PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, PARAMETER_COMPONENT_DOCUMENT, value);
        super.addAttribute(name, StringUtils.isBlank(narrTypeGroup)?value:narrTypeGroup);
    }
}
