/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
