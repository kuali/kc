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
