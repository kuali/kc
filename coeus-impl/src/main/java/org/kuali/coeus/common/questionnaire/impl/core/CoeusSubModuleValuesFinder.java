/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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



