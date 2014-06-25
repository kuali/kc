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
package org.kuali.coeus.common.questionnaire.impl.core;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.module.CoeusSubModule;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.krad.service.KeyValuesService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.*;

public class CoeusSubModuleValuesFinder extends UifKeyValuesFinderBase {
    List<KeyValue> subModuleCodes = null;
    private String moduleCode;
    @Override
    public List<KeyValue> getKeyValues() {

        if (subModuleCodes == null) {
            List<KeyValue> labels = new ArrayList<KeyValue>();
            labels.add(new ConcreteKeyValue("0", "select"));
            if (StringUtils.isNotBlank(moduleCode)) {
                KeyValuesService boService = KNSServiceLocator.getKeyValuesService();
                Map<String, Object> fieldValues = new HashMap<String, Object>();
                fieldValues.put("moduleCode", moduleCode);
                Collection<CoeusSubModule> codes = (Collection<CoeusSubModule>) boService.findMatching(CoeusSubModule.class,
                        fieldValues);
                for (CoeusSubModule coeusSubModule : codes) {
                    labels.add(new ConcreteKeyValue(coeusSubModule.getSubModuleCode(), coeusSubModule.getDescription()));
                }
            }
            subModuleCodes = labels;
        }
        return subModuleCodes;
    }

    public String getModuleCode() {
        return moduleCode;
    }
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }


}



