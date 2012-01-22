package org.kuali.kra.printing.service.impl;

import java.util.List;
import java.util.Map;

import org.kuali.kra.common.printing.CurrentReportBean;
import org.kuali.kra.common.printing.PendingReportBean;
import org.kuali.kra.dao.CurrentReportDao;
import org.kuali.kra.dao.PendingReportDao;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.printing.print.AbstractPrint;
import org.kuali.kra.printing.print.CurrentProposalPrint;
import org.kuali.kra.printing.print.PendingProposalPrint;
import org.kuali.kra.printing.service.CurrentAndPendingReportService;
import org.kuali.kra.printing.service.PrintingService;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Transactional
public class CurrentAndPendingReportServiceImpl implements CurrentAndPendingReportService {
    private CurrentReportDao currentReportDao;
    private PendingReportDao pendingReportDao;
    private PrintingService printingService;
    private CurrentProposalPrint currentProposalPrint;
    private PendingProposalPrint pendingProposalPrint;

    // setters for dependency injection
    public void setCurrentReportDao(CurrentReportDao currentReportDao) {
        this.currentReportDao = currentReportDao;
    }
    public void setPendingReportDao(PendingReportDao pendingReportDao) {
        this.pendingReportDao = pendingReportDao;
    }
    public void setCurrentProposalPrint(CurrentProposalPrint currentProposalPrint) {
        this.currentProposalPrint = currentProposalPrint;
    }
    public void setPendingProposalPrint(PendingProposalPrint pendingProposalPrint) {
        this.pendingProposalPrint = pendingProposalPrint;
    }
    public void setPrintingService(PrintingService printingService) {
        this.printingService = printingService;
    }

    /**
     * @see org.kuali.kra.printing.service.CurrentAndPendingReportService#loadCurrentReportData(java.lang.String)
     */
    public List<CurrentReportBean> loadCurrentReportData(String personId) {
        List<CurrentReportBean> data;
        try {
            data = currentReportDao.queryForCurrentSupport(personId);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    /**
     * @see org.kuali.kra.printing.service.CurrentAndPendingReportService#loadPendingReportData(java.lang.String)
     */
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
     * @see org.kuali.kra.printing.service.CurrentAndPendingReportService#printCurrentAndPendingReportReport(java.lang.String, java.util.Map)
     */
    public AttachmentDataSource printCurrentAndPendingSupportReport(
            String reportName, Map<String, Object> reportParameters) throws PrintingException {
        AttachmentDataSource source = null;
        AbstractPrint printable = null;
        if (reportName.equals(CURRENT_REPORT_TYPE)) {
            reportParameters.put(CURRENT_REPORT_BEANS_KEY, loadCurrentReportData((String)reportParameters.get(PERSON_ID_KEY)));
            printable = currentProposalPrint;
        } else if (reportName
                .equals(PENDING_REPORT_TYPE)) {
            reportParameters.put(PENDING_REPORT_BEANS_KEY, loadPendingReportData((String)reportParameters.get(PERSON_ID_KEY)));
            printable = pendingProposalPrint;
        }
        printable.setPrintableBusinessObject(null);
        printable.setReportParameters(reportParameters);
        source = printingService.print(printable);
        source.setFileName(reportName.replace(' ', '_')+Constants.PDF_FILE_EXTENSION);
        return source;
    }


}
