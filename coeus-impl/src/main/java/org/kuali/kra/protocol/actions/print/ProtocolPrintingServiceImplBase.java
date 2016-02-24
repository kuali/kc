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
package org.kuali.kra.protocol.actions.print;

import org.kuali.coeus.common.framework.print.AbstractPrint;
import org.kuali.coeus.common.framework.print.Printable;
import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.common.impl.print.PrintingServiceImpl;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolFormBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * This class to implement the getProtocolPrintable.
 */
public abstract class ProtocolPrintingServiceImplBase extends PrintingServiceImpl implements ProtocolPrintingService {

    protected static final String ERROR_MESSAGE = "Unknown report type specified";
    private ProtocolFullProtocolPrintBase protocolFullProtocolPrint;
    private ProtocolHistoryPrintBase protocolHistoryPrint;
    private ProtocolReviewCommentsPrintBase protocolReviewCommentsPrint;
    private ProtocolSummaryViewPrintBase protocolSummaryViewPrint;
    private static final String FILE_NAME_EXTENSION = "report.pdf";
    private static final String REPORT_PREFIX = "ProtocolBase-";

    private static final Map<String, String> PRINTTAG_MAP = new HashMap<String, String>() {
        {
            put("summary", "PROTOCOL_SUMMARY_VIEW_REPORT");
            put("full", "PROTOCOL_FULL_PROTOCOL_REPORT");
            put("history", "PROTOCOL_PROTOCOL_HISTORY_REPORT");
            put("comments", "PROTOCOL_REVIEW_COMMENTS_REPORT");
    }};
    
    private String reportName;

