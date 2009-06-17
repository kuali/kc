/*
 * Copyright 2006-2009 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the License);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.bo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerException;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.SpecialReview;
import org.kuali.kra.bo.SpecialReviewApprovalType;
import org.kuali.kra.bo.ValidSpecialReviewApproval;
import org.kuali.rice.kns.util.TypedArrayList;

/**
 * Class representing a Proposal Special Review.
 */
public class ProposalSpecialReview extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = -2375697998735262371L;

    private String proposalNumber;
    private Integer specialReviewNumber;
    private Date applicationDate;
    private Date approvalDate;
    private Date expirationDate;
    private String approvalTypeCode;
    private String comments;
    private String protocolNumber;
    private String specialReviewCode;
    private SpecialReview specialReview;
    private SpecialReviewApprovalType specialReviewApprovalType;

    private ValidSpecialReviewApproval validSpecialReviewApproval;

    private String[] exemptNumbers;
    private List<ProposalExemptNumber> proposalExemptNumbers = new ArrayList<ProposalExemptNumber>();
    
    /** {@inheritDoc} */
    @Override
    public void afterLookup(PersistenceBroker persistenceBroker) throws PersistenceBrokerException {
        super.afterLookup(persistenceBroker);
        this.syncProposalExemptNumbersToExemptNumbers();
    }
    
    public String getProposalNumber() {
        return this.proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public Integer getSpecialReviewNumber() {
        return this.specialReviewNumber;
    }

    public void setSpecialReviewNumber(Integer specialReviewNumber) {
        this.specialReviewNumber = specialReviewNumber;
    }

    public Date getApplicationDate() {
        return this.applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Date getApprovalDate() {
        return this.approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getApprovalTypeCode() {
        return this.approvalTypeCode;
    }

    public void setApprovalTypeCode(String approvalTypeCode) {
        this.approvalTypeCode = approvalTypeCode;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getProtocolNumber() {
        return this.protocolNumber;
    }

    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public String getSpecialReviewCode() {
        return this.specialReviewCode;
    }

    public void setSpecialReviewCode(String specialReviewCode) {
        this.specialReviewCode = specialReviewCode;
    }

    /** {@inheritDoc} */
    @Override
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("proposalNumber", this.getProposalNumber());
        hashMap.put("specialReviewNumber", this.getSpecialReviewNumber());
        hashMap.put("applicationDate", this.getApplicationDate());
        hashMap.put("approvalDate", this.getApprovalDate());
        hashMap.put("approvalTypeCode", this.getApprovalTypeCode());
        hashMap.put("comments", this.getComments());
        hashMap.put("protocolNumber", this.getProtocolNumber());
        hashMap.put("specialReviewCode", this.getSpecialReviewCode());
        return hashMap;
    }

    public ValidSpecialReviewApproval getValidSpecialReviewApproval() {
        return this.validSpecialReviewApproval;
    }

    public void setValidSpecialReviewApproval(ValidSpecialReviewApproval validSpecialReviewApproval) {
        this.validSpecialReviewApproval = validSpecialReviewApproval;
    }

    public SpecialReview getSpecialReview() {
        return this.specialReview;
    }

    public void setSpecialReview(SpecialReview specialReview) {
        this.specialReview = specialReview;
    }

    public SpecialReviewApprovalType getSpecialReviewApprovalType() {
        return this.specialReviewApprovalType;
    }

    public void setSpecialReviewApprovalType(SpecialReviewApprovalType specialReviewApprovalType) {
        this.specialReviewApprovalType = specialReviewApprovalType;
    }

    public Date getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
    
    public List<ProposalExemptNumber> getProposalExemptNumbers() {
        return this.proposalExemptNumbers;
    }
    public void setProposalExemptNumbers(List<ProposalExemptNumber> proposalExemptNumbers) {
        this.proposalExemptNumbers = proposalExemptNumbers;
        this.syncProposalExemptNumbersToExemptNumbers();

    }
    
    public String[] getExemptNumbers() {
        return this.exemptNumbers;
    }

    public void setExemptNumbers(String[] exemptNumbers) {
        this.exemptNumbers = exemptNumbers;
        this.syncExemptNumbersToProposalExemptNumbers();
    }
    
    /**
     * This method syncs the exempt numbers (the selected exempt numbers)
     * with the proposal exempt numbers (the persisted BOs).
     * 
     * This method is need to ensure that when a user selects exempt number(s)
     * on the page the corresponding BOs are created so that they can be persisted.
     */
    private void syncExemptNumbersToProposalExemptNumbers() {
        
        if (this.exemptNumbers == null) {
            return;
        }
        
        this.proposalExemptNumbers = new ArrayList<ProposalExemptNumber>();
        for (final String number : this.exemptNumbers) {
            if (number != null) {
                final ProposalExemptNumber exempt = new ProposalExemptNumber();
                
                exempt.setExemptionTypeCode(number);
                exempt.setProposalNumber(this.getProposalNumber());
                exempt.setSpecialReviewNumber(this.getSpecialReviewNumber());
                
                this.proposalExemptNumbers.add(exempt);
            }
        }
    }
    
    /**
     * This method syncs the proposal exempt numbers (the persisted BOs)
     * with the exempt numbers (the selected exempt numbers).
     * 
     * This method is need to ensure that when the page loads the currently
     * selected exempt numbers are correctly highlighted on the page.
     */
    private void syncProposalExemptNumbersToExemptNumbers() {
        
        if (this.proposalExemptNumbers == null) {
            return;
        }
        
        this.exemptNumbers = new String[this.proposalExemptNumbers.size()];
        
        for (int i = 0; i < this.proposalExemptNumbers.size(); i++) {
            this.exemptNumbers[i] = this.proposalExemptNumbers.get(i).getExemptionTypeCode();
        }
    }
}

