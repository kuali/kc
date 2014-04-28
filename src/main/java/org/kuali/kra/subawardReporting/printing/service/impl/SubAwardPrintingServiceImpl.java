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
package org.kuali.kra.subawardReporting.printing.service.impl;


import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.printing.service.AwardPrintingService;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.SponsorFormTemplate;
import org.kuali.kra.bo.SponsorFormTemplateList;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.printing.print.AbstractPrint;
import org.kuali.kra.printing.service.PrintingService;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.bo.SubAwardForms;
import org.kuali.kra.subaward.bo.SubAwardPrintAgreement;
import org.kuali.kra.subaward.bo.SubawardTemplateType;
import org.kuali.kra.subawardReporting.printing.SubAwardPrintType;
import org.kuali.kra.subawardReporting.printing.print.SubAwardFDPAgreement;
import org.kuali.kra.subawardReporting.printing.print.SubAwardFDPModification;
import org.kuali.kra.subawardReporting.printing.print.SubAwardSF294Print;
import org.kuali.kra.subawardReporting.printing.print.SubAwardSF295Print;
import org.kuali.kra.subawardReporting.printing.service.SubAwardPrintingService;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;



/**
 * This class is the implementation of {@link AwardPrintingService}. It has
 * capability to print report related to Negotiation 
 * 
 * @author
 * 
 */
public class SubAwardPrintingServiceImpl implements SubAwardPrintingService {
    
    private static final String SF_295_REPORT = "SF295";
    private static final String SF_294_REPORT = "SF294";
    private static final String SUB_AWARD_FDP_TEMPLATE = "fdpAgreement";
    private static final String SUB_AWARD_FDP_MODIFICATION = "fdpModification";
    
    private SubAwardSF294Print subAwardSF294Print;
    private SubAwardSF295Print subAwardSF295Print;
    private PrintingService printingService;     
    private SubAwardFDPModification subAwardFDPModification;
    private SubAwardFDPAgreement subAwardFDPAgreement;
    private BusinessObjectService businessObjectService;
    


    /**
     * Gets the businessObjectService attribute. 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }


    /**
     * Sets the businessObjectService attribute value.
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }


    /**
     * Gets the subAwardFDPModification attribute. 
     * @return Returns the subAwardFDPModification.
     */
    public SubAwardFDPModification getSubAwardFDPModification() {
        return subAwardFDPModification;
    }


    /**
     * Sets the subAwardFDPModification attribute value.
     * @param subAwardFDPModification The subAwardFDPModification to set.
     */
    public void setSubAwardFDPModification(SubAwardFDPModification subAwardFDPModification) {
        this.subAwardFDPModification = subAwardFDPModification;
    }


    /**
     * Gets the subAwardFDPAgreement attribute. 
     * @return Returns the subAwardFDPAgreement.
     */
    public SubAwardFDPAgreement getSubAwardFDPAgreement() {
        return subAwardFDPAgreement;
    }


    /**
     * Sets the subAwardFDPAgreement attribute value.
     * @param subAwardFDPAgreement The subAwardFDPAgreement to set.
     */
    public void setSubAwardFDPAgreement(SubAwardFDPAgreement subAwardFDPAgreement) {
        this.subAwardFDPAgreement = subAwardFDPAgreement;
    }


    @Override
    public AttachmentDataSource printSubAwardReport(KraPersistableBusinessObjectBase awardDocument,
            SubAwardPrintType subAwardPrintType, Map<String, Object> reportParameters) throws PrintingException {
        AttachmentDataSource source = null;
        AbstractPrint printable = null;         
        if (reportParameters.get("printType") != null) {
            if(reportParameters.get("printType").equals(SF_295_REPORT)){
                printable = getSubAwardSF295Print();                
            } else {
                printable = getSubAwardSF294Print();                
            }
        }  
        
        Award award=(Award) awardDocument;        
        printable.setPrintableBusinessObject(awardDocument);
        printable.setReportParameters(reportParameters);       
        source = getPrintingService().print(printable);        
        source.setContentType(Constants.PDF_REPORT_CONTENT_TYPE);
        
        if(reportParameters.get("printType").equals(SF_295_REPORT)){
            source.setFileName(SF_295_REPORT);
        } else {
            source.setFileName(SF_294_REPORT);
        }
        return source;   
    }
    
    
    public PrintingService getPrintingService() {
        return printingService;
    }


