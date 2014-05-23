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
package org.kuali.kra.subaward.reporting.printing.service.impl;

import org.kuali.coeus.common.framework.print.AbstractPrint;
import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.common.framework.print.PrintingService;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.printing.service.AwardPrintingService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.bo.SubAwardForms;
import org.kuali.kra.subaward.bo.SubAwardPrintAgreement;
import org.kuali.kra.subaward.reporting.printing.SubAwardPrintType;
import org.kuali.kra.subaward.reporting.printing.print.SubAwardSF294Print;
import org.kuali.kra.subaward.reporting.printing.print.SubAwardSF295Print;
import org.kuali.kra.subaward.reporting.printing.service.SubAwardPrintingService;
import org.kuali.kra.subawardReporting.printing.print.SubAwardFDPAgreement;
import org.kuali.kra.subawardReporting.printing.print.SubAwardFDPModification;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.ArrayList;
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
        return KcServiceLocator.getService(BusinessObjectService.class);
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
    public AttachmentDataSource printSubAwardReport(KcPersistableBusinessObjectBase awardDocument,
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
        source.setType(Constants.PDF_REPORT_CONTENT_TYPE);
        
        if(reportParameters.get("printType").equals(SF_295_REPORT)){
            source.setName(SF_295_REPORT);
        } else {
            source.setName(SF_294_REPORT);
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
    public AttachmentDataSource printSubAwardFDPReport(KcPersistableBusinessObjectBase subAwardDoc,SubAwardPrintType subAwardPrintType, Map<String, Object> reportParameters) throws PrintingException {
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
        source.setType(Constants.PDF_REPORT_CONTENT_TYPE);
        
        if(reportParameters.get("fdpType").equals(SUB_AWARD_FDP_TEMPLATE)){
            source.setName(SUB_AWARD_FDP_TEMPLATE);
        } else {
            source.setName(SUB_AWARD_FDP_MODIFICATION);
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
        if(subAwardPrint.getAttachment4()){
            printFormTemplates.add(getBusinessObjectService().findBySinglePrimaryKey(SubAwardForms.class, "FDP_ATT_4"));
        }
        if(subAwardPrint.getAfosrSponsor()){
            printFormTemplates.add(getBusinessObjectService().findBySinglePrimaryKey(SubAwardForms.class, "FDP_AFOSR"));
         }
         if(subAwardPrint.getAmrmcSponsor()){
             printFormTemplates.add(getBusinessObjectService().findBySinglePrimaryKey(SubAwardForms.class, "FDP_AMRMC"));
         }
          if(subAwardPrint.getAroSponsor()){
              printFormTemplates.add(getBusinessObjectService().findBySinglePrimaryKey(SubAwardForms.class, "FDP_ARO"));
          }
          if(subAwardPrint.getDoeSponsor()){
              printFormTemplates.add(getBusinessObjectService().findBySinglePrimaryKey(SubAwardForms.class, "FDP_DOE"));
          }
          if(subAwardPrint.getEpaSponsor()){
              printFormTemplates.add(getBusinessObjectService().findBySinglePrimaryKey(SubAwardForms.class, "FDP_EPA"));
          }
          if(subAwardPrint.getNasaSponsor()){
              printFormTemplates.add(getBusinessObjectService().findBySinglePrimaryKey(SubAwardForms.class, "FDP_NASA"));
          }
          if(subAwardPrint.getNihSponsor()){
              printFormTemplates.add(getBusinessObjectService().findBySinglePrimaryKey(SubAwardForms.class, "FDP_NIH"));
          }
          if(subAwardPrint.getNsfSponsor()){
              printFormTemplates.add(getBusinessObjectService().findBySinglePrimaryKey(SubAwardForms.class, "FDP_NSF"));
          }
          if(subAwardPrint.getOnrSponsor()){
              printFormTemplates.add(getBusinessObjectService().findBySinglePrimaryKey(SubAwardForms.class, "FDP_ONR"));
          }
          if(subAwardPrint.getUsdaSponsor()){
              printFormTemplates.add(getBusinessObjectService().findBySinglePrimaryKey(SubAwardForms.class, "FDP_USDA"));
          }
        return printFormTemplates;
    }
}
