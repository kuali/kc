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
package org.kuali.coeus.common.impl.workflow;

import org.kuali.rice.core.api.exception.RiceIllegalArgumentException;
import org.kuali.rice.core.api.uif.RemotableAttributeError;
import org.kuali.rice.core.api.uif.RemotableAttributeField;
import org.kuali.rice.kew.api.document.Document;
import org.kuali.rice.kew.api.document.DocumentContent;
import org.kuali.rice.kew.framework.peopleflow.PeopleFlowTypeService;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component("kcPeopleFlowTypeService")
public class KcPeopleFlowTypeServiceImpl implements PeopleFlowTypeService {

    @Override
    public List<String> filterToSelectableRoleIds(String kewTypeId, List<String> roleIds) {

        return roleIds;
    }

    @Override
    public Map<String, String> resolveRoleQualifiers(String kewTypeId, String roleId, Document document,
            DocumentContent documentContent) {

        return null;
    }

    @Override
    public List<RemotableAttributeField> getAttributeFields(String kewTypeId) {

        return Collections.emptyList();
    }

    @Override
    public List<RemotableAttributeError> validateAttributes(String kewTypeId, Map<String, String> attributes)
            throws RiceIllegalArgumentException {

        return Collections.emptyList();
    }

    @Override
    public List<RemotableAttributeError> validateAttributesAgainstExisting(String kewTypeId, Map<String, String> newAttributes,
            Map<String, String> oldAttributes) throws RiceIllegalArgumentException {

        return Collections.emptyList();
    }

    @Override
    public List<Map<String, String>> resolveMultipleRoleQualifiers(String arg0, String arg1, Document arg2, DocumentContent arg3) {
        return null;
    }

}
