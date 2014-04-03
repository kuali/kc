/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.subaward.templateAttachments;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.kuali.coeus.common.framework.attachment.AttachmentFile;
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
    
    private void syncNewFiles(List<SubAwardAttachments> attachments) {
        assert attachments != null : "the attachments was null";
        
        for (SubAwardAttachments attachment : attachments) {
            if (SubAwardAttachmentFormBean.doesNewFileExist(attachment)) {
                final AttachmentFile newFile = AttachmentFile.createFromFormFile(attachment.getNewFile());
                //setting the sequence number to the old file sequence number
                if (attachment.getFile() != null) {
                    newFile.setSequenceNumber(attachment.getFile().getSequenceNumber());
                }
                attachment.setFile(newFile);
            }
        }
    }
    /**
     * Checks if a new file exists on an attachment
     * 
     * @param attachment the attachment
     * @return true if new false if not
     */
    private static boolean doesNewFileExist(SubAwardAttachments attachment) {
        return attachment.getNewFile() != null && StringUtils.isNotBlank(attachment.getNewFile().getFileName());
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
        this.syncNewFiles(Collections.singletonList(this.getNewAttachment()));
        
        this.assignDocumentId(Collections.singletonList(this.getNewAttachment()), 
                this.createTypeToMaxDocNumber(this.getSubAward().getSubAwardAttachments()));
        
        this.newAttachment.setSubAwardId(this.getSubAward().getSubAwardId()); //OJB Hack.  Could not get the awardId to persist with anonymous access in repository file.
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

}
