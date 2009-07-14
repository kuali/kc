/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.customdata.AwardCustomData;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.institutionalproposal.customdata.InstitutionalProposalCustomData;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.DocumentAuditRule;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.RiceKeyConstants;

/**
 * This class...
 */
public class AwardCustomDataAuditRule implements DocumentAuditRule {

    /**
     * @see org.kuali.core.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.rice.kns.document.Document)
     */
    /**
     * @see org.kuali.rice.kns.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.rice.kns.document.Document)
     */
    @SuppressWarnings("unchecked")
    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;
        AwardDocument  awardDocument = (AwardDocument) document;
        Map<String, CustomAttributeDocument> customAttributeDocuments = awardDocument.getCustomAttributeDocuments();
        List<AwardCustomData> awardCustomDataList = 
            awardDocument.getAward().getAwardCustomDataList();
        if(awardCustomDataList.size() == 0) {
            awardCustomDataList = buildDummyListForAuditDisplay(customAttributeDocuments);
        }
        for (Map.Entry<String, CustomAttributeDocument> customAttributeDocumentEntry : customAttributeDocuments.entrySet()) {
            CustomAttributeDocument customAttributeDocument = customAttributeDocumentEntry.getValue();
            if (customAttributeDocument.isRequired()) {
                for (AwardCustomData awardCustomData : awardCustomDataList) {
                    if (customAttributeDocument.getCustomAttributeId() == awardCustomData.getCustomAttributeId().longValue()
                             && awardCustomData.getValue() == null) {
                        valid = false;
                        getAuditClusterAndReportErrors(customAttributeDocument);
                    }else if (customAttributeDocument.getCustomAttributeId() == awardCustomData.getCustomAttributeId().longValue()
                            && awardCustomData.getValue().equals("")) {
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
        AuditCluster auditCluster = (AuditCluster) GlobalVariables.getAuditErrorMap().get(key);
        if (auditCluster == null) {
            List<AuditError> auditErrors = new ArrayList<AuditError>();
            auditCluster = new AuditCluster(customAttributeDocument.getCustomAttribute().getGroupName(), auditErrors, Constants.AUDIT_ERRORS);
            GlobalVariables.getAuditErrorMap().put(key, auditCluster);
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
    private List<AwardCustomData> buildDummyListForAuditDisplay(Map<String, CustomAttributeDocument> customAttributeDocuments) {
        List<AwardCustomData> awardCustomDataList = new ArrayList<AwardCustomData>();
        for (Map.Entry<String, CustomAttributeDocument> customAttributeDocumentEntry : customAttributeDocuments.entrySet()) {
            CustomAttributeDocument customAttributeDocument = customAttributeDocumentEntry.getValue();           
            AwardCustomData awardCustomData = new AwardCustomData();
            awardCustomData.setCustomAttribute(customAttributeDocument.getCustomAttribute());
            awardCustomData.setCustomAttributeId((long) customAttributeDocument.getCustomAttribute().getId());
            awardCustomDataList.add(awardCustomData);
        }
        return awardCustomDataList;
    }

}
