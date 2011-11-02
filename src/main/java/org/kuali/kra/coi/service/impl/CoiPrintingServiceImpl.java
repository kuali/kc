/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.coi.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.coi.print.*;
import org.kuali.kra.coi.service.CoiPrintingService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.printing.Printable;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.printing.print.AbstractPrint;
import org.kuali.kra.printing.service.impl.PrintingServiceImpl;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;

//TODO: Needs work.  Lots of work.

/**
 * 
 * This class implements the CoiPrintingService.
 */
public class CoiPrintingServiceImpl extends PrintingServiceImpl implements CoiPrintingService {

    private static final String ERROR_MESSAGE = "Unknown report type specified";
    
    private CoiTemplatePrint coiTemplatePrint;
    private CoiCorrespondenceTemplatePrint coiCorrespondenceTemplatePrint;
    private CoiBatchCorrespondencePrint coiBatchCorrespondencePrint;

    /**
     * {@inheritDoc}
     */
    public AbstractPrint getCoiPrintable(CoiReportType reportType) {
        AbstractPrint printable = null;
        
        switch(reportType) {
            case COI_TEMPLATE:
                printable = getCoiTemplatePrint();
                break;
            case COI_CORRESPONDENCE_TEMPLATE:
                printable = getCoiCorrespondenceTemplatePrint();
                break;
            case COI_BATCH_CORRESPONDENCE:
                printable = getCoiBatchCorrespondencePrint();
                break;
            default :
                throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        
        return printable;
    }
    
    //TODO
    @Override
    public AttachmentDataSource printCoiDisclosureReport(KraPersistableBusinessObjectBase printableBusinessObject, 
                                                         String reportName, Map<String, Object> reportParameters) throws PrintingException {
        return null;
    }

    @Override
    public AttachmentDataSource print(List<Printable> printableArtifactList) throws PrintingException {
        AttachmentDataSource attachmentDataSource = super.print(printableArtifactList);

        String fileName = "CoiReport" + Constants.PDF_FILE_EXTENSION;
        try {
            attachmentDataSource.setFileName(URLEncoder.encode(fileName,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            attachmentDataSource.setFileName(fileName);
        }
        attachmentDataSource.setContentType(Constants.PDF_REPORT_CONTENT_TYPE);

        return attachmentDataSource;
    }
    
    public CoiTemplatePrint getCoiTemplatePrint() {
        return coiTemplatePrint;
    }

    public void setCoiTemplatePrint(CoiTemplatePrint coiTemplatePrint) {
        this.coiTemplatePrint = coiTemplatePrint;
    }

    public CoiCorrespondenceTemplatePrint getCoiCorrespondenceTemplatePrint() {
        return coiCorrespondenceTemplatePrint;
    }

    public void setCoiCorrespondenceTemplatePrint(CoiCorrespondenceTemplatePrint coiCorrespondenceTemplatePrint) {
        this.coiCorrespondenceTemplatePrint = coiCorrespondenceTemplatePrint;
    }

    public void setCoiBatchCorrespondencePrint(CoiBatchCorrespondencePrint coiBatchCorrespondencePrint) {
        this.coiBatchCorrespondencePrint = coiBatchCorrespondencePrint;
    }

    public CoiBatchCorrespondencePrint getCoiBatchCorrespondencePrint() {
        return coiBatchCorrespondencePrint;
    }

}
