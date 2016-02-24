/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
    public AttachmentDataSource printCurrentReport(Map<String, Object> reportParameters) throws PrintingException {
        reportParameters.put(PrintConstants.CURRENT_REPORT_BEANS_KEY, loadCurrentReportData((String)reportParameters.get(PrintConstants.PERSON_ID_KEY)));
        AbstractPrint printable = currentProposalPrint;

        printable.setPrintableBusinessObject(null);
        printable.setReportParameters(reportParameters);
        AttachmentDataSource source = printingService.print(printable);
        source.setName(PrintConstants.CURRENT_REPORT_TYPE.replace(' ', '_')+Constants.PDF_FILE_EXTENSION);
        return source;
    }

    @Override
    public AttachmentDataSource printPendingReport(Map<String, Object> reportParameters) throws PrintingException {
        reportParameters.put(PrintConstants.PENDING_REPORT_BEANS_KEY, loadPendingReportData((String)reportParameters.get(PrintConstants.PERSON_ID_KEY)));
        AbstractPrint printable = pendingProposalPrint;

        printable.setPrintableBusinessObject(null);
        printable.setReportParameters(reportParameters);
        AttachmentDataSource source= printingService.print(printable);
        source.setName(PrintConstants.PENDING_REPORT_TYPE.replace(' ', '_')+Constants.PDF_FILE_EXTENSION);
        return source;
    }




}
