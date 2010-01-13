package org.kuali.kra.dao;

import org.kuali.kra.common.printing.PendingReportBean;
import org.kuali.rice.kew.exception.WorkflowException;

import java.util.List;

/**
 * Contract for pending support DAO
 */
public interface PendingReportDao {
    /**
     * Loads the Pending Report data into a list of PendingReportBeans
     * @param personId - The person for whom pending support obligations are being queried
     * @return
     */
    List<PendingReportBean> queryForPendingSupport(String personId) throws WorkflowException;
}
