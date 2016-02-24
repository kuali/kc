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
        List<Map<String,String>> krmsErrors = getKrmsRulesExecutionService().processUnitKcValidations(pdDocument.getDevelopmentProposal().getAllUnitNumbers(),pdDocument);
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
