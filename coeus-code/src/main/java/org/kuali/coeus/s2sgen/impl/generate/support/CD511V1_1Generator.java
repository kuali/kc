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

import gov.grants.apply.forms.cd511V11.CD511Document;
import gov.grants.apply.forms.cd511V11.CD511Document.CD511;
import org.apache.xmlbeans.XmlObject;

import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.kuali.coeus.s2sgen.impl.generate.S2SBaseFormGenerator;
import org.kuali.coeus.s2sgen.impl.person.DepartmentalPersonDto;
import org.kuali.coeus.s2sgen.impl.person.DepartmentalPersonService;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.util.Calendar;

/**
 * 
 * This class is used to generate XML Document object for grants.gov CD511V1.1. This form is generated using XMLBean API's generated
 * by compiling CD511V1_1 schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@FormGenerator("CD511V1_1Generator")
public class CD511V1_1Generator extends S2SBaseFormGenerator {

    @Value("http://apply.grants.gov/forms/CD511-V1.1")
    private String namespace;

    @Value("CD511-V1.1")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/CD511-V1.1.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.CD511V11")
    private String packageName;

    @Value(DEFAULT_SORT_INDEX)
    private int sortIndex;

    @Autowired
    @Qualifier("departmentalPersonService")
    private DepartmentalPersonService departmentalPersonService;

    private DepartmentalPersonDto aorInfo;

    /**
     * 
     * This method returns CD511Document object based on proposal development document which contains the CD511Document informations
     * OrganizationName,AwardNumber,ProjectName,ContactName,Title,Signature,SubmittedDate for a particular proposal
     * 
     * @return cd511Document(CD511Document){@link XmlObject} of type CD511Document.
     */
    private CD511Document getcd511Document() {
        CD511Document cd511Document = CD511Document.Factory.newInstance();
        CD511 cd511 = CD511.Factory.newInstance();
        cd511.setFormVersion(FormVersion.v1_1.getVersion());

        if (pdDoc.getDevelopmentProposal().getApplicantOrganization() != null) {
            cd511.setOrganizationName(pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization().getOrganizationName());
        }
        if (pdDoc.getDevelopmentProposal().getCurrentAwardNumber() != null && !pdDoc.getDevelopmentProposal().getCurrentAwardNumber().equals("")) {
            cd511.setAwardNumber(pdDoc.getDevelopmentProposal().getCurrentAwardNumber());
        }
        if (pdDoc.getDevelopmentProposal().getTitle() != null && !pdDoc.getDevelopmentProposal().getTitle().equals("")) {
            cd511.setProjectName(pdDoc.getDevelopmentProposal().getTitle());
        }

        String title = "";
        if (aorInfo != null) {
            cd511.setContactName(globLibV20Generator.getHumanNameDataType(aorInfo));
            if (aorInfo.getPrimaryTitle() != null && !aorInfo.getPrimaryTitle().equals("")) {
                title = aorInfo.getPrimaryTitle();
            }
        }
        cd511.setTitle(title);

        // As per Coeus,CD511-V1.1 data analysis file said:
        // if this application is submitted through Grants.gov leave signature to blank
        cd511.setSignature("  ");
        cd511.setSubmittedDate(Calendar.getInstance());
        cd511Document.setCD511(cd511);
        return cd511Document;
    }

    /**
     * This method creates {@link XmlObject} of type {@link CD511Document} by populating data from the given
     * {@link ProposalDevelopmentDocumentContract}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocumentContract}
     */
    public XmlObject getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        aorInfo = departmentalPersonService.getDepartmentalPerson(pdDoc);
        return getcd511Document();
    }

    public DepartmentalPersonService getDepartmentalPersonService() {
        return departmentalPersonService;
    }

    public void setDepartmentalPersonService(DepartmentalPersonService departmentalPersonService) {
        this.departmentalPersonService = departmentalPersonService;
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
