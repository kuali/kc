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

package org.kuali.coeus.common.impl.custom;

import com.google.common.base.Objects;
import org.kuali.coeus.common.framework.custom.CustomDataContainer;
import org.kuali.coeus.common.framework.custom.DocumentCustomData;
import org.kuali.rice.krms.api.engine.TermResolutionException;
import org.kuali.rice.krms.api.engine.TermResolver;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CustomDataResolver implements TermResolver<Object> {
    public static final String CUSTOM_ATTRIBUTE_ID = "Custom Attribute Id";
    private String outputName;
    private Set<String> prereqs;
    private Set<String> params;

    private String moduleNamePreReq;

    public CustomDataResolver(String outputName, Set<String> params, String moduleNamePrereq) {
        this.outputName = outputName;
        this.prereqs = new HashSet<>();
        this.moduleNamePreReq = moduleNamePrereq;
        prereqs.add(moduleNamePrereq);
        if (params == null) {
            this.params = Collections.emptySet();
        } else {
            this.params = params;
        }
    }

    public String getModuleNamePreReq() {
        return moduleNamePreReq;
    }

    @Override
    public Set<String> getPrerequisites() {
        return prereqs;
    }

    @Override
    public String getOutput() {
        return outputName;
    }

    @Override
    public Set<String> getParameterNames() {
        return params;
    }

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public Object resolve(Map<String, Object> resolvedPrereqs, Map<String, String> parameters) throws TermResolutionException {
        CustomDataContainer customDataContainer = (CustomDataContainer) resolvedPrereqs.get(getModuleNamePreReq());
        return customDataContainer.getCustomDataList().stream().
                                                            filter(
                                                                    customData -> Objects.equal(customData.getCustomAttributeId().toString(), parameters.get(CUSTOM_ATTRIBUTE_ID))
                                                            ).findFirst().map(DocumentCustomData::getValue).orElse(null);
    }
}
