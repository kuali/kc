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
package org.kuali.coeus.s2sgen.impl.generate.support;

import gov.grants.apply.forms.keyContactsV10.KeyContactsDocument;
import gov.grants.apply.forms.keyContactsV10.KeyContactsDocument.KeyContacts;
import gov.grants.apply.forms.keyContactsV10.KeyContactsDocument.KeyContacts.RoleOnProject;
import gov.grants.apply.system.globalLibraryV20.AddressDataType;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.s2sgen.api.core.S2SException;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.kuali.coeus.s2sgen.impl.generate.S2SBaseFormGenerator;
import org.kuali.coeus.s2sgen.impl.person.DepartmentalPersonDto;
import org.kuali.coeus.s2sgen.impl.person.DepartmentalPersonService;
import org.kuali.coeus.common.api.org.OrganizationContract;
import org.kuali.coeus.common.api.org.OrganizationRepositoryService;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;
@FormGenerator("KeyContactsV1_0Generator")
public class KeyContactsV1_0Generator extends S2SBaseFormGenerator {
    
    public static final String AUTHORIZED_REPRESENTATIVE = "auth";

    @Value("http://apply.grants.gov/forms/Key_Contacts-V1.0")
    private String namespace;

    @Value("Key_Contacts-V1.0")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/KeyContacts.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.KeyContactsV10")
    private String packageName;

    @Value(DEFAULT_SORT_INDEX)
    private int sortIndex;

    @Autowired
    @Qualifier("departmentalPersonService")
    private DepartmentalPersonService departmentalPersonService;

    @Autowired
    @Qualifier("organizationRepositoryService")
    private OrganizationRepositoryService organizationRepositoryService;

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
        keyContacts.setFormVersion(FormVersion.v1_0.getVersion());
        
        List<RoleOnProject> roleOnProjectList = new ArrayList<RoleOnProject>();        
        setAuthorizedRepresentative(roleOnProjectList);        
        
        keyContacts.setRoleOnProjectArray(roleOnProjectList.toArray(new RoleOnProject[0]));
        if(pdDoc.getDevelopmentProposal().getOwnedByUnit() != null &&
                pdDoc.getDevelopmentProposal().getOwnedByUnit().getOrganizationId() != null) {

            final OrganizationContract organization = organizationRepositoryService.getOrganization(pdDoc.getDevelopmentProposal().getOwnedByUnit().getOrganizationId());
            keyContacts.setApplicantOrganizationName(organization.getOrganizationName());
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
        DepartmentalPersonDto aorInfo = departmentalPersonService.getDepartmentalPerson(pdDoc);
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

    @Override
    public XmlObject getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) throws S2SException {

        this.pdDoc = proposalDevelopmentDocument;
        return getKeyContactsDocument();
    }

    public DepartmentalPersonService getDepartmentalPersonService() {
        return departmentalPersonService;
    }

    public void setDepartmentalPersonService(DepartmentalPersonService departmentalPersonService) {
        this.departmentalPersonService = departmentalPersonService;
    }
    
    public OrganizationRepositoryService getOrganizationRepositoryService() {
        return organizationRepositoryService;
    }

    public void setOrganizationRepositoryService(OrganizationRepositoryService organizationRepositoryService) {
        this.organizationRepositoryService = organizationRepositoryService;
    }

    @Override
    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    @Override
    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    @Override
    public Resource getStylesheet() {
        return stylesheet;
    }

    public void setStylesheet(Resource stylesheet) {
        this.stylesheet = stylesheet;
    }

    @Override
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }
}
