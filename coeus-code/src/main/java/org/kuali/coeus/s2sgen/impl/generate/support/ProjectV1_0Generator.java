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

import gov.grants.apply.forms.projectV10.ProjectNarrativeAttachmentsDocument;
import gov.grants.apply.forms.projectV10.ProjectNarrativeAttachmentsDocument.ProjectNarrativeAttachments;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.attachmentsV10.AttachmentGroupMin1Max100DataType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;


/**
 * Class for generating the XML object for grants.gov ProjectV1.0. Form is generated using XMLBean classes and is based on Project
 * schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@FormGenerator("ProjectV1_0Generator")
public class ProjectV1_0Generator extends ProjectBaseGenerator {

    private static final Log LOG = LogFactory.getLog(ProjectV1_0Generator.class);

    @Value("http://apply.grants.gov/forms/Project-V1.0")
    private String namespace;

    @Value("Project-V1.0")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/Project-V1.0.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.projectV10")
    private String packageName;

    @Value(DEFAULT_SORT_INDEX)
    private int sortIndex;

    /**
     * 
     * This method is used to get Narrative Attachments for Particular Project
     * 
     * @return proAttachmentsDocument {@link XmlObject} of type ProjectNarrativeAttachmentsDocument.
     */
    private ProjectNarrativeAttachmentsDocument getProjectNarrativeAttachments() {

        LOG.info("Inside Project Attachment ");
        ProjectNarrativeAttachmentsDocument proAttachmentsDocument = ProjectNarrativeAttachmentsDocument.Factory.newInstance();
        ProjectNarrativeAttachments proAttachments = ProjectNarrativeAttachments.Factory.newInstance();
        proAttachments.setFormVersion(FormVersion.v1_0.getVersion());
        AttachmentGroupMin1Max100DataType attMin1Max100DataType = AttachmentGroupMin1Max100DataType.Factory.newInstance();
        attMin1Max100DataType.setAttachedFileArray(getAttachedFileDataTypes());
        proAttachments.setAttachments(attMin1Max100DataType);
        proAttachmentsDocument.setProjectNarrativeAttachments(proAttachments);
        return proAttachmentsDocument;
    }

    /**
     * 
     * This method is used to get List of project attachments from NarrativeAttachment
     * 
     * @return AttachedFileDataType[] array of attachments for the narrative type code PROJECT_ATTACHMENTS.
     */
    private AttachedFileDataType[] getAttachedFileDataTypes() {
        return getAttachedFileDataTypes(""+PROJECT_ATTACHMENTS);
    }

    /**
     * This method creates {@link XmlObject} of type {@link ProjectNarrativeAttachmentsDocument} by populating data from the given
     * {@link ProposalDevelopmentDocumentContract}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocumentContract}
     */
    public XmlObject getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getProjectNarrativeAttachments();
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
