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
package org.kuali.kra.award;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.award.awardhierarchy.AwardHierarchyService;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncChange;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.versioning.VersionHistory;
import org.kuali.kra.bo.versioning.VersionStatus;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.VersionHistoryService;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

/**
 * This class...
 */
public class AwardSyncAuditRule implements DocumentAuditRule {

    protected String SYNC_ERRORS = "awardSyncAuditErrors";
    
    protected List<AuditError> auditErrors;
    protected List<AuditError> auditWarnings;
    
    /**
     * @see org.kuali.rice.krad.rules.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.rice.krad.document.Document)
     */
    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;
        AwardDocument awardDocument = (AwardDocument) document;
        auditErrors = new ArrayList<AuditError>();
        auditWarnings = new ArrayList<AuditError>();
        Award award = awardDocument.getAward();
        if (award.getSyncChanges() != null) {
            for (AwardSyncChange change : award.getSyncChanges()) {
                if (change.getSyncDescendantsType() == null) {
                    valid = false;
                    auditErrors.add(new AuditError("document.awardList[0].syncChanges[" 
                            + award.getSyncChanges().indexOf(change) + "].syncDescendants",
                            KeyConstants.ERROR_SYNC_DESCENDANT_BLANK, 
                            Constants.MAPPING_AWARD_ACTIONS_PAGE + "." + "Award Hierarchy Sync"));
                }
            }
            AwardHierarchy hierarchy = getAwardHierarchyService().loadAwardHierarchyBranch(award.getAwardNumber());
            if (hierarchy != null) {
                for (AwardHierarchy curHierarchy : hierarchy.getChildren()) {
                    valid &= validateHierarchyIsActive(curHierarchy);
                }
            }
        }
        reportAndCreateAuditCluster();
        return valid;
    }
    
    protected AwardHierarchyService getAwardHierarchyService() {
        return KraServiceLocator.getService(AwardHierarchyService.class);
    }
    
    protected VersionHistoryService getVersionHistoryService() {
        return KraServiceLocator.getService(VersionHistoryService.class);
    }
    
    /**
     * This method creates and adds the AuditCluster to the Global AuditErrorMap.
     */
    @SuppressWarnings("unchecked")
    protected void reportAndCreateAuditCluster() {
        if (auditErrors.size() > 0) {
            KNSGlobalVariables.getAuditErrorMap().put(SYNC_ERRORS, new AuditCluster("Award Hierarchy Sync",
                    auditErrors, Constants.AUDIT_ERRORS));
        } 
    }
    
    protected boolean validateHierarchyIsActive(AwardHierarchy hierarchy) {
        boolean success = isAwardActive(hierarchy.getAwardNumber());
        if (!success) {
            auditErrors.add(new AuditError("document.awardList[0].syncChanges",
                    KeyConstants.ERROR_SYNC_AWARD_STATUS, 
                    Constants.MAPPING_AWARD_ACTIONS_PAGE + "." + "Award Hierarchy Sync", 
                    new String[]{hierarchy.getAwardNumber()}));
        }
        for (AwardHierarchy curHierarchy : hierarchy.getChildren()) {
            success &= validateHierarchyIsActive(curHierarchy);
        }
        return success;
    }
    
    protected boolean isAwardActive(String awardNumber) {
        boolean success = false;
        List<VersionHistory> versions = getVersionHistoryService().loadVersionHistory(Award.class, awardNumber);
        for (VersionHistory history : versions) {
            if (history.getStatus() == VersionStatus.PENDING) {
                return false;
            } else if (history.getStatus() == VersionStatus.ACTIVE) {
                success = true;
            }
        }
        return success;
    }
}
