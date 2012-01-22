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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.CoeusSubModule;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;
import org.kuali.rice.krad.service.KRADServiceLocator;
import org.kuali.rice.krad.service.KeyValuesService;

public class CoeusSubModuleValuesFinder extends KeyValuesBase {
    List<KeyValue> subModuleCodes = null;
    private String moduleCode;
    /*
     * @see org.kuali.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @SuppressWarnings("unchecked")
    public List<KeyValue> getKeyValues() {

        if (subModuleCodes == null) {
            List<KeyValue> labels = new ArrayList<KeyValue>();
            labels.add(new ConcreteKeyValue("0", "select"));
            if (StringUtils.isNotBlank(moduleCode)) {
                KeyValuesService boService = KRADServiceLocator.getKeyValuesService();
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



