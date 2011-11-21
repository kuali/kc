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
package org.kuali.kra.negotiations.printing.xmlstream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import noNamespace.ActivitiesType;
import noNamespace.ActivityTypes;
import noNamespace.LeadUnitType;
import noNamespace.NegotiationDataType;
import noNamespace.NegotiationsDocument;
import noNamespace.ProposalTypes;
import noNamespace.SponsorTypes;
import noNamespace.StatusType;
import noNamespace.NegotiationsDocument.Negotiations;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.negotiations.bo.NegotiationActivity;
import org.kuali.kra.negotiations.printing.NegotiationActivityPrintType;
import org.kuali.kra.printing.xmlstream.XmlStream;
import org.kuali.kra.proposaldevelopment.bo.ProposalType;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DateTimeService;
import org.kuali.rice.kns.service.DocumentService;


public class NegotiationActivityXmlStream implements XmlStream {
       
    private Negotiation negotiation;
    private DocumentService documentService;
    private DateTimeService dateTimeService;
    private BusinessObjectService businessObjectService;
    
    private static final String PROP_LOG = "PL";
    private static final String PROP_TYPE_CODE = "proposalTypeCode";
     
    public Negotiation getNegotiation() {
        return negotiation;
    }

    public void setNegotiation(Negotiation negotiation) {
        this.negotiation = negotiation;
    }    
    
