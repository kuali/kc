/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.kuali.core.exceptions.ValidationException;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalPersonRoleService;
import org.kuali.rice.KNSServiceLocator;
import static org.kuali.kra.infrastructure.Constants.CO_INVESTIGATOR_ROLE;
import static org.kuali.kra.infrastructure.Constants.PRINCIPAL_INVESTIGATOR_ROLE;
import static org.kuali.kra.infrastructure.Constants.KEY_PERSON_ROLE;
import edu.iu.uis.eden.exception.WorkflowException;


public class ProposalPersonRoleServiceImpl implements ProposalPersonRoleService {

    private BusinessObjectService businessObjectService;
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalPersonRoleServiceImpl.class);
    
    
    /**
     * 
     * @see org.kuali.kra.proposaldevelopment.service.ProposalPersonRoleService#getProposalCoInvestigators(String docid)
     */
    public List<String> getProposalCoInvestigators(String docId) {
        // TODO Auto-generated method stub
        List<String> coinvestigators = new ArrayList<String>();
        ProposalDevelopmentDocument proposaldevdocument;
        try{
            proposaldevdocument=(ProposalDevelopmentDocument) KNSServiceLocator.getDocumentService().getByDocumentHeaderId(docId);
           
        }
        catch (WorkflowException e) {
            throw new ValidationException("Could not find the document.", e);
        }

        for (Iterator<ProposalPerson> person_it = proposaldevdocument.getProposalPersons().iterator(); person_it.hasNext();) {
            ProposalPerson person = person_it.next();
            if((person!= null) && (person.getProposalPersonRoleId().equals(CO_INVESTIGATOR_ROLE))){
              if(person.getPersonId()!=null){
                  LOG.info("Adding the person id to  coinvestigator");
                   coinvestigators.add(person.getPersonId());
                }
                else{
                    
                    LOG.info("Adding the Rolodex id to coinvestigator");
                    coinvestigators.add(person.getRolodexId().toString());
                }

            }

        }
        return coinvestigators;
    }
    /**
     * 
     * @see org.kuali.kra.proposaldevelopment.service.ProposalPersonRoleService#getProposalInvestigator(String docid)
     */
    public List<String> getProposalInvestigator(String docId) {
        // TODO Auto-generated method stub
        
        
        List<String> Principalinvestigator = new ArrayList<String>();
        ProposalDevelopmentDocument proposaldevdocument;
        try{
            proposaldevdocument=(ProposalDevelopmentDocument) KNSServiceLocator.getDocumentService().getByDocumentHeaderId(docId);
           
        }
        catch (WorkflowException e) {
            throw new ValidationException("Could not find the document.", e);
        }

        for (Iterator<ProposalPerson> person_it = proposaldevdocument.getProposalPersons().iterator(); person_it.hasNext();) {
            ProposalPerson person = person_it.next();
            if((person!= null) && (person.getProposalPersonRoleId().equals(PRINCIPAL_INVESTIGATOR_ROLE))){
              if(person.getPersonId()!=null){
                  LOG.info("Adding the person id to  PrinicpalInvestigator");
                  Principalinvestigator.add(person.getPersonId());
                }
                else{
                    
                    LOG.info("Adding the Rolodex id to PrinicpalInvestigator");
                    Principalinvestigator.add(person.getRolodexId().toString());
                }

            }

        }
        return Principalinvestigator;
        
    }

    public List<String> getProposalInvestigators(String docId) {
// TODO Auto-generated method stub
        
        
        List<String> Investigators = new ArrayList<String>();
        ProposalDevelopmentDocument proposaldevdocument;
        try{
            proposaldevdocument=(ProposalDevelopmentDocument) KNSServiceLocator.getDocumentService().getByDocumentHeaderId(docId);
           
        }
        catch (WorkflowException e) {
            throw new ValidationException("Could not find the document.", e);
        }

        for (Iterator<ProposalPerson> person_it = proposaldevdocument.getProposalPersons().iterator(); person_it.hasNext();) {
            ProposalPerson person = person_it.next();
            if((person!= null) && (person.getProposalPersonRoleId().equals(PRINCIPAL_INVESTIGATOR_ROLE)) || (person.getProposalPersonRoleId().equals(CO_INVESTIGATOR_ROLE))){
              if(person.getPersonId()!=null){
                  LOG.info("Adding the person id to  Investigators(PI and CO-Is");
                  Investigators.add(person.getPersonId());
                }
                else{
                    
                    LOG.info("Adding the Rolodex id to Investigators");
                    Investigators.add(person.getRolodexId().toString());
                }

            }

        }
        return Investigators;
    }

    public List<String> getProposalKeyPersons(String docId) {
        List<String> KeyPersons = new ArrayList<String>();
        ProposalDevelopmentDocument proposaldevdocument;
        try{
            proposaldevdocument=(ProposalDevelopmentDocument) KNSServiceLocator.getDocumentService().getByDocumentHeaderId(docId);
           
        }
        catch (WorkflowException e) {
            throw new ValidationException("Could not find the document.", e);
        }

        for (Iterator<ProposalPerson> person_it = proposaldevdocument.getProposalPersons().iterator(); person_it.hasNext();) {
            ProposalPerson person = person_it.next();
            if((person!= null) && (person.getProposalPersonRoleId().equals(KEY_PERSON_ROLE))){
              if(person.getPersonId()!=null){
                  LOG.info("Adding the person id to  KeyPerson");
                  KeyPersons.add(person.getPersonId());
                }
                else{
                    
                    LOG.info("Adding the Rolodex id to KeyPerson");
                    KeyPersons.add(person.getRolodexId().toString());
                }

            }

        }
        return KeyPersons;
    
    }

    public List<String> getProposalPersons(String docId) {
        List<String> ProposalPersons = new ArrayList<String>();
        ProposalDevelopmentDocument proposaldevdocument;
        try{
            proposaldevdocument=(ProposalDevelopmentDocument) KNSServiceLocator.getDocumentService().getByDocumentHeaderId(docId);
           
        }
        catch (WorkflowException e) {
            throw new ValidationException("Could not find the document.", e);
        }

        for (Iterator<ProposalPerson> person_it = proposaldevdocument.getProposalPersons().iterator(); person_it.hasNext();) {
            ProposalPerson person = person_it.next();
            if((person!= null) && (person.getProposalPersonRoleId().equals(PRINCIPAL_INVESTIGATOR_ROLE)) ||  (person.getProposalPersonRoleId().equals(CO_INVESTIGATOR_ROLE)) || (person.getProposalPersonRoleId().equals(KEY_PERSON_ROLE))){
              if(person.getPersonId()!=null){
                  LOG.info("Adding the person id to  ProposalPersons(PIs,CO-Is,KP)");
                  ProposalPersons.add(person.getPersonId());
                }
                else{
                    
                    LOG.info("Adding the Rolodex id to Investigators");
                    ProposalPersons.add(person.getRolodexId().toString());
                }

            }

        }
        return ProposalPersons;
    
    }

    
    
    
    /**
     * Accessor for <code>{@link BusinessObjectService}</code>
     *
     * @param bos BusinessObjectService
     */
    public void setBusinessObjectService(BusinessObjectService bos) {
        businessObjectService = bos;
    }

    /**
     * Accessor for <code>{@link BusinessObjectService}</code>
     *
     * @return BusinessObjectService
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

}
