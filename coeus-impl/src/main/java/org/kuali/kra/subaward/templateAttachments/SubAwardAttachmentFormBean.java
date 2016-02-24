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
package org.kuali.kra.subaward.templateAttachments;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.kuali.coeus.common.framework.attachment.AttachmentDocumentStatus;
import org.kuali.coeus.common.framework.attachment.KcAttachmentService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.subaward.SubAwardForm;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.bo.SubAwardAttachments;
import org.kuali.kra.subaward.bo.SubAwardReports;
import org.kuali.rice.krad.service.BusinessObjectService;



public class SubAwardAttachmentFormBean implements Serializable {
   
   
    private final SubAwardForm form;
    
    private SubAwardAttachments newAttachment;
    
    private SubAwardReports newReport;
    
    private boolean disableAttachmentRemovalIndicator=false;

    /**
     * Gets the newReport attribute. 
     * @return Returns the newReport.
     */
    public SubAwardReports getNewReport() {
        if (this.newReport == null) {
            this.initReport();
        }
        
        return this.newReport;
    }

    /**
     * Sets the newReport attribute value.
     * @param newReport The newReport to set.
     */
    public void setNewReport(SubAwardReports newReport) {
        this.newReport = newReport;
    }

	public SubAwardAttachmentFormBean(final SubAwardForm form) {
        this.form = form;
    }

    /**
     * Gets the new attachment. This method will not return null.
     * @return the new attachment
     */
    public SubAwardAttachments getNewAttachment() {
        if (this.newAttachment == null) {
            this.initAttachment();
        }
        
        return this.newAttachment;
    }
    
    /**
     * initializes a new report setting the subaward id.
     */
    private void initReport() {
        this.setNewReport(new SubAwardReports(this.getSubAward()));
    }
    /**
     * initializes a new attachment setting the subaward id.
     */
    
    private void initAttachment() {
        this.setNewAttachment(new SubAwardAttachments(this.getSubAward()));
    }

    public void setNewAttachment(SubAwardAttachments newAttachment) {
        this.newAttachment = newAttachment;
    }

    public boolean isDisableAttachmentRemovalIndicator() {
		return disableAttachmentRemovalIndicator;
	}

	public void setDisableAttachmentRemovalIndicator(
			boolean disableAttachmentRemovalIndicator) {
		this.disableAttachmentRemovalIndicator = disableAttachmentRemovalIndicator;
	}

	public SubAwardForm getForm() {
        return form;
    }
    
    public SubAward getSubAward() {

        if (this.form.getSubAwardDocument() == null) {
            throw new IllegalArgumentException("the document is null");
        }
        
        if (this.form.getSubAwardDocument().getSubAward() == null) {
            throw new IllegalArgumentException("the subaward is null");
        }

        return this.form.getSubAwardDocument().getSubAward();
    }
    
    /**
     * Creates a map containing the highest doc number from a collection of attachments for each type code.
     * @param <T> the type
     * @param attachments the collection of attachments
     * @return the map
     */
    private Map<String, Integer> createTypeToMaxDocNumber(List<SubAwardAttachments> attachments) {
        
        final Map<String, Integer> typeToDocNumber = new HashMap<String, Integer>();
        
         for (SubAwardAttachments attachment : attachments) {
            final Integer curMax = typeToDocNumber.get(attachment.getSubAwardAttachmentTypeCode());
            if (curMax == null || curMax.intValue() < attachment.getDocumentId().intValue()) {
                typeToDocNumber.put(attachment.getSubAwardAttachmentTypeCode(), attachment.getDocumentId());
            }
        }
        
        return typeToDocNumber;
    }
    
    
    public SubAwardAttachments retrieveExistingAttachment(int attachmentNumber) {
        if (!validIndexForList(attachmentNumber, this.getSubAward().getSubAwardAttachments())) {
            return null;
        }
        
        return this.getSubAward().getSubAwardAttachments().get(attachmentNumber);
    }
    