    public Printable getProtocolPrintable(ProtocolPrintType reportType) {
        Printable printable = null;
        ProtocolPrintHelper printHelper = getProtocolPrintHelper(reportType);
        switch(reportType) {
            case PROTOCOL_FULL_PROTOCOL_REPORT :
                getProtocolFullProtocolPrint().setPrintHelper(printHelper);
                printable = getProtocolFullProtocolPrint();
                break;
            case PROTOCOL_PROTOCOL_HISTORY_REPORT :
                getProtocolHistoryPrint().setPrintHelper(printHelper);
                printable = getProtocolHistoryPrint();
                break;
            case PROTOCOL_REVIEW_COMMENTS_REPORT :
                getProtocolReviewCommentsPrint().setPrintHelper(printHelper);
                printable = getProtocolReviewCommentsPrint();
                break;
            case PROTOCOL_SUMMARY_VIEW_REPORT :
                getProtocolSummaryViewPrint().setPrintHelper(printHelper);
                printable = getProtocolSummaryViewPrint();
                break;
            default :
                throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        return printable;
    }
    
    @Override
     public Printable getProtocolPrintArtifacts(ProtocolBase protocol) { 
         ProtocolPrintType printType = ProtocolPrintType.PROTOCOL_FULL_PROTOCOL_REPORT;
         AbstractPrint printable = (AbstractPrint)getProtocolPrintable(printType);
         printable.setPrintableBusinessObject(protocol);
         return printable;
     }
        
    public ProtocolFullProtocolPrintBase getProtocolFullProtocolPrint() {
        return protocolFullProtocolPrint;
    }

    public void setProtocolFullProtocolPrint(ProtocolFullProtocolPrintBase protocolFullProtocolPrint) {
        this.protocolFullProtocolPrint = protocolFullProtocolPrint;
    }

    public ProtocolHistoryPrintBase getProtocolHistoryPrint() {
        return protocolHistoryPrint;
    }

    public void setProtocolHistoryPrint(ProtocolHistoryPrintBase protocolHistoryPrint) {
        this.protocolHistoryPrint = protocolHistoryPrint;
    }

    public ProtocolReviewCommentsPrintBase getProtocolReviewCommentsPrint() {
        return protocolReviewCommentsPrint;
    }

    public void setProtocolReviewCommentsPrint(ProtocolReviewCommentsPrintBase protocolReviewCommentsPrint) {
        this.protocolReviewCommentsPrint = protocolReviewCommentsPrint;
    }

    public ProtocolSummaryViewPrintBase getProtocolSummaryViewPrint() {
        return protocolSummaryViewPrint;
    }

    public void setProtocolSummaryViewPrint(ProtocolSummaryViewPrintBase protocolSummaryViewPrint) {
        this.protocolSummaryViewPrint = protocolSummaryViewPrint;
    }
    
    @Override
    public String getReportName() {
        return reportName;
    }

    /**
     * Sets the reportName attribute value.
     * @param reportName The reportName to set.
     */
    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public AttachmentDataSource print(String reportName, List<Printable> printableArtifactList) throws PrintingException {
        setReportName(reportName);
        return super.print(printableArtifactList);
    }
    

    /*
     * set up all artifacts and filename
     */
    protected List<Printable> getPrintReportArtifacts(ProtocolFormBase protocolForm, StringBuffer fileName) {
        Boolean printSummary = protocolForm.getActionHelper().getSummaryReport();
        Boolean printFull = protocolForm.getActionHelper().getFullReport();
        Boolean printHistory = protocolForm.getActionHelper().getHistoryReport();
        Boolean printReviewComments = protocolForm.getActionHelper().getReviewCommentsReport();
        ProtocolBase protocol = protocolForm.getProtocolDocument().getProtocol();
        
        List<Printable> printableArtifactList = new ArrayList<Printable>();
        if (printSummary) {
            Map reportParameters = getReportOptions(protocolForm,ProtocolPrintType.PROTOCOL_SUMMARY_VIEW_REPORT);
            printableArtifactList.add(getPrintableArtifacts(protocol, "summary", fileName,reportParameters));
            protocolForm.getActionHelper().setSummaryReport(false);
        }
        if (printFull) {
            Map reportParameters = getReportOptions(protocolForm,ProtocolPrintType.PROTOCOL_FULL_PROTOCOL_REPORT);
            printableArtifactList.add(getPrintableArtifacts(protocol, "full", fileName,reportParameters));
            protocolForm.getActionHelper().setFullReport(false);
        }
        if (printHistory) {
            Map reportParameters = getReportOptions(protocolForm,ProtocolPrintType.PROTOCOL_PROTOCOL_HISTORY_REPORT);
            printableArtifactList.add(getPrintableArtifacts(protocol, "history", fileName,reportParameters));
            protocolForm.getActionHelper().setHistoryReport(false);
        }
        if (printReviewComments) {
            Map reportParameters = getReportOptions(protocolForm,ProtocolPrintType.PROTOCOL_REVIEW_COMMENTS_REPORT);
            printableArtifactList
                    .add(getPrintableArtifacts(protocol, "comments", fileName,reportParameters));
            protocolForm.getActionHelper().setReviewCommentsReport(false);
        }
        fileName.append(FILE_NAME_EXTENSION);
        return printableArtifactList;
    }

    protected Map<Class,Object> getReportOptions(ProtocolFormBase protocolForm, ProtocolPrintType printType) {
        Map<Class,Object> reportParameters = new HashMap<Class, Object>();
        ProtocolSummaryPrintOptions summaryOptions = protocolForm.getActionHelper().getProtocolPrintOption();
        if(printType.equals(ProtocolPrintType.PROTOCOL_FULL_PROTOCOL_REPORT)){
            summaryOptions.setActions(true);
            summaryOptions.setAmendmentRenewalHistory(true);
            summaryOptions.setAmmendmentRenewalSummary(true);
            summaryOptions.setAreaOfResearch(true);
            summaryOptions.setAttachments(true);
            summaryOptions.setCorrespondents(true);
            summaryOptions.setDocuments(true);
            summaryOptions.setFundingSource(true);
            summaryOptions.setInvestigator(true);
            summaryOptions.setNotes(true);
            summaryOptions.setOrganizaition(true);
            summaryOptions.setProtocolDetails(true);
            summaryOptions.setReferences(true);
            summaryOptions.setRiskLevel(true);
            summaryOptions.setRoles(true);
            summaryOptions.setSpecialReview(true);
            summaryOptions.setStudyPersonnels(true);
            summaryOptions.setSubjects(true);
        }
        reportParameters.put(ProtocolSummaryPrintOptions.class, summaryOptions);
        return reportParameters;
    }

    protected Printable getPrintableArtifacts(ProtocolBase protocol, String reportType, StringBuffer fileName,Map reportParameters) {
        ProtocolPrintType printType = ProtocolPrintType.valueOf(PRINTTAG_MAP.get(reportType));

        AbstractPrint printable = (AbstractPrint)getProtocolPrintable(printType);
        printable.setPrintableBusinessObject(protocol);
        printable.setReportParameters(reportParameters);
        fileName.append(reportType).append("-");
        return printable;
    }

    protected List<Printable> getPrintArtifacts(ProtocolFormBase protocolForm) {
        List<Printable> printableArtifactList = new ArrayList<Printable>();
        ProtocolPrintType printType = ProtocolPrintType.valueOf(PRINTTAG_MAP.get("full"));
        AbstractPrint printable = (AbstractPrint)getProtocolPrintable(printType);
        printable.setPrintableBusinessObject(protocolForm.getProtocolDocument().getProtocol());
        Map reportParameters = new HashMap();
        ProtocolSummaryPrintOptions summaryOptions = protocolForm.getActionHelper().getProtocolPrintOption();
        reportParameters.put(ProtocolSummaryPrintOptions.class, summaryOptions);
        printable.setReportParameters(reportParameters);
        printableArtifactList.add(printable);
        if (summaryOptions.isReviewComments()) {
            Map reportParameters1 = getReportOptions(protocolForm,ProtocolPrintType.PROTOCOL_REVIEW_COMMENTS_REPORT);
            AbstractPrint printable1 = (AbstractPrint)getProtocolPrintable(ProtocolPrintType.valueOf(PRINTTAG_MAP.get("comments")));
            printable1.setPrintableBusinessObject(protocolForm.getProtocolDocument().getProtocol());
            printable1.setReportParameters(reportParameters1);
            printableArtifactList.add(printable1);
            
        }
        return printableArtifactList;
    }
    
    public AttachmentDataSource printProtocolDocument(ProtocolFormBase protocolForm) throws PrintingException {
        ProtocolBase protocol = protocolForm.getProtocolDocument().getProtocol();
        StringBuffer fileName = new StringBuffer().append(REPORT_PREFIX);
        String protocolNumber = protocol.getProtocolNumber();
        ProtocolPrintHelper printHelper = getProtocolPrintHelper(ProtocolPrintType.PROTOCOL_FULL_PROTOCOL_REPORT);
        String reportPrintName = printHelper.getReportName();
        String reportName = protocolNumber + "-" + reportPrintName;
        return  print(reportName,getPrintReportArtifacts(protocolForm, fileName));
    }

    public AttachmentDataSource printProtocolSelectedItems(ProtocolFormBase protocolForm) throws PrintingException {
        ProtocolBase protocol = protocolForm.getProtocolDocument().getProtocol();
        ProtocolPrintHelper printHelper = getProtocolPrintHelper(ProtocolPrintType.PROTOCOL_FULL_PROTOCOL_REPORT);
        String reportName = protocol.getProtocolNumber() + "-" + printHelper.getReportName();
        AttachmentDataSource dataSource = print(reportName, getPrintArtifacts(protocolForm));
        dataSource.setName(getProtocolPrintHelper(ProtocolPrintType.PROTOCOL_FULL_PROTOCOL_REPORT).getFileName());
        return dataSource;
    }
    
    private ProtocolPrintHelper getProtocolPrintHelper(ProtocolPrintType printType) {
        return getProtocolPrintParameterHook().get(printType);
    }
    
    protected abstract HashMap<ProtocolPrintType, ProtocolPrintHelper> getProtocolPrintParameterHook();

}
