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

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.kra.protocol.ProtocolDocumentBase;

public class ProtocolActionPrintEvent<T extends KcBusinessRule> extends KcDocumentEventBaseExtension {

    private Boolean summaryReport;
    private Boolean fullReport;
    private Boolean historyReport;
    private Boolean reviewCommentsReport;

    public ProtocolActionPrintEvent(ProtocolDocumentBase document, Boolean summaryReport, Boolean fullReport,Boolean historyReport ,Boolean reviewCommentsReport) {
        super("ProtocolBase Print", "", document);
        this.summaryReport = summaryReport;
        this.fullReport = fullReport;
        this.historyReport = historyReport;
        this.reviewCommentsReport = reviewCommentsReport;
    }
    
    public ProtocolDocumentBase getProtocolDocument() {
        return (ProtocolDocumentBase) getDocument();
    }
    

    @Override
    public KcBusinessRule getRule() {
        return new ProtocolActionPrintRule();
    }

    public Boolean getSummaryReport() {
        return summaryReport;
    }

    public void setSummaryReport(Boolean summaryReport) {
        this.summaryReport = summaryReport;
    }

    public Boolean getFullReport() {
        return fullReport;
    }

    public void setFullReport(Boolean fullReport) {
        this.fullReport = fullReport;
    }

    public Boolean getHistoryReport() {
        return historyReport;
    }

    public void setHistoryReport(Boolean historyReport) {
        this.historyReport = historyReport;
    }

    public Boolean getReviewCommentsReport() {
        return reviewCommentsReport;
    }

    public void setReviewCommentsReport(Boolean reviewCommentsReport) {
        this.reviewCommentsReport = reviewCommentsReport;
    }

}
