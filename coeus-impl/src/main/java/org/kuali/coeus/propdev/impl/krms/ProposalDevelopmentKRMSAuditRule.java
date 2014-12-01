package org.kuali.coeus.propdev.impl.krms;

import org.kuali.coeus.common.framework.krms.KrmsRulesExecutionService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krms.framework.type.ValidationActionTypeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.kuali.coeus.propdev.impl.datavalidation.ProposalDevelopmentDataValidationConstants.GENERIC_ERROR;

public class ProposalDevelopmentKRMSAuditRule  extends KcTransactionalDocumentRuleBase implements DocumentAuditRule {

    private KrmsRulesExecutionService krmsRulesExecutionService;

    @Override
    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;
        ProposalDevelopmentDocument pdDocument = (ProposalDevelopmentDocument) document;
        List<Map<String,String>> krmsErrors = getKrmsRulesExecutionService().processUnitKcValidations(pdDocument.getLeadUnitNumber(),pdDocument);
        for (Map<String,String> error: krmsErrors) {
            valid = false;
            String areaName = error.get(KcKrmsConstants.ValidationAction.VALIDATIONS_ACTION_AREA_ATTRIBUTE);
            String sectionName = error.get(KcKrmsConstants.ValidationAction.VALIDATIONS_ACTION_SECTION_ATTRIBUTE);
            String errorParam = error.get(ValidationActionTypeService.VALIDATIONS_ACTION_MESSAGE_ATTRIBUTE);
            String severity = error.get(ValidationActionTypeService.VALIDATIONS_ACTION_TYPE_CODE_ATTRIBUTE).equals("E")?"Error":"Warnings";
            String pageID = error.get(KcKrmsConstants.ValidationAction.VALIDATIONS_ACTION_NAVIGATE_TO_PAGE_ID_ATTRIBUTE);
            String sectionId = error.get(KcKrmsConstants.ValidationAction.VALIDATIONS_ACTION_NAVIGATE_TO_SECTION_ID_ATTRIBUTE);

            getAuditErrors(areaName,sectionName,severity).add(new AuditError(pageID,GENERIC_ERROR,pageID+"."+sectionId,new String[]{errorParam}));
        }
        return valid;
    }

    private List<AuditError> getAuditErrors(String areaName, String sectionName,String severity ) {
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        String clusterKey = areaName + "." + sectionName;
        if (!GlobalVariables.getAuditErrorMap().containsKey(clusterKey+"." + severity)) {
            GlobalVariables.getAuditErrorMap().put(clusterKey+severity, new AuditCluster(clusterKey, auditErrors, severity));
        }
        else {
            auditErrors = GlobalVariables.getAuditErrorMap().get(clusterKey+"."+severity).getAuditErrorList();
        }

        return auditErrors;
    }

    public KrmsRulesExecutionService getKrmsRulesExecutionService() {
        if (krmsRulesExecutionService == null) {
            krmsRulesExecutionService = KcServiceLocator.getService(KrmsRulesExecutionService.class);
        }

        return krmsRulesExecutionService;
    }

    public void setKrmsRulesExecutionService(KrmsRulesExecutionService krmsRulesExecutionService) {
        this.krmsRulesExecutionService = krmsRulesExecutionService;
    }
}
