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

package org.kuali.kra.award.paymentreports.awardreports.reporting;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.common.framework.print.stream.xml.XmlStream;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTermRecipient;
import org.kuali.kra.award.paymentreports.awardreports.reporting.service.ReportTrackingDao;
import org.kuali.kra.printing.schema.AwardReportingRequirementDocument;
import org.kuali.kra.printing.schema.AwardReportingRequirementDocument.AwardReportingRequirement;
import org.kuali.kra.printing.schema.ReportingRequirement;
import org.kuali.kra.printing.schema.ReportingRequirementDetail;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.DocumentService;

import java.util.*;

public class ReportTrackingXmlStream implements XmlStream {

    private DateTimeService dateTimeService;
    private DocumentService documentService;
    private static final Log LOG = LogFactory.getLog(ReportTrackingXmlStream.class);
    /**
     * This method generates XML committee report. It uses data passed in
     * {@link KcPersistableBusinessObjectBase} for populating the XML nodes. The XMl once
     * generated is returned as {@link XmlObject}
     *
     * @param printableBusinessObject
     *            using which XML is generated
     * @param reportParameters
     *            parameters related to XML generation
     * @return {@link XmlObject} representing the XML
     */
    public Map<String, XmlObject> generateXmlStream(KcPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
        ReportTracking reporTracking=(ReportTracking)printableBusinessObject;
        AwardReportingRequirementDocument awardReportRequirementDoc=AwardReportingRequirementDocument.Factory.newInstance();
        try {       
            awardReportRequirementDoc.setAwardReportingRequirement(getAwardReporting(reporTracking,reportParameters));
        }  catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        Map<String, XmlObject> xmlObjectList = new LinkedHashMap<String, XmlObject>();
        Map<String, XmlObject> map = new HashMap<String,XmlObject>();
        map.put("AwardReportTracking", awardReportRequirementDoc);
        return map;

    }




