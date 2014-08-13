package org.kuali.coeus.common.proposal.impl.report;

import org.kuali.coeus.common.framework.print.PendingReportBean;
import org.kuali.rice.kew.api.exception.WorkflowException;

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
