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

import gov.grants.apply.forms.otherV10.OtherNarrativeAttachmentsDocument;
import gov.grants.apply.forms.otherV10.OtherNarrativeAttachmentsDocument.OtherNarrativeAttachments;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.attachmentsV10.AttachmentGroupMin1Max100DataType;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;


/**
 * Class for generating the XML object for grants.gov OtherV1_0. Form is generated using XMLBean classes and is based on OtherV1_0
 * schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@FormGenerator("OtherV1_0Generator")
public class OtherV1_0Generator extends OtherBaseGenerator {

    /**
     * 
     * This method is used to get Other Narrative Attachments
     * 
     * @return otherNarrativeAttachmentsDocument {@link XmlObject} of type OtherNarrativeAttachmentsDocument.
     */
    private OtherNarrativeAttachmentsDocument getOtherNarrativeAttachments() {

        OtherNarrativeAttachmentsDocument otherNarrativeAttachmentsDocument = OtherNarrativeAttachmentsDocument.Factory
                .newInstance();
        OtherNarrativeAttachments otherNarrativeAttachments = OtherNarrativeAttachments.Factory.newInstance();
        otherNarrativeAttachments.setFormVersion(FormVersion.v1_0.getVersion());
        AttachmentGroupMin1Max100DataType attachmentGroupMin1Max100DataType = AttachmentGroupMin1Max100DataType.Factory
                .newInstance();
        attachmentGroupMin1Max100DataType.setAttachedFileArray(getAttachedFileDataTypes());
        otherNarrativeAttachments.setAttachments(attachmentGroupMin1Max100DataType);
        otherNarrativeAttachmentsDocument.setOtherNarrativeAttachments(otherNarrativeAttachments);
        return otherNarrativeAttachmentsDocument;
    }

    /**
     * 
     * This method is used to get List of Other attachments from NarrativeAttachment
     * 
     * @return AttachedFileDataType[] based on the narrative type code.
     */
    private AttachedFileDataType[] getAttachedFileDataTypes() {
        return getAttachedFileDataTypes(""+OTHER_ATTACHMENTS_FORM);
    }

    /**
     * This method creates {@link XmlObject} of type {@link OtherNarrativeAttachmentsDocument} by populating data from the given
     * {@link ProposalDevelopmentDocumentContract}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocumentContract}
     */
    public XmlObject getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getOtherNarrativeAttachments();
    }
}