    /**
     *
     * This method is to get the AwardReporting data for print.
     */
    public AwardReportingRequirement getAwardReporting(KcPersistableBusinessObjectBase
        printableBusinessObject, Map<String, Object> htData) throws Exception{
        ReportTracking reportTracking =
        (ReportTracking) printableBusinessObject;
        AwardReportingRequirement awardReporting =
            AwardReportingRequirement.Factory.newInstance();
        Map<String, String> searchValues = new HashMap<String, String>();
        if(reportTracking.getPiName()!=null)
        searchValues.put("piName", reportTracking.getPiName());
        if(reportTracking.getFrequencyBaseCode()!=null)
        searchValues.put("frequencyBaseCode", reportTracking.getFrequencyBaseCode());
        if(reportTracking.getFrequencyCode()!=null)
        searchValues.put("frequencyCode", reportTracking.getFrequencyCode());
        if(reportTracking.getReportClassCode()!=null)
        searchValues.put("reportClassCode", reportTracking.getReportClassCode());
        if(reportTracking.getReportCode()!=null)
        searchValues.put("reportCode", reportTracking.getReportCode());
        if(reportTracking.getBaseDate()!=null)
        searchValues.put("baseDate", getDateTimeService().toDateString(reportTracking.getBaseDate()));
        if(reportTracking.getSponsorCode()!=null)
        searchValues.put("sponsorCode", reportTracking.getSponsorCode());
        if(reportTracking.getOverdue()!=null)
        searchValues.put("overdue", reportTracking.getOverdue().toString());
        if(reportTracking.getPreparerName()!=null)
        searchValues.put("preparerName", reportTracking.getPreparerName());
        if(reportTracking.getActivityDate()!=null)
        searchValues.put("activityDate",  getDateTimeService().toDateString(reportTracking.getActivityDate()));
        if(reportTracking.getLeadUnitNumber()!=null)
        searchValues.put("leadUnitNumber", reportTracking.getLeadUnitNumber());
        if(reportTracking.getDueDate()!=null)
        searchValues.put("dueDate",  getDateTimeService().toDateString(reportTracking.getDueDate()));
        if(reportTracking.getComments()!=null)
        searchValues.put("comments", reportTracking.getComments());
        if (reportTracking.getOspDistributionCode() != null) {
            searchValues.put("ospDistributionCode", reportTracking.getOspDistributionCode());
        }            
        
        List<String> detailFields = new ArrayList<String>();
        detailFields.add("awardNumber");
        detailFields.add("leadUnitNumber");
        List<ReportTracking> detailResults = KcServiceLocator.getService(ReportTrackingDao.class).getDetailResults(searchValues,detailFields);
        setReportingRequirements(awardReporting, reportTracking,detailResults);
           return awardReporting;
    }
    private void setReportingRequirements(
            AwardReportingRequirement awardReporting,
            ReportTracking reportTracking,List<ReportTracking> detailResults){
        List<ReportingRequirement>reportReqList = new ArrayList<ReportingRequirement>();
        ReportingRequirement reportingRequirement = ReportingRequirement.Factory.newInstance();
        reportingRequirement.setPrincipleInvestigatorName(reportTracking.getPiName());
        reportingRequirement.setReportClass(reportTracking.getReportClass().getDescription());
        if (reportTracking.getFrequency() != null) {
        reportingRequirement.setFrequency(reportTracking.getFrequency().getDescription());
        }
        if (reportTracking.getFrequencyBase() != null) {
        reportingRequirement.setFrequencyBase(reportTracking.getFrequencyBase().getDescription());
        }
        if(reportTracking.getBaseDate()!=null){
            reportingRequirement.setBaseDate(reportTracking.getBaseDate().toString());}
        reportingRequirement.setReportType(reportTracking.getReport().getDescription());
        reportingRequirement.setCopyOSP(reportTracking.getDistribution().getDescription());
        setReportingRequirementsDetail(awardReporting,reportingRequirement,detailResults);

        reportReqList.add(reportingRequirement);
        awardReporting.setCurrentDate(KcServiceLocator.getService(DateTimeService.class).getCurrentCalendar());
        awardReporting.setReportingReqsArray(
                reportReqList.toArray(new ReportingRequirement[0]));

    }
    private void setReportingRequirementsDetail(
            AwardReportingRequirement awardReporting, ReportingRequirement reportingRequirement,List<ReportTracking> detailResults) {
        List<ReportingRequirementDetail>reportReqDetailList = new ArrayList<ReportingRequirementDetail>();

        for(ReportTracking reportTracking : detailResults){
            List<Award> awardList = KcServiceLocator.getService(AwardService.class).findAwardsForAwardNumber(reportTracking.getAwardNumber());
            ReportingRequirementDetail reportReqDetails;
            for (Award award : awardList){
                List <AwardReportTermRecipient> recipientList = new ArrayList<AwardReportTermRecipient>();
                if (!award.getAwardReportTermItems().isEmpty()){
                    List<AwardReportTerm>  awardReportTermItems = award.getAwardReportTermItems();
                    for (AwardReportTerm awardReportTerm :  awardReportTermItems){
                        if ((reportTracking.getAwardReportTermId()).equals(awardReportTerm.getAwardReportTermId())){
                            recipientList=awardReportTerm.getAwardReportTermRecipients();  }  
                    }
                    if(!recipientList.isEmpty()){
                        for (AwardReportTermRecipient awardReportTermRecipient : recipientList) {
                            reportReqDetails = ReportingRequirementDetail.Factory.newInstance();
                            if ((reportTracking.getAwardReportTermId()).equals(awardReportTermRecipient.getAwardReportTerm().getAwardReportTermId())){
                                reportReqDetails.setRecipientName(awardReportTermRecipient.getRolodex().getFullName());
                                reportReqDetails.setRecipientOrganization(awardReportTermRecipient.getRolodex().getOrganization());
                                reportReqDetails.setContact(awardReportTermRecipient.getContactType().getDescription());
                                reportReqDetails.setAddress(awardReportTermRecipient.getRolodex().getAddressLine1()+""+awardReportTermRecipient.getRolodex().getAddressLine2()+""+awardReportTermRecipient.getRolodex().getAddressLine3());
                            }
                            
            reportReqDetails.setAwardNo(reportTracking.getAwardNumber());
            if(reportTracking.getLeadUnit()!=null){
                reportReqDetails.setUnitNo(reportTracking.
                        getLeadUnit().getUnitNumber());      
                reportReqDetails.setUnitName(reportTracking.
                        getLeadUnit().getUnitName());
            }
                            reportReqDetails.setStatus(reportTracking.getReportStatus().getDescription());
            if (reportTracking.getDueDate() != null) {
                reportReqDetails.setDueDate(reportTracking.getDueDate().toString());
            }

            reportReqDetails.setCopies(reportTracking.getItemCount());
            if(reportTracking.getOverdue()!=null){
                reportReqDetails.setOverdueNo(reportTracking.getOverdue());}
            if (reportTracking.getActivityDate() != null) {
                                reportReqDetails.setActivityDate(reportTracking.getActivityDate().toString());
                            }
            
            
                            reportTracking.refreshReferenceObject("awardReportTermRecipient");
                            if(reportTracking.getComments()!=null){
                                reportReqDetails.setComments(reportTracking.getComments());}
                            if(reportTracking.getPreparerName()!=null){
                                reportReqDetails.setPersonName(reportTracking.getPreparerFullname());}
                           
                            reportReqDetailList.add(reportReqDetails);
                        }
                    }
                    else  {
                        reportReqDetails = ReportingRequirementDetail.Factory.newInstance();
                        reportReqDetails.setAwardNo(reportTracking.getAwardNumber());
                        if(reportTracking.getLeadUnit()!=null){
                            reportReqDetails.setUnitNo(reportTracking.
                            getLeadUnit().getUnitNumber());      
                            reportReqDetails.setUnitName(reportTracking.
                            getLeadUnit().getUnitName());
                        }
                        reportReqDetails.setStatus(reportTracking.getReportStatus().getDescription());
                        if (reportTracking.getDueDate() != null) {
                            reportReqDetails.setDueDate(reportTracking.getDueDate().toString());
            }

                        reportReqDetails.setCopies(reportTracking.getItemCount());
                        if(reportTracking.getOverdue()!=null){
                            reportReqDetails.setOverdueNo(reportTracking.getOverdue());}
                        if (reportTracking.getActivityDate() != null) {
                            reportReqDetails.setActivityDate(reportTracking.getActivityDate().toString()); }
       
       
                        reportTracking.refreshReferenceObject("awardReportTermRecipient");

            if(reportTracking.getComments()!=null){
                reportReqDetails.setComments(reportTracking.getComments());}
            if(reportTracking.getPreparerName()!=null){
                            reportReqDetails.setPersonName(reportTracking.getPreparerFullname());}

            reportReqDetailList.add(reportReqDetails);
                
                    }
                }
            }
        }
        if(!reportReqDetailList.isEmpty()){
            reportingRequirement.setReportingReqDetailsArray(
                    reportReqDetailList.toArray(new ReportingRequirementDetail[0]));
        }
    }

    public DateTimeService getDateTimeService() {
        return dateTimeService;
    }

    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    public DocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
}



