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

import gov.grants.apply.forms.otherV11.OtherNarrativeAttachmentsDocument;
import gov.grants.apply.forms.otherV11.OtherNarrativeAttachmentsDocument.OtherNarrativeAttachments;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.attachmentsV10.AttachmentGroupMin1Max100DataType;

import java.util.ArrayList;
import java.util.List;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.util.S2SConstants;

/**
 * Class for generating the XML object for grants.gov OtherV1_1. Form is generated using XMLBean classes and is based on OtherV1_1
 * schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class OtherV1_1Generator extends OtherBaseGenerator {

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
        otherNarrativeAttachments.setFormVersion(S2SConstants.FORMVERSION_1_1);
        AttachmentGroupMin1Max100DataType attachmentGroupMin1Max100DataType = AttachmentGroupMin1Max100DataType.Factory
                .newInstance();
        attachmentGroupMin1Max100DataType.setAttachedFileArray(getAttachedFileDataTypes());
        otherNarrativeAttachments.setAttachments(attachmentGroupMin1Max100DataType);
        otherNarrativeAttachmentsDocument.setOtherNarrativeAttachments(otherNarrativeAttachments);
        return otherNarrativeAttachmentsDocument;
    }

    /**
     * 
     * This method is used to get List of Other attachments from NarrativeAttachmentList
     * 
     * @return AttachedFileDataType[] based on the narrative type code.
     */
    private AttachedFileDataType[] getAttachedFileDataTypes() {

        List<AttachedFileDataType> attachedFileDataTypeList = new ArrayList<AttachedFileDataType>();
        AttachedFileDataType attachedFileDataType = null;
        for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeTypeCode() != null
                    && Integer.parseInt(narrative.getNarrativeTypeCode()) == OTHER_ATTACHMENTS_FORM) {
            	attachedFileDataType = getAttachedFileType(narrative);
            	if(attachedFileDataType != null){
            		attachedFileDataTypeList.add(attachedFileDataType);
            	}
            }
        }
        return attachedFileDataTypeList.toArray(new AttachedFileDataType[0]);
    }

    /**
     * This method creates {@link XmlObject} of type {@link OtherNarrativeAttachmentsDocument} by populating data from the given
     * {@link ProposalDevelopmentDocument}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocument}
     * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(ProposalDevelopmentDocument)
     */
    public XmlObject getFormObject(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getOtherNarrativeAttachments();
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
        OtherNarrativeAttachments othNarrativeAttachments = (OtherNarrativeAttachments) xmlObject;
        OtherNarrativeAttachmentsDocument othNarrativeAttachmentsDocument = OtherNarrativeAttachmentsDocument.Factory.newInstance();
        othNarrativeAttachmentsDocument.setOtherNarrativeAttachments(othNarrativeAttachments);
        return othNarrativeAttachmentsDocument;
    }

}
