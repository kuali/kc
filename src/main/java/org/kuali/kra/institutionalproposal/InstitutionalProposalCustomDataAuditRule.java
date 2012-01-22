/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.institutionalproposal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.institutionalproposal.customdata.InstitutionalProposalCustomData;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

/**
 * This class processes audit rules (warnings) for the Custom Data Information.
 */
public class InstitutionalProposalCustomDataAuditRule implements DocumentAuditRule {
    
    
    /**
     * @see org.kuali.core.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.rice.krad.document.Document)
     */
    /**
     * @see org.kuali.rice.krad.rules.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.rice.krad.document.Document)
     */
    @SuppressWarnings("unchecked")
    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;
        InstitutionalProposalDocument institutionalProposalDocument = (InstitutionalProposalDocument)document;
        Map<String, CustomAttributeDocument> customAttributeDocuments = institutionalProposalDocument.getCustomAttributeDocuments();
        List<InstitutionalProposalCustomData> proposalCustomDataList = 
            institutionalProposalDocument.getInstitutionalProposal().getInstitutionalProposalCustomDataList();
        if(proposalCustomDataList.size() == 0) {
            proposalCustomDataList = buildDummyListForAuditDisplay(customAttributeDocuments);
        }
        for (Map.Entry<String, CustomAttributeDocument> customAttributeDocumentEntry : customAttributeDocuments.entrySet()) {
            CustomAttributeDocument customAttributeDocument = customAttributeDocumentEntry.getValue();
            if (customAttributeDocument.isRequired()) {
                for (InstitutionalProposalCustomData proposalCustomData : proposalCustomDataList) {
                    if (customAttributeDocument.getCustomAttributeId() == proposalCustomData.getCustomAttributeId().longValue()
                             && proposalCustomData.getValue() == null) {
                        valid = false;
                        getAuditClusterAndReportErrors(customAttributeDocument);
                    }else if (customAttributeDocument.getCustomAttributeId() == proposalCustomData.getCustomAttributeId().longValue()
                            && proposalCustomData.getValue().equals("")) {
                        valid = false;
                        getAuditClusterAndReportErrors(customAttributeDocument);
                    }
                }
            }
        }

        return valid;
    }
    
    /**
     * This method gets global audit cluster and generates new error and adds to error list on audit cluster.
     * @param customAttributeDocument
     */
    @SuppressWarnings("unchecked")
    private void getAuditClusterAndReportErrors(CustomAttributeDocument customAttributeDocument) {
        String key = "CustomData" + StringUtils.deleteWhitespace(customAttributeDocument.getCustomAttribute().getGroupName()) + "Errors";
        AuditCluster auditCluster = (AuditCluster) KNSGlobalVariables.getAuditErrorMap().get(key);
        if (auditCluster == null) {
            List<AuditError> auditErrors = new ArrayList<AuditError>();
            auditCluster = new AuditCluster(customAttributeDocument.getCustomAttribute().getGroupName(), auditErrors, Constants.AUDIT_ERRORS);
            KNSGlobalVariables.getAuditErrorMap().put(key, auditCluster);
        }
        List<AuditError> auditErrors = auditCluster.getAuditErrorList();
        auditErrors.add(new AuditError("customAttributeValues(id" + customAttributeDocument.getCustomAttributeId() + ")",
                 RiceKeyConstants.ERROR_REQUIRED, 
                 StringUtils.deleteWhitespace(Constants.CUSTOM_ATTRIBUTES_PAGE + "." + customAttributeDocument.getCustomAttribute().getGroupName()), 
                 new String[]{customAttributeDocument.getCustomAttribute().getLabel()}));
    }
    
    
    
    /**
     * This method creates dummy custom data list if a new Institutional Proposal and custom data tab has not been previously visited.
     * @param customAttributeDocuments
     * @return
     */
    private List<InstitutionalProposalCustomData> buildDummyListForAuditDisplay(Map<String, CustomAttributeDocument> customAttributeDocuments) {
        List<InstitutionalProposalCustomData> proposalCustomData = new ArrayList<InstitutionalProposalCustomData>();
        for (Map.Entry<String, CustomAttributeDocument> customAttributeDocumentEntry : customAttributeDocuments.entrySet()) {
            CustomAttributeDocument customAttributeDocument = customAttributeDocumentEntry.getValue();           
            InstitutionalProposalCustomData institutionalProposalCustomData = new InstitutionalProposalCustomData();
            institutionalProposalCustomData.setCustomAttribute(customAttributeDocument.getCustomAttribute());
            institutionalProposalCustomData.setCustomAttributeId((long) customAttributeDocument.getCustomAttribute().getId());
            proposalCustomData.add(institutionalProposalCustomData);
        }
        return proposalCustomData;
    }
    

}
