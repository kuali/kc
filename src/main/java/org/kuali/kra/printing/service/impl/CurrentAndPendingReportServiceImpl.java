package org.kuali.kra.printing.service.impl;

import org.kuali.kra.common.printing.CurrentReportBean;
import org.kuali.kra.common.printing.PendingReportBean;
import org.kuali.kra.dao.CurrentReportDao;
import org.kuali.kra.dao.PendingReportDao;
import org.kuali.kra.printing.service.CurrentAndPendingReportService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Transactional
public class CurrentAndPendingReportServiceImpl implements CurrentAndPendingReportService {
    private CurrentReportDao currentReportDao;
    private PendingReportDao pendingReportDao;

    public List<CurrentReportBean> loadCurrentReportData(String personId) {
        List<CurrentReportBean> data;
        try {
            data = currentReportDao.queryForCurrentSupport(personId);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    public List<PendingReportBean> loadPendingReportData(String personId) {
        List<PendingReportBean> data;
        try {
            data = pendingReportDao.queryForPendingSupport(personId);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    /**
     * 
     * @param currentReportDao
     */
    public void setCurrentReportDao(CurrentReportDao currentReportDao) {
        this.currentReportDao = currentReportDao;
    }

    /**
     *
     * @param pendingReportDao
     */
    public void setPendingReportDao(PendingReportDao pendingReportDao) {
        this.pendingReportDao = pendingReportDao;
    }
}
