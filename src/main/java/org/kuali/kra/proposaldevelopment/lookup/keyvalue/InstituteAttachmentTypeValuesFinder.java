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
package org.kuali.kra.proposaldevelopment.lookup.keyvalue;

import static org.kuali.kra.infrastructure.Constants.PARAMETER_COMPONENT_DOCUMENT;
import static org.kuali.kra.infrastructure.Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.core.lookup.keyvalues.KeyValuesBase;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.lookup.keyvalue.KeyValueFinderService;
import org.kuali.kra.proposaldevelopment.bo.NarrativeType;

/**
 * 
 * This class is to get the drop down list of institute attachment type.
 */
public class InstituteAttachmentTypeValuesFinder  extends KeyValuesBase {
        KeyValueFinderService keyValueFinderService= (KeyValueFinderService)KraServiceLocator.getService("keyValueFinderService");
        public List<KeyLabelPair> getKeyValues() {
            String instituteNarrativeTypeGroup = getService(KualiConfigurationService.class).getParameterValue(PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, PARAMETER_COMPONENT_DOCUMENT, Constants.INSTITUTE_NARRATIVE_TYPE_GROUP);
            Map<String,String> queryMap = new HashMap<String,String>();
            queryMap.put("narrativeTypeGroup", instituteNarrativeTypeGroup);
            queryMap.put("systemGenerated", "N");
            return keyValueFinderService.getKeyValues(NarrativeType.class, "narrativeTypeCode", "description",queryMap);
        }
}