    public void setPrintingService(PrintingService printingService) {
        this.printingService = printingService;
    }

    public SubAwardSF294Print getSubAwardSF294Print() {
        return subAwardSF294Print;
    }


    public void setSubAwardSF294Print(SubAwardSF294Print subAwardSF294Print) {
        this.subAwardSF294Print = subAwardSF294Print;
    }    


    public SubAwardSF295Print getSubAwardSF295Print() {
        return subAwardSF295Print;
    }


    public void setSubAwardSF295Print(SubAwardSF295Print subAwardSF295Print) {
        this.subAwardSF295Print = subAwardSF295Print;
    }
    
    @Override
    public AttachmentDataSource printSubAwardFDPReport(KraPersistableBusinessObjectBase subAwardDoc,SubAwardPrintType subAwardPrintType, Map<String, Object> reportParameters) throws PrintingException {
        AttachmentDataSource source = null;
        AbstractPrint printable = null;         
        if (reportParameters.get("fdpType") != null) {
            if(reportParameters.get("fdpType").equals(SUB_AWARD_FDP_TEMPLATE)){
                printable = getSubAwardFDPAgreement();                
            } else {
                printable = getSubAwardFDPModification();                
            }
        }  
        SubAward subAward = (SubAward)subAwardDoc;
        printable.setPrintableBusinessObject(subAwardDoc);
        printable.setReportParameters(reportParameters);       
        source = getPrintingService().print(printable); 
        source.setContentType(Constants.PDF_REPORT_CONTENT_TYPE);
        
        if(reportParameters.get("fdpType").equals(SUB_AWARD_FDP_TEMPLATE)){
            source.setFileName(SUB_AWARD_FDP_TEMPLATE);
        } else {
            source.setFileName(SUB_AWARD_FDP_MODIFICATION);
        }
        return source;   
    }
    /**
     * This method gets the  form template from the given sponsor form table
     * 
     * 
     * @param sponsorFormTemplateLists -
     *            list of sponsor form template list
     * @return list of sponsor form template
     */
    public List<SubAwardForms> getSponsorFormTemplates(SubAwardPrintAgreement subAwardPrint) {
        List<SubAwardForms> printFormTemplates = new ArrayList<SubAwardForms>();
        if(subAwardPrint.getFdpType().equals(SUB_AWARD_FDP_TEMPLATE)){
            printFormTemplates.add(getBusinessObjectService().findBySinglePrimaryKey(SubAwardForms.class, "FDP Template"));
        }else if(subAwardPrint.getFdpType().equals(SUB_AWARD_FDP_MODIFICATION))
        {
            printFormTemplates.add(getBusinessObjectService().findBySinglePrimaryKey(SubAwardForms.class, "FDP Modification"));
        }
        if(subAwardPrint.getAttachment3A()){
            printFormTemplates.add(getBusinessObjectService().findBySinglePrimaryKey(SubAwardForms.class, "FDP_ATT_3A"));
        }
        if(subAwardPrint.getAttachment3B()){
            printFormTemplates.add(getBusinessObjectService().findBySinglePrimaryKey(SubAwardForms.class, "FDP_ATT_3B"));
        }
        if(subAwardPrint.getAttachment3BPage2()){
            printFormTemplates.add(getBusinessObjectService().findBySinglePrimaryKey(SubAwardForms.class, "FDP_ATT_3B_2"));
        }
        return printFormTemplates;
    }
}
