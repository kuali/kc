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
package org.kuali.coeus.common.impl.version.history;

import org.junit.Test;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.common.framework.version.history.VersionHistory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class VersionHistoryServiceImplTest {

    @Test
    public void test_findActiveVersionHistory_thereIsNoActiveVersionForEmptyList(){
        VersionHistoryServiceImpl versionHistoryService = new VersionHistoryServiceImpl();
        VersionHistory activeVersionHistory = versionHistoryService.findActiveVersionHistory(new ArrayList<VersionHistory>());
        assertNull(activeVersionHistory);
    }

    @Test
         public void test_findActiveVersionHistory_onlyItemInListIsActiveVersion(){
        VersionHistoryServiceImpl versionHistoryService = new VersionHistoryServiceImpl();
        List<VersionHistory> versionHistories = new ArrayList<VersionHistory>() {{
            add(getVersionHistory(VersionStatus.ACTIVE, "bo"));
        }};
        VersionHistory activeVersionHistory = versionHistoryService.findActiveVersionHistory(versionHistories);
        assertEquals("activeVersionHistory is not active version", "bo", activeVersionHistory.getSequenceOwnerClassName());
    }

    @Test
    public void test_findActiveVersionHistory_onlyItemInListIsNotActive(){
        VersionHistoryServiceImpl versionHistoryService = new VersionHistoryServiceImpl();
        List<VersionHistory> versionHistories = new ArrayList<VersionHistory>() {{
            add(getVersionHistory(VersionStatus.PENDING, "bo"));
        }};
        VersionHistory activeVersionHistory = versionHistoryService.findActiveVersionHistory(versionHistories);
        assertNull("activeVersion should be null", activeVersionHistory);
    }

    @Test
    public void test_findActiveVersionHistory_onlyItemInListIsNotActiveReturnsNull(){
        VersionHistoryServiceImpl versionHistoryService = new VersionHistoryServiceImpl();
        List<VersionHistory> versionHistories = new ArrayList<VersionHistory>() {{
            add(getVersionHistory(VersionStatus.PENDING, "bo"));
        }};
        VersionHistory activeVersionHistory = versionHistoryService.findActiveVersionHistory(versionHistories);
        assertNull("activeVersion should be null", activeVersionHistory);
    }

    @Test
    public void test_findActiveVersionHistory_multipleItemInListReturnActive(){
        VersionHistoryServiceImpl versionHistoryService = new VersionHistoryServiceImpl();
        List<VersionHistory> versionHistories = new ArrayList<VersionHistory>() {{
            add(getVersionHistory(VersionStatus.PENDING, ""));
            add(getVersionHistory(VersionStatus.ACTIVE, "bo"));
        }};
        VersionHistory activeVersionHistory = versionHistoryService.findActiveVersionHistory(versionHistories);
        assertEquals("activeVersionHistory is not active version", "bo", activeVersionHistory.getSequenceOwnerClassName());
    }

    @Test
    public void test_findActiveVersionHistory_multipleActiveVersionsReturnFirstActiveVersionFound(){
        VersionHistoryServiceImpl versionHistoryService = new VersionHistoryServiceImpl();
        List<VersionHistory> versionHistories = new ArrayList<VersionHistory>() {{
            add(getVersionHistory(VersionStatus.PENDING, ""));
            add(getVersionHistory(VersionStatus.ACTIVE, "bo"));
            add(getVersionHistory(VersionStatus.ACTIVE, "do"));
        }};
        VersionHistory activeVersionHistory = versionHistoryService.findActiveVersionHistory(versionHistories);
        assertEquals("activeVersionHistory is not first active version", "bo", activeVersionHistory.getSequenceOwnerClassName());
    }


    protected VersionHistory getVersionHistory(VersionStatus status, String bo) {
        final VersionHistory versionHistory = new VersionHistory();
        versionHistory.setStatus(status);
        versionHistory.setSequenceOwnerClassName(bo);
        return versionHistory;
    }

}
