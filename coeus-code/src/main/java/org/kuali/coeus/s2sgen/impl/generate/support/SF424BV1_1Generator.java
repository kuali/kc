/*
 * Copyright 2005-2014 The Kuali Foundation.
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
package org.kuali.coeus.s2sgen.impl.generate.support;


import gov.grants.apply.forms.sf424BV11.AssuranceType;
import gov.grants.apply.forms.sf424BV11.AssurancesDocument;
import gov.grants.apply.forms.sf424BV11.AuthorizedRepresentativeDocument.AuthorizedRepresentative;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.api.org.OrganizationContract;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.kuali.coeus.s2sgen.impl.person.DepartmentalPersonDto;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;


/**
 * This Class is used to generate XML object for grants.gov SF424BV1.1. This form is generated using XMLBean classes and is based on
 * SF424BV1.1 schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@FormGenerator("SF424BV1_1Generator")
public class SF424BV1_1Generator extends SF424BaseGenerator {

    private static final String CORE_SCHEMA_VERSION_1_1 = "1.1";

    @Value("http://apply.grants.gov/forms/SF424B-V1.1")
    private String namespace;

    @Value("SF424B-V1.1")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/SF424B-V1.1.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.sf424BV11")
    private String packageName;

    @Value(DEFAULT_SORT_INDEX)
    private int sortIndex;

    /**
     * 
     * This method returns AssurancesDocument object based on proposal development document which contains all the information for a
     * particular proposal
     * 
     * @return assuranceDocument {@link XmlObject} of type AssurancesDocument.
     */
    private AssurancesDocument getAssurance() {

        AssurancesDocument assuranceDocument = AssurancesDocument.Factory.newInstance();
        assuranceDocument.setAssurances(getAssuranceType());
        return assuranceDocument;
    }

    /**
     * 
     * This method gets AssuranceType details based on proposal development document.AssuranceType type includes details like
     * ProgramType,AuthorizedRepresentative,ApplicantOrganizationName.
     * 
     * @return AssuranceType object containing information about ApplicantOrganizationName,ProgramType,AuthorizedRepresentative
     */
    private AssuranceType getAssuranceType() {

        AssuranceType assuranceType = AssuranceType.Factory.newInstance();
        assuranceType.setFormVersionIdentifier(FormVersion.v1_1.getVersion());
        assuranceType.setProgramType(PROGRAM_TYPE);
        assuranceType.setCoreSchemaVersion(CORE_SCHEMA_VERSION_1_1);
        assuranceType.setAuthorizedRepresentative(getAuthorizedRepresentative());
        OrganizationContract organization = pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization();
        assuranceType.setApplicantOrganizationName(organization.getOrganizationName());

        return assuranceType;
    }

    /**
     * 
     * This method gets AuthorizedRepresentative details RepresentativeTitle based on ProposalDevelopmentDocumentContract.
     * 
     * @return AuthorizedRepresentative authorized representative title.
     */
    private AuthorizedRepresentative getAuthorizedRepresentative() {

        AuthorizedRepresentative authorizedRepresentative = AuthorizedRepresentative.Factory.newInstance();
        DepartmentalPersonDto aorInfo = departmentalPersonService.getDepartmentalPerson(pdDoc);
        if (aorInfo.getPrimaryTitle() != null) {
            authorizedRepresentative.setRepresentativeTitle(aorInfo.getPrimaryTitle());
        }
        return authorizedRepresentative;
    }

    /**
     * This method creates {@link XmlObject} of type {@link AssurancesDocument} by populating data from the given
     * {@link ProposalDevelopmentDocumentContract}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocumentContract}
     */
    public XmlObject getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getAssurance();
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
