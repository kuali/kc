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
package org.kuali.kra.s2s.keycontacts;

import java.util.ArrayList;
import java.util.List;

import gov.grants.apply.forms.keyContactsV10.KeyContactsDocument;
import gov.grants.apply.forms.keyContactsV10.KeyContactsDocument.KeyContacts;
import gov.grants.apply.forms.keyContactsV10.KeyContactsDocument.KeyContacts.RoleOnProject;
import gov.grants.apply.system.globalLibraryV20.AddressDataType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.UnitAdministrator;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.generator.S2SBaseFormGenerator;
import org.kuali.kra.s2s.generator.bo.DepartmentalPerson;
import org.kuali.kra.s2s.service.S2SUtilService;
import org.kuali.kra.s2s.util.S2SConstants;

public class KeyContactsV1_0Generator extends S2SBaseFormGenerator {
    
    public static final String AUTHORIZED_REPRESENTATIVE = "auth";
    public static final String PRINCIPAL_INVESTIGATOR = "pi";
    public static final String ADMINISTRATIVE_CONTACT = "admin";
    public static final String PAYEE_CONTACT_TYPE ="PAYEE_CONTACT_TYPE";
    public static final String PAYEE = "payee";
    
    private static final Log LOG = LogFactory.getLog(KeyContactsV1_0Generator.class);    
    
    /**
     * 
     * This method returns KeycontContactsDocument object based on proposal development document which contains the KeycontContactsDocument information
     * for a particular proposal
     *      
     * @return KeyContactsDocument {@link XmlObject} of type KeyContactsDocument.
     */
    private KeyContactsDocument getKeyContactsDocument() {        
        KeyContactsDocument keycontContactsDocument = KeyContactsDocument.Factory.newInstance();
        keycontContactsDocument.setKeyContacts(getKeyContacts());
        return keycontContactsDocument;
    }   
    
    /**
     * 
     * This method gets KeyContacts information.
     *      
     * @return KeyContacts.
     */
    private KeyContacts getKeyContacts() {
        KeyContacts keyContacts = KeyContacts.Factory.newInstance();      
        keyContacts.setFormVersion(S2SConstants.FORMVERSION_1_0);
        
        List<RoleOnProject> roleOnProjectList = new ArrayList<RoleOnProject>();        
        setAuthorizedRepresentative(roleOnProjectList);        
        
        keyContacts.setRoleOnProjectArray(roleOnProjectList.toArray(new RoleOnProject[0]));
        if(pdDoc.getDevelopmentProposal().getOwnedByUnit() != null &&
                pdDoc.getDevelopmentProposal().getOwnedByUnit().getOrganization() != null) {
            keyContacts.setApplicantOrganizationName(pdDoc.getDevelopmentProposal().getOwnedByUnit().
                    getOrganization().getOrganizationName());
        }        
        return keyContacts;
    }
    
    /**
     * 
     * This method sets Authorized Representative information.
     *  
     * @param roleOnProjectList (RoleOnProject).
     * @return RoleOnProject.
     */
    private void setAuthorizedRepresentative(List<RoleOnProject> roleOnProjectList) {
        RoleOnProject roleOnProject = null;
        DepartmentalPerson aorInfo = KraServiceLocator.getService(S2SUtilService.class).getDepartmentalPerson(pdDoc);
        if (aorInfo != null) {
            roleOnProject = RoleOnProject.Factory.newInstance();
            
            roleOnProject.setContactTitle(aorInfo.getPrimaryTitle());
            roleOnProject.setContactPhone(aorInfo.getOfficePhone());
            roleOnProject.setContactEmail(aorInfo.getEmailAddress());
            roleOnProject.setContactFax(aorInfo.getFaxNumber());
            roleOnProject.setContactName(globLibV20Generator.getHumanNameDataType(aorInfo));
            roleOnProject.setContactProjectRole(AUTHORIZED_REPRESENTATIVE);
            
            
            
            AddressDataType address = (AddressDataType) globLibV20Generator.getAddressDataType(aorInfo);
            roleOnProject.setContactAddress((gov.grants.apply.system.globalLibraryV20.AddressDataType) address);
        }
        if (roleOnProject != null) {
            roleOnProjectList.add(roleOnProject);
        }
    }    
    
