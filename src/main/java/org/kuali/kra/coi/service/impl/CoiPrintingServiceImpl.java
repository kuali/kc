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
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.print.*;
import org.kuali.kra.coi.service.CoiPrintingService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.printing.Printable;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.printing.print.AbstractPrint;
import org.kuali.kra.printing.service.PrintingService;
import org.kuali.kra.printing.service.impl.PrintingServiceImpl;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.KRADServiceLocator;

//TODO: Needs work.  Lots of work.

/**
 * 
 * This class implements the CoiPrintingService.
 */
public class CoiPrintingServiceImpl  implements CoiPrintingService {

    private PrintingService printingService;
    private CoiCertificationPrint coiCertificationPrint;
    private BusinessObjectService businessObjectService;
    private ConfigurationService configurationService;
    
    public AttachmentDataSource printDisclosureCertification(KraPersistableBusinessObjectBase printableBusinessObject, 
                                                             String reportName, Map<String, Object> reportParameters) throws PrintingException {
        System.out.println("\nNew printDisclosureCertification event occurred.... ");        
        AttachmentDataSource source = null;
        AbstractPrint printable = null;
        printable = getCoiCertificationPrint();
        printable.setPrintableBusinessObject(printableBusinessObject);
        printable.setReportParameters(reportParameters);
        source = getPrintingService().print(printable);
        return source;
    }
        
    public PrintingService getPrintingService() {
        return printingService;
    }
    
    public void setPrintingService(PrintingService printingService) {
        this.printingService = printingService;
    }

    public CoiCertificationPrint getCoiCertificationPrint() {
        return coiCertificationPrint;
    }

    public void setCoiCertificationPrint(CoiCertificationPrint coiCertificationPrint) {
        this.coiCertificationPrint = coiCertificationPrint;
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * 
     * @see org.kuali.kra.irb.actions.print.ProtocolPrintingService#getProtocolPrintArtifacts(org.kuali.kra.irb.Protocol)
     */
     public Printable getCoiPrintArtifacts(CoiDisclosure coiDisclosure) { 
         
         CoiReportType reportType = CoiReportType.COI_BATCH_CORRESPONDENCE;
         AbstractPrint printable = (AbstractPrint)getCoiPrintable(reportType);
         printable.setPrintableBusinessObject(coiDisclosure);
         return printable;
     }
     public AttachmentDataSource print(List<Printable> printableArtifactList) throws PrintingException {
         AttachmentDataSource attachmentDataSource =  getPrintingService().print(printableArtifactList);
           String fileName = "ApprovedDisclosure" + Constants.PDF_FILE_EXTENSION;
           try {
               attachmentDataSource.setFileName(URLEncoder.encode(fileName,"UTF-8"));
           } catch (UnsupportedEncodingException e) {
               attachmentDataSource.setFileName(fileName);
           }
           attachmentDataSource.setContentType(Constants.PDF_REPORT_CONTENT_TYPE);

           return attachmentDataSource;
       }
       

    @Override
    public AbstractPrint getCoiPrintable(CoiReportType reportType) {
    	AbstractPrint printable = null;
        switch(reportType) {
            case COI_APPROVED_DISCLOSURE :
                printable = getCoiCertificationPrint();
                break;
                }
        return printable;
    }
    public ConfigurationService getKualiConfigurationService() {
        return KRADServiceLocator.getKualiConfigurationService();
    }
      
    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }
    }


