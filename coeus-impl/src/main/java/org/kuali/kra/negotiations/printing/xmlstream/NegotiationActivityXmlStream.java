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
package org.kuali.kra.negotiations.printing.xmlstream;

import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.framework.print.stream.xml.XmlStream;
import org.kuali.coeus.common.framework.type.ProposalType;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.negotiations.bo.*;
import org.kuali.kra.negotiations.printing.NegotiationActivityPrintType;
import org.kuali.kra.printing.schema.*;
import org.kuali.kra.printing.schema.NegotiationsDocument.Negotiations;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.BusinessObjectService;


import java.util.*;


public class NegotiationActivityXmlStream implements XmlStream {

    private static final String PROP_LOG = "PL";
    private static final String PROP_TYPE_CODE = "PROPOSAL_TYPE_CODE";

    private Negotiation negotiation;
    private DateTimeService dateTimeService;
    private BusinessObjectService businessObjectService;

    /**
     * This method generates XML for Negotiation Activity Report. It uses data passed in
     * {@link org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase} for populating the XML nodes. The XML once
     * generated is returned as {@link XmlObject}
     * 
     * @param printableBusinessObject
     *            using which XML is generated
     * @param reportParameters
     *            parameters related to XML generation
     * @return {@link XmlObject} representing the XML
     */
    public Map<String, XmlObject> generateXmlStream(KcPersistableBusinessObjectBase printableBusinessObject,
            Map<String, Object> reportParameters) {
        Map<String, XmlObject> xmlObjectList = new LinkedHashMap<>();
        NegotiationsDocument negotiationsDocument = NegotiationsDocument.Factory.newInstance();
        initialize((Negotiation) printableBusinessObject);
        negotiationsDocument.setNegotiations(getNegotiations());
        xmlObjectList.put(NegotiationActivityPrintType.NEGOTIATION_ACTIVITY_REPORT
                .getNegotiationActivityPrintType(), negotiationsDocument);
        return xmlObjectList;  
    }
    
    /*
     * This method initializes negotiation
     * reference variable
     */
    private void initialize(Negotiation negotiation) {
       this.negotiation = negotiation;      
    }
    
    /*
     * This method will set the values to Negotiations attributes and
     * finally returns Negotiations Xml object
     */
    protected Negotiations getNegotiations() {
        Negotiations negotiations = Negotiations.Factory.newInstance();  
        List<NegotiationDataType> negotiationDataList = new ArrayList<>();
        negotiationDataList.add(getNegotiationDataType());
        negotiations.setNegotiationDataArray(negotiationDataList.toArray(new NegotiationDataType[0]));
        return negotiations;
    }
    
    /*
     * This method will set the values to NegotiationDataType attributes
     * and finally returns NegotiationDataType Xml object
     */
    protected NegotiationDataType getNegotiationDataType() {
        NegotiationDataType negotiationDataType = NegotiationDataType.Factory.newInstance();   
        NegotiationAssociationType negotiationAssociationType = negotiation.getNegotiationAssociationType(); 

        if(negotiation.getNegotiator() != null ){
            negotiationDataType.setNegotiator(negotiation.getNegotiator().getFullName());
        }    
        if(negotiation.getNegotiationStartDate() != null){
            negotiationDataType.setStartDate(getDateTimeService().getCalendar(negotiation.getNegotiationStartDate())); 
        }

        Calendar cal = getDateTimeService().getCurrentCalendar();
		negotiationDataType.setCurrentDate(cal);
		
        if(negotiation.getNegotiationStatus() != null){
            StatusType statusType = StatusType.Factory.newInstance();
            statusType.setStatusDesc(negotiation.getNegotiationStatus().getDescription());
            negotiationDataType.setStatus(statusType);       
        }
        if(negotiation.getUnAssociatedDetail() != null){
            setUnAssociatedDetails(negotiationDataType); 
        }              
        else if(negotiation.getAssociatedDocument() != null){
            setAssociatedDetails(negotiationDataType);
        }         
        if(negotiationAssociationType != null 
                && negotiationAssociationType.getCode().equals(PROP_LOG)){            
        negotiationDataType.setProposalNumber(negotiation.getAssociatedNegotiable().getAssociatedDocumentId());        
        ProposalTypes proposalTypes = ProposalTypes.Factory.newInstance();
        proposalTypes.setProposalTypeDesc(getProposalTypeDescription
                (Integer.parseInt(negotiation.getAssociatedNegotiable().getNegotiableProposalTypeCode())));
        negotiationDataType.setProposalTypes(proposalTypes);
        }
        negotiationDataType.setDocFileAddress(negotiation.getDocumentFolder());        
        negotiationDataType.setNegotiationId(negotiation.getNegotiationId().toString());
        setActivitiesType(negotiationDataType);  
        
        return negotiationDataType;
    }   
    
