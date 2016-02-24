/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.award.notesandattachments.attachments;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.kuali.coeus.common.framework.attachment.AttachmentFile;
import org.kuali.coeus.common.framework.attachment.AttachmentDocumentStatus;
import org.kuali.coeus.common.framework.attachment.KcAttachmentService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.home.Award;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AwardAttachmentFormBean implements Serializable{


    private static final long serialVersionUID = 4184903707661244083L;
    
    private final AwardForm form;
    
    private AwardAttachment newAttachment;
    
    private boolean disableAttachmentRemovalIndicator=false;

    public AwardAttachmentFormBean(final AwardForm form) {
        this.form = form;
    }

    public AwardAttachment getNewAttachment() {
        if (this.newAttachment == null) {
            this.initAttachment();
        }
        
        return this.newAttachment;
    }

    private void initAttachment() {
        this.setNewAttachment(new AwardAttachment(this.getAward()));
    }

    public void setNewAttachment(AwardAttachment newAttachment) {
        this.newAttachment = newAttachment;
    }

	public boolean isDisableAttachmentRemovalIndicator() {
		return disableAttachmentRemovalIndicator;
	}

	public void setDisableAttachmentRemovalIndicator(
			boolean disableAttachmentRemovalIndicator) {
		this.disableAttachmentRemovalIndicator = disableAttachmentRemovalIndicator;
	}

    public AwardForm getForm() {
        return form;
    }

    public Award getAward() {

        if (this.form.getAwardDocument() == null) {
            throw new IllegalArgumentException("the document is null");
        }
        
        if (this.form.getAwardDocument().getAward() == null) {
            throw new IllegalArgumentException("the award is null");
        }

        return this.form.getAwardDocument().getAward();
    }

    public void addNewAwardAttachment() {
        this.refreshAttachmentReferences(Collections.singletonList(this.getNewAttachment()));
        this.syncNewFiles(Collections.singletonList(this.getNewAttachment()));
        
        this.assignDocumentId(Collections.singletonList(this.getNewAttachment()),
                this.createTypeToMaxDocNumber(this.getAward().getAwardAttachments()));
        
        this.newAttachment.setAwardId(this.getAward().getAwardId()); //OJB Hack.  Could not get the awardId to persist with anonymous access in repository file.
        this.newAttachment.setDocumentStatusCode(AttachmentDocumentStatus.ACTIVE.getCode());
        this.getAward().addAttachment(this.newAttachment);
        getBusinessObjectService().save(this.newAttachment);

        this.initNewAttachment();
    }

    public AwardAttachment retrieveExistingAttachment(int attachmentNumber) {
        if (!validIndexForList(attachmentNumber, this.getAward().getAwardAttachments())) {
            return null;
        }
        
        return this.getAward().getAwardAttachments().get(attachmentNumber);
    }

    private static boolean validIndexForList(int index, final List<?> forList) {      
        return forList != null && index >= 0 && index <= forList.size() - 1;
    }

    private void initNewAttachment() {
        this.setNewAttachment(new AwardAttachment(this.getAward()));
    }

    private Map<String, Integer> createTypeToMaxDocNumber(List<AwardAttachment> attachments) {
        
        final Map<String, Integer> typeToDocNumber = new HashMap<String, Integer>();
        
        for (AwardAttachment attachment : attachments) {
            final Integer curMax = typeToDocNumber.get(attachment.getTypeCode());
            if (curMax == null || curMax.intValue() < attachment.getDocumentId().intValue()) {
                typeToDocNumber.put(attachment.getTypeCode(), attachment.getDocumentId());
            }
        }
        
        return typeToDocNumber;
    }

    private void assignDocumentId(List<AwardAttachment> attachments,
        final Map<String, Integer> typeToDocNumber) {
        for (AwardAttachment attachment : attachments) {
            if (attachment.isNew()) {
                final Integer nextDocNumber = AwardAttachmentFormBean.createNextDocNumber(typeToDocNumber.get(attachment.getTypeCode()));
                attachment.setDocumentId(nextDocNumber);
            }
        }
    }

    private static Integer createNextDocNumber(final Integer docNumber) {
        return docNumber == null ? NumberUtils.INTEGER_ONE : Integer.valueOf(docNumber.intValue() + 1);
    }

    private void refreshAttachmentReferences(List<AwardAttachment> attachments) {
        assert attachments != null : "the attachments was null";
        
        for (final AwardAttachment attachment : attachments) {   
                attachment.refreshReferenceObject("type");
        }
    }

    private void syncNewFiles(List<AwardAttachment> attachments) {
        assert attachments != null : "the attachments was null";

        for (AwardAttachment attachment : attachments) {
            if (getKcAttachmentService().doesNewFileExist(attachment.getNewFile())) {
                final AttachmentFile newFile = AttachmentFile.createFromFormFile(attachment.getNewFile());
                //setting the sequence number to the old file sequence number
                if (attachment.getFile() != null) {
                    newFile.setSequenceNumber(attachment.getFile().getSequenceNumber());
                }
                attachment.setFile(newFile);
            }
        }
    }

    private BusinessObjectService getBusinessObjectService() {
        return KcServiceLocator.getService(BusinessObjectService.class);
    }

    private KcAttachmentService getKcAttachmentService() {
        return KcServiceLocator.getService(KcAttachmentService.class);
    }

}
