package org.kuali.coeus.common.proposal.impl.report;

import org.kuali.coeus.common.framework.print.AbstractPrint;
import org.kuali.coeus.common.framework.print.PrintConstants;
import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.common.framework.print.PrintingService;
import org.kuali.coeus.common.proposal.framework.report.CurrentAndPendingReportService;
import org.kuali.coeus.common.framework.print.CurrentReportBean;
import org.kuali.coeus.common.framework.print.PendingReportBean;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 *
 */
@Transactional
@Component("currentAndPendingReportService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CurrentAndPendingReportServiceImpl implements CurrentAndPendingReportService {

    @Autowired
    @Qualifier("currentReportDao")
    private CurrentReportDao currentReportDao;

    @Autowired
    @Qualifier("pendingReportDao")
    private PendingReportDao pendingReportDao;

    @Autowired
    @Qualifier("printingService")
    private PrintingService printingService;

    @Autowired
    @Qualifier("currentProposalPrint")
    private CurrentProposalPrint currentProposalPrint;

    @Autowired
    @Qualifier("pendingProposalPrint")
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
        source.setName(reportName.replace(' ', '_')+Constants.PDF_FILE_EXTENSION);
        return source;
    }


}
