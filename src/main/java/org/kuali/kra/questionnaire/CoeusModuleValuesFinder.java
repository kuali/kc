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
package org.kuali.kra.questionnaire;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;
import org.kuali.rice.krad.service.KRADServiceLocator;
import org.kuali.rice.krad.service.KeyValuesService;

/*
 * This is for questionnaire maintenance.  Only list the codes that user has permission to associate
 */
public class CoeusModuleValuesFinder extends KeyValuesBase {
    List<KeyValue> moduleCodes = null;

    /*
     * @see org.kuali.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @SuppressWarnings("unchecked")
    public List<KeyValue> getKeyValues() {
        List<String> validCodes = KraServiceLocator.getService(QuestionnaireService.class).getAssociateModules();
        if (moduleCodes == null) {
            KeyValuesService boService = KRADServiceLocator.getKeyValuesService();
            Collection<CoeusModule> codes = (Collection<CoeusModule>) boService.findAll(CoeusModule.class);
            List<KeyValue> labels = new ArrayList<KeyValue>();
            labels.add(new ConcreteKeyValue("", "select"));
            for (CoeusModule coeusModule : codes) {
                if (validCodes.contains(coeusModule.getModuleCode())) {
                    labels.add(new ConcreteKeyValue(coeusModule.getModuleCode(), coeusModule.getDescription()));
                }
            }

            moduleCodes = labels;
        }
        return moduleCodes;
    }


}
