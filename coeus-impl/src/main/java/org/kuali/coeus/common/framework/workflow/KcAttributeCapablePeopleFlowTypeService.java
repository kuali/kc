package org.kuali.coeus.common.framework.workflow;

import java.util.Map;

import org.kuali.rice.kew.api.document.Document;
import org.kuali.rice.kew.api.document.DocumentContent;
import org.kuali.rice.kew.api.peopleflow.PeopleFlowDefinition;
import org.kuali.rice.kew.framework.peopleflow.PeopleFlowTypeService;

public interface KcAttributeCapablePeopleFlowTypeService extends PeopleFlowTypeService {

	Map<String, String> resolveRoleQualifiers(PeopleFlowDefinition peopleflowDefinition, String roleId, Document document,
        DocumentContent documentContent);

}
