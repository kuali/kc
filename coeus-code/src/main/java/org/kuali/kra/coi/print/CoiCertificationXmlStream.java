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
package org.kuali.kra.coi.print;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.common.framework.print.xmlstream.XmlStream;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.coi.CoiDiscDetail;
import org.kuali.kra.coi.CoiDisclProject;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.notesandattachments.attachments.CoiDisclosureAttachment;
import org.kuali.kra.coi.notesandattachments.notes.CoiDisclosureNotepad;
import org.kuali.kra.printing.schema.ApprovedDisclosureDocument;
import org.kuali.kra.printing.schema.ApprovedDisclosureDocument.ApprovedDisclosure;
import org.kuali.kra.printing.schema.CoiDisclosureDetailsDocument.CoiDisclosureDetails;
import org.kuali.kra.printing.schema.DisclosureDocumentsDocument.DisclosureDocuments;
import org.kuali.kra.printing.schema.DisclosureNotesDocument.DisclosureNotes;
import org.kuali.kra.printing.schema.DisclosureProjectsDocument.DisclosureProjects;
import org.kuali.kra.printing.schema.PersonDocument;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

import java.util.*;

public class CoiCertificationXmlStream implements XmlStream {

    private static final Log LOG = LogFactory.getLog(CoiCertificationXmlStream.class);

    private DateTimeService dateTimeService;
    private BusinessObjectService businessObjectService;
    private DocumentService documentService;

    /**
     * This method generates XML committee report. It uses data passed in
     * {@link ResearchDocumentBase} for populating the XML nodes. The XMl once
     * generated is returned as {@link XmlObject}
     * 
     * @param printableBusinessObject
     *            using which XML is generated
     * @param reportParameters
     *            parameters related to XML generation
     * @return {@link XmlObject} representing the XML
     */
    public Map<String, XmlObject> generateXmlStream(KcPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
    	CoiDisclosure disclosure=(CoiDisclosure)printableBusinessObject;
        ApprovedDisclosureDocument approvedDisclosureDoc = ApprovedDisclosureDocument.Factory.newInstance(); 
        try {
			approvedDisclosureDoc.setApprovedDisclosure(getDisclosureData(disclosure,reportParameters));
		} catch (PrintingException e) {
			LOG.error(e.getMessage(), e);
		}
        Map<String, XmlObject> xmlObjectList = new LinkedHashMap<String, XmlObject>();
        Map<String, XmlObject> map = new HashMap<String,XmlObject>();
        map.put("DisclosureSummary", approvedDisclosureDoc);
        return map;
    }

    /**
     * 
     * This method is to get the Disclosure Certification data for print.
     * @param document
     * @param params
     * @return
     * @throws PrintingException
     */
    @SuppressWarnings("unchecked")
    
