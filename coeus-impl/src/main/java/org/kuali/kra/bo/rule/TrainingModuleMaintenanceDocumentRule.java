package org.kuali.kra.bo.rule;

import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.TrainingModule;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.HashMap;

public class TrainingModuleMaintenanceDocumentRule extends KcMaintenanceDocumentRuleBase {

    public static final String TRAINING_CODE = "trainingCode";
    public static final String MODULE_CODE = "moduleCode";
    public static final String ERROR_TRAINING_EXISTS = "error.training.exists";
    public static final String DOCUMENT_NEW_MAINTAINABLE_OBJECT_TRAINING_CODE = "document.newMaintainableObject.trainingCode";

    public TrainingModuleMaintenanceDocumentRule() {
        super();
    }

    public boolean processCustomSaveDocumentBusinessRules(MaintenanceDocument document) {
        return canRecordBeAdded(document);
    }

    public boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        return canRecordBeAdded(document);
    }

    private boolean canRecordBeAdded(MaintenanceDocument document) {
        TrainingModule trainingModule = (TrainingModule)document.getDocumentBusinessObject();
        HashMap<String, Object> criteria = new HashMap<>();
        criteria.put(TRAINING_CODE, trainingModule.getTrainingCode());
        criteria.put(MODULE_CODE, trainingModule.getModuleCode());
        boolean success = getBoService().countMatching(TrainingModule.class, criteria) == 0;
        if(!success) {
            getGlobalVariableService().getMessageMap().putErrorWithoutFullErrorPath(DOCUMENT_NEW_MAINTAINABLE_OBJECT_TRAINING_CODE, ERROR_TRAINING_EXISTS,
                    trainingModule.getModuleCode(), trainingModule.getTrainingCode().toString());

        }
        return success;
    }

    public BusinessObjectService getBusinessObjectService() {
        return KcServiceLocator.getService(BusinessObjectService.class);
    }
}
