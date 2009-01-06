/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.bo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.SpecialReview;
import org.kuali.kra.bo.SpecialReviewApprovalType;
import org.kuali.kra.bo.ValidSpecialReviewApproval;
import org.kuali.kra.bo.AbstractSpecialReviewExemption;

/**
 * This class...
 */
public abstract class AbstractSpecialReview<T> extends KraPersistableBusinessObjectBase {
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 3671069178534624665L;
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
    private List<T> specialReviewExemptions;
    private String[] exemptionTypeCodes;
    
    public AbstractSpecialReview() {
        super();
        specialReviewExemptions = new ArrayList<T>();
    }

    public Integer getSpecialReviewNumber() {
        return specialReviewNumber;
    }

    public void setSpecialReviewNumber(Integer specialReviewNumber) {
        this.specialReviewNumber = specialReviewNumber;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getApprovalTypeCode() {
        return approvalTypeCode;
    }

    public void setApprovalTypeCode(String approvalTypeCode) {
        this.approvalTypeCode = approvalTypeCode;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getProtocolNumber() {
        return protocolNumber;
    }

    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public String getSpecialReviewCode() {
        return specialReviewCode;
    }

    public void setSpecialReviewCode(String specialReviewCode) {
        this.specialReviewCode = specialReviewCode;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        managedLists.add(getSpecialReviewExemptions());
        return managedLists;
    }

    public ValidSpecialReviewApproval getValidSpecialReviewApproval() {
        return validSpecialReviewApproval;
    }

    public void setValidSpecialReviewApproval(ValidSpecialReviewApproval validSpecialReviewApproval) {
        this.validSpecialReviewApproval = validSpecialReviewApproval;
    }

    public SpecialReview getSpecialReview() {
        return specialReview;
    }

    public void setSpecialReview(SpecialReview specialReview) {
        this.specialReview = specialReview;
    }

    public SpecialReviewApprovalType getSpecialReviewApprovalType() {
        return specialReviewApprovalType;
    }

    public void setSpecialReviewApprovalType(SpecialReviewApprovalType specialReviewApprovalType) {
        this.specialReviewApprovalType = specialReviewApprovalType;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
    
    public T getSpecialReviewExemption(int index) {
        return specialReviewExemptions.get(index);
    }
    
    public List<T> getSpecialReviewExemptions() {
        return specialReviewExemptions;
    }
    public void setSpecialReviewExemptions(List<T> specialReviewExemptions) {
        this.specialReviewExemptions = specialReviewExemptions;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = new LinkedHashMap();
        hashMap.put("specialReviewNumber", getSpecialReviewNumber());
        hashMap.put("applicationDate", getApplicationDate());
        hashMap.put("approvalDate", getApprovalDate());
        hashMap.put("approvalTypeCode", getApprovalTypeCode());
        hashMap.put("comments", getComments());
        hashMap.put("protocolNumber", getProtocolNumber());
        hashMap.put("specialReviewCode", getSpecialReviewCode());
        return hashMap;
    }

    public abstract T newSpecialReviewExemption(String exemptionTypeCode);
    
    public void addSpecialReviewExemption(String exemptionTypeCode){
      getSpecialReviewExemptions().add(newSpecialReviewExemption(exemptionTypeCode));
    }

    /**
     * Gets the exemptionTypeCode attribute. 
     * @return Returns the exemptionTypeCode.
     */
    @SuppressWarnings("unchecked")
    public String[] getExemptionTypeCodes() {
        if(exemptionTypeCodes==null){
            exemptionTypeCodes = new String[specialReviewExemptions.size()];
            int i = 0;
            for (Iterator iterator = specialReviewExemptions.iterator(); iterator.hasNext();) {
                AbstractSpecialReviewExemption specialReviewExemption = 
                    (AbstractSpecialReviewExemption) iterator.next();
                exemptionTypeCodes[i++] = specialReviewExemption.getExemptionTypeCode();
            }
        }
        return exemptionTypeCodes;
    }

    /**
     * Sets the exemptionTypeCode attribute value.
     * @param exemptionTypeCodes The exemptionTypeCode to set.
     */
    public void setExemptionTypeCodes(String... exemptionTypeCodes) {
        this.exemptionTypeCodes = exemptionTypeCodes;
    }

}