    public ApprovedDisclosure getDisclosureData(KcPersistableBusinessObjectBase printableBusinessObject,
    	    Map<String, Object> htData) throws PrintingException{
    	    CoiDisclosure disclosure=(CoiDisclosure)printableBusinessObject;
    	    ApprovedDisclosure approvedDisclosure=ApprovedDisclosure.Factory.newInstance();
    	    approvedDisclosure.setDisclosureNumber(disclosure.getCoiDisclosureNumber());
    	    approvedDisclosure.setSequenceNumber(disclosure.getSequenceNumber().toString());
    	    approvedDisclosure.setPersonID(disclosure.getPersonId());
    	    String certText=disclosure.getCertificationText();
    	    certText = certText.substring(3);
    	    approvedDisclosure.setCertificationText(certText);
    	    approvedDisclosure.setCertifiedBy(disclosure.getCertifiedBy());
    	    approvedDisclosure.setCertificationTimestamp(disclosure.getCertificationTimestampString());
    	    approvedDisclosure.setDisclosureDipositionStatus(disclosure.getDisclosureDispositionCode());
    	    approvedDisclosure.setDisclosureStatus(disclosure.getDisclosureStatusCode());
    	    approvedDisclosure.setExpirationDate(disclosure.getExpirationDate().toString());
    	    setPersonDetails(approvedDisclosure,disclosure);
    	    setDisclosureProjects(approvedDisclosure,disclosure);
    	    setCoiDisclosureDetails(approvedDisclosure,disclosure);
    	    setCertificationQuestionnaires(approvedDisclosure,disclosure);
    	    setDisclosureNotes(approvedDisclosure,disclosure);
    	    setDisclosureAttachments(approvedDisclosure,disclosure);
    	    return approvedDisclosure;
    	  }
    	    private void setPersonDetails(ApprovedDisclosure approvedDisclosure, CoiDisclosure disclosure){
    	     List<org.kuali.kra.printing.schema.PersonDocument.Person> personDocList = new ArrayList<org.kuali.kra.printing.schema.PersonDocument.Person>();
    	     org.kuali.kra.printing.schema.PersonDocument.Person person = org.kuali.kra.printing.schema.PersonDocument.Person.Factory.newInstance();
    	    KcPerson reporter= KcServiceLocator.getService(KcPersonService.class).getKcPersonByPersonId(disclosure.getPersonId());
    	        person.setFullName(reporter.getFullName());
    	        person.setAddress1(reporter.getAddressLine1()+""+reporter.getAddressLine2()+""+reporter.getAddressLine3());
    	        person.setDirDept(reporter.getDirectoryDepartment());
    	        person.setSchool(reporter.getSchool());
    	        person.setOffPhone(reporter.getOfficePhone());
    	        person.setEmail(reporter.getEmailAddress());
    	       personDocList.add(person);
    	    approvedDisclosure.setPersonArray(personDocList.toArray(new PersonDocument.Person[0]));
    	    }
    	    private void setCertificationQuestionnaires(ApprovedDisclosure approvedDisclosure, CoiDisclosure disclosure){
    	    //Still to implement
    	           
    	    }
    	    private void setCoiDisclosureDetails(ApprovedDisclosure approvedDisclosure, CoiDisclosure disclosure){
    	        List<CoiDisclosureDetails> coiDisclosurProjectsList=new ArrayList<CoiDisclosureDetails>();
    	        List<CoiDisclProject> coiDisclProjectList = disclosure.getCoiDisclProjects();    	        
    	        for (CoiDisclProject coiDisclProject : coiDisclProjectList) {    	            
    	            for (CoiDiscDetail coiDiscDetail : coiDisclProject.getCoiDiscDetails()) {    	                
    	                if (coiDiscDetail.getPersonFinIntDisclosure() != null) { 
    	                    CoiDisclosureDetails coiDisclosureDetails = CoiDisclosureDetails.Factory.newInstance();
                            coiDisclosureDetails.setEntityName(coiDiscDetail.getPersonFinIntDisclosure().getEntityName());
                            if (coiDiscDetail.getCoiEntityDispositionStatus() != null) {
                                coiDisclosureDetails.setConflictStatus(coiDiscDetail.getCoiEntityDispositionStatus().getDescription());
                            }                            
                            coiDisclosurProjectsList.add(coiDisclosureDetails);
    	                }    	                
    	            }    	           
    	        }    	      
                if (coiDisclosurProjectsList.size() > 0) {
                    approvedDisclosure.setCoiDisclosureDetailsArray(coiDisclosurProjectsList.toArray(new CoiDisclosureDetails[0]));
                }    	        
    	    }
    	    private void setDisclosureProjects(ApprovedDisclosure approvedDisclosure, CoiDisclosure disclosure){
    	    
    	    	List<DisclosureProjects>discProjectsList=new ArrayList<DisclosureProjects>();
    	        List<CoiDisclProject> disclosureProjectList = disclosure.getCoiDisclProjects();
      	         for (CoiDisclProject coiDisclProject : disclosureProjectList) {
    	           	DisclosureProjects disclosureProjects=DisclosureProjects.Factory.newInstance();
    	           if(coiDisclProject.getProjectId()!=null){
    	           	disclosureProjects.setProjectID(coiDisclProject.getProjectId());}
    	           	if(coiDisclProject.getCoiProjectTitle()!=null){
    	           	disclosureProjects.setProjectTitle(coiDisclProject.getCoiProjectTitle());}
    	           	if(coiDisclProject.getLongTextField1()!=null){
    	           		disclosureProjects.setProjectSponsor(coiDisclProject.getLongTextField1());
    	           	}
    	        	if(coiDisclProject.getDateField1()!=null){
    	           		disclosureProjects.setProjectStartDate(coiDisclProject.getDateField1().toString());
    	           	  	}
    	        	if(coiDisclProject.getDateField2()!=null){
    	           		disclosureProjects.setProjectEndDate(coiDisclProject.getDateField2().toString());
    	           	  	}
    	           	if(coiDisclProject.getProjectTypeLabel()!=null){
    	           	disclosureProjects.setProjectType(coiDisclProject.getProjectTypeLabel());}
    	           
    	     discProjectsList.add(disclosureProjects);
    	     }if(discProjectsList.size()>0){
    	    	approvedDisclosure.setDisclosureProjectsArray(discProjectsList.toArray(new DisclosureProjects[0]));
    	        }}
    	    private void setDisclosureNotes(ApprovedDisclosure approvedDisclosure, CoiDisclosure disclosure){ 
    	        List<DisclosureNotes> disclosureNoteslist = new ArrayList<DisclosureNotes>();  
    	        List<CoiDisclosureNotepad> coiDisclosureNotepadList = disclosure.getCoiDisclosureNotepads();
    	        for(CoiDisclosureNotepad coiDisclosureNotepad:coiDisclosureNotepadList){
    	        	if(!coiDisclosureNotepad.getRestrictedView()){
    	            DisclosureNotes disclosureNotes = DisclosureNotes.Factory.newInstance();
    	            disclosureNotes.setComments(coiDisclosureNotepad.getComments());
    	            disclosureNotes.setNoteEntry(coiDisclosureNotepad.getNoteTopic());
    	             disclosureNotes.setUpdateTimestamp(coiDisclosureNotepad.getUpdateTimestamp().toString());      
    	             disclosureNotes.setUpdateUser(coiDisclosureNotepad.getUpdateUser());
    	             disclosureNoteslist.add(disclosureNotes);    
    	        }
    	        	if(disclosureNoteslist.size()>0){
    	        approvedDisclosure.setDisclosureNotesArray(disclosureNoteslist.toArray(new DisclosureNotes[0]));
    	    }}}
    	    private void setDisclosureAttachments(ApprovedDisclosure approvedDisclosure, CoiDisclosure disclosure){
    	     List<DisclosureDocuments> disclosureDocumentList= new ArrayList<DisclosureDocuments>();
    	      List<CoiDisclosureAttachment>coiDisclAttachmentList=disclosure.getCoiDisclosureAttachments();
    	        for (CoiDisclosureAttachment coiDiscAttachments : coiDisclAttachmentList){
    	        DisclosureDocuments disclDocuments=DisclosureDocuments.Factory.newInstance();
    	         disclDocuments.setComments(coiDiscAttachments.getComments());
    	        disclDocuments.setDescription(coiDiscAttachments.getDescription());
    	        disclDocuments.setUpdateTimestamp((coiDiscAttachments.getUpdateTimestamp().toString()));
    	        disclDocuments.setUpdateUser(coiDiscAttachments.getUpdateUser());
    	        disclosureDocumentList.add(disclDocuments);
    	        }
    	        approvedDisclosure.setDisclosureDocumentsArray(disclosureDocumentList.toArray(new DisclosureDocuments[0]));
    	    } 
    	    
    	    public BusinessObjectService getBusinessObjectService() {
    	        return businessObjectService;
    	    }

    	    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
    	        this.businessObjectService = businessObjectService;
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

    
    
    


