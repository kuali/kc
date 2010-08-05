/*
 * Copyright 2005-2010 The Kuali Foundation.
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
package org.kuali.kra.s2s.generator.impl;

import gov.grants.apply.forms.projectV10.ProjectNarrativeAttachmentsDocument;
import gov.grants.apply.forms.projectV10.ProjectNarrativeAttachmentsDocument.ProjectNarrativeAttachments;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.attachmentsV10.AttachmentGroupMin1Max100DataType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.util.S2SConstants;

/**
 * Class for generating the XML object for grants.gov ProjectV1.0. Form is generated using XMLBean classes and is based on Project
 * schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProjectV1_0Generator extends ProjectBaseGenerator {

    private static final Log LOG = LogFactory.getLog(ProjectV1_0Generator.class);

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
        proAttachments.setFormVersion(S2SConstants.FORMVERSION_1_0);
        AttachmentGroupMin1Max100DataType attMin1Max100DataType = AttachmentGroupMin1Max100DataType.Factory.newInstance();
        attMin1Max100DataType.setAttachedFileArray(getAttachedFileDataTypes());
        proAttachments.setAttachments(attMin1Max100DataType);
        proAttachmentsDocument.setProjectNarrativeAttachments(proAttachments);
        return proAttachmentsDocument;
    }

    /**
     * 
     * This method is used to get List of project attachments from NarrativeAttachmentList
     * 
     * @return AttachedFileDataType[] array of attachments for the narrative type code PROJECT_ATTACHMENTS.
     */
    private AttachedFileDataType[] getAttachedFileDataTypes() {
//        Log.info("Getting AttachedFileDataType ");
//        List<AttachedFileDataType> attachedFileDataTypeList = new ArrayList<AttachedFileDataType>();
//        AttachedFileDataType attachedFileDataType = null;
//        for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
//            if (narrative.getNarrativeTypeCode() != null
//                    && Integer.parseInt(narrative.getNarrativeTypeCode()) == PROJECT_ATTACHMENTS) {
//            	attachedFileDataType = getAttachedFileType(narrative);
//            	if(attachedFileDataType != null){
//            		attachedFileDataTypeList.add(attachedFileDataType);
//            	}
//                Log.info("Attachmentcount" + attachedFileDataTypeList.size());
//            }
//        }
//        return attachedFileDataTypeList.toArray(new AttachedFileDataType[0]);
        return getAttachedFileDataTypes(""+PROJECT_ATTACHMENTS);
    }

    /**
     * This method creates {@link XmlObject} of type {@link ProjectNarrativeAttachmentsDocument} by populating data from the given
     * {@link ProposalDevelopmentDocument}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocument}
     * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(ProposalDevelopmentDocument)
     */
    public XmlObject getFormObject(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getProjectNarrativeAttachments();
    }

    /**
     * This method typecasts the given {@link XmlObject} to the required generator type and returns back the document of that
     * generator type.
     * 
     * @param xmlObject which needs to be converted to the document type of the required generator
     * @return {@link XmlObject} document of the required generator type
     * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(XmlObject)
     */
    public XmlObject getFormObject(XmlObject xmlObject) {
        ProjectNarrativeAttachments proAttachments = (ProjectNarrativeAttachments) xmlObject;
        ProjectNarrativeAttachmentsDocument proAttachmentsDocument = ProjectNarrativeAttachmentsDocument.Factory.newInstance();
        proAttachmentsDocument.setProjectNarrativeAttachments(proAttachments);
        return proAttachmentsDocument;
    }

}
