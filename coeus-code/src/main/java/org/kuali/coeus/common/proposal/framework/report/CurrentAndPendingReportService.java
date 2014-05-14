package org.kuali.coeus.common.proposal.framework.report;

import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.common.framework.print.CurrentReportBean;
import org.kuali.coeus.common.framework.print.PendingReportBean;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;

import java.util.List;
import java.util.Map;

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
