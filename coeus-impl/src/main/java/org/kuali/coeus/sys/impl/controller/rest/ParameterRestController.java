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
package org.kuali.coeus.sys.impl.controller.rest;


import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.controller.rest.SimpleCrudMapBasedRestController;
import org.kuali.coeus.sys.framework.rest.ResourceNotFoundException;
import org.kuali.rice.coreservice.impl.parameter.ParameterBo;
import org.kuali.rice.krad.data.CompoundKey;

import java.util.Map;
import static org.kuali.coeus.sys.framework.util.CollectionUtils.entriesToMap;

public class ParameterRestController extends SimpleCrudMapBasedRestController<ParameterBo> {

    private static final String DELIMETER = ":";
    private static final String NAMESPACE_CODE = "namespaceCode";
    private static final String COMPONENT_CODE = "componentCode";
    private static final String NAME = "name";
    private static final String APPLICATION_ID = "applicationId";

    @Override
    public String getPrimaryKeyColumn() {
        return NAMESPACE_CODE + DELIMETER + COMPONENT_CODE + DELIMETER + NAME + DELIMETER + APPLICATION_ID;
    }

    @Override
    protected Object getPrimaryKeyIncomingObject(Map<String, Object> dataObject) {
        return new CompoundKey(dataObject.entrySet().stream()
                .filter(e -> NAMESPACE_CODE.equals(e.getKey())
                        || COMPONENT_CODE.equals(e.getKey())
                        || NAME.equals(e.getKey())
                        || APPLICATION_ID.equals(e.getKey()))
                .collect(entriesToMap()));
    }

    @Override
    protected ParameterBo getFromDataStore(Object code) {
        if (code instanceof String) {
            code = new CompoundKey(getKeyMap((String)code));
        }

        return super.getFromDataStore(code);
    }

    //validation methods are no op for now
    @Override
    protected boolean validateDeleteDataObject(ParameterBo dataObject) {
        return true;
    }

    @Override
    protected boolean validateInsertDataObject(ParameterBo dataObject) {
        return true;
    }

    @Override
    protected boolean validateUpdateDataObject(ParameterBo dataObject) {
        return true;
    }

    protected Map<String, String> getKeyMap(String compoundKey) {

        if (compoundKey.contains(DELIMETER)) {
            final String[] keyElements = compoundKey.split(DELIMETER);
            if (keyElements.length < 4) {
                throw new ResourceNotFoundException("compoundKey does not contain all key elements in format: " + NAMESPACE_CODE + DELIMETER + COMPONENT_CODE + DELIMETER + NAME + DELIMETER + APPLICATION_ID);
            }

            final String namespaceCode = keyElements[0];
            final String componentCode = keyElements[1];
            final String name = keyElements[2];
            final String applicationId = keyElements[3];
            if (StringUtils.isBlank(namespaceCode)) {
                throw new ResourceNotFoundException("blank " + NAMESPACE_CODE + " from compoundKey");
            }

            if (StringUtils.isBlank(componentCode)) {
                throw new ResourceNotFoundException("blank " + COMPONENT_CODE + " from compoundKey");
            }

            if (StringUtils.isBlank(name)) {
                throw new ResourceNotFoundException("blank " + NAME + " from compoundKey");
            }

            if (StringUtils.isBlank(applicationId)) {
                throw new ResourceNotFoundException("blank " + APPLICATION_ID + " from compoundKey");
            }

            return new HashedMap<String, String>() {{
                put(NAMESPACE_CODE, namespaceCode);
                put(COMPONENT_CODE, componentCode);
                put(NAME, name);
                put(APPLICATION_ID, applicationId);
            }};
        } else {
            throw new ResourceNotFoundException("compoundKey does not contain all key elements in format: " + NAMESPACE_CODE + DELIMETER + COMPONENT_CODE + DELIMETER + NAME + DELIMETER + APPLICATION_ID);
        }
    }
}
