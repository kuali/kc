package org.kuali.coeus.common.impl.krms;

import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.rice.krms.api.engine.ExecutionEnvironment;
import org.kuali.rice.krms.framework.type.ValidationActionType;
import org.kuali.rice.krms.framework.engine.Action;
import org.kuali.rice.krms.framework.type.ValidationActionTypeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KcValidationAction implements Action {
    private final ValidationActionType type;
    private final String message;
    private final String areaName;
    private final String sectionName;
    private final String navigateToPageId;
    private final String navigateToSectionId;

    public KcValidationAction(ValidationActionType type, String areaName, String sectionName, String navigateToPageId, String navigateToSectionId, String message) {

        this.type = type;
        this.areaName = areaName;
        this.sectionName = sectionName;
        this.navigateToPageId = navigateToPageId;
        this.navigateToSectionId = navigateToSectionId;
        this.message = message;
    }

    @Override
    public void execute(ExecutionEnvironment environment) {
        // create or extend an existing attribute on the EngineResults to communicate the selected Validation and
        List<Map<String,String>> attributeMapList = new ArrayList<Map<String,String>>();
        Object value = environment.getEngineResults().getAttribute(KcKrmsConstants.ValidationAction.VALIDATIONS_ACTION_ATTRIBUTE);

        if (value!=null && value instanceof List) {
            attributeMapList.addAll((List)value);
        }

        Map<String,String> attributeMap = new HashMap<String,String>();
        attributeMap.put(ValidationActionTypeService.VALIDATIONS_ACTION_TYPE_CODE_ATTRIBUTE,type.getCode());
        attributeMap.put(KcKrmsConstants.ValidationAction.VALIDATIONS_ACTION_AREA_ATTRIBUTE,areaName);
        attributeMap.put(KcKrmsConstants.ValidationAction.VALIDATIONS_ACTION_SECTION_ATTRIBUTE,sectionName);
        attributeMap.put(KcKrmsConstants.ValidationAction.VALIDATIONS_ACTION_NAVIGATE_TO_PAGE_ID_ATTRIBUTE,navigateToPageId);
        attributeMap.put(KcKrmsConstants.ValidationAction.VALIDATIONS_ACTION_NAVIGATE_TO_SECTION_ID_ATTRIBUTE,navigateToSectionId);
        attributeMap.put(ValidationActionTypeService.VALIDATIONS_ACTION_MESSAGE_ATTRIBUTE,message);

        attributeMapList.add(attributeMap);
        // set our attribute on the engine results
        environment.getEngineResults().setAttribute(KcKrmsConstants.ValidationAction.VALIDATIONS_ACTION_ATTRIBUTE,
                attributeMapList
        );
    }

    @Override
    public void executeSimulation(ExecutionEnvironment environment) {
        // our action doesn't need special handling during simulations
        execute(environment);
    }
}


