/*
 * Copyright 2006-2008 The Kuali Foundation
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
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.KeyValuesService;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

/*
 * This is for questionnaire maintenance.  Only list the codes that user has permission to associate
 */
public class CoeusModuleValuesFinder extends KeyValuesBase {
    List<KeyLabelPair> moduleCodes = null;

    /*
     * @see org.kuali.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @SuppressWarnings("unchecked")
    public List<KeyLabelPair> getKeyValues() {
        List<String> validCodes = KraServiceLocator.getService(QuestionnaireService.class).getAssociateModules();
        if (moduleCodes == null) {
            KeyValuesService boService = KNSServiceLocator.getKeyValuesService();
            Collection<CoeusModule> codes = (Collection<CoeusModule>) boService.findAll(CoeusModule.class);
            List<KeyLabelPair> labels = new ArrayList<KeyLabelPair>();
            labels.add(new KeyLabelPair("", "select"));
            for (CoeusModule coeusModule : codes) {
                if (validCodes.contains(coeusModule.getModuleCode())) {
                    labels.add(new KeyLabelPair(coeusModule.getModuleCode(), coeusModule.getDescription()));
                }
            }

            moduleCodes = labels;
        }
        return moduleCodes;
    }


}
