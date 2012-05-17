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
package org.kuali.kra.krms.service.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.kra.krms.KrmsRulesContext;
import org.kuali.kra.krms.service.KrmsRulesExecutionService;
import org.kuali.rice.krms.api.KrmsApiServiceLocator;
import org.kuali.rice.krms.api.engine.Engine;
import org.kuali.rice.krms.api.engine.EngineResults;
import org.kuali.rice.krms.api.engine.Facts;
import org.kuali.rice.krms.api.engine.SelectionCriteria;
import org.kuali.rice.krms.framework.type.ValidationActionTypeService;

public class KrmsRulesExecutionServiceImpl implements KrmsRulesExecutionService {
    
    protected final Log LOG = LogFactory.getLog(KrmsRulesExecutionServiceImpl.class);
    
    public List<String> processUnitValidations(String unitNumber, KrmsRulesContext rulesContext) {
        Map<String, String> contextQualifiers = new HashMap<String, String>();
        rulesContext.populateContextQualifiers(contextQualifiers);
        SelectionCriteria selectionCriteria = SelectionCriteria.createCriteria(null, contextQualifiers,
                Collections.singletonMap(KcKrmsConstants.UNIT_NUMBER, unitNumber));

        Facts.Builder factsBuilder = Facts.Builder.create();
        rulesContext.addFacts(factsBuilder);

        Engine engine = KrmsApiServiceLocator.getEngine();
        if (engine == null) {
            LOG.error("Could not resolve KRMS Rules Engine - Unit Validations will not be evaluated!");
        } else {
            EngineResults results = engine.execute(selectionCriteria, factsBuilder.build(), null);
        
            // comma-delimited list of error & warning messages
            if (results != null) {
                String errors = (String) results.getAttribute(ValidationActionTypeService.VALIDATIONS_ACTION_ATTRIBUTE);
                if (errors != null) {
                    String[] errorArray = StringUtils.split(errors, ",");
                    return Arrays.asList(errorArray);
                }
            } else {
                LOG.warn("Results returned from KRMS Rules Engine was null.");
            }
        }
        return Collections.emptyList();
    }
}