    /*
     * This method will set the NegotiationUnassociatedDetail values to NegotiationActivity attributes   
     */
    private void setUnAssociatedDetails(NegotiationDataType negotiationDataType){       
        NegotiationUnassociatedDetail negotiationUnassociatedDetail = negotiation.getUnAssociatedDetail();
        
        negotiationDataType.setTitle(negotiationUnassociatedDetail.getTitle());
        negotiationDataType.setInvestigator(negotiationUnassociatedDetail.getPiName()); 
        if(negotiationUnassociatedDetail.getLeadUnit() != null ){
            LeadUnitType leadUnitType=LeadUnitType.Factory.newInstance();
            leadUnitType.setUnitNumber(negotiationUnassociatedDetail.getLeadUnit().getUnitNumber());
            leadUnitType.setUnitName(negotiationUnassociatedDetail.getLeadUnit().getUnitName());
            negotiationDataType.setLeadUnit(leadUnitType);
        }        
        if(negotiationUnassociatedDetail.getSponsor() != null ){
            SponsorTypes sponsorTypes = SponsorTypes.Factory.newInstance();
            sponsorTypes.setSponsorName(negotiationUnassociatedDetail.getSponsor().getSponsorName());
            sponsorTypes.setSponsorCode(negotiationUnassociatedDetail.getSponsorCode());        
            negotiationDataType.setSponsor(sponsorTypes);
        }  
        if(negotiationUnassociatedDetail.getContactAdmin() != null ){
            negotiationDataType.setContractAdmin(negotiationUnassociatedDetail.getContactAdmin().getFullName());
        }
    }
    
    /*
     * This method will set the NegotiationAssociatedDocument values to NegotiationActivity attributes   
     */
    private void setAssociatedDetails(NegotiationDataType negotiationDataType){       
        Negotiable negotiable = negotiation.getAssociatedDocument();
        
        LeadUnitType leadUnitType=LeadUnitType.Factory.newInstance();
        leadUnitType.setUnitNumber(negotiable.getLeadUnitNumber());
        leadUnitType.setUnitName(negotiable.getLeadUnitName());
        negotiationDataType.setLeadUnit(leadUnitType);          
            
        SponsorTypes sponsorTypes = SponsorTypes.Factory.newInstance();
        sponsorTypes.setSponsorName(negotiable.getSponsorName());
        sponsorTypes.setSponsorCode(negotiable.getSponsorCode());        
        negotiationDataType.setSponsor(sponsorTypes);

        negotiationDataType.setTitle(negotiable.getTitle());
        negotiationDataType.setInvestigator(negotiable.getPiName()); 
        negotiationDataType.setContractAdmin(negotiable.getAdminPersonName());
    }
    
    /*
     * This method will set the values to NegotiationActivity attributes     
     */
    private void setActivitiesType(NegotiationDataType negotiationDataType){
        List<ActivitiesType> activitiesTypeList = new ArrayList<>();
        
        List<NegotiationActivity> negotiationActivities = negotiation.getActivities(); 
        if(negotiation.getPrintindex() == 0){
           for (NegotiationActivity negotiationActivity : negotiationActivities) {  
              if (negotiation.isPrintAll() || negotiationActivity.getEndDate() == null) {
                  activitiesTypeList.add(getActivitiesType(negotiationActivity));
              }
           }
        }
        else{
            activitiesTypeList.add(getActivitiesType(negotiationActivities.get(negotiation.getPrintindex()-1)));
        }
        negotiationDataType.setActivitiesArray(activitiesTypeList.toArray(new ActivitiesType[0]));
    }
    
    /*
     * This method will set the values to NegotiationActivity attributes    
     * finally returns ActivitiesType Xml object 
     */
    private ActivitiesType getActivitiesType(NegotiationActivity negotiationActivity){
        ActivitiesType activitiesType = ActivitiesType.Factory.newInstance();
        activitiesType.setDescription(negotiationActivity.getDescription()); 
        if(negotiationActivity.getCreateDate() != null)
        activitiesType.setCreateDate(getDateTimeService().getCalendar(negotiationActivity.getCreateDate()));
        if(negotiationActivity.getFollowupDate() != null)
        activitiesType.setFollowupDate(getDateTimeService()
                .getCalendar(negotiationActivity.getFollowupDate()));        
        activitiesType.setLastDate(getDateTimeService().getCalendar(negotiationActivity.getLastModifiedDate()));
        activitiesType.setUpdatedBy(negotiationActivity.getLastModifiedUser().getFullName());
        if(negotiationActivity.getStartDate() != null)
        activitiesType.setActivityDate(getDateTimeService().getCalendar(negotiationActivity.getStartDate()));
        
        ActivityTypes activityTypes = ActivityTypes.Factory.newInstance();          
        activityTypes.setActivityDesc(negotiationActivity.getActivityType().getDescription());
        activitiesType.setActivity(activityTypes);
        
        return activitiesType;
    }  
    
    /*
     * This method will return the proposal type description based on the
     * proposal type code.
     */
    private String getProposalTypeDescription(int proposalTypeCode) {
        String proposalTypeDescription = null;
        Map<String, String> proposalTypeDescMap = new HashMap<>();
        
        proposalTypeDescMap.put(PROP_TYPE_CODE, String
                .valueOf(proposalTypeCode));
        List<ProposalType> proposalTypeList = (List<ProposalType>) getBusinessObjectService()
                        .findMatching(ProposalType.class,proposalTypeDescMap);
        if (proposalTypeList != null && !proposalTypeList.isEmpty()) {
            ProposalType proposalType = proposalTypeList.get(0);
            proposalTypeDescription = proposalType.getDescription();
        }
        return proposalTypeDescription;
    }

    public Negotiation getNegotiation() {
        return negotiation;
    }

    public void setNegotiation(Negotiation negotiation) {
        this.negotiation = negotiation;
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public DateTimeService getDateTimeService() {
        return  dateTimeService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }
}
