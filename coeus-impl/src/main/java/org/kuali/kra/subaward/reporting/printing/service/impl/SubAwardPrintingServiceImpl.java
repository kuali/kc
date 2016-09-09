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
package org.kuali.kra.subaward.reporting.printing.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.print.AbstractPrint;
import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.common.framework.print.PrintingService;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.award.awardhierarchy.AwardHierarchyService;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.printing.service.AwardPrintingService;
import org.kuali.kra.award.version.service.AwardVersionService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.kra.subaward.bo.*;

import org.kuali.kra.subaward.reporting.printing.SubAwardPrintType;
import org.kuali.kra.subaward.reporting.printing.print.SubAwardSF294Print;
import org.kuali.kra.subaward.reporting.printing.print.SubAwardSF295Print;
import org.kuali.kra.subaward.reporting.printing.service.SubAwardPrintingService;
import org.kuali.kra.subawardReporting.printing.print.SubAwardFDPAgreement;
import org.kuali.kra.subawardReporting.printing.print.SubAwardFDPModification;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.*;


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
    private static final String FDP_FROM_PARENT_AWARD = "FDP_FROM_PARENT_AWARD";
    
    private SubAwardSF294Print subAwardSF294Print;
    private SubAwardSF295Print subAwardSF295Print;
    private PrintingService printingService;       
    private SubAwardFDPModification subAwardFDPModification;
    private SubAwardFDPAgreement subAwardFDPAgreement;
    private BusinessObjectService businessObjectService;
    private ParameterService parameterService;
    private AwardVersionService awardVersionService;
    private AwardHierarchyService awardHierarchyService;

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
    

    protected AttachmentDataSource printSubAwardFDPReport(KcPersistableBusinessObjectBase subAwardDoc, Map<String, Object> reportParameters) throws PrintingException {
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
        Map<String, byte[]> formAttachments = new LinkedHashMap<String, byte[]>();
        if(subAward.getSubAwardAttachments() != null) {
            for(SubAwardAttachments subAwardAttachments:subAward.getSubAwardAttachments()) {
                if(subAwardAttachments.getSelectToPrint()) {
                    if(isPdf(subAwardAttachments.getAttachmentContent())) {
                   formAttachments.put(subAwardAttachments.getAttachmentId().toString(),
                            subAwardAttachments.getAttachmentContent());   
                    }
                }
            }
        }
        resetSelectedFormList(subAward.getSubAwardAttachments());
        
        
        printable.setAttachments(formAttachments);
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

    @Override
    public AttachmentDataSource printSubAwardFDPReport(SubAwardPrintAgreement agreement, SubAward subAward) {
        Map<String, Object> reportParameters = new HashMap<>();
        List<SubAwardForms> printFormTemplates = getSponsorFormTemplates(agreement,subAward.getSubAwardForms());

        if(agreement.getFundingSource() != null){
            for (SubAwardFundingSource subAwardFunding : subAward.getSubAwardFundingSourceList()) {
                if(agreement.getFundingSource().equals(subAwardFunding.getSubAwardFundingSourceId().toString())){

                    final Award workingAward = getAwardVersionService().getWorkingAwardVersion(subAwardFunding.getAward().getAwardNumber());
                    final Award award = (workingAward != null) ? workingAward : subAwardFunding.getAward();

                    reportParameters.put("awardNumber",award.getAwardNumber());
                    reportParameters.put("awardTitle",award.getParentTitle());
                    reportParameters.put("sponsorAwardNumber",award.getSponsorAwardNumber());
                    if (award.getSponsor() != null) {
                        reportParameters.put("sponsorName", award.getSponsor().getSponsorName());
                    }
                    reportParameters.put("cfdaNumber",award.getCfdaNumber());
                    reportParameters.put("awardID",award.getAwardId());

                    if (getParameterService().getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_SUBAWARD, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, FDP_FROM_PARENT_AWARD)) {
                        final AwardHierarchy hierarchy = getAwardHierarchyService().loadAwardHierarchy(subAwardFunding.getAward().getAwardNumber());
                        if (hierarchy != null) {
                            reportParameters.put("fain", hierarchy.getRoot().getAward().getFainId());

                            if (hierarchy.getRoot().getAward().getObligatedTotal() != null) {
                                reportParameters.put("obligatedTotal", hierarchy.getRoot().getAward().getObligatedTotal().bigDecimalValue());
                            }

                            if (hierarchy.getRoot().getAward().getAnticipatedTotal() != null) {
                                reportParameters.put("anticipatedTotal", hierarchy.getRoot().getAward().getAnticipatedTotal().bigDecimalValue());
                            }
                        }
                    } else {
                        reportParameters.put("fain", award.getFainId());

                        if (award.getObligatedTotal() != null) {
                            reportParameters.put("obligatedTotal", award.getObligatedTotal().bigDecimalValue());
                        }

                        if (award.getAnticipatedTotal() != null) {
                            reportParameters.put("anticipatedTotal", award.getAnticipatedTotal().bigDecimalValue());
                        }
                    }


                    if (award.getPrimeSponsor() != null) {
                        reportParameters.put("primeSponsorName", award.getPrimeSponsor().getSponsorName());
                    } else {
                        reportParameters.put("primeSponsorName", award.getSponsor().getSponsorName());
                    }

                    if (award.getNoticeDate() != null) {
                        final Calendar noticeDate = Calendar.getInstance();
                        noticeDate.setTime(award.getNoticeDate());
                        reportParameters.put("noticeDate", noticeDate);
                    }

                    break;
                }
            }
        }

        if (!subAward.getSubAwardAmountInfoList().isEmpty()) {
            final int index = subAward.getSubAwardAmountInfoList().size() - 1;
            final String modificationTypeCode = subAward.getSubAwardAmountInfoList().get(index).getModificationTypeCode();
            if (StringUtils.isNotEmpty(modificationTypeCode)) {
                reportParameters.put("modificationType", modificationTypeCode);
            }
        }

        if (!subAward.getSubAwardTemplateInfo().isEmpty()) {
            reportParameters.put("fcoi", subAward.getSubAwardTemplateInfo().get(0).getFcio());
            reportParameters.put("costshare", subAward.getSubAwardTemplateInfo().get(0).getIncludesCostSharing());
            reportParameters.put("randd", subAward.getSubAwardTemplateInfo().get(0).getrAndD());
        }

        reportParameters.put("ffata",subAward.getFfataRequired());

        reportParameters.put(SubAwardPrintingService.SELECTED_TEMPLATES, printFormTemplates);
        reportParameters.put("fdpType",agreement.getFdpType());
        return printSubAwardFDPReport(subAward, reportParameters);
    }

    /**
     * 
     * This method is to reset the selected form list.
     * 
     * @param subAwardFormList
     *            list of subAwardFormList.
     */
    protected void resetsubAwardFormList(
            List<SubAwardForms> subAwardFormList) {
        for (SubAwardForms subAwardFormValues : subAwardFormList) {
            subAwardFormValues.setSelectToPrint(false);
        }
    }

    protected List<SubAwardForms> getSponsorFormTemplates(SubAwardPrintAgreement subAwardPrint, List<SubAwardForms> subAwardFormList) {
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
        if(subAwardFormList != null) {
        for(SubAwardForms subAwardFormValues : subAwardFormList){
            if(subAwardFormValues.getSelectToPrint()){
                String description = subAwardFormValues.getDescription();
                String[] token = description.split("\\s");
                printFormTemplates.add(getBusinessObjectService().findBySinglePrimaryKey(SubAwardForms.class, "FDP_"+token[0]));
            }
        }
        resetsubAwardFormList(subAwardFormList);
        }

        return printFormTemplates;
    }
    
    protected void resetSelectedFormList(
            List<SubAwardAttachments> subAwardFormList) {
        for (SubAwardAttachments subAwardFormValues : subAwardFormList) {
            subAwardFormValues.setSelectToPrint(false);
        }
    }

    @Override
    public boolean isPdf(byte[] data) {
        final int ATTRIBUTE_CHUNK_SIZE = 1200;// increased for ppt
        final String PRE_HEXA = "0x";

        boolean retValue = false;
        String str[] = { "25", "50", "44", "46" };
        byte byteCheckArr[] = new byte[4];
        byte byteDataArr[] = new byte[4];

        for (int byteIndex = 0; byteIndex < byteCheckArr.length; byteIndex++) {
            byteCheckArr[byteIndex] = Integer.decode(PRE_HEXA + str[byteIndex])
                    .byteValue();
        }

        int startPoint, endPoint;

        startPoint = 0;
        endPoint = (ATTRIBUTE_CHUNK_SIZE > (data.length / 2)) ? data.length / 2
                : ATTRIBUTE_CHUNK_SIZE;

        for (int forwardIndex = startPoint; forwardIndex < endPoint
                - str.length; forwardIndex++) {
            if (forwardIndex == 0) {
                // Fill All Data
                for (int fillIndex = 0; fillIndex < str.length; fillIndex++) {
                    byteDataArr[fillIndex] = toUnsignedBytes(data[fillIndex]);
                }
            } else {
                // Push Data, Fill last index
                for (int fillIndex = 0; fillIndex < str.length - 1; fillIndex++) {
                    byteDataArr[fillIndex] = byteDataArr[fillIndex + 1];
                }
                byteDataArr[str.length - 1] = toUnsignedBytes(data[str.length
                        - 1 + forwardIndex]);
            }

            if (new String(byteCheckArr).equals(new String(byteDataArr))) {
                retValue = true;
            }
        }

        
        return retValue;
    }
    public static byte toUnsignedBytes(int intVal) {
        byte byteVal;
        if (intVal > 127) {
            int temp = intVal - 256;
            byteVal = (byte) temp;
        } else {
            byteVal = (byte) intVal;
        }
        return byteVal;
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public AwardVersionService getAwardVersionService() {
        return awardVersionService;
    }

    public void setAwardVersionService(AwardVersionService awardVersionService) {
        this.awardVersionService = awardVersionService;
    }

    public AwardHierarchyService getAwardHierarchyService() {
        return awardHierarchyService;
    }

    public void setAwardHierarchyService(AwardHierarchyService awardHierarchyService) {
        this.awardHierarchyService = awardHierarchyService;
    }
}