    public DocumentService getDocumentService() {
        return documentService;
    }    
    
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }  
    
    @Override
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    @Override
    public DateTimeService getDateTimeService() {        
        return  dateTimeService;
    }

    @Override
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
        
    }

    @Override
    public void setDateTimeService(DateTimeService dateTimeService) {
       this.dateTimeService = dateTimeService;
        
    }    
   
    @Override
    public Map<String, XmlObject> generateXmlStream(KraPersistableBusinessObjectBase printableBusinessObject,
            Map<String, Object> reportParameters) {
        Map<String, XmlObject> xmlObjectList = new LinkedHashMap<String, XmlObject>(); 
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
        List<NegotiationDataType> negotiationDataList = new ArrayList<NegotiationDataType>();
        negotiationDataList.add(getNegotiationDataType());
        negotiations.setNegotiationDataArray( (NegotiationDataType[]) negotiationDataList.toArray(new NegotiationDataType[0]));
        return negotiations;
    }
    
    /*
     * This method will set the values to NegotiationDataType attributes
     * and finally returns NegotiationDataType Xml object
     */
    protected NegotiationDataType getNegotiationDataType() {
        NegotiationDataType negotiationDataType = NegotiationDataType.Factory.newInstance();   
               
        if(negotiation.getNegotiator() != null )
            negotiationDataType.setNegotiator(negotiation.getNegotiator().getFullName());
        if(negotiation.getNegotiationStartDate() != null )
            negotiationDataType.setStartDate(getDateTimeService().getCalendar(negotiation.getNegotiationStartDate()));       
        if(negotiation.getNegotiationStatus() != null){
            StatusType statusType = StatusType.Factory.newInstance();       
            statusType.setStatusDesc(negotiation.getNegotiationStatus().getDescription());
            negotiationDataType.setStatus(statusType);       
        }    
        negotiationDataType.setDocFileAddress(negotiation.getDocumentFolder()) ;  
        
        if(negotiation.getUnAssociatedDetail() != null){        
            if( negotiation.getUnAssociatedDetail().getLeadUnit() != null ){
                LeadUnitType leadUnitType=LeadUnitType.Factory.newInstance();
                leadUnitType.setUnitNumber(negotiation.getUnAssociatedDetail().getLeadUnit().getUnitNumber());
                leadUnitType.setUnitName(negotiation.getUnAssociatedDetail().getLeadUnit().getUnitName());
                negotiationDataType.setLeadUnit(leadUnitType);
             }        
            if(negotiation.getUnAssociatedDetail().getSponsor() != null ){
                SponsorTypes sponsorTypes = SponsorTypes.Factory.newInstance();
                sponsorTypes.setSponsorName(negotiation.getUnAssociatedDetail().getSponsor().getSponsorName());
                sponsorTypes.setSponsorCode(negotiation.getUnAssociatedDetail().getSponsorCode());        
                negotiationDataType.setSponsor(sponsorTypes);
            }  
            if(negotiation.getUnAssociatedDetail().getContactAdmin() != null )
                negotiationDataType.setContractAdmin(negotiation.getUnAssociatedDetail().getContactAdmin().getFullName());   
        
                negotiationDataType.setTitle(negotiation.getUnAssociatedDetail().getTitle());
                negotiationDataType.setInvestigator(negotiation.getUnAssociatedDetail().getPiName()); 
         }
               
         else if(negotiation.getAssociatedDocument() != null){
           
                LeadUnitType leadUnitType=LeadUnitType.Factory.newInstance();
                leadUnitType.setUnitNumber(negotiation.getAssociatedDocument().getLeadUnitNumber());
                leadUnitType.setUnitName(negotiation.getAssociatedDocument().getLeadUnitName());
                negotiationDataType.setLeadUnit(leadUnitType);          
                    
                SponsorTypes sponsorTypes = SponsorTypes.Factory.newInstance();
                sponsorTypes.setSponsorName(negotiation.getAssociatedDocument().getSponsorName());
                sponsorTypes.setSponsorCode(negotiation.getAssociatedDocument().getSponsorCode());        
                negotiationDataType.setSponsor(sponsorTypes);
        
                negotiationDataType.setTitle(negotiation.getAssociatedDocument().getTitle());
                negotiationDataType.setInvestigator(negotiation.getAssociatedDocument().getPiName()); 
                negotiationDataType.setContractAdmin(negotiation.getAssociatedDocument().getAdminPersonName());  
         }       
        
        if(negotiation.getNegotiationAssociationType() != null 
                && negotiation.getNegotiationAssociationType().getCode().equals(PROP_LOG)){            
        negotiationDataType.setProposalNumber(negotiation.getAssociatedNegotiable().getAssociatedDocumentId());        
        ProposalTypes proposalTypes = ProposalTypes.Factory.newInstance();    
        proposalTypes.setProposalTypeDesc(getProposalTypeDescription
                (Integer.parseInt(negotiation.getAssociatedNegotiable().getNegotiableProposalTypeCode())));
        negotiationDataType.setProposalTypes(proposalTypes);
        }
        
        setActivitiesType(negotiationDataType);  
        
        return negotiationDataType;
    }   
    
    /*
     * This method will set the values to NegotiationActivity attributes     
     */
    private void setActivitiesType(NegotiationDataType negotiationDataType){
        List<ActivitiesType> activitiesTypeList = new ArrayList<ActivitiesType>();
        
        List<NegotiationActivity> negotiationActivities = negotiation.getActivities(); 
        if(negotiation.getPrintindex() == 0)
           for (NegotiationActivity negotiationActivity : negotiationActivities) {  
              activitiesTypeList.add(getActivitiesType(negotiationActivity));
           }
        else
            activitiesTypeList.add(getActivitiesType(negotiationActivities.get(negotiation.getPrintindex()-1)));
        negotiationDataType.setActivitiesArray((ActivitiesType[]) activitiesTypeList.toArray(new ActivitiesType[0]));      
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
        Map<String, String> proposalTypeDescMap = new HashMap<String, String>();
        List<ProposalType> proposalTypeList = new ArrayList<ProposalType>();
        
        proposalTypeDescMap.put(PROP_TYPE_CODE, String
                .valueOf(proposalTypeCode));       
        proposalTypeList = (List<ProposalType>) getBusinessObjectService()
                        .findMatching(ProposalType.class,proposalTypeDescMap);
        if (proposalTypeList != null && !proposalTypeList.isEmpty()) {
            ProposalType proposalType = proposalTypeList.get(0);
            proposalTypeDescription = proposalType.getDescription();
        }
        return proposalTypeDescription;
    }        
}
