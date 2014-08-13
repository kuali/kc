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
package org.kuali.coeus.common.impl.person.attr;

import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.krad.service.KeyValuesService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class PersonEditableFieldModuleValuesFinder extends UifKeyValuesFinderBase {
    List<KeyValue> moduleCodes = null;

    @Override
    public List<KeyValue> getKeyValues() {
        List<String> validCodes = Arrays.asList(new String[] {CoeusModule.PROPOSAL_DEVELOPMENT_MODULE_CODE, CoeusModule.IRB_MODULE_CODE});
        if (moduleCodes == null) {
            KeyValuesService boService = KNSServiceLocator.getKeyValuesService();
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
