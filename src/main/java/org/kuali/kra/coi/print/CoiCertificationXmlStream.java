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
package org.kuali.kra.coi.print;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import noNamespace.ApprovedDisclosureDocument;
import noNamespace.ApprovedDisclosureDocument.ApprovedDisclosure;
import noNamespace.DisclosureDocumentsDocument.DisclosureDocuments;
import noNamespace.DisclosureNotesDocument.DisclosureNotes;
import noNamespace.DisclosureProjectsDocument.DisclosureProjects;
import noNamespace.PersonDocument;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.coi.CoiDisclProject;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiDisclosureDocument;
import org.kuali.kra.coi.notesandattachments.attachments.CoiDisclosureAttachment;
import org.kuali.kra.coi.notesandattachments.notes.CoiDisclosureNotepad;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.printing.xmlstream.XmlStream;
import org.kuali.kra.questionnaire.print.QuestionnaireXmlStream;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.DocumentService;

public class CoiCertificationXmlStream implements XmlStream {

    private DateTimeService dateTimeService;
    private BusinessObjectService businessObjectService;
    private DocumentService documentService;
    
    private static final Log LOG = LogFactory.getLog(QuestionnaireXmlStream.class);

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
    public Map<String, XmlObject> generateXmlStream(KraPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
    	CoiDisclosure disclosure=(CoiDisclosure)printableBusinessObject;
        ApprovedDisclosureDocument approvedDisclosureDoc = ApprovedDisclosureDocument.Factory.newInstance(); 
        try {
			approvedDisclosureDoc.setApprovedDisclosure(getDisclosureData(disclosure,reportParameters));
		} catch (PrintingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
    
    public ApprovedDisclosure getDisclosureData(KraPersistableBusinessObjectBase printableBusinessObject,
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
    	    setCertificationQuestionnaires(approvedDisclosure,disclosure);
    	    setDisclosureNotes(approvedDisclosure,disclosure);
    	    setDisclosureAttachments(approvedDisclosure,disclosure);
    	    return approvedDisclosure;
    	  }
    	    private void setPersonDetails(ApprovedDisclosure approvedDisclosure, CoiDisclosure disclosure){
    	     List<noNamespace.PersonDocument.Person> personDocList = new ArrayList<noNamespace.PersonDocument.Person>();
    	     noNamespace.PersonDocument.Person person = noNamespace.PersonDocument.Person.Factory.newInstance();
    	    KcPerson reporter= KraServiceLocator.getService(KcPersonService.class).getKcPersonByPersonId(disclosure.getPersonId());
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
    	    private void setDisclosureProjects(ApprovedDisclosure approvedDisclosure, CoiDisclosure disclosure){
    	    
    	    	List<DisclosureProjects>discProjectsList=new ArrayList<DisclosureProjects>();
    	        List<CoiDisclProject> disclosureProjectList = disclosure.getCoiDisclProjects();
      	         for (CoiDisclProject coiDisclProject : disclosureProjectList) {
    	           	DisclosureProjects disclosureProjects=DisclosureProjects.Factory.newInstance();
    	           if(coiDisclProject.getProjectId()!=null){
    	           	disclosureProjects.setProjectID(coiDisclProject.getProjectId());}
    	           	if(coiDisclProject.getLongTextField1()!=null){
    	           	disclosureProjects.setProjectTitle(coiDisclProject.getLongTextField1());}
    	           	if(coiDisclProject.getLongTextField2()!=null){
    	           		disclosureProjects.setProjectSponsor(coiDisclProject.getLongTextField2());
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

    
    
    


