/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
     * This method returns the proper Award for displaying information in T&amp;M, Budget and Award documents.  Logic for canceled documents.
     * @param awardNumber
     * @return
     */
    @Override
    public Award getWorkingAwardVersion(String awardNumber) {
        List<VersionHistory> versions = versionHistoryService.findVersionHistory(Award.class, awardNumber);
        VersionHistory activeVersion = getActiveVersionHistory(versions);
        VersionHistory pendingVersion = getPendingVersionHistory(versions);
        VersionHistory workingVersion = null;
        if(activeVersion != null) {
            workingVersion = activeVersion;
        } else if(pendingVersion != null) {
            workingVersion = pendingVersion;
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
