package org.kuali.kra.printing.service;

import org.kuali.kra.common.printing.CurrentReportBean;
import org.kuali.kra.common.printing.PendingReportBean;

import java.util.List;

/**
 *
 */
public interface CurrentAndPendingReportService {
    /**
     * Loads the Current Report data into a list of CurrentReportBeans
     *
     * @param personId - The person for whom current support obligations are being queried
     * @return The list of beans or an empty list if no data found.
     * @throws RuntimeException if an exception is thrown during the call
     */
    List<CurrentReportBean> loadCurrentReportData(String personId);

    /**
     * Loads the Pending Report data into a list of PendingReportBeans
     *
     * @param personId - The person for whom pending support obligations are being queried
     * @return The list of beans or an empty list if no data found.
     * @throws RuntimeException if an exception is thrown during the call
     */
    List<PendingReportBean> loadPendingReportData(String personId);
}
