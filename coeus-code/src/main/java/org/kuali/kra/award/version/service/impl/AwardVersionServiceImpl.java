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
package org.kuali.kra.award.version.service.impl;

import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.common.framework.version.history.VersionHistory;
import org.kuali.coeus.common.framework.version.history.VersionHistoryService;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.version.service.AwardVersionService;

import java.util.List;

/**
 * Award Version Service implementation
 */
public class AwardVersionServiceImpl implements AwardVersionService {


    private VersionHistoryService versionHistoryService;
    
    /**
     * This method returns the proper Award for displaying information in T&M, Budget and Award documents.  Logic for canceled documents.
     * @param awardNumber
     * @return
     */
    @Override
    public Award getWorkingAwardVersion(String awardNumber) {
        List<VersionHistory> versions = versionHistoryService.findVersionHistory(Award.class, awardNumber);
        VersionHistory activeVersion = getActiveVersionHistory(versions);
        VersionHistory pendingVersion = getPendingVersionHistory(versions);
        VersionHistory workingVersion = null;
        if(!(pendingVersion == null)) {
            workingVersion = pendingVersion;
        } else if(!(activeVersion == null)) {
            workingVersion = activeVersion;
        } else {
            return null;
        }
        versionHistoryService.loadSequenceOwner(Award.class,workingVersion);
        return (Award)workingVersion.getSequenceOwner();
    }
    
    
    
    @Override
    public Award getActiveAwardVersion(String awardNumber) {
        List<VersionHistory> versions = versionHistoryService.findVersionHistory(Award.class, awardNumber);
        VersionHistory result = getActiveVersionHistory(versions);
        return (result == null) ? null : (Award) result.getSequenceOwner();
    }



    @Override
    public Award getPendingAwardVersion(String awardNumber) {
        List<VersionHistory> versions = versionHistoryService.findVersionHistory(Award.class, awardNumber);
        VersionHistory result = getPendingVersionHistory(versions);
        return (result == null) ? null : (Award) result.getSequenceOwner();
    }



    private VersionHistory getPendingVersionHistory (List<VersionHistory> list) {
        VersionHistory returnVal = null;
        for(VersionHistory vh : list) {
            if(vh.getStatus().equals(VersionStatus.PENDING)) {
                returnVal = vh;
            }
        }
        return returnVal;
    }
    
    private VersionHistory getActiveVersionHistory (List<VersionHistory> list) {
        VersionHistory returnVal = null;
        for(VersionHistory vh : list) {
            if(vh.getStatus().equals(VersionStatus.ACTIVE)) {
                returnVal = vh;
            }
        }
        return returnVal;
    }

    
    public VersionHistoryService getVersionHistoryService() {
        return versionHistoryService;
    }



    public void setVersionHistoryService(VersionHistoryService versionHistoryService) {
        this.versionHistoryService = versionHistoryService;
    }
}
