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
