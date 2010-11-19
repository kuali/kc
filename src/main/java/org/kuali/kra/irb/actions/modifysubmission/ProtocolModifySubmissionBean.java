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
package org.kuali.kra.irb.actions.modifysubmission;

import java.io.Serializable;
import java.util.List;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.actions.ActionHelper;
import org.kuali.kra.irb.actions.ProtocolActionBean;
import org.kuali.kra.irb.actions.submit.CheckListService;
import org.kuali.kra.irb.actions.submit.ExemptStudiesCheckListItem;
import org.kuali.kra.irb.actions.submit.ExpeditedReviewCheckListItem;
import org.kuali.kra.irb.actions.submit.ProtocolExemptStudiesCheckListItem;
import org.kuali.kra.irb.actions.submit.ProtocolExpeditedReviewCheckListItem;
import org.kuali.kra.irb.actions.submit.ProtocolReviewType;

/**
 * This class is really just a "form" for editing a protocol submission.
 */
public class ProtocolModifySubmissionBean extends ProtocolActionBean implements Serializable {

    private static final long serialVersionUID = 6809400244968716321L;
    
    private String submissionTypeCode = "";
    private String protocolReviewTypeCode = "";
    private String submissionQualifierTypeCode = "";
    private boolean billable;

    private List<ExpeditedReviewCheckListItem> expeditedReviewCheckList;
    private List<ExemptStudiesCheckListItem> exemptStudiesCheckList;
    
    private int checkListItemDescriptionIndex = 0;
    private String selectedProtocolReviewTypeCode = null;
    
    /**
     * Constructs a ProtocolModifySubmissionBean.
     * @param actionHelper Reference back to the action helper for this bean
     */
    public ProtocolModifySubmissionBean(ActionHelper actionHelper) {
        super(actionHelper);
        
        this.submissionTypeCode = actionHelper.getProtocol().getProtocolSubmission().getProtocolSubmissionType().getSubmissionTypeCode();
        this.submissionQualifierTypeCode = actionHelper.getProtocol().getProtocolSubmission().getSubmissionTypeQualifierCode();
        this.protocolReviewTypeCode = actionHelper.getProtocol().getProtocolSubmission().getProtocolReviewTypeCode();
        this.billable = actionHelper.getProtocol().getProtocolSubmission().isBillable();
        
        expeditedReviewCheckList = getCheckListService().getExpeditedReviewCheckList();        
        for (ExpeditedReviewCheckListItem displayItem : expeditedReviewCheckList) {
            for (ProtocolExpeditedReviewCheckListItem databaseItem : actionHelper.getProtocol().getProtocolSubmission().getExpeditedReviewCheckList()) {
                if (displayItem.getExpeditedReviewCheckListCode().equals(databaseItem.getExpeditedReviewCheckListCode())) {
                    displayItem.setChecked(true);
                }
            }
        }
        
        exemptStudiesCheckList = getCheckListService().getExemptStudiesCheckList();
        for (ExemptStudiesCheckListItem displayItem : exemptStudiesCheckList) {
            for (ProtocolExemptStudiesCheckListItem databaseItem : actionHelper.getProtocol().getProtocolSubmission().getExemptStudiesCheckList()) {
                if (displayItem.getExemptStudiesCheckListCode().equals(databaseItem.getExemptStudiesCheckListCode())) {
                    displayItem.setChecked(true);
                }
            }
        }
    }

    /**
     * Prepare the Modify Protocol Submission for rendering with JSP.
     * Leaving this function in place in case it is needed later.
     */
    public void prepareView() {
    }

    public String getSubmissionTypeCode() {
        return submissionTypeCode;
    }

    public void setSubmissionTypeCode(String submissionTypeCode) {
        this.submissionTypeCode = submissionTypeCode;
    }
    
    public String getProtocolReviewTypeCode() {
        return protocolReviewTypeCode;
    }

    public void setProtocolReviewTypeCode(String protocolReviewTypeCode) {
        this.protocolReviewTypeCode = protocolReviewTypeCode;
    }
    
    public String getSubmissionQualifierTypeCode() {
        return submissionQualifierTypeCode;
    }

    public void setSubmissionQualifierTypeCode(String submissionQualifierTypeCode) {
        this.submissionQualifierTypeCode = submissionQualifierTypeCode;
    }
    
    public void setExpeditedReviewCheckList(List<ExpeditedReviewCheckListItem> checkList) {
        this.expeditedReviewCheckList = checkList;
    }
    
    public List<ExpeditedReviewCheckListItem> getExpeditedReviewCheckList() {
        return expeditedReviewCheckList;
    }
    
    public void setExemptStudiesCheckList(List<ExemptStudiesCheckListItem> checkList) {
        this.exemptStudiesCheckList = checkList;
    }
    
    public List<ExemptStudiesCheckListItem> getExemptStudiesCheckList() {
        return exemptStudiesCheckList;
    }
    
    public boolean isBillable() {
        return billable;
    }

    public void setBillable(boolean billable) {
        this.billable = billable;
    }
    
    /**
     * When a user wants to display the entire description of check list item,
     * the currently selected protocol review type and the index of the check list
     * item are stored here for later rendering.
     * @param protocolReviewTypeCode
     * @param index
     */
    public void setCheckListItemDescriptionInfo(String protocolReviewTypeCode, int index) {
        this.selectedProtocolReviewTypeCode = protocolReviewTypeCode;
        checkListItemDescriptionIndex = index;   
    }
    
    /**
     * Get the description of the check list item that 
     * was specified in setCheckListItemDescriptionInfo().
     * @return
     */
    public String getCheckListItemDescription() {
        String retVal = "";
        if (ProtocolReviewType.EXPEDITED_REVIEW_TYPE_CODE.equals(selectedProtocolReviewTypeCode)) {
            retVal = getExpeditedReviewCheckList().get(checkListItemDescriptionIndex).getDescription();
        } else if (ProtocolReviewType.EXEMPT_STUDIES_REVIEW_TYPE_CODE.equals(selectedProtocolReviewTypeCode)) {
            retVal = getExemptStudiesCheckList().get(checkListItemDescriptionIndex).getDescription();
        }
        return retVal;
    }
    
    private CheckListService getCheckListService() {
        return KraServiceLocator.getService(CheckListService.class);
    }
}