    /**
     * 
     * This method sets Principal Investigator information.
     * 
     * @param roleOnProjectList (RoleOnProject).     
     * @return RoleOnProject.
     */
    private RoleOnProject setPrincipalInvestigator(List<RoleOnProject> roleOnProjectList) {
        RoleOnProject roleOnProject = null;
        
        ProposalPerson proposalPerson  = KraServiceLocator.getService(S2SUtilService.class).getPrincipalInvestigator(pdDoc);
        if (proposalPerson != null) {
            roleOnProject = RoleOnProject.Factory.newInstance();
            
            roleOnProject.setContactName(globLibV20Generator.getHumanNameDataType(proposalPerson));
            roleOnProject.setContactTitle(proposalPerson.getPrimaryTitle());
            roleOnProject.setContactProjectRole(PRINCIPAL_INVESTIGATOR);
            roleOnProject.setContactFax(proposalPerson.getFaxNumber());
            roleOnProject.setContactEmail(proposalPerson.getEmailAddress());
            roleOnProject.setContactPhone(proposalPerson.getPhoneNumber());
                    
            AddressDataType address = (AddressDataType) globLibV20Generator.getAddressDataType(proposalPerson);
            roleOnProject.setContactAddress(address); 
        }
        if (roleOnProject != null) {
            roleOnProjectList.add(roleOnProject);
        }
        return roleOnProject;
    }
    
    /**
     * 
     * This method sets Administrative Contact information.
     * 
     * @param roleOnProjectList (RoleOnProject).     
     * @return RoleOnProject.
     */
    private RoleOnProject setAdministrativeContact(List<RoleOnProject> roleOnProjectList) {
        RoleOnProject roleOnProject = null;
        DepartmentalPerson contactPerson = KraServiceLocator.getService(S2SUtilService.class).getContactPerson(pdDoc);        
        if (contactPerson != null) {
            roleOnProject = RoleOnProject.Factory.newInstance();
            
            roleOnProject.setContactName(globLibV20Generator
                    .getHumanNameDataType(contactPerson));
            roleOnProject.setContactPhone(contactPerson.getOfficePhone());
            if (contactPerson.getFaxNumber() != null
                    && !contactPerson.getFaxNumber().equals("")) {
                roleOnProject.setContactFax(contactPerson.getFaxNumber());
            }
            if (contactPerson.getEmailAddress() != null
                    && !contactPerson.getEmailAddress().equals("")) {
                roleOnProject.setContactEmail(contactPerson.getEmailAddress());
            }
            roleOnProject.setContactTitle(contactPerson.getPrimaryTitle());
            roleOnProject.setContactAddress(globLibV20Generator
                    .getAddressDataType(contactPerson));
            roleOnProject.setContactProjectRole(ADMINISTRATIVE_CONTACT);
        }
        if (roleOnProject != null) {
            roleOnProjectList.add(roleOnProject);
        }
        return roleOnProject;
    }
    
    /**
     * 
     * This method sets Payee  information.
     * 
     * @param roleOnProjectList (RoleOnProject).     
     * @return RoleOnProject.
     */
    private RoleOnProject setPayee(List<RoleOnProject> roleOnProjectList) {
        RoleOnProject roleOnProject = null;
        for (UnitAdministrator unitAdministrator : pdDoc.getDevelopmentProposal().getOwnedByUnit().getUnitAdministrators()) {            
            if (unitAdministrator.getUnitAdministratorTypeCode().equals(
                        KraServiceLocator.getService(S2SUtilService.class).getParameterValue(PAYEE_CONTACT_TYPE))) { 
                if (unitAdministrator.getPerson() != null) {
                    roleOnProject = RoleOnProject.Factory.newInstance();
                    
                    roleOnProject.setContactName(globLibV20Generator
                            .getHumanNameDataType(unitAdministrator.getPerson()));
                    roleOnProject.setContactPhone(unitAdministrator.getPerson().getOfficePhone());
                    if (unitAdministrator.getPerson().getFaxNumber() != null
                            && !unitAdministrator.getPerson().getFaxNumber().equals("")) {
                        roleOnProject.setContactFax(unitAdministrator.getPerson().getFaxNumber());
                    }
                    if (unitAdministrator.getPerson().getEmailAddress() != null
                            && !unitAdministrator.getPerson().getEmailAddress().equals("")) {
                        roleOnProject.setContactEmail(unitAdministrator.getPerson().getEmailAddress());
                    }
                    roleOnProject.setContactTitle(unitAdministrator.getPerson().getPrimaryTitle());
                    roleOnProject.setContactAddress(globLibV20Generator
                            .getAddressDataType(unitAdministrator.getPerson()));
                    roleOnProject.setContactProjectRole(PAYEE);
                }                               
            }            
        }
        if (roleOnProject != null) {
            roleOnProjectList.add(roleOnProject);
        }
        return  roleOnProject;
    } 

    @Override
    public XmlObject getFormObject(ProposalDevelopmentDocument proposalDevelopmentDocument) throws S2SException {
        // TODO Auto-generated method stub
        this.pdDoc = proposalDevelopmentDocument;
        return getKeyContactsDocument();
    }
}