    private void initNewAttachment() {
        this.setNewAttachment(new SubAwardAttachments(this.getSubAward()));
    }
    
    private void initNewReport() {
        this.setNewReport(new SubAwardReports(this.getSubAward()));
    }
    
    private static boolean validIndexForList(int index, final List<?> forList) {      
        return forList != null && index >= 0 && index <= forList.size() - 1;
    }
    
    
    /** 
     * refreshes a given Collection of attachment's references that can change.
     * @param attachments the attachments.
     */
    private void refreshAttachmentReferences(List<SubAwardAttachments> attachments) {
        assert attachments != null : "the attachments was null";
        
        for (final SubAwardAttachments attachment : attachments) {   
                attachment.refreshReferenceObject("typeAttachment");
        }
    }
    
    /** 
     * refreshes a given Collection of attachment's references that can change.
     * @param attachments the attachments.
     */
    private void refreshReportReferences(List<SubAwardReports> reports) {
        assert reports != null : "the report was null";
        
        for (final SubAwardReports report : reports) {   
            report.refreshReferenceObject("typeCode");
        }
    }

    /** 
     * assigns a document id to all attachments in the passed in collection based on the passed in type to doc number map. 
     * 
     * @param <T> the type of attachments
     * @param attachments the collection of attachments that require doc number assignment
     * @param typeToDocNumber the map that contains all the highest doc numbers of existing attachments based on type code
     */
    
    private void assignDocumentId(List<SubAwardAttachments> attachments,
            final Map<String, Integer> typeToDocNumber) {
            for (SubAwardAttachments attachment : attachments) {
                if (attachment.isNew()) {
                    final Integer nextDocNumber = SubAwardAttachmentFormBean.createNextDocNumber(typeToDocNumber.get(attachment.getSubAwardAttachmentTypeCode()));
                   attachment.setDocumentId(nextDocNumber);
                }
            }
        }
    
    /**
     * Creates the next doc number from a passed in doc number.  If null 1 is returned.
     * @param docNumber the doc number to base the new number off of.
     * @return the new doc number.
     */
    private static Integer createNextDocNumber(final Integer docNumber) {
        return docNumber == null ? NumberUtils.INTEGER_ONE : Integer.valueOf(docNumber.intValue() + 1);
    }
    
    /**
     * Adds the "new" Attachment to the SubAward.  Before
     * adding this method executes validation.  If the validation fails the attachment is not added.
     */
    public void addNewAwardAttachment() {
        this.refreshAttachmentReferences(Collections.singletonList(this.getNewAttachment()));
        
        this.assignDocumentId(Collections.singletonList(this.getNewAttachment()),
                this.createTypeToMaxDocNumber(this.getSubAward().getSubAwardAttachments()));
        
        this.newAttachment.setSubAward(this.getSubAward());
        this.newAttachment.setDocumentStatusCode(AttachmentDocumentStatus.ACTIVE.getCode());
        this.getSubAward().addAttachment(this.newAttachment);
        getBusinessObjectService().save(this.newAttachment);
        this.initNewAttachment();
    }
    
    
    /**
     * Adds the "new" Report to the SubAward.  Before
     * adding this method executes validation.  If the validation fails the attachment is not added.
     */
    public void addNewReport(){
        this.refreshReportReferences(Collections.singletonList(this.getNewReport()));
        this.newReport.setSubAwardId(this.getSubAward().getSubAwardId()); //OJB Hack.  Could not get the awardId to persist with anonymous access in repository file.
        this.getSubAward().addReport(this.newReport);
        getBusinessObjectService().save(this.newReport); 
        this.initNewReport();
    }
    
    private BusinessObjectService getBusinessObjectService() {
        return KcServiceLocator.getService(BusinessObjectService.class);
    }

    private KcAttachmentService getKcAttachmentService() {
        return KcServiceLocator.getService(KcAttachmentService.class);
    }

}
