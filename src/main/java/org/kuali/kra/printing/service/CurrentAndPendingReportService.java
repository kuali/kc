package org.kuali.kra.printing.service;

import java.util.List;
import java.util.Map;

import org.kuali.kra.common.printing.CurrentReportBean;
import org.kuali.kra.common.printing.PendingReportBean;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;

/**
 *
 */
public interface CurrentAndPendingReportService {
    public static final String CURRENT_REPORT_TYPE = "Current Report";
    public static final String PENDING_REPORT_TYPE = "Pending Report";
    public static final String CURRENT_REPORT_BEANS_KEY = "Current Report Beans";
    public static final String PENDING_REPORT_BEANS_KEY = "Pending Report Beans";
    public static final String PERSON_ID_KEY = "personId";
    public static final String CURRENT_REPORT_ROWS_KEY = "currentReportRows";
    public static final String PENDING_REPORT_ROWS_KEY = "pendingReportRows";
    public static final String REPORT_PERSON_NAME_KEY = "reportPersonName";
    
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

    /**
     * This method generates the required report and returns the PDF stream as
     * {@link AttachmentDataSource}
     * 
     * @param reportName
     *            report to be generated
     * @param reportParameters
     *            {@link Map} of parameters required for report generation
     * @return {@link AttachmentDataSource} which contains the byte array of the
     *         generated PDF
     * @throws PrintingException
     *             if any errors occur during report generation
     */
    public AttachmentDataSource printCurrentAndPendingSupportReport(
            String reportName, Map<String, Object> reportParameters) throws PrintingException;
}
