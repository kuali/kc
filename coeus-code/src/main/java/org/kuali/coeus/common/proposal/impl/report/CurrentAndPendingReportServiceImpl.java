package org.kuali.coeus.common.proposal.impl.report;

import org.kuali.coeus.common.framework.print.AbstractPrint;
import org.kuali.coeus.common.framework.print.PrintConstants;
import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.common.framework.print.PrintingService;
import org.kuali.coeus.common.proposal.framework.report.CurrentAndPendingReportService;
import org.kuali.kra.common.printing.CurrentReportBean;
import org.kuali.kra.common.printing.PendingReportBean;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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

    @Override
    public List<CurrentReportBean> loadCurrentReportData(String personId) {
        List<CurrentReportBean> data;
        try {
            data = currentReportDao.queryForCurrentSupport(personId);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    @Override
    public List<PendingReportBean> loadPendingReportData(String personId) {
        List<PendingReportBean> data;
        try {
            data = pendingReportDao.queryForPendingSupport(personId);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    @Override
    public AttachmentDataSource printCurrentAndPendingSupportReport(
            String reportName, Map<String, Object> reportParameters) throws PrintingException {
        AttachmentDataSource source = null;
        AbstractPrint printable = null;
        if (reportName.equals(PrintConstants.CURRENT_REPORT_TYPE)) {
            reportParameters.put(PrintConstants.CURRENT_REPORT_BEANS_KEY, loadCurrentReportData((String)reportParameters.get(PrintConstants.PERSON_ID_KEY)));
            printable = currentProposalPrint;
        } else if (reportName
                .equals(PrintConstants.PENDING_REPORT_TYPE)) {
            reportParameters.put(PrintConstants.PENDING_REPORT_BEANS_KEY, loadPendingReportData((String)reportParameters.get(PrintConstants.PERSON_ID_KEY)));
            printable = pendingProposalPrint;
        }
        printable.setPrintableBusinessObject(null);
        printable.setReportParameters(reportParameters);
        source = printingService.print(printable);
        source.setFileName(reportName.replace(' ', '_')+Constants.PDF_FILE_EXTENSION);
        return source;
    }


}
